package com.tes.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tes.model.Containers;
import com.tes.model.HazardousManifest;
import com.tes.model.WasteDescriptionConsistency;
import com.tes.services.environmentalofficer.ContainersServices;
import com.tes.services.environmentalofficer.HazardousManifestServices;
import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;

@RestController
@RequestMapping("/rest/hazardous-manifest")
public class HazardousManifestRestController
{

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	HazardousManifestServices hazardousManifestServices;

	@Autowired
	WasteDescriptionConsistencyServices wasteDescriptionConsistencyServices;

	@Autowired
	ContainersServices containersServices;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private String setManifestData(@RequestBody JsonArray manifestDataList)
	{
		try
		{
			for (JsonElement manifest : manifestDataList)
			{
				JsonObject jsonObject = manifest.getAsJsonObject();
				HazardousManifest objhazardousManifest = new HazardousManifest();
				objhazardousManifest.setSendersName(manifest.getAsJsonObject().get("sendersName").getAsString());
				objhazardousManifest.setSendersMailingAddress(manifest.getAsJsonObject().get("sendersMailingAddress").getAsString());
				objhazardousManifest.setSendersPhoneNo(manifest.getAsJsonObject().get("sendersPhoneNo").getAsString());
				objhazardousManifest.setSendersAuthorizationNo(manifest.getAsJsonObject().get("sendersAuthorizationNo").getAsString());
				objhazardousManifest.setManifestDocumentsNo(manifest.getAsJsonObject().get("manifestDocumentsNo").getAsString());
				objhazardousManifest.setTransporterName(manifest.getAsJsonObject().get("transporterName").getAsString());
				objhazardousManifest.setTransporterAddress(manifest.getAsJsonObject().get("transporterAddress").getAsString());
				objhazardousManifest.setTransportermobilepNo(manifest.getAsJsonObject().get("transportersPhoneNo").getAsString());
				objhazardousManifest.setVehicleType(manifest.getAsJsonObject().get("vehicleType").getAsString());
				objhazardousManifest.setTransporterRegNo(manifest.getAsJsonObject().get("transporterRegNo").getAsString());
				objhazardousManifest.setTransporterVehicleRegNo(manifest.getAsJsonObject().get("transporterVehicleRegNo").getAsString());
				objhazardousManifest.setReceiversName(manifest.getAsJsonObject().get("receiversName").getAsString());
				objhazardousManifest.setReceiversAddress(manifest.getAsJsonObject().get("receiversAddress").getAsString());
				objhazardousManifest.setReceiversAuthorizationNo(manifest.getAsJsonObject().get("receiversAuthorizationNo").getAsString());
				objhazardousManifest.setReceiversPhoneNo(manifest.getAsJsonObject().get("receiversPhoneNo").getAsString());
				objhazardousManifest.setTransportDescWaste(manifest.getAsJsonObject().get("transportDescWaste").getAsString());
				objhazardousManifest.setDispatchedTo(manifest.getAsJsonObject().get("dispatchedTo").getAsString());
				objhazardousManifest.setSpecialHandling(manifest.getAsJsonObject().get("specialHandling").getAsString());
				objhazardousManifest.setSubmittedDate(Utilities.getTodaysDate());
				hazardousManifestServices.save(objhazardousManifest);
				JsonArray wasteDescriptionDataList = jsonObject.getAsJsonArray("wasteDescriptionData");
				for (JsonElement wasteDescription : wasteDescriptionDataList)
				{
					WasteDescriptionConsistency objwasteDescriptionConsistency = new WasteDescriptionConsistency();
					objwasteDescriptionConsistency.setHazardousManifest(objhazardousManifest);
					objwasteDescriptionConsistency.setConsistency((wasteDescription.getAsJsonObject().get("consistency").getAsString()));
					objwasteDescriptionConsistency.setDescription((wasteDescription.getAsJsonObject().get("description").getAsString()));
					objwasteDescriptionConsistency.setQuantityUtilized((wasteDescription.getAsJsonObject().get("quantityUtilized").getAsFloat()));
					objwasteDescriptionConsistency.setWasteName(wasteDescription.getAsJsonObject().get("wasteName").getAsString());
					objwasteDescriptionConsistency.setWasteUnits(wasteDescription.getAsJsonObject().get("wasteUnits").getAsString());
					objwasteDescriptionConsistency.setWasteCategoryNumber(wasteDescription.getAsJsonObject().get("categoryNo").getAsString());
					wasteDescriptionConsistencyServices.save(objwasteDescriptionConsistency);
				}
				JsonArray containersDataList = jsonObject.getAsJsonArray("containersData");
				for (JsonElement containers : containersDataList)
				{
					Containers objcontainers = new Containers();
					objcontainers.setContainersNumber(containers.getAsJsonObject().get("containersNumber").getAsString());
					objcontainers.setContainersType(containers.getAsJsonObject().get("containersType").getAsString());
					objcontainers.setHazardousManifest(objhazardousManifest);
					containersServices.save(objcontainers);
				}
			}
			return Constant.SUCCESS;
		}
		catch (Exception e)
		{
			return Constant.FAILURE;
		}
	}
}
