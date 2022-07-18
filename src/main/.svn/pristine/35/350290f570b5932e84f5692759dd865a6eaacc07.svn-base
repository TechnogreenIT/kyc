package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.repository.thirdparty.RegEffPollRepository;
import com.tes.repository.thirdparty.RegSewPollRepository;
import com.tes.services.environmentalofficer.WaterServices;

@Service
public class WaterServicesImpl implements WaterServices
{

	@Autowired
	RegEffPollRepository regEffPollRepository;

	@Autowired
	RegSewPollRepository regSewPollRepository;

	@Override
	public RegEffPoll save(RegEffPoll regEffPoll)
	{
		return regEffPollRepository.save(regEffPoll);
	}

	@Override
	public RegSewPoll save(RegSewPoll regSewPoll)
	{
		return regSewPollRepository.save(regSewPoll);
	}

}
