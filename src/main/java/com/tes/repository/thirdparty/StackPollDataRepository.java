package com.tes.repository.thirdparty;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.StackPollData;

@Repository
public interface StackPollDataRepository extends JpaRepository<StackPollData, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	public StackPollData save(StackPollData regStPoll);

	String q = "SELECT sp FROM RegStPoll r,StackPollData sp,Stack s WHERE r.regStPollId = sp.regStPoll.regStPollId AND r.stackName=s.stackName AND r.attachTo=s.attachedTo AND r.stackName=?1 AND r.attachTo=?2 AND EXTRACT(MONTH FROM r.sampSt) =?3 AND EXTRACT(YEAR FROM r.sampSt) =?4 AND r.regStPollId=?5";

	@Query(value = q, nativeQuery = false)
	public List<StackPollData> getStackPollData(@Param("stackname") String stackname, @Param("attachedTo") String attachedTo, @Param("month") int month, @Param("year") int year, @Param("stackPollId") int stackPollId);

	/* @Query("SELECT COALESCE(AVG(sp.pollLimit),0) FROM StackPollData sp left join sp.regStPoll rt left join rt.stack s left join s.consent c WHERE c.consStatus!='EXPIRED' and sp.pollName = :pollName AND s.stackName= :stackName AND s.attachedTo = :attachedTo AND rt.sampSt BETWEEN :fromDate AND :toDate") */
	@Query(value = "SELECT AVG(sp.conc_poll) FROM stack_poll_data sp left join reg_st_poll r on r.id=sp.reg_st_poll_id left join stack s  on s.id=r.stack_id left join consent c on c.id=s.consent_id  WHERE c.cons_status!='EXPIRED' and sp.poll_name = :pollName AND s.stack_name= :stackName AND s.attached_to = :attachTo AND r.samp_st BETWEEN :fromDate and :toDate", nativeQuery = true)
	Float getAvgconcPollByYear(@Param("pollName") String pollName, @Param("stackName") String stackName, @Param("attachTo") String attachTo, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("SELECT sp FROM StackPollData sp left join sp.regStPoll r WHERE r.sampSt between :fromDate and :toDate")
	public List<StackPollData> getStackPollDataByYear(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	// String q="";
	@Query("SELECT sp FROM StackPollData sp WHERE  sp.regStPoll.regStPollId = :stackPollId")
	public List<StackPollData> getStackPollDataDay(@Param("stackPollId") int stackPollId);

	@Query("SELECT AVG(spd.concPoll) FROM StackPollData spd LEFT JOIN spd.regStPoll rsp LEFT JOIN rsp.stack s WHERE spd.pollName= :pollName AND s.stackId= :stackId AND rsp.subSt BETWEEN :dateFrom AND :dateTo ")
	Float getAvgconcPollByName(@Param("pollName") String pollName, @Param("stackId") int stackId, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);

}
