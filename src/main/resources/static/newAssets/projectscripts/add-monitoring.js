$(document).ready(function () {
	console.log(" add monitoring data js loaded");
	getStackList();
	getAmbientList();
	getWaterList();
});

function getStackList() {
	$.ajax({
		url: 'ajax-tp-getStackList',
		type: 'post',
		contentType: 'application/json;',
		dataType: 'json',
		async: true,
		success: function (data) {
			var data1 = JSON.parse(data);
			var count = 0;
			$.each(data1, function (index, element) {
				var uniqueId = element.StackName + "_" + element.StackAttachedTo;
				var StackPollDatasList = element.StackPollDatas;
				var stId = element.StackId;
				var pollCount = 0;
				uniqueId = uniqueId.replace(/[\. ,:-]+/g, "");
				var html1 = "<h2><a href='#'> " + element.StackName + "-" + element.StackAttachedTo + " </a></h2>" +
					"<div><div class='card-body'>" +
					"<input type='hidden' id='stack_name_" + uniqueId + "' value='" + element.StackName + "'> <input type='hidden' id='attached_to_" + uniqueId + "' value='" + element.StackAttachedTo + "'><input type='hidden' id='stack_id_" + uniqueId + "' value='" + stId + "'>" +
					" <center> <h4 class='font-weight-bold'>STACK DETAILS</h4> </center>" +
					"<table class='table table-bordered normal'> <thead> <tr> <th>SR. NO.</th> <th>PARTICULARS</th> <th>DETAILS</th> <th>UNIT</th> </tr> </thead> " +
					"<tbody>" +
					" <tr> <td>1</td> <td>Material of Stack</td> <td>" + element.StackMatCons + "</td> <td>--</td> </tr>" +
					"<tr> <td>2</td> <td>Stack Height above roof</td> <td>" + element.StackHeight + "</td> <td>" + element.StackHeightUnit + "</td> </tr>" +
					"<tr> <td>3</td> <td>Type of Stack</td> <td>" + element.StackShape + "</td> <td>--</td> </tr>" +
					"<tr> <td>4</td> <td>Fuel Type</td> <td>" + element.StackFuelType + "</td> <td>" + element.StackFuelUnit + "</td> </tr>" +
					" <tr> <td>5</td> <td>Flue Gas Temperature</td> <td> <div class='form-group'><input type='text' class='form-control' placeholder='Flue Gas Temperature' id='gas_temp_" + uniqueId + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div><i class='form-group__bar'></i> </div> </td> <td><sup>o</sup>K</td> </tr>" +
					"<tr> <td>6</td> <td>Differential Pressure</td> <td> <div class='form-group'><input type='text' class='form-control' placeholder='Differential Pressure' id='pressure_" + uniqueId + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div><i class='form-group__bar'></i> </div> </td> <td>mmWG</td> </tr>" +
					" <tr> <td>7</td> <td>Diameter of Stack</td> <td>" + element.StackDia + "</td> <td>" + element.StackDiaUnit + "</td> </tr>" +
					"<tr> <td>8</td> <td>Velocity</td> <td> <div class='form-group'><input type='text' class='form-control' placeholder='Enter Velocity' id='velocity_" + uniqueId + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div><i class='form-group__bar'></i> </div> </td> <td>m/s</td> </tr>" +
					"<tr> <td>9</td> <td>Hours of Operation</td> <td> <div class='form-group'><input type='text' class='form-control' placeholder='Enter Hours of Operation' id='hrsOfOperation_" + uniqueId + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div><i class='form-group__bar'></i> </div> </td> <td> <div class='form-group'><select class='select2' data-placeholder='Select a unit' id='hrsOfOpUnit_" + uniqueId + "'> <option></option> <option>Minutes</option> <option>Hours</option> <option>Days</option></select> </div> </td> </tr>" +
					"<tr> <td>10</td> <td>Gas Volume</td> <td> <div class='form-group'><input type='text' class='form-control' placeholder='Enter Hours of Operation' id='volume_" + uniqueId + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div><i class='form-group__bar'></i> </div> </td> <td>Nm<sup>3</sup>/Hr</td> </tr>" +
					"</tbody> </table>"

					+
					" <center><h4 class='font-weight-bold'>TEST PARAMETERS</h4> </center> <table class='table table-bordered normal'><thead> <tr> <th>SR. NO.</th> <th>PARAMETERS</th> <th>RESULT</th> <th>UNIT</th> </tr></thead>" +
					"<tbody>"

				for (const [key, value] of StackPollDatasList.entries()) {
					var pollId = value.StackPollStackId;
					if (stId == pollId) {
						var html2 = "<tr>" +
							" <td>1</td>" +
							"<td>" +
							"<input type='hidden' id='pollutant_name_" + uniqueId + "_" + pollCount + "' value='" + value.StackPollName + "'>" + value.StackPollName +
							" </td><td>" +
							"<div class='form-group'><input type='text' class='form-control' placeholder='Enter Pollutant Quantity' id='pollutant_quantity_" + uniqueId + "_" + pollCount + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div><i class='form-group__bar'></i></div>" +
							" </td>  <td>" +
							"<input type='hidden' id='pollutant_unit_" + uniqueId + "_" + pollCount + "' value='" + value.StackPollUnit + "'>" + value.StackPollUnit +
							"</td> </tr>"
						pollCount++;

					} else {
						html2 = "<tr><td>NO POLLUTANT FOR THIS STACK </td></tr>";
					}
					html1 += html2;
				}

				var html3 = "</tbody>  </table>" +
					" <div class='container'>" +
						"<div class='row'>" +
							"<div class='form-group'>"
							+"<div class='fileinput fileinput-new' data-provides='fileinput'>"
							 +"<label>Stack Monitoring Copy : "
							  +"<span class='btn btn-primary btn-file'>"
							  +"<span class='fileinput-new'>Select file</span>"
							  +"<span class='fileinput-exists'>Change</span>"
							  +"<input type='file' id='stackfile_" + uniqueId + "' required>"
							  +"</span>"
							  +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
							+"</div>"

							+"</div>" +
						"</div>" +
						"<div class='row'> File name should not contain any special characters.</div>" +
						"<div class='row'>" +
							"<div class='col-12'>" +
								"<center>" +
									"<button class='btn btn-primary btn--icon-text' id='btn_" + uniqueId + "' onclick='saveTpStackData(\"" + uniqueId + "\",\"" + pollCount + "\",\"" + count + "\",this)'><i class='zmdi zmdi-save'></i> Submit</button>" +
								"</center>" +
							"</div" +
						"</div" +
						
						"</div>"
					"</div>" +
				"</div>";

				$("#stackAppend").append(html1 + html3);
				count++;
			});

			
			makeSelect2();
			$("#stackAppend").accordion({
				heightStyle: 'content',
				collapsible: true
			});


		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function getAmbientList() {
	$.ajax({
		url: 'ajax-tp-getAmbientList',
		type: 'post',
		contentType: 'application/json;',
		dataType: 'json',
		async: true,
		success: function (data) {
			var data1 = JSON.parse(data);
			var count = 1;
			$.each(data1, function (index, element) {

				var AmbientName = element.AmbientName;
				var AmbientId = element.AmbientId;
				var Criteria = element.Criteria;
				var AmbientPollDatasList = element.AmbientPollDatas;
				var stId = element.AmbientPollId;
				var pollCount = 1;
				uniqueId = AmbientName.replace(/[\. ,:-]+/g, "");
				//uniqueId = uniqueId.trim()
				uniqueId = uniqueId + "_" + count;
				var html1 = "<h2><a href='#'>" + AmbientName + "</a></h2>" +
					"<div class='card-body'><center><h4 class='font-weight-bold'>TEST PARAMETERS</h4></center>" +
					"<input type='hidden' id='ambient_name_" + uniqueId + "' value='" + element.AmbientName + "'><input type='hidden' id='criteria_" + uniqueId + "' value='" + element.Criteria + "'>" +
					"<input type='hidden' id='ambient_id" + uniqueId + "' value='" + element.AmbientId + "'>" +
					"<table class='table table-bordered normal'>" +
					"<thead><tr><th>SR. NO.</th><th>PARAMETERS</th><th>RESULT</th><th>UNIT</th></tr></thead>" +
					"<tbody>"

				for (const [key, value] of AmbientPollDatasList.entries()) {
					var html2 = "<tr><td>" + pollCount + "</td>" +
						"<td> <input type='hidden' id='pollutant_name_" + uniqueId + "_" + pollCount + "' value='" + value.AmbientPollName + "'>" + value.AmbientPollName + "</td>" +
						"<td><div class='form-group'> <input type='text' class='form-control' placeholder='Enter Pollutant Quantity' id='pollutant_quantity_" + uniqueId + "_" + pollCount + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div> <i class='form-group__bar'></i></div></td>" +
						"<td> <input type='hidden' id='pollutant_unit_" + uniqueId + "_" + pollCount + "' value='mg/Nm3'>" + value.AmbientPollUnit + "</td></tr>"
					pollCount++;

					html1 += html2;
				}


				var html3 = "</tbody></table>" +
					"<div class='container'>" +
					"<div class='row'>" +
					"<div class='form-group'>" 
					+"<div class='fileinput fileinput-new' data-provides='fileinput'>"
					 +"<label>ambient Monitoring Copy : "
					  +"<span class='btn btn-primary btn-file'>"
					  +"<span class='fileinput-new'>Select file</span>"
					  +"<span class='fileinput-exists'>Change</span>"
					  +"<input type='file' id='ambientfile_" + uniqueId + "' required>"
					  +"</span>"
					  +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
					+"</div>"+
					"</div></div>" +
					"<div class='row'> File name should not contain any special characters.</div>" +
					"<div class='row'><div class='col-12'><center> <button class='btn btn-primary btn--icon-text' id='btn_" + uniqueId + "' onclick='saveTpAmbientData(\"" + uniqueId + "\",\"" + pollCount + "\",this)'><i class='zmdi zmdi-save'></i> Submit</button></center></div></div>" +
					"</div></div></div></div></div>"


				$("#ambientAppend").append(html1 + html3);
				count++;
			});

			makeSelect2();
			$("#ambientAppend").accordion({
				heightStyle: 'content',
				collapsible: true
			});


		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});

}

function getWaterList() {
	$.ajax({
		url: 'ajax-tp-getWaterList',
		type: 'post',
		contentType: 'application/json;',
		dataType: 'json',
		async: true,
		success: function (data) {
			var data1 = JSON.parse(data);
			
			var Pollutantarray = ['Effluent Pollutant', 'Sewage Pollutant'];
			for (var i = 0; i < Pollutantarray.length; i++) {
				var mainAccordions = "<h2><a href='#'> " + Pollutantarray[i] + " </a></h2><div id='treatment_"+i+"'></div>";
				$("#waterAppend").append(mainAccordions);
				
				var subAccordion = "";
						$.each(data1, function (index, element) {
							var poluutantList = element.pollData;
							if (Pollutantarray[i] == element.Pollutant) {
								subAccordion += "<h2><a href='#'>"+element.label+"</a></h2>"
								+"<div><div class='card-body'>"
								+"<center><h4 class='font-weight-bold'>TEST PARAMETERS</h4></center>"
								+"<table class='table table-bordered normal'>"
								+"<thead><tr><th>SR. NO.</th><th>PARAMETER NAME</th><th>INLET (Conc.)</th><th>OUTLET (Conc.)</th><th>UNIT</th></tr></thead>"
								+"<tbody>"
								
								var count = 1;
								for (const [key, value] of poluutantList.entries()) {
									var html2 = "<tr><td>" + count + "</td>"
									+"<td> <input type='hidden' name='polutant_name[]' id='pollutant_name_" + element.wwtid + "_" + count + "' value='" + value.pollutantId + "'>" + value.WaterPollutantName + "</td>"
									+"<td><div class='form-group'> <input type='text' class='form-control' placeholder='Enter Pollutant Quantity' id='pollutant_inlet_" + element.wwtid + "_" + count + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div> <i class='form-group__bar'></i></div></td>"
									+"<td><div class='form-group'> <input type='text' class='form-control' placeholder='Enter Pollutant Quantity' id='pollutant_outlet_" + element.wwtid + "_" + count + "' required /><div class='invalid-feedback' hidden>Please provide a valid input</div> <i class='form-group__bar'></i></div><i class='form-group__bar'></i></div></td>"
									+"<td> <input type='hidden' name='polutant_unit[]' id='pollutant_unit_" + element.wwtid + "_" + count + "' value='" + value.WaterPollutantUnit + "'> " + value.WaterPollutantUnit + "</td>"
									+"</tr>"
									count++;
									subAccordion += html2;
								}
								
								subAccordion += "</tbody></table>"
												+"<div class='container'>"
												+"<div class='row'><h6 class='font-weight-bold'>water Monitoring Copy :</h6></div>"
												+"<div class='row'>"
												+"<div class='form-group'>"
												+"<div class='fileinput fileinput-new' data-provides='fileinput'>"
												 +"<label>Water Analysis Report Copy : "
												  +"<span class='btn btn-primary btn-file'>"
												  +"<span class='fileinput-new'>Select file</span>"
												  +"<span class='fileinput-exists'>Change</span>"
												  +"<input type='file' id='waterfile_" + element.wwtid + "' required>"
												  +"</span>"
												  +"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>"
												+"</div>"
												+"</div></div>"
												+"<div class='row'> File name should not contain any special characters.</div>"
												+"<div class='row'><div class='col-12'><center> <button class='btn btn-primary btn--icon-text' id='btn_" + element.label + "' onclick='saveTpwaterData(\"" + element.Pollutant + "\",\"" + element.wwtid + "\",\"" + count + "\",this)'><i class='zmdi zmdi-save'></i> Submit</button></center></div></div>"
												+"</div></div>"
												+"</div></div>"
												
							}
							
							
							
						});
						

						
						var idd = "treatment_"+i;
						$("#"+idd).append(subAccordion);
						$("#"+idd).accordion({ heightStyle: 'content', collapsible: true });
				
			}
			
			
			$("#waterAppend").accordion({
				heightStyle: 'content',
				collapsible: true
			});
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});

}

function saveTpStackData(stId, pollCount, accordionId,el) {
	var valid = 0;
	var samp_st = $("#samp_st").val();
	var sub_st = $("#sub_st").val();

	var pollutant_list1 = new Array();
	var pollutant_list2 = new Array();
	var pollutant_list3 = new Array();
	var main_array = new Array();

	var stack_id = $("#stack_id_" + stId).val();
	var stack_name = $("#stack_name_" + stId).val();
	var attached_to = $("#attached_to_" + stId).val();
	var gas_temp = $("#gas_temp_" + stId).val();
	var pressure = $("#pressure_" + stId).val();
	var velocity = $("#velocity_" + stId).val();

	var hrs = $("#hrsOfOperation_" + stId).val();
	var hrs_units = $("#hrsOfOpUnit_" + stId).val();
	var volume = $("#volume_" + stId).val();
	var filename = $("#stackfile_" + stId).val();

	for (i = 0; i < pollCount; i++) {
		var pollutant_name = $("#pollutant_name_" + stId + "_" + i).val();
		var pollutant_quantity = $("#pollutant_quantity_" + stId + "_" + i).val();
		var pollutant_unit = $("#pollutant_unit_" + stId + "_" + i).val();

		if (pollutant_quantity == "") {
			var tempId = "pollutant_quantity_" + stId + "_" + i;
			makeInvalid(tempId);
			valid++;
		} else {
			var tempId = "pollutant_quantity_" + stId + "_" + i;
			makeValid(tempId);
		}
		if (pollutant_unit == "") {
			var tempId = "pollutant_unit_" + stId + "_" + i;
			makeInvalid(tempId);
			valid++;
		} else {
			var tempId = "pollutant_unit_" + stId + "_" + i;
			makeValid(tempId);
		}
		pollutant_list1.push(pollutant_name);
		pollutant_list2.push(pollutant_quantity);
		pollutant_list3.push(pollutant_unit);
	}

	if (samp_st == "") {
		$("#samp_st_error").prop("hidden", false);
	} else {
		$("#samp_st_error").prop("hidden", true);
	}
	if (sub_st == "") {
		$("#sub_st_error").prop("hidden", false);
	} else {
		$("#sub_st_error").prop("hidden", true);
	}

	if (filename != "") {
		var file_data = $('#stackfile_' + stId).prop('files')[0]; // alert(file_data)
		filename1 = file_data.name;

		/*if(file_data=="") {
			makeInvalid("stackfile_" + stId);
			valid++;
		}*/
	}

	if (gas_temp == "") {
		makeInvalid("gas_temp_" + stId);
		valid++;
	} else {
		makeValid("gas_temp_" + stId);
	}

	if (pressure == "") {
		makeInvalid("pressure_" + stId);
		valid++;
	} else {
		makeValid("pressure_" + stId);
	}

	if (velocity == "") {
		makeInvalid("velocity_" + stId);
		valid++;
	} else {
		makeValid("velocity_" + stId);
	}

	if (volume == "") {
		makeInvalid("volume_" + stId);
		valid++;
	} else {
		makeValid("volume_" + stId);
	}

	if (hrs == "") {
		makeInvalid("hrsOfOperation_" + stId);
		valid++;
	} else {
		makeValid("hrsOfOperation_" + stId);
	}
	if (hrs_units == "") {
		makeInvalid("hrsOfOpUnit" + stId);
		valid++;
	} else {
		makeValid("hrsOfOpUnit" + stId);
	}

	if (valid == 0) {
		var form_data = new FormData();
		form_data.append('file', file_data);
		form_data.append('stackId', stack_id);
		form_data.append('sampSt', samp_st);
		form_data.append('subSt', sub_st);
		form_data.append('stackName', stack_name);
		form_data.append('attachTo', attached_to);
		form_data.append('gasTemp', gas_temp);
		form_data.append('pressure', pressure);
		form_data.append('exitGasVelocity', velocity);
		form_data.append('hrsOp', hrs + " " + hrs_units);
		form_data.append('gasQuant', volume);
		form_data.append('pollName', pollutant_list1);
		form_data.append('concPoll', pollutant_list2);
		form_data.append('units', pollutant_list3);
		$.ajax({
			url: "ajax-saveStackData?action=fileUploading&fn=stack",
			dataType: 'json',
			cache: false,
			contentType: false,
			processData: false,
			data: form_data,
			type: 'post',
			success: function (response) {
				if (response == "filesuccess") {
					findNextAccordion(el);
					var tempName = stack_name + " " + attached_to;
					jBoxBottomRightBigNotice("Success", tempName + " Saved !!", "green", "3000");
					$("#btn_" + stId).attr("disabled", true);
					$("#accordionId_" + accordionId).click();
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			}
		});
	}

}


//ambient add data
function saveTpAmbientData(uId, pollId,el) {
	var pollutant_list1 = new Array();
	var pollutant_list2 = new Array();
	var pollutant_list3 = new Array();
	var main_array = new Array();
	var valid = 0;

	var poallData = uId + "_" + pollId;
	var samp_amb = $("#samp_amb").val();
	var sub_amb = $("#sub_amb").val();
	var ambient_name = $("#ambient_name_" + uId).val();
	var ambient_id = $("#ambient_id" + uId).val();
	var criteria = $("#criteria_" + uId).val();
	var filename = $("#ambientfile_" + uId).val();

	if (filename != "") {
		var file_data = $('#ambientfile_' + uId).prop('files')[0]; // alert(file_data)
		filename1 = file_data.name;

		/*if(file_data=="") {
			makeInvalid("stackfile_" + stId);
			valid++;
		}*/
	}
	if (samp_amb == "") {
		$("#samp_amb_error").prop("hidden", false);
		valid++;
	} else {
		$("#samp_amb_error").prop("hidden", true);
	}
	if (sub_amb == "") {
		$("#sub_amb_error").prop("hidden", false);
		valid++;
	} else {
		$("#sub_amb_error").prop("hidden", true);
	}

	for (i = 1; i < pollId; i++) {
		var pollutant_name = $("#pollutant_name_" + uId + "_" + i).val();
		var pollutant_quantity = $("#pollutant_quantity_" + uId + "_" + i).val();
		var pollutant_unit = $("#pollutant_unit_" + uId + "_" + i).val();
		if (pollutant_quantity == "") {
			var tempId = "pollutant_quantity_" + uId + "_" + i;
			makeInvalid(tempId);
			valid++;
		} else {
			var tempId = "pollutant_quantity_" + uId + "_" + i;
			makeValid(tempId);
		}
		pollutant_list1.push(pollutant_name);
		pollutant_list2.push(pollutant_quantity);
		pollutant_list3.push(pollutant_unit);
	}

	if (valid == 0) {
		var form_data = new FormData();
		form_data.append('file', file_data);
		form_data.append('ambientId', ambient_id);
		form_data.append('ambientName', ambient_name);
		form_data.append('criteria', criteria);
		form_data.append('sampAmb', samp_amb);
		form_data.append('subAmb', sub_amb);
		form_data.append('pollName', pollutant_list1);
		form_data.append('concPoll', pollutant_list2);
		form_data.append('units', pollutant_list3);

		$.ajax({
			url: 'ajax-saveAmbientData?action=fileUploading&fn=ambient',
			dataType : 'json',
			cache : false,
			contentType : false,
			processData : false,
			data : form_data,
			type : 'post',
			success: function (data) {
				if (data == "Report Saved Successfully") {
					findNextAccordion(el);
					var tempName = ambient_name;
					jBoxBottomRightBigNotice("Success", tempName + " Saved !!", "green", "3000");
					$("#btn_" + uId).attr("disabled", true);
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}
			}
		});
	}
}


//water add data
function saveTpwaterData(str,wwtid,count,el) {
	var poll_name_e = new Array();
	var in_cons_e = new Array();
	var ou_cons_e = new Array();
	var unit_e = new Array();
	var valid = 0;

	var samp_e = $("#samp_e").val();
	var sub_e = $("#sub_e").val();
	var filename = $("#waterfile_" + wwtid).val();

	if (filename != "") {
		var file_data = $('#waterfile_' + wwtid).prop('files')[0]; // alert(file_data)
		filename1 = file_data.name;
	}
	if (samp_e == "") {
		$("#samp_water_error").prop("hidden", false);
		valid++;
	} else {
		$("#samp_water_error").prop("hidden", true);
	}
	if (sub_e == "") {
		$("#sub_water_error").prop("hidden", false);
		valid++;
	} else {
		$("#sub_water_error").prop("hidden", true);
	}
	for (i = 1; i < count; i++) {
		var pollutant_name = $("#pollutant_name_" + wwtid + "_" + i).val();
		var pollutant_inlet = $("#pollutant_inlet_" + wwtid + "_" + i).val();
		var pollutant_outlet = $("#pollutant_outlet_" + wwtid + "_" + i).val();
		var pollutant_unit = $("#pollutant_unit_" + wwtid + "_" + i).val();
		if (pollutant_inlet == "") {
			var tempId = "pollutant_inlet_" + i;
			makeInvalid(tempId);
			valid++;
		} else {
			var tempId = "pollutant_inlet_" + i;
			makeValid(tempId);
		}
		if (pollutant_outlet == "") {
			var tempId = "pollutant_outlet_" + i;
			makeInvalid(tempId);
			valid++;
		} else {
			var tempId = "pollutant_outlet_" + i;
			makeValid(tempId);
		}
		if (pollutant_unit == "") {
			pollutant_unit = "NA";
		}
		poll_name_e.push(pollutant_name);
		in_cons_e.push(pollutant_inlet);
		ou_cons_e.push(pollutant_outlet);
		unit_e.push(pollutant_unit);
	}
	if (str == 'Sewage Pollutant') {
		f_url = 'ajax-save-water-sew-poll-data';
	} else {
		f_url = 'ajax-save-waterie-pollutant';
	}

	if (valid == 0) {
		var form_data = new FormData();
		form_data.append('file', file_data);
		form_data.append('samp_e', samp_e);
		form_data.append('sub_e', sub_e);
		form_data.append('wwtpid', wwtid);
		form_data.append('poll_name_e', poll_name_e);
		form_data.append('in_cons_e', in_cons_e);
		form_data.append('ou_cons_e', ou_cons_e);
		form_data.append('unit_e', unit_e);
		
		$.ajax({
			url: f_url,
			dataType: 'json',
			cache: false,
			contentType: false,
			processData: false,
			data: form_data,
			type: 'post',
			success: function (data) {
				if (poll_name_e.length == data) {
					findNextAccordion(el);
					jBoxBottomRightBigNotice("Success", str + " Saved !!", "green", "3000");
					$("#btn_" + str).attr("disabled", true);
				} else {
					jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "2000");
				}

			}
		});
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
//extra function.
function demodemo(){
	var myObj = {};
	myObj['unitE']="jemish";
	
	$.ajax({
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        url: "ajax-dto-demo",
        data : JSON.stringify(myObj),
        success: function(data) {
            console.log(data);
        }
    });
}