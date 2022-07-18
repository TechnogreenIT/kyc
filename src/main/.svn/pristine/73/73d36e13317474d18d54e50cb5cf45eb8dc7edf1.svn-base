package com.tes.repository.environmentalofficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.WaterConGenComparativeSheet;

@Repository
public interface WaterConGenComparativeSheetRepository extends JpaRepository<WaterConGenComparativeSheet, Long>
{

	@Modifying
	@Transactional
	@Query("UPDATE WaterConGenComparativeSheet wc SET wc.status = 0 WHERE wc.status =:status")
	int setStatusInactiveToAll(@Param("status") byte status);

	@Query("SELECT  wcs.cons FROM WaterConGenComparativeSheet wcs LEFT JOIN wcs.waterConGen wc WHERE wcs.status = 1 AND wc.waterConGenParameter.waterConGenParameterId = :waterConGenParameterId")
	float getConsByStatusIsActiveAndWaterConGenParameterId(@Param("waterConGenParameterId") int waterConGenParameterId);

	@Query("SELECT  wcs.gen FROM WaterConGenComparativeSheet wcs LEFT JOIN wcs.waterConGen wc WHERE wcs.status = 1 AND wc.waterConGenParameter.waterConGenParameterId = :waterConGenParameterId")
	float getGenByStatusIsActiveAndWaterConGenParameterId(@Param("waterConGenParameterId") int waterConGenParameterId);

	@Query("SELECT COALESCE(SUM(wc.cons),0) FROM WaterConGenComparativeSheet wc INNER JOIN wc.waterConGen wg INNER JOIN wg.waterConGenParameter wp INNER JOIN wg.consent c where wp.parameterName=:name AND c.consType = 'Consent to Operate' AND c.validUpto >= :today AND c.consStatus != 'EXPIRED'")
	Float getSumOfProcessCons(@Param("name") String name, @Param("today") String today);

	@Query("SELECT COALESCE(SUM(wc.gen),0) FROM WaterConGenComparativeSheet wc INNER JOIN wc.waterConGen wg INNER JOIN wg.waterConGenParameter wp INNER JOIN wg.consent c where wp.parameterName=:name AND c.consType = 'Consent to Operate' AND c.validUpto >= :today AND c.consStatus != 'EXPIRED'")
	Float getSumOfProcessGen(@Param("name") String name, @Param("today") String today);

	@Query("SELECT SUM(wcs.cons) FROM WaterConGenComparativeSheet wcs WHERE wcs.status = 1")
	Float getcons();

	@Query("SELECT COALESCE(SUM(wc.gen),0) FROM WaterConGenComparativeSheet wc INNER JOIN wc.waterConGen wg INNER JOIN wg.waterConGenParameter wp INNER JOIN wg.consent c where wp.parameterName=:name AND wg.consent.consentId = :consentId")
	Float getSumOfProcessGenMonth(@Param("name") String name, @Param("consentId") int consentId);

	@Query("SELECT COALESCE(SUM(wc.cons),0) FROM WaterConGenComparativeSheet wc INNER JOIN wc.waterConGen wg INNER JOIN wg.waterConGenParameter wp INNER JOIN wg.consent c where wp.parameterName=:name AND wg.consent.consentId = :consentId")
	Float getSumOfProcessConMonth(@Param("name") String name, @Param("consentId") int consentId);

}
