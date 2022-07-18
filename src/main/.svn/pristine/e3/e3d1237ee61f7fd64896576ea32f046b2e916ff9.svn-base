package com.tes.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.model.IndustryCategory;
import com.tes.model.IndustryPrimary;
import com.tes.model.IndustrySecondary;
import com.tes.model.IndustryTypeList;
import com.tes.model.Users;
import com.tes.services.UsersServices;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.admin.IndustryCategoryServices;
import com.tes.services.admin.IndustryPrimaryServices;
import com.tes.services.admin.IndustrySecondaryServices;
import com.tes.services.admin.IndustryTypeListServices;

/**
 * This class used to Manage the company profile and there operation like add/edit/view the company profile.
 * 
 * @author Sushama Dadas
 */
@RestController
@RequestMapping(value = {"/admin", "/env", "/management"})
public class CompanyController
{

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

	@Autowired
	IndustryCategoryServices industryCategoryServices;

	@Autowired
	IndustryTypeListServices industryTypeListServices;

	@Autowired
	IndustryPrimaryServices industryPrimaryServices;

	@Autowired
	IndustrySecondaryServices industrySecondaryServices;

	@Autowired
	UsersServices usersServices;

	@Autowired
	EmpDataServices empDataServices;

	private static final Logger LOGGER = LogManager.getLogger(CompanyController.class);

	/**
	 * This method used to Save company profile.
	 * 
	 * @param CompanyProfile it represent the pojo of companyprofile.
	 * @param result the object of BindingResult.
	 * @param request the servlet request we are processing.
	 * @return redirect:/view-company-profile
	 */
	@RequestMapping("admin-addCompany")
	public ModelAndView addCompany(@ModelAttribute CompanyProfile CompanyProfile, BindingResult result,
			HttpServletRequest request)
	{
		try
		{
			companyProfileServices.save(CompanyProfile);
			if (!result.hasErrors())
			{
				List<CompanyProfile> companyData = companyProfileServices
						.findOneBycompanyProfileId(new PageRequest(0, 1));
				List<Users> adminUserDetails = usersServices.getUsersDetailsByAdmin(new PageRequest(0, 1));
				List<EmpData> empDataOfAdmin = empDataServices.getEmpDataDetailsByAdmin(new PageRequest(0, 1));
				for (int i = 0; i < companyData.size(); i++)
				{
					CompanyProfile companyinfo = companyData.get(i);
					int companyId = companyinfo.getCompanyProfileId();
					Users u = adminUserDetails.get(i);
					CompanyProfile cp = new CompanyProfile();
					cp.setCompanyProfileId(companyId);
					u.setCompanyProfile(cp);
					EmpData emp = empDataOfAdmin.get(i);
					emp.setCompanyProfile(cp);
					usersServices.save(u);
					empDataServices.save(emp);
				}
			}
			else
			{
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/view-company-profile");
	}

	/**
	 * This method used to Display the form of create company profile.
	 * 
	 * @return Display company profile
	 */
	@RequestMapping(value = "add-company", method = RequestMethod.GET)
	public ModelAndView addCompany()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Admin/CreateCompanyProfile");
		try
		{
			List<IndustryCategory> indcatList = industryCategoryServices.findAll();
			List<IndustryTypeList> indtypeList = industryTypeListServices.findAll();
			List<IndustryPrimary> indprimarylist = industryPrimaryServices.findPrimary();
			List<IndustrySecondary> indsecondarylist = industrySecondaryServices.findSecondary();
			modelAndView.addObject("IndtypeList", indtypeList);
			modelAndView.addObject("IndcatList", indcatList);
			modelAndView.addObject("Indprimarylist", indprimarylist);
			modelAndView.addObject("Indsecondarylist", indsecondarylist);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to View the Company profile.
	 * 
	 * @return it returns View Company profile model
	 */
	@RequestMapping(value = "view-company-profile")
	public ModelAndView getcompalllist()
	{
		CompanyProfile companylist = companyProfileServices.findOneBySinglecompanyProfileId();
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			modelAndView.setViewName("CommonUI/ViewCompany");

			Long conter = companyProfileServices.count();
			List<IndustryCategory> indcatList = industryCategoryServices.findAll();
			List<IndustryTypeList> indtypeList = industryTypeListServices.findAll();
			List<IndustryPrimary> indprimarylist = industryPrimaryServices.findAll();
			List<IndustrySecondary> indsecondarylist = industrySecondaryServices.findAll();

			modelAndView.addObject("companylist", companylist);
			modelAndView.addObject("IndtypeList", indtypeList);
			modelAndView.addObject("IndcatList", indcatList);
			modelAndView.addObject("Indprimarylist", indprimarylist);
			modelAndView.addObject("counterc", conter);
			modelAndView.addObject("Indsecondarylist", indsecondarylist);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	@RequestMapping("/ajax-updateCompanyBasicInformation")
	public @ResponseBody String UpdateCompanyBasicInformation(@RequestParam(value = "newCompanyName", required = false) String newCompanyName,
			@RequestParam(value = "newWebsite", required = false) String newWebsite,
			@RequestParam(value = "newBranch", required = false) String newBranch,
			@RequestParam(value = "newYearOfEst", required = false) String newYearOfEst,
			@RequestParam(value = "newEsDate", required = false) String newEsDate, HttpServletRequest request, HttpServletRequest response)
	{

		String ajaxResponse = "Error";
		try
		{
			CompanyProfile companyProfile = new CompanyProfile();
			companyProfile = companyProfileServices.findOneBySinglecompanyProfileId();

			String establishYear[] = newYearOfEst.split("-");

			companyProfile.setCompanyProfileId(companyProfile.getCompanyProfileId());
			companyProfile.setCompName(newCompanyName);
			companyProfile.setWebsite(newWebsite);
			companyProfile.setBranch(newBranch);
			companyProfile.setYearEstb(Integer.parseInt(establishYear[0]));
			companyProfile.setLastEnv(newEsDate);
			companyProfileServices.save(companyProfile);
			ajaxResponse = "Success";

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return ajaxResponse;
	}

	@RequestMapping("/ajax-updateCompanyInformation")
	public @ResponseBody String UpdateCompanyInformation(@RequestParam(value = "newPhoneNo", required = false) String newPhoneNo,
			@RequestParam(value = "newFax", required = false) String newFax, HttpServletRequest request, HttpServletRequest response)
	{
		String ajaxResponse = "Error";
		try
		{
			CompanyProfile companyProfile = new CompanyProfile();
			companyProfile = companyProfileServices.findOneBySinglecompanyProfileId();

			companyProfile.setCompanyProfileId(companyProfile.getCompanyProfileId());
			companyProfile.setPhoneNo(newPhoneNo);
			companyProfile.setFax(newFax);
			companyProfileServices.save(companyProfile);
			ajaxResponse = "Success";

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return ajaxResponse;
	}

	@RequestMapping("/ajax-updateContactPersonInformation")
	public @ResponseBody String UpdateContactPersonInformation(@RequestParam(value = "newContactName", required = false) String newContactName,
			@RequestParam(value = "newContactNo", required = false) String newContactNo,
			@RequestParam(value = "newContactEmail", required = false) String newContactEmail, HttpServletRequest request, HttpServletRequest response)
	{
		String ajaxResponse = "Error";
		try
		{
			CompanyProfile companyProfile = new CompanyProfile();
			companyProfile = companyProfileServices.findOneBySinglecompanyProfileId();

			companyProfile.setCompanyProfileId(companyProfile.getCompanyProfileId());
			companyProfile.setContPerName(newContactName);
			companyProfile.setContPerNo(newContactNo);
			companyProfile.setEmail(newContactEmail);
			companyProfileServices.save(companyProfile);
			ajaxResponse = "Success";

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return ajaxResponse;
	}

	@RequestMapping("/ajax-updateOtherInformation")
	public @ResponseBody String updateOtherInformation(@RequestParam(value = "newIndCategory", required = false) String newIndCategory,
			@RequestParam(value = "newIndType", required = false) String newIndType,
			@RequestParam(value = "newPrimaryCategory", required = false) String newPrimaryCategory,
			@RequestParam(value = "newSecondaryCategory", required = false) String newSecondaryCategory,
			@RequestParam(value = "newWoringDays", required = false) int newWoringDays,
			@RequestParam(value = "newWoringHours", required = false) int newWoringHours, HttpServletRequest request, HttpServletRequest response)
	{
		String ajaxResponse = "Error";
		try
		{
			CompanyProfile companyProfile = new CompanyProfile();
			companyProfile = companyProfileServices.findOneBySinglecompanyProfileId();

			companyProfile.setCompanyProfileId(companyProfile.getCompanyProfileId());
			companyProfile.setIndustryCategory(newIndCategory);
			companyProfile.setIndustryType(newIndType);
			companyProfile.setIndPrimary(newPrimaryCategory);
			companyProfile.setIndSecondary(newSecondaryCategory);
			companyProfile.setNoWorkDays(newWoringDays);
			companyProfile.setWorkingHour(newWoringHours);
			companyProfileServices.save(companyProfile);
			ajaxResponse = "Success";

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return ajaxResponse;
	}
}
