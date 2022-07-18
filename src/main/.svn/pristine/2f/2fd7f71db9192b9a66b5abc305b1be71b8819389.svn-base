package com.tes.services.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.IndustryCategory;
import com.tes.repository.admin.IndustryCategoryRepository;
import com.tes.services.admin.IndustryCategoryServices;

@Service
public class IndustryCategoryServicesImpl implements IndustryCategoryServices
{
	@Autowired
	IndustryCategoryRepository industrycategoryrepo;

	@Override
	public List<IndustryCategory> findAll()
	{

		return industrycategoryrepo.findAll();
	}

	@Override
	public IndustryCategory save(IndustryCategory industry_category)
	{

		return industrycategoryrepo.save(industry_category);
	}

}
