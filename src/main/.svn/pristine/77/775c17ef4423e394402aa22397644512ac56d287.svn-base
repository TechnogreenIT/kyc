package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegWastewaterRecycle;

@Repository
public interface RegWastewaterRecycleRepository extends JpaRepository<RegWastewaterRecycle, Long>
{

	@SuppressWarnings("unchecked")
	RegWastewaterRecycle save(RegWastewaterRecycle regWastewaterRecycle);

	@Query("SELECT rwr FROM RegWastewaterRecycle rwr WHERE rwr.wastewaterRecycle.wastewaterRecycleId=:wastewaterRecycleId ORDER BY rwr.regWastewaterRecycleId DESC")
	List<RegWastewaterRecycle> getRegWastewaterRecycleByRecycleId(@Param("wastewaterRecycleId") int wwrId, Pageable pageable);

	@Query("SELECT rwr.actualReading FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts WHERE DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d') =:date  AND wts.label =:treatType AND wwr.recycledTo =:recycledTo")
	Float findActualReadingByRfDatetreatTypeAndrecycledTo(@Param("date") String date, @Param("treatType") String treatType, @Param("recycledTo") String recycledTo);

	@Query("SELECT rwr.actualReading FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts  WHERE wts.label =:treatType AND wwr.recycledTo =:recycledTo AND DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d') BETWEEN :pDate AND :today ORDER BY rwr.regWastewaterRecycleId ASC")
	List<Float> getActualReadingListBytreaTypeAndDatesBetween(@Param("treatType") String treatType, @Param("recycledTo") String recycledTo, @Param("pDate") String pDate, @Param("today") String today);

	@Query("SELECT COUNT(rwr.regWastewaterRecycleId) FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts  WHERE wts.label =:treatType AND wwr.recycledTo =:recycledTo AND DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d') BETWEEN  :pDate AND :todayDate ")
	int countRegRecycleDataDateBtwDatesBytreatLabel(@Param("pDate") String pDate, @Param("todayDate") String todayDate, @Param("treatType") String treatType, @Param("recycledTo") String recycledTo);

	@Query("SELECT rwr FROM RegWastewaterRecycle rwr WHERE DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d') BETWEEN :fDate AND :lDate")
	List<RegWastewaterRecycle> findAllRegWasteWaterRecycleBetweenTwoDates(@Param("fDate") String fDate, @Param("lDate") String lDate);

	@Query("SELECT rwr.actualReading FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr WHERE wwr.wastewaterRecycleId= :id AND DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d') =:date")
	Float getActualreadingById(@Param("id") int id, @Param("date") String date);

	@Query("SELECT rwr.actualReading FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts  WHERE wwr.wastewaterRecycleId =:id AND DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d') BETWEEN :fWeek AND :sWeek")
	Float getActualReadingByIdandWeeks(@Param("id") int id, @Param("fWeek") String fWeek, @Param("sWeek") String sWeek);

	@Query("SELECT rwr.actualReading FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr WHERE wwr.wastewaterRecycleId= :id And (EXTRACT(YEAR FROM DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d')))= :year AND (EXTRACT(MONTH FROM DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d')))= :month ")
	Float getActualReadingByIdandMonth(@Param("id") int id, @Param("year") int year, @Param("month") int month);

	@Query("SELECT sum(rwr.actualReading) FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr WHERE wwr.wastewaterRecycleId= :id And (EXTRACT(YEAR FROM DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d')))= :year")
	Float getActualReadingByIdandYear(@Param("id") int id, @Param("year") int year);

	@Query("SELECT rwr from RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wt where DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d')= :selectedDate and wt.wastewaterTreatmentId= :wastewaterTreatmentId")
	List<RegWastewaterRecycle> getRegWastewaterRecycleDataBySelectedDate(@Param("selectedDate") String selectedDate, @Param("wastewaterTreatmentId") int wastewaterTreatmentId);

	@Query("SELECT rwr.actualReading FROM RegWastewaterRecycle rwr LEFT JOIN rwr.wastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts WHERE DATE_FORMAT(date(rwr.createDateTime), '%Y-%m-%d') =:date AND wwr.recycledTo =:recycledTo")
	Float findActualReadingByRfDateAndrecycledTo(@Param("date") String date, @Param("recycledTo") String recycledTo);

}
