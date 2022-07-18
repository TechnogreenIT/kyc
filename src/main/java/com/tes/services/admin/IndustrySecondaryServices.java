package com.tes.services.admin;

import java.util.List;

import com.tes.model.IndustrySecondary;

public interface IndustrySecondaryServices
{
	public List<IndustrySecondary> findAll();

	public IndustrySecondary save(IndustrySecondary industry_secondary);

	public List<IndustrySecondary> findSecondary();
}
