package com.tes.services.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.IndustryPrimary;
import com.tes.repository.admin.IndustryPrimaryRepository;
import com.tes.services.admin.IndustryPrimaryServices;

@Service
public class IndustryPrimaryServicesImpl implements IndustryPrimaryServices
{
	@Autowired
	IndustryPrimaryRepository industryPrimaryrepo;

	@Override
	public List<IndustryPrimary> findAll()
	{

		return industryPrimaryrepo.findAll();
	}

	@Override
	public IndustryPrimary save(IndustryPrimary industry_primary)
	{

		return industryPrimaryrepo.save(industry_primary);
	}

	@Override
	public IndustryPrimary findOneByindustryPrimaryId(Integer id)
	{

		return industryPrimaryrepo.findOneByindustryPrimaryId(id);
	}

	@Override
	public List<IndustryPrimary> findPrimary()
	{

		return industryPrimaryrepo.findPrimary();
	}

}
