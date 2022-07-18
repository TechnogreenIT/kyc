package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.EsrInvestment;
import com.tes.repository.environmentalofficer.EsrInvestmentRepository;
import com.tes.services.environmentalofficer.EsrInvestmentServices;

@Service
public class EsrInvestmentServicesImpl implements EsrInvestmentServices
{

	@Autowired
	EsrInvestmentRepository esrInvestmentRepository;

	@Override
	public EsrInvestment save(EsrInvestment esrInvestment)
	{
		return esrInvestmentRepository.saveAndFlush(esrInvestment);
	}

	@Override
	public List<EsrInvestment> findOneByGetEsrInvestmentByYear(String year)
	{
		return esrInvestmentRepository.findOneByGetEsrInvestmentByYear(year);
	}

	@Override
	public List<EsrInvestment> findOneByGetEsrInvestmentByNextYear(String year, String year1)
	{
		return esrInvestmentRepository.findOneByGetEsrInvestmentByNextYear(year, year1);
	}

	@Override
	public List<EsrInvestment> findOneByGetEsrInvestmentByMonth(String yearWithMonth, String year)
	{
		return esrInvestmentRepository.findOneByGetEsrInvestmentByMonth(yearWithMonth, year);
	}

	@Override
	public List<EsrInvestment> findOneByGetEsrInvestmentByNextYearMonth(String yearWithMonth, String nextYear)
	{
		return esrInvestmentRepository.findOneByGetEsrInvestmentByNextYearMonth(yearWithMonth, nextYear);
	}

}
