package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.repository.query.Param;
import com.tes.model.MultipleFilter;

public interface MultipleFilterServices
{

	public List<String> getAllFiltersLabels();

	public List<MultipleFilter> getAllFiltersIdAndLabels();

	public boolean getMetervaluefilter(@Param("filterName") String filterName);

	public List<MultipleFilter> findAllMultipleFiltersData(int waterInventoryId);

	public List<MultipleFilter> getAllFiltersIdAndLabelsByPrefilterId(int pid);

	public List<MultipleFilter> getAllFiltersIdAndLabelsByFilterId(@Param("fid") int fid);

	public List<String> findFilterTypeByMultiFilter(String mfLabel);

	public void save(MultipleFilter objMultipleFilter);

}
