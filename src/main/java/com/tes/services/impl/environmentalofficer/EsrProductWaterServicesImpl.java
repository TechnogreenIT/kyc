package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.EsrProductWater;
import com.tes.repository.environmentalofficer.EsrProductWaterRepository;
import com.tes.services.environmentalofficer.EsrProductWaterServices;

@Service
public class EsrProductWaterServicesImpl implements EsrProductWaterServices
{

	@Autowired
	EsrProductWaterRepository esrProductWaterRepository;

	@Override
	public List<EsrProductWater> getAllDataByProNameTypeYear(String year, String productName, String productType)
	{
		return esrProductWaterRepository.getAllDataByProNameTypeYear(year, productName, productType);
	}

	@Override
	public EsrProductWater save(EsrProductWater esrProductWater)
	{
		return esrProductWaterRepository.save(esrProductWater);
	}

	@Override
	public List<EsrProductWater> getEsrProductWaterData(String year, String type)
	{
		return esrProductWaterRepository.getEsrProductWaterData(year, type);
	}

	@Override
	public List<EsrProductWater> getEsrProductWaterDataMonthly(String year, String month1, String type)
	{
		return esrProductWaterRepository.getEsrProductWaterDataMonthly(year, month1, type);
	}

	@Override
	public List<EsrProductWater> getAllDataByYear(String esrWholeYear, String type)
	{
		return esrProductWaterRepository.getAllDataByYear(esrWholeYear, type);
	}

}
