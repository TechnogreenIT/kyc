package com.tes.services.thirdparty;

import java.util.List;
import com.tes.model.StackPollData;

public interface StackPollDataServices
{

	StackPollData save(StackPollData stackPollData);

	List<StackPollData> getStackPollData(String stackname, String attachedTo, int month, int year, int regStackId);

	List<StackPollData> getStackPollDataDay(int regStackId);

	Float getAvgconcPollByYear(String pollName, String stackName, String attachedTo, String fromDate, String toDate);

	public List<StackPollData> getStackPollDataByYear(String fromDate, String toDate);

	Float getAvgconcPollByName(String pollName, Integer stackId, String dateFrom, String dateTo);

}
