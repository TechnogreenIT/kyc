package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tes.model.RegAPC;
import com.tes.repository.environmentalofficer.RegAPCRepository;
import com.tes.services.environmentalofficer.RegAPCServices;

@Service
public class RegAPCServicesImpl implements RegAPCServices
{

	@Autowired
	RegAPCRepository regAPCRepository;

	@Override
	public RegAPC save(RegAPC regAPC)
	{
		return regAPCRepository.save(regAPC);
	}

	@Override
	public List<RegAPC> getRegAPCData(int stackId, Pageable pageable)
	{
		return regAPCRepository.getRegAPCData(stackId, pageable);
	}

	@Override
	public List<RegAPC> getProductDetailsDataAir(String Date)
	{
		return regAPCRepository.getProductDetailsDataAir(Date);
	}

}
