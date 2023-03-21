var queryObject = "";
var queryObjectLen = "";
var fqueryObjectLen = "";
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 01
var yyyy = today.getFullYear();
var today_date = yyyy + "-" + mm + "-" + dd;
var date = getFormattedDate(today);
var colorsList = [ "#9966CC", "#7B3F00", "#DC143C", "#50C878", "#CC7722",
		"#333300", "#002233", "#803300", "#b30059", "#004d00", "#993333" ];
var action = 30;
var id = 30;
var width = 1050;
var height = 500;
var myLine = "";
var source_type = "";
var error_div = "chart_div_error";
var no_data = "<center><img src='img/nodata1.png' style='width:338px'></center>";
main_title = " Water used in CMD";

$(document).ready(function() {
	source_type = $("#source_type").val();
	setDefaultTitle("str");
	graphdata();
});
function getFormattedDate(today) {
	x = today.getMonth() + 1;
	y = today.getDate();
	if (x < 10) {
		x = "0" + x;
	}
	if (y < 10) {
		y = "0" + y;
	}
	var date = today.getFullYear() + '-' + x + '-' + y;
	return date;
}
function onMonthSetGetValues(id, action) {
	var yy = $("#yyear" + id).val();
	var mm = $("#mmonth" + id).val();
	if (yy == "") {
		jBoxBottomRightBigNotice("Warning", "Please select year", "yellow",
				"2000");
		return;
	}
	var lastDay = lastdayfromDate(yy, mm);

	if (action == "wa") {
		date = today;
		getMonthlyTitle(lastDay, "str");
		graphdata();
	} else if (action == "wb") {
		date = today;
		getMonthlyTitle(lastDay, "str1");
		statisticalAnalysis();
	} else if (action == "wc") {
		date = today;
		getMonthlyTitle(lastDay, "str2");
		dataQualityPerformanceStat();
	}
	if (action == "wd") {
		date = today;
		getMonthlyTitle(lastDay, "str3");
		dailyDataStat();
	}
}

function onDateSetGetValuesWater(id, dateValue, type, action) {
	var yy = document.getElementById('yyear' + id).value;
	var mm = document.getElementById('mmonth' + id).value;
	var dd = document.getElementById('add_days' + id).value;
	today = yy + "-" + mm + "-" + dd;

	if (action == "wa") {
		getTitle(today, "str");
		graphdata();
	} else if (action == "wb") {
		getTitle(today, "str1");
		statisticalAnalysis();
	} else if (action == "wc") {
		getTitle(today, "str2");
		dataQualityPerformanceStat();
	}
	if (action == "wd") {
		date = today;
		getTitle(today, "str3");
		dailyDataStat();
	}

	/*	$("#mmonth"+id).prop('selectedIndex',0);
	 $("#add_days"+id).prop('selectedIndex',0);
	 */
}

function getTitle(date, divId) {
	var c = new Date(date);
	c.setDate(c.getDate() - 30);
	x = c.getMonth() + 1;
	y = c.getDate();
	if (x < 10) {
		x = "0" + x;
	}
	if (y < 10) {
		y = "0" + y;
	}
	var prev_date = c.getFullYear() + '-' + x + '-' + y;
	$("#prevDate").val(prev_date);
	$("#newDate").val(date);
	var str = " <small> of previous 30 days from " + prev_date + " to " + date
			+ " </small>";
	document.getElementById(divId).innerHTML = str;
}

function graphdata() {
	var today_date = $("#newDate").val();
	var prevDate = $("#prevDate").val();
	var nameCapitalized = source_type.charAt(0).toUpperCase()
			+ source_type.slice(1);
	main_title = nameCapitalized;
	fwd_url = "ajax-get-each-water-data";
	$.ajax({
				type : 'POST',
				url : fwd_url,
				dataType : 'json',
				data : ({
					action : id,
					type : source_type,
					pdata : today_date,
				}),
				success : function(data) {
					
					divs = "source_div";
						if (data.length != 0) {
						$("#" + divs).show();
						$("#" + error_div).hide();
						if(source_type=="waste_water_treatment" || source_type=="waste_water_treatment_use")
						{
							makeCommonEchart(divs, main_title, "CMD", data, "", "zoomOption", "nodraggable","normal","onlyLine","smoothLine");
						}else{
							makeCommonEchart(divs, main_title, "CMD", data, "", "zoomOption", "nodraggable","advanced","onlyLine","smoothLine");
						}
			
					} else {
						$(".graph-div").hide();
						$("." + error_div).show();
						$("." + error_div)
								.html(
										"<center><img src='img/nodata1.png' style='width:338px'></center>");
					}
				},
				error : function(xhr, type) {
					$("." + error_div)
							.html(
									"<center><img src='img/nodata1.png' style='width:338px'></center>");
				},
				async : false
			});

}
function getPrevDate(date) {
	var c = new Date(date);
	c.setDate(c.getDate() - 30);
	x = c.getMonth() + 1;
	y = c.getDate();
	if (x < 10) {
		x = "0" + x;
	}
	if (y < 10) {
		y = "0" + y;
	}
	prev_date = c.getFullYear() + '-' + x + '-' + y;
	return prev_date;
}
function statisticalAnalysis() {
	var today_date = $("#newDate").val();
	var prevDate = $("#prevDate").val();
	a = "Water Used";
	if(source_type=="waste_water_treatment")
		{
		var msg_data = "<table id='sourcetableId'  class='table table-bordered'><thead>"
			+ "<th>"
			+ a
			+ "</th>"
			+ "<th >Min</th>"
			+ "<th>Max</th>"
			+ "<th >Avg</th>"
			+ "<th >Deviation</th>"
			+ "<th>98<SUB>th</SUB> Percentile</th>"
			+ "<th >Eng_Minimum</th>"
			+ "<th>Eng_Maximum</th>"
			+ "<th >Eng_Average</th>"
			+ "<th >Eng_Deviation</th>"
			+ "<th>Eng_98<SUB>th</SUB> Percentile</th></thead>";
	
		
		}
	else{
		var msg_data = "<table id='sourcetableId'  class='table table-bordered'><thead>"
			+ "<th>"
			+ a
			+ "</th>"
			+ "<th >Min</th>"
			+ "<th>Max</th>"
			+ "<th >Avg</th>"
			+ "<th >Deviation</th>"
			+ "<th>98<SUB>th</SUB> Percentile</th></thead>";
		
	}
	

	var fwd_url = "ajax-statisticsValues?action=water_statistical_analysis&type="
			+ source_type + "&today=" + today_date + "&prevDate=" + prevDate;

	$.ajax({
		type : 'GET',
		url : fwd_url,
		dataType : 'json',
		success : function(d) {
			var data = JSON.parse(d);
			$.each(data, function(index, element) {
				if(source_type=="waste_water_treatment")
					{
					temp = "<tr>" + "<td>" + element.product_name + "</td>"
					+ "<td>" + element.min_value + "</td>" + "<td>"
					+ element.max_value + "</td>" + "<td>"
					+ element.avg_value + "</td>" + "<td>" + element.sd
					+ "</td>" + "<td>" + element.percentile + "</td>"
					+ "<td>" + element.min_value_energy + "</td>" + "<td>"
					+ element.max_value_energy + "</td>" + "<td>"
					+ element.avg_value_energy + "</td>" + "<td>" + element.sd_energy
					+ "</td>" + "<td>" + element.percentile_energy + "</td>"
					+ "</tr>";
					}
				else{
					temp = "<tr>" + "<td>" + element.product_name + "</td>"
					+ "<td>" + element.min_value + "</td>" + "<td>"
					+ element.max_value + "</td>" + "<td>"
					+ element.avg_value + "</td>" + "<td>" + element.sd
					+ "</td>" + "<td>" + element.percentile + "</td>"
					+ "</tr>";	
				}
				
				msg_data = msg_data + temp;
			});

			temp = "</table>";
			msg_data = msg_data + temp;
			$("#statisticalAnalysisWater").html(msg_data);

		},
		error : function(xhr, type1) {
			$("#statisticalAnalysisWater").html(no_data);
		},
		async : false
	});
	makedatatable('sourcetableId');
}
function dataQualityPerformanceStat() {
	var today_date = $("#newDate").val();
	var prevDate = $("#prevDate").val();
	$("#performanceStatWater").empty();
	var fwd_url = "ajax-statisticsValues?action=water_performance_stat&type="
			+ source_type + "&today=" + today_date;

	$
			.ajax({
				type : 'GET',
				url : fwd_url,
				dataType : 'json',
				success : function(d) {
					var data = JSON.parse(d);
					//console.log(data);
					$
							.each(
									data,
									function(index, element) {
										var finalValue = 0;
										finalValue = parseFloat(
												element.finalValue).toFixed(2);
										parameter = element.sourceType;
										metervalue = element.hasMeter;
										if (metervalue == 'true') {
											var Meter = "Meter is avilable";
										} else {
											Meter = "Meter is not avilable";
										}

										var iddd = parameter.replace(/\s/g, '');

										var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 offset-lg-4 widget-pie__item grey lighten-4'>"
												+ "<div class='easy-pie-chart chart"
												+ index
												+ "' id='easyPie_"
												+ index
												+ "' data-percent='"
												+ finalValue
												+ "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>"
												+ "<span class='easy-pie-chart__value'>"
												+ finalValue
												+ "</span>"
												+ "</div>"
												+ "<div class='widget-pie__title text-black'>"
												+ parameter
												+ " - "
												+ getMeterPerformance(finalValue)
												+ "</div>" + "</div>")

										$("#performanceStatWater").append(
												msg_data);

										var sectorialColor = getMeterColor(finalValue);
										var idd = "easyPie_" + index;
										makeEasyPieChart(idd, sectorialColor);

									});
					makeToolTip();
					if (data.length == 0) {
						$("#performanceStatWater").html(no_data);
					}
				},
				error : function(xhr, type1) {
					$("#performanceStatWater").html(no_data);
				},
				async : false
			});

}

function dailyDataStat() {
	var today_date = $("#newDate").val();
	var prevDate = $("#prevDate").val();
	a = "Water Used";

	if (source_type == 'source') {
		var msg_data = "<table id='" + source_type
				+ "_table' class='table table-bordered'><thead><tr>"
				+ "<th>#</th>" + "<th>Date</th>" + "<th>Staff</th>"
				+ "<th>Source</th>" + "<th>Start Reading</th>"
				+ "<th>End Reading</th>"
				+ "<th>Actual Consumption</th></tr></thead>";
	} else if (source_type == 'prefilter') {
		var msg_data = "<table id='" + source_type
				+ "_table' class='table table-bordered'><thead><tr>"
				+ "<th>#</th>" + "<th>Date</th>" 
				+ "<th>Source</th>" + "<th>Start Reading</th>"
				+ "<th>End Reading</th>"
				+ "<th>Actual Consumption</th></tr></thead>";
	} else if (source_type == 'filter') {
		var msg_data = "<table id='" + source_type
				+ "_table' class='table table-bordered'><thead>" + "<th>#</th>"
				+ "<th >Date</th>" + "<th >Filters</th>"
				+ "<th>Filtered Water Used</th>" + "<th>Start Reading</th>"
				+ "<th>End Reading</th>"
				+ "<th>Actual Consumption</th></thead>";
	} else if (source_type == 'filteruse') {
		var msg_data = "<table id='" + source_type
				+ "_table' class='table table-bordered'><thead>" + "<th>" + a
				+ "</th>" + "<th>Date</th>" + "<th>Filter Use Label</th>"
				+ "<th>Start Reading</th>"
				+ "<th>End Reading</th>"
				+ "<th>Actual Consumption</th></thead>";
	} else if (source_type == 'useofwater') {
		var msg_data = "<table id='" + source_type
				+ "_table' class='table table-bordered'><thead>" + "<th>" + a
				+ "</th>" + "<th>Date</th>" + "<th>Source</th>"
				+ "<th>Start Reading</th>" + "<th>End Reading</th>"
				+ "<th>Actual Consumption</th></thead>";
	}
	else if (source_type == 'waste_water_treatment') {
		var msg_data = "<table id='" + source_type
				+ "_table' class='table table-bordered'><thead>" 
				+ "<th>#</th>" + "<th>Date</th>" + "<th>Label</th>"
				+ "<th>Treatment Type</th>" + "<th>Start Reading</th>"
				+ "<th>End Reading</th>"
				+ "<th>Actual Consumption</th>" 
				+ "<th>Energy Start Readings</th>" 
				+ "<th>Energy End Reading </th>" 
				+ "<th>Energy Avg Reading</th></thead>";
	}else if(source_type=="waste_water_treatment_use")
		{
		var msg_data = "<table id='" + source_type
		+ "_table' class='table table-bordered'><thead>" + "<th>" + a
		+ "</th>" + "<th>Date</th>" + "<th>Source</th>"
		+ "<th>Start Reading</th>" + "<th>End Reading</th>"
		+ "<th>Actual Consumption</th></thead>";
		}
	var fwd_url = "ajax-statisticsValues?action=water_daily_data&type="
			+ source_type + "&today=" + today_date + "&prevDate=" + prevDate;
	;

	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		success : function(d) {
			var data = JSON.parse(d);
			var i = 1;
			$.each(data, function(index, element) {
				if (source_type == 'source') {
					temp = "<tbody><tr>" + "<td>" + i + "</td>" + "<td>"
							+ element.input_date + "</td>" + "<td>"
							+ element.staff + "</td>" + "<td>"
							+ element.source_name + "</td>" + "<td>"
							+ element.start_reading + "</td>" + "<td>"
							+ element.end_reading + "</td>" + "<td>"
							+ element.actual_reading + "</td>"
							+ "</tr></tbody>";
				} else if (source_type == 'prefilter') {
					temp = "<tbody><tr>" + "<td>" + i + "</td>" + "<td>"
							+ element.input_date + "</td>" + "<td>"
							+ element.source_name + "</td>" + "<td>"
							+ element.start_reading + "</td>" + "<td>"
							+ element.end_reading + "</td>" + "<td>"
							+ element.actual_reading + "</td>"
							+ "</tr></tbody>";
				} else if (source_type == 'filteruse') {
					temp = "<tbody><tr>" + "<td>" + i + "</td>" + "<td>"
							+ element.input_date + "</td>" + "<td>"
							+ element.source_name + "</td>" + "<td>"
							+ element.start_reading + "</td>" + "<td>"
							+ element.end_reading + "</td>" + "<td>"
							+ element.actual_reading + "</td>"
							+ "</tr></tbody>";
				} else if (source_type == 'filter') {
					temp = "<tbody><tr>" + "<td>" + i + "</td>" + "<td>"
							+ element.input_date + "</td>" + "<td>"
							+ element.source_name + "</td>" + "<td>"
							+ element.filter_type + "</td>" + "<td>"
							+ element.start_reading + "</td>" + "<td>"
							+ element.end_reading + "</td>" + "<td>"
							+ element.actual_reading + "</td>"
							+ "</tr></tbody>";
				} else if (source_type == 'useofwater') {
					temp = "<tbody><tr>" + "<td>" + i + "</td>" + "<td>"
							+ element.source_name + "</td>" + "<td>"
							+ element.input_date + "</td>" + "<td>"
							+ element.start_reading + "</td>" + "<td>"
							+ element.end_reading + "</td>" + "<td>"
							+ element.actual_reading + "</td>"
							+ "</tr></tbody>";
				}
				else if (source_type == 'waste_water_treatment') {
					temp = "<tbody><tr>" + "<td>" + i + "</td>" + "<td>"
							+ element.input_date + "</td>" + "<td>"
							+ element.treatType + "</td>"+"<td>"
							+ element.label + "</td>"+"<td>"
							+ element.start_reading + "</td>" + "<td>"
							+ element.end_reading + "</td>" + "<td>"
							+ element.actual_reading + "</td>" + "<td>"
							+ element.eng_start_reading + "</td>"+"<td>"
							+ element.eng_end_reading + "</td>"+"<td>"
							+ element.eng_avg_reading + "</td>"
							+ "</tr></tbody>";
				}
				else if(source_type=="waste_water_treatment_use")
				{
					temp = "<tbody><tr>" + "<td>" + i + "</td>" + "<td>"
					+ element.input_date + "</td>" + "<td>"
					+ element.source_name + "</td>" + "<td>"
					+ element.start_reading + "</td>" + "<td>"
					+ element.end_reading + "</td>" + "<td>"
					+ element.actual_reading + "</td>"
					+ "</tr></tbody>";
				}
				msg_data = msg_data + temp;
				i++;
			});

			temp = "</table>";
			//msg_data = msg_data + temp;
			$("#dailyDataStat").html(msg_data);
			makedatatable(source_type +"_table");

		},
		error : function(xhr, type1) {
			$("#dailyDataStat").html(no_data);

		}
	});
}
function getMeterPerformance(value) {
	var msg = "Best";
	if (value >= 100)
		msg = " Best";
	else if (value > 88.89 && value < 100)
		msg = " Best";
	else if (value > 77.78 && value <= 88.88)
		msg = " Better";
	else if (value > 66.67 && value <= 77.77)
		msg = " Good";
	else if (value > 55.55 && value <= 66.66)
		msg = " Above Average";
	else if (value > 44.45 && value <= 55.55)
		msg = " Average";
	else if (value > 33.34 && value <= 44.44)
		msg = " Below Average";
	else if (value > 22.23 && value <= 33.33)
		msg = " Bad";
	else if (value > 11.12 && value <= 22.22)
		msg = " Worse";
	else if (value >= 0 && value <= 11.11)
		msg = " Worst";

	return msg;
}
function getMeterColor(value) {
	var msg = "#fff";
	if (value >= 100)
		msg = "#004d1a";
	else if (value >= 88.89 && value < 100)
		msg = "#004d1a";
	else if (value >= 77.78 && value <= 88.88)
		msg = "#009900";
	else if (value >= 66.67 && value <= 77.77)
		msg = "#99FF00";
	else if (value >= 55.55 && value <= 66.66)
		msg = "#ffff00";
	else if (value >= 44.45 && value <= 55.55)
		msg = "#ffcc00";
	else if (value >= 33.34 && value <= 44.44)
		msg = "#ff9900";
	else if (value >= 22.23 && value <= 33.33)
		msg = "#ff6600";
	else if (value >= 11.12 && value <= 22.22)
		msg = "#ff3300";
	else if (value >= 0 && value <= 11.11)
		msg = "#FF0000";

	return msg;
}

function makeEasyPieChart(id, sectorialColor) {
	var temp = $("#" + id);
	var size = temp.data('size');
	temp.find('.easy-pie-chart__value').css({
		lineHeight : (size - 2) + 'px',
		fontSize : (size / 6) + 'px',
		color : '#000'
	});

	temp.easyPieChart({
		animate : 6000,
		barColor : sectorialColor,
		lineCap : 'circle',
		lineWidth : 9,
		scaleColor : 'black',
		lineCap : 'round',
		size : size
	})
}