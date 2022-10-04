package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.AllProducts;

@Repository
public interface AllProductsRepository extends JpaRepository<AllProducts, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	AllProducts save(AllProducts allProducts);

	@Query("SELECT NEW AllProducts(p.allProductsId,p.consent,p.allProductName,p.quantity,p.unit,p.users) FROM AllProducts p INNER JOIN p.allProductName pn WHERE pn.type = :productType AND p.consent.consentId =:consentId")
	List<AllProducts> findByProductType(@Param("productType") String productType, @Param("consentId") int consentId);

	@Query("SELECT NEW AllProducts(p.allProductsId,p.consent,p.allProductName,p.quantity,p.unit,p.users) FROM AllProducts p INNER JOIN p.allProductName pn WHERE pn.type = :productType AND p.consent.consentId =:consentId AND pn.productName = :productName")
	List<AllProducts> findByProductType(@Param("productType") String productType, @Param("consentId") int consentId, @Param("productName") String productName);

	@Query("SELECT SUM(a.quantity) FROM AllProducts a, Consent c WHERE a.consent=c.consentId AND c.consType = 'Consent to Operate' AND a.allProductName.allProductNameId = :productId")
	Float quantitySum(@Param("productId") int productId);

	@Query("SELECT  an.productName FROM AllProductComparativeSheet apc  LEFT JOIN apc.allProducts a  LEFT JOIN a.consent c LEFT JOIN a. allProductName an	 WHERE an.type=:type  AND a.consent.consentId=:consentId")
	List<String> productNameList(@Param("type") String type, @Param("consentId") int consentId);

	@Query("SELECT DISTINCT a.allProductName.productName  FROM AllProducts a , Consent c, ConsentExtendedDate ce,AllProductName pn WHERE a.allProductsId = pn.allProductNameId AND a.consent=c.consentId AND ce.consent= c.consentId AND c.consType='Consent to Operate' AND pn.type =:productType AND c.consStatus != 'EXPIRED' AND ce.validUpto >= :today")
	List<String> productNameExtended(@Param("productType") String productType, @Param("today") String today);

	@Query("SELECT SUM(a.quantity) FROM AllProducts a, Consent c WHERE a.consent=c.consentId AND c.consType = 'Consent to Operate' AND a.allProductName.productName = :productName AND c.consStatus != 'EXPIRED'")
	Float sumQtyWithconsStatus(@Param("productName") String productName);

	@Query("SELECT NEW AllProducts(p.allProductsId,p.allProductName,p.quantity,p.unit) FROM AllProducts p,Consent c WHERE p.consent.consentId=c.consentId AND c.consType= :type AND p.consent.consentId= :consentNo")
	List<AllProducts> findByConsTypeAndConsId(@Param("type") String type, @Param("consentNo") int consentNo);

	@Query("SELECT p.allProductsId,p.consent,p.allProductName.productName FROM AllProducts p WHERE p.consent.consentId = 3")
	List<AllProducts> findByTesting();// this is for only testing after CrudRepository

	@Query("SELECT DISTINCT  (u.units) FROM AllProductComparativeSheet apc LEFT JOIN apc.allProducts a  LEFT JOIN a.consent c LEFT JOIN a. allProductName an LEFT JOIN a.unit u WHERE c.consType LIKE 'Consent to Operate' AND  an.type LIKE :productType")
	List<String> findGetunitByProductType(@Param("productType") String productType);

	@Query("SELECT u.units FROM Unit u, AllProducts a, Consent c WHERE  a.consent=c.consentId AND c.consType = 'Consent to Operate' AND a.allProductName.productName = :productName")
	List<Integer> getUnitsFromAllProducts(@Param("productName") String productName);

	@Query("SELECT distinct(a.allProductName.allProductNameId) FROM AllProducts a WHERE a.allProductName.productName=:productName")
	List<Integer> AllProductIdByProductName(@Param("productName") String productName);

	@Modifying
	@Transactional
	@Query("UPDATE AllProducts p SET p.quantity =:quantity, p.unit.unitId =:unitId WHERE p.allProductsId =:productId")
	int updateProductById(@Param("productId") int productId, @Param("quantity") float quantity, @Param("unitId") int unitId);

	@Query("SELECT an.productName FROM AllProductComparativeSheet apc  INNER JOIN apc.allProducts a  INNER JOIN a.consent c INNER JOIN a.allProductName an  INNER JOIN a.unit u WHERE c.issueDate <=:esrConsentDate  AND u.units = :units  AND c.consType ='Consent to Operate' AND an.type =:productType GROUP BY an.productName")
	public List<String> findOneByProductTypeUnits(@Param("productType") String productType, @Param("units") String units, @Param("esrConsentDate") String esrConsentDate);

	@Query("SELECT MAX(apc.quantity) AS quantity FROM AllProductComparativeSheet apc INNER JOIN apc.allProducts a   INNER JOIN a.consent c  INNER JOIN a.allProductName an INNER JOIN a.unit u WHERE c.issueDate <=:consentDate   AND c.consType ='Consent to Operate'  AND an.productName =:productName GROUP BY  an.productName")
	Float findOneByGetConsentData(@Param("productName") String productName, @Param("consentDate") String consentDate);

	@Query("SELECT COUNT (r.regularDataId) FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE apn.productName=:productName  AND r.inputDate BETWEEN :pdate AND :today")
	int getNumberFromRegularData(@Param("productName") String productName, @Param("pdate") String pdate, @Param("today") String today);

	@Query("SELECT cp.noWorkDays FROM CompanyProfile cp WHERE cp.companyProfileId =:companyId")
	int getNoWorkDays(@Param("companyId") int companyId);

	@Query("SELECT  DISTINCT(apn.productName),u.units FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  LEFT JOIN a.unit u WHERE apn.type= :productType AND r.inputDate BETWEEN :pdate AND :today")
	List<Object[]> findOneByGetProductIdUnits(@Param("productType") String productType, @Param("pdate") String pdate, @Param("today") String today);

	@Query("SELECT MAX(apc.quantity) AS quantity  FROM AllProductComparativeSheet apc  INNER JOIN apc.allProducts a  INNER JOIN a.consent c  INNER JOIN a. allProductName an INNER JOIN a.unit u 	  WHERE  c.issueDate <=:today    AND c.consType ='Consent to Operate'   AND an.type =:productName GROUP BY  an.productName")
	public float getSumOfQuantity(@Param("today") String today, @Param("productName") String productName);

	@Query("SELECT  DISTINCT(apn.productName),u.units  FROM RegularData r  LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a  LEFT JOIN a.allProductName apn   LEFT JOIN a.unit u WHERE apn.type= :productType ")
	List<Object[]> getProductByType(@Param("productType") String productType);

	@Query("SELECT DISTINCT (a.quantity)  FROM AllProductComparativeSheet apc  INNER JOIN apc.allProducts a   INNER JOIN a.consent c  INNER JOIN a. allProductName an WHERE  c.consType ='Consent to Operate'   AND an.productName =:pName")
	Float getQuantityByProductName(@Param("pName") String pName);
}
