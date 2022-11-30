package com.tes.controller.environmentalofficer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.CompanyProfile;
import com.tes.model.Consent;
import com.tes.model.EsrHwSolidWaste;
import com.tes.model.EsrInvestment;
import com.tes.model.EsrParticulars;
import com.tes.model.EsrPollutionControl;
import com.tes.model.EsrProductWater;
import com.tes.model.EsrWaterPollReasons;
import com.tes.model.RegPollReasons;
import com.tes.model.Stack;
import com.tes.model.StackPoll;
import com.tes.model.WaterSewPoll;
import com.tes.model.WateriePollutant;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
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
 * This class manage monthly ESR Form and ESR yearly popup form.
 * 
 * @author Sushama Dadas
 * @author Jemish Moradiya
 */
@RestController
@RequestMapping({"/env"})
public class EsrController
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

	@Autowired
	RegSewPollServices regSewPollServices;

	private static final Logger LOGGER = LogManager.getLogger(EsrController.class);

	/**
	 * This method used to Add ESR year in pop-up box For monthly ESR Form.
	 * 
	 * @return jsonString the supplied String.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	// make pop-up for monthly esr .. with min consent issue date
	@RequestMapping("/ajax-getEsrPopUpValues")
	public @ResponseBody String getEsrPopUpValues() throws JSONException
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

	/**
	 * This method used to open Monthly ESR Form.
	 * 
	 * @param encodedYear it used to encoded year.
	 * @param encodedMonth it used to encoded month.
	 * @return getESRForm
	 */
	// page redirect mapping
	@RequestMapping("monthly-esr")
	public ModelAndView getESRForm(@RequestParam(value = "year", required = false) String encodedYear,
			@RequestParam(value = "month", required = false) String encodedMonth)
	{

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EnvrOfficer/ESRform");
		List<Float> capitalInvestmentList = new ArrayList<>();
		List<EsrHwSolidWaste> esrHwHazardousWasteData = new ArrayList<>();
		List<EsrHwSolidWaste> esrHwSolidWasteData = new ArrayList<>();
		List<EsrPollutionControl> esrPollutionControlDataByYear = new ArrayList<>();
		List<EsrInvestment> getEsrInvestmentbyYear = new ArrayList<>();
		List<EsrInvestment> getEsrInvestmentbyNextYear = new ArrayList<>();
		List<EsrParticulars> esrParticularsDataByYear = new ArrayList<>();
		List<EsrProductWater> esrProductWaterData = new ArrayList<>();
		String year = Utilities.decodeString(encodedYear), esrConsentDate = null,
				monthNumber = Utilities.decodeString(encodedMonth), dateRes[] = year.split("-"),
				fromDate = (dateRes[0]) + "-04-01", toDate = (dateRes[1]) + "-03-31", lastEnvSubmitted = null,
				set_title = "31st March " + toDate, year1[] = year.split("-"), consNo = "", issueDate = "", validUpto = "";
		int a = Integer.parseInt(year1[0]), b = Integer.parseInt(year1[1]);
		String nextYear = (a + 1) + "-" + (b + 1);
		String yearWithMonth = year + "-" + monthNumber;
		List<String> consNoList = new ArrayList<>();
		List<String> issueDateList = new ArrayList<>();
		List<String> validUptoList = new ArrayList<>();
		float capitalInvestment = 0.0f;
		try
		{

			// date to compared compared with consent issue date for picking-up consent
			switch (monthNumber)
			{
				case "01":
					esrConsentDate = dateRes[1] + "-" + monthNumber + "-" + 31;
					break;
				case "02":
					esrConsentDate = dateRes[1] + "-" + monthNumber + "-" + 31;
					break;
				case "03":
					esrConsentDate = dateRes[1] + "-" + monthNumber + "-" + 31;
					break;
				default:
					// if month is beetween april to December pick 1st year from esr year
					esrConsentDate = dateRes[0] + "-" + monthNumber + "-" + 31;
			}
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
			esrProductWaterData = esrProductWaterServices.getEsrProductWaterDataMonthly(year, monthNumber, "Recycled");
			String esrDatas[] = {year, set_title, lastEnvSubmitted, fromDate, toDate, monthNumber, esrConsentDate};
			esrHwHazardousWasteData = esrHwSolidWasteServices.getEsrHwSolidWasteDataByHazYear(yearWithMonth);
			esrHwSolidWasteData = esrHwSolidWasteServices.getEsrHwSolidWasteDataBySolidYear(yearWithMonth);
			esrPollutionControlDataByYear = esrPollutionControlServices.findOneByEsrPollutionByMonth(year, monthNumber);
			getEsrInvestmentbyYear = esrInvestmentServices.findOneByGetEsrInvestmentByMonth(yearWithMonth, year);
			getEsrInvestmentbyNextYear = esrInvestmentServices.findOneByGetEsrInvestmentByNextYear(yearWithMonth, nextYear);
			esrParticularsDataByYear = esrParticularsServices.getEsrParticularsbyyearMonth(year, monthNumber);
			modelAndView.addObject("companyProfileDatas", companyProfileDatas);
			modelAndView.addObject("consentDatas", consentDatas);
			modelAndView.addObject("consNoList", consNoList);
			modelAndView.addObject("issueDateList", issueDateList);
			modelAndView.addObject("validUptoList", validUptoList);
			modelAndView.addObject("capitalInvestmentList", capitalInvestmentList);
			modelAndView.addObject("esrDatas", esrDatas);
			modelAndView.addObject("esrProductWaterData", esrProductWaterData);
			modelAndView.addObject("esrHwHazardousWasteData", esrHwHazardousWasteData);
			modelAndView.addObject("esrHwSolidWasteData", esrHwSolidWasteData);
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
	 * @param action
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping("/ajax-getEsrConsentData")
	public @ResponseBody String getEsrConsentData(@RequestParam(value = "action", required = false) String action)
			throws JSONException
	{

		String jsonString = null, todayDate = Utilities.getTodaysDate(), today_date[] = todayDate.split("-");
		int esrMaxYear = Integer.parseInt(today_date[0]) + 1, esrMinYear = 0;
		JSONArray jsonArray = new JSONArray();
		try
		{
			esrMinYear = consentServices.consentMinYear();
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

	/**
	 * This method used to get production details for monthly ESR Form.
	 * 
	 * @param category it returns industry category of company.
	 * @param type it returns type of product.
	 * @param datefrom it returns selected esr monthly From date.
	 * @param dateto it returns selected esr monthly To date.
	 * @param month get selected ESR month.
	 * @param workingDays the no. of working days.
	 * @param esrConsentDate it returns esr consent date.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getMonthlyESRValues")
	public @ResponseBody String getMonthlyESRValues(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "datefrom", required = false) String datefrom,
			@RequestParam(value = "dateto", required = false) String dateto,
			@RequestParam(value = "month", required = false) int month,
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

				allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(type,
						esrConsentDate);
				if (!Validator.isEmpty(allProductsLists))
				{
					for (Object[] allProductsListData : allProductsLists)
					{
						units = (String) allProductsListData[2];
						String unitSplit[] = units.split("/");
						unitp = unitSplit[0] + "/M";
						consent_quantity = Utilities.getFloatpoint((Float) allProductsListData[0], 2);

						if (units.equalsIgnoreCase("mg/Nm3"))
						{
							unitp = "mg/NM3";
						}
						else
						{
							consent_quantity = getMonthlyConsentQuantity(units, unitSplit[0], consent_quantity, workingHr, 30);
						}
						product_name = (String) allProductsListData[1];
						quantity = regularDataServices.findRegulardataSum(product_name, datefrom, dateto, month);
						if (quantity != null)
						{
							quantity = Utilities.getFloatpoint(quantity, 2);
						}
						else
						{
							quantity = (float) 0.0;
						}
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("product_name", new String(product_name));
						hashMap.put("consent_quantity", new Float(consent_quantity));
						hashMap.put("quantity", new Float(quantity));
						hashMap.put("unitp", new String(unitp));
						jsonArray.put(hashMap);
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
	 * This method used to get ESR process water Consumption data.
	 * 
	 * @param processCons it returns process consumption data of product.
	 * @param type it returns type of product .
	 * @param selectedYear it returns selected year of esr.
	 * @param esrMonth it returns selected month of esr.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getESRWaterConsumption")
	public @ResponseBody String getESRWaterConsumption(
			@RequestParam(value = "processCons", required = false) Float processCons,
			@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "esrMonth", required = false) int esrMonth) throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		int esrYearFrom = 0, esrYearTo = 0;
		Float total_cons = 0.0f, total_Avg = 0.0f;
		String todayDate = Utilities.getTodaysDate(), selectedYears[] = selectedYear.split("-");
		String[] list1 = new String[] {"Biodegradable", "cooling", "domestic", "All-Others"};
		String[] list2 = new String[] {"Process", "cooling", "domestic", "Others"};

		esrYearFrom = Integer.valueOf(selectedYears[0]);
		esrYearTo = Integer.valueOf(selectedYears[1]);
		for (int i = 0; i < list1.length; i++)
		{
			json = new JSONObject();
			if (list1[i].equalsIgnoreCase("Biodegradable"))
			{
				Float process_cons = waterConGenComparativeSheetServices.getSumOfProcessCons("Biodegradable", todayDate);

				// Effected By Water Inventory ........by vishal
				Float process_avg2 = 0.0f;// regularWaterUseDataServices.actualReadingSumByRwDate(esrYearFrom, esrYearTo, esrMonth);

				if (process_avg2 == null)
					process_avg2 = 0.0f;

				total_Avg += process_avg2;
				total_cons += process_cons;
				json.put("Namee", new String(list2[i]));
				json.put("constantValue", new Float(process_cons));
				json.put("ActualQuantity", new Float(process_avg2));

				jsonArray.put(json);
			}
			else if (list1[i].equalsIgnoreCase("cooling"))
			{
				Float process_cons = waterConGenComparativeSheetServices.getSumOfProcessCons("cooling", todayDate);
				// Effected By Water Inventory ........by vishal
				Float process_avg2 = 0.0f;// regularWaterUseDataServices.actualReadingCoolingSumByRwDate(esrYearFrom, esrYearTo, esrMonth);

				if (process_avg2 == null)
					process_avg2 = 0.0f;

				total_Avg += process_avg2;
				total_cons += process_cons;
				json.put("Namee", new String(list2[i]));
				json.put("constantValue", new Float(process_cons));
				json.put("ActualQuantity", new Float(process_avg2));

				jsonArray.put(json);

			}
			else if (list1[i].equalsIgnoreCase("domestic"))
			{
				Float process_cons = waterConGenComparativeSheetServices.getSumOfProcessCons("domestic", todayDate);
				// Effected By Water Inventory ........by vishal
				Float process_avg2 = 0.0f;// regularWaterUseDataServices.avgActualReading(esrYearFrom, esrYearTo, esrMonth);

				if (process_avg2 == null)
					process_avg2 = 0.0f;

				total_Avg += process_avg2;
				total_cons += process_cons;
				json.put("Namee", new String(list2[i]));
				json.put("constantValue", new Float(process_cons));
				json.put("ActualQuantity", new Float(process_avg2));

				jsonArray.put(json);

			}
			else
			{
				Float process_cons = waterConGenComparativeSheetServices.getSumOfProcessCons("type", todayDate);
				Float process_avg2 = 0.0f;// regularWaterUseDataServices.avgOthersActualReading(esrYearFrom, esrYearTo, esrMonth);

				if (process_avg2 == null)
					process_avg2 = 0.0f;

				total_Avg += process_avg2;
				total_cons += process_cons;
				json.put("Namee", new String(list2[i]));
				json.put("constantValue", new Float(process_cons));
				json.put("ActualQuantity", new Float(process_avg2));

				jsonArray.put(json);
			}

		}
		json = new JSONObject();
		json.put("Namee", new String("Total"));
		json.put("constantValue", new Float(total_cons));
		json.put("ActualQuantity", new Float(total_Avg));

		jsonArray.put(json);
		return jsonArray.toString();
	}

	/**
	 * This method used to save process water consumption for ESR Monthly.
	 * 
	 * @param productName The name of product.
	 * @param prevData get previous data of esr product water.
	 * @param currData get current data of esr product water.
	 * @param unit get unit of ESR product water.
	 * @param productType get type of ESR product water.
	 * @param selectedYear It return selected year of monthly ESR.
	 * @param esrMonth get selected month of monthly ESR.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("ajax-save-product-esr-water-monthly")
	public @ResponseBody String saveProcessWaterConsumption(@RequestParam(value = "productList") String[] productName,
			@RequestParam(value = "prevDataList") String[] prevData,
			@RequestParam(value = "currDataList", required = false) String[] currData,
			@RequestParam(value = "unitList", required = false) String[] unit,
			@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value = "year", required = false) String selectedYear,
			@RequestParam(value = "month", required = false) String esrMonth, HttpServletRequest request)
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
				esrProductWater.setYear(selectedYear + "-" + esrMonth);
				esrProductWaterData = esrProductWaterServices.save(esrProductWater);
			}
			if (esrProductWaterData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", new String(selectedYear));
				jsonObject.put("esrMonth", new String(esrMonth));
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
	 * This method used to get the ESR water generation data of monthly ESR.
	 * 
	 * @param effGen it returns effluent generated data.
	 * @param type the type of product.
	 * @return res it return effluent generation and sewage generation
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getESRWaterGeneration")
	public @ResponseBody String getESRWaterGeneration(@RequestParam(value = "effGen", required = false) Float effGen,
			@RequestParam(value = "type", required = false) String type) throws JSONException
	{
		String res = null;
		float sewGen = 0;
		try
		{
			String todayDate = Utilities.getTodaysDate();
			effGen = getESRWaterGenerationSum(effGen, type, todayDate);
			sewGen = getESRWaterGenerationSum(sewGen, "domestic", todayDate);
			res = effGen + "-" + sewGen;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return res;
	}

	/**
	 * This method used to get process water consumption data of montly ESR
	 * for product and raw material.
	 * 
	 * @param esrMonth it return selected Month of esr.
	 * @param esrYear it return selected year of esr.
	 * @param productType the type of product.
	 * @param request the servlet request we are processing.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getProcessWaterConsumption")
	public @ResponseBody String getProcessWaterConsumption(
			@RequestParam(value = "esrMonth", required = false) String esrMonth,
			@RequestParam(value = "esrYear", required = false) String esrYear,
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
			String consentDate = selectedYears[1] + "-" + esrMonth + "-01";
			String esrDate = Utilities.getLastDate(consentDate);
			String esrWholeYear = esrYear + "-" + esrMonth;
			String keys[] = {"productName", "previousData", "currentData", "ip", "productCount", "productType",
					"selectedYear", "esrMonth"};
			if (industryCategory.equalsIgnoreCase("Industry"))
			{
				distProductLists = allProductComparativeSheetServices.getDistinctProductNameByEsrYear(productType,
						esrDate);
				productCount = distProductLists.size();
				if (!distProductLists.isEmpty())
				{
					for (int i = 0; i < distProductLists.size(); i++)
					{
						productName = distProductLists.get(i);
						productCount++;
						esrProductWaterDatas = esrProductWaterServices.getAllDataByProNameTypeYear(esrWholeYear,
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
									HashMap<String, String> hashMap = new HashMap<String, String>();
									hashMap.put(keys[0], new String(productName));
									hashMap.put(keys[1], new String(previousData));
									hashMap.put(keys[2], new String(currentData));
									hashMap.put(keys[3], "SHOWDATA");
									hashMap.put(keys[4], Integer.toString(productCount));
									hashMap.put(keys[5], new String(productType));
									hashMap.put(keys[6], new String(esrYear));
									hashMap.put(keys[7], new String(esrMonth));
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
							hashMap.put(keys[7], new String(esrMonth));
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
	 * This method used to get Effluent pollutant water of monthly esr.
	 * 
	 * @param selectedYear the selected year of esr.
	 * @param esrMonth the selected Month of esr.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getEffluentPollutant")
	public @ResponseBody String getEffluentPollutant(
			@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{

		String jsonString = null, pollName = "NA", years[] = selectedYear.split("-"), esrYear = years[0],
				esrYear1 = years[1], y1 = "April" + esrYear, y2 = "March" + esrYear1, dateTo = null, dateFrom = null;
		List<WateriePollutant> wateriePollutantDatas = new ArrayList<>();
		Float quantity = 0.0f, quan = 0.0f;
		float variation = 0;
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		List<String> rsList = new ArrayList<>();
		try
		{
			switch (esrMonth)
			{
				case "01":
				case "02":
				case "03":
					dateFrom = years[1] + "-" + esrMonth + "-01";
					break;

				default:
					// if month is beetween april to December pick 1st year from esr year
					dateFrom = years[0] + "-" + esrMonth + "-01";
			}
			dateTo = Utilities.getLastDate(dateFrom);
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
					String rs = "NA";
					rsList = regPollReasonsServices.getRegReasonByNametypeAndYearMonth(pollName, "effluent", y1, y2,
							esrMonth, new PageRequest(0, 1));
					if (!Validator.isEmpty(rs))
					{
						for (int j = 0; j < rsList.size(); j++)
						{
							rs = rsList.get(j);
						}
					}
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
					json.put("esrMonth", esrMonth);

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
				json.put("esrMonth", esrMonth);

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
	@RequestMapping(value = "/ajax-saveEffluentReasonMonthly", method = RequestMethod.POST)
	public @ResponseBody String saveEffluentReason(
			@RequestParam(value = "sewPollNameList", required = false) String[] sewPollNameList,
			@RequestParam(value = "sewResoanList", required = false) String[] sewResoanList,
			@RequestParam(value = "effPollNameList", required = false) String[] effPollNameList,
			@RequestParam(value = "effResoanList", required = false) String[] effResoanList,
			@RequestParam(value = "selectedYear", required = false) String selected_year,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{

		String year[] = selected_year.split("-"), date = null;
		try
		{
			switch (esrMonth)
			{
				case "01":
				case "02":
				case "03":
					date = year[1] + "-" + esrMonth;
					break;

				default:
					// if month is beetween april to December pick 1st year from esr year
					date = year[0] + "-" + esrMonth;
			}

			EsrWaterPollReasons esrWaterPollReasonsData = new EsrWaterPollReasons();
			for (int i = 0; i < effPollNameList.length; i++)
			{
				EsrWaterPollReasons esrWaterPollReasons = new EsrWaterPollReasons();
				esrWaterPollReasons.setPollName(effPollNameList[i]);
				esrWaterPollReasons.setPollType("eff");
				esrWaterPollReasons.setReason(effResoanList[i]);
				esrWaterPollReasons.setReasonDate(date);
				esrWaterPollReasonsData = esrWaterPollReasonServices.save(esrWaterPollReasons);
			}

			for (int i = 0; i < sewPollNameList.length; i++)
			{
				EsrWaterPollReasons esrWaterPollReasons = new EsrWaterPollReasons();
				esrWaterPollReasons.setPollName(sewPollNameList[i]);
				esrWaterPollReasons.setPollType("sew");
				esrWaterPollReasons.setReason(sewResoanList[i]);
				esrWaterPollReasons.setReasonDate(date);
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
	 * This method used to get sewage water pollutant data of monthly esr.
	 * 
	 * @param selectedYear get selected year of sewage pollutant data
	 * @param esrMonth get selected month of ESR monthly data.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getSewagePollutant")
	public @ResponseBody String getSewagePollutant(
			@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{

		String jsonString = null, pollName = "NA", years[] = selectedYear.split("-"), esrYear = years[0],
				y1 = "April" + years[0], y2 = "March" + years[1];
		List<WaterSewPoll> getWaterSewPolls = new ArrayList<>();
		Float quantity = 0.0f, variation = 0.0f, quan = 0.0f;
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		String dateFrom = null, dateTo = null,
				keys[] = {"pollName", "quan", "variation", "quantity", "reasons"};
		switch (esrMonth)
		{
			case "01":
				dateFrom = years[1] + "-" + esrMonth + "-01";
				break;
			case "02":
				dateFrom = years[1] + "-" + esrMonth + "-01";
				break;
			case "03":
				dateFrom = years[1] + "-" + esrMonth + "-01";
				break;
			default:
				// if month is beetween april to December pick 1st year from esr year
				dateFrom = years[0] + "-" + esrMonth + "-01";
		}
		dateTo = Utilities.getLastDate(dateFrom);
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
					int esrYear0 = Integer.valueOf(esrYear);
					int esrmonth = Integer.valueOf(esrMonth);
					quan = regSewPollServices.getSewAvgOuConsByDate(pollName, dateFrom, dateTo);
					if (quan == null)
						quan = 0.0f;
					String rs = "NA";
					rsList = regPollReasonsServices.getRegReasonByNametypeAndYearMonth(pollName, "sewage", y1, y2,
							esrMonth, new PageRequest(0, 1));
					if (!Validator.isEmpty(rs))
					{
						for (int j = 0; j < rsList.size(); j++)
						{
							rs = rsList.get(j);
						}
					}
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
					json.put("esrMonth", esrMonth);

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
				json.put("esrMonth", esrMonth);

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
	 * This method used to save the monthly sewage water pollutant data of monthly esr.
	 * 
	 * @param pollNameList the name of pollutant list data.
	 * @param reasonList the reason of sewage list of monthly ESR.
	 * @param pollType the type of pollutant type
	 * @param stackName the name of stack.
	 * @param attachedTo the attachto list monthly ESR
	 * @param dateFrom the selected Start date of monthly ESR From.
	 * @param dateTo the selected End date of monthly ESR From.
	 * @param selected_year the selected year of monthly ESR.
	 * @param esrMonth the selected month of Monthly ESR.
	 * @return status it return success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveSewageReasonMonthly", method = RequestMethod.POST)
	public @ResponseBody String saveSewageReason(
			@RequestParam(value = "pollNameList", required = false) String[] pollNameList,
			@RequestParam(value = "reasonList", required = false) String[] reasonList,
			@RequestParam(value = "pollType", required = false) String pollType,
			@RequestParam(value = "stackName", required = false) String stackName,
			@RequestParam(value = "attachedTo", required = false) String attachedTo,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "selectedYear", required = false) String selected_year,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{

		String status = null, year[] = selected_year.split("-"), year1 = "April" + year[0] + "-March" + year[1];
		RegPollReasons regPollReasonsData = new RegPollReasons();
		try
		{
			for (int i = 0; i < pollNameList.length; i++)
			{
				RegPollReasons regPollReasons = new RegPollReasons();
				regPollReasons.setAttachedTo(attachedTo);
				regPollReasons.setPollName(pollNameList[i]);
				regPollReasons.setPollType(pollType);
				regPollReasons.setStackName(stackName);
				regPollReasons.setReason(reasonList[i]);
				regPollReasons.setReasonDate(year1 + "-" + esrMonth);
				regPollReasonsData = regPollReasonsServices.save(regPollReasons);
			}
			if (regPollReasonsData != null)
			{
				status = "success";
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
	 * This method used to get Air stack data of monthly ESR.
	 * 
	 * @param selectedYear the selected year of monthly ESR.
	 * @param esrMonth the selected month of Monthly ESR.
	 * @return jsonString it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-getAirStack")
	public @ResponseBody String getAirStack(@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{
		String jsonString = null, dateFrom = null, dateTo = null, rsdate = null;
		JSONObject json;
		JSONArray jsonArray = new JSONArray();
		String today = Utilities.getTodaysDate();
		String years[] = selectedYear.split("-");
		List<StackPoll> stackPollDataList = new ArrayList<>();
		switch (esrMonth)
		{
			case "01":
			case "02":
			case "03":
				dateFrom = years[1] + "-" + esrMonth + "-" + "01";
				rsdate = years[1] + "-" + esrMonth;
				break;

			default:
				dateFrom = years[0] + "-" + esrMonth + "-" + "01";
				rsdate = years[0] + "-" + esrMonth;
		}
		dateTo = Utilities.getLastDate(dateFrom);
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

					String rs = esrStackPollReasonsServices.getReasonByDate(mainName, rsdate);

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
	 * This method used to save reason of air stack for monthly ESR.
	 * 
	 * @param pollNameList the name of pollutant list data.
	 * @param reasonList the reason of sewage list of monthly ESR.
	 * @param pollType the type of pollutant type
	 * @param stackName the name of stack.
	 * @param attachedTo the attachto list monthly ESR
	 * @param dateFrom the selected Start date of monthly ESR From.
	 * @param dateTo the selected End date of monthly ESR From.
	 * @param selected_year the selected year of monthly ESR.
	 * @param esrMonth the selected month of Monthly ESR.
	 * @return status it return success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveAirReasonMonthly", method = RequestMethod.POST)
	public @ResponseBody String saveAirReason(
			@RequestParam(value = "pollNameList", required = false) String[] pollNameList,
			@RequestParam(value = "reasonList", required = false) String[] reasonList,
			@RequestParam(value = "pollType", required = false) String pollType,
			@RequestParam(value = "stackNameList", required = false) String[] stackName,
			@RequestParam(value = "attatchedToList", required = false) String[] attachedTo,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "selectedYear", required = false) String selected_year,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{

		String status = "success", year[] = selected_year.split("-"), year1 = "April" + year[0] + "-March" + year[1];
		RegPollReasons regPollReasonsData = new RegPollReasons();
		try
		{
			for (int i = 0; i < pollNameList.length; i++)
			{
				RegPollReasons regPollReasons = new RegPollReasons();
				regPollReasons.setAttachedTo(attachedTo[i]);
				regPollReasons.setPollName(pollNameList[i]);
				regPollReasons.setPollType(pollType);
				regPollReasons.setStackName(stackName[i]);
				regPollReasons.setReason(reasonList[i]);
				regPollReasons.setReasonDate(year1 + "-" + esrMonth);
				regPollReasonsData = regPollReasonsServices.save(regPollReasons);
			}
			if (regPollReasonsData != null)
			{
				status = "success";
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
	@RequestMapping("/ajax-getMonthlyHazardousProcessData")
	public @ResponseBody String getHazardousProcessData(@RequestParam(value = "ptype", required = false) String type,
			@RequestParam(value = "selectedYear", required = false) String selectedYear,
			@RequestParam(value = "esrMonth") String esrMonth) throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject json;
		String jsonString = null, dateFrom = null, dateFromprv = null, dateTo = null, dateToprv = null;
		String years[] = selectedYear.split("-");
		List<Object[]> allProductsLists = new ArrayList<>();
		switch (esrMonth)
		{
			case "01":
			case "02":
			case "03":
				dateFrom = years[1] + "-" + esrMonth + "-" + "01";
				break;

			default:
				dateFrom = years[0] + "-" + esrMonth + "-" + "01";
		}
		int temp = Integer.parseInt(esrMonth) - 1;
		String prvMonth = "";
		if (temp > 0 && temp < 10)
		{
			prvMonth = "0" + temp;
		}
		else if (temp == 0)
		{
			prvMonth = "12";
		}
		else
		{
			prvMonth = Integer.toString(temp);
		}
		switch (prvMonth)
		{
			case "01":
			case "02":
			case "03":
				dateFromprv = years[1] + "-" + prvMonth + "-" + "01";
				break;

			default:
				dateFromprv = years[0] + "-" + prvMonth + "-" + "01";
		}
		dateTo = Utilities.getLastDate(dateFrom);
		dateToprv = Utilities.getLastDate(dateFromprv);
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

	// ajax-getEsrConsentData

	/**
	 * This method used to manage monthly esr popup form.
	 * 
	 * @param action type of esr method
	 * @param year the selected year of esr
	 * @param hwUnits the hazardous waste units
	 * @param floatArray1 the quantity of product
	 * @param stringArray1 the name of product
	 * @param stringArray2 the current protection mesure details of investment year
	 * @param stringArray3 the investment details
	 * @param stringArray4 the current protection mesure details of next investment year
	 * @param floatArray2 the concentration value of hazardous-solid waste or reduction fuel of pollution control
	 * @param floatArray3 the reduction rm of pollution control
	 * @param floatArray4 the reduction pc of pollution control
	 * @param floatArray5 the capitalInvestment of pollution control
	 * @param floatArray6 the reductionMaintenance of pollution control
	 * @return jsonString it returns String value of json array.
	 * @throws ParseException If parsing causes an error
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping("/ajax-checkForESR")
	public @ResponseBody String checkForESR(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "hwUnits", required = false) String[] hwUnits,
			@RequestParam(value = "floatArray1", required = false) Float[] floatArray1,
			@RequestParam(value = "stringArray1", required = false) String[] stringArray1,
			@RequestParam(value = "stringArray2", required = false) String[] stringArray2,
			@RequestParam(value = "stringArray3", required = false) String[] stringArray3,
			@RequestParam(value = "stringArray4", required = false) String[] stringArray4,
			@RequestParam(value = "floatArray2", required = false) Float[] floatArray2,
			@RequestParam(value = "floatArray3", required = false) Float[] floatArray3,
			@RequestParam(value = "floatArray4", required = false) Float[] floatArray4,
			@RequestParam(value = "floatArray5", required = false) Float[] floatArray5,
			@RequestParam(value = "floatArray6", required = false) Float[] floatArray6)
			throws ParseException, JSONException
	{

		String jsonString = null;
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int yearFrom = Utilities.getCurrentStartYearFromCurrentMonth(currentMonth);
		int yearTo = yearFrom + 1;
		String forNextYear = yearTo + "-" + (yearTo + 1);
		List<Object[]> HazardousWasteLists = new ArrayList<>();
		try
		{
			if (action.equals("WasteHw"))
			{
				// check if hazardous waste data is already exists...
				jsonString = "WasteHw";
				HazardousWasteLists = esrHwSolidWasteServices.findOneByGetHazardousWasteByYear(year, "Hazardous Waste");
				if (!HazardousWasteLists.isEmpty())
				{
					jsonString = "WasteHwYes";
				}
			}
			else if (action.equals("WasteSw"))
			{
				// check if Solid waste data is already exists.......
				jsonString = "WasteSw";
				List<Object[]> HazardousSolidWasteLists = new ArrayList<>();
				HazardousSolidWasteLists = esrHwSolidWasteServices.findOneByGetHazardousWasteByYear(year,
						"Solid Waste");
				if (!HazardousSolidWasteLists.isEmpty())
				{
					jsonString = "WasteSwYes";
				}
			}
			else if (action.equals("PC"))
			{
				jsonString = "Pollution";
				List<EsrPollutionControl> EsrPollutionLists = new ArrayList<>();
				EsrPollutionLists = esrPollutionControlServices.findOneByEsrPollutionByYear(year);
				if (!EsrPollutionLists.isEmpty())
				{
					jsonString = "PollutionYes";
				}
			}
			else if (action.equals("investment"))
			{
				jsonString = "Investmet";
				List<EsrInvestment> EsrPollutionCYearLists = new ArrayList<>();
				List<EsrInvestment> EsrPollutionNYearLists = new ArrayList<>();
				EsrPollutionCYearLists = esrInvestmentServices.findOneByGetEsrInvestmentByYear(year);
				if (!EsrPollutionCYearLists.isEmpty())
				{
					jsonString = "PollutionYes";
				}
				EsrPollutionNYearLists = esrInvestmentServices.findOneByGetEsrInvestmentByYear(forNextYear);
				if (!EsrPollutionNYearLists.isEmpty())
				{
					jsonString = "PollutionYes";
				}
			}
			else if (action.equals("particular"))
			{
				jsonString = "Particulars";
				List esrParticularsList = new ArrayList<>();
				esrParticularsList = esrParticularsServices.findOneByGetParticularsByYear(year);
				if (!esrParticularsList.isEmpty())
				{
					jsonString = "ParticularsYes";
				}
			}
			else if (action.equals("submitHzWasteData"))
			{
				String[] hwId = stringArray1; // type of waste
				Float[] hwConc = floatArray2; // hz waste concerntrate
				Float[] hwQuantity = floatArray1; // quantity in float
				// String[] hwUnits = hwUnits; // units in int reference to units table
				int hwIddata = hwId.length;
				int hwConcData = hwConc.length;
				for (int i = 0; i < hwIddata; i++)
				{
					EsrHwSolidWaste esrHwSolidWaste = new EsrHwSolidWaste();
					esrHwSolidWaste.setTypeOfWaste(hwId[i]); // Get-Set TypeOfWaste
					esrHwSolidWaste.setQuantity(hwQuantity[i]); // Get-Set Quantity
					if (hwConcData > 0)
					{
						esrHwSolidWaste.setConcentration(hwConc[i]); // Get-Set Concentration
					}
					esrHwSolidWaste.setType("Hazardous Waste"); // Get-Set Type
					esrHwSolidWaste.setUnit(hwUnits[i]); // Set Units
					esrHwSolidWaste.setYear(year); // Set Year
					esrHwSolidWasteServices.save(esrHwSolidWaste);
					jsonString = "success";
				}

			}
			else if (action.equals("submitSwWasteData"))
			{
				String[] swId = stringArray1; // type of waste
				Float[] swQuantity = floatArray1; // quantity in float
				Float[] swConc = floatArray2; // sw waste concerntrate
				int swIddata = swId.length;
				int swConcData = swConc.length;
				for (int i = 0; i < swIddata; i++)
				{
					EsrHwSolidWaste esrHwSolidWaste = new EsrHwSolidWaste();
					esrHwSolidWaste.setTypeOfWaste(swId[i]); // Get-Set TypeOfWaste
					esrHwSolidWaste.setQuantity(swQuantity[i]); // Get-Set Quantity
					if (swConcData > 0)
					{
						esrHwSolidWaste.setConcentration(swConc[i]); // Get-Set Concentration
					}
					esrHwSolidWaste.setType("Solid Waste"); // Get-Set Type
					esrHwSolidWaste.setUnit(hwUnits[i]); // Set Units
					esrHwSolidWaste.setYear(year); // Set Year
					esrHwSolidWasteServices.save(esrHwSolidWaste);
					jsonString = "success";
				}
			}
			else if (action.equals("submitPC"))
			{
				String[] description = stringArray1; // description in string
				Float[] reductionWater = floatArray1; // reductionWater in float
				Float[] reductionFuel = floatArray2; // reductionFuel in float
				Float[] reductionRm = floatArray3; // reductionRm in float
				Float[] reductionPc = floatArray4; // reductionPc in float
				Float[] capitalInvestment = floatArray5; // capitalInvestment in float
				Float[] reductionMaintenance = floatArray6; // reductionMaintenance in float
				for (int i = 0; i < description.length; i++)
				{
					EsrPollutionControl esrPollutionControl = new EsrPollutionControl();
					esrPollutionControl.setDescription(description[i]); // set pc description
					esrPollutionControl.setYear(year); // set pc year
					if (reductionWater.length > 0)
					{
						esrPollutionControl.setReductionWater(reductionWater[i]);
					}
					if (reductionFuel.length > 0)
					{
						esrPollutionControl.setReductionFuel(reductionFuel[i]);
					}
					if (reductionRm.length > 0)
					{
						esrPollutionControl.setReductionRm(reductionRm[i]);
					}
					if (reductionPc.length > 0)
					{
						esrPollutionControl.setReductionPc(reductionPc[i]);
					}
					if (capitalInvestment.length > 0)
					{
						esrPollutionControl.setCapitalInvestment(capitalInvestment[i]);
					}
					if (reductionMaintenance.length > 0)
					{
						esrPollutionControl.setReductionMaintenance(reductionMaintenance[i]);
					}
					esrPollutionControlServices.save(esrPollutionControl);
					jsonString = "success";
				}

			}
			else if (action.equals("submitInvestment"))
			{
				String[] currDetail = stringArray1; // description in string
				String[] currProtectionMea = stringArray2; // current protection measure
				Float[] currCapitalInvestment = floatArray1; // reductionWater in float
				String[] nextDetail = stringArray3; // description in string
				String[] nextProtectionMea = stringArray4; // next protection measure
				Float[] nextCapitalInvestment = floatArray2; // reductionWater in float

				if (currDetail.length > 0)
				{
					for (int i = 0; i < currDetail.length; i++)
					{
						EsrInvestment esrInvestment = new EsrInvestment();
						esrInvestment.setDetails(currDetail[i]);
						esrInvestment.setProtectionMea(currProtectionMea[i]);
						esrInvestment.setCapitalInvestment(currCapitalInvestment[i]);
						esrInvestment.setYear(year);
						esrInvestment.setInvestmentYear(year);
						esrInvestmentServices.save(esrInvestment);
						jsonString = "success";
					}
				}
				if (nextDetail.length > 0)
				{
					for (int i = 0; i < nextDetail.length; i++)
					{
						EsrInvestment esrInvestment = new EsrInvestment();
						esrInvestment.setDetails(nextDetail[i]);
						esrInvestment.setProtectionMea(nextProtectionMea[i]);
						esrInvestment.setCapitalInvestment(nextCapitalInvestment[i]);
						esrInvestment.setYear(year);
						esrInvestment.setInvestmentYear(forNextYear);
						esrInvestmentServices.save(esrInvestment);
						jsonString = "success";
					}
				}
			}
			else if (action.equals("submitParticulars"))
			{
				String[] particulars = stringArray1; // description in string
				if (particulars.length > 0)
				{
					EsrParticulars esrParticulars = new EsrParticulars();
					for (int i = 0; i < particulars.length; i++)
					{
						esrParticulars.setParticulars(particulars[i]);
						esrParticulars.setYear(year);
						esrParticularsServices.save(esrParticulars);
						jsonString = "success";
					}
				}
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}

	/**
	 * This method used to return monthly consent Quantity.
	 * 
	 * @param units the unit of consent quantity.
	 * @param unitSplit the splitted unit
	 * @param consent_quantity the consent quantity.
	 * @param workingHr
	 * @param workingDays the working days.
	 * @return actual_quantity of monthly consent Quantity
	 */
	private static float getMonthlyConsentQuantity(String units, String unitSplit, Float consent_quantity, int workingHr,
			int workingDays)
	{

		Float actual_quantity = null;
		try
		{
			if (units.equalsIgnoreCase(unitSplit + "/Year"))
			{
				actual_quantity = consent_quantity / 12;
			}
			if (units.equalsIgnoreCase(unitSplit + "/Month"))
			{
				actual_quantity = consent_quantity;
			}
			if (units.equalsIgnoreCase(unitSplit + "/Day"))
			{
				actual_quantity = consent_quantity * workingDays;
			}
			if (units.equalsIgnoreCase(unitSplit + "/Hr"))
			{
				actual_quantity = consent_quantity * workingHr * workingDays;
			}
			if (units.equalsIgnoreCase(unitSplit + "/Week"))
			{
				actual_quantity = consent_quantity * 4;
			}
			actual_quantity = Utilities.getFloatpoint(actual_quantity, 2);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return actual_quantity;
	}

	/**
	 * This method used to get Sum of ESR water generation depending on type.
	 * 
	 * @param effGen the effluent generation value
	 * @param type the type of water generation type
	 * @param todayDate the current date of water generation.
	 * @return ConValue of water consumption and generation
	 */
	public float getESRWaterGenerationSum(Float effGen, String type, String todayDate)
	{

		float ConValue = 0, conGenSum = 0;
		try
		{
			if (type.equalsIgnoreCase("domestic"))
			{
				conGenSum = waterConGenComparativeSheetServices.getSumOfProcessGen(type, todayDate);
				effGen = effGen + conGenSum;
				conGenSum = waterConGenComparativeSheetServices.getSumOfProcessGen("not Biodegradable", todayDate);
				effGen = effGen + conGenSum;
				conGenSum = waterConGenComparativeSheetServices.getSumOfProcessGen("cooling", todayDate);
				effGen = effGen + conGenSum;
				ConValue = Utilities.round(effGen, 2);
			}
			else
			{
				ConValue = waterConGenComparativeSheetServices.getSumOfProcessGen("domestic", todayDate);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return ConValue;
	}

	@RequestMapping("/ajax-getEsrMonthlyEffGenActualQuantity")
	public @ResponseBody Float getEsrMonthlyGenActualQuantity(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{
		int etpno = 0;
		String year1 = null;
		Float etpQuantity = 0.0f;
		if (esrMonth.equals("01") || esrMonth.equals("02") || esrMonth.equals("03"))
		{
			year1 = year.split("-")[1];
		}
		else
		{
			year1 = year.split("-")[0];
		}
		String dateFrom = year1 + "-" + esrMonth + "-01";
		String dateTo = Utilities.getLastDate(dateFrom);
		etpno = wastewaterTreatmentServices.getWaterTreatmentType(type);
		if (etpno > 0)
		{
			// effected by water inventory
			// etpQuantity = regularTreatmentDataServices.getAvgActualReadingByYearandType(type, dateFrom, dateTo);
		}
		return etpQuantity;
	}

	/**
	 * This method used to saved recycled data of monthly ESR .
	 * 
	 * @param wasteTypeList the type of waste list.
	 * @param prevDataList the previous data list of recycled data.
	 * @param currDataList the current data list of recycled data.
	 * @param pTypeList the product type list of recycled data.
	 * @param unitList the unit list of recycled data.
	 * @param selected_year the selected year
	 * @param esrMonth the ESR monthly data.
	 * @return status it returns success or fail
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveRecycledMonthly", method = RequestMethod.POST)
	public @ResponseBody String SaveQuantityRecycled(
			@RequestParam(value = "recycled", required = false) String[] wasteTypeList,
			@RequestParam(value = "recycled_previous", required = false) String[] prevDataList,
			@RequestParam(value = "recycled_current", required = false) String[] currDataList,
			@RequestParam(value = "ptype", required = false) String pTypeList,
			@RequestParam(value = "recycled_units") String[] unitList,
			@RequestParam(value = "selected_year") String selected_year,
			@RequestParam(value = "esrMonth") String esrMonth) throws JSONException
	{

		String status = "success";
		JSONArray jsonarray = new JSONArray();
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
				esrProductWater.setYear(selected_year + "-" + esrMonth);
				esrProductWaterData = esrProductWaterServices.save(esrProductWater);
			}
			if (esrProductWaterData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", selected_year);
				jsonObject.put("month1", esrMonth);
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", "NA");
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
	 * This method used to save hazardous water data for monthly esr.
	 * 
	 * @param wasteTypeList the list of waste type list
	 * @param quantityList the list of quantity list
	 * @param unitList the list of unit
	 * @param pTypeList the list of product type list
	 * @param concList the concentration list
	 * @param selected_year the selected year for monthly esr
	 * @param esr_month the ESR month for monthly esr
	 * @return status it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveHazardousWaterDataMonth", method = RequestMethod.POST)
	public @ResponseBody String saveHazardousWaterDataEsrHWWaste(
			@RequestParam(value = "hw_id", required = false) String[] wasteTypeList,
			@RequestParam(value = "hw_quantity", required = false) Float[] quantityList,
			@RequestParam(value = "hw_units", required = false) String[] unitList,
			@RequestParam(value = "ptype", required = false) String pTypeList,
			@RequestParam(value = "hw_conc") Float[] concList,
			@RequestParam(value = "selected_year") String selected_year,
			@RequestParam(value = "esr_month") String esr_month) throws JSONException
	{

		String status = null;
		JSONArray jsonarray = new JSONArray();
		try
		{
			EsrHwSolidWaste esrHwSolidWasteData = new EsrHwSolidWaste();
			for (int i = 0; i < wasteTypeList.length; i++)
			{
				EsrHwSolidWaste esrHwSolidWaste = new EsrHwSolidWaste();
				esrHwSolidWaste.setYear(selected_year + "-" + esr_month);
				esrHwSolidWaste.setTypeOfWaste(wasteTypeList[i]);
				esrHwSolidWaste.setConcentration(concList[i]);
				esrHwSolidWaste.setQuantity(quantityList[i]);
				esrHwSolidWaste.setType(pTypeList);
				esrHwSolidWaste.setUnit(unitList[i]);
				esrHwSolidWasteData = esrHwSolidWasteServices.save(esrHwSolidWaste);
			}
			if (esrHwSolidWasteData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", selected_year);
				jsonObject.put("month1", esr_month);
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", "NA");
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
	 * This method used to save solid waste data for monthly esr.
	 * 
	 * @param wasteTypeList the list of waste type list
	 * @param quantityList the list of quantity list
	 * @param unitList the list of unit list
	 * @param pTypeList the product type list
	 * @param concList the list of concentration list
	 * @param selected_year the selected year
	 * @param esr_month the ESR month value
	 * @return status it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveSolidWasteDataMonth", method = RequestMethod.POST)
	public @ResponseBody String saveSolidWasteDataESR(
			@RequestParam(value = "sw_id", required = false) String[] wasteTypeList,
			@RequestParam(value = "sw_quantity", required = false) Float[] quantityList,
			@RequestParam(value = "sw_units", required = false) String[] unitList,
			@RequestParam(value = "ptype", required = false) String pTypeList,
			@RequestParam(value = "sw_conc") Float[] concList,
			@RequestParam(value = "selected_year") String selected_year,
			@RequestParam(value = "esr_month") String esr_month) throws JSONException
	{

		String status = null;
		try
		{
			JSONArray jsonarray = new JSONArray();
			EsrHwSolidWaste esrHwSolidWasteData = new EsrHwSolidWaste();
			for (int i = 0; i < wasteTypeList.length; i++)
			{
				EsrHwSolidWaste esrHwSolidWaste = new EsrHwSolidWaste();
				esrHwSolidWaste.setYear(selected_year + "-" + esr_month);
				esrHwSolidWaste.setTypeOfWaste(wasteTypeList[i]);
				esrHwSolidWaste.setConcentration(concList[i]);
				esrHwSolidWaste.setQuantity(quantityList[i]);
				esrHwSolidWaste.setType(pTypeList);
				esrHwSolidWaste.setUnit(unitList[i]);
				esrHwSolidWasteData = esrHwSolidWasteServices.save(esrHwSolidWaste);
			}
			if (esrHwSolidWasteData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", selected_year);
				jsonObject.put("month1", esr_month);
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", "NA");
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
	 * This method used to save the environmental Investment data for monthly esr.
	 * 
	 * @param curr_detail the current details
	 * @param curr_protection_mea the current protection
	 * @param curr_capital_investment the current capital investment
	 * @param selected_year the selected year
	 * @param esrMonth the ESR monthly data
	 * @return status it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveEnvInvestDataMonthly", method = RequestMethod.POST)
	public @ResponseBody String saveEnvInvestDataEsrYearly(
			@RequestParam(value = "curr_detail", required = false) String[] curr_detail,
			@RequestParam(value = "curr_protection_mea", required = false) String[] curr_protection_mea,
			@RequestParam(value = "curr_capital_investment", required = false) Float[] curr_capital_investment,
			@RequestParam(value = "selected_year", required = false) String selected_year,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{

		String status = null;
		JSONArray jsonarray = new JSONArray();
		try
		{
			EsrInvestment esrInvestmentData = new EsrInvestment();
			for (int i = 0; i < curr_detail.length; i++)
			{
				EsrInvestment esrInvestment = new EsrInvestment();
				esrInvestment.setProtectionMea(curr_protection_mea[i]);
				esrInvestment.setDetails(curr_detail[i]);
				esrInvestment.setInvestmentYear(selected_year);
				esrInvestment.setYear(selected_year + "-" + esrMonth);
				esrInvestment.setCapitalInvestment(curr_capital_investment[i]);
				esrInvestmentData = esrInvestmentServices.save(esrInvestment);
			}
			if (esrInvestmentData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", selected_year);
				jsonObject.put("month1", esrMonth);
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", "NA");
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
	 * This method used to save pollution data for monthly esr
	 * 
	 * @param description the description of product
	 * @param reduction_water the reduction water
	 * @param reduction_fuel the reduction fuel
	 * @param reduction_rm the reduction of raw material
	 * @param reduction_pc the reduction of pollution control
	 * @param capital_investment the capital investment
	 * @param reduction_maintenance the reduction maintenance
	 * @param selected_year the selected year
	 * @param esr_month the ESR month
	 * @return status it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-savePollutionDataMonth", method = RequestMethod.POST)
	public @ResponseBody String savePolutionControlDataESR(
			@RequestParam(value = "description", required = false) String[] description,
			@RequestParam(value = "reduction_water", required = false) Float[] reduction_water,
			@RequestParam(value = "reduction_fuel", required = false) Float[] reduction_fuel,
			@RequestParam(value = "reduction_rm", required = false) Float[] reduction_rm,
			@RequestParam(value = "reduction_pc", required = false) Float[] reduction_pc,
			@RequestParam(value = "capital_investment", required = false) Float[] capital_investment,
			@RequestParam(value = "reduction_maintenance", required = false) Float[] reduction_maintenance,
			@RequestParam(value = "selected_year", required = false) String selected_year,
			@RequestParam(value = "esr_month") String esr_month) throws JSONException
	{

		String status = null;
		JSONArray jsonarray = new JSONArray();
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
				esrPollutionControl.setYear(selected_year + "-" + esr_month);
				esrPollutionControlData = esrPollutionControlServices.save(esrPollutionControl);
			}
			if (esrPollutionControlData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", selected_year);
				jsonObject.put("month1", esr_month);
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", "NA");
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
	 * This method used to save the environmental investment data for next year of yearly esr and monthly esr
	 * 
	 * @param next_detail the next details
	 * @param next_protection_mea the data protection
	 * @param next_capital_investment the next capital investment data
	 * @param selected_year the selected year
	 * @param esrMonth the selected month.
	 * @return status it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveEnvInvestDataNextYearMonthly", method = RequestMethod.POST)
	public @ResponseBody String saveEnvInvestDataNextYearESRMonth1(
			@RequestParam(value = "next_detail", required = false) String[] next_detail,
			@RequestParam(value = "next_protection_mea", required = false) String[] next_protection_mea,
			@RequestParam(value = "next_capital_investment", required = false) Float[] next_capital_investment,
			@RequestParam(value = "selected_year", required = false) String selected_year,
			@RequestParam(value = "esrMonth", required = false) String esrMonth) throws JSONException
	{

		String status = null, year1[] = selected_year.split("-");
		int a = Integer.parseInt(year1[0]), b = Integer.parseInt(year1[1]);
		String nextYear = (a + 1) + "-" + (b + 1);
		JSONArray jsonarray = new JSONArray();
		try
		{
			EsrInvestment esrInvestmentData = new EsrInvestment();
			for (int i = 0; i < next_detail.length; i++)
			{
				EsrInvestment esrInvestment = new EsrInvestment();
				esrInvestment.setProtectionMea(next_protection_mea[i]);
				esrInvestment.setDetails(next_detail[i]);
				esrInvestment.setYear(selected_year + "-" + esrMonth);
				esrInvestment.setInvestmentYear(nextYear);
				esrInvestment.setCapitalInvestment(next_capital_investment[i]);
				esrInvestmentData = esrInvestmentServices.save(esrInvestment);
			}
			if (esrInvestmentData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", selected_year);
				jsonObject.put("month1", esrMonth);
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", "NA");
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

	//// mmmm
	// @RequestMapping(value = "/ajax-getRecycledDataYearm", method = RequestMethod.POST)
	// public @ResponseBody String getEsrRecycledDataYear(
	// @RequestParam(value = "type", required = false) String type,
	// @RequestParam(value = "selectedYear", required = false) String selectedYear) throws JSONException
	// {
	// String jsonString = null;
	// List<EsrProductWater> esrProductWaterDatas = new ArrayList<>();
	// esrProductWaterDatas = esrProductWaterServices.getAllDataByYear(selectedYear, type);
	// JSONArray jsonArray = new JSONArray();
	// JSONObject json;
	// for (int i = 0; i < esrProductWaterDatas.size(); i++)
	// {
	// json = new JSONObject();
	// json.put("PollName", esrProductWaterDatas.get(i).getProductName());
	// json.put("PrivieousData", esrProductWaterDatas.get(i).getPreviousData());
	// json.put("CurrantData", esrProductWaterDatas.get(i).getCurrentData());
	// json.put("Uom", esrProductWaterDatas.get(i).getUnit());
	//
	// jsonArray.put(json);
	// }
	// int size = jsonArray.length();
	// if (size != 0)
	// {
	// jsonString = jsonArray.toString();
	// }
	//
	// return jsonString;
	// }

	///////
	/**
	 * This method used to save particular data of the monthly esr
	 * 
	 * @param particulars the particulars name
	 * @param selected_year the selected year
	 * @param esr_month the ESR monthly data.
	 * @return status it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-saveParticularsMonth", method = RequestMethod.POST)
	public @ResponseBody String getParticularsESRM(
			@RequestParam(value = "particulars", required = false) String[] particulars,
			@RequestParam(value = "selected_year", required = false) String selected_year,
			@RequestParam(value = "esr_month") String esr_month) throws JSONException
	{

		String status = null;
		JSONArray jsonarray = new JSONArray();
		try
		{
			EsrParticulars esrParticularsData = new EsrParticulars();
			for (int i = 0; i < particulars.length; i++)
			{
				EsrParticulars esrParticulars = new EsrParticulars();
				esrParticulars.setParticulars(particulars[i]);
				esrParticulars.setYear(selected_year + "-" + esr_month);
				esrParticularsData = esrParticularsServices.save(esrParticulars);
			}
			if (esrParticularsData != null)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", selected_year);
				jsonObject.put("month1", esr_month);
				jsonarray.put(jsonObject);
			}
			else
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("selectedYear", "NA");
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
}
