package com.tes.services.admin;

import java.util.List;

import com.tes.model.IndustryCategory;

public interface IndustryCategoryServices
{
	public List<IndustryCategory> findAll();

	public IndustryCategory save(IndustryCategory industry_category);
}
