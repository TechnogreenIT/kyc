package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.EsrPollutionControl;

@Repository
public interface EsrPollutionControlRepository extends JpaRepository<EsrPollutionControl, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	EsrPollutionControl save(EsrPollutionControl esrPollutionControl);

	@Query("SELECT epc FROM EsrPollutionControl epc WHERE epc.year =:yearTo")
	List<EsrPollutionControl> findOneByEsrPollutionByYear(@Param("yearTo") String yearToe);

	@Query("SELECT epc FROM EsrPollutionControl epc WHERE epc.year LIKE :yearTo||'%' AND epc.year LIKE '%'||:month1")
	List<EsrPollutionControl> findOneByEsrPollutionByMonth(@Param("yearTo") String yearToe, @Param("month1") String month1);

}
