package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.WasteDescriptionConsistency;

@Repository
public interface WasteDescriptionConsistencyRepository extends JpaRepository<WasteDescriptionConsistency, Long>
{
	@Override
	@SuppressWarnings("unchecked")
	WasteDescriptionConsistency save(WasteDescriptionConsistency wasteDescriptionConsistency);

	@Query("SELECT w FROM  WasteDescriptionConsistency w  LEFT JOIN w.hazardousManifest hm WHERE w.hazardousManifest.hazardousManifestId =:hmId")
	public List<WasteDescriptionConsistency> wdDataBywasteDesc(@Param("hmId") int hmId);

	@Query("SELECT Distinct(w.consistency) FROM HazardousManifest hm, WasteDescriptionConsistency w WHERE w.hazardousManifest.hazardousManifestId =:hmId")
	public List<WasteDescriptionConsistency> wdDataByConsistency(@Param("hmId") int hmId);

	@Query("SELECT DISTINCT(w.description) FROM WasteDescriptionConsistency w, HazardousManifest h WHERE h.submittedDate BETWEEN :FROM AND :to")
	public List<WasteDescriptionConsistency> getWasteDescriptionByDate(@Param("FROM") String FROM, @Param("to") String to);

	@Query("SELECT COALESCE(SUM(wc.wasteQuantity),0) FROM WasteDescriptionConsistency wc INNER JOIN wc.hazardousManifest hm WHERE hm.submittedDate BETWEEN :fromDate AND :toDate AND wc.wasteName like '%'||:productName AND wc.wasteCategoryNumber=:categoryNo")
	public float getSumWasteQuantityByWasteName(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("productName") String productName, @Param("categoryNo") String categoryNo);

	@Query("SELECT DISTINCT(wc.wasteName) FROM WasteDescriptionConsistency wc INNER JOIN wc.hazardousManifest hm WHERE wc.hazardousManifest.hazardousManifestId=hm.hazardousManifestId AND hm.submittedDate BETWEEN :fromDate AND :toDate AND hm.dispatchedTo='Reused Within Industry'||'Utilized In House'")
	public List<String> getInHouseWasteNameBetweenDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("SELECT DISTINCT NEW WasteDescriptionConsistency(wc.wasteName, wc.wasteCategoryNumber, wc.description) FROM WasteDescriptionConsistency wc INNER JOIN wc.hazardousManifest hm WHERE hm.submittedDate BETWEEN :fromDate AND :toDate AND hm.dispatchedTo NOT like '%Reused Within Industry'||'%Utilized In House'")
	public List<WasteDescriptionConsistency> getHazardousWasteNameBetweenDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
