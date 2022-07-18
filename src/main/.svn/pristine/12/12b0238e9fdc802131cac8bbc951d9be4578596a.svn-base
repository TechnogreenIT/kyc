package com.tes.controller.thirdparty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tes.controller.base.BaseThirdPartyController;
import com.tes.model.Ambient;
import com.tes.model.AmbientPoll;
import com.tes.model.AmbientPollData;
import com.tes.model.EmpData;
import com.tes.model.RegAmbientPoll;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.RegStPoll;
import com.tes.model.Stack;
import com.tes.model.StackPoll;
import com.tes.model.StackPollData;
import com.tes.model.WastewaterTreatment;
import com.tes.model.WaterSewPoll;
import com.tes.model.WaterSewPollOp;
import com.tes.model.WateriePollutant;
import com.tes.model.WateriePollutantOp;
import com.tes.repository.thirdparty.StackPollRepository;
import com.tes.services.environmentalofficer.AmbientPollServices;
import com.tes.services.environmentalofficer.AmbientServices;
import com.tes.services.environmentalofficer.WaterServices;
import com.tes.services.environmentalofficer.WaterSewPollOpServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantOpServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.thirdparty.AmbientPollDataServices;
import com.tes.services.thirdparty.RegAmbientPollServices;
import com.tes.services.thirdparty.RegStPollServices;
import com.tes.services.thirdparty.StackPollDataServices;
import com.tes.services.thirdparty.StackPollServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate used of the Third Party Monitoring the product Consent data
 * i.e.Stack, Ambient and Water(water Pollutant and sewage).
 * This class perform the operation of add / Saved the Monitoring data.
 * 
 * @author Tushar Chougule
 */
@RestController
public class MonitoringController extends BaseThirdPartyController
{

	@Autowired
	StackServices stackServices;

	@Autowired
	StackPollServices stackPollServices;

	@Autowired
	RegStPollServices regStPollServices;

	@Autowired
	AmbientServices ambientServices;

	@Autowired
	StackPollRepository stackPollRepository;

	@Autowired
	WaterServices waterServices;

	@Autowired
	RegAmbientPollServices regAmbientPollServices;

	@Autowired
	AmbientPollServices ambientPollServices;

	@Autowired
	StackPollDataServices stackPollDataServices;

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	AmbientPollDataServices ambientPollDataServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	WateriePollutantOpServices wateriePollutantOpServices;

	@Autowired
	WaterSewPollOpServices waterSewPollOpServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;
	private static final Logger LOGGER = LogManager.getLogger(MonitoringController.class);

	/**
	 * This method is used to show the third party monitoring data i.e. Ambient, Stack and Water.
	 * 
	 * @param request The servlet request we are processing.
	 * @return Added monitoring data
	 */
	@RequestMapping("monitoring-add")
	public ModelAndView getThirdPartyDeskAdd(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView();
		try
		{
			List<StackPoll> stackpoll = null;
			List<AmbientPoll> ambientpoll = null;

			ArrayList<Ambient> arAmbientList = new ArrayList<>();
			ArrayList<AmbientPoll> arAmbientPollList = new ArrayList<>();

			Integer companyId = null;
			String today = Utilities.getTodaysDate();
			EmpData empdata = (EmpData) request.getSession().getAttribute(Constant.EMPDATASESSION);

			companyId = empdata.getCompanyProfile().getCompanyProfileId();

			// For Ambient
			// get ambient id
			arAmbientList.addAll(ambientServices.findByAmbient(today));

			// get ambientPoll entries to show in jsp page
			for (int i = 0; i < arAmbientList.size(); i++)
			{
				ambientpoll = ambientPollServices.findAmbientInfo(arAmbientList.get(i).getAmbientId());
				arAmbientPollList.addAll(ambientpoll);
			}

			// Changes the code in WateriePollutant ConsentToOperate='Yes'
			// changes to code in op table
			List<WateriePollutant> wateriePollutant = wateriePollutantServices.findByTodayDateAndCid(today);
			List<WaterSewPoll> waterSewPoll = waterSewPollServices.findByTodayDateAndCmpid(today);

			model.setViewName("ThirdParty/Analysing");

			model.addObject("waterSewPoll", waterSewPoll);
			model.addObject("wateriePollutant", wateriePollutant);
			model.addObject("companyIdSession", companyId);
			model.addObject("ambientPollList", arAmbientPollList);
			model.addObject("ambientList", arAmbientList);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return model;
	}

	/**
	 * This method used to saved the Stack monitoring data.
	 * 
	 * @param action It return file uploading and file name
	 * @param fn The file name
	 * @param stackId The ID of the Stack.
	 * @param file_data the upload the file stack Monitering data
	 * @param sampSt The date of the Sample Collected Date
	 * @param subSt The date of the Report Submitted Date
	 * @param stackName The Name of the Stack.
	 * @param attachTo The address of the attach file address
	 * @param gasTemp The check the Temperature of Flue Gas
	 * @param pressure The start and end pressure calculate and Differential Pressure
	 * @param exitGasVelocity The exit gas velocity
	 * @param hrsOp The Hours of Operation
	 * @param gasQuant The calculate the gas quantity.
	 * @param pollName The Name of the Pollutant
	 * @param concPoll
	 * @param request The servlet request we are processing.
	 * @param units The calculate the units
	 * @return if data save it return filesuccess otherwise return NoDates
	 */
	@PostMapping(value = "ajax-saveStackData")
	@ResponseBody
	public String saveStackData(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "fn", required = false) String fn,
			@RequestParam(value = "stackId", required = false) Stack stackId,
			@RequestParam(value = "file", required = false) MultipartFile fileData,
			@RequestParam(value = "sampSt", required = false) String sampSt,
			@RequestParam(value = "subSt", required = false) String subSt,
			@RequestParam(value = "stackName", required = false) String stackName,
			@RequestParam(value = "attachTo", required = false) String attachTo,
			@RequestParam(value = "gasTemp", required = false) String gasTemp,
			@RequestParam(value = "pressure", required = false) String pressure,
			@RequestParam(value = "exitGasVelocity", required = false) String exitGasVelocity,
			@RequestParam(value = "hrsOp", required = false) String hrsOp,
			@RequestParam(value = "gasQuant", required = false) String gasQuant,
			@RequestParam(value = "pollName", required = false) String[] pollName,
			@RequestParam(value = "concPoll", required = false) Float[] concPoll, HttpServletRequest request,
			@RequestParam(value = "units", required = false) String[] units)
	{
		String result = null;

		EmpData empDataSession = null;
		String compName = null;
		String renameFile = null;
		String mainFile = null;
		if (!Validator.isEmpty(request.getSession().getAttribute(Constant.EMPDATASESSION)))
		{
			empDataSession = (EmpData) request.getSession().getAttribute(Constant.EMPDATASESSION);
			compName = empDataSession.getCompanyProfile().getCompName();
		}
		renameFile = Utilities.renameFile(compName);
		if (action.equals("fileUploading"))
		{
			String folderName = fn;
			String file = null;
			int regstpollid = 0;
			try
			{
				byte[] bytes = null;
				if (!fileData.isEmpty())
				{
					file = fileData.getOriginalFilename();
					bytes = fileData.getBytes();
				}
				mainFile = renameFile + "_" + file;
				Path path = Paths.get(Constant.ThirdParty_file_path + folderName + "\\" + mainFile);
				Files.write(path, bytes);
				for (int i = 0; i < pollName.length; i++)
				{
					if (!sampSt.equalsIgnoreCase("") || !subSt.equalsIgnoreCase(""))
					{
						if (pollName[i] != null)
						{
							if (i < 1)
							{
								RegStPoll regstack = new RegStPoll();
								regstack.setFileName(mainFile);
								regstack.setStack(stackId);
								regstack.setStackFilePath(Constant.ThirdParty_file_path + folderName + "\\" + mainFile);
								regstack.setSampSt(sampSt);
								regstack.setSubSt(subSt);
								regstack.setStackName(stackName);
								regstack.setAttachTo(attachTo);
								regstack.setGasTemp(gasTemp);
								regstack.setPressure(pressure);
								regstack.setExitGasVelocity(Float.valueOf(exitGasVelocity));
								regstack.setHrsOp(hrsOp);
								regstack.setGasQuant(gasQuant);
								regStPollServices.save(regstack);
								regstpollid = regstack.getRegStPollId();
							}
							RegStPoll regstpoll = new RegStPoll();
							regstpoll.setRegStPollId(regstpollid);
							StackPollData stdata = new StackPollData();
							stdata.setRegStPoll(regstpoll);
							stdata.setPollName(pollName[i]);
							stdata.setConcPoll(concPoll[i]);
							stdata.setPollUnit(units[i]);
							stackPollDataServices.save(stdata);
							result = "filesuccess";
						}
						else
						{
							break;
						}
					}
					else
					{
						result = "NoDates";
					}
				}
			}
			catch (IOException e)
			{
				LOGGER.error(e);
			}
		}
		return result;
	}

	/**
	 * This method used to saved the Ambient monitoring data.
	 * 
	 * @param action It return file uploading and file name
	 * @param fn The file name
	 * @param ambientId The ID of the ambient
	 * @param file_data The upload the file Ambient Monitering data.
	 * @param ambientName The Name of the ambient
	 * @param criteria The criteria of the Ambient.
	 * @param sampAmb The date of the Sample Collected Date
	 * @param subAmb The date of the Report Submitted Date
	 * @param pollName The name of the Pollutant name
	 * @param concPoll
	 * @param request The servlet request we are processing.
	 * @param units The calculate the units.
	 * @return Report Saved Successfully
	 */
	@PostMapping(value = "ajax-saveAmbientData")
	@ResponseBody
	public String saveAmbientData(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "fn", required = false) String fn,
			@RequestParam(value = "ambientId", required = false) int ambientId,
			@RequestParam(value = "file", required = false) MultipartFile fileData,
			@RequestParam(value = "ambientName", required = false) String ambientName,
			@RequestParam(value = "criteria", required = false) String criteria,
			@RequestParam(value = "sampAmb", required = false) String sampAmb,
			@RequestParam(value = "subAmb", required = false) String subAmb,
			@RequestParam(value = "pollName", required = false) String[] pollName,
			@RequestParam(value = "concPoll", required = false) String[] concPoll, HttpServletRequest request,
			@RequestParam(value = "units", required = false) String[] units)
	{
		String result = null;
		try
		{
			EmpData empDataSession = null;
			String compName = null;
			String renameFile = null;
			String mainFile = null;
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				compName = empDataSession.getCompanyProfile().getCompName();
			}
			renameFile = Utilities.renameFile(compName);
			int regAmbientPollId = 0;
			if (action.equals("fileUploading"))
			{
				String folderName = fn;
				String file = null;
				byte[] bytes = null;
				if (!fileData.isEmpty())
				{
					file = fileData.getOriginalFilename();
					bytes = fileData.getBytes();
				}
				mainFile = renameFile + "_" + file;
				Path path = Paths.get(Constant.ThirdParty_file_path + folderName + "\\" + mainFile);
				Files.write(path, bytes);
				for (int i = 0; i < pollName.length; i++)
				{
					if (pollName[i] != null)
					{
						if (i < 1)
						{
							RegAmbientPoll regAmbientPoll = new RegAmbientPoll();
							Ambient ambient = new Ambient();
							ambient.setAmbientId(ambientId);
							regAmbientPoll.setAmbient(ambient);
							regAmbientPoll.setSampAmb(sampAmb);
							regAmbientPoll.setSubAmb(subAmb);
							regAmbientPoll.setFilePath(Constant.ThirdParty_file_path + folderName + "\\" + mainFile);
							regAmbientPoll.setFileName(mainFile);
							regAmbientPoll.setLocName(ambientName);
							regAmbientPoll.setCriteria(criteria);
							regAmbientPollServices.save(regAmbientPoll);
							regAmbientPollId = regAmbientPoll.getRegAmbientPollId();
						}
						RegAmbientPoll regAmbientPoll = new RegAmbientPoll();
						regAmbientPoll.setRegAmbientPollId(regAmbientPollId);

						AmbientPollData ambientPollData = new AmbientPollData();
						ambientPollData.setRegAmbientPoll(regAmbientPoll);
						ambientPollData.setPollName(pollName[i]);
						ambientPollData.setConcPoll(concPoll[i]);
						ambientPollData.setUnits(units[i]);
						ambientPollDataServices.save(ambientPollData);
						result = "Report Saved Successfully";
					}
					else
					{
						break;
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return result;
	}

	/**
	 * This method used to saved the water Effluent Pollutant monitoring data.
	 * 
	 * @param pollNameE The name of the Effluent Pollutant.
	 * @param wateriePollutantFile The upload the Effluent Monitoring file.
	 * @param inConsE The value of the Inlet (Conc.) Effluent.
	 * @param ouConsE The value of the Outlet (Conc.) Effluent.
	 * @param unitE The value of unit Effluent.
	 * @param sampE The date of the Effluent Sample Collected Dateajax-tp-getAmbientList
	 * @param request The servlet request we are processing.
	 * @param subE The date of the Effluent Report Submited Date.
	 * @return flag it return count acording to number of row inserted
	 */
	@PostMapping(value = "ajax-save-waterie-pollutant")
	public @ResponseBody int setWateriePollutantData(
			@RequestParam(value = "poll_name_e", required = false) Integer[] pollNameE,
			@RequestParam(value = "file", required = false) MultipartFile wateriePollutantFile,
			@RequestParam(value = "wwtpid", required = false) Integer wwtid,
			@RequestParam(value = "in_cons_e", required = false) float[] inConsE,
			@RequestParam(value = "ou_cons_e", required = false) float[] ouConsE,
			@RequestParam(value = "unit_e", required = false) String[] unitE,
			@RequestParam(value = "samp_e", required = false) String sampE, HttpServletRequest request,
			@RequestParam(value = "sub_e", required = false) String subE)
	{
		int flag = 0;
		try
		{
			EmpData empDataSession = null;
			String compName = null;
			String renameFile = null;
			String mainFile = null;
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				compName = empDataSession.getCompanyProfile().getCompName();
			}
			renameFile = Utilities.renameFile(compName);
			String file = wateriePollutantFile.getOriginalFilename();
			String folderName = "eff";
			if (!wateriePollutantFile.isEmpty())
			{
				mainFile = renameFile + "_" + file;
				Path path = Paths.get(Constant.ThirdParty_file_path + folderName + "\\" + mainFile);
				try
				{
					byte[] bytes = wateriePollutantFile.getBytes();
					Files.write(path, bytes);
				}
				catch (IOException e)
				{
					LOGGER.error(e);
				}
			}
			int pollNameData = pollNameE.length;
			int unitEData = unitE.length;
			WastewaterTreatment wastewaterTreatment = new WastewaterTreatment();
			wastewaterTreatment.setWastewaterTreatmentId(wwtid);
			for (int i = 0; i < pollNameData && i < unitEData; i++)
			{
				RegEffPoll regEffPoll = new RegEffPoll();

				regEffPoll.setWastewaterTreatment(wastewaterTreatment);

				WateriePollutantOp wateriePollutantOp = new WateriePollutantOp();
				wateriePollutantOp.setWateriePollutantOpId(pollNameE[i]);

				regEffPoll.setWateriePollutantOp(wateriePollutantOp);

				regEffPoll.setInConsE(inConsE[i]);
				regEffPoll.setOuConsE(ouConsE[i]);
				regEffPoll.setUnitE(unitE[i]);
				regEffPoll.setSampE(sampE);
				regEffPoll.setSubE(subE);
				regEffPoll.setFileName(mainFile);
				regEffPoll.setFilePath(Constant.ThirdParty_file_path + folderName + "\\" + mainFile);
				waterServices.save(regEffPoll);
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
	 * This method used to saved the water sewage Pollutant monitoring data.
	 * 
	 * @param pollNameE The name of the Sewage Pollutant.
	 * @param inConsE The value of the Inlet (Conc.) sewage.
	 * @param ouConsE The value of the Outlet (Conc.) sewage.
	 * @param unitE The value of unit Sewage.
	 * @param sampS The date of the sewage Sample Collected Date.
	 * @param subS The date of the sewage Report Submited Date.
	 * @param request The servlet request we are processing.
	 * @param waterSewFile The upload the water Sewage Monitering data
	 * @return Report Saved Successfully
	 */
	@PostMapping(value = "ajax-save-water-sew-poll-data")
	public @ResponseBody int setWaterSewData(
			@RequestParam(value = "poll_name_e", required = false) Integer[] pollNameE,
			@RequestParam(value = "in_cons_e", required = false) float[] inConsE,
			@RequestParam(value = "wwtpid", required = false) Integer wwtid,
			@RequestParam(value = "ou_cons_e", required = false) float[] ouConsE,
			@RequestParam(value = "unit_e", required = false) String[] unitE,
			@RequestParam(value = "samp_e", required = false) String sampS,
			@RequestParam(value = "sub_e", required = false) String subS, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile waterSewFile)
	{
		int result = 0;
		try
		{
			EmpData empDataSession = null;
			String compName = null;
			String renameFile = null;
			String mainFile = null;
			if (!Validator.isEmpty(request.getSession().getAttribute("empDataSession")))
			{
				empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				compName = empDataSession.getCompanyProfile().getCompName();
			}
			renameFile = Utilities.renameFile(compName);
			String file = waterSewFile.getOriginalFilename();
			String folderName = "sew";
			if (!waterSewFile.isEmpty())
			{
				mainFile = renameFile + "_" + file;
				Path path = Paths.get(Constant.ThirdParty_file_path + folderName + "//" + mainFile);
				byte[] bytes = waterSewFile.getBytes();
				Files.write(path, bytes);
			}

			int pollNameData = pollNameE.length;
			int unitEData = unitE.length;

			WastewaterTreatment wastewaterTreatment = new WastewaterTreatment();
			wastewaterTreatment.setWastewaterTreatmentId(wwtid);
			for (int i = 0; i < pollNameData && i < unitEData; i++)
			{
				RegSewPoll regSewPoll = new RegSewPoll();

				regSewPoll.setWastewaterTreatment(wastewaterTreatment);

				WaterSewPollOp waterSewPollOP = new WaterSewPollOp();
				waterSewPollOP.setWaterSewPollOpId(pollNameE[i]);
				regSewPoll.setWaterSewPollOp(waterSewPollOP);

				regSewPoll.setInConsS(inConsE[i]);
				regSewPoll.setOuConsS(ouConsE[i]);
				regSewPoll.setUnitS(unitE[i]);
				regSewPoll.setSampS(sampS);
				regSewPoll.setSubS(subS);
				regSewPoll.setFileName(mainFile);
				regSewPoll.setFilePath(Constant.ThirdParty_file_path + folderName + "//" + mainFile);
				waterServices.save(regSewPoll);
				result++;
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return result;
	}

	/*
	 * @GetMapping(value = "/ajax-openNewTab")
	 * public ResponseEntity<byte[]> showNewTab(HttpServletRequest request,
	 * @RequestParam(value = "filepath", required = false) String filePath) throws IOException {
	 * HttpHeaders headers = new HttpHeaders();
	 * String f = filePath;
	 * String fileName = filePath.substring(f.lastIndexOf('/'));
	 * String[] extName = fileName.split("\\.");
	 * headers.setContentType(MediaType.parseMediaType("application/" + extName[1]));
	 * Path path = Paths.get(f);
	 * byte[] data = Files.readAllBytes(path);
	 * headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	 * return new ResponseEntity<>(data, headers, HttpStatus.OK);
	 * }
	 */

	@RequestMapping("/ajax-tp-getStackList")
	public @ResponseBody String GetStackList()
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		try
		{
			ArrayList<Stack> StackListData = new ArrayList<>();
			List<StackPoll> stackpoll = new ArrayList<>();
			String today = Utilities.getTodaysDate();
			List<Stack> stack = stackServices.getConsentId(today);
			StackListData.addAll(stack);

			for (int i = 0; i < StackListData.size(); i++)
			{
				json = new JSONObject();
				json.put("StackId", new Integer(StackListData.get(i).getStackId())); // get StackId 1
				json.put("StackName", new String(StackListData.get(i).getStackName()));// get StackName 2
				json.put("StackAttachedTo", new String(StackListData.get(i).getAttachedTo()));// get AttachedTo 3
				json.put("StackMatCons", new String(StackListData.get(i).getMatCons()));// get MatCons 4
				json.put("StackHeight", new Float(StackListData.get(i).getHeight()));// get Height 5
				json.put("StackHeightUnit", new String(StackListData.get(i).getHtUnits()));// get StackHeightUnit 6
				json.put("StackShape", new String(StackListData.get(i).getStackName()));// get StackShape 7
				json.put("StackFuelType", new String(StackListData.get(i).getFuelType()));// get StackFuelType 8
				json.put("StackFuelUnit", new String(StackListData.get(i).getFuelUnits()));// get StackFuelUnit 9
				json.put("StackDia", new Float(StackListData.get(i).getDiam()));// get StackDia 10
				json.put("StackDiaUnit", new String(StackListData.get(i).getDiamUnits()));// get DiaUnit 11

				stackpoll = stackPollServices.findByStackId(stack.get(i).getStackId());
				JSONArray jsonPollDataArray1 = new JSONArray();
				for (int j = 0; j < stackpoll.size(); j++)
				{
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("StackPollStackId", new Integer(stack.get(i).getStackId())); // get StackPollId 1
					jsonObj.put("StackPollId", new Integer(stackpoll.get(j).getStackPollId())); // get StackPollId 1
					jsonObj.put("StackPollName", new String(stackpoll.get(j).getPollName())); // get getPollName 2
					jsonObj.put("StackPollUnit", new String(stackpoll.get(j).getUnit().getUnits())); // get StackPollUnit 3
					jsonPollDataArray1.put(jsonObj);
				}
				json.put("StackPollDatas", jsonPollDataArray1);
				FinalArray.put(json);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return FinalArray.toString();
	}

	@RequestMapping("/ajax-tp-getAmbientList")
	public @ResponseBody String GetAmbientList()
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		try
		{
			// amb
			ArrayList<Ambient> arAmbientList = new ArrayList<>();
			ArrayList<AmbientPoll> arAmbientPollList = new ArrayList<>();

			// ambient data
			String today = Utilities.getTodaysDate();
			arAmbientList.addAll(ambientServices.findByAmbient(today));

			// get ambientPoll entries to show in jsp page

			for (int i = 0; i < arAmbientList.size(); i++)
			{
				json = new JSONObject();
				List<AmbientPoll> ambientpoll = ambientPollServices.findAmbientInfo(arAmbientList.get(i).getAmbientId());

				json.put("AmbientName", new String(arAmbientList.get(i).getAmbientLocName()));
				json.put("Criteria", new String(arAmbientList.get(i).getCriteria()));
				json.put("AmbientId", new Integer(arAmbientList.get(i).getAmbientId()));
				JSONArray jsonPollDataArray = new JSONArray();

				for (int j = 0; j < ambientpoll.size(); j++)
				{
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("AmbientName", new String(arAmbientList.get(i).getAmbientLocName())); // get AmbientName 1
					jsonObj.put("AmbientPollId", new Integer(ambientpoll.get(j).getAmbientPollId())); // get AmbientPollId 1
					jsonObj.put("AmbientPollName", new String(ambientpoll.get(j).getPollName())); // get AmbientPollName 2
					jsonObj.put("AmbientPollUnit", new String(ambientpoll.get(j).getUnit().getUnits())); // get AmbientPollUnit 3
					jsonPollDataArray.put(jsonObj);
				}

				json.put("AmbientPollDatas", jsonPollDataArray);
				FinalArray.put(json);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return FinalArray.toString();
	}

	// water
	@RequestMapping("ajax-tp-getWaterList")
	public @ResponseBody String GetWaterList()
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		Boolean flag = false;
		try
		{

			String today = Utilities.getTodaysDate();
			int id = waterInventoryServices.getWaterInventoryIdByConsent(today);

			List<WastewaterTreatment> waterLabelList = wastewaterTreatmentServices.getAllWasteWaterTreatmentData(id);
			if (!Validator.isEmpty(waterLabelList))
			{
				flag = true;
				for (int i = 0; i < waterLabelList.size(); i++)
				{
					json = new JSONObject();
					json.put("Result", flag);
					json.put("tretmentType", waterLabelList.get(i).getTreatmentType());
					json.put("label", waterLabelList.get(i).getLabel());
					json.put("wwtid", waterLabelList.get(i).getWastewaterTreatmentId());
					JSONArray jsonPollDataArray = new JSONArray();
					String abc = waterLabelList.get(i).getTreatmentType();
					if (waterLabelList.get(i).getTreatmentType().equals("ETP"))
					{

						List<WateriePollutantOp> wateriePollutant = wateriePollutantOpServices.findByTodayDateAndCid(today);
						json.put("Pollutant", new String("Effluent Pollutant"));
						for (int j = 0; j < wateriePollutant.size(); j++)
						{
							JSONObject jsonObj = new JSONObject();
							jsonObj.put("WaterPollutantName", new String(wateriePollutant.get(j).getWateriePollutant().getPollName()));
							jsonObj.put("pollutantId", new Integer(wateriePollutant.get(j).getWateriePollutantOpId())); // id
							jsonObj.put("WaterPollutantUnit", new String(wateriePollutant.get(j).getWateriePollutant().getUnit().getUnits())); // get unit 1
							jsonPollDataArray.put(jsonObj);
						}

					}
					else
					{
						json.put("Pollutant", new String("Sewage Pollutant"));
						List<WaterSewPollOp> waterSewPoll = waterSewPollOpServices.findByTodayDateAndCmpid(today);
						for (int j = 0; j < waterSewPoll.size(); j++)
						{
							JSONObject jsonObj = new JSONObject();
							jsonObj.put("WaterPollutantName", new String(waterSewPoll.get(j).getWaterSewPoll().getPollName())); // get AmbientName 1
							jsonObj.put("pollutantId", new Integer(waterSewPoll.get(j).getWaterSewPollOpId()));
							jsonObj.put("WaterPollutantUnit", new String(waterSewPoll.get(j).getWaterSewPoll().getUnit().getUnits())); // get AmbientPollId 1
							jsonPollDataArray.put(jsonObj);
						}
					}

					json.put("pollData", jsonPollDataArray);
					FinalArray.put(json);
				}
			}
			else
			{
				json = new JSONObject();
				json.put("Result", flag);
				FinalArray.put(json);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return FinalArray.toString();
	}
}
