package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import com.tes.model.FilterUse;

public interface FilterUseServices
{

	FilterUse save(FilterUse filterUse);

	public List<FilterUse> findFilterUseTypeAndlabel();

	List<String> getFilterUseData();

	public List<FilterUse> getFilterUseDataByLabel(String filterUseLabel);

	Float getWaterLoss(String filterLabelName);

	// TreeSet<String> getUsedFilterUseName();
	//
	// List<String> getFiltersUseByFilterId(int filterId);
	//
	// public boolean getMetervalueOfFilterUse(String filterType,String filterUse);

	public List<FilterUse> getFilterUseByPreFilterId(int pid);

	public List<FilterUse> getFilterUseByMultiFilterId(int mfid);

	public List<String> findAllFilterUseLabel();

	public List<FilterUse> findAll();

	public int findPid(int pid);

	public int findMidByfilterId(int fId);

	public List<FilterUse> getFilterUseAll();

	boolean isFilter(int filterId);

	public List<String> getFilterUseLabelByMfLabel(String filterUseLabel);

	public String isFilterByfiterType(String fiterType);

	public List<Integer> findPidAll();

	public List<Integer> findPrefilterId();

	public List<FilterUse> findFilterUseByPrefilter(int pid);

	public List<String> getMultipeFilterByMf(String filterUseLable);

	public boolean checkIsIndustrial(String filterUseLable);

}
