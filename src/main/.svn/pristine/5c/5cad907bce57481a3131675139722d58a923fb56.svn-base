package com.tes.services.impl.thirdparty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.StackPollData;
import com.tes.repository.thirdparty.StackPollDataRepository;
import com.tes.services.thirdparty.StackPollDataServices;

@Service
public class StackPollDataServicesImpl implements StackPollDataServices
{

	@Autowired
	StackPollDataRepository stackPollDataRepository;

	@Override
	public StackPollData save(StackPollData stackPollData)
	{
		return stackPollDataRepository.save(stackPollData);
	}

	@Override
	public List<StackPollData> getStackPollData(String stackname, String attachedTo, int month, int year, int regStackId)
	{
		return stackPollDataRepository.getStackPollData(stackname, attachedTo, month, year, regStackId);
	}

	@Override
	public Float getAvgconcPollByYear(String pollName, String stackName, String attachedTo, String fromDate,
			String toDate)
	{
		return stackPollDataRepository.getAvgconcPollByYear(pollName, stackName, attachedTo, fromDate, toDate);
	}

	@Override
	public List<StackPollData> getStackPollDataByYear(String fromDate, String toDate)
	{
		return stackPollDataRepository.getStackPollDataByYear(fromDate, toDate);
	}

	@Override
	public List<StackPollData> getStackPollDataDay(int regStackId)
	{

		return stackPollDataRepository.getStackPollDataDay(regStackId);
	}

	@Override
	public Float getAvgconcPollByName(String pollName, Integer stackId, String dateFrom, String dateTo)
	{

		return stackPollDataRepository.getAvgconcPollByName(pollName, stackId, dateFrom, dateTo);
	}

}
