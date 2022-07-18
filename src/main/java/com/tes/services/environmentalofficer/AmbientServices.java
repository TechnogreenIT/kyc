package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.Ambient;

public interface AmbientServices
{

	public Ambient save(Ambient ambient);

	public List<Integer> getAmbientId(String today, int companyId);

	public Ambient findByAmbientId(int ambientId);

	public List<Ambient> findByConsType();

	public int updateConsentToOperate(int ambientId);

	int[] getAmbientIdForMonitor(String today, int companyId);

	public List<Ambient> findByConsentId(int consentId);

	public List<Ambient> findByconsentToOperateAndConsId(int consentId);

	public List<Ambient> deleteByAmbientId(int productId);

	public List<Ambient> findByAmbient(String today);

	public List<Integer> getAmbientIdForAhp(String today, int companyId);
}
