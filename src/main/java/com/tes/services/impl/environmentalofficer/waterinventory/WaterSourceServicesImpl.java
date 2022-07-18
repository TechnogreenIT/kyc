package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.WaterSource;
import com.tes.repository.environmentalofficer.waterinventory.WaterSourceRepository;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;

@Service
public class WaterSourceServicesImpl implements WaterSourceServices
{

	@Autowired
	WaterSourceRepository waterSourceRepository;

	@Override
	public WaterSource save(WaterSource objWaterSource)
	{
		return waterSourceRepository.save(objWaterSource);
	}

	@Override
	public List<WaterSource> getWaterSourceData(int wiId)
	{
		return waterSourceRepository.getWaterSourceData(wiId);
	}

	@Override
	public int findWaterSourceIdByName(String sourceName)
	{
		return waterSourceRepository.findWaterSourceIdByName(sourceName);
	}

	/*
	 * @Override public List<WaterSource> getWaterSourceData(int wiId) { return
	 * waterSourceRepository.getWaterSourceData(wiId); }
	 * @Override public List<String> getAllWaterSourceData() { return
	 * waterSourceRepository.getAllWaterSourceData(); }
	 * @Override public List<String> getWaterSourceName() { return
	 * waterSourceRepository.getWaterSourceName(); }
	 * @Override public List<String> getSourceNameByConsentValidUpto(String
	 * todaysDate) { return
	 * waterSourceRepository.getSourceNameByConsentValidUpto(todaysDate); }
	 * @Override public List<String> getSourceNameByWaterInvId(int waterInvId) {
	 * return waterSourceRepository.getSourceNameByWaterInvId(waterInvId); }
	 * @Override public int getnumberofMeter() { return
	 * waterSourceRepository.getnumberofMeter(); }
	 * @Override public String getMetervalue(String sourceName) { return
	 * waterSourceRepository.getMetervalue(sourceName); }
	 */

	@Override
	public List<String> getAllWaterSourceName()
	{
		return waterSourceRepository.getAllWaterSourceName();
	}

	@Override
	public List<String> getSourceNameByConsentValidUpto(String todaysDate)
	{
		return waterSourceRepository.getSourceNameByConsentValidUpto(todaysDate);
	}

	@Override
	public boolean getMetervalue(String sourceName)
	{

		return waterSourceRepository.getMetervalue(sourceName);
	}

	@Override
	public List<String> getAllWaterSourceData()
	{
		return waterSourceRepository.getAllWaterSourceData();
	}

}
