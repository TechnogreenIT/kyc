package com.tes.repository.environmentalofficer;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tes.model.AirMarks;
import com.tes.model.Consent;
import com.tes.model.RegularData;

public interface DailyInputRepository extends JpaRepository<RegularData, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegularData save(RegularData regularData);

	// Effected By Water Inventory ........by sushama
	// @Query("SELECT COUNT(DISTINCT rsd.rsDate) FROM RegularSourceData rsd WHERE rsd.rsDate between :startDate AND :date")
	// int getcountDistinctRsDate(@Param("startDate") Date startDate, @Param("date") Date date);

	@Query("SELECT u.units FROM Unit u")
	List unitList();

	@Query("SELECT c FROM Consent c WHERE c.consType=:consType2")
	List<Consent> consentByEstablish(@Param("consType2") String consType2);

	@Query("SELECT c FROM Consent c WHERE c.consType='Consent to Operate' AND c.issueDate < :date AND c.validUpto > :date")
	List consentByOperate(@Param("date") String date);

	@Query("SELECT c FROM Consent c")
	List<Consent> consentdata();

	@Query("SELECT e.effluentId FROM Effluent e, Consent c WHERE e.consent=c.consentId AND datediff(c.validUpto,now()) >=0")
	List<Integer> effId();

	@Query("SELECT w.waterSewPollId FROM WaterSewPoll w, Consent c WHERE w.consent=c.consentId AND datediff(c.validUpto,now()) >=0")
	List<Integer> sewPollId();

	@Query("SELECT DISTINCT(s.attachedTo) FROM Stack s")
	List attachedTo();

	@Query("SELECT am.marks FROM AirMaster am  WHERE am.type='stack' AND am.name IN :attachedTo")
	List<Float> masterMarks(@Param("attachedTo") String[] attachedTo);

	@Query("SELECT am FROM AirMarks am  WHERE am.mainType='stack' AND am.type IN :attachedTo")
	List<AirMarks> airMarks(@Param("attachedTo") String[] attachedTo);

	@Query("SELECT DISTINCT r.unit, r.allProductName.productName, c.consentId FROM AllProducts r, Consent c WHERE r.consent=c.consentId AND r.allProductName.type IN :productType AND c.consType = 'Consent to Operate' AND c.validUpto >=:date")
	List<Object[]> allProductsDataByType(@Param("productType") String[] productType, @Param("date") String date);

	@Query("SELECT DISTINCT r.unit, r.allProductName.productName, c.consentId FROM AllProducts r, Consent c WHERE r.consent=c.consentId AND (r.allProductName.type = 'hwp' OR r.allProductName.type = 'hwpcf' OR r.allProductName.type = 'nhwp' OR r.allProductName.type = 'nhwpcf') AND c.consType = 'Consent to Operate' AND c.validUpto >= :date")
	List<Object[]> allProductsDataByNHW(@Param("date") String date);

	@Query("SELECT DISTINCT r.unit, r.allProductName.productName, c.consentId FROM AllProducts r, Consent c, ConsentExtendedDate ce WHERE r.consent=c.consentId AND r.allProductName.type = :typeP AND ce.consent = c.consentId AND c.consType='Consent to Operate' AND r.allProductName.productType = :typeP AND c.consStatus != 'EXPIRED' AND (c.validUpto >= :today OR ce.validUpto >= :today)")
	List<Object[]> dataAllProductConsentExtended(@Param("typeP") String typeP, @Param("today") String today);

	@Query("SELECT p.quantity FROM AllProducts p  WHERE p.allProductName.productName IN :productName AND p.consent IN :consentId AND p.unit IN :unitId")
	List<Float> quantityAllProducts(@Param("productName") Object productName, @Param("consentId") Object consentId, @Param("unit") Object unitId);

	@Query("SELECT DISTINCT(wm.type) FROM WateriePollutant w, Effluent e, Consent c, WaterMarks wm WHERE e.consent=c.consentId AND c.validUpto >= :today  AND wm.items = w.pollName AND wm.mainType = 'ETP'")
	List wmType(@Param("today") String today);

	@Query("SELECT DISTINCT(wm.items), wm.type, wm.marks FROM WaterMarks wm WHERE wm.mainType = 'ETP' AND wm.type =:tt")
	List<Object[]> wmData(@Param("tt") Object[] tt);

	@Query("SELECT DISTINCT(wm.items), wm.type, wm.marks FROM WateriePollutant w, Effluent eff, Consent c, WaterMarks wm  WHERE eff.consent=c.consentId AND c.validUpto >= :today  AND wm.items = w.pollName AND wm.mainType = 'ETP' AND wm.type = :tt")
	List<Object[]> wmDataByPollAndEff(@Param("today") String today, @Param("tt") Object[] tt);

	@Query("SELECT w.pollName, w.quantity, wm.type FROM Effluent eff, WateriePollutant w, Consent c,WaterMarks wm WHERE w.consent=c.consentId AND c.validUpto >= :today AND w.pollName = wm.items AND wm.mainType='ETP'")
	List<Object[]> dataWatMarkPoll(@Param("today") String today);

	// @Query("SELECT AVG(rf.ouConsE) FROM RegEffPoll rf WHERE rf.pollName = :item")
	// List ouConsE(@Param("item") String item);

	@Query("SELECT DISTINCT(wm.items) FROM WaterSewPoll w,Effluent eff, Consent c, WaterMarks wm WHERE eff.consent=c.consentId AND c.validUpto >= :today AND wm.items = w.pollName AND wm.mainType = 'STP'")
	List wmTypeBySTP(@Param("today") String today);

	@Query("SELECT u.units FROM Unit u, AllProducts a, Consent c WHERE u.unitId=a.unit AND  a.consent=c.consentId AND c.consType = 'Consent to Operate' AND a.allProductName.productName = :productName")
	List units(@Param("productName") String productName);

}
