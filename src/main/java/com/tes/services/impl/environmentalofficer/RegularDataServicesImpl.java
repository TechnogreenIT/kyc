package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.RegularData;
import com.tes.repository.environmentalofficer.RegularDataRepository;
import com.tes.services.environmentalofficer.RegularDataServices;

@Service
public class RegularDataServicesImpl implements RegularDataServices
{

	@Autowired
	RegularDataRepository regularDataRepository;

	@CacheEvict(value = {"regularData", "regularData1", "regularData2"}, allEntries = true)
	@CachePut(value = "regularData", key = "#regularData", condition = "#regularData!=null")
	@Override
	public RegularData save(RegularData regularData)
	{
		return regularDataRepository.save(regularData);

	}

	@Cacheable(value = "regularData", key = "{#productId, #today, #pageble}", unless = "#result==null")
	@Override
	public List<RegularData> getRegularData(int productId, String today, Pageable pageble)
	{
		return regularDataRepository.getRegularData(productId, today, pageble);
	}

	@Cacheable(value = "regularData", key = "{#productId, #currentYear, #cm1, #cm2}", condition = "{#productId!=null, #currentYear!=null, #cm1!=null, #cm2!=null}")
	@Override
	public Float qtySumRegDataWithNextYear(int productId, int currentYear, String cm1, String cm2)
	{
		return regularDataRepository.qtySumRegDataWithNextYear(productId, currentYear, cm1, cm2);
	}

	@Cacheable(value = "regularData1", key = "{#productId, #currentYear1, #cm1, #cm2}", condition = "{#productId!=null, #currentYear1!=null, #cm1!=null, #cm2!=null}")
	@Override
	public Float qtySumRegDataWithPreviousYear(int productId, int currentYear1, String cm1, String cm2)
	{
		return regularDataRepository.qtySumRegDataWithPreviousYear(productId, currentYear1, cm1, cm2);
	}

	@Override
	public List<Integer> rdDataId()
	{
		return regularDataRepository.rdDataId();
	}

	@Cacheable(value = "regularData", key = "{#productName, #datefrom, #dateto, #month}", unless = "#result==null")
	@Override
	public Float findRegulardataSum(String productName, String datefrom, String dateto, int month)
	{
		return regularDataRepository.findRegulardataSum(productName, datefrom, dateto, month);
	}

	@Override
	public List<Integer> getdate2regulardata()
	{
		return regularDataRepository.getdate2regulardata();
	}

	@Override
	public List<Object[]> getProductDetails(Integer inputDate)
	{
		return regularDataRepository.getProductDetails(inputDate);
	}

	@Override
	public List<Object[]> getByProductDetails(int year)
	{
		return regularDataRepository.getByProductDetails(year);
	}

	@Override
	public List<Object[]> getRawMaterialDetails(int year)
	{
		return regularDataRepository.getRawMaterialDetails(year);
	}

	@Override
	public List<Object[]> getFuelInfo(int year)
	{
		return regularDataRepository.getFuelInfo(year);
	}

	@Cacheable(value = "regularData", key = "{#productName, #date}", unless = "#result==null")
	@Override
	public Float quantityByProductNameDate(String productName, String date)
	{
		return regularDataRepository.quantityByProductNameDate(productName, date);
	}

	@Cacheable(value = "regularData", key = "{#productName, #pdate, #today}", unless = "#result==null")
	@Override
	public List<Float> getRegularQuantityBetween(String productName, String pdate, String today)
	{
		return regularDataRepository.getRegularQuantityBetween(productName, pdate, today);
	}

	@Override
	public List<Object[]> findByNonHazordousWasteProcess(int date)
	{
		return regularDataRepository.findByNonHazordousWasteProcess(date);
	}

	@Override
	public List<Object[]> findByNonHazordousWastePCF(int date)
	{
		return regularDataRepository.findByNonHazordousWastePCF(date);
	}

	@Override
	public List<Object[]> findByHazordousWasteProcess(int date)
	{
		return regularDataRepository.findByHazordousWasteProcess(date);
	}

	@Override
	public List<Object[]> findByHazordousWastePCF(int date)
	{
		return regularDataRepository.findByHazordousWastePCF(date);
	}

	@Override
	public List<Object[]> findByBioMedicalWasteDetails(int date)
	{
		return regularDataRepository.findByBioMedicalWasteDetails(date);
	}

	@Override
	public List<Object[]> findOneByGetQuantityInpdate(String productName, String pdate, String today)
	{
		return regularDataRepository.findOneByGetQuantityInpdate(productName, pdate, today);
	}

	@Override
	public List<Object[]> findByQuantityDateUnit(String productName, String date)
	{
		return regularDataRepository.findByQuantityDateUnit(productName, date);
	}

	@Cacheable(value = "regularData", key = "#productName", unless = "#result==null")
	@Override
	public float getSumOfRegularDataQuantity(String productName)
	{
		return regularDataRepository.getSumOfRegularDataQuantity(productName);
	}

	@Override
	public List<Object[]> getAllproductComparitiveId(String productName, Pageable pageble)
	{
		return regularDataRepository.getAllproductComparitiveId(productName, pageble);
	}

	@Cacheable(value = "regularData", unless = "#result==null")
	@Override
	public int regDataMinYear()
	{
		return regularDataRepository.regDataMinYear();
	}

	@Cacheable(value = "regularData", key = "{#productName, #pdate, #today, #complianceQuantity}", unless = "#result==null")
	@Override
	public int getNonComplianceByProductName(String productName, String pdate, String today, Float complianceQuantity)
	{
		return regularDataRepository.getNonComplianceByProductName(productName, pdate, today, complianceQuantity);
	}

	@Override
	public int updateQuantity(float quantity, int id)
	{
		return regularDataRepository.updateQuantity(quantity, id);
	}

	@Override
	public float sumOfQuantityFromRegularDataBetween(String productName, String startDate, String endDate)
	{
		return regularDataRepository.sumOfQuantityFromRegularDataBetween(productName, startDate, endDate);
	}

	@Cacheable(value = "regularData", key = "{#productName, #pdate, #today}", unless = "#result==null")
	@Override
	public List<Object[]> getQuantityInpdate(String productName, String pdate, String today)
	{
		return regularDataRepository.getQuantityInpdate(productName, pdate, today);
	}

	@Cacheable(value = "regularData", key = "{#pName, #fDate, #lDate}", condition = "{#fDate!=null, #lDate!=null}")
	@Override
	public Float getQuantityInBetweendates(String pName, String fDate, String lDate)
	{
		return regularDataRepository.getQuantityInBetweendates(pName, fDate, lDate);
	}

	@Cacheable(value = "regularData2", key = "{#productName, #pdate, #today}", unless = "#result==null")
	@Override
	public Float getAvgByDateProductName(String productName, String pdate, String today)
	{
		return regularDataRepository.getAvgByDateProductName(productName, pdate, today);
	}

	@Override
	public List<Float> getQuantityByIdAndDate(String pName, String date)
	{
		return regularDataRepository.getQuantityByIdAndDate(pName, date);
	}

	@Cacheable(value = "regularData", key = "{#pName, #month, #year}", unless = "#result==null")
	@Override
	public Float getQuantityByPNameAndMonthYear(String pName, int month, int year)
	{
		return regularDataRepository.getQuantityByPNameAndMonthYear(pName, month, year);
	}

	@Cacheable(value = "regularData", key = "{#pName, #year}", condition = "#result==null")
	@Override
	public Float getSumOfQuantityByPNameAndYear(String pName, int year)
	{
		return regularDataRepository.getSumOfQuantityByPNameAndYear(pName, year);
	}

	@Override
	public List<Integer> getProductNameIdFromRegularData()
	{
		return regularDataRepository.getProductNameIdFromRegularData();
	}

	@Override
	public List<Integer> getProductNameIdFromRegularDataByYear(String from, String to)
	{
		return regularDataRepository.getProductNameIdFromRegularDataByYear(from, to);
	}

	@Override
	public float getSumQuantityOfRegularDataByAPCId(int apcId)
	{
		return regularDataRepository.getSumQuantityOfRegularDataByAPCId(apcId);
	}

	@Cacheable(value = "regularData", key = "{#apcId, #fromDate, #toDate}", unless = "#result==null")
	@Override
	public float getSumQuantityOfRegularDataByAPCIdAndDate(int apcId, String fromDate, String toDate)
	{
		return regularDataRepository.getSumQuantityOfRegularDataByAPCIdAndDate(apcId, fromDate, toDate);
	}

	@Cacheable(value = "regularData", key = "{#pName, #fDate, #lDate}", unless = "#result==null")
	@Override
	public Float getAverageQuantityByPNameAndBetweenDates(String pName, String fDate, String lDate)
	{
		return regularDataRepository.getAverageQuantityByPNameAndBetweenDates(pName, fDate, lDate);
	}

	@Cacheable(value = "regularData", key = "{#pName, #month, #year}", unless = "#result==null")
	@Override
	public Float getAverageQuantityByPNameMonthYear(String pName, int month, int year)
	{
		return regularDataRepository.getAverageQuantityByPNameMonthYear(pName, month, year);
	}

	@Cacheable(value = "regularData", key = "{#pName, #year}", unless = "#result==null")
	@Override
	public Float getAverageQuantityByPNameAndYear(String pName, int year)
	{
		return regularDataRepository.getAverageQuantityByPNameAndYear(pName, year);
	}

	@Cacheable(value = "regularData", key = "{#productName, #fromDate, #toDate }", unless = "#result==null")
	@Override
	public float getSumQuantityofRegularDataByDate(String productName, String fromDate, String toDate)
	{
		return regularDataRepository.getSumQuantityofRegularDataByDate(productName, fromDate, toDate);
	}

	@Override
	public List<RegularData> findProductListByProductNameAndTodaysDate(String type, String todaysDate)
	{
		return regularDataRepository.findProductListByProductNameAndTodaysDate(type, todaysDate);
	}

	@Cacheable(value = "regularData", key = "{#productName, #month, #year }", unless = "#result==null")
	@Override
	public Float getAverageQuantityByPNameMonthYearForAhp(String productName, int month, int year)
	{
		return regularDataRepository.getAverageQuantityByPNameMonthYearForAhp(productName, month, year);
	}

	@Cacheable(value = "regularData", key = "{#date, #productType}", unless = "#result==null")
	@Override
	public List<RegularData> getProductDetailsData(String date, String productType)
	{

		return regularDataRepository.getProductDetailsData(date, productType);
	}

	@Override
	public Float findRegulardataSumBYYear(String productName, String datefrom, String dateto)
	{
		return regularDataRepository.findRegulardataSumBYYear(productName, datefrom, dateto);
	}

	@Override
	public List<RegularData> getCheckRegularData(String today, Pageable pageble)
	{
		// TODO Auto-generated method stub
		return regularDataRepository.getCheckRegularData(today, pageble);
	}

}
