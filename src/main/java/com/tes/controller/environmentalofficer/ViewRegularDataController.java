package com.tes.controller.environmentalofficer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.JsonObject;
import com.tes.model.DirectUseOfWater;
import com.tes.model.EmpData;
import com.tes.model.FilterUse;
import com.tes.model.ModifiedRecord;
import com.tes.model.RegAPC;
import com.tes.model.RegDirectUseOfWaterData;
import com.tes.model.RegFiltersUseData;
import com.tes.model.RegMultipleFilterData;
import com.tes.model.RegPrefilter;
import com.tes.model.RegWastewaterRecycle;
import com.tes.model.RegWaterSourceData;
import com.tes.model.RegularData;
import com.tes.model.RegularTreatmentData;
import com.tes.model.Todo;
import com.tes.model.Users;
import com.tes.repository.TodoRepository;
import com.tes.repository.admin.EmpDataRepository;
import com.tes.repository.environmentalofficer.ModifiedRecordRepository;
import com.tes.repository.environmentalofficer.RegularDataRepository;
import com.tes.services.UsersServices;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.environmentalofficer.AllProductNameServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.RegAPCServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegPrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.RegWastewaterRecycleServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.AuthenticationUtil;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class used to View regular data i.e. product, byproduct,water, raw
 * material, fuel, solid waste etc. and change units value, data modification,
 * submit updated data.
 * 
 * @author Tushar Chougule
 */
@RestController
@SessionAttributes({Constant.EMPDATASESSION})
@RequestMapping(value = {"/management", "/env"})
public class ViewRegularDataController
{

	@Autowired
	RegularDataRepository regularDataRepository;

	@Autowired
	CompanyProfileServices companyProfileServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	RegMultipleFilterDataServices regularFiltersDataServices;

	@Autowired
	RegFiltersUseDataServices regularFiltersUseDataServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWaterServices;

	@Autowired
	RegDirectUseOfWaterDataServices regularWaterUseDataServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	WastewaterTreatmentServices WastewaterTreatmentServices;

	@Autowired
	AllProductNameServices allProductNameServices;

	@Autowired
	ModifiedRecordRepository modifiedRecordRepository;

	// @Autowired
	// ModifiedRecordServices modifiedRecordServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	UnitServices unitServices;

	@Autowired
	RegAPCServices regAPCServices;

	@Autowired
	RegWaterSourceDataServices regWaterSourceDataServices;

	@Autowired
	UsersServices usersServices;

	@Autowired
	RegPrefilterServices regPrefilterServices;

	@Autowired
	RegMultipleFilterDataServices regMultipleFilterDataServices;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	RegFiltersUseDataServices regFiltersUseDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regDirectUseOfWaterDataServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	@Autowired
	RegWastewaterRecycleServices regWastewaterRecycleServices;

	@Autowired
	EmpDataRepository empDataRepository;

	@Autowired
	TodoRepository todoRepository;

	@Autowired
	AuthenticationUtil authenticationUtil;

	// @Autowired
	// ModifiedRecord modifiedRecord;

	private static final Logger LOGGER = LogManager.getLogger(ViewRegularDataController.class);

	/**
	 * This method used to Environmental officer view regular data details.
	 * i.e.Production, Byproduct, Raw material,water, Fuel, Solid Waste, Non
	 * Hazardous Waste from Process, Hazardous Waste PCF, Bio-Medical Waste details.
	 * 
	 * @param request the servlet request we are processing.
	 * @return ViewRegularData view
	 */
	@RequestMapping("view-regular-data")
	public @ResponseBody ModelAndView viewRegularData(HttpServletRequest request)
	{
		List<Integer> dailyInputyear = null;
		ModelAndView model;
		model = new ModelAndView("EnvrOfficer/ViewRegularData");
		try
		{
			dailyInputyear = regWaterSourceDataServices.getWaterMinYear();

			while (dailyInputyear.remove(null))
			{
			}
			Collections.sort(dailyInputyear);
			model.addObject("arrayYear", dailyInputyear);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return model;
	}

	/**
	 * This method used to convert the product unit for per day
	 * 
	 * @param units the unit of product
	 * @return unitp
	 */
	
	//changes by pallavi...
	public String changeUnits(String units)
	{
		String unitp;
		unitp = "NA";
		try
		{
			if (units.equalsIgnoreCase("Kg/Year") || units.equalsIgnoreCase("Kg/Month")
					|| units.equalsIgnoreCase("Kg/Week") || units.equalsIgnoreCase("Kg/Day"))
			{
				unitp = "Kg/Day";
			}
			else if (units.equalsIgnoreCase("Liter/Year") || units.equalsIgnoreCase("Liter/Month")
					|| units.equalsIgnoreCase("Liter/Week") || units.equalsIgnoreCase("Liter/Day"))
			{
				unitp = "Liter/Day";
			}
			else if (units.equalsIgnoreCase("Ton/Ton") || units.equalsIgnoreCase("MT/Year") || units.equalsIgnoreCase("MT/Month")
					|| units.equalsIgnoreCase("MT/Week") || units.equalsIgnoreCase("MT/Day"))
			{
				unitp = "MT/Day";
			}
			else if (units.equalsIgnoreCase("Nos/Year") || units.equalsIgnoreCase("Nos/Month")
					|| units.equalsIgnoreCase("Nos/Week") || units.equalsIgnoreCase("Nos/Day"))
			{
				unitp = "Nos/Day";
			}
			//pp
			else if (units.equalsIgnoreCase("Units/Year") || units.equalsIgnoreCase("Units/Month")
					|| units.equalsIgnoreCase("Units/Week") || units.equalsIgnoreCase("Units/Day"))
			{
				unitp = "Units/Day";
			}
			else if (units.equalsIgnoreCase("Pieces/Year") || units.equalsIgnoreCase("Pieces/Month")
					|| units.equalsIgnoreCase("Pieces/Week") || units.equalsIgnoreCase("Pieces/Day"))
			{
				unitp = "Pieces/Day";
			}
			else if (units.equalsIgnoreCase("Million Nos./Year") || units.equalsIgnoreCase("Million Nos./Month")
				 || units.equalsIgnoreCase("Million Nos./Day"))
			{
				unitp = "Million Nos./Day";
			}
			else if (units.equalsIgnoreCase("Cr Nos./Year") || units.equalsIgnoreCase("Cr Nos./Month")
					 || units.equalsIgnoreCase(" Cr Nos./Day"))
				{
					unitp = "Cr Nos./Day";
				}
			else if (units.equalsIgnoreCase("Mtrs/Year") || units.equalsIgnoreCase("Mtrs/Month")
					 || units.equalsIgnoreCase("Mtrs/Day"))
				{
					unitp = "Mtrs/Day";
				}
			
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return unitp;
	}

	// To Download .xls file
	/*
	 * @PostMapping(value = "/ajax-createSheet") public @ResponseBody String
	 * downloadExcel(@RequestParam(value = "name", required = false) String name,
	 * @RequestParam(value = "year", required = false) int year) { String excelpath;
	 * excelpath = null; int ii = 0; float quantity; String productName; String
	 * inputDate; String employeeName; List<Object[]> productList = null;
	 * List<Object[]> byproductList = null; List<Object[]> rawMaterialList = null;
	 * List<Object[]> fuelList = null; List<Object[]> nhwpList = null;
	 * List<Object[]> nhwpcfList = null; List<Object[]> hwpList = null;
	 * List<Object[]> hwpcfList = null; List<Object[]> bmwdList = null;
	 * List<Object[]> hazardousList = new ArrayList<>(); try { if
	 * (name.equalsIgnoreCase(Constant.PRODUCT)) { productList =
	 * regularDataServices.getProductDetails(year); if (!productList.isEmpty()) {
	 * for (Object[] productListdata : productList) { ii++; productName = (String)
	 * productListdata[1]; quantity = (float) productListdata[2]; inputDate =
	 * (String) productListdata[3]; employeeName = (String) productListdata[4];
	 * hazardousList.add(new Object[] { ii, productName, quantity, inputDate,
	 * employeeName }); } excelpath = buildExcel(name, hazardousList); } } else if
	 * (name.equalsIgnoreCase(Constant.BYPRODUCT)) { byproductList =
	 * regularDataServices.getByProductDetails(year); if (!byproductList.isEmpty())
	 * { for (Object[] byproductListdata : byproductList) { ii++; productName =
	 * (String) byproductListdata[1]; quantity = (float) byproductListdata[2];
	 * inputDate = (String) byproductListdata[3]; employeeName = (String)
	 * byproductListdata[4]; hazardousList.add(new Object[] { ii, productName,
	 * quantity, inputDate, employeeName }); } excelpath = buildExcel(name,
	 * hazardousList); } } else if (name.equalsIgnoreCase("Raw Material")) {
	 * rawMaterialList = regularDataServices.getRawMaterialDetails(year); if
	 * (!rawMaterialList.isEmpty()) { for (Object[] rawMaterialListdata :
	 * rawMaterialList) { ii++; productName = (String) rawMaterialListdata[1];
	 * quantity = (float) rawMaterialListdata[2]; inputDate = (String)
	 * rawMaterialListdata[3]; employeeName = (String) rawMaterialListdata[4];
	 * hazardousList.add(new Object[] { ii, productName, quantity, inputDate,
	 * employeeName }); } excelpath = buildExcel(name, hazardousList); } } else if
	 * (name.equalsIgnoreCase("Fuel")) { fuelList =
	 * regularDataServices.getFuelInfo(year); if (!fuelList.isEmpty()) { for
	 * (Object[] fuelListdata : fuelList) { ii++; productName = (String)
	 * fuelListdata[1]; quantity = (float) fuelListdata[2]; inputDate = (String)
	 * fuelListdata[3]; employeeName = (String) fuelListdata[4];
	 * hazardousList.add(new Object[] { ii, productName, quantity, inputDate,
	 * employeeName }); } excelpath = buildExcel(name, hazardousList); } } else if
	 * (name.equalsIgnoreCase("nhwp")) { nhwpList =
	 * regularDataServices.findByNonHazordousWasteProcess(year); if
	 * (!nhwpList.isEmpty()) { for (Object[] nhwpListdata : nhwpList) { ii++;
	 * productName = (String) nhwpListdata[1]; quantity = (float) nhwpListdata[2];
	 * inputDate = (String) nhwpListdata[3]; employeeName = (String)
	 * nhwpListdata[4]; hazardousList.add(new Object[] { ii, productName, quantity,
	 * inputDate, employeeName }); } excelpath = buildExcel(name, hazardousList); }
	 * } else if (name.equalsIgnoreCase(Constant.NHWPCF)) { nhwpcfList =
	 * regularDataServices.findByNonHazordousWastePCF(year); if
	 * (!nhwpcfList.isEmpty()) { for (Object[] nhwpcfListdata : nhwpcfList) { ii++;
	 * productName = (String) nhwpcfListdata[1]; quantity = (float)
	 * nhwpcfListdata[2]; inputDate = (String) nhwpcfListdata[3]; employeeName =
	 * (String) nhwpcfListdata[4]; hazardousList.add(new Object[] { ii, productName,
	 * quantity, inputDate, employeeName }); } excelpath = buildExcel(name,
	 * hazardousList); } } else if (name == "hwp") { hwpList =
	 * regularDataServices.findByHazordousWasteProcess(year); if
	 * (!hwpList.isEmpty()) { for (Object[] hwpListdata : hwpList) { ii++;
	 * productName = (String) hwpListdata[1]; quantity = (float) hwpListdata[2];
	 * inputDate = (String) hwpListdata[3]; employeeName = (String) hwpListdata[4];
	 * hazardousList.add(new Object[] { ii, productName, quantity, inputDate,
	 * employeeName }); } excelpath = buildExcel(name, hazardousList); } } else if
	 * (name == Constant.HWPCF) { hwpcfList =
	 * regularDataServices.findByHazordousWastePCF(year); if (!hwpcfList.isEmpty())
	 * { for (Object[] hwpcfListdata : hwpcfList) { ii++; productName = (String)
	 * hwpcfListdata[1]; quantity = (float) hwpcfListdata[2]; inputDate = (String)
	 * hwpcfListdata[3]; employeeName = (String) hwpcfListdata[4];
	 * hazardousList.add(new Object[] { ii, productName, quantity, inputDate,
	 * employeeName }); } excelpath = buildExcel(name, hazardousList); } } else {
	 * bmwdList = regularDataServices.findByHazordousWastePCF(year); if
	 * (!bmwdList.isEmpty()) { for (Object[] bmwdListdata : bmwdList) { ii++;
	 * productName = (String) bmwdListdata[1]; quantity = (float) bmwdListdata[2];
	 * inputDate = (String) bmwdListdata[3]; employeeName = (String)
	 * bmwdListdata[4]; hazardousList.add(new Object[] { ii, productName, quantity,
	 * inputDate, employeeName }); } excelpath = buildExcel(name, hazardousList); }
	 * } } catch (Exception e) { LOGGER.error(e); } return excelpath; }
	 */

	/*
	 * private String buildExcel(String name, List<Object[]> hazardousList) { String
	 * filepath; filepath = "D:/Project/" + name + ".xls"; try { HSSFWorkbook
	 * workbook = new HSSFWorkbook(); FileOutputStream fileOut; // create a new
	 * Excel sheet HSSFSheet sheet = workbook.createSheet("Hazardous");
	 * sheet.setDefaultColumnWidth(30); // create style for header cells CellStyle
	 * style = workbook.createCellStyle(); Font font = workbook.createFont();
	 * font.setFontName("Arial");
	 * style.setFillForegroundColor(HSSFColor.BLUE.index);
	 * style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	 * font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	 * font.setColor(HSSFColor.WHITE.index); style.setFont(font); // create header
	 * row HSSFRow header = sheet.createRow(0);
	 * header.createCell(0).setCellValue("Id");
	 * header.getCell(0).setCellStyle(style);
	 * header.createCell(1).setCellValue("product Name");
	 * header.getCell(1).setCellStyle(style);
	 * header.createCell(2).setCellValue("Quantity");
	 * header.getCell(2).setCellStyle(style);
	 * header.createCell(3).setCellValue("Input Date");
	 * header.getCell(3).setCellStyle(style);
	 * header.createCell(4).setCellValue("Employee Name");
	 * header.getCell(4).setCellStyle(style); // create data rows int rowCount = 1;
	 * for (Object[] hList : hazardousList) { HSSFRow aRow =
	 * sheet.createRow(rowCount++); aRow.createCell(0).setCellValue((int) hList[0]);
	 * aRow.createCell(1).setCellValue((String) hList[1]);
	 * aRow.createCell(2).setCellValue((float) hList[2]);
	 * aRow.createCell(3).setCellValue((String) hList[3]);
	 * aRow.createCell(4).setCellValue((String) hList[4]); } fileOut = new
	 * FileOutputStream(new File("D:/Project/" + name + ".xls"));
	 * workbook.write(fileOut); fileOut.close(); } catch (Exception e) {
	 * LOGGER.error(e); } return filepath; }
	 */

	/**
	 * This method used to View/update the water details
	 * 
	 * @param today the current date
	 * @param request the servlet request we are processing.
	 * @return ViewWaterData
	 */
	// Water View
	// Effected By Water Inventory ........by vishal
	/*
	 * @RequestMapping("/ajax-view-water-data") public @ResponseBody ModelAndView
	 * viewWaterData(@RequestParam(value = "today") String today, HttpServletRequest
	 * request) { ModelAndView model; model = new ModelAndView(); int staff1 = 0;
	 * int waterInvId = 0; int waterInventoryId = 0; ArrayList<Filters>
	 * filterUseAndMeterList = null; String rfdates = ""; String domesticUseOfSource
	 * = null; String industrialUseOfSource = null; String fireHydrantUseOfSource =
	 * null; String laundryUseOfSource = null; String staffId = "0"; int sourceIndex
	 * = 0; String sidd = null; String sourceName = null; String sourceMeter = null;
	 * filterUseAndMeterList = new ArrayList<>(); List<RegWaterSourceData>
	 * regSourceDataWatrInvIdAndSnameArray = new ArrayList<>();
	 * List<RegWaterSourceData> regSourceDataWatrInvIdAndSname = null;
	 * List<RegMultipleFilterData> regFiltDataFiltNameWatrInvId = null;
	 * List<RegMultipleFilterData> regFiltDataFiltnameWatrInvIdDate = null;
	 * List<WaterInventory> waterInventoryList = null; List<Filters> filtersList =
	 * null; List<Filters> filterUseAndMeter = null; List<RegFiltersUseData>
	 * filterUseDataNameTypeWaterInvId = null; List<Industrial> industrialList =
	 * null; List<UseOfWater> useOfWaterInvList = null; List<WaterInventory>
	 * waterInv = new ArrayList<>(); ArrayList<Filters> filtersArrayList = new
	 * ArrayList<>(); ArrayList<RegMultipleFilterData> regularFilterDataArrayList =
	 * new ArrayList<>(); ArrayList<RegFiltersUseData>
	 * filterUseDataNameTypeWaterInvIdArray = new ArrayList<>(); ArrayList<String>
	 * waterTreatmentTypeArrayList = new ArrayList<>(); ArrayList<String>
	 * waterTreatmentTypeNameArrayList = new ArrayList<>();
	 * ArrayList<RegularTreatmentData> regularTreatmentDataByTypeWiIdArrayList = new
	 * ArrayList<>(); ArrayList<RegularTreatmentData>
	 * regTreatmentDataByTypeWiIdDateArrayList = new ArrayList<>();
	 * ArrayList<DirectUseOfWater> regWaterUseDataByStypeWiIdArray = new
	 * ArrayList<>(); try { EmpData empdata = (EmpData)
	 * request.getSession().getAttribute(Constant.EMPDATASESSION); int companyId =
	 * empdata.getCompanyProfile().getCompanyProfileId(); waterInventoryList =
	 * waterInventoryServices.findByConsentId(companyId);
	 * if (!waterInventoryList.isEmpty()) { for (int i = 0; i <
	 * waterInventoryList.size(); i++) { waterInvId =
	 * waterInventoryList.get(i).getWaterInventoryId(); domesticUseOfSource =
	 * waterInventoryList.get(i).getDomesticUseOfSource(); industrialUseOfSource =
	 * waterInventoryList.get(i).getIndustrialUseOfSource(); fireHydrantUseOfSource
	 * = waterInventoryList.get(i).getFireHydrantUseOfSource(); laundryUseOfSource =
	 * waterInventoryList.get(i).getLaundryUseOfSource();
	 * List<RegWaterSourceData> regularSourceInfo = regularSourceDataServices
	 * .getByWaterInventoryAndDateId(waterInvId, today); if
	 * (!regularSourceInfo.isEmpty()) { String temp =
	 * String.valueOf(regularSourceInfo.get(0).getRegularSourceDataId()); if
	 * (staffId.equalsIgnoreCase("0")) staffId = temp; else staffId = staffId + ","
	 * + temp;
	 * staff1 = regularSourceInfo.get(0).getStaff(); waterInventoryId = waterInvId;
	 * model.addObject("today", today); model.addObject("staff", staff1);
	 * model.addObject("staffId", staffId); waterInv.add(waterInventoryList.get(i));
	 * model.addObject("WaterInventoryList", waterInv);
	 * List<WaterSource> waterSourceData =
	 * waterSourceServices.getWaterSourceData(waterInventoryId); if
	 * (!waterSourceData.isEmpty()) { model.addObject("waterSourceData",
	 * waterSourceData); if (!waterSourceData.isEmpty()) { sourceIndex++; sidd =
	 * waterInventoryId + "_" + sourceIndex; sourceName =
	 * waterSourceData.get(0).getSourceName(); sourceMeter =
	 * waterSourceData.get(0).getSourceMeter(); model.addObject("sidd", sidd);
	 * model.addObject("sourceName", sourceName); model.addObject("sourceMeter",
	 * sourceMeter); model.addObject("waterInventoryId", waterInventoryId);
	 * float regSourceDataStartReading; String regSourceDataDate = "";
	 * regSourceDataWatrInvIdAndSname = regularSourceDataServices
	 * .rsDataBySrcAndName(waterInventoryId, sourceName, new PageRequest(0, 1)); if
	 * (!regSourceDataWatrInvIdAndSname.isEmpty()) { regSourceDataStartReading =
	 * regSourceDataWatrInvIdAndSname.get(0).getStartReading(); regSourceDataDate =
	 * regSourceDataWatrInvIdAndSname.get(0).getRsDate();// rsDate } else {
	 * regSourceDataStartReading = 0.0f; regSourceDataDate = ""; } for (int b = 0; b
	 * < regSourceDataWatrInvIdAndSname.size(); b++) { RegWaterSourceData
	 * regularSourceDataDetails = regSourceDataWatrInvIdAndSname.get(b);
	 * regSourceDataWatrInvIdAndSnameArray.add(regularSourceDataDetails); }
	 * List<RegWaterSourceData> rsDataBySrcNameDate = null;
	 * ArrayList<RegWaterSourceData> rsDataArrayList = new ArrayList<>(); for
	 * (WaterSource waterSource : waterSourceData) { String sourceName1 =
	 * waterSource.getSourceName(); rsDataBySrcNameDate = regularSourceDataServices
	 * .rsDataBySrcNameDate(waterInventoryId, sourceName1, today, new PageRequest(0,
	 * 1)); rsDataArrayList.addAll(rsDataBySrcNameDate); }
	 * model.addObject("regSourceDataWatrInvIdAndSnameObject",
	 * regSourceDataWatrInvIdAndSnameArray); model.addObject("rsDataBySrcNameDate",
	 * rsDataArrayList); model.addObject("regSourceDataStartReading",
	 * regSourceDataStartReading); model.addObject("regSourceDataDate",
	 * regSourceDataDate); } }
	 * // Filters filtersList =
	 * filtersServices.findByFilterWaterInvId(waterInventoryId); if
	 * (!filtersList.isEmpty()) { filtersArrayList.addAll(filtersList);
	 * model.addObject("filterList", filtersArrayList);
	 * for (int j = 0; j < filtersArrayList.size(); j++) { String filterName =
	 * filtersArrayList.get(j).getFilterName(); regFiltDataFiltNameWatrInvId =
	 * regularFiltersDataServices.regFiltDataFiltNameWatrInvId( filterName,
	 * waterInventoryId, new PageRequest(0, 1)); for (int t = 0; t <
	 * regFiltDataFiltNameWatrInvId.size(); t++) { RegMultipleFilterData regFilter =
	 * regFiltDataFiltNameWatrInvId.get(t);
	 * regularFilterDataArrayList.add(regFilter); } regFiltDataFiltnameWatrInvIdDate
	 * = regularFiltersDataServices .regFiltDataFiltnameWatrInvIdDate(filterName,
	 * waterInventoryId, today, new PageRequest(0, 1));
	 * // To get filterUse and FilterMeter filterUseAndMeter =
	 * filtersServices.findByFilterNameAndWaterInv(filterName, waterInvId);
	 * filterUseAndMeterList.addAll(filterUseAndMeter);
	 * for (int k = 0; k < filterUseAndMeter.size(); k++) { String type =
	 * filterUseAndMeter.get(k).getFilterUse(); filterUseDataNameTypeWaterInvId =
	 * regularFiltersUseDataServices
	 * .getRegularFilterUseByFiltrNameTypeWiId(filterName, type, waterInvId, new
	 * PageRequest(0, 1));
	 * filterUseDataNameTypeWaterInvIdArray.addAll(filterUseDataNameTypeWaterInvId);
	 * List<RegFiltersUseData> regfiltrUseDataFiltrNameTypeWIdDate =
	 * regularFiltersUseDataServices
	 * .getRegfiltrUseDataFiltrNameTypeWIdDate(filterName, type, waterInvId, today,
	 * new PageRequest(0, 1));
	 * model.addObject("regfiltrUseDataFiltrNameTypeWIdDate",
	 * regfiltrUseDataFiltrNameTypeWIdDate); } } } }
	 * // Use Of Water useOfWaterInvList =
	 * useOfWaterServices.getAllUseOfWaterData(waterInvId); if
	 * (!useOfWaterInvList.isEmpty()) { model.addObject("useOfWaterInvList",
	 * useOfWaterInvList); float startReading; String rsDate; if
	 * (!useOfWaterInvList.isEmpty()) { if
	 * (domesticUseOfSource.equalsIgnoreCase(Constant.CHECKED)) { DirectUseOfWater
	 * regWUDateWiId = regularWaterUseDataServices.rWUdata(waterInvId);
	 * model.addObject("RegularWUDateWiId", regWUDateWiId);
	 * if (regWUDateWiId != null) { startReading = regWUDateWiId.getEndReading();
	 * rsDate = regWUDateWiId.getrWDate(); } else { startReading = 0.0f; rsDate =
	 * ""; } model.addObject("UWDstartReading", startReading);
	 * model.addObject("UWDrsDate", rsDate); }
	 * float indStartReading; String industrialDate = null; int useOfWaterId =
	 * useOfWaterInvList.get(0).getUseOfWaterId();
	 * // industrial Use of Source if
	 * (industrialUseOfSource.equalsIgnoreCase(Constant.CHECKED)) { industrialList =
	 * industrialServices.getAllIndustrialData(useOfWaterId); for (int t = 0; t <
	 * industrialList.size(); t++) { String industrailName =
	 * industrialList.get(t).getIndName(); List<DirectUseOfWater>
	 * regWaterByINameAndwiId = regularWaterUseDataServices
	 * .getRegularWaterUseDataByIndName(industrailName, waterInvId, new
	 * PageRequest(0, 1)); if (!regWaterByINameAndwiId.isEmpty()) { indStartReading
	 * = regWaterByINameAndwiId.get(0).getEndReading(); industrialDate =
	 * regWaterByINameAndwiId.get(0).getrWDate(); } else { indStartReading = 0.0f;
	 * industrialDate = ""; } model.addObject("indStartReading", indStartReading);
	 * model.addObject("Industrialdate", industrialDate); DirectUseOfWater
	 * rwudNameWaterInvDate = regularWaterUseDataServices
	 * .getRWUDByIndNameWaterInvDate(industrailName, waterInvId, today);
	 * model.addObject("RWUDNameWaterInvDate", rwudNameWaterInvDate); } }
	 * model.addObject("IndustrialList", industrialList);
	 * // Laundry Use of Source float laundryStartReading; String laundrydate = "";
	 * if (laundryUseOfSource.equalsIgnoreCase(Constant.CHECKED)) {
	 * List<DirectUseOfWater> regWaterUseDataByStypeWiId =
	 * regularWaterUseDataServices .regularWaterUseDataByLaundry(waterInvId, new
	 * PageRequest(0, 1)); if (!regWaterUseDataByStypeWiId.isEmpty()) {
	 * regWaterUseDataByStypeWiIdArray.addAll(regWaterUseDataByStypeWiId); if
	 * (!regWaterUseDataByStypeWiId.isEmpty()) { laundryStartReading =
	 * regWaterUseDataByStypeWiId.get(0).getEndReading(); laundrydate =
	 * regWaterUseDataByStypeWiId.get(0).getrWDate(); } else { laundryStartReading =
	 * 0.0f; laundrydate = ""; } model.addObject("laundryStartReading",
	 * laundryStartReading); model.addObject("laundrydate", laundrydate);
	 * DirectUseOfWater regWaterUDByWiIdDate = regularWaterUseDataServices
	 * .getRWUDBySTypeWiIdDate(waterInvId, today); if (regWaterUDByWiIdDate != null)
	 * { model.addObject("regWaterUDByWiIdDate", regWaterUDByWiIdDate); } } }
	 * // Fire hydrant String fireHDate = ""; if
	 * (fireHydrantUseOfSource.equalsIgnoreCase(Constant.CHECKED)) {
	 * List<DirectUseOfWater> regWaterUseDataByFireHydrantWiId =
	 * regularWaterUseDataServices .getregularWaterUseDataByFireHydrant(waterInvId,
	 * new PageRequest(0, 1)); if (regWaterUseDataByFireHydrantWiId != null) {
	 * model.addObject("regWaterUseDataByFireHydrantWiId",
	 * regWaterUseDataByFireHydrantWiId); if
	 * (!regWaterUseDataByFireHydrantWiId.isEmpty()) { fireHDate =
	 * regWaterUseDataByFireHydrantWiId.get(0).getrWDate(); } else { fireHDate = "";
	 * } model.addObject("fireHDate", fireHDate); List<DirectUseOfWater>
	 * regWaterUseDataByIdStartActualEnd = regularWaterUseDataServices
	 * .getregularWaterUseDataByFireHydrantDate(waterInvId, today, new
	 * PageRequest(0, 1)); if
	 * (!Validator.isEmpty(regWaterUseDataByIdStartActualEnd)) {
	 * model.addObject("RWUDWiIdDate", regWaterUseDataByIdStartActualEnd);
	 * model.addObject("rWUDWiIdDateStartReding",
	 * regWaterUseDataByIdStartActualEnd.get(0).getStartReading());
	 * model.addObject("regularWaterUseDataId",
	 * regWaterUseDataByIdStartActualEnd.get(0).getRegularWaterUseDataId());
	 * model.addObject("rWUDWiIdDateEndReding",
	 * regWaterUseDataByIdStartActualEnd.get(0).getEndReading());
	 * model.addObject("rWUDWiIdDateActualReding",
	 * regWaterUseDataByIdStartActualEnd.get(0).getActualReading()); } } } } }
	 * // Waste Water TreatmentregTreatmentDataByTypeWiId List<String>
	 * waterTreatmentTypeList =
	 * waterTreatmentServices.getTreatmentTypeByWiId(waterInvId); if
	 * (!waterTreatmentTypeList.isEmpty()) {
	 * waterTreatmentTypeArrayList.addAll(waterTreatmentTypeList); List<String>
	 * waterTreatmentTypeNameList = waterTreatmentServices
	 * .getTreatmentTypeNameFromWiId(waterInvId);
	 * waterTreatmentTypeNameArrayList.addAll(waterTreatmentTypeNameList); for (int
	 * ts = 0; ts < waterTreatmentTypeNameArrayList.size(); ts++) { String type =
	 * waterTreatmentTypeNameArrayList.get(ts); String[] arrOfStr = type.split("-");
	 * List<RegularTreatmentData> regTreatmentDataByTypeWiId =
	 * regularTreatmentDataServices .getRegularTreatmentData(arrOfStr[0],
	 * waterInvId, new PageRequest(0, 1));
	 * regularTreatmentDataByTypeWiIdArrayList.addAll(regTreatmentDataByTypeWiId);
	 * List<RegularTreatmentData> regTreatmentDataByTypeWiIdDate =
	 * regularTreatmentDataServices .rtDataByDate(arrOfStr[0], waterInvId, today,
	 * new PageRequest(0, 1));
	 * regTreatmentDataByTypeWiIdDateArrayList.addAll(regTreatmentDataByTypeWiIdDate
	 * ); } } } }
	 * model.addObject("WaterTreatmentTypeArrayList", waterTreatmentTypeArrayList);
	 * model.addObject("waterTreatmentTypeNameArrayList",
	 * waterTreatmentTypeNameArrayList);
	 * model.addObject("regularTreatmentDataByTypeWiIdArrayList",
	 * regularTreatmentDataByTypeWiIdArrayList);
	 * model.addObject("regTreatmentDataByTypeWiIdDateArrayList",
	 * regTreatmentDataByTypeWiIdDateArrayList);
	 * model.addObject("regWaterUseDataByStypeWiId",
	 * regWaterUseDataByStypeWiIdArray);
	 * model.addObject("regularFilterDataArrayList", regularFilterDataArrayList);
	 * model.addObject("filterUseDataNameType",
	 * filterUseDataNameTypeWaterInvIdArray);
	 * model.addObject("regFiltDataFiltnameWatrInvIdDate",
	 * regFiltDataFiltnameWatrInvIdDate); model.addObject("filterUseAndMeter",
	 * filterUseAndMeterList); model.addObject("rfdates", rfdates);
	 * model.addObject("today", today);
	 * model.setViewName("/EnvrOfficer/ViewWaterData"); } catch (Exception e) {
	 * LOGGER.error(e); } return model; }
	 */

	// Need Confirmation to do --->Tushar
	// <-- For "Add" button in Dailyinput View -->
	/*
	 * @RequestMapping(value="/ajax-checkValidity") public @ResponseBody String
	 * checkdateValidity(@RequestParam(value="type",required=false) String
	 * type,@RequestParam(value="missed_date",required=false) String missedDate,
	 * @RequestParam(value="product_name",required=false) String
	 * productName,@RequestParam(value="new_quantity",required=false) String
	 * newQuantity,
	 * @RequestParam(value="check_quan",required=false) String
	 * check_quan,@RequestParam(value="product_type",required=false) String
	 * productType,
	 * @RequestParam(value="update_for",required=false) String updateFor) { String
	 * result="NA"; int regularId = 0,unitDays=0; float quantitySum=0.0f; String
	 * productUnit=null,unitp=null; float sumOfQuantityBetween=0.0f; float a1=0.0f;
	 * float b=0.0f; float checkQuan=Float.valueOf(check_quan); float
	 * newQuantity1=Float.valueOf(newQuantity); try { String today =
	 * LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.DATEFORMAT));
	 * if(type.equalsIgnoreCase("quan")) { String a[] = updateFor.split("-"); String
	 * startDate = a[0]+"-"+a[1]+"-01"; String endDate = a[0]+"-"+a[1]+"-31"; int
	 * noDays=companyProfileServices.getnoOfWorkers(); int totalDays=noDays*52;
	 * if(type.equalsIgnoreCase("nhwp")||type.equalsIgnoreCase(Constant.NHWPCF)||
	 * type. equalsIgnoreCase("hwp")||type.equalsIgnoreCase(Constant.HWPCF)) {
	 * productUnit=unitServices.getUnitFromAllProducts(productName);
	 * quantitySum=allProductsServices.getSumOfQuantity(updateFor, productName); }
	 * else { productUnit=unitServices.getUnitFromAllProducts(productName);
	 * quantitySum=allProductsServices.getSumOfQuantity(updateFor, productName); }
	 * if(productUnit == "Kg/Year" || productUnit == "Kg/Month" || productUnit ==
	 * "Kg/Week" || productUnit == "Kg/Day"){
	 * if(productUnit.equalsIgnoreCase("Kg/Year")){ unitDays = totalDays; }else
	 * if(productUnit == "Kg/Month"){ unitDays = noDays * 4; }else if(productUnit ==
	 * "Kg/Week"){ unitDays = noDays * 7; }else if(productUnit == "Kg/Day"){
	 * unitDays = 1; } unitp = "Kg/Day"; }else if(productUnit == "Liter/Year" ||
	 * productUnit == "Liter/Month" || productUnit == "Liter/Week" || productUnit ==
	 * "Liter/Day"){ if(productUnit == "Liter/Year"){ unitDays = totalDays; }else
	 * if(productUnit == "Liter/Month"){ unitDays = noDays * 4; }else if(productUnit
	 * == "Liter/Week"){ unitDays = noDays * 7; }else if(productUnit ==
	 * "Liter/Day"){ unitDays = 1; } unitp = "Liter/Day"; }else if(productUnit ==
	 * "MT/Year" || productUnit == "MT/Month" || productUnit == "MT/Week" ||
	 * productUnit == "MT/Day"){ if(productUnit == "MT/Year"){ unitDays = totalDays;
	 * }else if(productUnit == "MT/Month"){ unitDays = noDays * 4; }else
	 * if(productUnit == "MT/Week"){ unitDays = noDays * 7; }else if(productUnit ==
	 * "MT/Day"){ unitDays = 1; } unitp = "MT/Day"; }else if(productUnit ==
	 * "Nos/Year" || productUnit == "Nos/Month" || productUnit == "Nos/Week" ||
	 * productUnit == "Nos/Day"){ if(productUnit == "Nos/Year"){ unitDays =
	 * totalDays; }else if(productUnit == "Nos/Month"){ unitDays = noDays * 4; }else
	 * if(productUnit == "Nos/Week"){ unitDays = noDays * 7; }else if(productUnit ==
	 * "Nos/Day"){ unitDays = 1; } unitp = "Nos/Day"; } if(productUnit == "Nos/Day"
	 * || productUnit == "MT/Day" || productUnit == "Liter/Day" || productUnit ==
	 * "Kg/Day"){ float quant=quantitySum; if(quant < newQuantity1) {
	 * result="complied"; } else if(quant> newQuantity1) { result="true"; } } else {
	 * sumOfQuantityBetween=regularDataServices.sumOfQuantityBetween(productName,
	 * startDate, endDate); b=sumOfQuantityBetween- checkQuan;
	 * a1=(quantitySum-b)/unitDays; if(a1<0) { a1=0; result="false"; } else
	 * if(a1<newQuantity1) { result="complied"; } else if(a1>newQuantity1) {
	 * result="true"; } } if(unitp.equalsIgnoreCase("Nos/Day")) { a1=Math.round(a1);
	 * }else { a1 =Float.parseFloat(new DecimalFormat("##.#").format(a1)); } } }
	 * catch (Exception e) { e.printStackTrace(); } return result; }
	 */

	/**
	 * This method used to modify all product type data.
	 * 
	 * @param id the id
	 * @param mdate the modification date
	 * @param name the name of product
	 * @param type the type of product
	 * @param quan the quantity of product
	 * @param units the unit of product
	 * @return ModifiedData
	 */
	@RequestMapping(value = "dataModification")
	public ModelAndView dataModification(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "mdate", required = false) String mdate,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "quan", required = false) String quan,
			@RequestParam(value = "units", required = false) String units)
	{

		ModelAndView model;
		model = new ModelAndView();
		try
		{
			ArrayList<Object> obj = new ArrayList<>();
			obj.add(id);
			obj.add(mdate);
			obj.add(name);
			obj.add(type);
			obj.add(quan);
			obj.add(units);
			model.setViewName("EnvrOfficer/DataModification");
			model.addObject("ModifiedData", obj);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return model;
	}

	/**
	 * This method used to submit the updated data of all product.
	 * 
	 * @param rdId the regular data id
	 * @param type the type of product
	 * @param productName the name of product
	 * @param reason the reason of data not submitting
	 * @param updateFor the updated by username
	 * @param oldQuantity the old quantity of product
	 * @param quan the quantity of product
	 * @param request the servlet request we are processing.
	 * @return redirect:envr-officer-view-regular-data otherwise
	 *         redirect:dataModification
	 */

	// @RequestMapping("/ajax-submitDataUpdate")
	// public ModelAndView UpdateRegularData(@RequestParam(value = "rd_id", required = false) String rdId,
	// @RequestParam(value = "type", required = false) String type,
	// @RequestParam(value = "product_name", required = false) String productName,
	// @RequestParam(value = "reason", required = false) String reason,
	// @RequestParam(value = "update_for", required = false) String updateFor,
	// @RequestParam(value = "old_quantity", required = false) String oldQuantity,
	// @RequestParam(value = "quan", required = false) String quan, HttpServletRequest request)
	// {
	// ModelAndView model;
	// model = new ModelAndView();
	// try
	// {
	// EmpData empDataSession = (EmpData) request.getSession().getAttribute(Constant.EMPDATASESSION);
	// int userId = empDataSession.getUsers().getUsersId();
	// String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.DATEFORMAT));
	// if (type.equalsIgnoreCase("update"))
	// {
	// int rId = Integer.parseInt(rdId);
	// float quantity = Float.parseFloat(quan);
	// float oldquantity = Float.parseFloat(oldQuantity);
	// int updateResult = regularDataServices.updateQuantity(quantity, rId);
	// if (updateResult > 0)
	// {
	// ModifiedRecord mr = new ModifiedRecord();
	// // mr.setRequestFor(productName);
	// // mr.setAction("modify");
	// // mr.setQuantity(oldquantity);
	// // mr.setModifiedDate(today);
	// // mr.setRequestForDate(updateFor);
	// // mr.setModifiedBy(userId);
	// // mr.setReason(reason);
	// //
	// mr.setNewQuantity(quantity);
	// // modifiedRecordServices.save(mr);
	// model.setViewName("redirect:envr-officer-view-regular-data");
	// }
	// else
	// {
	// model.setViewName("redirect:dataModification");
	// }
	// }
	// }
	// catch (NumberFormatException e)
	// {
	// LOGGER.error(e);
	// }
	// return model;
	// }

	/**
	 * This method used to submit data
	 * 
	 * @param type the type of product
	 * @param productName the name of product
	 * @param newQuantity the new quantity of product
	 * @param missedDate Missing date
	 * @param reason the reason of data not submitting
	 * @param rdId the regular data id
	 * @param updateFor the updated by username
	 * @param oldQuantity the old quantity of product
	 * @param quan the quantity of product
	 * @param request the servlet request we are processing.
	 * @return result1 = resultRegularDataa + resultModifiedRecords
	 */
	// @RequestMapping("/ajax-submitData")
	// public @ResponseBody String AddRegularData(@RequestParam(value = "type", required = false) String type,
	// @RequestParam(value = "product_name", required = false) String productName,
	// @RequestParam(value = "new_quantity", required = false) float newQuantity,
	// @RequestParam(value = "missed_date", required = false) String missedDate,
	// @RequestParam(value = "reason", required = false) String reason,
	// @RequestParam(value = "rd_id", required = false) String rdId,
	// @RequestParam(value = "update_for", required = false) String updateFor,
	// @RequestParam(value = "old_quantity", required = false) String oldQuantity,
	// @RequestParam(value = "quan", required = false) String quan, HttpServletRequest request)
	// {
	//
	// String result1;
	// result1 = null;
	// int resultRegularData = 0;
	// int resultModifiedRecord = 0;
	// String resultRegularDataa = null;
	// String resultModifiedRecords = null;
	// List<Object[]> comparitiveId = null;
	// String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.DATEFORMAT));
	// try
	// {
	// EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
	// int userId = empDataSession.getUsers().getUsersId();
	// if (type.equalsIgnoreCase("add"))
	// {
	// comparitiveId = regularDataServices.getAllproductComparitiveId(productName, new PageRequest(0, 1));
	// if (comparitiveId != null)
	// {
	// for (Object[] comparitiveData : comparitiveId)
	// {
	// Integer cmpId = Integer.parseInt((String) comparitiveData[0]);
	// Users u = new Users();
	// u.setUsersId(userId);
	// AllProductComparativeSheet apc = new AllProductComparativeSheet();
	// apc.setAllProductComparativeSheetId(cmpId);
	//
	// RegularData rd = new RegularData();
	// rd.setAllProductComparativeSheet(apc);
	// rd.setQuantity(newQuantity);
	// rd.setInputDate(missedDate);
	// rd.setUsers(u);
	// resultRegularData = regularDataServices.save(rd).getRegularDataId();
	// if (resultRegularData > 0)
	// {
	// resultRegularDataa = "success";
	// ModifiedRecord mr = new ModifiedRecord();
	// /*
	// * mr.setRequestFor(productName);
	// * mr.setAction("add");
	// * mr.setQuantity(newQuantity);
	// * mr.setModifiedDate(today);
	// * mr.setRequestForDate(missedDate);
	// * mr.setModifiedBy(userId);
	// * mr.setReason(reason);
	// */ mr.setNewQuantity(newQuantity);
	// // resultModifiedRecord = modifiedRecordServices.save(mr).getModifiedRecordId();
	// if (resultModifiedRecord > 0)
	// {
	// resultModifiedRecords = "-success";
	// }
	// else
	// {
	// resultModifiedRecords = "-unsuccess";
	// }
	// }
	// else
	// {
	// resultModifiedRecords = "unsuccess";
	// }
	// }
	// result1 = resultRegularDataa + resultModifiedRecords;
	// }
	// }
	// }
	// catch (Exception e)
	// {
	// LOGGER.error(e);
	// }
	// return result1;
	// }

	/**
	 * This method used to get product data by using product name.
	 * 
	 * @param productType the type of product
	 * @return jsonArray the array value at the specified position in this array
	 */
	@RequestMapping("/ajax-getTypesNames")
	public @ResponseBody String getTypesNames(@RequestParam(value = "type") String productType)
	{

		List<String> productNameList = null;
		JSONArray jsonArray = new JSONArray();
		String today = Utilities.getTodaysDate();
		try
		{
			if (productType.equalsIgnoreCase("raw"))
				productType = "Raw Material";
			else if (productType.equalsIgnoreCase("bio"))
				productType = "Bio-Medical";

			productNameList = allProductNameServices.getProductName(productType, today);
			if (!productNameList.isEmpty())
			{
				for (int i = 0; i < productNameList.size(); i++)
				{
					String productName = productNameList.get(i);
					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("productName", productName);
					jsonArray.put(hashMap);
				}
			}
			else
			{
				HashMap<String, Object> hashMap = new HashMap<>();
				hashMap.put("productName", "NA");
				jsonArray.put(hashMap);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	@RequestMapping(value = {"ajax-get-each-regular-data"}, method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String GetEachData(@RequestParam(value = "pdata") String pdata)
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		ArrayList<String> ProductionList = new ArrayList<String>();
		ProductionList.add("Product");
		ProductionList.add("ByProduct");
		ProductionList.add("Raw Material");
		ProductionList.add("Fuel");
		ProductionList.add("hwp");
		ProductionList.add("hwpcf");
		ProductionList.add("nhwp");
		ProductionList.add("nhwpcf");

		try
		{
			for (int i = 0; i < ProductionList.size(); i++)
			{

				json = new JSONObject();
				List<RegularData> productDetailsArrayList = new ArrayList<>();
				productDetailsArrayList = regularDataServices.getProductDetailsData(pdata, ProductionList.get(i));
				JSONArray jsonPollDataArray1 = new JSONArray();

				if (!Validator.isEmpty(productDetailsArrayList))
				{

					for (int j = 0; j < productDetailsArrayList.size(); j++)
					{
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("RQuantity", new Float(productDetailsArrayList.get(j).getQuantity())); // get
						hashMap.put("ApcQuantity", new Float(productDetailsArrayList.get(j).getAllProductComparativeSheet().getQuantity()));
						hashMap.put("ProductName", new String(productDetailsArrayList.get(j).getAllProductComparativeSheet().getAllProducts().getAllProductName().getProductName()));
						hashMap.put("InputDate", new String(productDetailsArrayList.get(j).getInputDate()));
						hashMap.put("regId", new Integer(productDetailsArrayList.get(j).getRegularDataId()));
						hashMap.put("productUnit", new String(productDetailsArrayList.get(j).getAllProductComparativeSheet().getAllProducts().getUnit().getUnits()));

						jsonPollDataArray1.put(hashMap);
					}
					json.put(ProductionList.get(i), jsonPollDataArray1);
					FinalArray.put(json);
				}

			}

		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return FinalArray.toString();
	}

	@RequestMapping(value = {"ajax-get-each-regular-dataAir"}, method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String GetEachDataAir(@RequestParam(value = "pdata") String pdata)
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;

		try
		{
			json = new JSONObject();
			List<RegAPC> productDetailsArrayList = regAPCServices.getProductDetailsDataAir(pdata);
			JSONArray jsonPollDataArray1 = new JSONArray();

			if (!Validator.isEmpty(productDetailsArrayList))
			{

				for (int j = 0; j < productDetailsArrayList.size(); j++)
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("InputDate", new String(productDetailsArrayList.get(j).getApcDate()));
					hashMap.put("Productname", new String(productDetailsArrayList.get(j).getStack().getApcSystem()));
					hashMap.put("StartReading", new Float(productDetailsArrayList.get(j).getStartReading()));
					hashMap.put("EndReading", new Float(productDetailsArrayList.get(j).getEndReading()));
					hashMap.put("ActualReading", new Float(productDetailsArrayList.get(j).getActualReading()));
					jsonPollDataArray1.put(hashMap);
				}
				json.put("apc", jsonPollDataArray1);
				FinalArray.put(json);
			}

		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return FinalArray.toString();
	}

	@RequestMapping(value = {"ajax-view-daily-water-source-data"}, method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String viewWaterSourceData(@RequestParam(value = "dailydate") String selectedDate,
			HttpServletRequest request)
	{
		JSONArray waterSourceArray = new JSONArray();
		JSONObject waterSourceObject = new JSONObject();
		JSONObject waterSourceDataObject = new JSONObject();
		List<RegWaterSourceData> regWaterSourceData = new ArrayList<>();
		ArrayList<Object> regWaterSourceDataList = new ArrayList<>();
		try
		{
			regWaterSourceData = regWaterSourceDataServices.getRegWaterSourceDataBySelectedDate(selectedDate);
			if (!Validator.isEmpty(regWaterSourceData))
			{
				waterSourceDataObject.put("staff", regWaterSourceData.get(0).getStaff());
				String userName = regWaterSourceData.get(0).getUsers().getUserName();
				for (RegWaterSourceData regWaterSource : regWaterSourceData)
				{
					HashMap<String, Object> sourceList = new HashMap<String, Object>();
					sourceList.put("regSourceId", new Integer(regWaterSource.getRegWaterSourceDataId()));
					sourceList.put("startReading", new Float(regWaterSource.getStartReading()));
					sourceList.put("endReading", new Float(regWaterSource.getEndReading()));
					sourceList.put("avgReading", new Float(regWaterSource.getActualReading()));
					sourceList.put("regSourceDate", regWaterSource.getCreateDateTime());
					sourceList.put("wsId", new Integer(regWaterSource.getWaterSource().getWaterSourceId()));
					sourceList.put("sourceName", new String(regWaterSource.getWaterSource().getSourceName()));
					sourceList.put("sourceMeter", new Boolean(regWaterSource.getWaterSource().isSourceMeter()));
					sourceList.put("userName", new String(userName));
					if (!regWaterSourceDataList.contains(sourceList))
					{
						regWaterSourceDataList.add(sourceList);
					}
					waterSourceDataObject.put("waterSourceData", regWaterSourceDataList);
				}
				waterSourceObject.put("Data", waterSourceDataObject);
				waterSourceObject.put("Result", true);
			}
			else
			{
				waterSourceObject.put("Data", waterSourceDataObject);
				waterSourceObject.put("Result", false);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		waterSourceArray.put(waterSourceObject);
		return waterSourceArray.toString();
	}

	@RequestMapping(value = {"ajax-daily-view-prefilter-data"}, method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String viewPrefilterDailyData(@RequestParam(value = "dailydate") String selectedDate,
			HttpServletRequest request)
	{
		JSONArray prefilterArray = new JSONArray();
		JSONObject prefilterObject = new JSONObject();
		JSONObject prefilterDataObject = new JSONObject();
		List<RegPrefilter> regPrefilterData = new ArrayList<>();
		ArrayList<Object> regPrefilterDataList = new ArrayList<>();
		try
		{
			regPrefilterData = regPrefilterServices.getRegPrefilterBySelectedDate(selectedDate);
			if (!Validator.isEmpty(regPrefilterData))
			{
				for (RegPrefilter regPrefilter : regPrefilterData)
				{
					HashMap<String, Object> prefilterList = new HashMap<String, Object>();
					prefilterList.put("prefilterName", new String(regPrefilter.getPrefilter().getWaterSource().getSourceName() + " Prefilter- " + regPrefilter.getPrefilter().getPrefilterId()));
					prefilterList.put("startReading", new Float(regPrefilter.getStartReading()));
					prefilterList.put("endReading", new Float(regPrefilter.getEndReading()));
					prefilterList.put("actReading", new Float(regPrefilter.getActualReading()));
					prefilterList.put("isMeter", new Boolean(regPrefilter.getPrefilter().isMeter()));
					prefilterList.put("userName", new String(regPrefilter.getUsers().getUserName()));
					prefilterList.put("prefilterId", new Integer(regPrefilter.getPrefilter().getPrefilterId()));
					prefilterList.put("regPrefilterId", new Integer(regPrefilter.getRegprefilterId()));
					prefilterList.put("regPrefilterDate", regPrefilter.getCreateDateTime());
					if (!regPrefilterDataList.contains(prefilterList))
					{
						regPrefilterDataList.add(prefilterList);
					}
				}
				prefilterDataObject.put("prefilterList", regPrefilterDataList);
				prefilterObject.put("Data", prefilterDataObject);
				prefilterObject.put("Result", true);
			}
			else
			{
				prefilterObject.put("Data", prefilterDataObject);
				prefilterObject.put("Result", false);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		prefilterArray.put(prefilterObject);
		return prefilterArray.toString();
	}

	@RequestMapping(value = "ajax-daily-view-filter-data", method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String viewDailyFilterData(@RequestParam(value = "dailydate") String selectedDate,
			HttpServletRequest request)
	{
		JSONArray filterArray = new JSONArray();
		JSONObject filterObject = new JSONObject();
		JSONObject filterDataObject = new JSONObject();
		List<RegMultipleFilterData> regMultipleFilterData = new ArrayList<>();
		ArrayList<Object> regMultipleFilterDataList = new ArrayList<>();

		try
		{
			regMultipleFilterData = regMultipleFilterDataServices.getRegMultipleFilterDataBySelectedDate(selectedDate);
			if (!Validator.isEmpty(regMultipleFilterData))
			{
				for (RegMultipleFilterData regMultipleFilter : regMultipleFilterData)
				{
					HashMap<String, Object> filterList = new HashMap<String, Object>();
					filterList.put("filterId", regMultipleFilter.getMultipleFilter().getMultipleFilterId());
					filterList.put("regMultipleFilterId", regMultipleFilter.getRegMultipleFilterDataId());
					filterList.put("filterLabel", regMultipleFilter.getMultipleFilter().getFilterLabel());
					filterList.put("filterType", regMultipleFilter.getMultipleFilter().getFilters().getFilterType());
					filterList.put("isMeter", regMultipleFilter.getMultipleFilter().isMeter());
					filterList.put("startReading", regMultipleFilter.getStartReading());
					filterList.put("endReading", regMultipleFilter.getEndReading());
					filterList.put("actualReading", regMultipleFilter.getActualReading());
					filterList.put("userName", regMultipleFilter.getUsers().getUserName());

					if (!regMultipleFilterDataList.contains(filterList))
					{
						regMultipleFilterDataList.add(filterList);
					}
				}
				filterDataObject.put("filtersData", regMultipleFilterDataList);
				filterObject.put("Data", filterDataObject);
				filterObject.put("Result", true);
			}
			else
			{
				filterObject.put("Data", filterDataObject);
				filterObject.put("Result", false);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage());
		}
		filterArray.put(filterObject);
		return filterArray.toString();
	}

	@RequestMapping(value = "ajax-daily-view-filteruse-data", method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String viewDailyFilterUseData(@RequestParam(value = "dailydate") String selectedDate,
			HttpServletRequest request)
	{
		JSONArray filterUseArray = new JSONArray();
		JSONObject filterUseObject = new JSONObject();
		JSONObject filterUseDataObject = new JSONObject();
		List<RegFiltersUseData> regFiltersUseData = new ArrayList<>();
		ArrayList<Object> regFilterUseDataList = new ArrayList<>();
		List<FilterUse> getFilterUseDataByLabel = new ArrayList<>();
		try
		{
			regFiltersUseData = regFiltersUseDataServices.getRegFilterUseDataBySelectedDate(selectedDate);
			if (!Validator.isEmpty(regFiltersUseData))
			{
				for (RegFiltersUseData regFiltersUse : regFiltersUseData)
				{
					HashMap<String, Object> filterUseList = new HashMap<String, Object>();
					filterUseList.put("filterUseLabel", new String(regFiltersUse.getFilterUseLabel()));
					String filterUseLabel = regFiltersUse.getFilterUseLabel();

					getFilterUseDataByLabel = filterUseServices.getFilterUseDataByLabel(filterUseLabel);
					if (!Validator.isEmpty(getFilterUseDataByLabel))
					{
						for (FilterUse filterUse : getFilterUseDataByLabel)
						{
							filterUseList.put("isMeter", new Boolean(filterUse.isMeter()));
							filterUseList.put("filterUseLabel", new String(filterUse.getFilterUseLabel()));
							filterUseList.put("filterUseType", new String(filterUse.getFilterUseType()));
						}
					}
					filterUseList.put("startReading", new Float(regFiltersUse.getStartReading()));
					filterUseList.put("endReading", new Float(regFiltersUse.getEndReading()));
					filterUseList.put("actualReading", new Float(regFiltersUse.getActualReading()));
					filterUseList.put("userName", new String(regFiltersUse.getUsers().getUserName()));
					if (!regFilterUseDataList.contains(filterUseList))
					{
						regFilterUseDataList.add(filterUseList);
					}
				}
				filterUseDataObject.put("filterUseData", regFilterUseDataList);
				filterUseObject.put("Data", filterUseDataObject);
				filterUseObject.put("Result", true);
			}
			else
			{
				filterUseObject.put("Data", filterUseDataObject);
				filterUseObject.put("Result", false);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage());
		}

		filterUseArray.put(filterUseObject);
		return filterUseArray.toString();
	}

	@RequestMapping(value = "ajax-daily-view-direct-use-of-water-data", method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String viewDailyDirectUseOfWaterData(@RequestParam(value = "dailydate") String selectedDate,
			HttpServletRequest request)
	{
		JSONArray directUseOfWaterArray = new JSONArray();
		JSONObject directUseOfWaterObject = new JSONObject();
		JSONObject directUseOfWaterDataObject = new JSONObject();
		List<RegDirectUseOfWaterData> regDirectUseOfWaterData = new ArrayList<>();
		ArrayList<Object> regDirectUseOfWaterDataList = new ArrayList<>();
		List<DirectUseOfWater> directUseOfWaterData = new ArrayList<>();
		try
		{
			regDirectUseOfWaterData = regDirectUseOfWaterDataServices.getRegDirectUseOfWaterDataBySelectedDate(selectedDate);
			if (!Validator.isEmpty(regDirectUseOfWaterData))
			{
				for (RegDirectUseOfWaterData regDirectUseOfWater : regDirectUseOfWaterData)
				{
					HashMap<String, Object> directUseList = new HashMap<String, Object>();
					String directUseName = regDirectUseOfWater.getWhereToUse();
					directUseOfWaterData = directUseOfWaterServices.getUseOfWaterByWhereToUse(directUseName);
					if (!Validator.isEmpty(directUseOfWaterData))
					{
						for (DirectUseOfWater directUseOfWater : directUseOfWaterData)
						{
							directUseList.put("whereToUse", new String(directUseOfWater.getWhereToUse()));
							directUseList.put("typeOfUse", new String(directUseOfWater.getTypeOfUse()));
							directUseList.put("isMeter", new Boolean(directUseOfWater.isMeter()));
						}
					}
					directUseList.put("startReading", new Float(regDirectUseOfWater.getStartReading()));
					directUseList.put("endReading", new Float(regDirectUseOfWater.getEndReading()));
					directUseList.put("actualReading", new Float(regDirectUseOfWater.getActualReading()));
					directUseList.put("userName", new String(regDirectUseOfWater.getUsers().getUserName()));
					if (!regDirectUseOfWaterDataList.contains(directUseList))
					{
						regDirectUseOfWaterDataList.add(directUseList);
					}
				}
				directUseOfWaterDataObject.put("directUseOfWaterData", regDirectUseOfWaterDataList);
				directUseOfWaterObject.put("Data", directUseOfWaterDataObject);
				directUseOfWaterObject.put("Result", true);
			}
			else
			{
				directUseOfWaterObject.put("Data", directUseOfWaterDataObject);
				directUseOfWaterObject.put("Result", false);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage());
		}
		directUseOfWaterArray.put(directUseOfWaterObject);
		return directUseOfWaterArray.toString();
	}

	@RequestMapping(value = "ajax-daily-view-treatment-data", method = RequestMethod.POST)
	@ResponseBody
	public @JsonRawValue String viewDailyTreatmentData(@RequestParam(value = "dailydate") String selectedDate,
			HttpServletRequest request)
	{
		JSONArray treatmentArray = new JSONArray();
		JSONObject treatmentObject = new JSONObject();
		JSONObject treatmentDataObject = new JSONObject();
		List<RegularTreatmentData> regularTreatmentData = new ArrayList<>();
		ArrayList<Object> regularTreatmentDataList = new ArrayList<>();
		List<RegWastewaterRecycle> regWastewaterRecycleData = new ArrayList<>();

		regularTreatmentData = regularTreatmentDataServices.getRegTreatmentDataBySelectedDate(selectedDate);
		if (!Validator.isEmpty(regularTreatmentData))
		{
			for (RegularTreatmentData regularTreatment : regularTreatmentData)
			{
				HashMap<String, Object> treatmentList = new HashMap<String, Object>();
				treatmentList.put("treatmentLable", new String(regularTreatment.getWastewaterTreatment().getLabel()));
				treatmentList.put("treatmentId", new Integer(regularTreatment.getWastewaterTreatment().getWastewaterTreatmentId()));
				treatmentList.put("regularTreatmentId", new Integer(regularTreatment.getRegularTreatmentDataId()));
				treatmentList.put("startReading", new Float(regularTreatment.getStartReading()));
				treatmentList.put("endReading", new Float(regularTreatment.getEndReading()));
				treatmentList.put("actualReading", new Float(regularTreatment.getActualReading()));
				treatmentList.put("energyStartReading", new Float(regularTreatment.getEnergyStartReading()));
				treatmentList.put("energyEndReading", new Float(regularTreatment.getEnergyEndReading()));
				treatmentList.put("energyActualReading", new Float(regularTreatment.getEnergyAvg()));
				treatmentList.put("userName", new String(regularTreatment.getUsers().getUserName()));

				regWastewaterRecycleData = regWastewaterRecycleServices.getRegWastewaterRecycleDataBySelectedDate(selectedDate, regularTreatment.getWastewaterTreatment().getWastewaterTreatmentId());
				if (!Validator.isEmpty(regWastewaterRecycleData))
				{
					ArrayList<Object> regWastewaterRecycleList = new ArrayList<>();
					for (RegWastewaterRecycle regWastewaterRecycle : regWastewaterRecycleData)
					{
						HashMap<String, Object> recycleList = new HashMap<String, Object>();
						recycleList.put("recycleId", new Integer(regWastewaterRecycle.getWastewaterRecycle().getWastewaterRecycleId()));
						recycleList.put("regRecycleId", new Integer(regWastewaterRecycle.getRegWastewaterRecycleId()));
						recycleList.put("useType", new String(regWastewaterRecycle.getWastewaterRecycle().getUseType()));
						recycleList.put("recycledTo", new String(regWastewaterRecycle.getWastewaterRecycle().getRecycledTo()));
						recycleList.put("recycleStartReading", new Float(regWastewaterRecycle.getStartReading()));
						recycleList.put("recycleEndReading", new Float(regWastewaterRecycle.getEndReading()));
						recycleList.put("recycleActualReading", new Float(regWastewaterRecycle.getActualReading()));
						recycleList.put("isMeter", new Boolean(regWastewaterRecycle.getWastewaterRecycle().isMeter()));
						recycleList.put("userName", new String(regWastewaterRecycle.getUsers().getUserName()));
						if (!regWastewaterRecycleList.contains(recycleList))
						{
							regWastewaterRecycleList.add(recycleList);
						}
						treatmentList.put("recycleData", regWastewaterRecycleList);
					}
				}
				if (!regularTreatmentDataList.contains(treatmentList))
				{
					regularTreatmentDataList.add(treatmentList);
				}
			}

			treatmentDataObject.put("treatmentData", regularTreatmentDataList);
			treatmentObject.put("Data", treatmentDataObject);
			treatmentObject.put("Result", true);
		}
		else
		{
			treatmentObject.put("Data", treatmentDataObject);
			treatmentObject.put("Result", false);
		}
		treatmentArray.put(treatmentObject);
		return treatmentArray.toString();
	}

	@PostMapping("ajax-save-modified-data")
	public boolean getModifiedData(@RequestBody JsonObject jsonObjModifiedRecord)
	{

		String itemName = jsonObjModifiedRecord.getAsJsonObject().get("itemName").getAsString();
		RegularData regularData = new RegularData();
		regularData.setRegularDataId(jsonObjModifiedRecord.getAsJsonObject().get("regId").getAsInt());
		Users users = new Users();
		users.setUsersId(authenticationUtil.getLoggedInUser().getUsersId());
		float cQuantity = jsonObjModifiedRecord.getAsJsonObject().get("cQuantity").getAsFloat();
		float nQuantity = jsonObjModifiedRecord.getAsJsonObject().get("nQuantity").getAsFloat();
		String mReason = jsonObjModifiedRecord.getAsJsonObject().get("mReason").getAsString();
		String inpDate = jsonObjModifiedRecord.getAsJsonObject().get("inpDate").getAsString();
		List<Todo> allTodos = new ArrayList<>();
		List<EmpData> managmentEmpList = new ArrayList<>();
		managmentEmpList = empDataRepository.findByContPerDesig("Management");
		if (!Validator.isEmpty(managmentEmpList))
		{
			for (int i = 0; i < managmentEmpList.size(); i++)
			{
				String createDate = Utilities.getTodaysDate();
				EmpData empData = new EmpData();
				EmpData getLoggedInEmp = authenticationUtil.getLoggedInEmp();
				String empName = getLoggedInEmp.getEmployeeName();

				empData.setEmpDataId(managmentEmpList.get(i).getEmpDataId());
				Todo todo = new Todo();
				todo.setTodo(" You've New Modify Request Of " + inpDate + " by " + empName);
				todo.setCreateDate(createDate);
				todo.setAction("new");
				todo.setEmpData(empData);
				allTodos.add(todo);

			}
		}

		todoRepository.save(allTodos);

		// JsonObject modifiedData = jsonObjModifiedRecord.getAsJsonObject("myObject");
		ModifiedRecord objModifiedRecord = new ModifiedRecord();

		// objModifiedRecord.setRequestDate(null);
		objModifiedRecord.setApprovalDate(null);
		objModifiedRecord.setRequestedById(users);
		objModifiedRecord.setRegId(regularData);
		objModifiedRecord.setOldQuantity(cQuantity);
		objModifiedRecord.setNewQuantity(nQuantity);
		objModifiedRecord.setAction("New");
		objModifiedRecord.setReason(mReason);

		modifiedRecordRepository.save(objModifiedRecord);

		System.out.println("success");
		return false;
	}

	// Effected By Water Inventory ........by vishal
	/*
	 * @RequestMapping(value = {"ajax-get-each-regular-dataFilter"}, method =
	 * RequestMethod.POST)
	 * @ResponseBody public @JsonRawValue String
	 * GetEachDataFilter(@RequestParam(value = "pdata") String pdata,
	 * HttpServletRequest request) { JSONArray FinalArray = new JSONArray();
	 * JSONObject json; EmpData empDataSession = null;
	 * try {
	 * int companyId = 0; List<WaterInventory> waterInventoryData = new
	 * ArrayList<>();
	 * if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
	 * { empDataSession = (EmpData)
	 * request.getSession().getAttribute("empDataSession"); }
	 * if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
	 * { companyId = empDataSession.getCompanyProfile().getCompanyProfileId(); }
	 * waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId,
	 * Utilities.getTodaysDate());
	 * for (int i = 0; i < waterInventoryData.size(); i++) { int waterInventoryId =
	 * 0; WaterInventory wiDetail = new WaterInventory(); wiDetail =
	 * waterInventoryData.get(i); waterInventoryId = wiDetail.getWaterInventoryId();
	 * }
	 * List<RegMultipleFilterData> productDetailsArrayList =
	 * regularFiltersDataServices.RegularFiltersDataBydate(pdata); if
	 * (!Validator.isEmpty(productDetailsArrayList)) {
	 * for (int j = 0; j < productDetailsArrayList.size(); j++) { json = new
	 * JSONObject(); JSONArray jsonPollDataArray1 = new JSONArray(); String
	 * filtername = productDetailsArrayList.get(j).getFilterName(); String
	 * waterMeter = filtersServices.getMetervaluefilter(filtername);
	 * json.put("FilterName", new String(filtername)); json.put("RegId", new
	 * Integer(productDetailsArrayList.get(j).getRegularFiltesDataId()));
	 * json.put("StartReading", new
	 * Float(productDetailsArrayList.get(j).getStartReading()));
	 * json.put("EndReading", new
	 * Float(productDetailsArrayList.get(j).getEndReading()));
	 * json.put("ActualReading", new
	 * Float(productDetailsArrayList.get(j).getActualReading()));
	 * json.put("UserName", new
	 * String(productDetailsArrayList.get(j).getUsers().getUserName()));
	 * json.put("waterMeter", new String(waterMeter));
	 * List<RegFiltersUseData> FilterUseArrayList =
	 * regularFiltersUseDataServices.RegularFiltersUseDataBydate(pdata, filtername);
	 * for (int i = 0; i < FilterUseArrayList.size(); i++) { String filterUsename =
	 * FilterUseArrayList.get(i).getFilterType(); String filterMeter =
	 * filtersServices.getMetervaluefiltertype(filtername, filterUsename);
	 * HashMap<String, Object> hashMap = new HashMap<String, Object>();
	 * hashMap.put("FilterUsename", new String(filterUsename));
	 * hashMap.put("StartReading", new
	 * Float(FilterUseArrayList.get(i).getStartReading()));
	 * hashMap.put("EndReading", new
	 * Float(FilterUseArrayList.get(i).getEndReading()));
	 * hashMap.put("ActualReading", new
	 * Float(FilterUseArrayList.get(i).getActualReading())); hashMap.put("RegId",
	 * new Integer(FilterUseArrayList.get(i).getRegularFiltersUseDataId()));
	 * hashMap.put("UserId", new
	 * Integer(FilterUseArrayList.get(i).getUsers().getUsersId()));
	 * hashMap.put("UserName", new
	 * String(FilterUseArrayList.get(i).getUsers().getUserName()));
	 * hashMap.put("usemeter", new String(filterMeter));
	 * jsonPollDataArray1.put(hashMap); } json.put("FilterUse", jsonPollDataArray1);
	 * FinalArray.put(json); } }
	 * } catch (Exception e) {
	 * LOGGER.error(e); }
	 * return FinalArray.toString(); }
	 */

	// Effected By Water Inventory ........by vishal
	/*
	 * @ResponseBody public @JsonRawValue String
	 * GetEachDataUseOfWater(@RequestParam(value = "pdata") String pdata,
	 * HttpServletRequest request) { JSONArray FinalArray = new JSONArray();
	 * JSONObject json; EmpData empDataSession = null; List<UseOfWater>
	 * useOfWaterData = new ArrayList<>(); List<Industrial>
	 * industrialDataByUseOfWaterId = new ArrayList<>(); try { int companyId = 0;
	 * int waterInventoryId = 0; String domesticUseOfSource = null,
	 * industrialUseOfsource = null, fireHydrantUseOfSource = null,
	 * laundryUseOfSource = null; List<WaterInventory> waterInventoryData = new
	 * ArrayList<>(); if
	 * (!Validator.isEmpty(request.getSession().getAttribute("empDataSession"))) {
	 * empDataSession = (EmpData)
	 * request.getSession().getAttribute("empDataSession"); }
	 * if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
	 * { companyId = empDataSession.getCompanyProfile().getCompanyProfileId(); }
	 * waterInventoryData = waterInventoryServices.getwaterInventoryData(companyId,
	 * Utilities.getTodaysDate());
	 * for (int i = 0; i < waterInventoryData.size(); i++) { WaterInventory wiDetail
	 * = new WaterInventory(); wiDetail = waterInventoryData.get(i);
	 * waterInventoryId = wiDetail.getWaterInventoryId();
	 * domesticUseOfSource = wiDetail.getDomesticUseOfSource();
	 * industrialUseOfsource = wiDetail.getIndustrialUseOfSource();
	 * laundryUseOfSource = wiDetail.getLaundryUseOfSource(); fireHydrantUseOfSource
	 * = wiDetail.getFireHydrantUseOfSource(); } useOfWaterData =
	 * useOfWaterServices.useOfWaterDataByWaterInventoryId(waterInventoryId);
	 * if (!Validator.isEmpty(useOfWaterData)) { String isDomestic = "",
	 * isIndustrial = "", isDomesticMeter = "", isLaundryMeter = "",
	 * isFireHydrantMeter = ""; for (int i = 0; i < useOfWaterData.size(); i++) {
	 * isDomestic = useOfWaterData.get(i).getDomestic(); isDomesticMeter =
	 * useOfWaterData.get(i).getDomesticMeter(); isIndustrial =
	 * useOfWaterData.get(i).getIndustrial();
	 * isLaundryMeter = useOfWaterData.get(i).getLaundryMeter(); isFireHydrantMeter
	 * = useOfWaterData.get(i).getFireHydrantMeter(); } // JSONArray
	 * jsonPollDataArray1 = new JSONArray(); if
	 * (industrialUseOfsource.equalsIgnoreCase("checked")) { for (int j = 0; j <
	 * useOfWaterData.size(); j++) { industrialDataByUseOfWaterId =
	 * industrialServices.industrialDataByUseOfWaterId(useOfWaterData.get(j).
	 * getUseOfWaterId());
	 * for (int i = 0; i < industrialDataByUseOfWaterId.size(); i++) { String
	 * indName = industrialDataByUseOfWaterId.get(i).getIndName(); DirectUseOfWater
	 * IndustrialArrayList = regularWaterUseDataServices.getdatawaterUse(indName,
	 * pdata); json = new JSONObject(); // HashMap<String, Object> hashMap = new
	 * HashMap<String, Object>(); json.put("waterMeter", new
	 * String(industrialDataByUseOfWaterId.get(i).getWaterMeter()));
	 * json.put("FilterUsename", new String(IndustrialArrayList.getSourceType()));
	 * json.put("StartReading", new Float(IndustrialArrayList.getStartReading()));
	 * json.put("EndReading", new Float(IndustrialArrayList.getEndReading()));
	 * json.put("ActualReading", new Float(IndustrialArrayList.getActualReading()));
	 * json.put("RegId", new
	 * Integer(IndustrialArrayList.getRegularWaterUseDataId()));
	 * json.put("UserName", new
	 * String(IndustrialArrayList.getUsers().getUserName())); //
	 * jsonPollDataArray1.put(hashMap); FinalArray.put(json); }
	 * } } if (domesticUseOfSource.equalsIgnoreCase("checked")) { json = new
	 * JSONObject(); String indName = "Domestic"; DirectUseOfWater DomasticArrayList
	 * = regularWaterUseDataServices.getdatawaterUse(indName, pdata); //
	 * HashMap<String, Object> hashMap = new HashMap<String, Object>(); if
	 * (!Validator.isEmpty(DomasticArrayList)) { json.put("waterMeter", new
	 * String(useOfWaterData.get(0).getDomesticMeter())); json.put("FilterUsename",
	 * new String(DomasticArrayList.getSourceType())); json.put("StartReading", new
	 * Float(DomasticArrayList.getStartReading())); json.put("EndReading", new
	 * Float(DomasticArrayList.getEndReading())); json.put("ActualReading", new
	 * Float(DomasticArrayList.getActualReading())); json.put("RegId", new
	 * Integer(DomasticArrayList.getRegularWaterUseDataId())); json.put("UserName",
	 * new String(DomasticArrayList.getUsers().getUserName())); //
	 * jsonPollDataArray1.put(hashMap); FinalArray.put(json); }
	 * } if (laundryUseOfSource.equalsIgnoreCase("checked")) { json = new
	 * JSONObject(); String indName = "Laundry"; DirectUseOfWater DomasticArrayList
	 * = regularWaterUseDataServices.getdatawaterUse(indName, pdata); //
	 * HashMap<String, Object> hashMap = new HashMap<String, Object>(); if
	 * (!Validator.isEmpty(DomasticArrayList)) { json.put("waterMeter", new
	 * String(useOfWaterData.get(0).getDomesticMeter())); json.put("FilterUsename",
	 * new String(DomasticArrayList.getSourceType())); json.put("StartReading", new
	 * Float(DomasticArrayList.getStartReading())); json.put("EndReading", new
	 * Float(DomasticArrayList.getEndReading())); json.put("ActualReading", new
	 * Float(DomasticArrayList.getActualReading())); json.put("RegId", new
	 * Integer(DomasticArrayList.getRegularWaterUseDataId())); json.put("UserName",
	 * new String(DomasticArrayList.getUsers().getUserName()));
	 * FinalArray.put(json); }
	 * } if (fireHydrantUseOfSource.equalsIgnoreCase("checked")) { json = new
	 * JSONObject(); String indName = "Fire Hydrant"; DirectUseOfWater
	 * DomasticArrayList = regularWaterUseDataServices.getdatawaterUse(indName,
	 * pdata); // HashMap<String, Object> hashMap = new HashMap<String, Object>();
	 * if (!Validator.isEmpty(DomasticArrayList)) { json.put("waterMeter", new
	 * String(useOfWaterData.get(0).getDomesticMeter())); json.put("FilterUsename",
	 * new String(DomasticArrayList.getSourceType())); json.put("StartReading", new
	 * Float(DomasticArrayList.getStartReading())); json.put("EndReading", new
	 * Float(DomasticArrayList.getEndReading())); json.put("ActualReading", new
	 * Float(DomasticArrayList.getActualReading())); json.put("RegId", new
	 * Integer(DomasticArrayList.getRegularWaterUseDataId())); json.put("UserName",
	 * new String(DomasticArrayList.getUsers().getUserName()));
	 * FinalArray.put(json); }
	 * } // json.put("domasticUse",jsonPollDataArray1); // FinalArray.put(json); } }
	 * catch (Exception e) {
	 * LOGGER.error(e); }
	 * return FinalArray.toString(); }
	 */
}
