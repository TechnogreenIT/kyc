package com.tes.services.environmentalofficer;

import java.util.List;
import com.tes.model.WateriePollutant;

public interface WateriePollutantServices
{

	public List<WateriePollutant> findbyConsType();

	public List<WateriePollutant> findByTodayDateAndCid(String today);

	public WateriePollutant save(WateriePollutant wateriePollutant);

	public int updateConsentToOperate(int wateriePollutantId);

	public List<WateriePollutant> getwateriepollutantdata();

	public List<WateriePollutant> findByConsent(int consentId);

	public List<WateriePollutant> findByConsentToOperateAndConsent(int consentId);

	public List<WateriePollutant> findByValidUpto(String todaysDate);

	public List<WateriePollutant> findByTodayDateAndConsentId(String todaysDate, int consentId);

	public WateriePollutant findByWateriePollutantId(int wateriePollutantId);

	public List<WateriePollutant> deleteByWateriePollutantId(int productId);

	List<String> getWateriePolls();

	public List<WateriePollutant> getWateriePollData();

	public List<Object[]> getWateriePollWaterMarkData();

	public List<WateriePollutant> getPollNameAndQuantity();

}
