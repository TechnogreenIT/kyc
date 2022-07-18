package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.WaterConGen;
import com.tes.repository.environmentalofficer.WaterConGenRepository;
import com.tes.services.environmentalofficer.WaterConGenServices;

@Service
public class WaterConGenServicesImpl implements WaterConGenServices
{

	@Autowired
	WaterConGenRepository waterConGenRepository;

	@Override
	public WaterConGen save(WaterConGen waterConGen)
	{
		return waterConGenRepository.save(waterConGen);
	}

	@Override
	public Float getConsByCtEAndWaterConGenParameterId(String todaysDate, int waterConGenParameterId)
	{
		return waterConGenRepository.getConsByCtEAndWaterConGenParameterId(todaysDate, waterConGenParameterId);
	}

	@Override
	public Float getGenByCtEAndWaterConGenParameterId(String todaysDate, int waterConGenParameterId)
	{
		return waterConGenRepository.getGenByCtEAndWaterConGenParameterId(todaysDate, waterConGenParameterId);
	}

	@Override
	public float sumOfConsByWaterConGenParameterIdAndCtO(String todaysDate, int waterConGenParameterId)
	{
		return waterConGenRepository.sumOfConsByWaterConGenParameterIdAndCtO(todaysDate, waterConGenParameterId);
	}

	@Override
	public float sumOfGenByWaterConGenParameterIdAndCtO(String todaysDate, int waterConGenParameterId)
	{
		return waterConGenRepository.sumOfGenByWaterConGenParameterIdAndCtO(todaysDate, waterConGenParameterId);
	}

	@Override
	public float getConsByWaterConGenParameterIdAndconsentId(int consentId, int waterConGenParameterId)
	{
		return waterConGenRepository.getConsByWaterConGenParameterIdAndconsentId(consentId, waterConGenParameterId);
	}

	@Override
	public float getGenByWaterConGenParameterIdAndconsentId(int consentId, int waterConGenParameterId)
	{
		return waterConGenRepository.getGenByWaterConGenParameterIdAndconsentId(consentId, waterConGenParameterId);
	}

}
