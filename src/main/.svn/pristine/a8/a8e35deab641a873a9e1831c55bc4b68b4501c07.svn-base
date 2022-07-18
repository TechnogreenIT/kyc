package com.tes.services.environmentalofficer;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tes.model.RegPollReasons;

public interface RegPollReasonsServices
{

	public RegPollReasons save(RegPollReasons regPollReasons);

	List<String> getRegReasonByNametypeAndYear(String pollName, String pollType, String dateFrom, String dateTo, Pageable pageable);

	List<String> getRegReasonByNametypeAndYearMonth(String pollName, String pollType, String dateFrom, String dateTe, String month, Pageable pageable);
}
