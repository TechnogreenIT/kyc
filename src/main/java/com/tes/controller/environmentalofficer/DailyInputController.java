package com.tes.controller.environmentalofficer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tes.model.AllProductComparativeSheet;
import com.tes.model.DirectUseOfWater;
import com.tes.model.EmpData;
import com.tes.model.FilterUse;
import com.tes.model.MultipleFilter;
import com.tes.model.Prefilter;
import com.tes.model.RegAPC;
import com.tes.model.RegDirectUseOfWaterData;
import com.tes.model.RegFiltersUseData;
import com.tes.model.RegMultipleFilterData;
import com.tes.model.RegPrefilter;
import com.tes.model.RegWastewaterRecycle;
import com.tes.model.RegWaterSourceData;
import com.tes.model.RegularData;
import com.tes.model.RegularTreatmentData;
import com.tes.model.Stack;
import com.tes.model.Users;
import com.tes.model.WastewaterRecycle;
import com.tes.model.WastewaterTreatment;
import com.tes.model.WaterInventory;
import com.tes.model.WaterSource;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.HazardousManifestServices;
import com.tes.services.environmentalofficer.RegAPCServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.MultipleFilterServices;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegPrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.RegWastewaterRecycleServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.WasteWaterRecycleSevices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class used to manage the daily input data. This class perform operation
 * save daily input data, save regular water source data, regular filter data
 * and water treatment data. Display all product reading of daily input data.
 * 
 * @author Sushama Dadas
 */
@Controller
@RequestMapping(value = { "/env" })
public class DailyInputController extends Constant {

	// Effected By Water Inventory ........by sushama
	// @Autowired
	// DailyInputServices dailyInputServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	RegMultipleFilterDataServices regularFiltersDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	@Autowired
	RegularDataServices regularDataServices;

	public static String[] unitArray;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	UnitServices unitServices;

	@Autowired
	RegAPCServices regAPCServices;

	@Autowired
	StackServices stackServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	MultipleFilterServices multipleFilterServices;

	@Autowired
	RegMultipleFilterDataServices regMultipleFilterDataServices;

	@Autowired
	RegFiltersUseDataServices regFiltersUseDataServices;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	HazardousManifestServices hazardousManifestServices;

	@Autowired
	WasteDescriptionConsistencyServices wasteDescriptionConsistencyServices;

	@Autowired
	PrefilterServices prefilterServices;

	@Autowired
	RegPrefilterServices regPrefilterServices;

	@Autowired
	WasteWaterRecycleSevices wasteWaterRecycleSevices;

	@Autowired
	RegWastewaterRecycleServices regWastewaterRecycleServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWaterServices;

	@Autowired
	RegDirectUseOfWaterDataServices regDirectUseOfWaterServices;

	private static final Logger LOGGER = LogManager.getLogger(DailyInputController.class);

	/**
	 * This method used to save daily input of product data.
	 * 
	 * @param action      the SaveRegularData.
	 * @param productName the name of product.
	 * @param quantity    the quantity of product.
	 * @param request     the servlet request we are processing.
	 * @return it return value of flag
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping("/ajax-save-regular-data")
	public @ResponseBody int getSaveRegularData(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "a_name", required = false) String[] productName,
			@RequestParam(value = "a_quantity", required = false) Float[] quantity, HttpServletRequest request)
			throws ParseException {
		int flag = 0;
		try {
			String today = Utilities.getTodaysDate();
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int id = empDataSession.getUsers().getUsersId();
			int apcId = 0;

			if (action.equalsIgnoreCase("SaveRegularData")) {
				Users users = new Users();
				users.setUsersId(id);
				for (int i = 0; i < productName.length; i++) {
					// changes
					List<Integer> apcIdList = allProductComparativeSheetServices
							.getAllProductComparativeSheetIdByProductName(productName[i], new PageRequest(0, 1));
					if (!Validator.isEmpty(apcIdList)) {
						for (int j = 0; j < apcIdList.size(); j++) {
							apcId = apcIdList.get(j);
						}
					} else {
						apcId = 0;
					}
					AllProductComparativeSheet allProductComparativeSheet = new AllProductComparativeSheet();
					allProductComparativeSheet.setAllProductComparativeSheetId(apcId);
					RegularData regularData = new RegularData();
					regularData.setUsers(users);
					regularData.setAllProductComparativeSheet(allProductComparativeSheet);
					regularData.setInputDate(today);
					regularData.setQuantity(quantity[i]);
					regularDataServices.save(regularData);
					flag++;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return flag;
	}

	/**
	 * This method used to save daily input of apc system.
	 * 
	 * @param action     return SaveRegularAPCData.
	 * @param apcStackId the Air pollution control ID
	 * @param apcStart   the apc Start reading
	 * @param apcEnd     the apc End reading
	 * @param apcAvg     the apc Avg reading
	 * @param request    the servlet request we are processing.
	 * @return flag it return value of flag
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping(value = { "ajax-save-regular-apc-data" }, method = RequestMethod.POST)
	public @ResponseBody int getSaveAPCData(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "apc_stack_id", required = false) int[] apcStackId,
			@RequestParam(value = "apc_start", required = false) Float[] apcStart,
			@RequestParam(value = "apc_end", required = false) Float[] apcEnd,
			@RequestParam(value = "apc_avg", required = false) Float[] apcAvg, HttpServletRequest request)
			throws ParseException {
		int apcflag = 0;
		try {
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int userId = empDataSession.getUsers().getUsersId();
			if (action.equalsIgnoreCase("SaveRegularAPCData")) {
				for (int i = 0; i < apcStackId.length; i++) {
					RegAPC regAPC = new RegAPC();
					Users users = new Users();
					users.setUsersId(userId);
					Stack stack = new Stack();
					stack.setStackId(apcStackId[i]);
					String today = Utilities.getTodaysDate();
					regAPC.setStack(stack);
					regAPC.setActualReading(apcAvg[i]);
					regAPC.setStartReading(apcStart[i]);
					regAPC.setEndReading(apcEnd[i]);
					regAPC.setApcDate(today);
					regAPC.setUsers(users);
					regAPC = regAPCServices.save(regAPC);
					if (regAPC != null) {
						apcflag = 1;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return apcflag;
	}

	/**
	 * This method used to save daily input of water source data.
	 * 
	 * @param action      return Save Regular Source Data.
	 * @param wi_id       the Id of water inventory ID.
	 * @param staff       the number of staff
	 * @param sourceName  the name of the water source name.
	 * @param sourceStart the Start Reading of the water source.
	 * @param sourceEnd   the End Reading of the water source
	 * @param sourceAvg   the Actual reading of the water source.
	 * @param request     the servlet request we are processing.
	 * @return flag 0 or 1
	 * @throws ParseException if parsing causes an error
	 */

	@RequestMapping(value = "ajax-save-regular-source-data", method = RequestMethod.POST)
	public @ResponseBody String saveRegularSourceData(@RequestBody JsonObject regularSourceObj,
			HttpServletRequest request) throws ParseException {
		String status = "";
		try {
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			if (!Validator.isEmpty(empDataSession)) {
				Users users = new Users();
				users.setUsersId(empDataSession.getUsers().getUsersId());
				if (!Validator.isEmpty(regularSourceObj)) {
					JsonArray regSourceArray = regularSourceObj.getAsJsonArray("regularSourceData");
					if (!Validator.isEmpty(regSourceArray)) {
						for (JsonElement regSourceData : regSourceArray) {
							RegWaterSourceData regularSourceData = new RegWaterSourceData();
							WaterSource waterSource = new WaterSource();
							waterSource.setWaterSourceId(regSourceData.getAsJsonObject().get("sourceId").getAsInt());
							regularSourceData.setWaterSource(waterSource);
							regularSourceData.setStaff(regularSourceObj.get("staff").getAsInt());
							regularSourceData
									.setStartReading(regSourceData.getAsJsonObject().get("sourceStart").getAsFloat());
							regularSourceData
									.setEndReading(regSourceData.getAsJsonObject().get("sourceEnd").getAsFloat());
							regularSourceData.setActualReading(
									regSourceData.getAsJsonObject().get("avgSourceStart").getAsFloat());
							regularSourceData
									.setStartReading(regSourceData.getAsJsonObject().get("sourceStart").getAsFloat());
							regularSourceData.setUsers(users);
							regularSourceData = regularSourceDataServices.save(regularSourceData);
							if (regularSourceData != null) {
								status = "success";
							} else {
								status = "fail";
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * This method used to return Yes- if category contain industry. else return No-
	 * 
	 * @param action  the getProduction.
	 * @param request the servlet request we are processing.
	 * @return isproduction it return value of isproduction
	 */
	@RequestMapping(value = "getProduction-saveData", method = RequestMethod.POST)
	public @ResponseBody String getProductionSaveData(@RequestParam(value = "action", required = false) String action,
			HttpServletRequest request) {
		String isproduction = YES;
		try {
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			String industry_category = empDataSession.getCompanyProfile().getIndustryCategory();
			if (action.equalsIgnoreCase("getProduction")) {
				isproduction = YES;
				if (industry_category.contains("Industry")) {
					isproduction = YES;
				} else if (industry_category.contains("Hospital")) {
					isproduction = "Bio-Medical";
				} else {
					isproduction = NO;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return isproduction;
	}

	/**
	 * This method used to Display all product data of daily input.
	 * 
	 * @param action  the getData
	 * @param type    the type of product type.
	 * @param request the servlet request we are processing.
	 * @return it returns String value of json array
	 * @throws JsonProcessingException
	 * @throws JSONException           indicates that some exception happened during
	 *                                 JSON processing.
	 * @throws ParseException          if parsing causes an error
	 */
	@RequestMapping(value = { "/ajax-getRegulardata" })
	@ResponseBody
	public @JsonRawValue String getRegulardata(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "type", required = false) String type, HttpServletRequest request)
			throws JsonProcessingException, JSONException, ParseException {
		JSONArray jsonArray = new JSONArray();
		try {
			int apcId = 0, currentYear, currentMonth, currentDay, i = 0, d1 = 0, diff = 0, productId = 0, unitDays = 0,
					d = 0, totalDays = 0;
			String today = Utilities.getTodaysDate(), cm1 = null, cm2 = null, status = "Fill", productName = null,
					unitP = null, u[] = null, unitName = null;
			Float filledValue = 0.0f, quantityCons = 0.0f, qtySumRgData, a = 0.0f, quantityR = 0.0f;
			EmpData empDataSession = null;
			LocalDateTime now = LocalDateTime.now();
			Date date1 = null, date2 = null;
			List<Object[]> productNameLists = new ArrayList<>();
			List<RegularData> rgData = new ArrayList<>();

			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			}
			int noDays = empDataSession.getCompanyProfile().getNoWorkDays();

			if (action.equalsIgnoreCase("getData")) {
				currentYear = now.getYear();
				currentMonth = now.getMonthValue();
				currentDay = now.getDayOfMonth();
				if (type.equalsIgnoreCase("raw"))
					type = "Raw Material";
				if (type.equalsIgnoreCase("bio"))
					type = "Bio";
				if (type.equalsIgnoreCase("medical"))
					type = "Medical";

				productNameLists = allProductComparativeSheetServices.getAllProductComparativeSheetForDailyInput(type,
						today);

				if (!Validator.isEmpty(productNameLists)) {
					for (Object[] allProductsListData : productNameLists) {

						productName = (String) allProductsListData[1]; // get product name
						quantityCons = (float) allProductsListData[0]; // get max quentity to generate exceed error
						unitName = (String) allProductsListData[2]; // get unit name
						status = "Fill";
						i = 0;
						a = (float) 0;
						filledValue = (float) 0;
						if (unitName.contains("/")) {
							u = unitName.split("/");
						}
						if (u[1].equals("Year")) {
							unitDays = totalDays;
						} else if (u[1].equals("Month")) {
							unitDays = noDays * 4;
						} else if (u[1].equals("Week")) {
							unitDays = noDays * 7;
						} else if (u[1].equals("Day")) {
							unitDays = 1;
						} else if (u[1].equals("Hr")) {
							unitDays = 60;
						}
						unitP = u[0] + "/Day";
						List<Integer> apcIdList = allProductComparativeSheetServices
								.getAllProductComparativeSheetIdByProductName(productName, new PageRequest(0, 1));

						if (!Validator.isEmpty(apcIdList)) {
							for (int j = 0; j < apcIdList.size(); j++) {
								apcId = apcIdList.get(j);
							}
						} else {
							apcId = 0;
						}
						if (apcId != 0) {
							rgData = regularDataServices.getRegularData(apcId, today, new PageRequest(0, 1));
						}
						if (rgData != null) {
							for (int i1 = 0; i1 < rgData.size(); i1++) {
								filledValue = rgData.get(i1).getQuantity();
								status = "Filled";
							}
						}
						a = (float) 0;
						quantityR = 0.0f;
						if (u[1].equals("Hr")) {
							a = quantityCons * 24;
						} else if (u[1].equals("Day")) {
							a = quantityCons;

						} else if (u[1].equals("Week")) {
						} else if (u[1].equals("Month")) {
							cm1 = currentYear + "-" + currentMonth + "-" + "01";
							cm2 = currentYear + "-" + currentMonth + "-" + currentDay;
							if (currentMonth <= 12 && currentMonth > 04) {
								qtySumRgData = regularDataServices.qtySumRegDataWithNextYear(apcId, currentYear, cm1,
										cm2);
							} else {
								qtySumRgData = regularDataServices.qtySumRegDataWithPreviousYear(apcId, currentYear,
										cm1, cm2);
							}
							if (qtySumRgData != null) {
								quantityR = qtySumRgData;
							}
							d = Utilities.getMaxDaysInMonth(currentMonth, currentYear);
							if (quantityR < quantityCons)
								a = (quantityCons - quantityR) / d;
							else
								a = 0.0f;
						} else if (u[1].equals("Year")) {
							if (currentMonth <= 12 && currentMonth > 04) {
								cm1 = currentYear + "-" + "04-01";
								cm2 = currentYear + "-" + currentMonth + "-" + currentDay;
								d = Utilities.getMaxDaysInMonth(currentMonth, currentYear);
								qtySumRgData = regularDataServices.qtySumRegDataWithNextYear(apcId, currentYear, cm1,
										cm2);
							} else {
								cm1 = currentYear - 1 + "-04-01";
								cm2 = currentYear + "-" + currentMonth + "-" + currentDay;
								d1 = Utilities.getMaxDaysInMonth(04, currentDay);
								d = Utilities.getMaxDaysInMonth(currentMonth, currentYear);
								qtySumRgData = regularDataServices.qtySumRegDataWithPreviousYear(apcId, currentYear,
										cm1, cm2);
							}
							while (qtySumRgData != null) {
								quantityR = qtySumRgData;
							}
							date1 = new SimpleDateFormat("yyyy-MM-dd").parse(cm1);
							date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cm2);
							diff = Utilities.daysBetween(date1, date2);
							d = diff;
							if (quantityR < quantityCons)
								a = (quantityCons - quantityR) / d;
							else
								a = 0.0f;

						} // year end

						if (u[0].equalsIgnoreCase("Nos")) {
							a = (float) Math.round(a);
						} else {
							a = Utilities.round(a, 1);
						}

						if (empDataSession == null) {
							return ("redirect:/logout");
						}
						// HashMap<String, Object> hashMap = new HashMap<String, Object>();
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("item", new String(productName));
						jsonObject.put("quantity", new Float(a));
						jsonObject.put("units", new String(unitP));
						jsonObject.put("status", new String(status));
						jsonObject.put("filledValue", new Float(filledValue));
						jsonObject.put("extended", new String("Consent validity is extended"));
						jsonArray.put(jsonObject);

					} // prodNameList for loop
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray.toString();
	}

	/**
	 * This method is used to open daily input form
	 * 
	 * @param request the servlet request we are processing.
	 * @return it returns RegularDailyData model
	 * @throws ParseException if parsing causes an error
	 */
	@RequestMapping("regular-daily-data")
	public ModelAndView getRegularDailyData(HttpServletRequest request) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("EnvrOfficer/RegularDailyData");
		return modelAndView;
	}

	/**
	 * This method used to upload Daily data sheet
	 * 
	 * @param regularFile the uploaded daily data file.
	 * @param request     the servlet request we are processing.
	 * @return msg it returns success or fail
	 */
	@SuppressWarnings("finally")
	@PostMapping("ajax-dailydata-sheet-upload")
	public @ResponseBody String getExcelSheetForDailyInput(@RequestParam("file") MultipartFile regularFile,
			HttpServletRequest request) {
		String fileUploadingMsg = null;
		int regularCounter = 0;
		String msg = SUCCESS;
		try {
			Files.write(Paths.get(DAILY_DATA_FILE + regularFile.getOriginalFilename()), regularFile.getBytes());
			fileUploadingMsg = Utilities.uploadFile(DAILY_DATA_FILE, regularFile.getSize());

			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int userId = empDataSession.getUsers().getUsersId();

			InputStream regularDataExcelFileToRead = new FileInputStream(
					DAILY_DATA_FILE + regularFile.getOriginalFilename());
			XSSFWorkbook regularDataWorkbook = new XSSFWorkbook(regularDataExcelFileToRead);
			XSSFSheet regularDataSheet = regularDataWorkbook.getSheetAt(0);
			Iterator<Row> regularDataIterator = regularDataSheet.iterator();

			while (regularDataIterator.hasNext()) {
				Row regularDataRow = regularDataIterator.next();
				if (regularDataRow.getRowNum() != 0) {
					RegularData objRegularData = new RegularData();
					int allProductComparativeId = allProductComparativeSheetServices
							.findComparativeSheetIdByPName(regularDataRow.getCell(0).toString());
					// Error raise if 1.File data is not match with 2.worng data
					// 3.NumberFormatException
					// 4.if product(Data) is already exit with same date
					// 5.if product name in excel sheet is not exist in db ....by vishal
					objRegularData.setRegularDataDetails(regularDataRow, allProductComparativeId, userId);
					regularDataServices.save(objRegularData);
					regularCounter++;
				}
			}
			if (regularCounter != 0 && regularDataSheet.getLastRowNum() != 0) {
				if (regularCounter == regularDataSheet.getLastRowNum()) {
					// stack is uploaded successfully
					fileUploadingMsg += "<br>Stack details saved successfully";
					msg = SUCCESS;
				} else {
					fileUploadingMsg += "<br>Incorrect Data in file.";
					msg = FAILURE;
				}
			} else {
				fileUploadingMsg += "<br>No data Available";
			}
			return msg + "-" + fileUploadingMsg;
		} catch (Exception e) {
			LOGGER.error(e);
			return "";
		}
	}

	@RequestMapping("/ajax-getRegAPCData")
	public @ResponseBody String GetStackList() {
		JSONArray jsonArray = new JSONArray();
		try {
			int stackId = 0;
			String apc = "";
			String today = Utilities.getTodaysDate();
			String status = "";
			List<Stack> stackData = new ArrayList<>();
			List<RegAPC> regapc = new ArrayList<>();
			List<Stack> stackDataList = new ArrayList<>();

			stackData = stackServices.findStackData(today);
			for (int i = 0; i < stackData.size(); i++) {
				apc = stackData.get(i).getApc();
				Stack stack = new Stack();
				stack = stackData.get(i);
				stackId = stack.getStackId();
				if (apc.equalsIgnoreCase("Yes")) {
					regapc = regAPCServices.getRegAPCData(stackId, new PageRequest(0, 1));
					if (Validator.isEmpty(regapc))
						regapc = null;
					else if (!Validator.isEmpty(regapc)) {
						stack.setRegAPCList(regapc);
					}
				}
				stackDataList.add(stack);
			}
			Float startReading = 0.0f;
			Float endReading = 0.0f;
			Float actualReading = 0.0f;
			for (int i = 0; i < stackDataList.size(); i++) {

				String apcSysName = stackDataList.get(i).getApcSystem();
				int stackIdd = stackDataList.get(i).getStackId();

				List<RegAPC> regapc1 = new ArrayList<>();
				regapc1 = stackDataList.get(i).getRegAPCList();
				if (regapc1.size() > 0) {

					for (int j = 0; j < regapc1.size(); j++) {

						String regApcDate = stackDataList.get(i).getRegAPCList().get(j).getApcDate();

						startReading = stackDataList.get(i).getRegAPCList().get(j).getStartReading();
						endReading = stackDataList.get(i).getRegAPCList().get(j).getEndReading();
						actualReading = stackDataList.get(i).getRegAPCList().get(j).getActualReading();

						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("stackId", new Integer(stackIdd));
						hashMap.put("apcSysName", new String(apcSysName));

						if (regApcDate.equalsIgnoreCase(today)) {
							status = "Filled";
							hashMap.put("status", new String(status));
							hashMap.put("startReading", new Float(startReading));
							hashMap.put("endReading", new Float(endReading));
							hashMap.put("actualReading", new Float(actualReading));

						} else {
							status = "Fill";
							hashMap.put("status", new String(status));
							hashMap.put("endReading", new Float(endReading));
						}

						jsonArray.put(hashMap);
					}

				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	@RequestMapping("/ajax-getDailyWaterSource")
	public @ResponseBody String getDailyWaterSource(HttpServletRequest request) {

		List<WaterInventory> waterInventoryData = new ArrayList<>();
		ArrayList<Object> waterInventoryList = new ArrayList<>();
		List<WaterSource> waterSourceData = new ArrayList<>();
		List<RegWaterSourceData> regularSourceData = new ArrayList<>();
		List<RegWaterSourceData> regularSourceDataList = new ArrayList<>();
		List<WaterSource> waterSourceList = new ArrayList<>();
		int companyId = 0, cnt1 = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0, cnt5 = 0, inputResult = 0;
		EmpData empDataSession = null;
		String regSourceDate = null;
		JSONObject waterSourceObject = new JSONObject();
		JSONArray waterSourceArray = new JSONArray();
		JSONObject waterSourceDataObject = new JSONObject();
		try {
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			}
			companyId = empDataSession.getCompanyProfile().getCompanyProfileId();
			waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId, Utilities.getTodaysDate());

			if (!Validator.isEmpty(waterInventoryData)) {
				for (int i = 0; i < waterInventoryData.size(); i++) {
					WaterInventory wiDetail = new WaterInventory();
					wiDetail = waterInventoryData.get(i);
					waterSourceData = waterSourceServices.getWaterSourceData(wiDetail.getWaterInventoryId());

					if (!Validator.isEmpty(waterSourceData)) {
						for (int j = 0; j < waterSourceData.size(); j++) {
							WaterSource watersourceDetail = new WaterSource();
							watersourceDetail = waterSourceData.get(j);
							regularSourceData = regularSourceDataServices.regularSourceDataBySourceId(
									watersourceDetail.getWaterSourceId(), new PageRequest(0, 1));
							if (!regularSourceDataList.containsAll(regularSourceData)) {
								regularSourceDataList.addAll(regularSourceData);
							}
							watersourceDetail.setRegularSourceDataList(regularSourceData);
							if (!waterSourceList.contains(watersourceDetail)) {
								waterSourceList.add(watersourceDetail);
							}
						}
					}
					wiDetail.setWaterSourceList(waterSourceList);

					if (!Validator.isEmpty(wiDetail.getWaterSourceList())) {
						for (WaterSource waterSource : wiDetail.getWaterSourceList()) {
							HashMap<String, Object> wiList = new HashMap<String, Object>();
							if (!Validator.isEmpty(waterSource.getRegularSourceDataList())) {
								wiList.put("wsId", waterSource.getWaterSourceId());
								// timestamp in string
								Timestamp regWaterSourceDate = waterSource.getRegularSourceDataList().get(0)
										.getCreateDateTime();
								if (!Validator.isEmpty(regWaterSourceDate)) {
									regSourceDate = Utilities.getDateinStringFromTimestamp(regWaterSourceDate);
									if ((regSourceDate.equals(Utilities.getTodaysDate()))
											&& (waterSource.isSourceMeter() == true)) {
										cnt1 = 0;
										wiList.put("status", "Filled");
										wiList.put("sourceMeter", waterSource.isSourceMeter());
										wiList.put("noOfStaff",
												waterSource.getRegularSourceDataList().get(0).getStaff());
										wiList.put("sourceName", waterSource.getSourceName());
										wiList.put("startReading",
												waterSource.getRegularSourceDataList().get(0).getStartReading());
										wiList.put("endReading",
												waterSource.getRegularSourceDataList().get(0).getEndReading());
										wiList.put("actualReading",
												waterSource.getRegularSourceDataList().get(0).getActualReading());

									} else if (!(regSourceDate.equals(Utilities.getTodaysDate()))
											&& (waterSource.isSourceMeter() == true)) {
										cnt2 = 1;
										wiList.put("status", "Fill");
										wiList.put("sourceMeter", waterSource.isSourceMeter());
										wiList.put("noOfStaff",
												waterSource.getRegularSourceDataList().get(0).getStaff());
										wiList.put("sourceName", waterSource.getSourceName());
										wiList.put("startReading", "");
										wiList.put("endReading",
												waterSource.getRegularSourceDataList().get(0).getEndReading());
										wiList.put("actualReading", "");

									} else if (!(regSourceDate.equals(Utilities.getTodaysDate()))
											&& (waterSource.isSourceMeter() == false)) {
										cnt3 = 1;
										wiList.put("status", "Fill");
										wiList.put("sourceMeter", waterSource.isSourceMeter());
										wiList.put("noOfStaff",
												waterSource.getRegularSourceDataList().get(0).getStaff());
										wiList.put("sourceName", waterSource.getSourceName());
										wiList.put("startReading", "");
										wiList.put("endReading", "");
										wiList.put("actualReading", "");

									} else if ((regSourceDate.equals(Utilities.getTodaysDate()))
											&& (waterSource.isSourceMeter() == false)) {
										cnt4 = 0;
										wiList.put("status", "Filled");
										wiList.put("sourceMeter", waterSource.isSourceMeter());
										wiList.put("noOfStaff",
												waterSource.getRegularSourceDataList().get(0).getStaff());
										wiList.put("sourceName", waterSource.getSourceName());
										wiList.put("startReading", "");
										wiList.put("endReading", "");
										wiList.put("actualReading",
												waterSource.getRegularSourceDataList().get(0).getActualReading());
									}

								}
							} else {
								cnt5 = 1;
								wiList.put("wsId", waterSource.getWaterSourceId());
								wiList.put("status", "New");
								wiList.put("sourceMeter", waterSource.isSourceMeter());
								wiList.put("sourceName", waterSource.getSourceName());
								wiList.put("noOfStaff", "");
								wiList.put("startReading", "");
								wiList.put("endReading", "");
								wiList.put("actualReading", "");
							}

							if (!waterInventoryList.contains(wiList)) {
								waterInventoryList.add(wiList);
							}
						}
						inputResult = cnt1 | cnt2 | cnt3 | cnt4 | cnt5;
						if (inputResult == 1) {
							waterSourceDataObject.put("Input", true);
						} else {
							waterSourceDataObject.put("Input", false);
						}
						waterSourceObject.put("Result", true);
						waterSourceDataObject.put("regularSourceDataList", waterInventoryList);
					} else {
						waterSourceObject.put("Result", false);
					}
				}
				waterSourceObject.put("Data", waterSourceDataObject);
			} else {
				waterSourceObject.put("Data", waterSourceDataObject);
				waterSourceObject.put("Result", false);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		waterSourceArray.put(waterSourceObject);
		return waterSourceArray.toString();
	}

	@RequestMapping("/ajax-getDailyPreFilterData")
	public @ResponseBody String getDailyPrefilterData(HttpServletRequest request) {
		List<WaterInventory> waterInventoryData = new ArrayList<>();
		ArrayList<Object> waterInventoryList = new ArrayList<>();
		List<WaterSource> waterSourceData = new ArrayList<>();
		List<WaterSource> waterSourceDataList = new ArrayList<>();
		List<Prefilter> prefilterData = new ArrayList<>();
		List<RegPrefilter> regPrefilterData = new ArrayList<>();
		JSONObject prefilterObject = new JSONObject();
		JSONObject prefilterDataObject = new JSONObject();
		JSONArray prefilterArray = new JSONArray();
		EmpData empDataSession = null;
		String regpfDate = null;
		int companyId = 0, cnt = 0, cnt1 = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0, cnt5 = 0, inputResult = 0;
		try {
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			}
			companyId = empDataSession.getCompanyProfile().getCompanyProfileId();
			waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId, Utilities.getTodaysDate());

			if (!Validator.isEmpty(waterInventoryData)) {
				for (int i = 0; i < waterInventoryData.size(); i++) {
					WaterInventory wiDetail = new WaterInventory();
					wiDetail = waterInventoryData.get(i);
					waterSourceData = waterSourceServices
							.getWaterSourceData(waterInventoryData.get(i).getWaterInventoryId());
					if (!Validator.isEmpty(waterSourceData)) {
						for (int j = 0; j < waterSourceData.size(); j++) {
							WaterSource watersourceDetail = new WaterSource();
							watersourceDetail = waterSourceData.get(j);
							prefilterData = prefilterServices
									.getAllActivePrefilterData(watersourceDetail.getWaterSourceId());
							List<Prefilter> prefilterDataList = new ArrayList<>();

							if (!Validator.isEmpty(prefilterData)) {
								for (int k = 0; k < prefilterData.size(); k++) {
									Prefilter prefilterDetail = new Prefilter();
									prefilterDetail = prefilterData.get(k);
									regPrefilterData = regPrefilterServices.getregPrefilterDataByPrefilterId(
											prefilterData.get(k).getPrefilterId(), new PageRequest(0, 1));
									prefilterDetail.setRegPrefilterList(regPrefilterData);
									if (!prefilterDataList.contains(prefilterDetail)) {
										prefilterDataList.add(prefilterDetail);
									}
								}
							}
							if (!waterSourceDataList.contains(watersourceDetail)) {
								waterSourceDataList.add(watersourceDetail);
							}
							watersourceDetail.setPrefilterList(prefilterDataList);
						}
					}
					wiDetail.setWaterSourceList(waterSourceDataList);
					if (!Validator.isEmpty(wiDetail.getWaterSourceList())) {
						for (WaterSource waterSource : wiDetail.getWaterSourceList()) {
							HashMap<String, Object> wiList = new HashMap<String, Object>();
							if (!Validator.isEmpty(waterSource.getPrefilterList())) {
								cnt++;
								prefilterObject.put("Result", true);
								for (Prefilter prefilter : waterSource.getPrefilterList()) {
									wiList.put("sourceName", waterSource.getSourceName());
									wiList.put("isMeter", prefilter.isMeter());
									wiList.put("count", cnt);
									wiList.put("prefilterId", prefilter.getPrefilterId());

									if (!Validator.isEmpty(prefilter.getRegPrefilterList())) {
										for (RegPrefilter regprefilter : prefilter.getRegPrefilterList()) {
											Timestamp rpfDate = regprefilter.getCreateDateTime();
											if (!Validator.isEmpty(rpfDate)) {
												regpfDate = Utilities.getDateinStringFromTimestamp(rpfDate);
												if (regpfDate.equals(Utilities.getTodaysDate())) {
													if (prefilter.isMeter() == true) {
														cnt1 = 0;
														wiList.put("status", "Filled");
														wiList.put("startReading", regprefilter.getStartReading());
														wiList.put("endReading", regprefilter.getEndReading());
														wiList.put("actualReading", regprefilter.getActualReading());
													} else {
														cnt2 = 0;
														wiList.put("status", "Filled");
														wiList.put("startReading", "");
														wiList.put("endReading", "");
														wiList.put("actualReading", regprefilter.getActualReading());
													}
												} else {
													if (prefilter.isMeter() == true) {
														cnt3 = 1;
														wiList.put("status", "Fill");
														wiList.put("startReading", regprefilter.getEndReading());
														wiList.put("endReading", "");
														wiList.put("actualReading", "");
													} else {
														cnt4 = 1;
														wiList.put("status", "Fill");
														wiList.put("startReading", "");
														wiList.put("endReading", "");
														wiList.put("actualReading", "");
													}
												}
											}
										}
									} else {
										cnt5 = 1;
										wiList.put("status", "New");
										wiList.put("startReading", "");
										wiList.put("endReading", "");
										wiList.put("actualReading", "");
									}
								}
								inputResult = cnt1 | cnt2 | cnt3 | cnt4 | cnt5;
								if (inputResult == 1) {
									prefilterDataObject.put("Input", true);
								} else {
									prefilterDataObject.put("Input", false);
								}
								if (!waterInventoryList.contains(wiList)) {
									waterInventoryList.add(wiList);
								}
								prefilterDataObject.put("prefilterList", waterInventoryList);
							} else {
								prefilterObject.put("Result", false);
							}
						}
					} else {
						prefilterObject.put("Result", false);
					}
				}
				prefilterObject.put("Data", prefilterDataObject);
			} else {
				prefilterObject.put("Data", prefilterDataObject);
				prefilterObject.put("Result", false);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		prefilterArray.put(prefilterObject);
		return prefilterArray.toString();
	}

	@RequestMapping(value = "ajax-save-regular-prefilter-data", method = RequestMethod.POST)
	public @ResponseBody String saveRegularPrefilterData(@RequestBody JsonObject prefilterObj,
			HttpServletRequest request) throws ParseException {
		String result = "";
		try {
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			if (!Validator.isEmpty(empDataSession)) {
				Users users = new Users();
				users.setUsersId(empDataSession.getUsers().getUsersId());
				if (!Validator.isEmpty(prefilterObj)) {
					JsonArray regPrefilterArray = prefilterObj.getAsJsonArray("regPrefilterData");
					if (!Validator.isEmpty(regPrefilterArray)) {
						for (JsonElement regPrefilterData : regPrefilterArray) {
							RegPrefilter regPrefilter = new RegPrefilter();
							Prefilter prefilter = new Prefilter();
							prefilter.setPrefilterId(regPrefilterData.getAsJsonObject().get("prefilterId").getAsInt());
							regPrefilter.setPrefilter(prefilter);
							regPrefilter.setStartReading(
									regPrefilterData.getAsJsonObject().get("prefilterStart").getAsFloat());
							regPrefilter
									.setEndReading(regPrefilterData.getAsJsonObject().get("prefilterEnd").getAsFloat());
							regPrefilter.setActualReading(
									regPrefilterData.getAsJsonObject().get("prefilterAvg").getAsFloat());
							regPrefilter.setUsers(users);
							regPrefilter = regPrefilterServices.save(regPrefilter);
							if (regPrefilter != null) {
								result = "success";
							} else {
								result = "fail";
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return result;
	}

	@RequestMapping("/ajax-getDailyFilterData")
	public @ResponseBody String getDailyFilterWater(HttpServletRequest request) {
		List<WaterInventory> waterInventoryData = new ArrayList<>();
		List<MultipleFilter> filterData = new ArrayList<>();
		List<MultipleFilter> filterDataList = new ArrayList<>();
		ArrayList<Object> filterList = new ArrayList<>();
		List<RegMultipleFilterData> regMultipleFilterData = new ArrayList<>();
		JSONArray filterArray = new JSONArray();
		JSONObject filterObject = new JSONObject();
		JSONObject filterDataObject = new JSONObject();
		EmpData empDataSession = null;
		String filterDailyDate = null;
		boolean isMeter = false;
		int companyId = 0, cnt1 = 0, cnt2 = 0, cnt3 = 0;
		try {
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			}
			companyId = empDataSession.getCompanyProfile().getCompanyProfileId();
			waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId, Utilities.getTodaysDate());
			if (!Validator.isEmpty(waterInventoryData)) {
				for (int i = 0; i < waterInventoryData.size(); i++) {
					WaterInventory wiDetail = new WaterInventory();
					wiDetail = waterInventoryData.get(i);
					filterData = multipleFilterServices.findAllMultipleFiltersData(wiDetail.getWaterInventoryId());
					if (!Validator.isEmpty(filterData)) {
						for (int j = 0; j < filterData.size(); j++) {
							MultipleFilter multipleFilter = new MultipleFilter();
							multipleFilter = filterData.get(j);
							regMultipleFilterData = regMultipleFilterDataServices
									.getRegularFiltersData(multipleFilter.getMultipleFilterId(), new PageRequest(0, 1));
							multipleFilter.setRegMultipleFilterData(regMultipleFilterData);
							if (!filterDataList.contains(multipleFilter)) {
								filterDataList.add(multipleFilter);
							}
						}
					}
					wiDetail.setMultipleFiltersData(filterDataList);
					if (!Validator.isEmpty(wiDetail.getMultipleFiltersData())) {
						filterObject.put("Result", true);
						for (MultipleFilter multipleFilter : wiDetail.getMultipleFiltersData()) {
							HashMap<String, Object> filterMap = new HashMap<>();
							isMeter = multipleFilter.isMeter();
							filterMap.put("filterId", multipleFilter.getMultipleFilterId());
							filterMap.put("filterName", multipleFilter.getFilterLabel() + " " + "("
									+ multipleFilter.getFilters().getFilterType() + ")");
							filterMap.put("isMeter", multipleFilter.isMeter());
							filterMap.put("filterType", multipleFilter.getFilters().getFilterType());
							if (!Validator.isEmpty(multipleFilter.getRegMultipleFilterData())) {
								Timestamp filterDate = multipleFilter.getRegMultipleFilterData().get(0)
										.getCreateDateTime();
								if (!Validator.isEmpty(filterDate)) {
									filterDailyDate = Utilities.getDateinStringFromTimestamp(filterDate);
									if (filterDailyDate.equalsIgnoreCase(Utilities.getTodaysDate())) {
										cnt1 = 0;
										if (isMeter == true) {
											filterMap.put("startReading",
													multipleFilter.getRegMultipleFilterData().get(0).getStartReading());
											filterMap.put("endReading",
													multipleFilter.getRegMultipleFilterData().get(0).getEndReading());
											filterMap.put("actualConsumtion", multipleFilter.getRegMultipleFilterData()
													.get(0).getActualReading());
											filterMap.put("status", "Filled");
										} else {
											filterMap.put("startReading", "");
											filterMap.put("endReading", "");
											filterMap.put("actualConsumtion", multipleFilter.getRegMultipleFilterData()
													.get(0).getActualReading());
											filterMap.put("status", "Filled");
										}
									} else {
										cnt2 = 1;
										if (isMeter == true) {
											filterMap.put("startReading",
													multipleFilter.getRegMultipleFilterData().get(0).getEndReading());
											filterMap.put("endReading", "");
											filterMap.put("actualConsumtion", "");
											filterMap.put("status", "Fill");
										} else {
											filterMap.put("startReading", "");
											filterMap.put("endReading", "");
											filterMap.put("actualConsumtion", multipleFilter.getRegMultipleFilterData()
													.get(0).getActualReading());
											filterMap.put("status", "Fill");
										}
									}
								}
							} else {
								cnt3 = 1;
								filterMap.put("startReading", "");
								filterMap.put("endReading", "");
								filterMap.put("actualConsumtion", "");
								filterMap.put("status", "New");
							}
							if (!filterList.contains(filterMap)) {
								filterList.add(filterMap);
							}
						}
						int inputResult = cnt1 | cnt2 | cnt3;
						if (inputResult == 1) {
							filterDataObject.put("Input", true);
						} else {
							filterDataObject.put("Input", false);
						}
						filterDataObject.put("UiOption", "Advanced");
						filterDataObject.put("filterList", filterList);
					} else {
						filterDataObject.put("filterList", filterList);
						filterObject.put("Result", false);
					}
				}
				filterObject.put("Data", filterDataObject);
			} else {
				filterObject.put("Data", filterDataObject);
				filterObject.put("Result", false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		filterArray.put(filterObject);
		return filterArray.toString();
	}

	@RequestMapping(value = "ajax-save-regular-filter-data", method = RequestMethod.POST)
	public @ResponseBody String saveRegularFilterData(@RequestBody JsonObject filterObj, HttpServletRequest request)
			throws ParseException {
		String result = "";
		try {
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			if (!Validator.isEmpty(empDataSession)) {
				Users users = new Users();
				users.setUsersId(empDataSession.getUsers().getUsersId());
				if (!Validator.isEmpty(filterObj)) {
					JsonArray regFilterArray = filterObj.getAsJsonArray("regfilterData");
					if (!Validator.isEmpty(regFilterArray)) {
						for (JsonElement regFilterData : regFilterArray) {
							RegMultipleFilterData regMultipleFilterData = new RegMultipleFilterData();
							MultipleFilter multipleFilter = new MultipleFilter();
							multipleFilter
									.setMultipleFilterId(regFilterData.getAsJsonObject().get("filterId").getAsInt());
							regMultipleFilterData.setMultipleFilter(multipleFilter);
							regMultipleFilterData
									.setStartReading(regFilterData.getAsJsonObject().get("filterStart").getAsFloat());
							regMultipleFilterData
									.setEndReading(regFilterData.getAsJsonObject().get("filterEnd").getAsFloat());
							regMultipleFilterData
									.setActualReading(regFilterData.getAsJsonObject().get("filterAvg").getAsFloat());
							regMultipleFilterData.setUsers(users);
							regMultipleFilterData = regMultipleFilterDataServices.save(regMultipleFilterData);
							if (regMultipleFilterData != null) {
								result = "success";
							} else {
								result = "fail";
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return result;
	}

	@RequestMapping("/ajax-getDailyFilterUseData")
	public @ResponseBody String getDailyFilterUseWater(HttpServletRequest request) {
		List<String> filterUseLabelList = new ArrayList<>();
		List<FilterUse> filterUseData = new ArrayList<>();
		List<FilterUse> filterUseDataList = new ArrayList<>();
		ArrayList<Object> filterUseList = new ArrayList<>();
		List<RegFiltersUseData> regFiltersUseDataList = new ArrayList<>();
		JSONArray filterUseArray = new JSONArray();
		JSONObject filterUseObject = new JSONObject();
		JSONObject filterUseDataObject = new JSONObject();
		EmpData empDataSession = null;

		int cnt1 = 0, cnt2 = 0, cnt3 = 0, companyId = 0;
		List<WaterInventory> waterInventoryGetId = new ArrayList<>();
		
		try {
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				companyId = empDataSession.getCompanyProfile().getCompanyProfileId();

			}
			//waterInventoryId.get(1).getWaterInventoryId();
			waterInventoryGetId = waterInventoryServices.getWaterInventorygetId(companyId, Utilities.getTodaysDate(),new PageRequest(0,1));

			if (!Validator.isEmpty(waterInventoryGetId)) {
				filterUseLabelList = filterUseServices.getFilterUseData();

				if (!Validator.isEmpty(filterUseLabelList)) {
					for (int i = 0; i < filterUseLabelList.size(); i++) {
						filterUseData = filterUseServices.getFilterUseDataByLabel(filterUseLabelList.get(i));
						if (!Validator.isEmpty(filterUseData)) {
							for (int j = 0; j < filterUseData.size(); j++) {
								FilterUse filterUse = new FilterUse();
								filterUse = filterUseData.get(j);
								regFiltersUseDataList = regFiltersUseDataServices.getRegFiltersUseDataByFilterUseLabel(
										filterUse.getFilterUseLabel(), new PageRequest(0, 1));
								filterUse.setRegFiltersUseData(regFiltersUseDataList);
								if (!filterUseDataList.contains(filterUse)) {
									filterUseDataList.add(filterUse);
								}
							}
						}
					}
					if (!Validator.isEmpty(filterUseDataList)) {
						filterUseObject.put("Result", true);
						for (FilterUse filterUse : filterUseDataList) {
							HashMap<String, Object> filterUseMap = new HashMap<>();
							filterUseMap.put("filterUseLabel", filterUse.getFilterUseLabel());
							filterUseMap.put("filterUseType", filterUse.getFilterUseType());
							filterUseMap.put("isMeter", filterUse.isMeter());
							if (!Validator.isEmpty(filterUse.getRegFiltersUseData())) {
								for (RegFiltersUseData regFiltersUseData : filterUse.getRegFiltersUseData()) {
									Timestamp filterUseDate = regFiltersUseData.getCreateDateTime();
									if (!Validator.isEmpty(filterUseDate)) {
										String filterUseDailyDate = Utilities
												.getDateinStringFromTimestamp(filterUseDate);
										if (filterUseDailyDate.equalsIgnoreCase(Utilities.getTodaysDate())) {
											cnt1 = 0;
											filterUseMap.put("status", "Filled");
											if (filterUse.isMeter() == true) {
												filterUseMap.put("startReading", regFiltersUseData.getStartReading());
												filterUseMap.put("endReading", regFiltersUseData.getEndReading());
												filterUseMap.put("actualReading", regFiltersUseData.getActualReading());
											} else {
												filterUseMap.put("startReading", "");
												filterUseMap.put("endReading", "");
												filterUseMap.put("actualReading", regFiltersUseData.getActualReading());
											}

										} else {
											cnt2 = 1;
											filterUseMap.put("status", "Fill");
											if (filterUse.isMeter() == true) {
												filterUseMap.put("startReading", regFiltersUseData.getEndReading());
												filterUseMap.put("endReading", "");
												filterUseMap.put("actualReading", "");
											} else {
												filterUseMap.put("startReading", "");
												filterUseMap.put("endReading", "");
												filterUseMap.put("actualReading", "");
											}
										}
									}
								}
							} else {
								cnt3 = 1;
								filterUseMap.put("status", "New");
								if (filterUse.isMeter() == true) {
									filterUseMap.put("startReading", "");
									filterUseMap.put("endReading", "");
									filterUseMap.put("actualReading", "");
								} else {
									filterUseMap.put("startReading", "");
									filterUseMap.put("endReading", "");
									filterUseMap.put("actualReading", "");
								}
							}
							if (!filterUseList.contains(filterUseMap)) {
								filterUseList.add(filterUseMap);
							}
							int inputResult = cnt1 | cnt2 | cnt3;
							if (inputResult == 1) {
								filterUseDataObject.put("Input", true);
							} else {
								filterUseDataObject.put("Input", false);
							}
						}
						filterUseDataObject.put("UiOption", "Normal");
						filterUseDataObject.put("filterUseList", filterUseList);
						filterUseObject.put("Data", filterUseDataObject);
					} else {
						filterUseObject.put("Data", filterUseDataObject);
						filterUseObject.put("Result", false);
					}

				} else {
					filterUseObject.put("Data", filterUseDataObject);
					filterUseObject.put("Result", false);
				}
			} else {
				filterUseObject.put("Data", filterUseDataObject);
				filterUseObject.put("Result", false);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}

		filterUseArray.put(filterUseObject);
		return filterUseArray.toString();
	}

	@RequestMapping(value = "ajax-save-regular-filter-use-data", method = RequestMethod.POST)
	public @ResponseBody String saveRegularFilterUseData(@RequestBody JsonObject filterUseObj,
			HttpServletRequest request) throws ParseException {
		String result = "";
		try {
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			if (!Validator.isEmpty(empDataSession)) {
				Users users = new Users();
				users.setUsersId(empDataSession.getUsers().getUsersId());
				if (!Validator.isEmpty(filterUseObj)) {
					JsonArray regFilterUseArray = filterUseObj.getAsJsonArray("regfilterUseData");
					if (!Validator.isEmpty(regFilterUseArray)) {
						for (JsonElement regFilterUse : regFilterUseArray) {
							RegFiltersUseData regFilterUseData = new RegFiltersUseData();
							regFilterUseData.setFilterUseLabel(
									regFilterUse.getAsJsonObject().get("filterUseLabel").getAsString());
							regFilterUseData
									.setStartReading(regFilterUse.getAsJsonObject().get("filterUseStart").getAsFloat());
							regFilterUseData
									.setEndReading(regFilterUse.getAsJsonObject().get("filterUseEnd").getAsFloat());
							regFilterUseData
									.setActualReading(regFilterUse.getAsJsonObject().get("filterUseAvg").getAsFloat());
							regFilterUseData.setUsers(users);
							regFilterUseData = regFiltersUseDataServices.save(regFilterUseData);
							if (regFilterUseData != null) {
								result = "success";
							} else {
								result = "fail";
							}
						}
					}
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

	@RequestMapping("/ajax-getDailyUseOfWaterData")
	public @ResponseBody String getDailyUseOfWater(HttpServletRequest request) {
		EmpData empDataSession = null;
		TreeSet<String> directUseName = new TreeSet<>();
		List<DirectUseOfWater> directUseOfWaterData = new ArrayList<>();
		List<DirectUseOfWater> directUseOfWaterDataList = new ArrayList<>();
		ArrayList<Object> directUseOfWaterList = new ArrayList<>();
		List<RegDirectUseOfWaterData> regDirectUseOfWaterDataList = new ArrayList<>();
		JSONArray directUseOfWaterArray = new JSONArray();
		JSONObject directUseOfWaterObject = new JSONObject();
		JSONObject directUseOfWaterDataObject = new JSONObject();
		List<WaterInventory> waterInventoryData = new ArrayList<>();
		int cnt1 = 0, cnt2 = 0, cnt3 = 0, companyId = 0, waterInventoryId = 0;
		try {
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			}
			companyId = empDataSession.getCompanyProfile().getCompanyProfileId();
			waterInventoryData = waterInventoryServices.getwaterInventoryById(companyId, Utilities.getTodaysDate(),new PageRequest(0, 1));
			// waterInventoryId = waterInventoryServices.getWaterInventoryId(companyId,Utilities.getTodaysDate(),new PageRequest(0, 1));
			// Utilities.getTodaysDate());
			if (!Validator.isEmpty(waterInventoryData)) {
				for (int k = 0; k < waterInventoryData.size(); k++) {
					WaterInventory wiDetail = new WaterInventory();
					wiDetail = waterInventoryData.get(k);
					int id = wiDetail.getWaterInventoryId();

					directUseName = directUseOfWaterServices.getDirectUseName(id);
					if (!Validator.isEmpty(directUseName)) {
						for (String directUse : directUseName) {
							directUseOfWaterData = directUseOfWaterServices.getUseOfWaterByWhereToUse(directUse);
							if (!Validator.isEmpty(directUseOfWaterData)) {
								for (int i = 0; i < directUseOfWaterData.size(); i++) {
									DirectUseOfWater directUseOfWater = new DirectUseOfWater();
									directUseOfWater = directUseOfWaterData.get(i);
									regDirectUseOfWaterDataList = regDirectUseOfWaterServices
											.getRegDirectUseOfWaterData(directUseOfWater.getWhereToUse(),
													new PageRequest(0, 1));
									if (!Validator.isEmpty(regDirectUseOfWaterDataList)) {
										directUseOfWater.setRegDirectUseOfWaterData(regDirectUseOfWaterDataList);
									}
									if (!directUseOfWaterDataList.contains(directUseOfWater)) {
										directUseOfWaterDataList.add(directUseOfWater);
									}
								}
							}
						}
					}
					if (!Validator.isEmpty(directUseOfWaterDataList)) {
						directUseOfWaterObject.put("Result", true);
						for (DirectUseOfWater directUseOfWater : directUseOfWaterDataList) {
							HashMap<String, Object> directUseOfWaterMap = new HashMap<>();
							directUseOfWaterMap.put("typeOfUse", directUseOfWater.getTypeOfUse());
							directUseOfWaterMap.put("whereToUse", directUseOfWater.getWhereToUse());
							directUseOfWaterMap.put("isMeter", directUseOfWater.isMeter());
							if (!Validator.isEmpty(directUseOfWater.getRegDirectUseOfWaterData())) {
								for (RegDirectUseOfWaterData regDirectUseOfWaterData : directUseOfWater
										.getRegDirectUseOfWaterData()) {
									Timestamp regDirectUseOfWaterDate = regDirectUseOfWaterData.getCreateDateTime();
									if (!Validator.isEmpty(regDirectUseOfWaterDate)) {
										String regDirectUseDailyDate = Utilities
												.getDateinStringFromTimestamp(regDirectUseOfWaterDate);
										if (!Validator.isEmpty(regDirectUseDailyDate)) {
											if (regDirectUseDailyDate.equalsIgnoreCase(Utilities.getTodaysDate())) {
												cnt1 = 0;
												directUseOfWaterMap.put("status", "Filled");

												if (directUseOfWater.isMeter() == true) {
													directUseOfWaterMap.put("startReading",
															regDirectUseOfWaterData.getStartReading());
													directUseOfWaterMap.put("endReading",
															regDirectUseOfWaterData.getEndReading());
													directUseOfWaterMap.put("actualReading",
															regDirectUseOfWaterData.getActualReading());

												} else {
													directUseOfWaterMap.put("startReading", "");
													directUseOfWaterMap.put("endReading", "");
													directUseOfWaterMap.put("actualReading",
															regDirectUseOfWaterData.getActualReading());
												}
											} else {
												cnt2 = 1;
												directUseOfWaterMap.put("status", "Fill");
												if (directUseOfWater.isMeter() == true) {
													directUseOfWaterMap.put("startReading",
															regDirectUseOfWaterData.getEndReading());
													directUseOfWaterMap.put("endReading", "");
													directUseOfWaterMap.put("actualReading", "");

												} else {
													directUseOfWaterMap.put("startReading", "");
													directUseOfWaterMap.put("endReading", "");
													directUseOfWaterMap.put("actualReading", "");
												}
											}
										}
									}
								}

							} else {
								cnt3 = 1;
								directUseOfWaterMap.put("status", "New");
								if (directUseOfWater.isMeter() == true) {
									directUseOfWaterMap.put("startReading", "");
									directUseOfWaterMap.put("endReading", "");
									directUseOfWaterMap.put("actualReading", "");

								} else {
									directUseOfWaterMap.put("startReading", "");
									directUseOfWaterMap.put("endReading", "");
									directUseOfWaterMap.put("actualReading", "");
								}
							}
							if (!directUseOfWaterList.contains(directUseOfWaterMap)) {
								directUseOfWaterList.add(directUseOfWaterMap);
							}
						}
						int inputResult = cnt1 | cnt2 | cnt3;
						if (inputResult == 1) {
							directUseOfWaterDataObject.put("Input", true);
						} else {
							directUseOfWaterDataObject.put("Input", false);
						}
						directUseOfWaterDataObject.put("useOfWaterData", directUseOfWaterList);
						directUseOfWaterDataObject.put("UiOption", "Normal");
						directUseOfWaterObject.put("Data", directUseOfWaterDataObject);
					} else {
						directUseOfWaterObject.put("Result", false);
					}
				}
			} else {
				directUseOfWaterObject.put("Data", directUseOfWaterDataObject);
				directUseOfWaterObject.put("Result", false);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		directUseOfWaterArray.put(directUseOfWaterObject);
		return directUseOfWaterArray.toString();
	}

	@RequestMapping("/ajax-save-regular-direct-use-of-water")
	public @ResponseBody String saveRegularDirectUseOFWater(@RequestBody JsonObject directUseOfWaterObj,
			HttpServletRequest request) {
		String result = "";
		try {
			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			if (!Validator.isEmpty(empDataSession)) {
				Users users = new Users();
				users.setUsersId(empDataSession.getUsers().getUsersId());
				if (!Validator.isEmpty(directUseOfWaterObj)) {
					JsonArray regDirectUseOfWaterArray = directUseOfWaterObj.getAsJsonArray("regDirectUseOfWaterData");
					if (!Validator.isEmpty(regDirectUseOfWaterArray)) {
						for (JsonElement regDirectUseOfWater : regDirectUseOfWaterArray) {
							RegDirectUseOfWaterData regDirectUseOfWaterData = new RegDirectUseOfWaterData();
							regDirectUseOfWaterData.setWhereToUse(
									regDirectUseOfWater.getAsJsonObject().get("whereToUse").getAsString());
							regDirectUseOfWaterData.setStartReading(
									regDirectUseOfWater.getAsJsonObject().get("directUseStart").getAsFloat());
							regDirectUseOfWaterData.setEndReading(
									regDirectUseOfWater.getAsJsonObject().get("directUseEnd").getAsFloat());
							regDirectUseOfWaterData.setActualReading(
									regDirectUseOfWater.getAsJsonObject().get("directUseAvg").getAsFloat());
							regDirectUseOfWaterData.setUsers(users);
							regDirectUseOfWaterData = regDirectUseOfWaterServices.save(regDirectUseOfWaterData);
							if (regDirectUseOfWaterData != null) {
								result = "success";
							} else {
								result = "fail";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/ajax-getDailyTreatmentWaterData")
	public @ResponseBody String getDailyTreatmentWater(HttpServletRequest request) {
		List<WaterInventory> waterInventoryData = new ArrayList<>();
		ArrayList<Object> waterInventoryList = new ArrayList<>();
		List<WastewaterTreatment> wastewaterTreatmentData = new ArrayList<>();
		List<WastewaterTreatment> wastewaterTreatmentList = new ArrayList<>();
		List<RegularTreatmentData> regularTreatmentData = new ArrayList<>();
		List<WastewaterRecycle> wasteWaterRecycleData = new ArrayList<>();
		List<RegWastewaterRecycle> regWastewaterRecycleData = new ArrayList<>();
		JSONObject wastewaterTreatmentObject = new JSONObject();
		JSONObject wastewaterTreatmentDataObject = new JSONObject();
		JSONArray wastewaterTreatmentArray = new JSONArray();
		EmpData empDataSession = null;
		String regtreatmentDate = null, regRecycleDate = null;
		int companyId = 0;
		try {
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			}
			companyId = empDataSession.getCompanyProfile().getCompanyProfileId();
			waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId, Utilities.getTodaysDate());
			if (!Validator.isEmpty(waterInventoryData)) {
				for (int i = 0; i < waterInventoryData.size(); i++) {
					WaterInventory wiDetail = new WaterInventory();
					wiDetail = waterInventoryData.get(i);
					wastewaterTreatmentData = wastewaterTreatmentServices
							.getAllWasteWaterTreatmentData(wiDetail.getWaterInventoryId());
					if (!Validator.isEmpty(wastewaterTreatmentData)) {
						for (int j = 0; j < wastewaterTreatmentData.size(); j++) {
							List<WastewaterRecycle> wasteWaterRecycleList = new ArrayList<>();
							WastewaterTreatment wastewaterTreatment = new WastewaterTreatment();
							wastewaterTreatment = wastewaterTreatmentData.get(j);
							regularTreatmentData = regularTreatmentDataServices.getRegularTreatementDataByDate(
									wastewaterTreatment.getWastewaterTreatmentId(), new PageRequest(0, 1));
							wastewaterTreatment.setRegularTreatmentData(regularTreatmentData);
							wasteWaterRecycleData = wasteWaterRecycleSevices
									.getWasteWaterRecycle(wastewaterTreatment.getWastewaterTreatmentId());

							if (!Validator.isEmpty(wasteWaterRecycleData)) {
								for (int k = 0; k < wasteWaterRecycleData.size(); k++) {
									WastewaterRecycle wastewaterRecycle = new WastewaterRecycle();
									wastewaterRecycle = wasteWaterRecycleData.get(k);
									regWastewaterRecycleData = regWastewaterRecycleServices
											.getRegWastewaterRecycleByRecycleId(
													wastewaterRecycle.getWastewaterRecycleId(), new PageRequest(0, 1));
									wastewaterRecycle.setRegWastewaterRecycleData(regWastewaterRecycleData);
									if (!wasteWaterRecycleList.contains(wastewaterRecycle)) {
										wasteWaterRecycleList.add(wastewaterRecycle);
									}
									wastewaterTreatment.setWasteWaterRecycleData(wasteWaterRecycleList);
								}
							}
							if (!wastewaterTreatmentList.contains(wastewaterTreatment)) {
								wastewaterTreatmentList.add(wastewaterTreatment);
							}
						}
					}
					wiDetail.setWastewaterTreatmentList(wastewaterTreatmentList);
					if (!Validator.isEmpty(wiDetail.getWastewaterTreatmentList())) {
						wastewaterTreatmentObject.put("Result", true);
						for (WastewaterTreatment wastewaterTreatment : wiDetail.getWastewaterTreatmentList()) {
							HashMap<String, Object> wiList = new HashMap<String, Object>();
							wiList.put("type", wastewaterTreatment.getTreatmentType());
							wiList.put("label", wastewaterTreatment.getLabel());
							wiList.put("capacity", wastewaterTreatment.getCapacity());
							wiList.put("wastewaterTreatmentId", wastewaterTreatment.getWastewaterTreatmentId());
							if (!Validator.isEmpty(wastewaterTreatment.getRegularTreatmentData())) {
								for (RegularTreatmentData regularTreatment : wastewaterTreatment
										.getRegularTreatmentData()) {
									Timestamp rtDate = regularTreatment.getCreateDateTime();
									if (!Validator.isEmpty(rtDate)) {
										regtreatmentDate = Utilities.getDateinStringFromTimestamp(rtDate);
										if (regtreatmentDate.equals(Utilities.getTodaysDate())) {
											wiList.put("status", "Filled");
											wiList.put("startReading", regularTreatment.getStartReading());
											wiList.put("endReading", regularTreatment.getEndReading());
											wiList.put("actualReading", regularTreatment.getActualReading());
											wiList.put("energystartReading", regularTreatment.getEnergyStartReading());
											wiList.put("energyendReading", regularTreatment.getEnergyEndReading());
											wiList.put("energyActualReading", regularTreatment.getEnergyAvg());
										} else {
											wiList.put("status", "Fill");
											wiList.put("startReading", "");
											wiList.put("endReading", regularTreatment.getEndReading());
											wiList.put("actualReading", "");
											wiList.put("energystartReading", "");
											wiList.put("energyendReading", regularTreatment.getEnergyEndReading());
											wiList.put("energyActualReading", "");
										}
									}
								}
							} else {
								wiList.put("status", "New");
								wiList.put("startReading", "");
								wiList.put("endReading", "");
								wiList.put("actualReading", "");
								wiList.put("energystartReading", "");
								wiList.put("energyendReading", "");
								wiList.put("energyActualReading", "");
							}
							ArrayList<Object> wastewaterRecycleList = new ArrayList<>();
							if (!Validator.isEmpty(wastewaterTreatment.getWasteWaterRecycleData())) {
								for (WastewaterRecycle wastewaterRecycle : wastewaterTreatment
										.getWasteWaterRecycleData()) {
									HashMap<String, Object> wwrList = new HashMap<String, Object>();
									wwrList.put("wastewaterRecycleId", wastewaterRecycle.getWastewaterRecycleId());
									wwrList.put("usedType", wastewaterRecycle.getUseType());
									wwrList.put("recycleTo", wastewaterRecycle.getRecycledTo());
									wwrList.put("quantity", wastewaterRecycle.getQuantity());
									wwrList.put("isMeter", wastewaterRecycle.isMeter());
									if (!Validator.isEmpty(wastewaterRecycle.getRegWastewaterRecycleData())) {
										for (RegWastewaterRecycle regWastewaterRecycle : wastewaterRecycle
												.getRegWastewaterRecycleData()) {
											Timestamp rwrDate = regWastewaterRecycle.getCreateDateTime();
											if (!Validator.isEmpty(rwrDate)) {
												regRecycleDate = Utilities.getDateinStringFromTimestamp(rwrDate);
												if (regRecycleDate.equals(Utilities.getTodaysDate())) {
													wwrList.put("recycleStatus", "Filled");
													if (wastewaterRecycle.isMeter() == true) {
														wwrList.put("recycleStartReading",
																regWastewaterRecycle.getStartReading());
														wwrList.put("recycleEndReading",
																regWastewaterRecycle.getEndReading());
														wwrList.put("recycleActualReading",
																regWastewaterRecycle.getActualReading());
													} else {
														wwrList.put("recycleStartReading", "");
														wwrList.put("recycleEndReading", "");
														wwrList.put("recycleActualReading",
																regWastewaterRecycle.getActualReading());
													}
												} else {
													wwrList.put("recycleStatus", "Fill");
													if (wastewaterRecycle.isMeter() == true) {
														wwrList.put("recycleStartReading", "");
														wwrList.put("recycleEndReading",
																regWastewaterRecycle.getEndReading());
														wwrList.put("recycleActualReading", "");
													} else {
														wwrList.put("recycleStartReading", "");
														wwrList.put("recycleEndReading", "");
														wwrList.put("recycleActualReading", "");
													}
												}
											}
										}
									} else {
										wwrList.put("recycleStatus", "New");
										if (wastewaterRecycle.isMeter() == true) {
											wwrList.put("recycleStartReading", "");
											wwrList.put("recycleEndReading", "");
											wwrList.put("recycleActualReading", "");
										} else {
											wwrList.put("recycleStartReading", "");
											wwrList.put("recycleEndReading", "");
											wwrList.put("recycleActualReading", "");
										}
									}
									if (!wastewaterRecycleList.contains(wwrList)) {
										wastewaterRecycleList.add(wwrList);
									}
									wiList.put("recycleList", wastewaterRecycleList);
								}
							}
							if (!waterInventoryList.contains(wiList)) {
								waterInventoryList.add(wiList);
							}
						}
						wastewaterTreatmentDataObject.put("treatmentData", waterInventoryList);
					} else {
						wastewaterTreatmentObject.put("Result", false);
					}
				}
				wastewaterTreatmentObject.put("Data", wastewaterTreatmentDataObject);
			} else {
				wastewaterTreatmentObject.put("Data", wastewaterTreatmentDataObject);
				wastewaterTreatmentObject.put("Result", false);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		wastewaterTreatmentArray.put(wastewaterTreatmentObject);
		return wastewaterTreatmentArray.toString();
	}

	@PostMapping("/ajax-save-regular-treatmentData")
	public @ResponseBody String saveRegularTreatmentData(@RequestBody JsonObject wasteWaterTreatmentObj,
			HttpServletRequest request) {
		JsonObject regTreatmentObj = wasteWaterTreatmentObj.getAsJsonObject("regularTreatmentData");
		String result = "";
		EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
		Users users = new Users();
		users.setUsersId(empDataSession.getUsers().getUsersId());
		try {
			if (!Validator.isEmpty(regTreatmentObj)) {
				WastewaterTreatment wasteWaterTreatment = new WastewaterTreatment();
				wasteWaterTreatment.setWastewaterTreatmentId(regTreatmentObj.get("treatmentId").getAsInt());
				RegularTreatmentData regularTreatmentData = new RegularTreatmentData();
				regularTreatmentData.setWastewaterTreatment(wasteWaterTreatment);
				regularTreatmentData.setStartReading(regTreatmentObj.get("treatmentSourceStart").getAsFloat());
				regularTreatmentData.setEndReading(regTreatmentObj.get("treatmentSourceEnd").getAsFloat());
				regularTreatmentData.setActualReading(regTreatmentObj.get("treatmentSourceAvg").getAsFloat());
				regularTreatmentData.setEnergyStartReading(regTreatmentObj.get("treatmentEnergyStart").getAsFloat());
				regularTreatmentData.setEnergyEndReading(regTreatmentObj.get("treatmentEnergyEnd").getAsFloat());
				regularTreatmentData.setEnergyAvg(regTreatmentObj.get("treatmentEnergyAvg").getAsFloat());
				regularTreatmentData.setUsers(users);
				regularTreatmentData = regularTreatmentDataServices.save(regularTreatmentData);

				if (regularTreatmentData != null) {
					result = "success";
				} else {
					result = "fail";
				}
				JsonArray regRecycleArray = regTreatmentObj.getAsJsonArray("regRecycleList");

				if (!Validator.isEmpty(regRecycleArray)) {
					for (JsonElement regWasteWaterRecycleObj : regRecycleArray) {
						RegWastewaterRecycle regWasteWaterRecycle = new RegWastewaterRecycle();
						WastewaterRecycle wastewaterRecycle = new WastewaterRecycle();
						wastewaterRecycle.setWastewaterRecycleId(
								regWasteWaterRecycleObj.getAsJsonObject().get("recycleId").getAsInt());
						regWasteWaterRecycle.setRegWastewaterRecycleId(
								regWasteWaterRecycleObj.getAsJsonObject().get("recycleId").getAsInt());
						regWasteWaterRecycle.setWastewaterRecycle(wastewaterRecycle);
						regWasteWaterRecycle.setStartReading(
								regWasteWaterRecycleObj.getAsJsonObject().get("recycleStartReading").getAsFloat());
						regWasteWaterRecycle.setEndReading(
								regWasteWaterRecycleObj.getAsJsonObject().get("recycleEndReading").getAsFloat());
						regWasteWaterRecycle.setActualReading(
								regWasteWaterRecycleObj.getAsJsonObject().get("recycleActReading").getAsFloat());
						regWasteWaterRecycle.setUsers(users);
						regWasteWaterRecycle = regWastewaterRecycleServices.save(regWasteWaterRecycle);
						if (regWasteWaterRecycle != null) {
							result = "success";
						} else {
							result = "fail";
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}
}
