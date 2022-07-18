package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.tes.model.AllProductName;
import com.tes.repository.environmentalofficer.AllProductNameRepository;
import com.tes.services.environmentalofficer.AllProductNameServices;

@Service
public class AllProductNameServicesImpl implements AllProductNameServices
{

	@Autowired
	AllProductNameRepository allProductNameRepository;

	@CacheEvict(value = "allProductName", allEntries = true)
	@CachePut(value = "allProductName", key = "#allProductName", condition = "#allProductName!=null")
	@Override
	public void save(AllProductName allProductName)
	{
		allProductNameRepository.save(allProductName);
	}

	@Cacheable(value = "allProductName", key = "{#allProductNameId, #productName}", unless = "#result==null")
	@Override
	public int updateProductNameById(int allProductNameId, String productName)
	{
		return allProductNameRepository.updateProductNameById(allProductNameId, productName);
	}

	// @CacheEvict(value = "allProductName", allEntries = true)
	// @CachePut(value = "allProductName", key = "{#productNameId, #status}", unless = "#result=null")
	@Override
	public int updateStatusByAllProductNameId(int productNameId, String status)
	{
		return allProductNameRepository.updateStatusByAllProductNameId(productNameId, status);
	}

	@Override
	public String getProductNameForModal(String date, String type)
	{
		return allProductNameRepository.getProductNameForModal(date, type);
	}

	@Cacheable(value = "allProductName", key = "{#productType, #date}", unless = "#result==null")
	@Override
	public List<String> getProductName(String productType, String date)
	{
		return allProductNameRepository.getProductName(productType, date);
	}

	@Cacheable(value = "allProductName", key = "{#productType}", unless = "#result==null")
	@Override
	public List<AllProductName> getProductsByProductType(String productType)
	{
		return allProductNameRepository.getProductsByProductType(productType);
	}

	@Cacheable(value = "allProductName", key = "{#consentId, #productType}", unless = "#result==null")
	@Override
	public List<AllProductName> getProductsByProductType(int consentId, String productType)
	{
		return allProductNameRepository.getProductsByProductType(consentId, productType);
	}

	@Cacheable(value = "allProductName", key = "{#regulardataProductId}", unless = "#result==null")
	@Override
	public List<String> getProductNameFromRegularData(int regulardataProductId)
	{
		return allProductNameRepository.getProductNameFromRegularData(regulardataProductId);
	}

	@Cacheable(value = "allProductName", key = "{#regulardataProductId}", unless = "#result==null")
	@Override
	public List<Object[]> getProductNameUnitsByProductType()
	{
		return allProductNameRepository.getProductNameUnitsByProductType();
	}

	@Cacheable(value = "allProductName", key = "{#date, #type}", unless = "#result==null")
	@Override
	public List<String> getProductNameByTypeAndDate(String date, String type)
	{
		return allProductNameRepository.getProductNameByTypeAndDate(date, type);
	}

	@Cacheable(value = "allProductName", key = "{#date, #type, #unit}", unless = "#result==null")
	@Override
	public List<String> getProductNameByTypeDateAndUnit(String date, String type, String unit)
	{
		return allProductNameRepository.getProductNameByTypeDateAndUnit(date, type, unit);
	}

	@Cacheable(value = "allProductName", key = "{#productName}", unless = "#result==null")
	@Override
	public int prouctNameIdByProductName(String productName)
	{
		return allProductNameRepository.prouctNameIdByProductName(productName);
	}

	@Cacheable(value = "allProductName", key = "{#esrConsentDate}", unless = "#result==null")
	@Override
	public List<String> getProductNameByConsentToEstablish(String esrConsentDate)
	{
		return allProductNameRepository.getProductNameByConsentToEstablish(esrConsentDate);
	}
}
