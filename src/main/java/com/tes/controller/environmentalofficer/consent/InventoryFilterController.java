package com.tes.controller.environmentalofficer.consent;

import java.util.HashMap;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tes.model.FilterUse;
import com.tes.model.Filters;
import com.tes.model.MultipleFilter;
import com.tes.model.Prefilter;
import com.tes.services.FilterNameListServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.MultipleFilterServices;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;

@Controller
@RequestMapping(value = {"/env"})
public class InventoryFilterController
{

	@Autowired
	PrefilterServices prefilterServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	MultipleFilterServices multipleFilterServices;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	FilterNameListServices filterNameListServices;

	@PostMapping(value = "ajax-save-inventory-filter-use")
	public @ResponseBody String saveFilterData(@RequestBody JsonObject filterUseInfo)
	{

		Prefilter objPrefilter = prefilterServices.findOne(filterUseInfo.getAsJsonObject().get("preFilterId").getAsInt());

		JsonArray filterUseJsonArray = filterUseInfo.getAsJsonArray("allFilterData");
		JSONArray jsonArray = new JSONArray();

		for (JsonElement filterUseJsonElement : filterUseJsonArray)
		{
			Filters objFilter = new Filters();
			MultipleFilter objMultipleFilter = new MultipleFilter();

			Filters objFilterByType = filtersServices.getFilterByType(filterUseJsonElement.getAsJsonObject().get("filterType").getAsString());
			if (objFilterByType == null)
			{
				objFilter.setPrefilter(objPrefilter);
				objFilter.setFilterType(filterUseJsonElement.getAsJsonObject().get("filterType").getAsString());
				filtersServices.save(objFilter);
				objMultipleFilter.setFilters(objFilter);
			}
			else
			{
				objMultipleFilter.setFilters(objFilterByType);
			}

			objMultipleFilter.setFilterLabel(filterUseJsonElement.getAsJsonObject().get("filterLable").getAsString());
			objMultipleFilter.setWaterLoss(filterUseJsonElement.getAsJsonObject().get("filterWaterLoss").getAsFloat());
			objMultipleFilter.setMeter(filterUseJsonElement.getAsJsonObject().get("filterWaterMeter").getAsBoolean());
			// System.out.println(filterUseJsonElement.getAsJsonObject().get("filterWaterMeter").getAsBoolean());
			multipleFilterServices.save(objMultipleFilter);

			JsonArray filterUseData = filterUseJsonElement.getAsJsonObject().get("filterUseData").getAsJsonArray();
			for (JsonElement filterUseDataElement : filterUseData)
			{
				FilterUse objFilterUse = new FilterUse();

				String filterType = filterUseDataElement.getAsJsonObject().get("filterUseType").getAsString();

				objFilterUse.setMultipleFilter(objMultipleFilter);
				// objFilterUse.setPrefilter(objPrefilter);
				objFilterUse.setFilterUseType(filterType);
				objFilterUse.setFilterUseLabel(filterUseDataElement.getAsJsonObject().get("filterUselabel").getAsString());
				objFilterUse.setWaterLoss(filterUseDataElement.getAsJsonObject().get("filterUsewaterLoss").getAsFloat());
				objFilterUse.setMeter(filterUseDataElement.getAsJsonObject().get("filterUsewaterUseMeter").getAsBoolean());

				boolean isFilter = filterNameListServices.getFilterIdByName(filterType);
				objFilterUse.setFilter(isFilter);
				filterUseServices.save(objFilterUse);

				if (isFilter)
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("Result", true);
					hashMap.put("filterType", filterType);
					hashMap.put("filterUselabel", filterUseDataElement.getAsJsonObject().get("filterUselabel").getAsString());
					hashMap.put("filterUsewaterLoss", filterUseDataElement.getAsJsonObject().get("filterUsewaterLoss").getAsString());
					hashMap.put("filterUsewaterUseMeter", filterUseDataElement.getAsJsonObject().get("filterUsewaterUseMeter").getAsString());
					jsonArray.put(hashMap);
				}
				else
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("Result", false);
					jsonArray.put(hashMap);
				}

			}

		}

		return jsonArray.toString();

	}

}
