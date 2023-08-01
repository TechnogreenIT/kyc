package com.tes.controller.environmentalofficer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.tes.model.EmpData;
import com.tes.model.WaterSewPoll;
import com.tes.services.environmentalofficer.AllProductComparativeSheetServices;
import com.tes.services.environmentalofficer.AllProductsServices;
import com.tes.services.environmentalofficer.AmbientServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.environmentalofficer.WaterMarksServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.services.thirdparty.StackServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@Controller
@RequestMapping(value = {"/env", "/management"})
public class OverallPerformanceController
{
	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	WaterMarksServices waterServices;

	@Autowired
	AllProductsServices allProductsServices;

	@Autowired
	AllProductComparativeSheetServices allProductComparativeSheetServices;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	StackServices stackServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	AmbientServices ambientServices;
	
	@Autowired
	WaterSewPollServices waterSewPollServices;

	private static final Logger LOGGER = LogManager.getLogger(OverallPerformanceController.class);

	
	float water =  0.0f;
	float air = 0.0f;
	float hazardous = 0.0f;
	String overallnameReson ="";
	String awhreson="Cross the Consent Limit  ";

	// @PreAuthorize("hasRole('ROLE_ENVROFFICER')")
	@RequestMapping("/waterOverall")
	public ModelAndView overallWaterPerformance(@RequestParam("type") String type, HttpServletRequest request)
	{
		ModelAndView model;
		model = new ModelAndView("EnvrOfficer/WaterOverallPerformance");
		int isETP = 0, isSTP = 0;
		try
		{
			isETP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("ETP");
			if (isETP > 0)
				isETP = 1;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		// Check STP availability
		try
		{
			isSTP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("STP");
			if (isSTP > 0)
				isSTP = 1;
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		model.addObject("type", type);
		model.addObject("isETP", isETP);
		model.addObject("isSTP", isSTP);
		return model;

	}

	// water
	@RequestMapping(value = {"ajax-getWaterPerformancetest"})
	@ResponseBody
	public @JsonRawValue String getWaterPerformance()
	{

		int isETP = 0, isSTP = 0, isBoth = 0, flagWater = 0;

		// Check ETP availability
		try
		{
			isETP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("ETP");
		}

		catch (Exception e)
		{
			LOGGER.error(e);
		}
		if (isETP > 0)
			isETP = 1;

		// Check STP availability
		try
		{
			isSTP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("STP");

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		if (isSTP > 0)
			isSTP = 1;

		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			Float finalEtpValue = 0.0f;
			Float finalStpValue = 0.0f;
			Float tempfinalEtpValue = 0.0f;
			Float tempfinalStpValue = 0.0f;
			Float finalcombined = 0.0f;
			String today = Utilities.getTodaysDate();
			int year = Utilities.getYearFromStringDate(today);

			// etp
			if (isETP == 1)
			{
				int pollId = 0;
				String pollutantName = null;
				float marks1 = 0.0f;
				float consentQuan = 0.0f;
				Float reqQuantity = 0.0f;
				float fuzz = 0.0f;
				float factordiv = 0.0f;
				float fuzzdiv = 0.0f;

				// list
				float percentile = 0.0f;
				List<String> pollname = new ArrayList<>();
				List<Object[]> pollquantity = new ArrayList<>();
				List<Float> marks = new ArrayList<>();
				List<Float> consentlimit = new ArrayList<>();
				
				List<Integer> pollIdn = new ArrayList<>();
				
				List<Float> updatedMarks = new ArrayList<>();
				List<Float> factors = new ArrayList<>();
				List<Float> consentdiv = new ArrayList<>();
				List<Float> mainFactor = new ArrayList<>();
				List<Float> factorValue = new ArrayList<>();
				List<Float> regularMarks = new ArrayList<>();
				List<Float> consentfuzz = new ArrayList<>();
				List<Float> regq = new ArrayList<>();
				float marknewSUM = 0;
				float mk = 0;
				float mk1 = 0;
				float temp = 0;
				float temp1 = 0;
				float finalfuzz = 0.0f;
				float finalRegular = 0.0f;
				float updated = 0.0f;
				float finalVal = 0.0f;
				pollquantity = waterServices.ahpWaterObj("ETP", today);
				// finding factor
				if (!Validator.isEmpty(pollquantity))
				{
					int i = 0;
					int countOfNonCompliance = 0;
					for (Object[] pollquantitydata : pollquantity)
					{
						pollutantName = (String) pollquantitydata[0];
						consentQuan = (Float) pollquantitydata[1];
						marks1 = (Float) pollquantitydata[2];
						pollname.add(pollutantName);
						marks.add(marks1);
						consentlimit.add(consentQuan);

					}

					if (!Validator.isEmpty(marks))
					{
						for (int k = 0; k < marks.size(); k++)
						{
							mk = marks.get(k);
							marknewSUM += mk;
							percentile = consentlimit.get(k) * 10 / 100;
							fuzz = percentile + consentlimit.get(k);
							consentfuzz.add(fuzz);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							mk = marks.get(j);
							temp = (mk / marknewSUM) * 100;// for each poll
							temp = Utilities.getFloatpoint(temp, 3);
							updatedMarks.add(temp);// updated marks
							consentdiv.add(consentfuzz.get(j) / 4);

						}

						for (int j = 0; j < marks.size(); j++)
						{
							updated = updatedMarks.get(j) / 5;
							factors.add(updated);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							factordiv = factors.get(j) / consentdiv.get(j);
							factordiv = Utilities.getFloatpoint(factordiv, 3);
							mainFactor.add(factordiv);
						}

					}

					// regular quantity
					for (int j = 0; j < pollname.size(); j++)
					{
						// reqQuantity = waterServices.getRegEffPollData(year, pollname.get(j));
						reqQuantity = regEffPollServices.getEffPollAvg(pollname.get(j), year);
						if(reqQuantity > consentlimit.get(j)) {
								overallnameReson +=  pollname.get(j)+",";
							}
							
						if (reqQuantity != null)
						{
							regq.add(reqQuantity);
						}
						else
						{
							regq.add(0.0f);
						}

					}

					// regularmarks
					for (int j = 0; j < consentfuzz.size(); j++)
					{
						finalfuzz = consentfuzz.get(j);
						finalRegular = regq.get(j);
						if (finalRegular >= finalfuzz)
						{
							regularMarks.add(updatedMarks.get(j));
						}
						else
						{
							regularMarks.add(mainFactor.get(j) * regq.get(j));
						}
					}

					for (int ii = 0; ii < regularMarks.size(); ii++)
					{

						mk1 = regularMarks.get(ii);
						finalVal += mk1;

					}
					finalEtpValue = finalVal;

				}

			}
			// stp
			if (isSTP == 1)
			{
				int pollId = 0;
				String pollutantName = null;
				float marks1 = 0.0f;
				float consentQuan = 0.0f;
				Float reqQuantity = 0.0f;
				float fuzz = 0.0f;
				float factordiv = 0.0f;
				float fuzzdiv = 0.0f;

				// list
				float percentile = 0.0f;
				List<String> pollname = new ArrayList<>();
				List<Object[]> pollquantity = new ArrayList<>();
				List<Float> marks = new ArrayList<>();
				List<Float> consentlimit = new ArrayList<>();
				
				List<Integer> pollIdn = new ArrayList<>();
				List<Float> updatedMarks = new ArrayList<>();
				List<Float> factors = new ArrayList<>();
				List<Float> consentdiv = new ArrayList<>();
				List<Float> mainFactor = new ArrayList<>();
				List<Float> factorValue = new ArrayList<>();
				List<Float> regularMarks = new ArrayList<>();
				List<Float> consentfuzz = new ArrayList<>();
				List<Float> regq = new ArrayList<>();
				float marknewSUM = 0;
				float mk = 0;
				float mk1 = 0;
				float temp = 0;
				float temp1 = 0;
				float finalfuzz = 0.0f;
				float finalRegular = 0.0f;
				float updated = 0.0f;
				float finalVal = 0.0f;

				pollquantity = waterServices.ahpWatersew(today);
				if (!Validator.isEmpty(pollquantity))
				{
					int i = 0;
					int countOfNonCompliance = 0;
					for (Object[] pollquantitydata : pollquantity)
					{
						pollutantName = (String) pollquantitydata[0];
						consentQuan = (Float) pollquantitydata[1];
						marks1 = (Float) pollquantitydata[2];
						pollId = (int) pollquantitydata[3];
						pollname.add(pollutantName);
						marks.add(marks1);
						consentlimit.add(consentQuan);
						pollIdn.add(pollId);
					}

					if (!Validator.isEmpty(marks))
					{

						for (int k = 0; k < marks.size(); k++)
						{
							mk = marks.get(k);
							marknewSUM += mk;
							percentile = consentlimit.get(k) * 10 / 100;
							fuzz = percentile + consentlimit.get(k);
							consentfuzz.add(fuzz);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							mk = marks.get(j);
							temp = (mk / marknewSUM) * 100;// for each poll
							temp = Utilities.getFloatpoint(temp, 3);
							updatedMarks.add(temp);// updated marks
							consentdiv.add(consentfuzz.get(j) / 4);

						}

						for (int j = 0; j < marks.size(); j++)
						{
							updated = updatedMarks.get(j) / 5;
							factors.add(updated);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							factordiv = factors.get(j) / consentdiv.get(j);
							factordiv = Utilities.getFloatpoint(factordiv, 3);
							mainFactor.add(factordiv);
						}

						// regular quantity
						for (int j = 0; j < pollname.size(); j++)
						{
							// reqQuantity = waterServices.getRegSewPollData(year, pollname.get(j));
							reqQuantity = regSewPollServices.getSewPollAvg(pollname.get(j), year);
							if(reqQuantity > consentlimit.get(j)) {								
								overallnameReson +=  pollname.get(j)+ ",";
								}
							if (reqQuantity != null)
							{
								regq.add(reqQuantity);
							}
							else
							{
								regq.add(0.0f);
							}

						}
						// regularmarks
						for (int j = 0; j < consentfuzz.size(); j++)
						{
							finalfuzz = consentfuzz.get(j);
							finalRegular = regq.get(j);
							if (finalRegular >= finalfuzz)
							{
								regularMarks.add(updatedMarks.get(j));
							}
							else
							{
								regularMarks.add(mainFactor.get(j) * regq.get(j));
							}
						}

						for (int ii = 0; ii < regularMarks.size(); ii++)
						{

							mk1 = regularMarks.get(ii);
							finalVal += mk1;

						}
						finalStpValue = finalVal;
					}
				}

			}

			// etp and stp

			tempfinalEtpValue = finalEtpValue * 70 / 100;
			tempfinalStpValue = finalStpValue * 30 / 100;
			finalcombined = tempfinalEtpValue + tempfinalStpValue;

			if (isETP == 1 && isSTP == 1)
			{
				water = finalcombined;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine")); // from eto 70% & stp 30%
				hashMap.put("finalCombinedValue", new Float(Utilities.getFloatpoint(finalcombined, 2))); // from eto 70% & stp 30%
				jsonArray.put(hashMap);
			}
			else if (isETP == 1 && isSTP == 0)
			{
				water = finalEtpValue;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine"));
				hashMap.put("finalCombinedValue", new Float(Utilities.getFloatpoint(finalEtpValue, 2)));
				jsonArray.put(hashMap);

			}
			else if (isETP == 0 && isSTP == 1)
			{
				water = finalStpValue;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine"));
				hashMap.put("finalCombinedValue", new Float(Utilities.getFloatpoint(finalStpValue, 2)));
				jsonArray.put(hashMap);
			}
			else
			{
				water = 0;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine"));
				hashMap.put("finalCombinedValue", new Float(0.0f));
				jsonArray.put(hashMap);

			}

			if (isETP == 1)
			{
				HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
				hashMap1.put("meterType", new String("ETP")); // from eto 70% & stp 30%
				hashMap1.put("finalEtpValue", new Float(Utilities.getFloatpoint(finalEtpValue, 2))); // from eto 70% & stp 30%
				jsonArray.put(hashMap1);
			}

			else
			{
				HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
				hashMap1.put("meterType", new String("ETP"));
				hashMap1.put("finalEtpValue", new Float(0.0f));
				jsonArray.put(hashMap1);
			}

			if (isSTP == 1)
			{
				HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
				hashMap2.put("meterType", new String("STP")); // from eto 70% & stp 30%
				hashMap2.put("finalStpValue", new Float(Utilities.getFloatpoint(finalStpValue, 2))); // from eto 70% & stp 30%
				jsonArray.put(hashMap2);
			}
			else
			{
				HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
				hashMap2.put("meterType", new String("STP"));
				hashMap2.put("finalStpValue", new Float(0.0f));
				jsonArray.put(hashMap2);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	///// mmmm STP waste water non-compliance performance display.
	@RequestMapping(value = {"ajax-getWaterPerformanceSTPtest"})
	@ResponseBody
	public @JsonRawValue String getWaterPerformancenew()
	{
		if(!overallnameReson.isEmpty())
		{
			overallnameReson="";
		}
		
		int isSTP = 0, isBoth = 0, flagWater = 0;
		// Check STP availability
		try
		{
			isSTP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("STP");

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		if (isSTP > 0)
			isSTP = 1;

		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			Float finalStpValue = 0.0f;
			Float tempfinalStpValue = 0.0f;
			Float finalcombined = 0.0f;
			String today = Utilities.getTodaysDate();
			int year = Utilities.getYearFromStringDate(today);
			String pollNameReson="";
			String reson="Improve your ";
			// stp
			if (isSTP == 1)
			{
				
				int pollId = 0;
				String pollutantName = null;
				float marks1 = 0.0f;
				float consentQuan = 0.0f;
				Float reqQuantity = 0.0f;
				float fuzz = 0.0f;
				float factordiv = 0.0f;
				float fuzzdiv = 0.0f;

				// list
				float percentile = 0.0f;
				List<String> pollname = new ArrayList<>();
				List<Object[]> pollquantity = new ArrayList<>();
				List<Float> marks = new ArrayList<>();
				List<Float> consentlimit = new ArrayList<>();
				/// mmmm
				List<Integer> pollIdn = new ArrayList<>();
				List<Float> updatedMarks = new ArrayList<>();
				List<Float> factors = new ArrayList<>();
				List<Float> consentdiv = new ArrayList<>();
				List<Float> mainFactor = new ArrayList<>();
				List<Float> factorValue = new ArrayList<>();
				List<Float> regularMarks = new ArrayList<>();
				List<Float> consentfuzz = new ArrayList<>();
				List<Float> regq = new ArrayList<>();
				float marknewSUM = 0;
				float mk = 0;
				float mk1 = 0;
				float temp = 0;
				float temp1 = 0;
				float finalfuzz = 0.0f;
				float finalRegular = 0.0f;
				float updated = 0.0f;
				float finalVal = 0.0f;
				
				

				pollquantity = waterServices.ahpWatersew(today);
				if (!Validator.isEmpty(pollquantity))
				{
					int i = 0;
					int countOfNonCompliance = 0;
					for (Object[] pollquantitydata : pollquantity)
					{
						pollutantName = (String) pollquantitydata[0];
						consentQuan = (Float) pollquantitydata[1];
						marks1 = (Float) pollquantitydata[2];
						pollId = (int) pollquantitydata[3];
						pollname.add(pollutantName);
						marks.add(marks1);
						consentlimit.add(consentQuan);
						pollIdn.add(pollId);

					}

					if (!Validator.isEmpty(marks))
					{

						for (int k = 0; k < marks.size(); k++)
						{
							mk = marks.get(k);
							marknewSUM += mk;
							percentile = consentlimit.get(k) * 10 / 100;
							fuzz = percentile + consentlimit.get(k);
							consentfuzz.add(fuzz);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							mk = marks.get(j);
							temp = (mk / marknewSUM) * 100;// for each poll
							temp = Utilities.getFloatpoint(temp, 3);
							updatedMarks.add(temp);// updated marks
							consentdiv.add(consentfuzz.get(j) / 4);

						}

						for (int j = 0; j < marks.size(); j++)
						{
							updated = updatedMarks.get(j) / 5;
							factors.add(updated);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							factordiv = factors.get(j) / consentdiv.get(j);
							factordiv = Utilities.getFloatpoint(factordiv, 3);
							mainFactor.add(factordiv);
						}
						
						// regular quantity
						for (int j = 0; j < pollname.size(); j++)
						{
							// reqQuantity = waterServices.getRegSewPollData(year, pollIdn.get(j));
							reqQuantity = regSewPollServices.getSewPollAvg(pollname.get(j), year);
					
							///reson print
							if(reqQuantity > consentlimit.get(j)) {
							pollNameReson +=  pollname.get(j)+ ",";

						//	overallnameReson +=  pollname.get(j)+",";
							}
							
							///
							if (reqQuantity != null)
							{
								regq.add(reqQuantity);
							}
							else
							{
								regq.add(0.0f);
							}

						}
						// regularmarks
						for (int j = 0; j < consentfuzz.size(); j++)
						{
							finalfuzz = consentfuzz.get(j);
							finalRegular = regq.get(j);
							if (finalRegular >= finalfuzz)
							{
								regularMarks.add(updatedMarks.get(j));
							}
							else
							{
								regularMarks.add(mainFactor.get(j) * regq.get(j));
							}
						}

						for (int ii = 0; ii < regularMarks.size(); ii++)
						{

							mk1 = regularMarks.get(ii);
							finalVal += mk1;

						}
						finalStpValue = finalVal;
					}
				}

			}
			// stp

			// finalStpValue = 34.0f;// testing purpose mmmm
			tempfinalStpValue = finalStpValue * 30 / 100;
			finalcombined = tempfinalStpValue;

			if(pollNameReson != null) {
				reson += pollNameReson;
			}
			if (isSTP == 1)
			{
				//water += finalStpValue;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine"));
				hashMap.put("reson", reson);
				hashMap.put("finalCombinedValue", new Float(Utilities.getFloatpoint(finalStpValue, 2)));
				jsonArray.put(hashMap);
			}
			else
			{
				//water += 0;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine"));
				hashMap.put("reson", reson);
				hashMap.put("finalCombinedValue", new Float(0.0f));
				jsonArray.put(hashMap);

			}

//			if (isSTP == 1)
//			{
//				HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
//				hashMap2.put("meterType", new String("STP")); // from eto 70% & stp 30%
//				hashMap2.put("reson", reson);
//				hashMap2.put("finalStpValue", new Float(Utilities.getFloatpoint(finalStpValue, 2))); // from eto 70% & stp 30%
//				jsonArray.put(hashMap2);
//			}
//			else
//			{
//				HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
//				hashMap2.put("meterType", new String("STP"));
//				hashMap2.put("reson", reson);
//				hashMap2.put("finalStpValue", new Float(0.0f));
//				jsonArray.put(hashMap2);
//			}
			
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}
	/// Stp End Here

	//// ETP waste water non-compliance performance display.
	@RequestMapping(value = {"ajax-getWaterPerformanceETPtest"})
	@ResponseBody
	public @JsonRawValue String getWaterPerformanceETP()
	{

		int isETP = 0, isBoth = 0, flagWater = 0;

		// Check ETP availability
		try
		{
			isETP = wastewaterTreatmentServices.checkWaterTreatmentByTreatmentType("ETP");
		}

		catch (Exception e)
		{
			LOGGER.error(e);
		}
		if (isETP > 0)
			isETP = 1;

		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try
		{
			Float finalEtpValue = 0.0f;
			Float tempfinalEtpValue = 0.0f;
			Float finalcombined = 0.0f;
			String today = Utilities.getTodaysDate();
			int year = Utilities.getYearFromStringDate(today);
			String pollNameReson="";
			String reson="Improve your ";
			
			// etp
			if (isETP == 1)
			{
				String pollutantName = null;
				float marks1 = 0.0f;
				float consentQuan = 0.0f;
				Float reqQuantity = 0.0f;
				float fuzz = 0.0f;
				float factordiv = 0.0f;
				float fuzzdiv = 0.0f;

				// list
				float percentile = 0.0f;
				List<String> pollname = new ArrayList<>();
				List<Object[]> pollquantity = new ArrayList<>();
				List<Float> marks = new ArrayList<>();
				List<Float> consentlimit = new ArrayList<>();
				List<Float> updatedMarks = new ArrayList<>();
				List<Float> factors = new ArrayList<>();
				List<Float> consentdiv = new ArrayList<>();
				List<Float> mainFactor = new ArrayList<>();
				List<Float> factorValue = new ArrayList<>();
				List<Float> regularMarks = new ArrayList<>();
				List<Float> consentfuzz = new ArrayList<>();
				List<Float> regq = new ArrayList<>();
				float marknewSUM = 0;
				float mk = 0;
				float mk1 = 0;
				float temp = 0;
				float temp1 = 0;
				float finalfuzz = 0.0f;
				float finalRegular = 0.0f;
				float updated = 0.0f;
				float finalVal = 0.0f;
				pollquantity = waterServices.ahpWaterObj("ETP", today);
				// finding factor
				if (!Validator.isEmpty(pollquantity))
				{
					int i = 0;
					int countOfNonCompliance = 0;
					for (Object[] pollquantitydata : pollquantity)
					{
						pollutantName = (String) pollquantitydata[0];
						consentQuan = (Float) pollquantitydata[1];
						marks1 = (Float) pollquantitydata[2];
						pollname.add(pollutantName);
						marks.add(marks1);
						consentlimit.add(consentQuan);

					}

					if (!Validator.isEmpty(marks))
					{
						for (int k = 0; k < marks.size(); k++)
						{
							mk = marks.get(k);
							marknewSUM += mk;
							percentile = consentlimit.get(k) * 10 / 100;
							fuzz = percentile + consentlimit.get(k);
							consentfuzz.add(fuzz);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							mk = marks.get(j);
							temp = (mk / marknewSUM) * 100;// for each poll
							temp = Utilities.getFloatpoint(temp, 3);
							updatedMarks.add(temp);// updated marks
							consentdiv.add(consentfuzz.get(j) / 4);

						}

						for (int j = 0; j < marks.size(); j++)
						{
							updated = updatedMarks.get(j) / 5;
							factors.add(updated);
						}

						for (int j = 0; j < marks.size(); j++)
						{
							factordiv = factors.get(j) / consentdiv.get(j);
							factordiv = Utilities.getFloatpoint(factordiv, 3);
							mainFactor.add(factordiv);
						}

					}

					// regular quantity
					for (int j = 0; j < pollname.size(); j++)
					{
						reqQuantity = regEffPollServices.getEffPollAvg(pollname.get(j), year);
						//madetooltip reason
						if(reqQuantity > consentlimit.get(j)) {
							pollNameReson +=  pollname.get(j)+ ",";
							//overallnameReson +=  pollname.get(j)+ ",";
							}
						if (reqQuantity != null)
						{
							regq.add(reqQuantity);
						}
						else
						{
							regq.add(0.0f);
						}

					}

					// regularmarks
					for (int j = 0; j < consentfuzz.size(); j++)
					{
						finalfuzz = consentfuzz.get(j);
						finalRegular = regq.get(j);
						if (finalRegular >= finalfuzz)
						{
							regularMarks.add(updatedMarks.get(j));
						}
						else
						{
							regularMarks.add(mainFactor.get(j) * regq.get(j));
						}
					}

					for (int ii = 0; ii < regularMarks.size(); ii++)
					{

						mk1 = regularMarks.get(ii);
						finalVal += mk1;

					}
					finalEtpValue = finalVal;

				}

			}

			// etp
			// finalEtpValue = 34.0f; // testing purpose mmm
			tempfinalEtpValue = finalEtpValue * 70 / 100;
			finalcombined = tempfinalEtpValue;
			if(pollNameReson != null) {
				reson += pollNameReson;
			}
			
			if (isETP == 1)
			{
//				water += finalcombined;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine")); // from eto 70% & stp 30%
				hashMap.put("reson", reson);
				hashMap.put("finalCombinedValue", new Float(Utilities.getFloatpoint(finalcombined, 2))); // from eto 70% & stp 30%
				jsonArray.put(hashMap);
			}

			else
			{
//				water += 0;
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("meterType", new String("combine"));
				hashMap.put("reson", reson);
				hashMap.put("finalCombinedValue", new Float(0.0f));
				jsonArray.put(hashMap);

			}

			if (isETP == 1)
			{
				HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
				hashMap1.put("meterType", new String("ETP")); // from eto 70% & stp 30%
				hashMap1.put("finalEtpValue", new Float(Utilities.getFloatpoint(finalEtpValue, 2))); // from eto 70% & stp 30%
				jsonArray.put(hashMap1);
			}

			else
			{
				HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
				hashMap1.put("meterType", new String("ETP"));
				hashMap1.put("finalEtpValue", new Float(0.0f));
				jsonArray.put(hashMap1);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	//// mmmm end here
	@RequestMapping(value = {"ajax-overAllEnvPerformanceAir"})
	public @ResponseBody @JsonRawValue String overAllEnvPerformanceAir(HttpServletRequest request)
	{
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		Float finalEtpValue = 0.0f;
		Float finalStpValue = 0.0f;
		Float finalcombined = 0.0f;
		String pollNameReson="";
		String resonair="Improve your ";
		EmpData empdata = (EmpData) request.getSession().getAttribute("empDataSession");
		int companyId = empdata.getCompanyProfile().getCompanyProfileId();
		List<String> typeList = new ArrayList<>();
		String[] stackType = {"Process", "DG Sets", "Boiler", "Others"};
		try
		{
			String today = Utilities.getTodaysDate();
			int month = Utilities.getMonthFromStringDate(today);
			int year = Utilities.getYearFromStringDate(today);
			int countParticulate = 0;
			int countGases = 0;
			float finalStack = 0.0f;
			float finalStack70 = 0.0f;
			float finalAmbient = 0.0f;
			float finalAmbient30 = 0.0f;
			float finalStackParticulate = 0.0f;
			float finalStackGases = 0.0f;
			float finalOverall = 0.0f;
			int particulate = 0;
			int gases = 0;
			int stack = 0;
			int ambient = 0;
			int[] stackId1 = stackServices.getStackIdForAhp(today, companyId);
			List<Integer> ambientId1 = ambientServices.getAmbientIdForAhp(today, companyId);
			typeList = waterServices.typeList();
			for (int i = 0; i < typeList.size(); i++)
			{
				if (typeList.get(i).equalsIgnoreCase("Particulate"))
				{
					countParticulate += 1;
				}
				else if (typeList.get(i).equalsIgnoreCase("Gases"))
				{
					countGases += 1;
				}

			}
			if (countParticulate >= 1)
			{
				particulate = 1;
			}
			if (countGases >= 1)
			{
				gases = 1;
			}
			if (stackId1.length >= 1)
			{
				stack = 1;
			}
			if (ambientId1.size() >= 1)
			{
				ambient = 1;
			}
			if (stack == 1)
			{
				if (particulate == 1)
				{
					float factordiv = 0.0f;
					List<Object[]> pollquantity = new ArrayList<>();
					String productName = null;
					float temp = 0.0f;
					Float conQuantity = 0.0f;
					float tempAll = 0.0f;
					float marknewSUM = 0.0f;
					float fuzz = 0.0f;
					float percentile = 0.0f;
					float marksDiv = 0.0f;
					int countTypeList = 0;
					float factor = 0.0f;
					float finalfuzz = 0.0f;
					Float reqQuantity = 0.0f;
					Float finalRegular = 0.0f;
					Float mk = 0.0f;
					Float mk1 = 0.0f;
					List<String> pname = new ArrayList<>();
					Float finalVal = 0.0f;
					List<Float> consentQuan = new ArrayList<>();
					List<Float> consentFuzz = new ArrayList<>();
					List<Float> consentFuzzDiv = new ArrayList<>();
					List<Object[]> allProductsLists = new ArrayList<>();
					List<Float> factor1 = new ArrayList<>();
					List<Float> regq = new ArrayList<>();
					List<Float> mainFactor = new ArrayList<>();
					List<Float> regularMarks = new ArrayList<>();
					List<Float> marks = new ArrayList<>();
					List<Integer> id = new ArrayList<>();
					List<Integer> sid = new ArrayList<>();
					List<String> type = new ArrayList<>();
					List<Float> consentfuzz = new ArrayList<>();
					List<Float> updatedMarks = new ArrayList<>();
					List<Float> consentdiv = new ArrayList<>();
					List<Float> factors = new ArrayList<>();
					List<String> map = new ArrayList<>();
					float regfinal = 0.0f;
					for (int i = 0; i < stackType.length; i++)
					{
						int[] stackId = stackServices.getStackIdAttchedTo(companyId, stackType[i]);
						int count = stackId.length;
						for (int j = 0; j < count; j++)
						{
							allProductsLists = waterServices.ahpStackObj(stackId[j], stackType[i]);
							for (Object[] allProductsListData : allProductsLists)
							{
								marks.add((Float) allProductsListData[2]);
								pname.add((String) allProductsListData[0]);
								consentQuan.add((Float) allProductsListData[1]);
								type.add((String) allProductsListData[3]);
								id.add((Integer) allProductsListData[4]);
							}
						}

					}

					for (int j = 0; j < pname.size(); j++)
					{
						mk = marks.get(j);
						marknewSUM += mk;
						percentile = consentQuan.get(j) * 10 / 100;
						fuzz = percentile + consentQuan.get(j);
						consentfuzz.add(fuzz);
					}

					for (int j = 0; j < pname.size(); j++)
					{
						mk = marks.get(j);
						temp = (mk / marknewSUM) * 50;// for each poll
						temp = Utilities.getFloatpoint(temp, 3);
						updatedMarks.add(temp);// updated marks
						consentdiv.add(consentfuzz.get(j) / 4);

					}

					for (int j = 0; j < pname.size(); j++)
					{
						factors.add(updatedMarks.get(j) / 5);
					}

					for (int j = 0; j < pname.size(); j++)
					{
						factordiv = factors.get(j) / consentdiv.get(j);
						factordiv = Utilities.getFloatpoint(factordiv, 3);
						mainFactor.add(factordiv);
					}

					// regular quantity
					for (int i = 0; i < pname.size(); i++)
					{
						map.add(pname.get(i) + "-" + id.get(i));
					}

					String[] pollName = null;
					String temp11 = null;
					String name = null;
					int pollStack = 0;

					for (int ii = 0; ii < map.size(); ii++)
					{
						int[] stackId = stackServices.getStackIdAttchedToReg();
						temp11 = map.get(ii);
						pollName = temp11.split("-");
						name = pollName[0];
						pollStack = Integer.parseInt(pollName[1]);
						reqQuantity = waterServices.getRegStackPollData(name, month, year, pollStack);
						//stack reason
						if(reqQuantity > consentQuan.get(ii)) {		
							overallnameReson +=  name+ ",";
							pollNameReson +=  name+ ",";
							
							}
						if (reqQuantity != null)
						{
							regq.add(reqQuantity);
						}
						else
						{
							regq.add(0.0f);
						}
					}

					// regularmarks
					for (int j = 0; j < consentfuzz.size(); j++)
					{
						finalfuzz = consentfuzz.get(j);
						finalRegular = regq.get(j);
						if (finalRegular >= finalfuzz)
						{
							regularMarks.add(updatedMarks.get(j));
						}
						else
						{
							regfinal = mainFactor.get(j) * regq.get(j);
							regfinal = Utilities.getFloatpoint(regfinal, 3);
							regularMarks.add(regfinal);
						}
					}

					for (int ii = 0; ii < regularMarks.size(); ii++)
					{
						mk1 = regularMarks.get(ii);
						finalVal += mk1;
						finalVal = Utilities.getFloatpoint(finalVal, 3);
						finalStackParticulate = finalVal;
					}

				}

				// gas
				if (gases == 1)
				{
					float factordiv = 0.0f;
					List<Object[]> pollquantity = new ArrayList<>();
					String productName = null;
					float temp = 0.0f;
					Float conQuantity = 0.0f;
					float tempAll = 0.0f;
					float marknewSUM = 0.0f;
					float fuzz = 0.0f;
					float percentile = 0.0f;
					float marksDiv = 0.0f;
					int countTypeList = 0;
					float factor = 0.0f;
					float finalfuzz = 0.0f;
					Float reqQuantity = 0.0f;
					Float finalRegular = 0.0f;
					Float mk = 0.0f;
					Float mk1 = 0.0f;
					List<String> pname = new ArrayList<>();
					Float finalVal = 0.0f;
					List<Float> consentQuan = new ArrayList<>();
					List<Float> consentFuzz = new ArrayList<>();
					List<Float> consentFuzzDiv = new ArrayList<>();
					List<Object[]> allProductsLists = new ArrayList<>();
					List<Float> factor1 = new ArrayList<>();
					List<Float> regq = new ArrayList<>();
					List<Float> mainFactor = new ArrayList<>();
					List<Float> regularMarks = new ArrayList<>();
					List<Float> marks = new ArrayList<>();
					List<Integer> id = new ArrayList<>();
					List<Integer> sid = new ArrayList<>();
					List<String> type = new ArrayList<>();
					List<Float> consentfuzz = new ArrayList<>();
					List<Float> updatedMarks = new ArrayList<>();
					List<Float> consentdiv = new ArrayList<>();
					List<Float> factors = new ArrayList<>();
					List<Float> divmarks = new ArrayList<>();
					List<Float> orignalMarks = new ArrayList<>();
					List<String> map = new ArrayList<>();
					float regfinal = 0.0f;

					for (int i = 0; i < stackType.length; i++)
					{
						int[] stackId = stackServices.getStackIdAttchedTo(companyId, stackType[i]);
						int count = stackId.length;
						for (int j = 0; j < count; j++)
						{
							allProductsLists = waterServices.ahpStackObjGases(stackId[j], stackType[i]);
							for (Object[] allProductsListData : allProductsLists)
							{
								int cc = allProductsLists.size();
								orignalMarks.add((Float) allProductsListData[2]);
								marks.add((Float) allProductsListData[2] / cc);
								pname.add((String) allProductsListData[0]);
								consentQuan.add((Float) allProductsListData[1]);
								type.add((String) allProductsListData[3]);
								id.add((Integer) allProductsListData[4]);
							}

						}
					}

					for (int j = 0; j < pname.size(); j++)
					{
						mk = marks.get(j);
						marknewSUM += mk;
						percentile = consentQuan.get(j) * 10 / 100;
						fuzz = percentile + consentQuan.get(j);
						consentfuzz.add(fuzz);
					}

					for (int j = 0; j < pname.size(); j++)
					{
						mk = marks.get(j);
						temp = (mk / marknewSUM) * 50;// for each poll
						temp = Utilities.getFloatpoint(temp, 3);
						updatedMarks.add(temp);// updated marks
						consentdiv.add(consentfuzz.get(j) / 4);
					}

					for (int j = 0; j < pname.size(); j++)
					{
						factors.add(updatedMarks.get(j) / 5);
					}

					for (int j = 0; j < pname.size(); j++)
					{
						factordiv = factors.get(j) / consentdiv.get(j);
						factordiv = Utilities.getFloatpoint(factordiv, 3);
						mainFactor.add(factordiv);
					}

					// regular quantity
					for (int i = 0; i < pname.size(); i++)
					{
						map.add(pname.get(i) + "-" + id.get(i));
					}

					String[] pollName = null;
					String temp11 = null;
					String name = null;
					int pollStack = 0;
					for (int ii = 0; ii < map.size(); ii++)
					{
						int[] stackId = stackServices.getStackIdAttchedToRegGases();
						temp11 = map.get(ii);
						pollName = temp11.split("-");
						name = pollName[0];
						pollStack = Integer.parseInt(pollName[1]);
						
						reqQuantity = waterServices.getRegStackPollData(name, month, year, pollStack);
						//reason gasses
						if(reqQuantity > consentQuan.get(ii)) {								
							overallnameReson +=  name+ ",";
							pollNameReson +=  name+ ",";
							
							}
						if (reqQuantity != null)
						{
							regq.add(reqQuantity);
						}
						else
						{
							regq.add(0.0f);
						}
					}

					// regularmarks
					for (int j = 0; j < consentfuzz.size(); j++)
					{
						finalfuzz = consentfuzz.get(j);
						finalRegular = regq.get(j);
						if (finalRegular >= finalfuzz)
						{
							regularMarks.add(updatedMarks.get(j));
						}
						else
						{
							regfinal = mainFactor.get(j) * regq.get(j);
							regfinal = Utilities.getFloatpoint(regfinal, 3);
							regularMarks.add(regfinal);
						}
					}

					for (int ii = 0; ii < regularMarks.size(); ii++)
					{
						mk1 = regularMarks.get(ii);
						finalVal += mk1;
						finalVal = Utilities.getFloatpoint(finalVal, 3);
						finalStackGases = finalVal;
					}

				}
			}
			if (ambient == 1)
			{
				// ambient
				Float reqQuantity = 0.0f;
				float mk = 0.0f;
				float mk1 = 0.0f;
				float marknewSUM = 0.0f;
				float percentile = 0.0f;
				float fuzz = 0.0f;
				float temp = 0.0f;
				float factordiv = 0.0f;
				float finalfuzz = 0.0f;
				float finalRegular = 0.0f;
				float finalVal = 0.0f;
				List<Float> marks = new ArrayList<>();
				List<Integer> id = new ArrayList<>();
				List<Float> consentQuan = new ArrayList<>();
				List<Float> consentfuzz = new ArrayList<>();
				List<Float> consentdiv = new ArrayList<>();
				List<Float> updatedMarks = new ArrayList<>();
				List<String> type = new ArrayList<>();
				List<String> pname = new ArrayList<>();
				List<Float> factors = new ArrayList<>();
				List<Float> mainFactor = new ArrayList<>();
				List<Float> reqQuantityamb = new ArrayList<>();
				List<Float> regularMarks = new ArrayList<>();

				String[] ambiantType = {"particulate", "Criteria", "Organics", "Special Pollutants"};

				List<Integer> ambientId = ambientServices.getAmbientIdForAhp(today, companyId);
				List<Object[]> allProductsLists = new ArrayList<>();

				for (int j = 0; j < ambientId.size(); j++)
				{
					for (int i = 0; i < ambiantType.length; i++)
					{
						// data fetch for
						allProductsLists = waterServices.getAmbientmarkdata(ambiantType[i], ambientId.get(j));

						for (Object[] allPolutantsListData : allProductsLists)
						{
							marks.add((Float) allPolutantsListData[3]);
							pname.add((String) allPolutantsListData[0]);
							consentQuan.add((Float) allPolutantsListData[2]);
							type.add((String) allPolutantsListData[1]);
							id.add((Integer) allPolutantsListData[4]);
						}
					}
				}
				for (int k = 0; k < pname.size(); k++)
				{
					mk = marks.get(k);
					marknewSUM += mk;
					percentile = consentQuan.get(k) * 10 / 100;
					fuzz = percentile + consentQuan.get(k);
					consentfuzz.add(fuzz);
				}

				for (int t = 0; t < marks.size(); t++)
				{
					mk = marks.get(t);
					temp = (mk / marknewSUM) * 100;// for each poll
					temp = Utilities.getFloatpoint(temp, 3);
					updatedMarks.add(temp);// updated marks
					consentdiv.add(consentfuzz.get(t) / 4);
				}

				for (int j = 0; j < pname.size(); j++)
				{
					factors.add(updatedMarks.get(j) / 5);
				}

				for (int j = 0; j < pname.size(); j++)
				{
					factordiv = factors.get(j) / consentdiv.get(j);
					factordiv = Utilities.getFloatpoint(factordiv, 3);
					mainFactor.add(factordiv);
				}

				// regular quantity
				for (int j = 0; j < pname.size(); j++)
				{
					reqQuantity = waterServices.getRegAmbientPollData(pname.get(j), month, year, id.get(j));
					//reson ambient
					if(reqQuantity > consentQuan.get(j)) {						
						overallnameReson +=  pname.get(j)+ ",";
						pollNameReson +=  pname.get(j)+ ",";
						
						}
					if (reqQuantity != null)
					{
						reqQuantityamb.add(reqQuantity);
					}
					else
					{
						reqQuantityamb.add(0.0f);
					}

				}
				// regularmarks
				for (int j = 0; j < consentfuzz.size(); j++)
				{
					finalfuzz = consentfuzz.get(j);
					finalRegular = reqQuantityamb.get(j);
					if (finalRegular >= finalfuzz)
					{
						regularMarks.add(updatedMarks.get(j));
					}
					else
					{
						regularMarks.add(mainFactor.get(j) * reqQuantityamb.get(j));
					}
				}

				for (int ii = 0; ii < regularMarks.size(); ii++)
				{
					mk1 = regularMarks.get(ii);
					finalVal += mk1;
					finalVal = Utilities.getFloatpoint(finalVal, 3);
					finalAmbient = finalVal;
				}

			}

			if (particulate == 1 && gases == 1)
			{
				finalStack = finalStackParticulate + finalStackGases;
			}
			else if (particulate == 1 && gases == 0)
			{
				finalStack = finalStackParticulate * 2;// 100
			}
			else if (particulate == 0 && gases == 1)
			{
				finalStack = finalStackGases * 2;// 100
			}
			else
			{
				finalStack = 0.0f;
			}

			if (stack == 1 && ambient == 1)
			{
				finalAmbient30 = finalAmbient * 30 / 100;
				finalStack70 = finalStack * 70 / 100;
				finalOverall = finalStack70 + finalAmbient30;
				air = finalOverall;
			}
			if (stack == 1 && ambient == 0)
			{
				finalAmbient = 0.0f;
				finalOverall = finalStack + finalAmbient;
				air = finalOverall;
			}
			if (stack == 0 && ambient == 1)
			{
				finalStack = 0.0f;
				finalOverall = finalStack + finalAmbient;
				air = finalOverall;
			}
			if(pollNameReson != null) {
				resonair += pollNameReson;
			}
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("resonair", resonair);
			hashMap.put("Typen", new String("combine")); // from Stack 70% & Ambient 30%
			hashMap.put("finalCombinedValue", new Float(Utilities.getFloatpoint(finalOverall, 2)));
			
			jsonArray.put(hashMap);

			HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
			hashMap1.put("Typen", new String("Stack"));
			hashMap1.put("finalStackValue", new Float(Utilities.getFloatpoint(finalStack, 2)));
			jsonArray.put(hashMap1);

			HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
			hashMap2.put("Typen", new String("Ambient"));
			hashMap2.put("finalAmbientValue", new Float(Utilities.getFloatpoint(finalAmbient, 2)));
			jsonArray.put(hashMap2);

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return jsonArray.toString();
	}  

	// hz
	@RequestMapping(value = {"ajax-overAllEnvPerformanceHz"})
	public @ResponseBody @JsonRawValue String overAllEnvPerformanceHz()
	{
		Float total = 0.0f;
		Float sum = 0.0f;
		Float finalAll = 0.0f;
		List<Float> FinalAllList = new ArrayList<>();
		Float finalValue = 0.0f;
		boolean status = false;
		float marks = 100.0f;
		int noOfType = 0;
		float newmarks = 0.0f;
		// list
		String type_p = null;

		String[] pType = {"hwp", "hwpcf", "nhwp", "nhwpcf"};
		JSONArray jsonArray;
		jsonArray = new JSONArray();

		try
		{
			String today = Utilities.getTodaysDate();
			int month = Utilities.getMonthFromStringDate(today);
			int year = Utilities.getYearFromStringDate(today);
			noOfType = allProductComparativeSheetServices.countType();
			newmarks = marks / noOfType;
			for (int i = 0; i < pType.length; i++)
			{
				String productName = null;
				Float conQuantity = 0.0f;
				float tempAll = 0.0f;
				float fuzz = 0.0f;
				float percentile = 0.0f;
				float updatedMarks = 0.0f;
				float marksDiv = 0.0f;
				int countTypeList = 0;
				float factor = 0.0f;
				float finalfuzz = 0.0f;
				Float reqQuantity = 0.0f;
				Float finalRegular = 0.0f;
				Float mk1 = 0.0f;
				List<String> pname = new ArrayList<>();
				Float finalVal = 0.0f;
				List<Float> consentQuan = new ArrayList<>();
				List<Float> consentFuzz = new ArrayList<>();
				List<Float> consentFuzzDiv = new ArrayList<>();
				List<Float> factordiv = new ArrayList<>();
				List<Object[]> allProductsLists = new ArrayList<>();
				List<Float> factor1 = new ArrayList<>();
				List<Float> regq = new ArrayList<>();
				List<Float> mainFactor = new ArrayList<>();
				List<Float> regularMarks = new ArrayList<>();
				allProductsLists = allProductComparativeSheetServices.ahpHazardous(pType[i], today);

				countTypeList = allProductsLists.size();
				updatedMarks = newmarks / countTypeList;
				marksDiv = updatedMarks / 5;
				if (allProductsLists != null)
				{
					for (Object[] allProductsListData : allProductsLists)
					{
						productName = (String) allProductsListData[0];
						conQuantity = (Float) allProductsListData[1];
						pname.add(productName);
						consentQuan.add(conQuantity);
					}
					for (int j = 0; j < consentQuan.size(); j++)
					{
						percentile = consentQuan.get(j) * 10 / 100;
						fuzz = percentile + consentQuan.get(j);
						consentFuzz.add(fuzz);
					}

					for (int j = 0; j < consentFuzz.size(); j++)
					{
						consentFuzzDiv.add(consentFuzz.get(j) / 4);
					}

					for (int k = 0; k < consentFuzzDiv.size(); k++)
					{
						factor = marksDiv / consentFuzzDiv.get(k);
						factor = Utilities.getFloatpoint(factor, 3);
						mainFactor.add(factor);

					}

					// regular quantity
					for (int j = 0; j < pname.size(); j++)
					{
						reqQuantity = regularDataServices.getAverageQuantityByPNameMonthYearForAhp(pname.get(j), month,
								year);
						///reson print
						 float cqty=consentQuan.get(j);
						//if(reqQuantity > consentQuan.get(j)) {//
						 if(reqQuantity > cqty) {
						overallnameReson +=  pname.get(j)+ ",";
						}
						///
						
						if (reqQuantity != null)
						{
							regq.add(reqQuantity);
						}
						else
						{
							regq.add(0.0f);
						}

					}
					// regularmarks
					for (int j = 0; j < pname.size(); j++)
					{
						finalfuzz = consentFuzz.get(j);
						finalRegular = regq.get(j);
						if (finalRegular >= finalfuzz)
						{
							regularMarks.add(updatedMarks);
						}
						else
						{
							regularMarks.add(mainFactor.get(j) * regq.get(j));
						}
					}

					for (int ii = 0; ii < regularMarks.size(); ii++)
					{

						mk1 = regularMarks.get(ii);
						finalVal += mk1;

					}
					finalAll = finalVal;
					FinalAllList.add(finalAll);
				}

			}
			for (int ii = 0; ii < FinalAllList.size(); ii++)
			{
				sum = FinalAllList.get(ii);
				total += sum;
				if (total != 0)
				{
					hazardous = total;
				}
				else
				{
					hazardous = 0;
				}

			}
			
			if (FinalAllList != null)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("All", new Float(Utilities.getFloatpoint(total, 2)));
				jsonArray.put(hashMap);
			}
			else
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("All", new Float(0.0f));
				jsonArray.put(hashMap);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
	}

	// all
	@RequestMapping(value = {"ajax-overAllEnvPerformanceAllFinal"})
	 @ResponseBody
	public @JsonRawValue String overAllEnvPerformanceFinal()
	{
		float water40 = 0.0f;
		float air40 = 0.0f;
		float hz20 = 0.0f;
		float marksFinal = 0.0f;
		float finalTotal = 0.0f;
		float wmarks = 0;
		float amarks = 0;
		float hzmarks = 0;
		
		//
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		try {
		//
		if (water != 0 && air != 0 && hazardous != 0)
		{

			marksFinal = 100;
			water40 = water * 40 / marksFinal;
			air40 = air * 40 / marksFinal;
			hz20 = hazardous * 20 / marksFinal;
			finalTotal = water40 + air40 + hz20;
		}
		else if (water != 0 && air != 0 && hazardous == 0)
		{
			marksFinal = 80;
			hz20 = 0;
			wmarks = 40 / marksFinal * 100;
			amarks = 40 / marksFinal * 100;
			water40 = water * wmarks / 100;
			air40 = air * amarks / 100;
			finalTotal = water40 + air40 + hz20;
		}
		else if (water != 0 && air == 0 && hazardous != 0)
		{
			marksFinal = 60;
			wmarks = 40 / marksFinal * 100;
			hzmarks = 20 / marksFinal * 100;
			hz20 = hazardous * hzmarks / 100;
			water40 = water * wmarks / 100;
			air40 = 0;
			finalTotal = water40 + air40 + hz20;
		}

		else if (water != 0 && air == 0 && hazardous == 0)
		{
			water40 = water;
			air40 = 0;
			hz20 = 0;
			finalTotal = water40 + air40 + hz20;
		}
		else if (water == 0 && air != 0 && hazardous != 0)
		{
			marksFinal = 60;
			water40 = 0;
			amarks = 40 / marksFinal * 100;
			hzmarks = 20 / marksFinal * 100;
			air40 = air * amarks / marksFinal;
			hz20 = hazardous * hzmarks / marksFinal;
			finalTotal = water40 + air40 + hz20;
		}
		else if (water == 0 && air == 0 && hazardous != 1)
		{
			water40 = 0;
			air40 = 0;
			hz20 = hazardous;
			finalTotal = water40 + air40 + hz20;
		}
		else
		{
			marksFinal = 0;
			water40 = 0;
			air40 = 0;
			hz20 = 0;
			finalTotal = water40 + air40 + hz20;
		}
		//m
		if(overallnameReson != null) {
			if(!awhreson.isEmpty()) {
				awhreson = "Cross the Consent Limit  ";
			}
			awhreson += overallnameReson;
		}
		//m
//		Float finalRes = Utilities.getFloatpoint(finalTotal, 2);

		///mm
		if (finalTotal != 0)
		{
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("awhreson", awhreson);
			hashMap.put("overAll", new Float(Utilities.getFloatpoint(finalTotal, 2)));
			jsonArray.put(hashMap);
		}
		else
		{
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("awhreson", awhreson);
			hashMap.put("overAll", new Float(Utilities.getFloatpoint(finalTotal, 2)));
			jsonArray.put(hashMap);
		}
		}
		///mm
	//	return finalRes.toString();
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return jsonArray.toString();
		
	}
	

}
