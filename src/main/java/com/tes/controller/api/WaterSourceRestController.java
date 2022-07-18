package com.tes.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tes.model.RegWaterSourceData;
import com.tes.model.Users;
import com.tes.model.WaterInventory;
import com.tes.model.WaterSource;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
@RequestMapping("/rest/water-source")
public class WaterSourceRestController
{
	@Autowired
	WaterInventoryServices waterInventoryServices;

	// Effected By Water Inventory ........by sushama
	// @Autowired
	// WaterSourceServices waterSourceServices;
	// Effected By Water Inventory ........by sushma
	// @Autowired
	// RegWaterSourceDataServices regularSourceDataServices;

	private static final Logger LOGGER = LogManager.getLogger(WaterSourceRestController.class);

	// Effected By Water Inventory ........by vishal
	/*
	 * @GetMapping("/{companyId}")
	 * private ArrayList<Object> getWaterSourceData(@PathVariable("companyId") int companyId, HttpServletRequest request) {
	 * List<WaterInventory> waterInventoryData = new ArrayList<>();
	 * ArrayList<Object> waterInventoryList = new ArrayList<>();
	 * ArrayList<Object> jsonRegularSourceData = new ArrayList<>();
	 * HashMap<String,Object> waterSourceDataConditionMapList = new HashMap<String,Object>();
	 * List<WaterSource>waterSourceData=new ArrayList<>();
	 * List<RegWaterSourceData> regularSourceData = new ArrayList<>();
	 * List<RegWaterSourceData> regularSourceDataList = new ArrayList<>();
	 * List<WaterSource> waterSourceList=new ArrayList<>();
	 * int waterInventoryId=0;
	 * try
	 * {
	 * waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId, Utilities.getTodaysDate());
	 * if(!Validator.isEmpty(waterInventoryData))
	 * {
	 * for(int i=0;i<waterInventoryData.size();i++)
	 * {
	 * WaterInventory wiDetail = new WaterInventory();
	 * wiDetail = waterInventoryData.get(i);
	 * waterInventoryId=waterInventoryData.get(i).getWaterInventoryId();
	 * waterSourceData= waterSourceServices.getWaterSourceData(waterInventoryId);
	 * wiDetail.setWaterSourceList(waterSourceData);
	 * if(!Validator.isEmpty(waterSourceData))
	 * {
	 * for(int j=0;j<waterSourceData.size();j++)
	 * {
	 * WaterSource watersourceDetail = new WaterSource();
	 * watersourceDetail = waterSourceData.get(j);
	 * regularSourceData=regularSourceDataServices.regularSourceDataBySourceName(waterInventoryId, watersourceDetail.getSourceName(), new PageRequest(0,1));
	 * if(!regularSourceDataList.containsAll(regularSourceData)) {
	 * regularSourceDataList.addAll(regularSourceData);
	 * }
	 * if(!waterSourceList.contains(watersourceDetail)){
	 * waterSourceList.add(watersourceDetail);
	 * }
	 * watersourceDetail.setRegularSourceDataList(regularSourceDataList);
	 * }
	 * }
	 * wiDetail.setWaterSourceList(waterSourceList);
	 * wiDetail.setRegularSourceDataByDate(regularSourceData);
	 * if(!Validator.isEmpty(wiDetail.getWaterSourceList())) {
	 * for(WaterSource waterSource:wiDetail.getWaterSourceList()) {
	 * HashMap<String, Object> wiList = new HashMap<String, Object>();
	 * if((waterSource.getRegularSourceDataList().get(0).getRsDate().equals(Utilities.getTodaysDate()))&&(waterSource.getSourceMeter().equalsIgnoreCase((Constant.YES))))
	 * {
	 * wiList.put("sourceMeter", waterSource.getSourceMeter());
	 * wiList.put("staff", waterSource.getRegularSourceDataList().get(0).getStaff());
	 * wiList.put("sourceName", waterSource.getSourceName());
	 * wiList.put("startReading",waterSource.getRegularSourceDataList().get(0).getStartReading());
	 * wiList.put("endReading", waterSource.getRegularSourceDataList().get(0).getEndReading());
	 * wiList.put("actualReading", waterSource.getRegularSourceDataList().get(0).getActualReading());
	 * }else if(!(waterSource.getRegularSourceDataList().get(0).getRsDate().equals(Utilities.getTodaysDate()))&&(waterSource.getSourceMeter().equalsIgnoreCase((Constant.YES))))
	 * {
	 * wiList.put("sourceMeter", waterSource.getSourceMeter());
	 * wiList.put("staff", waterSource.getRegularSourceDataList().get(0).getStaff());
	 * wiList.put("sourceName", waterSource.getSourceName());
	 * wiList.put("startReading", waterSource.getRegularSourceDataList().get(0).getEndReading());
	 * wiList.put("endReading", "");
	 * wiList.put("actualReading", "");
	 * }else if(!(waterSource.getRegularSourceDataList().get(0).getRsDate().equals(Utilities.getTodaysDate()))&&(waterSource.getSourceMeter().equalsIgnoreCase((Constant.NO))))
	 * {
	 * wiList.put("sourceMeter", waterSource.getSourceMeter());
	 * wiList.put("staff", waterSource.getRegularSourceDataList().get(0).getStaff());
	 * wiList.put("sourceName", wiDetail.getWaterSourceList().get(0).getSourceName());
	 * wiList.put("startReading", "");
	 * wiList.put("endReading", "");
	 * wiList.put("actualReading", "");
	 * }
	 * else if((waterSource.getRegularSourceDataList().get(0).getRsDate().equals(Utilities.getTodaysDate()))&&(waterSource.getSourceMeter().equalsIgnoreCase((Constant.NO))))
	 * {
	 * wiList.put("sourceMeter", waterSource.getSourceMeter());
	 * wiList.put("staff", waterSource.getRegularSourceDataList().get(0).getStaff());
	 * wiList.put("sourceName", waterSource.getSourceName());
	 * wiList.put("startReading", "");
	 * wiList.put("endReading", "");
	 * wiList.put("actualReading", waterSource.getRegularSourceDataList().get(0).getActualReading());
	 * }else if(Validator.isEmpty(waterSource.getRegularSourceDataList()))
	 * {
	 * wiList.put("sourceMeter", waterSource.getSourceMeter());
	 * wiList.put("staff", "");
	 * wiList.put("sourceName", waterSource.getSourceName());
	 * wiList.put("startReading", "");
	 * wiList.put("endReading", "");
	 * wiList.put("actualReading", "");
	 * }
	 * if(waterSource.getRegularSourceDataList().get(0).getRsDate().equals(Utilities.getTodaysDate())) {
	 * waterSourceDataConditionMapList.put("Input", Constant.NO);
	 * }else {
	 * waterSourceDataConditionMapList.put("Input", Constant.YES);
	 * }
	 * waterInventoryList.add(wiList);
	 * waterSourceDataConditionMapList.put("regularSourceDataList", waterInventoryList);
	 * }
	 * waterSourceDataConditionMapList.put("Result", "True");
	 * }else {
	 * waterSourceDataConditionMapList.put("Result", "False");
	 * }
	 * }
	 * }
	 * }catch(Exception e)
	 * {
	 * LOGGER.error(e);
	 * }
	 * jsonRegularSourceData.add(waterSourceDataConditionMapList);
	 * return jsonRegularSourceData;
	 * }
	 */

	// Effected By Water Inventory ........by vishal
	/*
	 * @ResponseStatus(HttpStatus.CREATED)
	 * @PostMapping
	 * private String saveWaterSourceData(@RequestBody List<Map<String,String>> regularSourceDataList) {
	 * try {
	 * Users objUsersId = new Users();
	 * for (Map<String, String> regularSourceData : regularSourceDataList) {
	 * RegWaterSourceData objRegularSourceData = new RegWaterSourceData();
	 * WaterInventory objWaterInventoryId = new WaterInventory();
	 * objWaterInventoryId.setWaterInventoryId(Integer.parseInt(regularSourceData.get("waterInventoryId")));
	 * objUsersId.setUsersId(Integer.parseInt(regularSourceData.get("userId")));
	 * objRegularSourceData.setStaff(Integer.parseInt(regularSourceData.get("staff")));
	 * objRegularSourceData.setWaterInventory(objWaterInventoryId);
	 * objRegularSourceData.setSourceName(regularSourceData.get("sourceName"));
	 * objRegularSourceData.setStartReading(Float.parseFloat(regularSourceData.get("startReading")));
	 * objRegularSourceData.setEndReading(Float.parseFloat(regularSourceData.get("endReading")));
	 * objRegularSourceData.setActualReading(Float.parseFloat(regularSourceData.get("actualReading")));
	 * objRegularSourceData.setRsDate(Utilities.getTodaysDate());
	 * objRegularSourceData.setUsers(objUsersId);
	 * regularSourceDataServices.save(objRegularSourceData);
	 * }
	 * return Constant.SUCCESS;
	 * }catch(Exception e) {
	 * LOGGER.error(e);
	 * return Constant.FAILURE;
	 * }
	 * }
	 */
}
