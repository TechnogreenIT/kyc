package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.WateriePollutant;
import com.tes.repository.environmentalofficer.WateriePollutantRepository;
import com.tes.services.environmentalofficer.WateriePollutantServices;

@Service
public class WateriePollutantServicesImpl implements WateriePollutantServices
{

	@Autowired
	WateriePollutantRepository wateriePollutantRepository;

	@Override
	public List<WateriePollutant> findbyConsType()
	{
		return wateriePollutantRepository.findbyConsType();
	}

	@Override
	public List<WateriePollutant> findByTodayDateAndCid(String today)
	{
		return wateriePollutantRepository.findByTodayDateAndCid(today);
	}

	@Override
	public WateriePollutant save(WateriePollutant wateriePollutant)
	{
		return wateriePollutantRepository.save(wateriePollutant);
	}

	@Override
	public int updateConsentToOperate(int wateriePollutantId)
	{
		return wateriePollutantRepository.updateConsentToOperate(wateriePollutantId);
	}

	@Override
	public List<WateriePollutant> getwateriepollutantdata()
	{
		return wateriePollutantRepository.getwateriepollutantdata();
	}

	@Override
	public List<WateriePollutant> findByConsent(int consentId)
	{
		return wateriePollutantRepository.findByConsent(consentId);
	}

	@Override
	public List<WateriePollutant> findByConsentToOperateAndConsent(int consentId)
	{
		return wateriePollutantRepository.findByConsentToOperateAndConsent(consentId);
	}

	@Override
	public List<WateriePollutant> findByValidUpto(String todaysDate)
	{
		return wateriePollutantRepository.findByValidUpto(todaysDate);
	}

	@Override
	public List<WateriePollutant> findByTodayDateAndConsentId(String todaysDate, int consentId)
	{
		return wateriePollutantRepository.findByTodayDateAndConsentId(todaysDate, consentId);
	}

	@Override
	public WateriePollutant findByWateriePollutantId(int wateriePollutantId)
	{
		return wateriePollutantRepository.findByWateriePollutantId(wateriePollutantId);
	}

	@Override
	public List<WateriePollutant> deleteByWateriePollutantId(int wateriePollutantId)
	{
		return wateriePollutantRepository.deleteByWateriePollutantId(wateriePollutantId);
	}

	@Override
	public List<String> getWateriePolls()
	{
		return wateriePollutantRepository.getWateriePolls();
	}

	@Override
	public List<WateriePollutant> getWateriePollData()
	{
		return wateriePollutantRepository.getWateriePollData();
	}

	@Override
	public List<Object[]> getWateriePollWaterMarkData()
	{
		return wateriePollutantRepository.getWateriePollWaterMarkData();
	}

	@Override
	public List<WateriePollutant> getPollNameAndQuantity()
	{
		return wateriePollutantRepository.getPollNameAndQuantity();
	}
}
