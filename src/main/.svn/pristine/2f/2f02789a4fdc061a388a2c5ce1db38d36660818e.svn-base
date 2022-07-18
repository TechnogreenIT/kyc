package com.tes.controller.environmentalofficer.consent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tes.model.Ambient;
import com.tes.model.AmbientOp;
import com.tes.model.AmbientPoll;
import com.tes.model.Consent;
import com.tes.model.Unit;
import com.tes.services.environmentalofficer.AmbientOpServices;
import com.tes.services.environmentalofficer.AmbientPollServices;
import com.tes.services.environmentalofficer.AmbientServices;
import com.tes.services.environmentalofficer.UnitServices;
import com.tes.utilities.Constant;

/**
 * This class used to demonstrate used of the Ambient Consent details.
 * This class perform the operation of add / view the Ambient Consent details.
 * 
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class AmbientController
{

	@Autowired
	AmbientServices ambientServices;

	@Autowired
	AmbientPollServices ambientPollServices;

	@Autowired
	AmbientOpServices ambientOpServices;

	@Autowired
	UnitServices unitServices;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to Add the Data of Ambient for Consent to Establish
	 * 
	 * @param consentId The ID of the consent
	 * @param ambientLocIdent the ambient Id
	 * @param criteria The criteria of the ambient
	 * @param pollName The Name of the Pollutant
	 * @param limits The limits of the ambient
	 * @param units The unit of the ambient
	 * @return flag it return count acording to number of row inserted
	 */
	@RequestMapping("/ajax-add-ambient-data")
	public @ResponseBody int setAmbientData(@RequestParam(value = "consentId", required = false) int consentId,
			@RequestParam(value = "locIdent", required = false) String ambientLocIdent,
			@RequestParam(value = "criteria", required = false) String criteria,
			@RequestParam(value = "pollName", required = false) String[] pollName,
			@RequestParam(value = "limits", required = false) float[] limits,
			@RequestParam(value = "units", required = false) int[] units)
	{
		int flag = 0;
		try
		{
			Ambient ambient = new Ambient();
			Consent consent = new Consent();
			consent.setConsentId(consentId);

			ambient.setConsent(consent);
			ambient.setAmbientLocName(ambientLocIdent);
			ambient.setCriteria(criteria);
			ambient.setConsentToOperate(Constant.NO);
			ambientServices.save(ambient);

			int airPollutantDataSize = pollName.length;
			int airQuantitysDataSize = limits.length;
			int airUnitsDataSize = units.length;
			for (int i = 0; i < airPollutantDataSize && i < airQuantitysDataSize && i < airUnitsDataSize; i++)
			{
				AmbientPoll ambientPoll = new AmbientPoll();
				Unit objUnitId = new Unit();
				objUnitId.setUnitId(units[i]);
				ambientPoll.setAmbient(ambient);
				ambientPoll.setPollName(pollName[i]);
				ambientPoll.setLimits(limits[i]);
				ambientPoll.setUnit(objUnitId);
				ambientPollServices.save(ambientPoll);
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
	 * This method used to add the Data of Ambient for consent to operate.
	 * 
	 * @param consentId The ID of the consent
	 * @param ambientId The ID of the Ambient
	 * @return flag it return count acording to number of row inserted
	 */
	@RequestMapping("/ajax-edit-olist-ambient-c2o")
	public @ResponseBody int setAmbientForConsentToOperate(@RequestParam("consentId") int consentId,
			@RequestParam("ambientId") int[] ambientId)
	{
		int flag = 0;
		Consent objConsentId = new Consent();
		try
		{
			objConsentId.setConsentId(consentId);
			for (int i = 0, size = ambientId.length; i < size; i++)
			{
				AmbientOp objAmbientOp = new AmbientOp();
				Ambient objAmbientId = new Ambient();
				objAmbientId.setAmbientId(ambientId[i]);

				objAmbientOp.setConsent(objConsentId);
				objAmbientOp.setAmbient(objAmbientId);
				ambientOpServices.save(objAmbientOp);
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
	 * This method used to View Ambient data for Consent to Establish and Consent to Operate.
	 * 
	 * @param type The type of the ambient
	 * @param consentId The ID of the Consent
	 * @return ambientDetail, unit
	 */
	@RequestMapping("ajax-consent-view-get-ambient-details")
	public @ResponseBody String getAmbientViewAjax(@RequestParam("type") String type,
			@RequestParam("consentId") int consentId)
	{
		JSONArray mainJsonArray = new JSONArray();
		try
		{
			List<Ambient> ambientDetails = new ArrayList<>();
			;
			List<Ambient> ambientDetailList = new ArrayList<>();
			if (type.equalsIgnoreCase(Constant.ESTABLISH))
			{
				ambientDetails = ambientServices.findByConsentId(consentId);
				// #Data liquid order by is useless ......by vishal
			}
			else if (type.equalsIgnoreCase(Constant.OPERATE))
			{
				ambientDetails = ambientServices.findByconsentToOperateAndConsId(consentId);
				// #Data liquid order by is useless ......by vishal//change amin
			}
			for (int i = 0, size = ambientDetails.size(); i < size; i++)
			{
				Ambient ambientDetail = new Ambient();
				ambientDetail = ambientDetails.get(i);
				ambientDetail.setAmbientPollDetailList(ambientPollServices.findByAmbientId(ambientDetail.getAmbientId()));
				ambientDetailList.add(ambientDetail);
			}

			for (int i = 0; i < ambientDetailList.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("ambientId", ambientDetailList.get(i).getAmbientId());
				hashMap.put("ambientLocName", ambientDetailList.get(i).getAmbientLocName());
				hashMap.put("ambientCriteria", ambientDetailList.get(i).getCriteria());

				List<AmbientPoll> ambientPolls = new ArrayList<>();

				ambientPolls = ambientDetailList.get(i).getAmbientPollDetailList();
				JSONArray stackPollsArray = new JSONArray();
				for (int j = 0; j < ambientPolls.size(); j++)
				{
					HashMap<String, Object> stackPollHashMap = new HashMap<String, Object>();
					stackPollHashMap.put("ambientPollId", ambientPolls.get(j).getAmbientPollId());
					stackPollHashMap.put("ambientPollName", ambientPolls.get(j).getPollName());
					stackPollHashMap.put("ambientPollLimit", ambientPolls.get(j).getLimits());
					stackPollHashMap.put("ambientkPollUnit", ambientPolls.get(j).getUnit().getUnits());
					stackPollsArray.put(stackPollHashMap);
				}
				hashMap.put("ambientPollDatas", stackPollsArray);
				mainJsonArray.put(hashMap);
			}

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return mainJsonArray.toString();
	}
}
