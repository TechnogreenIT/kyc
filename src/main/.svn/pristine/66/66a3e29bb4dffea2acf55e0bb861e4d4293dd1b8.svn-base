package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tes.model.WaterSewPoll;

@Repository
public interface WaterSewPollRepository extends JpaRepository<WaterSewPoll, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	WaterSewPoll save(WaterSewPoll waterSewPoll);

	@Query("SELECT  wp from WaterSewPollOp wop LEFT JOIN wop.waterSewPoll  wp LEFT JOIN wop.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	List<WaterSewPoll> findByTodayDateAndCmpid(@Param("today") String today);

	@Query("SELECT w FROM WaterSewPoll w, Consent c WHERE w.consent=c.consentId AND c.consType='Consent to Establish'")
	List<WaterSewPoll> findbyConsType();

	@Transactional
	@Modifying
	@Query("UPDATE WaterSewPoll ws SET ws.consentToOperate = 'Yes' WHERE ws.waterSewPollId = :waterSewPollId")
	int updateConsentToOperate(@Param("waterSewPollId") int waterSewPollId);

	@Query("SELECT ws from WaterSewPoll ws")
	List<WaterSewPoll> getWaterSewagePollutant();

	@Query("SELECT NEW WaterSewPoll(w.waterSewPollId,w.consent,w.pollName,w.quantity,w.unit,w.consentToOperate) FROM WaterSewPoll w, Consent c WHERE c.consentId=w.consent AND w.consent.consentId = :consentId")
	List<WaterSewPoll> findByConsent(@Param("consentId") int consentId);

	@Query("SELECT w FROM WaterSewPollOp wsp LEFT JOIN wsp.waterSewPoll  w LEFT JOIN wsp.consent c LEFT JOIN c.consentExtendedDate ce  WHERE c.consType='Consent to Operate'  AND  c.consStatus != 'EXPIRED' AND wsp.consent.consentId= :consentId")
	List<WaterSewPoll> findByConsentToOperateAndConsent(@Param("consentId") int consentId);

	// not in use
	String getWaterSewPoll = "SELECT w.poll_name, w.quantity FROM `water_sew_poll` w where w.consent_to_operate = 'Yes' AND w.cons_id = :consNo";

	@Query(value = getWaterSewPoll, nativeQuery = true)
	List<Object[]> getWaterSewPoll(@Param("consNo") int consNo);

	// @Query("SELECT AVG(r.ouConsS) FROM RegSewPoll r WHERE r.pollName = :pollName AND EXTRACT(YEAR FROM r.sampS) = :esrYear AND EXTRACT(MONTH FROM r.sampS) = :esrMonth ")
	// Float getSewAvgOuConsE(@Param("pollName") String pollName,@Param("esrYear") int esrYear,@Param("esrMonth") int esrMonth);

	WaterSewPoll findByWaterSewPollId(int pollId);

	@Transactional
	List<WaterSewPoll> deleteByWaterSewPollId(int waterSewPollId);

	@Query("SELECT s.pollName FROM WaterSewPollOp sp INNER JOIN sp.waterSewPoll s INNER JOIN sp.consent c WHERE c.consStatus!='EXPIRED' AND s.pollName != 'ph'")
	List<String> getWaterSewPolls();

	@Query("SELECT DISTINCT  s  FROM WaterSewPollOp sp INNER JOIN sp.waterSewPoll s INNER JOIN sp.consent c WHERE c.consStatus!='EXPIRED' AND s.pollName != 'ph'")
	List<WaterSewPoll> getWaterSewPollData();

	@Query("SELECT DISTINCT   NEW WaterSewPoll(s.pollName,s.quantity) FROM WaterSewPollOp sp INNER JOIN sp.waterSewPoll s INNER JOIN sp.consent c  WHERE c.consStatus!='EXPIRED'")
	List<WaterSewPoll> getWaterSewPollNameAndQuantity();

}
