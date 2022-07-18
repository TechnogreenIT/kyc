package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegFiltersUseData;
import com.tes.repository.environmentalofficer.waterinventory.RegFiltersUseDataRepository;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;

@Service
public class RegFilterUseDataServicesImpl implements RegFiltersUseDataServices
{

	@Autowired
	RegFiltersUseDataRepository regularFiltersUseDataRepository;

	@Override
	public List<RegFiltersUseData> findByrfDateBetweenTwoDates(String fDate, String lDate)
	{
		return regularFiltersUseDataRepository.findByrfDateBetweenTwoDates(fDate, lDate);
	}

	@Override
	public List<RegFiltersUseData> getRegFiltersUseDataByFilterUseLabel(String filterUseLabel, Pageable pageable)
	{
		return regularFiltersUseDataRepository.getRegFiltersUseDataByFilterUseLabel(filterUseLabel, pageable);
	}

	@Override
	public List<Float> getActualReadingListByFilterUseLabelAndDatesBetween(String filterLabelName, String pDate,
			String today)
	{
		return regularFiltersUseDataRepository.getActualReadingListByFilterUseLabelAndDatesBetween(filterLabelName, pDate, today);
	}

	// affected query by filter ...........visahl
	// @Override
	// public Float getSumActualReadingListByFilterNameAndTypeAndDatesBetween(String filter, String filterType,
	// String fWeek, String sWeek) {
	// return regularFiltersUseDataRepository.getSumActualReadingListByFilterNameAndTypeAndDatesBetween(filter, filterType, fWeek, sWeek);
	// }

	// affected query by filter ...........visahl
	// @Override
	// public Float getSumActualReadingByYearFilterTypeNameMonth(int year, String filterType, String filterName,
	// int month) {
	// return regularFiltersUseDataRepository.getSumActualReadingByYearFilterTypeNameMonth(year, filterType, filterName, month);
	// }

	/*
	 * @Override
	 * public RegFiltersUseData save(RegFiltersUseData regularFiltersUseData) {
	 * return regularFiltersUseDataRepository.save(regularFiltersUseData);
	 * }
	 * @Override
	 * public Float actualReadingSumByRfDate(int esrYearFrom, int month) {
	 * return regularFiltersUseDataRepository.actualReadingSumByRfDate(esrYearFrom, month);
	 * }
	 * @Override
	 * public Float actualReadingCoolingSumByRfDate(int esrYearFrom, int esrYearTo, int month) {
	 * return regularFiltersUseDataRepository.actualReadingCoolingSumByRfDate(esrYearFrom,esrYearTo,month);
	 * }
	 * @Override
	 * public int updateStartEndAvgReading(float startReading, float endReading, float actualReading, int id) {
	 * return regularFiltersUseDataRepository.updateStartEndAvgReading(startReading, endReading, actualReading, id);
	 * }
	 * @Override
	 * public List<RegFiltersUseData> getRegularFilterUseByFiltrNameTypeWiId(String filterName, String type,int waterInvId,Pageable pageable) {
	 * return regularFiltersUseDataRepository.getRegularFilterUseByFiltrNameTypeWiId(filterName, type, waterInvId, pageable);
	 * }
	 * @Override
	 * public List<RegFiltersUseData> getRegfiltrUseDataFiltrNameTypeWIdDate(String filterName, String filterType, int wId,
	 * String rfDate,Pageable pageable) {
	 * return regularFiltersUseDataRepository.getRegfiltrUseDataFiltrNameTypeWIdDate(filterName, filterType, wId, rfDate,pageable);
	 * }
	 * @Override
	 * public List<String> findFilterUse(String filterName, int wiId) {
	 * return regularFiltersUseDataRepository.findFilterUse(filterName, wiId);
	 * }
	 * @Override
	 * public List<RegFiltersUseData> regularFiltersUseDataByfilterName(String filterName, String filterUse, int wiId, Pageable pageable) {
	 * return regularFiltersUseDataRepository.regularFiltersUseDataByfilterName(filterName, filterUse, wiId, pageable);
	 * }
	 * @Override
	 * public int countRegFilterUseDateBtwDatesByFilterNameType(String pDate, String todayDate, String filterType,String filterName) {
	 * return regularFiltersUseDataRepository.countRegFilterUseDateBtwDatesByFilterNameType(pDate, todayDate, filterType, filterName);
	 * }
	 * @Override
	 * public float getActualReadingByRFDate(String type,String esrYearFrom, String esrYearTo) {
	 * return regularFiltersUseDataRepository.getActualReadingByRFDate(type, esrYearFrom, esrYearTo);
	 * }
	 * @Override
	 * public float getCoolingActualReadingByRFDate(String esrYearFrom, String esrYearTo) {
	 * return regularFiltersUseDataRepository.getCoolingActualReadingByRFDate(esrYearFrom, esrYearTo);
	 * }
	 * @Override
	 * public Float getActualReadingByDateTypeName(String date, String filterType, String filterName) {
	 * return regularFiltersUseDataRepository.getActualReadingByDateTypeName(date, filterType, filterName);
	 * }
	 * @Override
	 * public Float getActualReadingByNameTypeBetweenDates(String filterName, String filterType, String fDate,
	 * String sDate) {
	 * return regularFiltersUseDataRepository.getActualReadingByNameTypeBetweenDates(filterName, filterType, fDate, sDate);
	 * }
	 * @Override
	 * public int getMinYear() {
	 * return regularFiltersUseDataRepository.getMinYear();
	 * }
	 * @Override
	 * public float getActualReadingByRfDateFilterTypeAndFilterName(String date, String filterName, String filterType) {
	 * return regularFiltersUseDataRepository.getActualReadingByRfDateFilterTypeAndFilterName(date,filterName,filterType);
	 * }
	 * @Override
	 * public Float sumActualReadingByYearFilterTypeNameMonth(int year, String filterType, String filterName,
	 * int month) {
	 * return regularFiltersUseDataRepository.sumActualReadingByYearFilterTypeNameMonth(year, filterType, filterName, month);
	 * }
	 * @Override
	 * public List<Integer> getYear() {
	 * return regularFiltersUseDataRepository.getYear();
	 * }
	 * @Override
	 * public Float sumActualReadingByYearTypeAndName(int year, String filterType, String filterName) {
	 * return regularFiltersUseDataRepository.sumActualReadingByYearTypeAndName(year, filterType, filterName);
	 * }
	 * @Override
	 * public List<Float> getActualReadingListByFilterTypeAndDatesBetween(String filter,String filterType, String pDate, String sDate) {
	 * return regularFiltersUseDataRepository.getActualReadingListByFilterTypeAndDatesBetween(filter,filterType, pDate, sDate);
	 * }
	 * @Override
	 * public List<RegFiltersUseData> findAll() {
	 * return regularFiltersUseDataRepository.findAll();
	 * }
	 * @Override
	 * public List<RegFiltersUseData> findByrfDateBetweenTwoDates(String fDate, String lDate) {
	 * return regularFiltersUseDataRepository.findByrfDateBetweenTwoDates(fDate,lDate);
	 * }
	 * @Override
	 * public int countRegFilterDataByFnameUName(String filterName, String filterType, String pDate, String sDate) {
	 * return regularFiltersUseDataRepository.countRegFilterDataByFnameUName(filterName, filterType, pDate, sDate);
	 * }
	 * @Override
	 * public Float getAvgActualReadingByWIdFNameFType(int waterInvId, String filterName, String filterType) {
	 * return regularFiltersUseDataRepository.getAvgActualReadingByWIdFNameFType(waterInvId, filterName, filterType);
	 * }
	 * @Override
	 * public List<RegFiltersUseData> RegularFiltersUseDataBydate(String date, String filtername) {
	 * return regularFiltersUseDataRepository.RegularFiltersUseDataBydate(date,filtername);
	 * }
	 */
	// @Override
	// public Float findActualReadingByRfDateFilterTypeAndFilterUseName(String date, String filterType,
	// String filterUseName) {
	// return regularFiltersUseDataRepository.findActualReadingByRfDateFilterTypeAndFilterUseName(date, filterType, filterUseName);
	// }

	// @Override
	// public List<Float> getActualReadingListByFilterTypeAndDatesBetween(String filter, String filterType, String pDate,String sDate) {
	// return regularFiltersUseDataRepository.getActualReadingListByFilterTypeAndDatesBetween(filter, filterType, pDate, sDate);
	//
	// }

	/*
	 * affected query by filter ...........visahl
	 * @Override public int findCountRegFilterUseByFilterType(String filterType,
	 * String filterName, String pDate, String sDate) { return
	 * regularFiltersUseDataRepository.findCountRegFilterUseByFilterType(filterType,
	 * filterName, pDate, sDate); }
	 */

	@Override
	public int findCountRegFilterUseByFilterType(String filterLabelName, String pDate, String sDate)
	{
		return regularFiltersUseDataRepository.findCountRegFilterUseByFilterType(filterLabelName, pDate, sDate);
	}

	@Override
	public RegFiltersUseData save(RegFiltersUseData regFiltersUseData)
	{
		return regularFiltersUseDataRepository.save(regFiltersUseData);
	}

	@Override
	public Float sumOfActualReadingByFilterUseLabel(String date, String filterLabelName)
	{
		return regularFiltersUseDataRepository.sumOfActualReadingByFilterUseLabel(date, filterLabelName);
	}

	@Override
	public float findActualReadingByDateAndLabel(String date, String filterUseLabel)
	{
		return regularFiltersUseDataRepository.findActualReadingByDateAndLabel(date, filterUseLabel);
	}

	@Override
	public Float getSumActualReadingByYearMonthFilterLabel(int year, int month, String filterUseLabel)
	{
		return regularFiltersUseDataRepository.getSumActualReadingByYearMonthFilterLabel(year, month, filterUseLabel);
	}

	@Override
	public Float sumActualReadingByYearAndFilterUseLabel(int year, String filterUseLabel)
	{
		return regularFiltersUseDataRepository.sumActualReadingByYearAndFilterUseLabel(year, filterUseLabel);
	}

	@Override
	public Float getSumActualReadingByFilterLabelAndDatesBetween(String filterUseLabel, String fWeek, String sWeek)
	{
		return regularFiltersUseDataRepository.getSumActualReadingByFilterLabelAndDatesBetween(filterUseLabel, fWeek, sWeek);
	}

	@Override
	public List<RegFiltersUseData> getRegFilterUseDataBySelectedDate(String selectedDate)
	{
		return regularFiltersUseDataRepository.getRegFilterUseDataBySelectedDate(selectedDate);
	}

	@Override
	public Float getActualReadingByRFDate(String type, String dateFrom, String dateTo)
	{
		return regularFiltersUseDataRepository.getActualReadingByRFDate(type, dateFrom, dateTo);
	}

	// @Override
	// public int findCountRegFilterUseByFilterLabelName(String filterUseType, String filterUseLabelName, String pDate,
	// String sDate) {
	// return regularFiltersUseDataRepository.findCountRegFilterUseByFilterLabelName(filterUseType, filterUseLabelName, pDate, sDate);
	// }

	// @Override
	// public Float sumActualReadingByYearTypeAndName(int year, String filterType, String filterName) {
	// return regularFiltersUseDataRepository.sumActualReadingByYearTypeAndName(year, filterType, filterName);
	// }

}
