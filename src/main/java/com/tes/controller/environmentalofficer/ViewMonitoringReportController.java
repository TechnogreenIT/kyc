package com.tes.controller.environmentalofficer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.AmbientPollData;
import com.tes.model.EmpData;
import com.tes.model.RegAmbientPoll;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.RegStPoll;
import com.tes.model.StackPollData;
import com.tes.services.environmentalofficer.AmbientServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.thirdparty.AmbientPollDataServices;
import com.tes.services.thirdparty.RegAmbientPollServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.services.thirdparty.RegStPollServices;
import com.tes.services.thirdparty.StackPollDataServices;
import com.tes.services.thirdparty.StackPollServices;
import com.tes.services.thirdparty.StackServices;

/**
 * This class used to View the third party monitoring Report including stack, ambient,water effluent and
 * water sewage report
 * 
 * @author Tushar Chougule
 */
@Controller
@RequestMapping(value = {"/env", "/management"})
public class ViewMonitoringReportController
{

	@Autowired
	RegStPollServices regStPollServices;

	@Autowired
	RegAmbientPollServices regAmbientPollServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	StackServices stackServices;

	@Autowired
	StackPollServices stackPollServices;

	@Autowired
	StackPollDataServices stackPollDataServices;

	@Autowired
	AmbientServices ambientServices;

	@Autowired
	AmbientPollDataServices ambientPollDataServices;

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	private static final Logger LOGGER = LogManager.getLogger(ViewMonitoringReportController.class);

	/**
	 * This method used to View the monitoring reports.
	 * 
	 * @param request the servlet request we are processing.
	 * @return ViewMonitoringReports
	 */
	@RequestMapping("view-monitoring-reports")
	public ModelAndView getViewMonitoringReports(HttpServletRequest request)
	{

		ModelAndView model;
		model = new ModelAndView("EnvrOfficer/ViewMonitoringReports");
		int cnt = 0;
		TreeSet<Integer> tsYear = new TreeSet<>();
		TreeSet<String> tsStack = new TreeSet<>();
		TreeSet<String> tsAmbient = new TreeSet<>();
		TreeSet<String> tsEffluent = new TreeSet<>();
		TreeSet<String> tsSewage = new TreeSet<>();
		ArrayList<String> stackarraydate = new ArrayList<>();
		ArrayList<String> ambientarraydate = new ArrayList<>();
		ArrayList<String> effluentarraydate = new ArrayList<>();
		ArrayList<String> sewagearraydate = new ArrayList<>();
		ArrayList<String> stackyrmonth = new ArrayList<>();
		ArrayList<String> ambientyrmonth = new ArrayList<>();
		ArrayList<String> sewageyrmonth = new ArrayList<>();
		ArrayList<RegStPoll> regStackArrayList = new ArrayList<>();
		ArrayList<RegAmbientPoll> regAmbientArrayList = new ArrayList<>();
		ArrayList<RegEffPoll> regEffluentArrayList = new ArrayList<>();
		ArrayList<RegSewPoll> regSewageArrayList = new ArrayList<>();
		// int minArray=tsYear.first();
		EmpData empdata = (EmpData) request.getSession().getAttribute("empDataSession");
		int companyId = empdata.getCompanyProfile().getCompanyProfileId();
		String today1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Integer> ambientYear = regAmbientPollServices.getYearFromAmbient();
		int today = Calendar.getInstance().get(Calendar.YEAR);
		// Ambient module
		try
		{
			ArrayList<Integer> ambientIdArray = new ArrayList<>();
			for (int i = 0; i < ambientYear.size(); i++)
			{
				List<String> ambientdate = regAmbientPollServices.getDateForAmbient(ambientYear.get(i));
				ambientarraydate.addAll(ambientdate);
				for (int k = 0; k < ambientarraydate.size(); k++)
				{
					String arrSplit[] = (ambientarraydate.get(k)).split("-");
					tsAmbient.add(arrSplit[0] + "-" + arrSplit[1]);
				}
			}
			ambientyrmonth.addAll(tsAmbient);
			int[] ambientId = ambientServices.getAmbientIdForMonitor(today1, companyId);// send
			for (int k = 0; k < ambientId.length; k++)
			{
				ambientIdArray.add(ambientId[k]);
			}
			for (int i = 0; i < ambientId.length; i++)
			{
				List<Integer> regAmbientPollId = regAmbientPollServices.getRegAmbientId(ambientId[i]);
				int ambientid = ambientId[i];
				for (int j = 0; j < regAmbientPollId.size(); j++)
				{
					int regAmbientId = regAmbientPollId.get(0).intValue();
					List<RegAmbientPoll> regambientdata = regAmbientPollServices.findByAmbientId(ambientid, regAmbientId);
					RegAmbientPoll regAmb = regambientdata.get(0);
					List<AmbientPollData> ambientPollData = ambientPollDataServices.getAmbientPollData(regAmbientId);
					regAmb.setAmbientPollData(ambientPollData);
					regAmbientArrayList.add(regAmb);
				}
			}
			model.addObject("AmbientYear", ambientYear);
			model.addObject("RegAmbientData", regAmbientArrayList);
			model.addObject("ambientdate", tsAmbient);

			// used in Ajax to make accordian
			model.addObject("YearArrayCount", cnt);
			model.addObject("today", today);
			// model.addObject("minPollutant", minArray);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		TreeSet<String> DateArray = new TreeSet<>();
		List<String> stackYear = regStPollServices.getYearFromStackDate();
		for (int j = 0; j < stackYear.size(); j++)
		{
			String a = stackYear.get(j).toString();
			DateArray.add(a);
		}
		List<String> ambientYear1 = regAmbientPollServices.getYearFromAmbientDate();
		for (int j = 0; j < ambientYear1.size(); j++)
		{
			String a = ambientYear1.get(j).toString();
			DateArray.add(a);
		}
		List<String> effluentYear = regEffPollServices.getYearFromEffluentDate();
		for (int j = 0; j < effluentYear.size(); j++)
		{
			String a = effluentYear.get(j).toString();
			DateArray.add(a);
		}
		List<String> sewageYear = regSewPollServices.getYearFromSewageDate();
		for (int j = 0; j < sewageYear.size(); j++)
		{
			String a = sewageYear.get(j).toString();
			DateArray.add(a);
		}
		TreeSet<String> intsReverse = (TreeSet<String>) DateArray.descendingSet();
		model.addObject("DateArray", intsReverse);
		return model;
	}

	/**
	 * This method used to set the monitoring data(i.e. stack, ambient, effluent and sewage) for
	 * monthly report.
	 * get the month to create accordiant
	 * 
	 * @param action
	 * @return jsonArray the array value at the specified position in this array
	 * @throws JSONException indicates that some exception happened during JSON processing.
	 */
	@GetMapping(value = "ajax-setMonths")
	@ResponseBody
	public String getMonth(@RequestParam(value = "action", required = false) String action) throws JSONException
	{
		JSONArray jsonarray;
		jsonarray = new JSONArray();
		try
		{
			ArrayList<String> tsMonth = new ArrayList<>();
			if (action.equals("stack"))
			{
				List<Integer> stackYear = regStPollServices.getYearFromStack();
				if (stackYear != null)
				{
					int arraycnt = stackYear.size();
					if (arraycnt > 0)
					{
						for (int i = 0; i < arraycnt; i++)
						{
							List<Integer> stackMonth = regStPollServices.getMonthFromStack(stackYear.get(i));
							if (stackMonth != null)
							{
								for (int j = 0; j < stackMonth.size(); j++)
								{
									String a = stackMonth.get(j).toString();

									if (a.length() < 2)
									{
										String arrayMonth = "month_" + stackYear.get(i) + "_" + "0"
												+ stackMonth.get(j);
										tsMonth.add(arrayMonth);
									}
									else
									{
										String arrayMonth = "month_" + stackYear.get(i) + "_"
												+ stackMonth.get(j);
										tsMonth.add(arrayMonth);
									}
								}
							}
						}
					}
				}
			}

			if (action.equals("ambient"))
			{
				List<Integer> ambientYear = regAmbientPollServices.getYearFromAmbient();
				if (ambientYear != null)
				{
					int arraycnt = ambientYear.size();
					if (arraycnt > 0)
					{
						for (int i = 0; i < arraycnt; i++)
						{
							List<Integer> ambientMonth = regAmbientPollServices.getMonthFromAmbients(ambientYear.get(i));
							if (ambientMonth != null)
							{
								for (int j = 0; j < ambientMonth.size(); j++)
								{
									String a = ambientMonth.get(j).toString();
									if (a.length() < 2)
									{
										String arrayMonth = "ambient_" + ambientYear.get(i) + "_" + "0"
												+ ambientMonth.get(j);
										tsMonth.add(arrayMonth);
									}
									else
									{
										String arrayMonth = "ambient_" + ambientYear.get(i) + "_"
												+ ambientMonth.get(j);
										tsMonth.add(arrayMonth);
									}
								}
							}
						}
					}
				}
			}

			if (action.equals("eff"))
			{
				List<Integer> effluentYear = regEffPollServices.getYearFromEffluent();
				if (effluentYear != null)
				{
					int arraycnt = effluentYear.size();
					if (arraycnt > 0)
					{
						for (int i = 0; i < arraycnt; i++)
						{
							List<Integer> effluentMonth = regEffPollServices.getMonthFromEffluent(effluentYear.get(i));
							if (effluentMonth != null)
							{
								for (int j = 0; j < effluentMonth.size(); j++)
								{
									String a = effluentMonth.get(j).toString();
									if (a.length() < 2)
									{
										String arrayMonth = "eff_" + effluentYear.get(i) + "_" + "0"
												+ effluentMonth.get(j);
										tsMonth.add(arrayMonth);
									}
									else
									{
										String arrayMonth = "eff_" + effluentYear.get(i) + "_"
												+ effluentMonth.get(j);
										tsMonth.add(arrayMonth);
									}
								}
							}
						}
					}
				}
			}

			if (action.equals("sew"))
			{
				List<Integer> sewYear = regSewPollServices.getYearFromSewage();
				if (sewYear != null)
				{
					int arraycnt = sewYear.size();
					if (arraycnt > 0)
					{
						for (int i = 0; i < arraycnt; i++)
						{
							List<Integer> sewageMonth = regSewPollServices.getMonthFromSewage(sewYear.get(i));
							if (sewageMonth != null)
							{
								for (int j = 0; j < sewageMonth.size(); j++)
								{
									String a = sewageMonth.get(j).toString();
									if (a.length() < 2)
									{
										String arrayMonth = "sew_" + sewYear.get(i) + "_" + "0"
												+ sewageMonth.get(j);
										tsMonth.add(arrayMonth);
									}
									else
									{
										String arrayMonth = "sew_" + sewYear.get(i) + "_" + sewageMonth.get(j);
										tsMonth.add(arrayMonth);
									}
								}
							}
						}
					}
				}
			}
			for (int k = 0; k < tsMonth.size(); k++)
			{
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("month_id", tsMonth.get(k));
				jsonarray.put(jsonobject);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonarray.toString();
	}

	@PostMapping(value = "ajax-view-getStackList")
	public @ResponseBody String getDate(@RequestParam(value = "pdata") String pdata)
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		try
		{
			// System.out.println("dsfsdfsdfs"+pdata);
			List<RegStPoll> StackListData = regStPollServices.getDateFromStackDate(pdata);

			for (int i = 0; i < StackListData.size(); i++)
			{

				json = new JSONObject();
				json.put("StackName", new String(StackListData.get(i).getStackName()));
				json.put("Attachto", new String(StackListData.get(i).getAttachTo()));
				json.put("ms", new String(StackListData.get(i).getStack().getMatCons()));
				json.put("hight", new Float(StackListData.get(i).getStack().getHeight()));
				json.put("hightUnits", new String(StackListData.get(i).getStack().getHtUnits()));
				json.put("Shape", new String(StackListData.get(i).getStack().getShape()));
				json.put("fuletype", new String(StackListData.get(i).getStack().getFuelType()));
				json.put("Gastemp", new String(StackListData.get(i).getGasTemp()));
				json.put("pressure", new String(StackListData.get(i).getPressure()));
				json.put("Diameter", new Float(StackListData.get(i).getStack().getDiam()));
				json.put("Diameterunit", new String(StackListData.get(i).getStack().getDiamUnits()));
				json.put("gasVelocity", new Float(StackListData.get(i).getExitGasVelocity()));
				json.put("hourseopration", new String(StackListData.get(i).getHrsOp()));
				json.put("gasqunt", new String(StackListData.get(i).getGasQuant()));
				json.put("stackFilename", new String(StackListData.get(i).getFileName()));

				int regStackId = StackListData.get(i).getRegStPollId();
				List<StackPollData> stackpolldata = stackPollDataServices.getStackPollDataDay(regStackId);

				JSONArray jsonPollDataArray1 = new JSONArray();
				for (int j = 0; j < stackpolldata.size(); j++)
				{
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("StackPollName", new String(stackpolldata.get(j).getPollName())); // get StackPollId 1
					jsonObj.put("StackPollCons", new Float(stackpolldata.get(j).getConcPoll())); // get getPollName 2
					jsonObj.put("StackPollUnit", new String(stackpolldata.get(j).getPollUnit())); // get StackPollUnit 3
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
		// return jsonarray.toString();
		return FinalArray.toString();
	}

	// ambient data
	@PostMapping(value = "ajax-view-getAmbientList")
	public @ResponseBody String gettodayDate(@RequestParam(value = "pdata") String pdata)
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		try
		{

			List<RegAmbientPoll> regambientdata = regAmbientPollServices.findByAmbientDate(pdata);

			for (int i = 0; i < regambientdata.size(); i++)
			{

				json = new JSONObject();
				json.put("AmbientName", new String(regambientdata.get(i).getLocName()));
				json.put("Filename", new String(regambientdata.get(i).getFileName()));
				json.put("Criteria", new String(regambientdata.get(i).getCriteria()));
				int regAmbientId = regambientdata.get(i).getRegAmbientPollId();

				List<AmbientPollData> ambientpoll = ambientPollDataServices.getAmbientPollData(regAmbientId);

				JSONArray jsonPollDataArray1 = new JSONArray();
				for (int j = 0; j < ambientpoll.size(); j++)
				{
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("AmbientPollId", new Integer(ambientpoll.get(j).getAmbientPollId())); // get AmbientPollId 1
					jsonObj.put("AmbientPollName", new String(ambientpoll.get(j).getPollName())); // get AmbientPollName 2
					jsonObj.put("Ambienconspoll", new String(ambientpoll.get(j).getConcPoll())); // get AmbientPollUnit 3
					jsonObj.put("AmbientPollUnit", new String(ambientpoll.get(j).getUnits())); // get AmbientPollUnit 4
					jsonPollDataArray1.put(jsonObj);
				}
				json.put("AmbientPollDatas", jsonPollDataArray1);
				FinalArray.put(json);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		// return jsonarray.toString();
		return FinalArray.toString();
	}

	@PostMapping(value = "ajax-view-getWaterList")
	public @ResponseBody String getWaterDate(@RequestParam(value = "pdata") String pdata)
	{
		JSONArray FinalArray = new JSONArray();
		JSONObject json;
		try
		{
			List<RegEffPoll> labelEffList = wastewaterTreatmentServices.getlabelListBydate(pdata);
			List<RegSewPoll> labelSewList = wastewaterTreatmentServices.getSewlabelListBydate(pdata);
			for (int i = 0; i < labelEffList.size(); i++)
			{
				json = new JSONObject();
				json.put("label", labelEffList.get(i).getLabel());
				int tretmentId = labelEffList.get(i).getTrementId();
				
					json.put("Pollutant", new String("Effluent Pollutant"));
					List<RegEffPoll> regeffpollList = regEffPollServices.getRegEffPollDataDateAndId(tretmentId, pdata);
					JSONArray dataList = new JSONArray();
					for (int j = 0; j < regeffpollList.size(); j++)
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("pollName", regeffpollList.get(j).getWateriePollutantOp().getWateriePollutant().getPollName());
						jsonObject.put("inCons", regeffpollList.get(j).getInConsE());
						jsonObject.put("outCons", regeffpollList.get(j).getOuConsE());
						jsonObject.put("unit", regeffpollList.get(j).getUnitE());
						jsonObject.put("filName", regeffpollList.get(j).getFileName());
						dataList.put(jsonObject);
					}
					json.put("PollList", dataList);
					FinalArray.put(json);
				}
			 for (int i = 0; i < labelSewList.size(); i++)
				{
				    json = new JSONObject();
				    json.put("label", labelSewList.get(i).getLabel());
					int tretmentId = labelSewList.get(i).getTrementId();
					
					json.put("Pollutant", new String("Sewage Pollutant"));
					List<RegSewPoll> regsewagepollList = regSewPollServices.getRegSewPollDataDateAndId(tretmentId, pdata);
					JSONArray dataList = new JSONArray();
					for (int j = 0; j < regsewagepollList.size(); j++)
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("pollName", regsewagepollList.get(j).getWaterSewPollOp().getWaterSewPoll().getPollName());
						jsonObject.put("inCons", regsewagepollList.get(j).getInConsS());
						jsonObject.put("outCons", regsewagepollList.get(j).getOuConsS());
						jsonObject.put("unit", regsewagepollList.get(j).getUnitS());
						dataList.put(jsonObject);
					}
					json.put("PollList", dataList);
				
				FinalArray.put(json);
			}
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		// return jsonarray.toString();
		return FinalArray.toString();
	}

}
