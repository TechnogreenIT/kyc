package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegFiltersUseData;

public interface RegFiltersUseDataServices
{

	RegFiltersUseData save(RegFiltersUseData regFiltersUseData);

	List<RegFiltersUseData> getRegFilterUseDataBySelectedDate(String selectedDate);

	List<RegFiltersUseData> getRegFiltersUseDataByFilterUseLabel(String filterUseLabel, Pageable pageable);

	List<RegFiltersUseData> findByrfDateBetweenTwoDates(String fDate, String lDate);

	// Float getSumActualReadingListByFilterNameAndTypeAndDatesBetween(String filter,String filterType,String fWeek,String sWeek);

	/*
	 * RegFiltersUseData save(RegFiltersUseData regularFiltersUseData);
	 * Float actualReadingSumByRfDate(int esrYearFrom, int month);
	 * Float actualReadingCoolingSumByRfDate(int esrYearFrom, int esrYearTo, int month);
	 * List<RegFiltersUseData> regularFiltersUseDataByfilterName(String filterName,String filterUse, int wiId, Pageable pageable);
	 * int updateStartEndAvgReading(float startReading, float endReading, float actualReading, int id);
	 * public List<RegFiltersUseData> getRegularFilterUseByFiltrNameTypeWiId(String filterName, String type, int waterInvId,Pageable pageable);
	 * public List<RegFiltersUseData> getRegfiltrUseDataFiltrNameTypeWIdDate(String filterName, String filterType, int wId, String rfDate,Pageable pageable);
	 * public List<String> findFilterUse(String filterName, int wiId);
	 * int countRegFilterUseDateBtwDatesByFilterNameType(String pDate,String todayDate,String filterType,String filterName);
	 * Float getActualReadingByDateTypeName(String date,String filterType,String filterName);
	 * Float getActualReadingByNameTypeBetweenDates(String filterName,String filterType, String fDate, String sDate);
	 * float getActualReadingByRFDate(String type, String esrYearFrom, String esrYearTo);
	 * float getCoolingActualReadingByRFDate(String esrYearFrom, String esrYearTo);
	 * int getMinYear();
	 * float getActualReadingByRfDateFilterTypeAndFilterName(String date, String filterName, String filterType);
	 * Float sumActualReadingByYearFilterTypeNameMonth(int year, String filterType ,String filterName, int month);
	 * List<Integer> getYear();
	 * Float sumActualReadingByYearTypeAndName(int year, String filterType,String filterName);
	 * List<Float> getActualReadingListByFilterTypeAndDatesBetween(String filter,String filterType, String pDate, String sDate);
	 * List<RegFiltersUseData> findAll();
	 * List<RegFiltersUseData> findByrfDateBetweenTwoDates(String fDate, String lDate);
	 * int countRegFilterDataByFnameUName(String filterName,String filterType, String pDate, String sDate);
	 * Float getAvgActualReadingByWIdFNameFType(int waterInvId,String filterName,String filterType);
	 * List<RegFiltersUseData> RegularFiltersUseDataBydate(String date,String filtername);
	 */
	// Float findActualReadingByRfDateFilterTypeAndFilterUseName(String date, String filterType,String filterUseName);

	// affected query by filter ...........visahl
	// int findCountRegFilterUseByFilterType(String filterType,String filterName, String pDate, String sDate);
	// List<Float> getActualReadingListByFilterTypeAndDatesBetween(String filterType,String filterName, String pDate, String sDate);

	// Float getSumActualReadingByYearFilterTypeNameMonth(int year, String filterType, String filterName, int month);

	// Float sumActualReadingByYearTypeAndName(int year, String filterType, String filterName);

	float findActualReadingByDateAndLabel(String date, String filterUseLabel);

	Float getSumActualReadingByFilterLabelAndDatesBetween(String filterUseLabel, String fWeek, String sWeek);

	Float getSumActualReadingByYearMonthFilterLabel(int year, int month, String filterUseLabel);

	Float sumActualReadingByYearAndFilterUseLabel(int year, String filterUseLabel);

	Float sumOfActualReadingByFilterUseLabel(String date, String filterLabelName);

	List<Float> getActualReadingListByFilterUseLabelAndDatesBetween(String filterLabelName, String pDate, String today);

	int findCountRegFilterUseByFilterType(String filterLabelName, String pDate, String sDate);
	// int findCountRegFilterUseByFilterLabelName( String filterUseType, String filterUseLabelName, String pDate, String sDate);

	Float getActualReadingByRFDate(String string, String dateFrom, String dateTo);

}
