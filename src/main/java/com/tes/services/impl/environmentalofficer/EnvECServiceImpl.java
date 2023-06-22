package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.EnvEC;
import com.tes.repository.environmentalofficer.EnvECRepository;
import com.tes.services.environmentalofficer.EnvECServices;

@Service
public class EnvECServiceImpl implements EnvECServices{

	@Autowired
	EnvECRepository envECRepository;

	
	
	@Override
	public EnvEC save(EnvEC envEC) {
		// TODO Auto-generated method stub
		return envECRepository.save(envEC);
	}



}
