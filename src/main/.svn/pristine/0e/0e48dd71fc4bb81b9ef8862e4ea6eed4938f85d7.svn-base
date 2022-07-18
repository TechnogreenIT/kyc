package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.AllProductName;

public interface AllProductNameServices
{

	void save(AllProductName allProductName);

	int updateProductNameById(int allProductNameId, String productName);

	int updateStatusByAllProductNameId(int productNameId, String status);

	public String getProductNameForModal(String date, String type);

	public List<String> getProductName(String productType, String date);

	public List<AllProductName> getProductsByProductType(String productType);

	public List<AllProductName> getProductsByProductType(int consentId, String productType);

	List<String> getProductNameFromRegularData(int regulardataProductId);

	List<Object[]> getProductNameUnitsByProductType();

	List<String> getProductNameByTypeAndDate(String date, String type);

	List<String> getProductNameByTypeDateAndUnit(String date, String type, String unit);

	int prouctNameIdByProductName(String productName);

	public List<String> getProductNameByConsentToEstablish(String esrConsentDate);
}
