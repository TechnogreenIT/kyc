package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tes.model.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long>
{

	@Override
	List<Unit> findAll();

	@Query("SELECT DISTINCT(u.units) FROM Unit u, AllProducts a, AllProductName an WHERE a.allProductName.allProductNameId= :productNameId  AND a.unit.unitId=u.unitId")
	List<String> unitName(@Param("productNameId") int productNameId);

	// remain
	@Query(value = "SELECT DISTINCT(u.units) FROM all_products a,all_product_name apn,unit u WHERE apn.product_name = :productName AND  a.all_product_name_id=apn.id AND a.unit_id=u.id limit 1", nativeQuery = true)
	public String getUnitFromAllProducts(@Param("productName") String productName);

	@Query("SELECT DISTINCT(u.units) FROM AllProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN  a.consent c  LEFT JOIN  a.allProductName apn LEFT JOIN a.unit u WHERE apn.type= :type AND c.consType = 'Consent to Operate'  AND c.issueDate <= :date")
	public List<String> getUnitsFromPType(@Param("type") String type, @Param("date") String date);

	@Query("SELECT u FROM Unit u WHERE u.units = :unitsName")
	Unit findUnitIdByUnits(@Param("unitsName") String unitsName);

	@Query("SELECT DISTINCT(u.units) FROM AllProductComparativeSheet apc  INNER JOIN apc.allProducts a  INNER JOIN a.consent c INNER JOIN a. allProductName an	INNER JOIN a.unit u WHERE c.issueDate <=:date  AND c.consType ='Consent to Operate' AND an.type =:type AND an.productName=:pName")
	public String getUnitByDateTypeAndProductName(@Param("date") String date, @Param("type") String type, @Param("pName") String pName);

	@Query("SELECT u FROM Unit u WHERE u.unitId = :unitId")
	Unit findByUnitId(@Param("unitId") int unitId);

	@Transactional
	@Modifying
	@Query("UPDATE Unit u SET u.units= 'vishal_tested' WHERE u.unitId= :unitId")
	int updateUnitByUnitId(@Param("unitId") int unitId);

	@Transactional
	List<Unit> deleteByUnitId(int unitId2);

	@Query("SELECT DISTINCT(u.units) FROM AllProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN  a.allProductName apn LEFT JOIN a.unit u WHERE apn.type= :type")
	public List<String> getUnitsFromProductType(@Param("type") String type);
}
