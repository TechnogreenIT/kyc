package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.WaterSewPollOp;

@Repository
public interface WaterSewPollOpRepository extends JpaRepository<WaterSewPollOp, Long>
{
	@Modifying
	@Transactional
	@Query("DELETE FROM WaterSewPollOp ws WHERE ws.waterSewPoll.waterSewPollId =:productId")
	int deleteByWaterSewPollIdOp(@Param("productId") int productId);

	@Query("SELECT  wop from WaterSewPollOp wop LEFT JOIN wop.waterSewPoll  wp LEFT JOIN wop.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	List<WaterSewPollOp> findByTodayDateAndCmpid(@Param("today") String today);

}
