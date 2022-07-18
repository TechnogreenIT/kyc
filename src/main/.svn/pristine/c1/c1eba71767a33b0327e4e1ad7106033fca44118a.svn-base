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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.DirectUseOfWater;
import com.tes.model.EmpData;
import com.tes.model.FilterUse;
import com.tes.model.Filters;
import com.tes.model.MultipleFilter;
import com.tes.model.Prefilter;
import com.tes.model.WaterInventory;
import com.tes.model.WaterSource;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.MultipleFilterServices;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping(value = {"/env"})
public class ComparativeWaterInventoryController
{

	@Autowired
	PrefilterServices prefilterServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWaterServices;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	MultipleFilterServices multipleFilterServices;

	private static final Logger LOGGER = LogManager.getLogger(ComparativeWaterInventoryController.class);

	/**
	 * @return
	 */
	@RequestMapping(value = "add-water-inventory")
	public ModelAndView addWaterInventory()
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			modelAndView.addObject("preFilterList", prefilterServices.findAll());
			modelAndView.setViewName("EnvrOfficer/AddWaterInventory");
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "view-water-inventory")
	public ModelAndView saveFilterData()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EnvrOfficer/ViewWaterInventory");
		return modelAndView;
	}

	@PostMapping(value = "view-waterSource")
	public @ResponseBody String viewWaterInventory(HttpServletRequest request)
	{
		JSONArray jsonArray = new JSONArray();
		List<WaterSource> waterSourceList = new ArrayList<>();
		List<Prefilter> priFilterList = new ArrayList<>();
		List<DirectUseOfWater> directUseOfWaterList = new ArrayList<>();
		List<FilterUse> filterUseList = new ArrayList<>();

		List<WaterInventory> waterInventoryData = new ArrayList<>();
		int companyId = 0;
		EmpData empDataSession = null;
		if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
		{
			empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
		}
		companyId = empDataSession.getCompanyProfile().getCompanyProfileId();
		waterInventoryData = waterInventoryServices.getwaterInventoryById(companyId, Utilities.getTodaysDate(), new PageRequest(0, 1));

		if (!Validator.isEmpty(waterInventoryData))
		{
			for (int i = 0; i < waterInventoryData.size(); i++)
			{
				WaterInventory wiDetail = new WaterInventory();
				wiDetail = waterInventoryData.get(i);

				int wiId = wiDetail.getWaterInventoryId();
				waterSourceList = waterSourceServices.getWaterSourceData(wiId);
			}
		}

		JSONArray waterSource = new JSONArray();
		JSONArray directUseList = new JSONArray();
		JSONObject json = new JSONObject();

		for (WaterSource waterSourceListElement : waterSourceList)
		{
			HashMap<String, Object> wslist = new HashMap<String, Object>();

			wslist.put("sourceName", waterSourceListElement.getSourceName());
			wslist.put("isSourceMeter", waterSourceListElement.isSourceMeter());

			int priFilterId = 0;

			int wsId = waterSourceListElement.getWaterSourceId();
			priFilterList = prefilterServices.getAllActivePrefilterData(wsId);

			if (priFilterList != null)
			{
				priFilterId = priFilterList.get(0).getPrefilterId();
				wslist.put("acf", priFilterList.get(0).isAcf());
				wslist.put("psf", priFilterList.get(0).isPsf());
				wslist.put("dmf", priFilterList.get(0).isDmf());
				wslist.put("isMeter", priFilterList.get(0).isMeter());

			}

			directUseOfWaterList = directUseOfWaterServices.directUseOfWaterList(wsId);

			for (DirectUseOfWater directUseOfWaterElement : directUseOfWaterList)
			{
				HashMap<String, Object> dulist = new HashMap<String, Object>();
				dulist.put("typeOfUse", directUseOfWaterElement.getTypeOfUse());
				dulist.put("whereToUse", directUseOfWaterElement.getWhereToUse());
				dulist.put("isMeter", directUseOfWaterElement.isMeter());
				dulist.put("waterLoss", directUseOfWaterElement.getWaterLoss());
				directUseList.put(dulist);
			}

			filterUseList = filterUseServices.getFilterUseByPreFilterId(priFilterId);
			for (FilterUse filterUseElement : filterUseList)
			{
				HashMap<String, Object> dulist = new HashMap<String, Object>();
				dulist.put("typeOfUse", filterUseElement.getFilterUseType());
				dulist.put("whereToUse", filterUseElement.getFilterUseLabel());
				dulist.put("isMeter", filterUseElement.isMeter());
				dulist.put("waterLoss", filterUseElement.getWaterLoss());
				directUseList.put(dulist);
			}

			waterSource.put(wslist);
		}
		json.put("waterSource", waterSource);
		json.put("directUse", directUseList);
		jsonArray.put(json);

		return jsonArray.toString();

	}

	@PostMapping(value = "view-filterData")
	public @ResponseBody String viewDirectUse()
	{
		JSONArray jsonArray = new JSONArray();
		List<Filters> filterList = new ArrayList<>();
		List<MultipleFilter> multipleFilterList = new ArrayList<>();
		List<FilterUse> filterUseList = new ArrayList<>();

		JSONArray filterArray = new JSONArray();

		filterList = filtersServices.findAll();

		for (Filters filterElement : filterList)
		{
			JSONObject json = new JSONObject();
			json.put("filterType", filterElement.getFilterType());

			int filterId = filterElement.getFiltersId();

			JSONArray multipleFilterArray = new JSONArray();
			multipleFilterList = multipleFilterServices.getAllFiltersIdAndLabelsByFilterId(filterId);
			for (MultipleFilter MultipleFilterElement : multipleFilterList)
			{
				HashMap<String, Object> mflist = new HashMap<String, Object>();
				int multipleId = MultipleFilterElement.getMultipleFilterId();
				mflist.put("filterLabel", MultipleFilterElement.getFilterLabel());
				mflist.put("filterWaterLoss", MultipleFilterElement.getWaterLoss());
				mflist.put("filterIsMeter", MultipleFilterElement.isMeter());

				JSONArray filterUseArray = new JSONArray();
				filterUseList = filterUseServices.getFilterUseByMultiFilterId(multipleId);
				for (FilterUse filterUseElement : filterUseList)
				{
					HashMap<String, Object> fulist = new HashMap<String, Object>();
					fulist.put("filterUseLabel", filterUseElement.getFilterUseLabel());
					fulist.put("waterLoss", filterUseElement.getWaterLoss());
					fulist.put("isMeter", filterUseElement.isMeter());
					filterUseArray.put(fulist);
				}

				mflist.put("filterUse", filterUseArray);
				multipleFilterArray.put(mflist);

			}

			json.put("multipleFilter", multipleFilterArray);
			filterArray.put(json);

		}

		return filterArray.toString();

	}
}
