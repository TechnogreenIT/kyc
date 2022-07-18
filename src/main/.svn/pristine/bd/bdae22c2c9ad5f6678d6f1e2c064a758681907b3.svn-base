package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegularTreatmentData;
import com.tes.repository.environmentalofficer.waterinventory.RegularTreatmentDataRepository;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;

@Service
public class RegularTreatmentDataServicesImpl implements RegularTreatmentDataServices
{

	@Autowired
	RegularTreatmentDataRepository regularTreatmentDataRepository;

	@Override
	public RegularTreatmentData save(RegularTreatmentData regularTreatMentData)
	{
		return regularTreatmentDataRepository.save(regularTreatMentData);
	}

	@Override
	public List<RegularTreatmentData> getRegularTreatementDataByDate(int wastewaterTreatmentId, Pageable pageable)
	{
		return regularTreatmentDataRepository.getRegularTreatementDataByDate(wastewaterTreatmentId, pageable);
	}

	@Override
	public Float getActualReadingByWWTIdAndDate(int wastewaterTreatmentId, String date)
	{
		return regularTreatmentDataRepository.getTreatTypeStartEndReadingByDate(wastewaterTreatmentId, date);
	}

	@Override
	public RegularTreatmentData getActualReadingByWWTIdAndBetweenDate(int id, String fWeek, String sWeek)
	{
		return regularTreatmentDataRepository.getActualReadingByWWTIdAndBetweenDate(id, fWeek, sWeek);
	}

	@Override
	public RegularTreatmentData getActualReadingByWWTIdAndYearMonth(int id, int year, int month)
	{
		return regularTreatmentDataRepository.getActualReadingByWWTIdAndYearMonth(id, year, month);
	}

	@Override
	public RegularTreatmentData getActualReadingByWWTIdAndYear(int id, int year)
	{
		return regularTreatmentDataRepository.getActualReadingByWWTIdAndYear(id, year);
	}

	@Override
	public RegularTreatmentData getActualEnergyReadingByIdAndDate(int id, String sDate)
	{
		return regularTreatmentDataRepository.getActualEnergyReadingByIdAndDate(id, sDate);
	}

	@Override
	public Float getActualReadingByDateAndTreatmentTypeLabel(String date, String label)
	{
		return regularTreatmentDataRepository.getActualReadingByDateAndTreatmentTypeLabel(date, label);
	}

	@Override
	public Float getEnergyReadingByDateAndTreatmentTypeLabel(String date, String label)
	{
		return regularTreatmentDataRepository.getEnergyReadingByDateAndTreatmentTypeLabel(date, label);
	}

	@Override
	public List<RegularTreatmentData> findAllRegTreatmentDataBetweenTwoDates(String fDate, String lDate)
	{
		return regularTreatmentDataRepository.findAllRegTreatmentDataBetweenTwoDates(fDate, lDate);
	}

	@Override
	public List<Float> findActualReadingListByTreatLabelAndDatesBetween(String label, String fDate, String sDate)
	{
		return regularTreatmentDataRepository.findActualReadingListByTreatLabelAndDatesBetween(label, fDate, sDate);
	}

	@Override
	public List<Float> findEnergyReadingListByTreatLabelAndDatesBetween(String label, String fDate, String sDate)
	{
		return regularTreatmentDataRepository.findEnergyReadingListByTreatLabelAndDatesBetween(label, fDate, sDate);
	}

	@Override
	public int countRegTreatmentDataDateBtwDatesBytreatLabel(String pDate, String todayDate, String label)
	{
		return regularTreatmentDataRepository.countRegTreatmentDataDateBtwDatesBytreatLabel(pDate, todayDate, label);
	}

	@Override
	public List<RegularTreatmentData> getRegTreatmentDataBySelectedDate(String selectedDate)
	{
		return regularTreatmentDataRepository.getRegTreatmentDataBySelectedDate(selectedDate);
	}

	@Override
	public List<RegularTreatmentData> getRegTreatmentDataByTreatmentTypeAndDate(String date, String treatType,
			String treatTypeLabel)
	{
		return regularTreatmentDataRepository.getRegTreatmentDataByTreatmentTypeAndDate(date, treatType, treatTypeLabel);
	}

	@Override
	public Float getActualReadingByTypeAndDate(String type, String dateFrom, String dateTo)
	{
		return regularTreatmentDataRepository.getActualReadingByTypeAndDate(type, dateFrom, dateTo);
	}

	/*
	 * @Override public List<RegularTreatmentData>
	 * getRegularTreatmentDataByTreatmentType(String treatmentType, int wiId,
	 * Pageable pageable) { return
	 * regularTreatmentDataRepository.getRegularTreatmentDataByTreatmentType(
	 * treatmentType, wiId, pageable); }
	 * @Override public List<RegularTreatmentData> rtDataByTreatmentTypeDate(String
	 * treatmentType, int wiId, String date,Pageable pageable) { return
	 * regularTreatmentDataRepository.rtDataByTreatmentTypeDate(treatmentType, wiId,
	 * date,pageable); }
	 * @Override public List<RegularTreatmentData> getRegularTreatmentData(String
	 * type, int waterInvId,Pageable pageable) { return
	 * regularTreatmentDataRepository.getRegularTreatmentData(type,
	 * waterInvId,pageable); }
	 * @Override public List<RegularTreatmentData> rtDataByDate(String type, int
	 * waterInvId, String today,Pageable pageable) { return
	 * regularTreatmentDataRepository.rtDataByDate(type, waterInvId,
	 * today,pageable); }
	 * @Override public RegularTreatmentData
	 * getTreatTypeStartEndReadingByDate(String date,String treatmentType) { return
	 * regularTreatmentDataRepository.getTreatTypeStartEndReadingByDate(date,
	 * treatmentType); }
	 * @Override public List<String> getTreatmentTypeByTypeAndDate(String type,
	 * String date) { return
	 * regularTreatmentDataRepository.getTreatmentTypeByTypeAndDate(type, date); }
	 * @Override public Float getActualReadingByTreatmentTypeBetweenDates(String
	 * treatmentType, String fDate, String sDate) { return
	 * regularTreatmentDataRepository.getActualReadingByTreatmentTypeBetweenDates(
	 * treatmentType, fDate, sDate); }
	 * @Override public List<String> getTreatmentTypeByTypeAndBetweenDates(String
	 * type, String fDate, String sDate) { return
	 * regularTreatmentDataRepository.getTreatmentTypeByTypeAndBetweenDates(type,
	 * fDate, sDate); }
	 * @Override public List<String> getTreatmentTypeByTypeAndMonth(String type, int
	 * month) { return
	 * regularTreatmentDataRepository.getTreatmentTypeByTypeAndMonth(type, month); }
	 * @Override public Float sumActualReadingByYearTypeMonth(int year, String type,
	 * int month) { return
	 * regularTreatmentDataRepository.sumActualReadingByYearTypeMonth(year, type,
	 * month); }
	 * @Override public List<String> getTreatmentTypeByTypeAndYear(String type, int
	 * Year) { return
	 * regularTreatmentDataRepository.getTreatmentTypeByTypeAndYear(type, Year); }
	 * @Override public Float getActualReadingByTypeAndYear(String type, int year) {
	 * return regularTreatmentDataRepository.getActualReadingByTypeAndYear(type,
	 * year); }
	 * @Override public Float getAvgActualReadingByYearandType(String type, String
	 * dateFrom, String dateTo) { return
	 * regularTreatmentDataRepository.getAvgActualReadingByYearandType(type,
	 * dateFrom, dateTo); }
	 * @Override public List<RegularTreatmentData> RegularTretmentDataBydate(String
	 * date) { return
	 * regularTreatmentDataRepository.RegularTretmentDataBydate(date); }s
	 */
}
