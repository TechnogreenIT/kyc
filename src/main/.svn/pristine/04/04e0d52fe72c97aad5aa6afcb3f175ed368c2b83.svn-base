package com.tes.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;

@RestController
@RequestMapping("/rest/water/use-of-source")
public class UseOfSourceRestController
{

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	private static final Logger LOGGER = LogManager.getLogger(UseOfSourceRestController.class);

	// Effected By Water Inventory ........by vishal
	/*
	 * @GetMapping("/{companyId}")
	 * private ArrayList<Object> getUseOfSourceData(@PathVariable("companyId") int companyId, HttpServletRequest request) {
	 * List<UseOfWater> useOfWaterData = new ArrayList<>();
	 * List<WaterInventory> waterInventoryData = new ArrayList<>();
	 * //List<WaterInventory> waterInventoryDetails = new ArrayList<>();
	 * ArrayList<Object> jsonuseOfSourceDataArrayList = new ArrayList<>();
	 * HashMap<String, Object> useOfSourceMapList = new HashMap<String, Object>();
	 * List<Industrial> industrialDataByUseOfWaterId = new ArrayList<>();
	 * ArrayList<Object> useOfSourceDataArrayList = new ArrayList<>();
	 * int waterInventoryId = 0;
	 * String industrialUseOfSource = "", domesticUseOfSource="", fireHydrantUseOfSource="", today = Utilities.getTodaysDate(), laundryUseOfsource="";
	 * try {
	 * waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId, Utilities.getTodaysDate());
	 * if (!Validator.isEmpty(waterInventoryData)) {
	 * for (int i = 0; i < waterInventoryData.size(); i++) {
	 * WaterInventory wiDetail = new WaterInventory();
	 * wiDetail = waterInventoryData.get(i);
	 * domesticUseOfSource=wiDetail.getDomesticUseOfSource();
	 * industrialUseOfSource = wiDetail.getIndustrialUseOfSource();
	 * laundryUseOfsource=wiDetail.getLaundryUseOfSource();
	 * fireHydrantUseOfSource=wiDetail.getFireHydrantUseOfSource();
	 * waterInventoryId = wiDetail.getWaterInventoryId();
	 * useOfWaterData = useOfWaterServices.useOfWaterDataByWaterInventoryId(waterInventoryId);
	 * wiDetail.setUseOfWaterData(useOfWaterData);
	 * if (!Validator.isEmpty(useOfWaterData)) {
	 * //DOMESTIC
	 * if (domesticUseOfSource.equalsIgnoreCase("checked")) {
	 * for(UseOfWater useOfWater:wiDetail.getUseOfWaterData() )
	 * {
	 * wiDetail.setRegularWaterUseDataByDomestic(regularWaterUseDataServices.regularWaterUseDataByDomestic(waterInventoryId, new PageRequest(0, 1)));
	 * if(!Validator.isEmpty(wiDetail.getRegularWaterUseDataByDomestic()))
	 * {
	 * //for(int k=0;k<wiDetail.getRegularWaterUseDataByDomestic().size();k++)
	 * for(DirectUseOfWater regularWaterUseData:wiDetail.getRegularWaterUseDataByDomestic()) {
	 * HashMap<String, Object> wiList = new HashMap<String, Object>();
	 * if (useOfWater.getDomestic().equalsIgnoreCase("checked")) {
	 * if ((regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getDomesticMeter().equalsIgnoreCase(Constant.YES))) {
	 * wiList.put("wiId", waterInventoryId);
	 * wiList.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList.put("waterMeter", useOfWater.getDomesticMeter());
	 * wiList.put("sourceType",regularWaterUseData.getSourceType());
	 * wiList.put("startReading",regularWaterUseData.getStartReading());
	 * wiList.put("endReading",regularWaterUseData.getEndReading());
	 * wiList.put("actualReading",regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * } else if(!(regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getDomesticMeter().equalsIgnoreCase(Constant.YES))) {
	 * wiList.put("wiId", waterInventoryId);
	 * wiList.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList.put("waterMeter", useOfWater.getDomesticMeter());
	 * wiList.put("sourceType",regularWaterUseData.getSourceType());
	 * wiList.put("startReading",regularWaterUseData.getEndReading());
	 * wiList.put("endReading", "");
	 * wiList.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }else if((regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getDomesticMeter().equalsIgnoreCase(Constant.NO)))
	 * {
	 * wiList.put("wiId", waterInventoryId);
	 * wiList.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList.put("waterMeter", useOfWater.getDomesticMeter());
	 * wiList.put("sourceType",regularWaterUseData.getSourceType());
	 * wiList.put("startReading", "");
	 * wiList.put("endReading", "");
	 * wiList.put("actualReading", regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * }else if(!(regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getDomesticMeter().equalsIgnoreCase(Constant.NO)))
	 * {
	 * wiList.put("wiId", waterInventoryId);
	 * wiList.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList.put("waterMeter", useOfWater.getDomesticMeter());
	 * wiList.put("sourceType",useOfWater.getDomestic());
	 * wiList.put("startReading", "");
	 * wiList.put("endReading", "");
	 * wiList.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }
	 * useOfSourceDataArrayList.add(wiList);
	 * }
	 * }
	 * useOfSourceMapList.put("Result", "True");
	 * }else
	 * {
	 * useOfSourceMapList.put("Result", "False");
	 * }
	 * }
	 * }
	 * //LAUNDRY
	 * if(laundryUseOfsource.equalsIgnoreCase("checked"))
	 * {
	 * for(UseOfWater useOfWater:wiDetail.getUseOfWaterData())
	 * {
	 * wiDetail.setRegularWaterUseDataByLaundry(regularWaterUseDataServices.regularWaterUseDataByLaundry(waterInventoryId, new PageRequest(0,1)));
	 * if(!Validator.isEmpty(wiDetail.getRegularWaterUseDataByLaundry()))
	 * {
	 * for(DirectUseOfWater regularWaterUseData:wiDetail.getRegularWaterUseDataByLaundry())
	 * {
	 * HashMap<String, Object> wiList1 = new HashMap<String, Object>();
	 * if((regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getLaundryMeter().equalsIgnoreCase(Constant.YES)))
	 * {
	 * wiList1.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList1.put("wiId", waterInventoryId);
	 * wiList1.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList1.put("waterMeter", useOfWater.getLaundryMeter());
	 * wiList1.put("sourceType", regularWaterUseData.getSourceType());
	 * wiList1.put("startReading", regularWaterUseData.getStartReading());
	 * wiList1.put("endReading", regularWaterUseData.getEndReading());
	 * wiList1.put("actualReading", regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * }else if(!(regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getLaundryMeter().equalsIgnoreCase(Constant.YES)))
	 * {
	 * wiList1.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList1.put("wiId", waterInventoryId);
	 * wiList1.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList1.put("waterMeter", useOfWater.getLaundryMeter());
	 * wiList1.put("sourceType", regularWaterUseData.getSourceType());
	 * wiList1.put("startReading", regularWaterUseData.getEndReading());
	 * wiList1.put("endReading", "");
	 * wiList1.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }else if(!(regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getLaundryMeter().equalsIgnoreCase(Constant.NO)))
	 * {
	 * wiList1.put("id", "");
	 * wiList1.put("wiId", waterInventoryId);
	 * wiList1.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList1.put("waterMeter", useOfWater.getLaundryMeter());
	 * wiList1.put("sourceType", useOfWater.getLaundry());
	 * wiList1.put("startReading", "");
	 * wiList1.put("endReading", "");
	 * wiList1.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }
	 * else if((regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getLaundryMeter().equalsIgnoreCase(Constant.NO)))
	 * {
	 * wiList1.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList1.put("wiId", waterInventoryId);
	 * wiList1.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList1.put("waterMeter", regularWaterUseData.getSourceType());
	 * wiList1.put("sourceType", regularWaterUseData.getSourceType());
	 * wiList1.put("startReading", "");
	 * wiList1.put("endReading", "");
	 * wiList1.put("actualReading", regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * }
	 * useOfSourceDataArrayList.add(wiList1);
	 * }
	 * useOfSourceMapList.put("Result", "True");
	 * }
	 * else
	 * {
	 * useOfSourceMapList.put("Result", "False");
	 * }
	 * }
	 * }
	 * //FIRE HYDRANT
	 * if(fireHydrantUseOfSource.equalsIgnoreCase("checked"))
	 * {
	 * for(UseOfWater useOfWater:wiDetail.getUseOfWaterData())
	 * {
	 * wiDetail.setRegularWaterUseDataByFireHydrant(regularWaterUseDataServices.getregularWaterUseDataByFireHydrant(waterInventoryId, new PageRequest(0,1)));
	 * if(!Validator.isEmpty(wiDetail.getRegularWaterUseDataByFireHydrant()))
	 * {
	 * for(DirectUseOfWater regularWaterUseData:wiDetail.getRegularWaterUseDataByFireHydrant())
	 * {
	 * HashMap<String, Object> wiList2 = new HashMap<String, Object>();
	 * if((regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getFireHydrantMeter().equalsIgnoreCase(Constant.YES)))
	 * {
	 * wiList2.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList2.put("wiId", waterInventoryId);
	 * wiList2.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList2.put("waterMeter", useOfWater.getFireHydrantMeter());
	 * wiList2.put("sourceType", regularWaterUseData.getSourceType());
	 * wiList2.put("startReading", regularWaterUseData.getStartReading());
	 * wiList2.put("endReading", regularWaterUseData.getEndReading());
	 * wiList2.put("actualReading", regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * }else if(!(regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getFireHydrantMeter().equalsIgnoreCase(Constant.YES)))
	 * {
	 * wiList2.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList2.put("wiId", waterInventoryId);
	 * wiList2.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList2.put("waterMeter", useOfWater.getFireHydrantMeter());
	 * wiList2.put("sourceType", regularWaterUseData.getSourceType());
	 * wiList2.put("startReading", regularWaterUseData.getEndReading());
	 * wiList2.put("endReading", "");
	 * wiList2.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }
	 * else if(!(regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getFireHydrantMeter().equalsIgnoreCase(Constant.NO)))
	 * {
	 * wiList2.put("id", "");
	 * wiList2.put("wiId", waterInventoryId);
	 * wiList2.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList2.put("waterMeter", useOfWater.getFireHydrantMeter());
	 * wiList2.put("sourceType", useOfWater.getFireHydrant());
	 * wiList2.put("startReading", "");
	 * wiList2.put("endReading", "");
	 * wiList2.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }
	 * else if((regularWaterUseData.getrWDate().equals(today))&&(useOfWater.getFireHydrantMeter().equalsIgnoreCase(Constant.NO)))
	 * {
	 * wiList2.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList2.put("wiId", waterInventoryId);
	 * wiList2.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList2.put("waterMeter", useOfWater.getFireHydrantMeter());
	 * wiList2.put("sourceType", regularWaterUseData.getSourceType());
	 * wiList2.put("startReading", "");
	 * wiList2.put("endReading", "");
	 * wiList2.put("actualReading", regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * }
	 * useOfSourceDataArrayList.add(wiList2);
	 * }
	 * useOfSourceMapList.put("Result", "True");
	 * }
	 * else
	 * {
	 * useOfSourceMapList.put("Result", "False");
	 * }
	 * }
	 * } //firehydrant checked
	 * //INDUSTRIAL
	 * if (industrialUseOfSource.equalsIgnoreCase("checked")) {
	 * for (int j = 0; j < wiDetail.getUseOfWaterData().size(); j++) {
	 * industrialDataByUseOfWaterId = industrialServices.industrialDataByUseOfWaterId(wiDetail.getUseOfWaterData().get(j).getUseOfWaterId());
	 * wiDetail.setIndustrialDataByUOWId(industrialDataByUseOfWaterId);
	 * if (!Validator.isEmpty(industrialDataByUseOfWaterId)) {
	 * for (int k = 0; k < wiDetail.getIndustrialDataByUOWId().size(); k++) {
	 * wiDetail.setRegularWaterUseDataByIndName(regularWaterUseDataServices.getRegularWaterUseDataByIndName(industrialDataByUseOfWaterId.get(k).getIndName(),waterInventoryId, new PageRequest(0, 1)));
	 * }
	 * }
	 * }
	 * }
	 * }
	 * if (!Validator.isEmpty(wiDetail.getUseOfWaterData())) {
	 * for (UseOfWater useOfWater:wiDetail.getUseOfWaterData()) {
	 * HashMap<String, Object> wiList3 = new HashMap<String, Object>();
	 * if(!Validator.isEmpty(wiDetail.getIndustrialDataByUOWId())) {
	 * for(Industrial industrial:wiDetail.getIndustrialDataByUOWId()) {
	 * if (!Validator.isEmpty(wiDetail.getRegularWaterUseDataByIndName())) {
	 * for(DirectUseOfWater regularWaterUseData:wiDetail.getRegularWaterUseDataByIndName()) {
	 * if (useOfWater.getIndustrial().equalsIgnoreCase("checked")) {
	 * if ((regularWaterUseData.getrWDate().equals(today))&&(industrial.getWaterMeter().equalsIgnoreCase(Constant.YES))) {
	 * wiList3.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList3.put("wiId", waterInventoryId);
	 * wiList3.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList3.put("sourceType",regularWaterUseData.getSourceType());
	 * wiList3.put("waterMeter", industrial.getWaterMeter());
	 * wiList3.put("startReading",regularWaterUseData.getStartReading());
	 * wiList3.put("endReading",regularWaterUseData.getEndReading());
	 * wiList3.put("actualReading",regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * } else if(!(regularWaterUseData.getrWDate().equals(today))&&(industrial.getWaterMeter().equalsIgnoreCase(Constant.YES))){
	 * wiList3.put("id", regularWaterUseData.getRegularWaterUseDataId());
	 * wiList3.put("wiId", waterInventoryId);
	 * wiList3.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList3.put("waterMeter", industrial.getWaterMeter());
	 * wiList3.put("sourceType",regularWaterUseData.getSourceType());
	 * wiList3.put("startReading",regularWaterUseData.getEndReading());
	 * wiList3.put("endReading", "");
	 * wiList3.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }else if(!(regularWaterUseData.getrWDate().equals(today))&&(industrial.getWaterMeter().equalsIgnoreCase(Constant.NO))){
	 * wiList3.put("id", "");
	 * wiList3.put("wiId", waterInventoryId);
	 * wiList3.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList3.put("waterMeter", industrial.getWaterMeter());
	 * wiList3.put("sourceType",industrial.getIndName());
	 * wiList3.put("startReading", "");
	 * wiList3.put("endReading", "");
	 * wiList3.put("actualReading", "");
	 * useOfSourceMapList.put("Input", Constant.YES);
	 * }
	 * else if((regularWaterUseData.getrWDate().equals(today))&&(industrial.getWaterMeter().equalsIgnoreCase(Constant.NO))){
	 * wiList3.put("id", "");
	 * wiList3.put("wiId", waterInventoryId);
	 * wiList3.put("useOfWaterId", useOfWater.getUseOfWaterId());
	 * wiList3.put("waterMeter", industrial.getWaterMeter());
	 * wiList3.put("sourceType",industrial.getIndName());
	 * wiList3.put("startReading", "");
	 * wiList3.put("endReading", "");
	 * wiList3.put("actualReading", regularWaterUseData.getActualReading());
	 * useOfSourceMapList.put("Input", Constant.NO);
	 * }
	 * useOfSourceDataArrayList.add(wiList3);
	 * }
	 * }
	 * useOfSourceMapList.put("Result", "True");
	 * }
	 * }
	 * }else
	 * {
	 * useOfSourceMapList.put("Result", "False");
	 * }
	 * }
	 * }
	 * useOfSourceMapList.put("RegularWaterUseDataList", useOfSourceDataArrayList);
	 * jsonuseOfSourceDataArrayList.add(useOfSourceMapList);
	 * //waterInventoryDetails.add(wiDetail);
	 * }
	 * }
	 * } catch (Exception e) {
	 * LOGGER.error(e);
	 * }
	 * return jsonuseOfSourceDataArrayList;
	 * }/*
	 * //Effected By Water Inventory ........by vishal
	 * /*@ResponseStatus(HttpStatus.CREATED)
	 * @PostMapping
	 * public String setRegularWaterUseData(@RequestBody List<Map<String,String>> regularWaterUseDataList) {
	 * try {
	 * Users objUsersId = new Users();
	 * for (Map<String, String> regularWaterUseData : regularWaterUseDataList) {
	 * DirectUseOfWater objRegularWaterUseData = new DirectUseOfWater();
	 * WaterInventory objWaterInventoryId = new WaterInventory();
	 * objWaterInventoryId.setWaterInventoryId(Integer.parseInt(regularWaterUseData.get("waterInventoryId")));
	 * objUsersId.setUsersId(Integer.parseInt(regularWaterUseData.get("userId")));
	 * objRegularWaterUseData.setWaterInventory(objWaterInventoryId);
	 * objRegularWaterUseData.setSourceType(regularWaterUseData.get("sourceType"));
	 * objRegularWaterUseData.setStartReading(Float.parseFloat(regularWaterUseData.get("startReading")));
	 * objRegularWaterUseData.setEndReading(Float.parseFloat(regularWaterUseData.get("endReading")));
	 * objRegularWaterUseData.setActualReading(Float.parseFloat(regularWaterUseData.get("actualReading")));
	 * objRegularWaterUseData.setrWDate(Utilities.getTodaysDate());
	 * objRegularWaterUseData.setUsers(objUsersId);
	 * regularWaterUseDataServices.save(objRegularWaterUseData);
	 * }
	 * return Constant.SUCCESS;
	 * } catch (Exception e) {
	 * LOGGER.error(e);
	 * return Constant.FAILURE;
	 * }
	 * }
	 */

}
