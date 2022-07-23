package com.tes.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.EmpData;
import com.tes.model.Settings;
import com.tes.services.SettingServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.EmpDataServices;

/**
 * This class used to Manage the change password Form
 * 
 * @author Sushama Dadas
 */
@Controller
@RequestMapping(value = {"/admin", "/env"})
// fdsgfgg
public class ChangePasswordController
{
	@Autowired
	EmpDataServices empDataServices;
	@Autowired
	SettingServices settingServices;
	@Autowired
	UsersServices usersServives;
	private static final Logger LOGGER = LogManager.getLogger(ChangePasswordController.class);

	/**
	 * This method used to open change password Form.
	 * 
	 * @param model
	 * @param error
	 * @param request the servlet request we are processing.
	 * @return change-password page
	 */
	@RequestMapping("change-password")
	public ModelAndView changePasswordurl(Model model, String error, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			modelAndView.setViewName("Admin/ChangePassword");
			int usersId = usersServives.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			Settings settingsdata = settingServices.findSettingData(usersId);
			int imgvalue = settingsdata.getBackgroundImage();
			modelAndView.addObject("imgvalue", imgvalue);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to check old password and change current password
	 * 
	 * @param type the save and check value
	 * @param password the password
	 * @param request the servlet request we are processing.
	 * @return result it returns value of result
	 */
	@RequestMapping("ajax-check-oldpassword")
	public @ResponseBody String changePassword(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "password", required = false) String password, HttpServletRequest request)
	{
		String result = null;
		try
		{

			EmpData empdata = (EmpData) request.getSession().getAttribute("empDataSession");
			int usersId = empdata.getUsers().getUsersId();
			if (type.equals("save"))
			{
				int res = usersServives.updatePassword(password, usersId);
				if (res == 1)
				{
					result = "1";
				}

			}
			else if (type.equals("check"))
			{
				result = "false";
				// Settings settingsdata = settingServices.findSettingData(usersId);
				String res = usersServives.findPassword(usersId, password);
				if (res != null)
				{
					result = "true";
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return result;
	}

}
