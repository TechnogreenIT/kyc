package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegPrefilter;

@Repository
public interface RegPrefilterRepository extends JpaRepository<RegPrefilter, Long>
{

	@Query("SELECT SUM(rp.actualReading) FROM RegPrefilter rp LEFT JOIN rp.prefilter p  LEFT JOIN p.waterSource ws WHERE DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d') =:date AND ws.sourceName=:sourceName")
	Float getActualReadingByRsDateAndPrefilter(@Param("date") String date, @Param("sourceName") String sourceName);

	@Query("SELECT rp.actualReading FROM RegPrefilter rp LEFT JOIN  rp.prefilter p LEFT JOIN p.waterSource ws WHERE ws.sourceName = :sourceName AND  DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d')  BETWEEN :fDate AND :sDate ORDER BY p.prefilterId ASC")
	List<Float> findActualReadingListPrefilterBySourceNameAndDatesBetween(@Param("sourceName") String sourceName, @Param("fDate") String fDate, @Param("sDate") String sDate);

	@Query("SELECT COUNT(p.prefilterId) FROM  RegPrefilter rp LEFT JOIN  rp.prefilter p LEFT JOIN p.waterSource ws WHERE DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d') BETWEEN  :pDate AND :todayDate AND ws.sourceName = :sourceName")
	int countRegPreFilterDateBtwDatesBySourceName(@Param("pDate") String pDate, @Param("todayDate") String todayDate, @Param("sourceName") String sourceName);

	@Query("SELECT rp FROM RegPrefilter rp WHERE DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	List<RegPrefilter> findByPrefilterBetweenTwoDates(@Param("fDate") String fDate, @Param("lDate") String lDate);

	@SuppressWarnings("unchecked")
	RegPrefilter save(RegPrefilter regPrefilter);

	@Query("SELECT rpf FROM RegPrefilter rpf WHERE rpf.prefilter.prefilterId=:rpfId ORDER BY rpf.regprefilterId DESC")
	List<RegPrefilter> getregPrefilterDataByPrefilterId(@Param("rpfId") int rpfId, Pageable pageable);

	@Query("SELECT rp.actualReading FROM RegPrefilter rp left join rp.prefilter p WHERE p.prefilterId= :id and DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d')= :date")
	Float getRegPrefilterByIdAndDate(@Param("id") int id, @Param("date") String date);

	@Query("SELECT sum(rp.actualReading) FROM RegPrefilter rp left join rp.prefilter p WHERE p.prefilterId= :id and DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d')  BETWEEN :fWeek AND :sWeek")
	public Float getRegPrefilterByIdAndBetweenDates(@Param("id") int id, @Param("fWeek") String fWeek, @Param("sWeek") String sWeek);

	@Query("SELECT sum(rp.actualReading) FROM RegPrefilter rp left join rp.prefilter p WHERE p.prefilterId= :id and (EXTRACT(MONTH FROM DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d'))) = :month")
	public Float getRegPrefilterByIdAndMonth(@Param("id") int id, @Param("month") int month);

	@Query("SELECT sum(rp.actualReading) FROM RegPrefilter rp left join rp.prefilter p WHERE p.prefilterId= :id and (EXTRACT(YEAR FROM DATE_FORMAT(date(rp.createDateTime), '%Y-%m-%d'))) = :year")
	public Float getRegPrefilterByIdAndYear(@Param("id") int id, @Param("year") int year);

	@Query("SELECT rpf from RegPrefilter rpf LEFT JOIN rpf.prefilter pf where DATE_FORMAT(date(rpf.createDateTime), '%Y-%m-%d')= :selectedDate")
	List<RegPrefilter> getRegPrefilterBySelectedDate(@Param("selectedDate") String selectedDate);
}
