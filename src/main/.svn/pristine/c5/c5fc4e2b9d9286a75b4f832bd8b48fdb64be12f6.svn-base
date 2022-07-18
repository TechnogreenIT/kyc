package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.WasteDescriptionConsistency;

public interface WasteDescriptionConsistencyServices
{

	WasteDescriptionConsistency save(WasteDescriptionConsistency wasteDescriptionConsistency);

	public List<WasteDescriptionConsistency> wdDataBywasteDesc(int hmId);

	public List<WasteDescriptionConsistency> wdDataByConsistency(int hmId);

	public List<WasteDescriptionConsistency> getWasteDescriptionByDate(String from, String to);

	public float getSumWasteQuantityByWasteName(String fromDate, String toDate, String productName, String categoryNo);

	public List<String> getInHouseWasteNameBetweenDate(String fromDate, String toDate);

	public List<WasteDescriptionConsistency> getHazardousWasteNameBetweenDate(String fromDate, String toDate);

}
