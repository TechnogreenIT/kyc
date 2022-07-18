package com.tes.controller.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.utilities.Utilities;

/**
 * This class demonstrate used to View all the performance data and Manage by Management.
 * 
 * @author Jemish Moradiya
 */
@Controller
@RequestMapping(value = {"/management"})
public class ManagementPerformanceController
{

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	private static final Logger LOGGER = LogManager.getLogger(ManagementPerformanceController.class);

	/**
	 * This method used to display the performance in different category i.e.product, byproduct, raw material, fuel, hazordous waste process,
	 * hazordous waste from PCF, Non-hazordous waste process, Non-hazordous waste from PCF etc.
	 * 
	 * @param performanceName The name of the performace data.
	 * @param request The servlet request we are processing.
	 * @return performanceName, performanceTitle, consentYears
	 */
	@RequestMapping("management-performance")
	public ModelAndView manPerformance(@RequestParam("performancefor") String performanceName, HttpServletRequest request)
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
	 * This method used to Display the water performance.
	 * 
	 * @return water-performance
	 */
	@RequestMapping("management-water-performance")
	public ModelAndView getWaterPerformance()
	{

		ModelAndView modelAndView = new ModelAndView();
		try
		{
			modelAndView.setViewName("EnvrOfficer/WaterPerformance");

			List<Integer> regDatesList = new ArrayList<>();
			List<Integer> SewEffPollYearList = new ArrayList<>();
			List<Integer> regSourceYearList = new ArrayList<>();

			int regEffminYear = 0, regSewminYear = 0, finalMinYear = 0, minRegSourceYear = 0;
			String dateToSend[] = Utilities.getTodaysDate().split("-");
			int currentYear = Integer.parseInt(dateToSend[0]);

			try
			{
				minRegSourceYear = 0;
				// Effected By Water Inventory ........by sushama
				// minRegSourceYear = regularSourceDataServices.minRegSourceYear();
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}

			if (minRegSourceYear == 0)
			{

			}
			else
			{

				int maxYearDiff = currentYear - minRegSourceYear;
				maxYearDiff = maxYearDiff + 1;
				for (int i = 1; i <= maxYearDiff; i++)
				{
					regSourceYearList.add(currentYear);
					currentYear = currentYear - 1;
				}
			}

			try
			{
				regDatesList = regEffPollServices.getYearFromEffluent();
				regEffminYear = Collections.min(regDatesList);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}

			try
			{
				regDatesList = regSewPollServices.getYearFromSewage();
				regSewminYear = Collections.min(regDatesList);
			}
			catch (Exception e)
			{
				LOGGER.error(e);
			}

			if (regEffminYear > regSewminYear)
			{

				finalMinYear = regEffminYear;

			}
			else if (regSewminYear > regEffminYear)
			{
				finalMinYear = regSewminYear;
			}
			else
			{
				finalMinYear = 0;
			}

			if (finalMinYear == 0)
			{

			}
			else
			{
				int currentYear1 = Integer.parseInt(dateToSend[0]);
				// SewEffPollYearList.add(currentYear1);
				int maxYearDiff = currentYear1 - finalMinYear;
				maxYearDiff = maxYearDiff + 1;
				for (int i = 1; i <= maxYearDiff; i++)
				{
					SewEffPollYearList.add(currentYear1);
					currentYear1 = currentYear1 - 1;
				}
			}
			int isETP = 0, isSTP = 0, isBoth = 0, flagWater = 0;

			// code to check ETP / STP for water Performance > percentage non-compliance

			// Check ETP availability
			try
			{
				isETP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("ETP");
				if (isETP > 0)
					isETP = 1;
				flagWater = 1;
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
				flagWater = 1;
			}
			catch (Exception e)
			{
				LOGGER.error(e);
			}

			if (isETP == 1 && isSTP == 1)
				isBoth = 1;
			flagWater = 1;

			modelAndView.addObject("isETP", isETP);
			modelAndView.addObject("isSTP", isSTP);
			modelAndView.addObject("isBoth", isBoth);
			modelAndView.addObject("flagWater", flagWater);
			modelAndView.addObject("SewEffPollYearList", SewEffPollYearList);
			modelAndView.addObject("regSourceYearList", regSourceYearList);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}
}
