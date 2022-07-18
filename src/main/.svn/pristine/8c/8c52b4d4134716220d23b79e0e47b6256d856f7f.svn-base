package com.tes.repository.environmentalofficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.EsrStackPollReasons;

@Repository
public interface EsrStackPollReasonsRepository extends JpaRepository<EsrStackPollReasons, Long>
{
	@Query("SELECT reason FROM EsrStackPollReasons WHERE pollName = :mainName AND reasonDate = :rsdate")
	String getReasonByDate(@Param("mainName") String mainName, @Param("rsdate") String rsdate);
}
