package com.tes.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tes.model.RegAPC;
import com.tes.model.Stack;
import com.tes.model.Users;
import com.tes.services.UsersServices;
import com.tes.services.environmentalofficer.RegAPCServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping("/rest/apc")
public class ApcRestController
{

	private static final Logger LOGGER = LogManager.getLogger(ApcRestController.class);

	@Autowired
	StackServices stackServices;

	@Autowired
	RegAPCServices regAPCServices;

	@Autowired
	UsersServices usersServices;

	@GetMapping
	private ArrayList<Object> getApcSystem()
	{
		String today = Utilities.getTodaysDate();
		List<Stack> stackData = new ArrayList<>();
		ArrayList<Object> apcDataArrayList = new ArrayList<>();
		ArrayList<Object> jsonApcData = new ArrayList<>();
		HashMap<String, Object> apcDataConditionMapList = new HashMap<String, Object>();
		List<RegAPC> regapc = new ArrayList<>();
		List<Stack> stackDataList = new ArrayList<>();
		try
		{
			stackData = stackServices.findStackData(today);
			if (!Validator.isEmpty(stackData))
			{
				for (int i = 0; i < stackData.size(); i++)
				{
					Stack stack = new Stack();
					stack = stackData.get(i);
					if (stackData.get(0).getApc().equalsIgnoreCase("Yes"))
					{
						regapc = regAPCServices.getRegAPCData(stackData.get(i).getStackId(), new PageRequest(0, 1));
						if (Validator.isEmpty(regapc))
							regapc = null;
						else if (!Validator.isEmpty(regapc))
						{
							stack.setRegAPCList(regapc);
						}
					}
					stackDataList.add(stack);
				}
			}
			if (!Validator.isEmpty(stackDataList))
			{
				for (Stack stack : stackDataList)
				{
					HashMap<String, Object> apcDataListMap = new HashMap<String, Object>();
					if (!Validator.isEmpty(stack.getRegAPCList()))
					{
						for (RegAPC regapcData : stack.getRegAPCList())
						{
							if (regapcData.getApcDate().equals(Utilities.getTodaysDate()))
							{
								apcDataListMap.put("stackId", stack.getStackId());
								apcDataListMap.put("stackName", stack.getStackName());
								apcDataListMap.put("apcSystem", stack.getApcSystem());
								apcDataListMap.put("usersId", regapcData.getUsers().getUsersId());
								apcDataListMap.put("startReading", regapcData.getStartReading());
								apcDataListMap.put("endReading", regapcData.getEndReading());
								apcDataListMap.put("actualReading", regapcData.getActualReading());
							}
							else
							{
								apcDataListMap.put("stackId", stack.getStackId());
								apcDataListMap.put("stackName", stack.getStackName());
								apcDataListMap.put("apcSystem", stack.getApcSystem());
								apcDataListMap.put("usersId", regapcData.getUsers().getUsersId());
								apcDataListMap.put("startReading", regapcData.getStartReading());
								apcDataListMap.put("endReading", "");
								apcDataListMap.put("actualReading", "");
							}
						}
						apcDataConditionMapList.put("Input", Constant.NO);
					}
					else
					{
						apcDataListMap.put("stackId", stack.getStackId());
						apcDataListMap.put("stackName", stack.getStackName());
						apcDataListMap.put("apcSystem", stack.getApcSystem());
						apcDataListMap.put("usersId", "");
						apcDataListMap.put("startReading", "");
						apcDataListMap.put("endReading", "");
						apcDataListMap.put("actualReading", "");
						apcDataConditionMapList.put("Input", Constant.YES);
					}
					apcDataArrayList.add(apcDataListMap);
				}
				apcDataConditionMapList.put("Result", Constant.TRUE);
				apcDataConditionMapList.put("APCList", apcDataArrayList);
			}
			else
			{
				apcDataConditionMapList.put("Result", Constant.FALSE);
			}
			jsonApcData.add(apcDataConditionMapList);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonApcData;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private String setRegApc(@RequestBody JsonArray regApcList)
	{
		try
		{
			for (JsonElement regApc : regApcList)
			{
				JsonObject jsonObject = regApc.getAsJsonObject();
				JsonArray apcData = jsonObject.getAsJsonArray("APCList");
				for (JsonElement apcDatas : apcData)
				{
					RegAPC objRegAPC = new RegAPC();
					Stack objStackId = new Stack();
					Users objUsersId = new Users();
					objStackId.setStackId(apcDatas.getAsJsonObject().get("stackId").getAsInt());
					objUsersId.setUsersId(apcDatas.getAsJsonObject().get("usersId").getAsInt());
					objRegAPC.setStack(objStackId);
					objRegAPC.setStartReading(apcDatas.getAsJsonObject().get("startReading").getAsFloat());
					objRegAPC.setEndReading(apcDatas.getAsJsonObject().get("endReading").getAsFloat());
					objRegAPC.setActualReading(apcDatas.getAsJsonObject().get("actualReading").getAsFloat());
					objRegAPC.setApcDate(Utilities.getTodaysDate());
					objRegAPC.setUsers(objUsersId);
					regAPCServices.save(objRegAPC);
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
