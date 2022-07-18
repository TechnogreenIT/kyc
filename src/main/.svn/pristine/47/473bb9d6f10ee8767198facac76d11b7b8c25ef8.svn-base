package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import com.tes.model.Filters;

public interface FiltersServices
{
	Filters save(Filters filters);

	public List<String> findAllFilterType();

	public List<Filters> findAll();

	/*
	 * public List<Filters> filtersAllData(int wiId, String filterName);
	 * public Filters save(Filters objfilters);
	 * List<Filters> findByFilterWaterInvId(int waterInvId);
	 * public List<Filters> getDistinctFilterName(int wiId);
	 * public List<Filters> findFiltersData(String filterName, int wiId);
	 * public List<Filters> findByFilterNameAndWaterInv(String filterName, int
	 * waterInvId);
	 * List<String> getAllDistFiltersName();
	 * List<String> getFiltersUseByFilterName(String filterName);
	 * String getMetervaluefilter(String filterName);
	 * String getMetervaluefiltertype(String filterName,String filterUse);
	 * public List<String> getFilterUseList();
	 * public List<String> getSourceNameByWaterInvId(int waterInvId);
	 * public List<String> getFilterUseByWaterInvIdAndFilterName(int
	 * waterInvId,String filterName);
	 * public List<Float> getLossvalueByWaterInvIdFilterName(int waterInvId,String
	 * filterName,Pageable pageable);
	 * public List<Float> getwaterLossByWaterInvIdFilterNameUse(@Param("waterInvId")
	 * int waterInvId,@Param("filterName") String filterName,@Param("filterUse")
	 * String filterUse,Pageable pageable);
	 * public int getnumberofMeter();
	 */
	public List<Filters> findAllFilters();

	public List<Filters> findAllFiltersByPrefilterId(int pid);

	Filters getFilterByType(String type);
}
