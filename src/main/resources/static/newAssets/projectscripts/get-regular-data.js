var isproduction = "";
var no_data = "<center><img src='../newAssets/img/nodata1.png' style='width:338px'></center>";
$(document).ready(
	function () {
		$.ajax({
			type: "POST",
			url: "getProduction-saveData",
			data: {
				action: 'getProduction',
			},
			success: function (data) {
				isproduction = data.trim();
			},
			async: false
		});

		var msg = "";
		var j = 0;
		if (isproduction == "Yes") {
			var list = ["product", "byproduct", "raw", "fuel", "apc", "hwp", "hwpcf", "nhwp", "nhwpcf"];
			var namesList = ["Product Details", "Byproduct Details", "Raw Material Details", "Fuel Details", " ", "Hazardous Waste from Process", "Hazardous Waste from Pollution Control Facility", "Non-Hazardous Waste from Process", "Non-Hazardous Waste from Pollution Control Facility"];
		} else if (isproduction == "Bio-Medical") {
			var list = ["bio", "medical", "fuel", "apc", "hwp", "hwpcf", "nhwp", "nhwpcf"];
		} else {
			var list = ["fuel","apc", "hwp", "hwpcf", "nhwp", "nhwpcf"];
		}

		if (list.length > 0) {
			for (var i = 0; i < list.length; i++) {
				$.ajax({
					type: 'POST',
					url: 'ajax-getRegulardata?action=getData&type=' + list[i],
					success: function (data) {
						var data = JSON.parse(data);
						if (data.length>0) {
							var html = "<h2><a href='#'>" + namesList[i] + "</a></h2>" +
								"<div><div class='card-body'>" +
								"<table class='table table-bordered' id='dailyip-table'>" +
								"<thead> <tr> <th>" + namesList[i] + " Name</th> <th>Quantity</th> <th>Units</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";

							$.each(data, function (index, element) {
								var finalValue = 0;
								item = element.item;
								quantity = element.quantity;
								units = element.units;
								status = element.status;
								filledValue = element.filledValue;

								if (status == "Filled") {

									var temp = "<tr>" +
										"<td><input type='hidden' name='" + list[i] + "_name[]' id='" + list[i] + "_" + index + "_name' value='" + item + "'>" + item + "</td>" +
										"<td>" +
										"<div class='form-group mb-0'>" +
										"<input type='text' name='" + list[i] + "_quantity[]' class='form-control' id='" + list[i] + "_" + index + "_name' value='" + filledValue + "' disabled/> <i class='form-group__bar'></i>" +
										"</div>" +
										"</td>" +
										"<td>" + units + "</td>" +
										"<td>" +
										"<div class='form-group'> <div id='error_" + list[i] + "_" + index + "Quantity'><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
										"</td>" +
										"</tr>"

									html = html + temp;
								} else if (status == "Fill") {

									var temp = "<tr>" +
										"<td><input type='hidden' name='" + list[i] + "_name[]' id='" + list[i] + "_" + index + "_name' value='" + item + "'>" + item + "</td>" +
										"<td>" +
										"<div class='form-group mb-0'>" +
										"<input type='text' name='" + list[i] + "_quantity[]' class='form-control' id='" + list[i] + "_" + index + "_name' onkeypress='numberValidate(event,\"error_" + list[i] + "_" + index + "Quantity\")' /> <i class='form-group__bar'></i>" +
										"</div>" +
										"</td>" +
										"<td>" + units + "</td>" +
										"<td>" +
										"<div class='form-group'> <div id='error_" + list[i] + "_" + index + "Quantity'></div> </div>" +
										"</td>" +
										"</tr>"

									html = html + temp;
								}
							});

							if (status == "Filled") {
								var temp2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> submitted </button> </center> </div>" +
									"</div> </div> </div>";
							} else if (status == "Fill") {
								var temp2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='regSaveBtn_" + list[i] + "' onclick='saveRegData(\"" + list[i] + "\",\"" + i + "\",this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
									"</div> </div> </div>";
							}

							var finalData = html + temp2;
							if (list[i] == "product" || list[i] == "byproduct" || list[i] == "raw") {
								$("#productionAccordion").append(finalData);
							}
							if (list[i] == "fuel" ) {
								$("#fuelAccordion").append(finalData);
							} 
							
							if (list[i] == "hwp" || list[i] == "hwpcf" || list[i] == "nhwp" || list[i] == "nhwpcf") {
								$("#solidWasteAccordion").append(finalData);
							}
							
						}
						if(list[i] == "apc"){
							getRegApcList();
						}
					},
					error: function (xhr, type) {
						alert('server error occoured');
					},
					async: false
				});
			}
			$("#productionAccordion").accordion({ heightStyle: 'content', collapsible: false });
			$("#fuelAccordion").accordion({ heightStyle: 'content', collapsible: true });
			$("#solidWasteAccordion").accordion({ heightStyle: 'content', collapsible: true });

			
		}
		getWaterSourceDailyInputData(); // get water source data for water consumption accordion
		getWaterPreFilterDailyInputData(); //get prefilter data for water consumption
		getWaterFilterDailyInputData(); // get water Filter data for water consumption accordion
		getWaterFilterUseDailyData();
		getUseOfWaterDailyInputData();
		getTreatmentWaterDailyInputData();
		$("#waterMainAccordion").accordion({ heightStyle: 'content', collapsible: true });
	});

function getRegApcList() {
	$.ajax({
		type: "POST",
		url: "ajax-getRegAPCData",
		success: function (res) {
			var dataLength = JSON.parse(res);
			if (dataLength.length > 0) {
				var html = "<h2><a href='#'> Air Pollution Control (APC) </a></h2>" +
					"<div><div class='card-body'>" +
					"<table class='table table-bordered' id='dailyip-table'>" +
					"<thead> <tr> <th> APC System </th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";

				var data = JSON.parse(res);
				var status ="";
				$.each(data, function (index, element) {

					var stackId = element.stackId;
					var apcSysName = element.apcSysName;
					var endReading = element.endReading;
					
					if(endReading == undefined) endReading = 0;
					var startReading = element.startReading;
					status = element.status;
					var actualReading = element.actualReading;

					if (status == "Fill") {

						var temp = "<tr>" +
							"<td><input type='hidden' name='stack_id[]' id='stack_id[]' value='" + stackId + "'>" + apcSysName + "</td>" +
							"<td>" +
							"<div class='form-group mb-0'>" +
							"<input type='text' placeholder='Start Reading' name='apc_start[]' class='form-control' id='apc_startid_" + stackId + "' value='" + endReading + "' disabled/> <i class='form-group__bar'></i>" +
							"</div>" +
							"</td>"+
							"<td>" +
							"<div class='form-group mb-0'>" +
							"<input type='text' placeholder='End Reading' name='apc_end[]' class='form-control' id='apc_endid_" + stackId + "' onkeypress='numberValidate(event,\"apc_Error_" + stackId + "\")' onchange='setConsumption(\"apc\",\"" + stackId + "\")'/> <i class='form-group__bar'></i>" +
							"</div>" +
							"</td>"+
							"<td>" +
							"<div class='form-group mb-0'>" +
							"<input type='text' placeholder='Act Consumption' name='apc_consumption[]' class='form-control' id='apc_avgid_" + stackId + "' disabled/> <i class='form-group__bar'></i>" +
							"</div>" +
							"</td>"+
							"<td>" +
							"<div class='form-group'> <div id='apc_Error_" + stackId + "'></div> </div>" +
							"</td>" +
							"</tr>"
						html = html + temp;
					} else if (status == "Filled") {

						var temp = "<tr>" +
							"<td>" + apcSysName + "</td>" +
							"<td>" +
							"<div class='form-group mb-0'>" +
							"<input type='text' class='form-control' value='" + startReading + "' disabled/>" +
							"</div>" +
							"</td>"+
							"<td>" +
							"<div class='form-group mb-0'>" +
							"<input type='text' class='form-control' value='" + endReading + "' disabled/>" +
							"</div>" +
							"</td>"+
							"<td>" +
							"<div class='form-group mb-0'>" +
							"<input type='text' class='form-control' value='" + actualReading + "' disabled/></i>" +
							"</div>" +
							"</td>"+
							"<td>" +
							"<div class='form-group'><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label> </div>" +
							"</td>" +
							"</tr>"
						html = html + temp;
					}
				});
				if (status == "Filled") {
					var temp2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> submitted</button> </center> </div>" +
						"</div> </div> </div>"
				} else if (status == "Fill") {
					var temp2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='saveApcbtn' onclick='saveApcData(this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
						"</div> </div> </div>"
				}


				html = html + temp2;
				$("#fuelAccordion").append(html);
				$("#productionAccordion").accordion({
					heightStyle: 'content',
					collapsible: true
				});
				$("#fuelAccordion").accordion({
					heightStyle: 'content',
					collapsible: true
				});
			}
		},
		async: false
	});
}

function getWaterSourceDailyInputData() {
	$.ajax({
		type: "POST",
		url: "ajax-getDailyWaterSource",
		success: function (data) {
			var data = JSON.parse(data);
			var result=data[0].Result;
			var buttonResult=0;
			if(result==true){
				var sourceId="", staff = "", status = "", temp = "", startReading="", endReading="", actualReading="", status="",sourceMeter="", sourceName="";
				var dataList=data[0].Data;
				if(dataList!=null){
				var regularSourceData=dataList.regularSourceDataList;
				var input=dataList.Input;
				staff="";
				if(regularSourceData.length>0){
					var staffInput=regularSourceData[0].status;
					if(staffInput!=null){
					if (staffInput =="Fill"||staffInput=="New") {
						temp = "<input type='number' class='form-control' id='noStaff' placeholder='Number of Staff'><i class='form-group__bar'></i> &nbsp; <div id='errorStaff'></div>";
					} else if(staffInput =="Filled") {
						staff=regularSourceData[0].noOfStaff;
						temp = "<input type='text' class='form-control' id='noStaff' value='" + staff + "' disabled> <i class='form-group__bar'></i> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div>";
					}
					}
					var html2="";
					var html = "<h2><a href='#'> Water Source </a></h2>" +
					"<div>" +
						"<div class='card-body'>" +
							"<div class='form-group' style='display: -webkit-inline-box;'>" + temp + "</div>" +
								"<table class='table table-bordered' id='dailyip-table'>" +
									"<thead> <tr> <th> Water Source Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
					if(regularSourceData.length>0){
						$.each(regularSourceData, function (index, element) {
							sourceId=element.wsId;
							status=element.status;
							sourceMeter=element.sourceMeter;
							sourceName=element.sourceName;
							startReading=element.startReading;
							endReading=element.endReading;
							actualReading=element.actualReading;
							
							if(status=="Fill" && sourceMeter==true){
								html +="<tr>" +
											"<td><input type='hidden' name='sourceId[]' value='" + sourceId + "'><input type='hidden' class='form-control' name='sourceName[]' value='" + sourceName + "'>" + sourceName +"</td>" +
											"<td>" +
												"<div class='form-group mb-0'>" +
												"<input type='text' name='sourceStart[]' id='source_startid_" + index + "' class='form-control' value='" + endReading + "' disabled/> <i class='form-group__bar'></i>" +
												"</div>" +
										    "</td>" +
										    "<td>" +
												"<div class='form-group mb-0'>" +
												"<input type='text' class='form-control' placeholder='End Reading' name='sourceEnd[]' id='source_endid_" + index + "' onkeypress='numberValidate(event,\"source_Error_" + index + "\")' onchange='setConsumption(\"source\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
												"</div>" +
										"</td>" +
										"<td>" +
											"<div class='form-group mb-0'>" +
											"<input type='text' placeholder='Act Consumption' name='avgSourceStart[]' class='form-control' id='source_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
											"</div>" +
										"</td>" +
										"<td>" +
											"<div class='form-group'> <div id='source_Error_" + index + "'></div> </div>" +
										"</td>" +
										"</tr>";
							}else if(status=="Fill" && sourceMeter==false){
								html += "<tr>" +
											"<td><input type='hidden' name='sourceId[]' value='" + sourceId + "'><input type='hidden' class='form-control' name='sourceName[]' value='" + sourceName + "'>" + sourceName + "</td>" +
											"<td>-<input type='hidden' class='form-control' name='sourceStart[]' value='0'></td>" +
											"<td>-<input type='hidden' class='form-control' name='sourceEnd[]' value='0'></td>" +
											"<td>" +
												"<div class='form-group mb-0'>" +
												"<input type='text' placeholder='Act Consumption' name='avgSourceStart[]' class='form-control' id='source_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
												"</div>" +
											"</td>" +
											"<td>" +
												"<div class='form-group'> <div id='source_Error_" + index + "'></div> </div>" +
											"</td>"+
											"</tr>";
								
							}else if(status=="Filled" && sourceMeter==true){
								html += "<tr>" +
											"<td><input type='hidden' name='sourceId1[]' value='" + sourceId + "'><input type='hidden' class='form-control' name='sourceName1[]' value='" + sourceName + "'>" + sourceName + "</td>" +
											"<td>" +
												"<div class='form-group mb-0'>" +
												"<input type='text' name='sourceStart1[]'  class='form-control' value='" + startReading + "' disabled/>"+
												"</div>" +
											"</td>" +
										    "<td>" +
												"<div class='form-group mb-0'>" +
												"<input type='text' name='sourceEnd1[]'  class='form-control' value='" + endReading + "' disabled/>"+								"</div>" +
										    "</td>" +
										    "<td>" +
												"<div class='form-group mb-0'>" +
												"<input type='text' name='avgSourceStart1[]' class='form-control'   value='" + actualReading + "' disabled/>"+								"</div>" +
										    "</td>" +
										    "<td>" +
												"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
											"</td>" +
										"</tr>";
							}
							else if(status=="Filled" && sourceMeter==false){
								html += "<tr>" +
											"<td><input type='hidden' name='sourceId1[]' value='" + sourceId + "'><input type='hidden' class='form-control' name='sourceName1[]' value='" + sourceName + "'>" + sourceName + "</td>" +
											"<td>-<input type='hidden' class='form-control' name='sourceStart1[]'  value='0'></td>" +
											"<td>-<input type='hidden' class='form-control' name='sourceEnd1[]' value='0'></td>" +
											"<td>" +
												"<div class='form-group mb-0'>" +
												"<input type='text' name='avgSourceStart1[]' class='form-control' value='" + actualReading + "' disabled/>"+
												"</div>" +
											"</td>" +
											"<td>" +
												"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
											"</td>" +
										"</tr>";
							}else if(status=="New" && sourceMeter==false){
								html +="<tr>" +
										"<td><input type='hidden' name='sourceId[]' value='" + sourceId + "'><input type='hidden' class='form-control' name='sourceName[]' value='" + sourceName + "'>" + sourceName + "</td>" +
										"<td>-<input type='hidden' class='form-control' name='sourceStart[]' value='0'></td>" +
										"<td>-<input type='hidden' class='form-control' name='sourceEnd[]' value='0'></td>" +
										"<td>" +
											"<div class='form-group mb-0'>" +
											"<input type='text' placeholder='Actual Consumption' name='avgSourceStart[]' onkeypress='numberValidate(event,\"source_Error_" + index + "\")' class='form-control'/> <i class='form-group__bar'></i></div>";
								        "</td>"+
										"<td>"+
										"<div class='form-group'> <div id='source_Error_" + index + "'></div> </div>" +
										"</td>" +
										"<td>" +
											"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
										"</td>" +
										"</tr>";
							}else if(status=="New" && sourceMeter==true){
								html +="<tr>" +
											"<td><input type='hidden' name='sourceId[]' value='" + sourceId + "'><input type='hidden' class='form-control' name='sourceName[]' value='" + sourceName + "'>" + sourceName + "</td>" +
											"<td>" +
												"<div class='form-group mb-0'>" +
													"<input type='text' name='sourceStart[]' value='0' id='source_startid_" + index + "' class='form-control' value='" + index + "' /> <i class='form-group__bar'></i>" +
												"</div>" +
											"</td>" +
											"<td>" +
												"<div class='form-group mb-0'>" +
													"<input type='text' class='form-control' placeholder='End Reading' name='sourceEnd[]' id='source_endid_" + index + "' onkeypress='numberValidate(event,\"source_Error_" + index + "\")' onchange='setConsumption(\"source\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
												"</div>" +
											"</td>" +
											"<td>" +
												"<div class='form-group mb-0'>" +
													"<input type='text' placeholder='Act Consumption' name='avgSourceStart[]' class='form-control' id='source_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
												"</div>" +
											"</td>" +
											"<td>" +
											"<div class='form-group'> <div id='source_Error_" + index + "'></div> </div>" +
											"</td>" +
										"</tr>";
							}
						});
						if(input==1){
							html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='regularSourceSavebtn' onclick='saveRegularSourceData(this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
							"</div> </div> </div>";
						}else
							{
							 html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> Submitted </button> </center> </div>" +
							"</div> </div> </div>";
							}
					}
					$("#waterMainAccordion").append(html+html2);
				}
				}
			} 
			
			$("#productionAccordion").accordion({
				heightStyle: 'content',
				collapsible: true
			});
		},
		async: false
	});
}

function saveRegularSourceData(el) {
	var main = new Array();
	var sourceId = 0, flag = 0;
	var staff = "NA";

	if ($("#noStaff").val() == "") {
		$("#errorStaff").html("<label class='text-red'>Enter number of staff</label>");
		flag++;
	} else {
		$("#errorStaff").html("");
		staff = $("#noStaff").val();
	}
	
	var myObj={};
	
	//SOURCE
	var  regularSourceArray= new Array();
	var inps = document.getElementsByName("sourceId[]");
	var inps1 = document.getElementsByName("sourceStart[]");
	var inps2 = document.getElementsByName("sourceEnd[]");
	var inps3 = document.getElementsByName("avgSourceStart[]");

	for (var i = 0; i < inps.length; i++) {
		var regSourceObj={};
		
		if (inps1[i].value == "" || inps2[i].value == "" || inps3[i].value == "") {
			$("#source_Error_" + i).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		} else {
			//$("#sourceError_" + i).html(""); 
			regSourceObj["sourceId"]=inps[i].value;
			regSourceObj["sourceStart"]=inps1[i].value;
			regSourceObj["sourceEnd"]=inps2[i].value;
			regSourceObj["avgSourceStart"]=inps3[i].value;
			
			
			regularSourceArray.push(regSourceObj);
		}
	}
	myObj["staff"]=staff;
	myObj["regularSourceData"]=regularSourceArray;
	if (flag <= 0) {
		$(document).ready(function () {
			$.ajax({
				type: "POST",
				url: "ajax-save-regular-source-data",
				contentType : "application/json",
				data : JSON.stringify(myObj),
				success: function (res) {
					if (res == "success") {
						$("#regularSourceSavebtn").attr("disabled", true);
						findNextAccordion(el);
						jBoxBottomRightBigNotice("Success", "Source Data Saved !!", "green", "2000");
						
					} else {
						jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
					}
				},
				error: function (e) {
					jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
					window.setTimeout(window.location = "logout", 7000);
				}
			});
		});
	}
}
function saveRegData(type, acc_id,el) {
	var clickedBtnId = ("regSaveBtn_" + type);
	var flag = 0;
	var a_name = new Array();
	var a_quantity = new Array();
	var inps = document.getElementsByName(type + '_name[]');
	var inps1 = document.getElementsByName(type + '_quantity[]');
	for (var i = 0; i < inps.length; i++) {
		var inp = inps[i];
		var inp1 = inps1[i];
		if (inp1.value == "") {
			$("#error_" + type + "_" + i + "Quantity").html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		} else {
			a_name.push(inp.value);
			a_quantity.push(inp1.value);
			$("#error_" + type + "_" + i + "Quantity").html(""); //error_fuel_0Quantity
		}
	}
	if (flag <= 0) {
		$.ajax({
			type: "POST",
			url: "ajax-save-regular-data",
			data: {
				action: 'SaveRegularData',
				a_name: a_name.toString(),
				a_quantity: a_quantity.toString()
			},
			success: function (data) {
				var res = data; //alert(data);
				findNextAccordion(el);
				jBoxBottomRightBigNotice("Success", type + " Data Saved !!", "green", "2000");
				if (inps.length == res) {
					acc_id++;
					$("#" + clickedBtnId).attr("disabled", true);
					
					if(type == "nhwpcf"){
	                	window.location.replace("http://localhost:8080/env/view-regular-data");
	                }
					

				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
				
			},
			error: function (e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
				window.setTimeout(window.location = "logout", 7000)
			}
		});
	}
}

function saveApcData(el) {
	var flag = 0;
	var apc_name = new Array();
	var apc_quantity = new Array();

	var apc_stack_id = new Array();
	var apc_start = new Array();
	var apc_end = new Array();
	var apc_avg = new Array();
	var apcId = 0,
		flag = 0;

	var stackIdArray = document.getElementsByName("stack_id[]");
	var apcStartArray = document.getElementsByName("apc_start[]");
	var apcEndArray = document.getElementsByName("apc_end[]");
	var apcConsumptionArray = document.getElementsByName("apc_consumption[]");


	var apc_consumptions = document.getElementsByName('apc_consumption[]');

	for (var i = 0; i < apc_consumptions.length; i++) {
		var inp1 = apc_consumptions[i];
		var iddd = apc_consumptions[i].id;

		var res = iddd.split("_");
		iddd = res[2];
		var apcEndVal = $("#apc_endid_" + iddd).val();
		if (inp1.value == "" || apcEndVal == "") {
			$("#apc_Error_" + iddd).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		} else {

			apc_stack_id.push(iddd);
			apc_start.push(apcStartArray[i].value);
			apc_end.push(apcEndArray[i].value);
			apc_avg.push(apcConsumptionArray[i].value);

			$("#apc_Error_" + iddd).html(""); // error_fuel_0Quantity
		}
	}

	if (flag <= 0) {
		$.ajax({
			type: 'POST',
			url: 'ajax-save-regular-apc-data',
			data: ({
				action: 'SaveRegularAPCData',
				apc_stack_id: apc_stack_id.toString(),
				apc_start: apc_start.toString(),
				apc_end: apc_end.toString(),
				apc_avg: apc_avg.toString()
			}),
			success: function (data) {
				if (data == 1) {
					$("#saveApcbtn").attr("disabled", true);
					findNextAccordion(el);
					jBoxBottomRightBigNotice("Success", " APC Data Saved !!", "green", "2000");
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			},
			error: function (e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
				window.setTimeout(window.location = "logout", 7000)
			}
		});
	}
}
function getWaterPreFilterDailyInputData(){
	$.ajax({
		type : "POST",
		url : "ajax-getDailyPreFilterData",
		success : function(data) {
				var data = JSON.parse(data);
				var html2="";
				var result=data[0].Result;
				if(result==true){
					var dataList=data[0].Data;
					if(dataList!=null){
					var input=dataList.Input;
					var prefilterList=dataList.prefilterList;
					if(prefilterList.length> 0){
						var html = "<h2><a href='#'> Prefilter </a></h2>" +
								   "<div>" +
						           	"<div class='card-body'>" +
						           		"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
						           			"<table class='table table-bordered' id='dailyip-table'>" +
						           				"<thead> <tr> <th> Prefilter Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
					
						$.each(prefilterList, function (index, element){
							var startReading=element.startReading;
							var endReading=element.endReading;
							var actualReading=element.actualReading;
							var isMeter=element.isMeter;
							var sourceName=element.sourceName;
							var status=element.status;
							var count=element.count;
							var prefilterId=element.prefilterId;
						
							if(status=="Fill"){
								if(isMeter==true){
									html +="<tr>" +
										   			"<input type='hidden' name='prefilterId[]' value='"+prefilterId+"'>"+
										   		"<td><input type='hidden' class='form-control' name='prefilterName[]' value='" + sourceName+count+ "'>" + sourceName +'- Prefilter'+count+ "</td>" +
										   		"<td>" +
										   			"<div class='form-group mb-0'>" +
										   				"<input type='text' name='prefilterStart[]' id='prefilter_startid_" + index + "' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
													"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' class='form-control' placeholder='End Reading' name='prefilterEnd[]' id='prefilter_endid_" + index + "' onkeypress='numberValidate(event,\"prefilter_Error_" + index + "\")' onchange='setConsumption(\"prefilter\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
													"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' placeholder='Act Consumption' name='avgprefilterStart[]' class='form-control' id='prefilter_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
													"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group'> <div id='prefilter_Error_" + index + "'></div> </div>" +
												"</td>" +
											"</tr>";
								     }else{
								    	 html +="<tr>" +
									        			"<input type='hidden' name='prefilterId[]' value='"+prefilterId+"'>"+
									        		"<td><input type='hidden' class='form-control' name='prefilterName[]' value='" + sourceName+count+ "'>" + sourceName +'- Prefilter'+count+ "</td>" +
									        	    "<td>-<input type='hidden' class='form-control' name='prefilterStart[]' value='0'></td>" +
									        	    "<td>-<input type='hidden' class='form-control' name='prefilterEnd[]' value='0'></td>" +
									        	    "<td>" +
									         			"<div class='form-group mb-0'>" +
									         				"<input type='text' placeholder='Act Consumption' name='avgprefilterStart[]' class='form-control' id='prefilter_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
									         			"</div>" +
									         		"</td>" +
									         		"<td>" +
									         			"<div class='form-group'> <div id='prefilter_Error_" + index + "'></div> </div>" +
									         		"</td>" +
									         	"</tr>";
								}
							}else if(status=="Filled"){
								if(isMeter==true){
									html += "<tr>" +
													"<input type='hidden' name='prefilterId1[]' value='"+prefilterId+"'>"+
												"<td><input type='hidden' class='form-control' name='prefilterName1[]' value='" + sourceName+count+ "'>" + sourceName +'- Prefilter'+count+ "</td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' name='prefilterStart1[]'  class='form-control' value='" + startReading + "' disabled/>"+
													"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' name='prefilterEnd1[]'  class='form-control' value='" + endReading + "' disabled/>"+"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' name='avgprefilterStart1[]' class='form-control' value='" + actualReading + "' disabled/>"+"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
												"</td>" +
											"</tr>";
								}else{
									html += "<tr>" +
													"<input type='hidden' name='prefilterId1[]' value='"+prefilterId+"'>"+
												"<td><input type='hidden' class='form-control' name='prefilterName1[]' value='" + sourceName + count+"'>" + sourceName +'- Prefilter'+count+ "</td>" +
												"<td>-<input type='hidden' class='form-control' name='prefilterStart1[]'  value='0'></td>" +
												"<td>-<input type='hidden' class='form-control' name='prefilterEnd1[]' value='0'></td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' name='avgprefilterStart1[]' class='form-control' value='" + actualReading + "' disabled/>"+"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
												"</td>" +
											"</tr>";
								}
							}else if(status=="New"){
								if(isMeter==true){
									html +="<tr>" +
										   			"<input type='hidden' name='prefilterId[]' value='"+prefilterId+"'>"+
										   		"<td><input type='hidden' class='form-control' name='prefilterName[]' value='" + sourceName +count+ "'>" + sourceName +'- Prefilter'+count+ "</td>" +
										   		"<td>" +
										   			"<div class='form-group mb-0'>" +
										   				"<input type='text' value='0' name='prefilterStart[]' id='prefilter_startid_" + index + "' class='form-control' /> <i class='form-group__bar'></i>" +
										   			"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' class='form-control' placeholder='End Reading' name='prefilterEnd[]' id='prefilter_endid_" + index + "' onkeypress='numberValidate(event,\"prefilter_Error_" + index + "\")' onchange='setConsumption(\"prefilter\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
													"</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' placeholder='Act Consumption' name='avgprefilterStart[]' class='form-control' id='prefilter_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
													"</div>" +
												"</td>" +
									            "<td>" +
									        		"<div class='form-group'> <div id='prefilter_Error_" + index + "'></div> </div>" +
									        	"</td>" +
									       "</tr>";
								}else{
									html +="<tr>" +
													"<input type='hidden' name='prefilterId[]' value='"+prefilterId+"'>"+
												"<td><input type='hidden' class='form-control' name='prefilterName[]' value='" + sourceName+count+ "'>" + sourceName +'- Prefilter'+count+ "</td>" +
												"<td>-<input type='hidden' class='form-control' name='prefilterStart[]' value='0'></td>" +
												"<td>-<input type='hidden' class='form-control' name='prefilterEnd[]' value='0'></td>" +
												"<td>" +
													"<div class='form-group mb-0'>" +
														"<input type='text' placeholder='Act Consumption' name='avgprefilterStart[]' class='form-control' id='prefilter_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
												    "</div>" +
												"</td>" +
												"<td>" +
													"<div class='form-group'> <div id='prefilter_Error_" + index + "'></div> </div>" +
												"</td>" +
											"</tr>";
								}
							}
						});
					}
					if(input==true){
						html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='regularPrefilterSavebtn' onclick='saveRegularPrefilterData(this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
										"</div> </div> </div>";
					}else
						{
						 html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> submitted </button> </center> </div>" +
						 				"</div> </div> </div>";
						}
					
					}
					var finalHtml = html + html2;
					$("#waterMainAccordion").append(finalHtml);
				}
                 
     			//$("#waterMainAccordion").accordion({heightStyle: 'content',collapsible: true});
		},
		async: false
	});
}

function saveRegularPrefilterData(e2){
	var prefilterArray = new Array();
    var myObj={};
    var flag=0;
	var inps = document.getElementsByName("prefilterName[]");
	var inps1 = document.getElementsByName("prefilterStart[]");
	var inps2 = document.getElementsByName("prefilterEnd[]");
	var inps3 = document.getElementsByName("avgprefilterStart[]");
	var inps4=document.getElementsByName("prefilterId[]");
	for (var i = 0; i < inps.length; i++) {
		var prefilterObj={};
		
		if (inps1[i].value == "" || inps2[i].value == "" || inps3[i].value == "") {
			$("#prefilter_Error_" + i).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		}else{
			$("#prefilter_Error_" + i).html("");
			prefilterObj["prefilterName"]=inps[i].value;
			prefilterObj["prefilterStart"]=inps1[i].value;
			prefilterObj["prefilterEnd"]=inps2[i].value;
			prefilterObj["prefilterAvg"]=inps3[i].value;
			prefilterObj["prefilterId"]=inps4[i].value;
			prefilterArray.push(prefilterObj);
	}
	}
	myObj["regPrefilterData"]=prefilterArray;
	if(flag<=0){
	$(document).ready(function () {
		$.ajax({
			type: "POST",
			url: "ajax-save-regular-prefilter-data",
			contentType : "application/json",
			data : JSON.stringify(myObj),
			success: function (res) {
				if (res == "success") {
					$("#regularPrefilterSavebtn").attr("disabled", true);
					findNextAccordion(e2);
					jBoxBottomRightBigNotice("Success", "Prefilter Data Saved !!", "green", "2000");
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			},
			error: function (e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
				window.setTimeout(window.location = "logout", 7000);
			}
		});
	});
	}
}
function getWaterFilterDailyInputData(){
	$.ajax({
		type: "POST",
		url: "ajax-getDailyFilterData",
		success: function (res) {
			var data=JSON.parse(res);
			var result=data[0].Result;
			if(result==true){
				var dataList=data[0].Data;
				if(dataList!=null){
					var ui=dataList.UiOption;
					var input=dataList.Input;
					var filterList=dataList.filterList;
					if(filterList!=null){
					if(ui=="Normal"){
						var html = "<h2><a href='#'> Filter </a></h2>" +
						   			"<div>" +
						   				"<div class='card-body'>" +
						   					"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
						   						"<table class='table table-bordered' id='dailyip-table'>" +
						   							"<thead> <tr> <th> Filter Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
						$.each(filterList, function (index, element){
							var filterId=element.filterId;
							var filterName=element.filterName;
							var filterType=element.filterType;
							var isMeter=element.isMeter;
							var startReading=element.startReading;
							var endReading=element.endReading;
							var actualReading=element.actualConsumtion;
							var status=element.status;
							if(status=="Fill"){
								if(isMeter==true){
									html +="<tr>" +
													"<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
												"<td><input type='hidden' class='form-control' name='filterName[]' value='" + filterName+ "'>"+filterName+"</td>" +
												"<td>" +
										    	 	"<div class='form-group mb-0'>" +
										    	 		"<input type='text' name='filterStart[]' id='filter_startid_" + index + "' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
										    	 	"</div>" +
										    	 "</td>" +
										    	 "<td>" +
										    	 	"<div class='form-group mb-0'>" +
										   		 		"<input type='text' class='form-control' placeholder='End Reading' name='filterEnd[]' id='filter_endid_" + index + "' onkeypress='numberValidate(event,\"filter_Error_" + index + "\")' onchange='setConsumption(\"filter\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
										   		 	"</div>" +
										   		 "</td>" +
										   		 "<td>" +
										   		 	"<div class='form-group mb-0'>" +
										   		 		"<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
										   		 	"</div>" +
										   		 "</td>" +
										   		 "<td>" +
										   		 	"<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
										   		 "</td>" +
										   		"</tr>";
								}else{
									html +="<tr>" +
						        	               "<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
						        	            "<td><input type='hidden' class='form-control' name='filterName[]' value='" + filterName+ "'>" +filterName+"</td>" +
						        	            "<td>-<input type='hidden' class='form-control' name='filterStart[]' value='0'></td>" +
						        	            "<td>-<input type='hidden' class='form-control' name='filterEnd[]' value='0'></td>" +
						        	            "<td>" +
						         		             "<div class='form-group mb-0'>" +
						         		                "<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
						         		             "</div>" +
						         	           "</td>" +
						         	            "<td>" +
						         		             "<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
						         	           "</td>" +
						                 "</tr>";	
								}
							}else if(status=="Filled"){
								if(isMeter==true){
									html +="<tr>" +
									               "<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
									            "<td><input type='hidden' class='form-control' name='filterName[]' value='" + filterName+ "'>"+filterName+"</td>" +
									            "<td>" +
										             "<div class='form-group mb-0'>" +
											         	 "<input type='text' name='filterStart1[]' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
											         "</div>" +
								               "</td>" +
								               "<td>" +
										            "<div class='form-group mb-0'>" +
										            	"<input type='text' name='filterEnd1[]' class='form-control' value='"+ endReading +"' disabled/> <i class='form-group__bar'></i>" +
										             "</div>" +
										       "</td>" +
										       "<td>" +
										       		"<div class='form-group mb-0'>" +
										       			"<input type='text' name='avgfilterStart1[]' class='form-control'  value='"+ actualReading +"' disabled/> <i class='form-group__bar'></i>" +
										            "</div>" +
										      "</td>" +
									          "<td>" +
									          	"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
									          "</td>" +
									    "</tr>";
								}else{
									html +="<tr>" +
												 	"<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
												"<td><input type='hidden' class='form-control' name='filterName1[]' value='" + filterName+ "'>"+filterName+"</td>" +
												"<td>-</td>" +
												"<td>-</td>" +
												"<td>" +
													 "<div class='form-group mb-0'>" +
													 	"<input type='text' name='avgfilterStart1[]' class='form-control' value='"+actualReading+"' disabled/> <i class='form-group__bar'></i>" +
													 "</div>" +
											    "</td>" +
											    "<td>" +
											    	 "<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
											    "</td>" +
										  "</tr>";
								}
							}else if(status=="New"){
								if(isMeter==true){
									html +="<tr>" +
												   "<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
											   "<td><input type='hidden' class='form-control' name='filterName[]' value='" + filterName+ "'>"+filterName+"</td>" +
									           "<td>" +
									           		"<div class='form-group mb-0'>" +
									           			"<input type='text' class='form-control' placeholder='Start Reading' name='filterStart[]' id='filter_startid_" + index + "' class='form-control' value='0'/> <i class='form-group__bar'></i>" +
									           		"</div>" +
									           "</td>" +
									           "<td>" +
									           		"<div class='form-group mb-0'>" +
									           			"<input type='text'  class='form-control' placeholder='End Reading' name='filterEnd[]' id='filter_endid_" + index + "' onkeypress='numberValidate(event,\"filter_Error_" + index + "\")' onchange='setConsumption(\"filter\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
									           		"</div>" +
									           "</td>" +
									           "<td>" +
									           		"<div class='form-group mb-0'>" +
									           			"<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
									           		"</div>" +
									           "</td>" +
									           "<td>" +
									           	    "<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
									           "</td>" +
									       "</tr>";
								}else{
									html +="<tr>" +
						        				  "<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
						        				"<td><input type='hidden' class='form-control' name='filterName[]' value='" + filterName+ "'>"+filterName +"</td>" +
						        				"<td>-<input type='hidden' class='form-control' name='filterStart[]' value='0'></td>" +
						        				"<td>-<input type='hidden' class='form-control' name='filterEnd[]' value='0'></td>" +
						        				"<td>" +
						        					 "<div class='form-group mb-0'>" +
						        					 	"<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
						        					 "</div>" +
						        				"</td>" +
						        				"<td>" +
						        					 "<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
						        				"</td>" +
						        		  "</tr>";
								}
							}
						});
						if(input==true){
							html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='regularFilterSavebtn' onclick='saveRegularAllFiltersData(this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
							        	"</div> </div> </div>";
						}else{
							 html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> submitted </button> </center> </div>" +
										"</div> </div> </div>";
						}
						var finalHtml=html+html2;
						 $("#waterMainAccordion").append(finalHtml);
					}else if(ui=="Advanced"){
						var filterAccordionHtml = "<h2><a href='#'>Filter</a></h2>" +
													"<div id='filterAccordion'>"+
													"</div>";
                        $("#waterMainAccordion").append(filterAccordionHtml); 
                        
                        $.each(filterList, function (index, element){
                        	var filterId=element.filterId;
							var filterName=element.filterName;
							var filterType=element.filterType;
							var isMeter=element.isMeter;
							var startReading=element.startReading;
							var endReading=element.endReading;
							var actualReading=element.actualConsumtion;
							var status=element.status;
                        	var filterNameHtml="<h2><a href='#'>"+filterName+"</a></h2>"+
                        					   	"<div>" +
                        					   		"<div class='card-body'>" +
                        					   			"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
                        					   				"<table class='table table-bordered' id='dailyip-table'>" +
                        					   					"<thead> <tr> <th> Filter Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
                        			if(status=="Fill"){
  								        if(isMeter==true){
  								        	filterNameHtml +="<tr>" +
  									        				 		"<input type='hidden' name='filterId[]' id='filter_id_"+ index + "' value='"+filterId+"'>"+
  									        				 	"<td><input type='hidden' class='form-control' name='filterName[]' id='filter_name_"+ index + "' value='" + filterName+ "'>"+filterName+"</td>" +
  									        				 	"<td>" +
  									        				 		"<div class='form-group mb-0'>" +
  									        				 			"<input type='text' name='filterStart[]' id='filter_startid_" + index + "' class='form-control' value='" + startReading+ "' disabled/> <i class='form-group__bar'></i>" +
  									        				 		"</div>" +
  									        					 "</td>" +
  									        					 "<td>" +
  									        					 	"<div class='form-group mb-0'>" +
  									        					 		"<input type='text' class='form-control' placeholder='End Reading' name='filterEnd[]' id='filter_endid_" + index + "' onkeypress='numberValidate(event,\"filter_Error_" + index + "\")' onchange='setConsumption(\"filter\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
  									        					 	"</div>" +
  									        					 "</td>" +
  									        					 "<td>" +
  									        					 	"<div class='form-group mb-0'>" +
  									        					 		"<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
  									        					 	"</div>" +
  									        					 "</td>" +
  									        					 "<td>" +
  									        					 	"<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
  									        					 "</td>" +
  									        					"</tr>";
  								}else{
  									filterNameHtml +="<tr>" +
  						        					 		"<input type='hidden' name='filterId[]' id='filter_id_"+ index + "' value='"+filterId+"'>"+
  						        					 	"<td><input type='hidden' class='form-control' name='filterName[]' id='filter_name_"+ index + "' value='" + filterName+ "'>" +filterName+"</td>" +
  						        					 	"<td>-<input type='hidden' class='form-control' name='filterStart[]' id='filter_startid_" + index + "' value='0'></td>" +
  						        					 	"<td>-<input type='hidden' class='form-control' name='filterEnd[]' id='filter_endid_" + index + "' value='0'></td>" +
  						        					 	"<td>" +
  						        					 		"<div class='form-group mb-0'>" +
  						        					 			"<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
  						        					 		"</div>" +
  						        					 	"</td>" +
  						        					 	"<td>" +
  						        					 		"<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
  						        					 	"</td>" +
  						        					 "</tr>";	
  								}
  							}else if(status=="Filled"){
  								if(isMeter==true){
  									filterNameHtml +="<tr>" +
  													 		"<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
  													 	"<td><input type='hidden' class='form-control' name='filterName[]' value='" + filterName+ "'>"+filterName+"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group mb-0'>" +
  													 			"<input type='text' name='filterStart1[]'  class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
  													 		"</div>" +
  													 	"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group mb-0'>" +
  													 			"<input type='text' name='filterEnd1[]'  class='form-control' value='" + endReading + "' disabled/> <i class='form-group__bar'></i>" +
  													 		"</div>" +
  													 	"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group mb-0'>" +
  													 			"<input type='text' name='avgfilterStart1[]' class='form-control' id='filter_avgid_" + index + "' value='" + actualReading + "' disabled/> <i class='form-group__bar'></i>" +
  													 		"</div>" +
  													 	"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
  													 	"</td>" +
  													 "</tr>";
  								}else{
  									filterNameHtml +="<tr>" +
  													 		"<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
  													 	"<td><input type='hidden' class='form-control' name='filterName1[]' value='" + filterName+ "'>"+filterName+"</td>" +
  													 	"<td>-</td>" +
  													 	"<td>-</td>" +
  													 	"<td>" +
  													 		"<div class='form-group mb-0'>" +
  													 			"<input type='text' name='avgfilterStart1[]' class='form-control'  value='"+actualReading+"' id='filter_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
  													 		"</div>" +
  													 	"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
  													 	"</td>" +
  													 "</tr>";
  								}
  							}else if(status=="New"){
  								if(isMeter==true){
  									filterNameHtml +="<tr>" +
  													 		"<input type='hidden' name='filterId[]' id='filter_id_"+ index + "' value='"+filterId+"'>"+
  													 	"<td><input type='hidden' class='form-control' name='filterName[]' id='filter_name_"+ index + "' value='" + filterName+ "'>"+filterName+"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group mb-0'>" +
  													 			"<input type='text' class='form-control' placeholder='Start Reading' name='filterStart[]' id='filter_startid_" + index + "' class='form-control' value='0'/> <i class='form-group__bar'></i>" +
  													 		"</div>" +
  													 	"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group mb-0'>" +
  													 			"<input type='text'  class='form-control' placeholder='End Reading' name='filterEnd[]' id='filter_endid_" + index + "' onkeypress='numberValidate(event,\"filter_Error_" + index + "\")' onchange='setConsumption(\"filter\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
  													 		"</div>" +
  													 	"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group mb-0'>" +
  													 			"<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
  													 		"</div>" +
  													 	"</td>" +
  													 	"<td>" +
  													 		"<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
  													 	"</td>" +
  													 "</tr>";
  								}else{
  									filterNameHtml +="<tr>" +
  						        					 		"<input type='hidden' name='filterId[]' id='filter_id_"+ index + "' value='"+filterId+"'>"+
  						        					 	"<td><input type='hidden' class='form-control' name='filterName[]' id='filter_name_"+ index + "' value='" + filterName+ "'>"+filterName +"</td>" +
  						        					 	"<td>-<input type='hidden' class='form-control' name='filterStart[]' id='filter_startid_" + index + "' value='0'></td>" +
  						        					 	"<td>-<input type='hidden' class='form-control' name='filterEnd[]' id='filter_endid_" + index + "' value='0'></td>" +
  						        					 	"<td>" +
  						        					 		"<div class='form-group mb-0'>" +
  						        					 			"<input type='text' placeholder='Act Consumption' name='avgfilterStart[]' class='form-control' id='filter_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
  						        					 		"</div>" +
  						        					 	"</td>" +
  						        					 	"<td>" +
  						        					 		"<div class='form-group'> <div id='filter_Error_" + index + "'></div> </div>" +
  						        					 	"</td>" +
  						        					 "</tr>";
  								}
  							}
                        	  filterNameHtml +="</tbody> </table>";
                        	  if(status=="New"||status=="Fill"){
                        		 filterNameHtml += "<div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='regularFilterSavebtn' onclick='saveRegularfilterData(\"" +index+ "\", this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
      											   		"</div> </div>";
      						}else{
      							 filterNameHtml += "<div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> Submitted </button> </center> </div>" +
      											   		"</div> </div>";
      						}
                        	  filterNameHtml +="</div></div>";
                        	  $("#filterAccordion").append(filterNameHtml);
                        });
                        $("#filterAccordion").accordion({
    		  				heightStyle: 'content',
    		  				collapsible: true
    		  			});
                        }//advanced
					}
					}
				}
		},
		async: false
		});
}
function sendToFilterApi(filterArray, flag, e1){
	var myObj={};
	myObj["regfilterData"]=filterArray;
	if(flag<=0){
	$(document).ready(function () {
		$.ajax({
			type: "POST",
			url: "ajax-save-regular-filter-data",
			contentType : "application/json",
			data : JSON.stringify(myObj),
			success: function (res) {
				if (res == "success") {
					$(e1).prop('disabled', true);
					findNextAccordion(e1);
					jBoxBottomRightBigNotice("Success", "Filter Data Saved !!", "green", "2000");
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			},
			error: function (e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
				window.setTimeout(window.location = "logout", 7000);
			}
		});
	});
	}
}
function saveRegularAllFiltersData(e1){
	var filterArray=new Array();
    var flag=0;
    var inps = document.getElementsByName("filterId[]");
	var inps1 = document.getElementsByName("filterName[]");
	var inps2 = document.getElementsByName("filterStart[]");
	var inps3 = document.getElementsByName("filterEnd[]");
	var inps4 = document.getElementsByName("avgfilterStart[]");
	for (var i = 0; i < inps.length; i++) {
		var filterObj={};
		if (inps2[i].value == "" || inps3[i].value == "" || inps4[i].value == "") {
			$("#filter_Error_" + i).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		}else{
			$("#filter_Error_" + i).html("");
			filterObj["filterId"]=inps[i].value;
			filterObj["filterName"]=inps1[i].value;
			filterObj["filterStart"]=inps2[i].value;
			filterObj["filterEnd"]=inps3[i].value;
			filterObj["filterAvg"]=inps4[i].value;
			filterArray.push(filterObj);
	}
	}
	sendToFilterApi(filterArray, flag, e1);
}

function saveRegularfilterData(index, e3){
	var filterArray=new Array();
	var myObj={};
    var flag=0;
    var filterId= $("#filter_id_"+index);
	var filterName = $("#filter_name_"+index);
	var filterStartId  = $("#filter_startid_"+index);
	var filterEndId  = $("#filter_endid_"+index);
	var filterAvgId = $("#filter_avgid_"+index);
	
	var filterObj={};
	if(filterStartId.val()==""||filterEndId.val()==""||filterAvgId.val()==""){
		$("#filter_Error_" + index).html("<label class='text-red'>Enter Quantity</label>");
		flag++;
	}else{
		$("#filter_Error_" + index).html("");
		filterObj["filterId"]=filterId.val();
		filterObj["filterName"]=filterName.val();
		filterObj["filterStart"]=filterStartId.val();
		filterObj["filterEnd"]=filterEndId.val();
		filterObj["filterAvg"]=filterAvgId.val();
		filterArray.push(filterObj);
	}
	sendToFilterApi(filterArray, flag, e3);
}

function getWaterFilterUseDailyData(){
	$.ajax({
		type: "POST",
		url: "ajax-getDailyFilterUseData",
		success: function (res) {
		var data=JSON.parse(res);
		if(data.length>0){
			var result=data[0].Result;
			if(result==true){
				var dataList=data[0].Data;
				if(dataList!=null){
					var filterUseList=dataList.filterUseList;
					var input=dataList.Input;
					var uiOption=dataList.UiOption;
					if(filterUseList.length>0){
						if(uiOption=="Normal"){
							var html = "<h2><a href='#'>FilterUse </a></h2>" +
				   						"<div>" +
				   							"<div class='card-body'>" +
				   								"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
				   									"<table class='table table-bordered' id='dailyip-table'>" +
				   										"<thead> <tr> <th> FilterUse Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
								
							$.each(filterUseList, function (index, element){
								var filterUseLabel=element.filterUseLabel;
								var filterUseType=element.filterUseType;
								var isMeter=element.isMeter;
								var startReading=element.startReading;
								var endReading=element.endReading;
								var actualReading=element.actualReading;
								var status=element.status;
								if(status=="Fill"){
									if(isMeter==true){
										html +="<tr>" +
										 				"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
										 			"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
													"<td>" +
											    		"<div class='form-group mb-0'>" +
											    	 		"<input type='text' name='filterUseStart[]' id='filterUse_startid_" + index + "' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
											    	 	"</div>" +
											    	 "</td>" +
											    	 "<td>" +
											    	 	"<div class='form-group mb-0'>" +
											   		 		"<input type='text' class='form-control' placeholder='End Reading' name='filterUseEnd[]' id='filterUse_endid_" + index + "' onkeypress='numberValidate(event,\"filterUse_Error_" + index + "\")' onchange='setConsumption(\"filterUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
											   		 	"</div>" +
											   		 "</td>" +
											   		 "<td>" +
											   		 	"<div class='form-group mb-0'>" +
											   		 		"<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
											   		 	"</div>" +
											   		 "</td>" +
											   		 "<td>" +
											   		 	"<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
											   		 "</td>" +
											   	"</tr>";
									}else{
										html +="<tr>" +
										 	   			"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
										 	    	"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
										 	    	"<td>-<input type='hidden' class='form-control' name='filterUseStart[]' value='0'></td>" +
										 	    	"<td>-<input type='hidden' class='form-control' name='filterUseEnd[]' value='0'></td>" +
										 	    	"<td>" +
							         		        	"<div class='form-group mb-0'>" +
							         		                "<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
							         		             "</div>" +
							         	           "</td>" +
							         	           "<td>" +
							         		       		"<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
							         	           "</td>" +
							                   "</tr>";	
									}
								}else if(status=="Filled"){
									if(isMeter==true){
										html +="<tr>" +
										 	   			"<input type='hidden' name='filterUseLabel1[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
										 	   		"<td><input type='hidden' class='form-control' name='filterUseName1[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
										            "<td>" +
											        	"<div class='form-group mb-0'>" +
												        	"<input type='text' name='filterUseStart1[]' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
												         "</div>" +
												    "</td>" +
												    "<td>" +
											            "<div class='form-group mb-0'>" +
											            	"<input type='text' name='filterUseEnd1[]' class='form-control' value='"+ endReading +"' disabled/> <i class='form-group__bar'></i>" +
											             "</div>" +
											       "</td>" +
											       "<td>" +
											       		"<div class='form-group mb-0'>" +
											       			"<input type='text' name='avgfilterUseStart1[]' class='form-control'  value='"+ actualReading +"' disabled/> <i class='form-group__bar'></i>" +
											            "</div>" +
											      "</td>" +
										          "<td>" +
										          	"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
										          "</td>" +
										    "</tr>";
									}else{
										html +="<tr>" +
										 				"<input type='hidden' name='filterUseLabel1[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
										 			"<td><input type='hidden' class='form-control' name='filterUseName1[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
													"<td>-</td>" +
													"<td>-</td>" +
													"<td>" +
														 "<div class='form-group mb-0'>" +
														 	"<input type='text' name='avgfilterUseStart1[]' class='form-control' value='"+actualReading+"' disabled/> <i class='form-group__bar'></i>" +
														 "</div>" +
												    "</td>" +
												    "<td>" +
												    	"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
												    "</td>" +
											  "</tr>";
									}
								}else if(status=="New"){
									if(isMeter==true){
										html +="<tr>" +
											   			"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
											   		"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
										            "<td>" +
										           		"<div class='form-group mb-0'>" +
										           			"<input type='text' class='form-control' placeholder='Start Reading' name='filterUseStart[]' id='filterUse_startid_" + index + "' class='form-control' value='0'/> <i class='form-group__bar'></i>" +
										           		"</div>" +
										           	"</td>" +
										           	"<td>" +
										           		"<div class='form-group mb-0'>" +
										           			"<input type='text'  class='form-control' placeholder='End Reading' name='filterUseEnd[]' id='filterUse_endid_" + index + "' onkeypress='numberValidate(event,\"filterUse_Error_" + index + "\")' onchange='setConsumption(\"filterUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
										           		"</div>" +
										           "</td>" +
										           "<td>" +
										           		"<div class='form-group mb-0'>" +
										           			"<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
										           		"</div>" +
										           "</td>" +
										           "<td>" +
										           	    "<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
										           "</td>" +
										       "</tr>";
									}else{
										html +="<tr>" +
										 				"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
							            			"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
							        				"<td>-<input type='hidden' class='form-control' name='filterUseStart[]' value='0'></td>" +
							        				"<td>-<input type='hidden' class='form-control' name='filterUseEnd[]' value='0'></td>" +
							        				"<td>" +
							        					 "<div class='form-group mb-0'>" +
							        					 	"<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
							        					 "</div>" +
							        				"</td>" +
							        				"<td>" +
							        					 "<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
							        				"</td>" +
							        		  "</tr>";
									}
								}
							});
							if(input==true){
								html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='regularFilterUseSavebtn' onclick='saveRegularAllFiltersUseData(this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
								        	"</div> </div> </div>";
							}else{
								 html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> submitted </button> </center> </div>" +
											"</div> </div> </div>";
							}
							var finalHtml=html+html2;
							 $("#waterMainAccordion").append(finalHtml);
							
						}else if(uiOption=="Advanced"){
							var filterUseAccordionHtml = "<h2><a href='#'>FilterUse</a></h2>" +
														"<div id='filterUseAccordion'>"+
														"</div>";
												$("#waterMainAccordion").append(filterUseAccordionHtml); 
												
							$.each(filterUseList, function (index, element){
								var filterUseLabel=element.filterUseLabel;
								var filterUseType=element.filterUseType;
								var isMeter=element.isMeter;
								var startReading=element.startReading;
								var endReading=element.endReading;
								var actualReading=element.actualReading;
								var status=element.status;
													
								var filterUseNameHtml="<h2><a href='#'>"+filterUseLabel+" ("+filterUseType+")"+"</a></h2>"+
	                        					   	  	"<div>" +
	                        					   	  		"<div class='card-body'>" +
	                        					   	  			"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
	                        					   	  				"<table class='table table-bordered' id='dailyip-table'>" +
	                        					   	  					"<thead> <tr> <th> FilterUse Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
								if(status=="Fill"){
									if(isMeter==true){
										filterUseNameHtml+="<tr>" +
														   			"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
																"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
																"<td>" +
																	"<div class='form-group mb-0'>" +
																    	"<input type='text' name='filterUseStart[]' id='filterUse_startid_" + index + "' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
																    "</div>" +
																"</td>" +
																"<td>" +
																	"<div class='form-group mb-0'>" +
																   		"<input type='text' class='form-control' placeholder='End Reading' name='filterUseEnd[]' id='filterUse_endid_" + index + "' onkeypress='numberValidate(event,\"filterUse_Error_" + index + "\")' onchange='setConsumption(\"filterUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
																   	"</div>" +
																"</td>" +
																"<td>" +
																	"<div class='form-group mb-0'>" +
																    	"<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
																   	"</div>" +
																"</td>" +
																"<td>" +
																	"<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
																"</td>" +
															"</tr>";
														}else{
															filterUseNameHtml +="<tr>" +
															 							"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
															 						"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
															 						"<td>-<input type='hidden' class='form-control' name='filterUseStart[]' value='0'></td>" +
															 						"<td>-<input type='hidden' class='form-control' name='filterUseEnd[]' value='0'></td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
															 						"</td>" +
															 					"</tr>";	
														}
													}else if(status=="Filled"){
														if(isMeter==true){
															filterUseNameHtml +="<tr>" +
															 							"<input type='hidden' name='filterUseLabel1[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
															 						"<td>" +
															 							"<input type='hidden' class='form-control' name='filterUseName1[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text' name='filterUseStart1[]' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text' name='filterUseEnd1[]' class='form-control' value='"+ endReading +"' disabled/> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text' name='avgfilterUseStart1[]' class='form-control'  value='"+ actualReading +"' disabled/> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
															 						"</td>" +
															 					"</tr>";
														}else{
															filterUseNameHtml +="<tr>" +
													         							"<input type='hidden' name='filterUseLabel1[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
													         						"<td><input type='hidden' class='form-control' name='filterUseName1[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
													         						"<td>-</td>" +
													         						"<td>-</td>" +
													         						"<td>" +
													         							"<div class='form-group mb-0'>" +
													         								"<input type='text' name='avgfilterUseStart1[]' class='form-control' value='"+actualReading+"' disabled/> <i class='form-group__bar'></i>" +
													         							"</div>" +
													         						"</td>" +
													         						"<td>" +
													         							"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
													         						"</td>" +
													         					"</tr>";
														}
													}else if(status=="New"){
														if(isMeter==true){
															filterUseNameHtml +="<tr>" +
															 							"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
															 						"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text' class='form-control' placeholder='Start Reading' name='filterUseStart[]' id='filterUse_startid_" + index + "' class='form-control' value='0'/> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text'  class='form-control' placeholder='End Reading' name='filterUseEnd[]' id='filterUse_endid_" + index + "' onkeypress='numberValidate(event,\"filterUse_Error_" + index + "\")' onchange='setConsumption(\"filterUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
															 						"</td>" +
															 					"</tr>";
														}else{
															filterUseNameHtml +="<tr>" +
															 							"<input type='hidden' name='filterUseLabel[]' id='filterUseLabel_" + index + "' value='"+filterUseLabel+"'>"+
															 						"<td><input type='hidden' class='form-control' name='filterUseName[]' value='" + filterUseLabel+ "' +' (' +'"+filterUseType+"'+ ')' >"+filterUseLabel+" ("+filterUseType+")"+"</td>" +
															 						"<td>-<input type='hidden' class='form-control' name='filterUseStart[]' value='0'></td>" +
															 						"<td>-<input type='hidden' class='form-control' name='filterUseEnd[]' value='0'></td>" +
															 						"<td>" +
															 							"<div class='form-group mb-0'>" +
															 								"<input type='text' placeholder='Act Consumption' name='avgfilterUseStart[]' class='form-control' id='filterUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
															 							"</div>" +
															 						"</td>" +
															 						"<td>" +
															 							"<div class='form-group'> <div id='filterUse_Error_" + index + "'></div> </div>" +
															 						"</td>" +
															 					"</tr>";
														}
													}
													filterUseNameHtml +="</tbody> </table>";
													if(status=="Fill"||status=="New"){
														filterUseNameHtml+="<div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='regularFilterUseSavebtn' onclick='saveRegularfilterUseData(\"" +index+ "\", this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
								   							"</div> </div>";
													}else{
														filterUseNameHtml+="<div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> Submitted</button> </center> </div>" +
							   							"</div> </div>";
													}
						                        	  filterUseNameHtml +="</div></div>";
						                        	  $("#filterUseAccordion").append(filterUseNameHtml);
												});
												 $("#filterUseAccordion").accordion({
							    		  				heightStyle: 'content',
							    		  				collapsible: true
							    		  			});
						}//advanced
					}
				}
			}
		}
		},
		async: false
	});
}

function saveRegularfilterUseData(index, e2){
	var filterUseArray=new Array();
	var myObj={};
    var flag=0;
	var filterUseLabel = $("#filterUseLabel_"+index);
	var filterUseStartId  = $("#filterUse_startid_"+index);
	var filterUseEndId  = $("#filterUse_endid_"+index);
	var filterUseAvgId = $("#filterUse_avgid_"+index);
	var filterUseObj={};
	if(filterUseStartId.val()==""||filterUseEndId.val()==""||filterUseAvgId.val()==""){
		$("#filterUse_Error_" + index).html("<label class='text-red'>Enter Quantity</label>");
		flag++;
	}else{
		$("#filterUse_Error_" + index).html("");
		filterUseObj["filterUseLabel"]=filterUseLabel.val();
		filterUseObj["filterUseStart"]=filterUseStartId.val();
		filterUseObj["filterUseEnd"]=filterUseEndId.val();
		filterUseObj["filterUseAvg"]=filterUseAvgId.val();
		filterUseArray.push(filterUseObj);
	}
	sendToFilterUseApi(filterUseArray, flag, e2);
}

function saveRegularAllFiltersUseData(e1){
	var filterUseArray=new Array();
    var flag=0;
	var inps1 = document.getElementsByName("filterUseLabel[]");
	var inps2 = document.getElementsByName("filterUseStart[]");
	var inps3 = document.getElementsByName("filterUseEnd[]");
	var inps4 = document.getElementsByName("avgfilterUseStart[]");
	for (var i = 0; i < inps1.length; i++) {
		var filterUseObj={};
		if (inps2[i].value == "" || inps3[i].value == "" || inps4[i].value == "") {
			$("#filterUse_Error_" + i).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		}else{
			$("#filterUse_Error_" + i).html("");
			filterUseObj["filterUseLabel"]=inps1[i].value;
			filterUseObj["filterUseStart"]=inps2[i].value;
			filterUseObj["filterUseEnd"]=inps3[i].value;
			filterUseObj["filterUseAvg"]=inps4[i].value;
			filterUseArray.push(filterUseObj);
	}
	}
	sendToFilterUseApi(filterUseArray, flag, e1);
}
function sendToFilterUseApi(filterUseArray, flag, e1){
	var myObj={};
	myObj["regfilterUseData"]=filterUseArray;
	if(flag<=0){
	$(document).ready(function () {
		$.ajax({
			type: "POST",
			url: "ajax-save-regular-filter-use-data",
			contentType : "application/json",
			data : JSON.stringify(myObj),
			success: function (res) {
				if (res == "success") {
					$(e1).prop('disabled', true);
					findNextAccordion(e1);
					jBoxBottomRightBigNotice("Success", "FilterUse Data Saved !!", "green", "2000");
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			},
			error: function (e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
				window.setTimeout(window.location = "logout", 7000);
			}
		});
	});
	}
}

function getUseOfWaterDailyInputData() {
	$.ajax({
		type: "POST",
		url: "ajax-getDailyUseOfWaterData",
		success: function (res) {
			var data=JSON.parse(res);
			if(data.length>0){
				var result=data[0].Result;
				if(result==true){
					var dataList=data[0].Data;
					if(dataList!=null){
						var input=dataList.Input;
						var uiOption=dataList.UiOption;
						var directUseOfWaterData=dataList.useOfWaterData;
						if(directUseOfWaterData!=null){
							if(uiOption=="Normal"){
								var html = "<h2><a href='#'>DirectUseOfWater </a></h2>" +
		   									"<div>" +
		   										"<div class='card-body'>" +
		   											"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
		   												"<table class='table table-bordered' id='dailyip-table'>" +
		   													"<thead> <tr> <th> DirectUse Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
								$.each(directUseOfWaterData, function (index, element){
									var typeOfUse=element.typeOfUse;
									var whereToUse=element.whereToUse;
									var isMeter=element.isMeter;
									var startReading=element.startReading;
									var endReading=element.endReading;
									var actualReading=element.actualReading;
									var status=element.status;
									if(status=="Fill"){
										if(isMeter==true){
											html +="<tr>" +
											 				"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
											 			"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
														"<td>" +
												    		"<div class='form-group mb-0'>" +
												    	 		"<input type='text' name='directUseStart[]' id='directUse_startid_" + index + "' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
												    	 	"</div>" +
												    	 "</td>" +
												    	 "<td>" +
												    	 	"<div class='form-group mb-0'>" +
												   		 		"<input type='text' class='form-control' placeholder='End Reading' name='directUseEnd[]' id='directUse_endid_" + index + "' onkeypress='numberValidate(event,\"directUse_Error_" + index + "\")' onchange='setConsumption(\"directUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
												   		 	"</div>" +
												   		 "</td>" +
												   		 "<td>" +
												   		 	"<div class='form-group mb-0'>" +
												   		 		"<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
												   		 	"</div>" +
												   		 "</td>" +
												   		 "<td>" +
												   		 	"<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
												   		 "</td>" +
												   	"</tr>";
										}else{
											html +="<tr>" +
											 	   			"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
											 	    	"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
											 	    	"<td>-<input type='hidden' class='form-control' name='directUseStart[]' value='0' id='directUse_startid_" + index + "'></td>" +
											 	    	"<td>-<input type='hidden' class='form-control' name='directUseEnd[]' value='0' id='directUse_endid_" + index + "'></td>" +
											 	    	"<td>" +
								         		        	"<div class='form-group mb-0'>" +
								         		                "<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
								         		             "</div>" +
								         	           "</td>" +
								         	           "<td>" +
								         		       		"<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
								         	           "</td>" +
								                   "</tr>";	
										}
									}else if(status=="Filled"){
										if(isMeter==true){
											html +="<tr>" +
											 	   			"<input type='hidden' name='whereToUse1[]' id='whereToUse1_" + index + "' value='"+whereToUse+"'>"+
											 	   		"<td><input type='hidden' class='form-control' name='directUseName1[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
											            "<td>" +
												        	"<div class='form-group mb-0'>" +
													        	"<input type='text' name='directUseStart1[]' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
													         "</div>" +
													    "</td>" +
													    "<td>" +
												            "<div class='form-group mb-0'>" +
												            	"<input type='text' name='directUseEnd1[]' class='form-control' value='"+ endReading +"' disabled/> <i class='form-group__bar'></i>" +
												             "</div>" +
												       "</td>" +
												       "<td>" +
												       		"<div class='form-group mb-0'>" +
												       			"<input type='text' name='avgfilterUseStart1[]' class='form-control'  value='"+ actualReading +"' disabled/> <i class='form-group__bar'></i>" +
												            "</div>" +
												      "</td>" +
											          "<td>" +
											          	"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
											          "</td>" +
											    "</tr>";
										}else{
											html +="<tr>" +
											 				"<input type='hidden' name='whereToUse1[]' id='whereToUse1_" + index + "' value='"+whereToUse+"'>"+
											 			"<td><input type='hidden' class='form-control' name='directUseName1[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
														"<td>-</td>" +
														"<td>-</td>" +
														"<td>" +
															 "<div class='form-group mb-0'>" +
															 	"<input type='text' name='avgDirectUseStart1[]' class='form-control' value='"+actualReading+"' disabled/> <i class='form-group__bar'></i>" +
															 "</div>" +
													    "</td>" +
													    "<td>" +
													    	"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
													    "</td>" +
												  "</tr>";
										}
									}else if(status=="New"){
										if(isMeter==true){
											html +="<tr>" +
												   			"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
												   		"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
											            "<td>" +
											           		"<div class='form-group mb-0'>" +
											           			"<input type='text' class='form-control' placeholder='Start Reading' name='directUseStart[]' id='directUse_startid_" + index + "' class='form-control' value='0'/> <i class='form-group__bar'></i>" +
											           		"</div>" +
											           	"</td>" +
											           	"<td>" +
											           		"<div class='form-group mb-0'>" +
											           			"<input type='text'  class='form-control' placeholder='End Reading' name='directUseEnd[]' id='directUse_endid_" + index + "' onkeypress='numberValidate(event,\"directUse_Error_" + index + "\")' onchange='setConsumption(\"directUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
											           		"</div>" +
											           "</td>" +
											           "<td>" +
											           		"<div class='form-group mb-0'>" +
											           			"<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
											           		"</div>" +
											           "</td>" +
											           "<td>" +
											           	    "<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
											           "</td>" +
											       "</tr>";
										}else{
											html +="<tr>" +
											 				"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
								            			"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
								        				"<td>-<input type='hidden' class='form-control' name='directUseStart[]' value='0' id='directUse_startid_" + index + "'></td>" +
								        				"<td>-<input type='hidden' class='form-control' name='directUseEnd[]' value='0' id='directUse_endid_" + index + "'></td>" +
								        				"<td>" +
								        					 "<div class='form-group mb-0'>" +
								        					 	"<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
								        					 "</div>" +
								        				"</td>" +
								        				"<td>" +
								        					 "<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
								        				"</td>" +
								        		  "</tr>";
										}
									}
								});
								if(input==true){
									html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='directUseOfWaterSavebtn' onclick='saveAllDirectUseOfWaterData(this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
									        	"</div> </div> </div>";
								}else{
									 html2 = "</tbody> </table> <div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> Submitted </button> </center> </div>" +
												"</div> </div> </div>";
								}
								var finalHtml=html+html2;
								 $("#waterMainAccordion").append(finalHtml);
								 
							}else if(uiOption=="Advanced"){
								var directUseAccordionHtml = "<h2><a href='#'>DirectUseOfWater</a></h2>" +
																"<div id='directUseAccordion'>"+
																"</div>";
						$("#waterMainAccordion").append(directUseAccordionHtml);
						
						$.each(directUseOfWaterData, function (index, element){
							var typeOfUse=element.typeOfUse;
							var whereToUse=element.whereToUse;
							var isMeter=element.isMeter;
							var startReading=element.startReading;
							var endReading=element.endReading;
							var actualReading=element.actualReading;
							var status=element.status;
							
							var directUseNameHtml="<h2><a href='#'>"+whereToUse+" ("+typeOfUse+")"+"</a></h2>"+
					   	  							"<div>" +
					   	  								"<div class='card-body'>" +
					   	  									"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
					   	  										"<table class='table table-bordered' id='dailyip-table'>" +
					   	  											"<thead> <tr> <th> DirectUse Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead> <tbody>";
						
							if(status=="Fill"){
								if(isMeter==true){
									directUseNameHtml +="<tr>" +
									 							"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
									 						"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
									 						"<td>" +
									 							"<div class='form-group mb-0'>" +
									 								"<input type='text' name='directUseStart[]' id='directUse_startid_" + index + "' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
									 							"</div>" +
									 						"</td>" +
									 						"<td>" +
									 							"<div class='form-group mb-0'>" +
									 								"<input type='text' class='form-control' placeholder='End Reading' name='directUseEnd[]' id='directUse_endid_" + index + "' onkeypress='numberValidate(event,\"directUse_Error_" + index + "\")' onchange='setConsumption(\"directUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
									 							"</div>" +
									 						"</td>" +
									 						"<td>" +
									 							"<div class='form-group mb-0'>" +
									 								"<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
									 							"</div>" +
									 						"</td>" +
									 					"<td>" +
									 						"<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
									 					"</td>" +
									 				"</tr>";
								}else{
									directUseNameHtml +="<tr>" +
									 	   						"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
									 	   					"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
									 	   					"<td>-<input type='hidden' class='form-control' name='directUseStart[]' value='0' id='directUse_startid_" + index + "'></td>" +
									 	   					"<td>-<input type='hidden' class='form-control' name='directUseEnd[]' value='0' id='directUse_endid_" + index + "'></td>" +
									 	   					"<td>" +
									 	   						"<div class='form-group mb-0'>" +
									 	   							"<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
									 	   						"</div>" +
									 	   					"</td>" +
									 	   					"<td>" +
									 	   						"<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
									 	   					"</td>" +
									 	   				"</tr>";	
								}
							}else if(status=="Filled"){
								if(isMeter==true){
									directUseNameHtml +="<tr>" +
									 	   						"<input type='hidden' name='whereToUse1[]' id='whereToUse1_" + index + "' value='"+whereToUse+"'>"+
									 	   					"<td><input type='hidden' class='form-control' name='directUseName1[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
									 	   					"<td>" +
									 	   						"<div class='form-group mb-0'>" +
									 	   							"<input type='text' name='directUseStart1[]' class='form-control' value='" + startReading + "' disabled/> <i class='form-group__bar'></i>" +
									 	   						"</div>" +
									 	   					"</td>" +
									 	   					"<td>" +
									 	   						"<div class='form-group mb-0'>" +
									 	   							"<input type='text' name='directUseEnd1[]' class='form-control' value='"+ endReading +"' disabled/> <i class='form-group__bar'></i>" +
									 	   						"</div>" +
									 	   					"</td>" +
									 	   					"<td>" +
									 	   						"<div class='form-group mb-0'>" +
									 	   							"<input type='text' name='avgfilterUseStart1[]' class='form-control'  value='"+ actualReading +"' disabled/> <i class='form-group__bar'></i>" +
									 	   						"</div>" +
									 	   					"</td>" +
									 	   					"<td>" +
									 	   						"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
									 	   					"</td>" +
									 	   				"</tr>";
								}else{
									directUseNameHtml +="<tr>" +
									 							"<input type='hidden' name='whereToUse1[]' id='whereToUse1_" + index + "' value='"+whereToUse+"'>"+
									 						"<td><input type='hidden' class='form-control' name='directUseName1[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
									 						"<td>-</td>" +
									 						"<td>-</td>" +
									 						"<td>" +
									 							"<div class='form-group mb-0'>" +
									 								"<input type='text' name='avgDirectUseStart1[]' class='form-control' value='"+actualReading+"' disabled/> <i class='form-group__bar'></i>" +
									 							"</div>" +
									 						"</td>" +
									 						"<td>" +
									 							"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
									 						"</td>" +
									 					"</tr>";
								}
							}else if(status=="New"){
								if(isMeter==true){
									directUseNameHtml +="<tr>" +
										   						"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
										   					"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
										   					"<td>" +
										   						"<div class='form-group mb-0'>" +
										   							"<input type='text' class='form-control' placeholder='Start Reading' name='directUseStart[]' id='directUse_startid_" + index + "' class='form-control' value='0'/> <i class='form-group__bar'></i>" +
										   						"</div>" +
										   					"</td>" +
										   					"<td>" +
										   						"<div class='form-group mb-0'>" +
										   							"<input type='text'  class='form-control' placeholder='End Reading' name='directUseEnd[]' id='directUse_endid_" + index + "' onkeypress='numberValidate(event,\"directUse_Error_" + index + "\")' onchange='setConsumption(\"directUse\",\"" + index + "\")'/> <i class='form-group__bar'></i>" +
										   						"</div>" +
										   					"</td>" +
										   					"<td>" +
										   						"<div class='form-group mb-0'>" +
										   							"<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' disabled/> <i class='form-group__bar'></i>" +
										   						"</div>" +
										   					"</td>" +
										   					"<td>" +
										   						"<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
										   					"</td>" +
										   				"</tr>";
								}else{
									directUseNameHtml +="<tr>" +
									 							"<input type='hidden' name='whereToUse[]' id='whereToUse_" + index + "' value='"+whereToUse+"'>"+
									 						"<td><input type='hidden' class='form-control' name='directUseName[]' value='" + whereToUse+ "' +' (' +'"+typeOfUse+"'+ ')' >"+whereToUse+" ("+typeOfUse+")"+"</td>" +
									 						"<td>-<input type='hidden' class='form-control' name='directUseStart[]' value='0' id='directUse_startid_" + index + "'></td>" +
									 						"<td>-<input type='hidden' class='form-control' name='directUseEnd[]' value='0' id='directUse_endid_" + index + "'></td>" +
									 						"<td>" +
									 							"<div class='form-group mb-0'>" +
									 								"<input type='text' placeholder='Act Consumption' name='avgDirectUseStart[]' class='form-control' id='directUse_avgid_" + index + "' /> <i class='form-group__bar'></i>" +
									 							"</div>" +
									 						"</td>" +
									 						"<td>" +
									 							"<div class='form-group'> <div id='directUse_Error_" + index + "'></div> </div>" +
									 						"</td>" +
									 					"</tr>";
								}
							}
							directUseNameHtml +="</tbody> </table>";
							if(status=="Fill"||status=="New"){
								directUseNameHtml+="<div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' id='directUseOfWaterSavebtn' onclick='saveDirectUseOfWater(\"" +index+ "\", this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
		   												"</div> </div>";
							}else{
								directUseNameHtml+="<div class='container'> <div class='row'> <div class='col-12'> <center> <button class='btn btn-primary btn--icon-text' disabled><i class='zmdi zmdi-save'></i> Submitted</button> </center> </div>" +
	   													"</div> </div>";
							}
							directUseNameHtml +="</div></div>";
                        	  $("#directUseAccordion").append(directUseNameHtml);
						});
						 $("#directUseAccordion").accordion({
	    		  				heightStyle: 'content',
	    		  				collapsible: true
	    		  			});
							}//advanced
						}
					}
				}
			}
		},
		async: false
	});
}

function saveAllDirectUseOfWaterData(e1){
	var directUseArray=new Array();
    var flag=0;
	var inps1 = document.getElementsByName("whereToUse[]");
	var inps2 = document.getElementsByName("directUseStart[]");
	var inps3 = document.getElementsByName("directUseEnd[]");
	var inps4 = document.getElementsByName("avgDirectUseStart[]");
	for (var i = 0; i < inps1.length; i++) {
		var directUseObj={};
		if (inps2[i].value == "" || inps3[i].value == "" || inps4[i].value == "") {
			$("#directUse_Error_" + i).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		}else{
			$("#directUse_Error_" + i).html("");
			directUseObj["whereToUse"]=inps1[i].value;
			directUseObj["directUseStart"]=inps2[i].value;
			directUseObj["directUseEnd"]=inps3[i].value;
			directUseObj["directUseAvg"]=inps4[i].value;
			directUseArray.push(directUseObj);
	}
	}
	sendToDirectUseOfWaterSaveApi(directUseArray, flag, e1);
}

function saveDirectUseOfWater(index, e1){
	var directUseArray=new Array();
	var myObj={};
    var flag=0;
	var whereToUse = $("#whereToUse_"+index);
	var directUseStartId  = $("#directUse_startid_"+index);
	var directUseEndId  = $("#directUse_endid_"+index);
	var directUseAvgId = $("#directUse_avgid_"+index);
	var directUseObj={};
	if(directUseStartId.val()==""||directUseEndId.val()==""||directUseAvgId.val()==""){
		$("#directUse_Error_" + index).html("<label class='text-red'>Enter Quantity</label>");
		flag++;
	}else{
		$("#directUse_Error_" + index).html("");
		directUseObj["whereToUse"]=whereToUse.val();
		directUseObj["directUseStart"]=directUseStartId.val();
		directUseObj["directUseEnd"]=directUseEndId.val();
		directUseObj["directUseAvg"]=directUseAvgId.val();
		directUseArray.push(directUseObj);
	}
	sendToDirectUseOfWaterSaveApi(directUseArray, flag, e1);
}

function sendToDirectUseOfWaterSaveApi(directUseArray, flag, e1){
	var myObj={};
	myObj["regDirectUseOfWaterData"]=directUseArray;
	if(flag<=0){
	$(document).ready(function () {
		$.ajax({
			type: "POST",
			url: "ajax-save-regular-direct-use-of-water",
			contentType : "application/json",
			data : JSON.stringify(myObj),
			success: function (res) {
				if (res == "success") {
					$(e1).prop('disabled', true);
					findNextAccordion(e1);
					jBoxBottomRightBigNotice("Success", "Direct Use Of Water Data Saved !!", "green", "2000");
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			},
			error: function (e) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
				window.setTimeout(window.location = "logout", 7000);
			}
		});
	});
	}
}

/*function saveRegularUseOfSourceData(el) {

	var main = new Array();
	var sourceId = 0,
		flag = 0;
	var wi_id = $("#wi_id").val();

	//SOURCE
	var source_use = new Array();
	var source_use_start = new Array();
	var source_use_end = new Array();
	var source_use_avg = new Array();

	var useName = document.getElementsByName("source_use_name[]");
	var useStart = document.getElementsByName("use_start[]");
	var useEnd = document.getElementsByName("use_end[]");
	var useAvg = document.getElementsByName("avg_use_start[]");
	//sourceId = storeValues("error_use_"+wi_id+"_", inps, inps1, inps2, inps3, source_use, source_use_start, source_use_end, source_use_avg, flag, sourceId);

	for (var i = 0; i < useName.length; i++) {
		var iddd = useAvg[i].id;

		var res = iddd.split("_");
		iddd = res[2];
		//var apcEndVal = $("#apcEnd_" + iddd).val();
		var a = useAvg[i].value;
		if (a == "") {
			$("#use_Error_" + iddd).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		} else {
			source_use.push((useName[i].value).trim());
			source_use_start.push(useStart[i].value);
			source_use_end.push(useEnd[i].value);
			source_use_avg.push(useAvg[i].value);

			$("#use_Error_" + iddd).html("");
		}
	}

	if (flag <= 0) {
		$(document).ready(function () {
			$.ajax({
				type: "POST",
				url: "ajax-save-regular-use-of-source-data",
				data: {
					action: 'SaveRegularUseOfSourceData',
					wi_id: wi_id,
					source_use: source_use.toString(),
					source_use_start: source_use_start.toString(),
					source_use_end: source_use_end.toString(),
					source_use_avg: source_use_avg.toString(),
				},
				success: function (data) {
					if (data == 1) {
						$("#regularWaterUseSavebtn").attr("disabled", true);
						findNextAccordion(el);
						jBoxBottomRightBigNotice("Success", " Use of Source Data Saved !!", "green", "2000");
					} else {
						jBoxBottomRightBigNotice("Error", " Not Saved !!", "red", "2000");
					}
				},
				error: function (e) {
					jBoxBottomRightBigNotice("error", "Oopss !! session expired /n", "red", "2000");
					window.setTimeout(window.location = "logout", 7000)
				}
			});
		});
	}

}*/
function getTreatmentWaterDailyInputData() {
	$.ajax({
		type: "POST",
		url: "ajax-getDailyTreatmentWaterData",
		success: function (data) {
			var data = JSON.parse(data);
			var result=data[0].Result;
			if(result==true){
				var dataList=data[0].Data;
				if(dataList!=null){
				var treatmentList = dataList.treatmentData;
					var wwtpaccordionHtml = "<h2><a href='#'> Waste Water treatment Plant </a></h2>" +
											"<div id='wwtpAccordion'>"+
											"</div>";
					$("#waterMainAccordion").append(wwtpaccordionHtml); 
					$.each(treatmentList, function (index, element){
						var treatmentType=element.treatmentType;
						var label=element.label;
						var treatmentStatus=element.status;
						var treatmentId=element.wastewaterTreatmentId;
						var treatmentStartReading=element.startReading;
						var treatmentEndReading=element.endReading;
						var treatmentActualReading=element.actualReading;
						var energyStartReading=element.energystartReading;
						var energyEndReading=element.energyendReading;
						var energyActualReading=element.energyActualReading;
						var recycleStatus = "";
					    var singleAccordion ="<h2><a href='#'>"+ label +" </a></h2>"+
						 "<div>"+
							 "<div class='card-body'>"+
							 "<input type='hidden' class='form-control'  id='treatmentwater_id_"+label +"' value='"+treatmentId+"'>"+
							 "<table class='table table-bordered' id='dailyip-table'>" +
								"<thead> <tr> <th> Treatment Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Message/Warnings/Errors</th> </tr> </thead>";
								
								if (treatmentStatus == "Fill") {
								singleAccordion += "<tr>" +
									                "<td>" +
									                	"<label class='col-form-label'>" + label + " (Water Reading)</label>" +
									                "</td>" +
									                "<td>" +
										                "<div class='form-group mb-0'>" +
										                	"<input type='text' class='form-control' placeholder='Inlet' onchange='setTreatmentNewReadingConsumption(\"waterReading\",\"" + label + "\")' id='treatmentwater_waterReading_start_" + label + "' value='" + treatmentEndReading + "' disabled /> <i class='form-group__bar'></i>" +
										                "</div>" +
									                "</td>" +
									                "<td>" +
										                "<div class='form-group mb-0'>" +
										                	"<input type='text' class='form-control' placeholder='Outlet' onchange='setTreatmentNewReadingConsumption(\"waterReading\",\"" + label + "\")' id='treatmentwater_waterReading_end_" + label + "' /> <i class='form-group__bar'></i>" +
										                "</div>" +
									                "</td>" +
									                "<td>" +
										                "<div class='form-group mb-0'>" +
										                	"<input type='text' placeholder='Act Consumption'  id='treatmentwater_waterReading_avg_" + label + "'  class='form-control' disabled/> <i class='form-group__bar'></i>" +
										                "</div>" +
									                "</td>" +
									                "<td style='width: 184px;'>" +
									                	"<div class='form-group'> <div id='treatmentwater_waterReading_error_" + label + "'></div> </div>" +
									                "</td>" +
								                "</tr>" +
								                
								                "<tr>" +
									                "<td>" +
										                "<input type='hidden' value='" + label + "'>" +
										                "<label class='col-form-label'>" + label + " (Energy Reading)</label>" +
									                "</td>" +
									                "<td>" +
										                "<div class='form-group mb-0'>" +
										                "<input type='text' class='form-control' placeholder='Start Reading' name='energyStart[]' onchange='setTreatmentNewReadingConsumption(\"energyReading\",\"" + label + "\")' id='treatmentwater_energyReading_start_" + label + "' value='" + energyEndReading + "' disabled/> <i class='form-group__bar'></i>" +
										                "</div>" +
									                "</td>" +
									                "<td>" +
										                "<div class='form-group mb-0'>" +
										                	"<input type='text' class='form-control' placeholder='End Reading' name='energyEnd[]'  onkeypress='numberValidate(event,\"treatmentenergy_Error_" + index + "\")' onchange='setTreatmentNewReadingConsumption(\"energyReading\",\"" + label + "\")' id='treatmentwater_energyReading_end_" + label + "'/> <i class='form-group__bar'></i>" +
										                "</div>" +
									                "</td>" +
									                "<td>" +
										                "<div class='form-group mb-0'>" +
										                	"<input type='text' placeholder='Act Consumption' name='avgTreatmentStart[]' id='treatmentwater_energyReading_avg_" + label + "'  class='form-control' disabled/> <i class='form-group__bar'></i>" +
										                "</div>" +
									                "</td>" +
									                "<td style='width: 184px;'>" +
									                	"<div class='form-group'> <div id='treatmentwater_energyReading_error_" + label + "'></div> </div>" +
									                "</td>" +
								                "</tr>";
							        } else if (treatmentStatus == "Filled") {
							        	singleAccordion += "<tr>" +
											                "<td>" +
											                	"<label class='col-form-label'>" + label + " (Water Reading)</label>" +
											                "</td>" +
											                "<td>" +
												                "<div class='form-group mb-0'>" +
												                	"<input type='text' class='form-control' placeholder='Inlet' name='treatment_start[]'  value='" + treatmentStartReading + "' disabled /> <i class='form-group__bar'></i>" +
												                "</div>" +
											                "</td>" +
											                "<td>" +
												                "<div class='form-group mb-0'>" +
												                	"<input type='text' class='form-control' placeholder='Outlet' name='treatment_end[]' value='" + treatmentEndReading + "' disabled /> <i class='form-group__bar'></i>" +
												                "</div>" +
											                "</td>" +
											                "<td>" +
												                "<div class='form-group mb-0'>" +
												                	"<input type='text' placeholder='Act Consumption' name='avg_treatment_start[]'  value='" + treatmentActualReading + "' disabled class='form-control' disabled/> <i class='form-group__bar'></i>" +
												                "</div>" +
											                "</td>" +
											                "<td>" +
											                	"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
											                "</td>" +
										                	"</tr>" +
										                "<tr>" +
										                "<td>" +
											                "<input type='hidden' name='treatmentLabel[]' value='" + label + "'>" +
											                "<label class='col-form-label'>" + label + " (Energy Reading)</label>" +
										                "</td>" +
										                "<td>" +
											                "<div class='form-group mb-0'>" +
											                	"<input type='text' class='form-control' placeholder='Start Reading' name='energyStart[]' value='" + energyStartReading + "' disabled  /> <i class='form-group__bar'></i>" +
											                "</div>" +
										                "</td>" +
										                "<td>" +
											                "<div class='form-group mb-0'>" +
											                	"<input type='text' class='form-control' placeholder='End Reading' name='energyEnd[]' value='" + energyEndReading + "' disabled  /> <i class='form-group__bar'></i>" +
											                "</div>" +
										                "</td>" +
										                "<td>" +
											                "<div class='form-group mb-0'>" +
											                	"<input type='text' placeholder='Act Consumption' name='avgTreatmentStart[]' value='" + energyActualReading + "' disabled  class='form-control' disabled/> <i class='form-group__bar'></i>" +
											                "</div>" +
										                "</td>" +
										                "<td>" +
										                	"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
										                "</td>" +
								                	"</tr>";
							        	} else if (treatmentStatus == "New") {
							        		singleAccordion += "<tr>" +
												                "<td>" +
												                	"<label class='col-form-label'>" + label + " (Water Reading)</label>" +
												                "</td>" +
												                "<td>" +
													                "<div class='form-group mb-0'>" +
													                	"<input type='text' class='form-control' placeholder='Inlet' name='treatment_start[]' onchange='setTreatmentNewReadingConsumption(\"waterReading\",\"" + label + "\")' id='treatmentwater_waterReading_start_" + label + "' /> <i class='form-group__bar'></i>" +
													                "</div>" +
												                "</td>" +
												                "<td>" +
													                "<div class='form-group mb-0'>" +
													                	"<input type='text' class='form-control' placeholder='Outlet' name='treatment_end[]' onchange='setTreatmentNewReadingConsumption(\"waterReading\",\"" + label + "\")' id='treatmentwater_waterReading_end_" + label + "' /> <i class='form-group__bar'></i>" +
													                "</div>" +
												                "</td>" +
												                "<td>" +
													                "<div class='form-group mb-0'>" +
													                	"<input type='text' placeholder='Act Consumption' name='avg_treatment_start[]' id='treatmentwater_waterReading_avg_" + label + "'  class='form-control' disabled/> <i class='form-group__bar'></i>" +
													                "</div>" +
												                "</td>" +
												                "<td style='width: 184px;'>" +
												                	"<div class='form-group'> <div id='treatmentwater_waterReading_error_" + label + "'></div> </div>" +
												                "</td>" +
										                	"</tr>" +
											                "<tr>" +
												                "<td>" +
													                "<input type='hidden' name='treatmentLabel[]' value='" + label + "'>" +
													                "<label class='col-form-label'>" + label + " (Energy Reading)</label>" +
												                "</td>" +
												                "<td>" +
													                "<div class='form-group mb-0'>" +
													                "<input type='text' class='form-control' placeholder='Start Reading' name='energyStart[]' onchange='setTreatmentNewReadingConsumption(\"energyReading\",\"" + label + "\")' id='treatmentwater_energyReading_start_" + label + "' /> <i class='form-group__bar'></i>" +
													                "</div>" +
												                "</td>" +
												                "<td>" +
													                "<div class='form-group mb-0'>" +
													                	"<input type='text' class='form-control' placeholder='End Reading' name='energyEnd[]' onchange='setTreatmentNewReadingConsumption(\"energyReading\",\"" + label + "\")' id='treatmentwater_energyReading_end_" + label + "' /> <i class='form-group__bar'></i>" +
													                "</div>" +
												                "</td>" +
												                "<td>" +
													                "<div class='form-group mb-0'>" +
													                	"<input type='text' placeholder='Act Consumption' name='avgTreatmentStart[]' id='treatmentwater_energyReading_avg_" + label + "'  class='form-control' disabled/> <i class='form-group__bar'></i>" +
													                "</div>" +
												                "</td>" +
												                "<td style='width: 184px;'>" +
												                	"<div class='form-group'> <div id='treatmentwater_energyReading_error_" + label + "'></div> </div>" +
												                "</td>" +
										                	"</tr>";
							        }
								
								var recycleData = element.recycleList;
								if (recycleData!=null) {
									$.each(recycleData, function(index1, element1) {
										var wrid = element1.wastewaterRecycleId;
										var usedType = element1.usedType;
										var recycledTo = element1.recycleTo;
										var quantity = element1.quantity;
										var recycleStartReading = element1.recycleStartReading;
										var recycleEndReading = element1.recycleEndReading;
										var recycleActualReading = element1.recycleActualReading;
										recycleStatus = element1.recycleStatus;
										var recycleMeter = element1.isMeter;
										if (recycleStatus == "Fill") {
											if (recycleMeter == true) {
											singleAccordion += "<tr>" +
																"<td>" +
																	"<input type='hidden' name='treatmentwaterRecycle_id_" + label + "_[]' value='" + wrid + "'>" +
																	"<label class='col-form-label'>" + usedType + ": " + recycledTo + "</label>" +
																"</td>" +
																"<td>" +
																	"<div class='form-group mb-0'>" +
																		"<input type='text' class='form-control' name='treatmentwaterRecycle_start_" + label + "_[]' id='treatmentwater_recycleReading_start_" + wrid + "_" + label + "' name='recycleStart[]' value='" + recycleEndReading + "' disabled  /> <i class='form-group__bar'></i>" +
																	"</div>" +
																"</td>" +
																"<td>" +
																	"<div class='form-group mb-0'>" +
																		"<input type='text' class='form-control'name='treatmentwaterRecycle_end_" + label + "_[]' id='treatmentwater_recycleReading_end_" + wrid + "_" + label + "'   name='recycleEnd[]' placeholder='End Reading' onchange='setTreatmentNewReadingConsumption(\"recycleReading\",\"" + wrid + "_" + label + "\")'/> <i class='form-group__bar'></i>" +
																	"</div>" +
																"</td>" +
																"<td>" +
																	"<div class='form-group mb-0'>" +
																		"<input type='text' placeholder='Act Consumption' name='treatmentwaterRecycle_avg_" + label + "_[]' id='treatmentwater_recycleReading_avg_" + wrid + "_" + label + "' name='avgRecycleStart[]'  class='form-control' disabled/> <i class='form-group__bar'></i>" +
																	"</div>" +
																"</td>" +
																"<td style='width: 184px;'>" +
																	"<div class='form-group'> <div id='treatmentwater_recycleReading_error_" + wrid + "_" + label + "'></div> </div>" +
																"</td>" +
															"</tr>";
											} else {
											singleAccordion += "<tr>" +
																"<td>" +
																	"<input type='hidden' name='treatmentwaterRecycle_id_" + label + "_[]' value='" + wrid + "'>" +
																	"<label class='col-form-label'>" + usedType + ": " + recycledTo + "</label>" +
																"</td>" +
																"<td>-<input type='hidden' class='form-control' id='treatmentwater_recycleReading_start_" + wrid + "_" + label + "' name='treatmentwaterRecycle_start_" + label + "_[]' value='0'></td>" +
																"<td>-<input type='hidden' class='form-control' id='treatmentwater_recycleReading_end_" + wrid + "_" + label + "' name='treatmentwaterRecycle_end_" + label + "_[]' value='0'></td>" +
																"<td>" +
																	"<div class='form-group mb-0'>" +
																	"<input type='text' placeholder='Act Consumption' id='treatmentwater_recycleReading_avg_" + wrid + "_" + label + "' name='treatmentwaterRecycle_avg_" + label + "_[]'  class='form-control'/> <i class='form-group__bar'></i>" +
																	"</div>" +
																"</td>" +
																"<td style='width: 184px;'>" +
																	"<div class='form-group'> <div id='treatmentwater_recycleReading_error_" + wrid + "_" + label + "'></div> </div>" +
																"</td>" +
															"</tr>";
											}
										} else if (recycleStatus == "Filled") {
											if (recycleMeter == true) {
												singleAccordion += "<tr>" +
																	"<input type='hidden' name='wrid[]' value='" + wrid + "'>" +
																	"<td><input type='hidden' name='usedType[]' value='" + usedType + "'>" +
																		"<input type='hidden' name='recycledTo[]' value='" + recycledTo + "'>" +
																		"<label class='col-form-label'>" + usedType + ": " + recycledTo + "</label>" +
																	"</td>" +
																	"<td>" +
																		"<div class='form-group mb-0'>" +
																			"<input type='text' class='form-control'  name='recycleStart1[]' value='" + recycleStartReading + "' disabled  /> <i class='form-group__bar'></i>" +
																		"</div>" +
																	"</td>" +
																	"<td>" +
																		"<div class='form-group mb-0'>" +
																			"<input type='text' class='form-control'  name='recycleEnd1[]' value='" + recycleEndReading + "' disabled  /> <i class='form-group__bar'></i>" +
																		"</div>" +
																	"</td>" +
																	"<td>" +
																		"<div class='form-group mb-0'>" +
																			"<input type='text' placeholder='Act Consumption' name='avgRecycleStart1[]' value='" + recycleActualReading + "' disabled  class='form-control' disabled/> <i class='form-group__bar'></i>" +
																		"</div>" +
																	"</td>" +
																	"<td>" +
																		"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
																	"</td>" +
																"</tr>";

											}else {
												singleAccordion += "<tr>" +
																	"<input type='hidden' name='wrid[]' value='" + wrid + "'>" +
																		"<td><input type='hidden' name='usedType[]' value='" + usedType + "'>" +
																		"<input type='hidden' name='recycledTo[]' value='" + recycledTo + "'>" +
																		"<label class='col-form-label'>" + usedType + ": " + recycledTo + "</label>" +
																		"</td>" +
																		"<td>-<input type='hidden' class='form-control' name='recycleStart1[]'  value='0'></td>" +
																		"<td>-<input type='hidden' class='form-control' name='recycleEnd1[]' value='0'></td>" +
																		"<td>" +
																			"<div class='form-group mb-0'>" +
																			"<input type='text' placeholder='Act Consumption' name='avgRecycleStart[]' value='" + recycleActualReading + "' disabled  class='form-control' disabled/> <i class='form-group__bar'></i>" +
																			"</div>" +
																		"</td>" +
																		"<td>" +
																			"<div class='form-group'> <div><label class='text-cyan'> <i class='zmdi zmdi-check-circle zmdi-hc-fw'></i>Data Filled!</label></div> </div>" +
																		"</td>" +
																"</tr>";
										}
									} else if (recycleStatus == "New") {
										if (recycleMeter == true) {
											singleAccordion += "<tr>" +
																	"<td>" +
																		"<input type='hidden' name='treatmentwaterRecycle_id_" + label + "_[]' value='" + wrid + "'>" +
																		"<label class='col-form-label'>" + usedType + ": " + recycledTo + "</label>" +
																	"</td>" +
																	"<td>" +
																		"<div class='form-group mb-0'>" +
																		"<input type='text' class='form-control' id='treatmentwater_recycleReading_start_" + wrid + "_" + label + "' name='treatmentwaterRecycle_start_" + label + "_[]' /> <i class='form-group__bar'></i>" +
																		"</div>" +
																	"</td>" +
																	"<td>" +
																		"<div class='form-group mb-0'>" +
																			"<input type='text' class='form-control' id='treatmentwater_recycleReading_end_" + wrid + "_" + label + "' name='treatmentwaterRecycle_end_" + label + "_[]' onchange='setTreatmentNewReadingConsumption(\"recycleReading\",\"" + wrid + "_" + label + "\")'  /> <i class='form-group__bar'></i>" +
																		"</div>" +
																	"</td>" +
																	"<td>" +
																		"<div class='form-group mb-0'>" +
																			"<input type='text' placeholder='Act Consumption' id='treatmentwater_recycleReading_avg_" + wrid + "_" + label + "' name='treatmentwaterRecycle_avg_" + label + "_[]'  class='form-control'/> <i class='form-group__bar'></i>" +
																		"</div>" +
																	"</td>" +
																	"<td style='width: 184px;'>" +
																		"<div class='form-group'> <div id='treatmentwater_recycleReading_error_" + wrid + "_" + label + "'></div> </div>" +
																	"</td>" +
																"</tr>";
										} else {
											singleAccordion += "<tr>" +
																	"<td>" +
																		"<input type='hidden' name='treatmentwaterRecycle_id_" + label + "_[]' value='" + wrid + "'>" +
																		"<label class='col-form-label'>" + usedType + ": " + recycledTo + "</label>" +
																	"</td>" +
																	"<td>-<input type='hidden' class='form-control' id='treatmentwater_recycleReading_start_" + wrid + "_" + label + "' name='treatmentwaterRecycle_start_" + label + "_[]' value='0'></td>" +
																	"<td>-<input type='hidden' class='form-control' id='treatmentwater_recycleReading_end_" + wrid + "_" + label + "' name='treatmentwaterRecycle_end_" + label + "_[]' value='0'></td>" +
																	"<td>" +
																		"<div class='form-group mb-0'>" +
																		"<input type='text' placeholder='Act Consumption' id='treatmentwater_recycleReading_avg_" + wrid + "_" + label + "' name='treatmentwaterRecycle_avg_" + label + "_[]'  class='form-control'/> <i class='form-group__bar'></i>" +
																		"</div>" +
																	"</td>" +
																	"<td style='width: 184px;'>" +
																		"<div class='form-group'> <div id='treatmentwater_recycleReading_error_" + wrid + "_" + label + "'></div> </div>" +
																	"</td>" +
																"</tr>";
										       }
									                        }
									});
					}
						singleAccordion +="</tbody>"+
								"<table>";
								if(treatmentStatus!="Filled"|| recycleStatus!="Filled"){
									singleAccordion +="<div class='container'> <div class='row'> <div class='col-12'><center> <button class='btn btn-primary btn--icon-text' id='regularTreatmentSavebtn"+label+"' onclick='saveRegularTreatmentData(\"" +label+ "\", this)'><i class='zmdi zmdi-save'></i> Submit</button> </center> </div>" +
					                "</div></div>";
								}else{
									singleAccordion +="<div class='container'> <div class='row'> <div class='col-12'><center> <button class='btn btn-primary btn--icon-text' id='regularTreatmentSavebtn"+label+"' disabled><i class='zmdi zmdi-save'></i> Submitted</button> </center> </div>" +
									"</div></div>";	
								}
						singleAccordion+="</div>"+
						"</div>";
					
					$("#wwtpAccordion").append(singleAccordion); 
				});
					$("#wwtpAccordion").accordion({
		  				heightStyle: 'content',
		  				collapsible: true
		  			});
				}
			}
		},
		async: false
	});
}
function saveRegularTreatmentData(label, e3) {
	var myObj={};
	var flag=0;
	var treatmentObj={};
	var treatmentSourceId= $("#treatmentwater_id_"+label);
	var treatmentSourceStartId  = $("#treatmentwater_waterReading_start_"+label);
	var treatmentSourceEndId  = $("#treatmentwater_waterReading_end_"+label);
	var treatmentSourceAvgId  = $("#treatmentwater_waterReading_avg_"+label);
	var treatmentEnergyStartId = $("#treatmentwater_energyReading_start_"+label);
	var treatmentEnergyEndId = $("#treatmentwater_energyReading_end_"+label);
	var treatmentEnergyAvgId = $("#treatmentwater_energyReading_avg_"+label);
	
	//Treatment Water Reading Validation
	if(treatmentSourceStartId.val()==""|| treatmentSourceEndId.val()==""|| treatmentSourceAvgId.val()==""){
		$("#treatmentwater_waterReading_error"+"_"+ label).html("<label class='text-red'>Enter Quantity</label>");
	flag++;
	}else{
	treatmentObj["treatmentId"]=treatmentSourceId.val();
	treatmentObj["treatmentSourceStart"]=treatmentSourceStartId.val();
	treatmentObj["treatmentSourceEnd"]=treatmentSourceEndId.val();
	treatmentObj["treatmentSourceAvg"]=treatmentSourceAvgId.val();
	}
	
	//Treatment Energy Reading Validation
	if(treatmentEnergyStartId.val()==""|| treatmentEnergyEndId.val()==""|| treatmentEnergyAvgId.val()==""){
		$("#treatmentwater_energyReading_error"+"_"+ label).html("<label class='text-red'>Enter Quantity</label>");
	flag++;
	}else{
	treatmentObj["treatmentEnergyStart"]=treatmentEnergyStartId.val();
	treatmentObj["treatmentEnergyEnd"]= treatmentEnergyEndId.val();
	treatmentObj["treatmentEnergyAvg"]=treatmentEnergyAvgId.val();
	}
	
	var recycleDataArray = new Array();
	var recycelNamesIds=document.getElementsByName('treatmentwaterRecycle_id_'+label +'_[]');
	var recycelStartReadingIds=document.getElementsByName('treatmentwaterRecycle_start_'+label +'_[]');
	var recycelEndReadingIds=document.getElementsByName('treatmentwaterRecycle_end_'+label +'_[]');
	var recycelActReadingIds=document.getElementsByName('treatmentwaterRecycle_avg_'+label +'_[]');

	for (var i = 0; i < recycelNamesIds.length; i++) {
		var recycleObj = {};
		
		//Treatment Energy Reading Validation
		if (recycelStartReadingIds[i].value== "" || recycelEndReadingIds[i].value == "" || recycelActReadingIds[i].value == "") {
			$("#treatmentwater_recycleReading_error_" + recycelNamesIds[i].value+"_"+label).html("<label class='text-red'>Enter Quantity</label>");
			flag++;
		}else{
		recycleObj["recycleId"] = recycelNamesIds[i].value;
		recycleObj["recycleStartReading"] = recycelStartReadingIds[i].value;
		recycleObj["recycleEndReading"] = recycelEndReadingIds[i].value;
		recycleObj["recycleActReading"] = recycelActReadingIds[i].value;
		
		recycleDataArray.push(recycleObj);
		}
	}
	treatmentObj["regRecycleList"] = recycleDataArray;
	myObj["regularTreatmentData"]=treatmentObj;
	if(flag<=0){
	$.ajax({
		type : "POST",
		url : "ajax-save-regular-treatmentData",
		contentType : "application/json",
		data : JSON.stringify(myObj),
		success : function(data) {
         if(data=="success"){
        	 $("#regularTreatmentSavebtn"+label).attr("disabled", true);
				findNextAccordion(e3);
				jBoxBottomRightBigNotice("Success", "treatment Data Saved !!", "green", "2000");
         }
		}
	});
	}
}
function openRegularData(){
	var dismissMsg="success";
	window.location="envr-officer-view-regular-data?msg="+dismissMsg;
}
function setConsumption(stackDivId) {
	var firstValue = $("#apcStart_" + stackDivId).val();
	var secondValue = $("#apcEnd_" + stackDivId).val();

	if (firstValue != "" || secondValue != "") {
		if (Number(firstValue) <= Number(secondValue)) {
			var actConsumption = secondValue - firstValue;
			$("#apcError_" + stackDivId).html("");
			$("#actConsumption_" + stackDivId).val(actConsumption.toFixed(2));

		} else {
			$("#apcEnd_" + stackDivId).focus();
			$("#actConsumption_" + stackDivId).val("");
			$("#apcError_" + stackDivId).html("<label class='text-red'><i class='zmdi zmdi-alert-circle-o zmdi-hc-fw'></i>Check the reading!</label>");
		}
	}
}
function setTreatmentReadingConsumption(id) {
	var firstValue = $("#treatmentwater_startid_" + id).val();
	var secondValue = $("#treatmentwater_endtid_" + id).val();

	if (firstValue != "" || secondValue != "") {
		if (Number(firstValue) >= Number(secondValue)) {
			var actConsumption = firstValue - secondValue;
			$("#treatmentwater_Error_" + id).html("");
			$("#treatmentwater_avgid_" + id).val(actConsumption.toFixed(2));
		} else {
			$("#treatmentwater_endtid_" + id).focus();
			$("#treatmentwater_avgid_" + id).val("");
			$("#treatmentwater_Error_" + id).html("<label class='text-red'><i class='zmdi zmdi-alert-circle-o zmdi-hc-fw'></i>Outlet should be less than Inlet !!</label>");
		}
	}
}
function setTreatmentNewReadingConsumption(type,label) {
var startId = $("#treatmentwater_"+type+"_start_" + label);
var endId = $("#treatmentwater_"+type+"_end_" + label);
var avgId = $("#treatmentwater_"+type+"_avg_" + label);
var errorId = $("#treatmentwater_"+type+"_error_" + label);

var firstValue =  startId.val();
var secondValue =  endId.val();
	
	if (firstValue != "" || secondValue != "") {
		
		// for energy & recycleReading
		if(type == "energyReading"|| type=="recycleReading"){
			if (Number(firstValue) <= Number(secondValue)) {
				var actConsumption = secondValue-firstValue;
				errorId.html("");
				avgId.val(actConsumption.toFixed(2));
			} else {
				//endId.focus();
				avgId.val("");
				errorId.html("<label class='text-red'><i class='zmdi zmdi-alert-circle-o zmdi-hc-fw'></i>Please check the reading !!</label>");
			}
		} else  {
			
			// for water reading
			if (Number(firstValue) >= Number(secondValue)) {
				var actConsumption = firstValue - secondValue;
				errorId.html("");
				avgId.val(actConsumption.toFixed(2));
			} else {
				avgId.val("");
				errorId.html("<label class='text-red'><i class='zmdi zmdi-alert-circle-o zmdi-hc-fw'></i>Outlet should be less than Inlet !!</label>");
			}
		}
	}
}
function setConsumption(filterType, filterNameId) {
	var firstValue = $("#" + filterType + "_startid_" + filterNameId).val();
	var secondValue = $("#" + filterType + "_endid_" + filterNameId).val();
	$("#"+filterType+"_Error_"+filterNameId).val("");
	$("#"+filterType+"_avgid_"+filterNameId).val("");

	if (firstValue != "" || secondValue != "") {
		if (Number(firstValue) <= Number(secondValue)) {
			var actConsumption = secondValue - firstValue;
			$("#" + filterType + "_Error_" + filterNameId).html("");
			$("#" + filterType + "_avgid_" + filterNameId).val(actConsumption.toFixed(2));
		} else {
			$("#" + filterType + "_endid_" + filterNameId).focus();
			$("#" + filterType + "_avgid_" + filterNameId).val("");
			var a = filterType + "_Error_" + filterNameId;
			$("#" + a).html("<label class='text-red'><i class='zmdi zmdi-alert-circle-o zmdi-hc-fw'></i>Check the reading!</label>");
		}
	}
}
var specialKeys = new Array();
specialKeys.push(8);
function numberValidate(e, divId) {
	var keyCode = e.which ? e.which : e.keyCode;
	var ret = (((keyCode >= 48 && keyCode <= 57) || keyCode == 46 || keyCode == 60 ||
		keyCode == 62 || keyCode == 66 || keyCode == 68 || keyCode == 76) || specialKeys.indexOf(keyCode) != -1);
	var isnumber = ret ? "true " : "false";
	if (isnumber.indexOf("true") > -1) {
		$("#" + divId).html('');
		return true;
	} else {
		e.preventDefault();
		$("#" + divId).html('<label class="text-red">No characters allowed, except "."</label>');
		return false;
	}
}
function makeInvalid(el) {
	$("#" + el).addClass("is-invalid");
	$("#" + el).siblings('div').prop("hidden", false);
}
function makeValid(el) {
	$("#" + el).removeClass("is-invalid");
	$("#" + el).siblings('div').prop("hidden", true);
}
function makeSelect2() {
	if ($('select.select2')[0]) {
		var select2parent = $('.select2-parent')[0] ? $('.select2-parent') :
			$('body');

		$('select.select2').select2({
			dropdownAutoWidth: true,
			width: '100%',
			dropdownParent: select2parent
		});
	}
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
