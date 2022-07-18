package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Ambient;
import com.tes.model.AmbientPoll;
import com.tes.repository.environmentalofficer.AmbientPollRepository;
import com.tes.services.environmentalofficer.AmbientPollServices;

@Service
public class AmbientPollServicesImpl implements AmbientPollServices
{

	@Autowired
	AmbientPollRepository ambientPollRepository;

	@Override
	public List<AmbientPoll> findAmbientInfo(Integer ambientId)
	{

		return ambientPollRepository.findAmbientInfo(ambientId);
	}

	@Override
	public List<AmbientPoll> findByAmbientId(int ambientId)
	{
		return ambientPollRepository.findByAmbientId(ambientId);
	}

	@Override
	public List<AmbientPoll> deleteByAmbient(Ambient ambientId)
	{
		return ambientPollRepository.deleteByAmbient(ambientId);
	}

	@Override
	public AmbientPoll save(AmbientPoll ambientPoll)
	{
		return ambientPollRepository.save(ambientPoll);
	}

	@Override
	public List<AmbientPoll> deleteByAmbientPollId(int ambientPollId)
	{
		return ambientPollRepository.deleteByAmbientPollId(ambientPollId);
	}
}
