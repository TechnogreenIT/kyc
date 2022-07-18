package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.EsrWaterPollReasons;
import com.tes.repository.environmentalofficer.EsrWaterPollReasonsRepository;
import com.tes.services.environmentalofficer.EsrWaterPollReasonsServices;

@Service
public class EsrWaterPollReasonsServicesImpl implements EsrWaterPollReasonsServices
{

	@Autowired
	EsrWaterPollReasonsRepository esrWaterPollReasonRepository;

	@Override
	public EsrWaterPollReasons save(EsrWaterPollReasons esrWaterPollReasons)
	{
		return esrWaterPollReasonRepository.save(esrWaterPollReasons);
	}

	@Override
	public String pollReasonByName(String pollName, String type, String selectedYear)
	{
		return esrWaterPollReasonRepository.pollReasonByName(pollName, type, selectedYear);
	}
}
