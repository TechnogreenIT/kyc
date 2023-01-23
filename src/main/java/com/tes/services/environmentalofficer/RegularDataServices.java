package com.tes.services.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.RegularData;

public interface RegularDataServices
{

	RegularData save(RegularData regularData);

	List<RegularData> getRegularData(int productId, String today, Pageable pageble);

	Float qtySumRegDataWithNextYear(int productId, int currentYear, String cm1, String cm2);

	Float qtySumRegDataWithPreviousYear(int productId, int currentYear1, String cm1, String cm2);

	Float findRegulardataSum(String productName, String datefrom, String dateto, int month);

	List<Integer> rdDataId();

	List<Integer> getdate2regulardata();

	List<Object[]> getProductDetails(Integer integer);

	List<Object[]> getByProductDetails(int year);

	List<Object[]> getRawMaterialDetails(int year);

	List<Object[]> getFuelInfo(int year);

	Float quantityByProductNameDate(String productName, String date);

	List<Float> getRegularQuantityBetween(String productName, String pdate, String today);

	List<Object[]> findByNonHazordousWasteProcess(int date);

	List<Object[]> findByNonHazordousWastePCF(int date);

	List<Object[]> findByHazordousWasteProcess(int date);

	List<Object[]> findByHazordousWastePCF(int date);

	List<Object[]> findByBioMedicalWasteDetails(int date);

	public List<Object[]> findOneByGetQuantityInpdate(String productName, String pdate, String today);

	List<Object[]> findByQuantityDateUnit(String productName, String date);

	public float getSumOfRegularDataQuantity(String productName);

	public List<Object[]> getAllproductComparitiveId(String productName, Pageable pageable);

	public int regDataMinYear();

	int getNonComplianceByProductName(String productName, String pdate, String today, Float complianceQuantity);

	public int updateQuantity(float quantity, int id);

	public float sumOfQuantityFromRegularDataBetween(String productName, String startDate, String endDate);

	public List<Float> getQuantityByIdAndDate(String pName, String date);

	public Float getQuantityInBetweendates(String pName, String fDate, String lDate);

	public List<Object[]> getQuantityInpdate(String productName, String pdate, String today);

	public Float getAvgByDateProductName(String productName, String pdate, String today);

	public Float getQuantityByPNameAndMonthYear(String pName, int month, int year);

	public Float getSumOfQuantityByPNameAndYear(String pName, int year);

	List<Integer> getProductNameIdFromRegularData();

	List<Integer> getProductNameIdFromRegularDataByYear(String from, String to);

	public float getSumQuantityOfRegularDataByAPCId(int apcId);

	public float getSumQuantityOfRegularDataByAPCIdAndDate(int apcId, String fromDate, String toDate);

	public Float getAverageQuantityByPNameAndBetweenDates(String pName, String fDate, String lDate);

	public Float getAverageQuantityByPNameMonthYear(String pName, int month, int year);

	public Float getAverageQuantityByPNameAndYear(String pName, int year);

	public float getSumQuantityofRegularDataByDate(String productName, String fromDate, String toDate);

	List<RegularData> findProductListByProductNameAndTodaysDate(String type, String todaysDate);

	public Float getAverageQuantityByPNameMonthYearForAhp(String productName, int month, int year);

	List<RegularData> getProductDetailsData(String date, String productType);

	Float findRegulardataSumBYYear(String productName, String datefrom, String dateto);

	List<RegularData> getCheckRegularData(String today, Pageable pageble);

	// List<String> getRegDate();

	List<String> getRegPName();

	List<String> getRegDate();

	public List<RegularData> checkDatewithProduct(String productName, String checkDate);

	// List<RegularData> getregData(String productName, String today);

	// List<Object[]> getRegularData1(int i, String createDate, Pageable pageable);

	// Integer checkregDataPresent();
}
