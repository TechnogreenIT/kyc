package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.Prefilter;
import com.tes.repository.environmentalofficer.waterinventory.PrefilterRepository;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;

@Service
public class PrefilterServicesImpl implements PrefilterServices
{

	@Autowired
	PrefilterRepository prefilterRepository;

	@Override
	public Prefilter save(Prefilter prefilter)
	{
		return prefilterRepository.save(prefilter);
	}

	@Override
	public List<Prefilter> findAllPrefilters()
	{
		return prefilterRepository.findAllPrefilters();

	}

	@Override
	public List<Prefilter> getAllActivePrefilterData(int waterSourceId)
	{
		return prefilterRepository.getAllActivePrefilterData(waterSourceId);
	}

	@Override
	public List<Prefilter> getAllIdAndIsPrifilter()
	{
		return prefilterRepository.getAllIdAndIsPrifilter();
	}

	@Override
	public List<Prefilter> getPrefiterById(int pid)
	{
		return prefilterRepository.getPrefiterById(pid);
	}

	@Override
	public List<Prefilter> findAll()
	{
		return prefilterRepository.findAll();
	}

	@Override
	public Prefilter findOne(int id)
	{
		return prefilterRepository.findOne(id);
	}

}
