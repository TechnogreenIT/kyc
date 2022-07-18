package com.tes.controller.management;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.JsonObject;
import com.tes.model.EmpData;
import com.tes.model.ModifiedRecord;
import com.tes.model.RegularData;
import com.tes.model.Todo;
import com.tes.model.Users;
import com.tes.repository.TodoRepository;
import com.tes.repository.UsersRepository;
import com.tes.repository.admin.EmpDataRepository;
import com.tes.repository.environmentalofficer.ModifiedRecordRepository;
import com.tes.repository.environmentalofficer.RegularDataRepository;
import com.tes.services.impl.environmentalofficer.ModifiedRecordServicesImpl;
import com.tes.utilities.AuthenticationUtil;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping(value = {"/management"})
public class DailyDataController
{

	@Autowired
	ModifiedRecordServicesImpl modifiedRecordServicesImpl;

	@Autowired
	ModifiedRecordRepository modifiedRecordRepository;

	@Autowired
	RegularDataRepository regularDataRepository;

	@Autowired
	AuthenticationUtil authenticationUtil;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	EmpDataRepository empDataRepository;

	@Autowired
	TodoRepository todoRepository;

	private static final Logger LOGGER = LogManager.getLogger(DailyDataController.class);

	@RequestMapping("modify-daily-data-request")
	public @ResponseBody ModelAndView viewRegularData(HttpServletRequest request)
	{
		ModelAndView model;
		model = new ModelAndView("Management/ModifyDailyDataRequest");
		return model;
	}

	@RequestMapping(value = {"ajax-get-modify-daily-data-request"}, method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String getAllModifiedRecordsByType()
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		ArrayList<String> ProductionList = new ArrayList<String>();
		ProductionList.add("Product");
		ProductionList.add("ByProduct");
		ProductionList.add("Raw Material");
		ProductionList.add("Fuel");
		ProductionList.add("hwp");
		ProductionList.add("hwpcf");
		ProductionList.add("nhwp");
		ProductionList.add("nhwpcf");

		try
		{
			for (int i = 0; i < ProductionList.size(); i++)
			{

				json = new JSONObject();
				List<ModifiedRecord> productDetailsArrayList = modifiedRecordServicesImpl.getAllModifiedRecordsByType(ProductionList.get(i), "New");
				JSONArray jsonPollDataArray1 = new JSONArray();

				if (!Validator.isEmpty(productDetailsArrayList))
				{
					Timestamp timestamp = productDetailsArrayList.get(0).getRequestDate();

					String requestDate = Utilities.getDateinStringFromTimestamp(timestamp);

					for (int j = 0; j < productDetailsArrayList.size(); j++)
					{
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("id", new Integer(productDetailsArrayList.get(j).getId())); // get
						hashMap.put("ProductName", new String(productDetailsArrayList.get(j).getRegId().getAllProductComparativeSheet().getAllProducts().getAllProductName().getProductName())); // get
						hashMap.put("CQuantity", new Float(productDetailsArrayList.get(j).getOldQuantity())); // get
						hashMap.put("NQuantity", new Float(productDetailsArrayList.get(j).getNewQuantity())); // get
						hashMap.put("regId", new Integer(productDetailsArrayList.get(j).getRegId().getRegularDataId()));
						hashMap.put("reason", new String(productDetailsArrayList.get(j).getReason())); // get
						hashMap.put("requestDate", new String(requestDate));
						hashMap.put("userName", new String(productDetailsArrayList.get(j).getRequestedById().getUserName()));
						hashMap.put("action", new String(productDetailsArrayList.get(j).getAction()));

						jsonPollDataArray1.put(hashMap);
					}
					json.put(ProductionList.get(i), jsonPollDataArray1);
					FinalArray.put(json);
				}
			}

		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return FinalArray.toString();
	}

	@PostMapping("ajax-change-status-modify-record")
	public boolean getModifiedDailyDataRequest(@RequestBody JsonObject jsonObjModifiedRecord)
	{
		RegularData regularData = null;
		ModifiedRecord modifiedRecord = null;
		Users users = new Users();
		int regId = jsonObjModifiedRecord.getAsJsonObject().get("regId").getAsInt();
		int modifiedId = jsonObjModifiedRecord.getAsJsonObject().get("modifiedId").getAsInt();
		float nQuantity = jsonObjModifiedRecord.getAsJsonObject().get("nQuantity").getAsFloat();
		String status = jsonObjModifiedRecord.getAsJsonObject().get("status").getAsString();
		Users loggedInUser = authenticationUtil.getLoggedInUser();
		int uId = (int) loggedInUser.getUsersId();

		Date today = new Date();
		Timestamp timestamp = new Timestamp(today.getTime());

		List<Todo> allTodos = new ArrayList<>();
		List<EmpData> managmentEmpList = new ArrayList<>();
		managmentEmpList = empDataRepository.findByContPerDesig("Environmental Officer");
		if (!Validator.isEmpty(managmentEmpList))
		{
			for (int i = 0; i < managmentEmpList.size(); i++)
			{
				String createDate = Utilities.getTodaysDate();
				EmpData empData = new EmpData();
				EmpData getLoggedInEmp = authenticationUtil.getLoggedInEmp();
				String empName = getLoggedInEmp.getEmployeeName();

				empData.setEmpDataId(managmentEmpList.get(i).getEmpDataId());
				Todo todo = new Todo();
				todo.setTodo(" You are Modify Request is " + status + " by " + empName);
				todo.setCreateDate(createDate);
				todo.setAction("new");
				todo.setEmpData(empData);
				allTodos.add(todo);

			}
		}

		todoRepository.save(allTodos);

		regularData = regularDataRepository.findByRegularDataId(regId);
		modifiedRecord = modifiedRecordRepository.findById(modifiedId);
		users = usersRepository.findByusersId(uId);

		if (status.equals("Approved"))
		{
			modifiedRecord.setAction("Approved");
			regularData.setQuantity(nQuantity);

		}
		else
		{
			modifiedRecord.setAction("Rejected");
		}
		modifiedRecord.setManagementId(users);
		modifiedRecord.setApprovalDate(timestamp);

		modifiedRecordRepository.save(modifiedRecord);
		regularDataRepository.save(regularData);

		return false;
	}

}
