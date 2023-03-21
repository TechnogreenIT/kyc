var no_data = "<center><img src='img/nodata1.png' style='width:338px'></center>";

$( document ).ready(function() {
    console.log( "ready!" );
    
    var today = new Date();
    x = today.getMonth() + 1;
    y = today.getDate();
    if (x < 10) {
        x = "0" + x;
    }
    if (y < 10) {
        y = "0" + y;
    }
    var date = today.getFullYear() + '-' + x + '-' + y;
    
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
    var str = " <small> of previous 30 days from " + prev_date + " to " + date + " </small>";
    document.getElementById("str").innerHTML = str;
   /* document.getElementById("str1").innerHTML = str1;
    document.getElementById("str2").innerHTML = str;*/

    //var prevD = lastdayfromDate(yy,mm);
    $("#performance_prevDate").val(prev_date);
    $("#performance_newDate").val(date);
    
    
    getWaterPerformanceQuality();
});

function getMonthChangeData(id, dateValue, action) {
    var yy = $("#yyear" + id).val();
    var mm = $("#mmonth" + id).val();
    
    if (yy == "") {
        jBoxBottomRightBigNotice("Warning", "Please select year", "yellow", "2000");
        return;
    }
    var lastDay = lastdayfromDate(yy, mm);

    if (action == "a") {
        getMonthlyTitlePerformance(lastDay, "str");
        getWaterPerformanceQuality();
    } else if (action == "b") {
        isRecordFor = "Month";
        getMonthlyTitlePerformance(lastDay, "str1");
        //date = today;
        getPerCompliance();
    } else if (action == "c") {
        //	date = today;
        isRecordFor = "Month";
        getMonthlyTitlePerformance(lastDay, "str2");
        getEnvQualityData();

    }
}

function getMonthlyTitlePerformance(date, divId) {
    var a = date.split("-");
    var prev_date = a[0] + '-' + a[1] + '-01';
    $("#performance_prevDate").val(prev_date);
    $("#performance_newDate").val(date);
    var str = " <small>from " + prev_date + " to " + date + " </small>";
    if (divId == "str1") {
        var monthName = getMonthNameBydate(date);
        var str = " <small>record for " + monthName + " </small>";
    }
    if (divId == "str2") {
        var monthName = getMonthNameBydate(date);
        var str = " <small>record for " + monthName + " </small>";
    }
    document.getElementById(divId).innerHTML = str;
}
function getOnDateSetWaterData(id) {
    id1 = $("#yyear" + id).val();
    id2 = $("#mmonth" + id).val();
    id3 = $("#add_days" + id).val();
    date = id1 + "-" + id2 + "-" + id3;
    if (id == 1) {
        getTitlePerformance(date, "str");
        getWaterPerformanceQuality();
    } else if (id == 2) {
        getDayChnageTitlePerformance(date, "str1");
        getPerCompliance();
    } else if (id == 3) {
        getDayChnageTitlePerformance(date, "str2");
        getEnvQualityData();
    }
}

function getTitlePerformance(date, divId) {

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
    $("#performance_prevDate").val(prev_date);
    $("#performance_newDate").val(date);
    var str = " <small>from " + prev_date + " to " + date + " </small>";
    document.getElementById(divId).innerHTML = str;
}

//this is for water performance tab 1 
function getWaterPerformanceQuality() {
	waterPerformanceFinalValue = 0;
    count = 0;
    var ii = 0;
    var totalnumber = 0;
	var typeName = $("#type_name").val();
    var todayDate = $("#performance_newDate").val();
    
    $.ajax({
        type: 'POST',
        url: 'ajax-getWaterPerformanceAboutQuality',
        data: ({
            type: typeName,
            today: todayDate
        }),

        success: function(data) {
        	 var parseData = JSON.parse(data);
        	 $("#sectoralDataQality").empty();$("#overAllDataQality").empty();
             $.each(parseData, function(index, element) {
                 var sourceName = element.sourceName;
                 var availableData = parseFloat(element.availableData).toFixed(2);
                 var metervalue = element.hasMeter;

                 if (metervalue == 'true') {
                     var meter = "Meter is avilable";
                 } else {
                     var meter = "Meter is not avilable";
                 }
                 if(sourceName != "OverAll"){
                	 makeDataMeter(sourceName, availableData, meter,"sectoralDataQality");
                 } else {
                	 makeDataMeter(sourceName, availableData, meter,"overAllDataQality");
                 }
             });

         
        },
        error: function(xhr, type) {
            alert('error in getWaterPerformanceQuality');
        },
        async: false
    });
}

function waterPerformancePointer(parameter, productid, waterPerformanceFinalValue, count, ii) {
    var temp = productid;
    var meter = count + " Out of " + ii + " meter Avaliable ";


    var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 widget-pie__item grey lighten-4'>" +
        "<div class='easy-pie-chart chart" + i + "' id='easyPie_" + i + "' data-percent='" + waterPerformanceFinalValue + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" +
        "<span class='easy-pie-chart__value'>" + waterPerformanceFinalValue + "</span>" +
        "</div>" +
        "<div class='widget-pie__title text-black'>" + getMeterPerformance(waterPerformanceFinalValue) + "</div>" +
        "</div>")

    $("#waterPerformanceDataQuality").append(msg_data);


    var sectorialColor = getMeterColor(waterPerformanceFinalValue);
    var idd = "easyPie_" + i;
    makeEasyPieChart(idd, sectorialColor);
    waterPerformanceFinalValue = 0;
}

function makeDataMeter(parameter, availableData, meter,appendId) {
    var min=0; 
    var max=100;  
    var random = Math.round(Math.random() * (+max - +min) + +min); 
        
    var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 widget-pie__item grey lighten-4  mr-5'>" +
		"<div class='easy-pie-chart chart" + i + "' id='easyPie_"+ random + "' data-percent='" + availableData + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" +
		"<span class='easy-pie-chart__value'>" + availableData + "</span>" +
		"</div>" +
		"<div class='widget-pie__title text-black'>" + parameter + " - " + getMeterPerformance(availableData) + "</div>" +
		"</div>")

    $("#"+appendId).append(msg_data);

    var sectorialColor = getMeterColor(availableData);
    var idd = "easyPie_"+random ;
    makeEasyPieChart(idd,sectorialColor);
    
}
function onTabClick(id){
	
	if(id == "1"){
		getWaterPerformanceQuality();
	}
	
}
//grapgh meter
function getMeterPerformance(value) {
    var msg = "Best";
    if (value >= 100) msg = " Best";
    else if (value > 88.89 && value < 100) msg = " Best";
    else if (value > 77.78 && value <= 88.88) msg = " Better";
    else if (value > 66.67 && value <= 77.77) msg = " Good";
    else if (value > 55.55 && value <= 66.66) msg = " Above Average";
    else if (value > 44.45 && value <= 55.55) msg = " Average";
    else if (value > 33.34 && value <= 44.44) msg = " Below Average";
    else if (value > 22.23 && value <= 33.33) msg = " Bad";
    else if (value > 11.12 && value <= 22.22) msg = " Worse";
    else if (value >= 0 && value <= 11.11) msg = " Worst";

    return msg;
}

function getMeterColor(value) {
    var msg = "#fff";
    if (value >= 100) msg = "#004d1a";
    else if (value >= 88.89 && value < 100) msg = "#004d1a";
    else if (value >= 77.78 && value <= 88.88) msg = "#009900";
    else if (value >= 66.67 && value <= 77.77) msg = "#99FF00";
    else if (value >= 55.55 && value <= 66.66) msg = "#ffff00";
    else if (value >= 44.45 && value <= 55.55) msg = "#ffcc00";
    else if (value >= 33.34 && value <= 44.44) msg = "#ff9900";
    else if (value >= 22.23 && value <= 33.33) msg = "#ff6600";
    else if (value >= 11.12 && value <= 22.22) msg = "#ff3300";
    else if (value >= 0 && value <= 11.11) msg = "#FF0000";

    return msg;
}

function makeEasyPieChart(id, sectorialColor) {
    var temp = $("#" + id);
    var size = temp.data('size');
    temp.find('.easy-pie-chart__value').css({
        lineHeight: (size - 2) + 'px',
        fontSize: (size / 6) + 'px',
        color: '#000'
    });

    temp.easyPieChart({
        animate: 6000,
        barColor: sectorialColor,
        lineCap: 'circle',
        lineWidth: 9,
        scaleColor: 'black',
        lineCap: 'round',
        size: size,
    })
}

function getMonthNameBydate(dateValue) {
    var objDate = new Date(dateValue),
        locale = "en-us",
        month = objDate.toLocaleString(locale, {
            month: "long"
        });
    return month;
}
function checkMonth(id){
	$("#mmonth"+id).prop('selectedIndex',0);
	$("#add_days"+id).prop('selectedIndex',0);
}
function openDays(id,id1){
	var d = new Date();
	var dd = d.getDate();
	var mm = d.getMonth()+1; //January is 01
	var yyyy = d.getFullYear();
	var today_date = yyyy+"-"+mm+"-"+dd;
	
	var msg_data,msg_data1,msg_data11,msg_data2,k;
	var month_id = document.getElementById('mmonth'+id1).value;
	var res;
	$('#add_days'+id1).empty();
	if(month_id == 1 || month_id == 3 || month_id == 5 || month_id == 7 || month_id == 8 || month_id == 10 || month_id == 12){
		$('#add_days'+id1).empty();
		msg_data = "<option value=''>Select day</option>";$(msg_data).appendTo("#add_days"+id1);
		for(k = 1; k <= 31; k++){
			if(k < 10){
				kk = "0"+k;
			} else {
				kk = k;
			}
			msg_data = "<option value='"+kk+"'>"+k+"</option>";
			$(msg_data).appendTo("#add_days"+id1);
		}
	}
	if(month_id == 02){
		$('#add_days'+id1).empty();
		msg_data = "<option value=''>Select day</option>";$(msg_data).appendTo("#add_days"+id1);
		var yr = document.getElementById('yyear'+id1).value;
		if (yr % 400 == 0 || (yr % 100 != 0 && yr % 4 == 0)) {
			for(k = 1; k <= 29; k++){
				if(k < 10){
					kk = "0"+k;
				} else {
					kk = k;
				}
				msg_data = "<option value='"+kk+"'>"+k+"</option>";
				$(msg_data).appendTo("#add_days"+id1);
			}
		}else {
			for(k = 1; k <= 28; k++){
				if(k < 10){
					kk = "0"+k;
				} else {
					kk = k;
				}
				msg_data = "<option value='"+kk+"'>"+k+"</option>";
				$(msg_data).appendTo("#add_days"+id1);
			}
		}
	}
	if(month_id == 04 || month_id == 06 || month_id == 09 || month_id == 11){ 
		$('#add_days'+id1).empty();		
		msg_data = "<option value=''>Select day</option>";$(msg_data).appendTo("#add_days"+id1);
		for(k = 1; k <= 30; k++){
			if(k < 10){
				kk = "0"+k;
			} else {
				kk = k;
			}
			msg_data = "<option value='"+kk+"'>"+k+"</option>";
			$(msg_data).appendTo("#add_days"+id1);
		}
	}
}
function getPerformanceTitle(date, divId){
	var c = new Date(date);
	c.setDate(c.getDate() - 30);
	x = c.getMonth()+1;
	y = c.getDate();
	if(x < 10){
		x = "0"+x;
	}
	if(y < 10){
		y = "0"+y;
	}
	var prev_date = c.getFullYear()+'-'+x+'-'+y;
	$("#performance_prevDate").val(prev_date);$("#performance_newDate").val(date);
	var str = " <small> of previous 30 days from "+prev_date+" to "+date+" </small>";
	document.getElementById(divId).innerHTML = str;
}

function lastdayfromDate(yy,mm) {
	var date = new Date();
	var lastDay = new Date(yy, mm, 0);
	var dd = lastDay.getDate();
	return  lastDayWithSlashes = (yy + '-' + mm + '-' + dd);
}