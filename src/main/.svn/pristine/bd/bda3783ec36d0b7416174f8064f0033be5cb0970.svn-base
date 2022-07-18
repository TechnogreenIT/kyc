package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.EsrParticulars;

@Repository
public interface EsrParticularsRepository extends JpaRepository<EsrParticulars, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	EsrParticulars save(EsrParticulars esrParticulars);

	@Query("SELECT ep FROM EsrParticulars ep WHERE ep.year =:year")
	List<Object[]> findOneByGetParticularsByYear(@Param("year") String year);

	@Query("SELECT ei FROM EsrParticulars ei WHERE ei.year = :year")
	List<EsrParticulars> getEsrParticularsbyyear(@Param("year") String year);

	@Query("SELECT ei FROM EsrParticulars ei WHERE ei.year LIKE :year||'%' AND ei.year LIKE '%'||:month1")
	List<EsrParticulars> getEsrParticularsbyyearMonth(@Param("year") String year, @Param("month1") String month1);
}
