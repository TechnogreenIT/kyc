package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.FilterUse;
import com.tes.repository.environmentalofficer.waterinventory.FilterUseRepository;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;

@Service
public class FilterUseServicesImpl implements FilterUseServices
{

	@Autowired
	FilterUseRepository filterUseRepository;

	@Override
	public FilterUse save(FilterUse filterUse)
	{
		return filterUseRepository.save(filterUse);
	}

	@Override
	public List<FilterUse> findFilterUseTypeAndlabel()
	{
		return filterUseRepository.findFilterUseTypeAndlabel();
	}

	@Override
	public List<FilterUse> getFilterUseByPreFilterId(int pid)
	{
		return filterUseRepository.getFilterUseByPreFilterId(pid);
	}

	@Override
	public List<String> getFilterUseData()
	{
		return filterUseRepository.getFilterUseData();
	}

	@Override
	public List<FilterUse> getFilterUseDataByLabel(String filterUseLabel)
	{
		return filterUseRepository.getFilterUseDataByLabel(filterUseLabel);
	}

	@Override
	public List<FilterUse> getFilterUseByMultiFilterId(int mfid)
	{
		return filterUseRepository.getFilterUseByMultiFilterId(mfid);
	}

	@Override
	public List<String> findAllFilterUseLabel()
	{

		return filterUseRepository.findAllFilterUseLabel();
	}

	@Override
	public List<FilterUse> findAll()
	{
		return filterUseRepository.findAll();
	}

	@Override
	public int findPid(int pid)
	{
		return filterUseRepository.findPid(pid);
	}

	@Override
	public int findMidByfilterId(int fId)
	{
		return filterUseRepository.findMidByfilterId(fId);
	}

	@Override
	public List<FilterUse> getFilterUseAll()
	{
		return filterUseRepository.getFilterUseAll();
	}

	@Override
	public boolean isFilter(int filterId)
	{
		return filterUseRepository.isFilter(filterId);
	}

	@Override
	public List<String> getFilterUseLabelByMfLabel(String filterUseLabel)
	{
		return filterUseRepository.getFilterUseLabelByMfLabel(filterUseLabel);
	}

	@Override
	public String isFilterByfiterType(String fiterType)
	{
		return filterUseRepository.isFilterByfiterType(fiterType);
	}

	@Override
	public List<Integer> findPidAll()
	{
		return filterUseRepository.findPidAll();
	}

	@Override
	public List<Integer> findPrefilterId()
	{
		return filterUseRepository.findPrefilterId();
	}

	@Override
	public List<FilterUse> findFilterUseByPrefilter(int pid)
	{
		return filterUseRepository.findFilterUseByPrefilter(pid);
	}

	@Override
	public Float getWaterLoss(String filterLabelName)
	{
		return filterUseRepository.getWaterLoss(filterLabelName);
	}

	@Override
	public List<String> getMultipeFilterByMf(String filterUseLable)
	{
		return filterUseRepository.getMultipeFilterByMf(filterUseLable);
	}

	@Override
	public boolean checkIsIndustrial(String filterUseLable)
	{
		return filterUseRepository.checkIsIndustrial(filterUseLable);
	}

	// @Override
	// public TreeSet<String> getUsedFilterUseName() {
	// return filterUseRepository.getUsedFilterUseName();
	// }
	//
	// @Override
	// public List<String> getFiltersUseByFilterId(int filterId) {
	// return filterUseRepository.getFiltersUseByFilterId(filterId);
	// }
	//
	// @Override
	// public boolean getMetervalueOfFilterUse(String filterType, String filterUse) {
	// return filterUseRepository.getMetervalueOfFilterUse(filterType, filterUse);
	// }

}
