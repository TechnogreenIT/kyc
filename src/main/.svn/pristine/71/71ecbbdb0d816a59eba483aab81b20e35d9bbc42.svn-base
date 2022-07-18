package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.Unit;

public interface UnitServices
{

	List<Unit> findAll();

	List<String> unitName(int productNameId);

	/* List<Unit> getBioMedicalUnits(); */

	public String getUnitFromAllProducts(String productName);

	public List<String> getUnitsFromPType(String type, String date);

	public Unit findUnitIdByUnits(String unitsName);

	Unit save(Unit Unit);

	public String getUnitByDateTypeAndProductName(String date, String type, String pName);

	Unit findByUnitId(int unitId);

	int updateUnitByUnitId(int unitId);

	List<Unit> deleteByUnitId(int unitId);

	public List<String> getUnitsFromProductType(String type);

}
