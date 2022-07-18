package com.tes.controller.environmentalofficer.consent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tes.kyc.KycApplication;
import com.tes.model.CompanyProfile;
import com.tes.model.Consent;
import com.tes.model.ConsentAmulgamation;
import com.tes.model.EmpData;
import com.tes.model.FilterNameList;
import com.tes.model.FilterUseNames;
import com.tes.model.HazardousWastes;
import com.tes.model.Unit;
import com.tes.model.Users;
import com.tes.model.WaterSourceNames;
import com.tes.services.FilterNameListServices;
import com.tes.services.FilterUseNameServices;
import com.tes.services.HazardousWastesServices;
import com.tes.services.WaterSourceNamesServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate used of the consent operation.
 * This class perform the operation of create the consent to establish and view the consent details.
 * 
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class CreateConsentController
{

	@Autowired
	ConsentServices consentServices;

	@Autowired
	UnitServices unitServices;

	@Autowired
	HazardousWastesServices hazardousWastesServices;

	@Autowired
	WaterSourceNamesServices waterSourceNamesServices;

	@Autowired
	FilterNameListServices filterNameListServices;

	@Autowired
	FilterUseNameServices filterUseNameServices;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to check data for existing consent data.
	 * 
	 * @param request The servlet request we are processing.
	 * @return totPlotArea, totBuildArea, openSpaceASva, totGreenArea
	 */
	@RequestMapping("create-consent")
	public ModelAndView getCreateConsent(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		EmpData empDataSession = null;
		try
		{
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				modelAndView.addObject("consentCtoE",
						consentServices.findByCmpIdAndCtoE(empDataSession.getCompanyProfile().getCompanyProfileId()));
				modelAndView.addObject("consentCtoO",
						consentServices.findByCmpIdAndCtoO(empDataSession.getCompanyProfile().getCompanyProfileId()));
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		float totPlotArea = 0.0f;
		float a = 0.0f;
		float totBuildArea = 0.0f;
		float a1 = 0.0f;
		float openSpaceAva = 0.0f;
		float a3 = 0.0f;
		float totGreenArea = 0.0f;
		float a4 = 0.0f;

		try
		{
			totPlotArea = consentServices.findBySumTotalAreaCtoEs();
			a = consentServices.findBySumTotalAreaCtoOp();

			totBuildArea = consentServices.findBySumTotalBuildAreaCtoEs();
			a1 = consentServices.findBySumTotalBuildAreaCtoOp();

			openSpaceAva = consentServices.findBySumTOpenSpaceAreaCtoEs();
			a3 = consentServices.findBySumTOpenSpaceAreaCtoOp();

			openSpaceAva = consentServices.findBySumTotalGreenAreaCtoEs();
			a4 = consentServices.findBySumTotalGreenAreaCtoOp();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		modelAndView.addObject("totPlotArea", totPlotArea -= a);
		modelAndView.addObject("totBuildArea", totBuildArea -= a1);
		modelAndView.addObject("openSpaceASva", openSpaceAva -= a3);
		modelAndView.addObject("totGreenArea", totGreenArea -= a4);
		if (Validator.isEmpty(empDataSession))
		{
			modelAndView.setViewName("Login");
		}
		else
		{
			modelAndView.setViewName("EnvrOfficer/CreateConsent");
		}
		return modelAndView;
	}

	/**
	 * This method used to create the consent to establish and operate.
	 * 
	 * @param concentFile the file of the consent
	 * @param expandedFile the expanded file detail
	 * @param amulgamationEId the amulgamation establish id
	 * @param amulgamationOId the amulgamation operate id
	 * @param msg the message of the consent
	 * @param request the servlet request we are processing.
	 * @param redirectAttributes the redirect the attribute of the consent.
	 * @return msg, unit, consentId
	 */
	@RequestMapping(value = "worker-create-consent", method = RequestMethod.POST) // do not refresh repeat ......by vishal
	public ModelAndView setWorkerCreateConsent(
			@RequestParam(value = "consentFilePath", required = false) MultipartFile concentFile,
			@RequestParam(value = "expande6dConsentFilePath", required = false) MultipartFile expandedFile,
			@RequestParam(value = "aml_e[]", required = false) int[] amulgamationEId,
			@RequestParam(value = "aml_o[]", required = false) int[] amulgamationOId,
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
		String consType = request.getParameter("consType");
		Consent consent = new Consent();
		Users user = new Users();
		CompanyProfile companyProfile = new CompanyProfile();
		String status = null;
		try
		{
			if (!Validator.isEmpty(request.getParameter("status")))
				status = request.getParameter("status");

			String que2 = Constant.NO;
			if (request.getParameter("Validstatus").equalsIgnoreCase(Constant.NO))
				que2 = request.getParameter("ExValidstatus");
			if (que2.equalsIgnoreCase(Constant.NO))
			{
				request.getSession().setAttribute("multipleOperate", request.getParameter("MultipleOP"));
			}

			user.setUsersId(empDataSession.getUsers().getUsersId());// if session is null it will give error
			companyProfile.setCompanyProfileId(empDataSession.getCompanyProfile().getCompanyProfileId());
			consent.setUsers(user);
			consent.setCompanyProfile(companyProfile);
			consent.setConsType(consType);
			consent.setConsStatus(status);
			consent.setExpansionStatus(que2);

			if (Validator.isEmpty(request.getParameter("expand")))
			{
				int noStaff = 0;
				if ((empDataSession.getCompanyProfile().getIndustryCategory()).equalsIgnoreCase("Commercial-Cinemas, concert halls and theatres"))
				{
					noStaff = Integer.parseInt(request.getParameter("no_staff1")) + Integer.parseInt(request.getParameter("no_staff2"));
				}
				else
				{
					noStaff = Integer.parseInt(request.getParameter("noStaff"));
				}
				consent.setConsNo(request.getParameter("consNo"));
				consent.setIssueDate(request.getParameter("issueDate"));
				consent.setValidUpto(request.getParameter("validUpto"));
				consent.setGrossCi(Float.parseFloat(request.getParameter("grossCi")));
				consent.setNoStaff(noStaff);
				consent.setNoWorker(Integer.parseInt(request.getParameter("noWorker")));
				consent.setTotPlotArea(Float.parseFloat(request.getParameter("totPlotArea")));
				consent.setTotPlotAreaUnits(request.getParameter("totPlotAreaUnits"));
				consent.setTotBuildArea(Float.parseFloat(request.getParameter("totBuildArea")));
				consent.setTotBuildAreaUnits(request.getParameter("totBuildAreaUnits"));
				consent.setOpenSpaceAva(Float.parseFloat(request.getParameter("openSpaceAva")));
				consent.setOpenSpaceAvaUnits(request.getParameter("openSpaceAvaUnits"));
				consent.setTotGreenArea(Float.parseFloat(request.getParameter("totGreenArea")));
				consent.setTotGreenAreaUnits(request.getParameter("totGreenAreaUnits"));
			}
			else
			{
				consent.setConsNo(request.getParameter("expandedConsNo"));
				consent.setIssueDate(request.getParameter("expandedIssueDate"));
				consent.setValidUpto(request.getParameter("expandedValidUpto"));
			}

			String file = null;
			String mainFile = null;
			byte[] bytes = null;
			if (!concentFile.isEmpty())
			{
				file = concentFile.getOriginalFilename();
				bytes = concentFile.getBytes();
			}
			else if (!expandedFile.isEmpty())
			{
				file = expandedFile.getOriginalFilename();
				bytes = expandedFile.getBytes();
			}
			mainFile = renameFile + "_" + file;
			consent.setConsentFileName(mainFile);
			// Get the file and save it somewhere
			Files.write(Paths.get(Constant.consent_file_path + mainFile), bytes); // if path is not getting ready it will show error ....by vishal
			consent.setConsentFilePath(Constant.consent_file_path + mainFile);// if file size 0 it show error ....by vishal
			consentServices.save(consent);

			// Amulgamation form here
			Consent consentId = new Consent();
			consentId.setConsentId(consent.getConsentId());
			if (status.equalsIgnoreCase("Amalgamant") && (!status.equalsIgnoreCase("New")) || (!Validator.isEmpty(request.getParameter("expand"))))
			{
				int[] amulData = null;
				if (Validator.isEmpty(amulgamationEId))
					amulData = amulgamationOId;
				else if (Validator.isEmpty(amulgamationOId))
					amulData = amulgamationEId;
				for (int i = 0, size = amulData.length; i < size; i++)
				{
					ConsentAmulgamation consentAmulgamation = new ConsentAmulgamation();
					consentAmulgamation.setConsent(consentId);
					consentAmulgamation.setAmulgamationId(amulData[i]);
					consentServices.save(consentAmulgamation);
				}
			}
			if (!Validator.isEmpty(consentServices.save(consent)))
			{
				if (consType.equalsIgnoreCase(Constant.CONSENT_TO_ESTABLISH))
				{
					// modelAndView.addObject("msg", Constant.SUCCESS);
					modelAndView.setViewName("redirect:elist-product");
					// added by jemish
					/*
					 * modelAndView.addObject("unit", unitServices.findAll());
					 * modelAndView.addObject("hwCategroriesList",hazardousWastesServices.findAll());
					 */

				}
				else if (consType.equalsIgnoreCase(Constant.CONSENT_TO_OPERATE))
				{// (consentServices.findByConsType(consType) need to change this query concern Amin ....by vishal
					if ((consentServices.findByConsType(consType) == 0 ? Constant.YES : Constant.NO).equalsIgnoreCase(Constant.YES))
					{ // not record of Consent to Operate in table
						// modelAndView.addObject("msg", Constant.SUCCESS);
						// added by jemish
						/*
						 * modelAndView.addObject("unit", unitServices.findAll());
						 * modelAndView.addObject("hwCategroriesList",hazardousWastesServices.findAll());
						 */

						modelAndView.setViewName("redirect:elist-product");
					}
					else
					{
						// modelAndView.addObject("msg", Constant.SUCCESS);// record of Consent to Operate in consent table table
						// mv.setViewName("EnvrOfficer/OListOfProduct");
						modelAndView.addObject("consentId", consent.getConsentId());
						modelAndView.setViewName("redirect:olist-product");
					}
				}
			}
			else
			{

				modelAndView.addObject("msg", KycApplication.messageBundle.getString("consent.save.error"));
				modelAndView.setViewName("EnvrOfficer/CreateConsent");
			}
			// commented By Jemish
			/*
			 * modelAndView.addObject("unit", unitServices.findAll());
			 * modelAndView.addObject("hwCategroriesList",hazardousWastesServices.findAll());
			 */
			// modelAndView.addObject("consentId", consent.getConsentId());
		}
		catch (IOException e)
		{
			LOGGER.error("IOException=" + e);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to View consent Details including consent to Establish and consent to operate.
	 * 
	 * @param type the type of product
	 * @param consentNo the number of consent Id
	 * @return get-consent-details-by-id
	 */
	@RequestMapping("ajax-get-consent-details-by-id")
	public @ResponseBody List<Consent> getConsentDetailById(@RequestParam("type") String type, @RequestParam("consent_no") int consentNo)
	{
		return new ArrayList<Consent>(consentServices.findbyConsentId(consentNo));// ask bro .....by vishal
	}

	/**
	 * This method used to view consent details.
	 * 
	 * @param msg the message of consent
	 * @param request the servlet request we are processing.
	 * @return unit, actionType, consent
	 */
	// View Consent Details Start view-eData.jsp
	@RequestMapping("view-eData")
	public ModelAndView getViewEData(@RequestParam("msg") String msg, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		EmpData empDataSession = null;
		try
		{
			List<Consent> consent = null;
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				if (msg.equalsIgnoreCase("establish"))
				{
					consent = consentServices.findByCmpIdAndCtoE(empDataSession.getCompanyProfile().getCompanyProfileId());
				}
				else if (msg.equalsIgnoreCase("operate"))
				{
					consent = consentServices.findByCmpIdAndCtoO(empDataSession.getCompanyProfile().getCompanyProfileId());
				}
			}
			modelAndView.addObject("unit", unitServices.findAll());
			modelAndView.addObject("actionType", msg);
			modelAndView.addObject("consent", consent);
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
			modelAndView.setViewName("EnvrOfficer/ViewEData");
		}
		return modelAndView;
	}

	/**
	 * This method used to view and modify the consent details.
	 * 
	 * @param consentId the id of consent
	 * @param issueDate the issue date of consent establish
	 * @param validUpto the consent validate
	 * @param grossCi the gross consent income
	 * @param noStaff the total number of staff
	 * @param noWorker the total number of worker
	 * @param totPlotArea the total size of Plot Area
	 * @param totBuildArea the total size of Build Area
	 * @param totGreenArea the total size of Green Area
	 * @param consentFileOld the consent file uploading
	 * @param request the servlet request we are processing.
	 * @param documentFile the document of consent file
	 * @return isUpdated it return success/failuer
	 * @throws IOException if an input/output error occurs
	 */
	@RequestMapping(value = "ajax-view-consent-modify-consent-data", method = RequestMethod.POST)
	public @ResponseBody String setModifyConsentData(@RequestParam("consentNo") int consentId,
			@RequestParam("issue_date") String issueDate, @RequestParam("valid_upto") String validUpto,
			@RequestParam("gross_ci") float grossCi, @RequestParam("no_staff") int noStaff,
			@RequestParam("no_worker") int noWorker, @RequestParam("tot_plot_area") float totPlotArea,
			@RequestParam("tot_build_area") float totBuildArea, @RequestParam("tot_green_area") float totGreenArea,
			@RequestParam("consent_file_old") String consentFileOld, HttpServletRequest request,
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
				mainFile = consentFileOld;
				filePath = Constant.consent_file_path + consentFileOld;
			}
			else
			{
				file = documentFile.getOriginalFilename();
				bytes = documentFile.getBytes();
				// Get the file and save it somewhere
				mainFile = renameFile + "_" + file;
				Files.write(Paths.get(Constant.consent_file_path + mainFile), bytes);
				file = documentFile.getOriginalFilename();
				filePath = Constant.consent_file_path + mainFile;
			}
			int isUpdated = consentServices.updateConsentByConsentId(consentId, issueDate, validUpto, grossCi, noStaff,
					noWorker, totPlotArea, totBuildArea, totGreenArea, mainFile, filePath);
			return isUpdated == 1 ? Constant.SUCCESS : Constant.FAILURE;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			return Constant.FAILURE;
		}
	}

	@RequestMapping("/ajax-getConsentForm")
	public @ResponseBody String getConsentForm(@RequestParam("consentType") String consentType, HttpServletRequest request)
	{
		JSONArray FinalArray = new JSONArray();
		EmpData empDataSession = null;
		List<Consent> consentDatas = new ArrayList<>();
		try
		{
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				String industry_type = empDataSession.getCompanyProfile().getIndustryCategory();
				if (consentType.equalsIgnoreCase("Consent to Establish"))
				{
					consentDatas = consentServices.findByCmpIdAndCtoE(empDataSession.getCompanyProfile().getCompanyProfileId());
				}
				else if (consentType.equalsIgnoreCase("Consent to Operate"))
				{
					consentDatas = consentServices.findByCmpIdAndCtoO(empDataSession.getCompanyProfile().getCompanyProfileId());
				}
				for (int i = 0; i < consentDatas.size(); i++)
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("consentName", new String(consentDatas.get(i).getConsNo()));
					hashMap.put("consentId", new Integer(consentDatas.get(i).getConsentId()));
					FinalArray.put(hashMap);
				}
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return FinalArray.toString();
	}

	@RequestMapping(value = "elist-product") // Comparative Sheet
	public ModelAndView getElistOfProduct(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{

			List<Consent> consentData = consentServices.findLastAddedConsentByConsType("Consent to Establish", new PageRequest(0, 1));
			int consentId = 0;
			for (int i = 0; i < consentData.size(); i++)
			{
				consentId = consentData.get(i).getConsentId();
			}

			modelAndView.addObject("consentId", consentId);
			modelAndView.addObject("unit", unitServices.findAll());
			modelAndView.addObject("hwCategroriesList", hazardousWastesServices.findAll());
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		modelAndView.setViewName("EnvrOfficer/EListOfProduct");
		return modelAndView;
	}

	@RequestMapping("/ajax-getAllUnits")
	public @ResponseBody String getAllUnits(HttpServletRequest request)
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			List<Unit> UnitsData = new ArrayList<>();
			UnitsData = unitServices.findAll();
			for (int i = 0; i < UnitsData.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("unitId", new Integer(UnitsData.get(i).getUnitId()));
				hashMap.put("unitName", new String(UnitsData.get(i).getUnits()));

				jsonArray.put(hashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	@RequestMapping("/ajax-getAllHzCategory")
	public @ResponseBody String getAllHzCategory(HttpServletRequest request)
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			List<HazardousWastes> hzCategoryData = new ArrayList<>();
			hzCategoryData = hazardousWastesServices.findAll();
			for (int i = 0; i < hzCategoryData.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("categoryNumber", new String(hzCategoryData.get(i).getCategoryNumber()));
				hashMap.put("categoryDesc", new String(hzCategoryData.get(i).getCategoryDesc()));

				jsonArray.put(hashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	@RequestMapping("/ajax-getHzCategoryByCatNumber")
	public @ResponseBody String getHzCategoryByCatNumber(@RequestParam("categoryNumber") String categoryNumber,
			HttpServletRequest request)
	{
		String hzcategory = "";
		try
		{
			hzcategory = hazardousWastesServices.getGategoryTypeByCatNumber(categoryNumber);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return hzcategory;
	}

	@RequestMapping("/ajax-getHzNumberByCatDesc")
	public @ResponseBody String getHzNumberByCatDesc(@RequestParam("categoryDesc") String categoryDesc,
			HttpServletRequest request)
	{
		String hzCategoryDesc = "";
		try
		{
			hzCategoryDesc = hazardousWastesServices.getHzNumberByCatDesc(categoryDesc);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return hzCategoryDesc;
	}

	@RequestMapping("/ajax-getAllWaterSourcesNames")
	public @ResponseBody String getAllWaterSourcesNames(HttpServletRequest request)
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			List<WaterSourceNames> waterSourceNamesList = new ArrayList<>();
			waterSourceNamesList = waterSourceNamesServices.findAll();
			for (int i = 0; i < waterSourceNamesList.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("waterSourceId", new Integer(waterSourceNamesList.get(i).getId()));
				hashMap.put("waterSourceName", new String(waterSourceNamesList.get(i).getSourceName()));

				jsonArray.put(hashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	@RequestMapping("/ajax-getAllFiltersNames")
	public @ResponseBody String getAllFiltersNames()
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			List<FilterNameList> FilterNameList = new ArrayList<>();
			FilterNameList = filterNameListServices.findAll();
			for (int i = 0; i < FilterNameList.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("filterNameId", new Integer(FilterNameList.get(i).getId()));
				hashMap.put("filterName", new String(FilterNameList.get(i).getFilterName()));

				jsonArray.put(hashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	@RequestMapping("/ajax-getAllFilterUseNames")
	public @ResponseBody String getAllFilterUseNames()
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			List<FilterUseNames> filterUseNamesList = new ArrayList<>();
			filterUseNamesList = filterUseNameServices.findAll();
			for (int i = 0; i < filterUseNamesList.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("filterUseNameId", new Integer(filterUseNamesList.get(i).getId()));
				hashMap.put("filterUseName", new String(filterUseNamesList.get(i).getFilterUseName()));

				jsonArray.put(hashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	// "ajax-getWaterSourceAndFilterLbl"
	@RequestMapping("test")
	public @ResponseBody String getWaterSourcesAndFilterLbl()
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			List<WaterSourceNames> waterSourceNamesList = new ArrayList<>();
			waterSourceNamesList = waterSourceNamesServices.findAll();
			for (int i = 0; i < waterSourceNamesList.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("waterSourceId", new Integer(waterSourceNamesList.get(i).getId()));
				hashMap.put("waterSourceName", new String(waterSourceNamesList.get(i).getSourceName()));

				jsonArray.put(hashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}
}
