var act_value = "";
var consentNo = "";
var selectedYear = "";
var esrMonth = "";

var companyCategory = ($('#company_category').val()).split("-");
var consentid = $('#consent_id').val();
var esrConsentDate = $("#esrConsentDate").val();
var dateFrom = $('#date_from').val();
var dateTo = $('#date_to').val();
var esrMonth = $('#esr_month').val();
var selected_year = $('#selected_year').val();
var workingDays = $('#working_days').val();
var workingHr = $('#working_hr').val();
var Category = $.trim(companyCategory[0]);


getMonthlyESRValues(Category,"product",dateFrom,dateTo,esrMonth,workingDays,workingHr,esrConsentDate);
getMonthlyESRValues(Category,"byproduct",dateFrom,dateTo,esrMonth,workingDays,workingHr,esrConsentDate);
getMonthlyESRValues(Category,"Fuel",dateFrom,dateTo,esrMonth,workingDays,workingHr,esrConsentDate);
getESRWaterConsumption(selected_year,esrMonth);
getProductWaterConsumption(selected_year,esrMonth);
getRawMaterialWaterConsumption(selected_year,esrMonth);
getEffluentPollutant(selected_year,esrMonth);
getSewagePollutant(selected_year,esrMonth);
getAirStack(selected_year,esrMonth);
getMonthlyHazardousAndNonHazardousDataMonth("hwp",selected_year,esrMonth);
getMonthlyHazardousAndNonHazardousDataMonth("hwpcf",selected_year, esrMonth);
getMonthlyHazardousAndNonHazardousDataMonth("nhwp",selected_year,esrMonth);
getMonthlyHazardousAndNonHazardousDataMonth("nhwpcf",selected_year, esrMonth);

function getMonthlyESRValues(category, type, datefrom, dateto, month, workingDays,workingHr,esrConsentDate){
	var fwd_url = "ajax-getMonthlyESRValues";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			category : category,
			type : type,
			datefrom : datefrom,
			dateto : dateto,
			month : month,
			workingDays : workingDays,
			workingHr : workingHr,
			esrConsentDate : esrConsentDate
		}),

		success : function(data) {
			if (data.length != 0) {
				var parseData = JSON.parse(data);
				$('#'+type+'-information').empty(); //Clear div when new load
				
				$.each(parseData, function(index, element) { 
					var productName=element.product_name;
					if(productName!="NA"){
					var tableRaw = $("<tr><td>"
								+"<label>"+element.product_name+"</label>"
								+"</td>"
								+"<td>"
								+"<label>"+element.consent_quantity+"</label>"
								+"</td>"
								+"<td>"
								+"<label>"+element.quantity+"</label>"
								+"</td>"
								+"<td>"
								+"<label>"+element.unitp+"</label>"
								+"</td></tr>")
					$("#"+type+"_info").append(tableRaw); 
					}
					else if(productName=="NA")
						{
						$("#"+type+"-information").empty();
						
						var div = $("<div class='col-sm-3'><label><b>NA</b></label></div>"
									+"<div class='col-sm-3'><label>NA</label></div>"
									+"<div class='col-sm-3'><label>NA</label></div>"
									+"<div class='col-sm-3'><label><b>NA</b></label></div>")
								$("#"+type+"_info").append(div); 
						}
				});
			}
			
		}
	});
}
function getESRWaterConsumption(selected_year,esrMonth){
	var fwd_url = "ajax-getESRWaterConsumption";
	
	var list1 = ["Process", "Cooling", "Domestic", "All-others"];
	var list2 = ["Biodegradable", "cooling", "domestic", "Others"];
	var process_cons1 = 0;
		$.ajax({
			type : 'POST',
			url : fwd_url,
			data : ({
				processCons : process_cons1,
				selectedYear : selected_year,
				esrMonth : esrMonth
			}),

			success : function(data) {
				if (data.length != 0) {
				var parseData = JSON.parse(data);
				//$('#'+list1[i]+'-esrdiv').empty(); //Clear div when new load
				$.each(parseData, function(index, element) { 
					var pro_cons = element.constantValue;
					var pro_avg = element.ActualQuantity;
					
					pro_cons = (+pro_cons).toFixed(2);
					pro_avg = (+pro_avg).toFixed(2);
				
					var div = $("<tr><td>"
						+"<label><b>"+element.Namee+"</b></label>"
						+"</td>"
						+"<td>"
						+"<label id='"+element.Namee+"_cons' class='vals1'>"+pro_cons+"</label>"
						+"</td>"
						+"<td>"
						+"<label id='"+element.Namee+"_avg' class='vals2'>"+pro_avg+"</label>"
						+"</td></tr>")
					$("#water_info").append(div); 
				});
				//divsum();
				}
		},
		error : function(xhr, type) {
		alert('server error occoured at getESRWaterConsumption');
	},
		async: false
	});
}
function getProductWaterConsumption(esrYear,esrMonth){
	$("#div_esr_product_consumption").empty();
	var fwd_url = "ajax-getProcessWaterConsumption";
	var productType = "product";
		$.ajax({
			type : 'POST',
			url : fwd_url,
			data : ({
				esrYear : esrYear,
				esrMonth: esrMonth,
				productType : productType
			}),
			success : function(data) {
			
				var parseData = JSON.parse(data);
				
				$.each(parseData, function(index, element) { 
					
					var productName = element.productName;
					var previousData = element.previousData;
					var currentData = element.currentData;
					var productCount = element.productCount;
					var ip = element.ip;
					var productType=element.productType;
					var year=element.selectedYear;
					var month=element.esrMonth;
					$("#esrmonthly_product_water_consumption_button").hide();
					if(ip=="NA")
						{
						$("#esrmonthly_product_water_consumption_button").show();
						}
					// to fill data
					if(ip == "NA"||ip==""){
					
						var tabledata = $("<tr><td>"
											+"<input type='hidden' class='form-control' name='product_name[]' id='"+productType+"_"+ip+"' value='"+productName+"'>"
											+"<label>"+productName+"</label>"
										+"</td>"
										+"<td>"
										+"<div class='form-group mb-0'>"
											+"<input type='text' class='form-control' name='prev_data[]'  id='"+productType+"_prev_"+ip+"' required>"
											+"<div class='invalid-feedback'>Required !</div>"
											+"<i class='form-group__bar'></i>"
											+"</div>"
											+"</td>"
										+"<td>"
										+"<div class='form-group mb-0'>"
											+"<input type='text' class='form-control' name='curr_data[]' id='"+productType+"_curr_"+ip+"' required>"
											+"<div class='invalid-feedback'>Required ! !</div>"
											+"<i class='form-group__bar'></i>"
											+"</div>"
											+"</td>"
										+"<td>"
											+"<input type='hidden' class='form-control' name='unit_product[]'  id='"+productType+"_units_"+ip+"' value='CMD'>"
											+"<label>CMD</label>"
										+"</td></tr>")
						var div = $("<input type='hidden' id='is_product_month' value='Yes'>"
									+"<div class='col-sm-12'>"
									+"<div class='col-sm-3'>"
									+"<input type='hidden' class='form-control' id='"+productType+"t_count' value='"+productCount+"'>"
								    +"<input type='hidden' class='form-control' id='product_type' name='product_type' value='"+productType+"'>"
								    +"<input type='hidden' class='form-control' id='product_year' name='product_year' value='"+year+"'>"
								    +"<input type='hidden' class='form-control' id='product_month' name='product_month' value='"+month+"'>"
								    +"</div>"
								    +"</div>")
								    
								    
								$("#div_esr_product_consumption").append(tabledata); 
						        $("#hidden_esr_product").append(div); 
						
					}
										
					// to show data
					if(ip == "SHOWDATA"){
						
						var div1 = $("<tr><td><input type='hidden' id='is_"+productType+"' value='No'>"
												+"<label>"+productName+"</label>"
											+"</td>"
											+"<td>"
												+"<label>"+previousData+"</label>"
											+"</td>"
											+"<td>"
												+"<label>"+currentData+"</label>"
											+"</td>"
											+"<td>"
												+"<label>CMD</label>"
									+"<input type='hidden' class='form-control' id='"+productType+"_count' value='"+productCount+"'></td>")
							
						$("#div_esr_product_consumption").append(div1); 
					} 
					if(productName=="NA")
					{
					var div = $("<tr><td>"
							+"<label>NA</label>"
						+"</td>"
						+"<td>"
							+"<label>NA</label>"
						+"</td>"
						+"<td>"
							+"<label>NA</label>"
						+"</td>"
						+"<td>"
							+"<label>NA</label>"
						+"</td>"
						+"</tr>")
				
						$("#div_esr_product_consumption").append(div);
					$("#esrmonthly_product_water_consumption_button").hide();
					}
				});
		},
		error : function(xhr, type) {
		alert('server error occoured at getProductWaterConsumption');
	},
		async: false
	});
}
function saveProductMonthESR() {
	var productList = [];var flag=0;
	var productName = document.getElementsByName('product_name[]');
	for (var i = 0; i < productName.length; i++) {
		var inp = productName[i];
		productList.push(inp.value);
	}
	var prevDataList = [];
	var prevData = document.getElementsByName('prev_data[]');
	for (var i = 0; i < prevData.length; i++) {
		var inp = prevData[i];
		 if (inp.value == "") {
	            flag--;
	            inp.classList.add("is-invalid");
	        } else {
	        	prevDataList.push(inp.value);
	        	inp.classList.remove("is-invalid");
	        }
		
	}
	var currDataList = [];
	var currData = document.getElementsByName('curr_data[]');
	for (var i = 0; i < currData.length; i++) {
		var inp = currData[i];
		if (inp.value == "") {
            flag--;
            inp.classList.add("is-invalid");
        } else {
        	currDataList.push(inp.value);
        	inp.classList.remove("is-invalid");
        }
		
	}
	var unitList = [];
	var unit = document.getElementsByName('unit_product[]');
	for (var i = 0; i < unit.length; i++) {
		var inp = unit[i];
		unitList.push(inp.value);
	}
	var month = $('#product_month').val();
	var year = $('#product_year').val();
	var productType = $('#product_type').val();
	var fwd_url1 = "ajax-save-product-esr-water-monthly";
	var dateFrom = "";
	var dateTo = "";
	if(flag == 0){
		$.ajax({
			type : 'POST',
			url : fwd_url1,
			data : ({
				productList : productList.toString(),
				prevDataList : prevDataList.toString(),
				currDataList : currDataList.toString(),
				unitList : unitList.toString(),
				productType : productType,
				year : year,
				month : month
			}),

			success : function(data) {
				if (data != null) {
					jBoxBottomRightBigNotice("Success", "ESR Monthly Product Data Saved !!", "green", "2000");
					var parseData = JSON.parse(data);
					// console.log("in esr >>"+parseData);
					$.each(parseData, function(index, element) {
						year = element.selectedYear;
						month = element.esrMonth;

					});
					getProductWaterConsumption(year, month);
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			},
			error : function(e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
				window.setTimeout(window.location = "logout", 7000)
			}
		});
	}
	
}
function getRawMaterialWaterConsumption(esrYear,esrMonth){
	$("#div_esr_rawmaterial_consumption").empty();
	var fwd_url = "ajax-getProcessWaterConsumption";
	var productType = "Raw Material";
	  var type="rawmaterial";
		$.ajax({
			type : 'POST',
			url : fwd_url,
			data : ({
				esrYear : esrYear,
				esrMonth: esrMonth,
				productType : productType
			}),

			success : function(data) {
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) { 
					
					var productName = element.productName;
					var previousData = element.previousData;
					var currentData = element.currentData;
					var productCount = element.productCount;
					var ip = element.ip;
					var productType=element.productType;
					var year=element.selectedYear;
					var month=element.esrMonth;
					$("#esrmonthly_product_rawmaterial_button").hide();
					if(ip=="NA")
						{
						$("#esrmonthly_product_rawmaterial_button").show();
						}
					// to fill data
					if(ip == "NA"||ip==""){
						var table = $("<tr><td>"
											+"<input type='hidden' class='form-control' name='rawmaterial_product_name[]' id='"+"rawmaterial_"+ip+"' value='"+productName+"'>"
											+"<label>"+productName+"</label>"
										+"</td>"
										+"<td>"
										+"<div class='form-group mb-0'>"
											+"<input type='text' class='form-control' name='rawmaterial_prev_data[]'  id='"+"rawmaterial_prev_"+ip+"' required>"
											+"<div class='invalid-feedback'>Required !</div>"
											+"<i class='form-group__bar'></i>"
											+"</div>"
											+"</td>"
										+"<td>"
										+"<div class='form-group mb-0'>"
											+"<input type='text' class='form-control' name='rawmaterial_curr_data[]' id='"+"rawmaterial_curr_"+ip+"' required>"
											+"<div class='invalid-feedback'>Required !</div>"
											+"<i class='form-group__bar'></i>"
										+"</div>"
										+"</td>"
										+"<td>"
											+"<input type='hidden' class='form-control' name='rawmaterial_unit_product[]'  id='"+"rawmaterial_units_"+ip+"' value='CMD'>"
											+"<label>CMD</label>"
										+"</td><tr>")
										
										var div = $("<input type='hidden' id='is_raw_month' value='Yes'>"
									+"<div class='col-sm-12'>"
									+"<div class='col-sm-3'>"
									+"<input type='hidden' class='form-control' id='"+productType+"t_count' value='"+productCount+"'>"
								    +"<input type='hidden' class='form-control' id='raw_product_type' name='raw_product_type' value='"+productType+"'>"
								    +"<input type='hidden' class='form-control' id='raw_year' name='raw_year' value='"+year+"'>"
								    +"<input type='hidden' class='form-control' id='raw_month' name='raw_month' value='"+month+"'>"
								    +"</div>"
								    +"</div>")
								
								$("#div_esr_rawmaterial_consumption").append(table); 
						        $("#hidden_esr_rawmaterial").append(div); 
					}
										
					// to show data
					if(ip == "SHOWDATA"){
						var div = $("<tr>"
									+"<td>"
										+"<label>"+productName+"</label>"
									+"</td>"
									+"<td>"
										+"<label>"+previousData+"</label>"
									+"</td>"
									+"<td>"
										+"<label>"+currentData+"</label>"
									+"</td>"
									+"<td>"
										+"<label>CMD</label>"
									+"</td></tr>"
							+"<input type='hidden' class='form-control' id='"+productType+"_count' value='"+productCount+"'>")
				$("#div_esr_rawmaterial_consumption").append(div); 
					} 
					if(productName=="NA")
					{
					var div = $("<tr><td>"
							+"<label>NA</label>"
						+"</td>"
						+"<td>"
							+"<label>NA</label>"
						+"</td>"
						+"<td>"
							+"<label>NA</label>"
						+"</td>"
						+"<td>"
							+"<label>NA</label>"
						+"</td></tr>")
				
						$("#div_esr_rawmaterial_consumption").append(div);
					$("#esrmonthly_product_rawmaterial_button").hide();
					}
				});
		},
		error : function(xhr, type) {
		alert('server error occoured at getRawMaterialWaterConsumption');
	},
		async: false
	});
}
function saveRawMaterialMonthESR()
{
	var productList=[];var flag = 0;
	var productName = document.getElementsByName('rawmaterial_product_name[]');
	for (var i = 0; i < productName.length; i++) {
		var inp = productName[i];
		productList.push(inp.value);
	}

	var prevDataList = [];
	var prevData = document.getElementsByName('rawmaterial_prev_data[]');
	for (var i = 0; i < prevData.length; i++) {
		var inp = prevData[i];
		 if (inp.value == "") {
	            flag--;
	            inp.classList.add("is-invalid");
	        } else {
	        	prevDataList.push(inp.value);
	        	inp.classList.remove("is-invalid");
	        }
		
	}
	
	var currDataList = [];
	var currData = document.getElementsByName('rawmaterial_curr_data[]');
	for (var i = 0; i < currData.length; i++) {
		var inp = currData[i];
		if (inp.value == "") {
            flag--;
            inp.classList.add("is-invalid");
        } else {
        	currDataList.push(inp.value);
        	inp.classList.remove("is-invalid");
        }
		
	}
	
	var unitList = [];
	var unit = document.getElementsByName('rawmaterial_unit_product[]');
	for (var i = 0; i < unit.length; i++) {
		var inp = unit[i];
		unitList.push(inp.value);
	}
	var month = $('#raw_month').val();
	var year = $('#raw_year').val();
	var productType = $('#raw_product_type').val();
	var fwd_url1="ajax-save-product-esr-water-monthly";
	var dateFrom="";
	var dateTo="";
	if(flag == 0)
		{
		$.ajax({
			type : 'POST',
			url : fwd_url1,
			data : ({
				productList : productList.toString(),
				prevDataList : prevDataList.toString(),
				currDataList : currDataList.toString(),
				unitList : unitList.toString(),
				productType : productType,
				year : year,
				month : month
			}),

			success : function(data) {
				jBoxBottomRightBigNotice("Success", "Process Consumption of Product Data Saved !!", "green","2000");
				if(data!=null){
				var parseData = JSON.parse(data);
				//console.log("in esr >>"+parseData);
				$.each(parseData, function(index, element) { 
					year=element.selectedYear;
					month=element.esrMonth;
					
				});
				getRawMaterialWaterConsumption(year,month);
				}else{
					jBoxBottomRightBigNotice("Error","Oopss !! something went wrong", "red","2000");
				}
			},
			error : function(e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
				window.setTimeout(window.location = "logout",7000)
			}
			});
		}
	
}
function getEffluentPollutant(selectedYear,esrMonth){
	var fwd_url = "ajax-getEffluentPollutant";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selectedYear,
			esrMonth : esrMonth
		}),
		//var tabled = "<tr><td>Effulant Polutant</td></tr>";
		success : function(data) {
			if (data.length != 0) {
			var parseData = JSON.parse(data);
			var regRule = /[ )(]/g;
			$.each(parseData, function(index, element) {
				var pollName = element.PollName;
				var bg_red ="";
				
				if (element.Concentration > element.Standard) {
					bg_red = "danger-color";
					if(element.reason == "NA"){
						var reasons = "<div class='form-group'>"
							 +"<input type='text' class='form-control' name='eff_reasons[]'>"
							 +"<div class='invalid-feedback'>Required !</div>"
							 +"<i class='form-group__bar'></i>"
							 +"</div>";
					}else{
						$("#effSaveMonthly").hide();
						var reasons = element.reason;
					}
				}else{
					if(element.reason == "NA"){
						var reasons = "<div class='form-group'>"
							 +"<input type='text' class='form-control' name='eff_reasons[]' value='NA' disabled>"
							 +"<div class='invalid-feedback'>Required !</div>"
							 +"<i class='form-group__bar'></i>"
							 +"</div>";
					}else{
						$("#effSaveMonthly").hide();
						var reasons = element.reason;
					}
				}
				
				
				var Variation = element.variation;
				if(Variation == "NA")
					{
					   Variation = "-";
					}
				
				 
				if(pollName != "NODATA") {
					
					var	 tabledata = $("<tr><td>"
							+"<label>"+pollName+"</label>"
							+"<input type='hidden' name='eff_poll_name[]' value='"+pollName+"'/>"
						+"</td>"
						+"<td>"
							+"<label>"+element.Quantity+"</label>"
						+"</td>"
						+"<td>"
							+"<label>"+element.Concentration+"</label>"
						+"</td>"
						+"<td>"
							+"<label class="+bg_red+">"+Variation+"</label>"
						+"</td>"
						+"<td>"
							+"<label>"+element.Standard+"</label>"
						+"</td>"
						+"<td>"
							+"<label>"+reasons+"</label>"
						+"</td></tr>")
				}else{
					var tabledata = $("<input type='hidden' name='esrMonth' value='"+esrMonth+"'/>"
							+"<tr>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label>0</label>"
							+"</td>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label><b>NA</b></label>"
							+"</td>"
						+"</tr>")
					}
				var div = $("<input type='hidden' name='esrMonth' value='"+esrMonth+"'/>"
						+"<input type='hidden' name='poll_name[]' value='"+pollName+"'/>"
						+"<input type='hidden' name='dateFrom' value='"+dateFrom+"'/>"
						+"<input type='hidden' name='dateTo' value='"+dateTo+"'/>")
						
				$("#esr-div-effluent-pollutant").append(tabledata); 
				$("#esr-div-effluentHidden-pollutant").append(div); 
			});
			}
			
	},
	error : function(xhr, type) {
		alert('server error occoured at getEffPollutant');
	},
	async: false
	});
	
}
function getSewagePollutant(selectedYear,esrMonth){
	
	var fwd_url = "ajax-getSewagePollutant";
	
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selectedYear,
			esrMonth : esrMonth
		}),

		success : function(data) {
			if (data.length != 0) {
			var parseData = JSON.parse(data);
			var regRule = /[ )(]/g;
			$.each(parseData, function(index, element) {
				var bg_red ="";
				var pollName = element.PollName;
				if (element.Concentration > element.Standard) {
					bg_red = "danger-color";
					if(element.reason == "NA"){
						var reasons = "<div class='form-group'>"
									 +"<input type='text' class='form-control' name='sew_reasons[]'>"
									 +"<div class='invalid-feedback'>Required !</div>"
									 +"<i class='form-group__bar'></i>"
									 +"</div>";
					}else{
						$("#effSaveMonthly").hide();
						var reasons = element.reason;
					}
				}else{
					if(element.reason == "NA"){
						var reasons = "<div class='form-group'>"
									 +"<input type='text' class='form-control' name='sew_reasons[]' value='NA' disabled>"
									 +"<div class='invalid-feedback'>Required !</div>"
									 +"<i class='form-group__bar'></i>"
									 +"</div>";
					}else{
						$("#effSaveMonthly").hide();
						var reasons = element.reason;
					}

				}
				
				
				var Variation = element.variation;
				if(Variation == "NA")
					{
					   Variation = "-";
					}
				
                         if(pollName != "NODATA") {
					
					var	 tabledata = $("<tr><td>"
							+"<label>"+pollName+"</label>"
							+"<input type='hidden' name='sew_poll_name[]' value='"+pollName+"'/>"
						+"</td>"
						+"<td>"
							+"<label>"+element.Quantity+"</label>"
						+"</td>"
						+"<td>"
							+"<label>"+element.Concentration+"</label>"
						+"</td>"
						+"<td>"
							+"<label class="+bg_red+">"+Variation+"</label>"
						+"</td>"
						+"<td>"
							+"<label>"+element.Standard+"</label>"
						+"</td>"
						+"<td>"
							+"<label>"+reasons+"</label>"
						+"</td></tr>")
				}else{
					var tabledata = $("<input type='hidden' name='esrMonth' value='"+esrMonth+"'/>"
							+"<tr>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label>0</label>"
							+"</td>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label>NA</label>"
							+"</td>"
							+"<td>"
								+"<label><b>NA</b></label>"
							+"</td>"
						+"</tr>")
					}
                         var div =$("<input type='hidden' name='dateFrom' value='"+dateFrom+"'/>"
     							+"<input type='hidden' name='dateTo' value='"+dateTo+"'/>")
     							
     						$("#esr-div-sewageHidden-pollutant").append(div); 
                         $("#esr-div-sewage-pollutant").append(tabledata);
			});
			}
			
			
	},
	error : function(xhr, type) {
		alert('server error occoured at getSewagePollutant');
	},
	async: false
	});
}


function saveEffSewResonMothly(){
	 var pollNameSew = document.getElementsByName("sew_poll_name[]");
	 var sewResoan = document.getElementsByName("sew_reasons[]");
	 var pollNameEff = document.getElementsByName("eff_poll_name[]");
	 var effResoan = document.getElementsByName("eff_reasons[]");
	 var esrMonth = $('#esr_month').val();
	 var selected_year = $('#selected_year').val();
	 var sewPollNameList = []; var sewResoanList = []; var effPollNameList = []; var effResoanList = [];
	 for (var i = 0; i < pollNameEff.length; i++) {
			var inp = pollNameEff[i];
			var inp1 = effResoan[i]
			effPollNameList.push(inp.value);
			if (inp1.value == "") {
                inp1.classList.add("is-invalid");
                return;
            } else {
            	effResoanList.push(inp1.value);
                inp1.classList.remove("is-invalid");
            }
			
		}
	 
	 for (var i = 0; i < pollNameSew.length; i++) {
			var inp = pollNameSew[i];
			var inp1 = sewResoan[i]
			sewPollNameList.push(inp.value);
			if (inp1.value == "") {
             inp1.classList.add("is-invalid");
             return;
         } else {
        	 sewResoanList.push(inp1.value);
             inp1.classList.remove("is-invalid");
         }
			
		}
	 
	 $.ajax({
			type : "POST",
			url : "ajax-saveEffluentReasonMonthly",
			data : ({
				sewPollNameList: sewPollNameList.toString(),
				sewResoanList:sewResoanList.toString(),
				effPollNameList: effPollNameList.toString(),
				effResoanList: effResoanList.toString(),
				selectedYear : selected_year,
				esrMonth: esrMonth
			}),

			success : function(data) {
				
			},
			error : function(xhr, type) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
				window.setTimeout(window.location = "logout",7000)
			},
			async: false
		});
	
}

function getAirStack(selectedYear,month){
	var fwd_url = "ajax-getAirStack";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selectedYear,
			esrMonth : esrMonth
		}),

		success : function(data) {
			if (data.length != 0) {
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var bg_red ="";
					var pollName = element.PollName;
					if (element.Concentration > element.Standard) {
						bg_red = "danger-color";
						if(element.reason == "NO"){
							var reasons = "<div class='form-group'>"
										 +"<input type='text' class='form-control' name='sew_reasons[]'>"
										 +"<div class='invalid-feedback'>Required !</div>"
										 +"<i class='form-group__bar'></i>"
										 +"</div>";
						}else{
							$("#airSaveMonthly").hide();
							var reasons = element.reason;
						}
					}else{
						if(element.reason == "NO"){
							var reasons = "<div class='form-group'>"
										 +"<input type='text' class='form-control' name='sew_reasons[]' value='NA' disabled>"
										 +"<div class='invalid-feedback'>Required !</div>"
										 +"<i class='form-group__bar'></i>"
										 +"</div>";
						}else{
							$("#airSaveMonthly").hide();
							var reasons = element.reason;
						}

					}
					
					
					var Variation = element.variation;
					if(Variation == "NA")
						{
						   Variation = "-";
						}
					
	                         if(pollName != "NODATA") {
						
						var	 tabledata = $("<tr><td>"
								+"<label>"+pollName+"</label>"
								+"<input type='hidden' name='sew_poll_name[]' value='"+pollName+"'/>"
							+"</td>"
							+"<td>"
								+"<label>"+element.Quantity+"</label>"
							+"</td>"
							+"<td>"
								+"<label>"+element.Concentration+"</label>"
							+"</td>"
							+"<td>"
								+"<label class="+bg_red+">"+Variation+"</label>"
							+"</td>"
							+"<td>"
								+"<label>"+element.Standard+"</label>"
							+"</td>"
							+"<td>"
								+"<label>"+reasons+"</label>"
							+"</td></tr>")
					}else{
						var tabledata = $("<input type='hidden' name='esrMonth' value='"+esrMonth+"'/>"
								+"<tr>"
								+"<td>"
									+"<label>NA</label>"
								+"</td>"
								+"<td>"
									+"<label>0</label>"
								+"</td>"
								+"<td>"
									+"<label>NA</label>"
								+"</td>"
								+"<td>"
									+"<label>NA</label>"
								+"</td>"
								+"<td>"
									+"<label>NA</label>"
								+"</td>"
								+"<td>"
									+"<label><b>NA</b></label>"
								+"</td>"
							+"</tr>")
						}
	     							
	     						$("#esr-div-Stack-pollutant").append(tabledata); 
				});
			}
		},
	error : function(xhr, type) {
		alert('server error occoured at getAirStack');
	},
	async: false
	});
}

function getMonthlyHazardousAndNonHazardousDataMonth(ptype,selected_year, esrMonth)
{
	var fwd_url = "ajax-getMonthlyHazardousProcessData";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			ptype: ptype,
			selectedYear : selected_year,
			esrMonth: esrMonth
		}),

		success : function(data) {
			if (data.length != 0) {
				var parseData = JSON.parse(data);
				$("#esr-"+ptype).empty(); //Clear div when new load
				
				$.each(parseData, function(index, element) {
					var productName=element.HazName;
					if(productName!="NA"){
					var tableRaw = $("<tr><td>"
								+"<label>"+element.HazName+"</label>"
								+"</td>"
								+"<td>"
								+"<label>"+element.PriveousQuantity+"</label>"
								+"</td>"
								+"<td>"
								+"<label>"+element.CurrentQuantity+"</label>"
								+"</td>"
								+"<td>"
								+"<label>"+element.Unit+"</label>"
								+"</td></tr>")
					$("#esr-"+ptype).append(tableRaw); 
					}
					else
						{
						 $("#esr-"+ptype).empty();
						
						var div = $("<div class='col-sm-3'><label><b>NA</b></label></div>"
									+"<div class='col-sm-3'><label>NA</label></div>"
									+"<div class='col-sm-3'><label>NA</label></div>"
									+"<div class='col-sm-3'><label><b>NA</b></label></div>")
									$("#esr-"+ptype).append(div);
						}
				});
			}
		}
	});
}


function AddSolidContainer(){
	var randomId = randomIdNumber();
	var hzHtml = "";
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'><div class='col-sm-1'></div>"
				+"<div class='col-sm-3'>"
				+"<label>No. of Containers:</label>"
				+"</div>"
				+"<div class='col-sm-8'>"
				+"<div class='row'><div class='col-5'>"
				+"<div class='form-group'>"
				+"<input type='text' name='containers_Number[]' class='form-control' placeholder='Container Number'>"
				+"<div class='invalid-feedback'>Required ! !</div>"
				+"<i class='form-group__bar'></i></div></div>"
				+"<div class='col-5'><div class='form-group'>"
				+"<input type='text' name='containers_Type[]' class='form-control' placeholder='Type'>"
				+"<div class='invalid-feedback'>Required ! !</div>"
				+"<i class='form-group__bar'></i></div></div>"
				+"<div class='col-2'><div class='form-group'>"
				+"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
				+"<i class='zmdi zmdi-delete'></i> Remove</button>"
				+"</div></div>"
				+"</div></div></div>"
	$(html).insertAfter("#appendContainers");
	makeSelect2();
}
/////mmmmm
//function RecycledDataYear(selected_year){
//	var id ="appendContainers";
//	$("#"+id).empty();
//	var fwd_url = "ajax-getRecycledDataYearm";
//	$.ajax({
//		type : 'POST',
//		url : fwd_url,
//		data : ({
//			selectedYear : selected_year,
//			type:  "Recycled"
//		}),
//
//		success : function(data) {
//			var size= data.length;
//			if(size != 0){
////				$("#esrmonthly_recylced_data").hide();
//				var parseData = JSON.parse(data);
//				$.each(parseData, function(index, element) {
//					var html = "<div class='row'>"
//					   +"<div class='col-3'><div class='form-group'>"
//					   +"<lable>"+element.PollName+"<lable>"
//					   +"<div class='invalid-feedback'>Required ! !</div>"
//					   +"<i class='form-group__bar'></i>"
//					   +"</div></div>"
//					   +"<div class='col-3'><div class='form-group'>"
//					   +"<lable>"+element.PrivieousData+"<lable>"
//					   +"<div class='invalid-feedback'>Required ! !</div>"
//					   +"<i class='form-group__bar'></i>"
//					   +"</div></div>"
//					   +"<div class='col-3'><div class='form-group'>"
//					   +"<lable>"+element.CurrantData+"<lable>"
//					   +"<div class='invalid-feedback'>Required ! !</div>"
//					   +"<i class='form-group__bar'></i>"
//					   +"</div></div>"
//					   +"<div class='col-2'><div class='form-group'>"
//					   +"<lable>"+element.Uom+"<lable>"
//					   +"<div class='invalid-feedback'>Required ! !</div>"
//					   +"<i class='form-group__bar'></i>"
//					   +"</div></div>"
//					   +"<div class='col-1'></div>"
//			           +"</div>"
//			           
//			           $("#"+id).append(html); 
//				
//				})
//			}else{
//				var html = "<div class='row'>"
//				   +"<div class='col-3'><div class='form-group'>"
//				   +"<input type='text' name='WasteName[]' class='form-control' placeholder='Enter Waste Type'>"
//				   +"<div class='invalid-feedback'>Required ! !</div>"
//				   +"<i class='form-group__bar'></i>"
//				   +"</div></div>"
//				   +"<div class='col-3'><div class='form-group'>"
//				   +"<input type='text' name='PreviousMonth[]' class='form-control'>"
//				   +"<div class='invalid-feedback'>Required ! !</div>"
//				   +"<i class='form-group__bar'></i>"
//				   +"</div></div>"
//				   +"<div class='col-3'><div class='form-group'>"
//				   +"<input type='text' name='CurrentMonth[]' class='form-control'>"
//				   +"<div class='invalid-feedback'>Required ! !</div>"
//				   +"<i class='form-group__bar'></i>"
//				   +"</div></div>"
//				   +"<div class='col-2'><div class='form-group'>"
//				   +"<input type='text' name='Uom[]' class='form-control'>"
//				   +"<div class='invalid-feedback'>Required ! !</div>"
//				   +"<i class='form-group__bar'></i>"
//				   +"</div></div>"
//				   +"<div class='col-1'><div class='form-group'>"
//				   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddSolidContainer(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
//				   +"</div></div>"
//		           +"</div>"
//		           
//		           $("#"+id).append(html); 
//			}
//			
//		},
//	error : function(xhr, type) {
//		alert('server error occoured at RecycledDataMonthly');
//	},
//	async: false
//	});
//
//}
//mm
//function saveRecyledData1(){
//	var wasteName=new Array();
//	var PreviousMonth=new Array();
//	var CurrentMonth=new Array();
//	var Uom=new Array();
//	var esrMonth = $('#esr_month').val();
//	var selected_year = $('#selected_year').val();
//	var inps1 = document.getElementsByName("WasteName[]");
//    var inps2 = document.getElementsByName("PreviousMonth[]");
//    var inps3 = document.getElementsByName("CurrentMonth[]");
//    var inps4 = document.getElementsByName("Uom[]");
//    
//    for (var i = 0; i < inps1.length; i++) {
//        var inp1 = inps1[i];
//        var inp2 = inps2[i];
//        var inp3 = inps3[i];
//        var inp4 = inps4[i];
//
//        if (inp1.value == "") {
//            inp1.classList.add("is-invalid");
//            return;
//        } else {
//        	wasteName.push(inp1.value);
//            inp1.classList.remove("is-invalid");
//        }
//
//        if (inp2.value == "") {
//            inp2.classList.add("is-invalid");
//            return;
//        } else {
//        	PreviousMonth.push(inp2.value);
//            inp2.classList.remove("is-invalid");
//        }
//        
//        if (inp3.value == "") {
//            inp3.classList.add("is-invalid");
//            return;
//        } else {
//        	CurrentMonth.push(inp3.value);
//            inp3.classList.remove("is-invalid");
//        }
//        
//        if (inp4.value == "") {
//            inp4.classList.add("is-invalid");
//            return;
//        } else {
//        	Uom.push(inp4.value);
//            inp4.classList.remove("is-invalid");
//        }
//
//    }
//    
//    $.ajax({
//		type : "POST",
//		url : "ajax-saveRecycledMonthly",
//		data : ({
//			selected_year : esrMonth,selected_year,
//			ptype:  "Recycled",
//			recycled: wasteName.toString(),
//			recycled_prev: PreviousMonth.toString(),
//			recycled_curr: CurrentMonth.toString(),
//			recycled_units: Uom.toString()
//			
//		}),
//
//		success : function(data) {
//			$.each(parseData, function(index, element) { 
//				year=element.selectedYear;
//				month=element.esrMonth;
//				
//			});
//			RecycledDataYear(sesrMonth,selected_year);
//		},
//		error : function(xhr, type) {
//			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
//			//window.setTimeout(window.location = "logout",7000)
//		},
//		async: false
//	});
//}
/////
function randomIdNumber() {
	var min = 1;
	var max = 5000;
	return Math.round(Math.random() * (+max - +min) + +min);
}

function removeElement(removeId){
	$("#"+removeId).remove();
}





