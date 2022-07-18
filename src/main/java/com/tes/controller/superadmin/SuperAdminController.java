package com.tes.controller.superadmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController
{
	private static final Logger LOGGER = LogManager.getLogger(SuperAdminController.class);

	@RequestMapping("/dashboard")
	public ModelAndView SuperAdminDashboard(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("SuperAdmin/SuperAdminDashBoard");
		try
		{
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}
}
