package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.AllProductName;

@Repository
public interface AllProductNameRepository extends JpaRepository<AllProductName, Long>
{
	@Modifying
	@Transactional
	@Query("UPDATE AllProductName p SET p.productName = :productName WHERE p.allProductNameId = :allProductNameId")
	int updateProductNameById(@Param("allProductNameId") int allProductNameId,
			@Param("productName") String productName);

	@Modifying
	@Transactional

	@Query("UPDATE AllProductName p SET p.status = :status WHERE p.allProductNameId = :allProductNameId")
	int updateStatusByAllProductNameId(@Param("allProductNameId") int productNameId, @Param("status") String status);

	// not in use
	@Query(value = "SELECT DISTINCT(apn.product_name) FROM all_products a, consent c,all_product_name apn,all_product_comparative_sheet apc WHERE valid_upto >= :date AND a.consent_id=c.id AND apn.type = :type AND apc.all_products_id=a.id AND a.all_product_name_id=apn.id AND c.cons_type='Consent to Operate'", nativeQuery = true)
	public String getProductNameForModal(@Param("date") String date, @Param("type") String type);

	@Query("SELECT DISTINCT(an.productName) FROM AllProductComparativeSheet apc LEFT JOIN apc.allProducts a  LEFT JOIN a.consent c  LEFT JOIN a.allProductName an	  WHERE an.type =:productType   AND c.validUpto >=:today   AND c.consType='Consent to Operate'")
	public List<String> getProductName(@Param("productType") String productType, @Param("today") String today);

	@Query("SELECT pn FROM AllProducts p LEFT JOIN p.allProductName pn WHERE p.consent.consType ='Consent to Establish' AND pn.type= :productType")
	List<AllProductName> getProductsByProductType(@Param("productType") String productType);

	@Query("SELECT pn FROM AllProducts p LEFT JOIN p.allProductName pn WHERE p.consent.consentId = :consentId AND pn.type= :productType")
	List<AllProductName> getProductsByProductType(@Param("consentId") int consentId, @Param("productType") String productType);

	@Query("SELECT DISTINCT(an.productName) FROM AllProductName an, AllProducts a, AllProductComparativeSheet apc, RegularData r WHERE a.allProductName.allProductNameId=an.allProductNameId AND a.allProductsId=apc.allProducts.allProductsId AND apc.allProductComparativeSheetId=r.allProductComparativeSheet.allProductComparativeSheetId AND r.allProductComparativeSheet.allProductComparativeSheetId= :regulardataProductId")
	List<String> getProductNameFromRegularData(@Param("regulardataProductId") int regulardataProductId);

	@Query("SELECT DISTINCT(apn.productName),u.units FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn LEFT JOIN a.unit u WHERE apn.type= 'Product'")
	List<Object[]> getProductNameUnitsByProductType();

	@Query("SELECT pn.productName FROM AllProductComparativeSheet apc  INNER JOIN apc.allProducts a INNER JOIN a.consent c  INNER JOIN a.allProductName pn INNER JOIN a.unit u WHERE c.issueDate <= :date AND c.consType = 'Consent to Operate'  AND pn.type = :type  GROUP BY pn.productName")
	List<String> getProductNameByTypeAndDate(@Param("date") String date, @Param("type") String type);

	@Query("SELECT pn.productName FROM AllProductComparativeSheet apc  INNER JOIN apc.allProducts a INNER JOIN a.consent c  INNER JOIN a.allProductName pn INNER JOIN a.unit u WHERE c.issueDate <= :date AND c.consType = 'Consent to Operate'  AND pn.type = :type AND u.units= :unit GROUP BY pn.productName")
	List<String> getProductNameByTypeDateAndUnit(@Param("date") String date, @Param("type") String type, @Param("unit") String unit);

	@Query("SELECT DISTINCT(an.allProductNameId) FROM AllProductName an WHERE an.productName=:productName")
	int prouctNameIdByProductName(@Param("productName") String productName);

	@Query("SELECT DISTINCT(ap.productName) FROM AllProductComparativeSheet apc INNER JOIN  apc.allProducts a INNER JOIN a.allProductName ap INNER JOIN a.consent c WHERE ap.type='product' OR ap.type='byproduct' AND ap.status='Active' AND (c.issueDate <=:esrConsentDate) ")
	List<String> getProductNameByConsentToEstablish(@Param("esrConsentDate") String esrConsentDate);
}
