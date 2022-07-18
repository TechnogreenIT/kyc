package com.tes.services.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.IndustrySecondary;
import com.tes.repository.admin.IndustrySecondaryRepository;
import com.tes.services.admin.IndustrySecondaryServices;

@Service
public class IndustrySecondaryServicesImpl implements IndustrySecondaryServices
{

	@Autowired
	IndustrySecondaryRepository industrysecondaryrepo;

	@Override
	public List<IndustrySecondary> findAll()
	{

		return industrysecondaryrepo.findAll();
	}

	@Override
	public IndustrySecondary save(IndustrySecondary industry_secondary)
	{

		return industrysecondaryrepo.save(industry_secondary);
	}

	@Override
	public List<IndustrySecondary> findSecondary()
	{

		return industrysecondaryrepo.findAll();
	}

}
