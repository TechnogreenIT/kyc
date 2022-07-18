package com.tes.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.FilterNameList;
import com.tes.repository.FilterNameListRepository;
import com.tes.services.FilterNameListServices;

@Service
public class FilterNameListImpl implements FilterNameListServices
{

	@Autowired
	FilterNameListRepository filterNameListRepository;

	@Override
	public List<FilterNameList> findAll()
	{
		return filterNameListRepository.findAll();
	}

	@Override
	public boolean getFilterIdByName(String filterName)
	{
		return filterNameListRepository.getFilterIdByName(filterName);
	}
}
