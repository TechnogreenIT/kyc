package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tes.model.RegMultipleFilterData;
import com.tes.repository.environmentalofficer.waterinventory.RegMultipleFilterDataRepository;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;

@Service
public class RegMultipleFilterDataServicesImpl implements RegMultipleFilterDataServices
{

	@Autowired
	RegMultipleFilterDataRepository regMultipleFilterDataRepository;

	@Override
	public RegMultipleFilterData save(RegMultipleFilterData regularFiltersData)
	{
		return regMultipleFilterDataRepository.save(regularFiltersData);
	}

	@Override
	public List<Float> getActualReadingListByFilterNameDatesBetween(String filterName, String fDate, String sDate)
	{
		return regMultipleFilterDataRepository.getActualReadingListByFilterNameDatesBetween(filterName, fDate, sDate);
	}

	@Override
	public int countRegFilterDateBtwDatesByFilterName(String pDate, String todayDate, String filterName)
	{
		return regMultipleFilterDataRepository.countRegFilterDateBtwDatesByFilterName(pDate, todayDate, filterName);
	}

	@Override
	public Float getActualReadingByDateAndFilterId(String date, int filterId)
	{
		return regMultipleFilterDataRepository.getActualReadingByDateAndFilterId(date, filterId);
	}

	@Override
	public Float getSumActualReadingByFilterNameBetweenDates(String filterName, String fDate, String sDate)
	{
		return regMultipleFilterDataRepository.getSumActualReadingByFilterNameBetweenDates(filterName, fDate, sDate);
	}

	@Override
	public Float SumActualReadingByfilterNameYearMonth(String filterName, int year, int month)
	{
		return regMultipleFilterDataRepository.SumActualReadingByfilterNameYearMonth(filterName, year, month);
	}

	@Override
	public Float SumActualReadingByfilterNameYear(String filterName, int year)
	{
		return regMultipleFilterDataRepository.SumActualReadingByfilterNameYear(filterName, year);
	}

	@Override
	public List<RegMultipleFilterData> findByFilterBetweenTwoDates(String fDate, String lDate)
	{
		return regMultipleFilterDataRepository.findByFilterBetweenTwoDates(fDate, lDate);
	}

	@Override
	public List<RegMultipleFilterData> getRegularFiltersData(int multipleFilterId, Pageable pageable)
	{
		return regMultipleFilterDataRepository.getRegularFiltersData(multipleFilterId, pageable);
	}

	@Override
	public Float getSumActualReadingByRfDateAndFilterName(String rfDate, String filterName, String filterType)
	{
		return regMultipleFilterDataRepository.getSumActualReadingByRfDateAndFilterName(rfDate, filterName, filterType);
	}

	@Override
	public float getSumActualReadingByRfDateAndFilterName(String rfDate, String filterName)
	{
		return regMultipleFilterDataRepository.getActualReadingByRfDateAndFilterName(rfDate, filterName);
	}

	@Override
	public List<RegMultipleFilterData> getRegMultipleFilterDataBySelectedDate(String selectedDate)
	{
		return regMultipleFilterDataRepository.getRegMultipleFilterDataBySelectedDate(selectedDate);
	}

	/*
	 * @Override
	 * public List<RegMultipleFilterData> regularFiltersDataByfilterName(String filterName, int wiId, Pageable pageable) {
	 * return regularFiltersDatarepository.regularFiltersDataByfilterName(filterName, wiId,pageable);
	 * }
	 * @Override
	 * public int updateStartEndingActualReading(float startReading, float endReading, float actualReading, int id) {
	 * return regularFiltersDatarepository.updateStartEndingActualReading(startReading, endReading, actualReading, id);
	 * }
	 * @Override
	 * public List<RegMultipleFilterData> regFiltDataFiltNameWatrInvId(String filterName, int WaterInvId,Pageable pageable) {
	 * return regularFiltersDatarepository.regFiltDataFiltNameWatrInvId(filterName, WaterInvId,pageable);
	 * }
	 * @Override
	 * public List<RegMultipleFilterData> regFiltDataFiltnameWatrInvIdDate(String filterName, int WaterInvId, String rfDate,Pageable pageable) {
	 * return regularFiltersDatarepository.regFiltDataFiltnameWatrInvIdDate(filterName, WaterInvId, rfDate,pageable);
	 * }
	 * @Override
	 * public int countRegFilterDateBtwDatesByFilterName(String pDate, String todayDate, String filterName) {
	 * return regularFiltersDatarepository.countRegFilterDateBtwDatesByFilterName(pDate,todayDate,filterName);
	 * }
	 * @Override
	 * public Float getActualReadingByDate(String date,String filterName) {
	 * return regularFiltersDatarepository.getActualReadingByDate(date,filterName);
	 * }
	 * @Override
	 * public Float getSumActualReadingByFilterNameBetweenDates(String filterName, String fDate, String sDate) {
	 * return regularFiltersDatarepository.getSumActualReadingByFilterNameBetweenDates(filterName, fDate, sDate);
	 * }
	 * @Override
	 * public int getMinYear() {
	 * return regularFiltersDatarepository.getMinYear();
	 * }
	 * @Override
	 * public float getActualReadingByRfDateAndSourceName(String rfDate, String filterName) {
	 * return regularFiltersDatarepository.getActualReadingByRfDateAndSourceName(rfDate,filterName);
	 * }
	 * @Override
	 * public Float SumActualReadingByfilterNameYearMonth(String filterName, int year, int month) {
	 * return regularFiltersDatarepository.SumActualReadingByfilterNameYearMonth(filterName, year, month);
	 * }
	 * @Override
	 * public List<Integer> getYear() {
	 * return regularFiltersDatarepository.getYear();
	 * }
	 * @Override
	 * public float SumActualReadingByfilterNameYear(String filterName, int year) {
	 * return regularFiltersDatarepository.SumActualReadingByfilterNameYear(filterName, year);
	 * }
	 * @Override
	 * public List<Float> getActualReadingListByFilterNameDatesBetween(String filterName, String fDate, String sDate) {
	 * return regularFiltersDatarepository.getActualReadingListByFilterNameDatesBetween(filterName, fDate, sDate);
	 * }
	 * @Override
	 * public List<RegMultipleFilterData> findAll() {
	 * return regularFiltersDatarepository.findAll();
	 * }
	 * @Override
	 * public List<RegMultipleFilterData> findByrfDateBetweenTwoDates(String fDate, String lDate) {
	 * return regularFiltersDatarepository.findByrfDateBetweenTwoDates(fDate,lDate);
	 * }
	 * @Override
	 * public Float getAvgActReadingByWaterInvIdFilterName(int waterInvId, String filterName) {
	 * return regularFiltersDatarepository.getAvgActReadingByWaterInvIdFilterName(waterInvId, filterName);
	 * }
	 * @Override
	 * public List<RegMultipleFilterData> RegularFiltersDataBydate(String date) {
	 * return regularFiltersDatarepository.RegularFiltersDataBydate(date);
	 * }
	 */

	// @Override
	// public Float getSumActualReadingByRfDateAndFilterName(String rfDate, String filterName,String filterType) {
	// return regMultipleFilterDataRepository.getActualReadingByRfDateAndFilterName(rfDate, filterName,filterType);
	// }
}
