package com.tes.services.thirdparty;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegEffPoll;

public interface RegEffPollServices
{

	RegEffPoll save(RegEffPoll regEffPoll);

	public List<Integer> getYearFromEffluent();

	public List<String> getYearFromEffluentDate();

	public List<Integer> getMonthFromEffluent(int Yr);

	public List<String> getDateForEffluent(int Yr);

	// public List<RegEffPoll> getRegEffPollData(int Yr,String pollname);

	Float getAvgRegEffOuConsE(String pollName, String dateTo, String dateFrom);

	// public List<RegEffPoll> getRegEffPollDatas(String pollname, Pageable pageable);

	// public List<RegEffPoll> getRegEffPollDataBetweenDateByPollName(String pollname,String todayDate, int yearValue,int monthValue, Pageable pageable);

	// public List<RegEffPoll> getRegEffPollDataBetweenDateByPollName(String pollname, int yearValue, Pageable pageable);

	// public List<RegEffPoll> getRegEffPollDataBetweenDateByPollName(String pollname, int yearValue,int monthValue, Pageable pageable);

	public List<String> getRegEffPollDates(String today, int yearValue, Pageable pageable);

	public List<String> getRegEffPollDates(String today, int yearValue, int monthvalue, Pageable pageable);

	public List<String> getRegEffPollDates(String today, Pageable pageable);

	// Float getAvgRegEffOuConsE(String pollName,String esrYear,String earMonth);
	// Float getRegEffPollouConsEDatePollName(String pollname,String todayDate); effocted by thirdparty

	// Float getAvgOuConsEByYear(String pollName, String dateFrom, String dateTo);

	public List<RegEffPoll> getRegEffPollDataDate(String date);

	public List<RegEffPoll> getRegEffPollDataDateAndId(int id, String date);

	public List<String> getRegEffPollDate(Pageable pageable);

	public List<RegEffPoll> getRegEffPollDatasbyDate(String labelName, String date);

	List<RegEffPoll> checkRegEffPollData(Pageable pageable);

}
