package com.tes.services.impl.thirdparty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.AmbientPollData;
import com.tes.repository.thirdparty.AmbientPollDataRepository;
import com.tes.services.thirdparty.AmbientPollDataServices;

@Service
public class AmbientPollDataServicesImpl implements AmbientPollDataServices
{

	@Autowired
	AmbientPollDataRepository ambientPollDataRepository;

	@Override
	public AmbientPollData save(AmbientPollData ambientPollData)
	{
		return ambientPollDataRepository.save(ambientPollData);
	}

	@Override
	public List<AmbientPollData> getAmbientPollData(int regAmbientId)
	{
		return ambientPollDataRepository.getAmbientPollData(regAmbientId);
	}

}
