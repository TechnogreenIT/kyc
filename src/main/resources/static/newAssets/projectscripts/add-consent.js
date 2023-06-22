
$(document).ready(function () {
	console.log(" add consent js loaded");		
	var issue_date = "" ; var valid_upto ="";
	var industryCat = $("#industryCat").val();	
});
var amalgamationCount = 0;


function showForm(id) {
	console.log(id);
	
	if(id == 'EYes'){
		/*action = "";
		document.getElementById("consentType").innerHTML = "<label class='text-black'>Consent Type: Consent to Establish</label>"
															+"<input type='hidden' class='form-control' readonly name='consType' "
															+"value='Consent to Establish'>";
		$("#consentform").show();
		checkEstablish("Consent to Establish");										
		$("#que2").hide();
		$("#que3").hide();̥
		$("#que4").hide();*/
		$('#que2').css('display', 'none');
		$('#que3').css('display', 'none');
		$('#que4').css('display', 'none');
		$("#consentform").empty();
		getConsentToEstablishForm('Consent to Establish');
	} else if (id == 'ExYes') {
		$("#consentform").empty();
		getConsentToEstablishForm('Consent to Establish');
		$('#que3').css('display', 'none');
		$('#que4').css('display', 'none');
	} else if (id == 'ENo') {
		$("#consentform").empty();
		$('#que2').css('display', 'block');
	} else if( id == 'ExNo'){
		$("#consentform").empty();
		$('#que3').css('display', 'block');
	}else if(id == 'MultipleYes' || id == 'MultipleNo'){
		$("#consentform").empty();
		$('#que4').css('display', 'block');
	} else if( id == 'expandOPNo'){
		$("#consentform").empty();
		// open operarte
		getConsentToEstablishForm('Consent to Operate');
	} else if( id == 'expandOPYes'){
		$("#consentform").empty();
		
		getAmalgamationOpForm();
	}else {
		$("#consentform").empty();
	}
	 
}

function validateConsentDates(datepickerId){
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; //January is 01  .replace('dd', date.getDate());
	var yyyy = today.getFullYear();
	if(dd<10) 
	{
	    dd='0'+dd;
	} 

	if(mm<10) 
	{
	    mm='0'+mm;
	} 
	var today_date = yyyy + "-" + mm + "-" + dd;
	var validdt= $("#valid_upto").val();
	var isdt = $("#issue_date").val();
	var ecdt = $("#ecvalid_upto").val();
	
	var datepickerDate = $('#'+datepickerId).val();
	//
	var ecvaliddt = $("#ecvalid_upto").val();
   if(datepickerId == "ecvalid_upto"){
	    ecvalid_upto =  new Date($('#'+datepickerId).val());
	    //
	    if (ecdt < today_date) {	
			$("#ecdtvalidation").html("<label class='text-red'>Enter greater than or equal to Today Date ! !</label>");
			$("#btnsv_").attr("disabled", true);		
			} 
			else {
			$("#ecdtvalidation").empty();
			$("#btnsv_").attr("disabled", false);
		}
	    //
   }
  
   
   //
	if(datepickerId == "issue_date"){
		issue_date =  new Date($('#'+datepickerId).val());
		if (isdt > today_date) {	
			$("#isdtvalidation").html("<label class='text-red'>Enter lees than or equal to Today Date ! !</label>");
			$("#btnsv_").attr("disabled", true);		
			} 	
		else {
			$("#isdtvalidation").empty();
			$("#btnsv_").attr("disabled", false);
		}
	} else if(datepickerId == "valid_upto"){
		valid_upto =  new Date($('#'+datepickerId).val());
		if (isdt > validdt) {	
			$("#validdtval").html("<label class='text-red'>Valid upto date should be greater than Issue Date !!</label>");
			$("#btnsv_").attr("disabled", true);
			} 
		else {
			$("#validdtval").empty();
			$("#btnsv_").attr("disabled", false);			
		}
	}
	
	
//	if(issue_date != "" && valid_upto != "" && issue_date > valid_upto){
//		jBoxBottomRightBigNotice("Invalid", "Valid upto date should be greater than Issue Date !!", "yellow", "5000");
//	}
	

}


//EIA otification que
function getEIAnotification(){
						$('#EIAform').css('display', 'block');
						$("#EIAform").empty();	
	var radioValue = $("input[name='eiastatus']:checked").val();
					if(radioValue == "Yes"){					
					 htmlContent1 ="<div class='row'>"
									+"<div class='col-12 col-md-12'>"
									+"<div class='form-group'>"
									+"<input type='checkbox'' id='eiaQ1' name='eia1' value='Yes'/>&nbsp;&nbsp;&nbsp;"
    								+"<label for='eiaQ1'><span class='font-weight-bold'>Whether located Proximity to Protected Area Notified Under the Wild Life(Protection) Act,1972 ?</span></label></br>"
									+"<input type='checkbox' id='eiaQ2' name='eia2' value='Yes'/>&nbsp;&nbsp;&nbsp;"
    								+"<label for='eiaQ2'><span class='font-weight-bold'>Whether located proximity to critically Polluted area as identified by the CPCB from time to time ?</span></label></br>"
    								+"<input type='checkbox' id='eiaQ3' name='eia3' value='Yes'/>&nbsp;&nbsp;&nbsp;"
    								+"<label for='eiaQ2'><span class='font-weight-bold'>Whether located proximity to Notified Eco-Sensitive area notified under Environmental(Protection) Act,1986 ?</span></label>"
									+ "</div>"	
									+ "</div>"
									
									
									+ "</div>"	
									$("#EIAform").append(htmlContent1);
								}
								else{
								$("#EIAform").hide();						
								}	
}
//if Ec is applicable then fill the Ec form
function getECpresentornot(){
						$('#ecform').css('display', 'block');
						$("#ecform").empty();	
						var radioValue = $("input[name='ecstatus']:checked").val();
						if(radioValue == "Yes"){					
						 htmlContent1 ="<div class='row'>"
									+"<div class='col-12 col-md-4' >"
									+"<div class='form-group'>"
									+ "<label> <span class='font-weight-bold'>EC Number:</span></label>"
									+ "<input type='text' class='form-control' id='ec_no' name='ecNo' placeholder='Environmental Clearance Number' title='EC number' required><i class='form-group__bar'></i>"
									+ "</div>"	
									+ "</div>"	
									+"<div class='col-12 col-md-4'>"
									+"<div class='form-group'>"
									+"<label>EC Valid Upto :</label>"
									+"<input type='date' class='form-control hidden-md-up'>"
									+"<input type='text' class='form-control date-picker hidden-sm-down' name='ecvalidupto' id='ecvalid_upto' onchange='validateConsentDates(\"ecvalid_upto\")'  placeholder='Valid Upto'><div id='ecdtvalidation'></div>"//required
									+"</div>"
									+"</div>"
									//̥
								+"<div class='col-12 col-md-4'>"
								+"<div class='form-group'>"
						   		//+"<div class='form-group row'>"
							    +"<div class='fileinput fileinput-new' data-provides='fileinput'>"
							    +"<label>Environmental Clearance File : "
							    +"<span class='btn btn-primary btn-file'>"
							    +"<span class='fileinput-new'>Select file</span>"
							    +"<span class='fileinput-exists'>Change</span>"
							    +"<input type='file' name='ecFilePath' id='ecdataFile_file' required>"
							    +"</span>"
							    +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
							    +"</div>"
						      //  +"</div>"
						        +"</div>"
					            +"</div>"
									//
									+ "</div>"
										
									+"<div class='row'>"
									+"<div class='col-12 col-md-12'>"
									+"<div class='form-group'>"
									+ "<label> <span class='font-weight-bold'>Whether project/activity attracts the general condition specified in the Schedule of EIA Notification ?</span></label></br>"
									//rd btn
									+"<div class='radio radio--inline'>"
									+"<input type='radio' name='eiastatus' id='eia_y'  value='Yes' onchange='getEIAnotification();'  required><label class='radio__label' for='eia_y'>Yes</label>"
									+"</div>"
									+"<div class='radio radio--inline'>"
									+"<input type='radio' name='eiastatus' id='eia_n' value='No' checked onchange='getEIAnotification();'  required><label class='radio__label' for='eia_n'>No</label>"
									+"</div>"
									+ "</div>"	
									+ "</div>"	
									+ "</div>"	
									
									//EIA notification que
									+"<div id='EIAform'>"	
					 				+"</div>"	
									
									
									$("#ecform").append(htmlContent1);
									makeDatePicker();
									}
						else{
						$('#ecform').css('display', 'none');					
						}
						}


function getAmalgamation(consentType){
	var radioValue = $("input[name='status']:checked").val();	
	if(radioValue == "Amalgamant" && amalgamationCount == 0){
		amalgamationppend(consentType);
		amalgamationCount++;
	} else if (radioValue == 'New') {
		$('#amalgamation_append').empty();
		amalgamationCount = 0;
	}
}


function getConsentToEstablishForm(consentType){	
	var industryCat = $("#industryCat").val();
	 var str1 = "Number of Staff";
	 var str2 = "Number of Worker";
	 
		 if (industryCat == "Construction-Residential/Construction") {
			str1 = "Residential Population";
			str2 = "Commercial Population";
		} else if (industryCat == "Commercial-Offices") {
			str1 = "Number of Staff";
			str2 = "Number of Worker";
		} else if (industryCat == "Commercial-Cinemas, concert halls and theatres") {
			str1 = "Capacity of Theatre";
			str2 = "Number of Worker";
		} else if (industryCat == "Industry-Canteen with Cooking") {
			str1 = "Number of Staff";
			str2 = "Number of Worker";
		} else if (industryCat == "Industry-Canteen without Cooking") {
			str1 = "Number of Staff";
			str2 = "Number of Worker";
		} else if (industryCat == "Industry-Without Canteen") {
			str1 = "Number of Staff";
			str2 = "Number of Worker";
		} else if (industryCat == "Hospital (Including Laundry)") {
			str1 = "Number of Staff";
			str2 = "Number of Beds";
		} else if (industryCat == "Hospital-Up to 100 beds") {
			str1 = "Number of Staff";
			str2 = "Number of Beds";
		} else if (industryCat == "Hospital-More than 100 beds") {
			str1 = "Number of Staff";
			str2 = "Number of Beds";
		} else if (industryCat == "Hospital-Nurses homes and medical quarters") {
			str1 = "Number of Staff";
			str2 = "Number of Beds";
		} else if (industryCat == "Educational Institute-School with Hostel") {
			str1 = "Number of Staff";
			str2 = "Number of Students";
		} else if (industryCat == "Educational Institute-School without Hostel") {
			str1 = "Number of Staff";
			str2 = "Number of Students";
		} else if (industryCat == "Hotel-Hotel with Lodging Facility") {
			str1 = "Number of Worker(Including Staff)";
			str2 = "Number of Key";
		} else if (industryCat == "Hotel-Hotel without Lodging Facility") {
			str1 = "Number of Worker";
			str2 = "Number of Staff";
		} else if (industryCat == "Hotel-Up to 4 Star") {
			str1 = "Number of Worker(Including Staff)";
			str2 = "Number of Key";
		} else if (industryCat == "Hotel-5 Star & above") {
			str1 = "Number of Worker(Including Staff)";
			str2 = "Number of Key";
		}
		 
		 if (industryCat == "Commercial-Cinemas, concert halls and theatres"){
			 var temp = "<div class='col-12 col-md-2'>"
					+"<div class='form-group'>"
					   +"<label>Number of Shows</label>"
					   +"<input type='number' class='form-control' name='no_staff1' title='Number of Shows' placeholder='Number of Shows' required> <i class='form-group__bar'></i>"
					+"</div>"
				 +"</div>"
				 +"<div class='col-12 col-md-2'>"
						+"<div class='form-group'>"
						   +"<label>"+str1+"</label>"
						   +"<input type='number' class='form-control' name='no_staff2' title='"+str1+"' placeholder='"+str1+"' required> <i class='form-group__bar'></i>"
						+"</div>"
					 +"</div>";
		 } else {
			var temp = "<div class='col-12 col-md-4'>"
				+"<div class='form-group'>"
				   +"<label>"+str1+"</label>"
				   +"<input type='number' class='form-control' name='noStaff' title='"+str1+"' placeholder='"+str1+"' required> <i class='form-group__bar'></i>"
				+"</div>"
			 +"</div>";
		 }
	
					var htmlContent ="<div class='row'>"//ask  Ec present or not
						+"<div class='col-4'>"
						+ "<label> <span class='font-weight-bold'>EC Applicable</span>&nbsp;&nbsp; </label>"
							+"<div class='radio radio--inline'>"
								+"<input type='radio' name='ecstatus' id='ec_y'  value='Yes' onchange='getECpresentornot();'  required><label class='radio__label' for='ec_y'>Yes</label>"
							+"</div>"
							+"<div class='radio radio--inline'>"
								+"<input type='radio' name='ecstatus' id='ec_n' value='No' checked onchange='getECpresentornot();'  required><label class='radio__label' for='ec_n'>No</label>"
							+"</div>"				
						+"</div>"
						+"</div>"
				
					+"<div class='form-group'>"

						+ "<label class='font-weight-bold'>Consent Type: "+consentType+"</label>"
						+ " <input type='hidden' class='form-control' readonly='' name='consType' value='"+consentType+"'>"
			
					+ "<div class='form-group'>"
						+ "<label> <span class='font-weight-bold'>Consent Number:</span> (Write consent number as specified in Consent granted from MPCB)</label>"
						+ "<input type='text' class='form-control' id='cons_no' name='consNo' placeholder='Consent Number' title='Consent number' required><i class='form-group__bar'></i>"
					+ "</div>"
					
					+"<div class='row'>"
						+"<div class='col-4'>"
							+"<div class='radio radio--inline'>"
								+"<input type='radio' name='status' id='oper_new' value='New' onclick='getAmalgamation(\""+consentType+"\");' required><label class='radio__label' for='oper_new' >New</label>"
							+"</div>"
							+"<div class='radio radio--inline'>"
								+"<input type='radio' name='status' id='open_amal' value='Amalgamant' onclick='getAmalgamation(\""+consentType+"\");' required><label class='radio__label' for='open_amal'>Amalgamation</label>"
							+"</div>"
							+"<div id='amalgamation_append'></div>"
						+"</div>"
						+"<div class='col-12 col-md-4'>"
							+"<div class='form-group' id='issueDate'>"
								+"<label>Issue Date :</label>"
								+"<input type='date' class='form-control hidden-md-up'>"
								+"<input type='text' class='form-control date-picker hidden-sm-down' name='issueDate' id='issue_date' onchange='validateConsentDates(\"issue_date\")' placeholder='Issue Date' required> &nbsp; <div id='isdtvalidation'></div>"
							+"</div>"
						+"</div>"
						+"<div class='col-12 col-md-4'>"
							+"<div class='form-group'>"
								+"<label>Valid Upto :</label>"
								+"<input type='date' class='form-control hidden-md-up'>"
								+"<input type='text' class='form-control date-picker hidden-sm-down' name='validUpto' id='valid_upto' onchange='validateConsentDates(\"valid_upto\")' placeholder='Valid Upto' required> &nbsp; <div id='validdtval'></div>"
							+"</div>"
						+"</div>"
					+"</div>"
					
//					+"<div class='row'>"
//					 +"<div class='col-12 col-md-4'>"
//						+"<div class='form-group'>"
//						   +"<label>Gross CI</label>"
//						   +"<input type='number' class='form-control' id='gross_ci' name='grossCi' placeholder='Gross CI' title='Gross CI' required> <i class='form-group__bar'></i>"											  
//						   +"</div>"		
//					 +"</div>"
					 +"<div class='row'>"
					 +"<div class='col-12 col-md-4'>"
						+"<div class='form-group'>"
						   +"<label>Gross CI</label>"
						   +"<div class='row'>"
							  +"<div class='col-6'>"
								 +"<input type='number' class='form-control'  id='gross_ci'  placeholder='Gross CI' name='grossCi' title='Gross CI' required> <i class='form-group__bar'></i>"
							  +"</div>"
							  +"<div class='col-6'>"
							  +"<select class='select2' id='gsunit' name='grossunit'>"
								+"<option>Lakhs.</option>"
								+"<option>Cr.</option>"
							 +"</select>"
							  +"</div>"
						   +"</div>"
						+"</div>"
					 +"</div>"
					
					+temp
					 +"<div class='col-12 col-md-4'>"
						+"<div class='form-group'>"
						   +"<label>Number of Worker</label>"
						   +"<input type='number' class='form-control' name='noWorker' placeholder='Number of Worker' title='Number of Worker' required> <i class='form-group__bar'></i>"
						+"</div>"
					 +"</div>"
					 
					 
					+"</div>"
					+"<div class='row'>"
					 +"<div class='col-12 col-md-4'>"
						+"<div class='form-group'>"
						   +"<label>Total Plot Area</label>"
						   +"<div class='row'>"
							  +"<div class='col-6'>"
								 +"<input type='number' class='form-control'  id='tot_plot_area' name='totPlotArea' placeholder='Total Plot Area' title='Total Plot Area'  required> <i class='form-group__bar'></i>"
							  +"</div>"
							  +"<div class='col-6'>"
								 +"<select class='select2' name='totPlotAreaUnits'>"
									+"<option>sq. m.</option>"
									+"<option>sq. feet</option>"
								+"</select>"
							  +"</div>"
						   +"</div>"
						+"</div>"
					 +"</div>"
					 +"<div class='col-12 col-md-4'>"
						+"<div class='form-group'>"
						   +"<label>Total Built Up Area</label>"
						   +"<div class='row'>"
							  +"<div class='col-6 col-md-6'>"
								 +"<input type='number' class='form-control'  id='tot_build_area' name='totBuildArea'  placeholder='Total Build Area' title='Total Build Area'  required> <i class='form-group__bar'></i>"
							  +"</div>"
							  +"<div class='col-6 col-md-'>"
								 +"<select class='select2' id='tot_build_area_unit' name='totBuildAreaUnits'>"
									+"<option>sq. m.</option>"
									+"<option>sq. feet</option>"
								 +"</select>"
							  +"</div>"
						   +"</div>"
						+"</div>"
					 +"</div>"
					 +"<div class='col-12 col-md-4'>"
						+"<div class='form-group'>"
						   +"<label>Open Space Available</label>"
						   +"<div class='row'>"
							  +"<div class='col-6'>"
								 +"<input type='number' class='form-control' id='open_space_ava' name='openSpaceAva' required=''  placeholder='Open Space Ava.'  title='Open Space Ava.'  required> <i class='form-group__bar'></i>"
							  +"</div>"
							  +"<div class='col-6'>"
								 +"<select class='select2' id='open_space_ava_unit' name='openSpaceAvaUnits'>"
									+"<option>sq. m.</option>"
									+"<option>sq. feet</option>"
								 +"</select>"
							  +"</div>"
						   +"</div>"
						+"</div>"
					 +"</div>"
					+"</div>"
					+"<div class='row'>"
					 +"<div class='col-12 col-md-4'>"
						+"<div class='form-group'>"
						   +"<label>Total Green Area</label>"
						   +"<div class='row'>"
							  +"<div class='col-6 col-md-6'>"
								 +"<input type='number' class='form-control' id='tot_green_area' name='totGreenArea' data-toggle='tooltip' data-placement='top' placeholder='Total Green Area' title='Total Green Area' required> <i class='form-group__bar'></i>"
							  +"</div>"
							  +"<div class='col-6 col-md-'>"
								 +"<select class='select2' id='tot_green_area_unit' name='totGreenAreaUnits'>"
									+"<option>sq. m.</option>"
									+"<option>sq. feet</option>"
								 +"</select>"
							  +"</div>"
						   +"</div>"
						+"</div>"
					 +"</div>"
					 +"<div class='col-12 col-md-6'>"
						+"<div class='form-group'>"
						   +"<div class='form-group row'>"
							   +"<div class='fileinput fileinput-new' data-provides='fileinput'>"
							   +"<label>Consent File : "
							    +"<span class='btn btn-primary btn-file'>"
							    +"<span class='fileinput-new'>Select file</span>"
							    +"<span class='fileinput-exists'>Change</span>"
							    +"<input type='file' name='consentFilePath' name='dataFile_file' id='dataFile_file' required>"
							    +"</span>"
							    +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
							  +"</div>"
							 
						   +"</div>"
						+"</div>"
					 +"</div>"
					+"</div>"
					+"</div>"
				+"<div id='ecform'>"	
					 +"</div>"
					
				+"<div class='row'>"
					 +"<div class='col-12'>"
						+"<center>"
						   +"<button type='reset' class='btn red lighten-1 text-white'> <i class='zmdi zmdi-close'></i> cancel </button>"
						   +"<button type='submit' id='btnsv_' class='btn btn-primary btn--icon-text'> <i class='zmdi zmdi-save'></i> Submit </button>"
						+"</center>"
					 +"</div>"
					$("#consentform").append(htmlContent);
				
	makeSelect2();makeDatePicker();
}


function getAmalgamationOpForm(){
	$("#consentform").empty();
var html ="<div class='row'>"
+"<input type='hidden' name='extendDate' value='ExpandDate'>"
				+"<div class='col-md-12 mb-3'>"
					+"<center><label class='font-weight-bold'>Select consent to operate which you have Amalgamation copy of consent and Enter the extended consent  no.</label></center>"
				+"</div>"
				
				+"<div class='col-12 col-md-4 offset-md-2'>"
		   			+"<div class='form-group'>"
						+"<label class='font-weight-bold'>Consent Number</label>"
						+"<div id='amalgamation_append'> </div>"
					+"</div>"
				+"</div>"
			
				+"<div class='col-12 col-md-4'>"
			   		+"<div class='form-group'>"
						+"<label class='font-weight-bold'>Enter new consent no.:</label>"
						+"<input type='text' class='form-control' id='new_consent_no' name='expandedConsNo' placeholder='Enter Consent Number' title='Enter Consent number' required><i class='form-group__bar'></i>"
					+"</div>"
				+"</div>"
				
				+"<div class='col-12 col-md-4 offset-md-2'>"
					+"<div class='form-group'>"
						+"<label>Issue Date :</label>"
						+"<input type='date' class='form-control hidden-md-up' placeholder='Issue Date'>"
						+"<input type='text' class='form-control date-picker hidden-sm-down' name='expandedInputDate' id='issue_date' placeholder='Issue Date' onchange='validateConsentDates(\"issue_date\")' required>"
					+"</div>"
				+"</div>"
			
				+"<div class='col-12 col-md-4'>"
					+"<div class='form-group'>"
						+"<label>Valid Date :</label>"
						+"<input type='date' class='form-control hidden-md-up' placeholder='Valid Date'>"
						+"<input type='text' class='form-control date-picker hidden-sm-down' name='expandedValidUpto' id='valid_upto' placeholder='Valid Date' onchange='validateConsentDates(\"valid_upto\")' required>"
					+"</div>"
				+"</div>"
				
				+"<div class='col-12 col-md-4 offset-md-2'>"
					+"<div class='form-group row'>"
						+"<div class='fileinput fileinput-new' data-provides='fileinput'>"
						 +"<label>Consent File : "
						  +"<span class='btn btn-primary btn-file'>"
						  +"<span class='fileinput-new'>Select file</span>"
						  +"<span class='fileinput-exists'>Change</span>"
						  +"<input type='file' name='expandedConsentFilePath' id='dataFile_file' required>"
						  +"</span>"
						  +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
						+"</div>"
				  +"</div>"
				+"</div>"
				+"<div class='col-md-12 mb-3'>"
					+"<center><button type='submit' class='btn btn-primary btn--icon-text'> <i class='fas fa-external-link-square-alt'></i>  Extend </button></center>"
				+"</div>"
				
				+"</div>"
				
				$("#consentform").append(html);
				amalgamationppend('Consent to Operate');
				makeSelect2();makeDatePicker();makeFilePicker();
}
function amalgamationppend(consentType){
	$("#amalgamation_append").empty();
	$.ajax({	
		url: 'ajax-getConsentForm?consentType=' + consentType,
		type: 'post',
		dataType: 'json',
		async: true,
		success: function (data) {
			var result = JSON.parse(data);
			var html2= "";
			var selectOpName = "";
			if(consentType == 'Consent to Establish'){
				selectOpName = "aml_e[]";
			} else {
				selectOpName = "aml_o[]";
			}
			var html = "<div class='mt-2'>" +
						"<select class='select2' multiple data-placeholder='Select one or more choices' data-max-options='3' title='Choose only upto 3' id='cons_to_establish01' name='"+selectOpName+"' required></div><div id='consty'></div>"
						$.each(result, function (index, element) {
							html += "<option value='"+element.consentId+"'>"+element.consentName+"</option>"
							
						});
			
				html+="</select>" +
	   					"</div>";
			$("#amalgamation_append").append(html);
			makeSelect2();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});

}

function makeSelect2() {
	if ($('select.select2')[0]) {
		var select2parent = $('.select2-parent')[0] ? $('.select2-parent') : $('body');

		$('select.select2').select2({
			dropdownAutoWidth: true,
			width: '100%',
			dropdownParent: select2parent
		});
	}
}

function makeFilePicker(){
   	// file uploads button script 
   	document.querySelector("html").classList.add('js');
   	var fileInput  = document.querySelector( ".input-file" ),  
   	    button     = document.querySelector( ".input-file-trigger" ),
   	    the_return = document.querySelector(".file-return");
   	      
   	button.addEventListener( "keydown", function( event ) {  
   	    if ( event.keyCode == 13 || event.keyCode == 32 ) {  
   	        fileInput.focus();  
   	    }  
   	});
   	button.addEventListener( "click", function( event ) {
   	   fileInput.focus();
   	   return false;
   	});  
   	fileInput.addEventListener( "change", function( event ) {  
   	    the_return.innerHTML = this.value;  
   	});  
}

/*------------------------------------------------
Datetime picker (Flatpickr)
------------------------------------------------*/
//Date and time
function makeDatePicker() {
	if ($('.datetime-picker')[0]) {
		$('.datetime-picker').flatpickr({
			enableTime : true,
			nextArrow : '<i class="zmdi zmdi-long-arrow-right" />',
			prevArrow : '<i class="zmdi zmdi-long-arrow-left" />'
		});
	}

	// Date only
	if ($('.date-picker')[0]) {
		$('.date-picker').flatpickr({
			enableTime : false,
			nextArrow : '<i class="zmdi zmdi-long-arrow-right" />',
			prevArrow : '<i class="zmdi zmdi-long-arrow-left" />'
		});
	}

	// Time only
	if ($('.time-picker')[0]) {
		$('.time-picker').flatpickr({
			noCalendar : true,
			enableTime : true
		});
	}

}