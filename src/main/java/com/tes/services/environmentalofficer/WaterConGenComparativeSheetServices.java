package com.tes.services.environmentalofficer;

import com.tes.model.WaterConGenComparativeSheet;

public interface WaterConGenComparativeSheetServices
{

	WaterConGenComparativeSheet save(WaterConGenComparativeSheet objWaterConGenComparativeSheet);

	public int setStatusInactiveToAll(byte status);

	float getConsByStatusIsActiveAndWaterConGenParameterId(int waterConGenParameterId);// no need of this query ....by vishal

	float getGenByStatusIsActiveAndWaterConGenParameterId(int waterConGenParameterId);// no need of this query ....by vishal

	Float getSumOfProcessCons(String name, String today);

	Float getSumOfProcessGen(String name, String today);

	Float getcons();

	Float getSumOfProcessGenMonth(String string, int consentId);

	Float getSumOfProcessConMonth(String string, int consentId);

}
