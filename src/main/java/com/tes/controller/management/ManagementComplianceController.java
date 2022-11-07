package com.tes.controller.management;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.CompanyProfile;
import com.tes.model.Consent;
import com.tes.model.EmpData;
import com.tes.model.EsrHwSolidWaste;
import com.tes.model.EsrInvestment;
import com.tes.model.EsrParticulars;
import com.tes.model.EsrPollutionControl;
import com.tes.model.EsrProductWater;
import com.tes.model.Stack;
import com.tes.model.StackPoll;
import com.tes.model.WaterInventory;
import com.tes.model.WaterSewPoll;
import com.tes.model.WateriePollutant;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.ContainersServices;
import com.tes.services.environmentalofficer.EsrHwSolidWasteServices;
import com.tes.services.environmentalofficer.EsrInvestmentServices;
import com.tes.services.environmentalofficer.EsrParticularsServices;
import com.tes.services.environmentalofficer.EsrPollutionControlServices;
import com.tes.services.environmentalofficer.EsrProductWaterServices;
import com.tes.services.environmentalofficer.EsrStackPollReasonsServices;
import com.tes.services.environmentalofficer.EsrWaterPollReasonsServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;
import com.tes.services.environmentalofficer.WaterConGenComparativeSheetServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.services.thirdparty.StackPollDataServices;
import com.tes.services.thirdparty.StackPollServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate used to View all the Compliance data and manage by Management.
 * 
 * @author Sushama Dadas
 * @author Tushar Chougule
 */
@Controller
@RequestMapping("/management")
public class ManagementComplianceController
{

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	WasteDescriptionConsistencyServices wasteDescriptionConsistencyServices;

	@Autowired
	ContainersServices containersServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	EsrProductWaterServices esrProductWaterServices;

	@Autowired
	EsrHwSolidWasteServices esrHwSolidWasteServices;

	@Autowired
	EsrPollutionControlServices esrPollutionControlServices;

	@Autowired
	EsrInvestmentServices esrInvestmentServices;

	@Autowired
	EsrParticularsServices esrParticularsServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	WaterConGenComparativeSheetServices waterConGenComparativeSheetServices;

	@Autowired
	RegFiltersUseDataServices regularFiltersUseDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	EsrWaterPollReasonsServices esrWaterPollReasonServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	StackServices stackServices;

	@Autowired
	StackPollServices stackPollServices;

	@Autowired
	StackPollDataServices stackPollDataServices;

	@Autowired
	EsrStackPollReasonsServices esrStackPollReasonsServices;

	private static final Logger LOGGER = LogManager.getLogger(ManagementComplianceController.class);

	/**
	 * This method used to manage to ESR form
	 * 
	 * @param encodedYear The encoded year
	 * @param request The servlet request we are processing.
	 * @return ManagementEsr
	 */
	// @RequestMapping("management-esr-form")
	@RequestMapping("/management-esr-form")
	public ModelAndView getYearlyESRform(@RequestParam(value = "year", required = false) String encodedYear,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		String year = Utilities.decodeString(encodedYear), dateRes[] = year.split("-"),
				fromDate = (dateRes[0]) + "-04-01", toDate = (dateRes[1]) + "-03-31", esrConsentDate = toDate,
				lastEnvSubmitted = null, consNo = "", issueDate = "", validUpto = "",
				set_title = "31st March " + dateRes[1];

		// modelAndView.setViewName("Management/ManagementEsr");
		modelAndView.setViewName("Management/ManEsr1");
		String year1[] = year.split("-");
		int a = Integer.parseInt(year1[0]), b = Integer.parseInt(year1[1]);
		String year2 = (a + 1) + "-" + (b + 1);
		List<String> consNoList = new ArrayList<>();
		List<String> issueDateList = new ArrayList<>();
		List<String> validUptoList = new ArrayList<>();
		List<Float> capitalInvestmentList = new ArrayList<>();
		float capitalInvestment = 0.0f;
		List<EsrProductWater> esrProductWaterData = new ArrayList<>();
		List<EsrHwSolidWaste> esrHwSolidWasteData = new ArrayList<>();
		List<EsrHwSolidWaste> esrHwSolidWasteDataBySolidYear = new ArrayList<>();
		List<EsrPollutionControl> esrPollutionControlDataByYear = new ArrayList<>();
		List<EsrInvestment> getEsrInvestmentbyYear = new ArrayList<>();
		List<EsrInvestment> getEsrInvestmentbyNextYear = new ArrayList<>();
		List<EsrParticulars> esrParticularsDataByYear = new ArrayList<>();
		try
		{
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int usersId = empDataSession.getUsers().getUsersId();
			EmpData userlogindata = empDataServices.getUserProfileData(usersId);
			List<CompanyProfile> companyProfileDatas = companyProfileServices.findAll();
			for (CompanyProfile companyProfileData : companyProfileDatas)
			{
				String lastEnv = companyProfileData.getLastEnv();
				if (lastEnv.equals(""))
				{
					lastEnvSubmitted = "No";
				}
				else
				{
					lastEnvSubmitted = "Yes";
				}
			}
			List<Consent> consentDatas = consentServices.getConsentDataByIssueDate(esrConsentDate);
			if (!Validator.isEmpty(consentDatas))
			{
				for (int i = 0; i < consentDatas.size(); i++)
				{
					consNo = consentDatas.get(i).getConsNo();
					issueDate = consentDatas.get(i).getIssueDate();
					validUpto = consentDatas.get(i).getValidUpto();
					capitalInvestment = consentDatas.get(i).getGrossCi();
					if (!consNoList.contains(consNo))
					{
						consNoList.add(consNo);
					}
					if (!issueDateList.contains(issueDate))
					{
						issueDateList.add(issueDate);
					}
					if (!validUptoList.contains(validUpto))
					{
						validUptoList.add(validUpto);
					}
					if (!capitalInvestmentList.contains(capitalInvestment))
					{
						capitalInvestmentList.add(capitalInvestment);
					}
				}
			}
			esrProductWaterData = esrProductWaterServices.getEsrProductWaterData(year, "Recycled");
			esrHwSolidWasteData = esrHwSolidWasteServices.getEsrHwSolidWasteDataByHazYear(year);
			esrHwSolidWasteDataBySolidYear = esrHwSolidWasteServices.getEsrHwSolidWasteDataBySolidYear(year);
			esrPollutionControlDataByYear = esrPollutionControlServices.findOneByEsrPollutionByYear(year);
			getEsrInvestmentbyYear = esrInvestmentServices.findOneByGetEsrInvestmentByYear(year);
			getEsrInvestmentbyNextYear = esrInvestmentServices.findOneByGetEsrInvestmentByNextYear(year, year2);
			esrParticularsDataByYear = esrParticularsServices.getEsrParticularsbyyear(year);
			String esrDatas[] = {year, set_title, lastEnvSubmitted, fromDate, toDate, esrConsentDate};
			modelAndView.addObject("userlogindata", userlogindata);
			modelAndView.addObject("companyProfileDatas", companyProfileDatas);
			modelAndView.addObject("consentDatas", consentDatas);
			modelAndView.addObject("consNoList", consNoList);
			modelAndView.addObject("issueDateList", issueDateList);
			modelAndView.addObject("validUptoList", validUptoList);
			modelAndView.addObject("capitalInvestmentList", capitalInvestmentList);
			modelAndView.addObject("esrDatas", esrDatas);
			modelAndView.addObject("esrConsentDate", esrConsentDate);
			modelAndView.addObject("esrProductWaterData", esrProductWaterData);
			modelAndView.addObject("esrHwSolidWasteData", esrHwSolidWasteData);
			modelAndView.addObject("esrHwSolidWasteDataBySolidYear", esrHwSolidWasteDataBySolidYear);
			modelAndView.addObject("esrPollutionControlDataByYear", esrPollutionControlDataByYear);
			modelAndView.addObject("getEsrInvestmentbyYear", getEsrInvestmentbyYear);
			modelAndView.addObject("getEsrInvestmentbyNextYear", getEsrInvestmentbyNextYear);
			modelAndView.addObject("esrParticularsDataByYear", esrParticularsDataByYear);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to manage the water cess form.
	 * 
	 * @return Management/WaterCessForm
	 */
	@RequestMapping("management-water-cess-form")
	public ModelAndView getWaterCessForm()
	{
		return new ModelAndView("Management/WaterCessForm");
	}

	/**
	 * This method used to manage the water budget data.
	 * 
	 * @return Management/WaterBudget
	 */
	@RequestMapping("management-water-budget")
	public ModelAndView getWaterBudget()
	{
		return new ModelAndView("Management/WaterBudget");
	}

	@RequestMapping(value = "/ajax-getHazardousValuesMan")
	@ResponseBody
	public String getHazardousValues() throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		String todayDate = Utilities.getTodaysDate();
		String today_date[] = todayDate.split("-");
		String jsonString = null;
		int esrMinYear = 0, esrMaxYear = Integer.parseInt(today_date[0]);
		try
		{
			esrMinYear = consentServices.consentMinYear();
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("esrMinYear", new Integer(esrMinYear));
			jsonobject.put("esrMaxYear", new Integer(esrMaxYear));
			jsonArray.put(jsonobject);
			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	@RequestMapping("envr-officer-hazardous-return-man")
	public ModelAndView getHazardousReturn(@RequestParam(value = "year", required = false) String encodedYear,
			HttpServletRequest request) throws ParseException
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			String issueDate = "", consNo = "";
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int usersId = empDataSession.getUsers().getUsersId();
			EmpData userlogindata = empDataServices.getUserProfileData(usersId);
			modelAndView.setViewName("EnvrOfficer/HazardousReturn");
			String today = Utilities.getTodaysDate();
			String year = Utilities.decodeString(encodedYear);
			String dateRes[] = year.split("-");
			String fromDate = (dateRes[0]) + "-04-01";
			String toDate = (dateRes[1]) + "-03-31";
			List<Consent> getissueDateAndconsentNumber = new ArrayList<>();
			List<String> issueDateList = new ArrayList<String>();
			List<String> consentNumberList = new ArrayList<String>();
			List<CompanyProfile> companyData = empDataServices.getCompanydata();
			getissueDateAndconsentNumber = consentServices.getIssueDateAndConsNoByIssueDate(fromDate, toDate, today);
			for (int i = 0; i < getissueDateAndconsentNumber.size(); i++)
			{
				issueDate = getissueDateAndconsentNumber.get(i).getIssueDate();
				consNo = getissueDateAndconsentNumber.get(i).getConsNo();
				if (!issueDateList.contains(issueDate))
				{
					issueDateList.add(issueDate);
				}
				if (!consentNumberList.contains(consNo))
				{
					consentNumberList.add(consNo);
				}
			}
			modelAndView.addObject("userlogindata", userlogindata);
			modelAndView.addObject("companyData", companyData);
			modelAndView.addObject("issueDateList", issueDateList);
			modelAndView.addObject("consentNumberList", consentNumberList);
			modelAndView.addObject("today", today);
			modelAndView.addObject("selectedYear", year);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	// made by monali
	@RequestMapping(value = "/ajax-getYearlyEsrValuesMan")
	@ResponseBody
	public String getYearlyEsrValues() throws JSONException
	{
		String jsonString = null, todayDate = Utilities.getTodaysDate(), today_date[] = todayDate.split("-");
		int esrMaxYear = Integer.parseInt(today_date[0]) + 1, esrMinYear = 0;
		JSONArray jsonArray = new JSONArray();
		try
		{
			esrMinYear = consentServices.consentMinYearForEsr();
			int maxYearDiff = esrMaxYear - esrMinYear;
			for (int i = 0; i <= maxYearDiff; i++)
			{
				String yearPair = (esrMaxYear - 1) + "-" + esrMaxYear;
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("esrYear", new String(yearPair));
				jsonArray.put(hashMap);
				esrMaxYear = esrMaxYear - 1;
			}
			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	///// mmm
	@RequestMapping("/ajax-getYearlyESRProductValues")
	public @ResponseBody String getYearlyESRProductValues(
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "datefrom", required = false) String datefrom,
			@RequestParam(value = "dateto", required = false) String dateto,
			@RequestParam(value = "workingDays", required = false) int workingDays,
			@RequestParam(value = "esrConsentDate", required = false) String esrConsentDate) throws JSONException
	{
		String jsonString = null, product_name = "NA", unitp = "NA", units = null;
		Float quantity = null, consent_quantity = null;

		try
		{
			JSONArray jsonArray = new JSONArray();
			List<Object[]> allProductsLists = new ArrayList<>();
			if (category.equalsIgnoreCase("Industry") || type.equalsIgnoreCase("Product")
					|| type.equalsIgnoreCase("byproduct") || type.equalsIgnoreCase("raw")
					|| type.equalsIgnoreCase("Fuel") || type.equalsIgnoreCase("hwp") || type.equalsIgnoreCase("hwpcf")
					|| type.equalsIgnoreCase("nhwp") || type.equalsIgnoreCase("nhwpcf"))
			{

				allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(type, esrConsentDate);
				if (!Validator.isEmpty(allProductsLists))
				{
					for (Object[] allProductsListData : allProductsLists)
					{
						units = (String) allProductsListData[2];
						String unitSplit[] = units.split("/");
						unitp = unitSplit[0] + "/Y";
						consent_quantity = Utilities.getFloatpoint((Float) allProductsListData[0], 2);

						if (units.equalsIgnoreCase("mg/Nm3"))
						{
							unitp = "mg/NM3";
						}
						else
						{
							consent_quantity = Utilities.getYearlyConsentQuantity(units, unitSplit[0], consent_quantity, workingDays);
						}
						product_name = (String) allProductsListData[1];
						quantity = regularDataServices.findRegulardataSumBYYear(product_name, datefrom, dateto);
						if (quantity != null)
						{
							quantity = Utilities.getFloatpoint(quantity, 2);
						}
						else
						{
							quantity = (float) 0.0;
						}
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("product_name", new String(product_name));
						jsonObject.put("consent_quantity", new Float(consent_quantity));
						jsonObject.put("quantity", new Float(quantity));
						jsonObject.put("unitp", new String(unitp));
						jsonArray.put(jsonObject);
					}

				}
				else
				{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("product_name", "NA");
					jsonObject.put("consent_quantity", "NA");
					jsonObject.put("quantity", "NA");
					jsonObject.put("unitp", "NA");
					jsonArray.put(jsonObject);
				}
			}
			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	//// 2
	@RequestMapping("/ajax-getESRYearlyWaterConsumption")
	public @ResponseBody String getESRYearlyWaterConsumption(@Param("processCons") Float processCons, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo,
			@Param("selectedYear") String selectedYear)
	{

		String[] list1 = new String[] {"Biodegradable", "Cooling", "Domestic", "All-Others"};
		String[] list2 = new String[] {"process", "cooling", " domestic", "others"};

		List<WaterInventory> getCookingData = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		String today = Utilities.getTodaysDate(), type1 = null;// cookingCanteen = null, houseCanteen = null,
		String jsonString = null;
		Float processAvgFilter = 0.0f, processAvgWater = 0.0f, processwaterFilter = 0.0f, processAvg = 0.0f, avgStaff = 1f, y = 0.0f;
		int n1 = 0, n2 = 0;
		try
		{
			List<Integer> cToOId = consentServices.findByDate(dateTo, new PageRequest(0, 1));

			for (int i = 0; i < list1.length; i++)
			{
				int consentId = cToOId.get(0);
				if (list1[i].equalsIgnoreCase("Biodegradable"))
				{
					list1[i] = "Biodegradable".toLowerCase();
					type1 = "non-biodegradable".toLowerCase();
					processCons = waterConGenComparativeSheetServices.getSumOfProcessConMonth("Biodegradable", consentId)
							+ waterConGenComparativeSheetServices.getSumOfProcessConMonth("Non Biodegradable", consentId);
					if (processCons == null)
						processCons = 0.0f;
					processAvgFilter = regularFiltersUseDataServices.getActualReadingByRFDate("Process", dateFrom, dateTo);

					if (processAvgFilter == 0)
						processAvgFilter = 0.0f;
					processAvgWater = regularWaterUseDataServices.getActualReadingbydate("Process", dateFrom, dateTo);
					if (processAvgWater == null)
						processAvgWater = 0.0f;
					if (processAvgFilter > 0)
					{
						n1 = 1;
					}
					else
					{
						n1 = 0;
					}
					if (processAvgWater > 0)
					{
						n2 = 1;
					}
					else
					{
						n2 = 0;
					}
					int n3 = n1 + n2;
					if (n3 == 0)
						n3 = 1;

					processAvg = (processAvgFilter + processAvg) / (n3);
					processAvg = Utilities.getFloatpoint(processAvg, 2);
					processCons = Utilities.getFloatpoint(processCons, 2);

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("Namee", new String(list2[i]));
					jsonObject.put("constantValue", new Float(processCons));
					jsonObject.put("ActualQuantity", new Float(processAvg));
					jsonArray.put(jsonObject);
				}
				else if (list1[i].equalsIgnoreCase("Cooling"))
				{
					processCons = waterConGenComparativeSheetServices.getSumOfProcessConMonth(list1[i], consentId);
					if (processCons == null)
						processCons = 0.0f;
					processAvgFilter = regularFiltersUseDataServices.getActualReadingByRFDate("Cooling", dateFrom, dateTo);// regularFiltersUseDataServices.getCoolingActualReadingByRFDate(dateFrom, dateTo);
					if (processAvgFilter == null)
						processAvgFilter = 0.0f;
					processAvgWater = regularWaterUseDataServices.getActualReadingbydate("Cooling", dateFrom, dateTo);// regularWaterUseDataServices.getCoolingSumActualReading(dateFrom, dateTo);
					if (processAvgWater == null)
						processAvgWater = 0.0f;
					if (processAvgFilter > 0)
					{
						n1 = 1;
					}
					else
					{
						n1 = 0;
					}
					if (processAvgWater > 0)
					{
						n2 = 1;
					}
					else
					{
						n2 = 0;
					}
					int n3 = n1 + n2;
					if (n3 == 0)
						n3 = 1;
					processAvg = (processAvgFilter + processAvg) / (n3);
					processAvg = Utilities.getFloatpoint(processAvg, 2);
					processCons = Utilities.getFloatpoint(processCons, 2);

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("Namee", new String(list2[i]));
					jsonObject.put("constantValue", new Float(processCons));
					jsonObject.put("ActualQuantity", new Float(processAvg));
					jsonArray.put(jsonObject);
				}

				else if (list1[i].equalsIgnoreCase("domestic"))
				{
					processCons = waterConGenComparativeSheetServices.getSumOfProcessConMonth(list1[i], consentId);
					if (processCons == null)
						processCons = 0.0f;
					processAvgFilter = regularFiltersUseDataServices.getActualReadingByRFDate("Domestic", dateFrom, dateTo);// regularWaterUseDataServices.getDomesticSumActualReading(dateFrom, dateTo);
					if (processAvgFilter == null)
						processAvgFilter = 0.0f;
					processAvgWater = regularWaterUseDataServices.getActualReadingbydate("Domestic", dateFrom, dateTo);// regularSourceDataServices.getAvgActualReadingBetweenDates(dateFrom, dateTo);
					if (processAvgWater == null)
						processAvgWater = 0.0f;
					if (processAvgFilter > 0)
					{
						n1 = 1;
					}
					else
					{
						n1 = 0;
					}
					if (processAvgWater > 0)
					{
						n2 = 1;
					}
					else
					{
						n2 = 0;
					}
					int n3 = n1 + n2;
					if (n3 == 0)
						n3 = 1;
					processwaterFilter = (processAvgFilter + processAvg) / (n3);
					getCookingData = waterInventoryServices.getHouseCookingCateenData();
					y = processwaterFilter * avgStaff;
					if (!Validator.isEmpty(getCookingData))
					{
						boolean cookingCanteen = getCookingData.get(0).isCookingCanteen();
						boolean houseCanteen = getCookingData.get(0).isHouseCanteen();

						if (houseCanteen)
						{
							if (cookingCanteen)
							{
								processAvg = (float) (y * 0.9);
								processAvg = Utilities.getFloatpoint(processAvg, 2);
							}
							else
							{
								processAvg = (float) (y * 0.45);
								processAvg = Utilities.getFloatpoint(processAvg, 2);
							}
						}
						else
						{
							processAvg = (float) (y * 0.45);
							processAvg = Utilities.getFloatpoint(processAvg, 2);
						}
					}
					processCons = Utilities.getFloatpoint(processCons, 2);

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("Namee", new String(list2[i]));
					jsonObject.put("constantValue", new Float(processCons));
					jsonObject.put("ActualQuantity", new Float(processAvg));
					jsonArray.put(jsonObject);
				}
				else
				{
					processCons = waterConGenComparativeSheetServices.getSumOfProcessConMonth("Others", consentId);
					processCons = Utilities.getFloatpoint(processCons, 2);
					processAvg = regularWaterUseDataServices.getActualReadingbydate("Fire Hydrant", dateFrom, dateTo);// regularWaterUseDataServices.getFireHydrantAndLaundryAvgActualReading(dateFrom, dateTo);
					if (processAvg == null)
						processAvg = 0.0f;
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("Namee", new String(list2[i]));
					jsonObject.put("constantValue", new Float(processCons));
					jsonObject.put("ActualQuantity", new Float(processAvg));
					jsonArray.put(jsonObject);
				}

			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		jsonString = jsonArray.toString();
		return jsonString;
	}

	/// 3
	@RequestMapping("/ajax-getYearlyProcessWaterConsumption")
	public @ResponseBody String getProcessWaterConsumption(@RequestParam(value = "dateFrom") String dateFrom,
			@RequestParam(value = "dateTo") String dateTo,
			@RequestParam(value = "selected_year", required = false) String esrYear,
			@RequestParam(value = "productType", required = false) String productType, HttpServletRequest request)
			throws JSONException
	{

		String jsonString = null, productName = "NA", previousData = "NA", currentData = "NA";
		int productCount = 0;
		List<String> distProductLists = new ArrayList<>();
		List<EsrProductWater> esrProductWaterDatas = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		try
		{
			String industryCategory = (String) request.getSession().getAttribute("sessionIndustryType");
			if (productType.equalsIgnoreCase("Product"))
			{
				productType = "product";
			}
			String keys[] = {"productName", "previousData", "currentData", "ip", "productCount", "productType",
					"selectedYear"};
			if (industryCategory.equalsIgnoreCase("Industry"))
			{
				distProductLists = allProductComparativeSheetServices.getDistinctProductNameByEsrYear(productType, dateTo);
				productCount = distProductLists.size();
				if (!distProductLists.isEmpty())
				{
					for (int i = 0; i < distProductLists.size(); i++)
					{
						productName = (String) distProductLists.get(i);
						esrProductWaterDatas = esrProductWaterServices.getAllDataByProNameTypeYear(esrYear, productName,
								productType);
						if (!esrProductWaterDatas.isEmpty())
						{

							for (int j = 0; j < esrProductWaterDatas.size(); j++)
							{
								previousData = esrProductWaterDatas.get(j).getPreviousData();
								currentData = esrProductWaterDatas.get(j).getCurrentData();

								for (EsrProductWater esrProductWaterData : esrProductWaterDatas)
								{
									previousData = esrProductWaterData.getPreviousData();
									currentData = esrProductWaterData.getCurrentData();
									HashMap<String, String> hashMap = new HashMap<String, String>();
									hashMap.put(keys[0], new String(productName));
									hashMap.put(keys[1], new String(previousData));
									hashMap.put(keys[2], new String(currentData));
									hashMap.put(keys[3], "SHOWDATA");
									hashMap.put(keys[4], Integer.toString(productCount));
									hashMap.put(keys[5], new String(productType));
									hashMap.put(keys[6], new String(esrYear));
									jsonArray.put(hashMap);
								}
							}
						}
						else
						{
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put(keys[0], new String(productName));
							hashMap.put(keys[1], previousData);
							hashMap.put(keys[2], currentData);
							hashMap.put(keys[3], "NA");
							hashMap.put(keys[4], new Integer(productCount));
							hashMap.put(keys[5], new String(productType));
							hashMap.put(keys[6], new String(esrYear));
							jsonArray.put(hashMap);
						}
					}
				}
				else
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put(keys[0], "NA");
					hashMap.put(keys[1], "NA");
					hashMap.put(keys[2], "NA");
					hashMap.put(keys[3], "NA");
					hashMap.put(keys[4], "NA");
					hashMap.put(keys[5], "NA");
					hashMap.put(keys[6], "NA");
					jsonArray.put(hashMap);
				}
				jsonString = jsonArray.toString();
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	/// 4
	@RequestMapping(value = "/ajax-getRecycledDataYear", method = RequestMethod.POST)
	public @ResponseBody String getEsrRecycledDataYear(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		String jsonString = null;
		List<EsrProductWater> esrProductWaterDatas = new ArrayList<>();
		esrProductWaterDatas = esrProductWaterServices.getAllDataByYear(selectedYear, type);
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (int i = 0; i < esrProductWaterDatas.size(); i++)
		{
			json = new JSONObject();
			json.put("PollName", esrProductWaterDatas.get(i).getProductName());
			json.put("PrivieousData", esrProductWaterDatas.get(i).getPreviousData());
			json.put("CurrantData", esrProductWaterDatas.get(i).getCurrentData());
			json.put("Uom", esrProductWaterDatas.get(i).getUnit());

			jsonArray.put(json);
		}
		int size = jsonArray.length();
		if (size != 0)
		{
			jsonString = jsonArray.toString();
		}

		return jsonString;
	}

	/// 5
	@RequestMapping(value = "/ajax-getHazardusDataYear", method = RequestMethod.POST)
	public @ResponseBody String getEsrHazDataYear(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		String jsonString = null;
		List<EsrHwSolidWaste> esrProductWaterDatas = new ArrayList<>();
		esrProductWaterDatas = esrHwSolidWasteServices.getAllDataByTypeMonth(selectedYear, type);
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (int i = 0; i < esrProductWaterDatas.size(); i++)
		{
			json = new JSONObject();
			json.put("WasteType", esrProductWaterDatas.get(i).getTypeOfWaste());
			json.put("Quantity", esrProductWaterDatas.get(i).getQuantity());
			json.put("Concentration", esrProductWaterDatas.get(i).getConcentration());
			json.put("Uom", esrProductWaterDatas.get(i).getUnit());

			jsonArray.put(json);
		}
		int size = jsonArray.length();
		if (size != 0)
		{
			jsonString = jsonArray.toString();
		}

		return jsonString;
	}

	/// 6
	@RequestMapping(value = "/ajax-getPollutionControlDataYear", method = RequestMethod.POST)
	public @ResponseBody String getPolutionControlDataYear(
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		String jsonString = null;
		List<EsrPollutionControl> esrProductWaterDatas = new ArrayList<>();
		esrProductWaterDatas = esrPollutionControlServices.findOneByEsrPollutionByYear(selectedYear);
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (int i = 0; i < esrProductWaterDatas.size(); i++)
		{
			json = new JSONObject();
			json.put("Description", esrProductWaterDatas.get(i).getDescription());
			json.put("RedWater", esrProductWaterDatas.get(i).getReductionWater());
			json.put("RedFule", esrProductWaterDatas.get(i).getReductionFuel());
			json.put("RedRaw", esrProductWaterDatas.get(i).getReductionRm());
			json.put("RedPowar", esrProductWaterDatas.get(i).getReductionPc());
			json.put("CapitalInvestment", esrProductWaterDatas.get(i).getCapitalInvestment());
			json.put("RedMaintaince", esrProductWaterDatas.get(i).getReductionMaintenance());

			jsonArray.put(json);
		}
		int size = jsonArray.length();
		if (size != 0)
		{
			jsonString = jsonArray.toString();
		}

		return jsonString;
	}

	/// 7
	@RequestMapping(value = "/ajax-getParticularDataYear", method = RequestMethod.POST)
	public @ResponseBody String getParticularDataYear(
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		String jsonString = null;
		List<EsrParticulars> particularrDatas = new ArrayList<>();
		particularrDatas = esrParticularsServices.getEsrParticularsbyyear(selectedYear);
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (int i = 0; i < particularrDatas.size(); i++)
		{
			json = new JSONObject();
			json.put("Particular", particularrDatas.get(i).getParticulars());

			jsonArray.put(json);
		}
		int size = jsonArray.length();
		if (size != 0)
		{
			jsonString = jsonArray.toString();
		}

		return jsonString;
	}

	//// 8
	@RequestMapping(value = "/ajax-getInvestmentNextDataYear", method = RequestMethod.POST)
	public @ResponseBody String getEnviromentInvestmentNextDataYear(
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		String jsonString = null;
		String year1[] = selectedYear.split("-");
		int a = Integer.parseInt(year1[0]), b = Integer.parseInt(year1[1]);
		String nextYear = (a + 1) + "-" + (b + 1);
		List<EsrInvestment> esrInvestmenDatas = new ArrayList<>();
		esrInvestmenDatas = esrInvestmentServices.findOneByGetEsrInvestmentByMonth(selectedYear, nextYear);
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (int i = 0; i < esrInvestmenDatas.size(); i++)
		{
			json = new JSONObject();
			json.put("Detail", esrInvestmenDatas.get(i).getDetails());
			json.put("Measures", esrInvestmenDatas.get(i).getProtectionMea());
			json.put("Investment", esrInvestmenDatas.get(i).getCapitalInvestment());

			jsonArray.put(json);
		}
		int size = jsonArray.length();
		if (size != 0)
		{
			jsonString = jsonArray.toString();
		}

		return jsonString;
	}

	private static float getYearlyConsentQuantity(String unit, String unitSplit, float quantity, int workinhHr)
	{

		Float actual_quantity = null;
		try
		{
			if (unit.equalsIgnoreCase(unitSplit + "/Year"))
			{
				actual_quantity = quantity;
			}
			if (unit.equalsIgnoreCase(unitSplit + "/Month"))
			{
				actual_quantity = quantity * 12;
			}
			if (unit.equalsIgnoreCase(unitSplit + "/Day"))
			{
				actual_quantity = quantity * 365;
			}
			if (unit.equalsIgnoreCase(unitSplit + "/Hr"))
			{
				actual_quantity = quantity * 24 * 30 * 12;
			}
			if (unit.equalsIgnoreCase(unitSplit + "/Week"))
			{
				actual_quantity = quantity * 52;
			}
			actual_quantity = Utilities.getFloatpoint(actual_quantity, 2);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return actual_quantity;

	}

	/// 9
	@RequestMapping(value = "/ajax-getInvestmentDataYear", method = RequestMethod.POST)
	public @ResponseBody String getEnviromentInvestmentDataYear(
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		String jsonString = null;
		List<EsrInvestment> esrInvestmenDatas = new ArrayList<>();
		esrInvestmenDatas = esrInvestmentServices.findOneByGetEsrInvestmentByMonth(selectedYear, selectedYear);
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		for (int i = 0; i < esrInvestmenDatas.size(); i++)
		{
			json = new JSONObject();
			json.put("Detail", esrInvestmenDatas.get(i).getDetails());
			json.put("Measures", esrInvestmenDatas.get(i).getProtectionMea());
			json.put("Investment", esrInvestmenDatas.get(i).getCapitalInvestment());

			jsonArray.put(json);
		}
		int size = jsonArray.length();
		if (size != 0)
		{
			jsonString = jsonArray.toString();
		}

		return jsonString;
	}

	/// 10
	@RequestMapping("/ajax-getESRYearlyWaterGeneration")
	public @ResponseBody String getESRYearlyWaterGeneration(
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo) throws JSONException
	{

		String jsonString = null;
		Float StpValue = 0.0f, EtpValue = 0.0f, Etp = 0.0f, quanETP = 0.0f, quanSTP = 0.0f;
		String[] list1 = new String[] {"domestic", "Biodegradable", "Non Biodegradable", "cooling"};
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		try
		{
			List<Integer> cToOId = consentServices.findByDate(dateTo, new PageRequest(0, 1));
			for (int i = 0; i < list1.length; i++)
			{
				int consentId = cToOId.get(0);
				switch (list1[i])
				{

					case "domestic":
						StpValue = waterConGenComparativeSheetServices.getSumOfProcessGenMonth(list1[i], consentId);
						break;

					default:
						Etp = waterConGenComparativeSheetServices.getSumOfProcessGenMonth(list1[i], consentId);
						EtpValue += Etp;
				}
			}

			quanETP = regularTreatmentDataServices.getActualReadingByTypeAndDate("ETP", dateFrom, dateTo);
			if (quanETP == null)
				quanETP = 0.0f;
			quanSTP = regularTreatmentDataServices.getActualReadingByTypeAndDate("STP", dateFrom, dateTo);
			if (quanSTP == null)
				quanSTP = 0.0f;
			json = new JSONObject();
			json.put("StpConsent", StpValue);
			json.put("EtpConsent", EtpValue);
			json.put("EtpAvg", quanETP);
			json.put("StpAvg", quanSTP);

			jsonArray.put(json);

			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);

		}

		return jsonString;

	}

	/// 11
	@RequestMapping("/ajax-getYearlyEffluentPollutant")
	public @ResponseBody String getEffluentPollutant(
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{

		String jsonString = null, pollName = "NA";
		List<WateriePollutant> wateriePollutantDatas = new ArrayList<>();
		Float quantity = 0.0f, quan = 0.0f;
		float variation = 0;
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		try
		{
			wateriePollutantDatas = wateriePollutantServices.getPollNameAndQuantity();
			if (!wateriePollutantDatas.isEmpty())
			{
				for (int i = 0; i < wateriePollutantDatas.size(); i++)
				{
					pollName = (String) wateriePollutantDatas.get(i).getPollName();
					quantity = (float) wateriePollutantDatas.get(i).getQuantity();
					quan = regEffPollServices.getAvgRegEffOuConsE(pollName, dateTo, dateFrom);
					if (quan == null)
						quan = 0.0f;
					String rs = "NOT";
					String reason = esrWaterPollReasonServices.pollReasonByName(pollName, "eff", selectedYear);
					if (reason != null)
						rs = reason;
					json = new JSONObject();
					double variationQuant;

					if (pollName.equalsIgnoreCase("pH"))
					{
						if (quan >= 5 && quan <= 9)
						{
							variationQuant = 0;
							json.put("variation", variationQuant);
						}
						else
						{
							if (quan < 5)
							{
								variationQuant = (quan / 5) * 100;
								json.put("variation", Utilities.getDoubleRoundPoint(variationQuant, 2));
							}
							else if (quan > 9)
							{
								variationQuant = (quan / 9) * 100;
								json.put("variation", Utilities.getDoubleRoundPoint(variationQuant, 2));
							}

						}
					}
					else
					{
						if (quan != 0)
						{
							variationQuant = ((quan - quantity) / quantity) * 100;
							json.put("variation", Utilities.getDoubleRoundPoint(variationQuant, 2));
						}
						else
						{
							json.put("variation", "NA");
						}
					}
					float QuantityPollutant = (float) (quan * 0.000001);
					json.put("PollName", pollName);
					json.put("Quantity", Utilities.getDoubleRoundPoint(QuantityPollutant, 6));
					json.put("Concentration", quan);
					json.put("Standard", quantity);
					json.put("reason", rs);
					json.put("dateFrom", dateFrom);
					json.put("dateTo", dateTo);

					jsonArray.put(json);
				}

			}
			else
			{
				json = new JSONObject();
				json.put("PollName", "NODATA");
				json.put("Quantity", "NODATA");
				json.put("Concentration", "NODATA");
				json.put("variation", "NODATA");
				json.put("Standard", "NODATA");
				json.put("dateFrom", dateFrom);
				json.put("dateTo", dateTo);

				jsonArray.put(json);
			}
			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	//// 12
	@RequestMapping("/ajax-getYearlySewagePollutant")
	public @ResponseBody String getYearlySewagePollutant(
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{

		String jsonString = null, pollName = "NA";
		List<WaterSewPoll> getWaterSewPolls = new ArrayList<>();
		Float quantity = 0.0f, variation = 0.0f, quan = 0.0f;
		JSONArray jsonArray = new JSONArray();
		JSONObject json;

		List<String> rsList = new ArrayList<>();
		try
		{
			getWaterSewPolls = waterSewPollServices.getWaterSewPollNameAndQuantity();
			if (!getWaterSewPolls.isEmpty())
			{
				for (int i = 0; i < getWaterSewPolls.size(); i++)
				{
					pollName = (String) getWaterSewPolls.get(i).getPollName();
					quantity = (float) getWaterSewPolls.get(i).getQuantity();
					quan = regSewPollServices.getSewAvgOuConsByDate(pollName, dateFrom, dateTo);
					if (quan == null)
						quan = 0.0f;
					String rs = esrWaterPollReasonServices.pollReasonByName(pollName, "sew", selectedYear);
					if (Validator.isEmpty(rs))
						rs = "NOT";

					json = new JSONObject();
					double variationQuant;

					if (quan != 0)
					{
						variationQuant = ((quan - quantity) / quantity) * 100;
						json.put("variation", variationQuant);
					}
					else
					{
						json.put("variation", "NA");
					}
					float QuantityPollutant = (float) (quan * 0.000001);
					json.put("PollName", pollName);
					json.put("Quantity", QuantityPollutant);
					json.put("Concentration", quan);
					json.put("Standard", quantity);
					json.put("reason", rs);
					json.put("dateFrom", dateFrom);
					json.put("dateTo", dateTo);

					jsonArray.put(json);

				}

			}
			else
			{
				json = new JSONObject();
				json.put("PollName", "NODATA");
				json.put("Quantity", "NODATA");
				json.put("Concentration", "NODATA");
				json.put("variation", "NODATA");
				json.put("Standard", "NODATA");
				json.put("dateFrom", dateFrom);
				json.put("dateTo", dateTo);

				jsonArray.put(json);
			}
			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	/// 13
	@RequestMapping("/ajax-getYearlyAirStack")
	public @ResponseBody String getYearlyAirStack(@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo") String dateTo,
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		String jsonString = null;
		JSONObject json;
		JSONArray jsonArray = new JSONArray();
		String today = Utilities.getTodaysDate();
		List<StackPoll> stackPollDataList = new ArrayList<>();

		List<Stack> stack = stackServices.getConsentId(today);
		if (!stack.isEmpty())
		{
			for (int i = 0; i < stack.size(); i++)
			{
				Integer stackId = stack.get(i).getStackId();
				stackPollDataList = stackPollServices.findByStackId(stack.get(i).getStackId());
				for (int j = 0; j < stackPollDataList.size(); j++)
				{
					json = new JSONObject();
					String pollName = stackPollDataList.get(j).getPollName();
					String nameOfAttachTo = stack.get(i).getAttachedTo();
					String stackName = stack.get(i).getStackName();
					String mainName = pollName + '-' + stackName + '-' + nameOfAttachTo;
					Float consActual = stackPollDataServices.getAvgconcPollByName(pollName, stackId, dateFrom, dateTo);

					if (consActual == null)
						consActual = 0.0f;
					Float conLimit = stackPollDataList.get(j).getPollLimit();
					String unit = stackPollDataList.get(j).getUnit().getUnits();
					double variationQuant;
					if (consActual != 0)
					{
						variationQuant = ((consActual - conLimit) / conLimit) * 100;
						json.put("variation", variationQuant);
					}
					else
					{
						json.put("variation", "NA");
					}

					String rs = esrStackPollReasonsServices.getReasonByDate(mainName, selectedYear);

					if (Validator.isEmpty(rs))
						rs = "NO";

					float QuantityPollutant = (float) (consActual * 0.000001);
					json.put("PollName", mainName);
					json.put("Quantity", QuantityPollutant);
					json.put("Concentration", consActual);
					json.put("Standard", conLimit);
					json.put("reason", rs);
					jsonArray.put(json);
				}
			}
		}
		else
		{
			json = new JSONObject();
			json.put("PollName", "NODATA");
			json.put("Quantity", "NODATA");
			json.put("Concentration", "NODATA");
			json.put("variation", "NODATA");
			json.put("Standard", "NODATA");
			json.put("reason", "reason");
			jsonArray.put(json);
		}
		jsonString = jsonArray.toString();
		return jsonString;
	}

	// 14
	@RequestMapping("/ajax-getYearlyHazardousProcessData")
	public @ResponseBody String getHazardousProcessData(@RequestParam(value = "ptype", required = false) String type,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		String jsonString = null, dateFromprv = null, dateToprv = null;
		List<Object[]> allProductsLists = new ArrayList<>();

		int year1 = Integer.parseInt(dateFrom.split("-")[0]) - 1;
		int year2 = Integer.parseInt(dateFrom.split("-")[0]);
		dateFromprv = year1 + "04-01";
		dateToprv = year2 + "03-31";
		allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(type, dateTo);
		for (Object[] allProductsListData : allProductsLists)
		{
			String productName = (String) allProductsListData[1];
			String units = (String) allProductsListData[2];

			Float quantity = regularDataServices.getAverageQuantityByPNameAndBetweenDates(productName, dateFrom, dateTo);
			if (quantity == null)
				quantity = 0.0f;
			Float priviousQuantity = regularDataServices.getAverageQuantityByPNameAndBetweenDates(productName, dateFromprv, dateToprv);
			if (priviousQuantity == null)
				priviousQuantity = 0.0f;

			json = new JSONObject();
			json.put("HazName", productName);
			json.put("CurrentQuantity", quantity);
			json.put("PriveousQuantity", priviousQuantity);
			json.put("Unit", units);
			jsonArray.put(json);
		}
		jsonString = jsonArray.toString();
		return jsonString;
	}

	//// mmm

}
