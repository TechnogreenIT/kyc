package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.WaterConGenComparativeSheet;
import com.tes.repository.environmentalofficer.WaterConGenComparativeSheetRepository;
import com.tes.services.environmentalofficer.WaterConGenComparativeSheetServices;

@Service
public class WaterConGenComparativeSheetServicesImpl implements WaterConGenComparativeSheetServices
{

	@Autowired
	WaterConGenComparativeSheetRepository waterConGenComparativeSheetRepository;

	@Override
	public WaterConGenComparativeSheet save(WaterConGenComparativeSheet objWaterConGenComparativeSheet)
	{
		return waterConGenComparativeSheetRepository.save(objWaterConGenComparativeSheet);
	}

	@Override
	public int setStatusInactiveToAll(byte status)
	{
		return waterConGenComparativeSheetRepository.setStatusInactiveToAll(status);
	}

	@Override
	public float getConsByStatusIsActiveAndWaterConGenParameterId(int waterConGenParameterId)
	{
		return waterConGenComparativeSheetRepository.getConsByStatusIsActiveAndWaterConGenParameterId(waterConGenParameterId);
	}

	@Override
	public float getGenByStatusIsActiveAndWaterConGenParameterId(int waterConGenParameterId)
	{
		return waterConGenComparativeSheetRepository.getGenByStatusIsActiveAndWaterConGenParameterId(waterConGenParameterId);
	}

	@Override
	public Float getSumOfProcessCons(String name, String today)
	{
		return waterConGenComparativeSheetRepository.getSumOfProcessCons(name, today);
	}

	@Override
	public Float getSumOfProcessGen(String name, String today)
	{
		return waterConGenComparativeSheetRepository.getSumOfProcessGen(name, today);
	}

	@Override
	public Float getcons()
	{
		return waterConGenComparativeSheetRepository.getcons();
	}

	@Override
	public Float getSumOfProcessGenMonth(String name, int consentId)
	{
		return waterConGenComparativeSheetRepository.getSumOfProcessGenMonth(name, consentId);
	}

	@Override
	public Float getSumOfProcessConMonth(String name, int consentId)
	{
		return waterConGenComparativeSheetRepository.getSumOfProcessConMonth(name, consentId);
	}

}
