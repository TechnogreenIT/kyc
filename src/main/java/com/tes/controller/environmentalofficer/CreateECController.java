package com.tes.controller.environmentalofficer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tes.model.CompanyProfile;
import com.tes.model.Consent;
import com.tes.model.EmpData;
import com.tes.model.EnvEC;
import com.tes.model.Users;
import com.tes.services.environmentalofficer.EnvECServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@Controller
@RequestMapping(value = {"/env"})
public class CreateECController
{
	@Autowired
	EnvECServices envECServices;
	
	@Autowired
	UnitServices unitServices;
	
	
	private static final Logger LOGGER = LogManager.getLogger(CreateECController.class);

@RequestMapping(value ="add-ec")
public ModelAndView addEC(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		EmpData empDataSession = null;
		try
		{
//			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
//			{
//				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
//				modelAndView.addObject(envECServices.findBycmpId(empDataSession.getCompanyProfile().getCompanyProfileId()));
			modelAndView.setViewName("EnvrOfficer/CreateEC");
			//}
			}
		catch (Exception e)
		{				
			LOGGER.error(e);
		}
//		if (Validator.isEmpty(empDataSession))
//		{
//			modelAndView.setViewName("Login");
//		}
//		else
//		{
//			modelAndView.setViewName("EnvrOfficer/CreateEC");
//		}
		return modelAndView;
	}




@RequestMapping(value = "/create-ec", method = RequestMethod.POST) // do not refresh repeat ......by vishal
public ModelAndView setCreateEC(	
		@RequestParam(value = "extendDate", required = false) String extendDate,			
		@RequestParam(value = "ecstatus", required = false) String ecStatus,	//ec	
		@RequestParam(value = "ecFilePath", required = false)  List<MultipartFile> ecFile,//ec
		@RequestParam(value = "eiastatus", required = false) String eiaStatus,	//ec
		@RequestParam(value = "eia1", required = false) String eia1,	//ec
		@RequestParam(value = "eia2", required = false) String eia2,	//ec
		@RequestParam(value = "eia3", required = false) String eia3,	//ec					
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
		RedirectAttributes redirectAttributes)
{
	ModelAndView modelAndView = new ModelAndView();
		EmpData empDataSession = null;
	String compName = null;
	String renameFile = null;
	if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
	{
		empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
		compName = empDataSession.getCompanyProfile().getCompName();
	}
	renameFile = Utilities.renameFile(compName);
	
	EnvEC envEC = new EnvEC();
	Users user = new Users();
	CompanyProfile companyProfile = new CompanyProfile();
	String status = null;
	// request.getParameter()

	
	try
	{
			
			//String consentid=consent.getConsNo();
		companyProfile.setCompanyProfileId(empDataSession.getCompanyProfile().getCompanyProfileId());
		envEC.setCompanyProfile(companyProfile);
		envEC.setEcNo(request.getParameter("ecNo"));
			envEC.setEcvalid_date(request.getParameter("ecvalidupto"));
//			if (!Validator.isEmpty(request.getParameter("eiastatus")))
//			{
			envEC.setEia_Notification(eiaStatus);
				if (request.getParameter("eiastatus").equalsIgnoreCase(Constant.YES))
				{	
					envEC.setProtect_Area_Wildlife(eia1);
					if(eia1==null){
						eia1=Constant.NO;
						envEC.setProtect_Area_Wildlife(eia1); 
					}
					envEC.setCriticalPoll_Area_Identify(eia2);
					if(eia2==null){
						eia2=Constant.NO;
						envEC.setCriticalPoll_Area_Identify(eia2); 
					}
					envEC.setEcosensitive_Area(eia3);
					if(eia3==null){
						eia3=Constant.NO;
						envEC.setEcosensitive_Area(eia3); 
					}
				}
				else {
					eia1=Constant.NO;
					envEC.setProtect_Area_Wildlife(eia1);
					eia2=Constant.NO;
					envEC.setCriticalPoll_Area_Identify(eia2);
					eia3=Constant.NO;
					envEC.setEcosensitive_Area(eia3);
				}
//				
////		//}
		
		//file uploading
		//String file1=""+"";
		String file1=null;
		String addmultiFN=""+"";
		String mainFile1 = null;
		byte[] bytes1 = null;
		//String addmultiFN="";
		if (!ecFile.isEmpty())
		{
	
//			for (int j = 0; j < ecFile.size(); j++)
//			{
//				file1 = ecFile.get(j).getOriginalFilename();
//				addmultiFN +=file1+"/";											 
//			}
			
		}
		String mutifilePath=""+"";
		//mainFile1 = renameFile + "_" + addmultiFN;	
		String mutifileName=""+"";
		if (!ecFile.isEmpty())
		{
		for (int j = 0; j < ecFile.size(); j++)
		{
			file1 = ecFile.get(j).getOriginalFilename();
			 String mainFile1nm = renameFile + "_" + file1;	
			 bytes1 = ecFile.get(j).getBytes();
			 bytes1=bytes1.clone();
			 Files.write(Paths.get(Constant.ec_file_path + mainFile1nm), bytes1);
			 mutifilePath+=Constant.ec_file_path + mainFile1nm+",";
			 mutifileName +=mainFile1nm+",";
			
		}
		}
	   // Files.write(Paths.get(Constant.ec_file_path + mainFile1), bytes1); // if path is not getting ready it will show error ....by vishal	
		envEC.setEcFileName(mutifileName);	
		envEC.setEcFilePath(mutifilePath);			  			   
	    envECServices.save(envEC);

	    if (!Validator.isEmpty(envECServices.save(envEC)))
		{
			
			//	modelAndView.setViewName("EnvrOfficer/ViewECData");
	    	modelAndView.setViewName("redirect:view-ecData");
		}
	 
	    
	}
	catch (Exception e) {
		LOGGER.error(e);
	}
	return modelAndView;
}

@RequestMapping("view-ecData")
public ModelAndView getViewECData(HttpServletRequest request)
{
	ModelAndView modelAndView = new ModelAndView();
	EmpData empDataSession = null;
	try
	{
		List<EnvEC> envEC = null;	
		if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
		{
			empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			
				envEC=envECServices.findBycmpId(empDataSession.getCompanyProfile().getCompanyProfileId());		
		}
		modelAndView.addObject("unit", unitServices.findAll());
		modelAndView.addObject("envEC", envEC);
		
	}
	catch (Exception e)
	{
		LOGGER.error(e);
	}
	if (Validator.isEmpty(empDataSession))
	{
		modelAndView.setViewName("Login");
	}
	else
	{
		modelAndView.setViewName("EnvrOfficer/ViewECData");
	}
	return modelAndView;
}

@RequestMapping("ajax-get-ec-details-by-id")
public @ResponseBody List<EnvEC> getEcDetailById(@RequestParam("ec_no") int ecNo)
{
	return new ArrayList<EnvEC>(envECServices.findbyEcId(ecNo));
}

//edit ec
@RequestMapping(value = "ajax-view-ec-modify-ec-data", method = RequestMethod.POST)
public @ResponseBody String setModifyEcData(@RequestParam("ecNo") int ecId,
		@RequestParam("ecvalid_upto") String ecvalid_upto, 
		@RequestParam("eiaQ") String eiaQue, 
		@RequestParam("eiaQ1") String eiaQue1, 
		@RequestParam("eiaQ2") String eiaQue2,
		@RequestParam("eiaQ3") String eiaQue3,
		@RequestParam("ec_file_old") String ecFileOld, HttpServletRequest request,
		//@RequestParam(value = "ecFilePath", required = false)  List<MultipartFile> ecFile
		@RequestParam(value = "file", required = false) MultipartFile documentFile, @RequestParam("status") String status) throws IOException
{
	try
	{
		EmpData empDataSession = null;
		String compName = null;
		String renameFile = null;
		if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
		{
			empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			compName = empDataSession.getCompanyProfile().getCompName();
		}
		renameFile = Utilities.renameFile(compName);
		String file = null, filePath = null, mainFile = null;
		byte[] bytes = null;
		if (status.equalsIgnoreCase("no"))
		{
			mainFile = ecFileOld;
			filePath = Constant.ec_file_path + ecFileOld;
		}
		else
		{
			file = documentFile.getOriginalFilename();
			bytes = documentFile.getBytes();
			// Get the file and save it somewhere
			mainFile = renameFile + "_" + file;
			Files.write(Paths.get(Constant.ec_file_path + mainFile), bytes);
			file = documentFile.getOriginalFilename();
			filePath = Constant.ec_file_path + mainFile;
		}
		int isUpdated=envECServices.updateECByEcId(ecId,ecvalid_upto,eiaQue,eiaQue1,eiaQue2,eiaQue3,mainFile,filePath);
		return isUpdated == 1 ? Constant.SUCCESS : Constant.FAILURE;
	}
	catch (Exception e)
	{
		LOGGER.error(e);
		return Constant.FAILURE;
	}
}



	}