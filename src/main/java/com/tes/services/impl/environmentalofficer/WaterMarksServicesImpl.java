package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.WaterMarks;
import com.tes.repository.environmentalofficer.WaterMarksServicesRepository;
import com.tes.services.environmentalofficer.WaterMarksServices;

@Service
public class WaterMarksServicesImpl implements WaterMarksServices
{

	@Autowired
	WaterMarksServicesRepository waterMarksServicesRepository;

	@Override
	public List<String> getAllwaterMarksByMainType(String treatmentType)
	{
		return waterMarksServicesRepository.getAllwaterMarksByMainType(treatmentType);
	}

	@Override
	public List<WaterMarks> getWaterMarksDataByTreatmentAndwmType(String treatmentType, String wmType)
	{
		return waterMarksServicesRepository.getWaterMarksDataByTreatmentAndwmType(treatmentType, wmType);
	}

	@Override
	public List<WaterMarks> getWaterMarksDataByForWateriePoll(String treatmentType, String wmType)
	{
		return waterMarksServicesRepository.getWaterMarksDataByForWateriePoll(treatmentType, wmType);
	}

	@Override
	public List<String> getWaterMarksDataByForWaterSewPollForStp()
	{
		return waterMarksServicesRepository.getWaterMarksDataByForWaterSewPollForStp();
	}

	@Override
	public List<String> ahpWater(String treatmentType, String today)
	{
		return waterMarksServicesRepository.ahpWater(treatmentType, today);
	}

	@Override
	public List<Object[]> ahpWaterObj(String treatmentType, String today)
	{
		return waterMarksServicesRepository.ahpWaterObj(treatmentType, today);
	}

	/*
	 * @Override public Float getRegEffPollData(int Yr,String pollname) { return
	 * waterMarksServicesRepository.getRegEffPollData(Yr,pollname); }
	 */

	@Override
	public List<Object[]> ahpWatersew(String today)
	{
		return waterMarksServicesRepository.ahpWatersew(today);
	}

	// @Override
	// public Float getRegSewPollData(int year, int pollIdn)
	// {
	// return waterMarksServicesRepository.getRegSewPollData(year, pollIdn);
	// }

	@Override
	public List<Object[]> ahpStackObj(int stackId, String poll)
	{
		return waterMarksServicesRepository.ahpStackObj(stackId, poll);
	}

	@Override
	public List<Object[]> ahpStackObjGases(int stackId, String poll)
	{
		return waterMarksServicesRepository.ahpStackObjGases(stackId, poll);
	}

	@Override
	public Float getRegStackPollData(String pollname, int month, int yr, int spid)
	{
		return waterMarksServicesRepository.getRegStackPollData(pollname, month, yr, spid);
	}

	@Override
	public Float getRegAmbientPollData(String pollname, int month, int yr, int ambientId)
	{
		return waterMarksServicesRepository.getRegAmbientPollData(pollname, month, yr, ambientId);
	}

	@Override
	public List<Object[]> getAmbientmarkdata(String poll, int ambientid)
	{
		return waterMarksServicesRepository.getAmbientmarkdata(poll, ambientid);
	}

	@Override
	public List<String> typeList()
	{
		return waterMarksServicesRepository.typeList();
	}
}
