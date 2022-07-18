package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tes.model.RegPollReasons;
import com.tes.repository.environmentalofficer.RegPollReasonsRepository;
import com.tes.services.environmentalofficer.RegPollReasonsServices;

@Service
public class RegPollReasonsServicesImpl implements RegPollReasonsServices
{

	@Autowired
	RegPollReasonsRepository regPollReasonsRepository;

	@Override
	public RegPollReasons save(RegPollReasons regPollReasons)
	{
		return regPollReasonsRepository.save(regPollReasons);
	}

	@Override
	public List<String> getRegReasonByNametypeAndYear(String pollName, String pollType, String dateFrom, String dateTo, Pageable pageable)
	{
		return regPollReasonsRepository.getRegReasonByNametypeAndYear(pollName, pollType, dateFrom, dateTo, pageable);
	}

	@Override
	public List<String> getRegReasonByNametypeAndYearMonth(String pollName, String pollType, String dateFrom,
			String dateTo, String month, Pageable pageable)
	{
		return regPollReasonsRepository.getRegReasonByNametypeAndYearMonth(pollName, pollType, dateFrom, dateTo, month, pageable);
	}

}
