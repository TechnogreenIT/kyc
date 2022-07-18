package com.tes.controller.management;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tes.controller.base.BaseManagementController;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.WaterConGenComparativeSheetServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
public class AnalyticalGraphsController extends BaseManagementController
{

	private static final Logger LOGGER = LogManager.getLogger(AnalyticalGraphsController.class);

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	RegWaterSourceDataServices regWaterSourceDataServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	WaterConGenComparativeSheetServices waterConGenComparativeSheetServices;

	@PostMapping(value = {"/ajax-analyticalGraph1"})
	public ResponseEntity<?> analyticalGraph1(HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		List<Object> data = new ArrayList<>();

		List<Object> productSeriesData = new ArrayList<>();

		boolean result = false;
		try
		{
			String industrytype = (String) request.getSession(true).getAttribute("sessionIndustryType");

			String todayDate = Utilities.getTodaysDate();
			String nextDate = Utilities.addedDateByDays(todayDate, -30);

			List<String> datesArray = new ArrayList<>();
			datesArray = Utilities.getDatesRangeArray(todayDate, nextDate);

			// if company is industry type > make production vs water graph
			if (industrytype.equalsIgnoreCase("Industry"))
			{
				JSONObject jsonDataObject = new JSONObject();

				ArrayList<Object> series = new ArrayList<>();
				ArrayList<Object> water = new ArrayList<>();

				productSeriesData = makeGraphDataSeries("Product", datesArray, Utilities.getTodaysDate());

				if (!Validator.isEmpty(productSeriesData))
				{
					result = true;

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

					data.addAll(productSeriesData); // adding all daily use graph series

					jsonDataObject.put("graphArray", data);
					jsonDataObject.put("graphTitle", new String("Production VS Water"));
					jsonDataObject.put("unit", new String("CMD"));
				}

				jsonObject.put("result", result);
				jsonObject.put("data", jsonDataObject);
			}
			else
			{

			}
		}
		catch (

		Exception e)
		{
			LOGGER.error(e);
		}
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
	}

	@PostMapping(value = {"/ajax-analyticalGraph2"})
	public ResponseEntity<?> analyticalGraph2(HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		List<Object> data = new ArrayList<>();
		boolean result = false;
		try
		{

			String todayDate = Utilities.getTodaysDate();
			String nextDate = Utilities.addedDateByDays(todayDate, -30);

			List<String> datesArray = new ArrayList<>();
			datesArray = Utilities.getDatesRangeArray(todayDate, nextDate);

			// if company is industry type > make production vs water graph
			JSONObject jsonDataObject = new JSONObject();

			ArrayList<Object> series = new ArrayList<>();
			ArrayList<Object> waterConData = new ArrayList<>();

			List<String> waterSourceNameList = waterSourceServices.getAllWaterSourceData();

			if (!Validator.isEmpty(waterSourceNameList))
			{
				result = true;

				series.add("Days");
				series.addAll(datesArray); // add all dates
				data.add(series); // add dates array to main data

				waterConData.add("water in CMD");

				Float finalCons = waterConGenComparativeSheetServices.getcons();
				if (finalCons == null)
					finalCons = 0.0f;

				for (int i = 0; i < datesArray.size(); i++)
				{
					waterConData.add(finalCons);
				}

				data.add(waterConData);

				for (int i = 0; i < waterSourceNameList.size(); i++)
				{
					String waterSourceName = waterSourceNameList.get(i);

					series = new ArrayList<>();
					series.add(waterSourceName);

					for (int j = 0; j < datesArray.size(); j++)
					{

						Float regWaterUse = regWaterSourceDataServices.getActualReadingByRsDateAndSourceName(datesArray.get(j), waterSourceName);
						if (regWaterUse == null)
							regWaterUse = 0.0f;

						series.add(regWaterUse);
					}
					data.add(series);

				}
				jsonDataObject.put("graphArray", data);
				jsonDataObject.put("graphTitle", new String("Water Consumption"));
				jsonDataObject.put("unit", new String("CMD"));
			}

			jsonObject.put("result", result);
			jsonObject.put("data", jsonDataObject);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
	}

	@PostMapping(value = {"/ajax-analyticalGraph3"})
	public ResponseEntity<?> analyticalGraph3(HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		List<Object> data = new ArrayList<>();
		boolean result = false;

		List<Object> productSeriesData = new ArrayList<>();

		try
		{
			String todayDate = Utilities.getTodaysDate();
			String nextDate = Utilities.addedDateByDays(todayDate, -30);

			List<String> datesArray = Utilities.getDatesRangeArray(todayDate, nextDate);
			JSONObject jsonDataObject = new JSONObject();
			ArrayList<Object> series = new ArrayList<>();

			productSeriesData = makeGraphDataSeries("Fuel", datesArray, Utilities.getTodaysDate());

			if (!Validator.isEmpty(productSeriesData))
			{
				result = true;

				series.add("Days");
				series.addAll(datesArray); // add all dates
				data.add(series);

				data.addAll(productSeriesData); // adding all daily use graph series

				jsonDataObject.put("graphArray", data);
				jsonDataObject.put("graphTitle", new String("Fuel Consumption"));
				jsonDataObject.put("unit", new String("Unit"));
			}

			jsonObject.put("result", result);
			jsonObject.put("data", jsonDataObject);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
	}

	public List<Object> makeGraphDataSeries(String productType, List<String> dateArrayList, String consentDate)
	{
		List<Object> data = new ArrayList<>();
		List<Object[]> productsLists = new ArrayList<>();
		ArrayList<Object> series = new ArrayList<>();

		productsLists = allProductComparativeSheetServices.getAllProductComparativeSheet(productType, consentDate);

		for (Object[] allProductsListData : productsLists)
		{
			String productName = (String) allProductsListData[1];
			String pUnit = (String) allProductsListData[2];
			String unitSplit[] = pUnit.split("/");
			String unit = unitSplit.length > 2 ? pUnit : unitSplit[0];

			series = new ArrayList<>();
			series.add(productName + " in " + unit + "/Day");

			for (int i = 0; i < dateArrayList.size(); i++)
			{
				Float quantity = regularDataServices.quantityByProductNameDate(productName, dateArrayList.get(i));

				if (quantity == null)
					quantity = 0.0f;

				series.add(quantity);
			}
			data.add(series);
		}
		return data;
	}
}
