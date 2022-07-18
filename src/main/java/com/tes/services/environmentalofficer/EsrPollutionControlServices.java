package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.EsrPollutionControl;

public interface EsrPollutionControlServices
{

	public EsrPollutionControl save(EsrPollutionControl esrPollutionControl);

	List<EsrPollutionControl> findOneByEsrPollutionByYear(String yearTo);

	List<EsrPollutionControl> findOneByEsrPollutionByMonth(String yearToe, String month1);

}
