package com.tes.controller.environmentalofficer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tes.model.DirectUseOfWater;
import com.tes.model.EmpData;
import com.tes.model.FilterUse;
import com.tes.model.Prefilter;
import com.tes.model.RegDirectUseOfWaterData;
import com.tes.model.RegFiltersUseData;
import com.tes.model.RegMultipleFilterData;
import com.tes.model.RegPrefilter;
import com.tes.model.RegWastewaterRecycle;
import com.tes.model.RegWaterSourceData;
import com.tes.model.RegularTreatmentData;
import com.tes.services.FilterUseNameServices;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.MultipleFilterServices;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegPrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.RegWastewaterRecycleServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.WasteWaterRecycleSevices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class used to data quality of statistics data and water filter data
 * quality statistics data and then check compliance and Non-compliancee value.
 * 
 * @author Jemish Moradiya
 * @author Vishal Gabani
 */
@RestController
@RequestMapping(value = {"/env", "/management"})
public class StatisticsController
{

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	RegMultipleFilterDataServices regularFiltersDataServices;

	@Autowired
	RegFiltersUseDataServices regularFiltersUseDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWater;

	@Autowired
	RegDirectUseOfWaterDataServices regDirectUseOfWaterDataServices;

	@Autowired
	MultipleFilterServices multipleFilterServices;

	@Autowired
	RegMultipleFilterDataServices regMultipleFilterDataServices;

	@Autowired
	FilterUseNameServices filterUseNameServices;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	RegFiltersUseDataServices regFiltersUseDataServices;

	@Autowired
	PrefilterServices prefilterServices;

	@Autowired
	RegPrefilterServices regPrefilterServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	@Autowired
	WasteWaterRecycleSevices wasteWaterRecycleSevices;

	@Autowired
	RegWastewaterRecycleServices regWastewaterRecycleServices;

	private static final Logger LOGGER = LogManager.getLogger(StatisticsController.class);

	/**
	 * This method used to get the statistics data.
	 * 
	 * @param statiticsName the name of the statistics name.
	 * @param request the servlet request we are processing.
	 * @return Statitics data
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public ModelAndView getStatitics(@RequestParam("statisticsfor") String statiticsName, HttpServletRequest request)
	{

		ModelAndView modelAndView;
		modelAndView = new ModelAndView();
		try
		{
			int consentMinYear = 0;
			List<String> allUnits = new ArrayList<>();
			List<Integer> consentYears = new ArrayList<>();

			byte[] valueDecoded = Base64.decodeBase64(statiticsName);
			String decodedStatiticsName = new String(valueDecoded);

			modelAndView.setViewName("EnvrOfficer/Statitics");

			String dateToSend[] = Utilities.getTodaysDate().split("-");
			int currentYear = Integer.parseInt(dateToSend[0]);

			try
			{
				consentMinYear = regularDataServices.regDataMinYear();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
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

			try
			{
				// allUnits = allProductsServices.findGetunitByProductType(decodedStatiticsName);
				allUnits = allProductsServices.findOnlyUnit(decodedStatiticsName);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			modelAndView.addObject("statiticsName", decodedStatiticsName);
			modelAndView.addObject("consentYears", consentYears);
			modelAndView.addObject("allUnits", allUnits);
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to get all individual product data reading of unit.
	 * 
	 * @param action it return get each data.
	 * @param type the type of product.
	 * @return jsonArray it returns String value of json array.
	 */
	@RequestMapping(value = {"ajax-getEachData"})
	@ResponseBody
	public @JsonRawValue String GetEachData(@RequestParam(value = "action") String action,
			@RequestParam(value = "type") String type)
	{

		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			String unit = null;
			if (action.equalsIgnoreCase("getUnits"))
			{

				// List<String> allUnits = new ArrayList<>();
				List<String> allUnits1 = new ArrayList<>();
				// allUnits = allProductsServices.findGetunitByProductType(type);
				allUnits1 = allProductsServices.findOnlyUnit(type);
				if (!Validator.isEmpty(allUnits1))
				{
					// for (String allUnit : allUnits)
					// {
					// unit = allUnit;
					// HashMap<String, Object> hashMap = new HashMap<String, Object>();
					// hashMap.put("unit", new String(unit));
					// jsonArray.put(hashMap);
					// }

					for (int i = 0; i < allUnits1.size(); i++)
					{
						unit = allUnits1.get(i);
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("unit", new String(unit));
						jsonArray.put(hashMap);

					}

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
	 * This method used to get the statistic graph data.
	 * 
	 * @param action it return get graph data
	 * @param type the type of product
	 * @param pdata the today date
	 * @param units the unit of product
	 * @return data it return statistic graph data
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping(value = {"ajax-getGraphData"})
	@ResponseBody
	public @JsonRawValue List<Object> GetGraphData(@RequestParam(value = "action") int action,
			@RequestParam(value = "type") String type, @RequestParam(value = "pdata") String pdata,
			@RequestParam(value = "units") String units) throws ParseException
	{

		List<Object> data;
		data = new ArrayList<>();
		try
		{
			List<String> allProducts = new ArrayList<>();
			ArrayList<Object> series = new ArrayList<>();

			allProducts = allProductsServices.findOneByProductTypeUnits(type, units, pdata);

			String today_date[] = pdata.split("-");
			int s_year = Integer.parseInt(today_date[0]);
			int s_month = Integer.parseInt(today_date[1]);
			int s_day = Integer.parseInt(today_date[2]);

			if (!Validator.isEmpty(allProducts))
			{
				ArrayList<String> daysList = new ArrayList<>();
				daysList.add("Days");
				daysList.addAll(Utilities.getDaysList(pdata, 30));
				data.add(daysList);
			}

			if (!Validator.isEmpty(allProducts))
			{
				for (int i = 0; i < allProducts.size(); i++)
				{
					if (!allProducts.get(i).equalsIgnoreCase("NA"))
					{
						String productName = allProducts.get(i);
						Float eachDay = allProductsServices.findOneByGetConsentData(productName, pdata);
						String res[] = units.split("/");
						if (eachDay == null)
							eachDay = 0.0f;
						if (eachDay > 0)
						{
							eachDay = Utilities.convertDataToPerDayByUnit(units, eachDay);
						}

						// ADD CONSENTED VALUE TO ARRAY
						series = new ArrayList<>();
						series.add("consent value " + productName);
						for (int days = 0; days <= 29; days++)
						{
							series.add(eachDay);
						}
						data.add(series);

						// ADD 30 DAYS VALUE
						series = new ArrayList<>();
						series.add(productName + " in " + res[0] + "/Day");

						for (int days = 0; days <= 29; days++)
						{
							Calendar cal = new GregorianCalendar(s_year, s_month - 1, s_day);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							cal.add(Calendar.DAY_OF_WEEK, -days);
							String date = sdf.format(cal.getTime());

							Float quantity = regularDataServices.quantityByProductNameDate(productName, date);

							if (quantity == null)
								quantity = 0.0f;
							series.add(quantity);
						}
						data.add(series);
					}

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
	 * This method is not used
	 * 
	 * @param type the type of product
	 * @param pdata the product data
	 * @return CountOfProduct
	 */
	@RequestMapping(value = {"ajax-getCountOfProduct"})
	@ResponseBody
	public @JsonRawValue int getCountOfProduct(@RequestParam(value = "type") String type,
			@RequestParam(value = "pdata") String pdata)
	{

		int fetchedResult;
		List<Object[]> allProductsLists;
		pdata = Utilities.DateFormat(pdata);
		fetchedResult = 0;
		allProductsLists = new ArrayList<>();
		try
		{
			if (type.equalsIgnoreCase("hwp"))
			{
				type = "hwp";
			}
			else if (type.equalsIgnoreCase("hwpcf"))
			{
				type = "hwpcf";
			}
			else if (type.equalsIgnoreCase("nhwp"))
			{
				type = "nhwp";
			}
			else if (type.equalsIgnoreCase("nhwpcf"))
			{
				type = "nhwpcf";
			}
			else if (type.equalsIgnoreCase("raw"))
			{
				type = "raw material";
			}

			allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(type, pdata);
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return fetchedResult = allProductsLists.size();
	}

	/**
	 * This method used to get product data quality of statistics values
	 * 
	 * @param action it return statistical analysis data & Its type.
	 * @param type the type of product.
	 * @param today the current date of statistical data.
	 * @param prevDate the previous date of statistical data.
	 * @param request the request object of the HttpServletRequest.
	 * @return jsonArray the array value at the specified position in this array
	 * @throws JsonProcessingException while converting Entity into string
	 * @throws JSONException indicates that some exception happened during
	 *         JSON processing.
	 * @throws ParseException if parsing causes an error
	 */
	// stats
	@RequestMapping("/ajax-statisticsValues")
	public @ResponseBody @JsonRawValue String getStatisticsValues(
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "today", required = false) String today,
			@RequestParam(value = "prevDate", required = false) String prevDate, HttpServletRequest request)
			throws JsonProcessingException, JSONException, ParseException
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			String jsonString1 = null;
			today = Utilities.DateFormat(today);
			String splitToday[] = today.split("-");
			int year = Integer.parseInt(splitToday[0]);

			if (action.equalsIgnoreCase("statistical_analysis"))
			{

				boolean flag = false;
				float minValue = 0, maxValue = 0, avgValue = 0, standardDeviation = 0, percentile = 0;
				int consentId = 0;

				String productName = null, units = null, percentileSend = null;

				List<Object[]> staticDatas = new ArrayList<>();
				List<Object[]> allProductsLists = new ArrayList<>();
				List quantity = new ArrayList<>();

				allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(type, today);

				if (!Validator.isEmpty(allProductsLists))
				{

					for (Object[] allProductsListData : allProductsLists)
					{

						productName = (String) allProductsListData[1];
						units = (String) allProductsListData[2];

						String a[] = units.split("/");
						String unitp = a[0] + "/Day";

						quantity = regularDataServices.getRegularQuantityBetween(productName, prevDate, today);
						int quantityListSize = quantity.size();
						if (Validator.isEmpty(quantity))
						{
							flag = true;
							percentileSend = percentile + " " + unitp;
						}
						else
						{
							flag = false;

							minValue = Utilities.round(Float.valueOf(Collections.min(quantity).toString()), 1);
							// System.out.println("minValue" + minValue);
							maxValue = Utilities.round(Float.valueOf(Collections.max(quantity).toString()), 1);

							float total = 0;
							for (int i = 0; i < quantity.size(); i++)
							{
								total += (float) quantity.get(i);
							}
							avgValue = Utilities.round((total / quantityListSize), 1);

							float value = (float) (0.98 * quantityListSize);
							int val1 = Math.round(value) - 1;

							standardDeviation = Utilities.round(stats_standard_deviation(quantity, avgValue), 1);
							percentile = Utilities.round((float) quantity.get(val1), 1);
							percentileSend = percentile + " " + unitp;
						}

						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("product_name", new String(productName));
						hashMap.put("min_value", new Float(minValue));
						hashMap.put("max_value", new Float(maxValue));
						hashMap.put("avg_value", new Float(avgValue));
						hashMap.put("sd", new Float(standardDeviation));
						hashMap.put("percentile", new String(percentileSend));
						jsonArray.put(hashMap);

						if (flag)
						{
							quantity.clear();
						}
					}
				}
				else
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("product_name", new String("NA"));
					hashMap.put("min_value", new String("NA"));
					hashMap.put("max_value", new String("NA"));
					hashMap.put("avg_value", new String("NA"));
					hashMap.put("sd", new String("NA"));
					hashMap.put("percentile", new String("NA"));
					jsonArray.put(hashMap);
				}

			}
			else if (action.equalsIgnoreCase("daily_data"))
			{

				boolean flag = false;
				int no_days = 0, total_days = 364, consentId = 0;
				String productType = null;
				float no = 0;
				List<Object[]> regularDatasList = new ArrayList<>();
				List<Object[]> regularQuantitys = new ArrayList<>();
				EmpData empDataSession = null;
				try
				{
					empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				int companyId = empDataSession.getCompanyProfile().getCompanyProfileId();

				no_days = allProductsServices.getNoWorkDays(companyId);
				total_days = no_days * 52;
				productType = Utilities.getFullNameOfProduct(type);
				regularDatasList = allProductsServices.findOneByGetProductIdUnits(productType, prevDate, today);

				float quantityDiffrence = 0;
				float w = 0;
				float quantity = 0;
				String warning = null;
				String inputDate = null;

				if (!Validator.isEmpty(regularDatasList))
				{

					for (Object[] regularData : regularDatasList)
					{
						String productName = (String) regularData[0];
						String units = (String) regularData[1];
						String a[] = units.split("/");
						String unitp = a[0] + "/Day";

						Float consentQuantity = 0.0f;
						consentQuantity = allProductsServices.findOneByGetConsentData(productName, today);
						if (consentQuantity == null)
							consentQuantity = 0.0f;

						consentQuantity = Utilities.convertDataToPerDayByUnit(units, consentQuantity);

						regularQuantitys = regularDataServices.findOneByGetQuantityInpdate(productName, prevDate, today);

						for (Object[] regularQuantity : regularQuantitys)
						{
							float rQuentity = Utilities.round((float) regularQuantity[0], 2);
							inputDate = (String) regularQuantity[1];
							if (consentQuantity > rQuentity)
							{
								quantityDiffrence = Utilities.round(((float) (consentQuantity - rQuentity)), 1);
								warning = "Production was <br>less by " + quantityDiffrence + " " + unitp;
							}
							else if (consentQuantity < rQuentity)
							{
								quantityDiffrence = Utilities.round(((float) (rQuentity - consentQuantity)), 1);
								warning = "Production was <br>more by " + quantityDiffrence + " " + unitp;
							}
							else
							{
								warning = "--";
							}
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("product_name", new String(productName));
							hashMap.put("input_date", new String(inputDate));
							hashMap.put("quantity", new Float(rQuentity));
							hashMap.put("unitp", new String(unitp));
							hashMap.put("warning", new String(warning));
							jsonArray.put(hashMap);
						}
					}

				}

			}
			else if (action.equalsIgnoreCase("performance_stat"))
			{

				String type_p = null;
				String type_p1 = null;
				boolean flag = false;
				int noCount = 0, consentId = 0;
				double finalValue = 0.0d;
				String productName = null;

				List<Object[]> allProductsLists = new ArrayList<>();

				if (type.equalsIgnoreCase("product"))
				{
					type_p1 = "Product";
					type_p = "Product";
				}
				else if (type.equalsIgnoreCase("byproduct"))
				{
					type_p1 = "byproduct";
					type_p = "byproduct";
				}
				else if (type.equalsIgnoreCase("raw material"))
				{
					type_p1 = "Raw Material";
					type_p = "Raw Material";
				}
				else if (type.equalsIgnoreCase("fuel"))
				{
					type_p1 = "Fuel";
					type_p = "Fuel";
				}
				else if (type.equalsIgnoreCase("hwp"))
				{
					type_p1 = "hwp";
					type_p = "Hazardous Waste From Process";
				}
				else if (type.equalsIgnoreCase("hwpcf"))
				{
					type_p1 = "hwpcf";
					type_p = "Hazardous Waste From PCF";
				}
				else if (type.equalsIgnoreCase("nhwp"))
				{
					type_p1 = "nhwp";
					type_p = "Non-Hazardous Waste From Process";
				}
				else if (type.equalsIgnoreCase("nhwpcf"))
				{
					type_p1 = "nhwpcf";
					type_p = "Non-Hazardous Waste From PCF";
				}
				else if (type.equalsIgnoreCase("bio"))
				{
					type_p1 = "Bio-Medical";
					type_p = "Bio-Medical";
				}

				allProductsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(type_p1, today);
				int productNameListsize = allProductsLists.size();
				int dailyInputNumberSum = 0;
				for (Object[] productNameListData : allProductsLists)
				{
					productName = (String) productNameListData[1];
					noCount = allProductsServices.getNumberFromRegularData(productName, prevDate, today);
					dailyInputNumberSum += noCount;
				}
				finalValue = (double) ((dailyInputNumberSum / (double) (productNameListsize * 30)) * 100);

				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("product_name", new String(type_p1));
				hashMap.put("missed_data", new Double(finalValue));
				jsonArray.put(hashMap);

			}
			else if (action.equalsIgnoreCase("water_statistical_analysis"))
			{
				boolean flag = false;
				boolean flag1 = false;
				float minValue = 0, maxValue = 0, avgValue = 0, percentile = 0, standardDeviation = 0;
				String percentileSend = null;

				float minValue_energy = 0, maxValue_energy = 0, avgValue_energy = 0, percentile_energy = 0,
						standardDeviation_energy = 0;
				String percentileSend_energy = null;
				List<Float> actualReadingList = null;
				boolean isIndusrial = false;
				List<RegularTreatmentData> regTreatmentList = null;
				String whereToUse = null;
				ArrayList<String> tital = new ArrayList<>();
				if (type.equalsIgnoreCase("useofwater"))
				{
					// TreeSet<String> directUseList=directUseOfWater.getUsedDirectUseName();
					List<DirectUseOfWater> directUseList = directUseOfWater.getUsedDirectUseNameList();
					if (!Validator.isEmpty(directUseList))
					{
						for (int i2 = 0; i2 < directUseList.size(); i2++)
						{
							String directUse = directUseList.get(i2).getWhereToUse();
							isIndusrial = directUseList.get(i2).isIndustrial();
							actualReadingList = regDirectUseOfWaterDataServices
									.getListOfActualReadingByWhereToUseAndBetweenDate(directUse, prevDate, today);
							if (Validator.isEmpty(actualReadingList))
							{
								flag = true;
								percentileSend = percentile + " CMD";
								flag = true;
								percentile = 0.0f;
								percentileSend = percentile + " CMD";
								minValue = 0.0f;
								maxValue = 0.0f;
								avgValue = 0.f;
								standardDeviation = 0.0f;
							}
							else
							{
								minValue = Utilities.round(Float.valueOf(Collections.min(actualReadingList).toString()),
										1);
								maxValue = Utilities.round(Float.valueOf(Collections.max(actualReadingList).toString()),
										1);

								float total = 0;
								for (int i = 0; i < actualReadingList.size(); i++)
									total += (float) actualReadingList.get(i);
								avgValue = Utilities.round((total / actualReadingList.size()), 1);

								float value = (float) (0.98 * actualReadingList.size());
								int val1 = Math.round(value) - 1;

								standardDeviation = Utilities
										.round(stats_standard_deviation(actualReadingList, avgValue), 1);
								percentile = Utilities.round((float) actualReadingList.get(val1), 1);
								percentileSend = percentile + " CMD";
							}

							if (isIndusrial)
							{
								directUse = "Industrial" + "-" + directUse;
							}
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("product_name", new String(directUse));
							hashMap.put("min_value", new Float(minValue));
							hashMap.put("max_value", new Float(maxValue));
							hashMap.put("avg_value", new Float(avgValue));
							hashMap.put("sd", new Float(standardDeviation));
							hashMap.put("percentile", new String(percentileSend));
							jsonArray.put(hashMap);

							if (flag)
								actualReadingList.clear();
						}
					}
					else
					{
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("product_name", new String("NA"));
						hashMap.put("min_value", new String("NA"));
						hashMap.put("max_value", new String("NA"));
						hashMap.put("avg_value", new String("NA"));
						hashMap.put("sd", new String("NA"));
						hashMap.put("percentile", new String("NA"));
						jsonArray.put(hashMap);
					}
				}
				else if (type.equalsIgnoreCase("waste_water_treatment"))
				{
					List<Float> energyReadingList = null;
					List<String> treatLabelsList = null;
					treatLabelsList = wastewaterTreatmentServices.findAlltreatmentTypeLabel();
					if (!Validator.isEmpty(treatLabelsList))
					{

						for (int i = 0; i < treatLabelsList.size(); i++)
						{
							actualReadingList = regularTreatmentDataServices
									.findActualReadingListByTreatLabelAndDatesBetween(treatLabelsList.get(i), prevDate,
											today);
							energyReadingList = regularTreatmentDataServices
									.findEnergyReadingListByTreatLabelAndDatesBetween(treatLabelsList.get(i), prevDate,
											today);

							if (Validator.isEmpty(actualReadingList))
							{

								flag = true;
								percentile = 0.0f;
								percentileSend = percentile + " CMD";
								minValue = 0.0f;
								maxValue = 0.0f;
								avgValue = 0.f;
								standardDeviation = 0.0f;
							}
							else if (Validator.isEmpty(energyReadingList))
							{
								flag1 = true;
								percentile_energy = 0.0f;
								percentileSend_energy = percentile + " CMD";
								minValue_energy = 0.0f;
								maxValue_energy = 0.0f;
								avgValue_energy = 0.f;
								standardDeviation_energy = 0.0f;

							}
							else
							{
								minValue = Utilities.round(Float.valueOf(Collections.min(actualReadingList).toString()),
										1);
								maxValue = Utilities.round(Float.valueOf(Collections.max(actualReadingList).toString()),
										1);

								minValue_energy = Utilities
										.round(Float.valueOf(Collections.min(energyReadingList).toString()), 1);
								maxValue_energy = Utilities
										.round(Float.valueOf(Collections.max(energyReadingList).toString()), 1);

								float total = 0;
								float total1 = 0;
								for (int j = 0; j < actualReadingList.size(); j++)
									total += (float) actualReadingList.get(j);

								avgValue = Utilities.round((total / actualReadingList.size()), 1);

								float value = (float) (0.98 * actualReadingList.size());
								int val1 = Math.round(value) - 1;

								standardDeviation = Utilities
										.round(stats_standard_deviation(actualReadingList, avgValue), 1);
								percentile = Utilities.round((float) actualReadingList.get(val1), 1);
								percentileSend = percentile + " CMD";

								// energy
								for (int k = 0; k < energyReadingList.size(); k++)
									total1 += (float) energyReadingList.get(k);

								avgValue_energy = Utilities.round((total1 / energyReadingList.size()), 1);

								float value1 = (float) (0.98 * energyReadingList.size());
								int val11 = Math.round(value1) - 1;

								standardDeviation_energy = Utilities
										.round(stats_standard_deviation(energyReadingList, avgValue_energy), 1);
								percentile_energy = Utilities.round((float) energyReadingList.get(val11), 1);
								percentileSend_energy = percentile_energy + " CMD";

							}
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("product_name", new String(treatLabelsList.get(i).toString()));
							hashMap.put("min_value", new Float(minValue));
							hashMap.put("max_value", new Float(maxValue));
							hashMap.put("avg_value", new Float(avgValue));
							hashMap.put("sd", new Float(standardDeviation));
							hashMap.put("percentile", new String(percentileSend));
							hashMap.put("min_value_energy", new Float(minValue_energy));
							hashMap.put("max_value_energy", new Float(maxValue_energy));
							hashMap.put("avg_value_energy", new Float(avgValue_energy));
							hashMap.put("sd_energy", new Float(standardDeviation_energy));
							hashMap.put("percentile_energy", new String(percentileSend_energy));
							jsonArray.put(hashMap);
							if (flag)
								actualReadingList.clear();
							if (flag1)
								energyReadingList.clear();

						}

					}
					else
					{
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("product_name", new String("NA"));
						hashMap.put("min_value", new String("NA"));
						hashMap.put("max_value", new String("NA"));
						hashMap.put("avg_value", new String("NA"));
						hashMap.put("sd", new String("NA"));
						hashMap.put("percentile", new String("NA"));
						hashMap.put("product_name_energy", new String("NA"));
						hashMap.put("min_value_energy", new String("NA"));
						hashMap.put("max_value_energy", new String("NA"));
						hashMap.put("avg_value_energy", new String("NA"));
						hashMap.put("sd_energy", new String("NA"));
						hashMap.put("percentile_energy", new String("NA"));
						jsonArray.put(hashMap);
					}

				}
				else
				{
					List<String> resultList = new ArrayList<>();
					if (type.equalsIgnoreCase("source"))
					{
						resultList = waterSourceServices.getAllWaterSourceName();
					}
					else if (type.equalsIgnoreCase("prefilter"))
					{
						// resultList = multipleFilterServices.getAllFiltersLabels();
						List<Prefilter> preFiliterList = prefilterServices.findAllPrefilters();
						if (!Validator.isEmpty(preFiliterList))
						{
							for (Prefilter prefilters : preFiliterList)
							{
								boolean isPrefilter = prefilters.isPrefilter();
								String waterSource = null;
								waterSource = prefilters.getWaterSource().getSourceName();
								int preFilterId = prefilters.getPrefilterId();
								if (isPrefilter)
								{
									resultList.add("Prefiler-" + waterSource);
								}
							}
						}
					}
					else if (type.equalsIgnoreCase("filter"))
					{
						resultList = multipleFilterServices.getAllFiltersLabels();
					}
					else if (type.equalsIgnoreCase("filteruse"))
					{
						resultList = filterUseServices.findAllFilterUseLabel();

					}
					else if (type.equalsIgnoreCase("waste_water_treatment_use"))
					{
						List<String> treatTypeList = wastewaterTreatmentServices.findAlltreatmentTypeLabel();
						for (String treatType : treatTypeList)
						{
							List<String> recycleTypeList = wasteWaterRecycleSevices.findAllRecycleTypeLabel(treatType);
							if (!Validator.isEmpty(recycleTypeList))
							{
								for (String recycleTo : recycleTypeList)
									resultList.add(treatType + "-" + recycleTo);
							}
						}
					}

					if (!Validator.isEmpty(resultList))
					{
						for (int i = 0; i < resultList.size(); i++)
						{
							if (type.equalsIgnoreCase("source"))
							{
								actualReadingList = regularSourceDataServices
										.findActualReadingListBySourceNameAndDatesBetween(resultList.get(i), prevDate,
												today);
							}
							if (type.equalsIgnoreCase("prefilter"))
							{
								String name = resultList.get(i);
								String[] usename = name.split("-");
								actualReadingList = regPrefilterServices
										.findActualReadingListPrefilterBySourceNameAndDatesBetween(usename[1], prevDate,
												today);
							}
							else if (type.equalsIgnoreCase("filter"))
							{
								actualReadingList = regularFiltersDataServices
										.getActualReadingListByFilterNameDatesBetween(resultList.get(i), prevDate,
												today);
							}
							else if (type.equalsIgnoreCase("filteruse"))
							{
								actualReadingList = regularFiltersUseDataServices
										.getActualReadingListByFilterUseLabelAndDatesBetween(resultList.get(i),
												prevDate, today);

							}
							else if (type.equalsIgnoreCase("waste_water_treatment_use"))
							{
								String name = resultList.get(i);
								String[] recycleTo = name.split("-");
								actualReadingList = regWastewaterRecycleServices
										.getActualReadingListBytreaTypeAndDatesBetween(recycleTo[0], recycleTo[1],
												prevDate, today);
							}

							if (Validator.isEmpty(actualReadingList))
							{
								flag = true;
								percentile = 0.0f;
								percentileSend = percentile + " CMD";
								minValue = 0.0f;
								maxValue = 0.0f;
								avgValue = 0.f;
								standardDeviation = 0.0f;
							}
							else
							{
								minValue = Utilities.round(Float.valueOf(Collections.min(actualReadingList).toString()),
										1);
								maxValue = Utilities.round(Float.valueOf(Collections.max(actualReadingList).toString()),
										1);

								float total = 0;
								for (int j = 0; j < actualReadingList.size(); j++)
									total += (float) actualReadingList.get(j);

								avgValue = Utilities.round((total / actualReadingList.size()), 1);

								float value = (float) (0.98 * actualReadingList.size());
								int val1 = Math.round(value) - 1;

								standardDeviation = Utilities
										.round(stats_standard_deviation(actualReadingList, avgValue), 1);
								percentile = Utilities.round((float) actualReadingList.get(val1), 1);
								percentileSend = percentile + " CMD";
							}
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("product_name", new String(resultList.get(i).toString()));
							hashMap.put("min_value", new Float(minValue));
							hashMap.put("max_value", new Float(maxValue));
							hashMap.put("avg_value", new Float(avgValue));
							hashMap.put("sd", new Float(standardDeviation));
							hashMap.put("percentile", new String(percentileSend));
							jsonArray.put(hashMap);
							if (flag)
								actualReadingList.clear();
						}
					}
					else
					{
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("product_name", new String("NA"));
						hashMap.put("min_value", new String("NA"));
						hashMap.put("max_value", new String("NA"));
						hashMap.put("avg_value", new String("NA"));
						hashMap.put("sd", new String("NA"));
						hashMap.put("percentile", new String("NA"));
						jsonArray.put(hashMap);
					}
				}
			}

			// water performance
			else if (action.equalsIgnoreCase("water_performance_stat"))
			{
				String pDate = Utilities.getDateFromSubstractDays(today, 30);

				switch (type)
				{
					case "source":
						List<String> sourceNameList = waterSourceServices.getAllWaterSourceName();
						if (!Validator.isEmpty(sourceNameList))
						{
							for (String sourceName : sourceNameList)
							{
								Float finalValue = 0.0f;
								HashMap<String, Object> hashMap = new HashMap<String, Object>();
								int countRows = regularSourceDataServices.getCountByRsDateAndSourceName(pDate, today,
										sourceName);
								boolean Metervalue = waterSourceServices.getMetervalue(sourceName);
								if (Metervalue == true)
								{
									finalValue += (float) 50;
								}
								finalValue += ((Utilities.getFloatpoint((countRows / 31.0f) * 100.0f, 2)) / 2);
								hashMap.put("sourceType", sourceName);
								hashMap.put("finalValue", finalValue);
								hashMap.put("hasMeter", Metervalue);
								jsonArray.put(hashMap);
							}
						}
						break;

					case "prefilter":
						List<Prefilter> preFilterList = prefilterServices.findAllPrefilters();
						if (!Validator.isEmpty(preFilterList))
						{
							for (Prefilter preFilter : preFilterList)
							{
								Float finalValue = 0.0f;
								String sourceName = preFilter.getWaterSource().getSourceName();
								int countRows = regPrefilterServices.countRegPreFilterDateBtwDatesBySourceName(pDate, today,
										sourceName);
								boolean Metervalue = preFilter.isMeter();
								if (Metervalue == true)
								{
									finalValue += (float) 50;
								}
								if (preFilter.isPrefilter())
								{
									sourceName = "PreFilter-" + sourceName;
									finalValue += ((Utilities.getFloatpoint((countRows / 31.0f) * 100.0f, 2)) / 2);
									HashMap<String, Object> hashMap = new HashMap<String, Object>();
									hashMap.put("sourceType", sourceName);
									hashMap.put("finalValue", finalValue);
									hashMap.put("hasMeter", Metervalue);
									jsonArray.put(hashMap);
								}
								else
								{
									HashMap<String, Object> hashMap = new HashMap<String, Object>();
									hashMap.put("sourceType", "NA");
									hashMap.put("finalValue", "NA");
									hashMap.put("hasMeter", "NA");
									jsonArray.put(hashMap);
								}
							}
						}
						break;
					case "filter":
						List<String> filterNameList = multipleFilterServices.getAllFiltersLabels();
						if (!Validator.isEmpty(filterNameList))
						{
							for (String filterName : filterNameList)
							{
								Float finalValue = 0.0f;
								int countRows = regularFiltersDataServices.countRegFilterDateBtwDatesByFilterName(pDate,
										today, filterName);
								boolean Metervalue = multipleFilterServices.getMetervaluefilter(filterName);
								if (Metervalue == true)
								{
									finalValue += (float) 50;
								}
								finalValue += ((Utilities.getFloatpoint((countRows / 31.0f) * 100.0f, 2)) / 2);
								HashMap<String, Object> hashMap = new HashMap<String, Object>();
								hashMap.put("sourceType", filterName);
								hashMap.put("finalValue", finalValue);
								hashMap.put("hasMeter", Metervalue);
								jsonArray.put(hashMap);
							}
						}
						break;
					case "filteruse":
						List<FilterUse> filterUseLabels = new ArrayList<>();
						filterUseLabels = filterUseServices.findAll();
						for (FilterUse filterUse : filterUseLabels)
						{
							boolean isActive = filterUse.isActive();
							String filterUseLabel = filterUse.getFilterUseLabel();
							Float finalValue = 0.0f;
							if (isActive)
							{
								int countRows = regularFiltersUseDataServices
										.findCountRegFilterUseByFilterType(filterUseLabel, pDate, today);
								boolean Metervalue = filterUse.isMeter(); // filterUseServices.getMetervalueOfFilterUse(filterName,filterUseName);
								if (Metervalue == true)
								{
									finalValue += (float) 50;
									finalValue += ((Utilities.getFloatpoint((countRows / 31.0f) * 100.0f, 2)) / 2);
									HashMap<String, Object> hashMap = new HashMap<String, Object>();
									hashMap.put("sourceType", filterUseLabel);
									hashMap.put("finalValue", finalValue);
									hashMap.put("hasMeter", Metervalue);
									jsonArray.put(hashMap);
								}
								else
								{
									HashMap<String, Object> hashMap = new HashMap<String, Object>();
									hashMap.put("sourceType", "NA");
									hashMap.put("finalValue", finalValue);
									hashMap.put("hasMeter", Metervalue);
									jsonArray.put(hashMap);
								}
							}

						}
						break;
					case "useofwater":
						List<DirectUseOfWater> directUseLists = new ArrayList<>();
						List<Float> regdirectUse = new ArrayList<>();
						directUseLists = directUseOfWater.getUsedDirectUseNameList();
						for (int i = 0; i < directUseLists.size(); i++)
						{
							Float finalValue = 0.0f;
							String directUse = directUseLists.get(i).getWhereToUse();
							regdirectUse = regDirectUseOfWaterDataServices
									.getListOfActualReadingByWhereToUseAndBetweenDate(directUse, pDate, today);
							int countRows = regdirectUse.size();
							boolean isIndustrial = false;
							boolean Metervalue = directUseLists.get(i).isMeter();
							isIndustrial = directUseLists.get(i).isIndustrial();
							if (Metervalue)
							{
								finalValue += (float) 50;
							}
							if (isIndustrial)
							{
								directUse = "Industrial" + ":" + directUse;
							}
							finalValue += ((Utilities.getFloatpoint((countRows / 31.0f) * 100.0f, 2)) / 2);
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("sourceType", directUse);
							hashMap.put("finalValue", finalValue);
							hashMap.put("hasMeter", Metervalue);
							jsonArray.put(hashMap);
						}
						break;
					case "waste_water_treatment":
						List<String> treatTypeLabelList = null;
						treatTypeLabelList = wastewaterTreatmentServices.findAlltreatmentTypeLabel();
						if (!Validator.isEmpty(treatTypeLabelList))
						{
							for (String treatLabel : treatTypeLabelList)
							{
								Float finalValue = 0.0f;
								boolean Metervalue = true;
								int countRows = regularTreatmentDataServices
										.countRegTreatmentDataDateBtwDatesBytreatLabel(pDate, today, treatLabel);
								finalValue += (float) 50;
								finalValue += ((Utilities.getFloatpoint((countRows / 31.0f) * 100.0f, 2)) / 2);
								HashMap<String, Object> hashMap = new HashMap<String, Object>();
								hashMap.put("sourceType", treatLabel);
								hashMap.put("finalValue", finalValue);
								hashMap.put("hasMeter", Metervalue);
								jsonArray.put(hashMap);
							}
						}
						break;
					case "waste_water_treatment_use":
						List<String> treatTypeList = new ArrayList<>();
						List<String> recycleToList = new ArrayList<>();
						treatTypeList = wasteWaterRecycleSevices.findAllRecycleTypeLabel();
						for (String treatType : treatTypeList)
						{
							List<String> recycleTypeList = wasteWaterRecycleSevices.findAllRecycleTypeLabel(treatType);
							if (!Validator.isEmpty(recycleTypeList))
							{
								for (String recycleTo : recycleTypeList)
								{
									Float finalValue = 0.0f;
									boolean Metervalue = wasteWaterRecycleSevices.getMetervalueOfRecycledTo(treatType,
											recycleTo);
									int countRows = regWastewaterRecycleServices
											.countRegRecycleDataDateBtwDatesBytreatLabel(pDate, today, treatType,
													recycleTo);
									if (Metervalue == true)
									{
										finalValue += (float) 50;
									}
									finalValue += ((Utilities.getFloatpoint((countRows / 31.0f) * 100.0f, 2)) / 2);
									HashMap<String, Object> hashMap = new HashMap<String, Object>();
									hashMap.put("sourceType", treatType + "-" + recycleTo);
									hashMap.put("finalValue", finalValue);
									hashMap.put("hasMeter", Metervalue);
									jsonArray.put(hashMap);
								}

							}
						}

				}

			}

			// Daily data
			else if (action.equalsIgnoreCase("water_daily_data"))
			{
				List<RegWaterSourceData> regularSourceDataList = null;
				List<RegMultipleFilterData> regularFiltersDataList = null;
				List<RegFiltersUseData> regularFiltersUseDataList = null;
				List<RegDirectUseOfWaterData> regularWaterUseDataList = null;
				List<RegularTreatmentData> regulaTreatmentDataList = null;
				List<RegPrefilter> regularPrefilterList = null;
				List<RegWastewaterRecycle> regWastewaterRecycleList = null;
				if (type.equalsIgnoreCase("source"))
				{
					regularSourceDataList = regularSourceDataServices.getAllRegularSourceDataBetweenDates(prevDate,
							today);
					int index = 0;
					if (!Validator.isEmpty(regularSourceDataList))
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						for (RegWaterSourceData regularSourceData : regularSourceDataList)
						{
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("start_reading", regularSourceData.getStartReading());
							jsonObject.put("end_reading", regularSourceData.getEndReading());
							jsonObject.put("actual_reading", regularSourceData.getActualReading() + " CMD");
							jsonObject.put("source_name", regularSourceData.getWaterSource().getSourceName());
							jsonObject.put("input_date", sdf.format(regularSourceData.getCreateDateTime()));
							jsonObject.put("staff", regularSourceData.getStaff());
							jsonArray.put(jsonObject);
						}
					}
				}
				else if (type.equalsIgnoreCase("prefilter"))
				{
					regularPrefilterList = regPrefilterServices.findByPrefilterBetweenTwoDates(prevDate, today);

					if (!Validator.isEmpty(regularPrefilterList))
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						for (RegPrefilter regPrefilter : regularPrefilterList)
						{
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("input_date", sdf.format(regPrefilter.getCreateDateTime()));
							jsonObject.put("source_name",
									"Prefilter-" + regPrefilter.getPrefilter().getWaterSource().getSourceName());
							jsonObject.put("start_reading", regPrefilter.getStartReading());
							jsonObject.put("end_reading", regPrefilter.getEndReading());
							jsonObject.put("actual_reading", regPrefilter.getActualReading() + " CMD");
							jsonArray.put(jsonObject);
						}
					}
				}
				else if (type.equalsIgnoreCase("filter"))
				{
					regularFiltersDataList = regMultipleFilterDataServices.findByFilterBetweenTwoDates(prevDate, today);
					if (!Validator.isEmpty(regularFiltersDataList))
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						for (RegMultipleFilterData regularFiltersData : regularFiltersDataList)
						{
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("start_reading", regularFiltersData.getStartReading());
							jsonObject.put("end_reading", regularFiltersData.getEndReading());
							jsonObject.put("actual_reading", regularFiltersData.getActualReading() + " CMD");
							jsonObject.put("source_name",
									regularFiltersData.getMultipleFilter().getFilters().getFilterType());
							jsonObject.put("filter_type", regularFiltersData.getMultipleFilter().getFilterLabel());
							jsonObject.put("input_date", sdf.format(regularFiltersData.getCreateDateTime()));
							jsonArray.put(jsonObject);
						}
					}
				}
				else if (type.equalsIgnoreCase("filteruse"))
				{
					regularFiltersUseDataList = regularFiltersUseDataServices.findByrfDateBetweenTwoDates(prevDate,
							today);
					if (!Validator.isEmpty(regularFiltersUseDataList))
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						int index = 0;
						for (RegFiltersUseData regularFiltersUseData : regularFiltersUseDataList)
						{
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("start_reading", regularFiltersUseData.getStartReading());
							jsonObject.put("end_reading", regularFiltersUseData.getEndReading());
							jsonObject.put("actual_reading", regularFiltersUseData.getActualReading() + " CMD");
							jsonObject.put("source_name", regularFiltersUseData.getFilterUseLabel());
							jsonObject.put("input_date", sdf.format(regularFiltersUseData.getCreateDateTime()));
							jsonArray.put(jsonObject);
						}
					}
				}
				else if (type.equalsIgnoreCase("useofwater"))
				{
					regularWaterUseDataList = regDirectUseOfWaterDataServices.findByrWDateBetweenTwoDates(prevDate,
							today);
					if (!Validator.isEmpty(regularWaterUseDataList))
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String sourceType = null;
						int index = 0;
						for (RegDirectUseOfWaterData regularWaterUseData : regularWaterUseDataList)
						{
							JSONObject jsonObject = new JSONObject();
							boolean isIndustrial = directUseOfWater
									.getDirectUseisIndustrial(regularWaterUseData.getWhereToUse());
							if (isIndustrial)
							{
								sourceType = "Industrial" + "-" + regularWaterUseData.getWhereToUse();
							}
							else
							{
								sourceType = regularWaterUseData.getWhereToUse();
							}
							jsonObject.put("start_reading", regularWaterUseData.getStartReading());
							jsonObject.put("end_reading", regularWaterUseData.getEndReading());
							jsonObject.put("actual_reading", regularWaterUseData.getActualReading() + " CMD");
							jsonObject.put("source_name", sourceType);
							jsonObject.put("input_date", sdf.format(regularWaterUseData.getCreateDateTime()));
							jsonArray.put(jsonObject);
						}
					}
				}
				else if (type.equalsIgnoreCase("waste_water_treatment"))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					regulaTreatmentDataList = regularTreatmentDataServices
							.findAllRegTreatmentDataBetweenTwoDates(prevDate, today);
					for (RegularTreatmentData regularTreatData : regulaTreatmentDataList)
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("treatType", regularTreatData.getWastewaterTreatment().getTreatmentType());
						jsonObject.put("label", regularTreatData.getWastewaterTreatment().getLabel());
						jsonObject.put("start_reading", regularTreatData.getStartReading());
						jsonObject.put("end_reading", regularTreatData.getEndReading());
						jsonObject.put("actual_reading", regularTreatData.getActualReading() + " CMD");
						jsonObject.put("eng_start_reading", regularTreatData.getEnergyStartReading());
						jsonObject.put("eng_end_reading", regularTreatData.getEnergyEndReading());
						jsonObject.put("eng_avg_reading", regularTreatData.getEnergyAvg());
						jsonObject.put("input_date", sdf.format(regularTreatData.getCreateDateTime()));
						jsonArray.put(jsonObject);
					}
				}
				else if (type.equalsIgnoreCase("waste_water_treatment_use"))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					regWastewaterRecycleList = regWastewaterRecycleServices
							.findAllRegWasteWaterRecycleBetweenTwoDates(prevDate, today);
					for (RegWastewaterRecycle regWastewaterRecycleData : regWastewaterRecycleList)
					{
						JSONObject jsonObject = new JSONObject();
						String sourceName = regWastewaterRecycleData.getWastewaterRecycle().getWastewaterTreatment()
								.getLabel() + "-" + regWastewaterRecycleData.getWastewaterRecycle().getRecycledTo();
						jsonObject.put("start_reading", regWastewaterRecycleData.getStartReading());
						jsonObject.put("end_reading", regWastewaterRecycleData.getEndReading());
						jsonObject.put("actual_reading", regWastewaterRecycleData.getActualReading() + " CMD");
						jsonObject.put("source_name", sourceName);
						jsonObject.put("input_date", sdf.format(regWastewaterRecycleData.getCreateDateTime()));
						jsonArray.put(jsonObject);
					}
				}
			}

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			LOGGER.error(e);
			// e.printStackTrace();
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to calculate the standard deviation
	 * 
	 * @param quantity the quantity of product
	 * @param avg_value the avg value of product
	 * @return standardDeviation / quantity of size
	 */
	private float stats_standard_deviation(List quantity, Float avg_value)
	{
		float standardDeviation;
		standardDeviation = (float) 0.0;
		try
		{
			for (int i = 0; i < quantity.size(); i++)
			{
				Float d = (float) quantity.get(i) - avg_value;
				standardDeviation += d * d;
			}
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return (float) Math.sqrt(standardDeviation / quantity.size());
	}

	/**
	 * This method used to get the water consumption details.
	 * 
	 * @return WaterConsumption
	 */
	// @PreAuthorize("hasAuthority('ROLE_ENVROFFICER')")
	@RequestMapping("/waterConsumption")
	private ModelAndView getWaterConsumptionDetails(@RequestParam("wc") String waterConsumptionfor,
			HttpServletRequest request)
	{
		ModelAndView modelAndView;
		modelAndView = new ModelAndView();
		try
		{
			byte[] valueDecoded = Base64.decodeBase64(waterConsumptionfor);
			String decodedWaterStatiticsName = new String(valueDecoded);

			List<Integer> regSourceYearsArrayList = new ArrayList<>();
			int regSourceDataMinYear = 0;
			int currentYear = Integer.parseInt(Utilities.getTodaysDate().split("-")[0]);

			try
			{
				regSourceDataMinYear = regularSourceDataServices.getRegSourceYearOfLastInput();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			if (regSourceDataMinYear == 0)
			{
			}
			else
			{
				int maxYearDiff = currentYear - regSourceDataMinYear;
				maxYearDiff = maxYearDiff + 1;
				for (int i = 1; i <= maxYearDiff; i++)
				{
					regSourceYearsArrayList.add(currentYear);
					currentYear = currentYear - 1;
				}
			}

			// tab1 water_source
			ArrayList<String> sourceNameArray = new ArrayList<String>();
			for (String sourceName : waterSourceServices.getAllWaterSourceName())
			{// get sourceName
				sourceNameArray.add(Utilities.removeSpace(sourceName));
			}

			// tab2 filters

			ArrayList<String> filterNameArray = new ArrayList<String>();
			ArrayList<String> filterUseArray = new ArrayList<String>();
			for (String filterName : filtersServices.findAllFilterType())
			{
				String tempFilterName = Utilities.removeSpace(filterName);
				filterNameArray.add(tempFilterName);
			}

			modelAndView.setViewName("EnvrOfficer/WaterConsumption");
			modelAndView.addObject("todaysDate", Utilities.getTodaysDate());
			modelAndView.addObject("regSourceYears", regSourceYearsArrayList);
			modelAndView.addObject("sourceName", sourceNameArray);
			modelAndView.addObject("filterName", filterNameArray);
			modelAndView.addObject("filterUse", filterUseArray);
			modelAndView.addObject("WaterStatiticsName", decodedWaterStatiticsName);
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to get each water data.
	 * 
	 * @param action the each water data
	 * @param type The type of product
	 * @param sDate The Start date of water data in statistics data.
	 * @return data
	 * @throws ParseException If parsing causes an error
	 */
	// graph
	@RequestMapping(value = "ajax-get-each-water-data", method = RequestMethod.POST)
	private @ResponseBody List<Object> getEachWaterData(@RequestParam("action") int action,
			@RequestParam("type") String type, @RequestParam("pdata") String sDate) throws ParseException
	{
		List<Object> data;
		data = new ArrayList<>();
		try
		{
			// mywork
			List<String> titalsList = null;
			List<String> sourceNameList = null;
			List<String> titalsList1 = null;
			List<Prefilter> preFiliterList = null;
			List<DirectUseOfWater> useOfWaterList = null;
			String[] splitDate = sDate.split("-");
			ArrayList<String> tital = new ArrayList<>();
			ArrayList<Object> series = new ArrayList<>();
			int s_year = Integer.parseInt(splitDate[0]);
			int s_month = Integer.parseInt(splitDate[1]);
			int s_day = Integer.parseInt(splitDate[2]);
			boolean isIndustrial = false;
			String whereToUse = null;
			String test = "";
			switch (type)
			{
				case "source":
					titalsList = waterSourceServices.getSourceNameByConsentValidUpto(sDate);
					break;
				case "prefilter":
					preFiliterList = prefilterServices.findAllPrefilters();
					if (!Validator.isEmpty(preFiliterList))
					{
						for (Prefilter prefilters : preFiliterList)
						{
							boolean isPrefilter = prefilters.isPrefilter();
							String waterSource = null;
							waterSource = prefilters.getWaterSource().getSourceName();
							int preFilterId = prefilters.getPrefilterId();
							if (isPrefilter)
							{
								tital.add("Prefiler-" + waterSource);
							}

						}
						titalsList = tital;
					}
					break;
				case "filter":
					titalsList1 = multipleFilterServices.getAllFiltersLabels();
					if (!Validator.isEmpty(titalsList1))
					{
						for (String filterName : titalsList1)
						{
							List<String> multipleFilterList = null;
							multipleFilterList = multipleFilterServices.findFilterTypeByMultiFilter(filterName);
							if (!Validator.isEmpty(multipleFilterList))
							{
								for (String filterType : multipleFilterList)
									tital.add(filterType + "-" + filterName);
							}
						}
						titalsList = tital;
					}
					break;
				case "filteruse":
					titalsList = filterUseServices.findAllFilterUseLabel();
					break;
				case "useofwater":
					useOfWaterList = directUseOfWater.getUsedDirectUseNameList();
					for (int i = 0; i < useOfWaterList.size(); i++)
					{
						isIndustrial = useOfWaterList.get(i).isIndustrial();
						whereToUse = useOfWaterList.get(i).getWhereToUse();
						if (isIndustrial)
						{
							tital.add("Industrial" + "-" + whereToUse);
						}
						else
						{
							tital.add(whereToUse);
						}

					}
					titalsList = tital;

					break;
				case "waste_water_treatment":
					titalsList1 = wastewaterTreatmentServices.findAlltreatmentTypeLabel();
					if (!Validator.isEmpty(titalsList1))
					{
						for (String treatType : titalsList1)
						{
							for (int i = 0; i <= 1; i++)
							{
								if (i == 0)
								{
									tital.add(treatType + "-Source Reading");
								}
								else
								{
									tital.add(treatType + "-Energy Reading");
								}

							}
						}

					}
					titalsList = tital;
					break;
				case "waste_water_treatment_use":
					titalsList1 = wasteWaterRecycleSevices.findAllRecycleTypeLabel();
					if (!Validator.isEmpty(titalsList1))
					{
						for (String treatType : titalsList1)
						{
							List<String> recycleTypeList = wasteWaterRecycleSevices.findAllRecycleTypeLabel(treatType);
							if (!Validator.isEmpty(recycleTypeList))
							{
								for (String recylcleTo : recycleTypeList)
									tital.add(treatType + "-" + recylcleTo);

							}

						}
					}
					titalsList = tital;
					break;
			}

			if (!Validator.isEmpty(titalsList))
			{
				series.add("Days");
				for (int days = 0; days <= 29; days++)
				{
					Calendar cal = new GregorianCalendar(s_year, s_month - 1, s_day);
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

			if (!Validator.isEmpty(titalsList))
			{

				for (int i2 = 0; i2 < titalsList.size(); i2++)
				{
					String filname = titalsList.get(i2);
					series = new ArrayList<>();
					series.add(filname);

					int noDay = 0;
					for (int days = 1; days <= 30; days++)
					{
						Float regdata = 0.0f;
						Calendar cal = new GregorianCalendar(s_year, s_month - 1, s_day);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						cal.add(Calendar.DAY_OF_WEEK, -noDay);

						String date = sdf.format(cal.getTime());
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
						String dateToSend[] = date.split("-");
						int day = Integer.parseInt(dateToSend[2]);
						String month = new SimpleDateFormat("MMM").format(date1);

						switch (type)
						{
							case "source":
								regdata = regularSourceDataServices.getActualReadingByRsDateAndSourceName(date,
										titalsList.get(i2).toString());
								break;
							case "prefilter":
								String[] source = ((String) titalsList.get(i2)).split("-");
								regdata = regPrefilterServices.getActualReadingByRsDateAndPrefilter(date, source[1]);

								break;
							case "filter":
								String[] multiFilterData = ((String) titalsList.get(i2)).split("-");
								regdata = regMultipleFilterDataServices.getSumActualReadingByRfDateAndFilterName(date,
										multiFilterData[1], multiFilterData[0]);
								break;

							case "filteruse":
								regdata = regFiltersUseDataServices.sumOfActualReadingByFilterUseLabel(date,
										titalsList.get(i2).toString());
								break;

							case "useofwater":
								String sourceType;
								if (isIndustrial)
								{
									sourceType = titalsList.get(i2).toString().split("-")[1];
								}
								else
								{
									sourceType = titalsList.get(i2).toString();
								}
								regdata = regularWaterUseDataServices.getActualReadingByDateAndSourceType(date, sourceType);

								break;

							case "waste_water_treatment":
								String readingType = titalsList.get(i2).toString().split("-")[1];
								if (readingType.equalsIgnoreCase("Source Reading"))
								{
									regdata = regularTreatmentDataServices.getActualReadingByDateAndTreatmentTypeLabel(date,
											titalsList.get(i2).toString().split("-")[0]);
								}
								else
								{
									regdata = regularTreatmentDataServices.getEnergyReadingByDateAndTreatmentTypeLabel(date,
											titalsList.get(i2).toString().split("-")[0]);
								}
								break;
							case "waste_water_treatment_use":

								String[] recycleTo = ((String) titalsList.get(i2)).split("-");
								regdata = regWastewaterRecycleServices.findActualReadingByRfDatetreatTypeAndrecycledTo(date,
										recycleTo[0], recycleTo[1]);
								break;
						}

						if (regdata == null)
							regdata = 0.0f;

						series.add(regdata);
						noDay++;
					}
					data.add(series);
				}
			}

		}
		catch (Exception e)
		{

			// LOGGER.error(e);
			e.printStackTrace();
		}
		return data;
	}

}
