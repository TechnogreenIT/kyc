package com.tes.controller.environmentalofficer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.JsonObject;
import com.tes.model.Consent;
import com.tes.model.EmpData;
import com.tes.model.WastewaterRecycle;
import com.tes.model.WastewaterTreatment;
import com.tes.model.WaterInventory;
import com.tes.model.WaterSourceNames;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.waterinventory.WasteWaterRecycleSevices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping(value = {"/env"})
public class WasteWaterTreatmentController
{

	@Autowired
	ConsentServices consentServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	WasteWaterRecycleSevices wasteWaterRecycleSevices;

	private static final Logger LOGGER = LogManager.getLogger(WasteWaterTreatmentController.class);

	@RequestMapping(value = "add-wastewater-treatment")
	public ModelAndView addWaterInventory()
	{
		List<Consent> allConsents = new ArrayList<>();
		List<WaterSourceNames> allWaterSources = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		try
		{

			modelAndView.setViewName("EnvrOfficer/AddWasteWaterTreatment");

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "view-wastewater-treatment")
	public ModelAndView viewWasteWaterTreatment()
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			modelAndView.setViewName("EnvrOfficer/ViewWasteWaterTreatment");
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = {"ajax-get-etp-waste-water-data"}, method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String GetWasteWaterDataETP(@RequestBody JsonObject jsonObjTreatmentType, HttpServletRequest request)
	{
		String treatemetType = jsonObjTreatmentType.getAsJsonObject().get("treatmentType").getAsString();
		List<WastewaterRecycle> wasteWaterRecycleData = new ArrayList<>();
		List<WastewaterTreatment> wastewaterTreatmentData = new ArrayList<>();
		WastewaterTreatment wastewaterTreatment = new WastewaterTreatment();
		List<WaterInventory> waterInventoryData = new ArrayList<>();
		JSONArray FinalArray = new JSONArray();
		// JSONArray wastewaterTreatmentDataObject = new JSONArray();
		int companyId = 0;
		EmpData empDataSession = null;
		try
		{
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			}
			companyId = empDataSession.getCompanyProfile().getCompanyProfileId();
			waterInventoryData = waterInventoryServices.getwaterInventoryById(companyId, Utilities.getTodaysDate(), new PageRequest(0, 1));
			// int waterInve = waterInventoryServices.getWaterInventoryTest(companyId, Utilities.getTodaysDate(), new PageRequest(0, 1));
			if (!Validator.isEmpty(waterInventoryData))
			{
				for (int i = 0; i < waterInventoryData.size(); i++)
				{
					WaterInventory wiDetail = new WaterInventory();
					wiDetail = waterInventoryData.get(i);
					wastewaterTreatmentData = wastewaterTreatmentServices.getTreatmentType(wiDetail.getWaterInventoryId(), treatemetType);
					// wastewaterTreatmentData = wastewaterTreatmentServices.getWaterTreatmentDataByType(treatemetType);
					if (!Validator.isEmpty(wastewaterTreatmentData))
					{
						for (int j = 0; j < wastewaterTreatmentData.size(); j++)
						{
							JSONObject json = new JSONObject();
							JSONArray jsonPollDataArray1 = new JSONArray();
							// wastewaterTreatmentDataObject.put(wastewaterTreatmentData.get(j).getLabel());
							wastewaterTreatment = wastewaterTreatmentData.get(j);
							wasteWaterRecycleData = wasteWaterRecycleSevices.getWasteWaterRecycle(wastewaterTreatment.getWastewaterTreatmentId());
							String labelName = wastewaterTreatmentData.get(j).getLabel();
							if (!Validator.isEmpty(wasteWaterRecycleData))
							{
								for (int k = 0; k < wasteWaterRecycleData.size(); k++)
								{
									HashMap<String, Object> hashMap = new HashMap<String, Object>();
									hashMap.put("label", new String(wastewaterTreatmentData.get(j).getLabel()));
									hashMap.put("treatmentType", new String(wastewaterTreatmentData.get(j).getTreatmentType()));
									hashMap.put("useType", new String(wasteWaterRecycleData.get(k).getUseType()));
									hashMap.put("recycleTo", new String(wasteWaterRecycleData.get(k).getRecycledTo()));
									hashMap.put("quantity", new Float(wasteWaterRecycleData.get(k).getQuantity()));
									jsonPollDataArray1.put(hashMap);
								}
							}
							json.put(labelName, jsonPollDataArray1);
							FinalArray.put(json);
						}
					}
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
