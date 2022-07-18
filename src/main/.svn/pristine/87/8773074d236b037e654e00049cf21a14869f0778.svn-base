package com.tes.services.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.tes.model.RegWastewaterRecycle;

public interface RegWastewaterRecycleServices
{

	RegWastewaterRecycle save(RegWastewaterRecycle regWastewaterRecycle);

	List<RegWastewaterRecycle> getRegWastewaterRecycleByRecycleId(int wwrId, Pageable pageable);

	Float findActualReadingByRfDatetreatTypeAndrecycledTo(String date, String treatType, String recycledTo);

	List<Float> getActualReadingListBytreaTypeAndDatesBetween(String treatType, String recycledTo, String pDate, String today);

	int countRegRecycleDataDateBtwDatesBytreatLabel(String pDate, String todayDate, String treatType, String recycledTo);

	List<RegWastewaterRecycle> findAllRegWasteWaterRecycleBetweenTwoDates(String fDate, String lDate);

	Float getActualreadingById(int id, String date);

	Float getActualReadingByIdandWeeks(int wastewaterRecycleId, String fWeek, String sWeek);

	Float getActualReadingByIdandMonth(int wastewaterRecycleId, int year, int k);

	Float getActualReadingByIdAndYear(int wastewaterRecycleId, int year);

	List<RegWastewaterRecycle> getRegWastewaterRecycleDataBySelectedDate(String selectedDate, int wastewaterTreatmentId);

	Float findActualReadingByRfDateAndrecycledTo(String date, String recycledTo);

}
