var lastResourceType = "pw";
var lastGraphTabName = "DailyTab";

var graph_loader ="<center><img src='../newAssets/loaders/graph-loader.svg' height='500px' width='500px'></center>";
var no_data = "<center><img src='../newAssets/img/nodata1.png' style='width:500px'></center>";

$(document).ready(function() {
	var todayDate = todayDateValue();
	$("#startDate").val(todayDate);
	createGraph(lastResourceType,todayDate,"DailyTab","");
});


function createGraph(lastResourceType,graphDate,graphTabName,unit){
	$("#lastResourceType").val(lastResourceType);
	
	var fwd_url = "";
	if(lastResourceType == "pw"){
		
		fwd_url = "ajax-getProductionVSWaterGraph?graphTabName="+graphTabName+"&graphDate="+graphDate;
		
	} else if(lastResourceType == "Product" || lastResourceType == "Byproduct" || lastResourceType =="Raw Material" || lastResourceType=="Fuel"
		||lastResourceType=="hwp"||lastResourceType=="hwpcf"||lastResourceType=="nhwp"||lastResourceType=="nhwpcf"){
		
		fwd_url = "ajax-getResourcesGraph?graphTabName="+graphTabName+"&graphDate="+graphDate+"&graphUnit="+unit+"&graphResourceType="+lastResourceType;
	}
	else if(lastResourceType == "swr" || lastResourceType == "fr" || lastResourceType == "fru" || lastResourceType == "fwur" || 
			lastResourceType == "swur" || lastResourceType == "tpr" || lastResourceType == "pfw"){
		fwd_url = "ajax-getWaterData?graphTabName="+graphTabName+"&graphDate="+graphDate+"&graphUnit="+unit+"&graphResourceType="+lastResourceType
	}

	makeTabTitle(graphDate,"graph_for_title",graphTabName);
	if (graphTabName == "DailyTab") {
    var graph_loading_id = "chart_div";
  } else if (graphTabName == "Back7Days") {
    var graph_loading_id = "chart_div1";
  } else if (graphTabName == "Weekly") {
    var graph_loading_id = "chart_div2";
  } else if (graphTabName == "Monthly") {
    var graph_loading_id = "chart_div3";
  } else if (graphTabName == "Yearly") {
    var graph_loading_id = "chart_div4";
  }
    
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'Json',
		beforeSend: function () {
      $("#"+graph_loading_id).html(graph_loader);
    },
		success : function(data) {
			if(graphTabName == "DailyTab"){
				var canvasId = "chart_div";
				
				if(lastResourceType == "pw"){
					
					var graph_title = "Production VS Water Consumption ";
	                makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","singleLineMultiBar","");
	                
				} else if(lastResourceType == "Product" || lastResourceType == "Byproduct" || lastResourceType =="Raw Material" || lastResourceType=="Fuel"
					||lastResourceType=="hwp"||lastResourceType=="hwpcf"||lastResourceType=="nhwp"||lastResourceType=="nhwpcf"){
					
					var graph_title = lastResourceType +" data in "+unit;
					makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
				}
				else if(lastResourceType == "swr" || lastResourceType == "fr" || lastResourceType == "fru" || lastResourceType == "fwur" || 
						lastResourceType == "swur" || lastResourceType == "tpr" || lastResourceType == "pfw"){
					var graph_title = lastResourceType +" data in CMD";
					if(lastResourceType == "tpr"){
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
					}else{
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","onlyLine","");
					}
				}
			}
			else if(graphTabName== "Back7Days"){
				var canvasId = "chart_div1";
				
				if(lastResourceType == "pw"){
					
					var graph_title = "Production VS Water Consumption ";
	                makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","singleLineMultiBar","");
	                
				} else if(lastResourceType == "Product" || lastResourceType == "Byproduct" || lastResourceType =="Raw Material" || lastResourceType=="Fuel"
					||lastResourceType=="hwp"||lastResourceType=="hwpcf"||lastResourceType=="nhwp"||lastResourceType=="nhwpcf"){
					
					var graph_title = lastResourceType +" data in "+unit;
					makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
				}
				else if(lastResourceType == "swr" || lastResourceType == "fr" || lastResourceType == "fru" || lastResourceType == "fwur" || 
						lastResourceType == "swur" || lastResourceType == "tpr" || lastResourceType == "pfw"){
					var graph_title = lastResourceType +" data in CMD";
					if(lastResourceType == "tpr"){
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
					}else{
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","onlyLine","");
					}
				}
			}
			else if(graphTabName== "Weekly"){
				var canvasId = "chart_div2";
				
				if(lastResourceType == "pw"){
					
					var graph_title = "Production VS Water Consumption ";
	                makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","singleLineMultiBar","");
	                
				} else if(lastResourceType == "Product" || lastResourceType == "Byproduct" || lastResourceType =="Raw Material" || lastResourceType=="Fuel"
					||lastResourceType=="hwp"||lastResourceType=="hwpcf"||lastResourceType=="nhwp"||lastResourceType=="nhwpcf"){
					
					var graph_title = lastResourceType +" data in "+unit;
					makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
				}
				else if(lastResourceType == "swr" || lastResourceType == "fr" || lastResourceType == "fru" || lastResourceType == "fwur" || 
						lastResourceType == "swur" || lastResourceType == "tpr" || lastResourceType == "pfw"){
					var graph_title = lastResourceType +" data in CMD";
					if(lastResourceType == "tpr"){
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
					}else{
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","onlyLine","");
					}
				}
			}
			else if(graphTabName== "Monthly"){
				var canvasId = "chart_div3";
				
				if(lastResourceType == "pw"){
					
					var graph_title = "Production VS Water Consumption ";
	                makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","singleLineMultiBar","");
	                
				} else if(lastResourceType == "Product" || lastResourceType == "Byproduct" || lastResourceType =="Raw Material" || lastResourceType=="Fuel"
					||lastResourceType=="hwp"||lastResourceType=="hwpcf"||lastResourceType=="nhwp"||lastResourceType=="nhwpcf"){
					
					var graph_title = lastResourceType +" data in "+unit;
					makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
				}
				else if(lastResourceType == "swr" || lastResourceType == "fr" || lastResourceType == "fru" || lastResourceType == "fwur" || 
						lastResourceType == "swur" || lastResourceType == "tpr" || lastResourceType == "pfw"){
					var graph_title = lastResourceType +" data in CMD";
					if(lastResourceType == "tpr"){
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
					}else{
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","onlyLine","");
					}
				}
			}
			else if(graphTabName== "Yearly"){
				var canvasId = "chart_div4";
				
				if(lastResourceType == "pw"){
					
					var graph_title = "Production VS Water Consumption ";
	                makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","singleLineMultiBar","");
	                
				} else if(lastResourceType == "Product" || lastResourceType == "Byproduct" || lastResourceType =="Raw Material" || lastResourceType=="Fuel"
					||lastResourceType=="hwp"||lastResourceType=="hwpcf"||lastResourceType=="nhwp"||lastResourceType=="nhwpcf"){
					
					var graph_title = lastResourceType +" data in "+unit;
					makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
				}
				else if(lastResourceType == "swr" || lastResourceType == "fr" || lastResourceType == "fru" || lastResourceType == "fwur" || 
						lastResourceType == "swur" || lastResourceType == "tpr" || lastResourceType == "pfw"){
					var graph_title = lastResourceType +" data in CMD";
					if(lastResourceType == "tpr"){
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","multiLineMultiBarGraph","");
					}else{
						makeCommonEchart(canvasId, graph_title, "values", data, "", "InnerZoomEnabled", "nodraggable","advanced","onlyLine","");
					}
				}
			}
		},
		error : function(xhr, type) {
			
		}
	});
}

function createSideGraph(typee,unit){
	$("#lastResourceType").val(typee);
	$("#lastResourceTypeUnit").val(unit);
	
	var graphDate = $("#startDate").val();
	var graphTabName= $("#lastGraphTabName").val();
	
	createGraph(typee,graphDate,graphTabName,unit);
}

// this function called on day change -->> select date year..month..day....
function makeOnChangeGraph(graphTabName){
	
	var yy = $("#yyear"+graphTabName).val();
	var mm = $("#mmonth"+graphTabName).val();
	var dd = $("#add_days"+graphTabName).val();
	var graphDate;
	if(graphTabName=="DailyTab"||graphTabName=="Back7Days"){
		graphDate= yy+"-"+mm+"-"+dd;
	}
	else if(graphTabName=="Weekly"){
		graphDate= yy+"-"+mm+"-01";
	}
	else if(graphTabName=="Monthly"){
		graphDate= yy+"-01-01";
	}
	else if(graphTabName=="Yearly"){
		graphDate= yy+"-01-01";
	}
	console.log(graphDate);
	
	lastResourceType = $("#lastResourceType").val();
	lastResourceTypeUnit = $("#lastResourceTypeUnit").val();
	createGraph(lastResourceType,graphDate,graphTabName,lastResourceTypeUnit);
	
	
}

function makeTabTitle(date,appendId,graphTabName){
	$("#"+appendId).empty();
	$("#startDate").val(date);
	//if(graphTabName == "DailyTab"){
		var str = "<small>graph for "+date+"</small>";
		$("#"+appendId).append(str);
	//}
}

function openTab(type,unit,tabValue){
	lastResourceType = type;
	var graphTitle = "";
	$("#lastGraphTabName").val(tabValue);
	var graphDate = $("#startDate").val();
	lastResourceType = $("#lastResourceType").val();
	lastResourceTypeUnit = $("#lastResourceTypeUnit").val();
	createGraph(lastResourceType,graphDate,tabValue,lastResourceTypeUnit);
}