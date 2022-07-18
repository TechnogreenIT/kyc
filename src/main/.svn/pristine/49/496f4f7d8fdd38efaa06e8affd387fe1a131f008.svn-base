package com.tes.controller.management;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Utilities;

/**
 * This class demonstrate used to View all the statistics data and Manage by Management
 * 
 * @author Jemish Moradiya
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class ManagementStaticsController
{
	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	WaterSourceServices waterSourceServices;
	private static final Logger LOGGER = LogManager.getLogger(ManagementStaticsController.class);

	/**
	 * This method used to display the statistics different category i.e.product, byproduct, raw material, fuel, hazordous waste process,
	 * hazordous waste from PCF, Non-hazordous waste process, Non-hazordous waste from PCF etc.
	 * 
	 * @param statiticsName The name of the statistics data.
	 * @param request The servlet request we are processing.
	 * @return Statitics data
	 */
	// @RequestMapping(value = "statistics", method = RequestMethod.GET)
	// public ModelAndView getStatitics(@RequestParam("statisticsfor") String statiticsName, HttpServletRequest request) {
	//
	// int consentMinYear = 0;
	// List<String> allUnits = new ArrayList<>();
	// List<Integer> consentYears = new ArrayList<>();
	//
	// byte[] valueDecoded = Base64.decodeBase64(statiticsName);
	// String decodedStatiticsName = new String(valueDecoded);
	//
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.setViewName("EnvrOfficer/Statitics");
	//
	// String dateToSend[] = Utilities.getTodaysDate().split("-");
	// int currentYear = Integer.parseInt(dateToSend[0]);
	//
	// try {
	// consentMinYear = regularDataServices.regDataMinYear();
	// } catch(Exception e) {
	// LOGGER.error(e);
	// }
	//
	// if (consentMinYear == 0) {
	//
	// } else {
	//
	// int maxYearDiff = currentYear - consentMinYear;
	// maxYearDiff = maxYearDiff + 1;
	// for (int i = 1; i <= maxYearDiff; i++) {
	// consentYears.add(currentYear);
	// currentYear = currentYear-1;
	// }
	// }
	//
	// // code optimazation remaining >> JEMSS
	//
	// try {
	// allUnits = allProductsServices.findGetunitByProductType(decodedStatiticsName);
	// }catch(Exception e) {
	// LOGGER.error(e);
	// }
	//
	// modelAndView.addObject("statiticsName", decodedStatiticsName);
	// modelAndView.addObject("consentYears", consentYears);
	// modelAndView.addObject("allUnits", allUnits);
	// return modelAndView;
	// }

	/**
	 * This method used to manage the water consumption data.
	 * 
	 * @return getWaterConsumptionDetails
	 */
	@RequestMapping("management-water-consumption")
	private ModelAndView getWaterConsumptionDetails()
	{
		ModelAndView modelAndView;
		modelAndView = new ModelAndView();
		try
		{

			List<Integer> regSourceYearsArrayList = new ArrayList<>();
			int regSourceDataMinYear = 0;
			int currentYear = Integer.parseInt(Utilities.getTodaysDate().split("-")[0]);

			try
			{
				// Effected By Water Inventory ........by sushama
				regSourceDataMinYear = 0;
				// regSourceDataMinYear = regularSourceDataServices.minRegSourceYear();
			}
			catch (Exception e)
			{
				LOGGER.error(e);
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
			// Effected By Water Inventory ........by vishal
			/*
			 * for (String sourceName : waterSourceServices.getAllWaterSourceData()) {//get
			 * sourceName sourceNameArray.add(Utilities.removeSpace(sourceName)) ; }
			 */

			// tab2 filters
			ArrayList<String> filterNameArray = new ArrayList<String>();
			ArrayList<String> filterUseArray = new ArrayList<String>();
			// Effected By Water Inventory ........by vishal
			/*
			 * for (String filterName : filtersServices.getAllDistFiltersName()) {
			 * String tempFilterName = Utilities.removeSpace(filterName);
			 * filterNameArray.add(tempFilterName);
			 * for(String filterUse : filtersServices.getFiltersUseByFilterName(filterName)) {
			 * filterUseArray.add(tempFilterName+"-"+Utilities.removeSpace(filterUse));
			 * }
			 * }
			 */

			modelAndView.setViewName("EnvrOfficer/WaterConsumption");
			modelAndView.addObject("todaysDate", Utilities.getTodaysDate());
			modelAndView.addObject("regSourceYears", regSourceYearsArrayList);
			modelAndView.addObject("sourceName", sourceNameArray);
			modelAndView.addObject("filterName", filterNameArray);
			modelAndView.addObject("filterUse", filterUseArray);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to manage the waste water.
	 * 
	 * @return WasteWater
	 */
	@RequestMapping("management-waste-water")
	public ModelAndView getWasteWater()
	{
		return new ModelAndView("Management/WasteWater");
	}

}
