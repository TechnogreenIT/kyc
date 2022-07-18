// package com.tes.controller.environmentalofficer;
//
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.text.ParseException;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;
//
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
//
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.apache.tomcat.util.codec.binary.Base64;
// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.SessionAttributes;
// import org.springframework.web.servlet.ModelAndView;
//
// import com.tes.controller.IndexController;
// import com.tes.handler.UserAuthenticationSuccessHandler;
// import com.tes.model.CompanyProfile;
// import com.tes.model.Consent;
// import com.tes.model.ConsentExtendedDate;
// import com.tes.model.Containers;
// import com.tes.model.EmpData;
// import com.tes.model.HazardousManifest;
// import com.tes.model.Settings;
// import com.tes.model.Todo;
// import com.tes.model.Unit;
// import com.tes.model.WasteDescriptionConsistency;
// import com.tes.services.SettingServices;
// import com.tes.services.TodoServices;
// import com.tes.services.UsersServices;
// import com.tes.services.admin.CompanyProfileServices;
// import com.tes.services.admin.EmpDataServices;
// import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
// import com.tes.services.environmentalofficer.AllProductNameServices;
// import com.tes.services.environmentalofficer.AllProductsServices;
// import com.tes.services.environmentalofficer.AmbientServices;
// import com.tes.services.environmentalofficer.ConsentExtendedDateServices;
// import com.tes.services.environmentalofficer.ConsentServices;
// import com.tes.services.environmentalofficer.ContainersServices;
// import com.tes.services.environmentalofficer.EsrHwSolidWasteServices;
// import com.tes.services.environmentalofficer.EsrInvestmentServices;
// import com.tes.services.environmentalofficer.EsrParticularsServices;
// import com.tes.services.environmentalofficer.EsrPollutionControlServices;
// import com.tes.services.environmentalofficer.EsrProductWaterServices;
// import com.tes.services.environmentalofficer.HazardousManifestServices;
// import com.tes.services.environmentalofficer.RegAPCServices;
// import com.tes.services.environmentalofficer.RegPollReasonsServices;
// import com.tes.services.environmentalofficer.RegularDataServices;
// import com.tes.services.environmentalofficer.UnitServices;
// import com.tes.services.environmentalofficer.ViewModifiedRecordServices;
// import com.tes.services.environmentalofficer.WasteDescriptionConsistencyServices;
// import com.tes.services.environmentalofficer.WaterConGenServices;
// import com.tes.services.environmentalofficer.WaterSewPollServices;
// import com.tes.services.environmentalofficer.WateriePollutantServices;
// import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
// import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
// import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
// import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
// import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
// import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
// import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
// import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
// import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
// import com.tes.services.thirdparty.RegAmbientPollServices;
// import com.tes.services.thirdparty.RegEffPollServices;
// import com.tes.services.thirdparty.RegSewPollServices;
// import com.tes.services.thirdparty.RegStPollServices;
// import com.tes.services.thirdparty.StackPollServices;
// import com.tes.services.thirdparty.StackServices;
// import com.tes.utilities.Constant;
// import com.tes.utilities.Utilities;
// import com.tes.utilities.Validator;
//
/// **
// * This class used to dispaly Enviromental dashbord and there functionality
// * other modules i.e. Filled /save/ display hazardous manifest from, get the ESR
// * from ,water budget, display hazardous return form display water consumption
// * data and water performance, and environmental officer update setting to there
// * profile
// *
// * @author Tushar Chougule
// * @author Sushama Dadas
// * @author Jemish Moradiya
// * @author Aashish Raut
// */
// @Controller
// @SessionAttributes({"empDataSession", "imgvalue", "multipleOperate", "emplogindata", "sessionIndustryType", "hmId",
// "industryCat", "uId", "userRole", "companyName", "userProfilePic"})
//
// public class EnvironmentalOfficerController
// {
//
// @Autowired
// UsersServices usersServices;
//
// @Autowired
// IndexController indexContr;
//
// @Autowired
// EmpDataServices empDataServices;
//
// @Autowired
// SettingServices settingServices;
//
// @Autowired
// ConsentServices consentServices;
//
// @Autowired
// TodoServices todoServices;
//
// @Autowired
// EsrHwSolidWasteServices esrHwSolidWasteServices;
//
// @Autowired
// EsrPollutionControlServices esrPollutionControlServices;
//
// @Autowired
// EsrInvestmentServices esrInvestmentServices;
//
// @Autowired
// EsrParticularsServices esrParticularsServices;
//
// @Autowired
// ConsentExtendedDateServices consentExtendedDateServices;
//
// @Autowired
// CompanyProfileServices companyProfileServices;
//
// @Autowired
// RegularDataServices regularDataServices;
//
// @Autowired
// RegAPCServices regAPCServices;
//
// @Autowired
// WaterInventoryServices waterInventoryServices;
//
// @Autowired
// RegWaterSourceDataServices regularSourceDataServices;
//
// @Autowired
// WaterSourceServices waterSourceServices;
//
// @Autowired
// FiltersServices filtersServices;
//
// @Autowired
// RegMultipleFilterDataServices regularFiltersDataServices;
//
// @Autowired
// RegDirectUseOfWaterDataServices regularWaterUseDataServices;
//
// @Autowired
// WastewaterTreatmentServices wastewaterTreatmentServices;
//
// @Autowired
// RegularTreatmentDataServices regularTreatmentDataServices;
//
// @Autowired
// RegFiltersUseDataServices regularFiltersUseDataServices;
//
// @Autowired
// UnitServices unitServices;
//
// @Autowired
// StackServices stackServices;
//
// @Autowired
// HazardousManifestServices hazardousManifestServices;
//
// @Autowired
// WasteDescriptionConsistencyServices wasteDescriptionConsistencyServices;
//
// @Autowired
// ContainersServices containersServices;
//
// @Autowired
// WaterConGenServices waterConGenServices;
//
// @Autowired
// AllProductsServices allProductsServices;
//
// @Autowired
// RegStPollServices regStPollServices;
//
// @Autowired
// RegSewPollServices regSewPollServices;
//
// @Autowired
// RegAmbientPollServices regAmbientPollServices;
//
// @Autowired
// RegEffPollServices regEffPollServices;
//
// @Autowired
// WateriePollutantServices wateriePollutantServices;
//
// @Autowired
// AmbientServices ambientServices;
//
// @Autowired
// WaterSewPollServices waterSewPollServices;
//
// @Autowired
// EsrProductWaterServices esrProductWaterServices;
//
// @Autowired
// RegPollReasonsServices regPollReasonsServices;
//
// @Autowired
// StackPollServices stackPollServices;
//
// @Autowired
// AllProductNameServices allProductNameServices;
//
// @Autowired
// ViewModifiedRecordServices viewModifiedRecordServices;
//
// @Autowired
// AllProductComparativeSheetServices allProductComparativeSheetServices;
//
// private static final Logger LOGGER = LogManager.getLogger(EnvironmentalOfficerController.class);
//
// /**
// * This method used to display the daskboard of environmental officer.
// *
// * @param request the servlet request we are processing.
// * @return environmental officer dashboard
// * @throws IOException if an input/output error occurs
// * @throws ServletException if a servlet-specified error occurs
// * @throws ParseException if parsing causes an error
// */
//
// @Secured("ROLE_ENVROFFICER")
// @RequestMapping(value = "/envrOfficerDesk")
// public ModelAndView getEnvrDesk(HttpServletRequest request) throws IOException, ServletException, ParseException
// {
// ModelAndView modelAndView = new ModelAndView();
// int yearFrom = 0, yearTo = 0;
// List esrHwSolidHazardousWasteList = new ArrayList<>();
// List esrHwSolidWasteList = new ArrayList<>();
// List esrPollutionList = new ArrayList<>();
// List esrInvestmentList = new ArrayList<>();
// List esrNextYearInvestmentList = new ArrayList<>();
// List esrParticularsList = new ArrayList<>();
// boolean flag = false;
// int todo_id = 0;
// String consentNo = null, validUpto = null;
// modelAndView.setViewName("EnvrOfficer/Officer");
// try
// {
// int usersId = usersServices.findUserIdByUserName(UserAuthenticationSuccessHandler.userName);
// request.getSession().setAttribute("userId", usersId);
// EmpData empDataSession = empDataServices.findByUserId(usersId);
// String industryCat = empDataSession.getCompanyProfile().getIndustryCategory();
// String userRole = empDataSession.getContPerDesig();
//
// String[] industryCategory = industryCat.split("-");
// String sessionIndustryType = industryCategory[0].replaceAll("\\s+", "");
//
// CompanyProfile companyData = companyProfileServices.findOne();
// // profiles pic by jemish
// String userProfilePic = empDataSession.getProfilePic();
// String filePath = Constant.UserProfiles_pic_path + userProfilePic;
//
// if (filePath == null)
// {
// filePath = Constant.DEFAULT_PROFILE_PIC;
// }
// File file = new File(filePath);
//
// byte[] userProfilePic1 = Files.readAllBytes(file.toPath());
// byte[] encodeBase64 = Base64.encodeBase64(userProfilePic1);
// String proPic = new String(encodeBase64, "UTF-8");
// // profiles pic by jemish
//
// Settings settingsdata = settingServices.findSettingData(usersId);
//
// int imgvalue = settingsdata.getBackgroundImage();
// modelAndView.addObject("imgvalue", imgvalue);
// modelAndView.addObject("multipleOperate", " ");
// EmpData userprofile = empDataServices.getUserProfileData(usersId);
// modelAndView.addObject("empDataSession", empDataSession);
// modelAndView.addObject("emplogindata", userprofile);
// modelAndView.addObject("sessionIndustryType", sessionIndustryType);
// modelAndView.addObject("userRole", userRole);
// modelAndView.addObject("industryCat", industryCat);
//
// // new session code By Jemish
// int uId = empDataSession.getUsers().getUsersId();
// modelAndView.addObject("uId", uId);
// modelAndView.addObject("userRole", userRole);
// modelAndView.addObject("userProfilePic", proPic);
// modelAndView.addObject("companyName", companyData.getCompName());
//
// // ALL Units list as select options in ESR
// List<Unit> unit = unitServices.findAll();
// modelAndView.addObject("unit", unit);
//
// // FOR TODO WHICH ARE EXPIRED OR GOING TO BE EXPIRED !!
//
// int empId = empDataSession.getEmpDataId();
//
// String warning = null;
// List<Object[]> consLists = consentServices.findByConsCTO();
//
// for (Object[] consList : consLists)
// {
// consentNo = (String) consList[1];
// validUpto = (String) consList[2];
//
// String cDate = Utilities.getTodaysDate();
// int ab1 = 0;
// int r = (int) Utilities.daysBetweenWithString(cDate, validUpto); // sign (positive or negative)
// int dayDiff = Math.abs(r); // positive value (diff of dates in days)
//
// if (r >= 0)
// {
//
// if (dayDiff < 90)
// {
// /* FOR UPCOMING CONSENT */
// warning = "CONSENT NO. " + consentNo + " is ABOUT to EXPIRE in " + dayDiff + " days";
// ab1 = dayDiff + 1;
//
// String todoMsg = "CONSENT NO. " + consentNo + " is ABOUT to EXPIRE in " + ab1 + " days";
//
// // GET ALL TO DO TO BE EXPIRE
// List todoDatas = null;
//
// // CHECK IF TO DO ALREADY EXISTS
// todoDatas = todoServices.findOneByGetAllToDoMsg(consentNo);
//
// if (todoDatas.size() > 0)
// {
// for (int i = 0; i < todoDatas.size(); i++)
// {
// todo_id = (int) todoDatas.get(i);
// flag = false;
// }
//
// }
// else
// {
// flag = true;
// }
//
// if (flag == true)
// {
// EmpData empData = new EmpData();
// empData.setEmpDataId(empId);
//
// Todo todo = new Todo();
// todo.setTodo(warning);
// todo.setCreateDate(cDate);
// todo.setEmpData(empData);
// todo.setAction("new");
//
// // Save new ToDo with about expire message
// todoServices.save(todo);
// }
// else
// {
//
// // Update new ToDo with expire message EveryDay
// todoServices.updateToDoMsg(warning, cDate, todo_id);
// }
// }
// }
// else
// {
// warning = "CONSENT NO. " + consentNo + " had EXPIRED " + dayDiff + " days before.";
// ab1 = dayDiff - 1;
//
// String todoMsg = "CONSENT NO. " + consentNo + " had EXPIRED " + ab1 + " days before.";
//
// List todoDatas = null;
//
// // CHECK IF TO DO ALREADY EXISTS
// todoDatas = todoServices.findOneByGetAllToDoMsg(consentNo);
//
// if (todoDatas.size() > 0)
// {
// for (int i = 0; i < todoDatas.size(); i++)
// {
// todo_id = (int) todoDatas.get(i);
// flag = true;
// }
//
// }
// else
// {
// flag = false;
// }
// if (flag == false)
// {
//
// EmpData empData = new EmpData();
// empData.setEmpDataId(empId);
//
// Todo todo = new Todo();
// todo.setTodo(warning);
// todo.setCreateDate(cDate);
// todo.setEmpData(empData);
// todo.setAction("new");
//
// // Save new ToDo with HAD EXPIRED message
// todoServices.save(todo);
// }
// else
// {
//
// // Update new ToDo with expire message EveryDay
// todoServices.updateToDoMsg(warning, cDate, todo_id);
// }
// }
// }
// int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
// yearFrom = Utilities.getCurrentStartYearFromCurrentMonth(currentMonth);
// yearTo = yearFrom + 1;
// String forYear = yearFrom + "-" + yearTo;
// String forNextYear = yearTo + "-" + (yearTo + 1);
//
// esrHwSolidHazardousWasteList = esrHwSolidWasteServices.findOneByGetHazardousWasteByYear(forYear,
// "Hazardous Waste");
// esrHwSolidWasteList = esrHwSolidWasteServices.findOneByGetHazardousWasteByYear(forYear, "Solid Waste");
// esrPollutionList = esrPollutionControlServices.findOneByEsrPollutionByYear(forYear);
// esrInvestmentList = esrInvestmentServices.findOneByGetEsrInvestmentByYear(forYear);
// esrNextYearInvestmentList = esrInvestmentServices.findOneByGetEsrInvestmentByNextYear(forYear, forNextYear);
// esrParticularsList = esrParticularsServices.findOneByGetParticularsByYear(forYear);
//
// modelAndView.addObject("HazardousWasteList", esrHwSolidHazardousWasteList);
// modelAndView.addObject("solidWasteList", esrHwSolidWasteList);
// modelAndView.addObject("PollutionList", esrPollutionList);
// modelAndView.addObject("esrInvestmentList", esrInvestmentList);
// modelAndView.addObject("esrNextYearInvestmentList", esrNextYearInvestmentList);
// modelAndView.addObject("esrParticularsList", esrParticularsList);
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
// return modelAndView;
// }
//
// /**
// * This method used to open fill hazardous waste manifest from.
// *
// * @return HWManifestForm
// */
// @RequestMapping("HWManifest-form")
// public ModelAndView getHWManifestForm()
// {
// ModelAndView modelAndView = new ModelAndView();
// try
// {
// List<Integer> productIdFromRegularData = new ArrayList<>();
// List<String> productByRegularDataId = new ArrayList<>();
// List<String> productByRegularDataIdList = new ArrayList<>();
// productIdFromRegularData = regularDataServices.getProductNameIdFromRegularData();
// int productId = 0;
// String wasteProductName = "";
// for (int i = 0; i < productIdFromRegularData.size(); i++)
// {
// productId = productIdFromRegularData.get(i);
// productByRegularDataId = allProductNameServices.getProductNameFromRegularData(productId);
// for (int j = 0; j < productByRegularDataId.size(); j++)
// {
// wasteProductName = productByRegularDataId.get(j);
// }
// if (!productByRegularDataIdList.containsAll(productByRegularDataId))
// {
// productByRegularDataIdList.addAll(productByRegularDataId);
// }
// }
//
// modelAndView.addObject("allProductName", productByRegularDataIdList);
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
//
// modelAndView.setViewName("EnvrOfficer/HWManifestForm");
// return modelAndView;
// }
//
// /**
// * This method used to View modified data
// *
// * @return modifiedDataList
// */
// @RequestMapping("view-modified-data")
// public ModelAndView getViewModifiedData()
// {
// ModelAndView model = new ModelAndView("EnvrOfficer/ViewModifiedData");
// String requestFor = null;
// String requestForDate = null;
// List<Object[]> modifiedDataList = null;
// List<Object[]> modifiedList = null;
// ArrayList<Object[]> modifiedArrayList = new ArrayList<>();
// ViewRegularDataController vrdc = new ViewRegularDataController();
// try
// {
// modifiedDataList = viewModifiedRecordServices.getModifiedRecordAndEmpName();
// if (!modifiedDataList.isEmpty())
// {
// for (int i = 0; i < modifiedDataList.size(); i++)
// {
// requestFor = (String) modifiedDataList.get(i)[0];
// requestForDate = (String) modifiedDataList.get(i)[1];
// modifiedList = regularDataServices.findByQuantityDateUnit(requestFor, requestForDate);//
// if (!modifiedList.isEmpty())
// {
// for (int j = 0; j < modifiedList.size(); j++)
// {
// modifiedArrayList.addAll(modifiedList);
// List<String> unitmList = new ArrayList<>();
// if (!modifiedArrayList.isEmpty())
// {
// for (Object[] modifiedunit : modifiedArrayList)
// {
// String unit = (String) modifiedunit[2];
// String unitm = vrdc.changeUnits(unit);
// if (unitm.equalsIgnoreCase("NA"))
// {
// unitm = unit;
// }
// unitmList.add(unitm);
// }
// }
// model.addObject("unitRegularModified", unitmList.get(j));
// }
// }
// }
// }
// model.addObject("modifiedDataList", modifiedDataList);
//
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
// return model;
// }
//
// /**
// * This method used to save the hazardous manifest from.
// *
// * @param hs hazardous data of string type.
// * @param hsi hazardous data of integer type.
// * @param consistency return consistency.
// * @param containersNumber return container number.
// * @param containersType return container type.
// * @param wasteQuantityUnits return unit of waste quantity.
// * @param wasteQuantity return the waste quantity.
// * @param wasteName return the waste name.
// * @param wasteDescription return the waste description.
// * @return add HW manifest or not
// */
// @RequestMapping(value = "/ajax-add-hw-manifest", method = RequestMethod.POST)
// public @ResponseBody String addHazardousManifesttt(@RequestParam(value = "sendersName", required = false) String sendersName,
// @RequestParam(value = "sendersMailingAddress", required = false) String sendersMailingAddress,
// @RequestParam(value = "sendersPhoneNo", required = false) String sendersPhoneNo,
// @RequestParam(value = "sendersAuthorizationNo", required = false) String sendersAuthorizationNo,
// @RequestParam(value = "manifestDocumentsNo", required = false) String manifestDocumentsNo,
// @RequestParam(value = "transporterName", required = false) String transporterName,
// @RequestParam(value = "transporterAddress", required = false) String transporterAddress,
// @RequestParam(value = "transportermobilepNo", required = false) String transportermobilepNo,
// @RequestParam(value = "transporterRegistrationNo", required = false) String transporterRegistrationNo,
// @RequestParam(value = "transporterVehicleRegistrationNo", required = false) String transporterVehicleRegistrationNo,
// @RequestParam(value = "vehicle_type", required = false) String vehicle_type,
// @RequestParam(value = "receiversName", required = false) String receiversName,
// @RequestParam(value = "receiversAddress", required = false) String receiversAddress,
// @RequestParam(value = "receiversAuthorizationNo", required = false) String receiversAuthorizationNo,
// @RequestParam(value = "receiversPhoneNo", required = false) String receiversPhoneNo,
// @RequestParam(value = "transport_desc_waste", required = false) String transport_desc_waste,
// @RequestParam(value = "totQuantityContainer", required = false) String totQuantityContainer,
// @RequestParam(value = "totQuantityContainerUnit", required = false) String totQuantityContainerUnit,
// @RequestParam(value = "specialHandling", required = false) String specialHandling,
// @RequestParam(value = "subdate", required = false) String subdate,
// @RequestParam(value = "containersNumber", required = false) String[] containersNumber,
// @RequestParam(value = "containersType", required = false) String[] containersType,
// @RequestParam(value = "wasteName", required = false) String[] wasteName,
// @RequestParam(value = "wasteQuantity", required = false) float[] wasteQuantity,
// @RequestParam(value = "wasteQuantityUnits", required = false) String[] wasteQuantityUnits,
// @RequestParam(value = "consistency", required = false) String[] consistency)
// {
// int hmId = 0;
// String status = "";
// /*
// * sendersName = hs[0];
// * sendersmailingAddress = hs[1];
// * sendersPhoneNo = hsi[0];
// * sendersAuthorizationNo = hs[2];
// * manifestDocumentsNo = hs[3];
// * transporterName = hs[4];
// * transporterAddress = hs[5];
// * transportermobilepNo = hsi[1];
// * vehicle_type = hs[6];
// * transporterRegistrationNo = hs[7];
// * transporterVehicleRegistrationNo = hs[8];
// * receiversName = hs[9];
// * receiversAddress = hs[10];
// * receiversPhoneno = hsi[2];
// * receiversAuthorizationNo = hs[11];
// * transport_desc_waste = hs[12];
// * totQuantityContainer = hs[13];
// * totContainerUnit=hs[14];
// * specialHandling = hs[15];
// * subdate = hs[15];
// */
// try
// {
// Containers c = new Containers();
// HazardousManifest hazardousManifest = new HazardousManifest();
// hazardousManifest.setSendersName(sendersName);
// hazardousManifest.setSendersMailingAddress(sendersMailingAddress);
// hazardousManifest.setSendersPhoneNo(sendersPhoneNo);
// hazardousManifest.setSendersAuthorizationNo(sendersAuthorizationNo);
// hazardousManifest.setManifestDocumentsNo(manifestDocumentsNo);
// hazardousManifest.setTransporterName(transporterName);
// hazardousManifest.setTransporterAddress(transporterAddress);
// hazardousManifest.setTransportermobilepNo(transportermobilepNo);
// hazardousManifest.setVehicleType(vehicle_type);
// hazardousManifest.setTransporterRegNo(transporterRegistrationNo);
// hazardousManifest.setTransporterVehicleRegNo(transporterVehicleRegistrationNo);
// hazardousManifest.setReceiversName(receiversName);
// hazardousManifest.setReceiversAddress(receiversAddress);
// hazardousManifest.setReceiversAuthorizationNo(receiversAuthorizationNo);
// hazardousManifest.setReceiversPhoneNo(receiversPhoneNo);
// hazardousManifest.setTransportDescWaste(transport_desc_waste);
// hazardousManifest.setTotQuantityContainer(totQuantityContainer);
// hazardousManifest.setTotQuantityContainerUnit(totQuantityContainerUnit);
// hazardousManifest.setSpecialHandling(specialHandling);
// hazardousManifest.setSubmittedDate(subdate);
// HazardousManifest hazardousManifest1 = hazardousManifestServices.save(hazardousManifest);
//
// if (hazardousManifest1 != null)
// {
// hmId = hazardousManifestServices.HWManifestId(sendersName, sendersMailingAddress, sendersPhoneNo, sendersAuthorizationNo,
// manifestDocumentsNo, transporterName, transporterAddress, transportermobilepNo, vehicle_type,
// transporterRegistrationNo, transporterVehicleRegistrationNo, receiversName, receiversAddress,
// receiversAuthorizationNo, receiversPhoneNo, transport_desc_waste, specialHandling);
//
// for (int i = 0; i < wasteName.length; i++)
// {
// WasteDescriptionConsistency wasteDescriptionConsistency = new WasteDescriptionConsistency();
// wasteDescriptionConsistency.setHazardousManifest(hazardousManifest1);
// wasteDescriptionConsistency.setConsistency(consistency[i]);
// wasteDescriptionConsistency.setWasteName(wasteName[i]);
// wasteDescriptionConsistency.setWasteQuantity(wasteQuantity[i]);
// wasteDescriptionConsistency.setWasteUnits(wasteQuantityUnits[i]);
// wasteDescriptionConsistencyServices.save(wasteDescriptionConsistency);
// }
//
// for (int i = 0; i < containersNumber.length; i++)
// {
// Containers containers = new Containers();
// containers.setHazardousManifest(hazardousManifest1);
// containers.setContainersNumber(containersNumber[i]);
// containers.setContainersType(containersType[i]);
// c = containersServices.save(containers);
// }
// }
// status = Constant.SUCCESS;
// }
// catch (
//
// Exception e)
// {
// LOGGER.error(e);
// status = Constant.FAILURE;
// }
// return status;
// }
//
// /**
// * This method used to display few manifest from.
// *
// * @param request the servlet request we are processing.
// * @return ViewManifest page
// */
// @RequestMapping("/view-manifest")
// public ModelAndView getViewManifest(HttpServletRequest request)
// {
// ModelAndView modelAndView = new ModelAndView();
// try
// {
// List<String> year = new ArrayList<String>();
// List<Integer> arrayYear = new ArrayList<Integer>();
// arrayYear = hazardousManifestServices.getHazardousManifestYear();
//
// modelAndView.addObject("arrayYear", arrayYear);
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
// modelAndView.setViewName("EnvrOfficer/ViewManifest");
// return modelAndView;
// }
//
// /**
// * This method used to get comparative main data
// *
// * @return ComparativeMain
// */
// @RequestMapping("comparative-main")
// public ModelAndView getComparativeMain()
// {
// return new ModelAndView("EnvrOfficer/ComparativeMain");
// }
//
// /**
// * This method used to get ESR form
// *
// * @return ESRform
// */
// @RequestMapping("envr-officer-esr-form")
// public ModelAndView getESRForm()
// {
// return new ModelAndView("EnvrOfficer/ESRform");
// }
//
// /**
// * This method used to get water cess form
// *
// * @return WaterCessForm
// */
// @RequestMapping("envr-officer-water-cess-form")
// public ModelAndView getWaterCessForm()
// {
// return new ModelAndView("EnvrOfficer/WaterCessForm");
// }
//
// /**
// * This method used to get water budget
// *
// * @return WaterBudget
// */
// @RequestMapping("water-budget")
// public ModelAndView getWaterBudget()
// {
// return new ModelAndView("EnvrOfficer/WaterBudget");
// }
//
// /**
// * This method used to display hazardous return from.
// *
// * @param encodedYear the return encoded year
// * @param request the servlet request we are processing.
// * @return getHazardousReturn
// * @throws ParseException if parsing causes an error
// */
// @RequestMapping("envr-officer-hazardous-return")
// public ModelAndView getHazardousReturn(@RequestParam(value = "year", required = false) String encodedYear,
// HttpServletRequest request) throws ParseException
// {
// ModelAndView modelAndView = new ModelAndView();
// try
// {
// String issueDate = "", consNo = "";
// EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
// int usersId = empDataSession.getUsers().getUsersId();
// EmpData userlogindata = empDataServices.getUserProfileData(usersId);
// modelAndView.setViewName("EnvrOfficer/HazardousReturn");
// String today = Utilities.getTodaysDate();
// String year = Utilities.decodeString(encodedYear);
// String dateRes[] = year.split("-");
// String fromDate = (dateRes[0]) + "-04-01";
// String toDate = (dateRes[1]) + "-03-31";
// List<Consent> getissueDateAndconsentNumber = new ArrayList<>();
// List<String> issueDateList = new ArrayList<String>();
// List<String> consentNumberList = new ArrayList<String>();
// List<CompanyProfile> companyData = empDataServices.getCompanydata();
// getissueDateAndconsentNumber = consentServices.getIssueDateAndConsNoByIssueDate(fromDate, toDate, today);
// for (int i = 0; i < getissueDateAndconsentNumber.size(); i++)
// {
// issueDate = getissueDateAndconsentNumber.get(i).getIssueDate();
// consNo = getissueDateAndconsentNumber.get(i).getConsNo();
// if (!issueDateList.contains(issueDate))
// {
// issueDateList.add(issueDate);
// }
// if (!consentNumberList.contains(consNo))
// {
// consentNumberList.add(consNo);
// }
// }
// modelAndView.addObject("userlogindata", userlogindata);
// modelAndView.addObject("companyData", companyData);
// modelAndView.addObject("issueDateList", issueDateList);
// modelAndView.addObject("consentNumberList", consentNumberList);
// modelAndView.addObject("today", today);
// modelAndView.addObject("selectedYear", year);
//
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
// return modelAndView;
// }
//
// /**
// * This method is used to return hazardous year for making pop-up of hazardous
// * return.
// *
// * @return jsonString it returns String value of json array.
// * @throws JSONException indicates that some exception happened during JSON
// * processing.
// */
// @RequestMapping(value = "/ajax-getHazardousValues")
// @ResponseBody
// public String getHazardousValues() throws JSONException
// {
// JSONArray jsonArray = new JSONArray();
// String todayDate = Utilities.getTodaysDate();
// String today_date[] = todayDate.split("-");
// String jsonString = null;
// int esrMinYear = 0, esrMaxYear = Integer.parseInt(today_date[0]);
// try
// {
// esrMinYear = consentServices.consentMinYear();
// JSONObject jsonobject = new JSONObject();
// jsonobject.put("esrMinYear", new Integer(esrMinYear));
// jsonobject.put("esrMaxYear", new Integer(esrMaxYear));
// jsonArray.put(jsonobject);
// jsonString = jsonArray.toString();
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
// return jsonString;
// }
//
// /**
// * This method used to make yearly ESR pop-up.
// *
// * @return jsonString it returns String value of json array.
// * @throws JSONException indicates that some exception happened during JSON
// * processing.
// */
// @RequestMapping(value = "/ajax-getYearlyEsrValues")
// @ResponseBody
// public String getYearlyEsrValues() throws JSONException
// {
// String jsonString = null, todayDate = Utilities.getTodaysDate(), today_date[] = todayDate.split("-");
// int esrMaxYear = Integer.parseInt(today_date[0]) + 1, esrMinYear = 0;
// JSONArray jsonArray = new JSONArray();
// try
// {
// esrMinYear = consentServices.consentMinYearForEsr();
// int maxYearDiff = esrMaxYear - esrMinYear;
// for (int i = 0; i <= maxYearDiff; i++)
// {
// String yearPair = (esrMaxYear - 1) + "-" + esrMaxYear;
// HashMap<String, String> hashMap = new HashMap<String, String>();
// hashMap.put("esrYear", new String(yearPair));
// jsonArray.put(hashMap);
// esrMaxYear = esrMaxYear - 1;
// }
// jsonString = jsonArray.toString();
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
// return jsonString;
// }
//
// /**
// * This method used to open profile page
// *
// * @return ProfileAbout
// */
// @RequestMapping("profile-about")
// public ModelAndView getProfileAbout()
// {
// return new ModelAndView("EnvrOfficer/ProfileAbout");
// }
//
// /**
// * This method used to environmental officer dashboard settings
// *
// * @return Setting page
// */
// @RequestMapping("envr-officer-settings")
// public ModelAndView getSettings()
// {
// return new ModelAndView("EnvrOfficer/Setting");
// }
//
// /**
// * This method used to get the statistics data.
// *
// * @param name the name of the statistics .
// * @param request the servlet request we are processing.
// * @return Statitics data
// */
// @RequestMapping("statitics")
// public ModelAndView getStatitics(String name, HttpServletRequest request)
// {
// ModelAndView modelAndView = new ModelAndView();
// modelAndView.setViewName("EnvrOfficer/Statitics");
// modelAndView.addObject("name", name);
// return modelAndView;
// }
//
// /**
// * This method used to Environment officer get the data of water consumption
// * data.
// *
// * @return WaterConsumption
// */
// @RequestMapping("envr-officer-water-consumption")
// public ModelAndView getWaterConsumption()
// {
// return new ModelAndView("EnvrOfficer/WaterConsumption");
// }
//
// /**
// * This method used to display the water performance.
// *
// * @return getWaterPerformance
// */
// @RequestMapping("water-performance")
// public ModelAndView getWaterPerformance()
// {
// ModelAndView modelAndView = new ModelAndView();
// try
// {
// modelAndView.setViewName("EnvrOfficer/WaterPerformance");
// List<Integer> regDatesList = new ArrayList<>();
// List<Integer> SewEffPollYearList = new ArrayList<>();
// List<Integer> regSourceYearList = new ArrayList<>();
//
// int regEffminYear = 0, regSewminYear = 0, finalMinYear = 0, minRegSourceYear = 0;
// String dateToSend[] = Utilities.getTodaysDate().split("-");
// int currentYear = Integer.parseInt(dateToSend[0]);
//
// try
// {
// //Effected By Water Inventory ........by sushama
// //minRegSourceYear = regularSourceDataServices.minRegSourceYear();
// }
// catch (Exception e2)
// {
// e2.printStackTrace();
// }
//
// if (minRegSourceYear == 0)
// {
//
// }
// else
// {
//
// int maxYearDiff = currentYear - minRegSourceYear;
// maxYearDiff = maxYearDiff + 1;
// for (int i = 1; i <= maxYearDiff; i++)
// {
// regSourceYearList.add(currentYear);
// currentYear = currentYear - 1;
// }
// }
//
// try
// {
// regDatesList = regEffPollServices.getYearFromEffluent();
// }
// catch (Exception e1)
// {
// e1.printStackTrace();
// }
// if (!Validator.isEmpty(regDatesList))
// regEffminYear = Collections.min(regDatesList);
//
// try
// {
// regDatesList = regSewPollServices.getYearFromSewage();
// }
// catch (Exception e1)
// {
// e1.printStackTrace();
// }
//
// if (!Validator.isEmpty(regDatesList))
// regSewminYear = Collections.min(regDatesList);
//
// if (regEffminYear > regSewminYear)
// {
// finalMinYear = regEffminYear;
// }
// else if (regSewminYear > regEffminYear)
// {
// finalMinYear = regSewminYear;
// }
// else
// {
// finalMinYear = 0;
// }
//
// if (finalMinYear == 0)
// {
//
// }
// else
// {
// int currentYear1 = Integer.parseInt(dateToSend[0]);
// // SewEffPollYearList.add(currentYear1);
// int maxYearDiff = currentYear1 - finalMinYear;
// maxYearDiff = maxYearDiff + 1;
// for (int i = 1; i <= maxYearDiff; i++)
// {
// SewEffPollYearList.add(currentYear1);
// currentYear1 = currentYear1 - 1;
// }
// }
// int isETP = 0, isSTP = 0, isBoth = 0, flagWater = 0;
// // Check ETP availability
// try
// {
// isETP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("ETP");
// if (isETP > 0)
// isETP = 1;
// flagWater = 1;
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
//
// // Check STP availability
// try
// {
// isSTP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("STP");
// if (isSTP > 0)
// isSTP = 1;
// flagWater = 1;
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
//
// if (isETP == 1 && isSTP == 1)
// isBoth = 1;
// flagWater = 1;
//
// modelAndView.addObject("isETP", isETP);
// modelAndView.addObject("isSTP", isSTP);
// modelAndView.addObject("isBoth", isBoth);
// modelAndView.addObject("flagWater", flagWater);
// modelAndView.addObject("SewEffPollYearList", SewEffPollYearList);
// modelAndView.addObject("regSourceYearList", regSourceYearList);
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
// return modelAndView;
// }
//
// /**
// * This method used to get reminder Status for consent ID expire to
// * environmental officer.
// *
// * @param action it return get Consent Reminder status.
// * @param consentIdForEx the consent ID for expire date
// * @param validUptoForEx the extended Consent Valid Upto to the expire date
// * @return jsonString it returns String value of json array.
// * @throws ParseException if parsing causes an error
// * @throws JSONException indicates that some exception happened during JSON
// * processing.
// */
// @RequestMapping("/ajax-GetReminderStatus")
// public @ResponseBody String getReminderStatus(@RequestParam(value = "action", required = false) String action,
// @RequestParam(value = "consentIdForEx", required = false) String consentIdForEx,
// @RequestParam(value = "validUptoForEx", required = false) String validUptoForEx)
// throws ParseException, JSONException
// {
// String jsonString = null;
// JSONArray jsonArray = new JSONArray();
//
// try
// {
// if (action.equals("getConsentReminder"))
// {
// boolean flag = false;
// Integer consId = 0;
// String consentNo = null;
// String validUpto = null;
//
// String resMessage = null; // response of ajax
//
// String cDate = Utilities.getTodaysDate();
//
// List<Object[]> consLists = new ArrayList<>();
//
// try
// {
// consLists = consentServices.findByConsCTO();
// }
// catch (Exception e1)
// {
// e1.printStackTrace();
// }
// if (!Validator.isEmpty(consLists))
// {
//
// for (Object[] consList : consLists)
// {
//
// flag = false;
// consId = ((Integer) consList[0]).intValue();
// consentNo = (String) consList[1];
// validUpto = (String) consList[2];
//
// JSONObject jsonObject = new JSONObject();
//
// int diffSymbolVal = (int) Utilities.daysBetweenWithString(cDate, validUpto); // sign (positive
// // or negative)
// int dayDiff = Math.abs(diffSymbolVal); // positive value (diff of dates in days)
//
// if (diffSymbolVal >= 0 && dayDiff < 30)
// {
// resMessage = consentNo + ":ABOUT TO EXPIRE:" + consId;
// jsonObject.put("msg", resMessage);
// }
// else if (diffSymbolVal < 0)
// {
// List extendedConsValidUptoLists = new ArrayList<>();
// try
// {
// extendedConsValidUptoLists = consentExtendedDateServices
// .findByConsValidUpto(new PageRequest(0, 1));
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
// if (extendedConsValidUptoLists.size() > 0)
// {
//
// for (int i = 0; i < extendedConsValidUptoLists.size(); i++)
// {
//
// String exValidUpto = (String) extendedConsValidUptoLists.get(i);
// int dateDiff = (int) Utilities.daysBetweenWithString(cDate, exValidUpto);
//
// if (dateDiff >= 0)
// {
// resMessage = consentNo + ":EXPIRED:" + exValidUpto + ":Yes";
// jsonObject.put("msg", resMessage);
// flag = true;
// }
// else if (!flag)
// {
// resMessage = consentNo + ":EXPIRED:" + validUpto + ":No";
// jsonObject.put("msg", resMessage);
// flag = true;
// }
// }
// }
// else if (!flag)
// {
// resMessage = consentNo + ":EXPIRED:" + validUpto + ":No";
// jsonObject.put("msg", resMessage);
// flag = true;
// }
// }
// else if (!flag)
// {
// resMessage = "NO ACTION";
// jsonObject.put("msg", resMessage);
// }
//
// jsonArray.put(jsonObject); // JSON Object Added to JsonArray
// }
//
// }
// else
// {
// JSONObject jsonObject = new JSONObject();
//
// resMessage = "NO ACTION";
// jsonObject.put("msg", resMessage);
//
// jsonArray.put(jsonObject); // JSON Object Added to JsonArray
// }
//
// }
// else if (action.equals("getConsents"))
// {
//
// boolean flag = false;
// String extended = "No";
// String todayDate = Utilities.getTodaysDate();
//
// Integer consId = 0;
// String consentNo = null;
// String validUpto = null;
//
// List<Object[]> consentNotExpireLists = new ArrayList<>();
//
// try
// {
// consentNotExpireLists = consentServices.findByConsByValidUpto(todayDate);
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
//
// if (!consentNotExpireLists.isEmpty())
// {
// for (Object[] consentNotExpireList : consentNotExpireLists)
// {
//
// consId = ((Integer) consentNotExpireList[0]).intValue();
// consentNo = (String) consentNotExpireList[1];
// validUpto = (String) consentNotExpireList[2];
//
// List<String> consExtValidUptoLists = new ArrayList<>();
//
// try
// {
// consExtValidUptoLists = consentExtendedDateServices.findByConsExtendedById(consId);
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
//
// if (!consExtValidUptoLists.isEmpty())
// {
// for (String consExtValidUptoList : consExtValidUptoLists)
// {
//
// validUpto = consExtValidUptoList;
// extended = "Yes";
//
// JSONObject jsonObject = new JSONObject();
//
// jsonObject.put("id", consId);
// jsonObject.put("consent_no", consentNo);
// jsonObject.put("valid_upto", validUpto);
// jsonObject.put("extended", extended);
//
// jsonArray.put(jsonObject);
// }
// }
// else
// {
//
// JSONObject jsonObject = new JSONObject();
//
// jsonObject.put("id", consId);
// jsonObject.put("consent_no", consentNo);
// jsonObject.put("valid_upto", validUpto);
// jsonObject.put("extended", extended);
// jsonArray.put(jsonObject);
// }
// }
// }
// else
// {
// JSONObject jsonObject = new JSONObject();
// jsonObject.put("id", "NoData");
// jsonObject.put("consent_no", "NoData");
// jsonObject.put("valid_upto", "NoData");
// jsonObject.put("extended", "NoData");
// jsonArray.put(jsonObject);
// }
//
// }
// else if (action.equals("extendConsentValidity"))
// {
//
// String todayDate = Utilities.getTodaysDate();
// int consId = Integer.parseInt(consentIdForEx);
// ConsentExtendedDate consentExtendedDate = new ConsentExtendedDate();
// Consent consent = new Consent();
// consent.setConsentId(consId);
// consentExtendedDate.setConsent(consent);
// consentExtendedDate.setInputDate(todayDate);
// consentExtendedDate.setValidUpto(validUptoForEx);
// try
// {
// consentExtendedDateServices.save(consentExtendedDate);
// consentServices.updateconsentextdate(validUptoForEx, consId);
// jsonString = "Success";
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }
// }
// }
// catch (Exception e)
// {
// LOGGER.error(e);
// }
//
// jsonString = jsonArray.toString();
// return jsonString;
// }
//
// }
