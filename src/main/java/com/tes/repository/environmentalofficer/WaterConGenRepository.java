package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.Consent;
import com.tes.model.WaterConGen;

@Repository
public interface WaterConGenRepository extends JpaRepository<WaterConGen, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	WaterConGen save(WaterConGen waterConGen);

	@Query("SELECT COALESCE(SUM(wc.cons),0) FROM WaterConGen wc INNER JOIN wc.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType = 'Consent to Establish' AND wc.waterConGenParameter.waterConGenParameterId = :waterConGenParameterId  AND (c.validUpto >= :todaysDate OR ce.validUpto >= :todaysDate)")
	Float getConsByCtEAndWaterConGenParameterId(@Param("todaysDate") String todaysDate, @Param("waterConGenParameterId") int waterConGenParameterId);
	// fetch single value but this return null so i can use sum.....by amin

	@Query("SELECT COALESCE(SUM(wc.gen),0) FROM WaterConGen wc INNER JOIN wc.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType = 'Consent to Establish' AND wc.waterConGenParameter.waterConGenParameterId = :waterConGenParameterId  AND (c.validUpto >= :todaysDate OR ce.validUpto >= :todaysDate)")
	Float getGenByCtEAndWaterConGenParameterId(@Param("todaysDate") String todaysDate, @Param("waterConGenParameterId") int waterConGenParameterId);
	// fetch single value but this return null so i can use sum.....by amin
	// ---coalesce(sum(p.value), 0)

	@Query("SELECT COALESCE(SUM(wc.cons),0) FROM WaterConGen wc LEFT JOIN wc.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND (c.validUpto >= :todaysDate OR ce.validUpto >= :todaysDate) AND wc.waterConGenParameter.waterConGenParameterId = :waterConGenParameterId")
	float sumOfConsByWaterConGenParameterIdAndCtO(@Param("todaysDate") String todaysDate, @Param("waterConGenParameterId") int waterConGenParameterId);

	@Query("SELECT COALESCE(SUM(wc.gen),0) FROM WaterConGen wc LEFT JOIN wc.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND (c.validUpto >= :todaysDate OR ce.validUpto >= :todaysDate) AND wc.waterConGenParameter.waterConGenParameterId = :waterConGenParameterId")
	float sumOfGenByWaterConGenParameterIdAndCtO(@Param("todaysDate") String todaysDate, @Param("waterConGenParameterId") int waterConGenParameterId);

	@Query("SELECT COALESCE(wcg.cons,0) FROM WaterConGen wcg RIGHT JOIN wcg.consent c RIGHT JOIN wcg.waterConGenParameter wcgp WHERE c.consentId = :consentId AND wcgp.waterConGenParameterId = :waterConGenParameterId")
	float getConsByWaterConGenParameterIdAndconsentId(@Param("consentId") int consentId, @Param("waterConGenParameterId") int waterConGenParameterId);

	@Query("SELECT COALESCE(wcg.gen,0) FROM WaterConGen wcg RIGHT JOIN wcg.consent c RIGHT JOIN wcg.waterConGenParameter wcgp WHERE c.consentId = :consentId AND wcgp.waterConGenParameterId = :waterConGenParameterId")
	float getGenByWaterConGenParameterIdAndconsentId(@Param("consentId") int consentId, @Param("waterConGenParameterId") int waterConGenParameterId);

}
