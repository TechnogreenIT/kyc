package com.tes.controller.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicExceptionController
{

	private static final Logger LOGGER = LogManager.getLogger(BasicExceptionController.class);

	@RequestMapping("error-404")
	public ModelAndView handleNoHandlerFoundException()
	{
		ModelAndView model = new ModelAndView();
		// model.addObject("errMsg", "This is a 'Exception.class' message.");
		model.setViewName("Error/404");
		return model;
	}
}
