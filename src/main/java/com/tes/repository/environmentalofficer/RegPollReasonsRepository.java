package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.RegPollReasons;

@Repository
public interface RegPollReasonsRepository extends JpaRepository<RegPollReasons, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegPollReasons save(RegPollReasons regPollReasons);

	@Query("SELECT rp.reason FROM RegPollReasons rp WHERE rp.pollName = :pollName AND rp.pollType = :pollType AND rp.reasonDate LIKE :dateFrom ||'%' AND rp.reasonDate LIKE '%'||:dateTo ORDER BY rp.reason DESC")
	List<String> getRegReasonByNametypeAndYear(@Param("pollName") String pollName, @Param("pollType") String pollType, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo, Pageable pageable);

	@Query("SELECT rp.reason FROM RegPollReasons rp  WHERE rp.pollName = :pollName  AND rp.pollType =:pollType  AND rp.reasonDate LIKE :dateFrom%  AND rp.reasonDate LIKE %:dateTo%  AND rp.reasonDate LIKE %:month1  ORDER BY rp.reason DESC")
	List<String> getRegReasonByNametypeAndYearMonth(@Param("pollName") String pollName, @Param("pollType") String pollType, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo, @Param("month1") String month1, Pageable pageable);
}
