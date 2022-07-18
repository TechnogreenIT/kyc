package com.tes.controller.environmentalofficer.consent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tes.model.Consent;
import com.tes.model.Unit;
import com.tes.model.WaterSewPoll;
import com.tes.model.WaterSewPollOp;
import com.tes.model.WateriePollutant;
import com.tes.model.WateriePollutantOp;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.services.environmentalofficer.WaterSewPollOpServices;
import com.tes.services.environmentalofficer.WaterSewPollServices;
import com.tes.services.environmentalofficer.WateriePollutantOpServices;
import com.tes.services.environmentalofficer.WateriePollutantServices;
import com.tes.utilities.Constant;

/**
 * This class demonstrate used of the water pollutant detail.
 * This class perform the operation of add / view the water pollutant data.
 * 
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class WaterPollutantController
{

	@Autowired
	WateriePollutantServices wateriePollutantServices;

	@Autowired
	WaterSewPollServices waterSewPollServices;

	@Autowired
	WateriePollutantOpServices wateriePollutantOpServices;

	@Autowired
	WaterSewPollOpServices waterSewPollOpServices;

	@Autowired
	UnitServices unitServices;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to add the water pollutant data for sewage and effluent.
	 * 
	 * @param consentId the id of consent.
	 * @param effName the name of effluent.
	 * @param effQuantity the quantity of effluent.
	 * @param effUnit the unit of effluent.
	 * @param sewName the name of sewage.
	 * @param sewQuantity the quantity of sewage.
	 * @param sewUnit the unit of sewage.
	 * @return flag it return count according to number of row inserted
	 */
	@RequestMapping("/ajax-add-water-pollutant-data")
	public @ResponseBody int setWaterData(@RequestParam(value = "consent_no", required = false) int consentId,
			@RequestParam(value = "e_a_name", required = false) String[] effName,
			@RequestParam(value = "e_a_quantity", required = false) float[] effQuantity,
			@RequestParam(value = "e_a_unit", required = false) int[] effUnit,
			@RequestParam(value = "s_a_name", required = false) String[] sewName,
			@RequestParam(value = "s_a_quantity", required = false) float[] sewQuantity,
			@RequestParam(value = "s_a_unit", required = false) int[] sewUnit)
	{
		int flag = 0;
		try
		{
			Consent consent = new Consent();
			consent.setConsentId(consentId);
			int effPollutantDataSize = effName.length, effQuantitysDataSize = effQuantity.length, effUnitsDataSize = effUnit.length;
			for (int i = 0; i < effPollutantDataSize && i < effQuantitysDataSize && i < effUnitsDataSize; i++)
			{
				WateriePollutant wateriePollutant = new WateriePollutant();
				Unit objUnit = new Unit();
				objUnit.setUnitId(effUnit[i]);
				wateriePollutant.setConsent(consent);
				wateriePollutant.setPollName(effName[i]);
				wateriePollutant.setQuantity(effQuantity[i]);
				wateriePollutant.setUnit(objUnit);
				wateriePollutant.setConsentToOperate(Constant.NO);
				wateriePollutantServices.save(wateriePollutant);
				flag++;
			}
			int sewPollutantDataSize = sewName.length, sewQuantitysDataSize = sewQuantity.length, sewUnitsDataSize = sewUnit.length;
			for (int i = 0; i < sewPollutantDataSize && i < sewQuantitysDataSize && i < sewUnitsDataSize; i++)
			{
				WaterSewPoll waterSewPoll = new WaterSewPoll();
				Unit objUnit = new Unit();
				objUnit.setUnitId(sewUnit[i]);
				waterSewPoll.setConsent(consent);
				waterSewPoll.setPollName(sewName[i]);
				waterSewPoll.setQuantity(sewQuantity[i]);
				waterSewPoll.setUnit(objUnit);
				waterSewPoll.setConsentToOperate(Constant.NO);
				waterSewPollServices.save(waterSewPoll);
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
	 * This method used to edit and modify the list of water pollutant data in consent to operate.
	 * 
	 * @param action it return water pollutant data.
	 * @param consentId the consent id of consent.
	 * @param effPollutantId the id of effluent pollutant.
	 * @param sewPollutantId the id of sewage pollutant.
	 * @return flag it return count according to number of row inserted
	 */
	@RequestMapping("/ajax-edit-olist-water-pollutant-c2o")
	public @ResponseBody int setWaterPollutantForConsentToOperate(@RequestParam("action") String action,
			@RequestParam("consentNo") int consentId, @RequestParam("effPollutant") int[] effPollutantId,
			@RequestParam("sewPollutant") int[] sewPollutantId)
	{// action & consentNo is not need ........by vishal
		int flag = 0;
		Consent objConsentId = new Consent();
		try
		{
			objConsentId.setConsentId(consentId);
			for (int i = 0; i < effPollutantId.length; i++)
			{
				WateriePollutantOp objEffulantOp = new WateriePollutantOp(); // insreting data consent to oprate table..amin
				WateriePollutant objEffId = new WateriePollutant();
				objEffId.setWateriePollutantId(effPollutantId[i]);

				objEffulantOp.setConsent(objConsentId);
				objEffulantOp.setWateriePollutant(objEffId);
				wateriePollutantOpServices.save(objEffulantOp);

				// wateriePollutantServices.updateConsentToOperate(effPollutantId[i]);
				flag++;
			}
			for (int i = 0; i < sewPollutantId.length; i++)
			{
				WaterSewPollOp objSewageOp = new WaterSewPollOp();
				WaterSewPoll objSewId = new WaterSewPoll();
				objSewId.setWaterSewPollId(sewPollutantId[i]);

				objSewageOp.setConsent(objConsentId);
				objSewageOp.setWaterSewPoll(objSewId);
				waterSewPollOpServices.save(objSewageOp);

				// waterSewPollServices.updateConsentToOperate(sewPollutantId[i]);
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
	 * This method used to view the water pollutant details.
	 * 
	 * @param type the type of product.
	 * @param consentId the id of consent.
	 * @return wateriePollutantListDetails, waterSewPollListDetails, unit
	 */
	@RequestMapping("ajax-view-consent-view-water-pollutant-details")
	public @ResponseBody String getViewWaterPollutantDetails(@RequestParam("type") String type, @RequestParam("consent_no") int consentId)
	{
		JSONObject mainJsonObject = new JSONObject();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try
		{
			List<WateriePollutant> waterEffPollListDetails = new ArrayList<>();
			List<WaterSewPoll> waterSewPollListDetails = new ArrayList<>();
			if (type.equalsIgnoreCase("establish"))
			{
				waterEffPollListDetails = wateriePollutantServices.findByConsent(consentId);
				waterSewPollListDetails = waterSewPollServices.findByConsent(consentId);
			}
			else if (type.equalsIgnoreCase("operate"))
			{
				waterEffPollListDetails = wateriePollutantServices.findByConsentToOperateAndConsent(consentId);// consentId //need to change because cto id is not save in this table ....by vishal
				waterSewPollListDetails = waterSewPollServices.findByConsentToOperateAndConsent(consentId); // consentId need to change because cto id is not save in this table ....by vishal
			}

			if (waterEffPollListDetails.size() > 0)
			{
				JSONArray effPollsArray = new JSONArray();
				for (int i = 0; i < waterEffPollListDetails.size(); i++)
				{
					HashMap<String, Object> effPollHashMap = new HashMap<String, Object>();
					effPollHashMap.put("pollId", waterEffPollListDetails.get(i).getWateriePollutantId());
					effPollHashMap.put("pollName", waterEffPollListDetails.get(i).getPollName());
					effPollHashMap.put("pollQuantity", waterEffPollListDetails.get(i).getQuantity());
					effPollHashMap.put("pollUnit", waterEffPollListDetails.get(i).getUnit().getUnits());
					effPollsArray.put(effPollHashMap);
				}
				mainJsonObject.put("eff", effPollsArray);
			}

			if (waterSewPollListDetails.size() > 0)
			{
				JSONArray sewPollsArray = new JSONArray();
				for (int i = 0; i < waterSewPollListDetails.size(); i++)
				{
					HashMap<String, Object> sewPollHashMap = new HashMap<String, Object>();
					sewPollHashMap.put("pollId", waterSewPollListDetails.get(i).getWaterSewPollId());
					sewPollHashMap.put("pollName", waterSewPollListDetails.get(i).getPollName());
					sewPollHashMap.put("pollQuantity", waterSewPollListDetails.get(i).getQuantity());
					sewPollHashMap.put("pollUnit", waterSewPollListDetails.get(i).getUnit().getUnits());
					sewPollsArray.put(sewPollHashMap);
				}
				mainJsonObject.put("sew", sewPollsArray);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return mainJsonObject.toString();
	}
}
