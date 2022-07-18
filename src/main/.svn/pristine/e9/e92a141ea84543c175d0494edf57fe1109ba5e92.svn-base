package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Ambient;
import com.tes.repository.environmentalofficer.AmbientRepository;
import com.tes.services.environmentalofficer.AmbientServices;

@Service
public class AmbientServicesImpl implements AmbientServices
{

	@Autowired
	AmbientRepository ambientRepository;

	@Override
	public Ambient save(Ambient ambient)
	{
		return ambientRepository.save(ambient);
	}

	@Override
	public List<Integer> getAmbientId(String today, int companyId)
	{
		return ambientRepository.getAmbientId(today, companyId);
	}

	@Override
	public List<Ambient> findByConsType()
	{
		return ambientRepository.findByConsType();
	}

	@Override
	public int[] getAmbientIdForMonitor(String today, int companyId)
	{
		return ambientRepository.getAmbientIdForMonitor(today, companyId);
	}

	@Override
	public int updateConsentToOperate(int ambientId)
	{
		return ambientRepository.updateConsentToOperate(ambientId);
	}

	@Override
	public List<Ambient> findByConsentId(int consentId)
	{
		return ambientRepository.findByConsentId(consentId);
	}

	@Override
	public List<Ambient> findByconsentToOperateAndConsId(int consentId)
	{
		return ambientRepository.findByconsentToOperateAndConsId(consentId);
	}

	@Override
	public List<Ambient> deleteByAmbientId(int productId)
	{
		return ambientRepository.deleteByAmbientId(productId);
	}

	@Override
	public Ambient findByAmbientId(int ambientId)
	{
		return ambientRepository.findByAmbientId(ambientId);
	}

	@Override
	public List<Ambient> findByAmbient(String today)
	{
		return ambientRepository.findByAmbient(today);
	}

	@Override
	public List<Integer> getAmbientIdForAhp(String today, int companyId)
	{
		return ambientRepository.getAmbientIdForAhp(today, companyId);
	}
}
