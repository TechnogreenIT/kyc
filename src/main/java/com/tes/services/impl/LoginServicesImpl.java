package com.tes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Users;
import com.tes.repository.LoginRepository;
import com.tes.services.LoginServices;

@Service
public class LoginServicesImpl implements LoginServices
{

	@Autowired
	LoginRepository loginRepository;

	@Override
	public List<Users> findAll()
	{
		return loginRepository.findAll();
	}
}
