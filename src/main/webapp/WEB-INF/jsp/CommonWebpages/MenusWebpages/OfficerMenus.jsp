<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="ct"%>
<%@ page isELIgnored="false"%>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page import="java.util.*"%>
<!-- OfficerMenus -->

<script>
function waterBudgetPopUP(){
	var fwd_url = "L";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		success : function(data) {
			var parseData = JSON.parse(data);
			var html = 	"<div class='modal fade' id='modalWindow' role='dialog'>"
			html += "<div class='modal-dialog modal-lg'>"
			html += "<div class='modal-content'>"
			html += "<div class='modal-header'>"
			html += "<button type='button' class='close' data-dismiss='modal'>&times;</button>"
			html += "<div class='col-sm-12'>"
				html += "<h4 class='modal-title'>SELECT CONSENT FOR WATER BUDGET REPORT<br></h4></div>"
				html += "</div>"
				html += "<div class='modal-body'>"
				html += "<div class='row'>"
				html += "<div class='col-sm-12'>"
				html += "<div class='col-sm-12'>"
				
					html += "<div class='form-group'>"
					html += " <select class='form-control' id='consentVal'>"
					html += "  <option value='selectcard'>select consent number</option>"
					
						 $.each(parseData, function(index, element) { 
							var consentNo = element.consentNo;
							var consentName = element.consentName;
							if(parseData != "NA"){
								html += "<option value='"+consentNo+"'>"+consentName+"</option>"
							} else {
								html += "<option value='selectcard'>You have no any consent to Operate</option>"
							}
						}); 
							
				html += " </select>"
		    	html += "</div>"
				html +=	"</div>"
				html +="</div>"
				html +="<div class='col-sm-12'>"
				html +=	"<center>"
				html +=	"<button type='button' class='btn btn-default' style='background-color:#9161cc;color:white;' data-dismiss='modal' onclick='javascript:return getWaterBudget();'>GET STATEMENT</button>"
				html +="</center>"
				html +="</div>"
				html +=	"</div>"
				html +="</div><br>"
				html +="<div class='modal-footer'>"
				html +="<div class='row'></div>"
				html +="</div>"
				html +="</div>"
				html +="</div>"
		    $("#idMyModalconsent").html(html);
		    $("#modalWindow").modal();
		}
	});
}
function makeMonthlyEsrPopUP() {
		var fwd_url = "ajax-getEsrPopUpValues";
		$.ajax({
			type : 'POST',
			url : fwd_url,
			success : function(data) {
				var parseData = JSON.parse(data);
				var html = 	"<div class='modal fade' id='modalWindow' role='dialog'>"
				html += "<div class='modal-dialog modal-lg'>"
				html += "<div class='modal-content'>"
				html += "<div class='modal-header'>"
				html += "<button type='button' class='close' data-dismiss='modal'>&times;</button>"
				html += "<div class='col-sm-12'>"
					html += "<h4 class='modal-title'>SELECT YEAR AND MONTH FOR ENVIRONMENTAL STATEMENT REPORT<br></h4></div>"
					html += "</div>"
					html += "<div class='modal-body'>"
					html += "<div class='row'>"
					html += "<div class='col-sm-12'>"
					html += "<div class='col-sm-6'>"
					
						html += "<div class='form-group'>"
						html += " <select class='form-control' id='month_year'>"
						html += "  <option value='selectcard'>Select Year</option>"
						
							$.each(parseData, function(index, element) { 
								var esrYear = element.esrYear;
								
								if(esrYear != "NA"){
									var res = esrYear.split("-");
									html += "<option value='"+esrYear+"'>April "+res[0]+" - March "+res[1]+"</option>"
								} else {
									html += "<option value='selectcard'>You have no any consent to Operate</option>"
								}
							});
								
					html += " </select>"
			    	html += "</div>"
					html +=	"</div>"
					html += "<div class='col-sm-6'>"
					
							html += "<div class='form-group'>"
							html += "<select class='form-control' id='month'>"
							html += "<option value='selectcard'>Select Month</option>"
							html += "<option value='04'>April</option>"
							html += "<option value='05'>May</option>"
							html += "<option value='06'>June</option>"
							html += "<option value='07'>July</option>"
							html += "<option value='08'>August</option>"
							html += "<option value='09'>September</option>"
							html += "<option value='10'>October</option>"
							html += "<option value='11'>November</option>"
							html += "<option value='12'>December</option>"
							html += "<option value='01'>January</option>"
							html += "<option value='02'>February</option>"
							html += "<option value='03'>March</option>"
						    html += "</select>"
					    	html += "</div>"
					    	
					html += "</div>"
					html +="</div>"
					html +="<div class='col-sm-12'>"
					html +=	"<center>"
					html +=	"<button type='button' class='btn btn-default' style='background-color:#9161cc;color:white;' data-dismiss='modal' onclick='javascript:return selectValidate();'>GET STATEMENT</button>"
					html +="</center>"
					html +="</div>"
					html +=	"</div>"
					html +="</div><br>"
					html +="<div class='modal-footer'>"
					html +="<div class='row'></div>"
					html +="</div>"
					html +="</div>"
					html +="</div>"
				$("#month_year").trigger("chosen:updated");
				$("#month").trigger("chosen:updated");
			    $("#idMyModal").html(html);
			    $("#modalWindow").modal();
				
			}
		});
}

function makeYearlyEsrPopUP() {
	var fwd_url = "ajax-getYearlyEsrValues";
	$.ajax({
		type : "POST",
		url : fwd_url,
		dataType : "json",
		success : function(data) {
			var parseData = JSON.parse(data);
			var html = 	"<div class='modal fade' id='esrYearlyModal' role='dialog'>"
				html += "<div class='modal-dialog modal-lg'>"
				html += "<div class='modal-content'>"
				html += "<div class='modal-header'>"
				html += "<button type='button' class='close' data-dismiss='modal'>&times;</button>"
				html += "<div class='col-sm-12'>"
					html += "<h4 class='modal-title'>SELECT YEAR FOR ENVIRONMENTAL STATEMENT REPORT<br></h4></div>"
					html += "</div>"
					html += "<div class='modal-body'>"
					html += "<div class='row'>"
					html += "<div class='col-sm-12'>"
					html += "<div class='col-sm-6'>"
					
						html += "<div class='form-group'>"
							html += " <select class='form-control' id='esr_year'>"
							html += "  <option value='selectcard'>Select Year</option>"
							
						var esrMinY = "";
						var esrMaxY = "";
						var yearArray=[];
						yearArray=parseData;
						$.each(parseData, function(index, element) { 
							var esrYear = element.esrYear;
							
							if(esrYear != "NA"){
								var res = esrYear.split("-");
								html += "<option value='"+esrYear+"'>April "+res[0]+" - March "+res[1]+"</option>"
							} else {
								html += "<option value='selectcard'>You have no any consent to Operate</option>"
							}
						});
					
					html += " </select>"
			    	html += "</div>"
			    	html += "</div>"
			    	html+="<div class='col-sm-2'></div>"
			    	html+="<div class='col-sm-3'>"
								html +=	"<button type='button' class='btn btn-default' style='background-color:#9161cc;color:white;' data-dismiss='modal' onclick='javascript:return selectESRValidateYear();'>GET STATEMENT</button>"
								html +="</div>"
								html +=	"</div>"
								html +=	"</div>"
								html +="</div><br>"
								html +="<div class='modal-footer'>"
								html +="<div class='row'></div>"
								html +="</div>"
								html +="</div>"
								html +="</div>"
								html +="</div>"
							$("#esr_year").trigger("chosen:updated");
						    $("#idMyModal").html(html);
						    $("#esrYearlyModal").modal();
                          }
	         });
}

//validation >> jemss....
function selectValidate() {
 var ddl1 = document.getElementById("month_year");
 var ddl2 = document.getElementById("month");
 var selectedValue1 = ddl1.options[ddl1.selectedIndex].value;
 var selectedValue2 = ddl2.options[ddl2.selectedIndex].value;
 var flag1 = 1;
 var flag2 = 1;
 
    if (selectedValue1 == "selectcard"){
    	document.getElementById("month_year").focus(); //set focus back to control
    	flag1 = 0;
    }
    else if (selectedValue2 == "selectcard"){
    	 document.getElementById("month").focus(); //set focus back to control
    	 flag2 = 0;
    }
    if (flag1 == 0 || flag2 == 0){
    	var message ="SELECT VALID ESR YEAR & MONTHS";
        
        notify("bottom", "right", "", "warning", "animated bounceInRight", "animated bounceOutRight", "Warning! ", message, 15000)
        //makeMonthlyEsrPopUP();
        return false;
    }
	if(flag2 == 1 && flag2 == 1){
	//	getWaste();
		 getMonthlyESRData();   
	}
}

function selectValidateYear() {
	 var ddl1 = document.getElementById("haz_year");
	 var selectedValue1 = ddl1.options[ddl1.selectedIndex].value;
	 var flag1 = 1;
	 var flag2 = 1;
	 
	    if (selectedValue1 == "selectcard"){
	    	document.getElementById("haz_year").focus(); //set focus back to control
	    	flag1 = 0;
	    }
	   
	    if (flag1 == 0 ){
	    	var message ="SELECT VALID YEAR & MONTHS";
	        
	        notify("bottom", "right", "", "warning", "animated bounceInRight", "animated bounceOutRight", "Warning! ", message, 15000)
	        return false;
	    }
		if(flag2 == 1 && flag2 == 1){
			getWaste();
		}
	}
function selectESRValidateYear() {
	 var ddl1 = document.getElementById("esr_year");
	 var selectedValue1 = ddl1.options[ddl1.selectedIndex].value;
	 var flag1 = 1;
	 var flag2 = 1;
	 
	    if (selectedValue1 == "selectcard"){
	    	document.getElementById("esr_year").focus(); //set focus back to control
	    	flag1 = 0;
	    }
	   
	    if (flag1 == 0 ){
	    	var message ="SELECT YEAR";
	        
	        notify("bottom", "right", "", "warning", "animated bounceInRight", "animated bounceOutRight", "Warning! ", message, 15000)
	        return false;
	    }
		if(flag2 == 1 && flag2 == 1){
			addESRData();
		}
	}
function getMonthlyESRData(){
	var year = document.getElementById("month_year").value;
	var month = document.getElementById("month").value;
	var encodedYear = btoa(year);
	var encodedMonth = btoa(month);
	window.location="monthly-esr?year="+encodedYear+"&month="+encodedMonth; /* envr-officer-esr-form */ 
}
function addESRData(){
	var year = document.getElementById("esr_year").value;
	var encodedYear = btoa(year);
	console.log("esr-year"+year);
	window.location="yearly-esr?year="+encodedYear;
}
function getWaterBudget(){
	var consent = document.getElementById("consentVal").value;
	//console.log("consent is "+consent);
	$.ajax({
		type : "POST",
		url : "water-budgetView?consent="+consent,
		dataType : 'json',
		success : function(data) {
			if(data=="Success"){
				window.open('getpdf1', '_blank');
			}else{
				alert("Sorry Data is Not Available!");
				//window.location="envrOfficerDesk";
			}
		}
	});
}
function getWaste(){
	var year = document.getElementById("haz_year").value;
	var encodedYear = btoa(year);
	console.log("year"+year);
	console.log("eyear"+encodedYear);
	window.location="envr-officer-hazardous-return?year="+encodedYear;
}
 function makeHazardousReturnPopUP(){
	var fwd_url = "ajax-getHazardousValues";
	$.ajax({
		type : "POST",
		url : fwd_url,
		dataType : "json",
		success : function(data) {
			var parseData = JSON.parse(data);
			console.log(parseData);
			var html = 	"<div class='modal fade' id='modalWindow1' role='dialog'>"
				html += "<div class='modal-dialog modal-lg'>"
				html += "<div class='modal-content'>"
				html += "<div class='modal-header'>"
				html += "<button type='button' class='close' data-dismiss='modal'>&times;</button>"
				html += "<div class='col-sm-12'>"
					html += "<h4 class='modal-title'>SELECT YEAR FOR HAZARDOUS RETURN<br></h4></div>"
					html += "</div>"
					html += "<div class='modal-body'>"
					html += "<div class='row'>"
					html += "<div class='col-sm-12'>"
					html += "<div class='col-sm-6'>"
					
						html += "<div class='form-group'>"
							html += " <select class='form-control' name='year' id='haz_year'>"
							html += "  <option value='selectcard'>Select Year</option>"
							
						var esrMinY = "";
						var esrMaxY = "";
						var yearArray=[];
						yearArray=parseData;
						$.each(parseData, function(index, element) { 
						    esrMinY = element.esrMinYear;
							console.log("miny>>>"+esrMinY);
							esrMaxY = element.esrMaxYear;
							 console.log("maxxx"+esrMaxY);
						//	console.log(">>>>>whole"+yearArray);
						});
						var a = esrMinY;
						var b = "";
						for(i = esrMinY ; i<=esrMaxY ;i++){
							b = a +1 ;
							html += "<option value='"+a+"-"+b+"'>April "+a+" - March "+b+"</option>"
							a = a +1;
						}
					
					html += " </select>"
			    	html += "</div>"
			    		html += "</div>"
			    	html+="<div class='col-sm-2'></div>"
			    	html+="<div class='col-sm-3'>"
								html +=	"<button type='button' class='btn btn-default' style='background-color:#9161cc;color:white;' data-dismiss='modal' onclick='javascript:return selectValidateYear();'>GET STATEMENT</button>"
								html +="</div>"
								html +=	"</div>"
								html +=	"</div>"
								html +="</div><br>"
								html +="<div class='modal-footer'>"
								html +="<div class='row'></div>"
								html +="</div>"
								html +="</div>"
								html +="</div>"
								html +="</div>"
							$("#haz_year").trigger("chosen:updated");
							//$("#month").trigger("chosen:updated");
						    $("#idMyModal").html(html);
						    $("#modalWindow1").modal();
                          }
	         });
	}
</script>
<%
ArrayList<String> urlstring=new ArrayList<String>();//Creating arraylist
ArrayList<String> urlencpt=new ArrayList<String>();//Creating arraylist'

urlstring.add("product"); //0
urlstring.add("byproduct"); //1'
urlstring.add("raw material"); //2
urlstring.add("fuel"); //3
urlstring.add("hwp"); //4
urlstring.add("hwpcf"); //5
urlstring.add("nhwp"); //6
urlstring.add("nhwpcf"); //7
urlstring.add("bio"); //8
urlstring.add("source"); //9
urlstring.add("filter"); //10
urlstring.add("filteruse"); //11
urlstring.add("useofwater"); //12

for(int i = 0; i < urlstring.size() ; i++)
{
	String name = urlstring.get(i);
    
	 byte[] bytesEncoded = Base64.encodeBase64(name.getBytes());
	 
	 urlencpt.add( new String(bytesEncoded));
}
%>
<aside id="sidebar" class="sidebar c-overflow">
	<%
					String dp = "";
					if (request.getSession().getAttribute("dp") != null) {
						dp = request.getSession().getAttribute("dp").toString();
					}
				%>
	<input type="hidden" name="year" value="${selectedYear }" />
	<div class="profile-menu"
		style="background: url('img/profileBackgroundImg/profile-menu${imgvalue}.png') no-repeat left top;background-size: 280px 137px;">
		<a href="#">
			<div class="profile-pic">

				<img
					src="data:image/jpeg;base64,<c:out value="${sessionScope.userProfilePic}"/>"
					id="user-profile-pic" alt="">
			</div>
			<div class="profile-info other-tooltip" data-toggle="tooltip"
				data-placement="bottom" data-html="true"
				title="Click here to View Your Profile ">
				<font style="text-transform: uppercase;">
					${empDataSession.getEmployeeName()} </font> <i
					class="zmdi zmdi-chevron-down"></i>
			</div>
		</a>

		<ul class="main-menu">

			<li><a href="user-profile"><font class="txtC" color=""><i
						class="zmdi zmdi-account"></i> View Profile</font></a></li>
			<li><a href="change-password"><font class="txtC"
					color="${settingdata.textColor }"><i
						class="zmdi zmdi-lock-open zmdi-hc-fw"></i> Change Password</font></a></li>
			<li><a href="settings"><font class="txtC" color=""><i
						class="zmdi zmdi-settings zmdi-hc-fw"></i> Settings</font></a></li>
		</ul>
	</div>

	<ul class="main-menu">
		<li class="active"><a href="envrOfficerDesk"> <i
				class="zmdi zmdi-home"></i> Home
		</a></li>
		<li><a href="view-company-profile"><i
				class="zmdi zmdi-balance zmdi-hc-fw"></i> View Company Profile</a></li>
		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-collection-text zmdi-hc-fw"></i> Consent</a>
			<ul>
				<li id="add_con"><a href="create-consent"><i
						class="zmdi zmdi-comment-edit zmdi-hc-fw"></i> Add</a></li>
				<li class="sub-menu"><a href="#"><i
						class="zmdi zmdi-view-list zmdi-hc-fw"></i> View</a>
					<ul>
						<li><a href="view-eData?msg=establish"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Consents to
								Establish</a></li>
						<li><a href="view-eData?msg=operate"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Consents to
								Operate</a></li>

					</ul></li>
			</ul></li>
		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-border-color zmdi-hc-fw"></i>Daily Input</a>
			<ul>
				<li data-toggle="tooltip" data-placement="bottom" data-html="true"
					class="other-tooltip" title="Click here to input daily data">

					<a href="regular-daily-data"><i
						class="zmdi zmdi-comment-edit zmdi-hc-fw"></i>Add</a>
				</li>
				<li data-toggle="tooltip" data-placement="bottom" data-html="true"
					class="other-tooltip" title="Click here to view daily data">
					<a href="envr-officer-view-regular-data"><i
						class="zmdi zmdi-view-list zmdi-hc-fw"></i> View</a></li>
				<li data-toggle="tooltip" data-placement="bottom" data-html="true"
					class="other-tooltip" title="Click here to view modified data">
					<a href="view-modified-data"><i
						class="zmdi zmdi-view-list zmdi-hc-fw"></i> View Modified Data</a>
				</li>
			</ul></li>
		<li><a href="view-monitoring-reports"><i
				class="zmdi zmdi-file zmdi-hc-fw"></i>View Monitoring Data</a></li>
		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-file-text zmdi-hc-fw"></i>Compliance Forms</a>
			<ul>
				<!-- <li><a href="comparative-sheet"><i class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Comparative Sheet</a></li> -->
				<li class="sub-menu"><a href="#"><i
						class="zmdi zmdi-file-text zmdi-hc-fw"></i>Environmental Statement
						Report(ESR)</a>
					<ul>
						<li><a onclick="javascript:makeMonthlyEsrPopUP()"
							style="cursor: pointer;"> <i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Monthly
						</a></li>
						<li><a onclick="javascript:makeYearlyEsrPopUP()"
							style="cursor: pointer;"> <i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Yearly
						</a></li>
					</ul></li>
				<li><a onclick="javascript:waterBudgetPopUP()"
					style="cursor: pointer;"> <i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Water Budget
				</a></li>
				<li class="sub-menu"><a href="#"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i>Hazardous Waste
						Manifest</a>
					<ul>

						<li><a href="HWManifest-form"><i
								class="zmdi zmdi-plus-circle-o zmdi-hc-fw"></i>Add</a></li>
						<li><a href="view-manifest"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> View</a></li>

					</ul></li>

				<li><a href="javascript:makeHazardousReturnPopUP();"
					style="cursor: pointer;"> <i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Return
						Form
				</a></li>
			</ul></li>

		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-trending-up zmdi-hc-fw"></i>Statistics</a>
			<ul>
				<c:if test="${sessionIndustryType == 'Industry'}">
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(0)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Products</a></li>
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(1)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Byproducts</a></li>
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(2)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Raw Materials</a></li>
				</c:if>

				<li><a href="statistics?statisticsfor=<%=urlencpt.get(3)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Fuel</a></li>
				<li><a href="statistics?statisticsfor=<%=urlencpt.get(4)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
						From Process</a></li>
				<li><a href="statistics?statisticsfor=<%=urlencpt.get(5)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
						From PCF</a></li>
				<li><a href="statistics?statisticsfor=<%=urlencpt.get(6)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
						Waste From Process</a></li>
				<li><a href="statistics?statisticsfor=<%=urlencpt.get(7)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
						Waste From PCF</a></li>

				<c:if test="${sessionIndustryType == 'Hospital'}">
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(8)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Bio-Medical Waste</a></li>
				</c:if>
			</ul></li>

		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-trending-up zmdi-hc-fw"></i>Water Statistics</a>
			<ul>
				<li><a href="waterConsumption?wc=<%=urlencpt.get(9)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Source</a></li>
				<li><a href="waterConsumption?wc=<%=urlencpt.get(10)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Filter</a></li>
				<li><a href="waterConsumption?wc=<%=urlencpt.get(11)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Filter Use </a></li>
				<li><a href="waterConsumption?wc=<%=urlencpt.get(12)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Use Of Water</a></li>

			</ul></li>

		<li><a href="view-report"><i
				class="zmdi zmdi-chart zmdi-hc-fw"></i>Graphs</a></li>

		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-assignment-check zmdi-hc-fw"></i>Performance</a>
			<ul>
				<c:if test="${sessionIndustryType == 'Industry'}">
					<li><a
						href="envr-officer-performance?performancefor=<%=urlencpt.get(0)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Products</a></li>
					<li><a
						href="envr-officer-performance?performancefor=<%=urlencpt.get(1)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Byproducts</a></li>
					<li><a
						href="envr-officer-performance?performancefor=<%=urlencpt.get(2)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Raw Materials</a></li>
				</c:if>
				<li><a
					href="envr-officer-performance?performancefor=<%=urlencpt.get(3)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Fuel</a></li>
				<li><a
					href="envr-officer-performance?performancefor=<%=urlencpt.get(4)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
						From Process</a></li>
				<li><a
					href="envr-officer-performance?performancefor=<%=urlencpt.get(5)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
						From PCF</a></li>
				<li><a
					href="envr-officer-performance?performancefor=<%=urlencpt.get(6)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
						Waste From Process</a></li>
				<li><a
					href="envr-officer-performance?performancefor=<%=urlencpt.get(7)%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
						Waste From PCF</a></li>

				<c:if test="${sessionIndustryType == 'Hospital'}">
					<li><a
						href="envr-officer-performance?performancefor=<%=urlencpt.get(8)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Bio-Medical Waste</a></li>
				</c:if>

				<!--cmnt by pallavi
				<li><a href="water-performance"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Water Performance</a></li>-->
				<!-- <li><a href="wasteWater.php"><i class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Wastewater Generation</a></li> -->

			</ul></li>

		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-trending-up zmdi-hc-fw"></i>Environment Performance</a>
			<ul>
				<li><a href="env-perormance?perormanceFor=water"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Water</a></li>
				<li><a href="env-perormance?perormanceFor=air"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Air</a></li>
				<li><a href="env-perormance?perormanceFor=hz"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> HZ</a></li>

			</ul></li>

		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-assignment-check zmdi-hc-fw"></i>Overall
				Environmental Performance</a>
			<ul>
				<li><a href="waterOverall?type=<%="water"%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Water</a></li>
				<li><a href="waterOverall?type=<%="air"%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Air</a></li>
				<li><a href="waterOverall?type=<%="hazardous"%>"><i
						class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous</a></li>

			</ul></li>
	</ul>
</aside>


<div id="idMyModal"></div>
<div id="idMyModalconsent"></div>

<div class="modal fade" id="esrYearly" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class='col-sm-12'>
					<h4 class="modal-title">
						SELECT YEAR FOR ENVIRONMENTAL STATEMENT REPORT<br>
					</h4>
				</div>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class='col-sm-12'>
						<div class='col-sm-6'></div>
						<div class='col-sm-2'></div>
						<div class='col-sm-3'>
							<button type="button" class='btn btn-default'
								style='margin-left: 85px; background-color: #9161cc; color: white;'
								data-dismiss='modal' onclick='javascript:addESRData();'>GET
								STATEMENT</button>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="modal-footer">
				<div class="row"></div>
			</div>
		</div>
	</div>
</div>
