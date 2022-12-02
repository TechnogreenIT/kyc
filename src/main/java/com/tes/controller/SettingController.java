package com.tes.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.EmpData;
import com.tes.model.Settings;
import com.tes.services.SettingServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.EmpDataServices;

/**
 * This class used to manage setting form.
 * 
 * @author sushama Dadas
 */
@Controller
@SessionAttributes("ImageId")
@RequestMapping(value = {"/thirdParty", "/admin", "/env","/management"})
public class SettingController
{
	@Autowired
	SettingServices settingServices;
	@Autowired
	EmpDataServices empDataServices;
	@Autowired
	UsersServices usersServices;
	private static final Logger LOGGER = LogManager.getLogger(SettingController.class);

	/**
	 * This method used to open setting form.
	 * 
	 * @param actionStatus it represent pojo
	 * @param status it active or not
	 * @param title
	 * @param request the servlet request we are processing.
	 * @return settingdata
	 */
	@RequestMapping(value = "settings")
	@ResponseBody
	public ModelAndView getAdminsettings(@RequestParam(value = "actionStatus", required = false) String actionStatus,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "title", required = false) String title, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{

			modelAndView.setViewName("Admin/Settings");
			EmpData empdata = (EmpData) request.getSession().getAttribute("empDataSession");

			int userId = empdata.getUsers().getUsersId();
			Settings settingdata = settingServices.findSettingData(userId);

			modelAndView.addObject("settingData", settingdata);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to change status of video.
	 * 
	 * @param actionStatus the actionStatus value.
	 * @param status active or not status
	 * @param title the title
	 * @param request the servlet request we are processing.
	 * @return it returns success or fail
	 */
	@RequestMapping("/ajax-changeStatus")
	public @ResponseBody String getIntroductoryStatus(@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "title", required = false) String title, HttpServletRequest request)
	{
		String returnStatus = "error";

		try
		{
			int userId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);

			if (title.equalsIgnoreCase("Introductory Video"))
				settingServices.updateIntroStatus(status, userId);

			returnStatus = status + "-" + title;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return returnStatus;
	}

	/**
	 * This method used to change background image and colour
	 * 
	 * @param action the changeTheme value
	 * @param pval return background image Id.
	 * @param txtColor return text colour value
	 * @param request the servlet request we are processing.
	 * @return it returns imgvalue id
	 */
	@RequestMapping("/ajax-changeTheme")
	public @ResponseBody int getChangeTheme(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "pval", required = false) int pval,
			@RequestParam(value = "txtColor", required = false) String txtColor, HttpServletRequest request)
	{

		int userId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
		if (action != null)
		{
			settingServices.updateBkImageAndColor(pval, txtColor, userId);
		}

		Settings settingsdata = settingServices.findSettingData(userId);
		int imgvalue = settingsdata.getBackgroundImage();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Admin/AdminDashBoard");
		modelAndView.addObject("settingdataa", imgvalue);
		modelAndView.addObject("settingsdata", settingsdata);
		modelAndView.addObject("ImageId", imgvalue);
		return imgvalue;
	}

	/*
	 * @RequestMapping("/admin-openBirthdayVideo") public @ResponseBody String
	 * getopenBirthdayVideo(@RequestParam(value = "action", required = false) String
	 * action, HttpServletRequest request) { EmpData empdata = (EmpData)
	 * request.getSession().getAttribute("empDataSession"); int userId =
	 * empdata.getUsers().getUsersId();
	 * Settings settingdata = null; String bdaystatus = null; if (action != null) {
	 * LocalDateTime now = LocalDateTime.now(); DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd"); String today =
	 * now.format(formatter); DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd"); Date date = new Date(); EmpData empdatabday =
	 * empDataServices.findEmpNameByBirthDay(userId, dateFormat.format(date)); //
	 * EmpData empdatabday = empDataServices.findEmpNameByBirthDay(userId, today);
	 * if (empdatabday != null) { settingdata =
	 * settingServices.findSettingData(userId); bdaystatus =
	 * settingdata.getBirthdayVideoStatus(); } else { bdaystatus = "OFF"; }
	 * } return bdaystatus;
	 * }
	 */

	/**
	 * This method used to close video window.
	 * 
	 * @param action
	 * @param status return the ON /Off value
	 * @param title return type of video
	 * @param request the servlet request we are processing.
	 */
	@RequestMapping("ajax-closeVideo")
	public void getcloseVideo(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "title", required = false) String title, HttpServletRequest request)
	{

		if (status.equals("OFF"))
		{
			if (title.equals("Birthday Video"))
			{
				status = "OFF";

			}
		}
	}

	/**
	 * This method used to Play Indroduction video.
	 * 
	 * @param action the Introduction data
	 * @param request the servlet request we are processing.
	 * @return it returns status of introstatus
	 */
	@RequestMapping("ajax-playIntroVideo")
	public @ResponseBody String getIntroStatus(@RequestParam(value = "action", required = false) String action,
			HttpServletRequest request)
	{
		int userId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);

		String introstatus = null;
		if (action != null)
		{
			Settings settingdata = null;
			try
			{
				settingdata = settingServices.findSettingData(userId);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				introstatus = settingdata.getIntroductoryVideoStatus();
			}
			catch (Exception e)
			{
				LOGGER.error(e);
			}

		}

		return introstatus;

	}

}
