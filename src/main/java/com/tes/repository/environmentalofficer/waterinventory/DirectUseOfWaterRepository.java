package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import java.util.TreeSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.DirectUseOfWater;

@Repository
public interface DirectUseOfWaterRepository extends JpaRepository<DirectUseOfWater, Long>
{

	@Query("SELECT DISTINCT(whereToUse) FROM DirectUseOfWater ")
	TreeSet<String> getUsedDirectUseName();

	@Query("SELECT DISTINCT(du.whereToUse) FROM DirectUseOfWater du LEFT JOIN du.waterSource ws LEFT JOIN ws.waterInventory wi WHERE wi.waterInventoryId=:waterInventoryId")
	TreeSet<String> getDirectUseName(@Param("waterInventoryId") int waterInventoryId);

	@Query("SELECT uof FROM DirectUseOfWater uof")
	List<DirectUseOfWater> getUsedDirectUseNameList();

	@Query("SELECT NEW DirectUseOfWater(whereToUse,isIndustrial) FROM DirectUseOfWater")
	List<DirectUseOfWater> getAllWhereToUseAndIsIndustries();

	@Query("SELECT dw.isIndustrial FROM DirectUseOfWater dw WHERE dw.whereToUse=:label")
	boolean getDirectUseisIndustrial(@Param("label") String label);

	@Query("SELECT new DirectUseOfWater(dw.typeOfUse, dw.whereToUse, dw.isMeter) FROM DirectUseOfWater dw WHERE dw.whereToUse=:whereToUse")
	List<DirectUseOfWater> getUseOfWaterByWhereToUse(@Param("whereToUse") String whereToUse);

	@Query("select du from DirectUseOfWater du where du.waterSource.waterSourceId= :wsId ")
	List<DirectUseOfWater> directUseOfWaterList(@Param("wsId") int wsId);

	///// mmmm
	@Query("SELECT case WHEN count(du)>0 then true else false end FROM DirectUseOfWater du LEFT JOIN du.waterSource ws LEFT JOIN ws.waterInventory wi where du.whereToUse like (:type%) And wi.waterInventoryId=:wiid")
	boolean getDomesticUseType(@Param("wiid") int wiid, @Param("type") String type);

	@Query("SELECT du.waterLoss FROM DirectUseOfWater du WHERE du.whereToUse LIKE (:type%)")
	Float getWaterLoss(@Param("type") String type);

	@Query("SELECT du FROM DirectUseOfWater du WHERE du.whereToUse LIKE (:type%)")
	List<DirectUseOfWater> getIndustrialAllData(@Param("type") String type);
}
