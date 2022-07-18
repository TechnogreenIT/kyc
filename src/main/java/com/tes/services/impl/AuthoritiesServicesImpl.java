package com.tes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Authorities;
import com.tes.repository.AuthoritiesRepository;
import com.tes.services.AuthoritiesServices;

@Service
public class AuthoritiesServicesImpl implements AuthoritiesServices
{

	@Autowired
	AuthoritiesRepository authoritiesRepository;

	@Override
	public Authorities save(Authorities authorities)
	{
		return authoritiesRepository.save(authorities);
	}

}
