package com.tes.controller.environmentalofficer.consent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tes.model.Consent;
import com.tes.model.WaterConGen;
import com.tes.model.WaterConGenComparativeSheet;
import com.tes.model.WaterConGenParameter;
import com.tes.services.environmentalofficer.WaterConGenComparativeSheetServices;
import com.tes.services.environmentalofficer.WaterConGenServices;
import com.tes.utilities.Constant;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

/**
 * This class demonstrate used of the water consumption and generation details.
 * This class perform the operation of add / view the water consumption and generation detail.
 * 
 * @author Vishal Gabani
 */
@Controller
@RequestMapping(value = {"/env"})
public class WaterConsumptionAndGenerationController
{

	@Autowired
	WaterConGenComparativeSheetServices waterConGenComparativeSheetServices;

	@Autowired
	WaterConGenServices waterConGenServices;

	private static final Logger LOGGER = LogManager.getLogger(ConsentController.class);

	/**
	 * This method used to add the data of water consumption and generation for consent to establish.
	 * 
	 * @param consentId the consent id
	 * @param wConGenPurpose the purpose of the water consumption and generation
	 * @param wCons the water consumption detail
	 * @param wGen the water generation detail
	 * @param wConGenUnit the unit of the water consumption and generation
	 * @return flag it return count acording to number of row inserted
	 */
	@RequestMapping("/ajax-add-watercongen") // Comparative Sheet ElistOfProduct
	public @ResponseBody int setWatercongen(@RequestParam(value = "consent_no", required = false) int consentId,
			@RequestParam(value = "a_purpose", required = false) String[] wConGenPurpose,
			@RequestParam(value = "a_consum", required = false) float[] wCons,
			@RequestParam(value = "a_gen", required = false) float[] wGen,
			@RequestParam(value = "a_unit", required = false, defaultValue = "CMD") String wConGenUnit)
	{// no need of CMD after comparative sheet ......by vishal
		Consent consent = new Consent();
		consent.setConsentId(consentId);
		int flag = 0;
		try
		{
			int wConGenPollutantDataSize = wConGenPurpose.length, wConGenConsumptionDataSize = wCons.length, wConGenDataSize = wGen.length;
			for (int i = 0; i < wConGenPollutantDataSize && i < wConGenConsumptionDataSize && i < wConGenDataSize; i++)
			{
				WaterConGen waterConGen = new WaterConGen();
				WaterConGenParameter objWaterConGenParameterId = new WaterConGenParameter();
				objWaterConGenParameterId.setWaterConGenParameterId(i + 1);
				waterConGen.setConsent(consent);
				waterConGen.setWaterConGenParameter(objWaterConGenParameterId);
				waterConGen.setCons(wCons[i]);
				waterConGen.setGen(wGen[i]);
				waterConGenServices.save(waterConGen);
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
	 * This method used to add the data of water consumption and generation for consent to operate.
	 * 
	 * @param action it return list of water consumption and generation data.
	 * @param consentId the consent id.
	 * @param purpose the purpose of the water consumption and generation.
	 * @param wCons the water consumption detail.
	 * @param wGen the water generation detail.
	 * @return flag it return count acording to number of row inserted
	 */
	@RequestMapping("/ajax-edit-olist-Water-cons-and-wastewater-gen-c2o") // Comparative Sheet OListOfProduct
	public @ResponseBody int setWaterConsAndWastewaterGenForCToO(@RequestParam("action") String action,
			@RequestParam("consentNo") int consentId, @RequestParam("purpose") String[] purpose,
			@RequestParam("consum") float[] wCons, @RequestParam("gen") float[] wGen)
	{// action is not need ........by
		// vishal
		int flag = 0;
		try
		{
			Consent objConsent = new Consent();
			objConsent.setConsentId(consentId);
			// change status old
			waterConGenComparativeSheetServices.setStatusInactiveToAll(Constant.ACTIVEINT);
			for (int i = 0; i < 5; i++)
			{
				WaterConGen objWaterConGen = new WaterConGen();
				WaterConGenParameter objWaterConGenParameterId = new WaterConGenParameter();
				objWaterConGenParameterId.setWaterConGenParameterId(i + 1);
				objWaterConGen.setConsent(objConsent);
				objWaterConGen.setWaterConGenParameter(objWaterConGenParameterId);
				objWaterConGen.setCons(wCons[i]);
				objWaterConGen.setGen(wGen[i]);
				waterConGenServices.save(objWaterConGen);

				// save to WaterConGenComparativeSheet
				WaterConGenComparativeSheet objWaterConGenComparativeSheet = new WaterConGenComparativeSheet();
				objWaterConGenComparativeSheet.setWaterConGen(objWaterConGen);
				objWaterConGenComparativeSheet.setCons(wCons[i]);
				objWaterConGenComparativeSheet.setConsBal(
						waterConGenServices.getConsByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(), i + 1)
								- waterConGenServices.sumOfConsByWaterConGenParameterIdAndCtO(Utilities.getTodaysDate(),
										i + 1));// bal of remaing
				objWaterConGenComparativeSheet.setGen(wGen[i]);
				objWaterConGenComparativeSheet.setGenBal(waterConGenServices
						.getGenByCtEAndWaterConGenParameterId(Utilities.getTodaysDate(), i + 1)
						- waterConGenServices.sumOfGenByWaterConGenParameterIdAndCtO(Utilities.getTodaysDate(),
								i + 1));// bal of remaing
				objWaterConGenComparativeSheet.setStatus(Constant.ACTIVEINT);
				waterConGenComparativeSheetServices.save(objWaterConGenComparativeSheet);
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
	 * This method used to view water consumption and generation data for ctoe and ctoo.
	 * 
	 * @param type the type of water.
	 * @param consentId the id of consent.
	 * @return ViewConGenDetails
	 */
	@RequestMapping("ajax-view-consent-view-con-gen-details") // Comparative Sheet
	public @ResponseBody ModelAndView getViewConGenDetails(@RequestParam("type") String type, @RequestParam("consent_no") int consentId)
	{
		ModelAndView modelAndView = new ModelAndView();
		double domesticCon = 0, biodegradableCon = 0, coolingCon = 0, otherCon = 0, nBiodegradableCon = 0;
		double domesticGen = 0, biodegradableGen = 0, coolingGen = 0, otherGen = 0, nBiodegradableGen = 0;
		try
		{
			// "Domestic", "Biodegradable", "Non Biodegradable", "Cooling", "Others";
			for (int i = 1; i <= 5; i++)
			{// this loop give error if there is no records is available in waterConGen(Consent to operate & Consent to Establish) ....by vishal
				float sumConsByWCGName = waterConGenServices.getConsByWaterConGenParameterIdAndconsentId(consentId, i);// getConsByStatusIsActiveAndWaterConGenParameterId(i);//waterConGenServices.sumOfCons(consentId,i);
				if (!Validator.isEmpty(sumConsByWCGName))
				{
					switch (i)
					{
						case 1:
							domesticCon = sumConsByWCGName;
							break;
						case 2:
							biodegradableCon = sumConsByWCGName;
							break;
						case 3:
							nBiodegradableCon = sumConsByWCGName;
							break;
						case 4:
							coolingCon = sumConsByWCGName;
							break;
						case 5:
							otherCon = sumConsByWCGName;
							break;
						default:
							System.out.println("Use Message Bundle ...by vishal");
					}
				}
				float sumGenByWCGName = waterConGenServices.getGenByWaterConGenParameterIdAndconsentId(consentId, i);// getGenByStatusIsActiveAndWaterConGenParameterId(i);
				if (!Validator.isEmpty(sumGenByWCGName))
				{
					switch (i)
					{
						case 1:
							domesticGen = sumGenByWCGName;
							break;
						case 2:
							biodegradableGen = sumGenByWCGName;
							break;
						case 3:
							nBiodegradableGen = sumGenByWCGName;
							break;
						case 4:
							coolingGen = sumGenByWCGName;
							break;
						case 5:
							otherGen = sumGenByWCGName;
							break;
						default:
							System.out.println("Use Message Bundle ...by vishal");
					}
				}
			}
			modelAndView.addObject("domesticCon", domesticCon);
			modelAndView.addObject("domesticGen", domesticGen);
			modelAndView.addObject("biodegradableCon", biodegradableCon);
			modelAndView.addObject("nBiodegradableCon", nBiodegradableCon);
			modelAndView.addObject("coolingCon", coolingCon);
			modelAndView.addObject("biodegradableGen", biodegradableGen);
			modelAndView.addObject("nBiodegradableGen", nBiodegradableGen);
			modelAndView.addObject("coolingGen", coolingGen);
			modelAndView.addObject("otherCon", otherCon);
			modelAndView.addObject("otherGen", otherGen);
			modelAndView.addObject("totalSewCon", domesticCon);
			modelAndView.addObject("totalSewGen", domesticGen);
			modelAndView.addObject("totalEffGen", biodegradableGen + nBiodegradableGen + coolingGen);
			modelAndView.addObject("totalEffCon", biodegradableCon + nBiodegradableCon + coolingCon);
			modelAndView.addObject("totalCon", domesticCon + biodegradableCon + nBiodegradableCon + coolingCon + otherCon);
			modelAndView.addObject("totalGen", domesticGen + biodegradableGen + nBiodegradableGen + coolingGen + otherGen);
			modelAndView.addObject("type", type);
			modelAndView.addObject("consentId", consentId);
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		modelAndView.setViewName("EnvrOfficer/ViewData/ViewConGenDetails");
		return modelAndView;
	}
}
