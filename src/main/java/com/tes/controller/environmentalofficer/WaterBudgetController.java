package com.tes.controller.environmentalofficer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.tes.controller.base.BaseEnvironmentOfficerController;
import com.tes.model.DirectUseOfWater;
import com.tes.model.FilterUse;
import com.tes.model.Filters;
import com.tes.model.MultipleFilter;
import com.tes.model.Prefilter;
import com.tes.model.RegWaterSourceData;
import com.tes.model.WastewaterRecycle;
import com.tes.model.WaterSource;
import com.tes.repository.environmentalofficer.waterinventory.MultipleFilterRepository;
import com.tes.services.environmentalofficer.waterinventory.DirectUseOfWaterServices;
import com.tes.services.environmentalofficer.waterinventory.FilterUseServices;
import com.tes.services.environmentalofficer.waterinventory.FiltersServices;
import com.tes.services.environmentalofficer.waterinventory.MultipleFilterServices;
import com.tes.services.environmentalofficer.waterinventory.PrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.RegDirectUseOfWaterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegFiltersUseDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegMultipleFilterDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegPrefilterServices;
import com.tes.services.environmentalofficer.waterinventory.RegWastewaterRecycleServices;
import com.tes.services.environmentalofficer.waterinventory.RegWaterSourceDataServices;
import com.tes.services.environmentalofficer.waterinventory.RegularTreatmentDataServices;
import com.tes.services.environmentalofficer.waterinventory.WasteWaterRecycleSevices;
import com.tes.services.environmentalofficer.waterinventory.WastewaterTreatmentServices;
import com.tes.services.environmentalofficer.waterinventory.WaterInventoryServices;
import com.tes.services.environmentalofficer.waterinventory.WaterSourceServices;
import com.tes.utilities.Utilities;
import com.tes.utilities.Validator;

@RestController
// @RequestMapping({"/env"})
public class WaterBudgetController extends BaseEnvironmentOfficerController
{

	@Autowired
	RegWaterSourceDataServices regWaterSourceDataServices;

	@Autowired
	PrefilterServices prefilterServices;

	@Autowired
	RegPrefilterServices regPrefilterServices;

	@Autowired
	WaterSourceServices waterSourceServices;

	@Autowired
	MultipleFilterServices multipleFilterServices;

	@Autowired
	MultipleFilterRepository multipleFilterRepository;

	@Autowired
	FilterUseServices filterUseServices;

	@Autowired
	FiltersServices filtersServices;

	@Autowired
	DirectUseOfWaterServices directUseOfWaterServices;

	@Autowired
	RegMultipleFilterDataServices regMultipleFilterDataServices;

	@Autowired
	RegFiltersUseDataServices regFiltersUseDataServices;

	@Autowired
	RegDirectUseOfWaterDataServices regDirectUseOfWaterData;

	@Autowired
	WastewaterTreatmentServices wastewaterTreatmentServices;

	@Autowired
	RegularTreatmentDataServices regularTreatmentDataServices;

	@Autowired
	WaterInventoryServices waterInventoryServices;

	@Autowired
	WasteWaterRecycleSevices wasteWaterRecycleSevices;

	@Autowired
	RegWastewaterRecycleServices regWastewaterRecycleServices;

	@Autowired
	RegWaterSourceDataServices regularSourceDataServices;

	// @PreAuthorize("hasAuthority('ROLE_ENVROFFICER')")
	// @Secured("ROLE_ENVROFFICER")
	@RequestMapping("/Water-budget")
	public ModelAndView getWaterBudgetTest()
	{
		ModelAndView modelAndView = new ModelAndView();
		List<Integer> regSourceYearsArrayList = new ArrayList<>();
		int regSourceDataMinYear = 0;
		int currentYear = Integer.parseInt(Utilities.getTodaysDate().split("-")[0]);

		try
		{
			regSourceDataMinYear = regularSourceDataServices.getRegSourceYearOfLastInput();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (regSourceDataMinYear == 0)
		{
			System.out.println("Year cant be zero");
		}
		else
		{
			int maxYearDiff = currentYear - regSourceDataMinYear;
			maxYearDiff = maxYearDiff + 1;
			for (int i = 1; i <= maxYearDiff; i++)
			{
				regSourceYearsArrayList.add(currentYear);
				currentYear = currentYear - 1;
			}
		}

		modelAndView.setViewName("EnvrOfficer/WaterBudget");
		modelAndView.addObject("regSourceYears", regSourceYearsArrayList);
		return modelAndView;
	}

	public static String CreateBoxAndTitleForPrefilter(String label, String title, float actualReading)
	{
		String finalString = label + "[labelType=\"html\" label=\"<span style='font-size:22px'> " + title
				+ "</span><br><span style='font-size:18px'>Prefilter actual reading=" + actualReading + " CMD</span>\"];";
		return finalString;
	}

	public static String CreateBoxAndTitleForRecycledTo(String label, String title, float actualReading)
	{
		String finalString = label + "[labelType=\"html\" label=\"<span style='font-size:22px'> " + title
				+ "</span><br><span style='font-size:18px'>recycledTo actual "
				+ "reading =" + actualReading + " CMD</span>\"];";
		return finalString;
	}

	public static String createListOfLabels(String treatLabels, float capacity, float actualReading, float energyReading)
	{
		String str1 = "<li style=\'font-size:18px\'>" + treatLabels + " capacity:" + capacity + " CMD</li>" +
				"<li style=\'font-size:18px\'>" + treatLabels + " actual reading:" + actualReading + " CMD</li>" +
				"<li style=\'font-size:18px\'>" + treatLabels + " energy reading :" + energyReading + " CMD</li>";
		return str1;
	}

	public static String createLabelList(String label, String treatType, String listString, float totalActualConsuption, float totalEncergyConsumption)
	{
		String finalString = label + "[labelType=\"html\" label=\"<span style='font-size:22px'> " + treatType
				+ "</span><hr><ul>" + listString + "</ul><hr><span style='font-size:22px'>Total Actual Water =" + totalActualConsuption +
				" CMD</span></br><span style='font-size:22px'>Total Energy Water=" + totalEncergyConsumption + " CMD</span>\"];";
		return finalString;
	}

	public static String CreateBoxAndTitleForFiltersAndFilterUse(String label, String title, float actualReading,
			float lossReading)
	{
		String finalString = label + " [labelType=\"html\" label=\"<span style='font-size:22px'> " + title
				+ "</span><br><span style='font-size:18px'>Input=" + actualReading
				+ "CMD </span><br><span style='font-size:18px'>Output=" + lossReading + " CMD</span>\"];";
		return finalString;
	}

	public static String createRelations(String rel1, String rel2)
	{
		String finalRelation = rel1 + " -> " + rel2 + ";";
		return finalRelation;

	}

	public static String createLossBox(String label, float waterLoss, float percentileLoss)
	{
		String finalString = label + "[labelType=\"html\" label=\"<span style='font-size:18px'>loss percentile="
				+ percentileLoss + "</span><br><span style='font-size:18px'>Loss @ " + waterLoss + " CMD</span>\"];";
		return finalString;
	}

	public static String TotalConsumptionBox(String label, String blockTitle, String sourcesNames, String temp2)
	{
		String myBlock = label + " [labelType=\"html\" label=\"<span style='font-size:32px'>" + blockTitle
				+ "</span></br> <span style='font-size:15px'>(" + sourcesNames
				+ ")</span> </br> <span style='font-size:15px'>Consented = 900 CMD</span> </br> <span style='font-size:15px'>"
				+ temp2 + " CMD</span>\"];";
		return myBlock;
	}

	public static Map<String, Float> getWaterLoss(Float availableWater, Float waterLoss)
	{
		Map<String, Float> lossValues = new HashMap<String, Float>();
		Float remainingLossValues = 0.0f;
		Float percentileVal = 0.0f;
		percentileVal = availableWater * waterLoss / 100;
		remainingLossValues = availableWater - percentileVal;
		lossValues.put("available", availableWater);
		lossValues.put("remaining", remainingLossValues);
		lossValues.put("waterLoss", waterLoss);
		lossValues.put("lossPercentile", percentileVal);
		return lossValues;
	}

	@RequestMapping("ajax-get-water-budget-data")
	public @ResponseBody @JsonRawValue String getGraphWaterDataRelations(@RequestParam(value = "sDate") String sDate)
	{
		int counter = 1;
		JSONObject jsonObjectFinal = new JSONObject();
		JSONArray jsonArray;
		jsonArray = new JSONArray();
		int flag0 = 0, flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0,
				flag9 = 0, flag10 = 0;
		/*
		 * flag 0 is for total water consumption flag1 is for prefilter; flag2 is for
		 * multifilter with prefilter; flag3 is for filter_use with prefilter; flag4 is
		 * for directUse; flag5 is for filters flag 6 is for multiFilters with filter;
		 * flag 7 is for filtrUse with multifilter ; flag 8 is for filters with prefiler
		 */

		// String sDate = "2020-02-24";
		ArrayList<String> relationList = new ArrayList<String>();
		ArrayList<String> labelsList = new ArrayList<String>();
		ArrayList<String> titalsList = new ArrayList<String>();
		List<RegWaterSourceData> regWaterSourceDataList = null;
		List<DirectUseOfWater> directUseOfWaterList = null;
		List<String> filtersList = null;
		List<MultipleFilter> multipleFilterList = null;
		List<FilterUse> filterUseList = null;
		List<FilterUse> filterUseListByMid = null;
		List<Filters> filterList = null;
		List<Filters> filterListByPid = null;
		boolean result = false;
		Float totalWaterConsumption = null;
		Float regQuantity = null;
		List<Object> data;
		data = new ArrayList<>();
		JSONObject labels = new JSONObject();
		JSONObject relations = new JSONObject();
		int waterInventoryId = waterInventoryServices.getWaterInventoryIdByConsent(sDate);
		List<String> treatTypeList = wastewaterTreatmentServices.getTreatmentTypeByWiId(waterInventoryId);
		int etpFlag = 0, stpFlag = 0;
		if (!Validator.isEmpty(treatTypeList))
		{
			for (int i = 0; i < treatTypeList.size(); i++)
			{
				if (treatTypeList.get(i).equalsIgnoreCase("ETP"))
				{
					etpFlag = 1;
				}
				if (treatTypeList.get(i).equalsIgnoreCase("STP"))
				{
					stpFlag = 1;
				}
			}
		}
		// prefilters
		List<Prefilter> preFiliterList = prefilterServices.getAllIdAndIsPrifilter();
		if (!Validator.isEmpty(preFiliterList))
		{
			flag1 = 1;

		}
		List<Integer> pIds = filterUseServices.findPidAll();
		if (!Validator.isEmpty(pIds))
		{
			for (int i = 0; i < pIds.size(); i++)
			{
				if (pIds.get(i) != null)
				{
					flag10 = 1;
				}
			}

		}

		// filters
		filterList = filtersServices.findAllFilters();
		if (!Validator.isEmpty(filterList))
		{
			flag5 = 1;
		}
		// multiFilter

		// directUse
		directUseOfWaterList = directUseOfWaterServices.getUsedDirectUseNameList();
		if (!Validator.isEmpty(directUseOfWaterList))
		{
			flag4 = 1;
		}
		// for water sources
		List<WaterSource> waterSourceData = new ArrayList<>();
		waterSourceData = waterSourceServices.getWaterSourceData(waterInventoryId);
		if (!Validator.isEmpty(waterSourceData))
		{
			flag0 = 1;
		}
		// filterUser all
		List<FilterUse> filterUseListAll = new ArrayList<>();
		filterUseListAll = filterUseServices.getFilterUseAll();
		if (!Validator.isEmpty(filterUseListAll))
		{
			flag9 = 1;

		}

		// TREATMENT
		if (!Validator.isEmpty(treatTypeList))
		{
			for (int i = 0; i < treatTypeList.size(); i++)
			{
				String listString = "";
				Float totalActual = 0.0f;
				Float totalEnergy = 0.0f;
				Float sumOfActualReading = 0.0f;
				Float sumOfEnergyReading = 0.0f;
				// String treatToken= Utilities.toAlphabetic(counter);
				String treatToken = treatTypeList.get(i).equalsIgnoreCase("ETP") ? "etp" : "stp";
				String treatType = treatTypeList.get(i).equalsIgnoreCase("ETP") ? "Effulent Treatment Plant"
						: "Sewage Treatment Plant";
				List<String> treatTypeLabel = wastewaterTreatmentServices.findAlltreatmentTypeLabel(treatTypeList.get(i));
				for (int j = 0; j < treatTypeLabel.size(); j++)
				{

					String treatLabel = treatTypeLabel.get(j);
					String treatLabelToken = treatTypeLabel.get(j) + j;
					int capacity = wastewaterTreatmentServices.findCapacityByLabel(treatLabel);
					Float actualReading = regularTreatmentDataServices.getActualReadingByDateAndTreatmentTypeLabel(sDate, treatLabel);
					Float energyReading = regularTreatmentDataServices.getEnergyReadingByDateAndTreatmentTypeLabel(sDate, treatLabel);
					if (actualReading == null)
					{
						actualReading = 0.0f;
					}
					if (energyReading == null)
					{
						energyReading = 0.0f;
					}
					sumOfActualReading = regularTreatmentDataServices.getActualReadingByDateAndTreatmentTypeLabel(sDate, treatLabel);
					sumOfEnergyReading = regularTreatmentDataServices.getEnergyReadingByDateAndTreatmentTypeLabel(sDate, treatLabel);
					totalActual = totalActual + sumOfActualReading;
					totalEnergy = totalEnergy + sumOfEnergyReading;
					String str = createListOfLabels(treatLabel, capacity, actualReading, energyReading);
					listString = listString + str;

					List<WastewaterRecycle> recycledToList = wasteWaterRecycleSevices.findAllWasteWaterRecycleBytreatLabel(treatTypeList.get(i), treatLabel);
					if (!Validator.isEmpty(recycledToList))
					{
						for (WastewaterRecycle recycledToData : recycledToList)
						{
							String recycledTo = recycledToData.getRecycledTo();
							String recycledToken = treatLabel + recycledTo;
							String useType = recycledToData.getUseType();
							Float quantity = recycledToData.getQuantity();
							// Float actualQuantity=regWastewaterRecycleServices.findActualReadingByRfDatetreatTypeAndrecycledTo(sDate, treatTypeList.get(i), recycledTo);
							Float actualQuantity = regWastewaterRecycleServices.findActualReadingByRfDateAndrecycledTo(sDate, recycledTo);
							if (actualQuantity == null)
							{
								actualQuantity = 0.0f;
							}
							String recycledToString = CreateBoxAndTitleForRecycledTo(recycledToken, recycledTo, actualQuantity);
							String recycledRelation = createRelations(treatToken, recycledToken);
							labelsList.add(recycledToString);
							relationList.add(recycledRelation);
						}
					}

					// use labels

				}
				String finalString = createLabelList(treatToken, treatType, listString, totalActual, totalEnergy);
				labelsList.add(finalString);
			}
		}

		if (flag0 == 1)
		{
			String sourcesNames = "";
			String label = Utilities.toAlphabetic(counter);
			String blockTitle = "Total Water Consumption";
			for (int j = 0; j < waterSourceData.size(); j++)
			{
				String temp = waterSourceData.get(j).getSourceName();
				sourcesNames = sourcesNames.concat(temp + " ");
			}
			totalWaterConsumption = regWaterSourceDataServices.getSumActualReadingByWaterInvId(waterInventoryId); // Added waterInventoryId instead of 1
			if (totalWaterConsumption == null)
			{
				totalWaterConsumption = 0.0f;
			}
			String temp2 = "Total consumed water =" + totalWaterConsumption;
			String myBlock = TotalConsumptionBox(label, blockTitle, sourcesNames, temp2);
			labelsList.add(myBlock);
			counter++;
			if (flag1 == 1)
			{
				for (Prefilter prefilters : preFiliterList)
				{
					int preFilterId = prefilters.getPrefilterId();
					String waterSource = null;
					waterSource = prefilters.getWaterSource().getSourceName();
					String preFilterLabel = Utilities.toAlphabetic(counter);
					boolean isPrefilter = prefilters.isPrefilter();
					Float actualReading = regPrefilterServices.getRegPrefilterByIdAndDate(preFilterId, sDate);
					if (actualReading == null)
					{
						actualReading = 0.0f;
					}
					multipleFilterList = multipleFilterServices.getAllFiltersIdAndLabelsByPrefilterId(preFilterId);
					if (!Validator.isEmpty(multipleFilterList))
					{
						flag2 = 1;
					}
					filterUseList = filterUseServices.getFilterUseByPreFilterId(preFilterId);
					if (!Validator.isEmpty(filterUseList))
					{
						flag3 = 1;
					}
					filterListByPid = filtersServices.findAllFiltersByPrefilterId(preFilterId);
					if (!Validator.isEmpty(filterListByPid))
					{
						flag8 = 1;
					}
					if (flag10 == 0)
					{
						String preFilterLblString = CreateBoxAndTitleForPrefilter(preFilterLabel, "Prefilter-" + waterSource, actualReading);
						labelsList.add(preFilterLblString);
						String tempRelation = createRelations("B", preFilterLabel);
						relationList.add(tempRelation);
						counter++;
					}

					// case where prefilter with direct use in filter use table (scenario->1)
					if (flag1 == 1 && flag2 == 0 && flag3 == 1 && flag8 == 0)
					{
						String preFilterLblString = CreateBoxAndTitleForPrefilter(preFilterLabel, "Prefilter-" + waterSource, actualReading);
						labelsList.add(preFilterLblString);
						String tempRelation = createRelations("B", preFilterLabel);
						relationList.add(tempRelation);
						counter++;
						for (FilterUse filterUse : filterUseList)
						{
							String fillterUseLabelToken = Utilities.toAlphabetic(counter);
							String filterUseName = filterUse.getFilterUseLabel();
							String filterType = filterUse.getFilterUseType();
							boolean isIndustrial = false;
							isIndustrial = filterUse.isIndustrial();
							Float waterLoss = 0.0f;
							Float actualWaterAval = 0.0f;
							Float remaingLossVal = 0.0f;
							Float percentileLoss = 0.0f;
							waterLoss = filterUse.getWaterLoss();
							actualWaterAval = regFiltersUseDataServices.sumOfActualReadingByFilterUseLabel(sDate,
									filterUseName);
							if (waterLoss == null)
							{
								waterLoss = 0.0f;
							}
							if (actualWaterAval == null)
							{
								actualWaterAval = 0.0f;
							}
							Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAval, waterLoss);
							remaingLossVal = getWaterLoss.get("remaining");
							percentileLoss = getWaterLoss.get("lossPercentile");
							String WaterLossString = createLossBox(fillterUseLabelToken + "loss", waterLoss,
									percentileLoss);
							String filterUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
									fillterUseLabelToken, filterUseName + "(" + filterType + ")", actualWaterAval,
									remaingLossVal);
							labelsList.add(filterUseNameLabelString);
							labelsList.add(WaterLossString);
							String relation = createRelations(preFilterLabel, fillterUseLabelToken);
							String lossRelation = createRelations(fillterUseLabelToken, fillterUseLabelToken + "loss");
							relationList.add(relation);
							relationList.add(lossRelation);
							if (isIndustrial == true && etpFlag == 1)
							{
								String treatmentRelation = createRelations(fillterUseLabelToken, "etp");
								relationList.add(treatmentRelation);
							}
							if (stpFlag == 1 && isIndustrial == false)
							{
								String treatmentRelation = createRelations(fillterUseLabelToken, "stp");
								relationList.add(treatmentRelation);
							}
							counter++;
						}
					}
					else if (flag1 == 1 && flag8 == 1 & flag10 == 0)
					{
						/*
						 * case where prefilter present with multifilter and also direct use,also multifilter
						 * can have another multifilter as filter use
						 * for ex. RO as Filter use for UF
						 * for scenario 5 & 6
						 */
						List<String> toComparefilterUseLabel = new ArrayList<>();
						HashMap<String, String> map = new HashMap<>();
						List<Map> listMap = new ArrayList<>();
						for (Filters filter : filterListByPid)
						{
							int filterId = filter.getFiltersId();
							String filterName = filter.getFilterType();
							String checkIsfilter = filterUseServices.isFilterByfiterType(filterName);
							multipleFilterList = multipleFilterServices.getAllFiltersIdAndLabelsByFilterId(filterId);
							if (!Validator.isEmpty(multipleFilterList))
							{
								flag6 = 1;
								for (MultipleFilter multipleFilter : multipleFilterList)
								{
									int multipleFilterId = multipleFilter.getMultipleFilterId();
									String multipleFilterLabelToken = Utilities.toAlphabetic(counter);
									String multipleFilterName = multipleFilter.getFilterLabel();
									Float waterLoss = 0.0f;
									Float actualWaterAval = 0.0f;
									Float remaingLossVal = 0.0f;
									Float percentileLoss = 0.0f;
									actualWaterAval = regMultipleFilterDataServices
											.getSumActualReadingByRfDateAndFilterName(sDate, multipleFilterName,
													filterName);
									waterLoss = multipleFilter.getWaterLoss();
									if (waterLoss == null)
									{
										waterLoss = 0.0f;
									}
									if (actualWaterAval == null)
									{
										actualWaterAval = 0.0f;
									}
									Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAval, waterLoss);
									remaingLossVal = getWaterLoss.get("remaining");
									percentileLoss = getWaterLoss.get("lossPercentile");
									String waterLossString = createLossBox(multipleFilterLabelToken + "Loss", waterLoss,
											percentileLoss);
									String multipleFilterLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
											multipleFilterLabelToken, multipleFilterName + "(" + filterName + ")",
											actualWaterAval, remaingLossVal);
									if (checkIsfilter == null)
									{
										labelsList.add(multipleFilterLabelString);
										labelsList.add(waterLossString);
										String relation = createRelations(preFilterLabel, multipleFilterLabelToken);
										String lossRelation = createRelations(multipleFilterLabelToken,
												multipleFilterLabelToken + "Loss");
										relationList.add(relation);
										relationList.add(lossRelation);
										counter++;
									}
									// work
									filterUseListByMid = filterUseServices
											.getFilterUseByMultiFilterId(multipleFilterId);
									if (!Validator.isEmpty(filterUseListByMid))
									{
										flag7 = 1;
										for (FilterUse filterUse : filterUseListByMid)
										{
											String fillterUseLabelToken = Utilities.toAlphabetic(counter);
											String filterUseName = filterUse.getFilterUseLabel();
											String filterUseType = filterUse.getFilterUseType();
											String temp = filterUse.getFilterUseLabel();
											int filterUseId = filterUse.getFilterUseId();
											List<FilterUse> isFilterList = new ArrayList<>();
											boolean isFilter = filterUse.isFilter();
											boolean isIndustrial = false;
											isIndustrial = filterUse.isIndustrial();
											Float waterLossFu = 0.0f;
											Float actualWaterAvalFu = 0.0f;
											Float remaingLossValFu = 0.0f;
											Float percentileLossFu = 0.0f;
											waterLossFu = filterUse.getWaterLoss();
											actualWaterAvalFu = regFiltersUseDataServices
													.sumOfActualReadingByFilterUseLabel(sDate, filterUseName);
											if (waterLossFu == null)
											{
												waterLossFu = 0.0f;
											}
											if (actualWaterAvalFu == null)
											{
												actualWaterAvalFu = 0.0f;
											}
											Map<String, Float> getFuWaterLoss = getWaterLoss(actualWaterAvalFu,
													waterLossFu);
											remaingLossValFu = getFuWaterLoss.get("remaining");
											percentileLossFu = getFuWaterLoss.get("lossPercentile");
											String WaterLossString = createLossBox(fillterUseLabelToken + "loss",
													waterLossFu, percentileLossFu);
											String filterUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
													fillterUseLabelToken, filterUseName + "(" + filterUseType + ")",
													actualWaterAvalFu, remaingLossValFu);
											if (checkIsfilter == null)
											{
												if (!toComparefilterUseLabel.contains(filterUseName))
												{
													labelsList.add(filterUseNameLabelString);
													labelsList.add(WaterLossString);
													String relation = createRelations(multipleFilterLabelToken,
															fillterUseLabelToken);
													String lossRelation = createRelations(fillterUseLabelToken,
															fillterUseLabelToken + "loss");
													relationList.add(relation);
													relationList.add(lossRelation);
													toComparefilterUseLabel.add(filterUseName);
													if (etpFlag == 1 && isIndustrial == true && isFilter == false)
													{
														String treatmentRelation = createRelations(fillterUseLabelToken, "etp");
														relationList.add(treatmentRelation);
													}
													if (stpFlag == 1 && isIndustrial == false && isFilter == false)
													{
														String treatmentRelation = createRelations(fillterUseLabelToken, "stp");
														relationList.add(treatmentRelation);
													}
													counter++;
													map.put(filterUseName, fillterUseLabelToken);
													map.put("filterUseLabel1", filterUseName);
													if (!listMap.contains(map))
													{
														listMap.add(map);
													}
												}
												else
												{
													for (Map<String, String> map1 : listMap)
													{
														for (Map.Entry<String, String> entry : map1.entrySet())
														{
															System.out
																	.println(entry.getKey() + " - " + entry.getValue());
															if (entry.getKey().equalsIgnoreCase(filterUseName))
															{
																String relationss = createRelations(
																		multipleFilterLabelToken, entry.getValue());
																relationList.add(relationss);
															}
														}
													}

												}

											}
											// isfilter
											if (isFilter)
											{
												List<String> filterUseLabel = filterUseServices
														.getFilterUseLabelByMfLabel(temp);

												for (int i = 0; i < filterUseLabel.size(); i++)
												{
													String fillterUseToken = Utilities.toAlphabetic(counter);
													Float waterLossFu1 = 0.0f;
													Float actualWaterAval1 = 0.0f;
													Float remaingLossValFu1 = 0.0f;
													Float percentileLossFu1 = 0.0f;
													boolean isIndustrial1 = false;
													isIndustrial1 = filterUseServices.checkIsIndustrial(filterUseLabel.get(i));
													actualWaterAval1 = regFiltersUseDataServices
															.sumOfActualReadingByFilterUseLabel(sDate,
																	filterUseLabel.get(i));
													waterLossFu1 = filterUseServices
															.getWaterLoss(filterUseLabel.get(i));
													if (waterLossFu1 == null)
													{
														waterLossFu1 = 0.0f;
													}
													if (actualWaterAval1 == null)
													{
														actualWaterAval1 = 0.0f;
													}
													Map<String, Float> getFuWaterLoss1 = getWaterLoss(actualWaterAval1,
															waterLossFu1);
													remaingLossValFu1 = getFuWaterLoss1.get("remaining");
													percentileLossFu1 = getFuWaterLoss1.get("lossPercentile");
													String waterLossStringFu = createLossBox(fillterUseToken + "loss",
															waterLossFu1, percentileLossFu1);

													String filterUseNameLabelString1 = CreateBoxAndTitleForFiltersAndFilterUse(
															fillterUseToken, filterUseLabel.get(i), actualWaterAval1,
															remaingLossValFu1);
													if (!toComparefilterUseLabel.contains(filterUseLabel.get(i)))
													{
														labelsList.add(filterUseNameLabelString1);
														labelsList.add(waterLossStringFu);
														String relation = createRelations(fillterUseLabelToken,
																fillterUseToken);
														String lossRelation1 = createRelations(fillterUseToken,
																fillterUseToken + "loss");
														relationList.add(relation);
														relationList.add(lossRelation1);
														toComparefilterUseLabel.add(filterUseLabel.get(i));

														map.put(filterUseLabel.get(i), fillterUseToken);
														map.put("filterUseLabel", filterUseLabel.get(i));
														if (!listMap.contains(map))
														{
															listMap.add(map);
														}
														if (etpFlag == 1 && isIndustrial1 == true)
														{
															String treatmentRelation = createRelations(fillterUseToken, "etp");
															relationList.add(treatmentRelation);
														}
														if (stpFlag == 1 && isIndustrial1 == false)
														{
															String treatmentRelation = createRelations(fillterUseToken, "stp");
															relationList.add(treatmentRelation);
														}
														counter++;

													}
													else
													{
														//
														for (Map<String, String> map1 : listMap)
														{
															for (Map.Entry<String, String> entry : map1.entrySet())
															{
																System.out.println(
																		entry.getKey() + " - " + entry.getValue());
																if (entry.getKey()
																		.equalsIgnoreCase(filterUseLabel.get(i)))
																{
																	String relationss = createRelations(
																			fillterUseLabelToken, entry.getValue());
																	relationList.add(relationss);
																}
															}
														}

													}

												}

											}
										}

									}

								}

							}

						} // end of filters

						// direct use
						if (flag4 == 1)
						{
							List<String> toCompareDirectUse = new ArrayList<>();
							for (DirectUseOfWater directUse : directUseOfWaterList)
							{
								String DirectUseLabelToken = Utilities.toAlphabetic(counter);
								String DirectUseName = directUse.getWhereToUse();
								Float waterLossFu = 0.0f;
								Float actualWaterAvalFu = 0.0f;
								Float remaingLossValFu = 0.0f;
								Float percentileLossFu = 0.0f;
								boolean isIndustrial = false;
								isIndustrial = directUse.isIndustrial();
								waterLossFu = directUse.getWaterLoss();
								actualWaterAvalFu = regDirectUseOfWaterData.getActualReadingByDateAndSourceType(sDate,
										DirectUseName);
								if (actualWaterAvalFu == null)
								{
									actualWaterAvalFu = 0.0f;
								}
								Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAvalFu, waterLossFu);
								remaingLossValFu = getWaterLoss.get("remaining");
								percentileLossFu = getWaterLoss.get("lossPercentile");
								String waterLossString = createLossBox(DirectUseLabelToken + "Loss", waterLossFu,
										percentileLossFu);
								String DirectUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
										DirectUseLabelToken, DirectUseName, actualWaterAvalFu, remaingLossValFu);
								if (!toCompareDirectUse.contains(DirectUseName))
								{
									labelsList.add(DirectUseNameLabelString);
									labelsList.add(waterLossString);
									String relation = createRelations("B", DirectUseLabelToken);
									String lossRelation = createRelations(DirectUseLabelToken,
											DirectUseLabelToken + "Loss");
									relationList.add(relation);
									relationList.add(lossRelation);
									if (etpFlag == 1 && isIndustrial == true)
									{
										String treatmentRelation = createRelations(DirectUseLabelToken, "etp");
										relationList.add(treatmentRelation);
									}
									if (stpFlag == 1 && isIndustrial == false)
									{
										String treatmentRelation = createRelations(DirectUseLabelToken, "stp");
										relationList.add(treatmentRelation);
									}
									counter++;
								}
							}

						}
					}

				}
				/* case scene 4 */
				if (flag1 == 1 && flag5 == 1 && flag10 == 1)
				{

					// case where filter_use present without the prefilters(scenario->3).
					// case where some filter_use present without the prefilters also some present with prefilter(scenario->4).
					List<String> toCompareList = new ArrayList<>();
					List<Map> listMap = new ArrayList<>();
					HashMap<String, String> map = new HashMap<>();
					for (Filters filter : filterList)
					{
						String filtersLabelToken = Utilities.toAlphabetic(counter);
						int filterId = filter.getFiltersId();
						String filterName = filter.getFilterType();
						multipleFilterList = multipleFilterServices.getAllFiltersIdAndLabelsByFilterId(filterId);

						if (!Validator.isEmpty(multipleFilterList))
						{
							for (MultipleFilter multipleFilter : multipleFilterList)
							{
								int multipleFilterId = multipleFilter.getMultipleFilterId();
								String multipleFilterLabelToken = Utilities.toAlphabetic(counter);
								String multipleFilterName = multipleFilter.getFilterLabel();
								Float waterLoss = 0.0f;
								Float actualWaterAval = 0.0f;
								Float remaingLossVal = 0.0f;
								Float percentileLoss = 0.0f;
								actualWaterAval = regMultipleFilterDataServices
										.getSumActualReadingByRfDateAndFilterName(sDate, multipleFilterName, filterName);
								waterLoss = multipleFilter.getWaterLoss();
								if (waterLoss == null)
								{
									waterLoss = 0.0f;
								}
								if (actualWaterAval == null)
								{
									actualWaterAval = 0.0f;
								}
								Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAval, waterLoss);
								remaingLossVal = getWaterLoss.get("remaining");
								percentileLoss = getWaterLoss.get("lossPercentile");
								String waterLossString = createLossBox(multipleFilterLabelToken + "Loss", waterLoss,
										percentileLoss);
								String multipleFilterLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
										multipleFilterLabelToken, multipleFilterName + "(" + filterName + ")",
										actualWaterAval, remaingLossVal);
								labelsList.add(multipleFilterLabelString);
								labelsList.add(waterLossString);
								String relation = createRelations("B", multipleFilterLabelToken);
								String lossRelation = createRelations(multipleFilterLabelToken,
										multipleFilterLabelToken + "Loss");
								relationList.add(relation);
								relationList.add(lossRelation);
								counter++;
								filterUseListByMid = filterUseServices.getFilterUseByMultiFilterId(multipleFilterId);
								if (!Validator.isEmpty(filterUseListByMid))
								{

									for (FilterUse filterUse : filterUseListByMid)
									{
										String fillterUseLabelToken = Utilities.toAlphabetic(counter);
										String filterUseName = filterUse.getFilterUseLabel();
										String filterUseType = filterUse.getFilterUseType();
										boolean isIndustrial = false;
										isIndustrial = filterUse.isIndustrial();
										Float waterLoss1 = 0.0f;
										Float actualWaterAval1 = 0.0f;
										Float remaingLossVal1 = 0.0f;
										Float percentileLoss1 = 0.0f;
										// actualWaterAval1=regMultipleFilterDataServices.getSumActualReadingByRfDateAndFilterName(sDate,
										// filterUseName, filterName);
										actualWaterAval1 = regFiltersUseDataServices
												.sumOfActualReadingByFilterUseLabel(sDate, filterUseName);
										waterLoss1 = filterUse.getWaterLoss();
										if (waterLoss1 == null)
										{
											waterLoss1 = 0.0f;
										}
										if (actualWaterAval1 == null)
										{
											actualWaterAval1 = 0.0f;
										}
										Map<String, Float> getWaterLoss1 = getWaterLoss(actualWaterAval1, waterLoss1);
										remaingLossVal1 = getWaterLoss1.get("remaining");
										percentileLoss1 = getWaterLoss1.get("lossPercentile");
										if (percentileLoss1 == null)
										{
											percentileLoss1 = 0.0f;
										}
										if (remaingLossVal1 == null)
										{
											remaingLossVal1 = 0.0f;
										}

										String filterUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
												fillterUseLabelToken, filterUseName + "(" + filterUseType + ")",
												actualWaterAval1, remaingLossVal1);
										String waterLossString1 = createLossBox(fillterUseLabelToken + "loss", waterLoss1,
												percentileLoss1);
										if (!toCompareList.contains(filterUseName))
										{
											toCompareList.add(filterUseName);
											map.put(filterUseName, fillterUseLabelToken);
											map.put("filterUseName", filterUseName);
											if (!listMap.contains(map))
											{
												listMap.add(map);
											}
											labelsList.add(filterUseNameLabelString);
											labelsList.add(waterLossString1);
											String relationss = createRelations(multipleFilterLabelToken,
													fillterUseLabelToken);
											String lossrelationss = createRelations(fillterUseLabelToken,
													fillterUseLabelToken + "loss");
											relationList.add(relationss);
											relationList.add(lossrelationss);
											if (etpFlag == 1 && isIndustrial == true)
											{
												String treatmentRelation = createRelations(fillterUseLabelToken, "etp");
												relationList.add(treatmentRelation);
											}
											if (stpFlag == 1 && isIndustrial == false)
											{
												String treatmentRelation = createRelations(fillterUseLabelToken, "stp");
												relationList.add(treatmentRelation);
											}
											counter++;
										}
										else
										{
											for (Map<String, String> map1 : listMap)
											{
												for (Map.Entry<String, String> entry : map1.entrySet())
												{
													// System.out.println(entry.getKey() + " - " + entry.getValue());
													if (entry.getKey().equalsIgnoreCase(filterUseName))
													{
														String relationss = createRelations(multipleFilterLabelToken,
																entry.getValue());
														relationList.add(relationss);
													}
												}
											}
											// if (map.get("filterUseName").x(filterUseName)) {
											// String relationss = createRelations(multipleFilterLabelToken,
											// map.get(filterUseName));
											// relationList.add(relationss);
											// }

										}

									}
								} // end filteruse

							}

						}

					} // end of filters

					// prefilters
					List<Integer> pidList = new ArrayList<>();
					pidList = filterUseServices.findPrefilterId();
					List<Prefilter> preFilterById = new ArrayList<>();
					for (int i = 0; i < pidList.size(); i++)
					{
						preFilterById = prefilterServices.getPrefiterById(pidList.get(i));
					}
					if (!Validator.isEmpty(preFilterById))
					{
						for (Prefilter prefilter : preFilterById)
						{
							String prefilterToken = Utilities.toAlphabetic(counter);
							String waterSource1 = prefilter.getWaterSource().getSourceName();
							int prefilterId = prefilter.getPrefilterId();
							Float actualReading1 = regPrefilterServices.getRegPrefilterByIdAndDate(prefilterId, sDate);
							if (actualReading1 == null)
							{
								actualReading1 = 0.0f;
							}
							String preFilterString = CreateBoxAndTitleForPrefilter(prefilterToken, "Prefilter" + waterSource1, actualReading1);
							labelsList.add(preFilterString);
							String relation = createRelations("B", prefilterToken);
							relationList.add(relation);
							counter++;
							List<FilterUse> filterUseByPrefilter = filterUseServices
									.findFilterUseByPrefilter(prefilterId);
							if (!Validator.isEmpty(filterUseByPrefilter))
							{
								for (FilterUse filterUse : filterUseByPrefilter)
								{
									String fillterUseLabelToken = Utilities.toAlphabetic(counter);
									String filterUseName = filterUse.getFilterUseLabel();
									String filterUseType = filterUse.getFilterUseType();
									boolean isIndustrial = false;
									isIndustrial = filterUse.isIndustrial();
									Float waterLoss = 0.0f;
									Float actualWaterAval = 0.0f;
									Float remaingLossVal = 0.0f;
									Float percentileLoss = 0.0f;
									// actualWaterAval=regMultipleFilterDataServices.getSumActualReadingByRfDateAndFilterName(sDate,
									// filterUseName, filterName);
									actualWaterAval = regFiltersUseDataServices
											.sumOfActualReadingByFilterUseLabel(sDate, filterUseName);
									waterLoss = filterUse.getWaterLoss();
									if (waterLoss == null)
									{
										waterLoss = 0.0f;
									}
									if (actualWaterAval == null)
									{
										actualWaterAval = 0.0f;
									}
									Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAval, waterLoss);
									remaingLossVal = getWaterLoss.get("remaining");
									percentileLoss = getWaterLoss.get("lossPercentile");
									String filterUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
											fillterUseLabelToken, filterUseName + "(" + filterUseType + ")",
											actualWaterAval, remaingLossVal);
									String waterLossString = createLossBox(fillterUseLabelToken + "loss", waterLoss,
											percentileLoss);
									if (!toCompareList.contains(filterUseName))
									{
										labelsList.add(filterUseNameLabelString);
										labelsList.add(waterLossString);
										String relationss = createRelations(prefilterToken, fillterUseLabelToken);
										String lossRelationss = createRelations(fillterUseLabelToken,
												fillterUseLabelToken + "loss");
										relationList.add(relationss);
										relationList.add(lossRelationss);
										if (etpFlag == 1 && isIndustrial == true)
										{
											String treatmentRelation = createRelations(fillterUseLabelToken, "etp");
											relationList.add(treatmentRelation);
										}
										if (stpFlag == 1 && isIndustrial == false)
										{
											String treatmentRelation = createRelations(fillterUseLabelToken, "stp");
											relationList.add(treatmentRelation);
										}
										counter++;
									}
									else
									{
										for (Map<String, String> map1 : listMap)
										{
											for (Map.Entry<String, String> entry : map1.entrySet())
											{
												// System.out.println(entry.getKey() + " - " + entry.getValue());
												if (entry.getKey().equalsIgnoreCase(filterUseName))
												{
													String relationss = createRelations(prefilterToken,
															entry.getValue());
													relationList.add(relationss);
												}
											}
										}
									}
								}
							}
						}
					}

					if (flag4 == 1)
					{
						List<String> toCompareDirectUse = new ArrayList<>();
						for (DirectUseOfWater directUse : directUseOfWaterList)
						{
							String DirectUseLabelToken = Utilities.toAlphabetic(counter);
							String DirectUseName = directUse.getWhereToUse();
							boolean isIndustrial = directUse.isIndustrial();
							Float waterLossFu = 0.0f;
							Float actualWaterAvalFu = 0.0f;
							Float remaingLossValFu = 0.0f;
							Float percentileLossFu = 0.0f;
							waterLossFu = directUse.getWaterLoss();
							actualWaterAvalFu = regDirectUseOfWaterData.getActualReadingByDateAndSourceType(sDate,
									DirectUseName);
							if (actualWaterAvalFu == null)
							{
								actualWaterAvalFu = 0.0f;
							}
							Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAvalFu, waterLossFu);
							remaingLossValFu = getWaterLoss.get("remaining");
							percentileLossFu = getWaterLoss.get("lossPercentile");
							String waterLossString = createLossBox(DirectUseLabelToken + "Loss", waterLossFu, percentileLossFu);
							String DirectUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(DirectUseLabelToken,
									DirectUseName, actualWaterAvalFu, remaingLossValFu);
							if (!toCompareDirectUse.contains(DirectUseName))
							{
								labelsList.add(DirectUseNameLabelString);
								labelsList.add(waterLossString);
								String relation = createRelations("B", DirectUseLabelToken);
								String lossRelation = createRelations(DirectUseLabelToken, DirectUseLabelToken + "Loss");
								relationList.add(relation);
								relationList.add(lossRelation);
								counter++;
							}
							if (etpFlag == 1 && isIndustrial == true)
							{
								String treatmentRelation = createRelations(DirectUseLabelToken, "etp");
								relationList.add(treatmentRelation);
							}
							if (stpFlag == 1 && isIndustrial == false)
							{
								String treatmentRelation = createRelations(DirectUseLabelToken, "stp");
								relationList.add(treatmentRelation);
							}

						}

					}
				}
			}
			else if (flag1 == 0 && flag5 == 1)
			{
				// case where filter_use present without the prefilters(scenario->3).
				// case where some filter_use present without the prefilters also some present with prefilter(scenario->4).
				List<String> toCompareList = new ArrayList<>();
				List<Map> listMap = new ArrayList<>();
				HashMap<String, String> map = new HashMap<>();
				for (Filters filter : filterList)
				{
					String filtersLabelToken = Utilities.toAlphabetic(counter);
					int filterId = filter.getFiltersId();
					String filterName = filter.getFilterType();
					multipleFilterList = multipleFilterServices.getAllFiltersIdAndLabelsByFilterId(filterId);

					if (!Validator.isEmpty(multipleFilterList))
					{
						for (MultipleFilter multipleFilter : multipleFilterList)
						{
							int multipleFilterId = multipleFilter.getMultipleFilterId();
							String multipleFilterLabelToken = Utilities.toAlphabetic(counter);
							String multipleFilterName = multipleFilter.getFilterLabel();
							Float waterLoss = 0.0f;
							Float actualWaterAval = 0.0f;
							Float remaingLossVal = 0.0f;
							Float percentileLoss = 0.0f;
							actualWaterAval = regMultipleFilterDataServices
									.getSumActualReadingByRfDateAndFilterName(sDate, multipleFilterName, filterName);
							waterLoss = multipleFilter.getWaterLoss();
							if (waterLoss == null)
							{
								waterLoss = 0.0f;
							}
							if (actualWaterAval == null)
							{
								actualWaterAval = 0.0f;
							}
							Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAval, waterLoss);
							remaingLossVal = getWaterLoss.get("remaining");
							percentileLoss = getWaterLoss.get("lossPercentile");
							String waterLossString = createLossBox(multipleFilterLabelToken + "Loss", waterLoss,
									percentileLoss);
							String multipleFilterLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
									multipleFilterLabelToken, multipleFilterName + "(" + filterName + ")",
									actualWaterAval, remaingLossVal);
							labelsList.add(multipleFilterLabelString);
							labelsList.add(waterLossString);
							String relation = createRelations("B", multipleFilterLabelToken);
							String lossRelation = createRelations(multipleFilterLabelToken,
									multipleFilterLabelToken + "Loss");
							relationList.add(relation);
							relationList.add(lossRelation);
							counter++;
							filterUseListByMid = filterUseServices.getFilterUseByMultiFilterId(multipleFilterId);
							if (!Validator.isEmpty(filterUseListByMid))
							{

								for (FilterUse filterUse : filterUseListByMid)
								{
									String fillterUseLabelToken = Utilities.toAlphabetic(counter);
									String filterUseName = filterUse.getFilterUseLabel();
									String filterUseType = filterUse.getFilterUseType();
									boolean isIndustrial = false;
									isIndustrial = filterUse.isIndustrial();
									Float waterLoss1 = 0.0f;
									Float actualWaterAval1 = 0.0f;
									Float remaingLossVal1 = 0.0f;
									Float percentileLoss1 = 0.0f;
									// actualWaterAval1=regMultipleFilterDataServices.getSumActualReadingByRfDateAndFilterName(sDate,
									// filterUseName, filterName);
									actualWaterAval1 = regFiltersUseDataServices
											.sumOfActualReadingByFilterUseLabel(sDate, filterUseName);
									waterLoss1 = filterUse.getWaterLoss();
									if (waterLoss1 == null)
									{
										waterLoss1 = 0.0f;
									}
									if (actualWaterAval1 == null)
									{
										actualWaterAval1 = 0.0f;
									}
									Map<String, Float> getWaterLoss1 = getWaterLoss(actualWaterAval1, waterLoss1);
									remaingLossVal1 = getWaterLoss1.get("remaining");
									percentileLoss1 = getWaterLoss1.get("lossPercentile");
									if (percentileLoss1 == null)
									{
										percentileLoss1 = 0.0f;
									}
									if (remaingLossVal1 == null)
									{
										remaingLossVal1 = 0.0f;
									}

									String filterUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(
											fillterUseLabelToken, filterUseName + "(" + filterUseType + ")",
											actualWaterAval1, remaingLossVal1);
									String waterLossString1 = createLossBox(fillterUseLabelToken + "loss", waterLoss1,
											percentileLoss1);
									if (!toCompareList.contains(filterUseName))
									{
										toCompareList.add(filterUseName);
										map.put(filterUseName, fillterUseLabelToken);
										map.put("filterUseName", filterUseName);
										if (!listMap.contains(map))
										{
											listMap.add(map);
										}
										labelsList.add(filterUseNameLabelString);
										labelsList.add(waterLossString1);
										String relationss = createRelations(multipleFilterLabelToken,
												fillterUseLabelToken);
										String lossrelationss = createRelations(fillterUseLabelToken,
												fillterUseLabelToken + "loss");
										relationList.add(relationss);
										relationList.add(lossrelationss);
										if (etpFlag == 1 && isIndustrial == true)
										{
											String treatmentRelation = createRelations(fillterUseLabelToken, "etp");
											relationList.add(treatmentRelation);
										}
										if (stpFlag == 1 && isIndustrial == false)
										{
											String treatmentRelation = createRelations(fillterUseLabelToken, "stp");
											relationList.add(treatmentRelation);
										}
										counter++;
									}
									else
									{
										for (Map<String, String> map1 : listMap)
										{
											for (Map.Entry<String, String> entry : map1.entrySet())
											{
												// System.out.println(entry.getKey() + " - " + entry.getValue());
												if (entry.getKey().equalsIgnoreCase(filterUseName))
												{
													String relationss = createRelations(multipleFilterLabelToken,
															entry.getValue());
													relationList.add(relationss);
												}
											}
										}
										// if (map.get("filterUseName").x(filterUseName)) {
										// String relationss = createRelations(multipleFilterLabelToken,
										// map.get(filterUseName));
										// relationList.add(relationss);
										// }

									}

								}
							} // end filteruse

						}

					}

				} // end of filters

				// case where source water to directuse with no prefilter,filter,filteruse(scenario-->2)
			}
			else if (flag4 == 1)
			{
				List<String> toCompareDirectUse = new ArrayList<>();
				for (DirectUseOfWater directUse : directUseOfWaterList)
				{
					String DirectUseLabelToken = Utilities.toAlphabetic(counter);
					String DirectUseName = directUse.getWhereToUse();
					boolean isIndustrial = directUse.isIndustrial();
					Float waterLossFu = 0.0f;
					Float actualWaterAvalFu = 0.0f;
					Float remaingLossValFu = 0.0f;
					Float percentileLossFu = 0.0f;
					waterLossFu = directUse.getWaterLoss();
					actualWaterAvalFu = regDirectUseOfWaterData.getActualReadingByDateAndSourceType(sDate,
							DirectUseName);
					if (actualWaterAvalFu == null)
					{
						actualWaterAvalFu = 0.0f;
					}
					Map<String, Float> getWaterLoss = getWaterLoss(actualWaterAvalFu, waterLossFu);
					remaingLossValFu = getWaterLoss.get("remaining");
					percentileLossFu = getWaterLoss.get("lossPercentile");
					String waterLossString = createLossBox(DirectUseLabelToken + "Loss", waterLossFu, percentileLossFu);
					String DirectUseNameLabelString = CreateBoxAndTitleForFiltersAndFilterUse(DirectUseLabelToken,
							DirectUseName, actualWaterAvalFu, remaingLossValFu);
					if (!toCompareDirectUse.contains(DirectUseName))
					{
						labelsList.add(DirectUseNameLabelString);
						labelsList.add(waterLossString);
						String relation = createRelations("B", DirectUseLabelToken);
						String lossRelation = createRelations(DirectUseLabelToken, DirectUseLabelToken + "Loss");
						relationList.add(relation);
						relationList.add(lossRelation);
						counter++;
					}
					if (etpFlag == 1 && isIndustrial == true)
					{
						String treatmentRelation = createRelations(DirectUseLabelToken, "etp");
						relationList.add(treatmentRelation);
					}
					if (stpFlag == 1 && isIndustrial == false)
					{
						String treatmentRelation = createRelations(DirectUseLabelToken, "stp");
						relationList.add(treatmentRelation);
					}

				}

			}

			if (!Validator.isEmpty(labelsList))
			{
				labels.put("labels", labelsList);
				data.add(labels);
			}

			if (!Validator.isEmpty(relationList))
			{
				relations.put("relations", relationList);
				data.add(relations);
			}

			if (!Validator.isEmpty(data))
			{
				result = true;
			}
			jsonObjectFinal.put("result", result);
			jsonObjectFinal.put("data", data);
			jsonArray.put(jsonObjectFinal);
			return jsonArray.toString();

		}
		else
		{
			jsonObjectFinal.put("NA", result);
			jsonObjectFinal.put("NA", data);
			jsonArray.put(jsonObjectFinal);
			return jsonArray.toString();
		}

	}

}
