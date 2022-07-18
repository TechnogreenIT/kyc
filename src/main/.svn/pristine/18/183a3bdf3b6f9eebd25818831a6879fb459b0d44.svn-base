package com.tes.services.environmentalofficer.waterinventory;

import com.tes.model.RegPrefilter;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface RegPrefilterServices
{

	Float getActualReadingByRsDateAndPrefilter(String date, String sourceName);

	List<Float> findActualReadingListPrefilterBySourceNameAndDatesBetween(String sourceName, String fDate, String sDate);

	int countRegPreFilterDateBtwDatesBySourceName(String pDate, String todayDate, String sourceName);

	List<RegPrefilter> findByPrefilterBetweenTwoDates(String fDate, String lDate);

	RegPrefilter save(RegPrefilter regPrefilter);

	List<RegPrefilter> getregPrefilterDataByPrefilterId(int pfId, Pageable pageable);

	Float getRegPrefilterByIdAndDate(int prefilterId, String sDate);

	Float getRegPrefilterByIdAndBetweenDates(int prefilterId, String fWeek, String sWeek);

	Float getRegPrefilterByIdAndMonth(int id, int month);

	Float getRegPrefilterByIdAndYear(int prefilterId, int year);

	List<RegPrefilter> getRegPrefilterBySelectedDate(String selectedDate);
}
