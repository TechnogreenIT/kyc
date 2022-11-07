package com.tes.controller.management;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.CompanyProfile;
import com.tes.model.Consent;
import com.tes.model.EmpData;
import com.tes.model.EsrHwSolidWaste;
import com.tes.model.EsrInvestment;
import com.tes.model.EsrParticulars;
import com.tes.model.EsrPollutionControl;
import com.tes.model.EsrProductWater;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.ContainersServices;
import com.tes.services.environmentalofficer.EsrHwSolidWasteServices;
import com.tes.services.environmentalofficer.EsrInvestmentServices;
import com.tes.services.environmentalofficer.EsrParticularsServices;
import com.tes.services.environmentalofficer.EsrPollutionControlServices;
import com.tes.services.environmentalofficer.EsrProductWaterServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate used to View all the Compliance data and manage by Management.
 * 
 * @author Sushama Dadas
 * @author Tushar Chougule
 */
@Controller
@RequestMapping("/management")
public class ManagementComplianceController
{

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	WasteDescriptionConsistencyServices wasteDescriptionConsistencyServices;

	@Autowired
	ContainersServices containersServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	EsrProductWaterServices esrProductWaterServices;

	@Autowired
	EsrHwSolidWasteServices esrHwSolidWasteServices;

	@Autowired
	EsrPollutionControlServices esrPollutionControlServices;

	@Autowired
	EsrInvestmentServices esrInvestmentServices;

	@Autowired
	EsrParticularsServices esrParticularsServices;

	private static final Logger LOGGER = LogManager.getLogger(ManagementComplianceController.class);

	/**
	 * This method used to manage to ESR form
	 * 
	 * @param encodedYear The encoded year
	 * @param request The servlet request we are processing.
	 * @return ManagementEsr
	 */
//	@RequestMapping("management-esr-form")
	@RequestMapping("/management-esr-form")
	public ModelAndView getYearlyESRform(@RequestParam(value = "year", required = false) String encodedYear,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		String year = Utilities.decodeString(encodedYear), dateRes[] = year.split("-"),
				fromDate = (dateRes[0]) + "-04-01", toDate = (dateRes[1]) + "-03-31", esrConsentDate = toDate,
				lastEnvSubmitted = null, consNo = "", issueDate = "", validUpto = "",
				set_title = "31st March " + dateRes[1];

		modelAndView.setViewName("Management/ManagementEsr");
		String year1[] = year.split("-");
		int a = Integer.parseInt(year1[0]), b = Integer.parseInt(year1[1]);
		String year2 = (a + 1) + "-" + (b + 1);
		List<String> consNoList = new ArrayList<>();
		List<String> issueDateList = new ArrayList<>();
		List<String> validUptoList = new ArrayList<>();
		List<Float> capitalInvestmentList = new ArrayList<>();
		float capitalInvestment = 0.0f;
		List<EsrProductWater> esrProductWaterData = new ArrayList<>();
		List<EsrHwSolidWaste> esrHwSolidWasteData = new ArrayList<>();
		List<EsrHwSolidWaste> esrHwSolidWasteDataBySolidYear = new ArrayList<>();
		List<EsrPollutionControl> esrPollutionControlDataByYear = new ArrayList<>();
		List<EsrInvestment> getEsrInvestmentbyYear = new ArrayList<>();
		List<EsrInvestment> getEsrInvestmentbyNextYear = new ArrayList<>();
		List<EsrParticulars> esrParticularsDataByYear = new ArrayList<>();
		try
		{
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int usersId = empDataSession.getUsers().getUsersId();
			EmpData userlogindata = empDataServices.getUserProfileData(usersId);
			List<CompanyProfile> companyProfileDatas = companyProfileServices.findAll();
			for (CompanyProfile companyProfileData : companyProfileDatas)
			{
				String lastEnv = companyProfileData.getLastEnv();
				if (lastEnv.equals(""))
				{
					lastEnvSubmitted = "No";
				}
				else
				{
					lastEnvSubmitted = "Yes";
				}
			}
			List<Consent> consentDatas = consentServices.getConsentDataByIssueDate(esrConsentDate);
			if (!Validator.isEmpty(consentDatas))
			{
				for (int i = 0; i < consentDatas.size(); i++)
				{
					consNo = consentDatas.get(i).getConsNo();
					issueDate = consentDatas.get(i).getIssueDate();
					validUpto = consentDatas.get(i).getValidUpto();
					capitalInvestment = consentDatas.get(i).getGrossCi();
					if (!consNoList.contains(consNo))
					{
						consNoList.add(consNo);
					}
					if (!issueDateList.contains(issueDate))
					{
						issueDateList.add(issueDate);
					}
					if (!validUptoList.contains(validUpto))
					{
						validUptoList.add(validUpto);
					}
					if (!capitalInvestmentList.contains(capitalInvestment))
					{
						capitalInvestmentList.add(capitalInvestment);
					}
				}
			}
			esrProductWaterData = esrProductWaterServices.getEsrProductWaterData(year, "Recycled");
			esrHwSolidWasteData = esrHwSolidWasteServices.getEsrHwSolidWasteDataByHazYear(year);
			esrHwSolidWasteDataBySolidYear = esrHwSolidWasteServices.getEsrHwSolidWasteDataBySolidYear(year);
			esrPollutionControlDataByYear = esrPollutionControlServices.findOneByEsrPollutionByYear(year);
			getEsrInvestmentbyYear = esrInvestmentServices.findOneByGetEsrInvestmentByYear(year);
			getEsrInvestmentbyNextYear = esrInvestmentServices.findOneByGetEsrInvestmentByNextYear(year, year2);
			esrParticularsDataByYear = esrParticularsServices.getEsrParticularsbyyear(year);
			String esrDatas[] = {year, set_title, lastEnvSubmitted, fromDate, toDate, esrConsentDate};
			modelAndView.addObject("userlogindata", userlogindata);
			modelAndView.addObject("companyProfileDatas", companyProfileDatas);
			modelAndView.addObject("consentDatas", consentDatas);
			modelAndView.addObject("consNoList", consNoList);
			modelAndView.addObject("issueDateList", issueDateList);
			modelAndView.addObject("validUptoList", validUptoList);
			modelAndView.addObject("capitalInvestmentList", capitalInvestmentList);
			modelAndView.addObject("esrDatas", esrDatas);
			modelAndView.addObject("esrConsentDate", esrConsentDate);
			modelAndView.addObject("esrProductWaterData", esrProductWaterData);
			modelAndView.addObject("esrHwSolidWasteData", esrHwSolidWasteData);
			modelAndView.addObject("esrHwSolidWasteDataBySolidYear", esrHwSolidWasteDataBySolidYear);
			modelAndView.addObject("esrPollutionControlDataByYear", esrPollutionControlDataByYear);
			modelAndView.addObject("getEsrInvestmentbyYear", getEsrInvestmentbyYear);
			modelAndView.addObject("getEsrInvestmentbyNextYear", getEsrInvestmentbyNextYear);
			modelAndView.addObject("esrParticularsDataByYear", esrParticularsDataByYear);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	/**
	 * This method used to manage the water cess form.
	 * 
	 * @return Management/WaterCessForm
	 */
	@RequestMapping("management-water-cess-form")
	public ModelAndView getWaterCessForm()
	{
		return new ModelAndView("Management/WaterCessForm");
	}

	/**
	 * This method used to manage the water budget data.
	 * 
	 * @return Management/WaterBudget
	 */
	@RequestMapping("management-water-budget")
	public ModelAndView getWaterBudget()
	{
		return new ModelAndView("Management/WaterBudget");
	}
	
	@RequestMapping(value = "/ajax-getHazardousValuesMan")
	@ResponseBody
	public String getHazardousValues() throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		String todayDate = Utilities.getTodaysDate();
		String today_date[] = todayDate.split("-");
		String jsonString = null;
		int esrMinYear = 0, esrMaxYear = Integer.parseInt(today_date[0]);
		try
		{
			esrMinYear = consentServices.consentMinYear();
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("esrMinYear", new Integer(esrMinYear));
			jsonobject.put("esrMaxYear", new Integer(esrMaxYear));
			jsonArray.put(jsonobject);
			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonString;
	}
	
	
	@RequestMapping("envr-officer-hazardous-return-man")
	public ModelAndView getHazardousReturn(@RequestParam(value = "year", required = false) String encodedYear,
			HttpServletRequest request) throws ParseException
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			String issueDate = "", consNo = "";
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int usersId = empDataSession.getUsers().getUsersId();
			EmpData userlogindata = empDataServices.getUserProfileData(usersId);
			modelAndView.setViewName("EnvrOfficer/HazardousReturn");
			String today = Utilities.getTodaysDate();
			String year = Utilities.decodeString(encodedYear);
			String dateRes[] = year.split("-");
			String fromDate = (dateRes[0]) + "-04-01";
			String toDate = (dateRes[1]) + "-03-31";
			List<Consent> getissueDateAndconsentNumber = new ArrayList<>();
			List<String> issueDateList = new ArrayList<String>();
			List<String> consentNumberList = new ArrayList<String>();
			List<CompanyProfile> companyData = empDataServices.getCompanydata();
			getissueDateAndconsentNumber = consentServices.getIssueDateAndConsNoByIssueDate(fromDate, toDate, today);
			for (int i = 0; i < getissueDateAndconsentNumber.size(); i++)
			{
				issueDate = getissueDateAndconsentNumber.get(i).getIssueDate();
				consNo = getissueDateAndconsentNumber.get(i).getConsNo();
				if (!issueDateList.contains(issueDate))
				{
					issueDateList.add(issueDate);
				}
				if (!consentNumberList.contains(consNo))
				{
					consentNumberList.add(consNo);
				}
			}
			modelAndView.addObject("userlogindata", userlogindata);
			modelAndView.addObject("companyData", companyData);
			modelAndView.addObject("issueDateList", issueDateList);
			modelAndView.addObject("consentNumberList", consentNumberList);
			modelAndView.addObject("today", today);
			modelAndView.addObject("selectedYear", year);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}
	// made by monali
		@RequestMapping(value = "/ajax-getYearlyEsrValuesMan")
		@ResponseBody
		public String getYearlyEsrValues() throws JSONException
		{
			String jsonString = null, todayDate = Utilities.getTodaysDate(), today_date[] = todayDate.split("-");
			int esrMaxYear = Integer.parseInt(today_date[0]) + 1, esrMinYear = 0;
			JSONArray jsonArray = new JSONArray();
			try
			{
				esrMinYear = consentServices.consentMinYearForEsr();
				int maxYearDiff = esrMaxYear - esrMinYear;
				for (int i = 0; i <= maxYearDiff; i++)
				{
					String yearPair = (esrMaxYear - 1) + "-" + esrMaxYear;
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("esrYear", new String(yearPair));
					jsonArray.put(hashMap);
					esrMaxYear = esrMaxYear - 1;
				}
				jsonString = jsonArray.toString();
			}
			catch (Exception e)
			{
				LOGGER.error(e);
			}
			return jsonString;
		}











	





}
