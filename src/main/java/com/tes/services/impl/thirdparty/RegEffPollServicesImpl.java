package com.tes.services.impl.thirdparty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegEffPoll;
import com.tes.repository.thirdparty.RegEffPollRepository;
import com.tes.services.thirdparty.RegEffPollServices;

@Service
public class RegEffPollServicesImpl implements RegEffPollServices
{

	@Autowired
	RegEffPollRepository regEffPollRepository;

	@Override
	public List<Integer> getYearFromEffluent()
	{
		return regEffPollRepository.getYearFromEffluent();
	}

	@Override
	public List<String> getYearFromEffluentDate()
	{
		return regEffPollRepository.getYearFromEffluentDate();
	}

	@Override
	public List<Integer> getMonthFromEffluent(int Yr)
	{
		return regEffPollRepository.getMonthFromEffluent(Yr);
	}

	@Override
	public List<String> getDateForEffluent(int Yr)
	{
		return regEffPollRepository.getDateForEffluent(Yr);
	}

	// @Override
	// public List<RegEffPoll> getRegEffPollData(int year, String pollname)
	// {
	// return regEffPollRepository.getRegEffPollData(year, pollname);
	// }

	@Override
	public Float getAvgRegEffOuConsE(String pollName, String dateTo, String dateFrom)
	{
		return regEffPollRepository.getAvgRegEffOuConsE(pollName, dateTo, dateFrom);
	}
	/*
	 * @Override public List<RegEffPoll> getRegEffPollDatas(String pollname,Pageable
	 * pageable) { return
	 * regEffPollRepository.getRegEffPollDatas(pollname,pageable); }
	 */

	/*
	 * @Override public List<RegEffPoll>
	 * getRegEffPollDataBetweenDateByPollName(String pollName, String todayDate,int
	 * yearValue,int monthValue,Pageable pageable) { return
	 * regEffPollRepository.getRegEffPollDataBetweenDateByPollName(pollName,
	 * todayDate,yearValue,monthValue, pageable); }
	 */

	/*
	 * @Override public List<RegEffPoll>
	 * getRegEffPollDataBetweenDateByPollName(String pollName,int yearValue,Pageable
	 * pageable) { return
	 * regEffPollRepository.getRegEffPollDataBetweenDateByPollName(pollName,
	 * yearValue, pageable); }
	 */

	/*
	 * @Override public List<RegEffPoll>
	 * getRegEffPollDataBetweenDateByPollName(String pollName,int yearValue,int
	 * monthValue,Pageable pageable) { return
	 * regEffPollRepository.getRegEffPollDataBetweenDateByPollName(pollName,
	 * yearValue,monthValue, pageable); }
	 */

	@Override
	public List<String> getRegEffPollDates(String today, int yearValue, Pageable pageable)
	{
		return regEffPollRepository.getRegEffPollDates(today, yearValue, pageable);
	}

	@Override
	public List<String> getRegEffPollDates(String today, int yearValue, int monthValue, Pageable pageable)
	{
		return regEffPollRepository.getRegEffPollDates(today, yearValue, monthValue, pageable);
	}

	@Override
	public List<String> getRegEffPollDates(String today, Pageable pageable)
	{
		return regEffPollRepository.getRegEffPollDates(today, pageable);
	}

	/*
	 * @Override public Float getRegEffPollouConsEDatePollName(String pollname,
	 * String todayDate) { return
	 * regEffPollRepository.getRegEffPollouConsEDatePollName(pollname,todayDate); }
	 */

	/*
	 * @Override public Float getAvgOuConsEByYear(String pollName, String dateFrom,
	 * String dateTo) { return regEffPollRepository.getAvgOuConsEByYear(pollName,
	 * dateFrom, dateTo); }
	 */

	@Override
	public RegEffPoll save(RegEffPoll regEffPoll)
	{
		return regEffPollRepository.save(regEffPoll);
	}

	@Override
	public List<RegEffPoll> getRegEffPollDataDate(String date)
	{

		return regEffPollRepository.getRegEffPollDataDate(date);
	}

	public List<RegEffPoll> getRegEffPollDataDateAndId(int id, String date)
	{
		return regEffPollRepository.getRegEffPollDataDateAndId(id, date);
	}

	@Override
	public List<String> getRegEffPollDate(Pageable pageable)
	{
		return regEffPollRepository.getRegEffPollDate(pageable);
	}

	@Override
	public List<RegEffPoll> getRegEffPollDatasbyDate(String labelName, String date)
	{
		return regEffPollRepository.getRegEffPollDatasbyDate(labelName, date);
	}

	@Override
	public List<RegEffPoll> checkRegEffPollData(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return regEffPollRepository.checkRegEffPollData(pageable);
	}

	@Override
	public Float getEffPollAvg(String pollName, int year)
	{
		// TODO Auto-generated method stub
		return regEffPollRepository.getEffPollAvg(pollName, year);
	}

}
