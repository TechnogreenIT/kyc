package com.tes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.HazardousWastes;
import com.tes.repository.HazardousWastesRepository;
import com.tes.services.HazardousWastesServices;

@Service
public class HazardousWastesServicesImpl implements HazardousWastesServices
{

	@Autowired
	HazardousWastesRepository hazardousWastesRepository;

	@Override
	public List<HazardousWastes> findAll()
	{
		return hazardousWastesRepository.findAll();
	}

	@Override
	public String getGategoryTypeByCatNumber(String categoryNumber)
	{
		return hazardousWastesRepository.getGategoryTypeByCatNumber(categoryNumber);
	}

	@Override
	public String getHzNumberByCatDesc(String categoryDesc)
	{
		return hazardousWastesRepository.getHzNumberByCatDesc(categoryDesc);
	}

}
