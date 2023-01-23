package com.tes.services.impl.thirdparty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegSewPoll;
import com.tes.repository.thirdparty.RegSewPollRepository;
import com.tes.services.thirdparty.RegSewPollServices;

@Service
public class RegSewPollServicesImpl implements RegSewPollServices
{

	@Autowired
	RegSewPollRepository regSewPollRepository;

	@Override
	public List<Integer> getYearFromSewage()
	{
		return regSewPollRepository.getYearFromSewage();
	}

	@Override
	public List<String> getYearFromSewageDate()
	{
		return regSewPollRepository.getYearFromSewageDate();
	}

	@Override
	public List<Integer> getMonthFromSewage(int Yr)
	{
		return regSewPollRepository.getMonthFromSewage(Yr);
	}

	@Override
	public List<String> getDateFromSewage(int Yr)
	{
		return regSewPollRepository.getDateFromSewage(Yr);
	}

	/*
	 * @Override public List<RegSewPoll> getRegSewagePollData(int yr, String
	 * pollname) { return regSewPollRepository.getRegSewagePollData(yr, pollname); }
	 */

	// @Override
	// public List<RegSewPoll> getRegSewPollDatas(String pollname, String sampDate)
	// {
	// return regSewPollRepository.getRegSewPollDatas(pollname, sampDate);
	// }

	/*
	 * @Override public List<RegSewPoll>
	 * getRegSewPollDataBetweenDateByPollNameDay(String pollName, String
	 * todayDate,int yearValue,int monthValue,Pageable pageable) { return
	 * regSewPollRepository.getRegSewPollDataBetweenDateByPollNameDay(pollName,
	 * todayDate,yearValue,monthValue, pageable); }
	 */

	/*
	 * @Override public List<RegSewPoll>
	 * getRegSewPollDataBetweenDateByPollNameMonth(String pollName,int yearValue,int
	 * monthValue,Pageable pageable) { return
	 * regSewPollRepository.getRegSewPollDataBetweenDateByPollNameMonth(pollName,
	 * yearValue,monthValue, pageable); }
	 */

	/*
	 * @Override public List<RegSewPoll>
	 * getRegSewPollDataBetweenDateByPollNameYear(String pollName,int
	 * yearValue,Pageable pageable) { return
	 * regSewPollRepository.getRegSewPollDataBetweenDateByPollNameYear(pollName,
	 * yearValue,pageable); }
	 */

	@Override
	public List<String> getRegSewPollDates(String today, int yearValue, int monthValue, Pageable pageable)
	{
		return regSewPollRepository.getRegSewPollDates(today, yearValue, monthValue, pageable);
	}

	@Override
	public List<String> getRegSewPollDates(String today, int yearValue, Pageable pageable)
	{
		return regSewPollRepository.getRegSewPollDates(today, yearValue, pageable);
	}

	@Override
	public List<String> getRegSewPollDates(String today, Pageable pageable)
	{
		return regSewPollRepository.getRegSewPollDates(today, pageable);
	}

	/*
	 * @Override public Float getRegSewPollouConsEDatePollName(String pollname,
	 * String todayDate) { return
	 * regSewPollRepository.getRegSewPollouConsEDatePollName(pollname,todayDate); }
	 */

	/*
	 * @Override public Float getSewAvgOuConsEByYear(String pollName, String
	 * dateFrom, String dateTo) { return
	 * regSewPollRepository.getSewAvgOuConsEByYear(pollName, dateFrom, dateTo); }
	 */

	@Override
	public RegSewPoll save(RegSewPoll regSewPoll)
	{
		return regSewPollRepository.save(regSewPoll);
	}

	@Override
	public List<RegSewPoll> getRegSewagePollDataDate(String date)
	{

		return regSewPollRepository.getRegSewagePollDataDate(date);
	}

	@Override
	public List<String> getRegSewPollDate(Pageable pageable)
	{
		return regSewPollRepository.getRegSewPollDate(pageable);
	}

	@Override
	public List<RegSewPoll> getRegSewPollDataDateAndId(int id, String date)
	{
		return regSewPollRepository.getRegSewPollDataDateAndId(id, date);
	}

	@Override
	public List<RegSewPoll> getRegSewPollDatasbyDate(String labelName, String date)
	{
		return regSewPollRepository.getRegSewPollDatasbyDate(labelName, date);
	}

	@Override
	public Float getSewAvgOuConsByDate(String pollName, String dateFrom, String dateTo)
	{
		return regSewPollRepository.getSewAvgOuConsByDate(pollName, dateFrom, dateTo);
	}

	@Override
	public List<RegSewPoll> checkRegSewPollData(Pageable pageable)
	{
		return regSewPollRepository.checkRegSewPollData(pageable);
	}

	@Override
	public Float getSewPollAvg(String pollName, int year)
	{
		// TODO Auto-generated method stub
		return regSewPollRepository.getSewAvgOuConsByDate(pollName, year);
	}
}
