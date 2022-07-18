package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegWastewaterRecycle;
import com.tes.repository.environmentalofficer.waterinventory.RegWastewaterRecycleRepository;
import com.tes.services.environmentalofficer.waterinventory.RegWastewaterRecycleServices;

@Service
public class RegWastewaterRecycleServiesImpl implements RegWastewaterRecycleServices
{

	@Autowired
	RegWastewaterRecycleRepository regWasterwaterRecycleRepository;

	@Override
	public List<RegWastewaterRecycle> getRegWastewaterRecycleByRecycleId(int wwrId, Pageable pageable)
	{
		return regWasterwaterRecycleRepository.getRegWastewaterRecycleByRecycleId(wwrId, pageable);
	}

	@Override
	public RegWastewaterRecycle save(RegWastewaterRecycle regWastewaterRecycle)
	{
		return regWasterwaterRecycleRepository.save(regWastewaterRecycle);
	}

	@Override
	public Float findActualReadingByRfDatetreatTypeAndrecycledTo(String date, String treatType, String recycledTo)
	{
		return regWasterwaterRecycleRepository.findActualReadingByRfDatetreatTypeAndrecycledTo(date, treatType, recycledTo);
	}

	@Override
	public List<Float> getActualReadingListBytreaTypeAndDatesBetween(String treatType, String recycledTo, String pDate, String today)
	{
		return regWasterwaterRecycleRepository.getActualReadingListBytreaTypeAndDatesBetween(treatType, recycledTo, pDate, today);
	}

	@Override
	public int countRegRecycleDataDateBtwDatesBytreatLabel(String pDate, String todayDate, String treatType, String recycledTo)
	{
		return regWasterwaterRecycleRepository.countRegRecycleDataDateBtwDatesBytreatLabel(pDate, todayDate, treatType, recycledTo);
	}

	@Override
	public List<RegWastewaterRecycle> findAllRegWasteWaterRecycleBetweenTwoDates(String fDate, String lDate)
	{
		return regWasterwaterRecycleRepository.findAllRegWasteWaterRecycleBetweenTwoDates(fDate, lDate);
	}

	@Override
	public Float getActualreadingById(int id, String date)
	{
		return regWasterwaterRecycleRepository.getActualreadingById(id, date);
	}

	@Override
	public Float getActualReadingByIdandWeeks(int id, String fWeek, String sWeek)
	{
		return regWasterwaterRecycleRepository.getActualReadingByIdandWeeks(id, fWeek, sWeek);
	}

	@Override
	public Float getActualReadingByIdandMonth(int id, int year, int month)
	{
		return regWasterwaterRecycleRepository.getActualReadingByIdandMonth(id, year, month);
	}

	@Override
	public Float getActualReadingByIdAndYear(int id, int year)
	{
		return regWasterwaterRecycleRepository.getActualReadingByIdandYear(id, year);
	}

	@Override
	public List<RegWastewaterRecycle> getRegWastewaterRecycleDataBySelectedDate(String selectedDate, int wastewaterTreatmentId)
	{
		return regWasterwaterRecycleRepository.getRegWastewaterRecycleDataBySelectedDate(selectedDate, wastewaterTreatmentId);
	}

	@Override
	public Float findActualReadingByRfDateAndrecycledTo(String date, String recycledTo)
	{
		return regWasterwaterRecycleRepository.findActualReadingByRfDateAndrecycledTo(date, recycledTo);
	}
}
