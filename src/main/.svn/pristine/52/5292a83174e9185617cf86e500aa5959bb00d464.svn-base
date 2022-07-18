package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegWaterSourceData;
import com.tes.repository.environmentalofficer.waterinventory.RegWaterSourceDataRepository;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;

@Service
public class RegWaterSourceDataServicesImpl implements RegWaterSourceDataServices
{

	@Autowired
	RegWaterSourceDataRepository regularSourceDataRepository;

	@Override
	public RegWaterSourceData save(RegWaterSourceData regularSourceData)
	{
		return regularSourceDataRepository.save(regularSourceData);
	}

	@Override
	public List<RegWaterSourceData> regularSourceDataBySourceId(int wsId, Pageable pageable)
	{
		return regularSourceDataRepository.regularSourceDataBySourceId(wsId, pageable);
	}

	// @Override
	// public List<RegWaterSourceData> findRegularSourceData(int wiId, String date,Pageable pageable) {
	// return regularSourceDataRepository.findRegularSourceData(wiId, date,pageable);
	// }
	//
	// @Override
	// public List<RegWaterSourceData> rsDataBySrcNameDate(int wiId, String sourceName, String date,Pageable pageable) {
	// return regularSourceDataRepository.rsDataBySrcNameDate(wiId, sourceName, date,pageable);
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public Float avgStaff(int esrYearFrom, int esrYearTo, int month) {
	// // return regularSourceDataRepository.avgStaff(esrYearFrom, esrYearTo, month);
	// //}
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public List<RegWaterSourceData> getByWaterInventoryAndDateId(int waterInventoryId, String date) {
	// // return regularSourceDataRepository.getByWaterInventoryAndDateId(waterInventoryId, date);
	// //}
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //@Transactional
	// //public int saveRegularSourceData(int staff, int id) {
	// // return regularSourceDataRepository.saveRegularSourceData(staff, id);
	// //}
	//
	//
	// @Override
	// public List<Integer> rsDataYear() {
	// return regularSourceDataRepository.rsDataYear();
	// }
	//
	// @Override
	// public List<RegWaterSourceData> regularSourceDataByMIDCWater(int wiId, Pageable pageable) {
	// return regularSourceDataRepository.regularSourceDataByMIDCWater(wiId, pageable);
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public int updateStartEndActualReading(float startReading, float endReading, float actualReading, int id) {
	// // return regularSourceDataRepository.updateStartEndActualReading(startReading, endReading, actualReading, id);
	// //}
	//
	// @Override
	// public List<RegWaterSourceData> rsDataBySrcAndName(int waterInventoryId, String sourceName,Pageable pageable) {
	// return regularSourceDataRepository.rsDataBySrcAndName(waterInventoryId, sourceName,pageable);
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public List<RegWaterSourceData> regularSourceDataByJackWell(int wiId, Pageable pageable) {
	// // return regularSourceDataRepository.regularSourceDataByJackWell(wiId,pageable);
	// //}
	//
	// //Effected By Water Inventory ........by sushama
	//// @Override
	// //public List<RegWaterSourceData> regularSourceDataByTankerWater(int wiId,Pageable pageable) {
	// // return regularSourceDataRepository.regularSourceDataByTankerWater(wiId, pageable);
	// //}
	//
	// @Override
	// public List<RegWaterSourceData> regularSourceDataByCorporation(int wiId,Pageable pageable) {
	// return regularSourceDataRepository.regularSourceDataByCorporation(wiId,pageable);
	// }
	//
	// @Override
	// public List<RegWaterSourceData> getAllRegularSourceData(String date) {
	// return regularSourceDataRepository.getAllRegularSourceData(date);
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public List<RegWaterSourceData> regularSourceDataBySourceName(int wiId, String sourceName,Pageable pageable) {
	// // return regularSourceDataRepository.regularSourceDataBySourceName(wiId, sourceName,pageable);
	// //}
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public List<RegWaterSourceData> getAllRegularSourceDataBetweenDates(String fDate, String lDate) {
	// // return regularSourceDataRepository.getAllRegularSourceDataBetweenDates(fDate, lDate);
	// //}
	//
	// @Override
	// public Float getSumOfActualReading(int month, int year) {
	// return regularSourceDataRepository.getSumOfActualReading(month, year);
	// }
	//
	// @Override
	// public List<Integer> getRegularSourceDataYearList() {
	// return regularSourceDataRepository.getRegularSourceDataYearList();
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public Float getSumOfActualReadingByYearAndSourceName(int year,String sourceName) {
	// //return regularSourceDataRepository.getSumOfActualReadingByYearAndSourceName(year,sourceName);
	// //}
	//
	// @Override
	// public Float getSumOfActualReadingByYear(int year) {
	// return regularSourceDataRepository.getSumOfActualReadingByYear(year);
	// }
	//
	// @Override
	// public Float getAvgActualReadingBetweenDates(String pDate, String todayDate) {
	// return regularSourceDataRepository.getAvgActualReadingBetweenDates(pDate,todayDate);
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public int countActualReadingBetweenDates(String pDate, String todayDate) {
	// // return regularSourceDataRepository.countActualReadingBetweenDates(pDate,todayDate);
	// //}
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public int countAvailableDataBetweenDatesBySourceName(String pDate, String todayDate, String sourceName) {
	// // return regularSourceDataRepository.countAvailableDataBetweenDatesBySourceName(pDate,todayDate,sourceName);
	// //}
	//
	// @Override
	// public Float getActualReadingByDateAndSourceName(String date, String sourceName) {
	// return regularSourceDataRepository.getActualReadingByDateAndSourceName(date, sourceName);
	// }
	//
	// @Override
	// public Float getSumActualReadingBySourceNameAndBetweenDates(String sourceName, String fDate, String sDate) {
	// return regularSourceDataRepository.getSumActualReadingBySourceNameAndBetweenDates(sourceName, fDate, sDate);
	// }
	//
	// @Override
	// public int minRegSourceYear() {
	// return regularSourceDataRepository.minRegSourceYear();
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public Float getActualReadingSumForDate(String date) {
	// // return regularSourceDataRepository.getActualReadingSumForDate(date);
	// //}
	//
	// @Override
	// public Float getActualReadingByRsDateAndSourceName(String sdate, String sourceName) {
	// return regularSourceDataRepository.getActualReadingByRsDateAndSourceName(sdate,sourceName);
	// }
	//
	// @Override
	// public int getCountByRsDateAndSourceName(String pDate, String today, String sourceName) {
	// return regularSourceDataRepository.getCountByRsDateAndSourceName(pDate, today, sourceName);
	// }
	//
	// //Effected By Water Inventory ........by sushama
	// //@Override
	// //public Float getSumActualByYearMonth(int year, int month,String sourceName) {
	// // return regularSourceDataRepository.getSumActualByYearMonth(year, month, sourceName);
	// //}
	//
	// @Override
	// public List<Float> getActualReadingListBySourceNameAndDatesBetween(String sourceName, String fDate, String sDate) {
	// return regularSourceDataRepository.getActualReadingListBySourceNameAndDatesBetween(sourceName, fDate, sDate);
	// }
	//
	// @Override
	// public List<RegWaterSourceData> findAll() {
	// return regularSourceDataRepository.findAll();
	// }
	//
	// @Override
	// public Float getActualReadingByInvIdSourceName(int invId, String sourceName) {
	// return regularSourceDataRepository.getActualReadingByInvIdSourceName(invId, sourceName);
	// }
	//
	// @Override
	// public Float getSumActualReadingByWaterInvId(int waterInvId) {
	// return regularSourceDataRepository.getSumActualReadingByWaterInvId(waterInvId);
	// }
	// @Override
	// public int countNonCompliedBySourceType(String previousDate, String todayDate, String SourceName, float ComplianceValue) {
	// return regularSourceDataRepository.countNonCompliedBySourceType(previousDate,todayDate, SourceName,ComplianceValue);
	// }
	//
	// @Override
	// public List<RegWaterSourceData> getSourceDetailsData(String date) {
	// return regularSourceDataRepository.getSourceDetailsData(date);
	// }

	@Override
	public int getRegSourceYearOfLastInput()
	{
		return regularSourceDataRepository.getRegSourceYearOfLastInput();
	}

	@Override
	public Float getActualReadingByRsDateAndSourceName(String sdate, String sourceName)
	{
		return regularSourceDataRepository.getActualReadingByRsDateAndSourceName(sdate, sourceName);
	}

	@Override
	public List<Float> findActualReadingListBySourceNameAndDatesBetween(String sourceName, String fDate, String sDate)
	{

		return regularSourceDataRepository.findActualReadingListBySourceNameAndDatesBetween(sourceName, fDate, sDate);
	}

	@Override
	public int getCountByRsDateAndSourceName(String pDate, String today, String sourceName)
	{

		return regularSourceDataRepository.getCountByRsDateAndSourceName(pDate, today, sourceName);
	}

	@Override
	public Float getActualReadingSumForDate(String date)
	{
		return regularSourceDataRepository.getActualReadingSumForDate(date);
	}

	@Override
	public Float getSumActualReadingBySourceNameAndBetweenDates(String sourceName, String fWeek, String sWeek)
	{
		return regularSourceDataRepository.getSumActualReadingBySourceNameAndBetweenDates(sourceName, fWeek, sWeek);
	}

	@Override
	public Float getSumActualByYearMonth(int year, int month, String sourceName)
	{
		return regularSourceDataRepository.getSumActualByYearMonth(year, month, sourceName);
	}

	@Override
	public Float getSumOfActualReadingByYearAndSourceName(int year, String sourceName)
	{
		return regularSourceDataRepository.getSumOfActualReadingByYearAndSourceName(year, sourceName);
	}

	@Override
	public List<RegWaterSourceData> getAllRegularSourceDataBetweenDates(String fDate, String lDate)
	{
		return regularSourceDataRepository.getAllRegularSourceDataBetweenDates(fDate, lDate);
	}

	@Override
	public List<Integer> getWaterMinYear()
	{
		return regularSourceDataRepository.getWaterMinYear();
	}

	@Override
	public List<RegWaterSourceData> getRegWaterSourceDataBySelectedDate(String selectedDate)
	{
		return regularSourceDataRepository.getRegWaterSourceDataBySelectedDate(selectedDate);
	}

	@Override
	public Float getSumOfActualReadingByMonthAndYear(int month, int year)
	{
		return regularSourceDataRepository.getSumOfActualReadingByMonthAndYear(month, year);
	}

	@Override
	public List<Integer> getRegularWaterSourceDataYearList()
	{
		return regularSourceDataRepository.getRegularSourceDataYearList();
	}

	@Override
	public Float getSumOfActualReadingByYear(int year)
	{
		return regularSourceDataRepository.getSumOfActualReadingByYear(year);
	}

	@Override
	public Float getSumActualReadingByWaterInvId(int waterInvId)
	{
		// TODO Auto-generated method stub
		return regularSourceDataRepository.getSumActualReadingByWaterInvId(waterInvId);
	}
}
