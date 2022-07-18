package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.WaterSewPoll;

public interface WaterSewPollServices
{

	List<WaterSewPoll> findbyConsType();

	int updateConsentToOperate(int waterSewPollId);

	List<WaterSewPoll> getWaterSewagePollutant();

	List<WaterSewPoll> findByConsent(int consentId);

	List<WaterSewPoll> findByConsentToOperateAndConsent(int consentId);

	List<Object[]> getWaterSewPoll(int consNo);

	// Float getSewAvgOuConsE(String pollName,int esrYear,int earMonth);

	public WaterSewPoll save(WaterSewPoll waterSewPoll);

	public List<WaterSewPoll> findByTodayDateAndCmpid(String today);

	WaterSewPoll findByWaterSewPollId(int pollId);

	List<WaterSewPoll> deleteByWaterSewPollId(int waterSewPollId);

	List<String> getWaterSewPolls();

	List<WaterSewPoll> getWaterSewPollData();

	List<WaterSewPoll> getWaterSewPollNameAndQuantity();

}
