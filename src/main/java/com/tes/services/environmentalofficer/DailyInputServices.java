package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.AirMarks;
import com.tes.model.Consent;
import com.tes.model.RegularData;

public interface DailyInputServices
{
	RegularData save(RegularData regularData);

	List unitList();

	List<Consent> consentByEstablish(String consType2);

	List consentByOperate(String date);

	List<Consent> consentdata();

	List<Integer> effId();

	List<Integer> sewPollId();

	List attachedTo();

	List<Float> masterMarks(String[] attachedArray);

	List<AirMarks> airMarks(String[] attachedTo);

	List<Object[]> allProductsDataByType(String productType[], String date);

	List<Object[]> allProductsDataByNHW(String date);

	List<Object[]> dataAllProductConsentExtended(String typeP, String today);

	List<Float> quantityAllProducts(Object productName, Object consentId, Object unit);

	List wmType(String today);

	List<Object[]> wmData(Object[] tt);

	List<Object[]> wmDataByPollAndEff(String today, Object[] tt);

	List<Object[]> dataWatMarkPoll(String today);

	// List ouConsE(String item);
	List wmTypeBySTP(String today);

}
