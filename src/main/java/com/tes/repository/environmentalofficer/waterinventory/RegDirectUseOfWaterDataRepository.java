package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegDirectUseOfWaterData;

@Repository
public interface RegDirectUseOfWaterDataRepository extends JpaRepository<RegDirectUseOfWaterData, Long>
{

	/*
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType = 'Domestic' AND rwu.waterInventory.waterInventoryId = :wiId ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> regularWaterUseDataByDomestic(@Param("wiId") int wiId, Pageable pageable);
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType = 'Domestic' AND rwu.waterInventory.waterInventoryId = :wiId AND rwu.rWDate=:today ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> regularWaterUseDataByDomesticDate(@Param("wiId") int wiId, @Param("today") String date, Pageable pageable);
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType =:indName AND rwu.waterInventory.waterInventoryId=:wiId ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> getRegularWaterUseDataByIndName(@Param("indName") String indName, @Param("wiId") int wiId, Pageable pageable);
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType =:indName AND rwu.waterInventory.waterInventoryId =:wiId  AND rwu.rWDate=:date ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> getRegularWaterUseDataByIndNameDate(@Param("indName") String indName, @Param("wiId") int wiId, @Param("date") String date,Pageable pageable);
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType = 'Laundry' AND rwu.waterInventory.waterInventoryId=:wiId ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> regularWaterUseDataByLaundry(@Param("wiId") int wiId, Pageable pageable);
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType ='Laundry' AND rwu.waterInventory.waterInventoryId =:wiId  AND rwu.rWDate=:date ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> getregularWaterUseDataByLaundryDate(@Param("wiId") int wiId, @Param("date") String date, Pageable pageable);
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType = 'Fire Hydrant' AND rwu.waterInventory.waterInventoryId =:wiId ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> getregularWaterUseDataByFireHydrant(@Param("wiId") int wiId, Pageable pageable);
	 * @Query("SELECT rwu FROM RegularWaterUseData rwu WHERE rwu.sourceType = 'Fire Hydrant' AND rwu.waterInventory.waterInventoryId = :wiId AND rwu.rWDate =:date ORDER BY rwu.regularWaterUseDataId DESC")
	 * List<DirectUseOfWater> getregularWaterUseDataByFireHydrantDate(@Param("wiId") int wiId,@Param("date") String date, Pageable pageable);
	 * @Query("SELECT SUM(rw.actualReading) FROM RegularWaterUseData rw WHERE rw.sourceType = 'Process' AND EXTRACT(YEAR FROM rw.rWDate)  BETWEEN :esrYearFrom AND :esrYearTo AND EXTRACT(MONTH FROM rw.rWDate) = :month")
	 * Float actualReadingSumByRwDate(@Param("esrYearFrom") int esrYearFrom,@Param("esrYearTo") int esrYearTo,@Param("month") int month);
	 * @Query("SELECT SUM(rw.actualReading) FROM RegularWaterUseData rw WHERE (rw.sourceType = 'Cooling' OR rw.sourceType = 'Boiler' OR rw.sourceType = 'Equipment Washing')  AND EXTRACT(YEAR FROM rw.rWDate) BETWEEN :esrYearFrom AND :esrYearTo AND EXTRACT(MONTH FROM rw.rWDate) = :month")
	 * Float actualReadingCoolingSumByRwDate(@Param("esrYearFrom") int esrYearFrom,@Param("esrYearTo") int esrYearTo,@Param("month") int month);
	 * @Query("SELECT AVG(rw.actualReading) FROM RegularWaterUseData rw  WHERE rw.sourceType = 'Domestic'  AND EXTRACT(YEAR FROM rw.rWDate) BETWEEN :esrYearFrom  AND :esrYearTo AND EXTRACT(MONTH FROM rw.rWDate) = :month")
	 * Float avgActualReading(@Param("esrYearFrom") int esrYearFrom, @Param("esrYearTo") int esrYearTo,@Param("month") int month);
	 * @Query("SELECT AVG(rw.actualReading) FROM RegularWaterUseData rw WHERE  (rw.sourceType = 'Fire Hydrant' OR rw.sourceType ='Laundry') AND EXTRACT(YEAR FROM rw.rWDate) BETWEEN :esrYearFrom  AND :esrYearTo AND EXTRACT(MONTH FROM rw.rWDate) = :month")
	 * Float avgOthersActualReading(@Param("esrYearFrom")int esrYearFrom, @Param("esrYearTo")int esrYearTo,@Param("month") int month);
	 * @Modifying
	 * @Transactional
	 * @Query("UPDATE RegularWaterUseData rw SET rw.startReading = :start, rw.endReading = :end, rw.actualReading = :actual WHERE rw.regularWaterUseDataId = :id")
	 * int updateStartEndActualReading(@Param("start") float startReading,@Param("end") float endReading,@Param("actual") float actualReading,@Param("id") int id);
	 * @Query("SELECT rw FROM RegularWaterUseData rw WHERE rw.sourceType =:indName AND rw.waterInventory.waterInventoryId =:waterInvId AND rw.rWDate =:date ORDER BY rw.regularWaterUseDataId DESC ")
	 * DirectUseOfWater getRWUDByIndNameWaterInvDate(@Param("indName") String indName, @Param("waterInvId") int waterInvId, @Param("date") String date);
	 * @Query("SELECT rw FROM RegularWaterUseData rw WHERE rw.sourceType ='Laundry' AND rw.waterInventory.waterInventoryId =:WaterInvId AND rw.rWDate =:date ORDER BY rw.regularWaterUseDataId DESC")
	 * public DirectUseOfWater getRWUDBySTypeWiIdDate(@Param("WaterInvId") int WaterInvId, @Param("date") String date);
	 * //remain
	 * @Query(value="SELECT rwu.* FROM regular_water_use_data rwu WHERE rwu.source_type = 'Domestic' AND rwu.wi_id = :wiId ORDER BY rwu.id DESC limit 1", nativeQuery = true)
	 * DirectUseOfWater rWUdata(@Param("wiId") int wiId);
	 * @Query("SELECT COUNT(rwu.regularWaterUseDataId) FROM RegularWaterUseData rwu WHERE rwu.rWDate BETWEEN :prevDate AND :todayDate AND rwu.sourceType= :sourceType")
	 * int countRegWaterUseDataByDateSType(@Param("prevDate") String prevDate, @Param("todayDate") String todayDate,@Param("sourceType") String sourceType);
	 * @Query("SELECT MIN(EXTRACT(YEAR FROM rw.rWDate)) FROM RegularWaterUseData rw")
	 * int getMinYear();
	 * @Query("SELECT r.actualReading FROM RegularWaterUseData r WHERE r.rWDate = :date AND r.sourceType = :sourceType ORDER BY r.regularWaterUseDataId ASC")
	 * Float getActualReadingByDateAndSourceType(@Param("date") String date,@Param("sourceType") String sourceType);
	 * @Query("SELECT NEW RegularWaterUseData(r.actualReading) FROM RegularWaterUseData r WHERE r.rWDate = :date AND r.sourceType = :sourceType ORDER BY r.regularWaterUseDataId ASC")
	 * DirectUseOfWater getStartEndReadingByDateSourceType(@Param("date") String date,@Param("sourceType") String sourceType);
	 * @Query("SELECT COALESCE(SUM(rwu.actualReading),0) FROM RegularWaterUseData rwu  WHERE rwu.sourceType = :type AND rWDate BETWEEN :esrYearFrom AND :esrYearTo")
	 * Float getProcessSumActualReading(@Param("type") String type, @Param("esrYearFrom")String esrYearFrom, @Param("esrYearTo")String esrYearTo);
	 * @Query("SELECT COALESCE(AVG(rwu.actualReading),0) FROM RegularWaterUseData rwu  WHERE rwu.sourceType = 'Cooling' OR rwu.sourceType='Boiler' OR rwu.sourceType='Equipment Washing' AND rWDate BETWEEN :esrYearFrom AND :esrYearTo")
	 * Float getCoolingSumActualReading(@Param("esrYearFrom")String esrYearFrom, @Param("esrYearTo")String esrYearTo);
	 * @Query("SELECT COALESCE(AVG(rwu.actualReading),0) FROM RegularWaterUseData rwu  WHERE rwu.sourceType ='Domestic' AND rWDate BETWEEN :esrYearFrom AND :esrYearTo")
	 * Float getDomesticSumActualReading(@Param("esrYearFrom")String esrYearFrom, @Param("esrYearTo")String esrYearTo);
	 * @Query("SELECT COALESCE(AVG(rwu.actualReading),0) FROM RegularWaterUseData rwu  WHERE rwu.sourceType ='Fire Hydrant' OR rwu.sourceType ='Laundry' AND rWDate BETWEEN :esrYearFrom AND :esrYearTo")
	 * Float getFireHydrantAndLaundryAvgActualReading(@Param("esrYearFrom")String esrYearFrom, @Param("esrYearTo")String esrYearTo);
	 * @Query("SELECT DISTINCT(EXTRACT(YEAR FROM rWDate)) FROM RegularWaterUseData ORDER BY rWDate ASC")
	 * List<Integer> getYear();
	 * @Query("SELECT rs.actualReading FROM RegularWaterUseData rs WHERE rs.sourceType =:sourceType AND rs.rWDate BETWEEN :fDate AND :sDate ORDER BY rs.actualReading ASC")
	 * List<Float> getListOfActualReadingBySourceTypeAndBetweenDate(@Param("sourceType") String sourceType,@Param("fDate") String fDate,@Param("sDate") String sDate);
	 * @Query("SELECT rw FROM RegularWaterUseData rw WHERE rw.rWDate BETWEEN :fDate AND :lDate")
	 * List<DirectUseOfWater> findByrWDateBetweenTwoDates(@Param("fDate")String fDate,@Param("lDate")String lDate);
	 * @Query("SELECT AVG(actualReading) FROM RegularWaterUseData WHERE waterInventory.waterInventoryId = :waterInvId AND sourceType = :sourceType")
	 * Float getAvgActualReadingByWaterInvIdSourceType(@Param("waterInvId") int waterInvId,@Param("sourceType") String sourceType);
	 * @Query("SELECT r FROM RegularWaterUseData r where sourceType=:indName and rWDate=:date")
	 * DirectUseOfWater getdatawaterUse(@Param("indName")String indName,@Param("date")String date);
	 */

	// @Query("SELECT rd.actualReading FROM RegDirectUseOfWaterData rd LEFT JOIN rd.directUseOfWater du WHERE du.whereToUse =:sourceType AND DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :sDate ORDER BY rd.actualReading ASC")
	// List<Float>getListOfActualReadingByWhereToUseAndBetweenDate(@Param("sourceType") String sourceType,@Param("fDate") String fDate,@Param("sDate") String sDate);
	//
	// @Query("SELECT rd.actualReading FROM RegDirectUseOfWaterData rd LEFT JOIN rd.directUseOfWater du WHERE DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d')= :date AND du.whereToUse = :sourceType ORDER BY rd.regDirectUseOfWaterDataId ASC")
	// Float getActualReadingByDateAndSourceType(@Param("date") String date,@Param("sourceType") String sourceType);
	//
	// @Query("SELECT rd FROM RegDirectUseOfWaterData rd WHERE DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	// List<RegDirectUseOfWaterData> findByrWDateBetweenTwoDates(@Param("fDate")String fDate,@Param("lDate")String lDate);
	//
	// @Query("SELECT sum(rd.actualReading) FROM RegDirectUseOfWaterData rd LEFT JOIN rd.directUseOfWater du WHERE du.whereToUse =:sourceType AND DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :sDate ORDER BY rd.actualReading ASC")
	// Float getActualReadingBySourceTypeAndBetweenDate(@Param("sourceType") String sourceType,@Param("fDate") String fDate,@Param("sDate") String sDate);
	//
	// @Query("SELECT sum(rd.actualReading) FROM RegDirectUseOfWaterData rd LEFT JOIN rd.directUseOfWater du WHERE (EXTRACT(YEAR FROM DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d'))) =:year AND (EXTRACT(MONTH FROM DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d'))) =:month AND du.whereToUse = :sourceType ORDER BY rd.regDirectUseOfWaterDataId ASC")
	// Float actualReadingByYearSourceTypeMonth(@Param("year") int year,@Param("sourceType") String sourceType,@Param("month") int month);
	//
	// @Query("SELECT sum(rd.actualReading) FROM RegDirectUseOfWaterData rd LEFT JOIN rd.directUseOfWater du WHERE (EXTRACT(YEAR FROM DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d'))) =:year AND du.whereToUse = :sourceType ORDER BY rd.regDirectUseOfWaterDataId ASC")
	// Float getSumOfActualReadingByYearAndSType(@Param("year") int year,@Param("sourceType") String sourceType);

	@Override
	@SuppressWarnings("unchecked")
	RegDirectUseOfWaterData save(RegDirectUseOfWaterData regularWaterUseData);

	@Query("SELECT rdu from RegDirectUseOfWaterData rdu WHERE DATE_FORMAT(date(rdu.createDateTime), '%Y-%m-%d')=:selectedDate")
	List<RegDirectUseOfWaterData> getRegDirectUseOfWaterDataBySelectedDate(@Param("selectedDate") String selectedDate);

	@Query("SELECT sum(rd.actualReading) FROM RegDirectUseOfWaterData rd WHERE (EXTRACT(YEAR FROM DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d'))) =:year AND rd.whereToUse = :sourceType ORDER BY rd.regDirectUseOfWaterDataId ASC")
	Float getSumOfActualReadingByYearAndSType(@Param("year") int year, @Param("sourceType") String sourceType);

	@Query("SELECT sum(rd.actualReading) FROM RegDirectUseOfWaterData rd WHERE rd.whereToUse =:sourceType AND DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :sDate ORDER BY rd.actualReading ASC")
	Float getActualReadingBySourceTypeAndBetweenDate(@Param("sourceType") String sourceType, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT sum(rd.actualReading) FROM RegDirectUseOfWaterData rd WHERE (EXTRACT(YEAR FROM DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d'))) =:year AND (EXTRACT(MONTH FROM DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d'))) =:month AND rd.whereToUse = :sourceType ORDER BY rd.regDirectUseOfWaterDataId ASC")
	Float actualReadingByYearSourceTypeMonth(@Param("year") int year, @Param("sourceType") String sourceType, @Param("month") int month);

	@Query("SELECT rd.actualReading FROM RegDirectUseOfWaterData rd  WHERE DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d')= :date AND rd.whereToUse like (:sourceType%) ORDER BY rd.regDirectUseOfWaterDataId ASC")
	Float getActualReadingByDateAndSourceType(@Param("date") String date, @Param("sourceType") String sourceType);

	@Query("SELECT rd.actualReading FROM RegDirectUseOfWaterData rd  WHERE rd.whereToUse =:sourceType AND DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :sDate ORDER BY rd.actualReading ASC")
	List<Float> getListOfActualReadingByWhereToUseAndBetweenDate(@Param("sourceType") String sourceType, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT rd FROM RegDirectUseOfWaterData rd WHERE  DATE_FORMAT(date(rd.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	List<RegDirectUseOfWaterData> findByrWDateBetweenTwoDates(@Param("fDate") String fDate, @Param("lDate") String lDate);

	@Query("SELECT rd FROM RegDirectUseOfWaterData rd WHERE rd.whereToUse=:whereToUse ORDER BY rd.regDirectUseOfWaterDataId DESC")
	List<RegDirectUseOfWaterData> getRegDirectUseOfWaterData(@Param("whereToUse") String whereToUse, Pageable pageable);

	@Query("SELECT AVG(rdu.actualReading) FROM RegDirectUseOfWaterData rdu, DirectUseOfWater du WHERE rdu.whereToUse=du.whereToUse AND du.typeOfUse= :type AND DATE_FORMAT(date(rdu.createDateTime), '%Y-%m-%d') BETWEEN :dateFrom AND :dateTo")
	Float getActualReadingbydate(@Param("type") String type, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);
}
