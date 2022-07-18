package com.tes.controller.thirdparty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tes.controller.base.BaseThirdPartyController;
import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.model.Settings;
import com.tes.repository.UsersRepository;
import com.tes.services.SettingServices;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.utilities.Constant;

/**
 * This class demonstrate used of the Third Party Dashbord operation i.e.Profile Manage.
 * 
 * @author Tushar Chowgule
 */
@Controller
@SessionAttributes({"empDataSession", "imgvalue", "emplogindata", "uId", "userRole", "companyName", "userProfilePic"})
public class ThirdPartyController extends BaseThirdPartyController
{

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	UsersRepository usersServices;

	@Autowired
	SettingServices settingServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

	private static final Logger LOGGER = LogManager.getLogger(ThirdPartyController.class);

	/**
	 * This method used to display the Third party dashboard.
	 * 
	 * @param request The servlet request we are processing.
	 * @return ThirdPartyDashboard
	 * @throws IOException if an input/output error occurs
	 * @throws ServletException if a servlet-specified error occurs
	 */
	@RequestMapping("/dashboard")
	public ModelAndView ThirdPartyDashboard(HttpServletRequest request) throws IOException, ServletException
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			modelAndView.setViewName("ThirdParty/ThirdPartyDashboard");
			int usersId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			EmpData empDataSession = empDataServices.findByUserId(usersId);
			String userRole = empDataSession.getContPerDesig();
			int uId = empDataSession.getUsers().getUsersId();
			Settings settingsdata = settingServices.findSettingData(usersId);
			int imgvalue = settingsdata.getBackgroundImage();
			EmpData userprofile = empDataServices.getUserProfileData(usersId);

			CompanyProfile companyData = companyProfileServices.findOne();
			// profiles pic by jemish
			String userProfilePic = empDataSession.getProfilePic();
			File file = new File(Constant.UserProfiles_pic_path + userProfilePic);
			byte[] userProfilePic1 = Files.readAllBytes(file.toPath());
			byte[] encodeBase64 = Base64.encodeBase64(userProfilePic1);
			String proPic = new String(encodeBase64, "UTF-8");
			// profiles pic by jemish

			modelAndView.addObject("empDataSession", empDataSession);
			modelAndView.addObject("imgvalue", imgvalue);
			modelAndView.addObject("emplogindata", userprofile);
			modelAndView.addObject("userRole", userRole);

			modelAndView.addObject("uId", uId);
			modelAndView.addObject("userProfilePic", proPic);
			modelAndView.addObject("companyName", companyData.getCompName());
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/*
	 * @RequestMapping("thirdparty-viewprofile")
	 * public ModelAndView thirdPartyViewProfile(HttpServletRequest request) {
	 * EmpData empdata=(EmpData) request.getSession().getAttribute("empDataSession");
	 * int userId=empdata.getUsers().getUsersId();
	 * EmpData emp=empDataServices.getAdminProfileData(userId);
	 * return new ModelAndView("ThirdParty/ThirdPartyProfile","emptable",emp);
	 * }
	 */

	/**
	 * This method used to display the third party employee basic information.
	 * 
	 * @param empdata The update the employee profile basic information.
	 * @param result The display the employee basic information.
	 * @param request The servlet request we are processing.
	 * @return str it return Third party basic info
	 */
	@RequestMapping("profileBasic")
	public String ProfileBasic(@ModelAttribute EmpData empdata, BindingResult result, HttpServletRequest request)
	{
		String str = null;
		try
		{
			EmpData empdata1 = (EmpData) request.getSession().getAttribute("empDataSession");
			int id = empdata1.getUsers().getUsersId();
			int empSave = empDataServices.updateUserBasicInfo(empdata.getEmployeeName(), empdata.getGender(), empdata.getBirthday(),
					empdata.getMaritalStatus(), empdata.getContPerDesig(), id);

			System.out.println("EmpName=" + empdata.getEmployeeName());
			System.out.println("Emp Gender=" + empdata.getGender());
			System.out.println("Emp birthdate=" + empdata.getBirthday());
			System.out.println("Emp marital=" + empdata.getMaritalStatus());
			System.out.println("Empdesignation=" + empdata.getContPerDesig());

			System.out.println("Emp status=" + empSave);
			str = "{\"user\": { \"name\": \"" + empdata.getEmployeeName() + "\",\"gender\": \""
					+ empdata.getGender() + "\",\"birthday\": \"" + empdata.getBirthday() + "\",\"marital_status\": \""
					+ empdata.getMaritalStatus() + "\",\"designation\": \"" + empdata.getContPerDesig() + "\"}}";
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return str;

	}

	/**
	 * This method used to display the third party employee contact information.
	 * 
	 * @param empdata The update the employee contact informatrion.
	 * @param result The Display the employee contact information.
	 * @param request The servlet request we are processing.
	 * @return profileContactInfo of third party
	 */
	@RequestMapping("profileContactInfo")
	public String ProfileContactInfo(@ModelAttribute EmpData empdata, BindingResult result, HttpServletRequest request)
	{
		try
		{
			EmpData empdata1 = (EmpData) request.getSession().getAttribute("empDataSession");
			int id = empdata1.getUsers().getUsersId();

			empDataServices.updateUserContactInfo(empdata.getAddress(), empdata.getAddress2(), empdata.getAddress3(),
					empdata.getContPerNo(), empdata.getEmail(), id);
			String str = "{\"user\": { \"Address\": \"" + empdata.getAddress() + "\",\"Address2\": \""
					+ empdata.getAddress2() + "\",\"Address3\": \"" + empdata.getAddress3() + "\",\"Contact\": \""
					+ empdata.getContPerNo() + "\",\"email\": \"" + empdata.getEmail() + "\"}}";
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return "OK";
	}

}
