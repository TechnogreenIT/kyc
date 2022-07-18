package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegFiltersUseData;

@Repository
public interface RegFiltersUseDataRepository extends JpaRepository<RegFiltersUseData, Long>
{

	@SuppressWarnings("unchecked")
	RegFiltersUseData save(RegFiltersUseData regFiltersUseData);

	@Query("SELECT rfu from RegFiltersUseData rfu WHERE DATE_FORMAT(date(rfu.createDateTime), '%Y-%m-%d')=:selectedDate")
	List<RegFiltersUseData> getRegFilterUseDataBySelectedDate(@Param("selectedDate") String selectedDate);

	@Query("SELECT rfu FROM RegFiltersUseData rfu WHERE rfu.filterUseLabel=:filterUseLabel ORDER BY rfu.regFiltersUseDataId DESC")
	List<RegFiltersUseData> getRegFiltersUseDataByFilterUseLabel(@Param("filterUseLabel") String filterUseLabel, Pageable pageable);

	// affected query by filter ...........visahl
	// @Query("SELECT COALESCE(SUM(rf.actualReading),0) FROM RegFiltersUseData rf LEFT JOIN rf.filterUse fu LEFT JOIN fu.filters f WHERE f.filterType =:filterType AND fu.filterUseName=:filter AND DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') BETWEEN :fWeek AND :sWeek ORDER BY rf.actualReading ASC")
	// Float getSumActualReadingListByFilterNameAndTypeAndDatesBetween(@Param("filter")String filter,@Param("filterType")String filterType,@Param("fWeek")String fWeek,@Param("sWeek")String sWeek);

	@Query("SELECT COALESCE(SUM(rf.actualReading),0) FROM RegFiltersUseData rf WHERE rf.filterUseLabel = :filterUseLabel AND (EXTRACT(YEAR FROM DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d')))= :year AND (EXTRACT(MONTH FROM DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d')))= :month ORDER BY rf.actualReading ASC")
	Float getSumActualReadingByYearMonthFilterLabel(@Param("year") int year, @Param("month") int month, @Param("filterUseLabel") String filterUseLabel);

	/*
	 * @Override
	 * @SuppressWarnings("unchecked")
	 * RegFiltersUseData save(RegFiltersUseData regularFiltersUseData);
	 * @Query("SELECT SUM(r.actualReading) FROM RegularFiltersUseData r WHERE r.filterType = 'Process' AND EXTRACT(YEAR FROM r.rfDate)  BETWEEN :esrYearFrom AND :esrYearFrom AND EXTRACT(MONTH FROM r.rfDate) = :month")
	 * Float actualReadingSumByRfDate(@Param("esrYearFrom") int esrYearFrom,@Param("month") int month);
	 * @Query("SELECT SUM(r.actualReading) FROM RegularFiltersUseData  r WHERE(r.filterType = 'Cooling' OR r.filterType = 'Boiler' OR r.filterType = 'Equipment Washing')  AND EXTRACT(YEAR FROM r.rfDate) BETWEEN :esrYearFrom  AND :esrYearTo AND EXTRACT(MONTH FROM r.rfDate) = :month")
	 * Float actualReadingCoolingSumByRfDate(@Param("esrYearFrom") int esrYearFrom,@Param("esrYearTo") int esrYearTo,@Param("month") int month);
	 * @Modifying
	 * @Transactional
	 * @Query("UPDATE RegularFiltersUseData rf SET rf.startReading = :start, rf.endReading = :end, rf.actualReading = :actual WHERE rf.regularFiltersUseDataId = :id")
	 * int updateStartEndAvgReading(@Param("start") float startReading, @Param("end") float endReading, @Param("actual") float actualReading, @Param("id") int id);
	 * @Query("SELECT r FROM RegularFiltersUseData r WHERE r.filterName = :filterName  AND r.filterType = :type AND r.waterInventory.waterInventoryId = :waterInvId  ORDER BY r.regularFiltersUseDataId DESC")
	 * public List<RegFiltersUseData> getRegularFilterUseByFiltrNameTypeWiId(@Param("filterName") String filterName,@Param("type") String type,@Param("waterInvId") int waterInvId,Pageable pageable);
	 * @Query("SELECT r FROM RegularFiltersUseData r   WHERE r.filterName = :filterName  AND r.filterType = :filterType AND r.waterInventory.waterInventoryId = :wId AND r.rfDate=:rfDate ORDER BY r.regularFiltersUseDataId DESC")
	 * public List<RegFiltersUseData> getRegfiltrUseDataFiltrNameTypeWIdDate(@Param("filterName") String filterName, @Param("filterType") String filterType, @Param("wId") int wId, @Param("rfDate") String rfDate,Pageable pageble);
	 * @Query( "SELECT f.filterType FROM RegularFiltersUseData f WHERE f.filterName=:filterName AND f.waterInventory.waterInventoryId= :wiId")
	 * public List<String> findFilterUse(@Param("filterName") String filterName, @Param("wiId") int wiId);
	 * @Query("SELECT rf FROM RegularFiltersUseData  rf WHERE rf.filterName = :filterName AND rf.filterType = :filterUse AND rf.waterInventory.waterInventoryId =:wiId ORDER BY rf.filterName, rf.regularFiltersUseDataId DESC")
	 * List<RegFiltersUseData> regularFiltersUseDataByfilterName(@Param("filterName") String filterName, @Param("filterUse") String filterUse, @Param("wiId") int wiId, Pageable pageable);
	 * @Query("SELECT COUNT(rfu.regularFiltersUseDataId) FROM RegularFiltersUseData  rfu WHERE rfu.rfDate BETWEEN :pDate AND :todayDate AND rfu.filterType= :filterType AND rfu.filterName= :filterName")
	 * int countRegFilterUseDateBtwDatesByFilterNameType(@Param("pDate") String pDate, @Param("todayDate") String todayDate, @Param("filterType") String filterType,@Param("filterName") String filterName);
	 * @Query("SELECT COALESCE(SUM(rfu.actualReading),0) FROM RegularFiltersUseData  rfu WHERE rfu.filterType = :type AND rfu.rfDate BETWEEN :esrYearFrom AND :esrYearTo")
	 * float getActualReadingByRFDate(@Param("type") String type, @Param("esrYearFrom") String esrYearFrom, @Param("esrYearTo") String esrYearTo);
	 * @Query("SELECT COALESCE(SUM(rfu.actualReading),0) FROM RegularFiltersUseData  rfu WHERE rfu.filterType ='Cooling' OR rfu.filterType ='Boiler' OR rfu.filterType ='Equipment Washing' AND rfu.rfDate BETWEEN :esrYearFrom AND :esrYearTo")
	 * float getCoolingActualReadingByRFDate(@Param("esrYearFrom") String esrYearFrom, @Param("esrYearTo") String esrYearTo);
	 * @Query("SELECT r.actualReading FROM RegularFiltersUseData r WHERE r.rfDate = :date AND r.filterType = :filterType AND r.filterName = :filterName ORDER BY r.regularFiltersUseDataId ASC")
	 * Float getActualReadingByDateTypeName(@Param("date") String date,@Param("filterType") String filterType,@Param("filterName") String filterName);
	 * @Query("SELECT SUM(r.actualReading) FROM RegularFiltersUseData r WHERE  r.filterName = :filterName AND r.filterType = :filterType AND r.rfDate between :fDate AND :sDate ORDER BY r.regularFiltersUseDataId ASC")
	 * Float getActualReadingByNameTypeBetweenDates(@Param("filterName") String filterName,@Param("filterType") String filterType,@Param("fDate") String fDate,@Param("sDate") String sDate);
	 * @Query("SELECT MIN(EXTRACT(YEAR FROM rf.rfDate)) FROM RegularFiltersUseData rf")
	 * int getMinYear();
	 * @Query("SELECT COALESCE(SUM(rf.actualReading),0) FROM RegularFiltersUseData rf WHERE rf.rfDate =:date AND rf.filterName =:filterName AND rf.filterType =:filterType")
	 * float getActualReadingByRfDateFilterTypeAndFilterName(@Param("date")String date,@Param("filterName")String filterName,@Param("filterType")String filterType);
	 * @Query("SELECT SUM(actualReading) FROM RegularFiltersUseData WHERE (EXTRACT(YEAR FROM rfDate)) = :year AND filterType = :filterType AND filterName = :filterName AND (EXTRACT(MONTH FROM rfDate)) = :month ORDER BY regularFiltersUseDataId ASC")
	 * Float sumActualReadingByYearFilterTypeNameMonth(@Param("year") int year,@Param("filterType") String filterType ,@Param("filterName") String filterName,@Param("month") int month);
	 * @Query("SELECT DISTINCT(EXTRACT(YEAR FROM rfDate)) FROM RegularFiltersUseData ORDER BY rfDate ASC")
	 * List<Integer> getYear();
	 * @Query("SELECT SUM(actualReading) FROM RegularFiltersUseData WHERE (EXTRACT(YEAR FROM rfDate)) = :year AND filterType = :filterType AND filterName = :filterName ORDER BY regularFiltersUseDataId ASC")
	 * Float sumActualReadingByYearTypeAndName(@Param("year") int year,@Param("filterType") String filterType,@Param("filterName") String filterName);
	 * @Query("SELECT rf.actualReading FROM RegularFiltersUseData rf WHERE rf.filterType =:filterType AND rf.filterName=:filter AND rf.rfDate BETWEEN :pDate AND :today ORDER BY rf.actualReading ASC")
	 * List<Float> getActualReadingListByFilterTypeAndDatesBetween(@Param("filter")String filter,@Param("filterType")String filterType,@Param("pDate")String pDate,@Param("today")String today);
	 * @Query("SELECT rf FROM RegularFiltersUseData rf WHERE rf.rfDate BETWEEN :fDate AND :lDate")
	 * List<RegFiltersUseData> findByrfDateBetweenTwoDates(@Param("fDate")String fDate,@Param("lDate") String lDate);
	 * @Query("SELECT COUNT(rfd.regularFiltersUseDataId) FROM RegularFiltersUseData rfd WHERE rfd.rfDate  BETWEEN :pDate AND :sDate  AND rfd.filterType = :filterType  AND rfd.filterName = :filterName")
	 * int countRegFilterDataByFnameUName(@Param("filterName") String filterName, @Param("filterType") String filterType,@Param("pDate") String pDate,@Param("sDate") String sDate);
	 * @Query("SELECT AVG(actualReading) FROM RegularFiltersUseData WHERE waterInventory.waterInventoryId = :waterInvId AND filterName  = :filterName AND filterType = :filterType")
	 * Float getAvgActualReadingByWIdFNameFType(@Param("waterInvId") int waterInvId,@Param("filterName") String filterName,@Param("filterType") String filterType);
	 * @Query("SELECT rf FROM RegularFiltersUseData rf WHERE rf.rfDate=:date and rf.filterName=:filtername")
	 * List<RegFiltersUseData> RegularFiltersUseDataBydate(@Param("date")String fDate,@Param("filtername") String filtername);
	 */

	@Query("SELECT COALESCE(SUM(rf.actualReading),0) FROM RegFiltersUseData rf WHERE DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') =:date  AND rf.filterUseLabel = :filterUseLabel")
	float findActualReadingByDateAndLabel(@Param("date") String date, @Param("filterUseLabel") String filterUseLabel);

	@Query("SELECT COALESCE(SUM(f.actualReading),0) FROM RegFiltersUseData f WHERE f.filterUseLabel =:filterUseLabel AND DATE_FORMAT(date(f.createDateTime), '%Y-%m-%d') BETWEEN :fWeek AND :sWeek ORDER BY f.actualReading ASC")
	Float getSumActualReadingByFilterLabelAndDatesBetween(@Param("filterUseLabel") String filterUseLabel, @Param("fWeek") String fWeek, @Param("sWeek") String sWeek);

	// affected query by filter ...........visahl
	// @Query("SELECT rf.actualReading FROM RegFiltersUseData rf LEFT JOIN rf.filterUse fu LEFT JOIN fu.filters f WHERE f.filterType =:filterType AND fu.filterUseName=:filterName AND DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') BETWEEN :pDate AND :today ORDER BY rf.regFiltersUseDataId ASC")
	// List<Float> getActualReadingListByFilterTypeAndDatesBetween(@Param("filterType")String filterType,@Param("filterName")String filterName,@Param("pDate")String pDate,@Param("today")String today);

	// affected query by filter ...........visahl
	// @Query("SELECT COUNT(rf.regFiltersUseDataId) FROM RegFiltersUseData rf LEFT JOIN rf.filterUse fu LEFT JOIN fu.multipleFilter mf LEFT JOIN .filters f WHERE DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') BETWEEN :pDate AND :sDate AND f.filterType = :filterType AND fu.filterUseName = :filterName")
	// int findCountRegFilterUseByFilterType(@Param("filterType") String filterType, @Param("filterName") String filterName,@Param("pDate") String pDate,@Param("sDate") String sDate);

	@Query("SELECT rf FROM RegFiltersUseData rf WHERE DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	List<RegFiltersUseData> findByrfDateBetweenTwoDates(@Param("fDate") String fDate, @Param("lDate") String lDate);

	@Query("SELECT COALESCE(SUM(rf.actualReading),0) FROM RegFiltersUseData rf WHERE (EXTRACT(YEAR FROM DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d'))) =:year  AND rf.filterUseLabel = :filterUseLabel")
	Float sumActualReadingByYearAndFilterUseLabel(@Param("year") int year, @Param("filterUseLabel") String filterUseLabel);

	@Query("SELECT SUM(rf.actualReading) FROM  RegFiltersUseData rf  WHERE rf.filterUseLabel=:filterLabelName AND  DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') =:date")
	Float sumOfActualReadingByFilterUseLabel(@Param("date") String date, @Param("filterLabelName") String filterLabelName);

	@Query("SELECT rf.actualReading FROM RegFiltersUseData rf WHERE rf.filterUseLabel=:filterLabelName AND  DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') BETWEEN :pDate AND :today ORDER BY rf.actualReading ASC")
	List<Float> getActualReadingListByFilterUseLabelAndDatesBetween(@Param("filterLabelName") String filterLabelName, @Param("pDate") String pDate, @Param("today") String today);

	@Query("SELECT COUNT(rf.regFiltersUseDataId) FROM RegFiltersUseData rf  WHERE DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d')  BETWEEN :pDate AND :sDate AND rf.filterUseLabel=:filterLabelName")
	int findCountRegFilterUseByFilterType(@Param("filterLabelName") String filterLabelName, @Param("pDate") String pDate, @Param("sDate") String sDate);

	@Query("SELECT AVG(rfu.actualReading) FROM RegFiltersUseData rfu, FilterUse fu WHERE fu.filterUseLabel=rfu.filterUseLabel AND fu.filterUseType= :type AND DATE_FORMAT(date(rfu.createDateTime), '%Y-%m-%d') BETWEEN :dateFrom AND :dateTo")
	Float getActualReadingByRFDate(@Param("type") String type, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);
	// @Query("SELECT COUNT(rf.regFiltersUseDataId) FROM RegFiltersUseData rf LEFT JOIN rf.filterUse fu WHERE DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d') BETWEEN :pDate AND :sDate AND fu.filterUseType = :filterUseType AND fu.filterUseLabel = :filterUseLabelName")
	// int findCountRegFilterUseByFilterLabelName(@Param("filterUseType") String filterUseType, @Param("filterUseLabelName") String filterUseLabelName,@Param("pDate") String pDate,@Param("sDate") String sDate);
}
