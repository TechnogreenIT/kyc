package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegMultipleFilterData;

@Repository
public interface RegMultipleFilterDataRepository extends JpaRepository<RegMultipleFilterData, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegMultipleFilterData save(RegMultipleFilterData regularFiltersData);

	@Query("SELECT rmf from RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf where DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d')=:selectedDate")
	List<RegMultipleFilterData> getRegMultipleFilterDataBySelectedDate(@Param("selectedDate") String selectedDate);

	@Query("SELECT rmf FROM RegMultipleFilterData rmf WHERE rmf.multipleFilter.multipleFilterId=:multipleFilterId ORDER BY rmf.regMultipleFilterDataId DESC")
	public List<RegMultipleFilterData> getRegularFiltersData(@Param("multipleFilterId") int multipleFilterId, Pageable pageable);

	/*
	 * @Query( "SELECT rf FROM RegularFiltersData rf WHERE rf.filterName =:filterName AND rf.waterInventory.waterInventoryId = :wiId ORDER BY rf.filterName, rf.regularFiltesDataId DESC")
	 * public List<RegMultipleFilterData> regularFiltersDataByfilterName(@Param("filterName") String filterName, @Param("wiId") int wiId, Pageable pageable);
	 * @Modifying
	 * @Transactional
	 * @Query("UPDATE RegularFiltersData rfd SET rfd.startReading = :start, rfd.endReading = :ending, rfd.actualReading = :actual WHERE rfd.regularFiltesDataId = :id")
	 * public int updateStartEndingActualReading(@Param("start") float startReading,@Param("ending") float endReading,@Param("actual") float actualReading,@Param("id") int id);
	 * @Query("SELECT r FROM RegularFiltersData r WHERE r.filterName = :filterName AND r.waterInventory.waterInventoryId =:waterInvId ORDER BY r.regularFiltesDataId DESC")
	 * public List<RegMultipleFilterData> regFiltDataFiltNameWatrInvId(@Param("filterName") String filterName,@Param("waterInvId") int WaterInvId,Pageable pageable);
	 * @Query("SELECT r FROM  RegularFiltersData r WHERE r.filterName = :filterName AND r.waterInventory.waterInventoryId =:WaterInvId  AND r.rfDate =:rfDate ORDER BY regularFiltesDataId DESC ")
	 * public List<RegMultipleFilterData> regFiltDataFiltnameWatrInvIdDate(@Param("filterName") String filterName,@Param("WaterInvId") int WaterInvId,@Param("rfDate") String rfDate,Pageable pageable);
	 * @Query("SELECT COUNT(rfd.regularFiltesDataId) FROM RegularFiltersData rfd WHERE rfd.rfDate between :pDate AND :todayDate AND rfd.filterName = :filterName")
	 * int countRegFilterDateBtwDatesByFilterName(@Param("pDate") String pDate, @Param("todayDate") String todayDate,@Param("filterName") String filterName);
	 * @Query("SELECT r.actualReading FROM RegularFiltersData  r WHERE r.rfDate= :date AND r.filterName = :filterName ORDER BY r.regularFiltesDataId ASC")
	 * Float getActualReadingByDate(@Param("date") String date,@Param("filterName") String filterName);
	 * @Query("SELECT SUM(r.actualReading) FROM RegularFiltersData r WHERE r.filterName = :filterName AND r.rfDate between :fDate AND :sDate ORDER BY regularFiltesDataId ASC")
	 * Float getSumActualReadingByFilterNameBetweenDates(@Param("filterName") String filterName,@Param("fDate") String fDate,@Param("sDate") String sDate);
	 * @Query("SELECT rf.actualReading FROM RegularFiltersData rf WHERE rf.filterName = :filterName AND rf.rfDate BETWEEN :fDate AND :sDate ORDER BY rf.regularFiltesDataId ASC")
	 * List<Float> getActualReadingListByFilterNameDatesBetween(@Param("filterName") String filterName,@Param("fDate") String fDate,@Param("sDate") String sDate);
	 * @Query("SELECT MIN(EXTRACT(YEAR FROM rf.rfDate)) FROM RegularFiltersData rf")
	 * int getMinYear();
	 * @Query("SELECT COALESCE(SUM(rf.actualReading),0) FROM RegularFiltersData rf WHERE rf.rfDate =:rfDate AND rf.filterName =:filterName")
	 * float getActualReadingByRfDateAndSourceName(@Param("rfDate")String rfDate,@Param("filterName")String filterName);
	 * @Query("SELECT SUM(actualReading) FROM RegularFiltersData WHERE filterName= :filterName AND (EXTRACT(YEAR FROM rfDate)) = :year AND (EXTRACT(MONTH FROM rfDate)) = :month ORDER BY regularFiltesDataId ASC")
	 * Float SumActualReadingByfilterNameYearMonth(@Param("filterName") String filterName,@Param("year") int year,@Param("month") int month);
	 * @Query("SELECT DISTINCT(EXTRACT(YEAR FROM rfDate)) FROM RegularFiltersData ORDER BY rfDate ASC")
	 * List<Integer> getYear();
	 * @Query("SELECT SUM(actualReading) FROM RegularFiltersData WHERE filterName= :filterName AND (EXTRACT(YEAR FROM rfDate)) = :year ORDER BY regularFiltesDataId ASC")
	 * float SumActualReadingByfilterNameYear(@Param("filterName") String filterName,@Param("year") int year);
	 * @Query("SELECT rf FROM RegularFiltersData rf WHERE rf.rfDate BETWEEN :fDate AND :lDate")
	 * List<RegMultipleFilterData> findByrfDateBetweenTwoDates(@Param("fDate") String fDate,@Param("lDate") String lDate);
	 * @Query("SELECT AVG(actualReading) FROM RegularFiltersData  WHERE waterInventory.waterInventoryId = :waterInvId AND filterName  = :filterName")
	 * Float getAvgActReadingByWaterInvIdFilterName(@Param("waterInvId") int waterInvId,@Param("filterName") String filterName);
	 * @Query( "SELECT rf FROM RegularFiltersData rf WHERE rf.rfDate=:date")s
	 * public List<RegMultipleFilterData> RegularFiltersDataBydate(@Param("date") String date);
	 */

	@Query("SELECT COALESCE(SUM(rmf.actualReading),0) FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf WHERE DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d') =:rfDate AND mf.filterLabel =:filterName")
	float getActualReadingByRfDateAndFilterName(@Param("rfDate") String rfDate, @Param("filterName") String filterName);

	@Query("SELECT rmf.actualReading FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf WHERE mf.filterLabel = :filterName AND DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :sDate ORDER BY rmf.regMultipleFilterDataId ASC")
	List<Float> getActualReadingListByFilterNameDatesBetween(@Param("filterName") String filterName, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT COUNT(rmf.regMultipleFilterDataId) FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf WHERE DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d') BETWEEN  :pDate AND :todayDate AND  mf.filterLabel = :filterName")
	int countRegFilterDateBtwDatesByFilterName(@Param("pDate") String pDate, @Param("todayDate") String todayDate, @Param("filterName") String filterName);

	@Query("SELECT rmf.actualReading FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf WHERE DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d') = :date AND mf.multipleFilterId = :filterId")
	Float getActualReadingByDateAndFilterId(@Param("date") String date, @Param("filterId") int filterId);

	@Query("SELECT rmf FROM RegMultipleFilterData rmf WHERE DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	List<RegMultipleFilterData> findByFilterBetweenTwoDates(@Param("fDate") String fDate, @Param("lDate") String lDate);

	@Query("SELECT COALESCE(SUM(rmf.actualReading),0) FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf WHERE mf.filterLabel = :filterName AND DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :sDate ORDER BY rmf.regMultipleFilterDataId ASC")
	Float getSumActualReadingByFilterNameBetweenDates(@Param("filterName") String filterName, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT SUM(rmf.actualReading) FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf WHERE mf.filterLabel = :filterName AND (EXTRACT(YEAR FROM DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d'))) =:year AND (EXTRACT(MONTH FROM DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d'))) =:month")
	Float SumActualReadingByfilterNameYearMonth(@Param("filterName") String filterName, @Param("year") int year, @Param("month") int month);

	@Query("SELECT SUM(rmf.actualReading) FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf WHERE mf.filterLabel = :filterName AND (EXTRACT(YEAR FROM DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d'))) =:year")
	Float SumActualReadingByfilterNameYear(@Param("filterName") String filterName, @Param("year") int year);

	@Query("SELECT SUM(rmf.actualReading) FROM RegMultipleFilterData rmf LEFT JOIN rmf.multipleFilter mf LEFT JOIN mf.filters f WHERE mf.filterLabel = :filterName AND f.filterType=:filterType AND DATE_FORMAT(date(rmf.createDateTime), '%Y-%m-%d')=:rfDate")
	Float getSumActualReadingByRfDateAndFilterName(@Param("rfDate") String rfDate, @Param("filterName") String filterName, @Param("filterType") String filterType);

}
