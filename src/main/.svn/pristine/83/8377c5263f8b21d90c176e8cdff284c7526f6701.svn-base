package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.WateriePollutantOp;
import com.tes.repository.environmentalofficer.WateriePollutantOpRepository;
import com.tes.services.environmentalofficer.WateriePollutantOpServices;

@Service
public class WateriePollutantOpServicesImpl implements WateriePollutantOpServices
{

	@Autowired
	WateriePollutantOpRepository wateriePollutantOpRepository;

	@Override
	public WateriePollutantOp save(WateriePollutantOp wateriePollutantOp)
	{

		return wateriePollutantOpRepository.save(wateriePollutantOp);
	}

	@Override
	public int deleteWateriePollutantOp(int productId)
	{
		return wateriePollutantOpRepository.deleteWateriePollutantOp(productId);
	}

	@Override
	public List<WateriePollutantOp> findByTodayDateAndCid(String today)
	{
		// TODO Auto-generated method stub
		return wateriePollutantOpRepository.findByTodayDateAndCid(today);
	}

}
