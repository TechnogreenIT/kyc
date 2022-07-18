package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegDirectUseOfWaterData;
import com.tes.repository.environmentalofficer.waterinventory.RegDirectUseOfWaterDataRepository;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;

@Service
public class RegDirectUseOfWaterDataServicesImpl implements RegDirectUseOfWaterDataServices
{

	@Autowired
	RegDirectUseOfWaterDataRepository regularWaterUseDataRepository;

	/*
	 * @Override
	 * public DirectUseOfWater save(DirectUseOfWater regularWaterUseData) {
	 * return null;//regularWaterUseDataRepository.save(regularWaterUseData);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> regularWaterUseDataByDomestic(int wiId, Pageable pageable) {
	 * return regularWaterUseDataRepository.regularWaterUseDataByDomestic(wiId, pageable);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> getRegularWaterUseDataByIndName(String indName, int wiId, Pageable pageable) {
	 * return regularWaterUseDataRepository.getRegularWaterUseDataByIndName(indName, wiId,pageable);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> getregularWaterUseDataByFireHydrant(int wiId, Pageable pageable) {
	 * return regularWaterUseDataRepository.getregularWaterUseDataByFireHydrant(wiId, pageable);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> getregularWaterUseDataByFireHydrantDate(int wiId, String date, Pageable pageable) {
	 * return regularWaterUseDataRepository.getregularWaterUseDataByFireHydrantDate(wiId, date, pageable);
	 * }
	 * @Override
	 * public Float actualReadingSumByRwDate(int esrYearFrom, int esrYearTo, int month) {
	 * return regularWaterUseDataRepository.actualReadingSumByRwDate(esrYearFrom, esrYearTo, month);
	 * }
	 * @Override
	 * public Float actualReadingCoolingSumByRwDate(int esrYearFrom, int esrYearTo, int month) {
	 * return regularWaterUseDataRepository.actualReadingCoolingSumByRwDate(esrYearFrom, esrYearTo, month);
	 * }
	 * @Override
	 * public Float avgActualReading(int esrYearFrom, int esrYearTo, int month) {
	 * return regularWaterUseDataRepository.avgActualReading(esrYearFrom, esrYearTo, month);
	 * }
	 * @Override
	 * public Float avgOthersActualReading(int esrYearFrom, int esrYearTo, int month) {
	 * return regularWaterUseDataRepository.avgOthersActualReading(esrYearFrom, esrYearTo, month);
	 * }
	 * @Override
	 * public int updateStartEndActualReading(float startReading, float endReading, float actualReading, int id) {
	 * return regularWaterUseDataRepository.updateStartEndActualReading(startReading, endReading, actualReading, id);
	 * }
	 * @Override
	 * public DirectUseOfWater getRWUDByIndNameWaterInvDate(String indName, int waterInvId, String date) {
	 * return regularWaterUseDataRepository.getRWUDByIndNameWaterInvDate(indName, waterInvId, date);
	 * }
	 * @Override
	 * public DirectUseOfWater getRWUDBySTypeWiIdDate(int WaterInvId, String date) {
	 * return regularWaterUseDataRepository.getRWUDBySTypeWiIdDate(WaterInvId, date);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> getRegularWaterUseDataByIndNameDate(String indName, int wiId, String date, Pageable pageable) {
	 * return regularWaterUseDataRepository.getRegularWaterUseDataByIndNameDate(indName, wiId, date, pageable);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> getregularWaterUseDataByLaundryDate(int wiId, String date, Pageable pageable) {
	 * return regularWaterUseDataRepository.getregularWaterUseDataByLaundryDate(wiId, date, pageable);
	 * }
	 * @Override
	 * public DirectUseOfWater rWUdata(int wiId) {
	 * return regularWaterUseDataRepository.rWUdata(wiId);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> regularWaterUseDataByDomesticDate(int wiId, String date, Pageable pageable) {
	 * return regularWaterUseDataRepository.regularWaterUseDataByDomesticDate(wiId, date, pageable);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> regularWaterUseDataByLaundry(int wiId, Pageable pageable) {
	 * return regularWaterUseDataRepository.regularWaterUseDataByLaundry(wiId, pageable);
	 * }
	 * @Override
	 * public int countRegWaterUseDataByDateSType(String prevDate, String todayDate, String sourceType) {
	 * return regularWaterUseDataRepository.countRegWaterUseDataByDateSType(prevDate, todayDate,sourceType);
	 * }
	 * @Override
	 * public Float getProcessSumActualReading(String type, String esrYearFrom, String esrYearTo) {
	 * return regularWaterUseDataRepository.getProcessSumActualReading(type, esrYearFrom, esrYearTo);
	 * }
	 * @Override
	 * public Float getCoolingSumActualReading(String esrYearFrom, String esrYearTo) {
	 * return regularWaterUseDataRepository.getCoolingSumActualReading(esrYearFrom, esrYearTo);
	 * }
	 * @Override
	 * public Float getDomesticSumActualReading(String esrYearFrom, String esrYearTo) {
	 * return regularWaterUseDataRepository.getDomesticSumActualReading(esrYearFrom, esrYearTo);
	 * }
	 * @Override
	 * public Float getFireHydrantAndLaundryAvgActualReading(String esrYearFrom, String esrYearTo) {
	 * return regularWaterUseDataRepository.getFireHydrantAndLaundryAvgActualReading(esrYearFrom, esrYearTo);
	 * }
	 * @Override
	 * public Float getActualReadingByDateAndSourceType(String date, String sourceType) {
	 * return regularWaterUseDataRepository.getActualReadingByDateAndSourceType(date, sourceType);
	 * }
	 * @Override
	 * public DirectUseOfWater getStartEndReadingByDateSourceType(String date, String sourceType) {
	 * return regularWaterUseDataRepository.getStartEndReadingByDateSourceType(date, sourceType);
	 * }
	 * @Override
	 * public int getMinYear() {
	 * return regularWaterUseDataRepository.getMinYear();
	 * }
	 * @Override
	 * public List<Integer> getYear() {
	 * return regularWaterUseDataRepository.getYear();
	 * }
	 * @Override
	 * public List<Float> getListOfActualReadingBySourceTypeAndBetweenDate(String directUse, String prevDate,
	 * String today) {
	 * return regularWaterUseDataRepository.getListOfActualReadingBySourceTypeAndBetweenDate(directUse, prevDate, today);
	 * }
	 * @Override
	 * public List<DirectUseOfWater> findByrWDateBetweenTwoDates(String fDate, String lDate) {
	 * return regularWaterUseDataRepository.findByrWDateBetweenTwoDates(fDate,lDate);
	 * }
	 * @Override
	 * public Float getAvgActualReadingByWaterInvIdSourceType(int waterInvId, String sourceType) {
	 * return regularWaterUseDataRepository.getAvgActualReadingByWaterInvIdSourceType(waterInvId, sourceType);
	 * }
	 * @Override
	 * public Float getSumOfActualReadingByYearAndSType(int year, String sourceType) {
	 * return regularWaterUseDataRepository.getSumOfActualReadingByYearAndSType(year, sourceType);
	 * }
	 * @Override
	 * public DirectUseOfWater getdatawaterUse(String indName, String date) {
	 * return regularWaterUseDataRepository.getdatawaterUse(indName, date);
	 * }
	 */

	// affected by ashish from
	// @Override
	// public List<Float> getListOfActualReadingByWhereToUseAndBetweenDate(String directUse, String prevDate,
	// String today) {
	//
	// return regularWaterUseDataRepository.getListOfActualReadingByWhereToUseAndBetweenDate(directUse, prevDate, today);
	// }
	//
	// @Override
	// public Float getActualReadingByDateAndSourceType(String date, String sourceType) {
	// return regularWaterUseDataRepository.getActualReadingByDateAndSourceType(date, sourceType);
	// }
	//
	// @Override
	// public List<RegDirectUseOfWaterData> findByrWDateBetweenTwoDates(String fDate, String lDate) {
	// return regularWaterUseDataRepository.findByrWDateBetweenTwoDates(fDate, lDate);
	// }
	//
	// here
	@Override
	public Float getActualReadingBySourceTypeAndBetweenDate(String sourceType, String fDate, String sDate)
	{
		return regularWaterUseDataRepository.getActualReadingBySourceTypeAndBetweenDate(sourceType, fDate, sDate);
	}

	@Override
	public Float actualReadingByYearSourceTypeMonth(int year, String sourceType, int month)
	{
		return regularWaterUseDataRepository.actualReadingByYearSourceTypeMonth(year, sourceType, month);
	}

	@Override
	public Float getSumOfActualReadingByYearAndSType(int year, String sourceType)
	{
		return regularWaterUseDataRepository.getSumOfActualReadingByYearAndSType(year, sourceType);
	}

	@Override
	public Float getActualReadingByDateAndSourceType(String date, String sourceType)
	{
		return regularWaterUseDataRepository.getActualReadingByDateAndSourceType(date, sourceType);
	}

	@Override
	public List<Float> getListOfActualReadingByWhereToUseAndBetweenDate(String directUse, String prevDate,
			String today)
	{
		return regularWaterUseDataRepository.getListOfActualReadingByWhereToUseAndBetweenDate(directUse, prevDate, today);
	}

	@Override
	public List<RegDirectUseOfWaterData> findByrWDateBetweenTwoDates(String fDate, String lDate)
	{
		return regularWaterUseDataRepository.findByrWDateBetweenTwoDates(fDate, lDate);
	}

	@Override
	public List<RegDirectUseOfWaterData> getRegDirectUseOfWaterData(String whereToUse, Pageable pageable)
	{
		return regularWaterUseDataRepository.getRegDirectUseOfWaterData(whereToUse, pageable);
	}

	@Override
	public RegDirectUseOfWaterData save(RegDirectUseOfWaterData regularWaterUseData)
	{
		return regularWaterUseDataRepository.save(regularWaterUseData);
	}

	@Override
	public List<RegDirectUseOfWaterData> getRegDirectUseOfWaterDataBySelectedDate(String selectedDate)
	{
		return regularWaterUseDataRepository.getRegDirectUseOfWaterDataBySelectedDate(selectedDate);
	}

	@Override
	public Float getActualReadingbydate(String type, String dateFrom, String dateTo)
	{
		return regularWaterUseDataRepository.getActualReadingbydate(type, dateFrom, dateTo);
	}

}
