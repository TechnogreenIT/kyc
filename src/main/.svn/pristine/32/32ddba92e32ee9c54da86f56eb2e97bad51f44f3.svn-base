package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.EsrParticulars;
import com.tes.repository.environmentalofficer.EsrParticularsRepository;
import com.tes.services.environmentalofficer.EsrParticularsServices;

@Service
public class EsrParticularsServicesImpl implements EsrParticularsServices
{

	@Autowired
	EsrParticularsRepository esrParticularsRepository;

	@Override
	public EsrParticulars save(EsrParticulars esrParticulars)
	{
		return esrParticularsRepository.saveAndFlush(esrParticulars);
	}

	@Override
	public List<Object[]> findOneByGetParticularsByYear(String year)
	{
		return esrParticularsRepository.findOneByGetParticularsByYear(year);
	}

	@Override
	public List<EsrParticulars> getEsrParticularsbyyear(String year)
	{
		return esrParticularsRepository.getEsrParticularsbyyear(year);
	}

	@Override
	public List<EsrParticulars> getEsrParticularsbyyearMonth(String year, String month1)
	{
		return esrParticularsRepository.getEsrParticularsbyyearMonth(year, month1);
	}

}
