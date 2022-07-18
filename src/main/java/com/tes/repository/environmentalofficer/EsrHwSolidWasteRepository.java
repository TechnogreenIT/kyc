package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.EsrHwSolidWaste;

@Repository
public interface EsrHwSolidWasteRepository extends JpaRepository<EsrHwSolidWaste, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	EsrHwSolidWaste save(EsrHwSolidWaste esrHwSolidWaste);

	@Override
	List<EsrHwSolidWaste> findAll();

	@Query("SELECT e FROM EsrHwSolidWaste e WHERE e.year =:yearTo AND e.type =:type")
	List<Object[]> findOneByGetHazardousWasteByYear(@Param("yearTo") String yearTo, @Param("type") String type);

	@Query("SELECT es FROM EsrHwSolidWaste es WHERE es.year = :year AND es.type ='Hazardous Waste'")
	List<EsrHwSolidWaste> getEsrHwSolidWasteDataByHazYear(@Param("year") String year);

	@Query("SELECT es FROM EsrHwSolidWaste es WHERE es.year = :year AND es.type ='Solid Waste'")
	List<EsrHwSolidWaste> getEsrHwSolidWasteDataBySolidYear(@Param("year") String year);

	@Query("SELECT es FROM EsrHwSolidWaste es WHERE es.year = :esrWholeYear AND es.type = :type")
	List<EsrHwSolidWaste> getAllDataByTypeMonth(@Param("esrWholeYear") String esrWholeYear, @Param("type") String type);

}
