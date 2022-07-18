package com.tes.services.environmentalofficer;

import com.tes.model.EsrWaterPollReasons;

public interface EsrWaterPollReasonsServices
{
	public EsrWaterPollReasons save(EsrWaterPollReasons esrWaterPollReasons);

	public String pollReasonByName(String pollName, String string, String selectedYear);
}
