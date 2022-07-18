package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.EsrInvestment;

@Repository
public interface EsrInvestmentRepository extends JpaRepository<EsrInvestment, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	EsrInvestment save(EsrInvestment esrInvestment);

	@Query("SELECT ei FROM EsrInvestment ei WHERE ei.year =:year AND ei.investmentYear =:year")
	List<EsrInvestment> findOneByGetEsrInvestmentByYear(@Param("year") String year);

	@Query("SELECT ei FROM EsrInvestment ei WHERE ei.year =:year AND ei.investmentYear =:year1")
	List<EsrInvestment> findOneByGetEsrInvestmentByNextYear(@Param("year") String year, @Param("year1") String year1);

	@Query("SELECT ei FROM EsrInvestment ei WHERE ei.year =:yearWithMonth AND  ei.investmentYear =:year")
	List<EsrInvestment> findOneByGetEsrInvestmentByMonth(@Param("yearWithMonth") String yearWithMonth, @Param("year") String year);

	@Query("SELECT ei FROM EsrInvestment ei WHERE ei.year =:yearWithMonth AND ei.investmentYear =:nextYear")
	List<EsrInvestment> findOneByGetEsrInvestmentByNextYearMonth(@Param("yearWithMonth") String yearWithMonth, @Param("nextYear") String nextYear);

}
