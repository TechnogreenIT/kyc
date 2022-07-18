package com.tes.services.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.IndustryTypeList;
import com.tes.repository.admin.IndustryTypeListRepository;
import com.tes.services.admin.IndustryTypeListServices;

@Service
public class IndustryTypeListServicesImpl implements IndustryTypeListServices
{

	@Autowired
	IndustryTypeListRepository industrytypelistrepo;

	@Override
	public List<IndustryTypeList> findAll()
	{
		return industrytypelistrepo.findAll();
	}

	@Override
	public IndustryTypeList save(IndustryTypeList industry_type_list)
	{
		return industrytypelistrepo.save(industry_type_list);
	}

	@Override
	public IndustryTypeList findOneByindustryTypeId(Integer id)
	{
		return industrytypelistrepo.findOneByindustryTypeId(id);
	}

}
