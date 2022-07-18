package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.EsrStackPollReasons;
import com.tes.repository.environmentalofficer.EsrStackPollReasonsRepository;
import com.tes.services.environmentalofficer.EsrStackPollReasonsServices;

@Service
public class EsrStackPollReasonsServicesImpl implements EsrStackPollReasonsServices
{
	@Autowired
	EsrStackPollReasonsRepository esrStackPollReasonsRepository;

	@Override
	public String getReasonByDate(String mainName, String rsdate)
	{

		return esrStackPollReasonsRepository.getReasonByDate(mainName, rsdate);
	}

	@Override
	public EsrStackPollReasons save(EsrStackPollReasons esrpollStackreason)
	{
		return esrStackPollReasonsRepository.save(esrpollStackreason);
	}

}
