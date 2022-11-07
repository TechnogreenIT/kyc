package com.tes.controller.environmentalofficer;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.tes.model.Containers;
import com.tes.model.HazardousManifest;
import com.tes.model.WasteDescriptionConsistency;
import com.tes.services.environmentalofficer.ContainersServices;
import com.tes.services.environmentalofficer.HazardousManifestServices;
import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;

/**
 * This class used to manage view manifest page.
 * 
 * @author Sushama Dadas
 */
@Controller
@SessionAttributes({"monthId"})
public class ViewManifestController
{

	@Autowired
	HazardousManifestServices hazardousManifestServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	ContainersServices containersServices;

	@Autowired
	WasteDescriptionConsistencyServices wasteDescriptionConsistencyServices;

	private static final Logger LOGGER = LogManager.getLogger(ViewManifestController.class);

	/**
	 * This method used to get month and date value of selected year of manifest for making accordion
	 * 
	 * @param action the month or date value.
	 * @return jsonString it returns String value of json array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "/ajax-day-hw-manifest", method = RequestMethod.POST)
	
	@ResponseBody
	public List<Integer> countDays(@RequestParam(value = "year", required = false) int year,
			@RequestParam(value = "month", required = false) int month) throws JSONException
	{
		List<Integer> arrayDay = new ArrayList<Integer>();
		try
		{

			arrayDay = hazardousManifestServices.HazardousManifestDayByYearandMonth(year, month);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return arrayDay;

	}

	
	
	
	
/*@RequestMapping(value = "/ajax-day-hw-manifest", method = RequestMethod.POST)
	
	@ResponseBody
	public List<Integer> countDays(@RequestParam(value = "year", required = false) int year,
			@RequestParam(value = "month", required = false) int month) throws JSONException
	{
		List<Integer> arrayDay = new ArrayList<Integer>();
		try
		{

			arrayDay = hazardousManifestServices.HazardousManifestDayByYearandMonth(year, month);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return arrayDay;

	}*/

	@RequestMapping(value = "/ajax-hw-manifest-data", method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String HazData(@RequestParam(value = "selectedDate", required = false) String selectedDate) throws JSONException
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		try
		{
			HazardousManifest hazardousManifestData = new HazardousManifest();
			hazardousManifestData = hazardousManifestServices.getHazardousManifestByDate(selectedDate);
			hazardousManifestData.setContainersList(containersServices.containersDataById(hazardousManifestData.getHazardousManifestId()));
			hazardousManifestData.setWasteDescriptionListByConsistency(wasteDescriptionConsistencyServices.wdDataBywasteDesc(hazardousManifestData.getHazardousManifestId()));

			json = new JSONObject();

			json.put("ReceiversPhoneNo", new String(hazardousManifestData.getReceiversPhoneNo()));
			json.put("ReceiversName", new String(hazardousManifestData.getReceiversName()));
			json.put("ReceiversAdress", new String(hazardousManifestData.getReceiversAddress()));
			json.put("ReceiversAuthNo", new String(hazardousManifestData.getReceiversAuthorizationNo()));

			json.put("SendersAuthNo", new String(hazardousManifestData.getSendersAuthorizationNo()));
			json.put("SendersMailAddress", new String(hazardousManifestData.getSendersMailingAddress()));
			json.put("SendersName", new String(hazardousManifestData.getSendersName()));
			json.put("SendersPhoneNo", new String(hazardousManifestData.getSendersPhoneNo()));

			json.put("TransportRegNo", new String(hazardousManifestData.getTransporterRegNo()));
			json.put("TransportVehicleRegNo", new String(hazardousManifestData.getTransporterVehicleRegNo()));
			json.put("TransportAddress", new String(hazardousManifestData.getTransporterAddress()));
			json.put("TransportsName", new String(hazardousManifestData.getTransporterName()));
			json.put("TransportPhoneNo", new String(hazardousManifestData.getTransportermobilepNo()));
			json.put("VehicleType", new String(hazardousManifestData.getVehicleType()));
			json.put("ManifestDocumentNo", new String(hazardousManifestData.getManifestDocumentsNo()));

			json.put("TransDescWeast", new String(hazardousManifestData.getTransportDescWaste()));
			json.put("TotalQuantity", new String(hazardousManifestData.getTotQuantityContainer()));
			json.put("TotalQuantityUnit", new String(hazardousManifestData.getTotQuantityContainerUnit()));
			json.put("SpecialHandling", new String(hazardousManifestData.getSpecialHandling()));
			json.put("SubDate", new String(hazardousManifestData.getSubmittedDate()));

			List<Containers> allContainer = hazardousManifestData.getContainersList();

			JSONArray jsonPollDataArray1 = new JSONArray();
			for (Containers containers : allContainer)
			{
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("Container", new String(containers.getContainersNumber())); // get StackPollId 1
				jsonObj.put("Type", new String(containers.getContainersType()));
				jsonPollDataArray1.put(jsonObj);
			}
			json.put("container", jsonPollDataArray1);

			List<WasteDescriptionConsistency> objWasteDescription = hazardousManifestData.getWasteDescriptionListByConsistency();

			JSONArray jsonPollDataArray2 = new JSONArray();
			for (WasteDescriptionConsistency wasteDescription : objWasteDescription)
			{
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("wasteName", new String(wasteDescription.getWasteName())); // get StackPollId 1
				jsonObj.put("Quantity", new Float(wasteDescription.getWasteQuantity()));
				jsonObj.put("unit", new String(wasteDescription.getWasteUnits()));
				jsonObj.put("consistency", new String(wasteDescription.getConsistency()));
				jsonPollDataArray2.put(jsonObj);
			}
			json.put("WasteDescription", jsonPollDataArray2);
			FinalArray.put(json);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return FinalArray.toString();

	}

}
