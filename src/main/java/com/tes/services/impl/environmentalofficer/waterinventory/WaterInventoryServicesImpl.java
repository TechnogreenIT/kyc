package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.tes.model.WaterInventory;
import com.tes.repository.environmentalofficer.waterinventory.WaterInventoryRepository;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;

@Service
public class WaterInventoryServicesImpl implements WaterInventoryServices
{

	@Autowired
	WaterInventoryRepository waterInventoryRepository;

	@Override
	public List<WaterInventory> getwaterInventoryData(int companyId, String todaysDate)
	{
		return waterInventoryRepository.getwaterInventoryData(companyId, todaysDate);
	}

	@Override
	public WaterInventory save(WaterInventory objWaterInventory)
	{
		return waterInventoryRepository.save(objWaterInventory);
	}

	/*
	 * @Override
	 * public int findByWaterInventoryId(int consentId, String canteen, String cookingcan, String filter_plant,
	 * String domestic_use_of_source, String industrial_use_of_source, String laundry_use_of_source,
	 * String fh_use_of_source, String treatment_plant) {
	 * return waterInventoryRepository.findByWaterInventoryId(consentId,canteen,cookingcan,filter_plant,domestic_use_of_source,industrial_use_of_source,laundry_use_of_source,fh_use_of_source,treatment_plant);
	 * }
	 */

	// Effected By Water Inventory ........by vishal
	/*
	 * @Override public List<Object[]> WaterInventoryDataByCid() { return
	 * waterInventoryRepository.WaterInventoryDataByCid(); }
	 */

	@Override
	public List<WaterInventory> findByConsentId(int companyId)
	{
		return waterInventoryRepository.findByConsentId(companyId);
	}

	@Override
	public List<WaterInventory> getUseOfSource()
	{
		return waterInventoryRepository.getUseOfSource();
	}

	// Effected By Water Inventory ........by vishal
	/*
	 * @Override
	 * public List<WaterInventory> getHouseCookingCateenData() {
	 * return waterInventoryRepository.getHouseCookingCateenData();
	 * }
	 * @Override
	 * public List<WaterInventory> getListOfDomesticIndustrialLaundryFireHydrant() {
	 * return waterInventoryRepository.getListOfDomesticIndustrialLaundryFireHydrant();
	 * }
	 * @Override
	 * public WaterInventory getWaterInventoryInfo(int waterInvId) {
	 * return waterInventoryRepository.getWaterInventoryInfo(waterInvId);
	 * }
	 */

	// Effected By Water Inventory ........by vishal
	/*
	 * @Override
	 * public List<UseOfWater> getUseOfwater(int waterInvId) {
	 * return waterInventoryRepository.getUseOfwater(waterInvId);
	 * }
	 */

	@Override
	public int getWaterInventoryIdByConsent(String todaysDate)
	{
		return waterInventoryRepository.getWaterInventoryIdByConsent(todaysDate);
	}

	@Override
	public int getWaterInventoryId(int companyId, String todaysDate)
	{
		return waterInventoryRepository.getWaterInventoryId(companyId, todaysDate);
	}

	@Override
	public List<WaterInventory> getHouseCookingCateenData()
	{
		return waterInventoryRepository.getHouseCookingCateenData();
	}

	@Override
	public List<WaterInventory> getwaterInventoryById(int companyId, String todaysDate, PageRequest pageRequest)
	{
		return waterInventoryRepository.getwaterInventoryById(companyId, todaysDate, pageRequest);
	}
}
