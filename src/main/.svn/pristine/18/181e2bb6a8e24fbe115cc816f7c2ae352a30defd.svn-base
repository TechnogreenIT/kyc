package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.tes.model.RegMultipleFilterData;

public interface RegMultipleFilterDataServices
{

	/*
	 * public List<RegMultipleFilterData> regularFiltersDataByfilterName(String filterName, int wiId,Pageable pageable);
	 * public int updateStartEndingActualReading(float startReading,float endReading, float actualReading, int id);
	 * public List<RegMultipleFilterData> regFiltDataFiltNameWatrInvId(String filterName, int WaterInvId,Pageable pageable);
	 * public List<RegMultipleFilterData> regFiltDataFiltnameWatrInvIdDate(String filterName, int WaterInvId, String rfDate,Pageable pageable);
	 * int countRegFilterDateBtwDatesByFilterName(String pDate,String todayDate,String filterName);
	 * Float getActualReadingByDate(String date,String filterName);
	 * Float getSumActualReadingByFilterNameBetweenDates(String filterName, String fDate, String sDate);
	 * int getMinYear();
	 * float getActualReadingByRfDateAndSourceName(String rfDate, String filterName);
	 * Float SumActualReadingByfilterNameYearMonth(String filterName,int year,int month);
	 * List<Integer> getYear();
	 * float SumActualReadingByfilterNameYear(String filterName,int year);
	 * List<Float> getActualReadingListByFilterNameDatesBetween(String filterName, String fDate, String sDate);
	 * List<RegMultipleFilterData> findAll();
	 * List<RegMultipleFilterData> findByrfDateBetweenTwoDates(String fDate, String lDate);
	 * Float getAvgActReadingByWaterInvIdFilterName(int waterInvId,String filterName);
	 * List<RegMultipleFilterData> RegularFiltersDataBydate(String date);
	 */
	RegMultipleFilterData save(RegMultipleFilterData regularFiltersData);

	List<RegMultipleFilterData> getRegMultipleFilterDataBySelectedDate(String selectedDate);

	float getSumActualReadingByRfDateAndFilterName(String rfDate, String filterName);

	Float getSumActualReadingByRfDateAndFilterName(String rfDate, String filterName, String filterType);

	List<Float> getActualReadingListByFilterNameDatesBetween(String filterName, String fDate, String sDate);

	int countRegFilterDateBtwDatesByFilterName(String pDate, String todayDate, String filterName);

	Float getActualReadingByDateAndFilterId(String date, int filterId);

	Float getSumActualReadingByFilterNameBetweenDates(String filterName, String fDate, String sDate);

	Float SumActualReadingByfilterNameYearMonth(String filterName, int year, int month);

	Float SumActualReadingByfilterNameYear(String filterName, int year);

	List<RegMultipleFilterData> findByFilterBetweenTwoDates(String fDate, String lDate);

	public List<RegMultipleFilterData> getRegularFiltersData(int multipleFilterId, Pageable pageable);

}
