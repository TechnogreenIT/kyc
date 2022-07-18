package com.tes.controller.environmentalofficer;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tes.model.HazardousManifest;
import com.tes.model.WasteDescriptionConsistency;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.HazardousManifestServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;
import com.tes.utilities.Validator;

/**
 * This class used to manage the hazardous return form.
 * 
 * @author Sushama Dadas
 */
@Controller
@RequestMapping(value = {"/env"})
public class HazardousReturnController
{

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	HazardousManifestServices hazardousManifestServices;

	@Autowired
	WasteDescriptionConsistencyServices wasteDescriptionConsistencyServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	UnitServices unitServices;

	@Autowired
	RegularDataServices regularDataServices;

	private static final Logger LOGGER = LogManager.getLogger(HazardousReturnController.class);

	/**
	 * This method used to return product details in hazardous return form.
	 * 
	 * @param encodedYear the encoded year of hazardous year.
	 * @param type the type of product type.
	 * @param request the servlet request we are processing.
	 * @return it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "ajax-getProductionData", method = RequestMethod.POST)
	public @ResponseBody String getProductionData(@RequestParam(value = "year", required = false) String encodedYear,
			@RequestParam(value = "type", required = false) String type, HttpServletRequest request)
			throws JSONException
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			String productName = "", unitName = "", year = encodedYear;
			float quantity = 0.0f;
			String dateRes[] = year.split("-");
			String fromDate = (dateRes[0]) + "-04-01";
			String toDate = (dateRes[1]) + "-03-31";
			int productId = 0, apcId = 0;
			List<String> unitList = new ArrayList<>();
			List<Integer> apcIdList = new ArrayList<>();
			List<String> allProductsNameList = new ArrayList<>();
			if (type.equalsIgnoreCase("production"))
			{
				allProductsNameList = allProductNameServices.getProductNameByConsentToEstablish(toDate);
				if (!Validator.isEmpty(allProductsNameList))
				{
					for (int i = 0; i < allProductsNameList.size(); i++)
					{
						productName = allProductsNameList.get(i);
						productId = allProductNameServices.prouctNameIdByProductName(productName);
						if (!Validator.isEmpty(productId))
						{
							unitList = unitServices.unitName(productId);
						}
						if (!Validator.isEmpty(unitList))
						{
							for (int k = 0; k < unitList.size(); k++)
							{
								unitName = unitList.get(k);
							}
						}
						else
						{
							unitName = "";
						}
						apcIdList = allProductComparativeSheetServices.getAllProductComparativeSheetIdByProductName(productName, new PageRequest(0, 1));
						if (!Validator.isEmpty(apcIdList))
						{
							for (int j = 0; j < apcIdList.size(); j++)
							{
								apcId = apcIdList.get(j);
							}
						}
						else
						{
							apcId = 0;
						}
						quantity = regularDataServices.getSumQuantityOfRegularDataByAPCIdAndDate(apcId, fromDate, toDate);
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("productType", "NA");
						jsonObject.put("productName", new String(productName));
						jsonObject.put("actualQuantity", new Float(quantity));
						jsonObject.put("UOM", new String(unitName));
						jsonArray.put(jsonObject);

					}
				}
				else
				{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("productType", "NA");
					jsonObject.put("productName", "NA");
					jsonObject.put("actualQuantity", "NA");
					jsonObject.put("UOM", "NA");
					jsonArray.put(jsonObject);
				}
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to return hazardous data in selected hazardous year.
	 * 
	 * @param encodedYear the encoded year of hazardous year.
	 * @param type the type of product type.
	 * @param request the servlet request we are processing.
	 * @return it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "ajax-getHazardousdata", method = RequestMethod.POST)
	public @ResponseBody String getHazardousdata(@RequestParam(value = "year", required = false) String encodedYear,
			@RequestParam(value = "type1", required = false) String type, HttpServletRequest request)
			throws JSONException
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			String productName = "", unitName = "", year = encodedYear, wasteName = "", wasteCategoryNo = "", description = "";
			float quantity = 0.0f;
			String dateRes[] = year.split("-");
			String fromDate = (dateRes[0]) + "-04-01";
			String toDate = (dateRes[1]) + "-03-31";
			int productId = 0, apcId = 0;
			List<WasteDescriptionConsistency> wasteNameList = new ArrayList<WasteDescriptionConsistency>();
			List<String> unitList = new ArrayList<>();
			List<Integer> apcIdList = new ArrayList<>();
			if (type.equalsIgnoreCase("wastegen"))
			{
				wasteNameList = wasteDescriptionConsistencyServices.getHazardousWasteNameBetweenDate(fromDate, toDate);
				if (!Validator.isEmpty(wasteNameList))
				{
					for (int i = 0; i < wasteNameList.size(); i++)
					{
						wasteName = wasteNameList.get(i).getWasteName();
						wasteCategoryNo = wasteNameList.get(i).getWasteCategoryNumber();
						description = wasteNameList.get(i).getDescription();
						// productNameList = allProductNameServices.getProductNameFromWasteName(wasteName);
						productName = wasteCategoryNo + "-" + wasteName;
						productId = allProductNameServices.prouctNameIdByProductName(productName);
						unitList = unitServices.unitName(productId);
						if (!Validator.isEmpty(unitList))
						{
							for (int k = 0; k < unitList.size(); k++)
							{
								unitName = unitList.get(k);
							}
						}
						else
						{
							unitName = "";
						}
						apcIdList = allProductComparativeSheetServices.getAllProductComparativeSheetIdByProductName(productName, new PageRequest(0, 1));
						if (!Validator.isEmpty(apcIdList))
						{
							for (int j = 0; j < apcIdList.size(); j++)
							{
								apcId = apcIdList.get(j);
							}
						}
						else
						{
							apcId = 0;
						}
						quantity = regularDataServices.getSumQuantityOfRegularDataByAPCIdAndDate(apcId, fromDate, toDate);
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("typeOfWaste", new String(productName));
						jsonObject.put("wasteName", new String(description));
						jsonObject.put("quantity", new Float(quantity));
						jsonObject.put("units", new String(unitName));
						jsonArray.put(jsonObject);
					}
				}
				else
				{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("typeOfWaste", "NA");
					jsonObject.put("wasteName", "NA");
					jsonObject.put("quantity", "NA");
					jsonObject.put("units", "NA");
					jsonArray.put(jsonObject);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to return dispatch data of hazardous waste for selected user.
	 * 
	 * @param encodedYear the encoded year of hazardous year.
	 * @param type the type of product type.
	 * @param request the servlet request we are processing.
	 * @return it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "ajax-getDispatchData", method = RequestMethod.POST)
	public @ResponseBody String getDispatchdata(@RequestParam(value = "year", required = false) String encodedYear,
			@RequestParam(value = "type1", required = false) String type, HttpServletRequest request)
			throws JSONException
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			String productName = "", unitName = "", year = encodedYear, facilityName = "", dispatchedTo = "",
					wasteName = "", wasteCategoryNo = "";
			float quantity = 0.0f;
			String dateRes[] = year.split("-");
			String fromDate = (dateRes[0]) + "-04-01";
			String toDate = (dateRes[1]) + "-03-31";
			int productId = 0;
			List<WasteDescriptionConsistency> wasteNameList = new ArrayList<WasteDescriptionConsistency>();
			List<String> unitList = new ArrayList<>();
			List<HazardousManifest> facilityAndDispatchedList = null;
			if (type.equalsIgnoreCase("dispatch"))
			{
				wasteNameList = wasteDescriptionConsistencyServices.getHazardousWasteNameBetweenDate(fromDate, toDate);
				if (!Validator.isEmpty(wasteNameList))
				{
					for (int i = 0; i < wasteNameList.size(); i++)
					{
						wasteName = wasteNameList.get(i).getWasteName();
						wasteCategoryNo = wasteNameList.get(i).getWasteCategoryNumber();
						// productNameList = allProductNameServices.getProductNameFromWasteName(wasteName);
						productName = wasteCategoryNo + "-" + wasteName;
						quantity = wasteDescriptionConsistencyServices.getSumWasteQuantityByWasteName(fromDate, toDate,
								wasteName, wasteCategoryNo);
						productId = allProductNameServices.prouctNameIdByProductName(productName);
						unitList = unitServices.unitName(productId);
						facilityAndDispatchedList = hazardousManifestServices
								.getDispatchedToAndFacilityNameByWasteName(wasteName, wasteCategoryNo, fromDate, toDate);

						for (int k = 0; k < facilityAndDispatchedList.size(); k++)
						{
							dispatchedTo = facilityAndDispatchedList.get(k).getDispatchedTo();
							facilityName = facilityAndDispatchedList.get(k).getReceiversName();
						}
						if (!Validator.isEmpty(unitList))
						{
							for (int k = 0; k < unitList.size(); k++)
							{
								unitName = unitList.get(k);
							}
						}
						else
						{
							unitName = "";
						}

						JSONObject jsonObject = new JSONObject();
						jsonObject.put("typeOfWaste", new String(productName));
						jsonObject.put("dispatchQuantity", new Float(quantity));
						jsonObject.put("dispatchUnits", new String(unitName));
						jsonObject.put("dispatchDispatchedTo", new String(dispatchedTo));
						jsonObject.put("dispatchFacilityName", new String(facilityName));
						jsonArray.put(jsonObject);
					}
				}
				else
				{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("typeOfWaste", "NA");
					jsonObject.put("dispatchQuantity", "NA");
					jsonObject.put("dispatchUnits", "NA");
					jsonObject.put("dispatchDispatchedTo", "NA");
					jsonObject.put("dispatchFacilityName", "NA");
					jsonArray.put(jsonObject);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to return inhouse data of hazardous waste for selected user.
	 * 
	 * @param encodedYear the encoded year of hazardous year.
	 * @param type the type of product type.
	 * @param request the servlet request we are processing.
	 * @return it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "ajax-getInhouseData", method = RequestMethod.POST)
	public @ResponseBody String getInhouseData(@RequestParam(value = "year", required = false) String encodedYear,
			@RequestParam(value = "type", required = false) String type, HttpServletRequest request)
			throws JSONException
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			String productName = "", unitName = "", year = encodedYear, wasteName = "", wasteCategoryNo = "", description = "";
			float quantity = 0.0f;
			String dateRes[] = year.split("-");
			String fromDate = (dateRes[0]) + "-04-01";
			String toDate = (dateRes[1]) + "-03-31";
			int productId = 0;
			List<String> inhouseNameList = new ArrayList<String>();
			List<String> unitList = new ArrayList<>();
			if (type.equalsIgnoreCase("inhouse"))
			{
				inhouseNameList = wasteDescriptionConsistencyServices.getInHouseWasteNameBetweenDate(fromDate, toDate);
				if (!Validator.isEmpty(inhouseNameList))
				{
					for (int i = 0; i < inhouseNameList.size(); i++)
					{
						wasteName = inhouseNameList.get(i);
						// wasteCategoryNo=wasteNameList.get(i).getWasteCategoryNumber();
						productName = wasteCategoryNo + "-" + wasteName;
						quantity = wasteDescriptionConsistencyServices.getSumWasteQuantityByWasteName(fromDate, toDate,
								wasteName, wasteCategoryNo);

						productId = allProductNameServices.prouctNameIdByProductName(productName);

						unitList = unitServices.unitName(productId);
						for (int k = 0; k < unitList.size(); k++)
						{
							unitName = unitList.get(k);
						}
						productName = productName.replace("-", "");
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("typeOfWaste", new String(productName));
						jsonObject.put("inhouseWasteName", new String(description));
						jsonObject.put("wasteQuantity", new Float(quantity));
						jsonObject.put("UOM", new String(unitName));
						jsonArray.put(jsonObject);
					}

				}
				else
				{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("typeOfWaste", "NA");
					jsonObject.put("inhouseWasteName", "NA");
					jsonObject.put("wasteQuantity", "NA");
					jsonObject.put("UOM", "NA");
					jsonArray.put(jsonObject);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	/**
	 * This method used to return storage data of hazardous waste for selected user.
	 * 
	 * @param encodedYear the encoded year of hazardous year.
	 * @param type the type of product type.
	 * @param request the servlet request we are processing.
	 * @return it returns String value of json array.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@RequestMapping(value = "ajax-getStorageData", method = RequestMethod.POST)
	public @ResponseBody String getStorageData(@RequestParam(value = "year", required = false) String encodedYear,
			@RequestParam(value = "type", required = false) String type, HttpServletRequest request)
			throws JSONException
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			String productName = "", unitName = "", year = encodedYear, wasteName = "", wasteCategoryNo = "", description = "";
			float wasteQuantity = 0.0f, quantityUtilized = 0.0f, storageQuantity = 0.0f;
			String dateRes[] = year.split("-");
			String fromDate = (dateRes[0]) + "-04-01";
			String toDate = (dateRes[1]) + "-03-31";
			int productId = 0, apcId = 0;
			List<WasteDescriptionConsistency> wasteNameList = new ArrayList<WasteDescriptionConsistency>();
			List<String> unitList = new ArrayList<>();
			List<Integer> apcIdList = new ArrayList<>();
			if (type.equalsIgnoreCase("storage"))
			{
				wasteNameList = wasteDescriptionConsistencyServices.getHazardousWasteNameBetweenDate(fromDate, toDate);
				if (!Validator.isEmpty(wasteNameList))
				{
					for (int i = 0; i < wasteNameList.size(); i++)
					{
						wasteName = wasteNameList.get(i).getWasteName();
						wasteCategoryNo = wasteNameList.get(i).getWasteCategoryNumber();
						description = wasteNameList.get(i).getDescription();
						wasteQuantity = wasteDescriptionConsistencyServices.getSumWasteQuantityByWasteName(fromDate,
								toDate, wasteName, wasteCategoryNo);
						productName = wasteCategoryNo + "-" + wasteName;
						productId = allProductNameServices.prouctNameIdByProductName(productName);
						unitList = unitServices.unitName(productId);
						if (!Validator.isEmpty(unitList))
						{
							for (int j = 0; j < unitList.size(); j++)
							{
								unitName = unitList.get(j);
							}
						}
						else
						{
							unitName = "";
						}
						apcIdList = allProductComparativeSheetServices.getAllProductComparativeSheetIdByProductName(productName,
								new PageRequest(0, 1));
						if (!Validator.isEmpty(apcIdList))
						{
							for (int j = 0; j < apcIdList.size(); j++)
							{
								apcId = apcIdList.get(j);
							}
						}
						else
						{
							apcId = 0;
						}
						quantityUtilized = regularDataServices.getSumQuantityOfRegularDataByAPCIdAndDate(apcId,
								fromDate, toDate);
						storageQuantity = quantityUtilized - wasteQuantity;
						if ((!Validator.isEmpty(storageQuantity)) && (storageQuantity > 0))
						{
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("storageTypeWasteName", new String(productName));
							jsonObject.put("storageWasteName", new String(description));
							jsonObject.put("storageQuantity", new Float(storageQuantity));
							jsonObject.put("UOM", new String(unitName));
							jsonArray.put(jsonObject);
						}
					}
				}
				else
				{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("storageTypeWasteName", "NA");
					jsonObject.put("storageWasteName", "NA");
					jsonObject.put("storageQuantity", "NA");
					jsonObject.put("UOM", "NA");
					jsonArray.put(jsonObject);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}
}
