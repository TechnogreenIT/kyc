package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.MultipleFilter;
import com.tes.repository.environmentalofficer.waterinventory.MultipleFilterRepository;
import com.tes.services.environmentalofficer.waterinventory.MultipleFilterServices;

@Service
public class MultipleFilterServicesImpl implements MultipleFilterServices
{

	@Autowired
	MultipleFilterRepository multipleFilterRepository;

	@Override
	public void save(MultipleFilter objMultipleFilter)
	{
		multipleFilterRepository.save(objMultipleFilter);

	}

	@Override
	public boolean getMetervaluefilter(String filterName)
	{
		return multipleFilterRepository.getMetervaluefilter(filterName);
	}

	@Override
	public List<String> getAllFiltersLabels()
	{
		return multipleFilterRepository.getAllFiltersLabels();
	}

	@Override
	public List<MultipleFilter> getAllFiltersIdAndLabels()
	{
		return multipleFilterRepository.getAllFiltersIdAndLabels();
	}

	@Override
	public List<MultipleFilter> getAllFiltersIdAndLabelsByPrefilterId(int pid)
	{
		return multipleFilterRepository.getAllFiltersIdAndLabelsByPrefilterId(pid);
	}

	@Override
	public List<MultipleFilter> findAllMultipleFiltersData(int waterInventoryId)
	{
		return multipleFilterRepository.findAllMultipleFiltersData(waterInventoryId);
	}

	@Override
	public List<MultipleFilter> getAllFiltersIdAndLabelsByFilterId(int fid)
	{
		// TODO Auto-generated method stub
		return multipleFilterRepository.getAllFiltersIdAndLabelsByFilterId(fid);
	}

	@Override
	public List<String> findFilterTypeByMultiFilter(String mfLabel)
	{
		return multipleFilterRepository.findFilterTypeByMultiFilter(mfLabel);
	}

}
