package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegWaterSourceData;

public interface RegWaterSourceDataServices
{

	RegWaterSourceData save(RegWaterSourceData regularSourceData);

	List<RegWaterSourceData> regularSourceDataBySourceId(int wsId, Pageable pageable);

	int getRegSourceYearOfLastInput();

	Float getActualReadingByRsDateAndSourceName(String sdate, String sourceName);

	List<Float> findActualReadingListBySourceNameAndDatesBetween(String sourceName, String fDate, String sDate);

	int getCountByRsDateAndSourceName(String pDate, String today, String sourceName);

	List<RegWaterSourceData> getAllRegularSourceDataBetweenDates(String fDate, String lDate);

	Float getActualReadingSumForDate(String date);

	Float getSumActualReadingBySourceNameAndBetweenDates(String sourceName, String fWeek, String sWeek);

	Float getSumActualByYearMonth(int year, int month, String sourceName);

	Float getSumOfActualReadingByYearAndSourceName(int year, String sourceName);

	List<Integer> getWaterMinYear();

	List<RegWaterSourceData> getRegWaterSourceDataBySelectedDate(String selectedDate);

	Float getSumOfActualReadingByMonthAndYear(int month, int year);

	List<Integer> getRegularWaterSourceDataYearList();

	Float getSumOfActualReadingByYear(int year);

	Float getSumActualReadingByWaterInvId(int waterInvId);
}
