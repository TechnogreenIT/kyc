package com.tes.controller.environmentalofficer;

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
import com.tes.model.EsrStackPollReasons;
import com.tes.model.EsrWaterPollReasons;
import com.tes.model.RegPollReasons;
import com.tes.model.Stack;
import com.tes.model.StackPoll;
import com.tes.model.WaterInventory;
import com.tes.model.WaterSewPoll;
import com.tes.model.WateriePollutant;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.EsrHwSolidWasteServices;
import com.tes.services.environmentalofficer.EsrInvestmentServices;
import com.tes.services.environmentalofficer.EsrParticularsServices;
import com.tes.services.environmentalofficer.EsrPollutionControlServices;
import com.tes.services.environmentalofficer.EsrProductWaterServices;
import com.tes.services.environmentalofficer.EsrStackPollReasonsServices;
import com.tes.services.environmentalofficer.EsrWaterPollReasonsServices;
import com.tes.services.environmentalofficer.RegPollReasonsServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.services.environmentalofficer.WaterConGenComparativeSheetServices;
import com.tes.services.environmentalofficer.WaterConGenServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.services.thirdparty.RegStPollServices;
import com.tes.services.thirdparty.StackPollDataServices;
import com.tes.services.thirdparty.StackPollServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class used to manage the yearly ESR form(i.e. process water generation and consumption, effluent, sewage and air stack data,
 * hazardous data, solid waste data, investment data, pollution control, particular, recycled data, etc) and save all types of product and water data.
 * 
 * @author Sushama Dadas
 */
@Controller
@RequestMapping(value = {"/env"})
public class ESRYearlyController
{
	@Autowired
	WaterConGenServices waterConGenServices;

	@Autowired
	EsrHwSolidWasteServices esrHwSolidWasteServices;

	@Autowired
	EsrPollutionControlServices esrPollutionControlServices;

	@Autowired
	EsrInvestmentServices esrInvestmentServices;

	@Autowired
	EsrParticularsServices esrParticularsServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	RegFiltersUseDataServices regularFiltersUseDataServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	RegPollReasonsServices regPollReasonsServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	EsrProductWaterServices esrProductWaterServices;

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	RegStPollServices regStPollServices;

	@Autowired
	StackPollServices stackPollServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	WaterConGenComparativeSheetServices waterConGenComparativeSheetServices;

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	UnitServices unitServices;

	@Autowired
	StackPollDataServices stackPollDataServices;

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	@Autowired
	EsrWaterPollReasonsServices esrWaterPollReasonServices;

	@Autowired
	StackServices stackServices;

	@Autowired
	EsrStackPollReasonsServices esrStackPollReasonsServices;
	private static final Logger LOGGER = LogManager.getLogger(ESRYearlyController.class);

	/**
	 * This method used to open yearly ESR Form
	 * 
	 * @param encodedYear the encoded year of selected year.
	 * @param request The servlet request we are processing.
	 * @return it returns EsrYearly model
	 */
	@RequestMapping("/yearly-esr")
	public ModelAndView getYearlyESRform(@RequestParam(value = "year", required = false) String encodedYear,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView();
		String year = Utilities.decodeString(encodedYear), dateRes[] = year.split("-"),
				fromDate = (dateRes[0]) + "-04-01", toDate = (dateRes[1]) + "-03-31", esrConsentDate = toDate,
				lastEnvSubmitted = null, consNo = "", issueDate = "", validUpto = "",
				set_title = "31st March " + dateRes[1];
		modelAndView.setViewName("EnvrOfficer/EsrYearly");
		String year1[] = year.split("-");
		int a = Integer.parseInt(year1[0]), b = Integer.parseInt(year1[1]);
		String year2 = (a + 1) + "-" + (b + 1);
		List<String> consNoList = new ArrayList<>();
		List<String> issueDateList = new ArrayList<>();
		List<String> validUptoList = new ArrayList<>();
		List<Float> capitalInvestmentList = new ArrayList<>();
		float capitalInvestment = 0.0f;
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
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to get ESR yearly water generation
	 * 
	 * @param effGen the effluent water generation data
	 * @param type the type of product
	 * @return res it return effluent and sewage generation
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
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

	/**
	 * This method used to get ESR yearly water generation of sum
	 * 
	 * @param effGen it return the effluent water generation data
	 * @param type the type of product
	 * @param todayDate the current date
	 * @return it returns ConValue value of process, cooling, domestic, etc
	 */
	public float getESRYearlyWaterGenerationSum(Float effGen, String type, String todayDate)
	{
		float ConValue = 0;
		Float conGenSum = 0.0f;
		try
		{
			conGenSum = waterConGenComparativeSheetServices.getSumOfProcessGen(type, todayDate);
			effGen = effGen + conGenSum;
			conGenSum = waterConGenComparativeSheetServices.getSumOfProcessGen("Non-Biodegradable", todayDate);
			effGen = effGen + conGenSum;
			conGenSum = waterConGenComparativeSheetServices.getSumOfProcessGen("cooling", todayDate);
			effGen = effGen + conGenSum;
			ConValue = Utilities.round(effGen, 2);
			ConValue = waterConGenComparativeSheetServices.getSumOfProcessGen("domestic", todayDate);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return ConValue;
	}

	/**
	 * This method used to get number of water treatment
	 * 
	 * @return res it return number of etp and stp
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getNoOfWaterTreatment")
	public @ResponseBody String getNoOfWaterTreatment() throws JSONException
	{

		String res = null;
		int etp_no = 0, stp_no = 0;
		try
		{
			etp_no = wastewaterTreatmentServices.getWaterTreatmentType("ETP");
			stp_no = wastewaterTreatmentServices.getWaterTreatmentType("STP");
			res = etp_no + "-" + stp_no;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return res;
	}

	@RequestMapping("/ajax-getEsrYearlyGenActualQuantity")
	public @ResponseBody Float getEsrYearlyGenActualQuantity(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "year", required = false) String year) throws JSONException
	{
		int etpno = 0;
		Float etpQuantity = 0.0f;
		String dateFrom = year.split("-")[0] + "-04-01";
		String dateTo = year.split("-")[1] + "-03-31";
		etpno = wastewaterTreatmentServices.getWaterTreatmentType(type);
		if (etpno > 0)
		{
			for (int i = 0; i <= etpno; i++)
			{
				// effected by water inventory .. sushma
				// etpQuantity = regularTreatmentDataServices.getAvgActualReadingByYearandType(type, dateFrom, dateTo);
			}
		}
		return etpQuantity;
	}

	/**
	 * This method used to get yearly process water consumption data
	 * 
	 * @param dateFrom the start date
	 * @param dateTo the End date
	 * @param esrYear the ESR year
	 * @param productType the type of product type
	 * @param request the servlet request we are processing.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("ajax-getESRYearlyWaterConsumption")
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

	/**
	 * This method is used to get ESR yearly water consumption.
	 * 
	 * @param processCons return the process consumption values
	 * @param type the type of product
	 * @param dateFrom the selected Start date of yearly water consumption
	 * @param dateTo the selected End date of yearly water consumption
	 * @param selectedYear the selected year.
	 * @return jsonString it returns String value of json array
	 */
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

	/**
	 * This method used to save ESR product water data
	 * 
	 * @param productName the name of the product
	 * @param prevData the previous data of esr product water
	 * @param currData the current data of esr product water
	 * @param unit the unit of the product
	 * @param productType the type of the product
	 * @param year the year of the ESR
	 * @param request the servlet request we are processing.
	 * @return jsonString it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-save-product-esr-water")
	public @ResponseBody String saveProductEsrWater(@RequestParam(value = "productList") String[] productName,
			@RequestParam(value = "prevDataList") String[] prevData,
			@RequestParam(value = "currDataList", required = false) String[] currData,
			@RequestParam(value = "unitList", required = false) String[] unit,
			@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value = "year", required = false) String year, HttpServletRequest request)
			throws JSONException
	{
		String jsonString = null;
		JSONArray jsonArray = new JSONArray();
		try
		{
			String dateFrom = year.split("-")[0] + "-04-01", dateTo = year.split("-")[1] + "-03-31";
			EsrProductWater esrProductWaterData = new EsrProductWater();
			for (int i = 0; i < productName.length; i++)
			{
				EsrProductWater esrProductWater = new EsrProductWater();
				esrProductWater.setProductName(productName[i]);
				esrProductWater.setPreviousData(prevData[i]);
				esrProductWater.setCurrentData(currData[i]);
				esrProductWater.setProductType(productType);
				esrProductWater.setUnit(unit[i]);
				esrProductWater.setYear(year);
				esrProductWaterData = esrProductWaterServices.save(esrProductWater);
			}
			if (esrProductWaterData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("dateFrom", new String(dateFrom));
				jsonObject.put("dateTo", new String(dateTo));
				jsonObject.put("selectedYear", new String(year));
				jsonArray.put(jsonObject);
				jsonString = jsonArray.toString();
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	/**
	 * This method used to get yearly esr product values
	 * 
	 * @param dateFrom the from date
	 * @param dateTo the to date
	 * @param selectedYear the selected year
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
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

	/**
	 * This method used to get Yearly Effluent Pollutant
	 * 
	 * @param dateFrom the from date
	 * @param dateTo the to date
	 * @param selectedYear the selected year
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
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

	/**
	 * This method used to get yearly sewage water pollutant data of yearly esr
	 * 
	 * @param dateFrom the from date
	 * @param dateTo the to date
	 * @param selectedYear the selected year
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
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

	/**
	 * This method used to get air stack water data
	 * 
	 * @param dateFrom the from date
	 * @param dateTo the To date
	 * @param selectedYear the selected year
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
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

	/**
	 * This method used to get yearly hazardous process data.
	 * 
	 * @param type the type of the product
	 * @param dateFrom the from date
	 * @param dateTo the To date
	 * @param selectedYear the selected year
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
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

	/**
	 * This method used to save recycled the ESR product water data of yearly
	 * 
	 * @param wasteTypeList the type of the waste list.
	 * @param prevDataList previous data list of recycled data.
	 * @param currDataList current data list of recycled data.
	 * @param pTypeList the product type list of recycled data.
	 * @param unitList the unit list of the recycled data.
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveRecycled", method = RequestMethod.POST)
	public @ResponseBody String SaveQuantityRecycled(
			@RequestParam(value = "recycled", required = false) String[] wasteTypeList,
			@RequestParam(value = "recycled_prev", required = false) String[] prevDataList,
			@RequestParam(value = "recycled_curr", required = false) String[] currDataList,
			@RequestParam(value = "ptype", required = false) String pTypeList,
			@RequestParam(value = "recycled_units") String[] unitList,
			@RequestParam(value = "selected_year") String selected_year) throws JSONException
	{

		String status = "success";
		try
		{
			EsrProductWater esrProductWaterData = new EsrProductWater();
			for (int i = 0; i < wasteTypeList.length; i++)
			{
				EsrProductWater esrProductWater = new EsrProductWater();
				esrProductWater.setProductName(wasteTypeList[i]);
				esrProductWater.setCurrentData(currDataList[i]);
				esrProductWater.setPreviousData(prevDataList[i]);
				esrProductWater.setUnit(unitList[i]);
				esrProductWater.setProductType(pTypeList);
				esrProductWater.setYear(selected_year);
				esrProductWaterData = esrProductWaterServices.save(esrProductWater);
			}
			if (esrProductWaterData != null)
			{
				status = selected_year;
			}
			else
			{
				status = "fail";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to save hazardous water data for yearly esr.
	 * 
	 * @param wasteTypeList the list of the waste type list
	 * @param quantityList the list of the quantity list
	 * @param unitList the list of the unit
	 * @param pTypeList the list of the product type list
	 * @param concList the concentration list
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveHazardousWaterData", method = RequestMethod.POST)
	public @ResponseBody String saveHazardousWaterDataEsrHWWaste(
			@RequestParam(value = "hw_id", required = false) String[] wasteTypeList,
			@RequestParam(value = "hw_quantity", required = false) Float[] quantityList,
			@RequestParam(value = "hw_units", required = false) String[] unitList,
			@RequestParam(value = "ptype", required = false) String pTypeList,
			@RequestParam(value = "hw_conc") Float[] concList,
			@RequestParam(value = "selected_year") String selected_year) throws JSONException
	{

		String status = null;
		try
		{
			EsrHwSolidWaste esrHwSolidWasteData = new EsrHwSolidWaste();
			for (int i = 0; i < wasteTypeList.length; i++)
			{
				EsrHwSolidWaste esrHwSolidWaste = new EsrHwSolidWaste();
				esrHwSolidWaste.setYear(selected_year);
				esrHwSolidWaste.setTypeOfWaste(wasteTypeList[i]);
				esrHwSolidWaste.setConcentration(concList[i]);
				esrHwSolidWaste.setQuantity(quantityList[i]);
				esrHwSolidWaste.setType(pTypeList);
				esrHwSolidWaste.setUnit(unitList[i]);
				esrHwSolidWasteData = esrHwSolidWasteServices.save(esrHwSolidWaste);
			}
			if (esrHwSolidWasteData != null)
			{
				status = selected_year;
			}
			else
			{
				status = "fail";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to save solid waste data for yearly esr.
	 * 
	 * @param wasteTypeList the list of the waste type list
	 * @param quantityList the list of the quantity list
	 * @param unitList the list of the unit list
	 * @param pTypeList the product type list
	 * @param concList the list of the concentration list
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveSolidWasteData", method = RequestMethod.POST)
	public @ResponseBody String saveSolidWasteDataESR(
			@RequestParam(value = "sw_id", required = false) String[] wasteTypeList,
			@RequestParam(value = "sw_quantity", required = false) Float[] quantityList,
			@RequestParam(value = "sw_units", required = false) String[] unitList,
			@RequestParam(value = "ptype", required = false) String pTypeList,
			@RequestParam(value = "sw_conc") Float[] concList,
			@RequestParam(value = "selected_year") String selected_year) throws JSONException
	{

		String status = null;
		try
		{
			EsrHwSolidWaste esrHwSolidWasteData = new EsrHwSolidWaste();
			for (int i = 0; i < wasteTypeList.length; i++)
			{
				EsrHwSolidWaste esrHwSolidWaste = new EsrHwSolidWaste();
				esrHwSolidWaste.setYear(selected_year);
				esrHwSolidWaste.setTypeOfWaste(wasteTypeList[i]);
				esrHwSolidWaste.setConcentration(concList[i]);
				esrHwSolidWaste.setQuantity(quantityList[i]);
				esrHwSolidWaste.setType(pTypeList);
				esrHwSolidWaste.setUnit(unitList[i]);
				esrHwSolidWasteData = esrHwSolidWasteServices.save(esrHwSolidWaste);
			}
			if (esrHwSolidWasteData != null)
			{
				status = selected_year;
			}
			else
			{
				status = "fail";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to save pollution control data of yearly ESR
	 * 
	 * @param description the description of data
	 * @param reduction_water the reduction water
	 * @param reduction_fuel the reduction fuel
	 * @param reduction_rm the reduction of raw material
	 * @param reduction_pc the reduction of pollustion control
	 * @param capital_investment the capital of investment
	 * @param reduction_maintenance the reduction maintenance
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-savePollutionData", method = RequestMethod.POST)
	public @ResponseBody String savePolutionControlDataESR(
			@RequestParam(value = "description", required = false) String[] description,
			@RequestParam(value = "reduction_water", required = false) Float[] reduction_water,
			@RequestParam(value = "reduction_fuel", required = false) Float[] reduction_fuel,
			@RequestParam(value = "reduction_rm", required = false) Float[] reduction_rm,
			@RequestParam(value = "reduction_pc", required = false) Float[] reduction_pc,
			@RequestParam(value = "capital_investment", required = false) Float[] capital_investment,
			@RequestParam(value = "reduction_maintenance", required = false) Float[] reduction_maintenance,
			@RequestParam(value = "selected_year", required = false) String selected_year) throws JSONException
	{

		String status = null;
		try
		{
			EsrPollutionControl esrPollutionControlData = new EsrPollutionControl();
			for (int i = 0; i < description.length; i++)
			{
				EsrPollutionControl esrPollutionControl = new EsrPollutionControl();
				esrPollutionControl.setDescription(description[i]);
				esrPollutionControl.setCapitalInvestment(capital_investment[i]);
				esrPollutionControl.setReductionFuel(reduction_fuel[i]);
				esrPollutionControl.setReductionMaintenance(reduction_maintenance[i]);
				esrPollutionControl.setReductionPc(reduction_pc[i]);
				esrPollutionControl.setReductionRm(reduction_rm[i]);
				esrPollutionControl.setReductionWater(reduction_water[i]);
				esrPollutionControl.setYear(selected_year);
				esrPollutionControlData = esrPollutionControlServices.save(esrPollutionControl);
			}
			if (esrPollutionControlData != null)
			{
				status = selected_year;
			}
			else
			{
				status = "fail";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to save environmental investment data
	 * 
	 * @param curr_detail the current details
	 * @param curr_protection_mea the curr_protection_mea
	 * @param curr_capital_investment the current capital investment
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveEnvInvestData", method = RequestMethod.POST)
	public @ResponseBody String saveEnvInvestDataEsr(
			@RequestParam(value = "curr_detail", required = false) String[] curr_detail,
			@RequestParam(value = "curr_protection_mea", required = false) String[] curr_protection_mea,
			@RequestParam(value = "curr_capital_investment", required = false) Float[] curr_capital_investment,
			@RequestParam(value = "selected_year", required = false) String selected_year) throws JSONException
	{

		String status = "false";
		try
		{
			EsrInvestment esrInvestmentData = new EsrInvestment();
			for (int i = 0; i < curr_detail.length; i++)
			{
				EsrInvestment esrInvestment = new EsrInvestment();
				esrInvestment.setProtectionMea(curr_protection_mea[i]);
				esrInvestment.setDetails(curr_detail[i]);
				esrInvestment.setInvestmentYear(selected_year);
				esrInvestment.setYear(selected_year);
				esrInvestment.setCapitalInvestment(curr_capital_investment[i]);
				esrInvestmentData = esrInvestmentServices.save(esrInvestment);
			}
			if (esrInvestmentData != null)
			{
				status = selected_year;
			}
			else
			{
				status = "fail";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to save Environment investment data of next year.
	 * 
	 * @param next_detail the detail of next year investment
	 * @param next_protection_mea the next_protection_mea of next year investment
	 * @param next_capital_investment the next_capital_investment of next year investment
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveEnvInvestDataNextYear", method = RequestMethod.POST)
	public @ResponseBody String saveEnvInvestDataNextYearESR(
			@RequestParam(value = "next_detail", required = false) String[] next_detail,
			@RequestParam(value = "next_protection_mea", required = false) String[] next_protection_mea,
			@RequestParam(value = "next_capital_investment", required = false) Float[] next_capital_investment,
			@RequestParam(value = "selected_year", required = false) String selected_year) throws JSONException
	{

		String status = "success", year1[] = selected_year.split("-");
		int a = Integer.parseInt(year1[0]), b = Integer.parseInt(year1[1]);
		String year2 = (a + 1) + "-" + (b + 1);
		try
		{
			EsrInvestment esrInvestmentData = new EsrInvestment();
			for (int i = 0; i < next_detail.length; i++)
			{
				EsrInvestment esrInvestment = new EsrInvestment();
				esrInvestment.setProtectionMea(next_protection_mea[i]);
				esrInvestment.setDetails(next_detail[i]);
				esrInvestment.setYear(selected_year);
				esrInvestment.setInvestmentYear(year2);
				esrInvestment.setCapitalInvestment(next_capital_investment[i]);
				esrInvestmentData = esrInvestmentServices.save(esrInvestment);
			}
			if (esrInvestmentData != null)
			{
				status = selected_year;
			}
			else
			{
				status = "fail";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to save particular ESR data of selected year
	 * 
	 * @param particulars the select particular of ESR data
	 * @param selected_year the select year of ESR save data
	 * @return stauts it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveParticulars", method = RequestMethod.POST)
	public @ResponseBody String getParticularsESR(
			@RequestParam(value = "particulars", required = false) String[] particulars,
			@RequestParam(value = "selected_year", required = false) String selected_year) throws JSONException
	{

		String status = "success";
		try
		{
			EsrParticulars esrParticularsData = new EsrParticulars();
			for (int i = 0; i < particulars.length; i++)
			{
				EsrParticulars esrParticulars = new EsrParticulars();
				esrParticulars.setParticulars(particulars[i]);
				esrParticulars.setYear(selected_year);
				esrParticularsData = esrParticularsServices.save(esrParticulars);
			}
			if (esrParticularsData != null)
			{
				status = selected_year;
			}
			else
			{
				status = "fail";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to save water sewage pollutant reason data of yearly esr
	 * 
	 * @param pollNameList it pollutant name list
	 * @param reasonList t reason list of pollutant data
	 * @param pollType the type of the pollutant type
	 * @param stackName the name of the stack data name
	 * @param attachedTo the attach file of the stack data
	 * @param dateFrom the starting date
	 * @param dateTo the end date
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveSewageReason", method = RequestMethod.POST)
	public @ResponseBody String saveSewageReason(
			@RequestParam(value = "pollNameList", required = false) String[] pollNameList,
			@RequestParam(value = "reasonList", required = false) String[] reasonList,
			@RequestParam(value = "pollType", required = false) String pollType,
			@RequestParam(value = "stackName", required = false) String stackName,
			@RequestParam(value = "attachedTo", required = false) String attachedTo,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "selectedYear", required = false) String selected_year) throws JSONException
	{

		String status = "success", year[] = selected_year.split("-"), year1 = "April" + year[0] + "-March" + year[1];
		JSONArray jsonarray = new JSONArray();
		try
		{
			RegPollReasons regPollReasonsData = new RegPollReasons();
			for (int i = 0; i < pollNameList.length; i++)
			{
				RegPollReasons regPollReasons = new RegPollReasons();
				regPollReasons.setAttachedTo(attachedTo);
				regPollReasons.setPollName(pollNameList[i]);
				regPollReasons.setPollType(pollType);
				regPollReasons.setStackName(stackName);
				regPollReasons.setReason(reasonList[i]);
				regPollReasons.setReasonDate(year1);
				regPollReasonsData = regPollReasonsServices.save(regPollReasons);
			}
			if (regPollReasonsData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("dateFrom", new String(dateFrom));
				jsonObject.put("dateTo", new String(dateTo));
				jsonObject.put("selectedYear", new String(selected_year));
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("dateFrom", new String(dateFrom));
				jsonObject.put("dateTo", new String(dateTo));
				jsonObject.put("selectedYear", new String(selected_year));
				jsonarray.put(jsonObject);
			}
			status = jsonarray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This class used to save Air stack reason data of yearly esr
	 * 
	 * @param pollNameList it pollution name list
	 * @param reasonList the list of reason pollution
	 * @param pollType the type of pollution
	 * @param stackName the name of the stack
	 * @param attachedTo the attach file of stack data
	 * @param dateFrom the starting date
	 * @param dateTo the end date
	 * @param selected_year the selected year
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveAirStackReason", method = RequestMethod.POST)
	public @ResponseBody String saveAirStackYearly(
			@RequestParam(value = "stackPollNameList", required = false) String[] pollNameList,
			@RequestParam(value = "stackResoanList", required = false) String[] reasonList,
			@RequestParam(value = "selectedYear", required = false) String selected_year) throws JSONException
	{
		String status = "success", year[] = selected_year.split("-"), year1 = "April" + year[0] + "-March" + year[1];
		JSONArray jsonarray = new JSONArray();
		try
		{
			EsrStackPollReasons esrpollStackreasondata = new EsrStackPollReasons();
			for (int i = 0; i < pollNameList.length; i++)
			{
				EsrStackPollReasons esrpollStackreason = new EsrStackPollReasons();
				esrpollStackreason.setPollName(pollNameList[i]);
				esrpollStackreason.setReason(reasonList[i]);
				esrpollStackreason.setReasonDate(selected_year);
				esrpollStackreasondata = esrStackPollReasonsServices.save(esrpollStackreason);
			}
			if (esrpollStackreasondata != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("result", new String("true"));
				jsonObject.put("selectedYear", new String(selected_year));
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("result", new String("false"));
				jsonObject.put("selectedYear", new String(selected_year));
				jsonarray.put(jsonObject);
			}
			status = jsonarray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return status;
	}

	@RequestMapping("/ajax-getYearlyESRValues")
	public @ResponseBody String getYearlyESRValues(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "datefrom", required = false) String datefrom,
			@RequestParam(value = "dateto", required = false) String dateto,
			@RequestParam(value = "workingDays", required = false) int workingDays,
			@RequestParam(value = "workingHr", required = false) int workingHr,
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
							consent_quantity = getYearlyConsentQuantity(units, unitSplit[0], consent_quantity, workingHr);
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

	@RequestMapping("/ajax-getTypeWaterConsumption")
	public @ResponseBody String getProcessWaterConsumption(
			@RequestParam(value = "esrYear", required = false) String esrYear,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "productType", required = false) String productType, HttpServletRequest request)
			throws JSONException
	{

		String productName = "NA", previousData = "NA", currentData = "NA", jsonString = null;
		int productCount = 0;
		try
		{
			String industryCategory = (String) request.getSession().getAttribute("sessionIndustryType");
			List<String> distProductLists = new ArrayList<>();
			List<EsrProductWater> esrProductWaterDatas = new ArrayList<>();
			JSONArray jsonArray = new JSONArray();
			String[] selectedYears = esrYear.split("-");
			String keys[] = {"productName", "previousData", "currentData", "ip", "productCount", "productType",
					"selectedYear", "esrMonth"};
			if (industryCategory.equalsIgnoreCase("Industry"))
			{
				distProductLists = allProductComparativeSheetServices.getDistinctProductNameByEsrYear(productType,
						dateTo);
				productCount = distProductLists.size();
				if (!distProductLists.isEmpty())
				{
					for (int i = 0; i < distProductLists.size(); i++)
					{
						productName = distProductLists.get(i);
						productCount++;
						esrProductWaterDatas = esrProductWaterServices.getAllDataByProNameTypeYear(esrYear,
								productName, productType);
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
									JSONObject jsonObject = new JSONObject();
									jsonObject.put(keys[0], new String(productName));
									jsonObject.put(keys[1], new String(previousData));
									jsonObject.put(keys[2], new String(currentData));
									jsonObject.put(keys[3], "SHOWDATA");
									jsonObject.put(keys[4], Integer.toString(productCount));
									jsonObject.put(keys[5], new String(productType));
									jsonObject.put(keys[6], new String(esrYear));
									jsonArray.put(jsonObject);
								}
							}
						}
						else
						{
							JSONObject JsonObject = new JSONObject();
							JsonObject.put(keys[0], new String(productName));
							JsonObject.put(keys[1], previousData);
							JsonObject.put(keys[2], currentData);
							JsonObject.put(keys[3], "NA");
							JsonObject.put(keys[4], new Integer(productCount));
							JsonObject.put(keys[5], new String(productType));
							JsonObject.put(keys[6], new String(esrYear));
							jsonArray.put(JsonObject);
						}
					}
				}
				else
				{
					JSONObject JsonObject = new JSONObject();
					JsonObject.put(keys[0], "NA");
					JsonObject.put(keys[1], "NA");
					JsonObject.put(keys[2], "NA");
					JsonObject.put(keys[3], "NA");
					JsonObject.put(keys[4], "NA");
					JsonObject.put(keys[5], "NA");
					JsonObject.put(keys[6], "NA");
					jsonArray.put(JsonObject);
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

	@RequestMapping("ajax-save-product-esr-water-yearly")
	public @ResponseBody String saveProcessWaterConsumption(@RequestParam(value = "productList") String[] productName,
			@RequestParam(value = "prevDataList") String[] prevData,
			@RequestParam(value = "currDataList", required = false) String[] currData,
			@RequestParam(value = "unitList", required = false) String[] unit,
			@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value = "year", required = false) String selectedYear, HttpServletRequest request)
			throws JSONException
	{
		String jsonString = null;
		JSONArray jsonArray = new JSONArray();
		try
		{

			EsrProductWater esrProductWaterData = new EsrProductWater();
			for (int i = 0; i < productName.length; i++)
			{
				EsrProductWater esrProductWater = new EsrProductWater();
				esrProductWater.setProductName(productName[i]);
				esrProductWater.setPreviousData(prevData[i]);
				esrProductWater.setCurrentData(currData[i]);
				esrProductWater.setProductType(productType);
				esrProductWater.setUnit(unit[i]);
				esrProductWater.setYear(selectedYear);
				esrProductWaterData = esrProductWaterServices.save(esrProductWater);
			}
			if (esrProductWaterData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", new String(selectedYear));
				jsonArray.put(jsonObject);
				jsonString = jsonArray.toString();
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	/**
	 * This method used to get Effluent pollutant water of monthly esr.
	 * 
	 * @param selectedYear the selected year of esr.
	 * @param esrMonth the selected Month of esr.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getEffluentPollutantYear")
	public @ResponseBody String getEffluentPollutantYear(
			@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo) throws JSONException
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

	/**
	 * This method used to get sewage water pollutant data of monthly esr.
	 * 
	 * @param selectedYear get selected year of sewage pollutant data
	 * @param esrMonth get selected month of ESR monthly data.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getSewagePollutantYear")
	public @ResponseBody String getSewagePollutant(
			@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo) throws JSONException
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

	/**
	 * This method used to save the monthly effluent pollutant data.
	 * 
	 * @param pollNameList the name of pollutant list.
	 * @param reasonList the reason list of effluent data.
	 * @param pollType the type of pollutant data.
	 * @param stackName the name of stack.
	 * @param attachedTo get attach to list monthly ESR
	 * @param dateFrom get the selected Start date of monthly ESR From.
	 * @param dateTo get the selected End date of monthly ESR From.
	 * @param selected_year get selected year of monthly ESR.
	 * @param esrMonth Get selected month of Monthly ESR.
	 * @return status it return success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveEffluentReasonYearly", method = RequestMethod.POST)
	public @ResponseBody String saveEffluentReasonYear(
			@RequestParam(value = "sewPollNameList", required = false) String[] sewPollNameList,
			@RequestParam(value = "sewResoanList", required = false) String[] sewResoanList,
			@RequestParam(value = "effPollNameList", required = false) String[] effPollNameList,
			@RequestParam(value = "effResoanList", required = false) String[] effResoanList,
			@RequestParam(value = "selectedYear", required = false) String selected_year) throws JSONException
	{

		try
		{
			EsrWaterPollReasons esrWaterPollReasonsData = new EsrWaterPollReasons();
			for (int i = 0; i < effPollNameList.length; i++)
			{
				EsrWaterPollReasons esrWaterPollReasons = new EsrWaterPollReasons();
				esrWaterPollReasons.setPollName(effPollNameList[i]);
				esrWaterPollReasons.setPollType("eff");
				esrWaterPollReasons.setReason(effResoanList[i]);
				esrWaterPollReasons.setReasonDate(selected_year);
				esrWaterPollReasonsData = esrWaterPollReasonServices.save(esrWaterPollReasons);
			}

			for (int i = 0; i < sewPollNameList.length; i++)
			{
				EsrWaterPollReasons esrWaterPollReasons = new EsrWaterPollReasons();
				esrWaterPollReasons.setPollName(sewPollNameList[i]);
				esrWaterPollReasons.setPollType("sew");
				esrWaterPollReasons.setReason(sewResoanList[i]);
				esrWaterPollReasons.setReasonDate(selected_year);
				esrWaterPollReasonsData = esrWaterPollReasonServices.save(esrWaterPollReasons);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return "success";
	}

	/**
	 * This method used to get Air stack data of monthly ESR.
	 * 
	 * @param selectedYear the selected year of monthly ESR.
	 * @param esrMonth the selected month of Monthly ESR.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getAirStackYear")
	public @ResponseBody String getAirStack(@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo) throws JSONException
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

	/**
	 * This method used to get monthly hazardous process data.
	 * 
	 * @param type the type of product.
	 * @param dateFrom1 the selected Start date of monthly ESR From.
	 * @param dateTo1 the selected End date of monthly ESR From.
	 * @param selectedYear the selected year of monthly ESR.
	 * @param esrMonth the selected month of Monthly ESR.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getHazardousProcessDataYearly")
	public @ResponseBody String getYearHazardousProcessData(@RequestParam(value = "ptype", required = false) String type,
			@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "dateFrom") String dateFrom,
			@RequestParam(value = "dateTo") String dateTo) throws JSONException
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
}
