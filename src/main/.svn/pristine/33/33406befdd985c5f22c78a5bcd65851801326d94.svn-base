/*
 * package com.tes.controller.management;
 * import java.time.LocalDateTime;
 * import java.time.format.DateTimeFormatter;
 * import java.util.ArrayList;
 * import java.util.Calendar;
 * import java.util.HashSet;
 * import java.util.Iterator;
 * import java.util.List;
 * import java.util.TreeSet;
 * import javax.servlet.http.HttpServletRequest;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Controller;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.servlet.ModelAndView;
 * import com.tes.model.AmbientPollData;
 * import com.tes.model.EmpData;
 * import com.tes.model.RegAmbientPoll;
 * import com.tes.model.RegEffPoll;
 * import com.tes.model.RegSewPoll;
 * import com.tes.model.RegStPoll;
 * import com.tes.model.StackPoll;
 * import com.tes.model.WaterSewPoll;
 * import com.tes.model.WateriePollutant;
 * import com.tes.services.environmentalofficer.AmbientServices;
 * import com.tes.services.environmentalofficer.WaterSewPollServices;
 * import com.tes.services.environmentalofficer.WateriePollutantServices;
 * import com.tes.services.thirdparty.AmbientPollDataServices;
 * import com.tes.services.thirdparty.RegAmbientPollServices;
 * import com.tes.services.thirdparty.RegEffPollServices;
 * import com.tes.services.thirdparty.RegSewPollServices;
 * import com.tes.services.thirdparty.RegStPollServices;
 * import com.tes.services.thirdparty.StackPollDataServices;
 * import com.tes.services.thirdparty.StackServices;
 * @Controller
 * public class ViewMonitoringController {
 * @Autowired
 * RegStPollServices regStPollServices;
 * @Autowired
 * RegAmbientPollServices regAmbientPollServices;
 * @Autowired
 * RegEffPollServices regEffPollServices;
 * @Autowired
 * RegSewPollServices regSewPollServices;
 * @Autowired
 * StackServices stackServices;
 * @Autowired
 * StackPollDataServices stackPollDataServices;
 * @Autowired
 * AmbientServices ambientServices;
 * @Autowired
 * AmbientPollDataServices ambientPollDataServices;
 * @Autowired
 * WateriePollutantServices wateriePollutantServices;
 * @Autowired
 * WaterSewPollServices waterSewPollServices;
 * @RequestMapping("management-view-monitoring-data")
 * public ModelAndView getMonitoringReports(HttpServletRequest request) {
 * int cnt=0,minArray=0;
 * TreeSet<Integer> tsYear=new TreeSet<Integer>();
 * TreeSet<String> tsStack=new TreeSet<String>();
 * TreeSet<String> tsAmbient=new TreeSet<String>();
 * TreeSet<String> tsEffluent=new TreeSet<String>();
 * TreeSet<String> tsSewage=new TreeSet<String>();
 * ArrayList<String> stackarraydate=new ArrayList<String>();
 * ArrayList<String> ambientarraydate=new ArrayList<String>();
 * ArrayList<String> effluentarraydate=new ArrayList<String>();
 * ArrayList<String> sewagearraydate=new ArrayList<String>();
 * ArrayList<String> stackyrmonth=new ArrayList<String>();
 * ArrayList<String> ambientyrmonth=new ArrayList<String>();
 * ArrayList<String> effluentyrmonth=new ArrayList<String>();
 * ArrayList<String> sewageyrmonth=new ArrayList<String>();
 * ModelAndView model=new ModelAndView("Management/ViewMonitoringReports");
 * ArrayList<StackPoll> arStackPollList = new ArrayList<StackPoll>();
 * ArrayList<RegStPoll> regStackArrayList=new ArrayList<RegStPoll>();
 * ArrayList<RegAmbientPoll> regAmbientArrayList=new ArrayList<RegAmbientPoll>();
 * ArrayList<RegEffPoll> regEffluentArrayList=new ArrayList<RegEffPoll>();
 * ArrayList<RegSewPoll> regSewageArrayList=new ArrayList<RegSewPoll>();
 * List<Integer> stackYear=regStPollServices.getYearFromStack();
 * tsYear.addAll(stackYear);
 * List<Integer> ambientYear=regAmbientPollServices.getYearFromAmbient();
 * tsYear.addAll(ambientYear);
 * List<Integer> effluentYear=regEffPollServices.getYearFromEffluent();
 * tsYear.addAll(effluentYear);
 * List<Integer> sewageYear=regSewPollServices.getYearFromSewage();
 * tsYear.addAll(sewageYear);
 * cnt=tsYear.size();
 * int today = Calendar.getInstance().get(Calendar.YEAR);
 * if(cnt>0) {
 * try {
 * minArray=tsYear.first();
 * } catch (Exception e) {
 * minArray=0;
 * }
 * }
 * EmpData empdata = (EmpData) request.getSession().getAttribute("empDataSession");
 * int companyId = empdata.getCompanyProfile().getCompanyProfileId();
 * String today1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
 * try {
 * //Stack module
 * for(int i=0;i<stackYear.size();i++) {
 * List<String> stackdate=regStPollServices.getDateFromStack(stackYear.get(i));
 * stackarraydate.addAll(stackdate);
 * System.out.println("Stack date="+stackdate);
 * }
 * for(int i=0;i<stackarraydate.size();i++) {
 * String arrSplit[] = (stackarraydate.get(i)).split("-");
 * tsStack.add(arrSplit[0]+"-"+arrSplit[1]);
 * }
 * stackyrmonth.addAll(tsStack);
 * try {
 * int[] stackId = stackServices.getStackId(today1, companyId);
 * if (stackId.length>0) {
 * for(int i=0;i<stackId.length;i++) {
 * for(int j=0;j<stackyrmonth.size();j++) {
 * String arrSplit[]=stackyrmonth.get(j).split("-");
 * int mon=Integer.parseInt(arrSplit[1]);
 * int yr=Integer.parseInt(arrSplit[0]);
 * //get all reg_st_poll data
 * List<RegStPoll> regStackInfo=regStPollServices.findRegStackInfoToShow(stackId[i], mon, yr);
 * if(!regStackInfo.isEmpty()) {
 * for(int t=0;t<regStackInfo.size();t++) {
 * int regStackId=regStackInfo.get(t).getRegStPollId();
 * String stackName=regStackInfo.get(t).getStackName();
 * String attachedTo=regStackInfo.get(t).getAttachTo();
 * RegStPoll rsp=new RegStPoll();
 * rsp=regStackInfo.get(t);
 * rsp.setStackPollData(stackPollDataServices.getStackPollData(stackName, attachedTo, mon, yr,regStackId));
 * regStackArrayList.add(rsp);
 * }
 * }
 * }
 * }
 * }
 * model.addObject("stackPoll", arStackPollList);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * //used in to show data for Stack
 * List<Integer> list=new ArrayList<Integer>();
 * Iterator<Integer> itr=tsYear.iterator();
 * model.addObject("RegPollutantYear",tsYear);
 * while(itr.hasNext()){
 * int c=itr.next();
 * list.add(c);
 * }
 * model.addObject("RegPollutantYear1",list);
 * model.addObject("stackDate", tsStack);
 * model.addObject("regStackList", regStackArrayList);
 * }
 * catch (Exception e) {
 * e.printStackTrace();
 * }
 * //Ambient module
 * try {
 * ArrayList<Integer> ambientIdArray=new ArrayList<Integer>();
 * for (int i = 0; i < ambientYear.size(); i++) {
 * List<String> ambientdate = regAmbientPollServices.getDateForAmbient(ambientYear.get(i));
 * ambientarraydate.addAll(ambientdate);
 * System.out.println("Ambient date=" + ambientdate);
 * for(int k=0;k<ambientarraydate.size();k++) {
 * String arrSplit[] = (ambientarraydate.get(k)).split("-");
 * tsAmbient.add(arrSplit[0] + "-" + arrSplit[1]);
 * }
 * }
 * ambientyrmonth.addAll(tsAmbient);
 * List<Integer> ambientId = ambientServices.getAmbientIdForMonitor(today1, companyId);
 * ambientIdArray.addAll(ambientId);
 * for (int i = 0; i < ambientId.size(); i++) {
 * List<Integer> regAmbientPollId=regAmbientPollServices.getRegAmbientId(ambientId.get(i));
 * int ambientid=ambientId.get(i).intValue();
 * for(int j=0;j<regAmbientPollId.size();j++) {
 * int regAmbientId=regAmbientPollId.get(0).intValue();
 * List<RegAmbientPoll> regambientdata = regAmbientPollServices.findByAmbientId(ambientid,regAmbientId);
 * RegAmbientPoll regAmb = new RegAmbientPoll();
 * regAmb = regambientdata.get(0);
 * List<AmbientPollData> ambientPollData=ambientPollDataServices.getAmbientPollData(regAmbientId);
 * regAmb.setAmbientPollData(ambientPollData);
 * regAmbientArrayList.add(regAmb);
 * }
 * }
 * model.addObject("RegAmbientData", regAmbientArrayList);
 * model.addObject("ambientdate", tsAmbient);
 * // used in Ajax to make accordian
 * model.addObject("YearArrayCount", cnt);
 * model.addObject("today", today);
 * model.addObject("minPollutant", minArray);
 * }
 * catch (Exception e) {
 * e.printStackTrace();
 * }
 * //WaterEff module
 * try {
 * for (int i = 0; i < effluentYear.size(); i++) {
 * List<String> effluentdate = regEffPollServices.getDateForEffluent(effluentYear.get(i));
 * effluentarraydate.addAll(effluentdate);
 * System.out.println("Effluent date=" + effluentdate);
 * String arrSplit[] = (effluentarraydate.get(i)).split("-");
 * tsEffluent.add(arrSplit[0] + "-" + arrSplit[1]);
 * int yr=Integer.parseInt(arrSplit[0]);
 * int mon=Integer.parseInt(arrSplit[1]);
 * HashSet<String> tspollname = new HashSet<String>();
 * List<WateriePollutant> waterpollutant= wateriePollutantServices.getwateriepollutantdata();
 * for(int k=0;k<waterpollutant.size();k++) {
 * String pollName=waterpollutant.get(k).getPollName();
 * tspollname.add(pollName);
 * }
 * for (String arrayElement : tspollname)
 * {
 * List<RegEffPoll> regeffpoll=regEffPollServices.getRegEffPollData(yr, mon, arrayElement);
 * regEffluentArrayList.addAll(regeffpoll);
 * }
 * }
 * effluentyrmonth.addAll(tsAmbient);
 * model.addObject("effluentdate", tsEffluent);
 * model.addObject("RegEffluentData", regEffluentArrayList);
 * }
 * catch(Exception e) {
 * e.printStackTrace();
 * }
 * //Sewage Module
 * try {
 * for (int i = 0; i < sewageYear.size(); i++) {
 * List<String> sewagedate = regSewPollServices.getDateFromSewage(sewageYear.get(i));
 * sewagearraydate.addAll(sewagedate);
 * String arrSplit[] = (sewagearraydate.get(i)).split("-");
 * tsSewage.add(arrSplit[0] + "-" + arrSplit[1]);
 * int yr=Integer.parseInt(arrSplit[0]);
 * int mon=Integer.parseInt(arrSplit[1]);
 * List<WaterSewPoll> watersewpollutant= waterSewPollServices.getWaterSewagePollutant();
 * for(int k=0;k<watersewpollutant.size();k++) {
 * String pollName=watersewpollutant.get(k).getPollName();
 * List<RegSewPoll> regsewagepoll=regSewPollServices.getRegSewagePollData(yr, mon, pollName);
 * regSewageArrayList.addAll(regsewagepoll);
 * }
 * }
 * sewageyrmonth.addAll(tsSewage);
 * model.addObject("RegSewageData", regSewageArrayList);
 * model.addObject("sewagedate", tsSewage);
 * }
 * catch(Exception e) {
 * e.printStackTrace();
 * }
 * return model;
 * }
 * }
 */
