package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.WaterSewPoll;
import com.tes.repository.environmentalofficer.WaterSewPollRepository;
import com.tes.services.environmentalofficer.WaterSewPollServices;

@Service
public class WaterSewPollServicesImpl implements WaterSewPollServices
{

	@Autowired
	WaterSewPollRepository waterSewPollRepository;

	@Override
	public List<WaterSewPoll> findbyConsType()
	{
		return waterSewPollRepository.findbyConsType();
	}

	@Override
	public int updateConsentToOperate(int waterSewPollId)
	{
		return waterSewPollRepository.updateConsentToOperate(waterSewPollId);
	}

	@Override
	public List<WaterSewPoll> getWaterSewagePollutant()
	{
		return waterSewPollRepository.getWaterSewagePollutant();
	}

	@Override
	public List<WaterSewPoll> findByConsent(int consentId)
	{
		return waterSewPollRepository.findByConsent(consentId);
	}

	@Override
	public List<WaterSewPoll> findByConsentToOperateAndConsent(int consentId)
	{
		return waterSewPollRepository.findByConsentToOperateAndConsent(consentId);
	}

	@Override
	public List<Object[]> getWaterSewPoll(int consNo)
	{
		return waterSewPollRepository.getWaterSewPoll(consNo);
	}

	/*
	 * @Override public Float getSewAvgOuConsE(String pollName, int esrYear, int
	 * earMonth) { return
	 * waterSewPollRepository.getSewAvgOuConsE(pollName,esrYear,earMonth); }
	 */

	@Override
	public WaterSewPoll save(WaterSewPoll waterSewPoll)
	{
		return waterSewPollRepository.save(waterSewPoll);
	}

	@Override
	public List<WaterSewPoll> findByTodayDateAndCmpid(String today)
	{
		return waterSewPollRepository.findByTodayDateAndCmpid(today);
	}

	@Override
	public WaterSewPoll findByWaterSewPollId(int pollId)
	{
		return waterSewPollRepository.findByWaterSewPollId(pollId);
	}

	@Override
	public List<WaterSewPoll> deleteByWaterSewPollId(int waterSewPollId)
	{
		return waterSewPollRepository.deleteByWaterSewPollId(waterSewPollId);
	}

	@Override
	public List<String> getWaterSewPolls()
	{
		return waterSewPollRepository.getWaterSewPolls();
	}

	@Override
	public List<WaterSewPoll> getWaterSewPollData()
	{
		return waterSewPollRepository.getWaterSewPollData();
	}

	@Override
	public List<WaterSewPoll> getWaterSewPollNameAndQuantity()
	{
		return waterSewPollRepository.getWaterSewPollNameAndQuantity();
	}

}
