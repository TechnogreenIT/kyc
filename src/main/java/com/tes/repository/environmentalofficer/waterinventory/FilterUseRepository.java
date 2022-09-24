package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import java.util.TreeSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.FilterUse;

@Repository
public interface FilterUseRepository extends JpaRepository<FilterUse, Long>
{

	// @Query("SELECT DISTINCT(filterUseType) FROM FilterUse")
	// TreeSet<String> getUsedFilterUseName();
	//
	// @Query("SELECT DISTINCT(fu.filterUseName) FROM FilterUse fu LEFT JOIN fu.filters f where f.filtersId= :filterId ")
	// List<String> getFiltersUseByFilterId(@Param("filterId") int filterId);
	//
	// @Query("SELECT fu.isMeter FROM FilterUse fu LEFT JOIN fu.filters f WHERE f.filterType= :filteType AND fu.filterUseName= :filterUse")
	// public boolean getMetervalueOfFilterUse(@Param("filteType") String filteType,@Param("filterUse") String filterUse);

	// @Query("SELECT fu.filterUseName FROM FilterUse fu LEFT JOIN fu.filters f WHERE f.filterType= :filterName")
	// public List<String> findFiltersUseByFilterName(@Param("filterName") String filterName);

	@SuppressWarnings("unchecked")
	FilterUse save(FilterUse filterUse);

	@Query("SELECT distinct NEW  FilterUse(f.filterUseType, f.filterUseLabel) FROM FilterUse f where f.isFilter='0'")
	public List<FilterUse> findFilterUseTypeAndlabel();

	@Query("SELECT DISTINCT(fu.filterUseLabel) FROM FilterUse fu WHERE fu.isFilter=false AND fu.isActive=true ORDER BY fu.filterUseType, fu.filterUseId ASC")
	public List<String> getFilterUseData();

	@Query("SELECT NEW FilterUse(fu.filterUseType, fu.filterUseLabel, fu.isMeter, fu.isFilter, fu.isActive) FROM FilterUse fu WHERE fu.filterUseLabel= :filterUseLabel ORDER BY fu.filterUseId ASC")
	public List<FilterUse> getFilterUseDataByLabel(@Param("filterUseLabel") String filterUseLabel);

	@Query("SELECT fu.waterLoss FROM FilterUse fu WHERE fu.filterUseLabel=:filterLabelName")
	Float getWaterLoss(@Param("filterLabelName") String filterLabelName);

	@Query("SELECT fu FROM FilterUse fu LEFT JOIN fu.prefilter p WHERE p.prefilterId=:pid")
	List<FilterUse> getFilterUseByPreFilterId(@Param("pid") int pid);

	@Query("SELECT fu FROM FilterUse fu LEFT JOIN fu.multipleFilter mf WHERE mf.multipleFilterId=:mfid")
	List<FilterUse> getFilterUseByMultiFilterId(@Param("mfid") int mfid);

	@Query("SELECT DISTINCT(fu.filterUseLabel) FROM  FilterUse fu WHERE fu.isActive=1")
	public List<String> findAllFilterUseLabel();

	@Query("SELECT COALESCE(p.prefilterId,0) FROM  FilterUse fu LEFT JOIN fu.prefilter p WHERE p.prefilterId=:pid")
	public int findPid(@Param("pid") int pid);

	@Query("SELECT COALESCE(mf.multipleFilterId,0) FROM  FilterUse fu LEFT JOIN fu.multipleFilter mf LEFT JOIN mf.filters f WHERE f.filtersId=:fId")
	public int findMidByfilterId(@Param("fId") int fId);

	@Query("SELECT fu FROM FilterUse fu")
	List<FilterUse> getFilterUseAll();

	@Query("SELECT fu.isFilter FROM FilterUse fu LEFT JOIN fu.multipleFilter mf LEFT JOIN mf.filters f WHERE f.filtersId=:filterId")
	public boolean isFilter(@Param("filterId") int filterId);

	@Query("SELECT Distinct(fu.filterUseLabel) FROM FilterUse fu  LEFT JOIN fu.multipleFilter mf LEFT JOIN mf.filters f WHERE mf.filterLabel=:filterUseLabel")
	public List<String> getFilterUseLabelByMfLabel(@Param("filterUseLabel") String filterUseLabel);

	@Query("SELECT fu.filterUseType FROM FilterUse fu LEFT JOIN fu.multipleFilter mf LEFT JOIN mf.filters f WHERE fu.filterUseType=:fiterType AND fu.isFilter=1")
	public String isFilterByfiterType(@Param("fiterType") String fiterType);

	@Query("SELECT fu.prefilter.prefilterId FROM  FilterUse fu LEFT JOIN fu.prefilter p")
	public List<Integer> findPidAll();

	@Query("SELECT DISTINCT (fu.prefilter.prefilterId) FROM FilterUse fu WHERE fu.prefilter.prefilterId IS NOT NULL")
	public List<Integer> findPrefilterId();

	@Query("SELECT fu FROM FilterUse fu WHERE fu.prefilter.prefilterId =:pid")
	public List<FilterUse> findFilterUseByPrefilter(@Param("pid") int pid);

	@Query("SELECT fu.waterLoss FROM FilterUse fu WHERE fu.filterUseLabel=:filterLabelName")
	Float get(@Param("filterLabelName") String filterLabelName);

	@Query("SELECT mf.filterLabel FROM FilterUse fu LEFT JOIN fu.multipleFilter mf WHERE fu.filterUseLabel=:filterUseLable ")
	public List<String> getMultipeFilterByMf(@Param("filterUseLable") String filterUseLable);

	@Query("SELECT fu.isIndustrial FROM FilterUse fu  WHERE fu.filterUseLabel=:filterUseLable ")
	public boolean checkIsIndustrial(@Param("filterUseLable") String filterUseLable);

	@Query("SELECT DISTINCT(filterUseType) FROM FilterUse")
	public TreeSet<String> getUsedFilteruseType();
}
