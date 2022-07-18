package com.tes.services.environmentalofficer;

import java.util.List;
import com.tes.model.EsrProductWater;

public interface EsrProductWaterServices
{

	EsrProductWater save(EsrProductWater esrProductWater);

	List<EsrProductWater> getAllDataByProNameTypeYear(String year, String productName, String productType);

	List<EsrProductWater> getEsrProductWaterData(String year, String type);

	List<EsrProductWater> getEsrProductWaterDataMonthly(String year, String month1, String type);

	List<EsrProductWater> getAllDataByYear(String esrWholeYear, String type);

}
