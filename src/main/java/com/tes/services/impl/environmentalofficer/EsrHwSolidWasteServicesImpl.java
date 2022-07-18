package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.EsrHwSolidWaste;
import com.tes.repository.environmentalofficer.EsrHwSolidWasteRepository;
import com.tes.services.environmentalofficer.EsrHwSolidWasteServices;

@Service
public class EsrHwSolidWasteServicesImpl implements EsrHwSolidWasteServices
{

	@Autowired
	EsrHwSolidWasteRepository esrHwSolidWasteRepository;

	@Override
	public EsrHwSolidWaste save(EsrHwSolidWaste esrHwSolidWaste)
	{
		return esrHwSolidWasteRepository.saveAndFlush(esrHwSolidWaste);
	}

	@Override
	public List<EsrHwSolidWaste> findAll()
	{
		return esrHwSolidWasteRepository.findAll();
	}

	@Override
	public List<Object[]> findOneByGetHazardousWasteByYear(String yearTo, String type)
	{
		return esrHwSolidWasteRepository.findOneByGetHazardousWasteByYear(yearTo, type);
	}

	@Override
	public List<EsrHwSolidWaste> getEsrHwSolidWasteDataByHazYear(String year)
	{
		return esrHwSolidWasteRepository.getEsrHwSolidWasteDataByHazYear(year);
	}

	@Override
	public List<EsrHwSolidWaste> getEsrHwSolidWasteDataBySolidYear(String year)
	{
		return esrHwSolidWasteRepository.getEsrHwSolidWasteDataBySolidYear(year);
	}

	@Override
	public List<EsrHwSolidWaste> getAllDataByTypeMonth(String esrWholeYear, String type)
	{
		// TODO Auto-generated method stub
		return esrHwSolidWasteRepository.getAllDataByTypeMonth(esrWholeYear, type);
	}
}
