package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import com.tes.model.WaterSource;

public interface WaterSourceServices
{

	public WaterSource save(WaterSource objWaterSource);

	public List<WaterSource> getWaterSourceData(int wiId);

	public int findWaterSourceIdByName(String sourceName);

	public List<String> getAllWaterSourceData();

	/*
	 * List<String> getAllWaterSourceData();
	 * List<String> getWaterSourceName();
	 * String getMetervalue(String sourceName);
	 * public List<String> getSourceNameByConsentValidUpto(String todaysDate);
	 * public List<String> getSourceNameByWaterInvId(int waterInvId);
	 * public int getnumberofMeter();
	 */

	List<String> getAllWaterSourceName();

	public List<String> getSourceNameByConsentValidUpto(String todaysDate);

	public boolean getMetervalue(String sourceName);
}
