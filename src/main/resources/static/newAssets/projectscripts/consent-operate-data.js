isproduction == "No";
var isSelectAll = false;
var isstackcheckboxcount = 0;
var stackcheckboxcount = 0;


var indType = $("#isproduction").val();
if(indType == "Industry")isproduction == "Yes"

	
	
var msg = "";
var j = 0;

//if (isproduction == "Yes") {
//	var list = [ "product", "byproduct", "raw", "fuel", "hwp", "hwpcf", "nhwp","nhwpcf" ];
//} else {
//	var list = [ "fuel", "hwp", "hwpcf", "nhwp", "nhwpcf" ];
//}
getAllCtoOData();

function getAllCtoOData(){
	$.ajax({
		type : "POST",
		url : "ajax-olist-data",
		dataType : 'json',
		success : function(data) {
			var parseData = JSON.parse(data);
			
			 var objSize = Object.keys(parseData).length;
			 
			 var keyArray = Object.keys(parseData);
			 
			 for(var i = 0 ;i<keyArray.length;i++){

				var varKeyName = keyArray[i];
				var allDataArray = new Array();
				allDataArray = parseData[varKeyName];

				var html = "";
				var ii = 0;
				
				if(varKeyName == "Raw Material") varKeyName = "rawMaterial";
				$.each(allDataArray, function(index, element) {
					allProductNameId = element.allProductNameId;
					productName = element.productName;
					quantity = element.quantity;
					units = element.units;
					unitId = element.unitId;
					var isDisabled="";
					if(quantity == 0)isDisabled = "disabled";
					html +="<div class='col-1'>"
							 +"<div class='form-group mt-2 checkbox'>"
								+"<input type='checkbox' name='"+varKeyName+"_checkBox[]' id='"+varKeyName+"checkBox"+ii+"' value='"+allProductNameId+"' "+isDisabled+">"
								+"<label class='checkbox__label' for='"+varKeyName+"checkBox"+ii+"'></label>"
							 +"</div>"
							+"</div>"
							+"<div class='col-6'>"
							 +"<div class='form-group'>"
								+"<div class='form-group'>"
								   +"<input type='hidden' name='"+varKeyName+"_name[]' value='"+productName+"' "+isDisabled+">"
								   +"<label class='form-control'>"+productName+"</label>"
								+"</div>"
							 +"</div>"
							+"</div>"
							+"<div class='col-3'>"
							 +"<div class='form-group'>"
								+"<input type='number' name='"+varKeyName+"_quantity[]' class='form-control' value='"+quantity+"' "+isDisabled+">"
								+"<div class='invalid-feedback'>Invalid !</div>"
								+"<i class='form-group__bar'></i>"
							 +"</div>"
							+"</div>"
							+"<div class='col-2'>"
							 +"<div class='form-group'>"
								+"<div class='form-group'>"
									+"<input type='hidden' class='form-control' name='"+varKeyName+"_units[]' value='"+unitId+"'>"
								   +"<label class='form-control no-border'>"+units+"</label>"
								+"</div>"
							 +"</div>"
							+"</div>"
							
							ii++;

				});
				$("#append_"+varKeyName).append(html);
			 }
		},
		error : function(xhr, type) {
		alert('server error occoured');
	},
		async: false
	});
}

function saveData(el,type){
	var consent_no = $("input[name=consent_no]").val();
	var idList = new Array();
	var listquantity = new Array();
	var listunits = new Array();
	var i = 0, flag = 0;
	
	var checkedBoxes = $("input[name='"+type+"_checkBox[]']:checked");
    for (var a = 0; a < checkedBoxes.length; a++) {
        var checkBox = checkedBoxes[a].value;

        idList.push(checkBox);

        var inps = $("input[name='"+type+"_quantity[]']")[a];
        var inps1 = $("[name='"+type+"_units[]']")[a];
        
        var quantity = inps.value;
        var unit = inps1.value;

        if (quantity == "" || quantity == 0) {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            listquantity.push(quantity);
        }
        
        listunits.push(unit);
    }
    if(type == "Product"){
    	type = "product";
	} else if(type == "rawMaterial"){
		type = "raw";
	} else if(type == "Fuel"){
		type = "fuel";
	} 
    if(flag == 0 && idList.length > 0){
    	$.ajax({
			type : "POST",
			url : "ajax-olistproduct-savedata?type="+type,
			data : {
				consentId : consent_no,
				allProductNameId : idList.toString(),
				listquantity : listquantity.toString(),
				listunits : listunits.toString()
			},
			success : function(data) {
				$("#save-" + type + "-btn").attr("disabled", true);
				findNextAccordion(el);
				jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
				
				
				if(type == "cutfill"){
                	window.location.replace("http://localhost:8080/env/dashboard");
                }
			}
		});
    	
    } else {
    	jBoxBottomRightBigNotice("Warning","Check atleast one data !!", "yellow", "3500");
    }
}

function saveStackData(el,type){
	var consent_no = $("input[name=consent_no]").val();
	var stack_id = new Array();
	$("input[name='stack_id[]']").each(function() {    
		if($(this).is(':checked')){
			stack_id.push($(this).val());
		}
	});
	if(stack_id.length > 0){
		$.ajax({
			type : "POST",
			url : "ajax-edit-olist-stack-c2o?action=stackfinish",
			data : {
				consentNo : consent_no,
				stackId : stack_id.toString()
			},
			success : function(data) {
				$("#save-" + type + "-btn").attr("disabled", true);
				findNextAccordion(el);
				jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
			}
		});
	} else {
		jBoxBottomRightBigNotice("Warning","Check atleast one data !!", "yellow", "3500");
	}
}
function saveAmbientData(el,type){
	var consent_no = $("input[name=consent_no]").val();
	var ambient_id = new Array();
	$("input[name='ambient_id[]']").each(function() { 
		if($(this).is(':checked')){
			ambient_id.push($(this).val());
		}
	});
	
	if(ambient_id.length > 0){
		$.ajax({
			type : "POST",
			url : "ajax-edit-olist-ambient-c2o",
			data : {
				consentId : consent_no,
				ambientId : ambient_id.toString()
				},
			success : function(data) {
				$("#save-" + type + "-btn").attr("disabled", true);
				findNextAccordion(el);
				jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
			}
		});
	} else {
		jBoxBottomRightBigNotice("Warning","Check atleast one data !!", "yellow", "3500");
	}
			
}

function savePollutantData(el,type){
	var consent_no = $("input[name=consent_no]").val();
	var eff_pollutant = new Array();
	var sew_pollutant = new Array();
	var flag = 0;
	$("input[name='eff_pollutant[]']").each(function() {    
		if($(this).is(':checked')){
			 eff_pollutant.push($(this).val());
			 flag++;
		}
	});
	$("input[name='sew_pollutant[]']").each(function() {    
		if($(this).is(':checked')){
			 sew_pollutant.push($(this).val());
			 flag++;
		}
	});
	if(flag > 0){
		$.ajax({
			type : "POST",
			url : "ajax-edit-olist-water-pollutant-c2o?action=waterpollutant",
			data : {
				consentNo : consent_no,
				effPollutant : eff_pollutant.toString(),
				sewPollutant : sew_pollutant.toString()
			},
			success : function(data) {
				$("#save-" + type + "-btn").attr("disabled", true);
				findNextAccordion(el);
				jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
			}
		});
	} else {
		jBoxBottomRightBigNotice("Warning","Check atleast one data !!", "yellow", "3500");
	}
}
function saveConGenData(el, type){
	var consent_no = $("input[name=consent_no]").val();
	var purpose = new Array();
	var consum = new Array();
	var gen = new Array();
	$("input[name='purpose[]']").each(function() {    
		purpose.push($(this).val());
	});
	$("input[name='consum[]']").each(function() {    
		consum.push($(this).val());
	});
	$("input[name='gen[]']").each(function() {    
		gen.push($(this).val());
	});
	$(document).ready(function() {
			$.ajax({
				type : "POST",
				url : "ajax-edit-olist-Water-cons-and-wastewater-gen-c2o?action=watercongen",
				data : {
					consentNo : consent_no,
					purpose : purpose.toString(),
					consum : consum.toString(),
					gen : gen.toString()
				},
				success : function(data) {
					$("#save-" + type + "-btn").attr("disabled", true);
	                findNextAccordion(el);
	                jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
				}
			});
		});
}
function openConsentForm(){
	var multipleOperate = $("#multipleOperate").val();
	multipleOperate = "Yes";
	if(multipleOperate == "Yes"){
		swal({
			  text: "Do you want to add more operate?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  confirmButtonText: "Yes",
			  cancelButtonText: "No",
			  allowOutsideClick: false
			})
			.then((res) => {
			  if (res.value == true) {
				  jBoxBottomRightBigNotice("Success", "You will be redirected to consent form", "green", "3000");
				  setTimeout(function(){
					  window.location="create-consent";
					}, 4000);
				  
			  } else if (res.dismiss == "cancel") {
				  jBoxBottomRightBigNotice("Success", "You will be redirected to admin dashboard", "green", "3000");
				  setTimeout(function(){
					  window.location="envrOfficerDesk";
					}, 4000);
			  }
			});
	}else{
		
	}
}
function abcc(){
	alert("k");
}
function conSum(el) {
    var dom = $("#domes_consum").val();
    var ecSum = +(document.getElementById("biodeg_consum").value) + +(document.getElementById("notbiodeg_consum").value) + +(document.getElementById("ind_consum").value);
    cSum = +dom + +ecSum;
    $("#cRes").val(cSum);
    $("#dcRes").val(dom);
    $("#ecRes").val(ecSum);
}

function genSum(id) {
    var domgen = document.getElementById("domes_gen").value;
    var egSum = +(document.getElementById("biodeg_gen").value) + +(document.getElementById("notbiodeg_gen").value) + +(document.getElementById("ind_gen").value);
    gSum = +domgen + +egSum;
    $("#gRes").val(gSum);
    $("#dgRes").val(domgen);
    $("#egRes").val(egSum);
}

function selectAll(id,type) {
	isstackcheckboxcount = 0;
	if(id.checked) {
		$('.'+type+'checkbox').each(function() {
			this.checked = true;
			isSelectAll = true;
			isstackcheckboxcount++;
		});
	}else {
		$('.'+type+'checkbox').each(function() {
			this.checked = false;
			isSelectAll = false;
			isstackcheckboxcount++;
		});
	}
}
function isChecked(id, type){
	if(id.checked) {
		id.checked = true;
		isstackcheckboxcount++;
		if(isSelectAll == true && isstackcheckboxcount == stackcheckboxcount){
			$('#'+type+'-select-all').attr('checked', true);
		}else{
			$('#'+type+'-select-all').attr('checked', false);
		}
	}else {
		id.checked = false;
		isstackcheckboxcount--;
		if(isSelectAll == true && isstackcheckboxcount < stackcheckboxcount){
			$('#'+type+'-select-all').attr('checked', false);
		}else{
			$('#'+type+'-select-all').attr('checked', true);
		}
	}
}
function customInputValidator(eleValue, eleName) {
    var flag = 0;
    if (eleValue == "") {
        if (eleName[0] == '#') {
            $(eleName).addClass("is-invalid");
        } else {
            $("input[name=" + eleName + "]").addClass("is-invalid");
        }
        flag--;
    } else {
        if (eleName[0] == '#') {
            $(eleName).removeClass("is-invalid");
        } else {
            $("input[name=" + eleName + "]").removeClass("is-invalid");
        }
    }
    return flag;
}

function customSelectValidator(eleValue, eleName) {
    var flag = 0;
    var errSelector = ".invalid-feedback";
    if (eleValue == "") {
        if (eleName[0] == '#') {
            $(eleName).parent().find(errSelector).addClass("d-block");
        } else {
            $("[name=" + eleName + "]").parent().find(errSelector).addClass("d-block");
        }
        flag--;
    } else {
        if (eleName[0] == '#') {
            $("#" + eleName).parent().find(errSelector).removeClass("d-block");
        } else {
            $("[name=" + eleName + "]").parent().find(errSelector).removeClass("d-block");
        }
    }
    return flag;
}
function findNextAccordion(el) {
    var selector = ".ui-accordion-header";
    var accordionId = $(el).closest('.ui-accordion-content').attr('id');
    var spl = Number(accordionId.split("-")[2]);
    spl--;
    var nextAcc = "ui-id-" + spl;

    var elementList = document.querySelectorAll(selector);

    for (var i = 0; i < elementList.length - 1; i++) {
        var tttt = elementList[i].id;
        if (tttt == nextAcc) {
            var triggEle = elementList[i + 1].id;
            $("#" + triggEle).trigger("click");
        }
    }
}
