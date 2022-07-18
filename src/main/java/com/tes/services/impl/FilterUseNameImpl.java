package com.tes.services.impl;

import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.FilterUseNames;
import com.tes.repository.FilterUseNameRepository;
import com.tes.services.FilterUseNameServices;

@Service
public class FilterUseNameImpl implements FilterUseNameServices
{

	@Autowired
	FilterUseNameRepository filterUseNameRepository;

	@Override
	public List<FilterUseNames> findAll()
	{
		return filterUseNameRepository.findAll();
	}

	// Affected by filter .....by vishal
	// @Override
	// public List<String> findFiltersUseByFilterName(String filterName)
	// {
	// return filterUseNameRepository.findFiltersUseByFilterName(filterName);
	// }

}
