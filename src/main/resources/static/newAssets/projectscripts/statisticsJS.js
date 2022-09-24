var queryObject = "";
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; // January is 0!
var yyyy = today.getFullYear();
var today_date = yyyy + "-" + mm + "-" + dd;
var action = 30;
var divs = "chart_div";
var myLine = "";
var source_type = "";
var error_div = "chart_div_error";
var fwd_url = "";
var date = getFormattedDate(today);
var prev_date = "";
var no_data = "<center><img src='../img/nodata1.png' style='width:338px'></center>";


$(document).ready(function() {
    var isWaterPage = $("#waterpageornot").val();
    source_type = $("#source_type").val();
    setDefaultTitle("str");
    if (isWaterPage == "No") {
        getUnits();
    }
});

function getUnits() {
    var send_unit = "getUnits";
    $.ajax({
        type: "POST",
        url: "ajax-getEachData",
        dataType: 'json',
        data: ({
            action: send_unit,
            type: source_type
        }),

        success: function(data) {
            var data1 = JSON.parse(data);
            var unit_data = "";
            $.each(data1, function(index, element) {
                unit_data = element.unit;
            });
            if (unit_data != "") {
                getGraphData(unit_data);
            }
        },
        error: function(xhr, type) {
            alert('server error occoured-0');
        },
        async: false
    });
}


function getgraphbyunit(id) {
    var unit = $('#allunit' + id).val();
    getGraphData(unit);
}

function getGraphData(units) {
    var today_date = $("#newDate").val();
    var fwd_url = "ajax-getGraphData";
    $.ajax({
        type: 'POST',
        url: fwd_url,
        dataType: 'json',
        data: ({
            action: '30',
            type: source_type,
            pdata: today_date,
            units: units
        }),
        success: function(data) {
        	divId = "stat_chart_div";
            if (data.length != 0) {
                $("#" + divId).show();
                $("#" + error_div).hide();
                var graphTitle = source_type + " in " + units;
                makeCommonEchart(divId, graphTitle, units, data, "", "zoomOption", "nodraggable","normal","multiLineMultiBarGraph","");
            } else {
                $("#" + divId).hide();
                $("#" + error_div).show();
                $("#" + error_div).html("<center><img src='img/nodata1.png' style='width:338px'></center>");
            }
        },
        error: function(xhr, type) {
            $("#" + error_div).html("<center><img src='img/nodata1.png' style='width:338px'></center>");
        },
        async: false
    });
}

function previousdate(date) {
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

function onDateSetGetValues(id, dateValue, type, action) {
    var yy = $("#yyear" + id).val();
    var mm = $("#mmonth" + id).val();
    var dd = $("#add_days" + id).val();
    today = yy + "-" + mm + "-" + dd;

    if (action == "a") {
        getTitle(today, "str");
        getUnits();
    } else if (action == "b") {
        getTitle(today, "str1");
        date = today;
        getStatisticalAnalysis();
    } else if (action == "c") {
        date = today;
        getTitle(today, "str2");
        dataQualityPerformanceStat();
    } else if (action == "d") {
        date = today;
        getTitle(today, "str3");
        dailyDataStat();
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
        getUnits();
    } else if (action == "b") {
        getMonthlyTitle(lastDay, "str1");
        //date = today;
        getStatisticalAnalysis();
    } else if (action == "c") {
        //	date = today;
        getMonthlyTitle(lastDay, "str2");
        dataQualityPerformanceStat();
    } else if (action == "d") {
        //date = today;
        getMonthlyTitle(lastDay, "str3");
        dailyDataStat();
    }
}

function getStatisticalAnalysis() {
    var today_date = $("#newDate").val();
    var prevDate = $("#prevDate").val();
    if (source_type == 'product') {
        a = "PRODUCT";
    } else if (source_type == 'byproduct') {
        a = "BYPRODUCT";
    } else if (source_type == 'raw material') {
        a = "RAW MATERIAL";
    } else if (source_type == 'fuel') {
        a = "FUEL";
    } else if (source_type == 'hwp') {
        a = "Hazardous Wastes from Process"; 
    } else if (source_type == 'hwpcf') {
        a = "Hazardous Wastes from PCF";
    } else if (source_type == 'nhwp') {
        a = "Non-Hazardous Wastes from Process";
    } else if (source_type == 'nhwpcf') {
        a = "Non-Hazardous Wastes from PCF";
    } else if (source_type == 'bio') {
        a = "Bio-Medical Waste";
    }
    var msg_data = "<table id='sourcetableId'  class='table table-bordered'><thead><tr>" +
        "<th>" + a + "</th>" +
        "<th >Minimum</th>" +
        "<th>Maximum</th>" +
        "<th >Average</th>" +
        "<th >Standard Deviation</th>" +
        "<th>98<SUB>th</SUB> Percentile</th></tr></thead>";

    var fwd_url = "ajax-statisticsValues?action=statistical_analysis&type=" + source_type + "&today=" + today_date + "&prevDate=" + prevDate;
    $.ajax({
        type: 'GET',
        url: fwd_url,
        dataType: 'json',
        success: function(d) {
            var data = JSON.parse(d);
            $.each(data, function(index, element) {
                temp = "<tr>" +
                    "<td>" + element.product_name + "</td>" +
                    "<td>" + element.min_value + "</td>" +
                    "<td>" + element.max_value + "</td>" +
                    "<td>" + element.avg_value + "</td>" +
                    "<td>" + element.sd + "</td>" +
                    "<td>" + element.percentile + "</td>" +
                    "</tr>";
                msg_data = msg_data + temp;
            });

            temp = "</table>";
            msg_data = msg_data + temp;
            $("#statisticalAnalysisAppend").html(msg_data);
        },
        error: function(xhr, type1) {
            $("#statisticalAnalysisAppend").html(no_data);
        },
        async: false
    });
    makedatatable('sourcetableId');
}

function dataQualityPerformanceStat() {
    var today_date = $("#newDate").val();
    var prevDate = $("#prevDate").val();
    var fwd_url = "ajax-statisticsValues?action=performance_stat&type=" + source_type + "&today=" + today_date + "&prevDate=" + prevDate;
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
                metervalue = element.meter_value;
                if (metervalue == 'Yes') {
                    var Meter = "Meter is avilable";
                } else {
                    Meter = "Meter is not avilable";
                }

                var iddd = parameter.replace(/\s/g, '');
                
             var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 offset-lg-4 widget-pie__item grey lighten-4'>" +
                "<div class='easy-pie-chart chart" + index + "' id='easyPie_" + iddd + "' data-percent='" + finalValue + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" +
                "<span class='easy-pie-chart__value'>" + finalValue + "</span>" +
                "</div>" +
                "<div class='widget-pie__title text-black'>" + parameter + " - " + getMeterPerformance(finalValue) + "</div>" +
                "</div>")
                    
                $("#performanceStat").html(msg_data);

                var sectorialColor = getMeterColor(finalValue);
                var sectorialColor = getMeterColor(finalValue);
                var idd = "easyPie_" + iddd;
                makeEasyPieChart(idd, sectorialColor);

            });
            makeToolTip();
            if (data.length == 0) {
                if (source_type == 'source' || source_type == 'filter' || source_type == 'filtered' || source_type == 'directUse') {
                    $("#performanceStat_" + source_type).html(no_data);
                } else {
                    $("#performanceStat").html(no_data);
                }
            }
        },
        error: function(xhr, type1) {
            $("#performanceStat").html(no_data);
        },
        async: false
    });

}

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


function dailyDataStat() {
    var today_date = $("#newDate").val();
    var prevDate = $("#prevDate").val();
    if (source_type == 'product') {
        a = "PRODUCT";
    } else if (source_type == 'byproduct') {
        a = "BYPRODUCT";
    } else if (source_type == 'raw') {
        a = "RAW MATERIAL";
    } else if (source_type == 'fuel') {
        a = "FUEL";
    } else if (source_type == 'hwp') {
        a = "Hazardous Wastes from Process";
    } else if (source_type == 'hwpcf') {
        a = "Hazardous Wastes from PCF";
    } else if (source_type == 'nhwp') {
        a = "Non-Hazardous Wastes from Process";
    } else if (source_type == 'nhwpcf') {
        a = "Non-Hazardous Wastes from PCF";
    } else if (source_type == 'bio') {
        a = "Bio-Medical Waste";
    }
    var msg_data = "<table class='table table-bordered normal' id='dailyDataTableId'><thead><tr>" +
        "<th>" + a + "</th>" +
        "<th>Date</th>" +
        "<th>Quantity</th>" +
        "<th>Units</th>" +
        "<th>Warnings</th></tr></thead>";
    var fwd_url = "ajax-statisticsValues?action=daily_data&type=" + source_type + "&today=" + today_date + "&prevDate=" + prevDate;
    $.ajax({
        type: 'POST',
        url: fwd_url,
        dataType: 'json',
        success: function(d) {
            var data = JSON.parse(d);
            var i = 1;
            $.each(data, function(index, element) {

                temp = "<tr>" +
                    "<td>" + element.product_name + "</td>" +
                    "<td>" + element.input_date + "</td>" +
                    "<td>" + element.quantity + "</td>" +
                    "<td>" + element.unitp + "</td>" +
                    "<td>" + element.warning + "</td>" +
                    "</tr>";

                msg_data = msg_data + temp;
                i++;
            });

            temp = "</table>";
            msg_data = msg_data + temp;

            $("#dailyDataStat").html(msg_data);
            makedatatable('dailyDataTableId');

        },
        error: function(xhr, type1) {
            $("#dailyDataStat").html(no_data);
        }
    });
    makedatatable('stata');
}

function statisticsChartGraph(title, y_axis_lebel, divId, queryObject) {
    destroyCanvas();
    var listlabel = new Array();
    var config = {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20,
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering an item
            },
            responsiveAnimationDuration: 0, // animation duration after a resize
            legend: {
                display: false
            },
            scales: {
                xAxes: [{
                    display: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            pan: {
                enabled: true,
                mode: "xy",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            }
        },

    };

    var ctx = document.getElementById(divId).getContext("2d");
    //drawChart
    window.myLine = new Chart(ctx, config);
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var colorCode = "";
            var linecolorIndex = i - 1;
            var barcolorIndex = i - 2;
            var typee = "";
            var mydata = new Array();
            if (isEven(i)) {
                typee = "bar";
                colorCode = colorsList[barcolorIndex];
            } else {
                typee = "line";
                colorCode = colorsList[linecolorIndex];
            }
            colorCode= getRandomColor();
            var newDataset = {
                type: typee,
                label: cube[0],
                backgroundColor: colorCode,
                borderColor: colorCode,
                borderWidth: 1,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                newDataset.data.push(mydata[index]);
            }
            config.data.datasets.push(newDataset);
        }
    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    window.myLine.update();

    document.getElementById('js-legend').innerHTML = myLine.generateLegend();

    $("#js-legend > ul > li").on("click", function(e) {
        console.log(this, e.view.myLine);
        var index = $(this).index();
        $(this).toggleClass("strike")
        var ci = e.view.myLine;
        console.log(index)
        //var meta = ci.getDatasetMeta(index);
        console.log();
        var curr = ci.data.datasets[0]._meta[0].data[index];

        curr.hidden = !curr.hidden
        if (meta.dataset) {
            meta.hidden = !meta.hidden;
        } else {
            meta.data[index].hidden = !meta.data[index].hidden;
        }

        // We hid a dataset ... rerender the chart
        ci.update();
    })
}

function isEven(n) {
    return n % 2 == 0;
}
var colorsList = ["#9966CC", "#7B3F00", "#DC143C", "#50c878", "#CC7722", "#531508", "#7F0099", "#F5597D", "#b30059", "#1ae728", "#f4460b", "#F9C905", "#F97805", "#05D4F9", "#09FA94", "#A299A7"];

function destroyCanvas() {
    $('canvas').parent().each(function() {
        // get child canvas id
        childCanvasId = $(this).find("canvas").attr('id');
        // remove canvas
        $('#' + childCanvasId).remove();
        // append new canvas to the parent again
        $(this).append('<canvas id="' + childCanvasId + '"></canvas>');
    });
}

function getRandomColor() {
  var letters = '0123456789ABCDEF';
  var color = '#';
  for (var i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
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


function multiLineMultiBar(resData,graphTitle,units) {

	 var dataSeries = new Array();
	 
	 //var myChart = echarts.init(document.getElementById('stat_chart_div'),"dark");
	var datesArray = new Array();
	var firstArray = resData[0];
	firstArray = firstArray.slice(1,32);
	
	var legendArray = new Array();
   
    for (var i = 0; i < resData.length; i++) {

    	if (i > 0) {
			var obj1 = new Object();

			var typee;
			var array_element = resData[i];
			var pName = array_element.slice(0, 1);
			var graphReading = array_element.slice(1, 32);

			if (isEven(i)) {
				typee = "bar";
			} else {
				typee = "line";
			}

			obj1.name = pName[0];legendArray.push(pName[0]);
			obj1.type = typee;
			obj1.data = graphReading;
			dataSeries.push(obj1);
		}
	}
    

    var option = {
		title : {
			text : graphTitle,
			left : 'center',
			top : 0
		},
        color: colorArray,
        tooltip: {

        },
        grid: {
            top: 100,
        },
        toolbox: {
            feature: {
//                dataView: {
//                    show: true,
//                    readOnly: false
//                },
//                magicType: {
//                    show: true,
//                    type: ['line', 'bar']
//                },
//                restore: {
//                    show: true
//                },
                saveAsImage: {
                    show: true
                }
            }
        },
        legend: [{
            type: 'scroll',
            selector: true,
            data : legendArray,
            backgroundColor: 'rgba(0,100,50,0.2)',
            pageButtonPosition: 'start',
            top:30
        }],
        xAxis: [{
            type: 'category',
            data: firstArray,
            axisPointer: {
                type: 'shadow'
            }
        }],
        yAxis: [{
            type: 'value',
            name: units,
            // min: 0,
            // max: 250,
            // interval: 50,
            axisLabel: {
                formatter: '{value} '+units,
                textStyle: {
                    fontSize: 10
                }
            },
            
        }],
        dataZoom: [{
                show: true,
                start: 90,
                end: 100
            },
            {
                type: 'inside',
                start: 90,
                end: 100
            },
            {
                show: true,
                yAxisIndex: 0,
                filterMode: 'empty',
                width: 30,
                height: '80%',
                showDataShadow: true,
                left: '93%'
            }
        ],

        series: dataSeries
    };


    //myChart.setOption(option);
    testHelper.createChart(echarts, document.getElementById('stat_chart_div'), option,'dark', {draggable: false});
}

var colorArray = ['#FF6633', '#FFB399', '#FF33FF', '#FFFF99', '#00B3E6', 
	  '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D',
	  '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A', 
	  '#FF99E6', '#CCFF1A', '#FF1A66', '#E6331A', '#33FFCC',
	  '#66994D', '#B366CC', '#4D8000', '#B33300', '#CC80CC', 
	  '#66664D', '#991AFF', '#E666FF', '#4DB3FF', '#1AB399',
	  '#E666B3', '#33991A', '#CC9999', '#B3B31A', '#00E680', 
	  '#4D8066', '#809980', '#E6FF80', '#1AFF33', '#999933',
	  '#FF3380', '#CCCC00', '#66E64D', '#4D80CC', '#9900B3', 
	  '#E64D66', '#4DB380', '#FF4D4D', '#99E6E6', '#6666FF'];