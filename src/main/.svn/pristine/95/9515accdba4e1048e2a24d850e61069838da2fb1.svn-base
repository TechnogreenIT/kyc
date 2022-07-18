package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegWaterSourceData;

@Repository
public interface RegWaterSourceDataRepository extends JpaRepository<RegWaterSourceData, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegWaterSourceData save(RegWaterSourceData regularSourceData);

	@Query("SELECT MIN(EXTRACT(YEAR FROM rs.createDateTime)) FROM RegWaterSourceData rs")
	int getRegSourceYearOfLastInput();

	@Query("SELECT SUM(rs.actualReading) FROM RegWaterSourceData rs LEFT JOIN rs.waterSource ws  WHERE DATE_FORMAT(date(rs.createDateTime), '%Y-%m-%d') =:sdate AND ws.sourceName =:sourceName")
	Float getActualReadingByRsDateAndSourceName(@Param("sdate") String sdate, @Param("sourceName") String sourceName);

	@Query("SELECT COALESCE(COUNT(rs.regWaterSourceDataId),0) FROM RegWaterSourceData rs LEFT JOIN rs.waterSource ws WHERE DATE_FORMAT(date(rs.createDateTime), '%Y-%m-%d') BETWEEN :pDate AND :today AND ws.sourceName =:sourceName")
	int getCountByRsDateAndSourceName(@Param("pDate") String pDate, @Param("today") String today, @Param("sourceName") String sourceName);

	@Query("SELECT COALESCE(SUM(r.actualReading),0) FROM RegWaterSourceData r WHERE  DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d') = :date")
	Float getActualReadingSumForDate(@Param("date") String date);

	@Query("SELECT COALESCE(SUM(r.actualReading),0) FROM RegWaterSourceData r LEFT JOIN r.waterSource ws WHERE ws.sourceName = :sourceName AND DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d') BETWEEN :fWeek AND :sWeek  ORDER BY r.regWaterSourceDataId ASC")
	Float getSumActualReadingBySourceNameAndBetweenDates(@Param("sourceName") String sourceName, @Param("fWeek") String fWeek, @Param("sWeek") String sWeek);

	@Query("SELECT rs FROM RegWaterSourceData rs WHERE rs.waterSource.waterSourceId= :wsId  ORDER BY rs.regWaterSourceDataId DESC")
	List<RegWaterSourceData> regularSourceDataBySourceId(@Param("wsId") int wsId, Pageable pageable);

	@Query("SELECT r.actualReading FROM RegWaterSourceData r LEFT JOIN r.waterSource ws WHERE ws.sourceName = :sourceName AND  DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d')  BETWEEN :fDate AND :sDate ORDER BY r.regWaterSourceDataId ASC")
	List<Float> findActualReadingListBySourceNameAndDatesBetween(@Param("sourceName") String sourceName, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT rs FROM RegWaterSourceData rs WHERE DATE_FORMAT(date(rs.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	List<RegWaterSourceData> getAllRegularSourceDataBetweenDates(@Param("fDate") String fDate, @Param("lDate") String lDate);

	@Query("SELECT SUM(r.actualReading) FROM RegWaterSourceData r LEFT JOIN r.waterSource ws WHERE ws.sourceName=:sourceName AND (EXTRACT(YEAR FROM DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d'))) = :year AND (EXTRACT(MONTH FROM DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d'))) = :month ORDER BY r.regWaterSourceDataId ASC")
	Float getSumActualByYearMonth(@Param("year") int year, @Param("month") int month, @Param("sourceName") String sourceName);

	@Query("SELECT SUM(r.actualReading) FROM RegWaterSourceData r LEFT JOIN r.waterSource ws WHERE (EXTRACT(YEAR FROM DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d'))) =:year and ws.sourceName= :sourceName ORDER BY r.regWaterSourceDataId ASC")
	Float getSumOfActualReadingByYearAndSourceName(@Param("year") int year, @Param("sourceName") String sourceName);

	@Query(value = "select extract(Year from rw.create_date_time) from reg_water_source_data rw\r\n" +
			"union\r\n" +
			"select extract(Year from rap.samp_amb) from reg_amb_poll rap\r\n" +
			"union\r\n" +
			"select extract(Year from rapc.apc_date) from reg_apc rapc\r\n" +
			"union\r\n" +
			"select extract(Year from rep.samp_e) from reg_eff_poll rep\r\n" +
			"union\r\n" +
			"select extract(Year from rsp.samp_s) from reg_sew_poll rsp\r\n" +
			"union\r\n" +
			"select extract(Year from rd.input_date) from regular_data rd\r\n" +
			"union\r\n" +
			"select extract(Year from rpr.reason_date) from reg_poll_reasons rpr\r\n" +
			"union\r\n" +
			"select extract(Year from rst.samp_st) from reg_st_poll rst", nativeQuery = true)
	List<Integer> getWaterMinYear();

	@Query("SELECT rs from RegWaterSourceData rs LEFT JOIN rs.waterSource ws where DATE_FORMAT(date(rs.createDateTime), '%Y-%m-%d')=:selectedDate")
	List<RegWaterSourceData> getRegWaterSourceDataBySelectedDate(@Param("selectedDate") String selectedDate);

	@Query("SELECT COALESCE(SUM(r.actualReading),0) FROM RegWaterSourceData r WHERE (EXTRACT(MONTH FROM DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d'))) =:month AND (EXTRACT(YEAR FROM DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d'))) =:year ORDER BY r.regWaterSourceDataId ASC")
	Float getSumOfActualReadingByMonthAndYear(@Param("month") int month, @Param("year") int year);

	@Query("SELECT DISTINCT((EXTRACT(MONTH FROM DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d')))) FROM RegWaterSourceData r ORDER BY r.createDateTime ASC")
	List<Integer> getRegularSourceDataYearList();

	@Query("SELECT SUM(r.actualReading) FROM RegWaterSourceData r WHERE (EXTRACT(YEAR FROM DATE_FORMAT(date(r.createDateTime), '%Y-%m-%d'))) =:year ORDER BY r.regWaterSourceDataId ASC")
	Float getSumOfActualReadingByYear(@Param("year") int year);

	@Query("SELECT SUM(r.actualReading) FROM RegWaterSourceData r    LEFT JOIN r.waterSource ws LEFT JOIN ws.waterInventory wi 	WHERE  wi.waterInventoryId= :waterInvId ")
	Float getSumActualReadingByWaterInvId(@Param("waterInvId") int waterInvId);
}
