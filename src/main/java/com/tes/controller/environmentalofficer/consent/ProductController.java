package com.tes.controller.environmentalofficer.consent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tes.enumeration.ProductTypes;
import com.tes.model.AllProductComparativeSheet;
import com.tes.model.AllProductName;
import com.tes.model.AllProducts;
import com.tes.model.Consent;
import com.tes.model.EmpData;
import com.tes.model.Unit;
import com.tes.model.Users;
import com.tes.repository.HazardousWastesRepository;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate the all production details.
 * This class perform the operation of add / view the consent product operation.
 * 
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class ProductController extends Constant
{

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	HazardousWastesRepository hazardousWastesRepository;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);
	private static final String USERSESSION = "empDataSession";

	/**
	 * This method used to add production details of the consent to establish
	 * 
	 * @param productName the name of product
	 * @param consentId the id of consent
	 * @param productType the Type of product
	 * @param quantitys the quantity of product
	 * @param units the unit of product
	 * @param request the servlet request we are processing.
	 * @return flag it return count according to number of row inserted
	 */
	@RequestMapping(value = {"/ajax-add-consentdata", "/ajax-view-consent-add-elements"})
	public @ResponseBody int setAllProductData(
			@RequestParam(value = "productName", required = false) String[] productName,
			@RequestParam(value = "consentNo", required = false) int consentId,
			@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value = "quantity", required = false) float[] quantitys,
			@RequestParam(value = "units", required = false) int[] units, HttpServletRequest request)
	{
		int flag = 0;
		try
		{
			if (!Validator.isEmpty(request.getSession().getAttribute(USERSESSION)))
			{
				EmpData empDataSession = (EmpData) request.getSession().getAttribute(USERSESSION);
				Users objUserId = new Users();
				Consent objConsentId = new Consent();
				Unit unit = new Unit();
				objUserId.setUsersId(empDataSession.getUsers().getUsersId());
				objConsentId.setConsentId(consentId);

				int productNameData = productName.length;
				int quantitysData = quantitys.length;
				int unitsData = units.length;
				productType = Utilities.getFullNameOfProduct(productType);
				for (int i = 0; i < productNameData && i < quantitysData && i < unitsData; i++)
				{
					AllProducts objAllProducts = new AllProducts();
					unit.setUnitId(units[i]);

					AllProductName objAllProductName = new AllProductName();
					objAllProductName.setProductName(productName[i]);
					// Name is unique if it duplicate it will raise error ......by vishal
					objAllProductName.setType(productType);
					objAllProductName.setStatus(ACTIVE);
					allProductNameServices.save(objAllProductName);

					objAllProducts.setUsers(objUserId);
					objAllProducts.setConsent(objConsentId);
					objAllProducts.setAllProductName(objAllProductName);
					objAllProducts.setQuantity(quantitys[i]);
					objAllProducts.setUnit(unit);
					allProductsServices.save(objAllProducts);
		 flag++;
			}
			}
			else
			{
				// Redict to login page ....by vishal
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return flag;
	}

	/**
	 * This method used to save data of production details for consent to operate .
	 * 
	 * @param type the type of product
	 * @param consentId the id of consent
	 * @param allProductNameId the name of all product
	 * @param proQuantity the quantity of product
	 * @param proUnits the unit of product
	 * @param request the servlet request we are processing.
	 * @return flag it return count according to number of row inserted
	 */
	// @RequestMapping(value = {"/ajax-olistproduct-savedata","/ajax-viewOList-consent-add-elements"},method = RequestMethod.POST)
	@RequestMapping("/ajax-olistproduct-savedata")
	public @ResponseBody int setDataOfOlist(@RequestParam("type") String type, @RequestParam("consentId") int consentId,
			@RequestParam("allProductNameId") int[] allProductNameId, @RequestParam("listquantity") float[] proQuantity,
			@RequestParam("listunits") int[] proUnits, HttpServletRequest request)
	{
		int flag = 0;
		try
		{
			Consent objConsent = new Consent();
			objConsent.setConsentId(consentId);
			Unit objUnit = new Unit();
			EmpData empDataSession = null;
			Users objUsers = new Users();
			if (!Validator.isEmpty((EmpData) request.getSession().getAttribute(USERSESSION)))
			{
				empDataSession = (EmpData) request.getSession().getAttribute(USERSESSION);
				objUsers.setUsersId(empDataSession.getUsers().getUsersId());
			}
			for (int i = 0, size = allProductNameId.length; i < size; i++)
			{
				AllProducts objAllProducts = new AllProducts();
				AllProductName objAllProductName = new AllProductName();
				objAllProductName.setAllProductNameId(allProductNameId[i]);
				objUnit.setUnitId(proUnits[i]);
				objAllProducts.setConsent(objConsent);
				objAllProducts.setAllProductName(objAllProductName);
				objAllProducts.setQuantity(proQuantity[i]);
				objAllProducts.setUnit(objUnit);
				objAllProducts.setUsers(objUsers);
				allProductsServices.save(objAllProducts);

				// inactive query fire base on here
				allProductComparativeSheetServices.setInactiveByAllProductNameId(allProductNameId[i]);

				AllProductComparativeSheet objAllProductComparativeSheet = new AllProductComparativeSheet();
				objAllProductComparativeSheet.setAllProducts(objAllProducts);

				// if one product took in one consent and in other c2o if not select it
				objAllProductComparativeSheet
						.setQuantity(allProductComparativeSheetServices.sumOfQuantityByCtoO(allProductNameId[i]));

				/*
				 * need to make only one query for this that return direct result(substration)
				 * change setting in mysql(Workbanch) Need to Safe Updates ....by vishal
				 */
				objAllProductComparativeSheet
						.setBalance(allProductComparativeSheetServices.findByQuantityByCtoE(allProductNameId[i])
								- objAllProductComparativeSheet.getQuantity());
				objAllProductComparativeSheet.setStatus(ACTIVE);
				allProductComparativeSheetServices.save(objAllProductComparativeSheet);
				flag++;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return flag;
	}

	/**
	 * This method used to fetching production data for consent to operate.
	 * 
	 * @param type the type of product
	 * @param consentId the id of the consent
	 * @return production details
	 */
	@RequestMapping("ajax-get-production-details")
	public @ResponseBody List<AllProducts> getProductionDetail(@RequestParam("type") String type,
			@RequestParam("consentId") int consentId)
	{
		try
		{
			if (type.equals(ESTABLISH))
			{
				type = CONSENT_TO_ESTABLISH;
			}
			else if (type.equals(OPERATE))
			{
				type = CONSENT_TO_OPERATE;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		List<AllProducts> list = new ArrayList<>(allProductsServices.findByConsTypeAndConsId(type, consentId));

		return list;
	}

	/**
	 * This method used to enable and disable product.
	 * 
	 * @param productNameId the Id of product Name
	 * @param status showing status to unable or disable
	 * @return success/fail
	 */
	@RequestMapping("ajax-view-consent-unable-disable-product")
	public @ResponseBody String unableDisableProduction(@RequestParam("productNameId") int productNameId,
			@RequestParam("status") String status)
	{
		try
		{
			int isUpdated = allProductNameServices.updateStatusByAllProductNameId(productNameId, status);
			return isUpdated == 1 ? SUCCESS : FAILURE;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			return FAILURE;
		}
	}

	/**
	 * This method used to view and modify the production details.
	 * 
	 * @param productId the id of product
	 * @param productName the Name of product
	 * @param quantity the quantity of product
	 * @param unitId the id of unit of the product
	 * @param productNameId the list order of product Name
	 * @return Success/failure
	 */
	@RequestMapping("ajax-view-consent-modify-production")
	public @ResponseBody String modifyProduction(@RequestParam("productId") int productId,
			@RequestParam("productName") String productName, @RequestParam("quantity") float quantity,
			@RequestParam("units") int unitId, @RequestParam("productNameId") int productNameId)
	{
		try
		{
			int isProductNameUpdated = allProductNameServices.updateProductNameById(productNameId, productName);
			int isProductDetailsUpdated = allProductsServices.updateProductById(productId, quantity, unitId);
			return isProductDetailsUpdated == 1 && isProductNameUpdated == 1 ? SUCCESS : FAILURE;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			return FAILURE;
		}
	}

	/**
	 * This method used to get product by product type
	 * 
	 * @param consentId the id of Consent
	 * @param productType the type of Product
	 * @return jsonArray it returns String value of json array.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping(value = "ajax-get-products-by-product-type")
	public @ResponseBody String getProductsByProductType(@RequestParam("consentId") int consentId,
			@RequestParam("productType") String productType)
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			if (productType.equals(ProductTypes.product) || productType.equals(ProductTypes.raw)
					|| productType.equals(ProductTypes.fuel) || productType.equals(ProductTypes.bio))
			{
				productType = Utilities.getFullNameOfProduct(productType);
			}
			List<AllProductName> allProductNameList = allProductNameServices.getProductsByProductType(productType);
			for (AllProductName allProductName : allProductNameList)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("allProductNameId", allProductName.getAllProductNameId());
				jsonObject.put("productName", allProductName.getProductName());
				jsonArray.put(jsonObject);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to add to the data for consent to operate.
	 * 
	 * @param productType the type of product
	 * @param action of getData
	 * @return jsonArray it returns String value of json array.
	 */
	@PostMapping(value = "ajax-olist-data")
	public @ResponseBody String setOlistData(HttpServletRequest request)
	{
		JSONObject mainJsonObject = new JSONObject();
		try
		{

			ArrayList<String> allProductsCatList = new ArrayList<>();

			String industrySession = (String) request.getSession().getAttribute("sessionIndustryType");
			if (industrySession.equalsIgnoreCase("Industry"))
			{
				allProductsCatList = new ArrayList<>(Arrays.asList("product", "byproduct", "raw", "fuel", "hwp", "hwpcf", "nhwp", "nhwpcf","eWaste" ,"pWaste","bWaste","cdWaste","cutfill"));
			}
			else
			{
				allProductsCatList = new ArrayList<>(Arrays.asList("fuel", "hwp", "nhwp", "nhwpcf"));
			}
			for (int j = 0; j < allProductsCatList.size(); j++)
			{
				JSONArray jsonArray = new JSONArray();
				String productType = allProductsCatList.get(j);
				if (productType.equals("product") || productType.equals("raw") || productType.equals("fuel")
						|| productType.equals("bio"))
				{
					productType = Utilities.getFullNameOfProduct(productType);
				}

				int flag = 0;
				int cToEId = 0;
				int[] consentId = consentServices.findByCtoEAndToday(Utilities.getTodaysDate());
				List<AllProducts> establishProductsList = new ArrayList<AllProducts>();
				if (consentId.length > 0)
				{
					for (int i = 0; i < consentId.length; i++)
					{
						cToEId = consentId[i];
						establishProductsList = allProductsServices.findByProductType(productType, cToEId);
					}
				}
				else
				{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("allProductNameId", "NA");
					jsonObject.put("productName", "NA");
					jsonObject.put("quantity", "NA");
					jsonObject.put("units", "NA");
					jsonObject.put("unitId", "NA");
					jsonArray.put(jsonObject);
				}

				for (int i = 0; i < establishProductsList.size(); i++)
				{
					String pName = establishProductsList.get(i).getAllProductName().getProductName();
					Float oldElstablishQuantity = Utilities.getFloatpoint(establishProductsList.get(i).getQuantity(), 2);

					int allProductNameId = establishProductsList.get(i).getAllProductName().getAllProductNameId();
					String unit = establishProductsList.get(i).getUnit().getUnits();
					int unitId = establishProductsList.get(i).getUnit().getUnitId();

					int[] cToOId = consentServices.findByCtoOAndToday(Utilities.getTodaysDate());
					float consentedQuantity = 0.0f;

					// operate available
					if (cToOId.length > 0)
					{
						Float remainingQuantity = 0.0f;

						for (int p = 0; p < cToOId.length; p++)
						{
							int consentOpId = cToOId[p];
							List<AllProducts> oldEstablishProductsList = new ArrayList<AllProducts>();
							oldEstablishProductsList = allProductsServices.findByProductType(productType, consentOpId, pName);

							for (int k = 0; k < oldEstablishProductsList.size(); k++)
							{
								consentedQuantity += oldEstablishProductsList.get(k).getQuantity();
							}

						}
						remainingQuantity = oldElstablishQuantity - consentedQuantity;

						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("allProductNameId", allProductNameId);
						hashMap.put("productName", pName);
						hashMap.put("quantity", Utilities.getFloatpoint(remainingQuantity, 2));
						hashMap.put("units", unit);
						hashMap.put("unitId", unitId);
						jsonArray.put(hashMap);
					}
					else
					{
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("allProductNameId", allProductNameId);
						hashMap.put("productName", pName);
						hashMap.put("quantity", oldElstablishQuantity);
						hashMap.put("units", unit);
						hashMap.put("unitId", unitId);
						jsonArray.put(hashMap);
					}
				}
				mainJsonObject.put(productType, jsonArray);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		String temp = mainJsonObject.toString();
		return temp;
	}

	/**
	 * Once Consent to operate filled user will able to view production details.
	 * 
	 * @param productName the name of product
	 * @param consentId the id of consent
	 * @param productType the type of product
	 * @param quantitys the quantity of product
	 * @param units the unit of product
	 * @param request the servlet request we are processing.
	 * @return flag it return count according to number of row inserted
	 */
	@PostMapping(value = "ajax-viewOList-consent-add-elements")
	public @ResponseBody int setAProductData(
			@RequestParam(value = "productName", required = false) String[] productName,
			@RequestParam(value = "consentNo", required = false) int consentId,
			@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value = "quantity", required = false) float[] quantitys,
			@RequestParam(value = "units", required = false) int[] units, HttpServletRequest request)
	{
		int flag = 0;
		int productId = 0;
		try
		{
			// prouctNameIdByProductName
			Consent objConsent = new Consent();
			objConsent.setConsentId(consentId);
			Unit objUnit = new Unit();
			EmpData empDataSession = null;
			Users objUsers = new Users();
			if (!Validator.isEmpty((EmpData) request.getSession().getAttribute(USERSESSION)))
			{
				empDataSession = (EmpData) request.getSession().getAttribute(USERSESSION);
				objUsers.setUsersId(empDataSession.getUsers().getUsersId());
			}
			for (int i = 0; i < productName.length; i++)
			{
				productId = allProductNameServices.prouctNameIdByProductName(productName[i]);
				AllProducts objAllProducts = new AllProducts();
				AllProductName objAllProductName = new AllProductName();
				objAllProductName.setAllProductNameId(productId);
				objUnit.setUnitId(units[i]);
				objAllProducts.setConsent(objConsent);
				objAllProducts.setAllProductName(objAllProductName);
				objAllProducts.setQuantity(quantitys[i]);
				objAllProducts.setUnit(objUnit);
				objAllProducts.setUsers(objUsers);
				allProductsServices.save(objAllProducts);

				objAllProducts.getAllProductsId();
				AllProductComparativeSheet objAllProductComparativeSheet = new AllProductComparativeSheet();
				objAllProductComparativeSheet.setAllProducts(objAllProducts);
				// if one product took in one consent and in other c2o if not select it
				objAllProductComparativeSheet
						.setQuantity(allProductComparativeSheetServices.sumOfQuantityByCtoO(productId));

				/*
				 * need to make only one query for this that return direct result(substration)
				 * change setting in mysql(Workbanch) Need to Safe Updates ....by vishal
				 */
				objAllProductComparativeSheet
						.setBalance(allProductComparativeSheetServices.findByQuantityByCtoE(productId)
								- objAllProductComparativeSheet.getQuantity());
				objAllProductComparativeSheet.setStatus(ACTIVE);
				allProductComparativeSheetServices.save(objAllProductComparativeSheet);
				flag++;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return flag;
	}

}
