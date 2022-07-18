package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.tes.model.WastewaterRecycle;

public interface WasteWaterRecycleSevices
{

	void save(WastewaterRecycle wastewaterRecycle);

	List<WastewaterRecycle> getWasteWaterRecycle(int wastewaterTreatmentId);

	List<String> findAllRecycleTypeLabel();

	List<String> findAllRecycleTypeLabel(String label);

	List<WastewaterRecycle> findAllWasteWaterRecycle();

	public boolean getMetervalueOfRecycledTo(String treatType, String recycledTo);

	// List<WastewaterRecycle> getAllLabelAndRecycleTo();
	List<WastewaterRecycle> findAllWasteWaterRecycleBytreatLabel(String treatType, String treatLabel);

	List<WastewaterRecycle> findAllRecycleIdAndLabel(String label);
	/*
	 * public WaterSource save(WaterSource objWaterSource);
	 * public List<WaterSource> getWaterSourceData(int wiId);
	 * List<String> getAllWaterSourceData();
	 * List<String> getWaterSourceName();
	 * String getMetervalue(String sourceName);
	 * public List<String> getSourceNameByConsentValidUpto(String todaysDate);
	 * public List<String> getSourceNameByWaterInvId(int waterInvId);
	 * public int getnumberofMeter();
	 * List<String> getAllWaterSourceName();
	 * public List<String> getSourceNameByConsentValidUpto(String todaysDate);
	 */
}
