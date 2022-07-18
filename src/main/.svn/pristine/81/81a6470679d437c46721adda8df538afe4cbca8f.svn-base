package com.tes.controller.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.WaterSewPoll;
import com.tes.model.WateriePollutant;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping("/rest/water-effluent-sewage")
public class WaterRestApiController
{

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	private static final Logger LOGGER = LogManager.getLogger(WaterRestApiController.class);

	@GetMapping("/effluent/{companyId}")
	private ArrayList<Object> sendEffluentPollutantList(@PathVariable("companyId") int companyId)
	{
		ArrayList<Object> effluentList = new ArrayList<>();
		HashMap<String, Object> effluentListHashMap = new HashMap<>();
		ArrayList<Object> finalEffluentResultList = new ArrayList<>();
		try
		{
			companyId = companyProfileServices.getCompanyProfileId();
		}
		catch (Exception e1)
		{
			companyId = 0;
		}
		String today = Utilities.getTodaysDate();

		try
		{
			if (!Validator.isEmpty(companyId))
			{
				List<WateriePollutant> wateriePollList = wateriePollutantServices.findByTodayDateAndCid(today);
				if (!wateriePollList.isEmpty())
				{
					for (int i = 0; i < wateriePollList.size(); i++)
					{
						LinkedHashMap<String, Object> effluentHashmap = new LinkedHashMap<>();
						effluentHashmap.put("PollName", wateriePollList.get(i).getPollName());
						effluentHashmap.put("Unit", wateriePollList.get(i).getUnit().getUnits());
						effluentList.add(effluentHashmap);
					}
					effluentListHashMap.put("List", effluentList);// need to change ....by vishal
					effluentListHashMap.put("Result", Constant.TRUE);
					finalEffluentResultList.add(effluentListHashMap);
				}
				else
				{
					effluentListHashMap.put("Result", Constant.FALSE);
					finalEffluentResultList.add(effluentListHashMap);
				}
			}
		}
		catch (Exception e)
		{
			effluentListHashMap.put("Result", Constant.FALSE);
			finalEffluentResultList.add(effluentListHashMap);
		}
		return finalEffluentResultList;
	}

	@GetMapping("/sewage/{companyId}")
	private ArrayList<Object> sendSewagePollutantList(@PathVariable("companyId") int companyId)
	{
		ArrayList<Object> sewageList = new ArrayList<>();
		HashMap<String, Object> sewageListHashMap = new HashMap<>();
		ArrayList<Object> finalSewageResultList = new ArrayList<>();

		try
		{
			companyId = companyProfileServices.getCompanyProfileId();
		}
		catch (Exception e1)
		{
			companyId = 0;
		}
		String today = Utilities.getTodaysDate();
		try
		{
			if (!Validator.isEmpty(companyId))
			{
				List<WaterSewPoll> waterSewPollList = waterSewPollServices.findByTodayDateAndCmpid(today);
				if (!waterSewPollList.isEmpty())
				{
					for (int i = 0; i < waterSewPollList.size(); i++)
					{
						LinkedHashMap<String, Object> sewageHashmap = new LinkedHashMap<>();
						sewageHashmap.put("PollName", waterSewPollList.get(i).getPollName());
						sewageHashmap.put("Unit", waterSewPollList.get(i).getUnit().getUnits());
						sewageList.add(sewageHashmap);
					}
					sewageListHashMap.put("List", sewageList);// need to change ....by vishal
					sewageListHashMap.put("Result", Constant.TRUE);
					finalSewageResultList.add(sewageListHashMap);
				}
				else
				{
					sewageListHashMap.put("Result", Constant.FALSE);
					finalSewageResultList.add(sewageListHashMap);
				}
			}
		}
		catch (Exception e)
		{
			sewageListHashMap.put("Result", Constant.FALSE);
			finalSewageResultList.add(sewageListHashMap);
		}
		return finalSewageResultList;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private String saveRegEffluentAndSewageData(@RequestParam("effluentOrSewageData") String effluentOrSewageData,
			@RequestParam("file") MultipartFile file, HttpSession session)
	{
		try
		{
			String fileName = file.getOriginalFilename();
			String realPathtoUploads = session.getServletContext().getRealPath("uploadsDir");
			if (!new File(realPathtoUploads).exists())
			{
				new File(realPathtoUploads).mkdir();
			}
			String filePath = realPathtoUploads + "/" + fileName;
			file.transferTo(new File(filePath));
			JSONObject effluentOrSewageDataObj = new JSONObject(effluentOrSewageData);
			if (!Validator.isEmpty(effluentOrSewageDataObj))
			{
				JSONArray effluentOrSewageArray = effluentOrSewageDataObj.getJSONArray("effluentOrSewageData");
				if (!Validator.isEmpty(effluentOrSewageArray))
				{
					for (int i = 0; i < effluentOrSewageArray.length(); i++)
					{
						JSONObject effluentOrSewageObj1 = new JSONObject(effluentOrSewageArray.get(i).toString());
						if (!Validator.isEmpty(effluentOrSewageObj1))
						{
							JSONArray waterPollArray = effluentOrSewageObj1.getJSONArray("WaterPollList");
							if (!Validator.isEmpty(waterPollArray))
							{
								for (int j = 0; j < waterPollArray.length(); j++)
								{
									JSONObject waterPollDataObj = new JSONObject(waterPollArray.get(j).toString());
									if (effluentOrSewageObj1.get("type").toString().equalsIgnoreCase("effluent"))
									{
										RegEffPoll regEffPoll = new RegEffPoll();
										// regEffPoll.setPollName(waterPollDataObj.get("pollName").toString());
										regEffPoll.setInConsE(Float.parseFloat(waterPollDataObj.get("pollInlet").toString()));
										regEffPoll.setOuConsE(Float.parseFloat(waterPollDataObj.get("pollOutlet").toString()));
										regEffPoll.setUnitE(waterPollDataObj.get("pollUnit").toString());
										regEffPoll.setSampE(effluentOrSewageObj1.get("sampleDate").toString());
										regEffPoll.setSubE(effluentOrSewageObj1.get("submitDate").toString());
										regEffPoll.setFileName(fileName);
										regEffPoll.setFilePath(filePath);
										regEffPollServices.save(regEffPoll);
									}
									else if (effluentOrSewageObj1.get("type").toString().equalsIgnoreCase("sewage"))
									{
										RegSewPoll regSewPoll = new RegSewPoll();
										// regSewPoll.setPollName(waterPollDataObj.get("pollName").toString());
										regSewPoll.setInConsS(Float.parseFloat(waterPollDataObj.get("pollInlet").toString()));
										regSewPoll.setOuConsS(Float.parseFloat(waterPollDataObj.get("pollOutlet").toString()));
										regSewPoll.setUnitS(waterPollDataObj.get("pollUnit").toString());
										regSewPoll.setSampS(effluentOrSewageObj1.get("sampleDate").toString());
										regSewPoll.setSubS(effluentOrSewageObj1.get("submitDate").toString());
										regSewPoll.setFileName(fileName);
										regSewPoll.setFilePath(filePath);
										regSewPollServices.save(regSewPoll);
									}
								}
							}
						}
					}
				}
			}
			return Constant.SUCCESS;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			return Constant.FAILURE;
		}
	}
}
