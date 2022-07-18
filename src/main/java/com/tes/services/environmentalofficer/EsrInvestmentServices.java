package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.EsrInvestment;

public interface EsrInvestmentServices
{

	public EsrInvestment save(EsrInvestment esrInvestment);

	List<EsrInvestment> findOneByGetEsrInvestmentByYear(String invYear);

	List<EsrInvestment> findOneByGetEsrInvestmentByNextYear(String year, String year1);

	List<EsrInvestment> findOneByGetEsrInvestmentByMonth(String yearWithMonth, String year);

	List<EsrInvestment> findOneByGetEsrInvestmentByNextYearMonth(String yearWithMonth, String nextYear);

}
