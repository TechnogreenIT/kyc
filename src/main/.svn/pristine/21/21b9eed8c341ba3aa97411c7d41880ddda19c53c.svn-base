var graph_loader ="<center><img src='../newAssets/loaders/graph-loader.svg' height='500px' width='500px'></center>";
var no_data = "<center><img src='../newAssets/img/nodata1.png' style='width:500px'></center>";
var activeEfficiency=0;
$(document).ready(function () {
	var industryType = $("#isproduction").val();
	// current date yyyy-mm-dd
	var today_date = todayDate();
	// previous date << 30 days 
	var prev_date = previousDate(today_date);
	//demo1();
	//demo3();
	getOverAllDailyDataQuality();
	getOverAllConformingPerformance();
	getEnvQualityDataWater(); // AHP WATER
	getEnvQualityDataAir(); // AHP AIR
	getEnvQualityDataHz(); // AHP HZ
	getOverAllAhp(); // AHP OVERALL
	if (industryType == "Industry") {
		isproduction = "Yes";
		
		//getProVsWaterGraph();
	} else {
		isproduction = "No";
		fuelConsumptionGraph();
	}
	analyticalGraph1();
	analyticalGraph2();
	
//	getWaterConsumptionGraph();
//	getSolidWasteGenerationGraphs();
//	getwasteWaterGenerationGraph();
//	getFuelConsumptionGraph();
//	getMonthlyEnvStatistics();
//	getSummary();
//	getStpEfficiency();
//	getEtpEfficiency();
});




function gaugeMeter(meterValue, appendId, appendReadingId) {
	var divId = "div1";
	var readingDiv = "div1-textfield1";
	var opts = {
		lines: 12, // The number of lines to draw
		angle: 0, // The length of each line
		lineWidth: 0.4, // The line thickness
		pointer: {
			length: 0.37, // The radius of the inner circle
			strokeWidth: 0.035, // The rotation offset
			color: '#000000' // Fill color
		},
		limitMax: 'false', // If true, the pointer will not go past the end of
		// the gauge
		colorStart: '#6FADCF', // Colors
		colorStop: '#8FC0DA', // just experiment with them
		strokeColor: '#E0E0E0', // to see which ones work best for you
		generateGradient: false,
		staticLabels: {
			font: "14px Arial", // Specifies font
			labels: [0, 11.11, 22.22, 33.33, 44.44, 55.55, 66.66, 77.77,
				88.88, 99.99, 100
			], // Print labels at these values
			color: "#000000", // Optional: Label text color
			fractionDigits: 0
			// Optional: Numerical precision. 0=round off.
		},
		staticZones: [{
			strokeStyle: "#FF0000",
			min: 0,
			max: 11.11
		}, {
			strokeStyle: "#ff3300",
			min: 11.11,
			max: 22.22
		}, {
			strokeStyle: "#ff6600",
			min: 22.22,
			max: 33.33
		}, {
			strokeStyle: "#ff9900",
			min: 33.33,
			max: 44.44
		}, {
			strokeStyle: "#ffcc00",
			min: 44.44,
			max: 55.55
		}, {
			strokeStyle: "#ffff00",
			min: 55.55,
			max: 66.66
		}, {
			strokeStyle: "#99FF00",
			min: 66.66,
			max: 77.77
		}, {
			strokeStyle: "#009900",
			min: 77.77,
			max: 88.88
		}, {
			strokeStyle: "#004d1a",
			min: 88.88,
			max: 100
		}, ],
	};
	var target = document.getElementById(appendId); // your canvas element
	var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
	gauge.maxValue = 100; // set max gauge value
	gauge.animationSpeed = 32; // set animation speed (32 is default value)
	gauge.set(meterValue); // set actual value
	gauge.setTextField(document.getElementById(appendReadingId));
}

function demo1() {
	var meterValue = 30;
	var divId = "div2";
	var readingDiv = "div2-textfield1";
	/*$("#"+divId).width(80);
    $("#"+divId).height(200);*/
	var opts = {
		lines: 12, // The number of lines to draw
		angle: 0, // The length of each line
		lineWidth: 0.4, // The line thickness
		pointer: {
			length: 0.37, // The radius of the inner circle
			strokeWidth: 0.035, // The rotation offset
			color: '#000000' // Fill color
		},
		limitMax: 'false', // If true, the pointer will not go past the end of
		// the gauge
		colorStart: '#6FADCF', // Colors
		colorStop: '#8FC0DA', // just experiment with them
		strokeColor: '#E0E0E0', // to see which ones work best for you
		generateGradient: false,
		staticLabels: {
			font: "14px Arial", // Specifies font
			labels: [0, 11.11, 22.22, 33.33, 44.44, 55.55, 66.66, 77.77,
				88.88, 99.99, 100
			], // Print labels at these values
			color: "#000000", // Optional: Label text color
			fractionDigits: 0
			// Optional: Numerical precision. 0=round off.
		},
		staticZones: [{
			strokeStyle: "#FF0000",
			min: 0,
			max: 11.11
		}, {
			strokeStyle: "#ff3300",
			min: 11.11,
			max: 22.22
		}, {
			strokeStyle: "#ff6600",
			min: 22.22,
			max: 33.33
		}, {
			strokeStyle: "#ff9900",
			min: 33.33,
			max: 44.44
		}, {
			strokeStyle: "#ffcc00",
			min: 44.44,
			max: 55.55
		}, {
			strokeStyle: "#ffff00",
			min: 55.55,
			max: 66.66
		}, {
			strokeStyle: "#99FF00",
			min: 66.66,
			max: 77.77
		}, {
			strokeStyle: "#009900",
			min: 77.77,
			max: 88.88
		}, {
			strokeStyle: "#004d1a",
			min: 88.88,
			max: 100
		}, ],
	};
	var target = document.getElementById(divId); // your canvas element
	var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
	gauge.maxValue = 100; // set max gauge value
	gauge.animationSpeed = 32; // set animation speed (32 is default value)
	gauge.set(meterValue); // set actual value
	gauge.setTextField(document.getElementById(readingDiv));
}

function reverseGaugeMeter(meterValue, appendId, appendReadingId) {
	var opts = {
		lines: 12, // The number of lines to draw
		angle: 0, // The length of each line
		lineWidth: 0.4, // The line thickness
		pointer: {
			length: 0.37, // The radius of the inner circle
			strokeWidth: 0.035, // The rotation offset
			color: '#000000' // Fill color
		},
		limitMax: 'false', // If true, the pointer will not go past the end of
		// the gauge
		colorStart: '#6FADCF', // Colors
		colorStop: '#8FC0DA', // just experiment with them
		strokeColor: '#E0E0E0', // to see which ones work best for you
		generateGradient: false,
		staticLabels: {
			font: "14px Arial", // Specifies font
			labels: [0, 11.11, 22.22, 33.33, 44.44, 55.55, 66.66, 77.77,
				88.88, 99.99, 100
			], // Print labels at these values
			color: "#000000", // Optional: Label text color
			fractionDigits: 0
			// Optional: Numerical precision. 0=round off.
		},
		staticZones: [{
			strokeStyle: "#004d1a",
			min: 0,
			max: 11.11
		}, {
			strokeStyle: "#009900",
			min: 11.11,
			max: 22.22
		}, {
			strokeStyle: "#99FF00",
			min: 22.22,
			max: 33.33
		}, {
			strokeStyle: "#ffff00",
			min: 33.33,
			max: 44.44
		}, {
			strokeStyle: "#ffcc00",
			min: 44.44,
			max: 55.55
		}, {
			strokeStyle: "#ff9900",
			min: 55.55,
			max: 66.66
		}, {
			strokeStyle: "#ff6600",
			min: 66.66,
			max: 77.77
		}, {
			strokeStyle: "#ff3300",
			min: 77.77,
			max: 88.88
		}, {
			strokeStyle: "#FF0000",
			min: 88.88,
			max: 100
		}, ],
	};
	var target = document.getElementById(appendId); // your canvas element
	var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
	gauge.maxValue = 100; // set max gauge value
	gauge.animationSpeed = 32; // set animation speed (32 is default value)
	gauge.set(meterValue); // set actual value
	gauge.setTextField(document.getElementById(appendReadingId));
}

function demo3() {
	var meterValue = 30;
	var divId = "div4";
	var readingDiv = "div4-textfield1";
	/*$("#"+divId).width(80);
    $("#"+divId).height(200);*/
	var opts = {
		lines: 12, // The number of lines to draw
		angle: 0, // The length of each line
		lineWidth: 0.4, // The line thickness
		pointer: {
			length: 0.37, // The radius of the inner circle
			strokeWidth: 0.035, // The rotation offset
			color: '#000000' // Fill color
		},
		limitMax: 'false', // If true, the pointer will not go past the end of
		// the gauge
		colorStart: '#6FADCF', // Colors
		colorStop: '#8FC0DA', // just experiment with them
		strokeColor: '#E0E0E0', // to see which ones work best for you
		generateGradient: false,
		staticLabels: {
			font: "14px Arial", // Specifies font
			labels: [0, 11.11, 22.22, 33.33, 44.44, 55.55, 66.66, 77.77,
				88.88, 99.99, 100
			], // Print labels at these values
			color: "#000000", // Optional: Label text color
			fractionDigits: 0
			// Optional: Numerical precision. 0=round off.
		},
		staticZones: [{
			strokeStyle: "#FF0000",
			min: 0,
			max: 11.11
		}, {
			strokeStyle: "#ff3300",
			min: 11.11,
			max: 22.22
		}, {
			strokeStyle: "#ff6600",
			min: 22.22,
			max: 33.33
		}, {
			strokeStyle: "#ff9900",
			min: 33.33,
			max: 44.44
		}, {
			strokeStyle: "#ffcc00",
			min: 44.44,
			max: 55.55
		}, {
			strokeStyle: "#ffff00",
			min: 55.55,
			max: 66.66
		}, {
			strokeStyle: "#99FF00",
			min: 66.66,
			max: 77.77
		}, {
			strokeStyle: "#009900",
			min: 77.77,
			max: 88.88
		}, {
			strokeStyle: "#004d1a",
			min: 88.88,
			max: 100
		}, ],
	};
	var target = document.getElementById(divId); // your canvas element
	var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
	gauge.maxValue = 100; // set max gauge value
	gauge.animationSpeed = 32; // set animation speed (32 is default value)
	gauge.set(meterValue); // set actual value
	gauge.setTextField(document.getElementById(readingDiv));
}

function sectoralDataQualityPerformance() {}

function getOverAllDailyDataQuality() {
	var today_date = todayDate();
	var prev_date = previousDate(today_date);
	var finalValue = 0,
		finalValue1 = 0,
		total = 0;
	var status = false;
	$.ajax({
		type: 'POST',
		url: "ajax-overAllDailyDataMgmt",
		success: function (data, textStatus, xhr) {
		  $.each(data, function (index, element) {
        var name = element.name;
        var quality = element.quality;
        
        if (name != "overAll" && name != "overAllOld") {
          var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 offset-lg-4 widget-pie__item grey lighten-4'>" + "<div class='easy-pie-chart chart" + index + "' id='easyPie_" + index + "' data-percent='" + quality + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" + "<span class='easy-pie-chart__value'>" + quality + "</span>" + "</div>" + "<div class='widget-pie__title text-black'>" + name + " - " + getMeterPerformance(quality) + "</div>" + "</div>")
            $("#append_quality_performance").append(msg_data);
            var sectorialColor = getMeterColor(quality);
            var idd = "easyPie_" + index;
            makeEasyPieChart(idd, sectorialColor);
          }
        if (name == "overAll") {
          newOverAllPercentage = quality;
          gaugeMeter(quality, "overAllDataQuality", "overAllDataQuality-text");
        }
        if (name == "overAllOld") {
          oldOverAllPercentage = quality;
          setOldData(newOverAllPercentage, oldOverAllPercentage, "overAll-state");
        }
		  });
		},
		async: false
	});
}

function setOldData(newOverAllPercentage, oldOverAllPercentage, id) {
	var colorClass = "text-success";
	var iconClass = "zmdi zmdi-trending-up";
	var oldDateTitle = meterOldDateTitle();
	var diffrence = (newOverAllPercentage - oldOverAllPercentage).toFixed(2);
	if (diffrence < 0) {
		colorClass = "text-danger";
		iconClass = "zmdi zmdi-trending-down";
	}
	var html = $("<span class='mantooltip hover' data-jbox-title='' data-jbox-content='" + oldDateTitle + "'><i class='" + iconClass + " zmdi-hc-lg " + colorClass + "'></i>" + "<span class='" + colorClass + "'> (" + diffrence + " %)</span></span>");
	var oldDateHtml = meterCurrentDateTitle();
	$("#" + id).append(html);
	makeToolTip();
}

function getOverAllConformingPerformance() {
	var finalValue = 0,
		finalValue1 = 0,
		total = 0;
	$.ajax({
		type: 'GET',
		url: "ajax-overAllEnvPerformance",
		success: function (data) {
			var newOverAllPercentage = 0;
			var oldOverAllPercentage = 0;
			var parseData = JSON.parse(data);
			$.each(parseData, function (index, element) {
				var name = element.name;
				var quality = element.quality;
				if (name != "overAll" && name != "overAllOld") {
					var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 offset-lg-4 widget-pie__item grey lighten-4'>" + "<div class='easy-pie-chart chart" + index + "' id='easyPie1_" + index + "' data-percent='" + quality + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" + "<span class='easy-pie-chart__value'>" + quality + "</span>" + "</div>" + "<div class='widget-pie__title text-black'>" + name + " - " + getMeterPerformance(quality) + "</div>" + "</div>")
					$("#append_non_conformance_performance").append(msg_data);
					var sectorialColor = getMeterColor(quality);
					var idd = "easyPie1_" + index;
					makeEasyPieChart(idd, sectorialColor);
				}
				if (name == "overAll") {
					newOverAllPercentage = quality;
					gaugeMeter(quality, "overAllDataCompliance", "overAllDataCompliance-text");
				}
				if (name == "overAllOld") {
					oldOverAllPercentage = quality;
					setOldData(newOverAllPercentage, oldOverAllPercentage, "overAllCompliance-state");
				}
			});
		},
		async: false
	});
	/*finalValue1 = finalValue1 / total;
	finalValue1 = finalValue1.toFixed(2);
	reason = "% of non-conformance: " + finalValue1;
	parameter = "Overall Non-conformance";
	p_id = "s";
	reversemeter(finalValue1, "div2","preview-textfield2","preview-textfield12");*/
	//setPerformanceMain(finalValue1, p_id, parameter, reason, 0);
}

function getEnvQualityDataWater() {
	$.ajax({
		type: 'POST',
		url: 'ajax-getWaterPerformancetest',
		dataType: 'json',
		success: function (data) {
			var data = JSON.parse(data);
			if (data.length != 0) {
				$.each(data, function (index, element) {
					treatmentType = element.meterType;
					if (treatmentType == "combine") {
						finalCombine = element.finalCombinedValue;
						reverseGaugeMeter(finalCombine, "waterTreatmentGaugeMeter", "waterTreatmentGaugeMeter-text");
					}
					/*if (treatmentType == "ETP") {
						finalEtp = (element.finalEtpValue).toFixed(2);
						meterName = "ETP";
						setFilter(meterName, finalEtp, "performanceStat_ETP");
					} else if (treatmentType == "STP") {
						finalStp = (element.finalStpValue).toFixed(2);
						meterName = "STP";
						setFilter(meterName, finalStp, "performanceStat_STP");
					}*/
				});
			} else {
				$("#noDataWaterPerformance").html("No");
			}
		},
		error: function (dd) {
			alert("error" + dd);
		}
	});
}

function getEnvQualityDataAir() {
	var fwd_url = "ajax-overAllEnvPerformanceAir";
	$.ajax({
		type: 'POST',
		url: fwd_url,
		dataType: 'json',
		success: function (data) {},
		error: function (data) {
			alert("error" + data);
		}
	});
}

function getEnvQualityDataHz() {
	var finalValue = 0,
		finalValue1 = 0,
		total = 0;
	var fwd_url = "ajax-overAllEnvPerformanceHz";
	$.ajax({
		type: 'POST',
		url: fwd_url,
		dataType: 'json',
		success: function (data) {},
		error: function (data) {
			alert("error" + data);
		}
	});
	//setPerformanceMain(finalValue1, p_id, parameter, reason, 0);
}

function getOverAllAhp() {
	var fwd_url = "ajax-overAllEnvPerformanceAllFinal";
	$.ajax({
		type: 'POST',
		url: fwd_url,
		dataType: 'json',
		success: function (data) {
			reverseGaugeMeter(data, "overAllEnvPerformance", "overAllEnvPerformance-text");
		},
		error: function (dd) {
			alert("error" + dd);
		}
	});
}

function getOverAllProductionConpliance() {
	var finalValue = 0,
		finalValue1 = 0,
		total = 0;
	var status = false;
	$.ajax({
		type: 'GET',
		url: "ajax-overAllEnvPerformance",
		success: function (data) {
			var newOverAllCompliancePercentage = 0;
			var oldOverAllCompliancePercentage = 0;
			var parseData = JSON.parse(data);
			$.each(parseData, function (index, element) {
				var name = element.name;
				var quality = element.quality;
				if (name != "overAll" && name != "overAllOld") {
					var msg_data = $("<div class='col-6 col-sm-4 col-md-6 col-lg-2 offset-lg-4 widget-pie__item grey lighten-4'>" + "<div class='easy-pie-chart chart" + index + "' id='easyPie_" + index + "' data-percent='" + quality + "' data-size='100' data-track-color='rgba(0,0,0,0.08)'>" + "<span class='easy-pie-chart__value'>" + quality + "</span>" + "</div>" + "<div class='widget-pie__title text-black'>" + name + " - " + getMeterPerformance(quality) + "</div>" + "</div>")
					$("#append_quality_performance").append(msg_data);
					var sectorialColor = getMeterColor(quality);
					var idd = "easyPie_" + index;
					makeEasyPieChart(idd, sectorialColor);
				}
				if (name == "overAll") {
					newOverAllCompliancePercentage = quality;
					gaugeMeter(quality, "overAllDataQuality", "overAllDataQuality-text");
				}
				if (name == "overAllOld") {
					oldOverAllCompliancePercentage = quality;
					setOldData(newOverAllCompliancePercentage, oldOverAllCompliancePercentage, "overAll-state");
				}
			});
		},
		async: false
	});
}

function getProVsWaterGraph() {
  dontBlock = true;
  var graphTitle = "Production VS Water";
  $.ajax({
    type: 'POST',
    url: "ajax-producstionvswater",
    dataType: 'json',
    beforeSend: function () {
      $("#chart_div").html(graph_loader);
    },
    success: function (data) {
      var units= "CMD";
      makeCommonEchart("chart_div", graphTitle, units, data, "", "zoomOption", "nodraggable","normal","singleLineMultiBar","");
      dontBlock = false;
     
    },
    error: function (xhr, type) {
      alert('Something went wrong');
    },
    async: true
  });
}

function getWaterConsumptionGraph() {
  dontBlock = true;
  $.ajax({
    type: 'POST',
    url: "ajax-WaterConsumptionGraph",
    dataType: 'json',
    beforeSend: function () {
      $("#chart_div1").html(graph_loader);
    },
    success: function (data) {
      var graphTitle = "Water Consumption";
      var units= "CMD";
      makeCommonEchart("chart_div1", graphTitle, units, data, "", "zoomOption", "nodraggable","normal","singleLineMultiBar","");
      dontBlock = false;
    },
    error: function (xhr, type) {
      if (pdata == "source") {
        $('#chart_div1').html("<center><img src='img/nodata1.png' style='width:338px'></center>");
      } else if (pdata == "waste_water") {
        $('#chart_div2').html("<center><img src='img/nodata1.png' style='width:338px'></center>");
      }
    },
    async: true
  });
}

function getSolidWasteGenerationGraphs() {
  dontBlock = true;
  $.ajax({
    type: 'GET',
    url: "ajax-solidWateGraph?name=Solid",
    dataType: 'json',
    beforeSend: function () {
      $("#chart_div2").html(graph_loader);
    },
    success: function (data) {
      var graphTitle = "Solid Waste Generation";
      var units= "Values";
      makeCommonEchart("chart_div2", graphTitle, units, data, "", "zoomOption", "nodraggable","normal","multiLineMultiBarGraph","");
      
      dontBlock = false;
    },
    error: function (xhr, type) {
      $('#chart_div2').html("<center><img src='img/nodata1.png' style='width:338px'></center>");
    },
    async: true
  });
}

function getwasteWaterGenerationGraph() {
  dontBlock = true;
  $.ajax({
    type: 'POST',
    url: "ajax-WaterWasteGraph",
    dataType: 'json',
    beforeSend: function () {
      $("#chart_div2").html(graph_loader);
    },
    success: function (data) {
    	if (data.length != 0) {
            var graphTitle = "Waste Water Generation";
            var units= "CMD";
            makeCommonEchart("chart_div3", graphTitle, units, data, "", "zoomOption", "nodraggable","normal","singleLineMultiBar","");
          } else {
            $("#chart_div3").html(no_data);
          }
          dontBlock = false;
    },
    error: function (xhr, type) {
      $('#chart_div2').html("<center><img src='img/nodata1.png' style='width:338px'></center>");
    },
    async: true
  });
}

function getFuelConsumptionGraph(){
  dontBlock = true;
  $.ajax({
    type : 'GET',
    url : "ajax-solidWateGraph",
    dataType : 'json',
    data: ({
      name : 'Fuel'
      }),
    beforeSend: function() {
      $("#chart_div4").html(graph_loader);
    },
    success : function(data) {
      
      var graphTitle = "Fuel Consumption";
      var units= "Values";
      makeCommonEchart("chart_div4", graphTitle, units, data, "", "zoomOption", "nodraggable","normal","multiLineMultiBarGraph","");
      
      dontBlock = false;
    },
    error : function(xhr, type) {
      },async : true
    });
}
function getMonthlyEnvStatistics(){
	var today_date = todayDate();
	// previous date << 30 days 
	var prev_date = previousDate(today_date);
	
	var fwd_url = "ajax-getMonthlyEnvStatistics";
		$.ajax({
			type : 'POST',
			url : fwd_url,
			data : ({
				today : today_date,
				prevdate : prev_date
			}),

			success : function(data) {
				var parseData = JSON.parse(data);
				if (parseData.length > 0) {
					$.each(parseData, function(index, element) {
						var type = element.type;
						var productname = element.product_name;
						var unit = element.unit;
						var avgquantity = element.avgquantity;
						if (type != "waterConsumption") {
							if(type == "product" || type == "byproduct"){
								var msg = "Production";
							} else if (type == "fuel" || type == "raw"){
								var msg = "Consumption";
							} else {
								var msg = "Generation";
							}
							
							var div = $("<p class='stat1 text-darkbrown'>Average Daily "+msg+" of "+productname+" = <b>"+avgquantity+" "+unit+"</b>.</p>")
						} else {
							var div = $("<p class='stat1 text-darkbrown'>Average Daily Water Consumed = <b>"+avgquantity+" CMD </b>.</p>")
						}
					$("#"+type+"_MonthlyStat").append(div); 
					});
				}
				
		},
		error : function(xhr, type) {
		alert('server error occoured at getMonthlyEnvStatistics');
	},
		async: true
	});
}
function getSummary(){
	
	var today_date = todayDate();
	// previous date << 30 days 
	var prev_date = previousDate(today_date);
	
	var fwd_url = "ajax-getSummary";
	
	var resourcesList = [ "product", "byproduct", "raw", "fuel", "hwp","hwpcf", "nhwp", "nhwpcf"];
	
	for(var i = 0; i < resourcesList.length; i++){
		$.ajax({
			type : 'POST',
			url : fwd_url,
			data : ({
				type : resourcesList[i],
				today : today_date,
				prevdate : prev_date
			}),

			success : function(data) {
				var parseData = JSON.parse(data);
				$('#'+resourcesList[i]+'_summary').empty(); //Clear div when new load
				var div = "";
				var iddd = resourcesList[i]+"_summary";
				$.each(parseData, function(index, element) { 
					var productName = element.productName;
					var noncount = element.noncount;
					var remark = element.remark;
					var markup = "<tr><td>"+productName+"</td><td>"+noncount+"</td><td>"+remark+"</td></tr>";
					$("#"+iddd).append(markup); 

				});
				
		},
		error : function(xhr, type) {
		alert('server error occoured at getSummary');
	},
		async: false
	});
		
	}
}
function todayDate() {
	var today = new Date();
	x = today.getMonth() + 1;
	y = today.getDate();
	if (x < 10) {
		x = "0" + x;
	}
	if (y < 10) {
		y = "0" + y;
	}
	return today.getFullYear() + '-' + x + '-' + y;
}

function previousDate(date) {
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
	return c.getFullYear() + '-' + x + '-' + y;
}

function previousDateByDayss(date, dayss) {
	var c = new Date(date);
	c.setDate(c.getDate() - dayss);
	x = c.getMonth() + 1;
	y = c.getDate();
	if (x < 10) {
		x = "0" + x;
	}
	if (y < 10) {
		y = "0" + y;
	}
	return c.getFullYear() + '-' + x + '-' + y;
}

function meterOldDateTitle() {
	var date1 = todayDate()
	var date2 = previousDateByDayss(date1, 30);
	var date3 = previousDateByDayss(date2, 30);
	var str = "<small>" + date3 + " to " + date2 + "</small>";
	return str;
}

function meterCurrentDateTitle() {
	var date1 = todayDate()
	var date2 = previousDateByDayss(date1, 30);
	var str = "<small>" + date2 + " to " + date1 + "</small>";
	return str;
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


function getStpEfficiency(){
	var fwd_url = "ajax-getStpEfficiency";
		$.ajax({
			type : 'POST',
			url : fwd_url,
			success : function(data) {
				var parseData = JSON.parse(data);
				var i =0;
				if (parseData != 0) {
					activeEfficiency++;
					 $('#efficiencyAll').css('display', 'block');
					 var htmlLi = '<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#EfficiencyStp" role="tab">Efficiency of STP</a></li>';
					 $("#appendLi").append(htmlLi);
					 var htmlContent = '<div class="tab-pane active fade show" id="EfficiencyStp" role="tabpanel"><center>%EFFICIENCY OF POLLUTANT REMOVAL</center><div id="append-Efficiency-STP"></div></div>';
					 $("#appendContent").append(htmlContent);
					 $.each(parseData, function(index, element) {
						i++;
						var date= element.date;
						var html = "<h2><a href='#'>"+date+"</a></h2><div id='STP"+i+"'></div>";
						$("#append-Efficiency-STP").append(html);
							var pollList= element.data;
							var stpData="";
							for (const [key, value] of pollList.entries()) {
								var labelName = value.label;
								stpData += "<h2><a href='#'>"+labelName+"</a></h2><div>"
											+"<div class='card-body'>"
											+"<center><h4 class='font-weight-bold'>TEST PARAMETERS</h4></center>"
											+"<table class='table table-bordered normal'>"
											+"<thead><tr><th>SR. NO.</th><th>PARAMETER NAME</th><th>INLET (Conc.)</th><th>OUTLET (Conc.)</th><th>Efficiency</th><th>UNIT</th></tr></thead>"
											+"<tbody>"
											var pollData = value.polldata;
											var count =1;
											for(const[key1,value1] of pollData.entries()){
												var name = value1.pollname;
												var html2 = "<tr><td>" + count + "</td>"
															+"<td>" + value1.pollname + "</td>"
															+"<td>" + value1.incons +"</td>"
															+"<td>"+value1.outcons+"</td>"
															+"<td>"+value1.efficiency+"</td>"
															+"<td>"+value1.unit+"</td>"
															+"</tr>"
												count++;
												stpData += html2;
											}
								stpData += "</tbody><table></div>"
											+"</div>";
							}
							$("#STP"+i).append(stpData);
							$(("#STP"+i)).accordion({heightStyle: 'content',collapsible: true});
					});
					$("#append-Efficiency-STP").accordion({heightStyle: 'content',collapsible: true});
				}
				
				
			},
		error : function(xhr, type) {
		alert('server error occoured at getStpEfficiency');
	},
		async: false
	});
}


function getEtpEfficiency(){
	var fwd_url = "ajax-getEtpEfficiency";
		$.ajax({
			type : 'POST',
			url : fwd_url,
			success : function(data) {
				var parseData = JSON.parse(data);
				var i =0;
				if (parseData.length != 0) {
					 $('#efficiencyAll').css('display', 'block');
					 if(activeEfficiency != 0){
						 var htmlContent = '<div class="tab-pane fade show" id="EfficiencyEtp" role="tabpanel"><center>%EFFICIENCY OF POLLUTANT REMOVAL</center><div id="append-Efficiency-ETP"></div></div>';
						 var htmlLi = '<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#EfficiencyEtp" role="tab">Efficiency of ETP</a></li>';
					 }else{
						 var htmlContent = '<div class="tab-pane active fade show" id="EfficiencyEtp" role="tabpanel"><center>%EFFICIENCY OF POLLUTANT REMOVAL</center><div id="append-Efficiency-ETP"></div></div>';
						 var htmlLi = '<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#EfficiencyEtp" role="tab">Efficiency of ETP</a></li>';
					 }
					 $("#appendLi").append(htmlLi);
					 $("#appendContent").append(htmlContent);
					$.each(parseData, function(index, element) {
						i++;
						var date= element.date;
						var html = "<h2><a href='#'>"+date+"</a></h2><div id='ETP"+i+"'></div>";
						$("#append-Efficiency-ETP").append(html);
							var pollList= element.data;
							for (const [key, value] of pollList.entries()) {
								var stpData="";
								var labelName = value.label;
								var html = "<h2><a href='#'>"+labelName+"</a></h2><div>"
											+"<div class='card-body'>"
											+"<center><h4 class='font-weight-bold'>TEST PARAMETERS</h4></center>"
											+"<table class='table table-bordered normal'>"
											+"<thead><tr><th>SR. NO.</th><th>PARAMETER NAME</th><th>INLET (Conc.)</th><th>OUTLET (Conc.)</th><th>Efficiency</th><th>UNIT</th></tr></thead>"
											+"<tbody>"
											var pollData = value.polldata;
											var count =1;
											for(const[key1,value1] of pollData.entries()){
												var name = value1.pollname;
												var html2 = "<tr><td>" + count + "</td>"
															+"<td>" + value1.pollname + "</td>"
															+"<td>" + value1.incons +"</td>"
															+"<td>"+value1.outcons+"</td>"
															+"<td>"+value1.efficiency+"</td>"
															+"<td>"+value1.unit+"</td>"
															+"</tr>"
												count++;
												html += html2;
											}
								html += "</tbody><table></div>"
											+"</div>";
								stpData += html;
								$("#ETP"+i).append(stpData);
							}
							$(("#ETP"+i)).accordion({heightStyle: 'content',collapsible: true});
					});
					$("#append-Efficiency-ETP").accordion({heightStyle: 'content',collapsible: true});
				}
				
			},
		error : function(xhr, type) {
		alert('server error occoured at getStpEfficiency');
	},
		async: false
	});
}

function analyticalGraph1() {
  dontBlock = true;
  $.ajax({
    type: 'POST',
    dataType: 'json',
    url: "ajax-analyticalGraph1",
    beforeSend: function () {
      $("#div_analyticalGraph2").html(graph_loader);
    },
    success: function (data) {
    	 var parseData = JSON.parse(data);
    	 var result = parseData.result;
    	 var jsonData = parseData.data;
    	 if(result == true){
    		 var graphTitle = jsonData.graphTitle;
    		 var unit = jsonData.unit;
    		 var graphData = jsonData.graphArray;
    		 
    		 makeCommonEchart("div_analyticalGraph1", graphTitle, unit, graphData, "", "zoomOption", "nodraggable","normal","singleLineMultiBar","");
    	 } else {
    		 $("#div_analyticalGraph2").html(no_data);
    	 }
      dontBlock = false;
     
    },
    error: function (xhr, type) {
    	jBoxBottomRightBigNotice("Error", "Something went wrong at : analyticalGraph1", "red","2000");
    },
    async: true
  });
}

function analyticalGraph2() {
	  dontBlock = true;
	  $.ajax({
	    type: 'POST',
	    dataType: 'json',
	    url: "ajax-analyticalGraph2",
	    beforeSend: function () {
	      $("#div_analyticalGraph2").html(graph_loader);
	    },
	    success: function (data) {
	    	 var parseData = JSON.parse(data);
	    	 var result = parseData.result;
	    	 var jsonData = parseData.data;
	    	 if(result == true){
	    		 var graphTitle = jsonData.graphTitle;
	    		 var unit = jsonData.unit;
	    		 var graphData = jsonData.graphArray;
	    		 
	    		 makeCommonEchart("div_analyticalGraph2", graphTitle, unit, graphData, "", "zoomOption", "nodraggable","normal","singleLineMultiBar","");
	    	 } else {
	    		 $("#div_analyticalGraph2").html(no_data);
	    	 }
	      dontBlock = false;
	     
	    },
	    error: function (xhr, type) {
	    	jBoxBottomRightBigNotice("Error", "Something went wrong at : analyticalGraph1", "red","2000");
	    },
	    async: true
	  });
	}
function analyticalGraph3() {
dontBlock = true;
$.ajax({
    type: 'POST',
    dataType: 'json',
    url: "ajax-analyticalGraph3",
    beforeSend: function() {
        $("#div_analyticalGraph3").html(graph_loader);
    },
    success: function(data) {
        var parseData = JSON.parse(data);
        var result = parseData.result;
        var jsonData = parseData.data;
        if (result == true) {
            var graphTitle = jsonData.graphTitle;
            var unit = jsonData.unit;
            var graphData = jsonData.graphArray;

            makeCommonEchart("div_analyticalGraph3", graphTitle, unit, graphData, "", "zoomOption", "nodraggable", "normal", "onlyBar", "");
        } else {
            $("#chart_div").html(no_data);
        }
        dontBlock = false;

    },
    error: function(xhr, type) {
        jBoxBottomRightBigNotice("Error", "Something went wrong at : analyticalGraph3", "red", "2000");
    },
    async: true
});
 
}