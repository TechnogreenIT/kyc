package com.tes.services.environmentalofficer;

import java.util.List;
import com.tes.model.WaterMarks;

public interface WaterMarksServices
{

	public List<String> getAllwaterMarksByMainType(String treatmentType);

	public List<WaterMarks> getWaterMarksDataByTreatmentAndwmType(String treatmentType, String wmType);

	public List<WaterMarks> getWaterMarksDataByForWateriePoll(String treatmentType, String wmType);

	public List<String> getWaterMarksDataByForWaterSewPollForStp();

	public List<String> ahpWater(String treatmentType, String today);

	public List<Object[]> ahpWaterObj(String treatmentType, String today);

	// public Float getRegEffPollData(int Yr, String pollname);

	public List<Object[]> ahpWatersew(String today);

	// public Float getRegSewPollData(int year, int pollIdn);

	public List<Object[]> ahpStackObj(int stackId, String poll);

	Float getRegStackPollData(String pollname, int month, int yr, int spid);

	public List<Object[]> ahpStackObjGases(int stackId, String poll);

	public List<Object[]> getAmbientmarkdata(String poll, int ambientid);

	Float getRegAmbientPollData(String pollname, int month, int yr, int ambientId);

	public List<String> typeList();
}
