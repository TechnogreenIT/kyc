package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.Filters;

@Repository
public interface FiltersRepository extends JpaRepository<Filters, Long>
{
	@SuppressWarnings("unchecked")
	Filters save(Filters filters);

	@Query("SELECT f.filterType FROM Filters f")
	public List<String> findAllFilterType();

	/*
	 * @Query("SELECT DISTINCT NEW Filters(f.filterName, f.waterMeter, f.sourceName, f.filterUse, f.filterMeter, f.waterInventory) FROM Filters f WHERE f.waterInventory.waterInventoryId= :wiId AND f.filterName=:filterName order by f.filterName")
	 * public List<Filters> filtersAllData(@Param("wiId") int wiId, @Param("filterName") String filterName);
	 * @Query("SELECT DISTINCT NEW Filters(f.filterName,f.waterMeter,f.sourceName) FROM Filters f WHERE f.waterInventory.waterInventoryId=:wId")
	 * List<Filters> findByFilterWaterInvId(@Param("wId") int wId);
	 * @Query("SELECT NEW Filters (f.filterUse,f.filterMeter) FROM Filters f WHERE f.filterName= :filterName AND f.waterInventory.waterInventoryId= :waterInvId ")
	 * public List<Filters> findByFilterNameAndWaterInv(@Param("filterName") String filterName, @Param("waterInvId") int waterInvId);
	 * @Query("SELECT DISTINCT NEW Filters(f.filterName,f.waterInventory) FROM Filters f WHERE f.waterInventory.waterInventoryId= :wiId")
	 * public List<Filters> getDistinctFilterName(@Param("wiId") int wiId);
	 * @Query("SELECT DISTINCT NEW Filters(f.sourceName, f.filterName, f.waterMeter, f.waterInventory) FROM Filters f WHERE f.filterName=:filterName AND f.waterInventory.waterInventoryId= :wiId")
	 * public List<Filters> findFiltersData(@Param("filterName") String filterName, @Param("wiId") int wiId);
	 * @Query("SELECT DISTINCT(f.filterName) FROM Filters f")
	 * public List<String> getAllDistFiltersName();
	 * @Query("SELECT f.filterUse FROM Filters f WHERE f.filterName= :filterName")
	 * public List<String> getFiltersUseByFilterName(@Param("filterName") String filterName);
	 * @Query("SELECT DISTINCT(f.filterUse) FROM Filters f")
	 * public List<String> getFilterUseList();
	 * @Query("SELECT DISTINCT (f.filterName) FROM Filters f WHERE f.waterInventory.waterInventoryId= :waterInvId")
	 * public List<String> getSourceNameByWaterInvId(@Param("waterInvId") int waterInvId);
	 * @Query("SELECT filterUse FROM Filters WHERE waterInventory.waterInventoryId= :waterInvId AND filterName = :filterName")
	 * public List<String> getFilterUseByWaterInvIdAndFilterName(@Param("waterInvId") int waterInvId,@Param("filterName") String filterName);
	 * @Query("SELECT lossValue FROM Filters  WHERE waterInventory.waterInventoryId = :waterInvId AND filterName = :filterName")
	 * public List<Float> getLossvalueByWaterInvIdFilterName(@Param("waterInvId") int waterInvId,@Param("filterName") String filterName,Pageable pageable);
	 * @Query("SELECT waterLoss FROM Filters  WHERE waterInventory.waterInventoryId  = :waterInvId AND filterName = :filterName AND filterUse = :filterUse")
	 * public List<Float> getwaterLossByWaterInvIdFilterNameUse(@Param("waterInvId") int waterInvId,@Param("filterName") String filterName,@Param("filterUse") String filterUse,Pageable pageable);
	 * @Query("SELECT COUNT(DISTINCT f.filterName) as TotalCountOfFilterName FROM Filters f WHERE f.waterMeter = 'Yes'")
	 * public int getnumberofMeter();
	 * @Query("SELECT f.waterMeter FROM Filters f WHERE f.filterName= :filterName")
	 * public String getMetervaluefilter(@Param("filterName") String filterName);
	 * @Query("SELECT f.filterMeter FROM Filters f WHERE f.filterName= :filterName AND f.filterUse= :filterUse")
	 * public String getMetervaluefiltertype(@Param("filterName") String filterName,@Param("filterUse") String filterUse);
	 */

	@Query("SELECT f FROM Filters f")
	public List<Filters> findAllFilters();

	@Query("SELECT f FROM Filters f LEFT JOIN f.prefilter p WHERE p.prefilterId=:pid")
	public List<Filters> findAllFiltersByPrefilterId(@Param("pid") int pid);

	@Query("SELECT f FROM Filters f WHERE f.filterType=:type")
	public Filters getFilterByType(@Param("type") String type);

}
