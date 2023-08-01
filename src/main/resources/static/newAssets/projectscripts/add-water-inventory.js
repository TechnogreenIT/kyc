var max_fields = 10; // maximum input boxes allowed
var wrapper = $(".appendExtraWaterSource"); // Fields wrapper
var add_button = $("#add_containers"); // Add button ID
var source_count = 1; // initlal text box count
var x = 0, pre = 0,can =0;cgwa=0;
var divFlag = 0;
var waterSourceOptions ="";
var waterFiltersOptions ="";
var usedWaterSources = new Array();
var filter_use_Array = new Array();


$( document ).ready(function() {
  waterSourceOptions = getWaterSourcesOptions();
  $("#appendWaterSources").append(waterSourceOptions);
  
  
  waterFiltersOptions = getWaterFiltersOptions();
  
  filter_use_Array = getWaterFilterUseName();

});

function cgwafileup(){	
	 // var chkAppliedorYes = document.getElementById("ddlCGWAs");
	 // var status = chkAppliedorYes.options[chkAppliedorYes.selectedIndex].text; 
	   $("#upfileornot").empty();	
	   var status = $("input[name='cgwastatus']:checked").val();
						
	  if(status=="Yes" ){
	  	var htmlcontent="<div class='col-12'>"
							    +"<div class='fileinput fileinput-new' data-provides='fileinput'>"
							    +"<label>CGWA Acknowledgement/CGWA Applied Acknowledgement : "
							    +"<span class='btn btn-primary btn-file'>"
							    +"<span class='fileinput-new'>Select file</span>"
							    +"<span class='fileinput-exists'>Change</span>"
							    +"<input type='file' name='dataFile_file' id='cgwadataFile'  for='dataFile_file' >"
							    +"</span>"
							    +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
							    +"</div>"
					            +"</div>"
					            	 $("#upfileornot").append(htmlcontent);
					            }
}



/////mmm
//try
function addcgwa(){
  
	
	if(cgwa==0){
			
	var chkborewell = document.getElementById("appendWaterSources");
	var bw = chkborewell.options[chkborewell.selectedIndex].text; 
	$("#appendcgwc").empty();	
	if(bw=="Bore well"){
	  			 var htmlcontent='<div class="row mt-5">'
									 +'<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">'
									// +'<div class="form-group">'
									 +'<label class="font-weight-bold">i.Do You Have CGWA Permission?</label>'
									 
								    +'<div class="row mt-1">'
							  
										+"<div class='col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3'>"
										+"<div class='radio radio--inline'>"
												+"<input type='radio' name='cgwastatus' id='cgwa_y'  value='Yes' onchange='cgwafileup();'  required><label class='radio__label' for='cgwa_y'>Yes/Applied</label>"
										+"</div>"
										+"<div class='radio radio--inline'>"
												+"<input type='radio' name='cgwastatus' id='cgwa_n' value='No' checked onchange='cgwafileup();'  required><label class='radio__label' for='cgwa_n'>No</label>"
										+"</div>"													
										+"</div>"
																				 
								      +'<div class="col-8 col-sm-8 col-md-8 col-lg-8 col-xl-8">'
								       +'<div id="upfileornot">'
								     + '</div>'							   
								     +'</div>'
								   
								  + '</div>'
									//+ '</div>'
									+'</div>'
									
									 $("#appendcgwc").append(htmlcontent);
    			cgwa++;
   			} 
   		//	}
	}
	//else {
		//if(data=="No"){
		//	$("#appendcgwc").empty();
		//	can--;
		//}
	//}
 
}
//


function addMoreWaterSource(){
  var waterSources = $("[name='waterSourceNames[]']");
  for (var a = 0; a < waterSources.length; a++) {
    var inpWaterSource = $("[name='waterSourceNames[]']")[a];
    var waterSourceValue = inpWaterSource.value;
    if (waterSourceValue == "") {
      inpWaterSource.closest('div').lastElementChild.classList.add("d-block");
      return;
    } else {
      usedWaterSources.push(waterSourceValue);
      inpWaterSource.closest('div').lastElementChild.classList.remove("d-block");
    }

  }
   
  if (source_count < max_fields) { // max input box allowed
    source_count++; // text box increment
    x++;
    if(divFlag == 0){
      $(wrapper).append('<div class="row">'
                  +'<div class="col-12 col-sm-5 col-md-5 col-lg-5 col-xl-5">'
                    +'<div class="form-group">'
                      +'<select class="select2" data-placeholder="Select Source"  name="waterSourceNames[]" id="waterSourceNames" >'
                        +'<option value="">Select Source</option>'
                        +waterSourceOptions
                      +'</select>'
                      +'<div class="invalid-feedback">Please select any !</div>'
                    +'</div>'
                  +'</div>'
                  
                  +'<div class="col-12 col-sm-5 col-md-5 col-lg-5 col-xl-5">'
                    +'<div class="form-group">'
                      +'<label class="font-weight-bold"> Do You Have Water Meter for Source Water?</label></br>'
                      +'<div class="radio radio--inline cursor-pointer"> <input type="radio" name="waterMeter'+x+'" id="meterY'+x+'" value="true"> <label class="radio__label" for="meterY'+x+'">Yes</label> </div> <div class="radio radio--inline cursor-pointer"> <input type="radio" name="waterMeter'+x+'" id="meterN'+x+'" value="false" checked> <label class="radio__label" for="meterN'+x+'">No</label> </div>'
                    +'</div>'
                  +'</div>'
                  
                  +'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
                    +'<button type="button" class="btn btn-sm btn-warning pt-1 pb-1" onclick="removeExtraWaterSource(this);"><i class="zmdi zmdi-delete"></i> Remove</button>'
                  +'</div>'
              +'</div>'); // add input box
      makeSelect2(); 
    }
    
  }
}

function removeExtraWaterSource(el){
  $(el).parent('div').parent('div').remove();
  source_count--;
}

function showCookingForm(data){
  
	
	if(can==0){
			if(data=="Yes"){
	  			var html ='<div class="form-group">'
    				+'<label class="font-weight-bold">2(i). Do You Have Cooking Facility in the canteen?</label></br>'
            		+' <div class="radio radio--inline cursor-pointer"> <input type="radio" name="isCookingCanteen" id="cookingCanteenY" value="true"> <label class="radio__label" for="cookingCanteenY">Yes</label> </div>'
            		+' <div class="radio radio--inline cursor-pointer"> <input type="radio" name="isCookingCanteen" id="cookingCanteenN" value="false" checked> <label class="radio__label" for="cookingCanteenN">No</label> </div>'
            		+'</div>';
    			$("#appendCookingFacilityBlock").append(html);
    			can++;
   			} 
	}
	else {
		if(data=="No"){
			$("#appendCookingFacilityBlock").empty();
			can--;
		}
	}
  
  
  
}

function preFilterForm(data){
	
	if(pre==0){  
	  if(data=="Yes"){
	    var html ='<div class="form-group"><div class="row mt-4 ">'
	            +'<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6"><div class="row ">'
	            +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 "><div class="checkbox"><input type="checkbox" name="ACF"  value="ACF" id="prefilterACF">'
	            +'<label class="checkbox__label" for="prefilterACF"> ACF </label></div></div>'
	            +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 "><div class="checkbox"><input type="checkbox" name="PSF" value="PSF" id="prefilterPSF">'
	            +'<label class="checkbox__label" for="prefilterPSF"> PSF </label></div></div>'
	            +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 "><div class="checkbox"><input type="checkbox" name="DMF" value="DMF" id="prefilterDMF">'
	            +'<label class="checkbox__label" for="prefilterDMF"> DMF </label></div></div>'
	            +'</div></div>'
	            +'<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6"> '
	        	+'<label class="font-weight-bold">3(i). Do You Have Water Meter ?</label></br>'
        		+' <div class="radio radio--inline cursor-pointer"> <input type="radio" name="isPrefilterMeter" id="preFilterY" value="true"> <label class="radio__label" for="preFilterY">Yes</label> </div>'
        		+' <div class="radio radio--inline cursor-pointer"> <input type="radio" name="isPrefilterMeter" id="preFilterN" value="false" checked> <label class="radio__label" for="preFilterN">No</label> </div>'
        		+'</div>'
	          +'</div></div>';
	    $("#preFilterFormBlock").append(html);
	    pre++;
	    }
	  }
	else {
		 if(data=="No"){
			 $("#preFilterFormBlock").empty();
			 pre--;
			 }
		 }
	  
	}


function getUseWaterSource(el,useType,useFltr,cnt){
  var tempId = useType.replace(/\s/g, '');
  var idd = "appendUseWaterSource"+tempId+useFltr+cnt;
  var htmlll = "";
  if(el.checked){
    htmlll = '<div class="row">'
          +'<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 mt-2">'
            +'<div class="form-group mb-1">'
              +'<label class="font-weight-bold"> 4(i). Do You Have Water Meter for '+useType+'?</label></br>'
            +'</div>'
          +'</div>'
        
          +'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'
            +'<div class="form-group mb-1">'
              +'<div class="radio radio--inline cursor-pointer"> <input type="radio" name="waterUseMeter'+idd+'" id="meterY'+idd+'" value="Yes"> <label class="radio__label" for="meterY'+idd+'">Yes</label> </div> <div class="radio radio--inline cursor-pointer"> <input type="radio" name="waterUseMeter'+idd+'" id="meterN'+idd+'" value="No" checked> <label class="radio__label" for="meterN'+idd+'">No</label> </div>'
            +'</div>'
          +'</div>'
        +'</div>';
    
      htmlll +='<div class="row">'
            +'<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 mt-2">'
              +'<div class="form-group mb-1">'
                +'<label class="font-weight-bold"> 4(ii). Water Loss in Percent (%) for '+useType+' :</label></br>'
              +'</div>'
            +'</div>'
            
            +'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'
              +'<div class="form-group mb-1">'
                +'<input type="number" name="waterUseLoss_'+idd+'" class="form-control" id="waterUseLoss_'+idd+'">'
                +'<div class="invalid-feedback">Required !</div>'
                +'<i class="form-group__bar"></i>'
              +'</div>'
            +'</div>'
          +'</div>';
    
    $("#"+idd).append(htmlll);
  } else {
    $("#"+idd).empty();
  }
}


function getUseWaterSourceIndustrial(el,UseFltr){
  var htmlll = "";
  if(el.checked){
    htmlll += '<div class="row">'
          +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'
            +'<div class="checkbox">'
              +'<input type="checkbox" name="directIndustrialUseWater'+UseFltr+'[]" id="useWaterSourceIndustrialProcess'+UseFltr+'" onclick="getFilterIndustrialUse(this,\'Process\',\''+UseFltr+'\');" value="Process">'
              +'<label class="checkbox__label" for="useWaterSourceIndustrialProcess'+UseFltr+'">Process</label>'
            +'</div>'
          +'</div>'
          +'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendFilterWaterUseIndustrialProcess'+UseFltr+'"> </div>'
          
          +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'
            +'<div class="checkbox">'
              +'<input type="checkbox" name="directIndustrialUseWater'+UseFltr+'[]" id="useWaterSourceIndustrialCooling'+UseFltr+'" onclick="getFilterIndustrialUse(this,\'Cooling\',\''+UseFltr+'\');" value="Cooling">'
              +'<label class="checkbox__label" for="useWaterSourceIndustrialCooling'+UseFltr+'">Cooling</label>'
            +'</div>'
          +'</div>'
          +'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendFilterWaterUseIndustrialCooling'+UseFltr+'"> </div>'
          
          +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'
            +'<div class="checkbox">'
              +'<input type="checkbox" name="directIndustrialUseWater'+UseFltr+'[]" id="useWaterSourceIndustrialBoiler'+UseFltr+'" onclick="getFilterIndustrialUse(this,\'Boiler\',\''+UseFltr+'\');" value="Boiler">'
              +'<label class="checkbox__label" for="useWaterSourceIndustrialBoiler'+UseFltr+'">Boiler</label>'
            +'</div>'
          +'</div>'
          +'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendFilterWaterUseIndustrialBoiler'+UseFltr+'"> </div>'
          
          
          +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'
            +'<div class="checkbox">'
              +'<input type="checkbox" name="directIndustrialUseWater'+UseFltr+'[]" id="useWaterSourceIndustrialEquipmentWashing'+UseFltr+'" onclick="getFilterIndustrialUse(this,\'Equipment Washing\',\''+UseFltr+'\');" value="Equipment Washing">'
              +'<label class="checkbox__label" for="useWaterSourceIndustrialEquipmentWashing'+UseFltr+'">Equipment Washing</label>'
            +'</div>'
          +'</div>'
          +'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendFilterWaterUseIndustrialEquipmentWashing'+UseFltr+'"> </div>'
            
          +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'
            +'<div class="checkbox">'
              +'<input type="checkbox" name="directIndustrialUseWater'+UseFltr+'[]" id="useWaterSourceIndustrialWaterScrubber'+UseFltr+'" onclick="getFilterIndustrialUse(this,\'Water Scrubber\',\''+UseFltr+'\');" value="Water Scrubber">'
              +'<label class="checkbox__label" for="useWaterSourceIndustrialWaterScrubber'+UseFltr+'">Water Scrubber</label>'
            +'</div>'
          +'</div>'
          +'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendFilterWaterUseIndustrialWaterScrubber'+UseFltr+'"> </div>'
        
        +'</div>';
    $("#appendUseWaterSourceIndustrial"+UseFltr).append(htmlll);
  } else {
    $("#appendUseWaterSourceIndustrial"+UseFltr).empty();
  }
}

function getFilterIndustrialUse(el,idsUseName,useFltr){
  var tempId = idsUseName.replace(/\s/g, '');
  var idd = "appendFilterWaterUseIndustrial"+tempId+useFltr;
  var htmlIndUseDiv = "";
  
  if(el.checked){
    htmlIndUseDiv += '<div class="row mt-3">'
              +'<div class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7 offset-lg-1">'
                +'<label class="font-weight-bold"> 1. Do You Have Water Meter for '+idsUseName+'?</label></br>'
              +'</div>'
              +'<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'
                +'<div class="radio radio--inline cursor-pointer"> <input type="radio" name="indUse_'+idd+'" id="meterY'+idd+'" value="Yes"> <label class="radio__label" for="meterY'+idd+'">Yes</label> </div> <div class="radio radio--inline cursor-pointer"> <input type="radio" name="indUse_'+idd+'" id="meterN'+idd+'" value="No" checked> <label class="radio__label" for="meterN'+idd+'">No</label> </div>'
              +'</div>'
              
      htmlIndUseDiv +='<div class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7 offset-lg-1 mt-3"><label> Water Loss in Percent (%) for '+idsUseName+' :</label></div>'
              +'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'
                +'<div class="form-group">'
                  +'<input type="number" name="customWaterLossIndustrialUse_'+idd+'" class="form-control" id="customWaterLossIndustrialUse_'+idd+'_value"><div class="invalid-feedback">Required !</div><i class="form-group__bar"></i>'
                +'</div>'
              +'</div>'
    
        htmlIndUseDiv +='</div>';
    $("#"+idd).append(htmlIndUseDiv);
  } else {
    $("#"+idd).empty();
  }
}
function saveWaterInventory(el,type) {
  // make an empty object
  var myObject = {};
  var waterDetails = new Array();
  var directUseWaterArray = new Array();
  var directIndUseWaterArray = new Array();

 // var waterDetails = new Array();
  var water_source_names_array = new Array();
  var water_source_meter_array = new Array();
  var flag = 0;
  var isCanteen = "false";
  var isCanteenCooking = "false";
  var isWaterTreatment = "false";
  var isPrefilter = "false";
  var isPrefilterMeter = "false";
 var acf ="false";
  var psf ="false";
 var dmf ="false";
// var cgwapermission="";
var cgwapermission="No";
 
  var consent_no= $("#consent_no").val();
  //cgwa permission
  
  // canteen value
  isCanteen = $('input[name=isHouseCanteen]:checked').val();
  
  // canteen cooking
  if(isCanteen == "true"){
    isCanteenCooking = $('input[name=isCookingCanteen]:checked').val();
  }
  // water treatment
  isWaterTreatment = $('input[name=isWaterTreatment]:checked').val();
  
  // wastewater treatment
  iswasteWaterTreatment = $('input[name=wasteTreatmentPlantQuestion]:checked').val();
  
 // cgwapermission=$('input[name=cgwastatus]:checked').val();//mm
   var form_data = new FormData();
 var chkborewell = document.getElementById("appendWaterSources");
	  var bw = chkborewell.options[chkborewell.selectedIndex].text; 
   //  var file;
if(bw=="Bore well")
 {
	 cgwapermission=$('input[name=cgwastatus]:checked').val();
	 if(cgwapermission=="Yes"){
	    var valid = 0;
    var filename = $('[name='+type+'_file]').val();  
    if (filename != "") {

        var file_data = $('[name=' + type + '_file]').prop('files')[0];
       // filename = file_data;
        if (filename == "") {
			file_data = createDefaultFile();//n
            valid--;
        }
    }   
     else {
        valid--;
    }
     if (valid == 0) {
  
	  form_data.append('file', file_data);
   }
   }
  
   
 }

 	 
   
   
  var item = {}
  item["consentId"] = consent_no;
  item["isHouseCanteen"] = isCanteen;
  item["isCookingCanteen"] = isCanteenCooking;
  item["isWaterTreatment"] = isWaterTreatment;
  item["iswasteWaterTreatment"] = iswasteWaterTreatment;
  item["iscgwapermissiion"] = cgwapermission;
  //item["cgwa_file_path"] = FormData;
  myObject.waterInventory = item;
 

  //waterSources
  var waterSources = $("[name='waterSourceNames[]']");
  for (var a = 0; a < waterSources.length; a++) {
    var inpWaterSource = $("[name='waterSourceNames[]']")[a];
    var waterSourceValue = inpWaterSource.value;
    var materCheckBoxValue = $("input[name='waterMeter" + a + "']:checked").val();
    if (waterSourceValue == "") {
      flag--;
      inpWaterSource.closest('div').lastElementChild.classList.add("d-block");
      return;
    } else {
      inpWaterSource.closest('div').lastElementChild.classList.remove("d-block");
      var item = {}
      item["sourceName"] = waterSourceValue;
      item["sourceMeter"] = materCheckBoxValue;
  
		      isPrefilter = $('input[name=isPre]:checked').val();
		      var preFilterData =new Array();
		      if(isPrefilter == "true"){
		    	
		    	  isPrefilterMeter = $('input[name=isPrefilterMeter]:checked').val();
		    	   acf = $('input[name=ACF]:checked').val();
		      	   psf = $('input[name=PSF]:checked').val();
		      	   dmf = $('input[name=DMF]:checked').val();
		      	  
		      	   for(var i=0;i<2;i++){
		      			var preFilter={}
		      			preFilter["isPrefilter"] =isPrefilter;
		      			preFilter["isPrefilterMeter"] = isPrefilterMeter;
		      	 
		      	if(acf == "ACF"){
		      		preFilter["acf"] = "true";
		      	}else{
		      		preFilter["acf"] = "false";
		      	}
		      	 
		      	if(psf == "PSF"){
		      		preFilter["psf"] = "true";
		      	}else{
		      		preFilter["psf"] = "false";
		      	}
		      	 
		    	if(dmf == "DMF"){
		    		preFilter["dmf"] = "true";
		      	}else{
		      		preFilter["dmf"] = "false";
      	        }
		    	
		    	preFilterData.push(preFilter);
		    	isPrefilter ="false";
		      	  } 
      }
      else{
    	  var preFilter={}
    	  preFilter["isPrefilter"] ="false";
    	  preFilter["isPrefilterMeter"] = "false";
    	  preFilter["acf"] = "false";
    	  preFilter["psf"] = "false";
    	  preFilter["dmf"] = "false";
    	  preFilterData.push(preFilter);
      }
      
	 item["preFilter"] = preFilterData;
      waterDetails.push(item);
       
    }
  } 

  myObject.waterSources = waterDetails;
 var alldata= JSON.stringify(myObject);
  form_data.append("alldata",alldata);
  
 // myObject.filedata = form_data;
   
   
  if (flag == 0) {
    $.ajax({
   //   type : "POST",
      url : "ajax-water-inventory-c2o",
     // contentType : "application/json",
     contentType : false,
       processData: false,
      data : form_data,
       type : "POST",
      success : function(data) {
    	  $("#save-waterInventory-btn").attr("disabled", true);  
          findNextAccordion(el);
          jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
      }
    });
  }
  
}
