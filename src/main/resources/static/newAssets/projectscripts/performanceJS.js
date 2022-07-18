var height = "100%";
var type = "";
var fwd_url = "";
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
var no_data = "<center><img src='img/nodata1.png' style='width:338px'></center>";
var type = "";
$(document).ready(function() {
    type = $("#type_name").val();
    setDefaultTitle("str");
    getDataQuality();
});


function getDataQuality() {
    var today_date = $("#newDate").val();
    var previous_date = $("#prevDate").val();

    var fwd_url = "ajax-statisticsValues?action=performance_stat&type=" + type + "&today=" + today_date + "&prevDate=" + previous_date;
    $.ajax({
        type: 'GET',
        url: fwd_url,
        dataType: 'json',
        success: function(d) {
            var data = JSON.parse(d);
            $.each(data, function(index, element) {
                var finalValue = 0;
                finalValue = parseFloat(element.missed_data).toFixed(2);
                parameter = element.product_name;

                productid = parameter.replace(/-/g, "_");
                productid = productid.replace(/ /g, "_");

                var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 offset-lg-4 widget-pie__item grey lighten-4'>" +
                    "<div class='easy-pie-chart chart" + i + "' id='easyPie_" + productid + "' data-percent='" + finalValue + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" +
                    "<span class='easy-pie-chart__value'>" + finalValue + "</span>" +
                    "</div>" +
                    "<div class='widget-pie__title text-black'>" + parameter + " - " + getMeterPerformance(finalValue) + "</div>" +
                    "</div>")

                $("#performanceData").html(msg_data);
                var sectorialColor = getMeterColor(finalValue);
                var idd = "easyPie_" + productid;
                makeEasyPieChart(idd, sectorialColor);

                getSingleDataQuality(type);
            });

        },
        error: function(xhr, type1) {
            $("#performanceStat").html(msg_data);
        }
    });
}

function getSingleDataQuality() {
    type = $("#type_name").val();

    var today_date = $("#newDate").val();
    var previous_date = $("#prevDate").val();
    var nc_count = 0;
    var did = "";
    var reason = "";
    fwd_url = "ajax-performanceValues?typename=" + type + "&action=SingleDataQuality&today=" + today_date + "&prevDate=" + previous_date;

    $.ajax({
        type: 'POST',
        url: fwd_url,
        dataType: 'json',
        success: function(responseData) {
            $("#performanceDataByProducts").empty();

            if (responseData != null) {
                var main_array = JSON.parse(responseData);

                mainArraySize = main_array.length;
                for (var i = 0; i < main_array.length; i++) {
                    var sub_array = main_array[i];
                    var parameter = sub_array[0];
                    var finalValue = sub_array[1].toFixed(2);

                    productid = "productid" + i;

                    var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 widget-pie__item grey lighten-4'>" +
                        "<div class='easy-pie-chart chart" + i + "' id='easyPie_" + i + "' data-percent='" + finalValue + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" +
                        "<span class='easy-pie-chart__value'>" + finalValue + "</span>" +
                        "</div>" +
                        "<div class='widget-pie__title text-black'>" + parameter + " - " + getMeterPerformance(finalValue) + "</div>" +
                        "</div>")

                    if (type == 'source' || type == 'filter' || type == 'filtered' || type == 'directUse') {
                        var ss = "#performanceStat_" + type + "_" + parameter;
                        ss = ss.replace(/\s/g, '');
                        $(ss).html(msg_data);
                    } else {
                        $("#performanceDataByProducts").append(msg_data);
                    }
                    var sectorialColor = getMeterColor(finalValue);
                    var idd = "easyPie_" + i;
                    makeEasyPieChart(idd, sectorialColor);
                }
            }
        }
    });
}

function getConformance() {
    var today_date = $("#newDate").val();
    var previous_date = $("#prevDate").val();
    type = $("#type_name_performance").val();

    fwd_url = "ajax-performanceComplianceValues?typename=" + type + "&action=SingleDataQuality&today=" + today_date + "&prevDate=" + previous_date;
    $.ajax({
        type: 'GET',
        url: fwd_url,
        dataType: 'json',
        success: function(responsedata) {
            if (responsedata != null) {
                var data = JSON.parse(responsedata);
                $.each(data, function(index, element) {
                    var finalValue = 0;
                    var resDara = element.NonCompliance;
                    a = resDara.split("-");
                  
                    finalValue = parseFloat(finalValue = a[0]).toFixed(2);
                    t_msg = "Exceeding : " + finalValue + " times";
                   
                    parameter = a[1];

                    var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 widget-pie__item grey lighten-4'>" +
                        "<div class='easy-pie-chart chart" + i + "' id='easyPie1_" + i + "' data-percent='" + finalValue + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" +
                        "<span class='easy-pie-chart__value'>" + finalValue + "</span>" +
                        "</div>" +
                        "<div class='widget-pie__title text-black'>" + parameter + " - " + getMeterPerformance(finalValue) + "</div>" +
                        "</div>")

                    $("#performanceComplianceAll").html(msg_data);

                    var sectorialColor = getMeterColor(finalValue);
                    var idd = "easyPie1_" + i;
                    makeEasyPieChart(idd, sectorialColor);

                    getSingleConformance();
                });
            }
        },
        error: function(xhr, type1) {
        	$("#performanceStat").html(msg_data);
        }
    });
}

function getSingleConformance() {
    var today_date = $("#newDate").val();
    var previous_date = $("#prevDate").val();
    var nc_count = 0;
    var did = "";
    var reason = "";
    type = $("#type_name_performance").val();
    fwd_url = "ajax-performanceComplianceSingleValues?typename=" + type + "&today=" + today_date + "&prevDate=" + previous_date;
    $.ajax({
        type: 'POST',
        url: fwd_url,
        dataType: 'json',
        success: function(responsedata) {
            $("#performanceComplianceDataByProducts").empty();
            if (responsedata != null) {
                var main_array = JSON.parse(responsedata);

                for (var i = 0; i < main_array.length; i++) {
                    var sub_array = main_array[i];
                    var parameter = sub_array[0];
                    var finalValue = sub_array[1].toFixed(2);

                    productid = "productid" + i;
                    var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 widget-pie__item grey lighten-4'>" +
                        "<div class='easy-pie-chart chart" + i + "' id='easyPie0_" + i + "' data-percent='" + finalValue + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" +
                        "<span class='easy-pie-chart__value'>" + finalValue + "</span>" +
                        "</div>" +
                        "<div class='widget-pie__title text-black'>" + parameter + " - " + getMeterPerformance(finalValue) + "</div>" +
                        "</div>")

                    $("#performanceComplianceDataByProducts").append(msg_data);

                    var sectorialColor = getMeterColor(finalValue);

                    var idd = "easyPie0_" + i;
                    makeEasyPieChart(idd, sectorialColor);

                }

            }
        }
    });
}

function getOnDateSetPerformance(id, dateValue, type, action) {
    id1 = $("#yyear" + id).val();
    id2 = $("#mmonth" + id).val();
    id3 = $("#add_days" + id).val();
    date = id1 + "-" + id2 + "-" + id3;
    if (action == "a") {
        getTitle(date, "str");
        getDataQuality();
    } else if (action == "b") {
        getTitle(date, "str1");
        getConformance();
    } else if (action == "c") {
        getTitle(date, "str2");
        getDailyData();
    }
}

function onMonthSetGetValues(id, dateValue, type, action) {
    var yy = $("#yyear" + id).val();
    var mm = $("#mmonth" + id).val();
    if (yy == "") {
        jBoxBottomRightBigNotice("Warning", "Please select year", "yellow", "2000");
        return;
    }
    var lastDay = lastdayfromDate(yy, mm);

    if (action == "a") {
        getMonthlyTitle(lastDay, "str");
        getDataQuality();
    } else if (action == "b") {
        getMonthlyTitle(lastDay, "str1");
        getConformance();
    } else if (action == "c") {
        getMonthlyTitle(lastDay, "str2");
        getDailyData();
    }
}
//graphs meter
function getMeterPerformance(value) {
    var msg = "Best";
    if (value >= 100) msg = " Best";
    else if (value > 88.89 && value < 100) msg = " Best";
    else if (value > 77.78 && value <= 88.88) msg = " Better";
    else if (value > 66.67 && value <= 77.77) msg = " Good";
    else if (value > 55.55 && value <= 66.66) msg = " Above Average";
    else if (value > 44.45 && value <= 55.55) msg = " Average";
    else if (value > 33.34 && value <= 44.44) msg = " Below  Average";
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


//DAILY DATA
function getDailyData() {
    var today_date = $("#newDate").val();
    var previous_date = $("#prevDate").val();
    //getPerformanceTitle(date, "str2");
    type_name = $("#type_name_daily_data").val();
    if (type_name == 'product') {
        a = "PRODUCT";
    } else if (type_name == 'byproduct') {
        a = "BYPRODUCT";
    } else if (type_name == 'raw') {
        a = "RAW MATERIAL";
    } else if (type_name == 'fuel') {
        a = "FUEL";
    } else if (type_name == 'hwp') {
        a = "Hazardous Wastes from Process";
    } else if (type_name == 'hwpcf') {
        a = "Hazardous Wastes from PCF";
    } else if (type_name == 'nhwp') {
        a = "Non-Hazardous Wastes from Process";
    } else if (type_name == 'nhwpcf') {
        a = "Non-Hazardous Wastes from PCF";
    } else if (type_name == 'bio') {
        a = "Bio-Medical Waste";
    }
    var msg_data = "<table class='table table-bordered normal' id='dailyDataTableId'><thead>" +
        "<th>" + a + "</th>" +
        "<th>Input Date</th>" +
        "<th>Quantity</th>" +
        "<th>Units</th>" +
        "<th>Warnings</th></thead>";

    fwd_url = "ajax-performanceDailyData?typename=" + type_name + "&today=" + today_date + "&prevDate=" + previous_date;
    $.ajax({
        type: 'POST',
        url: fwd_url,
        dataType: 'json',
        success: function(d) {
            var data = JSON.parse(d);
            $.each(data, function(index, element) {
                temp = "<tr>" +
                    "<td>" + element.product_name + "</td>" +
                    "<td>" + element.input_date + "</td>" +
                    "<td>" + element.quantity + "</td>" +
                    "<td>" + element.unitp + "</td>" +
                    "<td>" + element.warning + "</td>" +
                    "</tr>";
                msg_data = msg_data + temp;
            });
            temp = "</table>";
            msg_data = msg_data + temp;
            $("#performance_daily_data").html(msg_data);
            makedatatable('dailyDataTableId');
        },
        error: function(xhr, type) {}
    });

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