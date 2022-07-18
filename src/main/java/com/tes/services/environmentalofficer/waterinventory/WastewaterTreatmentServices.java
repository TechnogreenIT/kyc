package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import com.tes.model.RegEffPoll;
import com.tes.model.WastewaterTreatment;

public interface WastewaterTreatmentServices
{

	WastewaterTreatment save(WastewaterTreatment wastewaterTreatment);

	List<WastewaterTreatment> getAllWasteWaterTreatmentData(int waterInventoryId);

	List<WastewaterTreatment> treatmentType(String date, String type);

	List<String> treatmentTypeBywiId(int wiId);

	int getWaterTreatmentType(String treatmentType);

	List<String> getTreatmentTypeByWiId(int waterInv);

	int checkWaterTreatmentByTreatmentType(String treatmentType);

	Float sumOfcapacityByWIdTreatmentType(int waterInvId, String treatmentType);

	int generateLabelBytrtmentType(String plantType);

	List<WastewaterTreatment> getWaterTreatmentDataByType(String treatmentType);

	List<String> findAlltreatmentTypeLabel();

	List<String> findAlltreatmentTypeLabel(String treatType);

	List<WastewaterTreatment> findAll();

	List<WastewaterTreatment> getAllWasteWaterTreatmentData();

	int findCapacityByLabel(String label);

	List<WastewaterTreatment> getAllLabelAndId();

	List<RegEffPoll> getlabelListBydate(String date);

	List<WastewaterTreatment> getTreatmentType(int waterInventoryId, String treatemetType);

	// Float getQuantityByWIdTypeNameTypeRecycledTo(int waterInvId,String treatmentType,String recyledTo);
	// List<String> getRecyclerToByWIdTreatmentTypeAndName(int waterInvId, String treatmentType);
	// List<WastewaterTreatment> typeNameBywiId(int wiId);
	// List<String> getTreatmentTypeNameFromWiId(int waterInv);
}
