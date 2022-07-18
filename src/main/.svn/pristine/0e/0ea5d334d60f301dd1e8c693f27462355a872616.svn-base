package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.tes.model.AllProducts;
import com.tes.repository.environmentalofficer.AllProductsRepository;
import com.tes.services.environmentalofficer.AllProductsServices;

@Service
public class AllProductsServicesImpl implements AllProductsServices
{

	@Autowired
	AllProductsRepository allProductsRepository;

	@CacheEvict(value = "allProducts", allEntries = true)
	@CachePut(value = "allProducts", key = "#allProducts", condition = "#allProducts!=null")
	@Override
	public AllProducts save(AllProducts allProducts)
	{
		return allProductsRepository.save(allProducts);
	}

	@Cacheable(value = "allProducts", key = "{#productType, #consentId}", unless = "#result==null")
	@Override
	public List<AllProducts> findByProductType(String productType, int consentId)
	{
		return allProductsRepository.findByProductType(productType, consentId);
	}

	@Cacheable(value = "allProducts", key = "{#productType, #consentId, #productName}", unless = "#result==null")
	@Override
	public List<AllProducts> findByProductType(String productType, int consentId, String productName)
	{
		return allProductsRepository.findByProductType(productType, consentId, productName);
	}

	@Override
	public Float quantitySum(int productId)
	{
		return allProductsRepository.quantitySum(productId);
	}

	@Override
	public List<String> productNameList(String type, int consentId)
	{
		return allProductsRepository.productNameList(type, consentId);
	}

	@Override
	public List<String> productNameExtended(String productType, String today)
	{
		return allProductsRepository.productNameExtended(productType, today);
	}

	@Override
	public Float sumQtyWithconsStatus(String productName)
	{
		return allProductsRepository.sumQtyWithconsStatus(productName);
	}

	// @Cacheable(value = "allProducts", key = "{#type, #consentNo}", unless = "#result==null")
	@Override
	public List<AllProducts> findByConsTypeAndConsId(String type, int consentNo)
	{
		return allProductsRepository.findByConsTypeAndConsId(type, consentNo);
	}

	@Override
	public List<AllProducts> findByTesting()
	{
		return allProductsRepository.findByTesting();
	}

	@CacheEvict(value = "allProducts", allEntries = true)
	@CachePut(value = "allProducts", key = "{#productId, #quantity, #unitId}", unless = "#result==null")
	@Override
	public int updateProductById(int productId, float quantity, int unitId)
	{
		return allProductsRepository.updateProductById(productId, quantity, unitId);
	}

	@Cacheable(value = "allProducts", key = "{#productType}", unless = "#result==null")
	@Override
	public List<String> findGetunitByProductType(String productType)
	{
		return allProductsRepository.findGetunitByProductType(productType);
	}

	@Override
	public List<Integer> getUnitsFromAllProducts(String productName)
	{
		return allProductsRepository.getUnitsFromAllProducts(productName);
	}

	@Override
	public List<Integer> AllProductIdByProductName(String productName)
	{
		return allProductsRepository.AllProductIdByProductName(productName);
	}

	@Cacheable(value = "allProducts", key = "{#productType, #units, #esrConsentDate}", unless = "#result==null")
	@Override
	public List<String> findOneByProductTypeUnits(String productType, String units, String esrConsentDate)
	{
		return allProductsRepository.findOneByProductTypeUnits(productType, units, esrConsentDate);
	}

	@Cacheable(value = "allProducts", key = "{#productName, #consentDate}", unless = "#result==null")
	@Override
	public Float findOneByGetConsentData(String productName, String consentDate)
	{
		return allProductsRepository.findOneByGetConsentData(productName, consentDate);
	}

	@Cacheable(value = "allProducts", key = "{#productName, #pdate, #today}", unless = "#result==null")
	@Override
	public int getNumberFromRegularData(String productName, String pdate, String today)
	{
		return allProductsRepository.getNumberFromRegularData(productName, pdate, today);
	}

	@Cacheable(value = "allProducts", key = "{#companyId}", unless = "#result==null")
	@Override
	public int getNoWorkDays(int companyId)
	{
		return allProductsRepository.getNoWorkDays(companyId);
	}

	@Cacheable(value = "allProducts", key = "{#productType, #pdate, #today}", unless = "#result==null")
	@Override
	public List<Object[]> findOneByGetProductIdUnits(String productType, String pdate, String today)
	{
		return allProductsRepository.findOneByGetProductIdUnits(productType, pdate, today);
	}

	@Override
	public float getSumOfQuantity(String today, String productName)
	{
		return allProductsRepository.getSumOfQuantity(today, productName);
	}

	@Cacheable(value = "allProducts", key = "{#productType}", unless = "#result==null")
	@Override
	public List<Object[]> getProductByType(String productType)
	{
		return allProductsRepository.getProductByType(productType);
	}

	@Cacheable(value = "allProducts", key = "{#pName}", unless = "#result==null")
	@Override
	public Float getQuantityByProductName(String pName)
	{
		return allProductsRepository.getQuantityByProductName(pName);
	}
}
