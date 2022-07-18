package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import com.tes.model.WaterInventory;

public interface WaterInventoryServices
{

	public List<WaterInventory> getwaterInventoryData(int companyId, String todaysDate);

	public WaterInventory save(WaterInventory objWaterInventory);

	// Effected By Water Inventory ........by vishal
	// List<Object[]> WaterInventoryDataByCid();

	public List<WaterInventory> findByConsentId(int companyId);

	public int getWaterInventoryId(int companyId, String todaysDate);

	List<WaterInventory> getUseOfSource();

	// Effected By Water Inventory ........by vishal
	// List<WaterInventory> getHouseCookingCateenData();

	// Effected By Water Inventory ........by vishal
	// List<WaterInventory> getListOfDomesticIndustrialLaundryFireHydrant();

	// Effected By Water Inventory ........by vishal
	// List<UseOfWater> getUseOfwater(int waterInvId);

	// Effected By Water Inventory ........by vishal
	// WaterInventory getWaterInventoryInfo(int waterInvId);

	int getWaterInventoryIdByConsent(String todaysDate);

	public List<WaterInventory> getHouseCookingCateenData();

	public List<WaterInventory> getwaterInventoryById(int companyId, String todaysDate, PageRequest pageRequest);
}
