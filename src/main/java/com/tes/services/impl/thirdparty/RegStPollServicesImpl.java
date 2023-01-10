package com.tes.services.impl.thirdparty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegStPoll;
import com.tes.repository.thirdparty.RegStackPollRepository;
import com.tes.services.thirdparty.RegStPollServices;

@Service
public class RegStPollServicesImpl implements RegStPollServices
{

	@Autowired
	RegStackPollRepository regStackPollRepository;

	@Override
	public RegStPoll save(RegStPoll regStPoll)
	{
		return regStackPollRepository.save(regStPoll);
	}

	@Override
	public List<Integer> getYearFromStack()
	{
		return regStackPollRepository.getYearFromStack();
	}

	@Override
	public List<String> getYearFromStackDate()
	{
		return regStackPollRepository.getYearFromStackDate();
	}

	@Override
	public List<Integer> getMonthFromStack(int Yr)
	{
		return regStackPollRepository.getMonthFromStack(Yr);
	}

	@Override
	public List<String> getDateFromStack(Integer year)
	{
		return regStackPollRepository.getDateFromStack(year);
	}

	@Override
	public List<RegStPoll> getDateFromStackDate(String date)
	{
		return regStackPollRepository.getDateFromStackDate(date);
	}

	@Override
	public List<RegStPoll> findRegStackInfoToShow(int stackId, int mon, int yr)
	{
		return regStackPollRepository.findRegStackInfoToShow(stackId, mon, yr);
	}

	@Override
	public Float getConcPollAvgByPName(String pollName, String stName, String attachedTo, int selectedYear, int selectedYear1, int esrMonth)
	{
		return regStackPollRepository.getConcPollAvgByPName(pollName, stName, attachedTo, selectedYear, selectedYear1, esrMonth);
	}

	@Override
	public Float getExitGasVelocityAvgByPName(String pollName, String stName, String attachedTo, int year1, int year2, int esrMonth)
	{
		return regStackPollRepository.getExitGasVelocityAvgByPName(pollName, stName, attachedTo, year1, year2, esrMonth);
	}

	/*
	 * @Override public List<RegStPoll> getAVgExitGasVelocityByYear(String pollName,
	 * String stackName, String attachedTo, String dateFrom, String dateTo) { return
	 * regStackPollRepository.getAVgExitGasVelocityByYear(pollName, stackName,
	 * attachedTo, dateFrom, dateTo); }
	 */

	@Override
	public List<RegStPoll> getRegStackPollData(String fromDate, String toDate)
	{
		return regStackPollRepository.getRegStackPollData(fromDate, toDate);
	}

	@Override
	public List<String> getHoursOpByPName(String pollName, String stName, String attachedTo, int year1, int year2, int esrMonth)
	{
		return regStackPollRepository.getHoursOpByPName(pollName, stName, attachedTo, year1, year2, esrMonth);
	}

	@Override
	public Float getExitGasVelocityAvgByPNameForYear(String pollName, String stName, String attachedTo, int year1, int year2)
	{
		return regStackPollRepository.getExitGasVelocityAvgByPNameForYear(pollName, stName, attachedTo, year1, year2);
	}

	@Override
	public List<String> getHoursOpByPNameForYear(String pollName, String stName, String attachedTo, int year1, int year2)
	{
		return regStackPollRepository.getHoursOpByPNameForYear(pollName, stName, attachedTo, year1, year2);
	}

	@Override
	public List<RegStPoll> checkRegSTPollData(Pageable pageble)
	{
		// TODO Auto-generated method stub
		return regStackPollRepository.checkRegSTPollData(pageble);
	}

	/*
	 * @Override public Float getAvgconcPollByYear(String pollName, String
	 * stackName, String attachedTo, String fromDate, String toDate) { return
	 * regStackPollRepository.getAvgconcPollByYear(pollName, stackName, attachedTo,
	 * fromDate, toDate);
	 * }
	 */

}
