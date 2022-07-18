package com.tes.controller.environmentalofficer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.DirectUseOfWater;
import com.tes.model.EmpData;
import com.tes.model.FilterUse;
import com.tes.model.MultipleFilter;
import com.tes.model.Prefilter;
import com.tes.model.RegularTreatmentData;
import com.tes.model.WastewaterRecycle;
import com.tes.model.WastewaterTreatment;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.UnitServices;
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
 * This class used to manage the Graph data. i.e.Product, byproduct, Raw
 * Material, Fuel, hwp, hwpcf,nhwp, nhwpcf, water, Bio-Medical etc. View the
 * reports of all product and reading of all product.
 * 
 * @author Tushar Chougule
 */
@Controller
@RequestMapping(value = {"/env", "/management"})
public class GraphController
{

	@Autowired
	UnitServices unitServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	MultipleFilterServices multipleFilterServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	RegMultipleFilterDataServices regMultipleFilterDataServices;

	@Autowired
	RegFiltersUseDataServices regFiltersUseDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regDirectUseOfWaterDataServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWaterServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	PrefilterServices prefilterServices;

	@Autowired
	RegPrefilterServices regPrefilterServices;

	@Autowired
	WasteWaterRecycleSevices wasteWaterRecycleSevices;

	@Autowired
	RegWastewaterRecycleServices regWastewaterRecycleServices;

	private static final Logger LOGGER = LogManager.getLogger(GraphController.class);

	/**
	 * This method used to graph data i.e., Product, byproduct, Raw Material, Fuel,
	 * hwp, hwpcf, nhwp, nhwpcf, water, Bio-Medical etc.
	 * 
	 * @return view-report of all product
	 */

	// @Secured("ROLE_ENVROFFICER")
	@RequestMapping("view-report")
	public ModelAndView getViewReport()
	{
		ModelAndView model;
		model = new ModelAndView("EnvrOfficer/ViewReport");
		try
		{
			int consentMinYear = 0;
			List<Integer> consentYears = new ArrayList<>();
			String today = Utilities.getTodaysDate();
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
			String[] pType = {"Product", "byproduct", "Raw Material", "Fuel", "hwp", "hwpcf", "nhwp", "nhwpcf",
					"water", "Bio-Medical"};
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
			if (consentMinYear != 0)
			{
				int maxYearDiff = currentYear - consentMinYear;
				maxYearDiff = maxYearDiff + 1;
				for (int i = 1; i <= maxYearDiff; i++)
				{
					consentYears.add(currentYear);
					currentYear = currentYear - 1;
				}
			}
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
				else if (pType[i].equalsIgnoreCase("Raw Material"))
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
				model.addObject("consentYears", consentYears);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return model;
	}

	/**
	 * This method used to get the industry type.
	 * 
	 * @param request the servlet request we are processing.
	 * @return industryType
	 */
	@RequestMapping(value = "/ajax-getStatus")
	public @ResponseBody String getStatus(HttpServletRequest request)
	{
		String industryType = "";
		EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
		String industryCategory = empDataSession.getCompanyProfile().getIndustryCategory();
		try
		{
			String[] category = industryCategory.split("-");
			industryType = category[0].trim();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return industryType;
	}

	/**
	 * This method used to get the graph unit values
	 * 
	 * @param type the type of product type.
	 * @return unitList
	 */

	@GetMapping(value = "ajax-graph-getunits")
	public @ResponseBody List<String> getNoOfUnits(@RequestParam("type") String type)
	{
		List<String> unitList = null;
		unitList = unitServices.getUnitsFromProductType(type);
		return unitList;
	}

	/**
	 * This method used to it convert unit value as per the duration(i.e. year,
	 * month, day) .
	 * 
	 * @param request the servlet request we are processing.
	 * @param productName the name of product name
	 * @param type the type of product type
	 * @return unit value
	 */
	@GetMapping(value = "/ajax-setUnits")
	public @ResponseBody String SelectProductType(HttpServletRequest request,
			@RequestParam("productName") String productName, @RequestParam("type") String type)
	{
		String unitName;
		String[] unit = null;
		String unitp = null;
		int noOfWorkers = 0;
		float quantity = 0.0f;
		float a = 0.0f;
		try
		{
			EmpData empdata = (EmpData) request.getSession().getAttribute("empDataSession");
			int companyId = empdata.getCompanyProfile().getCompanyProfileId();
			String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			unitName = unitServices.getUnitFromAllProducts(productName);
			if (!unitName.isEmpty())
			{
				unit = unitName.split("/");
				unitp = unit[0] + "/Day";
			}
			noOfWorkers = companyProfileServices.noOfWorkingDays(companyId);
			int totalDays = noOfWorkers * 52;
			int unitsDays = 0;
			if (unit[1].equalsIgnoreCase("Year"))
			{
				unitsDays = totalDays;
			}
			else if (unit[1].equalsIgnoreCase("Month"))
			{
				unitsDays = noOfWorkers * 4;
			}
			else if (unit[1].equalsIgnoreCase("Week"))
			{
				unitsDays = noOfWorkers * 7;
			}
			else if (unit[1].equalsIgnoreCase("Day"))
			{
				unitsDays = 1;
			}
			else if (unitp.equalsIgnoreCase("NA"))
			{
				unitsDays = 1;
			}
			else
			{
				unitsDays = 1;
			}
			float quantityCons = allProductsServices.getSumOfQuantity(today, productName);
			if (unitName.equalsIgnoreCase("Nos/Day") || unitName.equalsIgnoreCase("MT/Day")
					|| unitName.equalsIgnoreCase("Liter/Day") || unitName.equalsIgnoreCase("Kg/Day"))
			{
				a = quantityCons;
			}
			else
			{
				quantity = regularDataServices.getSumOfRegularDataQuantity(productName);
				a = (quantityCons - quantity) / unitsDays;
				if (a < 0)
				{
					a = 0;
				}
			}
			if (unitp.equalsIgnoreCase("Nos/Day"))
			{
				a = Math.round(a);
			}
			else
			{
				a = Utilities.round(a, 1);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return (unitp + "-" + a);
	}

	/**
	 * This method used to get the product bar data as per particular product type
	 * 
	 * @param action
	 * @param sDate the start date of product data
	 * @param type the type of product
	 * @param unit the unit of product
	 * @param lastdate the last date of product data
	 * @return data
	 * @throws ParseException if parsing causes an error
	 */
	// @PostMapping(value = "ajax-getArrayBarData")
	// public @ResponseBody List<Object> getArrayData(@RequestParam(value = "action", required = false) int action,
	// @RequestParam(value = "pdata", required = false) String sDate,
	// @RequestParam(value = "type", required = false) String type,
	// @RequestParam(value = "units", required = false) String unit,
	// @RequestParam(value = "lastdate", required = false) String lastdate) throws ParseException {
	// List<String> getAllProductName;
	// List<Object> data;
	// data = new ArrayList<>();
	// String getunit = null;
	// ArrayList<Object> series = new ArrayList<>();
	// Float quantity = 0.0f;
	// int quan = 0;
	// String[] monthName = { " ", "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov",
	// "Dec" };
	//
	// int day = Utilities.getDayFromStringDate(sDate);
	// int month = Utilities.getMonthFromStringDate(sDate);
	// int year = Utilities.getYearFromStringDate(sDate);
	// int currentYear = year;
	// int prevYear = currentYear - 1;
	// try {
	// if (unit.equalsIgnoreCase("NA")) {
	// getAllProductName = allProductNameServices.getProductNameByTypeAndDate(sDate, type);
	// } else {
	// getAllProductName = allProductNameServices.getProductNameByTypeDateAndUnit(sDate, type, unit);
	// }
	// if (action == 1) {
	// series.add("Days");
	// Float regQuantity = 0.0f;
	// String pName = "";
	// series.add(" ");
	// series.add(monthName[month] + " " + day);
	// series.add(" ");
	// data.add(series);
	// if (!getAllProductName.isEmpty()) {
	// for (int i = 0; i < getAllProductName.size(); i++) {
	// series = new ArrayList<>();
	// pName = getAllProductName.get(i);
	// if (unit == "NA") {
	// series.add("Consent value of " + pName + "\t\t per Day");
	// getunit = unitServices.getUnitByDateTypeAndProductName(sDate, type, pName);
	// if (getunit != null) {
	// unit = getunit;
	// }
	// quantity = getConsentData(pName, unit, action);
	// quan = (int) Math.round(quantity);
	// if (quantity != null) {
	// series.add(0);
	// series.add(quan);
	// series.add(0);
	// }
	// } else {
	// series.add("Consent value of " + pName + "\t\t per Day");
	// quantity = getConsentData(pName, unit, action);
	// quan = (int) Math.round(quantity);
	// if (quantity != null) {
	// series.add(quan);
	// series.add(quan);
	// series.add(quan);
	// }
	// }
	// data.add(series);
	// regQuantity = regularDataServices.quantityByProductNameDate(pName, sDate);
	// series = new ArrayList<>();
	// if (regQuantity != null) {
	// series.add(pName + " in " + unit);
	// series.add(0);
	// series.add(regQuantity);
	// series.add(0);
	// data.add(series);
	// } else {
	// String res[] = unit.split("/");
	// series.add(pName + " in " + res[0] + "/Day");
	// // series.add(pName + " in " + unit);
	// series.add(null);
	// series.add(0);
	// series.add(null);
	// data.add(series);
	// }
	// }
	// }
	// }
	// if (action == 7) {
	// ArrayList<String> daysList = new ArrayList<>();
	// daysList.add("Days");
	// daysList.addAll(Utilities.getDaysList(sDate, 7));
	// data.add(daysList);
	//
	// if (!Validator.isEmpty(getAllProductName)) {
	// for (int i = 0; i < getAllProductName.size(); i++) {
	// if (!getAllProductName.get(i).equalsIgnoreCase("NA")) {
	// String productName = getAllProductName.get(i);
	// Float eachDay = allProductsServices.findOneByGetConsentData(productName, sDate);
	// String res[] = unit.split("/");
	// if (eachDay == null)
	// eachDay = 0.0f;
	// quan = (int) Math.round(eachDay);
	// if (eachDay > 0) {
	// eachDay = Utilities.convertDataToByUnitAsPerYrMnthDay(unit, eachDay, action);
	// quan = (int) Math.round(eachDay);
	// }
	//
	// // ADD CONSENTED VALUE TO ARRAY
	// series = new ArrayList<>();
	// series.add("consent value " + productName);
	// for (int days = 0; days <= 7; days++) {
	// series.add(quan);
	// }
	// data.add(series);
	// series = new ArrayList<>();
	// series.add(productName + " in " + res[0] + "/Day");
	// for (int days = 0; days <= 7; days++) {
	// Calendar cal = new GregorianCalendar(year, month - 1, day);
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// cal.add(Calendar.DAY_OF_WEEK, -days);
	// String date = sdf.format(cal.getTime());
	// quantity = regularDataServices.quantityByProductNameDate(productName, date);
	// if (quantity == null)
	// quantity = 0.0f;
	// series.add(quantity);
	// }
	// data.add(series);
	// }
	// }
	// }
	// } // 7 ends
	// else if (action == 30) {
	// series.add("Month");
	// List<String> arrayWeek = Utilities.getWeeklyArrayByDateOfMonth(lastdate);
	// for (int i = 0; i < arrayWeek.size() - 1; i++) {
	// String weekCnt = "Week" + (i + 1);
	// series.add(weekCnt);
	// }
	// data.add(series);
	// if (!getAllProductName.isEmpty()) {
	// for (int j = 0; j < getAllProductName.size(); j++) {
	// series = new ArrayList<>();
	// String pName = getAllProductName.get(j);
	// series.add("Consent value of " + pName + "\t\tper Week");
	// if (unit != "NA") {
	// quantity = getConsentData(pName, unit, action);
	// quan = (int) Math.round(quantity);
	// if (quantity != null) {
	// for (int i = 1; i <= arrayWeek.size() - 1; i++) {
	// series.add(quan);
	// }
	// data.add(series);
	// }
	// } else {
	// getunit = unitServices.getUnitByDateTypeAndProductName(sDate, type, pName);
	// if (getunit != null) {
	// unit = getunit;
	// }
	// quantity = getConsentData(pName, unit, action);
	// quan = (int) Math.round(quantity);
	// if (quantity != null) {
	// for (int i = 1; i < arrayWeek.size() - 1; i++) {
	// series.add(quan);
	// }
	// data.add(series);
	// }
	// }
	// series = new ArrayList<>();
	// String res[] = unit.split("/");
	// series.add(pName + " in " + res[0] + "/Week");
	// // series.add(pName);
	// for (int i = 0; i < arrayWeek.size() - 1; i++) {
	// quantity = regularDataServices.getAverageQuantityByPNameAndBetweenDates(pName,
	// arrayWeek.get(i), arrayWeek.get(i + 1));
	// if (quantity != null) {
	// series.add(quantity);
	// } else {
	// series.add(0);
	// }
	// }
	// data.add(series);
	// }
	// }
	// }
	// if (action == 365 || action == 366) {
	// String month1 = "";
	// int month2 = 0;
	// series = new ArrayList<>();
	// series.add(" ");
	// for (int i = 1; i <= 12; i++) {
	// if (i >= 1 && i <= 9) {
	// month1 = "0" + i;
	// month2 = Integer.valueOf(month1);
	// } else {
	// month2 = i;
	// }
	// String date = monthName[month2];
	// series.add(date);
	// }
	// data.add(series);
	// if (!Validator.isEmpty(getAllProductName)) {
	// for (int i = 0; i < getAllProductName.size(); i++) {
	// if (!getAllProductName.get(i).equalsIgnoreCase("NA")) {
	// String productName = getAllProductName.get(i);
	// Float eachDay = allProductsServices.findOneByGetConsentData(productName, sDate);
	// String res[] = unit.split("/");
	// if (eachDay == null)
	// eachDay = 0.0f;
	// quan = (int) Math.round(eachDay);
	// if (eachDay > 0) {
	// eachDay = Utilities.convertDataToByUnitAsPerYrMnthDay(unit, eachDay, action);
	// quan = (int) Math.round(eachDay);
	//
	// }
	// // ADD CONSENTED VALUE TO ARRAY
	// series = new ArrayList<>();
	// series.add("consent value " + productName + "\t\tper Month");
	// for (int days = 1; days <= 12; days++) {
	// series.add(quan);
	// }
	// data.add(series);
	// series = new ArrayList<>();
	// series.add(productName + " in " + res[0] + "/Month");
	// for (int days = 1; days <= 12; days++) {
	// quantity = regularDataServices.getAverageQuantityByPNameMonthYear(productName, days,
	// year);
	// if (quantity == null)
	// quantity = 0.0f;
	// series.add(quantity);
	// }
	// data.add(series);
	// }
	// }
	// }
	// }
	// if (action == 0) {
	// List<Integer> regularDataYearList = new ArrayList<>();
	// regularDataYearList.add(currentYear);
	// regularDataYearList.add(prevYear);
	// series.add("Year");
	// for (int x = 0; x < regularDataYearList.size(); x++) {
	// series.add(regularDataYearList.get(x));
	// }
	// data.add(series);
	// if (!Validator.isEmpty(getAllProductName)) {
	// for (int i = 0; i < getAllProductName.size(); i++) {
	// if (!getAllProductName.get(i).equalsIgnoreCase("NA")) {
	// String productName = getAllProductName.get(i);
	// Float eachDay = allProductsServices.findOneByGetConsentData(productName, sDate);
	// String res[] = unit.split("/");
	// if (eachDay == null)
	// eachDay = 0.0f;
	// quan = (int) Math.round(eachDay);
	// if (eachDay > 0) {
	// eachDay = Utilities.convertDataToByUnitAsPerYrMnthDay(unit, eachDay, action);
	// quan = (int) Math.round(eachDay);
	// }
	// // ADD CONSENTED VALUE TO ARRAY
	// series = new ArrayList<>();
	// series.add("consent value " + productName + "\t\tper Year");
	// for (int days = 0; days < regularDataYearList.size(); days++) {
	// series.add(quan);
	// }
	// data.add(series);
	// series = new ArrayList<>();
	// series.add(productName + " in " + res[0] + "/Day");
	// for (int days = 0; days < regularDataYearList.size(); days++) {
	// int year1 = regularDataYearList.get(days).intValue();
	// quantity = regularDataServices.getAverageQuantityByPNameAndYear(productName, year1);
	// if (quantity == null)
	// quantity = 0.0f;
	// series.add(quantity);
	// }
	// data.add(series);
	// }
	// }
	// }
	// }
	// } catch (Exception e) {
	// LOGGER.error(e);
	// }
	// return data;
	// }

	/**
	 * This method used to get the consent data as per duration of year, month,
	 * days.
	 * 
	 * @param prodName the name of the product
	 * @param units the unit of product
	 * @return eachDay
	 */

	private float getConsentData(String prodName, String units, String convertTo)
	{
		float eachDay;
		eachDay = 0.0f;
		try
		{
			float quantity;
			String[] res = units.split("/");
			quantity = allProductsServices.getQuantityByProductName(prodName);
			if (quantity != 0)
			{
				if (res[1].equalsIgnoreCase("Day"))
					eachDay = quantity;
				else if (res[1].equalsIgnoreCase("Hr"))
					eachDay = quantity * 24;
				else if (res[1].equalsIgnoreCase("Month"))
					eachDay = quantity / 30;
				else if (res[1].equalsIgnoreCase("Week"))
					eachDay = quantity / 7;
				else if (res[1].equalsIgnoreCase("Year"))
					eachDay = quantity / 365.0f;

				if (convertTo.equalsIgnoreCase("Weekly"))
				{
					eachDay = eachDay * 7.0f;
				}
				else if (convertTo.equalsIgnoreCase("Monthly"))
				{
					eachDay = eachDay * 30.0f;
				}
				else if (convertTo.equalsIgnoreCase("Yearly"))
				{
					eachDay = eachDay * 365.0f;
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return eachDay;
	}

	/**
	 * This method used to get water consumption data as per daily, back 7 days
	 * etc..
	 * 
	 * @param action
	 * @param sDate the start date
	 * @param type the type of the product
	 * @param unit the unit of the product
	 * @param lastdate the last date
	 * @return getWaterData
	 * @throws ParseException if parsing causes an error
	 */

	@PostMapping(value = "ajax-getWaterData")
	public @ResponseBody List<Object> getGraphWaterData(
			@RequestParam(value = "graphTabName", required = false) String graphTabName,
			@RequestParam(value = "graphDate", required = false) String graphDate,
			@RequestParam(value = "graphResourceType", required = false) String graphResourceType,
			@RequestParam(value = "graphUnit", required = false) String graphUnit) throws ParseException
	{

		Float aReading = 0.0f;
		Float eReading = 0.0f;
		List<String> waterInvSourceName = null;
		List<MultipleFilter> filterNames = null;
		List<FilterUse> filterUseList = null;
		List<Object> rowData = new ArrayList<>();
		List<DirectUseOfWater> directUseWaterList = null;
		List<WastewaterTreatment> recycleLabelList = null;
		Set<Integer> yearsList = new HashSet<>();

		ArrayList<Object> series = new ArrayList<>();
		ArrayList<Object> energy = new ArrayList<>();
		ArrayList<Object> dates = new ArrayList<>();
		ArrayList<Object> water = null;

		int day = Utilities.getDayFromStringDate(graphDate);
		int month = Utilities.getMonthFromStringDate(graphDate);
		int year = Utilities.getYearFromStringDate(graphDate);

		String lastdate = Utilities.getLastDate(graphDate);

		String[] monthName = {"", "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov",
				"Dec"};

		if (graphTabName.equalsIgnoreCase("DailyTab"))
		{
			try
			{
				series.add("Days");
				series.add(Utilities.getMonthInShort(graphDate));
				rowData.add(series);
				if (graphResourceType.equalsIgnoreCase("swr"))
				{
					waterInvSourceName = waterSourceServices.getAllWaterSourceName();
					if (!waterInvSourceName.isEmpty())
					{
						for (int i = 0; i < waterInvSourceName.size(); i++)
						{
							series = new ArrayList<>();
							String SourceName = waterInvSourceName.get(i);
							series.add(SourceName);
							aReading = regularSourceDataServices.getActualReadingByRsDateAndSourceName(graphDate,
									SourceName);
							if (aReading == null)
								aReading = 0.0f;
							series.add(aReading);
							rowData.add(series);
						}
					}
				}
				// filter
				else if (graphResourceType.equalsIgnoreCase("fr"))
				{
					filterNames = multipleFilterServices.getAllFiltersIdAndLabels();
					if (!filterNames.isEmpty())
					{
						for (int i = 0; i < filterNames.size(); i++)
						{
							Float filterActualReading = 0.0f;
							series = new ArrayList<>();
							String filterName = filterNames.get(i).getFilterLabel();
							series.add(filterName);
							filterActualReading = regMultipleFilterDataServices.getActualReadingByDateAndFilterId(
									graphDate, filterNames.get(i).getMultipleFilterId());
							if (filterActualReading == null)
								filterActualReading = 0.0f;
							series.add(filterActualReading);
							rowData.add(series);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fru"))
				{

					recycleLabelList = wastewaterTreatmentServices.getAllLabelAndId();
					for (int i = 0; i < recycleLabelList.size(); i++)
					{
						List<WastewaterRecycle> recycledToNameList = wasteWaterRecycleSevices.findAllRecycleIdAndLabel(recycleLabelList.get(i).getLabel());
						for (int j = 0; j < recycledToNameList.size(); j++)
						{

							Float actualReading = 0.0f;
							series = new ArrayList<>();
							series.add(recycleLabelList.get(i).getLabel() + "-" + recycledToNameList.get(j).getRecycledTo());
							actualReading = regWastewaterRecycleServices
									.getActualreadingById(recycledToNameList.get(j).getWastewaterRecycleId(), graphDate);
							if (actualReading == null)
							{
								actualReading = 0.0f;
							}
							series.add(actualReading);
							rowData.add(series);
						}
					}

				}
				// filter_use
				else if (graphResourceType.equalsIgnoreCase("fwur"))
				{

					filterUseList = filterUseServices.findFilterUseTypeAndlabel();
					if (!filterUseList.isEmpty())
					{
						for (int i = 0; i < filterUseList.size(); i++)
						{
							Float aReadingRegFilterUseData = 0.0f;
							series = new ArrayList<>();
							series.add(filterUseList.get(i).getFilterUseType() + "-"
									+ filterUseList.get(i).getFilterUseLabel());
							aReadingRegFilterUseData = regFiltersUseDataServices.findActualReadingByDateAndLabel(
									graphDate, filterUseList.get(i).getFilterUseLabel());

							if (aReadingRegFilterUseData == null)
								aReadingRegFilterUseData = 0.0f;
							series.add(aReadingRegFilterUseData);
							rowData.add(series);
						}
					}
				}
				// direct_use
				else if (graphResourceType.equalsIgnoreCase("swur"))
				{
					directUseWaterList = directUseOfWaterServices.getAllWhereToUseAndIsIndustries();
					if (!directUseWaterList.isEmpty())
					{
						String sourceType = "";
						for (int xx = 0; xx < directUseWaterList.size(); xx++)
						{
							Float actualReadingRegWaterUseData = 0.0f;
							series = new ArrayList<>();
							boolean isIndustrial = directUseWaterList.get(xx).isIndustrial();
							if (isIndustrial == true)
								sourceType = "Industrial-" + directUseWaterList.get(xx).getWhereToUse();
							else
								sourceType = directUseWaterList.get(xx).getWhereToUse();
							series.add(sourceType);

							actualReadingRegWaterUseData = regDirectUseOfWaterDataServices
									.getActualReadingByDateAndSourceType(graphDate,
											directUseWaterList.get(xx).getWhereToUse());
							if (actualReadingRegWaterUseData == null)
							{
								actualReadingRegWaterUseData = 0.0f;
							}
							series.add(actualReadingRegWaterUseData);
							rowData.add(series);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("tpr"))
				{
					List<WastewaterTreatment> wasteWaterTreatmentList = wastewaterTreatmentServices.findAll();
					RegularTreatmentData regularTreatment = null;
					if (!wasteWaterTreatmentList.isEmpty())
					{
						for (int i = 0; i < wasteWaterTreatmentList.size(); i++)
						{
							series = new ArrayList<>();
							series.add(wasteWaterTreatmentList.get(i).getLabel() + "-Source Reading");
							energy = new ArrayList<>();
							energy.add(wasteWaterTreatmentList.get(i).getLabel() + "-Energy Reading");
							regularTreatment = regularTreatmentDataServices.getActualEnergyReadingByIdAndDate(
									wasteWaterTreatmentList.get(i).getWastewaterTreatmentId(), graphDate);
							if (regularTreatment == null)
							{
								series.add(0.0f);
								energy.add(0.0f);
							}
							else
							{
								series.add(regularTreatment.getActualReading());
								energy.add(regularTreatment.getEnergyAvg());
							}
							rowData.add(series);
							rowData.add(energy);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("pfw"))
				{
					Float regPreFilter;
					List<Prefilter> preFilterList = prefilterServices.getAllIdAndIsPrifilter();
					if (!preFilterList.isEmpty())
					{
						for (int i = 0; i < preFilterList.size(); i++)
						{
							series = new ArrayList<>();
							series.add("PreFilter-" + preFilterList.get(i).getWaterSource().getSourceName());
							regPreFilter = regPrefilterServices
									.getRegPrefilterByIdAndDate(preFilterList.get(i).getPrefilterId(), graphDate);
							if (regPreFilter != null)
							{
								series.add(regPreFilter);
								rowData.add(series);
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		if (graphTabName.equalsIgnoreCase("Back7Days"))
		{
			try
			{
				int s = 0;
				dates.add("Days");
				for (int j = 1; j <= 7; j++)
				{
					Calendar cal = new GregorianCalendar(year, month - 1, day);
					cal.add(Calendar.DAY_OF_WEEK, -s);
					String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
					dates.add(Utilities.getMonthInShort(date));
					s++;
				}
				rowData.add(dates);
				s = 0;
				if (graphResourceType.equalsIgnoreCase("swr"))
				{
					waterInvSourceName = waterSourceServices.getAllWaterSourceName();
					if (!waterInvSourceName.isEmpty())
					{
						for (int x = 0; x < waterInvSourceName.size(); x++)
						{
							water = new ArrayList<>();
							water.add(waterInvSourceName.get(x));
							s = 0;
							for (int j = 1; j <= 7; j++)
							{
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								cal.add(Calendar.DAY_OF_WEEK, -s);
								String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
								Float actualReading = regularSourceDataServices
										.getActualReadingByRsDateAndSourceName(date, waterInvSourceName.get(x));
								if (actualReading != null)
								{
									water.add(actualReading);
								}
								else
								{
									water.add(0);
								}
								s++;
							}
							rowData.add(water);
						}
					}
				}
				if (graphResourceType.equalsIgnoreCase("fr"))
				{
					filterNames = multipleFilterServices.getAllFiltersIdAndLabels();
					if (!filterNames.isEmpty())
					{
						for (int i = 0; i < filterNames.size(); i++)
						{
							water = new ArrayList<>();
							water.add(filterNames.get(i));
							s = 0;
							for (int j = 0; j < 7; j++)
							{
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								cal.add(Calendar.DAY_OF_WEEK, -s);
								String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
								Float filterActualReading = regMultipleFilterDataServices
										.getActualReadingByDateAndFilterId(date,
												filterNames.get(i).getMultipleFilterId());
								if (filterActualReading != null)
								{
									water.add(filterActualReading);
								}
								else
								{
									water.add(0);
								}
								s++;
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fru"))
				{

					recycleLabelList = wastewaterTreatmentServices.getAllLabelAndId();
					for (int i = 0; i < recycleLabelList.size(); i++)
					{
						List<WastewaterRecycle> recycledToNameList = wasteWaterRecycleSevices.findAllRecycleIdAndLabel(recycleLabelList.get(i).getLabel());
						for (int q = 0; q < recycledToNameList.size(); q++)
						{

							Float actualReading;
							series = new ArrayList<>();
							series.add(recycleLabelList.get(i).getLabel() + "-" + recycledToNameList.get(q).getRecycledTo());
							for (int j = 0; j < 7; j++)
							{
								actualReading = 0.0f;
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								cal.add(Calendar.DAY_OF_WEEK, -s);
								String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
								actualReading = regWastewaterRecycleServices
										.getActualreadingById(recycledToNameList.get(q).getWastewaterRecycleId(), graphDate);
								if (actualReading == null)
									series.add(0);
								else
									series.add(actualReading);
								s++;
							}
							rowData.add(series);
						}
					}

				}
				else if (graphResourceType.equalsIgnoreCase("fwur"))
				{
					filterUseList = filterUseServices.findFilterUseTypeAndlabel();
					if (!filterUseList.isEmpty())
					{
						for (int i = 0; i < filterUseList.size(); i++)
						{
							water = new ArrayList<>();
							water.add(filterUseList.get(i).getFilterUseType() + "-"
									+ filterUseList.get(i).getFilterUseLabel());
							for (int j = 0; j < 7; j++)
							{
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								cal.add(Calendar.DAY_OF_WEEK, -s);
								String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
								Float actualReading = regFiltersUseDataServices.findActualReadingByDateAndLabel(date,
										filterUseList.get(i).getFilterUseLabel());
								if (actualReading != null)
								{
									water.add(actualReading);
								}
								s++;
							}
							s = 0;
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("swur"))
				{
					directUseWaterList = directUseOfWaterServices.getAllWhereToUseAndIsIndustries();
					if (!directUseWaterList.isEmpty())
					{
						String sourceType = "";
						Float actualReading = 0.0f;
						for (int i = 0; i < directUseWaterList.size(); i++)
						{
							s = 0;
							water = new ArrayList<>();
							boolean isIndustrial = directUseWaterList.get(i).isIndustrial();
							if (isIndustrial == true)
							{
								sourceType = "Industrial-" + directUseWaterList.get(i).getWhereToUse();
							}
							else
							{
								sourceType = directUseWaterList.get(i).getWhereToUse();
							}
							water.add(sourceType);
							for (int j = 0; j < 7; j++)
							{
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								cal.add(Calendar.DAY_OF_WEEK, -s);
								String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
								actualReading = regDirectUseOfWaterDataServices.getActualReadingByDateAndSourceType(
										date, directUseWaterList.get(i).getWhereToUse());
								if (actualReading != null)
								{
									water.add(actualReading);
								}
								else
								{
									water.add(0);
								}
								s++;
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("tpr"))
				{
					s = 0;
					Float actualReading, energyReading;
					RegularTreatmentData regularTreatment;
					List<WastewaterTreatment> wasteWaterTreatmentList = wastewaterTreatmentServices.findAll();
					if (!wasteWaterTreatmentList.isEmpty())
					{
						for (int i = 0; i < wasteWaterTreatmentList.size(); i++)
						{
							series = new ArrayList<>();
							series.add(wasteWaterTreatmentList.get(i).getLabel() + "-Source Reading");
							energy = new ArrayList<>();
							energy.add(wasteWaterTreatmentList.get(i).getLabel() + "-Energy Reading");
							for (int j = 1; j <= 7; j++)
							{
								actualReading = 0.0f;
								energyReading = 0.0f;
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								cal.add(Calendar.DAY_OF_WEEK, -s);
								String date = sdf.format(cal.getTime());
								regularTreatment = regularTreatmentDataServices.getActualEnergyReadingByIdAndDate(
										wasteWaterTreatmentList.get(i).getWastewaterTreatmentId(), date);
								if (regularTreatment != null)
								{
									actualReading = regularTreatment.getActualReading();
									energyReading = regularTreatment.getEnergyAvg();
								}
								series.add(actualReading);
								energy.add(energyReading);
								s++;
							}
							rowData.add(series);
							rowData.add(energy);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("pfw"))
				{
					s = 0;
					Float regPreFilter;
					List<Prefilter> preFilterList = prefilterServices.getAllIdAndIsPrifilter();
					if (!preFilterList.isEmpty())
					{
						for (int i = 0; i < preFilterList.size(); i++)
						{
							s = 0;
							series = new ArrayList<>();
							series.add("PreFilter-" + preFilterList.get(i).getWaterSource().getSourceName());
							for (int j = 1; j <= 7; j++)
							{
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								cal.add(Calendar.DAY_OF_WEEK, -s);
								String date = sdf.format(cal.getTime());
								regPreFilter = regPrefilterServices
										.getRegPrefilterByIdAndDate(preFilterList.get(i).getPrefilterId(), date);
								if (regPreFilter == null)
								{
									regPreFilter = 0.0f;
								}
								series.add(regPreFilter);
								s++;
							}
							rowData.add(series);
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if (graphTabName.equalsIgnoreCase("Weekly"))
		{
			try
			{
				dates.add("Month");
				List<String> arrayWeek = Utilities.getWeeklyArrayByDateOfMonth(lastdate);
				for (int i = 0; i < arrayWeek.size() - 1; i++)
				{
					String weekCnt = "Week " + (i + 1);
					dates.add(weekCnt);
				}
				rowData.add(dates);
				if (graphResourceType.equalsIgnoreCase("swr"))
				{
					waterInvSourceName = waterSourceServices.getAllWaterSourceName();
					if (!waterInvSourceName.isEmpty())
					{
						for (int x = 0; x < waterInvSourceName.size(); x++)
						{
							water = new ArrayList<>();
							water.add(waterInvSourceName.get(x));
							for (int i = 0; i < arrayWeek.size() - 1; i++)
							{
								Float wdata = getWaterData(arrayWeek.get(i), arrayWeek.get(i + 1), graphResourceType,
										waterInvSourceName.get(x));
								water.add(wdata);
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fr"))
				{
					filterNames = multipleFilterServices.getAllFiltersIdAndLabels();
					if (!filterNames.isEmpty())
					{
						for (int i = 0; i < filterNames.size(); i++)
						{
							water = new ArrayList<>();
							water.add(filterNames.get(i));
							for (int j = 0; j < arrayWeek.size() - 1; j++)
							{
								Float wdata = getWaterData(arrayWeek.get(j), arrayWeek.get(j + 1), graphResourceType,
										filterNames.get(i).getFilterLabel());
								water.add(wdata);
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fru"))
				{

					recycleLabelList = wastewaterTreatmentServices.getAllLabelAndId();
					for (int i = 0; i < recycleLabelList.size(); i++)
					{
						List<WastewaterRecycle> recycledToNameList = wasteWaterRecycleSevices.findAllRecycleIdAndLabel(recycleLabelList.get(i).getLabel());
						for (int j = 0; j < recycledToNameList.size(); j++)
						{

							Float actualReading;
							series = new ArrayList<>();
							series.add(recycleLabelList.get(i).getLabel() + "-" + recycledToNameList.get(j).getRecycledTo());
							for (int k = 0; k < arrayWeek.size() - 1; k++)
							{
								actualReading = regWastewaterRecycleServices.getActualReadingByIdandWeeks(
										recycledToNameList.get(j).getWastewaterRecycleId(), arrayWeek.get(k),
										arrayWeek.get(k + 1));
								if (actualReading == null)
									series.add(0);
								else
									series.add(actualReading);
							}
							rowData.add(series);
						}
					}

				}
				else if (graphResourceType.equalsIgnoreCase("fwur"))
				{
					filterUseList = filterUseServices.findFilterUseTypeAndlabel();
					if (!filterUseList.isEmpty())
					{
						for (int i = 0; i < filterUseList.size(); i++)
						{
							water = new ArrayList<>();
							water.add(filterUseList.get(i).getFilterUseType() + "-" + filterUseList.get(i).getFilterUseLabel());
							for (int j = 0; j < arrayWeek.size() - 1; j++)
							{
								Float wdata = getWaterData(arrayWeek.get(j), arrayWeek.get(j + 1), graphResourceType,
										filterUseList.get(i).getFilterUseLabel());
								water.add(wdata);
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("swur"))
				{
					String sourceType = "";
					directUseWaterList = directUseOfWaterServices.getAllWhereToUseAndIsIndustries();
					if (!directUseWaterList.isEmpty())
					{
						for (int i = 0; i < directUseWaterList.size(); i++)
						{
							water = new ArrayList<>();
							boolean isIndustrial = directUseWaterList.get(i).isIndustrial();
							if (isIndustrial == true)
							{
								sourceType = "Industrial-" + directUseWaterList.get(i).getWhereToUse();
							}
							else
							{
								sourceType = directUseWaterList.get(i).getWhereToUse();
							}
							water.add(sourceType);
							for (int k = 0; k < arrayWeek.size() - 1; k++)
							{
								Float wdata = getWaterData(arrayWeek.get(k), arrayWeek.get(k + 1), graphResourceType,
										directUseWaterList.get(i).getWhereToUse());
								water.add(wdata);
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("tpr"))
				{
					List<WastewaterTreatment> wasteWaterTreatmentList = wastewaterTreatmentServices.findAll();
					Float actualReading, energyReading;
					RegularTreatmentData regularTreatment;
					if (!wasteWaterTreatmentList.isEmpty())
					{
						for (int i = 0; i < wasteWaterTreatmentList.size(); i++)
						{
							series = new ArrayList<>();
							series.add(wasteWaterTreatmentList.get(i).getLabel() + "-Source Reading");
							energy = new ArrayList<>();
							energy.add(wasteWaterTreatmentList.get(i).getLabel() + "-Energy Reading");
							for (int j = 0; j < arrayWeek.size() - 1; j++)
							{
								actualReading = 0.0f;
								energyReading = 0.0f;
								regularTreatment = regularTreatmentDataServices.getActualReadingByWWTIdAndBetweenDate(
										wasteWaterTreatmentList.get(i).getWastewaterTreatmentId(), arrayWeek.get(j),
										arrayWeek.get(j + 1));
								if (regularTreatment != null)
								{
									actualReading = regularTreatment.getActualReading();
									energyReading = regularTreatment.getEnergyAvg();
								}
								series.add(actualReading);
								energy.add(energyReading);
							}
							rowData.add(series);
							rowData.add(energy);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("pfw"))
				{
					Float regPreFilter;
					List<Prefilter> preFilterList = prefilterServices.getAllIdAndIsPrifilter();
					if (!preFilterList.isEmpty())
					{
						for (int i = 0; i < preFilterList.size(); i++)
						{
							series = new ArrayList<>();
							series.add("PreFilter-" + preFilterList.get(i).getWaterSource().getSourceName());
							for (int j = 0; j < arrayWeek.size() - 1; j++)
							{
								regPreFilter = regPrefilterServices.getRegPrefilterByIdAndBetweenDates(
										preFilterList.get(i).getPrefilterId(), arrayWeek.get(j), arrayWeek.get(j + 1));
								if (regPreFilter == null)
								{
									regPreFilter = 0.0f;
								}
								series.add(regPreFilter);
							}
							rowData.add(series);
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if (graphTabName.equalsIgnoreCase("Monthly"))
		{
			try
			{
				series = new ArrayList<>();
				series.add(" ");
				for (int i = 1; i <= 12; i++)
				{
					String date = monthName[i];
					series.add(date);
				}
				rowData.add(series);
				if (graphResourceType.equalsIgnoreCase("swr"))
				{
					waterInvSourceName = waterSourceServices.getAllWaterSourceName();
					for (int j = 0; j < waterInvSourceName.size(); j++)
					{
						series = new ArrayList<>();
						series.add(waterInvSourceName.get(j));
						for (int k = 1; k <= 12; k++)
						{
							aReading = regularSourceDataServices.getSumActualByYearMonth(year, k,
									waterInvSourceName.get(j));
							if (aReading == null)
								aReading = 0.0f;
							series.add(aReading);
						}
						rowData.add(series);
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fr"))
				{
					filterNames = multipleFilterServices.getAllFiltersIdAndLabels();
					for (int j = 0; j < filterNames.size(); j++)
					{
						series = new ArrayList<>();
						series.add(filterNames.get(j));
						for (int k = 1; k <= 12; k++)
						{
							aReading = regMultipleFilterDataServices.SumActualReadingByfilterNameYearMonth(
									filterNames.get(j).getFilterLabel(), year, k);
							if (aReading == null)
								aReading = 0.0f;
							series.add(aReading);
						}
						rowData.add(series);
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fru"))
				{

					recycleLabelList = wastewaterTreatmentServices.getAllLabelAndId();
					for (int i = 0; i < recycleLabelList.size(); i++)
					{
						List<WastewaterRecycle> recycledToNameList = wasteWaterRecycleSevices
								.findAllRecycleIdAndLabel(recycleLabelList.get(i).getLabel());
						for (int j = 0; j < recycledToNameList.size(); j++)
						{
							Float actualReading;
							series = new ArrayList<>();
							series.add(recycleLabelList.get(i).getLabel() + "-" + recycledToNameList.get(j).getRecycledTo());
							for (int k = 1; k <= 12; k++)
							{
								actualReading = regWastewaterRecycleServices.getActualReadingByIdandMonth(
										recycledToNameList.get(j).getWastewaterRecycleId(), year, k);
								if (actualReading == null)
									series.add(0);
								else
									series.add(actualReading);
							}
							rowData.add(series);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fwur"))
				{
					filterUseList = filterUseServices.findFilterUseTypeAndlabel();
					for (int i = 0; i < filterUseList.size(); i++)
					{
						series = new ArrayList<>();
						series.add(filterUseList.get(i).getFilterUseLabel());
						for (int k = 1; k <= 12; k++)
						{
							aReading = regFiltersUseDataServices.getSumActualReadingByYearMonthFilterLabel(year, k,
									filterUseList.get(i).getFilterUseLabel());
							if (aReading == null)
								aReading = 0.0f;
							series.add(aReading);
						}
						rowData.add(series);
					}
				}
				else if (graphResourceType.equalsIgnoreCase("swur"))
				{
					directUseWaterList = directUseOfWaterServices.getAllWhereToUseAndIsIndustries();
					if (!Validator.isEmpty(directUseWaterList))
					{
						for (int i = 0; i < directUseWaterList.size(); i++)
						{
							series = new ArrayList<>();
							boolean isIndustrial = directUseWaterList.get(i).isIndustrial();
							if (isIndustrial == true)
							{
								series.add("Industrial-" + directUseWaterList.get(i).getWhereToUse());
							}
							else
							{
								series.add(directUseWaterList.get(i).getWhereToUse());
							}
							for (int k = 1; k <= 12; k++)
							{
								aReading = regDirectUseOfWaterDataServices.actualReadingByYearSourceTypeMonth(year,
										directUseWaterList.get(i).getWhereToUse(), k);
								if (aReading == null)
									aReading = 0.0f;
								series.add(aReading);
							}
							rowData.add(series);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("tpr"))
				{
					List<WastewaterTreatment> wasteWaterTreatmentList = wastewaterTreatmentServices.findAll();

					RegularTreatmentData regularTreatment;
					Float actualReading, energyReading;
					if (!wasteWaterTreatmentList.isEmpty())
					{
						for (int i = 0; i < wasteWaterTreatmentList.size(); i++)
						{
							series = new ArrayList<>();
							series.add(wasteWaterTreatmentList.get(i).getLabel() + "-Source Reading");
							energy = new ArrayList<>();
							energy.add(wasteWaterTreatmentList.get(i).getLabel() + "-Energy Reading");
							for (int k = 1; k <= 12; k++)
							{
								actualReading = 0.0f;
								energyReading = 0.0f;
								regularTreatment = regularTreatmentDataServices.getActualReadingByWWTIdAndYearMonth(
										wasteWaterTreatmentList.get(i).getWastewaterTreatmentId(), year, k);
								if (regularTreatment != null)
								{
									actualReading = regularTreatment.getActualReading();
									energyReading = regularTreatment.getEnergyAvg();
								}
								series.add(actualReading);
								energy.add(energyReading);
							}
							rowData.add(series);
							rowData.add(energy);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("pfw"))
				{
					Float regPreFilter;
					List<Prefilter> preFilterList = prefilterServices.getAllIdAndIsPrifilter();
					if (!preFilterList.isEmpty())
					{
						for (int i = 0; i < preFilterList.size(); i++)
						{
							series = new ArrayList<>();
							series.add("PreFilter-" + preFilterList.get(i).getWaterSource().getSourceName());
							for (int k = 1; k <= 12; k++)
							{

								regPreFilter = regPrefilterServices
										.getRegPrefilterByIdAndMonth(preFilterList.get(i).getPrefilterId(), k);
								if (regPreFilter == null)
								{
									regPreFilter = 0.0f;
								}
								series.add(regPreFilter);
							}
							rowData.add(series);
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if (graphTabName.equalsIgnoreCase("Yearly"))
		{
			try
			{
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
				rowData.add(yearList);
				if (graphResourceType.equalsIgnoreCase("swr"))
				{
					waterInvSourceName = waterSourceServices.getAllWaterSourceName();
					if (!waterInvSourceName.isEmpty())
					{
						for (int x = 0; x < waterInvSourceName.size(); x++)
						{
							water = new ArrayList<>();
							water.add(waterInvSourceName.get(x));
							for (Integer wyear : yearsList)
							{
								aReading = regularSourceDataServices.getSumOfActualReadingByYearAndSourceName(wyear,
										waterInvSourceName.get(x));
								if (aReading != null)
									water.add(aReading);
								else
									water.add(0);
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fr"))
				{
					filterNames = multipleFilterServices.getAllFiltersIdAndLabels();
					if (!filterNames.isEmpty())
					{
						for (int i = 0; i < filterNames.size(); i++)
						{
							water = new ArrayList<>();
							water.add(filterNames.get(i));

							for (Integer wyear : yearsList)
							{
								aReading = regMultipleFilterDataServices
										.SumActualReadingByfilterNameYear(filterNames.get(i).getFilterLabel(), wyear);
								if (aReading != null)
									water.add(aReading);
								else
									water.add(0);
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fru"))
				{
					recycleLabelList = wastewaterTreatmentServices.getAllLabelAndId();
					for (int i = 0; i < recycleLabelList.size(); i++)
					{
						List<WastewaterRecycle> recycledToNameList = wasteWaterRecycleSevices
								.findAllRecycleIdAndLabel(recycleLabelList.get(i).getLabel());
						for (int j = 0; j < recycledToNameList.size(); j++)
						{
							series = new ArrayList<>();
							series.add(recycleLabelList.get(i).getLabel() + "-" + recycledToNameList.get(j).getRecycledTo());
							for (Integer wyear : yearsList)
							{
								aReading = regWastewaterRecycleServices
										.getActualReadingByIdAndYear(recycledToNameList.get(j).getWastewaterRecycleId(), wyear);
								if (aReading != null)
									series.add(aReading);
								else
									series.add(0);
							}
							rowData.add(series);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("fwur"))
				{
					filterUseList = filterUseServices.findFilterUseTypeAndlabel();
					for (int i = 0; i < filterUseList.size(); i++)
					{
						water = new ArrayList<>();
						water.add(filterUseList.get(i).getFilterUseType() + "-" + filterUseList.get(i).getFilterUseLabel());
						for (Integer wyear : yearsList)
						{
							aReading = regFiltersUseDataServices.sumActualReadingByYearAndFilterUseLabel(wyear,
									filterUseList.get(i).getFilterUseLabel());
							if (aReading != null)
								water.add(aReading);
							else
								water.add(0);
						}
						rowData.add(water);
					}
				}
				else if (graphResourceType.equalsIgnoreCase("swur"))
				{
					String sourceType = null;
					directUseWaterList = directUseOfWaterServices.getAllWhereToUseAndIsIndustries();
					if (!directUseWaterList.isEmpty())
					{
						for (int i = 0; i < directUseWaterList.size(); i++)
						{
							sourceType = directUseWaterList.get(i).getWhereToUse();
							water = new ArrayList<>();
							water.add(sourceType);
							for (Integer wyear : yearsList)
							{
								aReading = regDirectUseOfWaterDataServices.getSumOfActualReadingByYearAndSType(wyear,
										sourceType);
								if (aReading != null)
									water.add(aReading);
								else
									water.add(0);
							}
							rowData.add(water);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("tpr"))
				{
					List<WastewaterTreatment> wasteWaterTreatmentList = wastewaterTreatmentServices.findAll();

					RegularTreatmentData regularTreatment;
					if (!wasteWaterTreatmentList.isEmpty())
					{
						for (int i = 0; i < wasteWaterTreatmentList.size(); i++)
						{
							series = new ArrayList<>();
							series.add(wasteWaterTreatmentList.get(i).getLabel() + "-Source Reading");
							energy = new ArrayList<>();
							energy.add(wasteWaterTreatmentList.get(i).getLabel() + "-Energy Reading");
							for (Integer wyear : yearsList)
							{
								regularTreatment = regularTreatmentDataServices.getActualReadingByWWTIdAndYear(
										wasteWaterTreatmentList.get(i).getWastewaterTreatmentId(), wyear);
								if (regularTreatment != null)
								{
									series.add(regularTreatment.getActualReading());
									energy.add(regularTreatment.getEnergyAvg());
								}
							}
							rowData.add(series);
							rowData.add(energy);
						}
					}
				}
				else if (graphResourceType.equalsIgnoreCase("pfw"))
				{
					Float regPreFilter;
					List<Prefilter> preFilterList = prefilterServices.getAllIdAndIsPrifilter();
					if (!preFilterList.isEmpty())
					{
						for (int i = 0; i < preFilterList.size(); i++)
						{
							series = new ArrayList<>();
							series.add("PreFilter-" + preFilterList.get(i).getWaterSource().getSourceName());

							for (Integer wyear : yearsList)
							{
								regPreFilter = regPrefilterServices
										.getRegPrefilterByIdAndYear(preFilterList.get(i).getPrefilterId(), wyear);

								if (regPreFilter != null)
									series.add(regPreFilter);
								else
									series.add(0);
							}
							rowData.add(series);
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return rowData;
	}

	/**
	 * This method used to get actual water data between 2 days.
	 * 
	 * @param fWeek the first week
	 * @param sWeek the second week
	 * @param type the type of product
	 * @param sName the name of sewage
	 * @return aReading it return actual reading.
	 */
	private Float getWaterData(String fWeek, String sWeek, String type, String sName)
	{
		Float aReading = 0.0f, x = 0.0f;
		try
		{
			if (type.contains("swr"))
			{
				try
				{
					x = regularSourceDataServices.getSumActualReadingBySourceNameAndBetweenDates(sName, fWeek, sWeek);
					if (x != null)
					{
						aReading = aReading + x;
					}
				}
				catch (Exception e)
				{
					aReading = 0.0f;
				}
			}
			else if (type.contains("fr"))
			{
				try
				{
					x = regMultipleFilterDataServices.getSumActualReadingByFilterNameBetweenDates(sName, fWeek, sWeek);
					if (x != null)
					{
						aReading = aReading + x;
					}
				}
				catch (Exception e)
				{
					aReading = 0.0f;
				}
			}
			else if (type.contains("fwur"))
			{
				try
				{
					x = regFiltersUseDataServices.getSumActualReadingByFilterLabelAndDatesBetween(sName, fWeek, sWeek);
					if (x != null)
					{
						aReading = aReading + x;
					}
				}
				catch (Exception e)
				{
					aReading = 0.0f;
				}
			}
			else if (type.contains("swur"))
			{
				try
				{
					x = regDirectUseOfWaterDataServices.getActualReadingBySourceTypeAndBetweenDate(sName, fWeek, sWeek);
					if (x != null)
					{
						aReading = aReading + x;
					}
				}
				catch (Exception e)
				{
					aReading = 0.0f;
				}
			}
			else if (type.contains("tpr"))
			{
				try
				{
					if (x != null)
					{
						aReading = aReading + x;
					}
				}
				catch (Exception e)
				{
					aReading = 0.0f;
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return aReading;
	}

	// new graph function

	@PostMapping(value = "ajax-getResourcesGraph")
	public @ResponseBody List<Object> getResourcesGraph(
			@RequestParam(value = "graphTabName", required = false) String graphTabName,
			@RequestParam(value = "graphDate", required = false) String graphDate,
			@RequestParam(value = "graphUnit", required = false) String graphUnit,
			@RequestParam(value = "graphResourceType", required = false) String graphResourceType)
			throws ParseException
	{
		List<String> getAllProductName;
		List<Object> data;
		data = new ArrayList<>();
		String getunit = null;
		ArrayList<Object> series = new ArrayList<>();
		Float quantity = 0.0f;
		int quan = 0;
		String[] monthName = {" ", "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov",
				"Dec"};

		int day = Utilities.getDayFromStringDate(graphDate);
		int month = Utilities.getMonthFromStringDate(graphDate);
		int year = Utilities.getYearFromStringDate(graphDate);
		int currentYear = year;
		int prevYear = currentYear - 1;
		try
		{
			if (graphUnit.equalsIgnoreCase("NA"))
			{
				getAllProductName = allProductNameServices.getProductNameByTypeAndDate(graphDate, graphUnit);
			}
			else
			{
				getAllProductName = allProductNameServices.getProductNameByTypeDateAndUnit(graphDate, graphResourceType,
						graphUnit);
			}
			if (graphTabName.equalsIgnoreCase("DailyTab"))
			{
				series.add("Days");
				Float regQuantity = 0.0f;
				String pName = "";
				series.add(monthName[month] + " " + day);
				data.add(series);
				if (!getAllProductName.isEmpty())
				{
					for (int i = 0; i < getAllProductName.size(); i++)
					{
						series = new ArrayList<>();
						pName = getAllProductName.get(i);
						if (graphUnit.equalsIgnoreCase("NA"))
						{
							series.add("Consent value of " + pName + " / Day");
							getunit = unitServices.getUnitByDateTypeAndProductName(graphDate, graphResourceType, pName);
							if (getunit != null)
							{
								graphUnit = getunit;
							}
							quantity = getConsentData(pName, graphUnit, graphTabName);
							quan = (int) Math.round(quantity);
							if (quantity != null)
							{
								series.add(quan);
							}
						}
						else
						{
							series.add("Consent value of " + pName + " / Day");
							quantity = getConsentData(pName, graphUnit, graphTabName);
							quan = (int) Math.round(quantity);
							if (quantity != null)
							{
								series.add(quan);
							}
						}
						data.add(series);
						regQuantity = regularDataServices.quantityByProductNameDate(pName, graphDate);
						series = new ArrayList<>();
						if (regQuantity != null)
						{
							series.add(pName + " in " + graphUnit);
							series.add(regQuantity);
							data.add(series);
						}
						else
						{
							String res[] = graphUnit.split("/");
							series.add(pName + " in " + res[0] + "/Day");
							series.add(0);
							data.add(series);
						}
					}
				}
			}
			if (graphTabName.equalsIgnoreCase("Back7Days"))
			{
				ArrayList<String> daysList = new ArrayList<>();
				daysList.add("Days");
				daysList.addAll(Utilities.getDaysList(graphDate, 7));
				data.add(daysList);

				if (!Validator.isEmpty(getAllProductName))
				{
					for (int i = 0; i < getAllProductName.size(); i++)
					{
						if (!getAllProductName.get(i).equalsIgnoreCase("NA"))
						{
							String productName = getAllProductName.get(i);
							Float eachDay = allProductsServices.findOneByGetConsentData(productName, graphDate);
							String res[] = graphUnit.split("/");
							if (eachDay == null)
								eachDay = 0.0f;
							quan = (int) Math.round(eachDay);
							if (eachDay > 0)
							{
								eachDay = Utilities.convertDataToByUnitAsPerYrMnthDay(graphUnit, eachDay, 7);
								quan = (int) Math.round(eachDay);
							}

							// ADD CONSENTED VALUE TO ARRAY
							series = new ArrayList<>();
							series.add("consent value " + productName);
							for (int days = 0; days <= 7; days++)
							{
								series.add(quan);
							}
							data.add(series);
							series = new ArrayList<>();
							series.add(productName + " in " + res[0] + "/Day");
							for (int days = 0; days <= 7; days++)
							{
								Calendar cal = new GregorianCalendar(year, month - 1, day);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								cal.add(Calendar.DAY_OF_WEEK, -days);
								String date = sdf.format(cal.getTime());
								quantity = regularDataServices.quantityByProductNameDate(productName, date);
								if (quantity == null)
									quantity = 0.0f;
								series.add(quantity);
							}
							data.add(series);
						}
					}
				}
			}
			else if (graphTabName.equalsIgnoreCase("Weekly"))
			{
				series.add("Month");
				List<String> arrayWeek = Utilities.getWeeklyArrayByDateOfMonth(graphDate);
				for (int i = 0; i < arrayWeek.size() - 1; i++)
				{
					String weekCnt = "Week" + (i + 1);
					series.add(weekCnt);
				}
				data.add(series);
				if (!getAllProductName.isEmpty())
				{
					for (int j = 0; j < getAllProductName.size(); j++)
					{
						series = new ArrayList<>();
						String pName = getAllProductName.get(j);
						series.add("Consent value of " + pName + "\t\tper Week");
						if (graphUnit != "NA")
						{
							quantity = getConsentData(pName, graphUnit, graphTabName);
							quan = (int) Math.round(quantity);
							if (quantity != null)
							{
								for (int i = 1; i <= arrayWeek.size() - 1; i++)
								{
									series.add(quan);
								}
								data.add(series);
							}
						}
						else
						{
							getunit = unitServices.getUnitByDateTypeAndProductName(graphDate, graphResourceType, pName);
							if (getunit != null)
							{
								graphUnit = getunit;
							}
							quantity = getConsentData(pName, graphUnit, graphTabName);
							quan = (int) Math.round(quantity);
							if (quantity != null)
							{
								for (int i = 1; i < arrayWeek.size() - 1; i++)
								{
									series.add(quan);
								}
								data.add(series);
							}
						}
						series = new ArrayList<>();
						String res[] = graphUnit.split("/");
						series.add(pName + " in " + res[0] + "/Week");
						for (int i = 0; i < arrayWeek.size() - 1; i++)
						{
							quantity = regularDataServices.getAverageQuantityByPNameAndBetweenDates(pName,
									arrayWeek.get(i), arrayWeek.get(i + 1));
							if (quantity != null)
							{
								series.add(quantity);
							}
							else
							{
								series.add(0);
							}
						}
						data.add(series);
					}
				}
			}
			else if (graphTabName.equalsIgnoreCase("Monthly"))
			{
				String month1 = "";
				int month2 = 0;
				series = new ArrayList<>();
				series.add(" ");
				for (int i = 1; i <= 12; i++)
				{
					if (i >= 1 && i <= 9)
					{
						month1 = "0" + i;
						month2 = Integer.valueOf(month1);
					}
					else
					{
						month2 = i;
					}
					String date = monthName[month2];
					series.add(date);
				}
				data.add(series);
				Calendar calTwo = new GregorianCalendar(year, 11, 31);
				int day1 = calTwo.get(Calendar.DAY_OF_YEAR);
				if (!Validator.isEmpty(getAllProductName))
				{
					for (int i = 0; i < getAllProductName.size(); i++)
					{
						if (!getAllProductName.get(i).equalsIgnoreCase("NA"))
						{
							String productName = getAllProductName.get(i);
							Float eachDay = allProductsServices.findOneByGetConsentData(productName, graphDate);
							String res[] = graphUnit.split("/");
							if (eachDay == null)
								eachDay = 0.0f;
							quan = (int) Math.round(eachDay);
							if (eachDay > 0)
							{
								eachDay = Utilities.convertDataToByUnitAsPerYrMnthDay(graphUnit, eachDay, day1);
								quan = (int) Math.round(eachDay);

							}
							// ADD CONSENTED VALUE TO ARRAY
							series = new ArrayList<>();
							series.add("consent value " + productName + "\t\tper Month");
							for (int days = 1; days <= 12; days++)
							{
								series.add(quan);
							}
							data.add(series);
							series = new ArrayList<>();
							series.add(productName + " in " + res[0] + "/Month");
							for (int days = 1; days <= 12; days++)
							{
								quantity = regularDataServices.getAverageQuantityByPNameMonthYear(productName, days,
										year);
								if (quantity == null)
									quantity = 0.0f;
								series.add(quantity);
							}
							data.add(series);
						}
					}
				}
			}
			else if (graphTabName.equalsIgnoreCase("Yearly"))
			{
				List<Integer> regularDataYearList = new ArrayList<>();
				regularDataYearList.add(currentYear);
				regularDataYearList.add(prevYear);
				series.add("Year");
				for (int x = 0; x < regularDataYearList.size(); x++)
				{
					series.add(regularDataYearList.get(x));
				}
				data.add(series);
				if (!Validator.isEmpty(getAllProductName))
				{
					for (int i = 0; i < getAllProductName.size(); i++)
					{
						if (!getAllProductName.get(i).equalsIgnoreCase("NA"))
						{
							String productName = getAllProductName.get(i);
							Float eachDay = allProductsServices.findOneByGetConsentData(productName, graphDate);
							String res[] = graphUnit.split("/");
							if (eachDay == null)
								eachDay = 0.0f;
							quan = (int) Math.round(eachDay);
							if (eachDay > 0)
							{
								eachDay = Utilities.convertDataToByUnitAsPerYrMnthDay(graphUnit, eachDay, 0);
								quan = (int) Math.round(eachDay);
							}
							// ADD CONSENTED VALUE TO ARRAY
							series = new ArrayList<>();
							series.add("consent value " + productName + "\t\tper Year");
							for (int days = 0; days < regularDataYearList.size(); days++)
							{
								series.add(quan);
							}
							data.add(series);
							series = new ArrayList<>();
							series.add(productName + " in " + res[0] + "/Day");
							for (int days = 0; days < regularDataYearList.size(); days++)
							{
								int year1 = regularDataYearList.get(days).intValue();
								quantity = regularDataServices.getAverageQuantityByPNameAndYear(productName, year1);
								if (quantity == null)
									quantity = 0.0f;
								series.add(quantity);
							}
							data.add(series);
						}
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

}
