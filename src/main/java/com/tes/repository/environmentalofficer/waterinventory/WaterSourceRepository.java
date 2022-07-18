package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.WaterSource;

@Repository
public interface WaterSourceRepository extends JpaRepository<WaterSource, Long>
{

	@Query("SELECT ws FROM WaterSource ws WHERE ws.waterInventory.waterInventoryId =:wiId")
	public List<WaterSource> getWaterSourceData(@Param("wiId") int wiId);

	@Query("SELECT ws.waterSourceId FROM WaterSource ws WHERE ws.sourceName=:sourceName")
	public int findWaterSourceIdByName(@Param("sourceName") String sourceName);

	@Query("SELECT DISTINCT(ws.sourceName) FROM WaterSource ws  LEFT JOIN ws.waterInventory w")
	List<String> getAllWaterSourceName();

	@Query("SELECT  DISTINCT(ws.sourceName) FROM WaterSource ws LEFT JOIN ws.waterInventory wi LEFT JOIN wi.consent c WHERE c.validUpto >= :todaysDate AND c.consType='Consent to Operate'")
	public List<String> getSourceNameByConsentValidUpto(@Param("todaysDate") String todaysDate);

	@Query("SELECT  ws.isSourceMeter FROM WaterSource ws where ws.sourceName= :sourceName")
	public boolean getMetervalue(@Param("sourceName") String sourceName);

	@Query("SELECT ws.sourceName FROM WaterSource ws")
	public List<String> getAllWaterSourceData();

	/*
	 * @Query("SELECT ws.sourceName FROM WaterSource ws")
	 * public List<String> getAllWaterSourceData();
	 * @Query("SELECT DISTINCT(ws.sourceName) FROM WaterSource ws  LEFT JOIN ws.waterInventory w"
	 * ) List<String> getWaterSourceName();
	 * @Query("SELECT  DISTINCT(ws.sourceName) FROM WaterSource ws LEFT JOIN ws.waterInventory wi LEFT JOIN wi.consent c WHERE c.validUpto >= :todaysDate"
	 * ) public List<String>
	 * getSourceNameByConsentValidUpto(@Param("todaysDate")String todaysDate);
	 * @Query("SELECT s.sourceName FROM WaterInventory w, WaterSource s WHERE s.waterInventory.waterInventoryId=w.waterInventoryId AND w.waterInventoryId = :waterInvId"
	 * ) public List<String> getSourceNameByWaterInvId(@Param("waterInvId") int
	 * waterInvId);
	 * @Query("SELECT count(ws.sourceMeter) FROM WaterSource ws where sourceMeter='Yes'"
	 * ) public int getnumberofMeter();
	 * @Query("SELECT ws.sourceMeter FROM WaterSource ws where ws.sourceName= :sourceName"
	 * ) public String getMetervalue(@Param("sourceName") String sourceName);
	 */

}
