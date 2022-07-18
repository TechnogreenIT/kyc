package com.tes.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;

@RestController
@RequestMapping("/rest/water-treatment")
public class WastewaterTreatmentRestController
{
	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;
	private static final Logger LOGGER = LogManager.getLogger(WastewaterTreatmentRestController.class);

	// Effected by water inventory --sushma
	/*
	 * @GetMapping("/{companyId}") private ArrayList<Object>
	 * getWaterTreatmentData(@PathVariable("companyId") int companyId,
	 * HttpServletRequest request) { ArrayList<Object> waterTreatmentDataList = new
	 * ArrayList<>(); ArrayList<Object> jsonWTPData = new ArrayList<>();
	 * HashMap<String,Object> wTPDataMapList = new HashMap<String,Object>();
	 * List<WastewaterTreatment> treatementTypeAndName = new ArrayList<>();
	 * List<WaterInventory> waterInventoryData = new ArrayList<>(); int
	 * waterInventoryId = 0; String waterTreatment="", treatmentType=""; try {
	 * waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId,
	 * Utilities.getTodaysDate()); if (!Validator.isEmpty(waterInventoryData)) { for
	 * (WaterInventory waterInventory:waterInventoryData) { WaterInventory wiDetail
	 * = new WaterInventory(); wiDetail = waterInventory; waterInventoryId =
	 * wiDetail.getWaterInventoryId(); //Effected By Water Inventory ........by
	 * vishal //waterTreatment=wiDetail.getWaterTreatment();
	 * if(waterTreatment.equalsIgnoreCase(Constant.YES)) { treatementTypeAndName=
	 * null; //treatementTypeAndName =
	 * wastewaterTreatmentServices.getTreatmentTypeAndName(waterInventoryId);
	 * wiDetail.setTreatmentTypeAndNameBywiId(treatementTypeAndName);
	 * if(!Validator.isEmpty(wiDetail.getTreatmentTypeAndNameBywiId())) {
	 * for(WastewaterTreatment
	 * watertreatment:wiDetail.getTreatmentTypeAndNameBywiId()) { treatmentType =
	 * watertreatment.getLabel().split("-")[0];
	 * wiDetail.setRegularTreatmentDataByTreatmentType(regularTreatmentDataServices.
	 * getRegularTreatmentDataByTreatmentType(treatmentType, waterInventoryId, new
	 * PageRequest(0,1)));
	 * if(!Validator.isEmpty(wiDetail.getRegularTreatmentDataByTreatmentType())) {
	 * for( RegularTreatmentData
	 * regularTreatmentData:wiDetail.getRegularTreatmentDataByTreatmentType()) {
	 * HashMap<String, Object> wiList = new HashMap<String, Object>();
	 * if(regularTreatmentData.getRtDate().equals(Utilities.getTodaysDate())) {
	 * wiList.put("wiId", waterInventoryId); wiList.put("type",
	 * regularTreatmentData.getType()); wiList.put("treatmentType",
	 * regularTreatmentData.getTreatmentType()); wiList.put("startReading",
	 * regularTreatmentData.getStartReading()); wiList.put("endReading",
	 * regularTreatmentData.getEndReading()); wiList.put("actualReading",
	 * regularTreatmentData.getEndReading()); wiList.put("energyStartReading",
	 * regularTreatmentData.getEnergyStartReading()); wiList.put("energyEndReading",
	 * regularTreatmentData.getEnergyEndReading());
	 * wiList.put("energyActualReading", regularTreatmentData.getEnergyAvg());
	 * wTPDataMapList.put("Input", Constant.NO); }else
	 * if(!(regularTreatmentData.getRtDate().equals(Utilities.getTodaysDate()))) {
	 * wiList.put("wiId", waterInventoryId); wiList.put("type",
	 * regularTreatmentData.getType()); wiList.put("treatmentType",
	 * regularTreatmentData.getTreatmentType()); wiList.put("startReading",
	 * regularTreatmentData.getEndReading()); wiList.put("endReading", "");
	 * wiList.put("actualReading",""); wiList.put("energyStartReading",
	 * regularTreatmentData.getEnergyEndReading()); wiList.put("energyEndReading",
	 * ""); wiList.put("energyActualReading", ""); wTPDataMapList.put("Input",
	 * Constant.YES);
	 * }else
	 * if(Validator.isEmpty(wiDetail.getRegularTreatmentDataByTreatmentType())) {
	 * wiList.put("wiId", waterInventoryId); wiList.put("type",
	 * watertreatment.getLabel()); wiList.put("treatmentType",
	 * watertreatment.getLabel().split("-")[0]); wiList.put("startReading", "");
	 * wiList.put("endReading", ""); wiList.put("actualReading","");
	 * wiList.put("energyStartReading", ""); wiList.put("energyEndReading", "");
	 * wiList.put("energyActualReading", ""); wTPDataMapList.put("Input",
	 * Constant.YES); wTPDataMapList.put("Result", "False"); }
	 * waterTreatmentDataList.add(wiList);
	 * wTPDataMapList.put("RegularTreatmentData", waterTreatmentDataList); }
	 * wTPDataMapList.put("Result", "True"); } } } } } } }catch(Exception e) {
	 * LOGGER.error(e); } jsonWTPData.add(wTPDataMapList); return jsonWTPData; }
	 */

	/*
	 * @ResponseStatus(HttpStatus.CREATED)
	 * @PostMapping private String setWaterTreatmentData(@RequestBody
	 * List<Map<String,String>> regularTreatmentDataList) { try { for (Map<String,
	 * String> regularTreatmentData : regularTreatmentDataList) {
	 * RegularTreatmentData objregularTreatmentData=new RegularTreatmentData();
	 * Users users=new Users();
	 * users.setUsersId(Integer.parseInt(regularTreatmentData.get("usersId")));
	 * WaterInventory waterInventory=new WaterInventory(); //Effected By Water
	 * Inventory ........by vishal
	 * //waterInventory.setWaterInventoryId(Integer.parseInt(regularTreatmentData.
	 * get("wiId")));
	 * objregularTreatmentData.setTreatmentType(regularTreatmentData.get(
	 * "treatmentType"));
	 * objregularTreatmentData.setType(regularTreatmentData.get("type"));
	 * objregularTreatmentData.setStartReading(Float.parseFloat(regularTreatmentData
	 * .get("startReading")));
	 * objregularTreatmentData.setEndReading(Float.parseFloat(regularTreatmentData.
	 * get("endReading")));
	 * objregularTreatmentData.setActualReading(Float.parseFloat(
	 * regularTreatmentData.get("actualReading")));
	 * objregularTreatmentData.setEnergyStartReading(Float.parseFloat(
	 * regularTreatmentData.get("energyStartReading")));
	 * objregularTreatmentData.setEnergyEndReading(Float.parseFloat(
	 * regularTreatmentData.get("energyEndReading")));
	 * objregularTreatmentData.setEnergyAvg(Float.parseFloat(regularTreatmentData.
	 * get("energyActualReading"))); objregularTreatmentData.setUsers(users);
	 * objregularTreatmentData.setWaterInventory(waterInventory);
	 * objregularTreatmentData.setRtDate(Utilities.getTodaysDate());
	 * regularTreatmentDataServices.save(objregularTreatmentData); } return
	 * Constant.SUCCESS; }catch(Exception e) { LOGGER.error(e); return
	 * Constant.FAILURE; } }
	 */
}
