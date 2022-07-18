package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.WaterSewPollOp;
import com.tes.repository.environmentalofficer.WaterSewPollOpRepository;
import com.tes.services.environmentalofficer.WaterSewPollOpServices;

@Service
public class WaterSewPollOpServicesImpl implements WaterSewPollOpServices
{

	@Autowired
	WaterSewPollOpRepository waterSewPollOpRepository;

	public WaterSewPollOp save(WaterSewPollOp waterSewPollOp)
	{

		return waterSewPollOpRepository.save(waterSewPollOp);
	}

	@Override
	public int deleteByWaterSewPollIdOp(int productId)
	{
		return waterSewPollOpRepository.deleteByWaterSewPollIdOp(productId);

	}

	@Override
	public List<WaterSewPollOp> findByTodayDateAndCmpid(String today)
	{
		return waterSewPollOpRepository.findByTodayDateAndCmpid(today);
	}

}
