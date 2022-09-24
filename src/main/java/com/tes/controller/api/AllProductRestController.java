package com.tes.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tes.model.AllProductComparativeSheet;
import com.tes.model.RegularData;
import com.tes.model.Users;
import com.tes.repository.environmentalofficer.RegularDataRepository;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

// pal commit
@RestController
@RequestMapping("/rest/allproduct")
public class AllProductRestController
{

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	RegularDataRepository regularDataRepository;

	@Autowired
	RegularDataServices regularDataServices;

	private static final Logger LOGGER = LogManager.getLogger(AllProductRestController.class);

	@GetMapping("/{type}")
	private ArrayList<Object> getPorductList(@PathVariable("type") String type) throws JSONException
	{
		ArrayList<Object> productDataArrayList = new ArrayList<>();
		ArrayList<Object> jsonProductList = new ArrayList<>();
		HashMap<String, Object> productListHashMap = new HashMap<>();
		List<RegularData> regularDataList = regularDataRepository.findProductListByProductNameAndTodaysDate(type, Utilities.getTodaysDate());
		try
		{
			if (!Validator.isEmpty(regularDataList))
			{
				for (RegularData regularData : regularDataList)
				{
					HashMap<String, Object> productData = new HashMap<>();
					productData.put("allProductComparativeSheetId", regularData.getAllProductComparativeSheet().getAllProductComparativeSheetId());
					productData.put("productName", regularData.getAllProductComparativeSheet().getAllProducts().getAllProductName().getProductName());
					productData.put("quantity", regularData.getQuantity());
					productData.put("unit", regularData.getAllProductComparativeSheet().getAllProducts().getUnit().getUnits());
					productDataArrayList.add(productData);
				}
				productListHashMap.put("Input", Constant.NO);
				productListHashMap.put("Result", Constant.TRUE);
				productListHashMap.put("productList", productDataArrayList);
				jsonProductList.add(productListHashMap);
				// return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonArray);//409 Conflict
			}
			else
			{
				List<AllProductComparativeSheet> allProductComparativeSheetList = allProductComparativeSheetServices.getAllProductByType(type);
				if (!Validator.isEmpty(allProductComparativeSheetList))
				{
					for (AllProductComparativeSheet allProductComparativeSheet : allProductComparativeSheetList)
					{
						HashMap<String, Object> productData = new HashMap<>();
						productData.put("allProductComparativeSheetId", allProductComparativeSheet.getAllProductComparativeSheetId());
						productData.put("productName", allProductComparativeSheet.getAllProducts().getAllProductName().getProductName());
						productData.put("quantity", "");
						productData.put("unit", allProductComparativeSheet.getAllProducts().getUnit().getUnits());
						productDataArrayList.add(productData);
					}
					productListHashMap.put("Input", Constant.YES);
					productListHashMap.put("Result", Constant.TRUE);
					productListHashMap.put("productList", productDataArrayList);
				}
				else
				{
					productListHashMap.put("Result", Constant.FALSE);
				}
				jsonProductList.add(productListHashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonProductList;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private String setAllProductData(@RequestBody List<Map<String, String>> regularDataList)
	{
		try
		{
			Users objUsersId = new Users();
			for (Map<String, String> regularData : regularDataList)
			{
				RegularData objRegularData = new RegularData();
				AllProductComparativeSheet objComparativeSheetId = new AllProductComparativeSheet();
				objComparativeSheetId.setAllProductComparativeSheetId(Integer.parseInt(regularData.get("allProductComparativeSheetId")));
				objUsersId.setUsersId(Integer.parseInt(regularData.get("userId")));
				objRegularData.setAllProductComparativeSheet(objComparativeSheetId);
				objRegularData.setUsers(objUsersId);
				objRegularData.setQuantity(Float.parseFloat(regularData.get("quantity")));
				objRegularData.setInputDate(Utilities.getTodaysDate());
				regularDataServices.save(objRegularData);
			}
			return Constant.SUCCESS;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			return Constant.FAILURE;
		}
	}

}
