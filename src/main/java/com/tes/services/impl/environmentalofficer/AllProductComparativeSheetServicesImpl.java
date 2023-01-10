package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.AllProductComparativeSheet;
import com.tes.repository.environmentalofficer.AllProductComparativeSheetRepository;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;

@Service
public class AllProductComparativeSheetServicesImpl implements AllProductComparativeSheetServices
{

	@Autowired
	AllProductComparativeSheetRepository allProductComparativeSheetRepository;

	@Override
	public Integer findComparativeSheetIdByAllProductId(int productId)
	{
		return allProductComparativeSheetRepository.findComparativeSheetIdByAllProductId(productId);
	}

	@CacheEvict(value = {"allProductComparativeSheet", "allProductComparativeSheet1"}, allEntries = true)
	@CachePut(value = "allProductComparativeSheet", key = "#allProductComparativeSheet", condition = "#allProductComparativeSheet!=null")
	@Override
	public AllProductComparativeSheet save(AllProductComparativeSheet allProductComparativeSheet)
	{
		return allProductComparativeSheetRepository.save(allProductComparativeSheet);
	}

	@Cacheable(value = "allProductComparativeSheet", key = "#allProductNameId", unless = "#result==null")
	@Override
	public float sumOfQuantityByCtoO(int allProductNameId)
	{
		return allProductComparativeSheetRepository.sumOfQuantityByCtoO(allProductNameId);
	}

	@Cacheable(value = "allProductComparativeSheet1", unless = "#result==null")
	@Override
	public float findByQuantityByCtoE(int allProductNameId)
	{
		return allProductComparativeSheetRepository.findByQuantityByCtoE(allProductNameId);
	}

	@Override
	public int setInactiveByAllProductNameId(int allProductNameId)
	{
		return allProductComparativeSheetRepository.setInactiveByAllProductNameId(allProductNameId);
	}

	@Override
	public List<Object[]> getAllProductComparativeSheet(String productType, String esrConsentDate)
	{
		return allProductComparativeSheetRepository.getAllProductComparativeSheet(productType, esrConsentDate);
	}

	@Override
	public List<Object[]> getAllProductComparativeSheetSolidWaste(String esrConsentDate)
	{
		return allProductComparativeSheetRepository.getAllProductComparativeSheetSolidWaste(esrConsentDate);
	}

	@Cacheable(value = "allProductComparativeSheet", key = "#productName", unless = "#result==null")
	@Override
	public List<Integer> getAllProductComparativeSheetIdByProductName(String productName, Pageable pageable)
	{
		return allProductComparativeSheetRepository.getAllProductComparativeSheetIdByProductName(productName, pageable);
	}

	@Cacheable(value = "allProductComparativeSheet", key = "{#selectedDate, #type}", unless = "#result==null")
	@Override
	public List<AllProductComparativeSheet> getAllProductsDetailsByYear(String selectedDate, String type)
	{
		return allProductComparativeSheetRepository.getAllProductsDetailsByYear(selectedDate, type);
	}

	@Cacheable(value = "allProductComparativeSheet", key = "{#productType, #esrDate}", unless = "#result==null")
	@Override
	public List<String> getDistinctProductNameByEsrYear(String productType, String esrDate)
	{
		return allProductComparativeSheetRepository.getDistinctProductNameByEsrYear(productType, esrDate);
	}

	@Cacheable(value = "allProductComparativeSheet", key = "{#type}", unless = "#result==null")
	@Override
	public List<AllProductComparativeSheet> getAllProductByType(String type)
	{
		return allProductComparativeSheetRepository.getAllProductByType(type);
	}

	// @Cacheable(value = "allProductComparativeSheet", unless = "#result==null")
	@Override
	public int findComparativeSheetIdByPName(String pName)
	{
		return allProductComparativeSheetRepository.findComparativeSheetIdByPName(pName);
	}

	// @Cacheable(value = "allProductComparativeSheet", key = "{#productType, #todayDate}", unless = "#result==null")
	@Override
	public List<Object[]> getAllProductComparativeSheetForDailyInput(String productType, String todayDate)
	{
		return allProductComparativeSheetRepository.getAllProductComparativeSheetForDailyInput(productType, todayDate);
	}

	@Cacheable(value = "allProductComparativeSheet", key = "{#productType, #today}", unless = "#result==null")
	@Override
	public List<Object[]> ahpHazardous(String productType, String today)
	{
		return allProductComparativeSheetRepository.ahpHazardous(productType, today);
	}

	@Cacheable(value = "allProductComparativeSheet", unless = "#result==null")
	@Override
	public int countType()
	{
		return allProductComparativeSheetRepository.countType();
	}

	// @Override
	// public String getComparativeSheetByPName(String pName)
	// {
	// // TODO Auto-generated method stub
	// return allProductComparativeSheetRepository.getComparativeSheetByPName(pName);
	// }

}
