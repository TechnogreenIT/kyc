package com.tes.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;

@RestController
@RequestMapping("/rest/water-filter")
public class FiltersRestController
{
	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	RegMultipleFilterDataServices regularFiltersDataServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	RegFiltersUseDataServices regularFiltersUseDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	private static final Logger LOGGER = LogManager.getLogger(FiltersRestController.class);

	// Effected By Water Inventory ........by vishal
	/*
	 * @GetMapping("/{companyId}")
	 * private ArrayList<Object> getFiltersData(@PathVariable("companyId") int companyId, HttpServletRequest request) {
	 * ArrayList<Object> jsonfiltersFinalList = new ArrayList<>();
	 * List<Filters> filtersDistinctData = new ArrayList<>();
	 * List<Filters> filtersDataByfilterName = new ArrayList<>();
	 * List<RegFiltersUseData> regularFiltersUseDataByFilterNameUse = new ArrayList<>();
	 * List<WaterInventory> waterInventoryData = new ArrayList<>();
	 * List<Filters> filtersDistinctDataList = new ArrayList<>();
	 * List<Filters> filtersList = new ArrayList<>();
	 * List<String> filtersUseList = new ArrayList<>();
	 * HashMap<String, Object> jsonFinalMapList = new HashMap<String, Object>();
	 * ArrayList<Object> filtersDataList = new ArrayList<>();
	 * int waterInventoryId = 0;
	 * try {
	 * waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId, Utilities.getTodaysDate());
	 * if (!Validator.isEmpty(waterInventoryData)) {
	 * for (int i = 0; i < waterInventoryData.size(); i++) {
	 * WaterInventory wiDetail = new WaterInventory();
	 * wiDetail = waterInventoryData.get(i);
	 * waterInventoryId = wiDetail.getWaterInventoryId();
	 * if (wiDetail.getFilterationPlant().equalsIgnoreCase("Yes")) {
	 * filtersDataByfilterName = filtersServices.getDistinctFilterName(waterInventoryId);
	 * if (!Validator.isEmpty(filtersDataByfilterName)) {
	 * for (int j = 0; j < filtersDataByfilterName.size(); j++) {
	 * Filters filters = new Filters();
	 * filters = filtersDataByfilterName.get(j);
	 * //filterName = filters.getFilterName();
	 * // get filters distinct data
	 * filtersDistinctData = filtersServices.findFiltersData(filters.getFilterName(), waterInventoryId);
	 * if (!filtersDistinctDataList.containsAll(filtersDistinctData)) {
	 * filtersDistinctDataList.addAll(filtersDistinctData);
	 * }
	 * wiDetail.setFiltersData(filtersDistinctDataList);
	 * }
	 * }
	 * if (!Validator.isEmpty(wiDetail.getFiltersData())) {
	 * for (int j = 0; j < wiDetail.getFiltersData().size(); j++) {
	 * Filters filters = new Filters();
	 * List<RegFiltersUseData> regularFiltersUseDataList = new ArrayList<>();
	 * filters = wiDetail.getFiltersData().get(j);
	 * waterInventoryId = filters.getWaterInventory().getWaterInventoryId();
	 * // get filters all data
	 * filters.setFiltersAllData(filtersServices.filtersAllData(waterInventoryId, filters.getFilterName()));
	 * filters.setRegularFiltersData(regularFiltersDataServices.regularFiltersDataByfilterName(filters.getFilterName(), waterInventoryId, new PageRequest(0, 1)));
	 * filtersUseList = regularFiltersUseDataServices.findFilterUse(filters.getFilterName(),waterInventoryId);
	 * for (int k = 0; k < filtersUseList.size(); k++) {
	 * regularFiltersUseDataByFilterNameUse = regularFiltersUseDataServices.regularFiltersUseDataByfilterName(filters.getFilterName(), filtersUseList.get(k), waterInventoryId,new PageRequest(0, 1));
	 * if (!regularFiltersUseDataList.containsAll(regularFiltersUseDataByFilterNameUse)) {
	 * regularFiltersUseDataList.addAll(regularFiltersUseDataByFilterNameUse);
	 * }
	 * }
	 * filters.setRegularFiltersUseDataByfilterName(regularFiltersUseDataList);
	 * filtersList.add(filters);
	 * }
	 * wiDetail.setFiltersData(filtersList);
	 * }
	 * if (!Validator.isEmpty(wiDetail.getFiltersData())) {
	 * for (Filters filters : wiDetail.getFiltersData()) {
	 * ArrayList<Object> filtersUseDataListObjectList = new ArrayList<>();
	 * HashMap<String, Object> filtersDataMapList = new HashMap<String, Object>();
	 * if (!Validator.isEmpty(filters.getRegularFiltersData())) {
	 * if ((filters.getRegularFiltersData().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filters.getWaterMeter().equalsIgnoreCase(Constant.YES))) {
	 * filtersDataMapList.put("wiId", filters.getWaterInventory().getWaterInventoryId());
	 * filtersDataMapList.put("sourceName", filters.getSourceName());
	 * filtersDataMapList.put("filterName", filters.getFilterName());
	 * filtersDataMapList.put("RegularFiltersUseDataList", filtersUseDataListObjectList);
	 * filtersDataMapList.put("waterMeter", filters.getWaterMeter());
	 * filtersDataMapList.put("startReading",filters.getRegularFiltersData().get(0).getStartReading());
	 * filtersDataMapList.put("endReading",filters.getRegularFiltersData().get(0).getEndReading());
	 * filtersDataMapList.put("actualReading",filters.getRegularFiltersData().get(0).getActualReading());
	 * } else if (!(filters.getRegularFiltersData().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filters.getWaterMeter().equalsIgnoreCase(Constant.YES))) {
	 * filtersDataMapList.put("wiId", filters.getWaterInventory().getWaterInventoryId());
	 * filtersDataMapList.put("sourceName", filters.getSourceName());
	 * filtersDataMapList.put("filterName", filters.getFilterName());
	 * filtersDataMapList.put("RegularFiltersUseDataList", filtersUseDataListObjectList);
	 * filtersDataMapList.put("waterMeter", filters.getWaterMeter());
	 * filtersDataMapList.put("startReading",filters.getRegularFiltersData().get(0).getEndReading());
	 * filtersDataMapList.put("endReading", "");
	 * filtersDataMapList.put("actualReading", "");
	 * } else if ((filters.getRegularFiltersData().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filters.getWaterMeter().equalsIgnoreCase(Constant.NO))) {
	 * filtersDataMapList.put("wiId", filters.getWaterInventory().getWaterInventoryId());
	 * filtersDataMapList.put("sourceName", filters.getSourceName());
	 * filtersDataMapList.put("filterName", filters.getFilterName());
	 * filtersDataMapList.put("RegularFiltersUseDataList", filtersUseDataListObjectList);
	 * filtersDataMapList.put("waterMeter", filters.getWaterMeter());
	 * filtersDataMapList.put("startReading", "");
	 * filtersDataMapList.put("endReading", "");
	 * filtersDataMapList.put("actualReading",filters.getRegularFiltersData().get(0).getActualReading());
	 * } else if (!(filters.getRegularFiltersData().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filters.getWaterMeter().equalsIgnoreCase(Constant.NO))) {
	 * filtersDataMapList.put("wiId", filters.getWaterInventory().getWaterInventoryId());
	 * filtersDataMapList.put("sourceName", filters.getSourceName());
	 * filtersDataMapList.put("filterName", filters.getFilterName());
	 * filtersDataMapList.put("RegularFiltersUseDataList", filtersUseDataListObjectList);
	 * filtersDataMapList.put("waterMeter", filters.getWaterMeter());
	 * filtersDataMapList.put("startReading", "");
	 * filtersDataMapList.put("endReading", "");
	 * filtersDataMapList.put("actualReading", "");
	 * }
	 * } else {
	 * filtersDataMapList.put("wiId", filters.getWaterInventory().getWaterInventoryId());
	 * filtersDataMapList.put("sourceName", filters.getSourceName());
	 * filtersDataMapList.put("filterName", filters.getFilterName());
	 * filtersDataMapList.put("waterMeter", filters.getWaterMeter());
	 * filtersDataMapList.put("startReading", "");
	 * filtersDataMapList.put("endReading", "");
	 * filtersDataMapList.put("actualReading","");
	 * }
	 * if (!Validator.isEmpty(filters.getFiltersAllData())) {
	 * for (Filters filtersFinalAllData : filters.getFiltersAllData()) {
	 * HashMap<String, Object> filtersUseMapList = new HashMap<String, Object>();
	 * if (!Validator.isEmpty(filters.getRegularFiltersUseDataByfilterName())) {
	 * if ((filters.getRegularFiltersUseDataByfilterName().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filtersFinalAllData.getFilterMeter().equalsIgnoreCase(Constant.YES))) {
	 * filtersUseMapList.put("filterName", filtersFinalAllData.getFilterName());
	 * filtersUseMapList.put("filterType", filtersFinalAllData.getFilterUse());
	 * filtersUseMapList.put("filterMeter", filtersFinalAllData.getFilterMeter());
	 * filtersUseMapList.put("filterstartReading",filters.getRegularFiltersUseDataByfilterName().get(0).getStartReading());
	 * filtersUseMapList.put("filterendReading", filters.getRegularFiltersUseDataByfilterName().get(0).getEndReading());
	 * filtersUseMapList.put("filteractualReading",filters.getRegularFiltersUseDataByfilterName().get(0).getActualReading());
	 * } else if (!(filters.getRegularFiltersUseDataByfilterName().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filtersFinalAllData.getFilterMeter().equalsIgnoreCase(Constant.YES))) {
	 * filtersUseMapList.put("filterName", filtersFinalAllData.getFilterName());
	 * filtersUseMapList.put("filterType", filtersFinalAllData.getFilterUse());
	 * filtersUseMapList.put("filterMeter", filtersFinalAllData.getFilterMeter());
	 * filtersUseMapList.put("filterstartReading", filters.getRegularFiltersUseDataByfilterName().get(0).getEndReading());
	 * filtersUseMapList.put("filterendReading", "");
	 * filtersUseMapList.put("filteractualReading", "");
	 * } else if ((filters.getRegularFiltersUseDataByfilterName().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filtersFinalAllData.getFilterMeter().equalsIgnoreCase(Constant.NO))) {
	 * filtersUseMapList.put("filterName", filtersFinalAllData.getFilterName());
	 * filtersUseMapList.put("filterType", filtersFinalAllData.getFilterUse());
	 * filtersUseMapList.put("filterMeter", filtersFinalAllData.getFilterMeter());
	 * filtersUseMapList.put("filterstartReading", "");
	 * filtersUseMapList.put("filterendReading", "");
	 * filtersUseMapList.put("filteractualReading",filters.getRegularFiltersUseDataByfilterName().get(0).getActualReading());
	 * } else if (!(filters.getRegularFiltersUseDataByfilterName().get(0).getRfDate().equals(Utilities.getTodaysDate()))&& (filtersFinalAllData.getFilterMeter().equalsIgnoreCase(Constant.NO))) {
	 * filtersUseMapList.put("filterName", filtersFinalAllData.getFilterName());
	 * filtersUseMapList.put("filterType", filtersFinalAllData.getFilterUse());
	 * filtersUseMapList.put("filterMeter", filtersFinalAllData.getFilterMeter());
	 * filtersUseMapList.put("filterstartReading", "");
	 * filtersUseMapList.put("filterendReading", "");
	 * filtersUseMapList.put("filteractualReading", "");
	 * }
	 * } else {
	 * filtersUseMapList.put("filterName", filtersFinalAllData.getFilterName());
	 * filtersUseMapList.put("filterType", filtersFinalAllData.getFilterUse());
	 * filtersUseMapList.put("filterMeter", filtersFinalAllData.getFilterMeter());
	 * filtersUseMapList.put("filterstartReading", "");
	 * filtersUseMapList.put("filterendReading", "");
	 * filtersUseMapList.put("filteractualReading", "");
	 * }
	 * filtersUseDataListObjectList.add(filtersUseMapList);
	 * } // filters all data
	 * }
	 * filtersDataList.add(filtersDataMapList);
	 * } // for filtersData
	 * jsonFinalMapList.put("filtersData", filtersDataList);
	 * jsonFinalMapList.put("Input", Constant.YES);
	 * jsonFinalMapList.put("Result", "True");
	 * } else {
	 * jsonFinalMapList.put("Input", Constant.NO);
	 * jsonFinalMapList.put("Result", "False");
	 * }
	 * jsonfiltersFinalList.add(jsonFinalMapList);
	 * }
	 * }
	 * }
	 * } catch (Exception e) {
	 * LOGGER.error(e);
	 * }
	 * return jsonfiltersFinalList;
	 * }
	 */

	// Effected By Water Inventory ........by vishal
	/*
	 * @ResponseStatus(HttpStatus.CREATED)
	 * @PostMapping
	 * private void setRegularFiltersDataAndRegularFiltersUseData(@RequestBody JsonArray filterDataList) {
	 * try {
	 * Users userId = new Users();
	 * for (JsonElement filterData : filterDataList) {
	 * JsonObject jsonObject = filterData.getAsJsonObject();
	 * JsonArray regularFiltersUseDataList = jsonObject.getAsJsonArray("regularFiltersUseData");
	 * for (JsonElement regularFiltersUseData : regularFiltersUseDataList) {
	 * RegFiltersUseData objRegularFiltersUseData = new RegFiltersUseData();
	 * WaterInventory waterInventoryId = new WaterInventory();
	 * waterInventoryId.setWaterInventoryId(regularFiltersUseData.getAsJsonObject().get("waterInventoryId").getAsInt());
	 * userId.setUsersId(regularFiltersUseData.getAsJsonObject().get("userId").getAsInt());
	 * objRegularFiltersUseData.setWaterInventory(waterInventoryId);
	 * objRegularFiltersUseData.setFilterName(regularFiltersUseData.getAsJsonObject().get("filterName").getAsString());
	 * objRegularFiltersUseData.setFilterType(regularFiltersUseData.getAsJsonObject().get("filterType").getAsString());
	 * objRegularFiltersUseData.setStartReading(regularFiltersUseData.getAsJsonObject().get("startReading").getAsFloat());
	 * objRegularFiltersUseData.setEndReading(regularFiltersUseData.getAsJsonObject().get("endReading").getAsFloat());
	 * objRegularFiltersUseData.setActualReading(regularFiltersUseData.getAsJsonObject().get("actualReading").getAsFloat());
	 * objRegularFiltersUseData.setRfDate(Utilities.getTodaysDate());
	 * objRegularFiltersUseData.setUsers(userId);
	 * regularFiltersUseDataServices.save(objRegularFiltersUseData);
	 * }
	 * JsonArray regularFiltersDataList = jsonObject.getAsJsonArray("regularFiltersData");
	 * for (JsonElement regularFiltersData : regularFiltersDataList) {
	 * RegMultipleFilterData objRegularFiltersData = new RegMultipleFilterData();
	 * WaterInventory waterInventoryId = new WaterInventory();
	 * waterInventoryId.setWaterInventoryId(regularFiltersData.getAsJsonObject().get("waterInventoryId").getAsInt());
	 * userId.setUsersId(regularFiltersData.getAsJsonObject().get("userId").getAsInt());
	 * objRegularFiltersData.setWaterInventory(waterInventoryId);
	 * objRegularFiltersData.setFilterName(regularFiltersData.getAsJsonObject().get("filterName").getAsString());
	 * objRegularFiltersData.setStartReading(regularFiltersData.getAsJsonObject().get("startReading").getAsFloat());
	 * objRegularFiltersData.setEndReading(regularFiltersData.getAsJsonObject().get("endReading").getAsFloat());
	 * objRegularFiltersData.setActualReading(regularFiltersData.getAsJsonObject().get("actualReading").getAsFloat());
	 * objRegularFiltersData.setRfDate(Utilities.getTodaysDate());
	 * objRegularFiltersData.setUsers(userId);
	 * regularFiltersDataServices.save(objRegularFiltersData);
	 * }
	 * }
	 * } catch (Exception e) {
	 * LOGGER.error(e);
	 * }
	 * }
	 */
}
