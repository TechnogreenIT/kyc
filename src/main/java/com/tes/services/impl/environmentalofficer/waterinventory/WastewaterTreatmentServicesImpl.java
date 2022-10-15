package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.WastewaterTreatment;
import com.tes.repository.environmentalofficer.waterinventory.WastewaterTreatmentRepository;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;

@Service
public class WastewaterTreatmentServicesImpl implements WastewaterTreatmentServices
{

	@Autowired
	WastewaterTreatmentRepository waterTreatmentRepository;

	@Override
	public WastewaterTreatment save(WastewaterTreatment wastewaterTreatment)
	{
		return waterTreatmentRepository.save(wastewaterTreatment);
	}

	@Override
	public List<WastewaterTreatment> getAllWasteWaterTreatmentData(int waterInventoryId)
	{
		return waterTreatmentRepository.getAllWasteWaterTreatmentData(waterInventoryId);
	}

	@Override
	public List<String> findAlltreatmentTypeLabel()
	{
		return waterTreatmentRepository.findAlltreatmentTypeLabel();
	}

	@Override
	public List<WastewaterTreatment> getWaterTreatmentDataByType(String treatmentType)
	{
		return waterTreatmentRepository.getWaterTreatmentDataByType(treatmentType);
	}

	@Override
	public List<WastewaterTreatment> findAll()
	{
		return waterTreatmentRepository.findAll();
	}

	@Override
	public List<WastewaterTreatment> treatmentType(String date, String type)
	{
		return waterTreatmentRepository.treatmentType(date, type);
	}

	@Override
	public List<String> treatmentTypeBywiId(int wiId)
	{
		return waterTreatmentRepository.treatmentTypeBywiId(wiId);
	}

	@Override
	public int getWaterTreatmentType(String treatmentType)
	{
		return waterTreatmentRepository.getWaterTreatmentType(treatmentType);
	}

	@Override
	public List<String> getTreatmentTypeByWiId(int waterInv)
	{
		return waterTreatmentRepository.getTreatmentTypeByWiId(waterInv);
	}

	@Override
	public int checkWaterTreatmentByTreatmentType(String treatmentType)
	{
		return waterTreatmentRepository.checkWaterTreatmentByTreatmentType(treatmentType);
	}

	@Override
	public Float sumOfcapacityByWIdTreatmentType(int waterInvId, String treatmentType)
	{
		return waterTreatmentRepository.sumOfcapacityByWIdTreatmentType(waterInvId, treatmentType);
	}

	@Override
	public int generateLabelBytrtmentType(String plantType)
	{
		return waterTreatmentRepository.generateLabelBytrtmentType(plantType);
	}

	@Override
	public List<WastewaterTreatment> getAllWasteWaterTreatmentData()
	{
		return waterTreatmentRepository.getAllWasteWaterTreatmentData();
	}

	@Override
	public List<String> findAlltreatmentTypeLabel(String treatType)
	{
		return waterTreatmentRepository.findAlltreatmentTypeLabel(treatType);
	}

	@Override
	public int findCapacityByLabel(String label)
	{
		return waterTreatmentRepository.findCapacityByLabel(label);
	}

	@Override
	public List<WastewaterTreatment> getAllLabelAndId()
	{
		return waterTreatmentRepository.getAllLabelAndId();
	}

	@Override
	public List<RegEffPoll> getlabelListBydate(String date)
	{
		return waterTreatmentRepository.getlabelListBydate(date);
	}

	@Override
	public List<WastewaterTreatment> getTreatmentType(int waterInventoryId, String treatemetType)
	{
		return waterTreatmentRepository.getTreatmentType(waterInventoryId, treatemetType);
	}

	@Override
	public List<RegSewPoll> getSewlabelListBydate(String pdata) {
		return waterTreatmentRepository.getSewlabelListBydate(pdata);
	}

//	@Override
//	public List<RegSewPoll> getSewlabelListBydate(String pdata) {
//		// TODO Auto-generated method stub
//		return waterTreatmentRepository.getSewlabelListBydate(pdata);
	//}

	/*
	 * @Override public Float getQuantityByWIdTypeNameTypeRecycledTo(int waterInvId,
	 * String treatmentType,String recyledTo) { return
	 * waterTreatmentRepository.getQuantityByWIdTypeNameTypeRecycledTo(waterInvId,
	 * treatmentType, recyledTo); }
	 * @Override public List<String> getRecyclerToByWIdTreatmentTypeAndName(int
	 * waterInvId, String treatmentType) { return
	 * waterTreatmentRepository.getRecyclerToByWIdTreatmentTypeAndName(waterInvId,
	 * treatmentType); }
	 * @Override public List<WastewaterTreatment> typeNameBywiId(int wiId) { return
	 * waterTreatmentRepository.typeNameBywiId(wiId); }
	 * @Override
	 * public List<String> getTreatmentTypeNameFromWiId(int waterInv) {
	 * return waterTreatmentRepository.getTreatmentTypeNameFromWiId(waterInv);
	 * }
	 */
}
