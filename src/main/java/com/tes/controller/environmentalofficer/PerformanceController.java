
package com.tes.controller.environmentalofficer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.WaterInventory;
import com.tes.model.WaterMarks;
import com.tes.model.WaterSewPoll;
import com.tes.model.WateriePollutant;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.WaterMarksServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class used to data quality of performance and water filter data quality preformance and
 * then check compliance and Non-compliancee value
 * 
 * @author Jemish Moradiya
 */
@RestController
@RequestMapping(value = {"/env", "/management"})
public class PerformanceController
{

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	RegMultipleFilterDataServices regularFiltersDataServices;

	@Autowired
	RegFiltersUseDataServices regularFiltersUseDataServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	WaterMarksServices waterMarksServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	private static final Logger LOGGER = LogManager.getLogger(PerformanceController.class);

	/**
	 * This method used to display performance data of different type of performance.
	 * 
	 * @param performanceName the name of the performance type
	 * @param request the servlet request we are processing.
	 * @return performanceName, performanceTitle, consentYears
	 */
	@RequestMapping("/envr-officer-performance")
	public ModelAndView envrOfficerPerformance(@RequestParam("performancefor") String performanceName,
			HttpServletRequest request)
	{

		ModelAndView modelAndView;
		modelAndView = new ModelAndView();
		try
		{
			byte[] valueDecoded = Base64.decodeBase64(performanceName);
			String decodedPerformnoteeanceName = new String(valueDecoded);

			int consentMinYear = 0;
			List<Integer> consentYears = new ArrayList<>();

			String dateToSend[] = Utilities.getTodaysDate().split("-");
			int currentYear = Integer.parseInt(dateToSend[0]);

			consentMinYear = regularDataServices.regDataMinYear();

			if (Validator.isEmpty(consentMinYear))
			{
				consentMinYear = 0;
			}

			if (consentMinYear == 0)
			{

			}
			else
			{

				int maxYearDiff = currentYear - consentMinYear;
				maxYearDiff = maxYearDiff + 1;
				for (int i = 1; i <= maxYearDiff; i++)
				{
					consentYears.add(currentYear);
					currentYear = currentYear - 1;
				}
			}

			modelAndView.addObject("performanceName", decodedPerformnoteeanceName);
			modelAndView.addObject("performanceTitle", new String(Utilities.getTitle(decodedPerformnoteeanceName)));
			modelAndView.addObject("consentYears", consentYears);
			modelAndView.setViewName("EnvrOfficer/Performance");
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return modelAndView;
	}

	/**
	 * This method used to display all type product and Individual product data performance
	 * 
	 * @param action it return Single Data Quality & today date data
	 * @param typename the type name of performance
	 * @param today the current date of performance data
	 * @param prevDate the previous date of performance data
	 * @param request the servlet request we are processing.
	 * @return jsonArray it returns String value of json array.
	 * @throws JsonProcessingException while converting Entity into string
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping(value = {"/ajax-performanceComplianceValues"})
	public @ResponseBody @JsonRawValue String performanceValues(
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "typename", required = false) String typename,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate, HttpServletRequest request)
			throws JsonProcessingException, JSONException, ParseException
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			List<Object[]> allProductsLists = new ArrayList<>();

			String splitToday[] = today.split("-");
			int year = Integer.parseInt(splitToday[0]);
			// for singal productType data quality performance
			String productName = null, pUnit = null;
			int totalNonComplianceCount = 0;
			Float complianceQuantity = 0.0f;
			Double noncompliancePercentage = 0.0d;
			int noOfAllproducts = 0;
			if (typename.equalsIgnoreCase("product"))
			{
				typename = "Product";
			}
			else if (typename.equalsIgnoreCase("byproduct"))
			{
				typename = "byproduct";
			}
			else if (typename.equalsIgnoreCase("raw"))
			{
				typename = "Raw Material";
			}
			else if (typename.equalsIgnoreCase("fuel"))
			{
				typename = "Fuel";
			}
			else if (typename.equalsIgnoreCase("hwp"))
			{
				typename = "hwp";
			}
			else if (typename.equalsIgnoreCase("hwpcf"))
			{
				typename = "hwpcf";
			}
			else if (typename.equalsIgnoreCase("nhwp"))
			{
				typename = "nhwp";
			}
			else if (typename.equalsIgnoreCase("nhwpcf"))
			{
				typename = "nhwpcf";
			}
			else if (typename.equalsIgnoreCase("bio"))
			{
				typename = "Bio-Medical";
			}

			allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(typename, today);
			noOfAllproducts = allProductsLists.size();

			if (!Validator.isEmpty(allProductsLists))
			{
				int i = 0;
				int countOfNonCompliance = 0;
				for (Object[] allProductsListData : allProductsLists)
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
						if ((0 == year % 4) && (0 != year % 100) || (0 == year % 400))
						{
							complianceQuantity = complianceQuantity / 366;
						}
						else
						{
							complianceQuantity = complianceQuantity / 365;
						}
					}

					try
					{
						countOfNonCompliance = regularDataServices.getNonComplianceByProductName(productName, prevDate,
								today, complianceQuantity);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

					totalNonComplianceCount += countOfNonCompliance;
				}

				noncompliancePercentage = (double) ((double) totalNonComplianceCount / ((double) noOfAllproducts * 30))
						* 100;

				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("NonCompliance", new String(noncompliancePercentage + "-" + typename));
				jsonArray.put(hashMap);
			}
			else
			{

				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("NonCompliance", new String("NA"));
			}
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return jsonArray.toString();
	}

	/**
	 * This method used for singal productType data quality performance
	 * 
	 * @param action it return Single Data Quality performance & today performance.
	 * @param typename the name of product type.
	 * @param today the current date of performance data.
	 * @param prevDate the previous date of performance data.
	 * @param request the servlet request we are processing.
	 * @return jsonString it returns String value of json array.
	 * @throws JsonProcessingException while converting Entity into string
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping(value = {"/ajax-performanceValues"})
	public @ResponseBody @JsonRawValue String performanceComplianceValues(
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "typename", required = false) String typename,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate, HttpServletRequest request)
			throws JsonProcessingException, JSONException, ParseException
	{

		Object[][] jsonString = null;
		Gson gson;
		gson = new Gson();
		try
		{
			List<Object[]> allProductsLists = new ArrayList<>();

			// for singal productType data quality performance
			String productName = null;
			int noCount = 0;

			if (typename.equalsIgnoreCase("product"))
			{
				typename = "Product";
			}
			else if (typename.equalsIgnoreCase("byproduct"))
			{
				typename = "byproduct";
			}
			else if (typename.equalsIgnoreCase("raw"))
			{
				typename = "Raw Material";
			}
			else if (typename.equalsIgnoreCase("fuel"))
			{
				typename = "Fuel";
			}
			else if (typename.equalsIgnoreCase("hwp"))
			{
				typename = "hwp";
			}
			else if (typename.equalsIgnoreCase("hwpcf"))
			{
				typename = "hwpcf";
			}
			else if (typename.equalsIgnoreCase("nhwp"))
			{
				typename = "nhwp";
			}
			else if (typename.equalsIgnoreCase("nhwpcf"))
			{
				typename = "nhwpcf";
			}
			else if (typename.equalsIgnoreCase("bio"))
			{
				typename = "Bio-Medical";
			}

			try
			{
				allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(typename, today);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}

			int maxraw = allProductsLists.size();

			jsonString = new Object[maxraw][2];

			if (!Validator.isEmpty(allProductsLists))
			{
				int i = 0;
				for (Object[] allProductsListData : allProductsLists)
				{

					productName = (String) allProductsListData[1];
					jsonString[i][0] = productName;
					try
					{
						noCount = allProductsServices.getNumberFromRegularData(productName, prevDate, today);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

					double finalValue = ((double) noCount / 30);
					finalValue = finalValue * 100;

					jsonString[i][1] = finalValue;

					i++;
				}

			}
			else
			{

				// NO PRODUCTS //
				/*
				 * jsonString[0][0] = "NA"; jsonString[0][1] = "NA";
				 */
			}

		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return gson.toJson(jsonString);
	}

	/**
	 * This method used to singal productType data quality performance of all product type of data and
	 * after all product unit reading is display compliance and Non-compliance value
	 * 
	 * @param typename the type name of product data
	 * @param today the current date of performance data
	 * @param prevDate the previous date of performance data
	 * @param request the servlet request we are processing.
	 * @return jsonString it returns String value of json array.
	 * @throws JsonProcessingException while converting Entity into string
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping(value = {"/ajax-performanceComplianceSingleValues"})
	public @ResponseBody @JsonRawValue String performanceComplianceSingleValues(
			@RequestParam(value = "typename", required = false) String typename,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate, HttpServletRequest request)
			throws JsonProcessingException, JSONException, ParseException
	{

		Object[][] jsonString = null;
		Gson gson;
		gson = new Gson();
		try
		{
			List<Object[]> allProductsLists = new ArrayList<>();
			// for singal productType data quality performance
			String productName = null, pUnit = null;
			String splitToday[] = today.split("-");
			int year = Integer.parseInt(splitToday[0]);
			// for singal productType data quality performance
			Float complianceQuantity = 0.0f;

			if (typename.equalsIgnoreCase("product"))
			{
				typename = "Product";
			}
			else if (typename.equalsIgnoreCase("byproduct"))
			{
				typename = "byproduct";
			}
			else if (typename.equalsIgnoreCase("raw"))
			{
				typename = "Raw Material";
			}
			else if (typename.equalsIgnoreCase("fuel"))
			{
				typename = "Fuel";
			}
			else if (typename.equalsIgnoreCase("hwp"))
			{
				typename = "hwp";
			}
			else if (typename.equalsIgnoreCase("hwpcf"))
			{
				typename = "hwpcf";
			}
			else if (typename.equalsIgnoreCase("nhwp"))
			{
				typename = "nhwp";
			}
			else if (typename.equalsIgnoreCase("nhwpcf"))
			{
				typename = "nhwpcf";
			}
			else if (typename.equalsIgnoreCase("bio"))
			{
				typename = "Bio-Medical";
			}

			try
			{
				allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(typename, today);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}

			int maxraw = allProductsLists.size();

			jsonString = new Object[maxraw][2];

			if (!Validator.isEmpty(allProductsLists))
			{
				int i = 0;
				int countOfNonCompliance = 0;
				for (Object[] allProductsListData : allProductsLists)
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
						if ((0 == year % 4) && (0 != year % 100) || (0 == year % 400))
						{
							complianceQuantity = complianceQuantity / 366;
						}
						else
						{
							complianceQuantity = complianceQuantity / 365;
						}
					}

					try
					{
						countOfNonCompliance = regularDataServices.getNonComplianceByProductName(productName, prevDate,
								today, complianceQuantity);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					jsonString[i][0] = productName;

					double finalValue = ((double) countOfNonCompliance / 30);
					finalValue = finalValue * 100;

					jsonString[i][1] = finalValue;

					i++;
				}

			}
			else
			{
				/*
				 * jsonString[0][0] = "NA"; jsonString[0][1] = "NA";
				 */
			}

		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return gson.toJson(jsonString);
	}

	/**
	 * This method used to display the daily performance data.
	 * 
	 * @param typename the type name of product data.
	 * @param today the current data of performance data.
	 * @param prevDate the previous date of performance data.
	 * @param request the servlet request we are processing.
	 * @return jsonArray it returns String value of json array.
	 * @throws JsonProcessingException while converting Entity into string
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping(value = {"/ajax-performanceDailyData"})
	public @ResponseBody @JsonRawValue String performanceDailyData(
			@RequestParam(value = "typename", required = false) String typename,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate, HttpServletRequest request)
			throws JsonProcessingException, JSONException, ParseException
	{

		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			List<Object[]> allProductsLists = new ArrayList<>();
			List<Object[]> regularQuantitys = new ArrayList<>();

			// for singal productType data quality performance
			String productName = null, pUnit = null, inputDate = null, warning = null;
			String splitToday[] = today.split("-");

			int year = Integer.parseInt(splitToday[0]);
			// for singal productType data quality performance
			Float complianceQuantity = 0.0f, quantityDiffrence = 0.0f;

			if (typename.equalsIgnoreCase("product"))
			{
				typename = "Product";
			}
			else if (typename.equalsIgnoreCase("byproduct"))
			{
				typename = "byproduct";
			}
			else if (typename.equalsIgnoreCase("raw"))
			{
				typename = "Raw Material";
			}
			else if (typename.equalsIgnoreCase("fuel"))
			{
				typename = "Fuel";
			}
			else if (typename.equalsIgnoreCase("hwp"))
			{
				typename = "hwp";
			}
			else if (typename.equalsIgnoreCase("hwpcf"))
			{
				typename = "hwpcf";
			}
			else if (typename.equalsIgnoreCase("nhwp"))
			{
				typename = "nhwp";
			}
			else if (typename.equalsIgnoreCase("nhwpcf"))
			{
				typename = "nhwpcf";
			}
			else if (typename.equalsIgnoreCase("bio"))
			{
				typename = "Bio-Medical";
			}

			try
			{
				allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(typename, today);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}

			if (!Validator.isEmpty(allProductsLists))
			{
				for (Object[] allProductsListData : allProductsLists)
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
						if ((0 == year % 4) && (0 != year % 100) || (0 == year % 400))
						{
							complianceQuantity = complianceQuantity / 366;
						}
						else
						{
							complianceQuantity = complianceQuantity / 365;
						}
					}

					try
					{
						regularQuantitys = regularDataServices.getQuantityInpdate(productName, prevDate, today);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

					for (Object[] regularQuantity : regularQuantitys)
					{

						float rQuentity = Utilities.round((float) regularQuantity[0], 2);
						inputDate = (String) regularQuantity[1];

						if (complianceQuantity > rQuentity)
						{
							quantityDiffrence = Utilities.round(((float) (complianceQuantity - rQuentity)), 1);
							warning = "Production was <br>less by " + quantityDiffrence + " " + pUnit;
						}
						else if (complianceQuantity < rQuentity)
						{
							quantityDiffrence = Utilities.round(((float) (rQuentity - complianceQuantity)), 1);
							warning = "Production was <br>more by " + quantityDiffrence + " " + pUnit;
						}
						else
						{
							warning = "--";
						}

						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("product_name", new String(productName));
						hashMap.put("input_date", new String(inputDate));
						hashMap.put("quantity", new Float(rQuentity));
						hashMap.put("unitp", new String(pUnit));
						hashMap.put("warning", new String(warning));

						jsonArray.put(hashMap);

					}
				}

			}
			else
			{
				/*
				 * jsonString[0][0] = "NA"; jsonString[0][1] = "NA";
				 */
			}
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return jsonArray.toString();
	}

	/**
	 * This method used to get water filter data and water performance data quality.
	 * 
	 * @param type The type of the product
	 * @param today The current date of performance data
	 * @param prevDate The previous date of performance data
	 * @return jsonArray it returns String value of json array.
	 */
	// Effected By Water Inventory ........by vishal
	/*
	 * @RequestMapping(value = {"ajax-getWaterPerformanceQuality"})
	 * @ResponseBody
	 * public @JsonRawValue String getCountOfProduct(@RequestParam(value = "type") String type,
	 * @RequestParam(value = "today", required = false) String today,
	 * @RequestParam(value = "prevDate", required = false) String prevDate)
	 * {
	 * // json array for ajax response
	 * JSONArray jsonArray;
	 * jsonArray = new JSONArray();
	 * try
	 * {
	 * // variable declarations
	 * String ProductName = null;
	 * double missedData = 0.0d;
	 * int countData = 0;
	 * // array lists
	 * List<String> waterSourceData = new ArrayList<>();
	 * List<String> filterNameList = new ArrayList<>();
	 * List<String> filterUseList = new ArrayList<>();
	 * List<WaterInventory> waterInvetoryUses = new ArrayList<>();
	 * List<String> industrialList = new ArrayList<>();
	 * if (type.equalsIgnoreCase("source"))
	 * {
	 * waterSourceData = waterSourceServices.getAllWaterSourceData();
	 * if (!Validator.isEmpty(waterSourceData))
	 * {
	 * for (int i = 0; i < waterSourceData.size(); i++)
	 * {
	 * double finalValue = 0;
	 * String sourceType = waterSourceData.get(i);
	 * String metervalue = null;
	 * try
	 * {
	 * countData = regularSourceDataServices.countAvailableDataBetweenDatesBySourceName(prevDate,
	 * today, sourceType);
	 * metervalue = waterSourceServices.getMetervalue(sourceType);
	 * if (metervalue.equalsIgnoreCase("Yes"))
	 * {
	 * finalValue = 50;
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * e.printStackTrace();
	 * }
	 * finalValue += (double) (((double) countData / 30) * 100) / 2;
	 * // finalValue = 100 - finalValue;
	 * HashMap<String, Object> hashMap = new HashMap<String, Object>();
	 * hashMap.put("sourceName", new String(sourceType));
	 * hashMap.put("missed_data", new Double(finalValue));
	 * hashMap.put("meter_value", new String(metervalue));
	 * jsonArray.put(hashMap);
	 * }
	 * }
	 * }
	 * else if (type.equalsIgnoreCase("filter"))
	 * {
	 * // List<Filters> filtersAllData
	 * filterNameList = filtersServices.getAllDistFiltersName();
	 * if (!Validator.isEmpty(filterNameList))
	 * {
	 * for (int i = 0; i < filterNameList.size(); i++)
	 * {
	 * double finalValue = 0;
	 * String filterName = filterNameList.get(i);
	 * String metervalue = null;
	 * try
	 * {
	 * //Effected By Water Inventory ........by vishal
	 * countData = 0;//regularFiltersDataServices.countRegFilterDateBtwDatesByFilterName(prevDate,today, filterName);
	 * metervalue = filtersServices.getMetervaluefilter(filterName);
	 * if (metervalue.equalsIgnoreCase("Yes"))
	 * {
	 * finalValue = 50;
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * e.printStackTrace();
	 * }
	 * finalValue += (double) (((double) countData / 30) * 100) / 2;
	 * // finalValue = 100 - finalValue;
	 * HashMap<String, Object> hashMap = new HashMap<String, Object>();
	 * hashMap.put("sourceName", new String("Filter : " + filterName));
	 * hashMap.put("missed_data", new Double(finalValue));
	 * hashMap.put("meter_value", new String(metervalue));
	 * jsonArray.put(hashMap);
	 * }
	 * }
	 * else
	 * {
	 * }
	 * }
	 * else if (type.equalsIgnoreCase("filtered"))
	 * {
	 * filterNameList = filtersServices.getAllDistFiltersName();
	 * if (!Validator.isEmpty(filterNameList))
	 * {
	 * for (int i = 0; i < filterNameList.size(); i++)
	 * {
	 * String filterName = filterNameList.get(i);
	 * filterUseList = filtersServices.getFiltersUseByFilterName(filterName);
	 * if (!Validator.isEmpty(filterUseList))
	 * {
	 * for (int j = 0; j < filterUseList.size(); j++)
	 * {
	 * double finalValue = 0;
	 * String filterUse = filterUseList.get(j);
	 * String metervalue = null;
	 * try
	 * {
	 * countData = regularFiltersUseDataServices
	 * .countRegFilterUseDateBtwDatesByFilterNameType(prevDate, today, filterUse,
	 * filterName);
	 * metervalue = filtersServices.getMetervaluefiltertype(filterName, filterUse);
	 * if (metervalue.equalsIgnoreCase("Yes"))
	 * {
	 * finalValue = 50;
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * e.printStackTrace();
	 * }
	 * finalValue += (double) (((double) countData / 30) * 100) / 2;
	 * // finalValue = 100 - finalValue;
	 * HashMap<String, Object> hashMap = new HashMap<String, Object>();
	 * hashMap.put("sourceName", new String(filterName + "-" + filterUse));
	 * hashMap.put("missed_data", new Double(finalValue));
	 * hashMap.put("meter_value", new String(metervalue));
	 * jsonArray.put(hashMap);
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * LOGGER.error(e);
	 * }
	 * return jsonArray.toString();
	 * }
	 */

	/**
	 * This method used to get list of single type of data Direct Use Data Quality.
	 * 
	 * @return directUseList
	 */
	@RequestMapping("ajax-getListOfDirectUse")
	@ResponseBody
	public @JsonRawValue List<String> getListOfDirectUse()
	{

		List<String> directUseList;
		directUseList = new ArrayList<>();
		try
		{
			// array lists
			List<WaterInventory> waterInvetoryUses = new ArrayList<>();
			List<String> industrialList = new ArrayList<>();

			// json array for ajax response
			JSONArray jsonArray = new JSONArray();

			// check use of source checked or not
			waterInvetoryUses = waterInventoryServices.getUseOfSource();

			if (!Validator.isEmpty(waterInvetoryUses))
			{
				for (int i = 0; i < waterInvetoryUses.size(); i++)
				{
					// Effected By Water Inventory ........by vishal
					String isDomestic = null;// waterInvetoryUses.get(i).getDomesticUseOfSource();
					String isIndustrial = null;// waterInvetoryUses.get(i).getIndustrialUseOfSource();
					String isLaundry = null;// waterInvetoryUses.get(i).getLaundryUseOfSource();
					String isFireHydrant = null;// waterInvetoryUses.get(i).getFireHydrantUseOfSource();

					if (isDomestic.equalsIgnoreCase("checked"))
					{
						directUseList.add("Domestic");
					}

					if (isIndustrial.equalsIgnoreCase("checked"))
					{
						// Effected By Water Inventory ........by vishal
						industrialList = new ArrayList<>();// industrialServices.getIndustrialLists();
						if (!Validator.isEmpty(industrialList))
						{
							for (int j = 0; j < industrialList.size(); j++)
							{
								String indName = industrialList.get(j);
								directUseList.add(indName);
							}
						}
					}

					if (isLaundry.equalsIgnoreCase("checked"))
					{
						directUseList.add("Laundry");
					}
					if (isFireHydrant.equalsIgnoreCase("checked"))
					{
						directUseList.add("Fire Hydrant");
					}
				}
			}
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return directUseList;
	}

	/**
	 * This method used to get single type of data Direct Use Data Quality.
	 * 
	 * @param directUseName The name of the direct use of product name
	 * @param today The current date of performance data
	 * @param prevDate The previous date of performance data
	 * @return jsonArray it returns String value of json array.
	 */
	@RequestMapping(value = {"ajax-singleDirectUseDataQuality"})
	@ResponseBody
	public @JsonRawValue String singleDirectUseDataQuality(@RequestParam(value = "directUseName") String directUseName,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate)
	{

		// json array for ajax response
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			// variable declarations
			int countData = 0;

			try
			{
				// Effected By Water Inventory ........by vishal
				countData = 0;// regularWaterUseDataServices.countRegWaterUseDataByDateSType(prevDate, today, directUseName);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			double finalValue = (double) ((double) countData / 30) * 100;
			// finalValue = 100 - finalValue;

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("sourceName", new String(directUseName));
			hashMap.put("missed_data", new Double(finalValue));
			jsonArray.put(hashMap);
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return jsonArray.toString();
	}

	/**
	 * This method used to get water performance data i.e STP and ETP in percentage of
	 * Non-Compliance data
	 * 
	 * @param type the type of the product
	 * @param today the current date of water performance
	 * @param prevDate the previous date of water performance
	 * @param isRecordFor the record
	 * @param yearValue
	 * @return jsonString it returns String value of json array.
	 */
	@RequestMapping(value = {"ajax-getWaterPercentageNonCompliance"})
	@ResponseBody
	public @JsonRawValue String getWaterPercentageNonCompliance(@RequestParam(value = "type") String type,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate,
			@RequestParam(value = "isRecordFor", required = false) String isRecordFor,
			@RequestParam(value = "yearValue", required = false) int yearValue)
	{

		Object[][] jsonString = null;
		Gson gson;
		gson = new Gson();
		try
		{
			// String isRecordFor = "Year";
			PageRequest recordCount = new PageRequest(0, 3);
			boolean forMonth = false;
			boolean forYear = false;
			boolean forDay = false;
			int monthValue = 0;

			if (isRecordFor.equalsIgnoreCase("Year"))
			{
				forYear = true;
			}
			else if (isRecordFor.equalsIgnoreCase("Month"))
			{
				forMonth = true;
				String temp[] = today.split("-");
				yearValue = Integer.valueOf(temp[0]);
				monthValue = Integer.valueOf(temp[1]);
			}
			else if (isRecordFor.equalsIgnoreCase("Day"))
			{
				forDay = true;
				String temp[] = today.split("-");
				yearValue = Integer.valueOf(temp[0]);
				monthValue = Integer.valueOf(temp[1]);

			}

			/* variable declairation for ETP */
			List<WateriePollutant> wateriePollutantDatas = new ArrayList<>();
			List<RegEffPoll> regEffPollDatas = new ArrayList<>();

			/* variable declairation for STP */
			List<WaterSewPoll> waterieSewPollutantDatas = new ArrayList<>();
			List<RegSewPoll> regSewPollDatas = new ArrayList<>();

			String pollutantName = "";
			float pollQuantity = 0.0f;
			int maxraw = 0;

			if (type.equalsIgnoreCase("ETP"))
			{
				wateriePollutantDatas = wateriePollutantServices.getWateriePollData();
				maxraw = wateriePollutantDatas.size();
			}
			else
			{
				waterieSewPollutantDatas = waterSewPollServices.getWaterSewPollData();
				maxraw = waterieSewPollutantDatas.size();
			}

			jsonString = new Object[maxraw + 1][6];

			if (type.equalsIgnoreCase("ETP"))
			{
				// ETP SECTION

				if (!Validator.isEmpty(wateriePollutantDatas))
				{

					// add ETP or STP
					jsonString[0][0] = type;
					jsonString[0][1] = "Limits";
					jsonString[0][5] = "Exceedence";

					// ETP is not Emptys
					for (int i = 0; i < maxraw; i++)
					{
						int exc = 0;
						float regEffQuantity = 0.0f;
						String sampleDate = null;
						pollutantName = wateriePollutantDatas.get(i).getPollName();
						pollQuantity = wateriePollutantDatas.get(i).getQuantity();

						jsonString[i + 1][0] = pollutantName;
						jsonString[i + 1][1] = String.valueOf(pollQuantity);

						if (forYear)
						{
							// regEffPollDatas = regEffPollServices.getRegEffPollDataBetweenDateByPollName(pollutantName,
							// yearValue, recordCount);
						}
						if (forMonth)
						{
							// regEffPollDatas = regEffPollServices.getRegEffPollDataBetweenDateByPollName(pollutantName,
							// yearValue, monthValue, recordCount);
						}
						if (forDay)
						{
							// regEffPollDatas = regEffPollServices.getRegEffPollDataBetweenDateByPollName(pollutantName,
							// today, yearValue, monthValue, recordCount);
						}

						if (!Validator.isEmpty(regEffPollDatas))
						{
							for (int j2 = 0; j2 < regEffPollDatas.size(); j2++)
							{

								// this will add outcons from regEffPoll
								regEffQuantity = regEffPollDatas.get(j2).getOuConsE();
								jsonString[i + 1][j2 + 2] = regEffQuantity;

								// get sample date //this will add date of last 3 samples
								sampleDate = regEffPollDatas.get(j2).getSampE();
								jsonString[0][j2 + 2] = sampleDate;

								if (regEffQuantity > pollQuantity)
									exc++;

								jsonString[i + 1][5] = exc;
							}
						}
						else
						{
							jsonString[i + 1][2] = "No Data";
							jsonString[i + 1][3] = "No Data";
							jsonString[i + 1][4] = "No Data";
							jsonString[i + 1][5] = exc;
						}
					}

				}
				else
				{

				}

			}
			else
			{
				// STP SECTION

				if (!Validator.isEmpty(waterieSewPollutantDatas))
				{

					// add ETP or STP
					jsonString[0][0] = type;
					jsonString[0][1] = "Limits";
					jsonString[0][5] = "Exceedence";

					// STP is not Empty
					for (int i = 0; i < maxraw; i++)
					{
						int exc = 0;
						float regSewQuantity = 0.0f;
						String sampleDate = null;
						pollutantName = waterieSewPollutantDatas.get(i).getPollName();
						pollQuantity = waterieSewPollutantDatas.get(i).getQuantity();

						jsonString[i + 1][0] = pollutantName;
						jsonString[i + 1][1] = String.valueOf(pollQuantity);

						if (forYear)
						{
							// regSewPollDatas = regSewPollServices.getRegSewPollDataBetweenDateByPollNameYear(pollutantName, yearValue, recordCount);
						}
						if (forMonth)
						{
							// regSewPollDatas = regSewPollServices.getRegSewPollDataBetweenDateByPollNameMonth(
							// pollutantName, yearValue, monthValue, recordCount);
						}
						if (forDay)
						{
							// regSewPollDatas = regSewPollServices.getRegSewPollDataBetweenDateByPollNameDay(
							// pollutantName, today, yearValue, monthValue, recordCount);
						}

						if (!Validator.isEmpty(regSewPollDatas))
						{
							for (int j2 = 0; j2 < regSewPollDatas.size(); j2++)
							{

								// this will add outcons from regSewPoll
								regSewQuantity = regSewPollDatas.get(j2).getInConsS();
								jsonString[i + 1][j2 + 2] = regSewQuantity;

								// get sample date //this will add date of last 3 samples
								sampleDate = regSewPollDatas.get(j2).getSampS();
								jsonString[0][j2 + 2] = sampleDate;

								if (regSewQuantity > pollQuantity)
									exc++;

								jsonString[i + 1][5] = exc;
							}
						}
						else
						{
							jsonString[i + 1][2] = "No Data";
							jsonString[i + 1][3] = "No Data";
							jsonString[i + 1][4] = "No Data";
							jsonString[i + 1][5] = exc;
						}
					}

				}
				else
				{

				}

				// STP ELSE END

			}

		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return gson.toJson(jsonString);
	}

	/**
	 * This method used to get water performance
	 * 
	 * @param etp the effluent treatment pollutant
	 * @param stp the sewage treatment pollutant
	 * @param toda the current date of water performance
	 * @param prevDate the privious date of water performance
	 * @param isRecordFor the record
	 * @param yearValue
	 * @return jsonArray it returns String value of json array.
	 */
	@RequestMapping(value = {"ajax-getWaterPerformance"})
	@ResponseBody
	public @JsonRawValue String getWaterPerformance(@RequestParam(value = "etp") int etp,
			@RequestParam(value = "stp", required = false) int stp,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate,
			@RequestParam(value = "isRecordFor", required = false) String isRecordFor,
			@RequestParam(value = "yearValue", required = false) int yearValue)
	{

		// json array for ajax response
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{

			int isETP = 0, isSTP = 0;
			// Check ETP availability
			try
			{
				isETP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("ETP");
				if (isETP > 0)
					isETP = 1;
			}
			catch (Exception e)
			{
				e.printStackTrace();
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
				e.printStackTrace();
			}

			// String isRecordFor = "Year";
			PageRequest recordCount = null;
			boolean forMonth = false;
			boolean forYear = false;
			boolean forDay = false;
			int monthValue = 0;
			List<RegEffPoll> regEffPollDatas = new ArrayList<>();

			if (isRecordFor.equalsIgnoreCase("Year"))
			{
				forYear = true;
				recordCount = new PageRequest(0, 12);
				today = yearValue + "-12-31";
			}
			else if (isRecordFor.equalsIgnoreCase("Month"))
			{
				String temp[] = today.split("-");
				yearValue = Integer.valueOf(temp[0]);
				monthValue = Integer.valueOf(temp[1]);
				recordCount = new PageRequest(0, 1);
				forMonth = true;
			}
			else if (isRecordFor.equalsIgnoreCase("Day"))
			{
				forDay = true;
				recordCount = new PageRequest(0, 1);
			}

			float finalETP = 0, finalSTP = 0, finalALL = 0, finalValue = 0;
			String isEtp = "", isStp = "";
			String sampleDate = "";
			// WaterMarksServices
			List<String> waterMarkTypeList = new ArrayList<>();
			List<WaterMarks> waterMarkDatas = new ArrayList<>();
			List<WaterMarks> waterMarkWateriePollDatas = new ArrayList<>();
			List<Object[]> wateriePollDatas = new ArrayList<>();

			float xx = 0; // Total marks for ETP
			float yy = 0; // Total marks for STP
			float mk = 0; // factor

			float organic = 0; // comparing with 70
			float metals = 0; // comparing with 70
			float solids = 0; // comparing with 70
			float nutrients = 0; // comparing with 70
			float special = 0;

			float stp_bod = 0; // comparing with 30
			float stp_ss = 0; // comparing with 30
			float stp_ong = 0; // comparing with 30

			String item = null;
			float marks = 0.0f;

			if (etp == 1)
				isEtp = "ETP";
			if (stp == 1)
				isStp = "STP";

			if (etp == 1 && stp == 1)
			{

				xx = 70; // Total marks for ETP
				yy = 30; // Total marks for STP
				mk = 0; // factor

				organic = 45.5f; // comparing with 70
				metals = 14f; // comparing with 70
				solids = 3.5f; // comparing with 70
				nutrients = 3.5f; // comparing with 70
				special = 3.5f;

				waterMarkTypeList = waterMarksServices.getAllwaterMarksByMainType(isEtp);

				String wpType = null;
				if (!Validator.isEmpty(waterMarkTypeList))
				{

					for (int i = 0; i < waterMarkTypeList.size(); i++)
					{
						wpType = waterMarkTypeList.get(i);

						if (wpType.equalsIgnoreCase("Organics"))
						{
							mk += organic;
						}
						else if (wpType.equalsIgnoreCase("Metals"))
						{
							mk += metals;
						}
						else if (wpType.equalsIgnoreCase("Solids"))
						{
							mk += solids;
						}
						else if (wpType.equalsIgnoreCase("Nutrients"))
						{
							mk += nutrients;
						}
						else if (wpType.equalsIgnoreCase("Special"))
						{
							mk += special;
						}
					}

					organic = 0;
					metals = 0;
					solids = 0;
					nutrients = 0;
					special = 0;

					for (int i = 0; i < waterMarkTypeList.size(); i++)
					{
						String wpType1 = waterMarkTypeList.get(i);

						if (wpType1.equalsIgnoreCase("Organics"))
						{
							organic = (xx * 45.5f) / mk;
						}
						else if (wpType1.equalsIgnoreCase("Metals"))
						{
							metals = (xx * 14f) / mk;
						}
						else if (wpType1.equalsIgnoreCase("Solids"))
						{
							solids = (xx * 3.5f) / mk;
						}
						else if (wpType1.equalsIgnoreCase("Nutrients"))
						{
							nutrients = (xx * 3.5f) / mk;
						}
						else if (wpType1.equalsIgnoreCase("Special"))
						{
							special = (xx * 3.5f) / mk;
						}
					}

					stp_bod = 16.15f; // comparing with 30
					stp_ss = 11.54f; // comparing with 30
					stp_ong = 2.31f; // comparing with 30
				}

			}
			else if (etp == 1 && stp == 0)
			{

				xx = 100f; // Total marks for ETP
				mk = 0f; // factor

				// Parameters of ETP
				organic = 65f; // comparing with 70
				metals = 20f; // comparing with 70
				solids = 5f; // comparing with 70
				nutrients = 5f; // comparing with 70
				special = 5f; // comparing with 70

				waterMarkTypeList = waterMarksServices.getAllwaterMarksByMainType(isEtp);

				String wpType = null;
				if (!Validator.isEmpty(waterMarkTypeList))
				{

					for (int i = 0; i < waterMarkTypeList.size(); i++)
					{
						wpType = waterMarkTypeList.get(i);

						if (wpType.equalsIgnoreCase("Organics"))
						{
							mk += organic;
						}
						else if (wpType.equalsIgnoreCase("Metals"))
						{
							mk += metals;
						}
						else if (wpType.equalsIgnoreCase("Solids"))
						{
							mk += solids;
						}
						else if (wpType.equalsIgnoreCase("Nutrients"))
						{
							mk += nutrients;
						}
						else if (wpType.equalsIgnoreCase("Special"))
						{
							mk += special;
						}
					}
				}

				if (!Validator.isEmpty(waterMarkTypeList))
				{
					String wpType12 = null;
					for (int i = 0; i < waterMarkTypeList.size(); i++)
					{
						wpType12 = waterMarkTypeList.get(i);

						if (wpType12.equalsIgnoreCase("Organics"))
						{
							organic = (xx * 45.5f) / mk;
						}
						else if (wpType12.equalsIgnoreCase("Metals"))
						{
							metals = (xx * 14f) / mk;
						}
						else if (wpType12.equalsIgnoreCase("Solids"))
						{
							solids = (xx * 3.5f) / mk;
						}
						else if (wpType12.equalsIgnoreCase("Nutrients"))
						{
							nutrients = (xx * 3.5f) / mk;
						}
						else if (wpType12.equalsIgnoreCase("Special"))
						{
							special = (xx * 3.5f) / mk;
						}
					}
				}

			}
			else if (etp == 0 && stp == 1)
			{
				yy = 100f; // Total marks for STP
				stp_bod = 53.85f; // comparing with 100
				stp_ss = 38.45f; // comparing with 100
				stp_ong = 7.7f; // comparing with 100
			}

			float mk1 = 0f, z = 0f;

			float cod = 0.0f, bod = 0.0f, ong = 0.0f, cr3 = 0.0f, cr6 = 0.0f, fe = 0.0f, zn = 0.0f, cd = 0.0f,
					pb = 0.0f, hg = 0.0f, as = 0.0f, ni = 0.0f, na = 0.0f, al = 0.0f, mn = 0.0f, cu = 0.0f, tss = 0.0f,
					tds = 0.0f, cl = 0.0f, so4 = 0.0f, f = 0.0f, nh4 = 0.0f, no3 = 0.0f, no2 = 0.0f, po4 = 0.0f,
					detergent = 0.0f, phenol = 0.0f, tcoli = 0.0f, fcoli = 0;

			for (int i = 0; i < waterMarkTypeList.size(); i++)
			{

				String wpType = waterMarkTypeList.get(i);
				waterMarkDatas = waterMarksServices.getWaterMarksDataByTreatmentAndwmType("ETP", wpType);

				// this calculation is for water mark only
				if (!Validator.isEmpty(waterMarkDatas))
				{

					for (int j = 0; j < waterMarkDatas.size(); j++)
					{
						item = waterMarkDatas.get(j).getItems();
						marks = waterMarkDatas.get(j).getMarks();

						if (item.equalsIgnoreCase("COD"))
						{
							cod = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Biological Oxygen Demand (BOD)"))
						{
							bod = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("O&G"))
						{
							ong = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Cr+3"))
						{
							cr3 = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Cr+6"))
						{
							cr6 = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Fe"))
						{
							fe = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Zn"))
						{
							zn = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Cd"))
						{
							cd = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Pb"))
						{
							pb = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Hg"))
						{
							hg = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("As"))
						{
							as = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Ni"))
						{
							ni = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Na"))
						{
							na = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Al"))
						{
							al = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Mn"))
						{
							mn = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Cu"))
						{
							cu = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Total Suspended Solids"))
						{
							tss = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Total Dissolved Solids"))
						{
							tds = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Cl"))
						{
							cl = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("SO4"))
						{
							so4 = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("F"))
						{
							f = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("NH4-N"))
						{
							nh4 = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("NO3"))
						{
							no3 = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("NO2"))
						{
							no2 = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("PO4"))
						{
							po4 = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Detergents"))
						{
							detergent = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("Phenol"))
						{
							phenol = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("T.Coli"))
						{
							tcoli = (70 * marks) / 100;
						}
						if (item.equalsIgnoreCase("F.Coli"))
						{
							fcoli = (70 * marks) / 100;
						}

					}

				}

				// this calculation if for pollutant in waterie_pollutant
				// getWaterMarksDataByForWateriePoll
				waterMarkWateriePollDatas = waterMarksServices.getWaterMarksDataByForWateriePoll("ETP", wpType);

				if (!Validator.isEmpty(waterMarkWateriePollDatas))
				{

					for (int j = 0; j < waterMarkWateriePollDatas.size(); j++)
					{
						String itemmm = waterMarkWateriePollDatas.get(j).getItems();

						if (itemmm.equalsIgnoreCase("COD"))
						{
							mk1 += cod;
						}
						if (itemmm.equalsIgnoreCase("Biological Oxygen Demand (BOD)"))
						{
							mk1 += bod;
						}
						if (itemmm.equalsIgnoreCase("O&G"))
						{
							mk1 += ong;
						}
						if (itemmm.equalsIgnoreCase("Cr+3"))
						{
							mk1 += cr3;
						}
						if (itemmm.equalsIgnoreCase("Cr+6"))
						{
							mk1 += cr6;
						}
						if (itemmm.equalsIgnoreCase("Fe"))
						{
							mk1 += fe;
						}
						if (itemmm.equalsIgnoreCase("Zn"))
						{
							mk1 += zn;
						}
						if (itemmm.equalsIgnoreCase("Cd"))
						{
							mk1 += cd;
						}
						if (itemmm.equalsIgnoreCase("Pb"))
						{
							mk1 += pb;
						}
						if (itemmm.equalsIgnoreCase("Hg"))
						{
							mk1 += hg;
						}
						if (itemmm.equalsIgnoreCase("As"))
						{
							mk1 += as;
						}
						if (itemmm.equalsIgnoreCase("Ni"))
						{
							mk1 += ni;
						}
						if (itemmm.equalsIgnoreCase("Na"))
						{
							mk1 += na;
						}
						if (itemmm.equalsIgnoreCase("Al"))
						{
							mk1 += al;
						}
						if (itemmm.equalsIgnoreCase("Mn"))
						{
							mk1 += mn;
						}
						if (itemmm.equalsIgnoreCase("Cu"))
						{
							mk1 += cu;
						}
						if (itemmm.equalsIgnoreCase("Total Suspended Solids"))
						{
							mk1 += tss;
						}
						if (itemmm.equalsIgnoreCase("Total Dissolved Solids"))
						{
							mk1 += tds;
						}
						if (itemmm.equalsIgnoreCase("Cl"))
						{
							mk1 += cl;
						}
						if (itemmm.equalsIgnoreCase("SO4"))
						{
							mk1 += so4;
						}
						if (itemmm.equalsIgnoreCase("F"))
						{
							mk1 += f;
						}
						if (itemmm.equalsIgnoreCase("NH4-N"))
						{
							mk1 += nh4;
						}
						if (itemmm.equalsIgnoreCase("NO3"))
						{
							mk1 += no3;
						}
						if (itemmm.equalsIgnoreCase("NO2"))
						{
							mk1 += no2;
						}
						if (itemmm.equalsIgnoreCase("PO4"))
						{
							mk1 += po4;
						}
						if (itemmm.equalsIgnoreCase("Detergents"))
						{
							mk1 += detergent;
						}
						if (itemmm.equalsIgnoreCase("Phenol"))
						{
							mk1 += phenol;
						}
						if (itemmm.equalsIgnoreCase("T.Coli"))
						{
							mk1 += tcoli;
						}
						if (itemmm.equalsIgnoreCase("F.Coli"))
						{
							mk1 += fcoli;
						}
					}
				}

				if (!Validator.isEmpty(waterMarkWateriePollDatas))
				{

					for (int j = 0; j < waterMarkWateriePollDatas.size(); j++)
					{
						String itemmm = waterMarkWateriePollDatas.get(j).getItems();
						String Typee = waterMarkWateriePollDatas.get(j).getType();

						if (Typee.equalsIgnoreCase("Organics"))
						{
							if (itemmm.equalsIgnoreCase("COD"))
							{
								cod = (organic * cod) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Biological Oxygen Demand (BOD)"))
							{
								bod = (organic * bod) / mk1;
								bod = Utilities.getFloatpoint(bod, 2);
							}
							if (itemmm.equalsIgnoreCase("O&G"))
							{
								ong = (organic * ong) / mk1;
								ong = Utilities.getFloatpoint(ong, 2);
							}
						}
						else if (Typee.equalsIgnoreCase("Metals"))
						{

							if (itemmm.equalsIgnoreCase("Cr+3"))
							{
								cr3 = (metals * cr3) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Cr+6"))
							{
								cr6 = (metals * cr6) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Fe"))
							{
								fe = (metals * fe) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Zn"))
							{
								zn = (metals * zn) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Cd"))
							{
								cd = (metals * cd) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Pb"))
							{
								pb = (metals * pb) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Hg"))
							{
								hg = (metals * hg) / mk1;
							}
							if (itemmm.equalsIgnoreCase("As"))
							{
								as = (metals * as) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Ni"))
							{
								ni = (metals * ni) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Na"))
							{
								na = (metals * na) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Al"))
							{
								al = (metals * al) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Mn"))
							{
								mn = (metals * mn) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Cu"))
							{
								cu = (metals * cu) / mk1;
							}

						}
						else if (Typee.equalsIgnoreCase("Solids"))
						{
							if (itemmm.equalsIgnoreCase("Total Suspended Solids"))
							{
								tss = (solids * tss) / mk1;
								tss = Utilities.getFloatpoint(tss, 2);
							}
							if (itemmm.equalsIgnoreCase("Total Dissolved Solids"))
							{
								tds = (solids * tds) / mk1;
								tds = Utilities.getFloatpoint(tds, 2);
							}
							if (itemmm.equalsIgnoreCase("Cl"))
							{
								cl = (solids * cl) / mk1;
							}
							if (itemmm.equalsIgnoreCase("SO4"))
							{
								so4 = (solids * so4) / mk1;
							}
							if (itemmm.equalsIgnoreCase("F"))
							{
								f = (solids * f) / mk1;
							}
						}
						else if (Typee.equalsIgnoreCase("Nutrients"))
						{
							// nutrients = (xx * 3.5)/mk;
							if (itemmm.equalsIgnoreCase("NH4-N"))
							{
								nh4 = (nutrients * nh4) / mk1;
							}
							if (itemmm.equalsIgnoreCase("NO3"))
							{
								no3 = (nutrients * no3) / mk1;
							}
							if (itemmm.equalsIgnoreCase("NO2"))
							{
								no2 = (nutrients * no2) / mk1;
							}
							if (itemmm.equalsIgnoreCase("PO4"))
							{
								po4 = (nutrients * po4) / mk1;
							}
						}
						else if (Typee.equalsIgnoreCase("Special"))
						{
							if (itemmm.equalsIgnoreCase("Detergents"))
							{
								detergent = (special * detergent) / mk1;
							}
							if (itemmm.equalsIgnoreCase("Phenol"))
							{
								phenol = (special * phenol) / mk1;
							}
							if (itemmm.equalsIgnoreCase("T.Coli"))
							{
								tcoli = (special * tcoli) / mk1;
							}
							if (itemmm.equalsIgnoreCase("F.Coli"))
							{
								fcoli = (special * fcoli) / mk1;
							}
						}
					}
				}

			} // END main for loop

			float ww = 0f, www = 0f;

			wateriePollDatas = wateriePollutantServices.getWateriePollWaterMarkData();

			if (!Validator.isEmpty(wateriePollDatas))
			{

				for (Object[] wateriePollData : wateriePollDatas)
				{

					String typeeeee = (String) wateriePollData[0];
					String itemm = (String) wateriePollData[1];
					float quantity = (float) wateriePollData[2];

					float fact = quantity * 0.1f;
					quantity = quantity + fact;
					quantity = Utilities.getFloatpoint(quantity, 2);

					if (typeeeee.equalsIgnoreCase("Organics"))
					{
						if (itemm.equalsIgnoreCase("COD"))
						{
							ww = cod / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Biological Oxygen Demand (BOD)"))
						{
							ww = bod / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("O&G"))
						{
							ww = ong / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
					}
					else if (typeeeee.equalsIgnoreCase("Metals"))
					{
						if (itemm.equalsIgnoreCase("Cr+3"))
						{
							ww = cr3 / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Cr+6"))
						{
							ww = cr6 / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Fe"))
						{
							ww = fe / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Zn"))
						{
							ww = zn / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Cd"))
						{
							ww = cd / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Pb"))
						{
							ww = pb / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Hg"))
						{
							ww = hg / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("As"))
						{
							ww = as / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Ni"))
						{
							ww = ni / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Na"))
						{
							ww = na / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Al"))
						{
							ww = al / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Mn"))
						{
							ww = mn / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Cu"))
						{
							ww = cu / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
					}
					else if (typeeeee.equalsIgnoreCase("Solids"))
					{
						if (itemm.equalsIgnoreCase("Total Suspended Solids"))
						{
							ww = tss / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Total Dissolved Solids"))
						{
							ww = tds / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Cl"))
						{
							ww = cl / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("SO4"))
						{
							ww = so4 / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("F"))
						{
							ww = f / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
					}
					else if (typeeeee.equalsIgnoreCase("Nutrients"))
					{
						if (itemm.equalsIgnoreCase("NH4-N"))
						{
							ww = nh4 / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("NO3"))
						{
							ww = no3 / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("NO2"))
						{
							ww = no2 / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("PO4"))
						{
							ww = po4 / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
					}
					else if (typeeeee.equalsIgnoreCase("Special"))
					{
						if (itemm.equalsIgnoreCase("Detergents"))
						{
							ww = detergent / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("Phenol"))
						{
							ww = phenol / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("T.Coli"))
						{
							ww = tcoli / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
						if (itemm.equalsIgnoreCase("F.Coli"))
						{
							ww = fcoli / quantity;
							ww = Utilities.getFloatpoint(ww, 2);
						}
					}

				}

				finalETP = (www);

			}

			if (isEtp.equalsIgnoreCase("ETP"))
			{

				List<String> sampleDateList = new ArrayList<>();

				if (forYear)
				{
					sampleDateList = regEffPollServices.getRegEffPollDates(today, yearValue, recordCount);
				}
				if (forMonth)
				{
					sampleDateList = regEffPollServices.getRegEffPollDates(today, yearValue, monthValue, recordCount);
				}
				if (forDay)
				{
					sampleDateList = regEffPollServices.getRegEffPollDates(today, recordCount);
				}

				int temp = 0;
				Float regEffOuCons = 0.0f;
				if (!Validator.isEmpty(sampleDateList))
				{
					for (int i = 0; i < sampleDateList.size(); i++)
					{
						String sDate = sampleDateList.get(i);

						if (!Validator.isEmpty(wateriePollDatas))
						{
							for (Object[] wateriePollData : wateriePollDatas)
							{
								String itemm = (String) wateriePollData[1];
								int teempp = 0;
								// regEffOuCons = regEffPollServices.getRegEffPollouConsEDatePollName(itemm, sDate);
								if (regEffOuCons == null)
								{
									regEffOuCons = 0.0f;
								}
								www += (regEffOuCons * ww);
								// System.out.println("jemsss >>>>> sample for date >"+sDate+" and teempp =
								// "+teempp);
								teempp++;
							}
						}
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("treatmentType", new String("ETP"));
						hashMap.put("sampleDate", new String(sDate));
						hashMap.put("finalValue", new Float(www));
						jsonArray.put(hashMap);
					}
				}
				else
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("treatmentType", new String("ETP"));
					hashMap.put("sampleDate", new String(""));
					hashMap.put("finalValue", new Float(0.0f));
					jsonArray.put(hashMap);
				}

			}

			if (isStp.equalsIgnoreCase("STP"))
			{

				List<String> waterMarksStpList = new ArrayList<>();

				// Parameters of STP
				float stp_mk = 0.0f, countSTP = 0f, stp_bod2 = 0f, stp_ss2 = 0f;
				stp_ong = 0f;

				String sewItem = "";
				waterMarksStpList = waterMarksServices.getWaterMarksDataByForWaterSewPollForStp();

				if (!Validator.isEmpty(waterMarksStpList))
				{

					for (int i = 0; i < waterMarksStpList.size(); i++)
					{

						sewItem = waterMarksStpList.get(i);

						if (sewItem.equalsIgnoreCase("Biological Oxygen Demand (BOD)"))
						{
							stp_mk += stp_bod;
						}
						else if (sewItem.equalsIgnoreCase("SS"))
						{
							stp_mk += stp_ss;
						}
						else if (sewItem.equalsIgnoreCase("O&G"))
						{
							stp_mk += stp_ong;
						}

					}

					for (int i = 0; i < waterMarksStpList.size(); i++)
					{

						sewItem = waterMarksStpList.get(i);

						if (sewItem.equalsIgnoreCase("Biological Oxygen Demand (BOD)"))
						{
							stp_bod = (yy * stp_bod) / stp_mk;
						}
						else if (sewItem.equalsIgnoreCase("SS"))
						{
							stp_ss = (yy * stp_ss) / stp_mk;
						}
						else if (sewItem.equalsIgnoreCase("O&G"))
						{
							stp_ong = (yy * stp_ong) / stp_mk;
						}
					}

					List<String> sampleDateStpList = new ArrayList<>();
					if (forYear)
					{
						sampleDateStpList = regSewPollServices.getRegSewPollDates(today, yearValue, recordCount);
					}

					if (forMonth)
					{
						sampleDateStpList = regSewPollServices.getRegSewPollDates(today, yearValue, monthValue,
								recordCount);
					}

					if (forDay)
					{
						sampleDateStpList = regSewPollServices.getRegSewPollDates(today, recordCount);
					}
					if (!Validator.isEmpty(sampleDateStpList))
					{
						for (int i = 0; i < sampleDateStpList.size(); i++)
						{
							String sewSampDate = sampleDateStpList.get(i);

							if (!Validator.isEmpty(waterMarksStpList))
							{

								for (int j = 0; j < waterMarksStpList.size(); j++)
								{
									Float regSewOuCons = 0.0f;
									sewItem = waterMarksStpList.get(j);
									// regSewOuCons = regSewPollServices.getRegSewPollouConsEDatePollName(sewItem,sewSampDate);

									if (regSewOuCons == null)
										regSewOuCons = 0.0f;

									www += (regSewOuCons * ww);
								}
							}
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("treatmentType", new String("STP"));
							hashMap.put("sampleDate", new String(sewSampDate));
							hashMap.put("finalValue", new Float(www));
							jsonArray.put(hashMap);

						}
					}
					else
					{
						// no matching polls for sew poll and water mark
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("treatmentType", new String("STP"));
						hashMap.put("sampleDate", new String(""));
						hashMap.put("finalValue", new Float(0.0f));
						jsonArray.put(hashMap);
					}

				}

			}
			// getWateriePollWaterMarkData
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return jsonArray.toString();
	}
}
