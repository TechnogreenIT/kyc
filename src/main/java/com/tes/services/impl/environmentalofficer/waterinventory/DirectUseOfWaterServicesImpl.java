package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.DirectUseOfWater;
import com.tes.repository.environmentalofficer.waterinventory.DirectUseOfWaterRepository;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;

@Service
public class DirectUseOfWaterServicesImpl implements DirectUseOfWaterServices
{

	@Autowired
	DirectUseOfWaterRepository directUseOfWaterRepository;

	@Override
	public DirectUseOfWater save(DirectUseOfWater objDirectUseOfWater)
	{
		return directUseOfWaterRepository.save(objDirectUseOfWater);
	}

	@Override
	public List<DirectUseOfWater> getUsedDirectUseNameList()
	{
		return directUseOfWaterRepository.getUsedDirectUseNameList();
	}

	@Override
	public TreeSet<String> getUsedDirectUseName()
	{
		return directUseOfWaterRepository.getUsedDirectUseName();
	}

	@Override
	public List<DirectUseOfWater> getAllWhereToUseAndIsIndustries()
	{
		return directUseOfWaterRepository.getAllWhereToUseAndIsIndustries();
	}

	@Override
	public boolean getDirectUseisIndustrial(String label)
	{
		return directUseOfWaterRepository.getDirectUseisIndustrial(label);
	}

	@Override
	public TreeSet<String> getDirectUseName(int waterInventoryId)
	{
		return directUseOfWaterRepository.getDirectUseName(waterInventoryId);
	}

	@Override
	public List<DirectUseOfWater> getUseOfWaterByWhereToUse(String whereToUse)
	{
		return directUseOfWaterRepository.getUseOfWaterByWhereToUse(whereToUse);
	}

	@Override
	public List<DirectUseOfWater> directUseOfWaterList(int wsId)
	{
		return directUseOfWaterRepository.directUseOfWaterList(wsId);
	}
}
