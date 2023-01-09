package com.tes.controller.environmentalofficer.consent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.Ambient;
import com.tes.model.AmbientPoll;
import com.tes.model.Consent;
import com.tes.model.Stack;
import com.tes.model.StackPoll;
import com.tes.model.Unit;
import com.tes.model.WaterSewPoll;
import com.tes.model.WateriePollutant;
import com.tes.services.environmentalofficer.AmbientPollServices;
import com.tes.services.environmentalofficer.AmbientServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.StackOpServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.services.environmentalofficer.WaterConGenServices;
import com.tes.services.environmentalofficer.WaterSewPollOpServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantOpServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.thirdparty.StackPollServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate used of the consent product operation.
 * This class perform the operation of add / view / display the consent product operation.
 * 
 * @author Vishal Gabani
 */
@RestController
@SessionAttributes("consentId")
@RequestMapping(value = {"/env"})
public class ConsentController extends Constant
{

	@Autowired
	StackServices stackServices;

	@Autowired
	StackOpServices stackOpServices;

	@Autowired
	StackPollServices stackPollServices;

	@Autowired
	AmbientServices ambientServices;

	@Autowired
	AmbientPollServices ambientPollServices;

	@Autowired
	WateriePollutantOpServices wateriePollutantOpServices;

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	WaterConGenServices waterConGenServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	UnitServices unitServices;

	@Autowired
	ConsentServices consentServices;

	@Autowired
	WaterSewPollOpServices waterSewPollOpServices;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to Display the product list of consent to operate (i.e.Stack, Ambient, Water).
	 * 
	 * @param request The servlet request we are processing.
	 * @return Product list of consent to Operate
	 */
	// Display on OlistProduct
	@RequestMapping(value = "olist-product") // Comparative Sheet
	public ModelAndView getOlistOfProduct(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			List<Stack> stackDetails = stackServices.findByConsType();
			List<Stack> stackDetailList = new ArrayList<>();
			for (int i = 0, size = stackDetails.size(); i < size; i++)
			{
				Stack objStackDetail = new Stack();
				objStackDetail = stackDetails.get(i);
				objStackDetail.setStackPollDetailList(stackPollServices.findByStackId(objStackDetail.getStackId()));
				stackDetailList.add(objStackDetail);
			}
			List<Ambient> ambientDetails = ambientServices.findByConsType();
			List<Ambient> ambientDetailList = new ArrayList<>();
			for (int i = 0; i < ambientDetails.size(); i++)
			{
				Ambient ambientDetail = new Ambient();
				ambientDetail = ambientDetails.get(i);
				ambientDetail.setAmbientPollDetailList(ambientPollServices.findByAmbientId(ambientDetail.getAmbientId()));
				ambientDetailList.add(ambientDetail);
			}
			// $id = $_SESSION['id'];-----> i dont know use of this DesignWaterPollutant.php page .....by vishal

			// DesignWaterConGen.jsp
			double domesticCon = 0, biodegradableCon = 0, coolingCon = 0, otherCon = 0, nBiodegradableCon = 0;
			double domesticGen = 0, biodegradableGen = 0, nBiodegradableGen = 0, coolingGen = 0, otherGen = 0;
			double totalCon = 0, totalGen = 0, totalSewCon = 0, totalSewGen = 0, totalEffCon = 0, totalEffGen = 0;

			boolean flagCon = false, flagGen = false;

			// String a[] = { "Domestic", "Biodegradable", "Non Biodegradable", "Cooling", "Others" };
			for (int i = 1; i <= 5; i++)
			{// this loop give error if there is no records is available in waterConGen(Consent to operate & Consent to Establish)
				float sumOfConsByCtO = waterConGenServices.sumOfConsByWaterConGenParameterIdAndCtO(Utilities.getTodaysDate(), i);// waterConGenServices.sumOfConsByCtoO(Utilities.getTodaysDate(), i);
				Float sumOfConsByCtoE = waterConGenServices.getConsByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(), i); // waterConGenServices.sumOfConsByCtoE(Utilities.getTodaysDate(), i);
				if (!Validator.isEmpty(sumOfConsByCtO))
				{
					// float sumOfConsByCtoE = waterConGenServices.getConsByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(),i);//if valid upto date in both table get less than error will raise ...by vishal waterConGenServices.sumOfConsByCtoE(Utilities.getTodaysDate(), i);
					if (!Validator.isEmpty(sumOfConsByCtoE))
					{
						switch (i)
						{
							case 1:
								domesticCon = sumOfConsByCtoE - sumOfConsByCtO;
								break;
							case 2:
								biodegradableCon = sumOfConsByCtoE - sumOfConsByCtO;
								break;
							case 3:
								nBiodegradableCon = sumOfConsByCtoE - sumOfConsByCtO;
								break;
							case 4:
								coolingCon = sumOfConsByCtoE - sumOfConsByCtO;
								break;
							case 5:
								otherCon = sumOfConsByCtoE - sumOfConsByCtO;
								break;
							default:
								System.out.println("Use Message Bundle ...by vishal");
						}
					}
				}
				else
				{
					flagCon = true;
				}
				if (flagCon == true)
				{
					// float sumOfConsByCtoE = waterConGenServices.getConsByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(),i); //waterConGenServices.sumOfConsByCtoE(Utilities.getTodaysDate(), i);
					if (!Validator.isEmpty(sumOfConsByCtoE))
					{
						switch (i)
						{
							case 1:
								domesticCon = sumOfConsByCtoE;
								break;
							case 2:
								biodegradableCon = sumOfConsByCtoE;
								break;
							case 3:
								nBiodegradableCon = sumOfConsByCtoE;
								break;
							case 4:
								coolingCon = sumOfConsByCtoE;
								break;
							case 5:
								otherCon = sumOfConsByCtoE;
								break;
							default:
								System.out.println("Use Message Bundle ...by vishal");
						}
					}
				}

				float sumOfGenByCtoO = waterConGenServices.sumOfGenByWaterConGenParameterIdAndCtO(Utilities.getTodaysDate(), i);
				Float sumOfGenByCtoE = waterConGenServices.getGenByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(), i);// waterConGenServices.sumOfGenByCtoE(Utilities.getTodaysDate(), i);
				if (!Validator.isEmpty(sumOfGenByCtoO))
				{
					// float sumOfGenByCtoE = waterConGenServices.getGenByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(),i);//waterConGenServices.sumOfGenByCtoE(Utilities.getTodaysDate(), i);
					switch (i)
					{
						case 1:
							domesticGen = sumOfGenByCtoE - sumOfGenByCtoO;
							break;
						case 2:
							biodegradableGen = sumOfGenByCtoE - sumOfGenByCtoO;
							break;
						case 3:
							nBiodegradableGen = sumOfGenByCtoE - sumOfGenByCtoO;
							break;
						case 4:
							coolingGen = sumOfGenByCtoE - sumOfGenByCtoO;
							break;
						case 5:
							otherGen = sumOfGenByCtoE - sumOfGenByCtoO;
							break;
						default:
							System.out.println("Use Message Bundle ...by vishal");
					}
				}
				else
				{
					flagGen = true;
				}
				if (flagGen == true)
				{
					// float sumOfGenByCtoE = waterConGenServices.getGenByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(),i);//waterConGenServices.sumOfGenByCtoE(Utilities.getTodaysDate(), i);
					if (!Validator.isEmpty(sumOfGenByCtoE))
					{
						switch (i)
						{
							case 1:
								domesticGen = sumOfGenByCtoE;
								break;
							case 2:
								biodegradableGen = sumOfGenByCtoE;
								break;
							case 3:
								nBiodegradableGen = sumOfGenByCtoE;
								break;
							case 4:
								coolingGen = sumOfGenByCtoE;
								break;
							case 5:
								otherGen = sumOfGenByCtoE;
								break;
							default:
								System.out.println("Use Message Bundle ...by vishal");
						}
					}
				}
			}

			totalSewCon = domesticCon;
			totalSewGen = domesticGen;
			totalEffCon = biodegradableCon + nBiodegradableCon + coolingCon;
			totalEffGen = biodegradableGen + nBiodegradableGen + coolingGen;
			totalCon = domesticCon + biodegradableCon + nBiodegradableCon + coolingCon + otherCon;
			totalGen = domesticGen + biodegradableGen + nBiodegradableGen + coolingGen + otherGen;
			// DesignWaterConGen.jsp End

			modelAndView.addObject("stackDetail", stackDetailList);
			modelAndView.addObject("ambientDetail", ambientDetailList);
			modelAndView.addObject("wateriePollutantDetailList", wateriePollutantServices.findbyConsType());
			modelAndView.addObject("waterSewPollDetailList", waterSewPollServices.findbyConsType());

			modelAndView.addObject("totalSewCon", totalSewCon);
			modelAndView.addObject("domesticCon", domesticCon);
			modelAndView.addObject("totalSewGen", totalSewGen);
			modelAndView.addObject("domesticGen", domesticGen);
			modelAndView.addObject("totalEffCon", totalEffCon);
			modelAndView.addObject("biodegradableCon", biodegradableCon);
			modelAndView.addObject("nBiodegradableCon", nBiodegradableCon);
			modelAndView.addObject("coolingCon", coolingCon);
			modelAndView.addObject("totalEffGen", totalEffGen);
			modelAndView.addObject("biodegradableGen", biodegradableGen);
			modelAndView.addObject("nBiodegradableGen", nBiodegradableGen);
			modelAndView.addObject("coolingGen", coolingGen);
			modelAndView.addObject("totalCon", totalCon);
			modelAndView.addObject("otherCon", otherCon);
			modelAndView.addObject("totalGen", totalGen);
			modelAndView.addObject("otherGen", otherGen);

			// String consentIdd = request.getParameter("consentId");

			List<Consent> consentData = consentServices.findLastAddedConsentByConsType("Consent to Operate", new PageRequest(0, 1));
			int consentId = 0;
			for (int i = 0; i < consentData.size(); i++)
			{
				consentId = consentData.get(i).getConsentId();
			}

			modelAndView.addObject("consentId", consentId);
			// modelAndView.addObject("consentId", consentIdd);// session
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		modelAndView.setViewName("EnvrOfficer/OListOfProduct");
		return modelAndView;
	}

	/**
	 * This method used to Delete the Pollutant data of consent to operate(Stack, Ambient, Water).
	 * 
	 * @param type the type of product
	 * @param request the servlet request we are processing.
	 * @return effluent and sewage if yes case matched and unsuccess if no case matched.
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	// deletePollutant.php
	@RequestMapping(value = "ajax-delete-pollutant", method = RequestMethod.GET)
	public @ResponseBody String deletePollutant(@RequestParam("type") String type, HttpServletRequest request) throws JSONException
	{
		// @RequestParam(value = "product_id",required = false)int productId,
		try
		{
			int productId = 0;
			String cType = null;
			if (!Validator.isEmpty(request.getParameter("product_id")))
				productId = Integer.parseInt(request.getParameter("product_id"));
			cType = request.getParameter("cons_type");
			if (type.equalsIgnoreCase("stackData"))
			{
				Stack stackId = new Stack();
				stackId.setStackId(productId);
				return (!Validator.isEmpty(stackPollServices.deleteByStack(stackId))) && (!Validator.isEmpty(stackServices.deleteByStackId(productId))) ? SUCCESS : UNSUCCESS;
			}
			else if (type.equalsIgnoreCase("ambientData"))
			{
				Ambient ambientId = new Ambient();
				ambientId.setAmbientId(productId);
				return (!Validator.isEmpty(ambientPollServices.deleteByAmbient(ambientId))) && (!Validator.isEmpty(ambientServices.deleteByAmbientId(productId))) ? SUCCESS : UNSUCCESS;
			}
			else if (type.equalsIgnoreCase("stack"))
				return !Validator.isEmpty(stackPollServices.deleteByStackPollId(productId)) ? SUCCESS : UNSUCCESS;// "DELETE FROM `stack_poll` WHERE id = ".$product_id;
			else if (type.equalsIgnoreCase("ambient"))
				return !Validator.isEmpty(ambientPollServices.deleteByAmbientPollId(productId)) ? SUCCESS : UNSUCCESS;
			else if (type.equalsIgnoreCase("loadPollutants"))
			{
				int stackOrAmbientId = Integer.parseInt(request.getParameter("stack_id"));
				String action = request.getParameter("action");
				JSONArray jsonArray = new JSONArray();
				if (action.equalsIgnoreCase("stack"))
				{
					for (StackPoll stackPoll : stackPollServices.findByStack(stackOrAmbientId))
					{// "select id, poll_name, poll_limit, poll_unit from stack_poll where stack_id = '$id'";
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("pollutantId", stackPoll.getStackPollId());
						jsonObject.put("pollutantName", stackPoll.getPollName());
						jsonObject.put("pollLimit", stackPoll.getPollLimit());
						jsonObject.put("pollUnit", stackPoll.getUnit().getUnits());
						jsonArray.put(jsonObject);
					}
					return jsonArray.toString();
				}
				else if (action.equalsIgnoreCase("ambient"))
				{
					for (AmbientPoll ambientPoll : ambientPollServices.findByAmbientId(stackOrAmbientId))
					{// List<AmbientPoll> ambientPollDetails = ;//"select id, poll_name, limits, units from ambient_poll where ambient_loc_id = '$id'";
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("pollutantId", ambientPoll.getAmbientPollId());
						jsonObject.put("pollutantName", ambientPoll.getPollName());
						jsonObject.put("pollLimit", ambientPoll.getLimits());
						jsonObject.put("pollUnit", ambientPoll.getUnit().getUnits());
						jsonArray.put(jsonObject);
					}
					return jsonArray.toString();
				}
			}
			else if (type.equalsIgnoreCase("effluent"))
			{

				if (cType.equalsIgnoreCase("establish"))
				{
					return !Validator.isEmpty(wateriePollutantServices.deleteByWateriePollutantId(productId)) ? SUCCESS : UNSUCCESS;
				}
				else if (cType.equalsIgnoreCase("operate"))
				{
					return !Validator.isEmpty(wateriePollutantOpServices.deleteWateriePollutantOp(productId)) ? SUCCESS : UNSUCCESS;
				}
			}
			else if (type.equalsIgnoreCase("sewage"))
			{

				if (cType.equalsIgnoreCase("establish"))
				{
					return !Validator.isEmpty(waterSewPollServices.deleteByWaterSewPollId(productId)) ? SUCCESS : UNSUCCESS;
				}
				else if (cType.equalsIgnoreCase("operate"))
				{
					return !Validator.isEmpty(waterSewPollOpServices.deleteByWaterSewPollIdOp(productId)) ? SUCCESS : UNSUCCESS;
				}
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return "hello";// if no case matched
	}

	/**
	 * This method used to add the pollutant data of the consent to operate(Stack, Ambient, Water).
	 * 
	 * @param type the type of product
	 * @param consentId the id of Consent
	 * @param pollName the Name of Pollutant
	 * @param limit the limit of product
	 * @param units the unit of product
	 * @param request the servlet request we are processing.
	 * @return isInserted it return status yes/no.
	 */
	// addPollutant.php
	@RequestMapping("ajax-add-pollutant")
	public @ResponseBody int addPollutant(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "cons_no", required = false) int consentId,
			@RequestParam(value = "poll_name", required = false) String pollName, @RequestParam(value = "limit", required = false) float limit,
			@RequestParam(value = "units", required = false) int units, HttpServletRequest request)
	{
		int isInserted = 0, pollId = 0;
		try
		{
			Unit objUnit = new Unit();
			objUnit.setUnitId(units);
			Consent objConsent = new Consent();
			objConsent.setConsentId(consentId);
			if (type.equalsIgnoreCase("stack"))
			{
				StackPoll objStackPoll = new StackPoll();
				Stack objStack = new Stack();
				objStack.setStackId(consentId);// consentId = Stackid
				objStackPoll.setStack(objStack);
				objStackPoll.setPollName(pollName);
				objStackPoll.setPollLimit(limit);
				objStackPoll.setUnit(objUnit);
				if (!Validator.isEmpty(stackPollServices.save(objStackPoll)))
					isInserted = 1;
			}
			else if (type.equalsIgnoreCase("ambient"))
			{
				AmbientPoll objAmbientPoll = new AmbientPoll();
				Ambient objAmbient = new Ambient();
				objAmbient.setAmbientId(consentId);// consentId = AmbientId
				objAmbientPoll.setAmbient(objAmbient);
				objAmbientPoll.setPollName(pollName);
				objAmbientPoll.setLimits(limit);
				objAmbientPoll.setUnit(objUnit);
				if (!Validator.isEmpty(ambientPollServices.save(objAmbientPoll)))
					isInserted = 1;
			}
			else if (type.equalsIgnoreCase("effluent"))
			{
				WateriePollutant objWateriePollutant = new WateriePollutant();
				objWateriePollutant.setConsent(objConsent);
				objWateriePollutant.setPollName(pollName);
				objWateriePollutant.setQuantity(limit);
				objWateriePollutant.setUnit(objUnit);
				objWateriePollutant.setConsentToOperate(NO);
				if (!Validator.isEmpty(wateriePollutantServices.save(objWateriePollutant)))
					;
				isInserted = 1;
			}
			else if (type.equalsIgnoreCase("sewage"))
			{
				WaterSewPoll objWaterSewPoll = new WaterSewPoll();
				objWaterSewPoll.setConsent(objConsent);
				objWaterSewPoll.setPollName(pollName);
				objWaterSewPoll.setQuantity(limit);
				objWaterSewPoll.setUnit(objUnit);
				objWaterSewPoll.setConsentToOperate(NO);
				if (!Validator.isEmpty(waterSewPollServices.save(objWaterSewPoll)))
					;
				isInserted = 1;
			}
			else if (type.equalsIgnoreCase("modifyEffluent"))
			{
				pollId = Integer.parseInt(request.getParameter("poll_id"));
				WateriePollutant wateriePollutant = wateriePollutantServices.findByWateriePollutantId(pollId);
				wateriePollutant.setPollName(pollName);
				wateriePollutant.setQuantity(limit);
				wateriePollutant.setUnit(objUnit);
				if (!Validator.isEmpty(wateriePollutantServices.save(wateriePollutant)))
					isInserted = 1;
				// isInserted = "UPDATE waterie_pollutant SET quantity = '$limit' , unit = '$units' WHERE id = '$poll_id'";
			}
			else if (type.equalsIgnoreCase("modifySewage"))
			{
				pollId = Integer.parseInt(request.getParameter("poll_id"));
				WaterSewPoll waterSewPollDetails = waterSewPollServices.findByWaterSewPollId(pollId);
				waterSewPollDetails.setPollName(pollName);
				waterSewPollDetails.setQuantity(limit);
				waterSewPollDetails.setUnit(objUnit);
				if (!Validator.isEmpty(waterSewPollServices.save(waterSewPollDetails)))
					isInserted = 1;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return isInserted;
	}

	/**
	 * This method used to view and modify data of the consent to operate (i.e. Stack, Ambient).
	 * 
	 * @param type the type of product.
	 * @param request the servlet request we are processing.
	 * @return it return status success/unsuccess.
	 */
	// modifyStackAmbient.php
	@RequestMapping("ajax-modify-stack-ambient")
	public @ResponseBody String modifyStackAmbient(@RequestParam("type") String type, HttpServletRequest request)
	{
		Stack isStackInserted = null;
		Ambient isAmbientInserted = null;
		try
		{
			if (type.equalsIgnoreCase("stack"))
			{// help @RequestParam or request.getParameter("stackid") ...by vishal
				Stack objStack = new Stack();
				int stackId = Integer.parseInt(request.getParameter("stackid"));
				objStack = stackServices.findByStackId(stackId);
				objStack.setStackId(stackId);
				objStack.setStackName(request.getParameter("stack_name"));
				objStack.setAttachedTo(request.getParameter("attached_to"));
				objStack.setCapacity(Float.parseFloat(request.getParameter("capacity")));
				objStack.setCapacityUnits(request.getParameter("capacity_units"));
				objStack.setFuelType(request.getParameter("fuel_type"));
				objStack.setFuelQuant(Float.parseFloat(request.getParameter("fuel_quant")));
				objStack.setFuelUnits(request.getParameter("fuel_units"));
				objStack.setShape(request.getParameter("shape"));
				objStack.setHeight(Float.parseFloat(request.getParameter("height")));
				objStack.setHtUnits(request.getParameter("ht_units"));
				objStack.setDiam(Float.parseFloat(request.getParameter("diam")));
				objStack.setDiamUnits(request.getParameter("diam_units"));
				objStack.setMatCons(request.getParameter("mat_cons"));
				
				//changes by pallavi..
				objStack.setGasQuant(Float.parseFloat(request.getParameter("gas_quant")));
				objStack.setGasUnit(request.getParameter("gas_unit"));
				objStack.setGasTemp(Float.parseFloat(request.getParameter("gas_temp")));
				objStack.setGasUnit(request.getParameter("gas_temp_unit"));
				objStack.setExitGasVel(Float.parseFloat(request.getParameter("exit_gas_vel")));
				objStack.setExitGasUnit(request.getParameter("exit_gas_unit"));
				objStack.setPrecedingStack(request.getParameter("preceding_stack"));
				objStack.setPolluPresent(request.getParameter("pollu_present"));
				objStack.setECSProvided(request.getParameter("eCS_provided"));
				objStack.setGenCapacity(Float.parseFloat(request.getParameter("gen_capacity")));
				objStack.setGenCapUnit(request.getParameter("gen_Cap_unit"));
				
				isStackInserted = stackServices.save(objStack);
			}
			else if (type.equalsIgnoreCase("ambient"))
			{
				Ambient objAmbient = new Ambient();
				int ambientId = Integer.parseInt(request.getParameter("ambientid"));
				objAmbient = ambientServices.findByAmbientId(ambientId);
				objAmbient.setAmbientId(ambientId);
				objAmbient.setCriteria(request.getParameter("criteria"));
				objAmbient.setAmbientLocName(request.getParameter("loc_ident"));
				isAmbientInserted = ambientServices.save(objAmbient);
			}
			return !Validator.isEmpty(isAmbientInserted) || !Validator.isEmpty(isStackInserted) ? SUCCESS : UNSUCCESS;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			return UNSUCCESS;
		}
	}

	/**
	 * This method used to add new unit.
	 * 
	 * @param unitsName the unit name
	 * @return the unitId matching if it's not exist it create new record and return
	 *         same
	 */
	public int getOrSetUnitIdIfNotExitst(String unitsName)
	{
		Unit unitId = unitServices.findUnitIdByUnits(unitsName);
		if (Validator.isEmpty(unitId))
		{
			Unit objUnit = new Unit();
			objUnit.setUnits(unitsName);
			Unit unitDetailsList = unitServices.save(objUnit);
			return unitDetailsList.getUnitId();
		}
		return unitId.getUnitId();
	}

	/**
	 * This method used to save the data of consent to establish (i.e. Stack, Ambient, Water).
	 * 
	 * @param fileUpload the file upload of stack and ambient data.
	 * @param action of fileUploading
	 * @param fn the name of the file.
	 * @param consentId the id of the consent
	 * @param folderName the name of the folder
	 * @return msg and fileUploadingMsg
	 * @throws IOException if an input/output error occurs
	 */
	// SaveData.php
	@PostMapping(value = "ajax-save-data")
	public @ResponseBody String saveData(@RequestParam("file") MultipartFile[] fileUpload,
			@RequestParam("action") String action, @RequestParam("fn") String fn,
			@RequestParam("consent_no") int consentId, @RequestParam("fn") String folderName) throws IOException
	{
		int getUnitId;
		int airEnvironmentCounter = 0;
		int airEnvironmentParameterCounter = 0;
		String fileUploadingMsg = null;
		String msg = SUCCESS;
		try
		{
			if (action.equalsIgnoreCase("fileUploading"))
			{
				for (int i = 0; i < fileUpload.length; i++)
				{
					Files.write(Paths.get(stackAndAmbient_file_path + fileUpload[i].getOriginalFilename()), fileUpload[i].getBytes());
					fileUploadingMsg = Utilities.uploadFile(stackAndAmbient_file_path, fileUpload[i].getSize());
				}

				// Stack Or Ambient Variable
				InputStream airEnvironmentExcelFileToRead = new FileInputStream(
						stackAndAmbient_file_path + fileUpload[0].getOriginalFilename());
				XSSFWorkbook airEnvironmentWorkbook = new XSSFWorkbook(airEnvironmentExcelFileToRead);
				XSSFSheet airEnvironmentSheet = airEnvironmentWorkbook.getSheetAt(0);
				Iterator<Row> airEnvironmentIterator = airEnvironmentSheet.iterator();

				// Stack Or Ambient Parameter Variable
				InputStream airEnvironmentParameterExcelFileToRead = new FileInputStream(
						stackAndAmbient_file_path + fileUpload[1].getOriginalFilename());
				XSSFWorkbook airEnvironmentParameterWorkbook = new XSSFWorkbook(airEnvironmentParameterExcelFileToRead);
				XSSFSheet airEnvironmentParameterSheet = airEnvironmentParameterWorkbook.getSheetAt(0);
				Iterator<Row> airEnvironmentParameterIterator = null;

				switch (folderName.toLowerCase())
				{
					case "stack":
						while (airEnvironmentIterator.hasNext())
						{
							Row airEnvironmentRow = airEnvironmentIterator.next();
							if (airEnvironmentRow.getRowNum() != 0)
							{
								Stack objStack = new Stack();
								/*
								 * Error raise if 1.File data is not match with 2.worng data
								 * 3.NumberFormatException ....by vishal
								 */
								objStack.setStackDetails(airEnvironmentRow, consentId);
								stackServices.save(objStack);
								airEnvironmentParameterIterator = airEnvironmentParameterSheet.iterator();
								while (airEnvironmentParameterIterator.hasNext())
								{
									Row airEnvironmentParameterRow = airEnvironmentParameterIterator.next();
									if (airEnvironmentParameterRow.getRowNum() != 0
											&& airEnvironmentParameterRow.getCell(1).toString()
													.equalsIgnoreCase(objStack.getStackName())
											&& airEnvironmentParameterRow.getCell(2).toString()
													.equalsIgnoreCase(objStack.getAttachedTo()))
									{
										StackPoll objStackPoll = new StackPoll();
										getUnitId = getOrSetUnitIdIfNotExitst(
												airEnvironmentParameterRow.getCell(5).toString());
										objStackPoll.setStackPollDetails(airEnvironmentParameterRow, objStack.getStackId(),
												getUnitId);
										stackPollServices.save(objStackPoll);
										airEnvironmentParameterCounter++;
									}
								}
								airEnvironmentCounter++;
							}
						}
						break;
					case "ambient":
						while (airEnvironmentIterator.hasNext())
						{
							Row airEnvironmentRow = airEnvironmentIterator.next();
							if (airEnvironmentRow.getRowNum() != 0)
							{
								Ambient objAmbient = new Ambient();
								/*
								 * Error raise if 1.File data is not match with 2.worng data
								 * 3.NumberFormatException ....by vishal
								 */
								objAmbient.setAmbientDetails(airEnvironmentRow, consentId);
								ambientServices.save(objAmbient);
								airEnvironmentParameterIterator = airEnvironmentParameterSheet.iterator();
								while (airEnvironmentParameterIterator.hasNext())
								{
									Row airEnvironmentParameterRow = airEnvironmentParameterIterator.next();
									if (airEnvironmentParameterRow.getRowNum() != 0
											&& airEnvironmentParameterRow.getCell(1).toString()
													.equalsIgnoreCase(objAmbient.getAmbientLocName())
											&& airEnvironmentParameterRow.getCell(2).toString()
													.equalsIgnoreCase(objAmbient.getCriteria()))
									{
										AmbientPoll objAmbientPoll = new AmbientPoll();
										getUnitId = getOrSetUnitIdIfNotExitst(
												airEnvironmentParameterRow.getCell(5).toString());
										objAmbientPoll.setAmbientPollDetails(airEnvironmentParameterRow,
												objAmbient.getAmbientId(), getUnitId);
										ambientPollServices.save(objAmbientPoll);
										airEnvironmentParameterCounter++;
									}
								}
								airEnvironmentCounter++;
							}
						}
						break;
					default:
						System.out.println("Something went wrong");// need to give relevant message ......by vishal
						break;
				}

				// need to improve correct msg to the client side .......by vishal
				if (airEnvironmentCounter != 0 && airEnvironmentSheet.getLastRowNum() != 0)
				{
					if (airEnvironmentCounter == airEnvironmentSheet.getLastRowNum())
					{
						// stack is uploaded successfully
						fileUploadingMsg += "<br>Stack details saved successfully";
						msg = "success";
						if (airEnvironmentParameterCounter == airEnvironmentParameterSheet.getLastRowNum())
						{
							fileUploadingMsg += " and stack parameters saved successfully.<br>";
							msg = "success";
						}
						else
						{
							fileUploadingMsg += "<br>Incorrect Data in parameter file.";
							msg = "success";
						}
					}
					else
					{
						fileUploadingMsg += "<br>Incorrect Data in file.";
						msg = "success";
					}
				}
				else
				{
					fileUploadingMsg += "<br>No data Available";
				}
			}
			return msg + "-" + fileUploadingMsg;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
			return msg + "-" + fileUploadingMsg;
		}
	}
}
