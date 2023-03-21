package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.RegularData;

@Repository
public interface RegularDataRepository extends JpaRepository<RegularData, Long>
{
	@Override
	@SuppressWarnings("unchecked")
	RegularData save(RegularData regularData);

	@Query("SELECT rd  FROM RegularData rd WHERE rd.allProductComparativeSheet.allProductComparativeSheetId=:productId  AND rd.inputDate = :today  ORDER BY rd.regularDataId DESC ")
	List<RegularData> getRegularData(@Param("productId") int productId, @Param("today") String today, Pageable pageble);

	@Query("SELECT SUM(r.quantity) FROM RegularData r WHERE r.allProductComparativeSheet.allProductComparativeSheetId=:productId AND EXTRACT(YEAR FROM r.inputDate) BETWEEN :currentYear AND :currentYear+1 AND  r.inputDate BETWEEN :cm1 AND :cm2")
	Float qtySumRegDataWithNextYear(@Param("productId") int productId, @Param("currentYear") int currentYear, @Param("cm1") String cm1, @Param("cm2") String cm2);

	@Query("SELECT SUM(r.quantity) FROM RegularData r WHERE r.allProductComparativeSheet.allProductComparativeSheetId=:productId  AND EXTRACT(YEAR FROM r.inputDate) BETWEEN :currentYear1-1 AND :currentYear1 AND  r.inputDate BETWEEN :cm1 AND :cm2")
	Float qtySumRegDataWithPreviousYear(@Param("productId") int productId, @Param("currentYear1") int currentYear1, @Param("cm1") String cm1, @Param("cm2") String cm2);

	@Query("SELECT SUM(r.quantity) FROM RegularData r INNER JOIN r.allProductComparativeSheet apc  INNER JOIN apc.allProducts a  INNER JOIN a.consent c INNER JOIN a. allProductName apn WHERE apn.productName= :productName AND (r.inputDate BETWEEN :datefrom AND :dateto)  AND EXTRACT(MONTH FROM r.inputDate) = :month ")
	Float findRegulardataSum(@Param("productName") String productName, @Param("datefrom") String datefrom, @Param("dateto") String dateto, @Param("month") int month);

	@Query("SELECT DISTINCT (r.regularDataId) FROM RegularData r, AllProducts a WHERE r.allProductComparativeSheet.allProducts.allProductName.allProductNameId = a.allProductName.allProductNameId AND a.allProductName.type = 'hwp' OR a.allProductName.type = 'hwpcf'")
	List<Integer> rdDataId();

	@Query("SELECT DISTINCT(EXTRACT(YEAR FROM r.inputDate)) FROM RegularData r ORDER BY r.inputDate ASC")
	List<Integer> getdate2regulardata();

	@Query("SELECT r.regularDataId,r.allProductComparativeSheet.allProducts.allProductName.productName,r.quantity,r.inputDate,e.employeeName,a.unit.units FROM RegularData r,EmpData e,AllProducts a WHERE r.users.usersId = e.users.usersId AND r.allProductComparativeSheet.allProducts.allProductsId = a.allProductsId AND a.allProductName.type = 'Product' AND (EXTRACT(YEAR FROM r.inputDate)) = :inputDate ORDER BY r.inputDate DESC")
	List<Object[]> getProductDetails(@Param("inputDate") int inputDate);

	@Query("SELECT r.quantity FROM  RegularData r  LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE apn.productName= :productName AND r.inputDate=:date")
	Float quantityByProductNameDate(@Param("productName") String productName, @Param("date") String date);

	@Query("SELECT r.regularDataId, r.allProductComparativeSheet.allProducts.allProductName.productName, r.quantity, r.inputDate, e.employeeName, a.unit.units FROM RegularData r, EmpData e, AllProducts a WHERE r.users.usersId = e.users.usersId AND r.allProductComparativeSheet.allProducts.allProductsId = a.allProductsId AND a.allProductName.type = 'byproduct' AND (EXTRACT(YEAR FROM r.inputDate)) = :year ORDER BY r.inputDate DESC")
	List<Object[]> getByProductDetails(@Param("year") int year);

	@Query("SELECT r.regularDataId,r.allProductComparativeSheet.allProducts.allProductName.productName,r.quantity,r.inputDate,e.employeeName,a.unit.units FROM RegularData r,EmpData e,AllProducts a WHERE r.users.usersId = e.users.usersId AND r.allProductComparativeSheet.allProducts.allProductsId = a.allProductsId AND a.allProductName.type = 'Raw Material' AND (EXTRACT(YEAR FROM r.inputDate)) = :year ORDER BY r.inputDate DESC")
	List<Object[]> getRawMaterialDetails(@Param("year") int year);

	@Query("SELECT r.regularDataId,r.allProductComparativeSheet.allProducts.allProductName.productName,r.quantity,r.inputDate,e.employeeName,a.unit.units,a.allProductName.type FROM RegularData r,EmpData e,AllProducts a WHERE r.users.usersId = e.users.usersId AND r.allProductComparativeSheet.allProducts.allProductsId = a.allProductsId AND a.allProductName.type = 'Fuel' AND (EXTRACT(YEAR FROM r.inputDate)) = :year ORDER BY r.inputDate DESC")
	List<Object[]> getFuelInfo(@Param("year") int year);

	@Query("SELECT r.quantity FROM RegularData r  LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE r.inputDate BETWEEN :pdate AND :today AND apn.productName = :productId ORDER BY r.quantity ASC")
	List<Float> getRegularQuantityBetween(@Param("productId") String productId, @Param("pdate") String pdate, @Param("today") String today);

	@Query("SELECT r.quantity,r.inputDate FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn WHERE apn.productName= :productName  AND (r.inputDate BETWEEN :pdate AND :today)")
	List<Object[]> findOneByGetQuantityInpdate(@Param("productName") String productName, @Param("pdate") String pdate, @Param("today") String today);

	@Query("SELECT DISTINCT (r.regularDataId),apn.productName,r.quantity,r.inputDate,e.employeeName,u.units,apn.type   FROM RegularData r,EmpData e   LEFT JOIN r.allProductComparativeSheet apc    LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  LEFT JOIN a.unit u WHERE apn.type='nhwp' AND r.users.usersId = e.users.usersId AND (EXTRACT(YEAR FROM r.inputDate)) = :date ORDER BY r.inputDate DESC")
	List<Object[]> findByNonHazordousWasteProcess(@Param("date") int date);

	@Query("SELECT DISTINCT (r.regularDataId),apn.productName,r.quantity,r.inputDate,e.employeeName,u.units,apn.type   FROM RegularData r,EmpData e   LEFT JOIN r.allProductComparativeSheet apc    LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  LEFT JOIN a.unit u WHERE apn.type='nhwpcf' AND r.users.usersId = e.users.usersId AND (EXTRACT(YEAR FROM r.inputDate)) = :date ORDER BY r.inputDate DESC")
	List<Object[]> findByNonHazordousWastePCF(@Param("date") int date);

	@Query("SELECT DISTINCT (r.regularDataId),apn.productName,r.quantity,r.inputDate,e.employeeName,u.units,apn.type   FROM RegularData r,EmpData e   LEFT JOIN r.allProductComparativeSheet apc    LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  LEFT JOIN a.unit u WHERE apn.type='hwp' AND r.users.usersId = e.users.usersId AND (EXTRACT(YEAR FROM r.inputDate)) = :date ORDER BY r.inputDate DESC")
	List<Object[]> findByHazordousWasteProcess(@Param("date") int date);

	@Query("SELECT DISTINCT (r.regularDataId),apn.productName,r.quantity,r.inputDate,e.employeeName,u.units,apn.type   FROM RegularData r,EmpData e   LEFT JOIN r.allProductComparativeSheet apc    LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  LEFT JOIN a.unit u WHERE apn.type='hwpcf' AND r.users.usersId = e.users.usersId AND (EXTRACT(YEAR FROM r.inputDate)) = :date ORDER BY r.inputDate DESC")
	List<Object[]> findByHazordousWastePCF(@Param("date") int date);

	@Query("SELECT DISTINCT (r.regularDataId),apn.productName,r.quantity,r.inputDate,e.employeeName,u.units,apn.type   FROM RegularData r,EmpData e   LEFT JOIN r.allProductComparativeSheet apc    LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  LEFT JOIN a.unit u WHERE apn.type='Bio-Medical' AND r.users.usersId = e.users.usersId AND (EXTRACT(YEAR FROM r.inputDate)) = :date ORDER BY r.inputDate DESC")
	List<Object[]> findByBioMedicalWasteDetails(@Param("date") int date);

	@Query("SELECT DISTINCT r.quantity,r.inputDate, u.units,apn.productName  FROM RegularData r  LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  LEFT JOIN a.unit u WHERE apn.productName=:productName AND  r.inputDate=:date")
	List<Object[]> findByQuantityDateUnit(@Param("productName") String productName, @Param("date") String date);

	@Query("SELECT SUM(r.quantity) FROM RegularData r  INNER JOIN r.allProductComparativeSheet apc  INNER JOIN apc.allProducts a INNER JOIN a.allProductName apn  WHERE apn.productName=:productName")
	public float getSumOfRegularDataQuantity(@Param("productName") String productName);

	@Query(" SELECT r.allProductComparativeSheet.allProductComparativeSheetId,a.users.usersId  FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE apn.productName= :productName ORDER BY r.allProductComparativeSheet.allProductComparativeSheetId DESC")
	public List<Object[]> getAllproductComparitiveId(@Param("productName") String productName, Pageable pageable);

	@Query("SELECT MIN(EXTRACT(YEAR FROM r.inputDate)) FROM RegularData r")
	public int regDataMinYear();

	@Query("SELECT COUNT(r.regularDataId) FROM RegularData r  LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE r.inputDate BETWEEN :pdate AND :today  AND apn.productName = :productName  AND r.quantity > :complianceQuantity ")
	public int getNonComplianceByProductName(@Param("productName") String productName, @Param("pdate") String pdate, @Param("today") String today, @Param("complianceQuantity") Float complianceQuantity);

	@Modifying
	@Transactional
	@Query("UPDATE RegularData r SET r.quantity = :quantity WHERE r.regularDataId= :rId")
	public int updateQuantity(@Param("quantity") float quantity, @Param("rId") int rId);

	// temp.. not in use
	@Query(value = "SELECT SUM(r.quantity) FROM regular_data r LEFT JOIN all_product_comparative_sheet apc on apc.id=r.all_product_comparative_id LEFT JOIN all_products a on a.id=apc.all_products_id LEFT JOIN all_product_name apn on apn.id=a.all_product_name_id WHERE apn.product_name=:productName AND r.input_date BETWEEN :startDate AND :endDate", nativeQuery = true)
	public float sumOfQuantityFromRegularDataBetween(@Param("productName") String productName, @Param("startDate") String startDate, @Param("endDate") String endDate);

	@Query("SELECT r.quantity FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn   WHERE apn.productName= :pName AND r.inputDate= :date")
	public List<Float> getQuantityByIdAndDate(@Param("pName") String pName, @Param("date") String date);

	@Query("SELECT COALESCE(SUM(r.quantity),0) FROM RegularData r  LEFT JOIN r.allProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE apn.productName= :pName AND r.inputDate BETWEEN :fdate AND :ldate")
	public Float getQuantityInBetweendates(@Param("pName") String pName, @Param("fdate") String fDate, @Param("ldate") String lDate);

	@Query("SELECT r.quantity,r.inputDate FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a  LEFT JOIN a.allProductName apn   WHERE apn.productName = :productName   AND (r.inputDate BETWEEN :pdate AND :today)   ORDER BY r.quantity DESC")
	List<Object[]> getQuantityInpdate(@Param("productName") String productName, @Param("pdate") String pdate, @Param("today") String today);

	@Query("SELECT AVG(r.quantity) FROM RegularData r  LEFT JOIN r.allProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE apn.productName = :productName AND (r.inputDate BETWEEN :pdate AND :today)")
	public Float getAvgByDateProductName(@Param("productName") String productName, @Param("pdate") String pdate, @Param("today") String today);

	@Query("SELECT DISTINCT r.allProductComparativeSheet.allProductComparativeSheetId FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName an WHERE an.type='hwp' OR an.type='hwpcf' AND an.status='Active'")
	List<Integer> getProductNameIdFromRegularData();

	@Query("SELECT DISTINCT r.allProductComparativeSheet.allProductComparativeSheetId FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName an WHERE an.type='hwp' OR an.type='hwpcf' AND an.status='Active' AND r.inputDate BETWEEN :FROM AND :to")
	List<Integer> getProductNameIdFromRegularDataByYear(@Param("FROM") String FROM, @Param("to") String to);

	@Query("SELECT COALESCE(SUM(r.quantity),0) FROM RegularData r  LEFT JOIN r.allProductComparativeSheet apc   LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE apn.productName= :pName  AND (EXTRACT(MONTH FROM r.inputDate)) = :month AND (EXTRACT(YEAR FROM r.inputDate)) = :year")
	public Float getQuantityByPNameAndMonthYear(@Param("pName") String pName, @Param("month") int month, @Param("year") int year);

	@Query("SELECT SUM(r.quantity) FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc  LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName apn  WHERE apn.productName= :pName AND (EXTRACT(YEAR FROM DATE_FORMAT(date(r.inputDate), '%Y-%m-%d')))= :year")
	public Float getSumOfQuantityByPNameAndYear(@Param("pName") String pName, @Param("year") int year);

	@Query("SELECT COALESCE(SUM(r.quantity),0) FROM RegularData r WHERE r.allProductComparativeSheet.allProductComparativeSheetId= :apcId")
	public float getSumQuantityOfRegularDataByAPCId(@Param("apcId") int apcId);

	@Query("SELECT COALESCE(SUM(r.quantity),0) FROM RegularData r WHERE r.allProductComparativeSheet.allProductComparativeSheetId= :apcId AND r.inputDate BETWEEN :fromDate AND :toDate")
	public float getSumQuantityOfRegularDataByAPCIdAndDate(@Param("apcId") int apcId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("SELECT AVG(r.quantity) FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName an WHERE an.productName=:pName AND r.inputDate BETWEEN :fDate AND :lDate")
	public Float getAverageQuantityByPNameAndBetweenDates(@Param("pName") String pName, @Param("fDate") String fDate, @Param("lDate") String lDate);

	@Query("SELECT  AVG(r.quantity) FROM RegularData r  INNER JOIN r.allProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.allProductName an WHERE an.productName= :pName AND EXTRACT(MONTH FROM r.inputDate)= :month AND EXTRACT(YEAR FROM r.inputDate)= :year")
	public Float getAverageQuantityByPNameMonthYear(@Param("pName") String pName, @Param("month") int month, @Param("year") int year);

	@Query("SELECT AVG(r.quantity) FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName an WHERE an.productName= :pName AND EXTRACT(YEAR FROM r.inputDate)= :year")
	public Float getAverageQuantityByPNameAndYear(@Param("pName") String pName, @Param("year") int year);

	@Query("SELECT SUM(r.quantity) FROM RegularData r INNER JOIN r.allProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.allProductName an WHERE an.productName= :productName AND r.inputDate BETWEEN :fromDate AND :toDate")
	public float getSumQuantityofRegularDataByDate(@Param("productName") String productName, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("SELECT rd FROM RegularData rd LEFT JOIN rd.allProductComparativeSheet cs LEFT JOIN cs.allProducts p LEFT JOIN p.allProductName pn WHERE pn.type = :type AND rd.inputDate= :todaysDate")
	List<RegularData> findProductListByProductNameAndTodaysDate(@Param("type") String type, @Param("todaysDate") String todaysDate);

	@Query("SELECT  AVG(r.quantity) FROM RegularData r  INNER JOIN r.allProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.allProductName an WHERE an.productName= :pName AND EXTRACT(MONTH FROM r.inputDate)= :month AND EXTRACT(YEAR FROM r.inputDate)= :year")
	public Float getAverageQuantityByPNameMonthYearForAhp(@Param("pName") String pName, @Param("month") int month, @Param("year") int year);

	@Query("SELECT r FROM RegularData r LEFT JOIN r.allProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName an WHERE r.inputDate =:date AND an.type=:productType ORDER BY  a.allProductName ASC")
	List<RegularData> getProductDetailsData(@Param("date") String date, @Param("productType") String productType);

	@Query("SELECT SUM(r.quantity) FROM RegularData r INNER JOIN r.allProductComparativeSheet apc  INNER JOIN apc.allProducts a  INNER JOIN a.consent c INNER JOIN a. allProductName apn WHERE apn.productName= :productName AND r.inputDate BETWEEN :datefrom AND :dateto")
	Float findRegulardataSumBYYear(@Param("productName") String productName, @Param("datefrom") String datefrom, @Param("dateto") String dateto);

	RegularData findByRegularDataId(int regularDataId);

	// @Query("SELECT DISTINCT NEW Consent(c.consentId,c.consNo,c.validUpto,c.grossCi,c.issueDate) FROM Consent c WHERE c.consType = 'Consent to Operate' AND c.consStatus != 'EXPIRED' AND c.issueDate <=:dateTo ORDER BY c.consentId DESC")
	// List<Consent> getConsentDataByIssueDate1(@Param("dateTo") String dateTo, Pageable pageable);
	// List<RegularData> getRegularData(@Param("productId") int productId, @Param("today") String today, Pageable pageble);

	@Query("SELECT rd.regularDataId  FROM RegularData rd WHERE rd.inputDate = :today  ORDER BY rd.regularDataId DESC ")
	List<RegularData> getCheckRegularData(@Param("today") String today, Pageable pageble);

	@Query("SELECT rd.inputDate FROM RegularData rd")
	List<String> getRegDate();

	@Query("SELECT an.productName FROM RegularData r INNER JOIN r.allProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.allProductName an")
	List<String> getRegPName();

	@Query("SELECT r.regularDataId FROM RegularData r INNER JOIN r.allProductComparativeSheet apc INNER JOIN apc.allProducts a INNER JOIN a.allProductName an Where an.productName=:productName and r.inputDate = :checkDate")
	public List<RegularData> checkDatewithProduct(@Param("productName") String productName, @Param("checkDate") String checkDate);

}
