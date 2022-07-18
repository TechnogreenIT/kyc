package com.tes.controller.environmentalofficer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tes.model.RegWaterSourceData;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class used to get All product data daily, back 7days, monthly, yearly
 * etc. and also get the water data.
 * 
 * @author Tushar Chougule
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/management", "/env"})
public class GetArrayDataController extends Utilities
{

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	RegWaterSourceDataServices regWaterSourceDataServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	private static final Logger LOGGER = LogManager.getLogger(GetArrayDataController.class);

	//// graph new code
	@PostMapping(value = "ajax-getProductionVSWaterGraph")
	public @ResponseBody List<Object> getProductionVSWaterGraph(
			@RequestParam(value = "graphTabName", required = false) String graphTabName,
			@RequestParam(value = "graphDate", required = false) String graphDate) throws ParseException
	{
		List<Object> data = new ArrayList<>();
		ArrayList<Object> series = new ArrayList<>();
		ArrayList<Object> water = new ArrayList<>();
		ArrayList<Object> dates = new ArrayList<>();
		List<Float> getQuantityByRegularIdAndDate = null;
		List<Object[]> productIdAndUnit = null;
		Set<Integer> yearsList = new HashSet<>();
		Float quantity = null;

		int ii1 = 1;
		Float quant = 0.0f;
		String[] monthName = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

		if (graphTabName.equalsIgnoreCase("DailyTab"))
		{
			series.add("Days");
			water.add("water in CMD");
			series.add(getMonthNameFromStringDate(graphDate) + " " + getDayFromStringDate(graphDate));
			water.add(regWaterSourceDataServices.getActualReadingSumForDate(graphDate));
			data.add(series);
			data.add(water);

			productIdAndUnit = allProductNameServices.getProductNameUnitsByProductType();
			for (int i = 0; i < productIdAndUnit.size(); i++)
			{
				series = new ArrayList<>();
				series.add(productIdAndUnit.get(i)[0].toString() + " in " + productIdAndUnit.get(i)[1].toString());
				getQuantityByRegularIdAndDate = regularDataServices
						.getQuantityByIdAndDate(productIdAndUnit.get(i)[0].toString(), graphDate);

				quantity = Validator.isEmpty(getQuantityByRegularIdAndDate) ? 0
						: getQuantityByRegularIdAndDate.get(0).floatValue();

				series.add(quantity);
				data.add(series);
			}
		}
		else if (graphTabName.equalsIgnoreCase("Back7Days"))
		{
			dates = new ArrayList<>();
			int s = 0;
			dates.add("Days");
			water.add("water in CMD");

			int day = getDayFromStringDate(graphDate);
			int month = getMonthFromStringDate(graphDate);
			int year = getYearFromStringDate(graphDate);
			for (int j = 1; j <= 7; j++)
			{
				Float sumOfAllSource = 0.0f;
				Calendar cal = new GregorianCalendar(year, month - 1, day);
				cal.add(Calendar.DAY_OF_WEEK, -s);
				String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
				dates.add(getMonthNameFromStringDate(date) + " " + getDayFromStringDate(date));
				sumOfAllSource = regWaterSourceDataServices.getActualReadingSumForDate(date);
				water.add(sumOfAllSource);
				s++;
			}
			data.add(dates);
			data.add(water);
			productIdAndUnit = allProductNameServices.getProductNameUnitsByProductType();
			for (int i = 0; i < productIdAndUnit.size(); i++)
			{
				s = 0;
				ArrayList<Object> productDetails = new ArrayList<>();
				productDetails
						.add(productIdAndUnit.get(i)[0].toString() + " in " + productIdAndUnit.get(i)[1].toString());
				for (int j = 1; j <= 7; j++)
				{
					Calendar cal = new GregorianCalendar(year, month - 1, day);
					cal.add(Calendar.DAY_OF_WEEK, -s);
					String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

					getQuantityByRegularIdAndDate = regularDataServices
							.getQuantityByIdAndDate(productIdAndUnit.get(i)[0].toString(), date);

					quantity = Validator.isEmpty(getQuantityByRegularIdAndDate) ? 0
							: getQuantityByRegularIdAndDate.get(0).floatValue();

					productDetails.add(quantity);
					s++;
				}
				data.add(productDetails);
			}
		}
		else if (graphTabName.equalsIgnoreCase("Weekly"))
		{
			String firstDate = graphDate.split("-")[0] + "-" + graphDate.split("-")[1] + "-00";
			dates = new ArrayList<>();
			dates.add("Week");
			water.add("Water consume in CMD");
			ArrayList<String> weekArray = new ArrayList<>();
			int weeks = getDayFromStringDate(Utilities.getLastDate(graphDate)) / 7;
			for (int i = 0; i <= weeks; i++)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(firstDate));
				String nextDate = "";
				if (i == 4)
				{
					nextDate = graphDate;
					weekArray.add(firstDate);
					weekArray.add(nextDate);
				}
				else
				{
					c.add(Calendar.DAY_OF_MONTH, 7);
					nextDate = sdf.format(c.getTime());
					weekArray.add(firstDate);
				}
				firstDate = nextDate;
			}

			for (int i = 0; i < weekArray.size() - 1; i++)
			{
				dates.add("Week " + ii1);

				String fWeek = weekArray.get(i);
				int w = Integer.parseInt(fWeek.split("-")[2]) + 1;

				fWeek = w >= 1 && w <= 9 ? fWeek.split("-")[0] + "-" + fWeek.split("-")[1] + "-0" + w
						: fWeek.split("-")[0] + "-" + fWeek.split("-")[1] + "-" + w;

				float wdata = getWaterData(fWeek, weekArray.get(i + 1));
				water.add(wdata);
				ii1++;
			}
			data.add(dates);
			data.add(water);
			productIdAndUnit = allProductNameServices.getProductNameUnitsByProductType();
			for (int j = 0; j < productIdAndUnit.size(); j++)
			{
				ArrayList<Object> productDetails = new ArrayList<>();
				productDetails.add(
						productIdAndUnit.get(j)[0].toString() + " " + "in " + productIdAndUnit.get(j)[1].toString());
				for (int i = 0; i < weekArray.size() - 1; i++)
				{
					String fWeek = weekArray.get(i);
					int w = Integer.parseInt(fWeek.split("-")[2]) + 1;

					fWeek = w >= 1 && w <= 9 ? fWeek.split("-")[0] + "-" + fWeek.split("-")[1] + "-0" + w
							: fWeek.split("-")[0] + "-" + fWeek.split("-")[1] + "-" + w;

					productDetails.add(regularDataServices.getQuantityInBetweendates(
							productIdAndUnit.get(j)[0].toString(), fWeek, weekArray.get(i + 1)));
				}
				data.add(productDetails);
			}
		}
		else if (graphTabName.equalsIgnoreCase("Monthly"))
		{
			dates.add("Month");
			water.add("Water consume in CMD");
			int year = getYearFromStringDate(graphDate);
			series = new ArrayList<>();
			series.add(" ");
			for (int i = 1; i <= 12; i++)
			{
				Float actualReading = 0.0f;
				String date = monthName[i];
				dates.add(date);
				actualReading = regWaterSourceDataServices.getSumOfActualReadingByMonthAndYear(i, year);
				water.add(actualReading);
			}
			data.add(dates);
			data.add(water);
			productIdAndUnit = allProductNameServices.getProductNameUnitsByProductType();
			ArrayList<Object> productDetails = null;
			for (int i = 0; i < productIdAndUnit.size(); i++)
			{
				productDetails = new ArrayList<>();
				String pName = productIdAndUnit.get(i)[0].toString();
				productDetails.add(pName + " in " + productIdAndUnit.get(i)[1].toString());
				for (int j = 1; j <= 12; j++)
				{
					quant = regularDataServices.getQuantityByPNameAndMonthYear(pName, j, year);
					quantity = Validator.isEmpty(quant) ? 0 : quant;
					productDetails.add(quantity);
				}
				data.add(productDetails);
			}
		}
		else if (graphTabName.equalsIgnoreCase("Yearly"))
		{

			ArrayList<Object> productDetails = null;
			water.add("Water consume in CMD");
			ArrayList<Object> yearList = new ArrayList<>();
			yearList.add("Year");
			String[] dateSplit = graphDate.split("-");
			int years = Integer.parseInt(dateSplit[0]);
			yearsList.add(years);
			for (int i = 0; i < 2; i++)
			{
				years = years - 1;
				yearsList.add(years);
			}
			yearList.addAll(yearsList);
			data.add(yearList);
			for (int i = 1; i < yearList.size(); i++)
			{
				Float waterReading = regWaterSourceDataServices.getSumOfActualReadingByYear((Integer) yearList.get(i));
				if (waterReading == null)
				{
					waterReading = 0.0f;
				}
				water.add(waterReading);
			}
			data.add(water);
			productIdAndUnit = allProductNameServices.getProductNameUnitsByProductType();
			if (productIdAndUnit != null)
			{
				for (int j = 0; j < productIdAndUnit.size(); j++)
				{
					productDetails = new ArrayList<>();
					String pName = productIdAndUnit.get(j)[0].toString();
					productDetails.add(pName + " in " + productIdAndUnit.get(j)[1].toString());

					for (Integer year : yearsList)
					{
						quantity = regularDataServices.getSumOfQuantityByPNameAndYear(pName, year);
						if (quantity == null)
						{
							quantity = 0.0f;
						}
						productDetails.add(quantity);
					}
					data.add(productDetails);
				}
			}
		}
		return data;
	}

	/**
	 * This method used to get water data
	 * 
	 * @param fDate the start date of the water data
	 * @param lDate the last date of the water data
	 * @return watervalue
	 */
	private float getWaterData(String fDate, String lDate)
	{
		float watervalue = 0.0f;
		List<RegWaterSourceData> regularSourceData = null;
		try
		{
			regularSourceData = new ArrayList<>();
			regularSourceData = regWaterSourceDataServices.getAllRegularSourceDataBetweenDates(fDate, lDate);
			int cnt = regularSourceData.size();
			if (cnt > 0)
			{
				for (int ii = 0; ii < cnt; ii++)
				{
					float a1 = regularSourceData.get(ii).getActualReading();
					float s = regularSourceData.get(ii).getStartReading();
					float r = regularSourceData.get(ii).getEndReading();
					float con = (float) (a1 == 0 ? r - s : Math.abs(a1));
					watervalue = watervalue + con;
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return watervalue;
	}

}
