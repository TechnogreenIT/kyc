package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.WastewaterTreatment;

@Repository
public interface WastewaterTreatmentRepository extends JpaRepository<WastewaterTreatment, Long>
{

	@SuppressWarnings("unchecked")
	WastewaterTreatment save(WastewaterTreatment wastewaterTreatment);

	@Query("SELECT wt FROM WastewaterTreatment wt WHERE wt.waterInventory.waterInventoryId=:waterInventoryId order by wt.treatmentType")
	List<WastewaterTreatment> getAllWasteWaterTreatmentData(@Param("waterInventoryId") int waterInventoryId);

	@Query("SELECT t.treatmentType FROM WastewaterTreatment t, WaterInventory w, Consent c WHERE t.waterInventory.waterInventoryId=w.waterInventoryId AND w.consent.consentId=c.consentId AND c.validUpto >=:date AND t.treatmentType =:type")
	List<WastewaterTreatment> treatmentType(@Param("date") String date, @Param("type") String type);

	@Query("SELECT DISTINCT w.treatmentType FROM WastewaterTreatment w WHERE w.waterInventory.waterInventoryId= :wiId")
	List<String> treatmentTypeBywiId(@Param("wiId") int wiId);

	@Query("SELECT COUNT(w.treatmentType) FROM  WastewaterTreatment w  WHERE w.treatmentType = :treatmentType")
	int getWaterTreatmentType(@Param("treatmentType") String treatmentType);

	@Query("SELECT COUNT(wt.wastewaterTreatmentId) FROM WastewaterTreatment wt WHERE wt.treatmentType= :treatmentType")
	int checkWaterTreatmentByTreatmentType(@Param("treatmentType") String treatmentType);

	@Query("SELECT SUM(capacity) FROM WastewaterTreatment WHERE waterInventory.waterInventoryId= :waterInvId AND treatmentType= :treatmentType")
	Float sumOfcapacityByWIdTreatmentType(@Param("waterInvId") int waterInvId, @Param("treatmentType") String treatmentType);

	@Query("SELECT DISTINCT wt.treatmentType FROM WastewaterTreatment wt WHERE wt.waterInventory.waterInventoryId = :waterInv")
	List<String> getTreatmentTypeByWiId(@Param("waterInv") int waterInv);

	@Query("SELECT wt FROM WastewaterTreatment wt WHERE wt.treatmentType = :treatmentType")
	List<WastewaterTreatment> getWaterTreatmentDataByType(@Param("treatmentType") String treatmentType);

	@Query("SELECT DISTINCT(wts.label) FROM WastewaterTreatment wts")
	List<String> findAlltreatmentTypeLabel();

	@Query("SELECT COUNT(treatmentType) FROM WastewaterTreatment WHERE treatmentType = :plantType")
	int generateLabelBytrtmentType(@Param("plantType") String plantType);

	@Query("SELECT wts.capacity FROM WastewaterTreatment wts WHERE wts.label=:label")
	int findCapacityByLabel(@Param("label") String label);

	@Query("SELECT wt FROM WastewaterTreatment wt LEFT JOIN wt.waterInventory 	wi order by wt.treatmentType")
	List<WastewaterTreatment> getAllWasteWaterTreatmentData();

	@Query("SELECT DISTINCT(wts.label) FROM WastewaterTreatment wts WHERE wts.treatmentType=:treatType")
	List<String> findAlltreatmentTypeLabel(@Param("treatType") String treatType);

	@Query("SELECT NEW WastewaterTreatment(wastewaterTreatmentId,label) FROM WastewaterTreatment")
	List<WastewaterTreatment> getAllLabelAndId();

	@Query("SELECT DISTINCT NEW RegEffPoll(ref.wastewaterTreatment) FROM RegEffPoll ref LEFT JOIN  ref.wastewaterTreatment wt WHERE ref.sampE=:date")
	List<RegEffPoll> getlabelListBydate(@Param("date") String date);

	@Query("SELECT wt FROM WastewaterTreatment wt WHERE wt.waterInventory.waterInventoryId=:waterInventoryId AND wt.treatmentType=:treatemetType")
	List<WastewaterTreatment> getTreatmentType(@Param("waterInventoryId") int waterInventoryId, @Param("treatemetType") String treatemetType);

	@Query("SELECT DISTINCT NEW RegSewPoll(ref.wastewaterTreatment) FROM RegSewPoll ref LEFT JOIN  ref.wastewaterTreatment wt WHERE ref.sampS=:pdata")
	List<RegSewPoll> getSewlabelListBydate(@Param("pdata") String pdata);

	/*
	 * @Query("SELECT DISTINCT wt.typeName FROM WastewaterTreatment wt WHERE wt.waterInventory.waterInventoryId = :waterInv"
	 * ) List<String> getTreatmentTypeNameFromWiId(@Param("waterInv") int waterInv);
	 */

	/*
	 * @Query("SELECT DISTINCT new WastewaterTreatment(w.treatmentType,w.typeName, w.waterInventory) FROM WastewaterTreatment w WHERE w.waterInventory.waterInventoryId= :wiId"
	 * ) List<WastewaterTreatment> getTreatmentTypeAndName(@Param("wiId") int wiId);
	 * @Query("SELECT quantity FROM WastewaterTreatment WHERE waterInventory.waterInventoryId = :waterInvId AND treatmentType= :treatmentType AND recycledTo= :recyledTo"
	 * ) Float getQuantityByWIdTypeNameTypeRecycledTo(@Param("waterInvId") int
	 * waterInvId,@Param("treatmentType") String treatmentType,@Param("recyledTo")
	 * String recyledTo);
	 * @Query("SELECT DISTINCT(treatmentType) FROM WastewaterTreatment WHERE waterInventory.waterInventoryId = :waterInvId"
	 * ) List<String> getTreatTypeByWaterInvId(@Param("waterInvId") int waterInvId);
	 * @Query("SELECT recycledTo FROM WastewaterTreatment WHERE waterInventory.waterInventoryId = :waterInvId AND treatmentType= :treatmentType")
	 * List<String> getRecyclerToByWIdTreatmentTypeAndName(@Param("waterInvId") int waterInvId, @Param("treatmentType") String treatmentType);
	 * @Query("SELECT DISTINCT w.typeName FROM WastewaterTreatment w WHERE w.waterInventory.waterInventoryId= :wiId")
	 * List<WastewaterTreatment> typeNameBywiId(@Param("wiId") int wiId);
	 */
}
