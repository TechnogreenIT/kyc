package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.WaterMarks;

@Repository
public interface WaterMarksServicesRepository extends JpaRepository<WaterMarks, Long>
{

	@Query("SELECT DISTINCT(wm.type) FROM WateriePollutantOp wp INNER JOIN wp.wateriePollutant w,WaterMarks wm WHERE wm.items=w.pollName AND wm.mainType= :treatmentType")
	List<String> getAllwaterMarksByMainType(@Param("treatmentType") String treatmentType);

	@Query("SELECT wm FROM WaterMarks wm WHERE wm.mainType= :treatmentType AND wm.type= :wmType")
	List<WaterMarks> getWaterMarksDataByTreatmentAndwmType(@Param("treatmentType") String treatmentType, @Param("wmType") String wmType);

	@Query("SELECT wm FROM WateriePollutantOp wp INNER JOIN wp.wateriePollutant w,WaterMarks wm WHERE  wm.items=w.pollName AND wm.mainType= :treatmentType AND wm.type= :wmType")
	List<WaterMarks> getWaterMarksDataByForWateriePoll(@Param("treatmentType") String treatmentType, @Param("wmType") String wmType);

	@Query("SELECT wm.items FROM WaterSewPollOp sp INNER JOIN sp.waterSewPoll ws,WaterMarks wm WHERE  wm.items=ws.pollName AND wm.mainType= 'STP'")
	List<String> getWaterMarksDataByForWaterSewPollForStp();// chnage by amin

	// ahp
	@Query("SELECT DISTINCT w.pollName,w.quantity,wm.marks FROM WateriePollutantOp wp LEFT JOIN wp.wateriePollutant w,WaterMarks wm LEFT JOIN wp.consent c LEFT JOIN c.consentExtendedDate ce   WHERE wm.items=w.pollName AND wm.mainType= :treatmentType AND  c.consType='Consent to Operate' AND c.consStatus != 'EXPIRED' AND (c.validUpto >= :today OR ce.validUpto >= :today) ")
	List<String> ahpWater(@Param("treatmentType") String treatmentType, @Param("today") String today);

	// object
	@Query("SELECT DISTINCT w.pollName,w.quantity,wm.marks FROM WateriePollutantOp wp LEFT JOIN wp.wateriePollutant w,WaterMarks wm LEFT JOIN wp.consent c LEFT JOIN c.consentExtendedDate ce   WHERE wm.items=w.pollName AND wm.mainType= :treatmentType AND  c.consType='Consent to Operate' AND c.consStatus != 'EXPIRED' AND (c.validUpto >= :today OR ce.validUpto >= :today) ")
	List<Object[]> ahpWaterObj(@Param("treatmentType") String treatmentType, @Param("today") String today);

	// qua
	// @Query("SELECT AVG(ref.ouConsE) FROM RegEffPoll ref WHERE (EXTRACT(YEAR FROM ref.sampE)) =:yr AND ref.pollName = :PollName")
	// public Float getRegEffPollData(@Param("yr") int yr, @Param("PollName") String PollName);

	// stp
	@Query("SELECT ws.pollName,ws.quantity,wm.marks,ws.waterSewPollId FROM WaterSewPollOp sp LEFT JOIN sp.waterSewPoll ws,WaterMarks wm LEFT JOIN sp.consent c LEFT JOIN c.consentExtendedDate ce WHERE  wm.items=ws.pollName AND wm.mainType= 'STP' AND c.consType='Consent to Operate' AND c.consStatus != 'EXPIRED' AND (c.validUpto >= :today OR ce.validUpto >= :today)")
	List<Object[]> ahpWatersew(@Param("today") String today);

	// SELECT AVG (res.ou_cons_s) from reg_sew_poll as res
	// LEFT JOIN water_sew_poll as ws on res.poll_name=ws.poll_name
	// where samp_s='2023-01-02' AND ws.poll_name='SS'
	// mm
	// @Query("SELECT AVG(res.ouConsS) FROM RegSewPoll res WHERE (EXTRACT(YEAR FROM res.sampS)) =:year AND res.waterSewPollOp = :pollIdn")
	// public Float getRegSewPollData(@Param("year") int year, @Param("pollIdn") int pollIdn);

	// mm
	// @Query("SELECT AVG(res.ouConsS) FROM RegSewPoll res LEFT JOIN res.waterSewPoll ws WHERE (EXTRACT(YEAR FROM res.sampS)) =:year AND res.waterSewPollOp.waterSewPollId=:pollIdn")
	// public Float getRegSewPollData(@Param("year") int year, @Param("pollIdn") int pollIdn);

	@Query("SELECT sp.pollName,sp.pollLimit,am.marks,am.type,sp.stack.stackId FROM StackPoll sp,AirMarks am,Particulate p WHERE am.mainType='stack' AND sp.stack.stackId=:stackId AND am.pollutants='Particulate' AND am.type=:poll AND sp.pollName=p.pollutant AND p.type='Particulate'")
	List<Object[]> ahpStackObj(@Param("stackId") int stackId, @Param("poll") String poll);

	@Query("SELECT sp.pollName,sp.pollLimit,am.marks,am.type,sp.stack.stackId FROM StackPoll sp,AirMarks am,Particulate p WHERE am.mainType='stack' AND sp.stack.stackId=:stackId AND am.pollutants='Gases' AND am.type=:poll AND sp.pollName=p.pollutant AND p.type='Gases'")
	List<Object[]> ahpStackObjGases(@Param("stackId") int stackId, @Param("poll") String poll);

	@Query("SELECT sp.concPoll FROM RegStPoll r,StackPollData sp,Stack s WHERE r.regStPollId = sp.regStPoll.regStPollId AND r.stack.stackId=s.stackId AND r.attachTo=s.attachedTo  AND EXTRACT(MONTH FROM r.sampSt) =:month AND EXTRACT(YEAR FROM r.sampSt) =:yr  AND sp.pollName=:PollName AND sp.regStPoll.regStPollId=:spid")
	public Float getRegStackPollData(@Param("PollName") String PollName, @Param("month") int month, @Param("yr") int yr, @Param("spid") int spid);

	@Query("SELECT m.pollutants,m.type,ap.limits, m.marks,ap.ambient.ambientId FROM AirMarks m, AmbientPoll ap LEFT JOIN  ap.ambient a WHERE ap.pollName=m.pollutants AND m.type=:PollName AND ap.ambient.ambientId=:ambientid")
	List<Object[]> getAmbientmarkdata(@Param("PollName") String poll, @Param("ambientid") int ambientid);

	@Query("SELECT ap.concPoll FROM RegAmbientPoll r,AmbientPollData ap,Ambient a  WHERE r.ambient.ambientId=a.ambientId  AND  r.regAmbientPollId=ap.regAmbientPoll.regAmbientPollId AND a.ambientLocName=r.locName AND  EXTRACT(MONTH FROM r.sampAmb) =:month  AND EXTRACT(YEAR FROM r.sampAmb) =:yr AND ap.pollName=:PollName AND r.ambient.ambientId=:ambientId")
	public Float getRegAmbientPollData(@Param("PollName") String PollName, @Param("month") int month, @Param("yr") int yr, @Param("ambientId") int ambientId);

	@Query("SELECT p.type  FROM StackPoll sp LEFT JOIN  sp.stack s ,Particulate p WHERE p.pollutant=sp.pollName")
	public List<String> typeList();
}
