package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.AllProductComparativeSheet;

@Repository
public interface AllProductComparativeSheetRepository extends JpaRepository<AllProductComparativeSheet, Long>
{
	@Query("SELECT apc.allProductComparativeSheetId FROM AllProductComparativeSheet apc WHERE apc.allProducts.allProductName.allProductNameId=:productId")
	public Integer findComparativeSheetIdByAllProductId(@Param("productId") int productId);

	@Query("SELECT  SUM(p.quantity) FROM AllProducts p INNER JOIN p.consent c WHERE p.consent = c.consentId AND c.consType = 'Consent to Operate' AND p.allProductName.allProductNameId = :allProductNameId")
	public float sumOfQuantityByCtoO(@Param("allProductNameId") int allProductNameId);

	@Query("SELECT  p.quantity FROM AllProducts p INNER JOIN p.consent c WHERE p.consent = c.consentId AND c.consType = 'Consent to Establish' AND p.allProductName.allProductNameId = :allProductNameId")
	public float findByQuantityByCtoE(@Param("allProductNameId") int allProductNameId);

	@Modifying
	@Transactional
	@Query("UPDATE AllProductComparativeSheet apc SET apc.status = 'Inactive' WHERE apc.allProducts IN (SELECT p.allProductsId FROM AllProducts p WHERE p.allProductName.allProductNameId = :allProductNameId)")
	public int setInactiveByAllProductNameId(@Param("allProductNameId") int allProductNameId);

	@Query("SELECT MAX(apc.quantity) AS quantity,pn.productName,u.units FROM AllProductComparativeSheet apc INNER JOIN apc.allProducts a  INNER JOIN a.consent c INNER JOIN a. allProductName pn INNER JOIN a.unit u WHERE c.issueDate <=:esrConsentDate  AND c.consType ='Consent to Operate' AND pn.type =:productType AND pn.status='Active' GROUP BY  pn.productName")
	public List<Object[]> getAllProductComparativeSheet(@Param("productType") String productType, @Param("esrConsentDate") String esrConsentDate);

	@Query("SELECT MAX(apc.quantity) AS quantity,pn.productName,u.units FROM AllProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.consent c LEFT JOIN c.consentExtendedDate ce LEFT JOIN a.allProductName pn LEFT JOIN a.unit u WHERE c.consType ='Consent to Operate' AND pn.status='Active' AND pn.type =:productType AND (c.validUpto >=:todayDate OR ce.validUpto>=:todayDate) GROUP BY pn.productName")
	public List<Object[]> getAllProductComparativeSheetForDailyInput(@Param("productType") String productType, @Param("todayDate") String todayDate);

	@Query("SELECT MAX(apc.quantity) AS quantity,pn.productName,u.units  FROM AllProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.consent c INNER JOIN a. allProductName pn INNER JOIN a.unit u WHERE c.issueDate <=:esrConsentDate AND c.consType ='Consent to Operate' AND (pn.type = 'hwp' OR pn.type = 'hwpcf' OR pn.type = 'hwpcf' OR pn.type = 'nhwp' OR pn.type = 'nhwpcf') GROUP BY pn.productName")
	public List<Object[]> getAllProductComparativeSheetSolidWaste(@Param("esrConsentDate") String esrConsentDate);

	@Query("SELECT apc.allProductComparativeSheetId FROM AllProductComparativeSheet apc  INNER JOIN apc.allProducts a INNER JOIN a.consent c INNER JOIN a. allProductName pn  WHERE   pn.productName=:productName ORDER BY apc.allProductComparativeSheetId desc")
	public List<Integer> getAllProductComparativeSheetIdByProductName(@Param("productName") String productName, Pageable pageable);

	@Query("SELECT DISTINCT NEW AllProductComparativeSheet(apc.allProducts,MAX(apc.quantity)) FROM AllProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.consent c INNER JOIN a.allProductName an INNER JOIN a.unit u WHERE c.consStatus!='EXPIRED' AND c.consType='Consent to Operate' AND c.issueDate<=:selectedDate AND an.type=:type AND an.status='Active' GROUP BY an.productName")
	public List<AllProductComparativeSheet> getAllProductsDetailsByYear(@Param("selectedDate") String selectedDate, @Param("type") String type);

	@Query("SELECT DISTINCT(an.productName) FROM AllProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN  a.consent c INNER JOIN  a.allProductName an INNER JOIN  a.unit u WHERE c.consStatus!='EXPIRED' AND c.consType='Consent to Operate' AND c.issueDate<=:esrDate AND an.type=:productType AND an.status='Active'")
	public List<String> getDistinctProductNameByEsrYear(@Param("productType") String productType, @Param("esrDate") String esrDate);

	@Query("SELECT cs FROM AllProductComparativeSheet cs LEFT JOIN cs.allProducts p LEFT JOIN p.allProductName pn WHERE cs.status ='Active' AND pn.type =:type")
	public List<AllProductComparativeSheet> getAllProductByType(@Param("type") String type);

	@Query("SELECT apcs.id FROM AllProductComparativeSheet apcs INNER JOIN apcs.allProducts ap INNER JOIN ap.allProductName pn WHERE pn.productName =:pName AND apcs.status='Active'")
	public int findComparativeSheetIdByPName(@Param("pName") String pName);

	// @Query("SELECT SUM(apc.quantity) AS quantity,pn.productName,u.units FROM AllProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.consent c INNER JOIN a. allProductName pn INNER JOIN a.unit u WHERE c.issueDate <=:today AND c.consType ='Consent to Operate' AND pn.type =:productType AND pn.status='Active' GROUP BY pn.productName")
	// public List<Object[]> ahpHazardous(@Param("productType")String productType,@Param("today") String today);

	@Query("SELECT pn.productName, apc.quantity FROM AllProductComparativeSheet apc  LEFT JOIN apc.allProducts a   LEFT JOIN a.consent c LEFT JOIN a. allProductName pn  WHERE c.issueDate <=:today   AND c.consType ='Consent to Operate'  AND pn.type =:productType GROUP BY  pn.productName")
	public List<Object[]> ahpHazardous(@Param("productType") String productType, @Param("today") String today);

	@Query("SELECT COUNT(DISTINCT type) FROM  AllProductName WHERE  type='hwp' OR type='hwpcf' OR type='nhwp' OR type='nhwpcf' ")
	public int countType();

	@SuppressWarnings("unchecked")
	AllProductComparativeSheet save(AllProductComparativeSheet allProductComparativeSheet);

	// @Query("SELECT pn.productName FROM AllProductComparativeSheet apcs INNER JOIN apcs.allProducts ap INNER JOIN ap.allProductName pn WHERE pn.productName =:pName AND apcs.status='Active'")
	// public String getComparativeSheetByPName(@Param("pName") String pName);

}
