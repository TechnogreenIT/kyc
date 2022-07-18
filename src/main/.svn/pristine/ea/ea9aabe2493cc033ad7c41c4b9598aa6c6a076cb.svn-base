package com.tes.controller.environmentalofficer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.tes.model.ModifiedRecord;
import com.tes.services.impl.environmentalofficer.ModifiedRecordServicesImpl;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping(value = {"/env", "/management"})
public class ViewModifiedDataController
{
	@Autowired
	ModifiedRecordServicesImpl modifiedRecordServicesImpl;

	private static final Logger LOGGER = LogManager.getLogger(ViewModifiedDataController.class);

	@RequestMapping("view-modified-data")
	public @ResponseBody ModelAndView viewModifiedData(HttpServletRequest request)
	{
		ModelAndView model;
		model = new ModelAndView("EnvrOfficer/NewViewModifiedData");
		return model;
	}

	@RequestMapping(value = {"ajax-view-modified-data"}, method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String getAllModifiedRecordsByType(@RequestParam(value = "action", required = false) String action)
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

		// ModifiedRecord modifiedRecord = new ModifiedRecord();

		String requestDate = null;
		String approvalDate = null;

		try
		{
			for (int i = 0; i < ProductionList.size(); i++)
			{

				json = new JSONObject();
				List<ModifiedRecord> productDetailsArrayList = modifiedRecordServicesImpl.getAllModifiedRecordsByType(ProductionList.get(i), "Approved");
				JSONArray jsonPollDataArray1 = new JSONArray();

				if (!Validator.isEmpty(productDetailsArrayList))
				{
					Timestamp timestamp = productDetailsArrayList.get(0).getRequestDate();
					Timestamp date = productDetailsArrayList.get(0).getApprovalDate();

					requestDate = Utilities.getDateinStringFromTimestamp(timestamp);
					approvalDate = Utilities.getDateinStringFromTimestamp(date);

					for (int j = 0; j < productDetailsArrayList.size(); j++)
					{
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("id", new Integer(productDetailsArrayList.get(j).getId())); // get
						hashMap.put("ProductName", new String(productDetailsArrayList.get(j).getRegId().getAllProductComparativeSheet().getAllProducts().getAllProductName().getProductName())); // get
						hashMap.put("CQuantity", new Float(productDetailsArrayList.get(j).getOldQuantity())); // get
						hashMap.put("NQuantity", new Float(productDetailsArrayList.get(j).getNewQuantity())); // get
						// hashMap.put("regId", new Integer(productDetailsArrayList.get(j).getRegId().getRegularDataId()));
						hashMap.put("reason", new String(productDetailsArrayList.get(j).getReason())); // get
						hashMap.put("requestDate", new String(requestDate));
						hashMap.put("userName", new String(productDetailsArrayList.get(j).getRequestedById().getUserName()));
						hashMap.put("action", new String(productDetailsArrayList.get(j).getAction()));
						hashMap.put("approvalDate", new String(approvalDate));
						hashMap.put("ManName", new String(productDetailsArrayList.get(j).getManagementId().getUserName()));

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

}
