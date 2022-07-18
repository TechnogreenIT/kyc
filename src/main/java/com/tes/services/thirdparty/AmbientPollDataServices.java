package com.tes.services.thirdparty;

import java.util.List;

import com.tes.model.AmbientPollData;

public interface AmbientPollDataServices
{

	AmbientPollData save(AmbientPollData ambientPollData);

	List<AmbientPollData> getAmbientPollData(int regAmbientId);
}
