package com.tes.services.impl.environmentalofficer.waterinventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.WastewaterRecycle;
import com.tes.repository.environmentalofficer.waterinventory.WasteWaterRecycleRepository;
import com.tes.services.environmentalofficer.waterinventory.WasteWaterRecycleSevices;

@Service
public class WasteWaterRecycleServicesImpl implements WasteWaterRecycleSevices
{

	@Autowired
	WasteWaterRecycleRepository wasteWaterRecycleRepository;

	@Override
	public void save(WastewaterRecycle wastewaterRecycle)
	{
		wasteWaterRecycleRepository.save(wastewaterRecycle);
	}

	@Override
	public List<WastewaterRecycle> getWasteWaterRecycle(int wastewaterTreatmentId)
	{
		return wasteWaterRecycleRepository.getWasteWaterRecycle(wastewaterTreatmentId);
	}

	@Override
	public List<String> findAllRecycleTypeLabel()
	{
		return wasteWaterRecycleRepository.findAllRecycleTypeLabel();
	}

	@Override
	public List<String> findAllRecycleTypeLabel(String label)
	{
		return wasteWaterRecycleRepository.findAllRecycleTypeLabel(label);
	}

	@Override
	public List<WastewaterRecycle> findAllWasteWaterRecycle()
	{
		return wasteWaterRecycleRepository.findAllWasteWaterRecycle();
	}

	@Override
	public boolean getMetervalueOfRecycledTo(String treatType, String recycledTo)
	{
		return wasteWaterRecycleRepository.getMetervalueOfRecycledTo(treatType, recycledTo);
	}

	/*
	 * @Override public List<WastewaterRecycle> getAllLabelAndRecycleTo() { return
	 * wasteWaterRecycleRepository.getAllLabelAndRecycleTo(); }
	 */

	@Override
	public List<WastewaterRecycle> findAllWasteWaterRecycleBytreatLabel(String treatType, String treatLabel)
	{
		return wasteWaterRecycleRepository.findAllWasteWaterRecycleBytreatLabel(treatType, treatLabel);
	}

	@Override
	public List<WastewaterRecycle> findAllRecycleIdAndLabel(String label)
	{
		return wasteWaterRecycleRepository.findAllRecycleIdAndLabel(label);
	}
}
