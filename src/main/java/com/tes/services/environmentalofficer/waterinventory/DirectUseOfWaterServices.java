package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import java.util.TreeSet;
import com.tes.model.DirectUseOfWater;

public interface DirectUseOfWaterServices
{

	DirectUseOfWater save(DirectUseOfWater objDirectUseOfWater);

	List<DirectUseOfWater> getUsedDirectUseNameList();

	TreeSet<String> getDirectUseName(int waterInventoryId);

	TreeSet<String> getUsedDirectUseName();

	List<DirectUseOfWater> getAllWhereToUseAndIsIndustries();

	boolean getDirectUseisIndustrial(String label);

	List<DirectUseOfWater> getUseOfWaterByWhereToUse(String whereToUse);

	List<DirectUseOfWater> directUseOfWaterList(int wsId);

	//// mmm
	boolean getDomesticUseType(int wiid, String type);
	// int waterInventoryId, String type

	/// mmmm for water loss purpose
	Float getWaterLoss(String type);

	List<DirectUseOfWater> getIndustrialAllData(String type);

}
