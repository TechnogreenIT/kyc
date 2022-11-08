var companyCategory = ($('#industryCat').val()).split("-");
var consentid = $('#consent_id').val();
var esrConsentDate = $("#esrConsentDate").val();
var dateFrom = $('#date_from').val();
var dateTo = $('#date_to').val();
var esrMonth = $('#esr_month').val();
var selected_year = $('#selected_year').val();
var workingDays = $('#working_days').val();
var workingHr = $('#working_hr').val();
var Category = $.trim(companyCategory[0]);

getYearlyESRValues(Category,"product",dateFrom,dateTo,workingDays,esrConsentDate);
getYearlyESRValues(Category,"byproduct",dateFrom,dateTo,workingDays,esrConsentDate);
getYearlyESRValues(Category,"Fuel",dateFrom,dateTo,workingDays,esrConsentDate);
getESRYearlyWaterConsumption(dateFrom, dateTo,selected_year);
getYearlyProductWaterConsumption(dateFrom,dateTo,selected_year);
getYearlyRawMaterialWaterConsumption(dateFrom,dateTo,selected_year);
getYearlyHazardousAndNonHazardousData("hwp",dateFrom,dateTo,selected_year);
getYearlyHazardousAndNonHazardousData("hwpcf",dateFrom,dateTo,selected_year);
getYearlyHazardousAndNonHazardousData("nhwp",dateFrom,dateTo,selected_year);
getYearlyHazardousAndNonHazardousData("nhwpcf",dateFrom,dateTo,selected_year);
RecycledDataYear(selected_year);
SolidDataYear(selected_year);
HazardusDataYear(selected_year);
PolutionControlDataYear(selected_year);
InvestmentEnviroment(selected_year);
InvestmentNextEnviroment(selected_year);
Particular(selected_year);
getESRYearlyWaterGeneration(dateFrom,dateTo);
getEffluentPollutantYear(dateFrom, dateTo, selected_year);
getSewagePollutantYear(dateFrom, dateTo, selected_year);
getAirStackYear(dateFrom, dateTo, selected_year);

function getYearlyESRValues(Category,type,dateFrom,dateTo,workingDays,esrConsentDate){
	var fwd_url = "ajax-getYearlyESRProductValues";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			category : Category,
			type : type,
			datefrom : dateFrom,
			dateto : dateTo,
			workingDays : workingDays,
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

//updated consumption
function getESRYearlyWaterConsumption(dateFrom, dateTo)
{
	var fwd_url = "ajax-getESRYearlyWaterConsumption";
	var process_cons1 = 0;
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			processCons : process_cons1,
			dateFrom: dateFrom,
			dateTo: dateTo,
			selectedYear : selected_year
		}),
		success : function(data) {
			if (data.length != 0) {
			var parseData = JSON.parse(data);
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


function getYearlyProductWaterConsumption(dateFrom,dateTo,selected_year)
{
	$("#div_esryearly_product_water_consumption").empty();
	 var fwd_url = "ajax-getYearlyProcessWaterConsumption";
		
		var productType ="product";
		
			$.ajax({
				type : 'POST',
				url : fwd_url,
				data : ({
					dateFrom: dateFrom,
					dateTo: dateTo,
					selected_year :selected_year,
					productType :productType
				}),

				success : function(data) {
					//console.log("before esr >>"+data);
					var parseData = JSON.parse(data);
					//console.log("in esr >>"+parseData);
					$.each(parseData, function(index, element) { 
						
						var productName = element.productName;
						var previousData = element.previousData;
						var currentData = element.currentData;
						var productCount = element.productCount;
						var ip = element.ip;
						var productType=element.productType;
						var year=element.selectedYear;
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
			alert('server error occoured at getProductWaterConsumption yearly');
		},
			async: false
		});
}
function saveProductYearESR() {
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
	var fwd_url1 = "ajax-save-product-esr-water";
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
					getYearlyProductWaterConsumption(dateFrom, dateTo,selected_year);
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
function getYearlyRawMaterialWaterConsumption(dateFrom,dateTo,selected_year){
	$("#div_esr_rawmaterial_consumption").empty();
	var fwd_url = "ajax-getYearlyProcessWaterConsumption";
	var productType = "Raw Material";
	  var type="rawmaterial";
		$.ajax({
			type : 'POST',
			url : fwd_url,
			data : ({
				dateFrom: dateFrom,
				dateTo: dateTo,
				selected_year :selected_year,
				productType :productType
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
function saveRawMaterialYearESR()
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
	var fwd_url1="ajax-save-product-esr-water";
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
				year : year
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
				getYearlyRawMaterialWaterConsumption(dateFrom,dateTo,selected_year)
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
function getEffluentPollutantYear(dateFrom,dateTo,selected_year){
	var effCons=$('#effConsumption').val();
	var fwd_url = "ajax-getYearlyEffluentPollutant";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			dateFrom : dateFrom,
			dateTo: dateTo,
			selectedYear : selected_year
		}),
		success : function(data) {
			if (data.length != 0) {
			var parseData = JSON.parse(data);
			var regRule = /[ )(]/g;
			$.each(parseData, function(index, element) {
				var pollName = element.PollName;
				var bg_red ="";
				
				if (element.Concentration > element.Standard) {
					bg_red = "danger-color";
					if(element.reason == "NOT"){
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
					if(element.reason == "NOT"){
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
function getSewagePollutantYear(dateFrom, dateTo, selected_year){
	var sewCons=$('#sewConsumption').val();
	var fwd_url = "ajax-getYearlySewagePollutant";
	
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			dateFrom : dateFrom,
			dateTo: dateTo,
			selectedYear : selected_year
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
					if(element.reason == "NOT"){
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
					if(element.reason == "NOT"){
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
function saveEffSewResonYear(){
	 var pollNameSew = document.getElementsByName("sew_poll_name[]");
	 var sewResoan = document.getElementsByName("sew_reasons[]");
	 var pollNameEff = document.getElementsByName("eff_poll_name[]");
	 var effResoan = document.getElementsByName("eff_reasons[]");
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
			url : "ajax-saveEffluentReasonYearly",
			data : ({
				sewPollNameList: sewPollNameList.toString(),
				sewResoanList:sewResoanList.toString(),
				effPollNameList: effPollNameList.toString(),
				effResoanList: effResoanList.toString(),
				selectedYear : selected_year
			}),

			success : function(data) {
				getEffluentPollutantYear(dateFrom,dateTo,selected_year);
				getSewagePollutantYear(dateFrom, dateTo, selected_year);
			},
			error : function(xhr, type) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
				window.setTimeout(window.location = "logout",7000)
			},
			async: false
		});
	
}
function getAirStackYear(dateFrom, dateTo, selected_year){
	var fwd_url = "ajax-getYearlyAirStack";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			dateFrom : dateFrom,
			dateTo: dateTo,
			selectedYear : selected_year
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
										 +"<input type='text' class='form-control' name='stack_reasons[]'>"
										 +"<div class='invalid-feedback'>Required !</div>"
										 +"<i class='form-group__bar'></i>"
										 +"</div>";
						}else{
							$("#airSaveYearly").hide();
							var reasons = element.reason;
						}
					}else{
						if(element.reason == "NO"){
							var reasons = "<div class='form-group'>"
										 +"<input type='text' class='form-control' name='stack_reasons[]' value='NA' disabled>"
										 +"<div class='invalid-feedback'>Required !</div>"
										 +"<i class='form-group__bar'></i>"
										 +"</div>";
						}else{
							$("#airSaveYearly").hide();
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
								+"<input type='hidden' name='stack_poll_name[]' value='"+pollName+"'/>"
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
function saveAirSewResonYear(){

	var selected_year = $('#selected_year').val();
	 var pollNameStack = document.getElementsByName("stack_poll_name[]");
	 var stackResoan = document.getElementsByName("stack_reasons[]");
	
	 var stackPollNameList = []; var stackResoanList = [];
	 
	 for (var i = 0; i < pollNameStack.length; i++) {
			var inp = pollNameStack[i];
			var inp1 = stackResoan[i]
			stackPollNameList.push(inp.value);
			if (inp1.value == "") {
	          inp1.classList.add("is-invalid");
	          return;
	      } else {
	    	  stackResoanList.push(inp1.value);
	          inp1.classList.remove("is-invalid");
	      }
			
		}
	 
	$.ajax({
		type : "POST",
		url : "ajax-saveAirStackReason",
		data : ({
			stackPollNameList: stackPollNameList.toString(),
			stackResoanList:stackResoanList.toString(),
			selectedYear : selected_year
		}),

		success : function(data) {
			getAirStackYear(dateFrom, dateTo, selected_year);
			jBoxBottomRightBigNotice("Success","Air Reason Saved !!", "green", "2000");
			
		},
		error : function(xhr, type) {
			alert('server error occoured');
		},
		async: false
	});


}
function getYearlyHazardousAndNonHazardousData(ptype,dateFrom, dateTo,selected_year)
{
	var fwd_url = "ajax-getYearlyHazardousProcessData";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			ptype: ptype,
			dateFrom : dateFrom,
			dateTo : dateTo,
			selectedYear : selected_year
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
function RecycledDataYear(selected_year){
	var id ="appendContainers";
	$("#"+id).empty();
	var fwd_url = "ajax-getRecycledDataYear";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selected_year,
			type:  "Recycled"
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				$("#esrmonthly_recylced_data").hide();
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var html = "<div class='row'>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.PollName+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.PrivieousData+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.CurrantData+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<lable>"+element.Uom+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'></div>"
			           +"</div>"
			           
			           $("#"+id).append(html); 
				
				})
			}else{
				var html = "<div class='row'>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='WasteName[]' class='form-control' placeholder='Enter Waste Type'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='PreviousMonth[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='CurrentMonth[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-2'><div class='form-group'>"
				   +"<input type='text' name='Uom[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
//    			   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddSolidContainer(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
				   +"</div></div>"
		           +"</div>"
		           
		           $("#"+id).append(html); 
			}
			
		},
	error : function(xhr, type) {
		alert('server error occoured at RecycledDataMonthly');
	},
	async: false
	});

}
function AddSolidContainer(Appendid){
	var id=Appendid;
	var randomId = randomIdNumber();
	var hzHtml = "";
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='WasteName[]' class='form-control' placeholder='Enter Waste Type'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='PreviousMonth[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='CurrentMonth[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<input type='text' name='Uom[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
					   +"<i class='zmdi zmdi-delete'></i> Remove</button>"
					   +"</div></div>"
			           +"</div>"
	//$(html).insertAfter("#"+id);
			 $("#"+id).append(html);
	makeSelect2();
}
function randomIdNumber() {
	var min = 1;
	var max = 5000;
	return Math.round(Math.random() * (+max - +min) + +min);
}
function removeElement(removeId){
	$("#"+removeId).remove();
}
function saveRecyledData(){
	var wasteName=new Array();
	var PreviousMonth=new Array();
	var CurrentMonth=new Array();
	var Uom=new Array();
	var esrMonth = $('#esr_month').val();
	var selected_year = $('#selected_year').val();
	var inps1 = document.getElementsByName("WasteName[]");
    var inps2 = document.getElementsByName("PreviousMonth[]");
    var inps3 = document.getElementsByName("CurrentMonth[]");
    var inps4 = document.getElementsByName("Uom[]");
    
    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];
        var inp4 = inps4[i];

        if (inp1.value == "") {
            inp1.classList.add("is-invalid");
            return;
        } else {
        	wasteName.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }

        if (inp2.value == "") {
            inp2.classList.add("is-invalid");
            return;
        } else {
        	PreviousMonth.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }
        
        if (inp3.value == "") {
            inp3.classList.add("is-invalid");
            return;
        } else {
        	CurrentMonth.push(inp3.value);
            inp3.classList.remove("is-invalid");
        }
        
        if (inp4.value == "") {
            inp4.classList.add("is-invalid");
            return;
        } else {
        	Uom.push(inp4.value);
            inp4.classList.remove("is-invalid");
        }

    }
    
    $.ajax({
		type : "POST",
		url : "ajax-saveRecycled",
		data : ({
			selected_year : selected_year,
			ptype:  "Recycled",
			recycled: wasteName.toString(),
			recycled_prev: PreviousMonth.toString(),
			recycled_curr: CurrentMonth.toString(),
			recycled_units: Uom.toString()
			
		}),

		success : function(data) {
			RecycledDataYear(selected_year);
		},
		error : function(xhr, type) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
			//window.setTimeout(window.location = "logout",7000)
		},
		async: false
	});
}
function SolidDataYear(selected_year){
	var id= "appendSoliddata";
	var fwd_url = "ajax-getHazardusDataYear";
	var unitOptions = getAllUnits();
	$("#"+id).empty();
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selected_year,
			type: "Solid Waste"
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				$("#esrmonthly_Solid_data").hide();
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var html = "<div class='row'>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.WasteType+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.Quantity+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.Uom+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<lable>"+element.Concentration+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'></div>"
			           +"</div>"
			           
			           $("#"+id).append(html); 
				
				})
			}else{
				var html = "<div class='row'>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='SolidName[]' class='form-control' placeholder='Enter Waste Type'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='SolidQuantity[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<select class='select2' name='UomSolid[]'>"
				   +"<option value=''>Select unit</option>"
				   +unitOptions 
				   +"</select>"
				   +"<div class='invalid-feedback'>Please select any !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-2'><div class='form-group'>"
				   +"<input type='text' name='SolidCons[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
//				   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddHazContainerSolid(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
				   +"</div></div>"
		           +"</div>"
		           
		           $("#"+id).append(html); 
			}
			
		},
	error : function(xhr, type) {
		alert('server error occoured at RecycledDataMonthly');
	},
	async: false
	});
}
function AddHazContainerSolid(Appendid){
	var id=Appendid;
	var randomId = randomIdNumber();
	var hzHtml = "";
	var unitOptions = getAllUnits();
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='SolidName[]' class='form-control' placeholder='Enter Waste Type'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='SolidQuantity[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<select class='select2' name='UomSolid[]'>"
					   +"<option value=''>Select unit</option>"
					   +unitOptions 
					   +"</select>"
					   +"<div class='invalid-feedback'>Please select any !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<input type='text' name='SolidCons[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
					   +"<i class='zmdi zmdi-delete'></i> Remove</button>"
					   +"</div></div>"
			           +"</div>"
	//$(html).insertAfter("#"+id);
			           $("#"+id).append(html);
	makeSelect2();
}
function saveSolidData(){
	var HazName=new Array();
	var Quantity=new Array();
	var Cons=new Array();
	var Uom=new Array();
	var esrMonth = $('#esr_month').val();
	var selected_year = $('#selected_year').val();
	var inps1 = document.getElementsByName("SolidName[]");
    var inps2 = document.getElementsByName("SolidQuantity[]");
    var inps3 = document.getElementsByName("UomSolid[]");
    var inps4 = document.getElementsByName("SolidCons[]");
    
    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];
        var inp4 = inps4[i];

        if (inp1.value == "") {
            inp1.classList.add("is-invalid");
            return;
        } else {
        	HazName.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }

        if (inp2.value == "") {
            inp2.classList.add("is-invalid");
            return;
        } else {
        	Quantity.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }
        
        if (inp3.value == "") {
            inp3.classList.add("is-invalid");
            return;
        } else {
        	Uom.push(inp3.value);
            inp3.classList.remove("is-invalid");
        }
        
        if (inp4.value == "") {
            inp4.classList.add("is-invalid");
            return;
        } else {
        	Cons.push(inp4.value);
            inp4.classList.remove("is-invalid");
        }

    }
    
    $.ajax({
		type : "POST",
		url : "ajax-saveHazardousWaterData",
		data : ({
			selected_year : selected_year,
			ptype:  'Solid Waste',
			hw_id : HazName.toString(),
			hw_quantity : Quantity.toString(),
			hw_conc : Cons.toString(),
			hw_units : Uom.toString()
			
		}),
		success : function(data) {
			jBoxBottomRightBigNotice("Success","Solid Weast Saved !!", "green", "2000");
			SolidDataYear(selected_year);
		},
		error : function(xhr, type) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
		},
		async: false
	});
}
function HazardusDataYear(selected_year){
	var id= "appendHWdata";
	var fwd_url = "ajax-getHazardusDataYear";
	var unitOptions = getAllUnits();
	$("#"+id).empty();
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selected_year,
			type: "Hazardous Waste"
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				$("#esrmonthly_Haz_data").hide();
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var html = "<div class='row'>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.WasteType+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.Quantity+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<lable>"+element.Uom+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<lable>"+element.Concentration+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'></div>"
			           +"</div>"
			           
			           $("#"+id).append(html); 
				
				})
			}else{
				var html = "<div class='row'>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='hazName[]' class='form-control' placeholder='Enter Waste Type'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='Quantity[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<select class='select2' name='UomHaz[]'>"
				   +"<option value=''>Select unit</option>"
				   +unitOptions 
				   +"</select>"
				   +"<div class='invalid-feedback'>Please select any !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-2'><div class='form-group'>"
				   +"<input type='text' name='Cons[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
//				   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddHazContainer(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
				   +"</div></div>"
		           +"</div>"
		           
		           $("#"+id).append(html); 
			}
			
		},
	error : function(xhr, type) {
		alert('server error occoured at HazardusDataYear');
	},
	async: false
	});
}
function AddHazContainer(Appendid){
	var id=Appendid;
	var randomId = randomIdNumber();
	var hzHtml = "";
	var unitOptions = getAllUnits();
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='hazName[]' class='form-control' placeholder='Enter Waste Type'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='Quantity[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<select class='select2' name='UomHaz[]'>"
					   +"<option value=''>Select unit</option>"
					   +unitOptions 
					   +"</select>"
					   +"<div class='invalid-feedback'>Please select any !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<input type='text' name='Cons[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
					   +"<i class='zmdi zmdi-delete'></i> Remove</button>"
					   +"</div></div>"
			           +"</div>"
	//$(html).insertAfter("#"+id);
			 $("#"+id).append(html);
	makeSelect2();
}
function saveHazData(){
	var HazName=new Array();
	var Quantity=new Array();
	var Cons=new Array();
	var Uom=new Array();
	var esrMonth = $('#esr_month').val();
	var selected_year = $('#selected_year').val();
	var inps1 = document.getElementsByName("hazName[]");
    var inps2 = document.getElementsByName("Quantity[]");
    var inps3 = document.getElementsByName("UomHaz[]");
    var inps4 = document.getElementsByName("Cons[]");
    
    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];
        var inp4 = inps4[i];

        if (inp1.value == "") {
            inp1.classList.add("is-invalid");
            return;
        } else {
        	HazName.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }

        if (inp2.value == "") {
            inp2.classList.add("is-invalid");
            return;
        } else {
        	Quantity.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }
        
        if (inp3.value == "") {
            inp3.classList.add("is-invalid");
            return;
        } else {
        	Uom.push(inp3.value);
            inp3.classList.remove("is-invalid");
        }
        
        if (inp4.value == "") {
            inp4.classList.add("is-invalid");
            return;
        } else {
        	Cons.push(inp4.value);
            inp4.classList.remove("is-invalid");
        }

    }
    
    $.ajax({
		type : "POST",
		url : "ajax-saveHazardousWaterData",
		data : ({
			selected_year : selected_year,
			ptype:  'Hazardous Waste',
			hw_id : HazName.toString(),
			hw_quantity : Quantity.toString(),
			hw_conc : Cons.toString(),
			hw_units : Uom.toString()
			
		}),
		success : function(data) {
			jBoxBottomRightBigNotice("Success","Hazardous waste Saved !!", "green", "2000");
			HazardusDataYear(selected_year);
		},
		error : function(xhr, type) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
		},
		async: false
	});
}
function PolutionControlDataYear(selected_year){
	var id= "appendPollutionControldata";
	var fwd_url = "ajax-getPollutionControlDataYear";
	var unitOptions = getAllUnits();
	$("#"+id).empty();
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selected_year
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				$("#esrmonthly_Polution_data").hide();
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var html = "<div class='row'>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<lable>"+element.Description+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<lable>"+element.RedWater+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<lable>"+element.RedFule+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<lable>"+element.RedRaw+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<lable>"+element.RedPowar+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<lable>"+element.CapitalInvestment+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<lable>"+element.RedMaintaince+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
			           +"</div>"
			           
			           $("#"+id).append(html); 
				
				})
			}else{
				var html = "<div class='row'>"
				   +"<div class='col-2'><div class='form-group'>"
				   +"<input type='text' name='description[]' class='form-control' placeholder='Enter Waste Type'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-2'><div class='form-group'>"
				   +"<input type='text' name='RedWater[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-2'><div class='form-group'>"
				   +"<input type='text' name='RedFule[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
				   +"<input type='text' name='RedRaw[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
				   +"<input type='text' name='RedPowar[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
				   +"<input type='text' name='CapitalInvestment[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-2'><div class='form-group'>"
				   +"<input type='text' name='RedMaintaince[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
//				   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddPolutionContainer(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
				   +"</div></div>"
		           +"</div>"
		           
		           $("#"+id).append(html); 
			}
			
		},
	error : function(xhr, type) {
		alert('server error occoured at PolutionControlDataYear');
	},
	async: false
	});

}
function AddPolutionContainer(Appendid){
	var id=Appendid;
	var randomId = randomIdNumber();
	var hzHtml = "";
	var unitOptions = getAllUnits();
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'>"
				       +"<div class='col-2'><div class='form-group'>"
					   +"<input type='text' name='description[]' class='form-control' placeholder='Enter Waste Type'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<input type='text' name='RedWater[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<input type='text' name='RedFule[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<input type='text' name='RedRaw[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<input type='text' name='RedPowar[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<input type='text' name='CapitalInvestment[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-2'><div class='form-group'>"
					   +"<input type='text' name='RedMaintaince[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
					   +"<i class='zmdi zmdi-delete'></i> Remove</button>"
					   +"</div></div>"
			           +"</div>"
			  $("#"+id).append(html);
	makeSelect2();
}
function savePollutionControlData(){
	var Description=new Array();
	var RedWater=new Array();
	var RedFule=new Array();
	var RedRaw=new Array();
	var RedPowar=new Array();
	var CapitalInvestment=new Array();
	var RedMaintaince=new Array();
	var selected_year = $('#selected_year').val();
	var inps1 = document.getElementsByName("description[]");
    var inps2 = document.getElementsByName("RedWater[]");
    var inps3 = document.getElementsByName("RedFule[]");
    var inps4 = document.getElementsByName("RedRaw[]");
    var inps5 = document.getElementsByName("RedPowar[]");
    var inps6 = document.getElementsByName("CapitalInvestment[]");
    var inps7 = document.getElementsByName("RedMaintaince[]");
    
    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];
        var inp4 = inps4[i];
        var inp5 = inps5[i];
        var inp6 = inps6[i];
        var inp7 = inps7[i];

        if (inp1.value == "") {
            inp1.classList.add("is-invalid");
            return;
        } else {
        	Description.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }

        if (inp2.value == "") {
            inp2.classList.add("is-invalid");
            return;
        } else {
        	RedWater.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }
        
        if (inp3.value == "") {
            inp3.classList.add("is-invalid");
            return;
        } else {
        	RedFule.push(inp3.value);
            inp3.classList.remove("is-invalid");
        }
        
        if (inp4.value == "") {
        	inp4.classList.add("is-invalid");
            return;
        } else {
        	RedRaw.push(inp4.value);
            inp4.classList.remove("is-invalid");
        }
        
        if (inp5.value == "") {
            inp5.classList.add("is-invalid");
            return;
        } else {
        	RedPowar.push(inp5.value);
            inp5.classList.remove("is-invalid");
        }
        
        if (inp6.value == "") {
            inp6.classList.add("is-invalid");
            return;
        } else {
        	CapitalInvestment.push(inp6.value);
            inp6.classList.remove("is-invalid");
        }
        
        if (inp7.value == "") {
            inp7.classList.add("is-invalid");
            return;
        } else {
        	RedMaintaince.push(inp7.value);
            inp7.classList.remove("is-invalid");
        }
    }
    
    $.ajax({
		type : "POST",
		url : "ajax-savePollutionData",
		data : ({
			selected_year : selected_year,
			description : Description.toString(),
			reduction_water : RedWater.toString(),
			reduction_fuel : RedFule.toString(),
			reduction_rm : RedRaw.toString(),
			reduction_pc : RedPowar.toString(),
			capital_investment : CapitalInvestment.toString(),
			reduction_maintenance : RedMaintaince.toString(),
			
		}),
		success : function(data) {
			jBoxBottomRightBigNotice("Success","Polution Data Saved !!", "green", "2000");
			 PolutionControlDataYear(selected_year);
		},
		error : function(xhr, type) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
		},
		async: false
	});
}
function InvestmentEnviroment(selected_year){
	var id= "appendInvestmentstatedata";
	var fwd_url = "ajax-getInvestmentDataYear";
	$("#"+id).empty();
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selected_year,
			esrMonth : esrMonth,
			type: "Solid Waste"
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				$("#esrmonthly_Investment_data").hide();
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var html = "<div class='row'>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<lable>"+element.Detail+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<lable>"+element.Measures+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<lable>"+element.Investment+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
			           +"</div>"
			           
			           $("#"+id).append(html); 
				
				})
			}else{
				var html = "<div class='row'>"
				   +"<div class='col-4'><div class='form-group'>"
				   +"<input type='text' name='DetailsInvest[]' class='form-control' placeholder='Enter Waste Type'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-4'><div class='form-group'>"
				   +"<input type='text' name='EnvProtection[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='CapitalInvest[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
//				   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddinvestContainer(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
				   +"</div></div>"
		           +"</div>"
		           
		           $("#"+id).append(html); 
			}
			
		},
	error : function(xhr, type) {
		alert('server error occoured at InvestMentYear');
	},
	async: false
	});
}
function AddinvestContainer(Appendid){
	var id=Appendid;
	var randomId = randomIdNumber();
	var hzHtml = "";
	var unitOptions = getAllUnits();
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<input type='text' name='DetailsInvest[]' class='form-control' placeholder='Enter Waste Type'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<input type='text' name='EnvProtection[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='CapitalInvest[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
					   +"<i class='zmdi zmdi-delete'></i> Remove</button>"
					   +"</div></div>"
			           +"</div>"
			   $("#"+id).append(html);
	makeSelect2();
}
function saveInvestmentData(){

	var Details=new Array();
	var EnvProdection=new Array();
	var CapitalInvestment=new Array();
	var selected_year = $('#selected_year').val();
	var inps1 = document.getElementsByName("DetailsInvest[]");
    var inps2 = document.getElementsByName("EnvProtection[]");
    var inps3 = document.getElementsByName("CapitalInvest[]");
    
    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];

        if (inp1.value == "") {
            inp1.classList.add("is-invalid");
            return;
        } else {
        	Details.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }

        if (inp2.value == "") {
            inp2.classList.add("is-invalid");
            return;
        } else {
        	EnvProdection.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }
        
        if (inp3.value == "") {
            inp3.classList.add("is-invalid");
            return;
        } else {
        	CapitalInvestment.push(inp3.value);
            inp3.classList.remove("is-invalid");
        }
    }
    
    $.ajax({
		type : "POST",
		url : "ajax-saveEnvInvestData",
		data : ({
			selected_year : selected_year,
			curr_detail : Details.toString(),
			curr_protection_mea : EnvProdection.toString(),
			curr_capital_investment : CapitalInvestment.toString()
			
		}),
		success : function(data) {
			jBoxBottomRightBigNotice("Success","Investment Data Saved !!", "green", "2000");
			InvestmentEnviroment(selected_year);
		},
		error : function(xhr, type) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
		},
		async: false
	});

}
function InvestmentNextEnviroment(selected_year){
	var id= "appendInvestmentstateNextdata";
	var fwd_url = "ajax-getInvestmentNextDataYear";
	$("#"+id).empty();
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selected_year,
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				$("#esrmonthly_InvestmentNext_data").hide();
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var html = "<div class='row'>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<lable>"+element.Detail+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<lable>"+element.Measures+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<lable>"+element.Investment+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
			           +"</div>"
			           
			           $("#"+id).append(html); 
				
				})
			}else{
				var html = "<div class='row'>"
				   +"<div class='col-4'><div class='form-group'>"
				   +"<input type='text' name='DetailsInvestNext[]' class='form-control' placeholder='Enter Waste Type'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-4'><div class='form-group'>"
				   +"<input type='text' name='EnvProtectionNext[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-3'><div class='form-group'>"
				   +"<input type='text' name='CapitalInvestNext[]' class='form-control'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
//				   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddinvestNextContainer(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
				   +"</div></div>"
		           +"</div>"
		           
		           $("#"+id).append(html); 
			}
			
		},
	error : function(xhr, type) {
		alert('server error occoured at InvestMentYear');
	},
	async: false
	});
}
function saveInvestmentNextData(){

	var Details=new Array();
	var EnvProdection=new Array();
	var CapitalInvestment=new Array();
	var selected_year = $('#selected_year').val();
	var inps1 = document.getElementsByName("DetailsInvestNext[]");
    var inps2 = document.getElementsByName("EnvProtectionNext[]");
    var inps3 = document.getElementsByName("CapitalInvestNext[]");
    
    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];

        if (inp1.value == "") {
            inp1.classList.add("is-invalid");
            return;
        } else {
        	Details.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }

        if (inp2.value == "") {
            inp2.classList.add("is-invalid");
            return;
        } else {
        	EnvProdection.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }
        
        if (inp3.value == "") {
            inp3.classList.add("is-invalid");
            return;
        } else {
        	CapitalInvestment.push(inp3.value);
            inp3.classList.remove("is-invalid");
        }
    }
    
    $.ajax({
		type : "POST",
		url : "ajax-saveEnvInvestDataNextYear",
		data : ({
			selected_year : selected_year,
			next_detail : Details.toString(),
			next_protection_mea : EnvProdection.toString(),
			next_capital_investment : CapitalInvestment.toString()
			
		}),
		success : function(data) {
			jBoxBottomRightBigNotice("Success","Investment Data Saved !!", "green", "2000");
			InvestmentNextEnviroment(selected_year);
		},
		error : function(xhr, type) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
		},
		async: false
	});

}
function AddinvestNextContainer(Appendid){
	var id=Appendid;
	var randomId = randomIdNumber();
	var hzHtml = "";
	var unitOptions = getAllUnits();
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<input type='text' name='DetailsInvestNext[]' class='form-control' placeholder='Enter Waste Type'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<input type='text' name='EnvProtectionNext[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-3'><div class='form-group'>"
					   +"<input type='text' name='CapitalInvestNext[]' class='form-control'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
					   +"<i class='zmdi zmdi-delete'></i> Remove</button>"
					   +"</div></div>"
			           +"</div>"
			           
			 $("#"+id).append(html);
	makeSelect2();
}
function Particular(selected_year){
	var id= "appendParticulardata";
	var fwd_url = "ajax-getParticularDataYear";
	$("#"+id).empty();
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data : ({
			selectedYear : selected_year
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				$("#esrmonthly_Particular_data").hide();
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var html = "<div class='row'>"
					   +"<div class='col-4'><div class='form-group'>"
					   +"<lable>"+element.Particular+"<lable>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
			           +"</div>"
			           
			           $("#"+id).append(html); 
				
				})
			}else{
				var html = "<div class='row'>"
				   +"<div class='col-11'><div class='form-group'>"
				   +"<input type='text' name='Particular[]' class='form-control' placeholder='Enter Waste Type'>"
				   +"<div class='invalid-feedback'>Required ! !</div>"
				   +"<i class='form-group__bar'></i>"
				   +"</div></div>"
				   +"<div class='col-1'><div class='form-group'>"
//				   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='AddParticularContainer(\""+id+"\")'>"
//				   +"<i class='zmdi zmdi-fw'></i> ADD</button>"
				   +"</div></div>"
		           +"</div>"
		           
		           $("#"+id).append(html); 
			}
			
		},
	error : function(xhr, type) {
		alert('server error occoured at InvestMentYear');
	},
	async: false
	});
}
function AddParticularContainer(Appendid){
	var id=Appendid;
	var randomId = randomIdNumber();
	var hzHtml = "";
	var unitOptions = getAllUnits();
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'>"
					   +"<div class='col-11'><div class='form-group'>"
					   +"<input type='text' name='Particular[]' class='form-control' placeholder='Enter Waste Type'>"
					   +"<div class='invalid-feedback'>Required ! !</div>"
					   +"<i class='form-group__bar'></i>"
					   +"</div></div>"
					   +"<div class='col-1'><div class='form-group'>"
					   +"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
					   +"<i class='zmdi zmdi-delete'></i> Remove</button>"
					   +"</div></div>"
			           +"</div>"
			           
			 $("#"+id).append(html);
	makeSelect2();
}
function saveParticularData(){
	var particular=new Array();
	var selected_year = $('#selected_year').val();
	var inps1 = document.getElementsByName("Particular[]");
    
    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];

        if (inp1.value == "") {
            inp1.classList.add("is-invalid");
            return;
        } else {
        	particular.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }
    }
    
    $.ajax({
		type : "POST",
		url : "ajax-saveParticulars",
		data : ({
			selected_year : selected_year,
			particulars : particular.toString()
			
		}),
		success : function(data) {
			jBoxBottomRightBigNotice("Success","Particular Data Saved !!", "green", "2000");
			Particular(selected_year);
		},
		error : function(xhr, type) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n","red", "2000");
		},
		async: false
	});
}


//updated genration
function getESRYearlyWaterGeneration(dateFrom, dateTo)
{
	//var fwd_url = "ajax-getESRYearlyWaterGeneration";
	var etp_no = 0;var stp_no = 0;
	var process_avg=0;var act_value=0;
	var eff_actual_quantity=0, sew_actual_quantity=0;
	$.ajax({
		type : 'POST',
		url : "ajax-getESRYearlyWaterGeneration",
		data : ({
			dateFrom : dateFrom,
			dateTo : dateTo
			
		}),

		success : function(data) {
			var size= data.length;
			if(size != 0){
				var parseData = JSON.parse(data);
				$.each(parseData, function(index, element) {
					var div = $("<tr><td>"
								+"<label><b>Daily quantity of trade effluent from the factory</b></label>"
								+"</td>"
								+"<td id='effCons'>"
								+"<label  class='vals1' >"+element.EtpConsent+"</label>"
								+"</td>"
								+"<td>"
								+"<label class='vals1' >"+element.EtpAvg+"</label>"
								+"</td></tr>"
								+"<tr><td>"
								+"<label><b>Daily quantity of trade Sweage from the factory</b></label>"
								+"</td>"
								+"<td id='seWcons'>"
								+"<label  class='vals1' >"+element.StpConsent+"</label>"
								+"</td>"
								+"<td>"
								+"<label class='vals1'>"+element.StpAvg+"</label>"
								+"</td></tr>")
						$("#water_Ger_info").append(div); 
						$("#effConsumption").val(element.EtpConsent);
						$("#sewConsumption").val(element.StpConsent);
					
				
				})
			}
			
		
			
		},
	error : function(xhr, type) {
		alert('server error occoured at water Generation');
	},
	async: false
	});
}