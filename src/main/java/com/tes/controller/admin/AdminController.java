package com.tes.controller.admin;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.tes.controller.base.BaseAdminController;
import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.model.Settings;
import com.tes.services.SettingServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Validator;

/**
 * This class used to display Admin Dashbord.
 * 
 * @author Sushama Dadas.
 */
@Controller
@SessionAttributes({"counterc", "imgvalue", "emplogindata", "empDataSession", "userRole", "uId", "companyName", "userProfilePic"})
public class AdminController extends BaseAdminController
{
	// gfddfdfferf
	// gfgs
	@Autowired
	UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	UsersServices usersServices;

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	SettingServices settingServices;

	public long conter;

	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

	/**
	 * This method used to display Admin Dashboard.
	 * 
	 * @param request the servlet request we are processing.
	 * @param response the servlet response we are creating.
	 * @return it returns AdminDashBoard model
	 * @throws UnsupportedEncodingException if the character encoding is not
	 *         supported
	 */
	@RequestMapping("/dashboard")
	public ModelAndView AdminDashboard(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Admin/AdminDashBoard");
		try
		{
			int companyCount = 0;
			EmpData empDataSession = null;
			String userRole = "Admin";
			int uId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			String userProfilePic = "default_user.jpg";
			empDataSession = empDataServices.findByUserId(uId);

			if (!Validator.isEmpty(empDataSession))
			{
				userRole = empDataSession.getContPerDesig();
				userProfilePic = empDataSession.getProfilePic();
				modelAndView.addObject("empDataSession", empDataSession);
			}
			File file = new File(Constant.UserProfiles_pic_path + userProfilePic);
			byte[] userProfilePic1 = Files.readAllBytes(file.toPath());
			byte[] encodeBase64 = Base64.encodeBase64(userProfilePic1);
			String proPic = new String(encodeBase64, "UTF-8");

			CompanyProfile updatedcompany = companyProfileServices.findOne();
			String companyName = "New User";
			if (!Validator.isEmpty(updatedcompany))
			{
				companyName = updatedcompany.getCompName();
				companyCount = 1;
			}
			Settings settingsdata = settingServices.findSettingData(uId);
			EmpData userprofile = empDataServices.getUserProfileData(uId);

			modelAndView.addObject("settingsdata", settingsdata);
			modelAndView.addObject("companyCount", companyCount);
			modelAndView.addObject("emplogindata", userprofile);

			modelAndView.addObject("userRole", userRole);

			modelAndView.addObject("uId", uId);
			modelAndView.addObject("userProfilePic", proPic);
			modelAndView.addObject("companyName", companyName);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

}
