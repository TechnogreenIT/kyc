package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.AirMarks;
import com.tes.model.Consent;
import com.tes.model.RegularData;
import com.tes.repository.environmentalofficer.DailyInputRepository;
import com.tes.services.environmentalofficer.DailyInputServices;

@Service
public class DailyInputServicesImpl implements DailyInputServices
{

	@Autowired
	DailyInputRepository dailyInputRepository;

	@Override
	public RegularData save(RegularData regularData)
	{
		return dailyInputRepository.save(regularData);
	}

	@Override
	public List unitList()
	{
		return dailyInputRepository.unitList();
	}

	@Override
	public List<Consent> consentByEstablish(String consType2)
	{
		return dailyInputRepository.consentByEstablish(consType2);
	}

	@Override
	public List consentByOperate(String date)
	{
		return dailyInputRepository.consentByOperate(date);
	}

	@Override
	public List<Consent> consentdata()
	{
		return dailyInputRepository.consentdata();
	}

	@Override
	public List<Integer> effId()
	{
		return dailyInputRepository.effId();
	}

	@Override
	public List<Integer> sewPollId()
	{
		return dailyInputRepository.sewPollId();
	}

	@Override
	public List attachedTo()
	{
		return dailyInputRepository.attachedTo();
	}

	@Override
	public List<Float> masterMarks(String[] attachedTo)
	{
		return dailyInputRepository.masterMarks(attachedTo);
	}

	@Override
	public List<AirMarks> airMarks(String[] attachedTo)
	{
		return dailyInputRepository.airMarks(attachedTo);
	}

	@Override
	public List<Object[]> allProductsDataByType(String[] productType, String date)
	{
		return dailyInputRepository.allProductsDataByType(productType, date);
	}

	@Override
	public List allProductsDataByNHW(String date)
	{
		return dailyInputRepository.allProductsDataByNHW(date);
	}

	@Override
	public List<Object[]> dataAllProductConsentExtended(String typeP, String today)
	{
		return dailyInputRepository.dataAllProductConsentExtended(typeP, today);
	}

	@Override
	public List<Float> quantityAllProducts(Object productName, Object consentId, Object unit)
	{
		return dailyInputRepository.quantityAllProducts(productName, consentId, unit);
	}

	@Override
	public List wmType(String today)
	{
		return dailyInputRepository.wmType(today);
	}

	@Override
	public List<Object[]> wmData(Object[] tt)
	{
		return dailyInputRepository.wmData(tt);
	}

	@Override
	public List<Object[]> wmDataByPollAndEff(String today, Object[] tt)
	{
		return dailyInputRepository.wmDataByPollAndEff(today, tt);
	}

	@Override
	public List<Object[]> dataWatMarkPoll(String today)
	{
		return dailyInputRepository.dataWatMarkPoll(today);
	}

	/*
	 * @Override public List ouConsE(String item) { return
	 * dailyInputRepository.ouConsE(item); }
	 */

	@Override
	public List wmTypeBySTP(String today)
	{
		return dailyInputRepository.wmTypeBySTP(today);
	}

}
