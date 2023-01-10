package com.tes.services.thirdparty;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegStPoll;

public interface RegStPollServices
{

	RegStPoll save(RegStPoll regStPoll);

	public List<Integer> getYearFromStack();

	List<String> getDateFromStack(Integer integer);

	List<RegStPoll> getDateFromStackDate(String date);

	public List<String> getYearFromStackDate();

	List<Integer> getMonthFromStack(int Yr);

	List<RegStPoll> findRegStackInfoToShow(int stackId, int mon, int yr);

	Float getConcPollAvgByPName(String pollName, String stName, String attachedTo, int selectedYear, int selectedYear1, int esrMonth);

	Float getExitGasVelocityAvgByPName(String pollName, String stName, String attachedTo, int year1, int year2, int esrMonth);

	List<String> getHoursOpByPName(String pollName, String stName, String attachedTo, int year1, int year2, int esrMonth);

	// Float getAvgconcPollByYear(String pollName, String stackName, String
	// attachedTo, String fromDate, String toDate);

	/*
	 * List<RegStPoll> getAVgExitGasVelocityByYear(String pollName,String
	 * stackName,String attachedTo, String dateFrom,String dateTo);
	 */
	public List<RegStPoll> getRegStackPollData(String fromDate, String toDate);

	Float getExitGasVelocityAvgByPNameForYear(String pollName, String stName, String attachedTo, int year1, int year2);

	List<String> getHoursOpByPNameForYear(String pollName, String stName, String attachedTo, int year1, int year2);

	List<RegStPoll> checkRegSTPollData(Pageable pageble);

}
