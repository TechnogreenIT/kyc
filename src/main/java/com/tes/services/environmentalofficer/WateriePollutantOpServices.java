package com.tes.services.environmentalofficer;

import java.util.List;
import com.tes.model.WateriePollutantOp;

public interface WateriePollutantOpServices
{

	WateriePollutantOp save(WateriePollutantOp wateriePollutantOp);

	int deleteWateriePollutantOp(int productId);

	List<WateriePollutantOp> findByTodayDateAndCid(String today);
}
