package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegDirectUseOfWaterData;

public interface RegDirectUseOfWaterDataServices
{

	// affected by ashish from
	// List<Float> getListOfActualReadingByWhereToUseAndBetweenDate(String directUse, String prevDate, String today);
	// List<RegDirectUseOfWaterData> findByrWDateBetweenTwoDates(String fDate, String lDate);
	// Float getSumOfActualReadingByYearAndSType(int year, String sourceType);
	// here

	RegDirectUseOfWaterData save(RegDirectUseOfWaterData regularWaterUseData);

	List<RegDirectUseOfWaterData> getRegDirectUseOfWaterDataBySelectedDate(String selectedDate);

	Float actualReadingByYearSourceTypeMonth(int year, String whereToUse, int k);

	Float getSumOfActualReadingByYearAndSType(int year, String sourceType);

	Float getActualReadingBySourceTypeAndBetweenDate(String sourceType, String fDate, String sDate);

	Float getActualReadingByDateAndSourceType(String date, String sourceType);

	public List<RegDirectUseOfWaterData> findByrWDateBetweenTwoDates(String fDate, String lDate);

	public List<Float> getListOfActualReadingByWhereToUseAndBetweenDate(String directUse, String prevDate, String today);

	List<RegDirectUseOfWaterData> getRegDirectUseOfWaterData(String whereToUse, Pageable pageable);

	Float getActualReadingbydate(String type, String dateFrom, String dateTo);

}
