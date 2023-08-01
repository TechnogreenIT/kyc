package com.tes.controller.environmentalofficer.consent;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.activation.FileDataSource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tes.model.Consent;
import com.tes.model.EmpData;
import com.tes.model.Prefilter;
import com.tes.model.WastewaterRecycle;
import com.tes.model.WastewaterTreatment;
import com.tes.model.WaterInventory;
import com.tes.model.WaterSource;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.WasteWaterRecycleSevices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

import antlr.Parser;

/**
 * This class demonstrate used of the water inventory details .
 * This class perform operation of add the water inventory details.
 * 
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class WaterInventoryController
{

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWaterServices;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	WasteWaterRecycleSevices wastewaterRecycleSevices;

	@Autowired
	PrefilterServices prefilterServices;

	/*
	 * @Autowired WaterInventoryServices waterInventoryServices;
	 * @Autowired WastewaterTreatmentServices wastewaterTreatmentServices;
	 * @Autowired WaterSourceServices waterSourceServices;
	 * @Autowired IndustrialServices industrialServices;
	 * @Autowired FiltersServices filtersServices;
	 */

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to add the data for water inventory such as filter, source,
	 * water treatment,waste water treatment,used of water.
	 * 
	 * @param action waterinventory
	 * @param consentId the id of consent
	 * @param source the source of water
	 * @param sourceWaterMeter The source water meter used to check the water reading
	 * @param main it represent in water inventory data in array.
	 * @param industrialName The industrial name
	 * @param industrialWaterMeter The industrial water meter
	 * @param industrialLoss the industrial loss value
	 * @param filterList the filter list
	 * @param filterSourceList the filter source list
	 * @param filterMeter the filter meter
	 * @param filterWaterLoss the filter water loss value
	 * @param softenerWaterUse the softener water use
	 * @param softenerWaterUseMeter the softener water use meter
	 * @param softenerWaterUseDefault the softener water use default
	 * @param dmWaterUse The DM water use
	 * @param dmWaterUseMeter The DM water use meter
	 * @param dmWaterUseDefault The DM water use default value
	 * @param ufWaterUse The UF water use
	 * @param ufWaterUseMeter The UF water use meter
	 * @param ufWaterUseDefault The UF water use default value
	 * @param roWaterUse The RO water use
	 * @param roWaterUseMeter The RO water use meter
	 * @param roWaterUseDefault The RO water use default value
	 * @param treatmentType The treatment type
	 * @param plantCapacity The plant capacity
	 * @param endUseName the name of etp and stp
	 * @param endUseQuantity the quantity of etp and stp
	 * @param count it count total number of etp and stp
	 * @return flag it return count acording to number of row inserted
	 */
	// Effected By Water Inventory ........by vishal
	/*
	 * @RequestMapping(value = "ajax-water-inventory-c2o", method = RequestMethod.POST)
	 * public @ResponseBody int setWaterInventoryForCToO(@RequestParam("action") String action, @RequestParam("consentNo") int consentId,
	 * @RequestParam("source") String[] source, @RequestParam("source_water_meter") String[] sourceWaterMeter,
	 * @RequestParam("main") String[] main, @RequestParam("industrial_name") String[] industrialName,
	 * @RequestParam("industrial_water_meter") String[] industrialWaterMeter,
	 * @RequestParam("industrial_loss") String[] industrialLoss, @RequestParam("filter_list") String[] filterList,
	 * @RequestParam("filter_source_list") String[] filterSourceList,
	 * @RequestParam("filter_meter") String[] filterMeter,
	 * @RequestParam("filter_water_loss") int[] filterWaterLoss,
	 * @RequestParam("softener_water_use") String[] softenerWaterUse,
	 * @RequestParam("softener_water_use_meter") String[] softenerWaterUseMeter,
	 * @RequestParam("softener_water_use_default") int[] softenerWaterUseDefault,
	 * @RequestParam("dm_water_use") String[] dmWaterUse,
	 * @RequestParam("dm_water_use_meter") String[] dmWaterUseMeter,
	 * @RequestParam("dm_water_use_default") int[] dmWaterUseDefault,
	 * @RequestParam("uf_water_use") String[] ufWaterUse,
	 * @RequestParam("uf_water_use_meter") String[] ufWaterUseMeter,
	 * @RequestParam("uf_water_use_default") int[] ufWaterUseDefault,
	 * @RequestParam("ro_water_use") String[] roWaterUse,
	 * @RequestParam("ro_water_use_meter") String[] roWaterUseMeter,
	 * @RequestParam("ro_water_use_default") int[] roWaterUseDefault,
	 * @RequestParam("treatment_type") String[] treatmentType, @RequestParam("plant_capacity") int[] plantCapacity,
	 * @RequestParam("end_use_name") String[] endUseName, @RequestParam("end_use_quantity") int[] endUseQuantity,
	 * @RequestParam("count") int[] count) {
	 * try {
	 * // if ($action == 'waterinventory') { // There is no need of this conditon ..........by vishal
	 * int flag = 0;
	 * // CATEEN
	 * String canteen = main[0];
	 * String cookingcan = main[1];
	 * // FILTERS
	 * String filterPlant = main[2];
	 * // DIRECT USE
	 * String domesticUseOfSource = main[3];
	 * String domesticMeter = main[4];
	 * int domesticWaterLoss = Integer.parseInt(main[5]); //
	 * String industrialUseOfSource = main[6];
	 * String laundryUseOfSource = main[7];
	 * String laundryMeter = main[8];
	 * int laundryWaterLoss = Integer.parseInt(main[9]); // -1 Means Not Applicable "NA"
	 * String fhUseOfSource = main[10];
	 * String fhMeter = main[11];
	 * int fhWaterLoss = Integer.parseInt(main[12]); // -1 Means Not Applicable "NA"
	 * // TREATEMENT PLANT
	 * String treatmentPlant = main[13];
	 * WaterInventory objWaterInventory = new WaterInventory();
	 * Consent objConsent = new Consent();
	 * objConsent.setConsentId(consentId);
	 * objWaterInventory.setConsent(objConsent);
	 * objWaterInventory.setHouseCanteen(canteen);
	 * objWaterInventory.setCookingCanteen(cookingcan);
	 * objWaterInventory.setFilterationPlant(filterPlant);
	 * objWaterInventory.setDomesticUseOfSource(domesticUseOfSource);
	 * objWaterInventory.setIndustrialUseOfSource(industrialUseOfSource);
	 * objWaterInventory.setLaundryUseOfSource(laundryUseOfSource);
	 * objWaterInventory.setFireHydrantUseOfSource(fhUseOfSource);
	 * objWaterInventory.setWaterTreatment(treatmentPlant);
	 * waterInventoryServices.save(objWaterInventory);
	 * for (int i = 0; i < source.length; i++) {
	 * WaterSource objWaterSource = new WaterSource();
	 * WaterInventory objWaterInventoryId = new WaterInventory();
	 * objWaterInventoryId.setWaterInventoryId(objWaterInventory.getWaterInventoryId());
	 * objWaterSource.setWaterInventory(objWaterInventoryId);
	 * objWaterSource.setSourceName(source[i]);
	 * objWaterSource.setSourceMeter(sourceWaterMeter[i]);
	 * waterSourceServices.save(objWaterSource);
	 * flag++;
	 * }
	 * if (filterPlant.equals(Constant.YES)) {
	 * if (filterList != null && filterList.length > 0) {
	 * for (int i = 0; i < filterList.length; i++) {
	 * String[] filterUse = null;
	 * String[] filterUseWaterMeter = null;
	 * int[] filterLoss = null;
	 * if (filterList[i].equals("Softener")) {
	 * filterUse = softenerWaterUse;
	 * filterUseWaterMeter = softenerWaterUseMeter;
	 * filterLoss = softenerWaterUseDefault;
	 * } else if (filterList[i].equals("DM")) {
	 * filterUse = dmWaterUse;
	 * filterUseWaterMeter = dmWaterUseMeter;
	 * filterLoss = dmWaterUseDefault;
	 * } else if (filterList[i].equals("RO")) {
	 * filterUse = roWaterUse;
	 * filterUseWaterMeter = roWaterUseMeter;
	 * filterLoss = roWaterUseDefault;
	 * } else if (filterList[i].equals("UF")) {
	 * filterUse = ufWaterUse;
	 * filterUseWaterMeter = ufWaterUseMeter;
	 * filterLoss = ufWaterUseDefault;
	 * }
	 * for (int j = 0; j < filterUse.length; j++) {
	 * Filters objfilters = new Filters();
	 * WaterInventory objWIid = new WaterInventory();
	 * objWIid.setWaterInventoryId(objWaterInventory.getWaterInventoryId());
	 * objfilters.setWaterInventory(objWIid);
	 * objfilters.setSourceName(filterSourceList[i]);
	 * objfilters.setFilterName(filterList[i]);
	 * objfilters.setWaterMeter(filterMeter[i]);
	 * objfilters.setLossValue(filterWaterLoss[i]);
	 * objfilters.setFilterUse(filterUse[j]);
	 * objfilters.setFilterMeter(filterUseWaterMeter[j]);
	 * objfilters.setWaterLoss(filterLoss[j]);
	 * filtersServices.save(objfilters);
	 * flag++;
	 * }
	 * }
	 * }
	 * }
	 * // DIRECT WATER USE
	 * UseOfWater objUseOfWater = new UseOfWater();
	 * WaterInventory objWIid = new WaterInventory();
	 * objWIid.setWaterInventoryId(objWaterInventory.getWaterInventoryId());
	 * objUseOfWater.setWaterInventory(objWIid);
	 * objUseOfWater.setDomestic(domesticUseOfSource);
	 * objUseOfWater.setDomesticMeter(domesticMeter);
	 * objUseOfWater.setDomesticWaterLoss(domesticWaterLoss);
	 * objUseOfWater.setIndustrial(industrialUseOfSource);
	 * objUseOfWater.setLaundry(laundryUseOfSource);
	 * objUseOfWater.setLaundryMeter(laundryMeter);
	 * objUseOfWater.setLaundryWaterLoss(laundryWaterLoss);
	 * objUseOfWater.setFireHydrant(fhUseOfSource);
	 * objUseOfWater.setFireHydrantMeter(fhMeter);
	 * objUseOfWater.setFireHydrantWaterLoss(fhWaterLoss);
	 * UseOfWater isUseOfWaterAdded = useOfWaterServices.save(objUseOfWater);
	 * if (isUseOfWaterAdded != null) {
	 * flag++;
	 * if (industrialUseOfSource.equals("checked")) {
	 * for (int i = 0, size = industrialName.length; i < size; i++) {
	 * Industrial objIndustrial = new Industrial();
	 * UseOfWater useOfWaterId = new UseOfWater();
	 * useOfWaterId.setUseOfWaterId(objUseOfWater.getUseOfWaterId());
	 * objIndustrial.setUseOfWater(useOfWaterId);
	 * objIndustrial.setIndName(industrialName[i]);
	 * objIndustrial.setWaterMeter(industrialWaterMeter[i]);
	 * //objIndustrial.setWaterLoss(Float.valueOf(industrialLoss[i])); // added by jemish after changing pojo Industrial string to float
	 * industrialServices.save(objIndustrial);
	 * flag++;
	 * }
	 * }
	 * }
	 * if (treatmentPlant.equals(Constant.YES)) {
	 * int swap = 0;
	 * int etp=0;
	 * int stp=0;
	 * String type="";
	 * for (int j = 0; j < treatmentType.length; j++) {
	 * int a = swap + count[j];
	 * if (treatmentType[j].equals("ETP") ) {
	 * etp++;
	 * type = treatmentType[j] +etp + "-" + plantCapacity[j] + " capacity";
	 * }else {
	 * stp++;
	 * type = treatmentType[j] +stp+ "-" + plantCapacity[j] + " capacity";
	 * }
	 * for (int k = swap; k < a; k++) {
	 * WaterTreatment objWaterTreatment = new WaterTreatment();
	 * WaterInventory objWaterInventoryId = new WaterInventory();
	 * objWaterInventoryId.setWaterInventoryId(objWaterInventory.getWaterInventoryId());
	 * objWaterTreatment.setWaterInventory(objWaterInventoryId);
	 * objWaterTreatment.setTreatmentType(treatmentType[j]);
	 * objWaterTreatment.setTypeName(type);
	 * objWaterTreatment.setCapacity(plantCapacity[j]);
	 * objWaterTreatment.setRecycledTo(endUseName[k]);
	 * objWaterTreatment.setQuantity(endUseQuantity[k]);// in ETP/STP is selected so it will show error because it not push in array by javascript ..........vishal
	 * waterTreatmentServices.save(objWaterTreatment);
	 * }
	 * swap = a;
	 * }
	 * }
	 * }catch(Exception e) {
	 * LOGGER.error(e);
	 * }
	 * return 0;
	 * }
	 */

	// TODO documentation by vishal
	
	@PostMapping("ajax-water-inventory-c2o")
	public @ResponseBody boolean getWaterInventoryData(@RequestParam( value = "file", required = false) MultipartFile cgwaFile,@RequestParam( "alldata") String alldt,HttpServletRequest request)
	{

		EmpData empDataSession = null;
		String compName = null;
		empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
		compName = empDataSession.getCompanyProfile().getCompName();
		String renameFile = null;
		renameFile = Utilities.renameFile(compName);
		JsonObject convertedObject = new Gson().fromJson(alldt, JsonObject.class);
	    convertedObject.isJsonObject();
//		JsonParser parser = new JsonParser();  
//		JSONObject json = (JSONObject) parser.parse(stringToParse);  
	//	JsonObject jsonObjWaterInventory = new JsonObject();
		
		JsonObject waterInventoryData =  convertedObject.getAsJsonObject("waterInventory");
		WaterInventory objWaterInventory = new WaterInventory();
	//	JsonArray waterSourcesDataList2 = convertedObject.getAsJsonArray("waterSources");
		
		boolean flag = false;

		try
		
		{
			Consent objConsent = new Consent();
			objConsent.setConsentId(waterInventoryData.getAsJsonObject().get("consentId").getAsInt());		
			objWaterInventory.setConsent(objConsent);
			objWaterInventory.setHouseCanteen(waterInventoryData.getAsJsonObject().get("isHouseCanteen").getAsBoolean());
			objWaterInventory.setCookingCanteen(waterInventoryData.getAsJsonObject().get("isCookingCanteen").getAsBoolean());
			objWaterInventory.setWaterTreatment(waterInventoryData.getAsJsonObject().get("isWaterTreatment").getAsBoolean());
			objWaterInventory.setWastewaterTreatment(waterInventoryData.getAsJsonObject().get("iswasteWaterTreatment").getAsBoolean());
			
			//get sourcename purpose
			JsonArray waterSourcesDataList1 = convertedObject.getAsJsonArray("waterSources");
			for (JsonElement waterSources : waterSourcesDataList1)
			{
		
     			WaterSource objWaterSource1 = new WaterSource();
     			
				objWaterSource1.setSourceName(waterSources.getAsJsonObject().get("sourceName").getAsString());
				//if using bore well watersource then need to check cgwa permission
				if(objWaterSource1.getSourceName().toString().equalsIgnoreCase ("Bore well")) 
				{
					// objWaterInventory.setIscgwapermissiion(waterInventoryData.getAsJsonObject().get("iscgwapermissiion").getAsString());
					 objWaterInventory.setIscgwapermissiion(waterInventoryData.getAsJsonObject().get("iscgwapermissiion").getAsBoolean());
					 //cgwa applied
					 if(waterInventoryData.getAsJsonObject().get("iscgwapermissiion").getAsString().equalsIgnoreCase(Constant.YES)) 
					 {		
						 String file1 = null;
							String mainFile1 = null;
							byte[] bytes1 = null;
							if (!cgwaFile.isEmpty())
							{
								file1=cgwaFile.getOriginalFilename();
								bytes1 = cgwaFile.getBytes();
							}
							mainFile1 = renameFile + "_" + file1;	
						 objWaterInventory.setCgwa_file_name(mainFile1);						
						  Files.write(Paths.get(Constant.cgwa_file_path + mainFile1), bytes1); // if path is not getting ready it will show error ....by vishal
						  objWaterInventory.setCgwa_file_path(Constant.cgwa_file_path + mainFile1);		
					 }
					 //cgwa not applied
					 else
					 { 
						 objWaterInventory.setCgwa_file_name(Constant.NA);		
						 objWaterInventory.setCgwa_file_path(Constant.NA);
					 }
					 
				}
				//if water source is not bore well
				else {
					 objWaterInventory.setIscgwapermissiion(waterInventoryData.getAsJsonObject().get("iscgwapermissiion").getAsBoolean());
					 objWaterInventory.setCgwa_file_name(Constant.NA);		
					 objWaterInventory.setCgwa_file_path(Constant.NA);
				}
				
			}
		//
			
			waterInventoryServices.save(objWaterInventory);

			JsonArray waterSourcesDataList = convertedObject.getAsJsonArray("waterSources");
		
		
			for (JsonElement waterSources : waterSourcesDataList)
			{
				WaterSource objWaterSource = new WaterSource();
				//WaterSource objWaterSource1=convertedObject.getAsJsonObject(WaterSource)
				objWaterSource.setWaterInventory(objWaterInventory);
				objWaterSource.setSourceName(waterSources.getAsJsonObject().get("sourceName").getAsString());
				objWaterSource.setSourceMeter(waterSources.getAsJsonObject().get("sourceMeter").getAsBoolean());
				waterSourceServices.save(objWaterSource);
				
 				
				JsonArray preFilterDataList = waterSources.getAsJsonObject().get("preFilter").getAsJsonArray();			
			 //jsonArray preFilterDataList = convertedObject.getAsJsonObject().getAsJsonArray("preFilter");
				for (JsonElement preFilters : preFilterDataList)
				{
					Prefilter objPreFilter = new Prefilter();
					objPreFilter.setWaterSource(objWaterSource);
					objPreFilter.setAcf(preFilters.getAsJsonObject().get("acf").getAsBoolean());
					objPreFilter.setPsf(preFilters.getAsJsonObject().get("psf").getAsBoolean());
					objPreFilter.setDmf(preFilters.getAsJsonObject().get("dmf").getAsBoolean());
					objPreFilter.setMeter(preFilters.getAsJsonObject().get("isPrefilterMeter").getAsBoolean());
					objPreFilter.setPrefilter(preFilters.getAsJsonObject().get("isPrefilter").getAsBoolean());
					prefilterServices.save(objPreFilter);
				}
				

			}

			flag = true;

		}
		catch (Exception e)
		{
 			LOGGER.error(e);
		}

		/*
		 * JsonArray directWaterUseDataList = jsonObjWaterInventory.getAsJsonArray("directUseOfWater");
		 * for (JsonElement directWaterUse : directWaterUseDataList)
		 * {
		 * DirectUseOfWater objDirectUseOfWater = new DirectUseOfWater();
		 * objDirectUseOfWater.setWhereToUse(directWaterUse.getAsJsonObject().get("whereToUse").getAsString());
		 * objDirectUseOfWater.setMeter(directWaterUse.getAsJsonObject().get("isMeter").getAsBoolean());
		 * objDirectUseOfWater.setWaterLoss(directWaterUse.getAsJsonObject().get("waterLoss").getAsFloat());
		 * directUseOfWaterServices.save(objDirectUseOfWater);
		 * }
		 */
		// Effected By Water Inventory ........by vishal
		/*
		 * JsonArray directIndWaterUseDataList = jsonObjWaterInventory.getAsJsonArray("industrial");
		 * for (JsonElement directIndWaterUse : directIndWaterUseDataList) {
		 * Industrial objIndustrial = new Industrial();
		 * objIndustrial.setIndName(directIndWaterUse.getAsJsonObject().get("indName").getAsString());
		 * objIndustrial.setWaterMeter(directIndWaterUse.getAsJsonObject().get("waterMeter").getAsString());
		 * objIndustrial.setWaterLoss(directIndWaterUse.getAsJsonObject().get("waterLoss").getAsFloat());
		 * industrialServices.save(objIndustrial);
		 * }
		 */

		return flag;
	}

	// get tretmentlabel...... By Amin
	@PostMapping("ajax-generateTretmentLabel")
	public @ResponseBody String treatmentType(@RequestParam(value = "plantType", required = false) String plantType)
	{
		String treatmentLabel = "";
		try
		{
			int countName = 0;
			countName = wastewaterTreatmentServices.generateLabelBytrtmentType(plantType);
			countName++;
			treatmentLabel = plantType + countName;

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return treatmentLabel;
	}

	// get Used Filter Data List .....by Amin
	@PostMapping("ajax-getUsedFilterUseNameList")
	public @ResponseBody TreeSet<String> usedFilterUseName()
	{
		TreeSet<String> wasteWaterUsedList = new TreeSet<>();
		try
		{
			TreeSet<String> filterusedList = new TreeSet<>();
			// Affected by filter .........by vishal
			filterusedList = filterUseServices.getUsedFilteruseType();// filterUseServices.getUsedFilterUseName();

			wasteWaterUsedList.addAll(filterusedList);

			TreeSet<String> directUseList = new TreeSet<>();
			directUseList = directUseOfWaterServices.getUsedDirectUseName();

			wasteWaterUsedList.addAll(directUseList);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return wasteWaterUsedList;
	}

	// save waste water tretment Data
	@PostMapping("ajax-water-wasteWater-c2o")
	public @ResponseBody String getWaterTretmentData(@RequestBody JsonObject jsonObjWasteWaterWater)
	{

		JsonObject waterInventoryData = jsonObjWasteWaterWater.getAsJsonObject("waterInventory");

		String todayDate = Utilities.getTodaysDate();
		WastewaterTreatment objWasteWaterTretment = new WastewaterTreatment();
	//	int id = waterInventoryServices.getWaterInventoryIdByConsent(todayDate);
		////mmm
		List<Integer> listId = new ArrayList<>();
		 listId= waterInventoryServices.getWaterInventoryIdByConsent(todayDate, new PageRequest(0, 1));
		
		if (!Validator.isEmpty(listId))
		{
//			int id = listId.get(0).getWaterInventoryId();	
			int id = listId.get(0);
			WaterInventory waterInventory = new WaterInventory();
		//	waterInventory.setWaterInventoryId(id);
			waterInventory.setWaterInventoryId(id);

			objWasteWaterTretment.setWaterInventory(waterInventory);
			objWasteWaterTretment.setTreatmentType(jsonObjWasteWaterWater.getAsJsonObject().get("tretmentType").getAsString());
			objWasteWaterTretment.setLabel(jsonObjWasteWaterWater.getAsJsonObject().get("labelName").getAsString());
			objWasteWaterTretment.setCapacity(jsonObjWasteWaterWater.getAsJsonObject().get("quantity").getAsInt());
			wastewaterTreatmentServices.save(objWasteWaterTretment);

			int wwid = objWasteWaterTretment.getWastewaterTreatmentId();

			WastewaterTreatment objWasteWaterTretmentId = new WastewaterTreatment();
			objWasteWaterTretmentId.setWastewaterTreatmentId(wwid);

			JsonArray waterRecyledDataList = jsonObjWasteWaterWater.getAsJsonArray("RecycledArrayList");
			for (JsonElement waterUse : waterRecyledDataList)
			{
				WastewaterRecycle objWaterRecyled = new WastewaterRecycle();
				objWaterRecyled.setWastewaterTreatment(objWasteWaterTretmentId);
				objWaterRecyled.setRecycledTo(waterUse.getAsJsonObject().get("Name").getAsString());
				objWaterRecyled.setUseType(waterUse.getAsJsonObject().get("type").getAsString());
				objWaterRecyled.setQuantity(waterUse.getAsJsonObject().get("Quantity").getAsFloat());
				objWaterRecyled.setMeter(waterUse.getAsJsonObject().get("meter").getAsBoolean());
				objWaterRecyled.setTreatmentLabel(jsonObjWasteWaterWater.getAsJsonObject().get("tretmentType").getAsString());
				wastewaterRecycleSevices.save(objWaterRecyled);
			}
		}
		return "Saved";
	}
}
