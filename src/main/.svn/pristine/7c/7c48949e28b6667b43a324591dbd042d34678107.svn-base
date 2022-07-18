package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.EsrPollutionControl;
import com.tes.repository.environmentalofficer.EsrPollutionControlRepository;
import com.tes.services.environmentalofficer.EsrPollutionControlServices;

@Service
public class EsrPollutionControlServicesImpl implements EsrPollutionControlServices
{

	@Autowired
	EsrPollutionControlRepository esrPollutionControlRepository;

	@Override
	public EsrPollutionControl save(EsrPollutionControl esrPollutionControl)
	{
		return esrPollutionControlRepository.saveAndFlush(esrPollutionControl);
	}

	@Override
	public List<EsrPollutionControl> findOneByEsrPollutionByYear(String yearTo)
	{
		return esrPollutionControlRepository.findOneByEsrPollutionByYear(yearTo);
	}

	@Override
	public List<EsrPollutionControl> findOneByEsrPollutionByMonth(String yearToe, String month1)
	{
		return esrPollutionControlRepository.findOneByEsrPollutionByMonth(yearToe, month1);
	}

}
