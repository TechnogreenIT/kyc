package com.tes.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.model.Users;
import com.tes.services.AuthoritiesServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;

/**
 * This class used to upload the profile picture of employee.
 * 
 * @author Sushama Dadas
 */
@Controller
@RequestMapping(value = {"/thirdParty", "/admin", "/management", "/env"})
public class UploadController
{
	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	UsersServices usersServices;

	@Autowired
	AuthoritiesServices authoritiesServices;

	private static final Logger LOGGER = LogManager.getLogger(UploadController.class);

	/**
	 * This method used to upload the profile picture.
	 * 
	 * @param session the object of the HttpSession.
	 * @param request the request object of the HttpServletRequest.
	 * @param profilePic the profile picture.
	 * @return it returns user-profile model
	 * @throws IOException if an input/output error occurs
	 */
	@RequestMapping(value = "/ajax-upload-pic")
	@ResponseBody
	public ModelAndView uploadpic(HttpSession session, HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile profilePic) throws IOException
	{
		try
		{

			int uId = (int) request.getSession().getAttribute("uId");

			EmpData userprofile = empDataServices.getUserProfileData(uId);
			Users users = new Users();
			users.setUsersId(userprofile.getUsers().getUsersId());
			CompanyProfile companyProfile = new CompanyProfile();
			companyProfile.setCompanyProfileId(userprofile.getCompanyProfile().getCompanyProfileId());
			userprofile.setCompanyProfile(companyProfile);
			userprofile.setUsers(users);
			String path = session.getServletContext().getRealPath("/");
			if (!profilePic.isEmpty())
			{
				byte[] imageByte = profilePic.getBytes();
				/*
				 * userprofile.setProfilePic(imageByte); byte[] bytes =
				 * userprofile.getProfilePic(); byte[] encodeBase64 =
				 * Base64.encodeBase64(bytes); String base64Encoded = new String(encodeBase64);
				 * request.getSession().setAttribute("dp", base64Encoded);
				 */
			}
			empDataServices.save(userprofile);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/user-profile");
	}

	@PostMapping(value = "ajax-save-user-profile")
	public @ResponseBody String changeUserProfilePic(@RequestParam("imageFile") String[] imageFile,
			HttpServletRequest request)
	{
		String res = "failed";
		try
		{
			int uId = (int) request.getSession().getAttribute("uId");
			String compName = (String) request.getSession().getAttribute("companyName");
			String userRole = (String) request.getSession().getAttribute("userRole");

			String newProfilepicName = Utilities.saveUserProfilePic(imageFile, compName, userRole);

			String oldProfilePicName = empDataServices.getProfilePicName(uId);

			if (!oldProfilePicName.equalsIgnoreCase("default_user.jpg"))
			{
				File file = new File(Constant.UserProfiles_pic_path + oldProfilePicName);
				file.delete();
			}

			int success = empDataServices.updateUserProfilePic(newProfilepicName, uId);
			if (success == 1)
			{
				res = "success";

				File fileForSession = new File(Constant.UserProfiles_pic_path + newProfilepicName);
				byte[] userProfilePic1 = Files.readAllBytes(fileForSession.toPath());
				byte[] encodeBase64 = Base64.encodeBase64(userProfilePic1);
				String proPic = new String(encodeBase64, "UTF-8");
				request.getSession().setAttribute("userProfilePic", proPic);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return res;
	}
}
