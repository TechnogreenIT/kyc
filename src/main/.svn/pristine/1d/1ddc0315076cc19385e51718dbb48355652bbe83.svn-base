package com.tes.controller.environmentalofficer.consent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tes.model.DirectUseOfWater;
import com.tes.model.FilterUse;
import com.tes.model.Prefilter;
import com.tes.model.WaterSource;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;

@Controller
@RequestMapping(value = {"/env"})
public class InventoryDirectUseController
{

	@Autowired
	PrefilterServices prefilterServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWaterServices;

	@Autowired
	FilterUseServices filterUseService;

	@PostMapping(value = "ajax-save-inventory-direct-use")
	public String getFil(@RequestBody JsonObject filterInfo)
	{

		System.out.println(filterInfo);

		Prefilter objPrefilter = prefilterServices.findOne(filterInfo.getAsJsonObject().get("preFilterId").getAsInt());
		JsonArray directUseJsonArray = filterInfo.getAsJsonArray("arrDirectUseData");

		if (objPrefilter.isPrefilter() == false)
		{
			// Direct use without prefilter
			WaterSource objWaterSource = new WaterSource();
			objWaterSource.setWaterSourceId(objPrefilter.getWaterSource().getWaterSourceId());

			for (JsonElement dirUseJsonElement : directUseJsonArray)
			{
				DirectUseOfWater objDirectUseOfWater = new DirectUseOfWater();
				objDirectUseOfWater.setWaterSource(objWaterSource);
				objDirectUseOfWater.setTypeOfUse(dirUseJsonElement.getAsJsonObject().get("directUseType").getAsString());
				objDirectUseOfWater.setWhereToUse(dirUseJsonElement.getAsJsonObject().get("directUseLabel").getAsString());
				objDirectUseOfWater.setMeter(dirUseJsonElement.getAsJsonObject().get("directUseMeter").getAsBoolean());
				objDirectUseOfWater.setWaterLoss(dirUseJsonElement.getAsJsonObject().get("directUseLoss").getAsFloat());
				objDirectUseOfWater.setIndustrial(dirUseJsonElement.getAsJsonObject().get("directUseIsIndustrial").getAsBoolean());
				directUseOfWaterServices.save(objDirectUseOfWater);
			}
		}
		else
		{
			// Filter use with prefilter

			for (JsonElement dirUseJsonElement : directUseJsonArray)
			{
				FilterUse objFilterUse = new FilterUse();
				objFilterUse.setPrefilter(objPrefilter);
				objFilterUse.setFilterUseType(dirUseJsonElement.getAsJsonObject().get("directUseType").getAsString());
				objFilterUse.setFilterUseLabel(dirUseJsonElement.getAsJsonObject().get("directUseLabel").getAsString());
				objFilterUse.setMeter(dirUseJsonElement.getAsJsonObject().get("directUseMeter").getAsBoolean());
				objFilterUse.setWaterLoss(dirUseJsonElement.getAsJsonObject().get("directUseLoss").getAsFloat());
				filterUseService.save(objFilterUse);
			}

		}

		return "Vishal";
	}
}
