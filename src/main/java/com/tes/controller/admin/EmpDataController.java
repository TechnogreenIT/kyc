package com.tes.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tes.handler.UserAuthenticationSuccessHandler;
import com.tes.model.Authorities;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.model.Settings;
import com.tes.model.Users;
import com.tes.services.AuthoritiesServices;
import com.tes.services.SettingServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmailServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Validator;

/**
 * This class used to Manage the employee profile and there operation like that
 * add/edit/view/enable-disable employee profile and send email.
 * 
 * @author Sushama Dadas
 */
@Controller
@SessionAttributes({"empDataSession"})
@RequestMapping(value = {"/admin", "/env", "/management", "/thirdParty","/superadmin"})
public class EmpDataController
{
	@Autowired
	EmpDataServices empDataServices;
	@Autowired
	UsersServices usersServices;
	@Autowired
	AuthoritiesServices authoritiesServices;
	@Autowired
	SettingServices settingServices;
	@Autowired
	EmailServices emailServices;
	@Autowired
	CompanyProfileServices companyProfileServices;

	private static final Logger LOGGER = LogManager.getLogger(EmpDataController.class);

	/**
	 * This method used to Save user details in database.
	 * 
	 * @param users it represent pojo of users.
	 * @param resultusers the usersobject of Bindingresult.
	 * @param request the servlet request we are processing.
	 * @param empdata it represent pojo of empdata.
	 * @param resultempdata the empdataobject of Bindingresult
	 * @param authorities it represent pojo of authorities.
	 * @return it returns view users model
	 * @throws IOException if an input/output error occurs
	 */

	@RequestMapping(value = "save-user")
	public ModelAndView addEmpData(@ModelAttribute Users users, BindingResult resultusers, HttpServletRequest request,
			@ModelAttribute EmpData empdata, BindingResult resultempdata, @ModelAttribute Authorities authorities)
			throws IOException
	{
		ModelAndView model = new ModelAndView();
		EmpData empDataSession = null;
		try
		{

			String compName = null;

			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				compName = empDataSession.getCompanyProfile().getCompName();
			}
			model.setViewName("redirect:/admin/view-users");
			int usersId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			empDataSession = empDataServices.findByUserId(usersId);
			List<CompanyProfile> companyData = companyProfileServices.findOneBycompanyProfileId(new PageRequest(0, 1));
			for (int j = 0; j < companyData.size(); j++)
			{
				CompanyProfile cp = new CompanyProfile();
				cp.setCompanyProfileId(companyData.get(j).getCompanyProfileId());
				users.setCompanyProfile(cp);
				users.setPassword(BCrypt.hashpw(users.getPassword(), BCrypt.gensalt()));
				users.setEnabled(true);
				users.setDesignation(empdata.getContPerDesig());
				if (empdata.getContPerDesig().equals("Management"))
				{
					authorities.setAuthority("ROLE_MANAGEMENT");
				}
				else if (empdata.getContPerDesig().equals("Environmental Officer"))
				{
					authorities.setAuthority("ROLE_ENVROFFICER");
				}
				else if (empdata.getContPerDesig().equals("Third Party"))
				{
					authorities.setAuthority("ROLE_THIRDPARTY");
				}

				else if (empdata.getContPerDesig().equals("Super Admin"))
				{
					authorities.setAuthority("ROLE_SUPERADMIN");
				}
				else
				{
					authorities.setAuthority("ROLE_ADMIN");
				}

				users.getAuthorities().add(authorities);
				authoritiesServices.save(authorities);
				Users usersdata = usersServices.save(users);
				usersServices.findByUsersId(usersdata.getUsersId());

				File file = new File(Constant.DEFAULT_PROFILE_PIC);
				byte[] oldFileBytes = Files.readAllBytes(file.toPath());

				String fileExtension = file.getName();
				int lastIndexOf = fileExtension.lastIndexOf(".");
				if (lastIndexOf == -1)
				{
					fileExtension = ""; // empty extension
				}
				fileExtension = fileExtension.substring(lastIndexOf);

				String userType = empdata.getContPerDesig();
				if (userType.equalsIgnoreCase("admin"))
				{
					userType = "admn";
				}
				else if (userType.equalsIgnoreCase("Environmental Officer"))
				{
					userType = "env";
				}
				else if (userType.equalsIgnoreCase("Management"))
				{
					userType = "mn";
				}
				else if (userType.equalsIgnoreCase("Third Party"))
				{
					userType = "tp";
				}

				String newImageName = "default_user" + fileExtension;
				String newPath = Constant.UserProfiles_pic_path + newImageName;

				File newFile = new File(newPath);
				try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile)))
				{
					outputStream.write(oldFileBytes);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

				empdata.setProfilePic(newImageName);
				empdata.setCompanyProfile(cp);
				empdata.setGender("Male");
				empdata.setMaritalStatus("Married");
				empdata.setUsers(usersdata);
				EmpData empdetails = empDataServices.save(empdata);
				Settings settings = new Settings();
				settings.setUsers(usersdata);
				settings.setBackgroundImage(1);
				settings.setIntroductoryVideoStatus("ON");
				settings.setTextColor("#ffffff");
				settingServices.save(settings);
				EmpData emplogindata = (EmpData) request.getSession().getAttribute("emplogindata");
				String from = emplogindata.getEmail();
				String toEmail = empdetails.getEmail();
				String message = "Your login details are created at kyc as: " + "\n" + "Username is" + " "
						+ usersdata.getUserName() + "\n " + "and Password is" + " " + usersdata.getPassword();
				String subject = "User Account Details at KYC";
				int i = 0;
				try
				{
					i = emailServices.sendMail(from, toEmail, subject, message);
				}
				catch (MailAuthenticationException e)
				{
					e.printStackTrace();
				}
				catch (MailException e)
				{
					e.printStackTrace();
				}
				if (i == 1)
				{
					empdetails.setEmailStatus("Mail sent");
					empDataServices.save(empdata);
				}
				else
				{
					empdetails.setEmailStatus("Mail sending failed");
					empDataServices.save(empdata);
				}
			}
			model.addObject("empDataSession", empDataSession);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return model;
	}

	/**
	 * This method used to display the user login profile.
	 * 
	 * @param request the servlet request we are processing.
	 * @param empdata it represent pojo of empdata
	 * @return user login profile model
	 */
	// @PreAuthorize("hasRole('ROLE_ENVROFFICER')")
	@RequestMapping(value = "user-profile", method = RequestMethod.GET)
	public ModelAndView viewLoginUserProfile(HttpServletRequest request, EmpData empdata)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CommonUI/UserProfile");
		try
		{
			int uId = (int) request.getSession().getAttribute("uId");
			EmpData userprofile = empDataServices.getUserProfileData(uId);
			String userDesignation = userprofile.getContPerDesig();
			if (userprofile.getProfilePic() != null)
			{
				/*
				 * byte[] bytes = userprofile.getProfilePic(); byte[] encodeBase64 =
				 * Base64.encodeBase64(bytes); String base64Encoded = new String(encodeBase64);
				 * request.getSession().setAttribute("dp", base64Encoded);
				 */
			}
			mv.addObject("emplogindata", userprofile);
			mv.addObject("userDesignation", userDesignation);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return mv;
	}

	/**
	 * This method used to edit the employee basic details.
	 * 
	 * @param employeeName the name of employee
	 * @param gender the gender of employee
	 * @param birthday the birth date of employee
	 * @param maritalStatus the marital status of employee
	 * @param contPerDesig the designation of employee
	 * @param request the servlet request we are processing.
	 * @param response the servlet response we are creating.
	 * @return it returns success or fail
	 */
	@RequestMapping(value = {"ajax-edit-emp-basic-info"}, method = RequestMethod.POST)
	public @ResponseBody String editEmpBasicInfo(
			@RequestParam(value = "employeeName", required = false) String employeeName,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "birthday", required = false) String birthday,
			@RequestParam(value = "maritalStatus", required = false) String maritalStatus,
			@RequestParam(value = "contPerDesig", required = false) String contPerDesig, HttpServletRequest request,
			HttpServletResponse response)
	{
		String status = null;
		try
		{
			int userloginid = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			EmpData userprofile = empDataServices.getUserProfileData(userloginid);
			employeeName = employeeName == "" ? null : employeeName;
			gender = gender == "" ? null : gender;
			birthday = birthday == null ? birthday : birthday;
			maritalStatus = maritalStatus == "" ? null : maritalStatus;
			contPerDesig = contPerDesig == "" ? null : contPerDesig;
			userprofile.setEmployeeName(employeeName);
			userprofile.setGender(gender);
			userprofile.setBirthday(birthday);
			userprofile.setMaritalStatus(maritalStatus);
			userprofile.setContPerDesig(contPerDesig);
			EmpData emp = empDataServices.save(userprofile);
			status = Constant.SUCCESS;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			status = Constant.FAILURE;
		}
		return status;
	}

	/**
	 * This method used to edit the employee contact details.
	 * 
	 * @param contPerNo the Phone no. of employee.
	 * @param email the email address of employee.
	 * @param address the address of employee.
	 * @param address2 the second address of employee.
	 * @param address3 the Third address of employee.
	 * @param request the servlet request we are processing.
	 * @param response the servlet response we are creating.
	 * @return it returns success or fail
	 */
	@RequestMapping(value = {"ajax-edit-emp-contact-info"}, method = RequestMethod.POST)
	public @ResponseBody String editEmpContactInfo(
			@RequestParam(value = "contPerNo", required = false) String contPerNo,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "address2", required = false) String address2,
			@RequestParam(value = "address3", required = false) String address3, HttpServletRequest request,
			HttpServletResponse response)
	{
		String status = null;
		try
		{
			int userloginid = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
			EmpData userprofile = empDataServices.getUserProfileData(userloginid);
			contPerNo = contPerNo == "" ? null : contPerNo;
			email = email == "" ? null : email;
			address = address == "" ? null : address;
			address2 = address2 == "" ? null : address2;
			address3 = address3 == "" ? null : address3;
			userprofile.setContPerNo(contPerNo);
			userprofile.setEmail(email);
			userprofile.setAddress(address);
			userprofile.setAddress2(address2);
			userprofile.setAddress3(address3);
			empDataServices.save(userprofile);
			status = Constant.SUCCESS;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			status = Constant.FAILURE;
		}
		return status;
	}

	/**
	 * This method uesd to open CreateUsers form.
	 * 
	 * @return CreateUsers page
	 */
	@RequestMapping(value = "add-user")
	public ModelAndView addEmpLink()
	{
		return new ModelAndView("Admin/CreateUsers");
	}

	/**
	 * This method used to delete the user profile.
	 * 
	 * @param the empDataId the ID of the employee.
	 * @param the settingId the setting ID.
	 * @return
	 */
	@RequestMapping(value = "/deleteEmpData", method = RequestMethod.POST)
	public @ResponseBody int deleteEmpDataByempDataId(
			@RequestParam(value = "empDataId", required = false) int empDataId, int settingId)
	{
		try
		{
			empDataServices.deleteByempDataId(empDataId);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return empDataId;
	}

	/**
	 * This method used to View all user profile.
	 * 
	 * @param request the servlet request we are processing.
	 * @return ViewUsers model
	 */
	@RequestMapping("view-users")
	public ModelAndView viewUsers(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Admin/ViewUsers");
		try
		{
			List<EmpData> userlist = empDataServices.findAll();
			List usersstatus = usersServices.findUserStatus();
			request.setAttribute("usersstatus", usersstatus);
			request.setAttribute("userlist", userlist);
			modelAndView.addObject("usersstatus", usersstatus);
			modelAndView.addObject("userlist", userlist);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to Change user Status i.e. Active or Inactive mode.
	 * 
	 * @param empDataId the ID of the employee.
	 * @param c_status the employee status.
	 * @return disablStatus or enableStatus
	 */
	@RequestMapping(value = "/ajax-changeUserStatus", method = RequestMethod.POST)
	public @ResponseBody int changeUserStatus(@RequestParam(value = "empDataId", required = false) int empDataId,
			@RequestParam(value = "currentStatus", required = false) String userStatus)
	{
		int msg = 0;
		try
		{
			if (userStatus.equalsIgnoreCase("Deactive"))
				msg = empDataServices.disablStatus(empDataId);
			else if (userStatus.equalsIgnoreCase("Active"))
				msg = empDataServices.enableStatus(empDataId);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return msg;
	}

	/**
	 * This method used to Open Email sending page.
	 * 
	 * @return EmailDashboard model
	 */
	@RequestMapping(value = "email-dashboard")
	public ModelAndView openMailPage()
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			modelAndView.setViewName("Admin/EmailDashboard");
			List emails = empDataServices.findEmail();
			modelAndView.addObject("emails", emails);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to upload profile status.
	 * 
	 * @param profileStatus the Profile status
	 * @param request the servlet request we are processing.
	 * @return ModelAndView it returns user-profile model
	 */
	@RequestMapping(value = "upload-profile-status")
	public ModelAndView uploadProfileStatus(
			@RequestParam(value = "profileStatus", required = false) String profileStatus, HttpServletRequest request)
	{
		try
		{
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("Admin/EmailDashboard");
			EmpData usersdetails = (EmpData) request.getSession().getAttribute("empDataSession");
			int userloginid = usersdetails.getUsers().getUsersId();
			EmpData userprofile = empDataServices.getUserProfileData(userloginid);
			EmpData empdata = empDataServices.save(userprofile);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/user-profile");
	}

	/**
	 * This method used to send the Email to users.
	 * 
	 * @param from the senders Email ID.
	 * @param toEmail the reciver Email ID.
	 * @param subject the subject of Email.
	 * @param request the servlet request we are processing.
	 * @param response the servlet response we are creating.
	 * @return status it returns success or fail
	 * @throws IOException if an input/output error occurs
	 * @throws MessagingException
	 */
	@RequestMapping(value = "ajax-send-email", method = RequestMethod.POST)
	public @ResponseBody String sendEmail(@RequestParam(value = "mail_from", required = false) String from,
			@RequestParam(value = "mail_to", required = false) String toEmail,
			@RequestParam(value = "subject", required = false) String subject, HttpServletRequest request,
			HttpServletResponse response) throws IOException, MessagingException
	{
		try
		{
			String message = "";
			String[] usersdata = usersServices.findUserNameAndPassword(toEmail);
			for (String usersdatas : usersdata)
			{
				String[] a = usersdatas.split(",");
				message = message.replaceAll("\\s", "");
				for (int i = 0; i < a.length; i++)
				{
				}
				message = "Your login details are created as: " + "\n" + "Username is" + " " + a[0] + "\n "
						+ "and Password is" + " " + a[1];
			}
			emailServices.sendMail(from, toEmail, subject, message);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return "Mail sent";
	}

	/**
	 * This method used to check the entered email is already exist or not in database.
	 * 
	 * @param c_email the current email ID.
	 * @return it reurns 0 or 1
	 */
	@RequestMapping(value = "/ajax-check-email", method = RequestMethod.POST)
	public @ResponseBody int checkEmail(@RequestParam(value = "c_email", required = false) String c_email)
	{
		int msg = 0;
		try
		{
			List emails = empDataServices.findEmail();
			if (emails.contains(c_email))
			{
				msg = 0;
			}
			else
			{
				msg = 1;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return msg;
	}

	/**
	 * This method used to check the entered employee name is already exist or not in database.
	 * 
	 * @param c_emp_name the current employee name.
	 * @return it returns 0 or 1
	 */
	@RequestMapping(value = "/ajax-check-employee-name", method = RequestMethod.POST)
	public @ResponseBody int checkEmployeeName(
			@RequestParam(value = "c_emp_name", required = false) String c_emp_name)
	{
		int msg = 0;
		try
		{
			List empName = empDataServices.findemployeeName();
			if (empName.contains(c_emp_name))
			{
				msg = 0;
			}
			else
			{
				msg = 1;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return msg;
	}

	/**
	 * This method used to check the entered user name is already exist or not in database.
	 * 
	 * @param c_userName the current user name.
	 * @return it returns 0 or 1
	 */
	@RequestMapping(value = "/ajax-check-user-name", method = RequestMethod.POST)
	public @ResponseBody int checkUserName(@RequestParam(value = "c_userName", required = false) String c_userName)
	{
		int msg = 0;
		try
		{
			List userName = usersServices.findUserName();
			if (userName.contains(c_userName))
			{
				msg = 0;
			}
			else
			{
				msg = 1;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return msg;
	}

	/**
	 * This method used to Display the contact page.
	 * 
	 * @return contact model
	 */
	@RequestMapping("contact")
	public ModelAndView getContactPages()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EnvrOfficer/Contact");

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
		modelAndView.addObject("userRole", userRole);
		return modelAndView;
	}

	/**
	 * This method used to send Contact information to Email.
	 * 
	 * @param from the From address of email.
	 * @param toEmail the To address of eamil
	 * @param textmsg the Text message of mail.
	 * @param filename the attachment file name of mail
	 * @param request the servlet request we are processing.
	 * @param session1 session from which the locale should be guessed
	 * @return it returns 0 or 1
	 * @throws MessagingException
	 */
	@RequestMapping("ajax-sendContactEmail")
	public @ResponseBody int sendContactEmail(@RequestParam("from") String from, @RequestParam("to") String toEmail,
			@RequestParam("message") String[] textmsg, @RequestParam("file") MultipartFile filename,
			HttpServletRequest request, HttpSession session1) throws MessagingException
	{

		int status = 0;
		try
		{
			final String username = from;
			final String password = "infotech@tes";
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.EnableSSL.enable", "true");
			props.put("mail.stmp.user", username);
			props.put("mail.smtp.password", password);
			props.setProperty("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthenticated()
				{
					return new PasswordAuthentication(username, password);
				}
			});
			String path = null;
			session.setDebug(false);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Contact Us");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(textmsg[1]);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			if (filename != null)
			{
				messageBodyPart = new MimeBodyPart();
				path = session1.getServletContext().getRealPath("/");
				File filepath = new File(path + filename.getOriginalFilename());
				DataSource source = new ByteArrayDataSource(filename.getBytes(), "application/octet-stream");
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename.getOriginalFilename());
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
			}
			else
			{
				message.setText(textmsg[1]);
			}
			Transport.send(message, username, password);
			LOGGER.log(Level.INFO, "Mail sent successfully\n");
			status = 1;
		}
		catch (Exception e)
		{
			status = 0;
			LOGGER.log(Level.ERROR, "Unable to send mail\n");
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "ajax-sendMail", method = RequestMethod.POST)
	public @ResponseBody String sendEmailToUsers(@RequestParam(value = "empDataId", required = false) int empDataId, HttpServletRequest request) throws IOException, MessagingException
	{
		try
		{
			String toEmpEmail = "", EmpUserName = "", EmpPass = "";
			String mailFrom = "technogreen.env.solutions@gmail.com";

			EmpData emplogindata = (EmpData) request.getSession().getAttribute("emplogindata");
			String mailSenderMail = emplogindata.getEmail();
			String mailSenderUserName = emplogindata.getUsers().getUserName();
			String companyName = emplogindata.getCompanyProfile().getCompName();
			EmpData empDatas = empDataServices.getEmpData(empDataId);

			if (!Validator.isEmpty(empDatas))
			{
				toEmpEmail = empDatas.getEmail();
				EmpUserName = empDatas.getUsers().getUserName();
				EmpPass = empDatas.getUsers().getPassword();
			}
			String message = "Your login details at KYC as: " + "\n" + "Username is :" + EmpUserName + "\n " + "and Password : " + " " + EmpPass + "\n\n By:" + mailSenderUserName + "(" + mailSenderMail + "). \n Admin at :" + companyName;
			String subject = "User Account Details at KYC";

			emailServices.sendMail(mailFrom, toEmpEmail, subject, message);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return "Mail sent";
	}
}
