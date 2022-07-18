package com.tes.controller.environmentalofficer.consent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TransactionRequiredException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tes.model.Consent;
import com.tes.model.Stack;
import com.tes.model.StackOp;
import com.tes.model.StackPoll;
import com.tes.model.Unit;
import com.tes.services.environmentalofficer.StackOpServices;
import com.tes.services.thirdparty.StackPollServices;
import com.tes.services.thirdparty.StackServices;

/**
 * This class demonstrate used of the stack details.
 * This class perform the operation of add / view the stack consent data.
 * 
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class StackController
{

	@Autowired
	StackServices stackServices;

	@Autowired
	StackPollServices stackPollServices;

	@Autowired
	StackOpServices stackOpServices;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to add the data of stack for consent to establish.
	 * 
	 * @param consentId the ID of consent.
	 * @param main it represent in stack data in array.
	 * @param aName the name of stack.
	 * @param aQuantity the quantity of stack.
	 * @param aUnit The unit of stack.
	 * @param request The servlet request we are processing.
	 * @return flag it return count acording to number of row inserted
	 */
	@RequestMapping(value = {"/ajax-add-stack-detail"})
	public @ResponseBody int setStackDetailData(@RequestParam(value = "consent_no", required = false) int consentId,
			@RequestParam(value = "main", required = false) String[] main,
			@RequestParam(value = "a_name", required = false) String[] aName,
			@RequestParam(value = "a_quantity", required = false) float[] aQuantity,
			@RequestParam(value = "a_unit", required = false) int[] aUnit, HttpServletRequest request)
	{
		int flag = 0;
		try
		{
			Stack stack = new Stack();
			Consent consent = new Consent();
			consent.setConsentId(consentId);
			stack.setConsent(consent);
			stack.setStackName(main[0]);
			stack.setAttachedTo(main[1]);
			stack.setCapacity(Float.parseFloat(main[2]));
			stack.setCapacityUnits(main[3]);
			stack.setMatCons(main[4]);
			stack.setShape(main[5]);
			stack.setFuelType(main[6]);
			stack.setHeight(Float.parseFloat(main[7]));
			stack.setHtUnits(main[8]);
			stack.setDiam(Float.parseFloat(main[9]));
			stack.setDiamUnits(main[10]);
			stack.setFuelQuant(Float.parseFloat(main[11]));
			stack.setFuelUnits(main[12]);// need to change
			stack.setApc(main[13]);
			stack.setApcSystem(main[14]);
			stackServices.save(stack);

			int pollutantDataSize = aName.length, quantitysDataSize = aQuantity.length, unitsDataSize = aUnit.length;
			for (int i = 0; i < pollutantDataSize && i < quantitysDataSize && i < unitsDataSize; i++)
			{
				StackPoll stackPoll = new StackPoll();
				Unit objUnitId = new Unit();
				objUnitId.setUnitId(aUnit[i]);
				stackPoll.setStack(stack);
				stackPoll.setPollName(aName[i]);
				stackPoll.setPollLimit(aQuantity[i]);
				stackPoll.setUnit(objUnitId);
				stackPollServices.save(stackPoll);
				flag++;
			}
		}
		catch (IllegalArgumentException se)
		{
			se.printStackTrace();
		}
		catch (EntityNotFoundException efe)
		{
			efe.printStackTrace();
		}
		catch (TransactionRequiredException tre)
		{
			tre.printStackTrace();
		}
		catch (IllegalStateException ise)
		{
			ise.printStackTrace();
		}
		catch (Exception ex)
		{
			LOGGER.error(ex);
		}
		return flag;
	}

	/**
	 * This method used to add stack details for consent to operate.
	 * 
	 * @param action stackfinish
	 * @param consentId the id of consent
	 * @param stackId the id of stack
	 * @return flag it return count acording to number of row inserted
	 */
	@RequestMapping("/ajax-edit-olist-stack-c2o")
	public @ResponseBody int setStackForConsentToOperate(@RequestParam("action") String action,
			@RequestParam("consentNo") int consentId, @RequestParam("stackId") int[] stackId)
	{// action & consentNo is not need ........by vishal
		int flag = 0;

		Consent objConsentId = new Consent();
		objConsentId.setConsentId(consentId);
		for (int i = 0; i < stackId.length; i++)
		{
			StackOp stackOp = new StackOp();
			Stack objStackId = new Stack();
			objStackId.setStackId(stackId[i]);

			stackOp.setConsent(objConsentId);
			stackOp.setStack(objStackId);
			stackOpServices.save(stackOp);

			// stackServices.updateConsentToOperate(stackId[i]);
			flag++;
		}
		return flag;
	}

	/**
	 * This method used to view stack data for ctoe and ctoo.
	 * 
	 * @param type the type of stack
	 * @param consentId the id of Consent
	 * @return stackDetail
	 */
	@RequestMapping("ajax-consent-view-get-stack-details")
	public @ResponseBody String getViewStackDetails(@RequestParam("type") String type,
			@RequestParam("consent_no") int consentId)
	{

		JSONArray mainJsonArray = new JSONArray();
		List<Stack> stackDetails = null, stackDetailList = new ArrayList<>();
		try
		{

			if (type.equalsIgnoreCase("establish"))
			{
				stackDetails = stackServices.findAllByConsNoOrderByASC(consentId);// order by is useless ......by vishal
			}
			else if (type.equalsIgnoreCase("operate"))
			{
				stackDetails = stackServices.findByconsentToOperate(consentId);// order by is useless ......by vishal//change the logic because consent id is not present....amin
			}
			for (int i = 0, size = stackDetails.size(); i < size; i++)
			{
				Stack objStackDetail = new Stack();
				objStackDetail = stackDetails.get(i);
				objStackDetail.setStackPollDetailList(stackPollServices.findByStackId(objStackDetail.getStackId()));
				stackDetailList.add(objStackDetail);
			}

			for (int i = 0; i < stackDetailList.size(); i++)
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("stackId", stackDetailList.get(i).getStackId());
				hashMap.put("stackName", stackDetailList.get(i).getStackName());
				hashMap.put("stackAttachedTo", stackDetailList.get(i).getAttachedTo());
				hashMap.put("stackCapacity", stackDetailList.get(i).getCapacity());
				hashMap.put("stackCapacityUnit", stackDetailList.get(i).getCapacityUnits());
				hashMap.put("stackFuelType", stackDetailList.get(i).getFuelType());
				hashMap.put("stackFuelQuant", stackDetailList.get(i).getFuelQuant());
				hashMap.put("stackFuelUnits", stackDetailList.get(i).getFuelUnits());
				hashMap.put("stackMatCons", stackDetailList.get(i).getMatCons());
				hashMap.put("stackShape", stackDetailList.get(i).getShape());
				hashMap.put("stackHeight", stackDetailList.get(i).getHeight());
				hashMap.put("stackHtUnits", stackDetailList.get(i).getHtUnits());
				hashMap.put("stackDiam", stackDetailList.get(i).getDiam());
				hashMap.put("stackDiamUnits", stackDetailList.get(i).getDiamUnits());

				List<StackPoll> stackPolls = new ArrayList<>();

				stackPolls = stackDetailList.get(i).getStackPollDetailList();
				JSONArray stackPollsArray = new JSONArray();
				for (int j = 0; j < stackPolls.size(); j++)
				{
					HashMap<String, Object> stackPollHashMap = new HashMap<String, Object>();
					stackPollHashMap.put("stackPollId", stackPolls.get(j).getStackPollId());
					stackPollHashMap.put("stackPollName", stackPolls.get(j).getPollName());
					stackPollHashMap.put("stackPollLimit", stackPolls.get(j).getPollLimit());
					stackPollHashMap.put("stackPollUnit", stackPolls.get(j).getUnit().getUnits());
					stackPollsArray.put(stackPollHashMap);
				}
				hashMap.put("stackPollDatas", stackPollsArray);

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
