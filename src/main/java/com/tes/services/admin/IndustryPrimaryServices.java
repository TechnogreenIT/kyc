package com.tes.services.admin;

import java.util.List;

import com.tes.model.IndustryPrimary;

public interface IndustryPrimaryServices
{
	public List<IndustryPrimary> findAll();

	public IndustryPrimary save(IndustryPrimary industry_primary);

	public IndustryPrimary findOneByindustryPrimaryId(Integer id);

	public List<IndustryPrimary> findPrimary();

}
