package com.tes.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.WaterSourceNames;
import com.tes.repository.WaterSourceNamesRepository;
import com.tes.services.WaterSourceNamesServices;

@Service
public class WaterSourceNamesImpl implements WaterSourceNamesServices
{

	@Autowired
	WaterSourceNamesRepository waterSourceNamesRepository;

	@Override
	public List<WaterSourceNames> findAll()
	{
		return waterSourceNamesRepository.findAll();
	}
}
