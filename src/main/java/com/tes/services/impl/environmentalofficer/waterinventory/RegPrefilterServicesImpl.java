package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegPrefilter;
import com.tes.repository.environmentalofficer.waterinventory.RegPrefilterRepository;
import com.tes.services.environmentalofficer.waterinventory.RegPrefilterServices;

@Service
public class RegPrefilterServicesImpl implements RegPrefilterServices
{

	@Autowired
	RegPrefilterRepository regPrefilterRepository;

	@Override
	public Float getActualReadingByRsDateAndPrefilter(String date, String sourceName)
	{
		return regPrefilterRepository.getActualReadingByRsDateAndPrefilter(date, sourceName);
	}

	@Override
	public List<Float> findActualReadingListPrefilterBySourceNameAndDatesBetween(String sourceName, String fDate, String sDate)
	{
		return regPrefilterRepository.findActualReadingListPrefilterBySourceNameAndDatesBetween(sourceName, fDate, sDate);
	}

	@Override
	public int countRegPreFilterDateBtwDatesBySourceName(String pDate, String todayDate, String sourceName)
	{
		return regPrefilterRepository.countRegPreFilterDateBtwDatesBySourceName(pDate, todayDate, sourceName);
	}

	@Override
	public List<RegPrefilter> findByPrefilterBetweenTwoDates(String fDate, String lDate)
	{
		return regPrefilterRepository.findByPrefilterBetweenTwoDates(fDate, lDate);
	}

	@Override
	public RegPrefilter save(RegPrefilter regPrefilter)
	{
		return regPrefilterRepository.save(regPrefilter);
	}

	@Override
	public List<RegPrefilter> getregPrefilterDataByPrefilterId(int pfId, Pageable pageable)
	{
		return regPrefilterRepository.getregPrefilterDataByPrefilterId(pfId, pageable);
	}

	@Override
	public Float getRegPrefilterByIdAndDate(int id, String sDate)
	{
		return regPrefilterRepository.getRegPrefilterByIdAndDate(id, sDate);
	}

	@Override
	public Float getRegPrefilterByIdAndBetweenDates(int id, String fWeek, String sWeek)
	{
		return regPrefilterRepository.getRegPrefilterByIdAndBetweenDates(id, fWeek, sWeek);
	}

	@Override
	public Float getRegPrefilterByIdAndMonth(int id, int month)
	{
		return regPrefilterRepository.getRegPrefilterByIdAndMonth(id, month);
	}

	@Override
	public Float getRegPrefilterByIdAndYear(int id, int year)
	{
		return regPrefilterRepository.getRegPrefilterByIdAndYear(id, year);
	}

	@Override
	public List<RegPrefilter> getRegPrefilterBySelectedDate(String selectedDate)
	{
		return regPrefilterRepository.getRegPrefilterBySelectedDate(selectedDate);
	}
}
