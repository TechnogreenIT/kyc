package com.tes.services.thirdparty;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegSewPoll;

public interface RegSewPollServices
{

	RegSewPoll save(RegSewPoll regSewPoll);

	public List<Integer> getYearFromSewage();

	public List<String> getYearFromSewageDate();

	public List<Integer> getMonthFromSewage(int Yr);

	public List<String> getDateFromSewage(int Yr);

	// public List<RegSewPoll> getRegSewagePollData(int yr,String pollname);

	// public List<RegSewPoll> getRegSewPollDatas(String pollname, String sampDate);

	// public List<RegSewPoll> getRegSewPollDataBetweenDateByPollNameDay(String pollname,String todayDate,int yearValue,int monthValue, Pageable pageable);

	// public List<RegSewPoll> getRegSewPollDataBetweenDateByPollNameYear(String pollname,int yearValue, Pageable pageable);

	// public List<RegSewPoll> getRegSewPollDataBetweenDateByPollNameMonth(String pollname,int yearValue, int monthValue, Pageable pageable);

	public List<String> getRegSewPollDates(String today, int yearValue, Pageable pageable);

	public List<String> getRegSewPollDates(String today, int yearValue, int monthValue, Pageable pageable);

	public List<String> getRegSewPollDates(String today, Pageable pageable);

	// Float getRegSewPollouConsEDatePollName(String pollname,String todayDate);

	// Float getSewAvgOuConsEByYear(String pollName,String dateFrom,String dateTo);

	public List<RegSewPoll> getRegSewagePollDataDate(String date);

	List<String> getRegSewPollDate(Pageable pageable);

	List<RegSewPoll> getRegSewPollDataDateAndId(int id, String date);

	List<RegSewPoll> getRegSewPollDatasbyDate(String labelName, String date);

	Float getSewAvgOuConsByDate(String pollName, String dateFrom, String dateTo);

	List<RegSewPoll> checkRegSewPollData(Pageable pageable);

	Float getSewPollAvg(String pollName, int year);

}
