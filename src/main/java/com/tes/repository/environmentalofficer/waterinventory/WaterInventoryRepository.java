package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.WaterInventory;

@Repository
public interface WaterInventoryRepository extends JpaRepository<WaterInventory, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	WaterInventory save(WaterInventory waterInventory);// there is no need of save in save() query in repository in whole project .........By Vishal

	@Query("SELECT NEW WaterInventory(w.waterInventoryId) FROM WaterInventory w LEFT JOIN w.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND c.consStatus!='Expired' AND c.companyProfile.companyProfileId = :companyId AND (c.validUpto>=:todaysDate OR ce.validUpto>=:todaysDate)")
	public List<WaterInventory> getwaterInventoryData(@Param("companyId") int companyId, @Param("todaysDate") String todaysDate);

	@Query("SELECT NEW WaterInventory(w.waterInventoryId) FROM WaterInventory w LEFT JOIN w.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType = 'Consent to Operate' AND c.consStatus!='Expired' AND c.companyProfile.companyProfileId=:companyId AND (c.validUpto >= :todaysDate OR ce.validUpto >= :todaysDate) ORDER BY c.consentId DESC")
	public List<WaterInventory>  getWaterInventorygetId(@Param("companyId") int companyId, @Param("todaysDate") String todaysDate ,Pageable pageable);
	//@Query("SELECT NEW WaterInventory(w.waterInventoryId) FROM WaterInventory w LEFT JOIN w.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND c.consStatus!='Expired' AND c.companyProfile.companyProfileId = :companyId AND (c.validUpto>=:todaysDate OR ce.validUpto>=:todaysDate) ORDER BY w.waterInventoryId DESC")
	

	// Effected By Water Inventory ........by vishal
	/*
	 * @Query("SELECT w.houseCanteen,w.cookingCanteen  FROM WaterInventory w LEFT JOIN w.consent")
	 * public List<Object[]> WaterInventoryDataByCid();
	 */

	@Query("SELECT w FROM WaterInventory w,Consent c WHERE w.consent.consentId=c.consentId AND c.companyProfile.companyProfileId=:companyId")
	public List<WaterInventory> findByConsentId(@Param("companyId") int companyId);

	@Query("SELECT wi FROM WaterInventory wi")
	List<WaterInventory> getUseOfSource();

	// Effected By Water Inventory ........by vishal
	/*
	 * @Query("SELECT DISTINCT NEW WaterInventory(wi.houseCanteen, wi.cookingCanteen) FROM WaterInventory wi")
	 * List<WaterInventory> getHouseCookingCateenData();
	 * @Query("SELECT NEW WaterInventory(w.domesticUseOfSource,w.industrialUseOfSource,w.laundryUseOfSource,w.fireHydrantUseOfSource) FROM WaterInventory w")
	 * List<WaterInventory> getListOfDomesticIndustrialLaundryFireHydrant();
	 * @Query("SELECT NEW WaterInventory(w.waterInventoryId,w.filterationPlant,w.domesticUseOfSource,w.industrialUseOfSource,w.laundryUseOfSource,w.fireHydrantUseOfSource,w.waterTreatment) FROM WaterInventory w WHERE w.consent.consentId= :waterInvId")
	 * WaterInventory getWaterInventoryInfo(@Param("waterInvId") int waterInvId);
	 */

	// Effected By Water Inventory ........by vishal
	/*
	 * @Query("SELECT uw FROM UseOfWater uw WHERE waterInventory.waterInventoryId= :waterInvId")
	 * List<UseOfWater> getUseOfwater(@Param("waterInvId") int waterInvId);
	 */

//	@Query("SELECT w.waterInventoryId FROM WaterInventory w LEFT JOIN w.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType= 'Consent to Operate' AND (c.validUpto>=:todaysDate OR ce.validUpto>=:todaysDate)")
//	int getWaterInventoryIdByConsent(@Param("todaysDate") String todaysDate);
	
	///mmm
	@Query("SELECT w.waterInventoryId FROM WaterInventory w LEFT JOIN w.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType= 'Consent to Operate' AND (c.validUpto>=:todaysDate OR ce.validUpto>=:todaysDate) ORDER BY w.waterInventoryId DESC")
	public List<Integer> getWaterInventoryIdByConsent(@Param("todaysDate") String todaysDate,Pageable pageable);

	@Query("SELECT DISTINCT NEW WaterInventory(wi.isHouseCanteen, wi.isCookingCanteen) FROM WaterInventory wi")
	List<WaterInventory> getHouseCookingCateenData();

	@Query("SELECT NEW WaterInventory(w.waterInventoryId) FROM WaterInventory w LEFT JOIN w.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND c.consStatus!='Expired' AND c.companyProfile.companyProfileId = :companyId AND (c.validUpto>=:todaysDate OR ce.validUpto>=:todaysDate) ORDER BY w.waterInventoryId DESC")
	public List<WaterInventory> getwaterInventoryById(@Param("companyId") int companyId, @Param("todaysDate") String todaysDate, Pageable pageable);
}
