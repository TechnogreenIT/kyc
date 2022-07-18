package com.tes.controller.api;

import java.io.File;
import java.io.IOException;
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
import com.tes.model.RegStPoll;
import com.tes.model.Stack;
import com.tes.model.StackPoll;
import com.tes.model.StackPollData;
import com.tes.services.thirdparty.RegStPollServices;
import com.tes.services.thirdparty.StackPollDataServices;
import com.tes.services.thirdparty.StackPollServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping("/rest/stack-and-stackpoll")
public class StackAndStackPollRestController
{

	@Autowired
	RegStPollServices regStPollServices;

	@Autowired
	StackPollDataServices stackPollDataServices;

	@Autowired
	StackServices stackServices;

	@Autowired
	StackPollServices stackPollServices;

	@GetMapping
	private ArrayList<Object> sendStackIdAndName()
	{
		ArrayList<Object> stackList = new ArrayList<>();
		HashMap<String, Object> stackListHashMap = new HashMap<>();
		ArrayList<Object> finalStackResultList = new ArrayList<>();
		String today = Utilities.getTodaysDate();
		try
		{
			List<Stack> stackIdAndName = stackServices.getConsentId(today);
			if (!Validator.isEmpty(stackIdAndName))
			{
				for (int i = 0; i < stackIdAndName.size(); i++)
				{
					// String stackNameWithAttachedTo=stackIdAndName.get(i).getStackName()+"-"+stackIdAndName.get(i).getAttachedTo();
					HashMap<String, Object> stackIdAndNameList = new HashMap<>();
					stackIdAndNameList.put("stackId", stackIdAndName.get(i).getStackId());
					stackIdAndNameList.put("stackName", stackIdAndName.get(i).getStackName());
					stackIdAndNameList.put("attachedTo", stackIdAndName.get(i).getAttachedTo());
					stackList.add(stackIdAndNameList);
				}
				stackListHashMap.put("Result", Constant.TRUE);
				stackListHashMap.put("StackList", stackList);
				finalStackResultList.add(stackListHashMap);
			}
			else
			{
				stackListHashMap.put("Result", Constant.FALSE);
				finalStackResultList.add(stackListHashMap);
			}
		}
		catch (Exception e)
		{
			stackListHashMap.put("Result", Constant.FALSE);
			finalStackResultList.add(stackListHashMap);
		}
		return finalStackResultList;
	}

	@GetMapping("/{stackId}")
	private ArrayList<Object> sendStackInfo(@PathVariable("stackId") int stackId)
	{

		ArrayList<Object> stackFinalArrayList = new ArrayList<>();
		// ArrayList<Object> stackArrayList=new ArrayList<>();
		LinkedHashMap<String, Object> stackJsonHashMap = new LinkedHashMap<>();
		try
		{
			Stack stackInfo = stackServices.findByStackId(stackId);
			if (!Validator.isEmpty(stackInfo))
			{
				LinkedHashMap<String, Object> stackInfoList = new LinkedHashMap<>();
				// For Stack Details
				stackInfoList.put("Material of Stack Details", stackInfo.getMatCons());
				stackInfoList.put("Stack Height above roof Details", stackInfo.getHeight());
				stackInfoList.put("Type of Stack Details", stackInfo.getShape());
				stackInfoList.put("Fuel Type Details", stackInfo.getFuelType());
				stackInfoList.put("Flue Gas Temperature Details", "");
				stackInfoList.put("Differential Pressure Details", "");
				stackInfoList.put("Diameter of Stack Details", stackInfo.getDiam());
				stackInfoList.put("Velocity Details", "");
				stackInfoList.put("Hours of Operation Details", "");
				stackInfoList.put("Gas Volume Details", "");

				// For stack Unit
				stackInfoList.put("Material of Stack Unit", "--");
				stackInfoList.put("Stack Height above roof Unit", stackInfo.getHtUnits());
				stackInfoList.put("Type of Stack Unit", "--");
				stackInfoList.put("Fuel Type Unit", "--");
				stackInfoList.put("Flue Gas Temperature Unit", "K");
				stackInfoList.put("Differential Pressure Unit", "mmWG");
				stackInfoList.put("Diameter of Stack Unit", stackInfo.getDiamUnits());
				stackInfoList.put("Velocity Unit", "m/s");
				stackInfoList.put("Hours of Operation Unit", "");
				stackInfoList.put("Gas Volume Unit", "Nm3/Hr");
				// stackArrayList.add(stackInfoList);
				stackJsonHashMap.put("stackList", stackInfoList);

				ArrayList<Object> stackPollArray = new ArrayList<>();

				List<StackPoll> stackpoll = stackPollServices.findByStackId(stackId);
				if (!Validator.isEmpty(stackpoll))
				{
					for (int j = 0; j < stackpoll.size(); j++)
					{
						LinkedHashMap<String, Object> stackPollList = new LinkedHashMap<>();
						stackPollList.put("pollName", stackpoll.get(j).getPollName());
						stackPollList.put("UnitName", stackpoll.get(j).getUnit().getUnits());
						stackPollArray.add(stackPollList);
					}
					stackJsonHashMap.put("stackPollList", stackPollArray);
				}
				stackJsonHashMap.put("Result", Constant.TRUE);
				stackFinalArrayList.add(stackJsonHashMap);
			}
			else
			{
				stackJsonHashMap.put("Result", Constant.FALSE);
				stackFinalArrayList.add(stackJsonHashMap);
			}
		}
		catch (Exception e)
		{
			stackJsonHashMap.put("Result", Constant.FALSE);
			stackFinalArrayList.add(stackJsonHashMap);
		}
		return stackFinalArrayList;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private String setRegStPollAndStackPollData(@RequestParam("stackPollData") String stackAndPollData,
			@RequestParam("file") MultipartFile file, HttpSession session) throws Throwable, IOException
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
			JSONObject regStPollObj = new JSONObject(stackAndPollData);
			Stack stack = new Stack();
			if (!Validator.isEmpty(regStPollObj))
			{
				stack.setStackId(Integer.parseInt(regStPollObj.get("stackId").toString()));
				RegStPoll regStPoll = new RegStPoll();
				regStPoll.setStack(stack);
				regStPoll.setStackName(regStPollObj.get("stackName").toString());
				regStPoll.setAttachTo(regStPollObj.get("attachedTo").toString());
				regStPoll.setSampSt(regStPollObj.get("sampleDate").toString());
				regStPoll.setSubSt(regStPollObj.get("submitDate").toString());
				regStPoll.setGasTemp(regStPollObj.get("gasTemp").toString());
				regStPoll.setPressure(regStPollObj.get("diffPressure").toString());
				regStPoll.setExitGasVelocity(Float.parseFloat(regStPollObj.get("exitGasVelocity").toString()));
				String hrsOp = regStPollObj.get("hoursOfOperation").toString() + " "
						+ regStPollObj.get("hoursUnit").toString();
				regStPoll.setHrsOp(hrsOp);
				regStPoll.setGasQuant(regStPollObj.get("gasVolume").toString());
				regStPoll.setFileName(fileName);
				regStPoll.setStackFilePath(filePath);
				regStPollServices.save(regStPoll);
				JSONArray stackPollDataArray = regStPollObj.getJSONArray("stackPollData");
				if (!Validator.isEmpty(stackPollDataArray))
				{
					for (int i = 0; i < stackPollDataArray.length(); i++)
					{
						JSONObject stackPollDataObject = stackPollDataArray.getJSONObject(i);
						StackPollData stackPollData = new StackPollData();
						stackPollData.setRegStPoll(regStPoll);
						stackPollData.setConcPoll(Float.parseFloat(stackPollDataObject.get("concPoll").toString()));
						stackPollData.setPollName(stackPollDataObject.get("pollName").toString());
						stackPollData.setPollUnit(stackPollDataObject.get("pollUnit").toString());
						stackPollDataServices.save(stackPollData);
					}
				}
			}
			return Constant.SUCCESS;
		}
		catch (NumberFormatException e)
		{
			return Constant.FAILURE;
		}
	}
}
