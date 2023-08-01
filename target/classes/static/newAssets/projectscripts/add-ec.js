$(document).ready(function () {
addECform();	
});

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
   }

function addmultipleFile(appendToId){
	var randomId = randomIdNumber();								
	var hzHtml ="<div class='col-12 col-md-12' >"					
								+"<div class='fileinput fileinput-new' data-provides='fileinput'>"
							    +"<label>EC File : "
							    +"<span class='btn btn-primary btn-file'>"
							    +"<span class='fileinput-new'>Select file</span>"
							    +"<span class='fileinput-exists'>Change</span>"
							    +"<input type='file' name='ecFilePath' id='ecdataFile_file' required>"
							    +"</span>"
							    +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"							  
								+"<button type='button' class='btn btn-sm btn-light pt-1 pb-1' onclick='removeElement(\"appended_"+randomId+"\")'><i class='zmdi zmdi-delete'></i>  Remove</button>"
								+"</div>"								
								+"</div>"				
								+"</div>";	
	var html = "<div class='row' id='appended_"+randomId+"'>"
					+hzHtml					
				+"</div>";
	$("#idmultifile").append(html);
	
}
function removeElement(removeId){
	$("#"+removeId).remove();
}
function randomIdNumber() {
	var min = 1;
	var max = 5000;
	return Math.round(Math.random() * (+max - +min) + +min);
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

function addECform(){
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
								+"<div class='form-group' >"
						   		
							    +"<div class='fileinput fileinput-new' data-provides='fileinput'>"
							 
							    +"<label>EC File : "
							    +"<span class='btn btn-primary btn-file'>"
							    +"<span class='fileinput-new'>Select file</span>"
							    +"<span class='fileinput-exists'>Change</span>"
							    +"<input type='file' name='ecFilePath' id='ecdataFile_file' required>"
							    +"</span>"
							    +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
							    +"</div>"
						     
						        ////mm file
						     
													+"<button type='button' class='btn btn-sm btn-light pt-1 pb' onclick='addmultipleFile(\"idmultifile\");'>"
														+"<i class='zmdi zmdi-plus zmdi-hc-fw'></i>Add</button>"

						        ///mm
						        +"<div id='idmultifile'>"	
					 				+"</div>"
						        //
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
									
										 +"<div class='col-12'>"
											+"<center>"
											   +"<button type='reset' class='btn red lighten-1 text-white'> <i class='zmdi zmdi-close'></i> cancel </button>"
											   +"<button type='submit' id='btnsv_' class='btn btn-primary btn--icon-text'> <i class='zmdi zmdi-save'></i> Submit </button>"
											+"</center>"
										 +"</div>"
									
									$("#ecform").append(htmlContent1);
									makeDatePicker();makeFilePicker();
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
	