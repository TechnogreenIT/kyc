package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.WasteDescriptionConsistency;
import com.tes.repository.environmentalofficer.WasteDescriptionConsistencyRepository;
import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;

@Service
public class WasteDescriptionConsistencyServicesImpl implements WasteDescriptionConsistencyServices
{

	@Autowired
	WasteDescriptionConsistencyRepository wasteDescriptionConsistencyRepository;

	@Override
	public WasteDescriptionConsistency save(WasteDescriptionConsistency wasteDescriptionConsistency)
	{
		return wasteDescriptionConsistencyRepository.save(wasteDescriptionConsistency);
	}

	@Override
	public List<WasteDescriptionConsistency> wdDataBywasteDesc(int hmId)
	{
		return wasteDescriptionConsistencyRepository.wdDataBywasteDesc(hmId);
	}

	@Override
	public List<WasteDescriptionConsistency> wdDataByConsistency(int hmId)
	{
		return wasteDescriptionConsistencyRepository.wdDataByConsistency(hmId);
	}

	@Override
	public List<WasteDescriptionConsistency> getWasteDescriptionByDate(String from, String to)
	{
		return wasteDescriptionConsistencyRepository.getWasteDescriptionByDate(from, to);
	}

	@Override
	public float getSumWasteQuantityByWasteName(String fromDate, String toDate, String productName, String categoryNo)
	{
		return wasteDescriptionConsistencyRepository.getSumWasteQuantityByWasteName(fromDate, toDate, productName, categoryNo);
	}

	@Override
	public List<String> getInHouseWasteNameBetweenDate(String fromDate, String toDate)
	{
		return wasteDescriptionConsistencyRepository.getInHouseWasteNameBetweenDate(fromDate, toDate);
	}

	@Override
	public List<WasteDescriptionConsistency> getHazardousWasteNameBetweenDate(String fromDate, String toDate)
	{
		return wasteDescriptionConsistencyRepository.getHazardousWasteNameBetweenDate(fromDate, toDate);
	}

}
