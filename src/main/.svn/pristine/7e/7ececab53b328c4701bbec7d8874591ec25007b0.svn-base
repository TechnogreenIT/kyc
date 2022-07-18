package com.tes.repository.environmentalofficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.EsrWaterPollReasons;

@Repository
public interface EsrWaterPollReasonsRepository extends JpaRepository<EsrWaterPollReasons, Long>
{
	@Override
	@SuppressWarnings("unchecked")
	EsrWaterPollReasons save(EsrWaterPollReasons esrWaterPollReasons);

	// String pollReasonByName(String pollName, String type, String dateTo, String dateFrom);

	// @Query("SELECT rp.reason FROM RegPollReasons rp WHERE rp.pollName = :pollName AND rp.pollType =:pollType AND rp.reasonDate LIKE :dateFrom% AND rp.reasonDate LIKE %:dateTo% AND rp.reasonDate LIKE %:month1 ORDER BY rp.reason DESC")
	@Query("SELECT rwp.reason FROM EsrWaterPollReasons rwp WHERE rwp.pollName= :pollName AND rwp.pollType=:type AND rwp.reasonDate = :selectedYear")
	String pollReasonByName(@Param("pollName") String pollName, @Param("type") String type, @Param("selectedYear") String selectedYear);

}
