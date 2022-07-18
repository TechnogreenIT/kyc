package com.tes.controller.environmentalofficer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tes.model.EmpData;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.utilities.Constant;

/**
 * This class used to save daily data and graph data.
 * 
 * @author Tushar Chowgule
 */
@Controller
public class SaveDataController
{

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	RegMultipleFilterDataServices regularFiltersDataServices;

	@Autowired
	RegFiltersUseDataServices regularFiltersUseDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	private static final Logger LOGGER = LogManager.getLogger(SaveDataController.class);

	/**
	 * This method used to update the product data i.e.source, filter etc
	 * 
	 * @param action
	 * @param request The servlet request we are processing.
	 * @param waterdateId the water date Id
	 * @param type the type of product
	 * @param startReading the start reading
	 * @param endReading the end reading
	 * @param avgReading the average reading
	 * @return flag it return true/ false
	 */
	@PostMapping(value = "/ajax-dailydata-SaveDataI")
	public @ResponseBody String dailyRegularData2(@RequestParam("action") String action, HttpServletRequest request,
			@RequestParam(value = "id", required = false) int waterdateId, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "start", required = false) float startReading,
			@RequestParam(value = "end", required = false) float endReading, @RequestParam(value = "avg", required = false) float avgReading)
	{
		String flag = null;
		try
		{

			int result = 0;

			EmpData empdata = (EmpData) request.getSession().getAttribute("empDataSession");
			int companyId = empdata.getCompanyProfile().getCompanyProfileId();
			if (action.equalsIgnoreCase("getProduction"))
			{
				String isproduction = Constant.YES;
				String industryType = null;
				String industry = companyProfileServices.getCategoryFromCompany(companyId);
				String iparr[] = industry.split("-");
				industryType = iparr[0].trim();
				if (industryType == "Industry")
				{
					isproduction = Constant.YES;
				}
				else
				{
					isproduction = Constant.NO;
				}
				flag = isproduction;
			}
			else if (action.equalsIgnoreCase("SaveEachWaterData"))
			{

				// Update regular_Source_Data
				if (type.equalsIgnoreCase("source"))
				{
					// Effected By Water Inventory ........by sushama
					result = 0;
					// result=regularSourceDataServices.updateStartEndActualReading(startReading, endReading, avgReading, waterdateId);
				}

				// Update regular_filter_Data
				// Effected By Water Inventory ........by vishal
				/*
				 * else if(type.equalsIgnoreCase("filter")){
				 * result=regularFiltersDataServices.updateStartEndingActualReading(startReading, endReading, avgReading, waterdateId);
				 * }
				 */

				// update regular_use_filter_use_data
				else if (type.equalsIgnoreCase("filter_type"))
				{
					// Effected By Water Inventory ........by vishal
					result = 0;// regularFiltersUseDataServices.updateStartEndAvgReading(startReading, endReading, avgReading, waterdateId);
				}

				// Update Regular Water use data
				else if (type.equalsIgnoreCase("source_use"))
				{
					// Effected By Water Inventory ........by vishal
					result = 0;// regularWaterUseDataServices.updateStartEndActualReading(startReading, endReading, avgReading, waterdateId);
				}

				// Update Regular treatment
				else if (type.equalsIgnoreCase("treatment"))
				{
					// result=regularTreatmentDataServices.updateStartEndActualreading(startReading, endReading, avgReading, waterdateId);
				}
				else if (action.equalsIgnoreCase("SaveRegularAPCData"))
				{

				}
				else if (action.equalsIgnoreCase("SaveRegularWaterData"))
				{

				}
				else if (action.equalsIgnoreCase("SaveRegularData"))
				{

				}
				else if (action.equalsIgnoreCase("fileUploading"))
				{

				}
				else
				{

				}
				if (result > 0)
					flag = "true";
				else
					flag = "false";
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * This method used to update staff data .
	 * 
	 * @param action it return daily data is save
	 * @param id the ID of the Staff Id
	 * @param staff the staff details
	 * @return flag it return count acording to number of row inserted
	 */
	@RequestMapping("/ajax-dailydata-SaveData")
	public @ResponseBody String saveStaffData(@RequestParam("action") String action, @RequestParam(value = "id") String id, @RequestParam(value = "staff") int staff)
	{
		String flag = "false";
		int result = 0;
		try
		{
			if (action.equalsIgnoreCase("SaveStaffData"))
			{
				String ids[] = id.split(",");
				for (int i = 0; i < ids[0].length(); i++)
				{
					String as = ids[0];
					int asd = Integer.parseInt(as);

					// Effected By Water Inventory ........by sushama
					result = 0;
					// result= regularSourceDataServices.saveRegularSourceData(staff, asd);
					if (result == 1)
						flag = "true";
					else
						flag = "false";
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return flag;
	}

	/**
	 * This method uesd to display the industry data..
	 * 
	 * @param action it return save graph data.
	 * @param request the servlet request we are processing.
	 * @param session session from which the locale should be guessed
	 * @return isProduction
	 */
	@GetMapping(value = "/ajax-graph-SaveData")
	public @ResponseBody String dailyRegularData2(@RequestParam("action") String action, HttpServletRequest request, HttpSession session)
	{
		String isProduction = "NA";
		try
		{
			if (action.equalsIgnoreCase("getProduction"))
			{
				String industryType = "NA";
				EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				String industryCategory = empDataSession.getCompanyProfile().getIndustryCategory();
				String[] iparr = industryCategory.split("-");
				industryType = iparr[0].trim();
				if (industryType.equalsIgnoreCase("Industry"))
					isProduction = "Yes";
				else
					isProduction = "No";
			}
		}
		catch (Exception e)
		{
			isProduction = "null";
			LOGGER.error(e);
		}
		return isProduction;
	}

	// @RequestMapping(value = "redirectToLogout")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// public void logout(HttpSession session) {
	// session.invalidate();
	//
	// }

}
