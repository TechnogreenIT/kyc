package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.Filters;
import com.tes.repository.environmentalofficer.waterinventory.FiltersRepository;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;

@Service
public class FiltersServicesImpl implements FiltersServices
{

	@Autowired
	FiltersRepository filtersRepository;

	@Override
	public List<String> findAllFilterType()
	{
		return filtersRepository.findAllFilterType();
	}

	@Override
	public List<Filters> findAll()
	{
		return filtersRepository.findAll();
	}

	@Override
	public List<Filters> findAllFilters()
	{
		return filtersRepository.findAllFilters();
	}

	@Override
	public List<Filters> findAllFiltersByPrefilterId(int pid)
	{
		return filtersRepository.findAllFiltersByPrefilterId(pid);
	}

	@Override
	public Filters save(Filters filters)
	{
		return filtersRepository.save(filters);
	}

	@Override
	public Filters getFilterByType(String type)
	{
		return filtersRepository.getFilterByType(type);
	}

	// Effected By Water Inventory ........by vishal
	/*
	 * @Override public List<Filters> filtersAllData(int wiId, String filterName) {
	 * return filtersRepository.filtersAllData(wiId, filterName); }
	 * @Override public Filters save(Filters objfilters) { return
	 * filtersRepository.save(objfilters); }
	 * @Override public List<Filters> getDistinctFilterName(int wiId) { return
	 * filtersRepository.getDistinctFilterName(wiId); }
	 * @Override public List<Filters> findByFilterWaterInvId(int waterInvId) {
	 * return filtersRepository.findByFilterWaterInvId(waterInvId); }
	 * @Override public List<Filters> findByFilterNameAndWaterInv(String filterName,
	 * int waterInvId) { return
	 * filtersRepository.findByFilterNameAndWaterInv(filterName, waterInvId); }
	 * @Override public List<Filters> findFiltersData(String filterName, int wiId) {
	 * return filtersRepository.findFiltersData(filterName, wiId); }
	 * @Override public List<String> getAllDistFiltersName() { return
	 * filtersRepository.getAllDistFiltersName(); }
	 * @Override public List<String> getFiltersUseByFilterName(String filterName) {
	 * return filtersRepository.getFiltersUseByFilterName(filterName); }
	 * @Override public List<String> getFilterUseList() { return
	 * filtersRepository.getFilterUseList(); }
	 * @Override public List<String> getSourceNameByWaterInvId(int waterInvId) {
	 * return filtersRepository.getSourceNameByWaterInvId(waterInvId); }
	 * @Override public List<String> getFilterUseByWaterInvIdAndFilterName(int
	 * waterInvId, String filterName) { return
	 * filtersRepository.getFilterUseByWaterInvIdAndFilterName(waterInvId,
	 * filterName); }
	 * @Override public List<Float> getLossvalueByWaterInvIdFilterName(int
	 * waterInvId, String filterName,Pageable pageable) { return
	 * filtersRepository.getLossvalueByWaterInvIdFilterName(waterInvId,
	 * filterName,pageable); }
	 * @Override public List<Float> getwaterLossByWaterInvIdFilterNameUse(int
	 * waterInvId, String filterName, String filterUse, Pageable pageable) { return
	 * filtersRepository.getwaterLossByWaterInvIdFilterNameUse(waterInvId,
	 * filterName, filterUse, pageable); }
	 * @Override public int getnumberofMeter() { return
	 * filtersRepository.getnumberofMeter(); }
	 * @Override public String getMetervaluefilter(String filterName) { return
	 * filtersRepository.getMetervaluefilter(filterName); }
	 * @Override public String getMetervaluefiltertype(String filterName, String
	 * filterUse) { return filtersRepository.getMetervaluefiltertype(filterName,
	 * filterUse); }
	 */

}
