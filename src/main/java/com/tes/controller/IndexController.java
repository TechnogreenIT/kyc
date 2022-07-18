package com.tes.controller;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.EmpData;
import com.tes.services.TodoServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.EmailServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.utilities.Validator;

/**
 * This class used to logout and category pages.
 * 
 * @author Vishal gabani
 * @author Sushama Dadas
 * @author Jemish Moradiya
 */
@Controller
@SessionAttributes("tenantSession")
public class IndexController
{

	@Autowired
	UsersServices usersServices;

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	TodoServices todoServices;

	@Autowired
	EmailServices emailServices;

	@Autowired
	ConsentServices consentServices;

	private static final Logger LOGGER = LogManager.getLogger(IndexController.class);

	/**
	 * This method used to get index page
	 * 
	 * @return index
	 */
	@RequestMapping("/")
	public ModelAndView getIndexPage()
	{

		return new ModelAndView("index");

	}

	/**
	 * This method used to connected to tanent
	 * 
	 * @param tanent it represent pojo
	 * @param request the servlet request we are processing.
	 * @param response the servlet response we are creating.
	 * @param ucBuilder the builder works in conjunction with the UriComponents class
	 * @return success/fail
	 */
	// jemish
	@RequestMapping("/ajax-tanent1")
	public @ResponseBody String WorkerAddTodo(@RequestHeader("X-TenantID") String tanent, HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder ucBuilder)
	{

		HttpSession session = request.getSession();
		session.setAttribute("tenantSession", tanent);

		return tanent;
	}

	/**
	 * This method used to category is call here
	 * 
	 * @return Category
	 */
	// category is call here
	@RequestMapping("category")
	public ModelAndView getCategoryPage()
	{
		return new ModelAndView("Category");
	}

	/**
	 * This method used to redirect to login page with error and logout message.
	 * 
	 * @param model the object of Model
	 * @param error the error message like invalid username and password
	 * @param logout the logout message like successfully logout
	 * @return it returns Login model
	 */
	// Login page is call here
	@RequestMapping("login")
	public ModelAndView login(Model model, HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login");

		String tenantHeader = (String) request.getSession().getAttribute("tenantSession");

		if (!Validator.isEmpty(tenantHeader))
		{
			modelAndView.addObject("tenantHeader", tenantHeader);
		}

		return modelAndView;
	}

	/**
	 * This method used to logout .
	 * 
	 * @param request The servlet request we are processing.
	 * @param response The servlet response we are creating.
	 * @return it redirect to login model
	 */
	@RequestMapping(value = {"logout", "**/logout"})
	public String logoutPage(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 * if (auth != null) { new SecurityContextLogoutHandler().logout(request,
		 * response, auth); } request.getSession().setAttribute("userName", "");
		 * request.getSession().setAttribute("password", "");
		 * request.getSession().invalidate();
		 */

		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		HttpSession hs = request.getSession();
		Enumeration e = hs.getAttributeNames();
		while (e.hasMoreElements())
		{
			String attr = (String) e.nextElement();
			hs.setAttribute(attr, null);
		}
		removeCookies(request);
		hs.invalidate();

		return "redirect:/login";// You can redirect wherever you want, but generally it's a good practice to
	}

	/**
	 * This method used to open report form.
	 * 
	 * @return report model
	 */
	@RequestMapping("reports")
	public ModelAndView getReportsPages()
	{
		ModelAndView modelAndView = new ModelAndView();
		String userRole = null;
		try
		{
			int usersId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			EmpData userprofile = empDataServices.getUserProfileData(usersId);
			if (!Validator.isEmpty(userprofile))
			{
				userRole = userprofile.getContPerDesig();
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		modelAndView.setViewName("EnvrOfficer/Reports");
		modelAndView.addObject("userRole", userRole);
		return modelAndView;
	}

	public void removeCookies(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				cookies[i].setMaxAge(0);
			}
		}
	}

	@RequestMapping(value = "/Access_Denied"/* , method = RequestMethod.GET */)
	public ModelAndView accessDeniedPage()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/CommonUI/accessDenied");
		return mv;
	}

	@RequestMapping(value = {"/errors", "/error"}, method = RequestMethod.GET)
	public ModelAndView renderErrorPage(HttpServletRequest httpRequest)
	{

		ModelAndView errorPage = new ModelAndView("/CommonUI/errorPage");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);

		switch (httpErrorCode)
		{
			case 400:
			{
				errorMsg = "Http Error Code: 400. Bad Request";
				break;
			}
			case 401:
			{
				errorMsg = "Http Error Code: 401. Unauthorized";
				break;
			}
			case 404:
			{
				errorMsg = "Http Error Code: 404. Resource not found";
				break;
			}
			case 500:
			{
				errorMsg = "Http Error Code: 500. Internal Server Error";
				break;
			}
		}
		errorPage.addObject("errorMsg", errorMsg);
		return errorPage;
	}

	private int getErrorCode(HttpServletRequest httpRequest)
	{
		return (Integer) httpRequest
				.getAttribute("javax.servlet.error.status_code");
	}

}
