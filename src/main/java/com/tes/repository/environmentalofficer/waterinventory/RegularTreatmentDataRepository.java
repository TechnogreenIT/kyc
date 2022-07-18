package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegularTreatmentData;

@Repository
public interface RegularTreatmentDataRepository extends JpaRepository<RegularTreatmentData, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegularTreatmentData save(RegularTreatmentData regularTreatMentData);

	@Query("SELECT rt FROM RegularTreatmentData rt where rt.wastewaterTreatment.wastewaterTreatmentId=:wastewaterTreatmentId ORDER BY rt.regularTreatmentDataId DESC")
	List<RegularTreatmentData> getRegularTreatementDataByDate(@Param("wastewaterTreatmentId") int wastewaterTreatmentId, Pageable pageable);

	@Query("select sum(rtd.actualReading) from RegularTreatmentData rtd LEFT JOIN rtd.wastewaterTreatment wwt where wwt.wastewaterTreatmentId= :id and DATE_FORMAT(date(rtd.createDateTime), '%Y-%m-%d')= :sDate")
	Float getTreatTypeStartEndReadingByDate(@Param("id") int id, @Param("sDate") String sDate);

	@Query("select NEW RegularTreatmentData(rtd.actualReading,rtd.energyAvg) from RegularTreatmentData rtd LEFT JOIN rtd.wastewaterTreatment wwt where wwt.wastewaterTreatmentId= :id AND DATE_FORMAT(date(rtd.createDateTime), '%Y-%m-%d') BETWEEN :fWeek AND :sWeek")
	RegularTreatmentData getActualReadingByWWTIdAndBetweenDate(@Param("id") int id, @Param("fWeek") String fWeek, @Param("sWeek") String sWeek);

	@Query("select NEW RegularTreatmentData(rtd.actualReading,rtd.energyAvg) from RegularTreatmentData rtd LEFT JOIN rtd.wastewaterTreatment wwt where wwt.wastewaterTreatmentId= :id AND (EXTRACT(YEAR FROM DATE_FORMAT(date(rtd.createDateTime), '%Y-%m-%d')))= :year AND (EXTRACT(MONTH FROM DATE_FORMAT(date(rtd.createDateTime), '%Y-%m-%d')))= :month")
	RegularTreatmentData getActualReadingByWWTIdAndYearMonth(@Param("id") int id, @Param("year") int year, @Param("month") int month);

	@Query("select NEW RegularTreatmentData(rtd.actualReading,rtd.energyAvg) from RegularTreatmentData rtd LEFT JOIN rtd.wastewaterTreatment wwt where wwt.wastewaterTreatmentId= :id AND (EXTRACT(YEAR FROM DATE_FORMAT(date(rtd.createDateTime), '%Y-%m-%d')))= :year")
	RegularTreatmentData getActualReadingByWWTIdAndYear(@Param("id") int id, @Param("year") int year);

	@Query("select NEW RegularTreatmentData(rtd.actualReading,rtd.energyAvg) from RegularTreatmentData rtd LEFT JOIN rtd.wastewaterTreatment wwt where wwt.wastewaterTreatmentId= :id and DATE_FORMAT(date(rtd.createDateTime), '%Y-%m-%d')= :sDate")
	RegularTreatmentData getActualEnergyReadingByIdAndDate(@Param("id") int id, @Param("sDate") String sDate);

	@Query("SELECT COALESCE(SUM(rt.actualReading),0) FROM RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt WHERE DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d')= :date AND wt.label = :label ORDER BY rt.regularTreatmentDataId ASC")
	Float getActualReadingByDateAndTreatmentTypeLabel(@Param("date") String date, @Param("label") String label);

	@Query("SELECT COALESCE(SUM(rt.energyAvg),0) FROM RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt WHERE DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d')= :date AND wt.label = :label ORDER BY rt.regularTreatmentDataId ASC")
	Float getEnergyReadingByDateAndTreatmentTypeLabel(@Param("date") String date, @Param("label") String label);

	@Query("SELECT rt FROM RegularTreatmentData rt WHERE DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	List<RegularTreatmentData> findAllRegTreatmentDataBetweenTwoDates(@Param("fDate") String fDate, @Param("lDate") String lDate);

	@Query("SELECT rt.actualReading FROM RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt WHERE wt.label = :label  AND  DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d')  BETWEEN :fDate AND :sDate ORDER BY rt.regularTreatmentDataId ASC")
	List<Float> findActualReadingListByTreatLabelAndDatesBetween(@Param("label") String label, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT rt.energyAvg FROM RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt WHERE wt.label = :label  AND  DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d')  BETWEEN :fDate AND :sDate ORDER BY rt.regularTreatmentDataId ASC")
	List<Float> findEnergyReadingListByTreatLabelAndDatesBetween(@Param("label") String label, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT COUNT(rt.regularTreatmentDataId) FROM RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt WHERE DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d') BETWEEN  :pDate AND :todayDate AND  wt.label = :label")
	int countRegTreatmentDataDateBtwDatesBytreatLabel(@Param("pDate") String pDate, @Param("todayDate") String todayDate, @Param("label") String label);

	@Query("SELECT rt from RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt where DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d')=:selectedDate")
	List<RegularTreatmentData> getRegTreatmentDataBySelectedDate(@Param("selectedDate") String selectedDate);

	@Query("SELECT rt from RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt WHERE DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d')=:date AND wt.treatmentType=:treatType AND wt.label=:treatTypeLabel")
	List<RegularTreatmentData> getRegTreatmentDataByTreatmentTypeAndDate(@Param("date") String date, @Param("treatType") String treatType, @Param("treatTypeLabel") String treatTypeLabel);

	@Query("SELECT AVG(rt.actualReading) FROM RegularTreatmentData rt LEFT JOIN rt.wastewaterTreatment wt WHERE wt.treatmentType= :type AND DATE_FORMAT(date(rt.createDateTime), '%Y-%m-%d') BETWEEN :dateFrom AND :dateTo")
	Float getActualReadingByTypeAndDate(@Param("type") String type, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);
	/*
	 * SELECT COALESCE(SUM(rf.actualReading),0) FROM RegFiltersUseData rf LEFT JOIN
	 * rf.filterUse fu LEFT JOIN fu.filters f WHERE f.filterType =:filterType AND
	 * fu.filterUseName=:filter AND DATE_FORMAT(date(rf.createDateTime), '%Y-%m-%d')
	 * BETWEEN :fWeek AND :sWeek ORDER BY rf.actualReading ASC
	 */

	/*
	 * @Query("SELECT rt FROM RegularTreatmentData rt  WHERE rt.treatmentType =:treatmentType AND rt.waterInventory.waterInventoryId = :wiId ORDER BY rt.regularTreatmentDataId DESC"
	 * ) List<RegularTreatmentData>
	 * getRegularTreatmentDataByTreatmentType(@Param("treatmentType") String
	 * treatmentType, @Param("wiId") int wiId, Pageable pageable);
	 * @Query("SELECT rt FROM RegularTreatmentData rt  WHERE rt.treatmentType = :treatmentType AND rt.waterInventory.waterInventoryId = :wiId  AND rt.rtDate = :date ORDER BY rt.regularTreatmentDataId DESC "
	 * ) List<RegularTreatmentData>
	 * rtDataByTreatmentTypeDate(@Param("treatmentType") String
	 * treatmentType, @Param("wiId") int wiId, @Param("date") String date,Pageable
	 * pageable) ;
	 * @Query("SELECT rt FROM RegularTreatmentData rt  WHERE rt.treatmentType = :type AND rt.waterInventory.waterInventoryId = :waterInvId  ORDER BY rt.regularTreatmentDataId DESC "
	 * ) List<RegularTreatmentData> getRegularTreatmentData(@Param("type") String
	 * type,@Param("waterInvId") int waterInvId,Pageable pageable);
	 * @Query("SELECT rt FROM RegularTreatmentData rt  WHERE treatmentType = :type AND rt.waterInventory.waterInventoryId = :waterInvId  AND rt.rtDate = :today ORDER BY rt.regularTreatmentDataId DESC"
	 * ) List<RegularTreatmentData> rtDataByDate(@Param("type") String
	 * type, @Param("waterInvId") int waterInvId, @Param("today") String
	 * today,Pageable pageable);
	 * @Query("SELECT NEW RegularTreatmentData(r.treatmentType,r.actualReading)  FROM RegularTreatmentData r WHERE r.rtDate = :date AND r.treatmentType= :treatmentType ORDER BY r.regularTreatmentDataId ASC"
	 * ) RegularTreatmentData getTreatTypeStartEndReadingByDate(@Param("date")
	 * String date,@Param("treatmentType") String treatmentType);
	 * @Query("SELECT rt.treatmentType FROM RegularTreatmentData rt WHERE rt.type= :type AND rt.rtDate = :date"
	 * ) List<String> getTreatmentTypeByTypeAndDate(@Param("type") String
	 * type,@Param("date") String date);
	 * @Query("SELECT SUM(r.actualReading) FROM RegularTreatmentData r WHERE  r.treatmentType = :treatmentType AND r.rtDate between :fDate AND :sDate ORDER BY r.regularTreatmentDataId ASC"
	 * ) Float getActualReadingByTreatmentTypeBetweenDates(@Param("treatmentType")
	 * String treatmentType,@Param("fDate") String fDate,@Param("sDate") String
	 * sDate);
	 * @Query("SELECT rt.treatmentType FROM RegularTreatmentData rt WHERE rt.type= :type AND rt.rtDate between :fDate AND :sDate"
	 * ) List<String> getTreatmentTypeByTypeAndBetweenDates(@Param("type") String
	 * type,@Param("fDate") String fDate,@Param("sDate") String sDate);
	 * @Query("SELECT rt.treatmentType FROM RegularTreatmentData rt WHERE rt.type= :type AND (EXTRACT(MONTH FROM rt.rtDate)) = :month "
	 * ) List<String> getTreatmentTypeByTypeAndMonth(@Param("type") String
	 * type,@Param("month") int month);
	 * @Query("SELECT SUM(actualReading) FROM RegularTreatmentData WHERE (EXTRACT(YEAR FROM rtDate)) = :year AND treatmentType = :type AND (EXTRACT(MONTH FROM rtDate)) = :month ORDER BY regularTreatmentDataId ASC"
	 * ) Float sumActualReadingByYearTypeMonth(@Param("year") int
	 * year,@Param("type") String type,@Param("month") int month);
	 * @Query("SELECT rt.treatmentType FROM RegularTreatmentData rt WHERE rt.type= :type AND (EXTRACT(Year FROM rt.rtDate)) = :Year "
	 * ) List<String> getTreatmentTypeByTypeAndYear(@Param("type") String
	 * type,@Param("Year") int Year);
	 * @Query("SELECT SUM(actualReading) FROM RegularTreatmentData WHERE (EXTRACT(YEAR FROM rtDate)) = :year AND treatmentType= :type"
	 * ) Float getActualReadingByTypeAndYear(@Param("type") String
	 * type,@Param("year") int year);
	 * @Query("SELECT COALESCE(AVG(rt.actualReading),0) FROM RegularTreatmentData rt where rt.type=:type and rt.rtDate between :dateFrom and :dateTo"
	 * ) Float getAvgActualReadingByYearandType(@Param("type") String
	 * type, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo );
	 * @Query("SELECT rt FROM RegularTreatmentData rt where rtDate= :date")
	 * List<RegularTreatmentData> RegularTretmentDataBydate(@Param("date") String
	 * date);
	 */
}
