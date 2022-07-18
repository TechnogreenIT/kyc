package com.tes.controller.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
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
import com.tes.model.Ambient;
import com.tes.model.AmbientPoll;
import com.tes.model.AmbientPollData;
import com.tes.model.RegAmbientPoll;
import com.tes.services.environmentalofficer.AmbientPollServices;
import com.tes.services.environmentalofficer.AmbientServices;
import com.tes.services.thirdparty.AmbientPollDataServices;
import com.tes.services.thirdparty.RegAmbientPollServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping("/rest/ambient")
public class AmbientRestApiController
{

	@Autowired
	AmbientServices ambientServices;

	@Autowired
	AmbientPollServices ambientPollServices;

	@Autowired
	RegAmbientPollServices regAmbientPollServices;

	@Autowired
	AmbientPollDataServices ambientPollDataServices;

	@GetMapping
	private ArrayList<Object> sendAmbientIdAndName()
	{
		ArrayList<Object> ambientList = new ArrayList<>();
		HashMap<String, Object> ambientListHashMap = new HashMap<>();
		ArrayList<Object> finalAmbientResultList = new ArrayList<>();
		String today = Utilities.getTodaysDate();
		try
		{
			List<Ambient> ambientIdNameList = ambientServices.findByAmbient(today);
			if (!ambientIdNameList.isEmpty())
			{
				for (int i = 0; i < ambientIdNameList.size(); i++)
				{
					HashMap<String, Object> ambientHashList = new HashMap<>();
					ambientHashList.put("ambientId", ambientIdNameList.get(i).getAmbientId());
					ambientHashList.put("ambientLocName", ambientIdNameList.get(i).getAmbientLocName());
					ambientHashList.put("criteria", ambientIdNameList.get(i).getCriteria());
					ambientList.add(ambientHashList);
				}
				ambientListHashMap.put("Result", Constant.TRUE);
				ambientListHashMap.put("ambientList", ambientList);
				finalAmbientResultList.add(ambientListHashMap);
			}
			else
			{
				ambientListHashMap.put("Result", Constant.FALSE);
				finalAmbientResultList.add(ambientListHashMap);
			}

		}
		catch (Exception e)
		{
			ambientListHashMap.put("Result", Constant.FALSE);
			finalAmbientResultList.add(ambientListHashMap);
		}
		return finalAmbientResultList;
	}

	@GetMapping("/{ambientId}")
	private ArrayList<Object> sendStackInfoWithId(@PathVariable("ambientId") int ambientId)
	{
		ArrayList<Object> ambientArray = new ArrayList<>();
		LinkedHashMap<String, Object> ambientLinkedHashList = new LinkedHashMap<>();
		ArrayList<Object> finalAmbientArray = new ArrayList<>();
		try
		{
			List<AmbientPoll> ambientInfoList = ambientPollServices.findByAmbientId(ambientId);
			if (!Validator.isEmpty(ambientInfoList))
			{
				for (int i = 0; i < ambientInfoList.size(); i++)
				{
					LinkedHashMap<String, Object> ambientHashList = new LinkedHashMap<>();
					ambientHashList.put("PollName", ambientInfoList.get(i).getPollName());
					ambientHashList.put("PollUnit", ambientInfoList.get(i).getUnit().getUnits());
					ambientArray.add(ambientHashList);
				}
				ambientLinkedHashList.put("AmbientList", ambientArray);
				ambientLinkedHashList.put("Result", Constant.TRUE);
				finalAmbientArray.add(ambientLinkedHashList);
			}
			else
			{
				ambientLinkedHashList.put("Result", Constant.FALSE);
				finalAmbientArray.add(ambientLinkedHashList);
			}
		}
		catch (Exception e)
		{
			ambientLinkedHashList.put("Result", Constant.FALSE);
			finalAmbientArray.add(ambientLinkedHashList);
		}
		return finalAmbientArray;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private String setAmbientData(@RequestParam("ambientPollData") String regambientData,
			@RequestParam("file") MultipartFile file, HttpSession session)
	{
		try
		{
			String originalName = file.getOriginalFilename();
			String realPathtoUploads = session.getServletContext().getRealPath("uploadsDir");
			if (!new File(realPathtoUploads).exists())
			{
				new File(realPathtoUploads).mkdir();
			}
			String filename = realPathtoUploads + "/" + originalName;
			file.transferTo(new File(filename));
			Ambient ambient = new Ambient();
			JSONObject regAmbientObject = new JSONObject(regambientData);
			ambient.setAmbientId(Integer.parseInt(regAmbientObject.get("ambientId").toString()));
			RegAmbientPoll regAmbientPoll = new RegAmbientPoll();
			regAmbientPoll.setAmbient(ambient);
			regAmbientPoll.setSampAmb(regAmbientObject.get("sampAmb").toString());
			regAmbientPoll.setSubAmb(regAmbientObject.get("subAmb").toString());
			regAmbientPoll.setLocName(regAmbientObject.get("locName").toString());
			regAmbientPoll.setCriteria(regAmbientObject.get("criteria").toString());
			regAmbientPoll.setFileName(filename);
			regAmbientPollServices.save(regAmbientPoll);
			JSONArray ambientPollArray = regAmbientObject.getJSONArray("AmbientList");
			for (int i = 0; i < ambientPollArray.length(); i++)
			{
				JSONObject ambientPollObject = ambientPollArray.getJSONObject(i);
				AmbientPollData ambientPollData = new AmbientPollData();
				ambientPollData.setRegAmbientPoll(regAmbientPoll);
				ambientPollData.setPollName(ambientPollObject.get("PollName").toString());
				ambientPollData.setConcPoll(ambientPollObject.get("PollQuantity").toString());
				ambientPollData.setUnits(ambientPollObject.get("PollUnit").toString());
				ambientPollDataServices.save(ambientPollData);
			}
			return Constant.SUCCESS;
		}
		catch (Exception e)
		{
			return Constant.FAILURE;
		}
	}
}
