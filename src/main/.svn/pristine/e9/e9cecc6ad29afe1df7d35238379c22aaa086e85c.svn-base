package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tes.model.EsrProductWater;

public interface EsrProductWaterRepository extends JpaRepository<EsrProductWater, Long>
{
	@Override
	@SuppressWarnings("unchecked")
	EsrProductWater save(EsrProductWater esrProductWater);

	@Query("SELECT ep FROM EsrProductWater ep WHERE ep.year = :year AND ep.productName = :productName AND ep.productType = :productType")
	List<EsrProductWater> getAllDataByProNameTypeYear(@Param("year") String year, @Param("productName") String productName, @Param("productType") String productType);

	@Query("SELECT ep FROM EsrProductWater ep WHERE ep.year = :year AND ep.productType = :type")
	List<EsrProductWater> getEsrProductWaterData(@Param("year") String year, @Param("type") String type);

	@Query("SELECT ep FROM EsrProductWater ep WHERE ep.year LIKE :year||'%' AND ep.year LIKE '%'||:month1 AND ep.productType = :type")
	List<EsrProductWater> getEsrProductWaterDataMonthly(@Param("year") String year, @Param("month1") String month1, @Param("type") String type);

	@Query("SELECT ep FROM EsrProductWater ep WHERE ep.year = :esrWholeYear AND ep.productType = :type")
	List<EsrProductWater> getAllDataByYear(@Param("esrWholeYear") String esrWholeYear, @Param("type") String type);
}
