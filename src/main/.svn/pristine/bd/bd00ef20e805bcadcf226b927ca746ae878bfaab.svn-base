package com.tes.repository.thirdparty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.RegStPoll;

@Repository
public interface RegStackPollRepository extends JpaRepository<RegStPoll, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	public RegStPoll save(RegStPoll regStPoll);

	@Query("SELECT DISTINCT(EXTRACT(YEAR FROM rs.sampSt)) FROM RegStPoll rs ORDER BY rs.sampSt ASC")
	public List<Integer> getYearFromStack();

	@Query("SELECT DISTINCT rs.sampSt FROM RegStPoll rs ORDER BY rs.sampSt ASC")
	public List<String> getYearFromStackDate();

	@Query("SELECT DISTINCT(EXTRACT(MONTH FROM rs.sampSt)) FROM RegStPoll rs WHERE (EXTRACT(YEAR FROM rs.sampSt)) =?1 ORDER BY rs.sampSt ASC")
	public List<Integer> getMonthFromStack(int yr);

	// select * from reg_st_poll rs ,stack s where rs.stack_id=s.id and s.consent_to_operate='Yes' and (extract(year from rs.samp_st))='2018' order by rs.samp_st asc;
	// @Query("SELECT DISTINCT(rs.sampSt) FROM RegStPoll rs, Stack s WHERE rs.stack.stackId=s.stackId and s.consentToOperate='Yes' and (EXTRACT(YEAR FROM rs.sampSt)) = :year ORDER BY rs.sampSt ASC")
	@Query("SELECT DISTINCT(rs.sampSt)  FROM RegStPoll rs,StackOp sp  WHERE rs.stack.stackId=sp.stack.stackId AND (EXTRACT(YEAR FROM rs.sampSt)) = :year ORDER BY rs.sampSt ASC")
	public List<String> getDateFromStack(@Param("year") int year);

	@Query("SELECT DISTINCT(r) from RegStPoll r, Stack s WHERE   r.sampSt = :date")
	public List<RegStPoll> getDateFromStackDate(@Param("date") String date);

	String query2 = "SELECT DISTINCT(r) from RegStPoll r, Stack s WHERE s.stackId= :stackId AND r.stackName = s.stackName AND r.attachTo = s.attachedTo AND EXTRACT(MONTH FROM r.sampSt) = :month AND (EXTRACT(YEAR FROM r.sampSt)) = :year";

	@Query(value = query2, nativeQuery = false)
	List<RegStPoll> findRegStackInfoToShow(@Param("stackId") int stackId, @Param("month") int month, @Param("year") int year);

	// JEMS >> remaining >>
	/* String getConcPollAvgByPName = "SELECT AVG(conc_poll) FROM reg_st_poll, stack_poll_data sp WHERE sp.poll_name = :pollName AND r.stack_name = :stName AND r.attached_to = :attachedTo AND EXTRACT(YEAR FROM r.samp_st) = :selectedYear AND EXTRACT(MONTH FROM r.samp_st) = :esrMonth"; */

	@Query(value = "SELECT AVG(sp.concPoll) FROM StackPollData sp left join sp.regStPoll r where sp.pollName = :pollName AND  r.stackName = :stName AND r.attachTo = :attachedTo AND (EXTRACT(YEAR FROM r.sampSt) = :selectedYear OR EXTRACT(YEAR FROM r.sampSt) = :selectedYear1) AND EXTRACT(MONTH FROM r.sampSt) = :esrMonth", nativeQuery = false)
	Float getConcPollAvgByPName(@Param("pollName") String pollName, @Param("stName") String stName, @Param("attachedTo") String attachedTo, @Param("selectedYear") int selectedYear, @Param("selectedYear1") int selectedYear1, @Param("esrMonth") int esrMonth);

	@Query(value = "SELECT AVG(r.exitGasVelocity) FROM StackPollData sp left join sp.regStPoll r WHERE sp.pollName = :pollName AND r.stackName = :stName AND r.attachTo = :attachedTo AND EXTRACT(YEAR FROM r.sampSt) = :year1 OR EXTRACT(YEAR FROM r.sampSt) = :year2 AND EXTRACT(MONTH FROM r.sampSt) = :esrMonth", nativeQuery = false)
	Float getExitGasVelocityAvgByPName(@Param("pollName") String pollName, @Param("stName") String stName, @Param("attachedTo") String attachedTo, @Param("year1") int year1, @Param("year2") int year2, @Param("esrMonth") int esrMonth);

	@Query(value = "SELECT r.hrsOp FROM StackPollData sp left join sp.regStPoll r WHERE sp.pollName = :pollName AND r.stackName = :stName AND r.attachTo = :attachedTo AND EXTRACT(YEAR FROM r.sampSt) = :year1 OR EXTRACT(YEAR FROM r.sampSt) = :year2 AND EXTRACT(MONTH FROM r.sampSt) = :esrMonth", nativeQuery = false)
	List<String> getHoursOpByPName(@Param("pollName") String pollName, @Param("stName") String stName, @Param("attachedTo") String attachedTo, @Param("year1") int year1, @Param("year2") int year2, @Param("esrMonth") int esrMonth);

	/*
	 * @Query("SELECT COALESCE(AVG(rt.gasQuant),0) FROM RegStPoll rt left join rt.stack s left join s.consent c WHERE c.consStatus!='EXPIRED' and s.pollName = :pollName AND s.stackName=:stackName AND s.attachedTo = :attachedTo AND rt.sampSt BETWEEN :fromDate AND :toDate")
	 * Float getAvgconcPollByYear(@Param("pollName")String pollName,@Param("stackName")String stackName, @Param("attachedTo")String attachedTo,@Param("fromDate")String fromDate,@Param("toDate")String toDate);
	 */
	// needs to be confirmed sushma
	/*
	 * @Query("SELECT DISTINCT new RegStPoll(COALESCE(AVG(rt.exitGasVelocity),0), COALESCE(AVG(rt.hrsOp),0)) from StackPollData sp left join sp.regStPoll rt left join rt.stack s WHERE sp.pollName = :pollName AND s.stackName = :stackName AND s.attachedTo = :attachedTo AND rt.sampSt BETWEEN :dateFrom AND :dateTo")
	 * List<RegStPoll> getAVgExitGasVelocityByYear(@Param("pollName")String pollName,@Param("stackName")String stackName,@Param("attachedTo")String attachedTo,@Param("dateFrom")String dateFrom,@Param("dateTo")String dateTo);
	 */
	@Query(value = "SELECT AVG(r.exitGasVelocity) FROM StackPollData sp left join sp.regStPoll r  WHERE sp.pollName = :pollName AND r.stackName = :stName AND r.attachTo = :attachedTo AND EXTRACT(YEAR FROM r.sampSt) = :year1 OR EXTRACT(YEAR FROM r.sampSt) = :year2 ", nativeQuery = false)
	Float getExitGasVelocityAvgByPNameForYear(@Param("pollName") String pollName, @Param("stName") String stName, @Param("attachedTo") String attachedTo, @Param("year1") int year1, @Param("year2") int year2);

	@Query(value = "SELECT r.hrsOp FROM StackPollData sp left join sp.regStPoll r WHERE sp.pollName = :pollName AND r.stackName = :stName AND r.attachTo = :attachedTo AND EXTRACT(YEAR FROM r.sampSt) = :year1 OR EXTRACT(YEAR FROM r.sampSt) = :year2", nativeQuery = false)
	List<String> getHoursOpByPNameForYear(@Param("pollName") String pollName, @Param("stName") String stName, @Param("attachedTo") String attachedTo, @Param("year1") int year1, @Param("year2") int year2);

	String q = "SELECT r FROM RegStPoll r left join r.stack s WHERE r.stackName=s.stackName AND r.attachTo=s.attachedTo  AND r.sampSt between :fromDate and :toDate";

	@Query(value = q, nativeQuery = false)
	public List<RegStPoll> getRegStackPollData(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
