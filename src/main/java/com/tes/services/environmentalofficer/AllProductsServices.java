package com.tes.services.environmentalofficer;

import java.util.List;
import com.tes.model.AllProducts;

public interface AllProductsServices
{

	public AllProducts save(AllProducts allProducts);

	public List<AllProducts> findByProductType(String productType, int consentId);

	public List<AllProducts> findByProductType(String productType, int consentId, String productName);

	Float quantitySum(int productId);

	List<String> productNameList(String type, int consentId);

	List<String> productNameExtended(String productType, String today);

	Float sumQtyWithconsStatus(String productName);

	public List<AllProducts> findByConsTypeAndConsId(String type, int consentNo);

	public List<AllProducts> findByTesting();

	public int updateProductById(int productId, float quantity, int unitId);

	public List<String> findGetunitByProductType(String productType);

	List<Integer> getUnitsFromAllProducts(String productName);

	List<Integer> AllProductIdByProductName(String productName);

	public List<String> findOneByProductTypeUnits(String productType, String units, String esrConsentDate);

	public Float findOneByGetConsentData(String productName, String consentDate);

	public int getNumberFromRegularData(String productName, String pdate, String today);

	public int getNoWorkDays(int companyId);

	public List<Object[]> findOneByGetProductIdUnits(String productType, String pdate, String today);

	public List<Object[]> getProductByType(String productType);

	public float getSumOfQuantity(String today, String productName);

	Float getQuantityByProductName(String pName);

	/// mmmm
	public List<String> findOnlyUnit(String productType);

}
