package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegularTreatmentData;

public interface RegularTreatmentDataServices
{

	RegularTreatmentData save(RegularTreatmentData regularTreatMentData);

	Float getActualReadingByWWTIdAndDate(int wastewaterTreatmentId, String sDate);

	RegularTreatmentData getActualReadingByWWTIdAndBetweenDate(int id, String fWeek, String sWeek);

	RegularTreatmentData getActualReadingByWWTIdAndYearMonth(int id, int year, int month);

	RegularTreatmentData getActualReadingByWWTIdAndYear(int id, int year);

	RegularTreatmentData getActualEnergyReadingByIdAndDate(int id, String sDate);

	List<RegularTreatmentData> getRegularTreatementDataByDate(int wastewaterTreatmentId, Pageable pageable);

	Float getActualReadingByDateAndTreatmentTypeLabel(String date, String label);

	Float getEnergyReadingByDateAndTreatmentTypeLabel(String date, String label);

	List<RegularTreatmentData> findAllRegTreatmentDataBetweenTwoDates(String fDate, String lDate);

	List<Float> findActualReadingListByTreatLabelAndDatesBetween(String label, String fDate, String sDate);

	List<Float> findEnergyReadingListByTreatLabelAndDatesBetween(String label, String fDate, String sDate);

	int countRegTreatmentDataDateBtwDatesBytreatLabel(String pDate, String todayDate, String label);

	List<RegularTreatmentData> getRegTreatmentDataBySelectedDate(String selectedDate);

	List<RegularTreatmentData> getRegTreatmentDataByTreatmentTypeAndDate(String date, String treatType, String treatTypeLabel);

	Float getActualReadingByTypeAndDate(String type, String dateFrom, String dateTo);

}
