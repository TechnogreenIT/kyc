package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.MultipleFilter;

@Repository
public interface MultipleFilterRepository extends JpaRepository<MultipleFilter, Long>
{
	@SuppressWarnings("unchecked")
	MultipleFilter save(MultipleFilter multipleFilter);

	@Query("SELECT DISTINCT(mf.filterLabel) FROM  MultipleFilter mf")
	public List<String> getAllFiltersLabels();

	@Query("SELECT NEW MultipleFilter(multipleFilterId,filterLabel) FROM  MultipleFilter")
	public List<MultipleFilter> getAllFiltersIdAndLabels();

	// @Query("SELECT COUNT(DISTINCT mf.filterLabel) as TotalCountOfFilterName FROM MultipleFilter mf WHERE mf.isMeter = '1' ")
	// public int getMultipleFilterCount();

	@Query("SELECT mf.isMeter FROM MultipleFilter mf  WHERE mf.filterLabel= :filterName")
	public boolean getMetervaluefilter(@Param("filterName") String filterName);

	@Query("SELECT mf from MultipleFilter mf LEFT JOIN  mf.filters f LEFT JOIN f.prefilter pf LEFT JOIN pf.waterSource ws LEFT JOIN ws.waterInventory wi WHERE mf.isActive=true AND wi.waterInventoryId= :waterInventoryId")
	public List<MultipleFilter> findAllMultipleFiltersData(@Param("waterInventoryId") int waterInventoryId);

	@Query("SELECT mf FROM  MultipleFilter mf LEFT JOIN mf.filters f LEFT JOIN f.prefilter p WHERE p.prefilterId=:pid ORDER BY p.prefilterId")
	public List<MultipleFilter> getAllFiltersIdAndLabelsByPrefilterId(@Param("pid") int pid);

	@Query("SELECT mf FROM  MultipleFilter mf LEFT JOIN mf.filters f  WHERE f.filtersId=:fid ORDER BY f.filtersId")
	public List<MultipleFilter> getAllFiltersIdAndLabelsByFilterId(@Param("fid") int fid);

	// @Query("SELECT fu.filterUseName FROM FilterUse fu LEFT JOIN fu.filters f WHERE f.filterType= :filterName")
	// public List<String> findFiltersUseByFilterName(@Param("filterName") String filterName);

	@Query("SELECT f.filterType FROM MultipleFilter mf LEFT JOIN mf.filters f  WHERE mf.filterLabel=:mfLabel")
	public List<String> findFilterTypeByMultiFilter(@Param("mfLabel") String mfLabel);
}
