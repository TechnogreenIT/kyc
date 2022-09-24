
package com.tes.controller.management;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tes.controller.base.BaseManagementController;
import com.tes.controller.environmentalofficer.consent.ConsentController;
import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.Settings;
import com.tes.model.WaterInventory;
import com.tes.repository.environmentalofficer.waterinventory.RegWaterSourceDataRepository;
import com.tes.services.SettingServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.services.environmentalofficer.WaterConGenComparativeSheetServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.utilities.Constant;
import com.tes.utilities.GenericConstantArrayList;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate used of the Management Dashbord operation i.e.Profile Manage.
 * 
 * @author Jemish Moradiya
 */
@RestController
@SessionAttributes({"empDataSession", "imgvalue", "emplogindata", "sessionIndustryType", "uId", "userRole", "companyName", "userProfilePic"})
public class ManagementController extends BaseManagementController
{

	@Autowired
	UsersServices usersServices;

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	SettingServices settingServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	RegWaterSourceDataServices regWaterSourceDataServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	WaterConGenComparativeSheetServices waterConGenComparativeSheetServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	UnitServices unitServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	RegWaterSourceDataRepository regWaterSourceDataRepository;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to display the Management dashboard.
	 * 
	 * @param request The servlet request we are processing.
	 * @return management dashboard
	 * @throws IOException if an input/output error occurs
	 * @throws ServletException if a servlet-specified error occurs
	 */
	@RequestMapping("/dashboard")
	public ModelAndView ManagementDashboard(HttpServletRequest request) throws IOException, ServletException
	{
		ModelAndView modelAndView = new ModelAndView();

		try
		{

			modelAndView.setViewName("Management/ManagementDashboard");
			int usersId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			EmpData empDataSession = empDataServices.findByUserId(usersId);
			String industryCat = empDataSession.getCompanyProfile().getIndustryCategory();
			String userRole = empDataSession.getContPerDesig();
			String[] industryCategory = industryCat.split("-");
			String sessionIndustryType = industryCategory[0].replaceAll("\\s+", "");

			Settings settingsdata = settingServices.findSettingData(usersId);
			EmpData userprofile = empDataServices.getUserProfileData(usersId);
			int imgvalue = settingsdata.getBackgroundImage();

			int isETP = 0, isSTP = 0, isBoth = 0, flagWater = 0;

			// code to check ETP / STP for water Performance > percentage non-compliance

			// Check ETP availability
			try
			{
				isETP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("ETP");
				if (isETP > 0)
					isETP = 1;
			}
			catch (Exception e)
			{
				LOGGER.error(e);
			}

			// Check STP availability
			try
			{
				isSTP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("STP");
				if (isSTP > 0)
					isSTP = 1;
			}
			catch (Exception e)
			{
				LOGGER.error(e);
			}

			CompanyProfile companyData = companyProfileServices.findOne();
			// profiles pic by jemish
			String userProfilePic = empDataSession.getProfilePic();
			File file = new File(Constant.UserProfiles_pic_path + userProfilePic);
			byte[] userProfilePic1 = Files.readAllBytes(file.toPath());
			byte[] encodeBase64 = Base64.encodeBase64(userProfilePic1);
			String proPic = new String(encodeBase64, "UTF-8");
			// profiles pic by jemish

			modelAndView.addObject("isETP", isETP);
			modelAndView.addObject("isSTP", isSTP);
			modelAndView.addObject("imgvalue", imgvalue);
			modelAndView.addObject("empDataSession", empDataSession);
			modelAndView.addObject("emplogindata", userprofile);
			modelAndView.addObject("sessionIndustryType", sessionIndustryType);
			modelAndView.addObject("userRole", userRole);
			int uId = empDataSession.getUsers().getUsersId();
			modelAndView.addObject("uId", uId);
			modelAndView.addObject("userProfilePic", proPic);
			modelAndView.addObject("companyName", companyData.getCompName());
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to View the regular data on management Dashbord.
	 * 
	 * @return Management/ViewRegularData
	 */
	@RequestMapping("management-view-regular-data")
	public ModelAndView getDailyData()
	{
		return new ModelAndView("Management/ViewRegularData");
	}

	/**
	 * This method used to View the Company profile on management Dashbord.
	 * 
	 * @return management-company-profile
	 */
	@RequestMapping("management-company-profile")
	public ModelAndView getManagementCompanyProfile()
	{
		return new ModelAndView("Management/ManagementCompanyProfile");
	}

	/**
	 * This method used to View the Managemet employee profile on management Dashbord.
	 * 
	 * @return management-profile
	 */
	@RequestMapping("management-profile")
	public ModelAndView getManagementProfile()
	{
		return new ModelAndView("Management/ManagementProfile");
	}

	/**
	 * This method used to View the Graphs i.e.product, byproduct, raw, fuel, hazordous waste process,
	 * hazordous waste from PCF,Non-hazordous waste process, Non-hazordous waste from PCF,
	 * water, Bio-Medical etc.
	 * 
	 * @return management-graph
	 */
	@RequestMapping("management-graph")
	public ModelAndView getManagementGraph()
	{
		ModelAndView model = new ModelAndView("EnvrOfficer/ViewReport");
		try
		{
			List<String> unitproductList = null;
			List<String> unitbyproductList = null;
			List<String> unitrawList = null;
			List<String> unitfuelList = null;
			List<String> unithwpList = null;
			List<String> unithwpcfList = null;
			List<String> unitnhwpList = null;
			List<String> unitnhwpcfList = null;
			List<String> unitwaterList = null;
			List<String> unitbiomedicalList = null;
			String[] pType = {"product", "byproduct", "raw", "fuel", "hwp", "hwpcf", "nhwp", "nhwpcf", "water", "Bio-Medical"};
			String today = Utilities.getTodaysDate();
			for (int i = 0; i < pType.length; i++)
			{
				if (pType[i].equalsIgnoreCase("product"))
				{
					unitproductList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitproductList != null)
					{
						model.addObject("ProductUnitList", unitproductList);
					}
				}
				else if (pType[i].equalsIgnoreCase("byproduct"))
				{
					unitbyproductList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitbyproductList != null)
					{
						model.addObject("ByProductUnitList", unitbyproductList);
					}
				}
				else if (pType[i].equalsIgnoreCase("raw"))
				{
					unitrawList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitrawList != null)
					{
						model.addObject("RawUnitList", unitrawList);
					}
				}
				else if (pType[i].equalsIgnoreCase("fuel"))
				{
					unitfuelList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitfuelList != null)
					{
						model.addObject("FuelUnitList", unitfuelList);
					}
				}
				else if (pType[i].equalsIgnoreCase("hwp"))
				{
					unithwpList = unitServices.getUnitsFromPType(pType[i], today);
					if (unithwpList != null)
					{
						model.addObject("HWPUnitList", unithwpList);
					}
				}
				else if (pType[i].equalsIgnoreCase("hwpcf"))
				{
					unithwpcfList = unitServices.getUnitsFromPType(pType[i], today);
					if (unithwpcfList != null)
					{
						model.addObject("HWPCFUnitList", unithwpcfList);
					}
				}
				else if (pType[i].equalsIgnoreCase("nhwp"))
				{
					unitnhwpList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitnhwpList != null)
					{
						model.addObject("NHWPUnitList", unitnhwpList);
					}
				}
				else if (pType[i].equalsIgnoreCase("nhwpcf"))
				{
					unitnhwpcfList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitnhwpcfList != null)
					{
						model.addObject("NHWPCFUnitList", unitnhwpcfList);
					}
				}
				else if (pType[i].equalsIgnoreCase("water"))
				{
					unitwaterList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitwaterList != null)
					{
						model.addObject("WaterUnitList", unitwaterList);
					}
				}
				else if (pType[i].equalsIgnoreCase("Bio-Medical"))
				{
					unitbiomedicalList = unitServices.getUnitsFromPType(pType[i], today);
					if (unitbiomedicalList != null)
					{
						model.addObject("BioMedicalUnitList", unitbiomedicalList);
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return model;
		// return new ModelAndView("Management/ManagementViewReport");
	}

	/**
	 * This method is used to show the data quality performance.
	 * 
	 * @param today The current month date(e.g ending date)
	 * @param prev_date The previous monthe date(e.g Starting date)
	 * @param name The name of the product data.
	 * @param request The servlet request we are processing.
	 * @return status it return true/ false
	 * @throws ParseException
	 */
	@PostMapping(value = {"/ajax-overAllDailyDataMgmt"})
	public ResponseEntity<?> overAllDailyDataMgmt(HttpServletRequest request) throws ParseException
	{

		JsonArray jsonArray = new JsonArray();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		String todayDate = Utilities.getTodaysDate();
		String previousDate = Utilities.getPreviousDate(todayDate, 30);
		String nextPreviousDate = Utilities.getPreviousDate(previousDate, 30);

		Float overAllValue = 0.0f;
		Float overAllOldValue = 0.0f;
		int indUsedResouces = 0;
		try
		{
			for (int i = 0; i < GenericConstantArrayList.resourcesNamesList.size(); i++)
			{
				int actualEntry = 0;
				int actualOld = 0;

				String resourceName = GenericConstantArrayList.resourcesNamesList.get(i);
				List<Object[]> productNameList = new ArrayList<>();

				productNameList = allProductsServices.getProductByType(resourceName);

				if (!Validator.isEmpty(productNameList))
				{
					indUsedResouces++;
					JsonObject jsonObject = new JsonObject();

					for (Object[] productNameListObj : productNameList)
					{
						int noCount = 0;
						int oldCount = 0;
						String singleName = (String) productNameListObj[0];

						try
						{
							noCount = allProductsServices.getNumberFromRegularData(singleName, previousDate, todayDate);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

						try
						{
							oldCount = allProductsServices.getNumberFromRegularData(singleName, nextPreviousDate, previousDate);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

						actualOld += oldCount;
						actualEntry += noCount;
					}
					Float finalValue = Utilities.calculatePercentage(actualEntry, productNameList.size() * 30);
					Float finalOldValue = Utilities.calculatePercentage(actualOld, productNameList.size() * 30);

					jsonObject.addProperty("name", resourceName);
					jsonObject.addProperty("quality", finalValue);
					jsonArray.add(jsonObject);

					overAllValue += finalValue;
					overAllOldValue += finalOldValue;
				}
			}
			JsonObject jsonObjectOverAll = new JsonObject();
			jsonObjectOverAll.addProperty("name", "overAll");
			jsonObjectOverAll.addProperty("quality", Utilities.getFloatpoint(overAllValue / indUsedResouces, 2));
			jsonArray.add(jsonObjectOverAll);

			JsonObject jsonObjectOverAllOld = new JsonObject();
			jsonObjectOverAllOld.addProperty("name", "overAllOld");
			jsonObjectOverAllOld.addProperty("quality", Utilities.getFloatpoint(overAllOldValue / indUsedResouces, 2));
			jsonArray.add(jsonObjectOverAllOld);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return new ResponseEntity<>(jsonArray, HttpStatus.OK);
	}

	/**
	 * This method used to display the Environmental performance.
	 * 
	 * @param today The current month date(e.g ending date)
	 * @param prev_date The previous monthe date(e.g Starting date)
	 * @param name The name of the product data.
	 * @param request The Request object of the HttpServletRequest.
	 * @return type of product and there values
	 */
	@RequestMapping(value = {"/ajax-overAllEnvPerformance"})
	public @ResponseBody @JsonRawValue String overAllEnvPerformance(HttpServletRequest request)
	{
		JSONArray jsonArray = new JSONArray();

		try
		{

			String date1 = Utilities.getTodaysDate();
			String date2 = Utilities.getPreviousDate(date1, 30);
			String date3 = Utilities.getPreviousDate(date2, 30);

			Float overAllValue = 0.0f;
			Float overAllOldValue = 0.0f;
			ArrayList<String> ProductionList = new ArrayList<String>();
			ProductionList.add("Product");
			ProductionList.add("ByProduct");
			ProductionList.add("Raw Material");
			ProductionList.add("Fuel");
			ProductionList.add("hwp");
			ProductionList.add("hwpcf");
			ProductionList.add("nhwp");
			ProductionList.add("nhwpcf");

			for (int i = 0; i < ProductionList.size(); i++)
			{
				String pTypeName = ProductionList.get(i);
				int noOfResources = 0;
				List<Object[]> allProductsLists = new ArrayList<>();

				HashMap<String, Object> hashMap = new HashMap<String, Object>();

				try
				{
					allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(pTypeName, date1);
				}
				catch (Exception e)
				{
				}

				noOfResources = allProductsLists.size();

				if (!Validator.isEmpty(allProductsLists))
				{
					int countOfNonCompliance = 0;
					int oldCountOfNonCompliance = 0;
					int totalNonComp = 0;
					int oldTotalNonComp = 0;
					Float finalValue = 0.0f;
					for (Object[] allProductsListData : allProductsLists)
					{

						String productName = (String) allProductsListData[1];
						String pUnit = (String) allProductsListData[2];
						Float complianceQuantity = Utilities.convertDataToPerDayByUnit(pUnit, (Float) allProductsListData[0]);

						try
						{
							countOfNonCompliance = regularDataServices.getNonComplianceByProductName(productName, date2, date1, complianceQuantity);
						}
						catch (Exception e)
						{
						}
						try
						{
							oldCountOfNonCompliance = regularDataServices.getNonComplianceByProductName(productName, date3, date2, complianceQuantity);
						}
						catch (Exception e)
						{
						}

						totalNonComp += countOfNonCompliance;
						oldTotalNonComp += oldCountOfNonCompliance;
					}

					float totaldays = allProductsLists.size() * 30;
					finalValue = (Float) (((float) totalNonComp / (float) totaldays) * 100);
					finalValue = (float) Utilities.getDoubleRoundPoint(finalValue, 2);
					overAllValue += finalValue;
					hashMap.put("name", new String(pTypeName));
					hashMap.put("quality", new Float(finalValue));

					jsonArray.put(hashMap);

					float finalOldValue = (Float) (((float) oldTotalNonComp / (float) totaldays) * 100);
					finalOldValue = (float) Utilities.getFloatpoint(finalOldValue, 2);
					overAllOldValue += finalOldValue;
				}

			}
			HashMap<String, Object> overAllData = new HashMap<String, Object>();
			Float overAllResult = Utilities.getFloatpoint((overAllValue / ProductionList.size()), 2);
			overAllData.put("name", new String("overAll"));
			overAllData.put("quality", new Float(overAllResult));
			jsonArray.put(overAllData);

			HashMap<String, Object> overAllOldData = new HashMap<String, Object>();
			Float overAllOldResult = Utilities.getFloatpoint(overAllOldValue / ProductionList.size(), 2);
			overAllOldData.put("name", new String("overAllOld"));
			overAllOldData.put("quality", new Float(overAllOldResult));
			jsonArray.put(overAllOldData);

			/*
			 * if (name.equalsIgnoreCase("water"))
			 * {
			 * waterSourceNameList = waterSourceServices.getAllWaterSourceData();
			 * Float finalComplianceCons = 0.0f;
			 * finalComplianceCons = waterConGenComparativeSheetServices.getcons();
			 * if (finalComplianceCons == null)
			 * finalComplianceCons = 0.0f;
			 * if (!Validator.isEmpty(waterSourceNameList))
			 * {
			 * status = true;
			 * for (int i = 0; i < waterSourceNameList.size(); i++)
			 * {
			 * String waterSourceName = waterSourceNameList.get(i);
			 * int nonCompliance = regularSourceDataServices.countNonCompliedBySourceType(prev_date, today, waterSourceName, finalComplianceCons);
			 * nonWaterCompliance += nonCompliance;
			 * }
			 * float totaldays = waterSourceNameList.size() * 30;
			 * finalValue = (Float) ((nonWaterCompliance / totaldays) * 100);
			 * finalValue = (float) Utilities.getDoubleRoundPoint(finalValue, 2);
			 * }
			 * }
			 */
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to display the Monthly Environmental Statistics data.
	 * There are 3 part are Fuel, Solid waste, water consumption.
	 * 
	 * @param type The Type of the product type.
	 * @param prevdate The previous monthe date(e.g Starting date)
	 * @param today The current month date(e.g ending date)
	 * @return All product list
	 */
	@RequestMapping(value = {"/ajax-getMonthlyEnvStatistics"})
	public @ResponseBody @JsonRawValue String getMonthlyEnvStatistics(
			@RequestParam(value = "prevdate", required = false) String prevdate,
			@RequestParam(value = "today", required = false) String today)
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			String productName = null, pUnit = null;
			Float quantityAvg = 0.0f;
			String jsonString = null;

			List<Object[]> allProductsLists = new ArrayList<>();
			String productions[] = {"Product", "byproduct", "Raw Material", "Fuel", "hwp", "hwpcf", "nhwp", "nhwpcf", "waterConsumption"};
			String productionsDiv[] = {"product", "byproduct", "raw", "fuel", "hwp", "hwpcf", "nhwp", "nhwpcf", "waterConsumption"};
			for (int i = 0; i < productions.length; i++)
			{

				if (!productions[i].equalsIgnoreCase("waterConsumption"))
				{
					allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(productions[i], today);

					if (!Validator.isEmpty(allProductsLists))
					{
						for (Object[] allProductsListData : allProductsLists)
						{

							productName = (String) allProductsListData[1];
							pUnit = (String) allProductsListData[2];

							quantityAvg = regularDataServices.getAvgByDateProductName(productName, prevdate, today);
							if (quantityAvg == null)
								quantityAvg = 0.0f;

							quantityAvg = Utilities.round(quantityAvg, 2);

							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("type", new String(productionsDiv[i]));
							hashMap.put("product_name", new String(productName));
							hashMap.put("unit", new String(pUnit));
							hashMap.put("avgquantity", new Float(quantityAvg));

							jsonArray.put(hashMap);

						}
					}

				}
				else
				{
					// Effected By Water Inventory ........by sushama
					// quantityAvg = regularSourceDataServices.getAvgActualReadingBetweenDates(prevdate, today);
					quantityAvg = 0.0f;
					if (quantityAvg == null)
						quantityAvg = 0.0f;

					quantityAvg = Utilities.round(quantityAvg, 2);

					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("type", new String("waterConsumption"));
					hashMap.put("product_name", new String("NA"));
					hashMap.put("unit", new String("NA"));
					hashMap.put("avgquantity", new Float(quantityAvg));

					jsonArray.put(hashMap);

				}
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return jsonArray.toString();
	}

	/**
	 * This method used to display the Summary of Non-compliance of current year.
	 * There are 2 type Fuel and solid waste.
	 *
	 * @param type The Type of product.
	 * @param today The current month date(e.g ending date)
	 * @param prevdate The previous monthe date(e.g Starting date)
	 * @return getSummary
	 */
	@RequestMapping(value = {"/ajax-getSummary"})
	public @ResponseBody @JsonRawValue String getSummary(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevdate", required = false) String prevdate)
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			String type_p = null, productName = null, pUnit = null;
			Float quantityAvg = 0.0f, complianceQuantity = 0.0f;

			String jsonString = null;
			List<Object[]> allProductsLists = new ArrayList<>();

			if (type.equalsIgnoreCase("product"))
			{
				type_p = "Product";
			}
			else if (type.equalsIgnoreCase("byproduct"))
			{
				type_p = "byproduct";
			}
			else if (type.equalsIgnoreCase("raw"))
			{
				type_p = "Raw Material";
			}
			else if (type.equalsIgnoreCase("fuel"))
			{
				type_p = "Fuel";
			}
			else if (type.equalsIgnoreCase("hwp"))
			{
				type_p = "hwp";
			}
			else if (type.equalsIgnoreCase("hwpcf"))
			{
				type_p = "hwpcf";
			}
			else if (type.equalsIgnoreCase("nhwp"))
			{
				type_p = "nhwp";
			}
			else if (type.equalsIgnoreCase("nhwpcf"))
			{
				type_p = "nhwpcf";
			}
			else if (type.equalsIgnoreCase("bio"))
			{
				type_p = "Bio-Medical";
			}

			allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(type_p, today);

			if (!Validator.isEmpty(allProductsLists))
			{

				for (Object[] allProductsListData : allProductsLists)
				{
					int countOfNonCompliance = 0;
					String remark = null;

					productName = (String) allProductsListData[1];
					pUnit = (String) allProductsListData[2];
					complianceQuantity = (Float) allProductsListData[0];

					String unitSplit[] = pUnit.split("/");

					if (unitSplit[1].equalsIgnoreCase("Day"))
					{
						remark = "This product has unit in " + pUnit;
						// do nothing
						// complianceQuantity = complianceQuantity;
					}
					else if (unitSplit[1].equalsIgnoreCase("Hr"))
					{
						remark = "This product has unit in " + pUnit;
						complianceQuantity = complianceQuantity * 24;
					}
					else if (unitSplit[1].equalsIgnoreCase("Week"))
					{
						remark = "This product has unit in " + pUnit;
						complianceQuantity = complianceQuantity / 7;
					}
					else if (unitSplit[1].equalsIgnoreCase("Month"))
					{
						remark = "This product has unit in " + pUnit;
						complianceQuantity = complianceQuantity / 30;
					}
					else if (unitSplit[1].equalsIgnoreCase("Year"))
					{
						remark = "This product has unit in " + pUnit;
						complianceQuantity = complianceQuantity / 365;
					}

					try
					{
						countOfNonCompliance = regularDataServices.getNonComplianceByProductName(productName, prevdate, today, complianceQuantity);
					}
					catch (Exception e)
					{
						LOGGER.error(e);
					}

					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("productName", productName);
					hashMap.put("noncount", countOfNonCompliance);
					hashMap.put("remark", remark);

					jsonArray.put(hashMap);

				}
			}
			else
			{

				// do nothing if there is no productsss...

			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to View the Solid waste Graph.
	 * 
	 * @param typename The name of the typename
	 * @param request The servlet request we are processing.
	 * @return solidWateGraph data
	 * @throws ParseException If parsing causes an error
	 */
	@RequestMapping(value = {"/ajax-solidWateGraph"})
	public @ResponseBody List<Object> solidWateGraph(
			@RequestParam(value = "name", required = false) String typename, HttpServletRequest request)
			throws ParseException
	{
		List<Object> data = new ArrayList<>();
		try
		{
			String todayDate = Utilities.getTodaysDate();
			String today_date[] = todayDate.split("-");
			int currentYear = Integer.parseInt(today_date[0]);
			int currentMonth = Integer.parseInt(today_date[1]);
			int currentDay = Integer.parseInt(today_date[2]);

			List<Object[]> productsLists = new ArrayList<>();

			ArrayList<Object> series = new ArrayList<>();

			ArrayList<Object> water = new ArrayList<>();

			String productName = null, pUnit = null;
			Float complianceQuantity = 0.0f, regQuantity = 0.0f;
			double finalValue = 0;

			if (typename.equalsIgnoreCase("Fuel"))
			{
				productsLists = allProductComparativeSheetServices.getAllProductComparativeSheet("Fuel", todayDate);
			}
			else
			{
				productsLists = allProductComparativeSheetServices.getAllProductComparativeSheetSolidWaste(todayDate);
			}
			if (!Validator.isEmpty(productsLists))
			{
				series.add("Days");
				for (int days = 0; days <= 29; days++)
				{
					Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cal.add(Calendar.DAY_OF_WEEK, -days);

					String date = sdf.format(cal.getTime());
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
					String dateToSend[] = date.split("-");
					int day = Integer.parseInt(dateToSend[2]);
					String month = new SimpleDateFormat("MMM").format(date1);
					String ddate = month + " " + day;
					series.add(ddate);
				}
				data.add(series);
			}

			if (!Validator.isEmpty(productsLists))
			{
				for (Object[] allProductsListData : productsLists)
				{
					productName = (String) allProductsListData[1];
					pUnit = (String) allProductsListData[2];
					complianceQuantity = (Float) allProductsListData[0];
					String unitSplit[] = pUnit.split("/");

					if (unitSplit[1].equalsIgnoreCase("Day"))
					{
						// do nothing
						// complianceQuantity = complianceQuantity;
					}
					else if (unitSplit[1].equalsIgnoreCase("Hr"))
					{
						complianceQuantity = complianceQuantity * 24;
					}
					else if (unitSplit[1].equalsIgnoreCase("Week"))
					{
						complianceQuantity = complianceQuantity / 7;
					}
					else if (unitSplit[1].equalsIgnoreCase("Month"))
					{
						complianceQuantity = complianceQuantity / 30;
					}
					else if (unitSplit[1].equalsIgnoreCase("Year"))
					{
						if ((0 == currentYear % 4) && (0 != currentYear % 100) || (0 == currentYear % 400))
						{
							complianceQuantity = complianceQuantity / 366;
						}
						else
						{
							complianceQuantity = complianceQuantity / 365;
						}
					}

					// ADD CONSENTED VALUE TO ARRAY
					series = new ArrayList<>();
					series.add("consent value " + productName);
					for (int days = 0; days <= 29; days++)
					{
						series.add(complianceQuantity);
					}
					data.add(series);

					// ADD 30 DAYS VALUE
					series = new ArrayList<>();
					series.add(productName + " in " + unitSplit[0] + "/Day");
					for (int days = 0; days <= 29; days++)
					{
						Float diff = 0.0f;
						Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						cal.add(Calendar.DAY_OF_WEEK, -days);
						String date = sdf.format(cal.getTime());

						regQuantity = regularDataServices.quantityByProductNameDate(productName, date);

						if (regQuantity == null)
							regQuantity = 0.0f;
						series.add(regQuantity);
					}
					data.add(series);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return data;
	}

	/**
	 * This method used to Efficiency Of STP(sewage treatment plant)
	 * Third party input the monitoring data including sewage treatment plant(STP)
	 * 
	 * @return getStpEfficiency
	 */
	@RequestMapping(value = {"/ajax-getStpEfficiency"})
	public @ResponseBody @JsonRawValue String getStpEfficiency()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		List<String> dateArrayList = new ArrayList();
		List<RegSewPoll> regPollDatas = new ArrayList();
		try
		{
			dateArrayList = regSewPollServices.getRegSewPollDate(new PageRequest(0, 5));
			if (!Validator.isEmpty(dateArrayList))
			{
				for (int i = 0; i < dateArrayList.size(); i++)
				{
					json = new JSONObject();
					JSONArray jsonPolldata = new JSONArray();
					json.put("date", dateArrayList.get(i));
					List<String> labelName = wastewaterTreatmentServices.findAlltreatmentTypeLabel("STP");
					for (int k = 0; k < labelName.size(); k++)
					{
						regPollDatas = regSewPollServices.getRegSewPollDatasbyDate(labelName.get(k), dateArrayList.get(i));
						if (!regPollDatas.isEmpty())
						{
							JSONArray jsonPollDataArray1 = new JSONArray();
							JSONObject etpData = new JSONObject();
							etpData.put("label", labelName.get(k));
							for (int j = 0; j < regPollDatas.size(); j++)
							{
								JSONObject jsonarray = new JSONObject();
								jsonarray.put("pollname", regPollDatas.get(j).getWaterSewPollOp().getWaterSewPoll().getPollName());
								jsonarray.put("pollLabel", regPollDatas.get(j).getWastewaterTreatment().getLabel());
								jsonarray.put("incons", regPollDatas.get(j).getInConsS());
								jsonarray.put("outcons", regPollDatas.get(j).getOuConsS());
								double effiency = Math.round((double) (((double) regPollDatas.get(j).getInConsS() - (double) regPollDatas.get(j).getOuConsS()) / (double) regPollDatas.get(j).getInConsS()) * 100);
								jsonarray.put("efficiency", effiency);
								jsonPollDataArray1.put(jsonarray);
							}
							etpData.put("polldata", jsonPollDataArray1);
							jsonPolldata.put(etpData);
						}
					}
					json.put("data", jsonPolldata);
					jsonArray.put(json);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		// System.out.print(jsonArray);
		return jsonArray.toString();
	}

	/**
	 * This method used to Efficiency Of ETP(effluent treatment plant)
	 * Third party input the monitoring data including effluent treatment plant(ETP)
	 * 
	 * @return getEtpEfficiency
	 */
	@RequestMapping(value = {"/ajax-getEtpEfficiency"})
	public @ResponseBody @JsonRawValue String getEtpEfficiency()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		List<String> dateArrayList = new ArrayList();
		List<RegEffPoll> regPollDatas = new ArrayList();
		try
		{
			// dateArrayList = regSewPollServices.getRegSewPollDate(new PageRequest(0, 5));
			dateArrayList = regEffPollServices.getRegEffPollDate(new PageRequest(0, 5));
			if (!Validator.isEmpty(dateArrayList))
			{
				for (int i = 0; i < dateArrayList.size(); i++)
				{
					json = new JSONObject();
					JSONArray jsonPolldata = new JSONArray();
					json.put("date", dateArrayList.get(i));
					List<String> labelName = wastewaterTreatmentServices.findAlltreatmentTypeLabel("ETP");
					for (int k = 0; k < labelName.size(); k++)
					{
						regPollDatas = regEffPollServices.getRegEffPollDatasbyDate(labelName.get(k), dateArrayList.get(i));
						if (!regPollDatas.isEmpty())
						{
							JSONArray jsonPollDataArray1 = new JSONArray();
							JSONObject etpData = new JSONObject();
							etpData.put("label", labelName.get(k));
							for (int j = 0; j < regPollDatas.size(); j++)
							{
								JSONObject jsonarray = new JSONObject();
								jsonarray.put("pollname", regPollDatas.get(j).getWateriePollutantOp().getWateriePollutant().getPollName());
								jsonarray.put("pollLabel", regPollDatas.get(j).getWastewaterTreatment().getLabel());
								jsonarray.put("incons", regPollDatas.get(j).getInConsE());
								jsonarray.put("outcons", regPollDatas.get(j).getOuConsE());
								jsonarray.put("unit", regPollDatas.get(j).getUnitE());
								double effiency = Math.round((double) (((double) regPollDatas.get(j).getInConsE() - (double) regPollDatas.get(j).getOuConsE()) / (double) regPollDatas.get(j).getInConsE()) * 100);
								jsonarray.put("efficiency", effiency);
								jsonPollDataArray1.put(jsonarray);
							}
							etpData.put("polldata", jsonPollDataArray1);
							jsonPolldata.put(etpData);
						}
					}
					json.put("data", jsonPolldata);
					jsonArray.put(json);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		// System.out.print(jsonArray);
		return jsonArray.toString();

	}

	/**
	 * This method used to view the Production vs water generation.
	 * 
	 * @param typename The name of the product type.
	 * @param request The Request object of the HttpServletRequest.
	 * @return producstionvswater data
	 * @throws ParseException If parsing causes an error
	 */
	@PostMapping(value = {"/ajax-producstionvswater"})
	public List<Object> producstionvswater()
			throws ParseException
	{
		List<Object> data = new ArrayList<>();
		try
		{
			String todayDate = Utilities.getTodaysDate();
			String nextDate = Utilities.addedDateByDays(todayDate, -30);

			List<String> datesArray = new ArrayList<>();
			datesArray = Utilities.getDatesRangeArray(todayDate, nextDate);

			String today_date[] = todayDate.split("-");
			int currentYear = Integer.parseInt(today_date[0]);
			int currentMonth = Integer.parseInt(today_date[1]);
			int currentDay = Integer.parseInt(today_date[2]);

			ArrayList<Object> series = new ArrayList<>();

			ArrayList<Object> water = new ArrayList<>();
			List<Object[]> productsLists = new ArrayList<>();

			productsLists = allProductComparativeSheetServices.getAllProductComparativeSheet("Product", todayDate);

			if (!Validator.isEmpty(productsLists))
			{

				series.add("Days");
				series.addAll(datesArray); // add all dates
				data.add(series);

				water.add("water in CMD");
				for (int i = 0; i < datesArray.size(); i++)
				{
					Float regWaterUse = regWaterSourceDataServices.getActualReadingSumForDate(datesArray.get(i));
					if (regWaterUse == null)
						regWaterUse = 0.0f;
					water.add(regWaterUse);
				}
				data.add(water);

				for (Object[] allProductsListData : productsLists)
				{
					String productName = (String) allProductsListData[1];
					String pUnit = (String) allProductsListData[2];
					String unitSplit[] = pUnit.split("/");

					series = new ArrayList<>();
					series.add(productName + " in " + unitSplit[0] + "/Day");

					for (int i = 0; i < datesArray.size(); i++)
					{

						Float quantity = regularDataServices.quantityByProductNameDate(productName, datesArray.get(i));

						if (quantity == null)
							quantity = 0.0f;

						series.add(quantity);
					}
					data.add(series);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return data;
	}

	/**
	 * This method used to View the Water consumption Graph.
	 * 
	 * @param typename The name of the product type
	 * @param request The servlet request we are processing.
	 * @return WaterConsumptionGraph
	 * @throws ParseException If parsing causes an error
	 */
	@RequestMapping(value = {"/ajax-WaterConsumptionGraph"})
	public @ResponseBody List<Object> waterConsumptionGraph(
			@RequestParam(value = "name", required = false) String typename, HttpServletRequest request)
			throws ParseException
	{
		List<Object> data = new ArrayList<>();
		try
		{
			String todayDate = Utilities.getTodaysDate();
			String today_date[] = todayDate.split("-");
			int currentYear = Integer.parseInt(today_date[0]);
			int currentMonth = Integer.parseInt(today_date[1]);
			int currentDay = Integer.parseInt(today_date[2]);

			ArrayList<Object> series = new ArrayList<>();
			ArrayList<Object> waterConData = new ArrayList<>();

			List<String> waterSourceNameList = waterSourceServices.getAllWaterSourceData();

			Float finalCons = 0.0f;
			finalCons = waterConGenComparativeSheetServices.getcons();
			if (finalCons == null)
				finalCons = 0.0f;

			series.add("Days");
			waterConData.add("Consent value");
			for (int days = 0; days <= 29; days++)
			{
				Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cal.add(Calendar.DAY_OF_WEEK, -days);

				String date = sdf.format(cal.getTime());
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				String dateToSend[] = date.split("-");
				int day = Integer.parseInt(dateToSend[2]);
				String month = new SimpleDateFormat("MMM").format(date1);
				String ddate = month + " " + day;

				series.add(ddate);
				waterConData.add(finalCons);
			}
			data.add(series);
			data.add(waterConData);

			if (!Validator.isEmpty(waterSourceNameList))
			{

				for (int i2 = 0; i2 < waterSourceNameList.size(); i2++)
				{
					String waterSourceName = waterSourceNameList.get(i2);

					series = new ArrayList<>();
					series.add(waterSourceName);

					int noDay = 0;
					for (int days = 1; days <= 30; days++)
					{
						Float regWaterUse = 0.0f;
						Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						cal.add(Calendar.DAY_OF_WEEK, -noDay);

						String date = sdf.format(cal.getTime());
						regWaterUse = 0.0f;
						regWaterUse = regWaterSourceDataServices.getActualReadingByRsDateAndSourceName(date, waterSourceName);
						if (regWaterUse == null)
							regWaterUse = 0.0f;

						series.add(regWaterUse);
						noDay++;
					}
					data.add(series);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return data;
	}

	/**
	 * This method is used to View the Waste-water Generation.
	 * 
	 * @param typename The name of the product type
	 * @param request The servlet request we are processing.
	 * @return WaterWasteGraph
	 * @throws ParseException If parsing causes an error
	 */
	@RequestMapping(value = {"/ajax-WaterWasteGraph"})
	public @ResponseBody List<Object> WaterWasteGraph(
			@RequestParam(value = "name", required = false) String typename, HttpServletRequest request)
			throws ParseException
	{
		List<Object> data = new ArrayList<>();
		try
		{
			String todayDate = Utilities.getTodaysDate();
			String today_date[] = todayDate.split("-");
			int currentYear = Integer.parseInt(today_date[0]);
			int currentMonth = Integer.parseInt(today_date[1]);
			int currentDay = Integer.parseInt(today_date[2]);

			ArrayList<Object> series = new ArrayList<>();
			ArrayList<Object> water = new ArrayList<>();

			// array lists
			List<WaterInventory> waterInvetoryUses = new ArrayList<>();
			List<Object[]> productsLists = new ArrayList<>();
			List<String> waterSourceNameList = new ArrayList<>();
			List<String> directUseList = new ArrayList<>();
			// Effected By Water Inventory ........by vishal
			// List<Industrial> industrialList = new ArrayList<>();
			List<Float> waterLossList = new ArrayList<>();

			// check use of source checked or not
			waterInvetoryUses = waterInventoryServices.getUseOfSource();
			if (!Validator.isEmpty(waterInvetoryUses))
			{
				for (int i = 0; i < waterInvetoryUses.size(); i++)
				{
					// Effected By Water Inventory ........by vishal
					String isDomestic = null; // waterInvetoryUses.get(i).getDomesticUseOfSource();
					String isIndustrial = null;// waterInvetoryUses.get(i).getIndustrialUseOfSource();
					String isLaundry = null;// waterInvetoryUses.get(i).getLaundryUseOfSource();
					String isFireHydrant = null;// waterInvetoryUses.get(i).getFireHydrantUseOfSource();

					// add domestic to waterUse List list
					if (isDomestic.equalsIgnoreCase("checked"))
					{
						directUseList.add("Domestic");
						Float loss = 0.0f;
						// Effected By Water Inventory ........by vishal
						loss = null;// useOfWaterServices.getUseOfWaterDataByDomesticLoss();

						if (loss == null)
						{
							waterLossList.add(95.0f); // default value
						}
						else
						{
							waterLossList.add(loss);
						}

					}

					// to check industrial waste
					if (isIndustrial.equalsIgnoreCase("checked"))
					{
						// Effected By Water Inventory ........by vishal
						/*
						 * industrialList = new ArrayList<>();//industrialServices.getIndustrialAllData();
						 * if (!Validator.isEmpty(industrialList))
						 * {
						 * for (int j = 0; j < industrialList.size(); j++)
						 * {
						 * directUseList.add(industrialList.get(j).getIndName());
						 * waterLossList.add(industrialList.get(j).getWaterLoss());
						 * }
						 * }
						 */
					}
					if (isLaundry.equalsIgnoreCase("checked"))
					{
						directUseList.add("Laundry");
						Float loss = 0.0f;
						// Effected By Water Inventory ........by vishal
						loss = null;// useOfWaterServices.getUseOfWaterDataByLaundryLoss();

						if (loss == null)
							waterLossList.add(0.0f);
						else
							waterLossList.add(loss);

					}
					if (isFireHydrant.equalsIgnoreCase("checked"))
					{
						directUseList.add("Fire Hydrant");
						Float loss = 0.0f;
						// Effected By Water Inventory ........by vishal
						loss = null;// useOfWaterServices.getUseOfWaterDataByFireHydrantLoss();
						if (loss == null)
							waterLossList.add(0.0f);
						else
							waterLossList.add(loss);
					}
				}

				series.add("Days");
				water.add("Consented Value");

				// add days and consented value
				for (int days = 0; days <= 29; days++)
				{
					Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cal.add(Calendar.DAY_OF_WEEK, -days);

					String date = sdf.format(cal.getTime());
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
					String dateToSend[] = date.split("-");
					int day = Integer.parseInt(dateToSend[2]);
					String month = new SimpleDateFormat("MMM").format(date1);
					String ddate = month + " " + day;
					series.add(ddate); // add days

					Float finalCons = 0.0f;
					finalCons = waterConGenComparativeSheetServices.getcons();

					if (finalCons == null)
						finalCons = 0.0f;
					water.add(finalCons); // add consented value
				}
				data.add(series);
				data.add(water);
			}

			series = new ArrayList<>();
			series.add("Waste water generation");

			for (int days = 0; days <= 29; days++)
			{
				Float diff = 0.0f, x = 0.0f;
				Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cal.add(Calendar.DAY_OF_WEEK, -days);
				String date = sdf.format(cal.getTime());
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				String dateToSend[] = date.split("-");
				int day = Integer.parseInt(dateToSend[2]);
				String month = new SimpleDateFormat("MMM").format(date1);

				for (int l = 0; l < waterLossList.size(); l++)
				{
					Float con = 0.0f;
					// Effected By Water Inventory ........by vishal
					con = 0.0f;// regularWaterUseDataServices.getActualReadingByDateAndSourceType(date, directUseList.get(l));

					if (con == null)
						con = 0.0f;
					x = x + ((con * waterLossList.get(l)) / 100);
				}
				series.add(x);
			}
			data.add(series);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return data;
	}
}
