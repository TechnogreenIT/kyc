package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Unit;
import com.tes.repository.environmentalofficer.UnitRepository;
import com.tes.services.environmentalofficer.UnitServices;

@Service
public class UnitServicesImpl implements UnitServices
{
	@Autowired
	UnitRepository unitRepository;

	@Override
	public List<Unit> findAll()
	{
		return unitRepository.findAll();
	}

	@Override
	public List<String> unitName(int productNameId)
	{
		return unitRepository.unitName(productNameId);
	}

	@Override
	public String getUnitFromAllProducts(String productName)
	{
		return unitRepository.getUnitFromAllProducts(productName);
	}

	@Override
	public List<String> getUnitsFromPType(String type, String date)
	{
		return unitRepository.getUnitsFromPType(type, date);
	}

	@Override
	public Unit findUnitIdByUnits(String unitsName)
	{
		return unitRepository.findUnitIdByUnits(unitsName);
	}

	@Override
	public Unit save(Unit Unit)
	{
		return unitRepository.save(Unit);
	}

	@Override
	public String getUnitByDateTypeAndProductName(String date, String type, String pName)
	{
		return unitRepository.getUnitByDateTypeAndProductName(date, type, pName);
	}

	@Override
	public Unit findByUnitId(int unitId)
	{
		return unitRepository.findByUnitId(unitId);
	}

	@Override
	public int updateUnitByUnitId(int unitId)
	{
		return unitRepository.updateUnitByUnitId(unitId);
	}

	@Override
	public List<Unit> deleteByUnitId(int unitId)
	{
		return unitRepository.deleteByUnitId(unitId);
	}

	@Override
	public List<String> getUnitsFromProductType(String type)
	{

		return null;
	}

}
