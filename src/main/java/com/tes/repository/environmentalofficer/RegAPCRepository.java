package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.RegAPC;

@Repository
public interface RegAPCRepository extends JpaRepository<RegAPC, Long>
{
	@Override
	@SuppressWarnings("unchecked")
	RegAPC save(RegAPC regAPC);

	@Query("SELECT r FROM RegAPC r INNER JOIN r.stack s WHERE r.stack.stackId=:stackId ORDER BY r.regAPCId DESC")
	List<RegAPC> getRegAPCData(@Param("stackId") int stackId, Pageable Pageable);

	@Query("select r from  RegAPC r where r.apcDate=:Date")
	List<RegAPC> getProductDetailsDataAir(@Param("Date") String Date);
}
