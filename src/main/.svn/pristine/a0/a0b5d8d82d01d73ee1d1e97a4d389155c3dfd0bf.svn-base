package com.tes.services.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.AllProductComparativeSheet;

public interface AllProductComparativeSheetServices
{

	public Integer findComparativeSheetIdByAllProductId(int productId);

	public AllProductComparativeSheet save(AllProductComparativeSheet objAllProductComparativeSheet);

	public List<Object[]> getAllProductComparativeSheetForDailyInput(String productType, String todayDate);

	public float sumOfQuantityByCtoO(int allProductNameId);

	public float findByQuantityByCtoE(int allProductNameId);

	public int setInactiveByAllProductNameId(int allProductNameId);

	public List<Object[]> getAllProductComparativeSheet(String productType, String esrConsentDate);

	public List<Object[]> getAllProductComparativeSheetSolidWaste(String esrConsentDate);

	public List<Integer> getAllProductComparativeSheetIdByProductName(String productName, Pageable pageable);

	public List<AllProductComparativeSheet> getAllProductsDetailsByYear(String selectedDate, String type);

	public List<String> getDistinctProductNameByEsrYear(String productType, String esrDate);

	public List<AllProductComparativeSheet> getAllProductByType(String type);

	public int findComparativeSheetIdByPName(String pName);

	public List<Object[]> ahpHazardous(String productType, String today);

	public int countType();

}
