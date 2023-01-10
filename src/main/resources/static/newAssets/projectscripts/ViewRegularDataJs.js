var no_data = "<center><img src='../newAssets/img/nodata1.png' style='width:338px'></center>";
function openDaysRegular(id,id1){
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


$(document).ready(function () {
	  var d = new Date();
	  var dd = d.getDate();
	  if(dd < 10){
			dd = "0"+dd;
		}
	  var mm = d.getMonth()+1; //January is 01
	  if(mm < 10){
			mm = "0"+mm;
		}
	  var yyyy = d.getFullYear();
	  var today_date = yyyy+"-"+mm+"-"+dd;
	   $("#newDate").val(today_date);
	   console.log(today_date);
	   	getTitle(today_date, "str");
	    dailyDataStat();
	    dailyDataStatAir();
		dailyDataStatWater();
});



function onDaySetGetDataRegular(id, dateValue){
	var yy="", mm="", dd="";
	 yy = document.getElementById('yyear'+id).value;
	 mm = document.getElementById('mmonth'+id).value;
	 dd = document.getElementById('add_days'+id).value;
	today = yy+"-"+mm+"-"+dd;
	
		date = today;

			getTitle(today, "str");
			dailyDataStat();
			dailyDataStatAir();
			dailyDataStatWater();
		
//	$("#mmonth"+id).prop('selectedIndex',0);
//	$("#add_days"+id).prop('selectedIndex',0);
}

function getTitle(date, divId){
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
	$("#prevDate").val(prev_date);$("#newDate").val(date);
	
	var today_date = $("#newDate").val();
	var str = " <small> This data for this date : " + today_date+" </small>";
	document.getElementById(divId).innerHTML = str;
}
function dailyDataStat(){
	var today_date = $("#newDate").val();
	
	fwd_url="ajax-get-each-regular-data";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			pdata : today_date,
		}),
		success : function(data) {
			var x = document.getElementById("dataProduct").childElementCount;
			if(x > 0){
				$( "#dataProduct" ).accordion( "destroy" );
				$( "#dataAir" ).accordion( "destroy" );$( "#dataSoild" ).accordion( "destroy" );
				
			}
			$("#dataProduct").empty();$("#dataAir").empty();$("#dataSoild").empty();
			
			var data1 = JSON.parse(data);
			 var objSize = Object.keys(data1).length;
			
			 for(var i = 0 ;i<objSize;i++){
				 var dataArray = data1[i];
				 var allDataArray = new Array();
				var pType = "";
				
				
				 Object.keys(dataArray).forEach(function(key) {
					 allDataArray = dataArray[key];
					 pType = key;
					});
				if(pType == "hwp"){
					var PType ="Hazardous Waste from Process";
				}else if(pType == "hwpcf"){
					var PType ="Hazardous Waste from Pollution Control Facility";
				}else if(pType == "nhwp"){
					var PType ="Non Hazardous Waste from Process";
				}else if(pType == "nhwpcf"){
					var PType ="Non Hazardous Waste from Pollution Control Facility";
				}else{
					var PType = pType;
				}
				 var html1 = "<h3><a href='#'>" + PType  + "</a></h3>" +
					"<div><div class='card-body'>"+
					"<table class='table table-bordered'> <thead> <tr> <th>SR. NO.</th> <th>Product Name</th> <th>Quantity</th> <th>Date</th> <th>Edit</th> </tr> </thead> " +
					"<tbody>";
				 var j = 1;
				  $.each(allDataArray, function (index, element) {
					  
					var itemName = element.ProductName;
					var cQuantity =  element.RQuantity;
					var inpDate = element.InputDate;
					var regId = element.regId;
					var pUnit = element.productUnit;
					//var regId = element
					html1 +=  "<tr> <td>"+ j +"</td> <td>"+ itemName +"</td> <td>" + cQuantity + "</td> <td>"+ inpDate +"</td> <td class='p-2'><button type='button' onclick='modifiedDataPOPUp(this,\""+itemName+"\",\""+cQuantity+"\",\""+inpDate+"\",\""+regId+"\",\""+pUnit+"\")' class='btn btn-primary btn-sm p-1'>Edit</button> </td> </tr>";
		 			j++; 
				  });
				 
				 var html3 = "</tbody></table></div></div>";
				
				 if(pType == "Raw Material" || pType == "Product" || pType == "ByProduct"){
					 $("#dataProduct").append(html1  + html3);
					
				 }else if(pType == "Fuel"){
					 $("#dataAir").append(html1  + html3);
				 }else if(pType == "nhwp"  || pType == "hwpcf" || pType == "nhwpcf" || pType == "hwp"){
					 $("#dataSoild").append(html1  + html3);
				 }
				 
			 }
			 var size=data1.length;
			 if(size > 0 ){
				 $("#dataProduct").accordion({heightStyle: 'content',collapsible: true});
				 $("#dataSoild").accordion({heightStyle: 'content',collapsible: true});
				 $("#dataAir").accordion({heightStyle: 'content',collapsible: true});
			 }
			
			
			
		},
		error : function(xhr, type) {
			//$("." + error_div).html("<center><img src='img/nodata1.png' style='width:338px'></center>");
		},
		async : false
	});
}
function dailyDataStatAir(){
var today_date = $("#newDate").val();
	
	fwd_url="ajax-get-each-regular-dataAir";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			pdata : today_date,
		}),
		success : function(data) {
			var data1 = JSON.parse(data);
			$.each(data1, function (index, element) {
				var AirPolutant = element.apc;
				var pollCount=0;
				 var html1 = "<h3><a href='#'>Air Polutant</a></h3>" +
					"<div><div class='card-body'>"+
					"<table class='table table-bordered'><thead><tr><th>SR. NO.</th> <th>Product Name</th> <th>Start Reading</th><th>End Reading</th><th>Actual Reading</th> </tr> </thead> " +
					"<tbody>";
						for (const [key, value] of AirPolutant.entries()) {
							pollCount++;
							 var html2 =  "<tr> <td>"+ pollCount +"</td><td>"+ value.Productname +"</td>" +
							 		    "<td>" + value.StartReading + "</td> " +
							 		    "<td>"+ value.EndReading +"</td> " +
							 		    "<td>"+ value.ActualReading +"</td></tr>";
							html1 += html2;
						}
						 var html3 = "</tbody></table></div></div>";
						 $("#dataAir").append(html1  + html3);
				  });
			 var size=data1.length;
			 if(size > 0 ){
				 $("#dataAir").accordion({heightStyle: 'content',collapsible: true});
			 }
			
		},
		error : function(xhr, type) {
			//$("." + error_div).html("<center><img src='img/nodata1.png' style='width:338px'></center>");
		},
		async : false
	});
	 
}
function dailyDataStatWater(){
	
	$("#dataWater").empty();
	var selected_date = $("#newDate").val();
	getSourceData(selected_date);
	getPrefilterData(selected_date);
	getFilterData(selected_date);
	getFilterUseData(selected_date);
	getDirectUseOfWaterData(selected_date);
	getDailyTreatmentData(selected_date);
	
	$("#dataWater").accordion({ heightStyle: 'content', collapsible: true });
}

function getSourceData(selected_date){
	fwd_url="ajax-view-daily-water-source-data";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			dailydate : selected_date,
		}),
		success : function(data) {
			var data = JSON.parse(data);
			if(data.length>0){
			var html1=""
			var result=data[0].Result;
			if(result==true){
				var dataList=data[0].Data;
				if(dataList!=null){
					var waterSourceDataList = dataList.waterSourceData;
					var staff=dataList.staff;
					if(waterSourceDataList!=null){
					 var html1 = "<h2><a href='#'>Water Source </a></h2>" +
									"<div id='sourcediv'><div class='card-body'>"+
									"<div class='row'>"+
									"<label class='col-sm-2 col-form-label'>Staff</label>"+
									"<div class='col-sm-8'><div class='form-group'>"+
										"<input type='text' class='form-control' value="+ staff +" disabled></div>"+
									"</div>"+
									"<table class='table table-bordered'><thead><tr><th>Source Name</th> <th>Start Reading</th><th>End Reading</th><th>Actual Reading</th><th>Input</th></tr> </thead> " +
									"<tbody>";
					
					 $.each(waterSourceDataList, function (index, element) {
						var html2="";
						var wsId=element.wsId;
						var sourceMeter=element.sourceMeter;	
						var sourceName=element.sourceName;
						var regSourceId=element.regSourceId;
						var startReading=element.startReading;
						var endReading=element.endReading;
						var avgReading=element.avgReading;
						var regSourceDate=element.regSourceDate;
						var userName=element.userName;
							
						if(sourceMeter==true){
								html2+= "<tr>" +
												"<input type='hidden' name='sourceId' value='"+wsId+"'>"+
												"<input type='hidden' name='regularSourceId' value='"+regSourceId+"'>"+
											"<td>"+ sourceName +"</td>" +
								 		    "<td>" + startReading + "</td>" +
								 		    "<td>"+ endReading +"</td>" +
								 		    "<td>"+ avgReading +"</td>" +
								            "<td>"+ userName +"</td>" +
								        "</tr>";
								}else{
								html2+= "<tr>" +
								                "<input type='hidden' name='sourceId' value='"+wsId+"'>"+
											   	"<input type='hidden' name='regularSourceId' value='"+regSourceId+"'>"+
											 "<td>"+ sourceName +"</td>" +
								 		     "<td>-</td> " +
								 		     "<td>-</td> " +
								 		     "<td>"+ avgReading +"</td>" +
								             "<td>"+ userName +"</td>"+
								         "</tr>";
				                  }
					           html1+=html2;
					        });
					var html3 = "</tbody></table></div></div>";
					 $("#dataWater").append(html1  + html3);
			}
				}
			}else{
				var nodataAccordion = "<h2><a href='#'>Water Source</a></h2>"+
				"<div>"+no_data+"</div>";
                $("#dataWater").append(nodataAccordion);
			}
		}
		},
		error : function(xhr, type) {
			//$("." + error_div).html("<center><img src='img/nodata1.png' style='width:338px'></center>");
		},
		async : false
	});
}
function getPrefilterData(date){
	var fwd_url="ajax-daily-view-prefilter-data";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			dailydate : date,
		}),
		success : function(data) {
			var data=JSON.parse(data);
			if(data.length>0){
				var result=data[0].Result;
				if(result==true){
					var dataList=data[0].Data;
					if(dataList!=null){
						var prefilersList=dataList.prefilterList;
						if(prefilersList!=null){
							if(prefilersList.length>0){
								var html1= "<h2><a href='#'>Prefilter</a></h2><div><div class='card-body'>"+
											"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
											"<table class='table table-bordered' id='dailyip-table'>" +
											"<thead> <tr> <th> Prefilter Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Input</th> </tr> </thead> <tbody>";
								
								$.each(prefilersList, function(index, element){
									var html2="";
									var meter=element.isMeter;
									var prefilterName=element.prefilterName;
									var userName=element.userName;
									var prefilterId=element.prefilterId;
									var regPrefilterId=element.regPrefilterId;;
									var regPrefilterDate=element.regPrefilterDate;
									var startReading=element.startReading;
									var endReading=element.endReading;
									var actReading=element.actReading;
									
									if(meter==true){
										html2+= "<tr>"+
						                			   "<input type='hidden' name='prefilterId[]' value='"+prefilterId+"'>"+
						                			   "<input type='hidden' name='regPrefilterId[]' value='"+regPrefilterId+"'>"+
						                		    "<td>"+prefilterName+ "</td>"+
						                		    "<td>"+startReading+ "</td>"+
						                		    "<td>"+endReading+ "</td>"+
						                		    "<td>"+actReading+ "</td>"+
						                		    "<td>"+userName+ "</td>"+
						                		 "</tr>";
										}else{
											html2+= "<tr>"+
													   		"<input type='hidden' name='prefilterId[]' value='"+prefilterId+"'>"+
													   		"<input type='hidden' name='regPrefilterId[]' value='"+regPrefilterId+"'>"+
													   	"<td>"+prefilterName +"</td>"+
													   	"<td>-</td>"+
													   	"<td>-</td>"+
													   	"<td>"+actReading +"</td>"+
													   	"<td>"+ userName+"</td>"+
													"</tr>";
						                 }
						html1+=html2;
					});
					var html3 = "</tbody></table></div></div></div>";
					 		$("#dataWater").append(html1  + html3);
				}
				}
					}
				}else{
					var nodataAccordion = "<h2><a href='#'>Prefilter</a></h2>"+
											"<div>"+no_data+"</div>";
							$("#dataWater").append(nodataAccordion);
				}
			}
		},
		async: false
		});
}
function getFilterData(date){
	var fwd_url="ajax-daily-view-filter-data";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			dailydate : date,
		}),
		success : function(data) {
			 var data=JSON.parse(data);
			 var result=data[0].Result;
			 if(result==true){
				 var dataList=data[0].Data;
				 if(dataList!=null){
					 var filterData=dataList.filtersData;
					 if(filterData!=null){
							var html1= "<h2><a href='#'>Filters</a></h2><div><div class='card-body'>"+
										"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
											"<table class='table table-bordered' id='dailyip-table'>" +
												"<thead> <tr> <th> Filter Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Input</th> </tr> </thead> <tbody>";
							
							$.each(filterData, function(index, element){
							 var html2="";
							 var filterId=element.filterId;
							 var regMultipleFilterId=element.regMultipleFilterId;
							 var filterLabel=element.filterLabel;
							 var filterType=element.filterType;
							 var isMeter=element.isMeter;
							 var startReading=element.startReading;
							 var endReading=element.endReading;
							 var actualReading=element.actualReading;
							 var userName=element.userName;
							 
							 if(isMeter==true){
									html2+= "<tr>"+
					                	    		"<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
					                	    		"<input type='hidden' name='regMultipleFilterId[]' value='"+regMultipleFilterId+"'>"+
					                		    "<td>"+filterLabel+" ("+filterType+")"+"</td>"+
					                		    "<td>"+startReading+ "</td>"+
					                		    "<td>"+endReading+ "</td>"+
					                		    "<td>"+actualReading+ "</td>"+
					                		    "<td>"+userName+ "</td>"+
					                		 "</tr>";
									}else{
									html2+= "<tr>"+
									   				"<input type='hidden' name='filterId[]' value='"+filterId+"'>"+
									   				"<input type='hidden' name='regMultipleFilterId[]' value='"+regMultipleFilterId+"'>"+
									   			"<td>"+filterLabel+" ("+filterType+")"+"</td>"+
											    "<td>-</td>"+
											    "<td>-</td>"+
											    "<td>"+actualReading +"</td>"+
											    "<td>"+ userName+"</td>"+
											 "</tr>";
					                 }
							 html1+=html2;
						 });
						     var html3= "</tbody></table></div></div></div>";
						     		$("#dataWater").append(html1+html3);
					 }
				 }
			 }else{
				 var nodataAccordion = "<h2><a href='#'>Filters</a></h2>"+
				 						"<div>"+no_data+"</div>";
				 $("#dataWater").append(nodataAccordion);
             }
		},
		async: false
	});
}
function getFilterUseData(selected_date){
	var fwd_url="ajax-daily-view-filteruse-data";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			dailydate : selected_date,
		}),
		success : function(res) {
			var data=JSON.parse(res);
			if(data.length>0){
				var result=data[0].Result;
				if(result==true){
					var dataList=data[0].Data;
					if(dataList!=null){
						 var filterUseData=dataList.filterUseData;
						 if(filterUseData!=null){
							 var html1= "<h2><a href='#'>FilterUse</a></h2><div><div class='card-body'>"+
											"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
												"<table class='table table-bordered' id='dailyip-table'>" +
													"<thead> <tr> <th> FilterUse Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Input</th> </tr> </thead> <tbody>";
							 
							 $.each(filterUseData, function(index, element){ 
								 var html2="";
								 var filterUseLabel=element.filterUseLabel;
								 var filterUseType=element.filterUseType;
								 var isMeter=element.isMeter;
								 var startReading=element.startReading;
								 var endReading=element.endReading;
								 var actualReading=element.actualReading;
								 var userName=element.userName;
								
								 if(isMeter==true){
										html2+= "<tr>"+
						                			   "<input type='hidden' name='filterUseLabel[]' value='"+filterUseLabel+"'>"+
						                		    "<td>"+filterUseLabel+" ("+filterUseType+")"+"</td>"+
						                		    "<td>"+startReading+ "</td>"+
						                		    "<td>"+endReading+ "</td>"+
						                		    "<td>"+actualReading+ "</td>"+
						                		    "<td>"+userName+ "</td>"+
						                		 "</tr>";
										}else{
										html2+= "<tr>"+
													   "<input type='hidden' name='filterUseLabel[]' value='"+filterUseLabel+"'>"+
													"<td>"+filterUseLabel+" ("+filterUseType+")"+"</td>"+
												    "<td>-</td>"+
												    "<td>-</td>"+
												    "<td>"+actualReading +"</td>"+
												    "<td>"+ userName+"</td>"+
												 "</tr>";
						                 }
								 html1+=html2;
							 });
							     var html3= "</tbody></table></div></div></div>";
							     	$("#dataWater").append(html1+html3);
							 
						 }
					}
				}else{ 
					var nodataAccordion = "<h2><a href='#'>FilterUse</a></h2>"+
											"<div>"+no_data+"</div>";
									$("#dataWater").append(nodataAccordion);
				}
			}
		},
		async:false
		});
}
function getDirectUseOfWaterData(selected_date){
	var fwd_url="ajax-daily-view-direct-use-of-water-data";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			dailydate : selected_date,
		}),
		success : function(res) {
			var data=JSON.parse(res);
			if(data.length>0){
				var result=data[0].Result;
				if(result==true){
					var dataList=data[0].Data;
					if(dataList!=null){
						var directUseData=dataList.directUseOfWaterData;
						if(directUseData!=null){
							 var html1= "<h2><a href='#'>DirectUse</a></h2><div><div class='card-body'>"+
											"<div class='form-group' style='display: -webkit-inline-box;'>" + "</div>" +
												"<table class='table table-bordered' id='dailyip-table'>" +
													"<thead> <tr> <th> DirectUse Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>Input</th> </tr> </thead> <tbody>";
							
							 $.each(directUseData, function(index, element){ 
								 var html2="";
								 var whereToUse=element.whereToUse;
								 var typeOfUse=element.typeOfUse;
								 var isMeter=element.isMeter;
								 var startReading=element.startReading;
								 var endReading=element.endReading;
								 var actualReading=element.actualReading;
								 var userName=element.userName;
								 
								 if(isMeter==true){
										html2+= "<tr>"+
						                				"<input type='hidden' name='whereToUse[]' value='"+whereToUse+"'>"+
						                		    "<td>"+whereToUse+" ("+typeOfUse+")"+"</td>"+
						                		    "<td>"+startReading+ "</td>"+
						                		    "<td>"+endReading+ "</td>"+
						                		    "<td>"+actualReading+ "</td>"+
						                		    "<td>"+userName+ "</td>"+
						                		 "</tr>";
										}else{
										html2+= "<tr>"+
										  				"<input type='hidden' name='whereToUse[]' value='"+whereToUse+"'>"+
										  			"<td>"+whereToUse+" ("+typeOfUse+")"+"</td>"+
												    "<td>-</td>"+
												    "<td>-</td>"+
												    "<td>"+actualReading +"</td>"+
												    "<td>"+ userName+"</td>"+
												 "</tr>";
						                 }
								 html1+=html2;
							 });
							 var html3= "</tbody></table></div></div></div>";
							    $("#dataWater").append(html1+html3);
						}
					}
				}else{ 
					var nodataAccordion = "<h2><a href='#'>DirectUse</a></h2>"+
											"<div>"+no_data+"</div>";
								$("#dataWater").append(nodataAccordion);
					}
				}
			},
			async:false
		});
}
function getDailyTreatmentData(date){
	var fwd_url="ajax-daily-view-treatment-data";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
		data : ({
			dailydate : date,
		}),
		success : function(data) {
			 var data=JSON.parse(data);
		  if(data.length>0){
			  var result=data[0].Result;
			  if(result==true){
			 var wwtpaccordionHtml1 ="<h2><a href='#'> Waste Water Treatment </a></h2>"+
				                     	"<div id='wwtpAccordion1'>"+
				                     	"</div>";
			
            $("#dataWater").append(wwtpaccordionHtml1); 
            var dataList=data[0].Data;
            if(dataList!=null){
			var treatmentDataList=dataList.treatmentData;
			if(treatmentDataList!=null){
				$.each(treatmentDataList, function(index, element){
					var treatmentLable=element.treatmentLable;
					var treatmentId=element.treatmentId;
					var regularTreatmentId=element.regularTreatmentId;
					var startReading=element.startReading;
					var endReading=element.endReading;
					var actualReading=element.actualReading;
					var energyStartReading=element.energyStartReading;
					var energyEndReading=element.energyEndReading;
					var energyActualReading=element.energyActualReading;
					var userName=element.userName;
					
					 var singleAccordion ="<h2><a href='#'>"+ treatmentLable +" </a></h2>"+
										  	"<div>"+
										  		"<div class='card-body'>"+
										  			"<input type='hidden' class='form-control'  id='treatmentwater_id_"+treatmentLable +"' value='"+treatmentId+"'>"+
										  				"<table class='table table-bordered' id='dailyip-table'>" +
											"<thead> <tr> <th> Treatment Name</th> <th>Start Reading</th> <th>End Reading</th> <th>Actual Consumption</th> <th>UserName</th> </tr> </thead>";
					              
					                  singleAccordion+= "<tr>"+
									        					"<input type='hidden' name='treatmentId[]' value='"+treatmentId+"'>"+
									        					"<input type='hidden' name='regularTreatmentId[]' value='"+regularTreatmentId+"'>"+
									        				  "<td>"+treatmentLable+"(Water Reading)</td>"+
									        				  "<td>"+startReading+"</td>"+
											        		  "<td>"+endReading+"</td>"+
											        		  "<td>"+actualReading+"</td>"+
											        		  "<td>"+userName+"</td>"+
									        			"</tr>"+
										                "<tr>"+
											                  "<td>"+treatmentLable+"(Energy Reading)</td>"+
											                  "<td>"+energyStartReading+"</td>"+
											                  "<td>"+energyEndReading+"</td>"+
											                  "<td>"+energyActualReading+"</td>"+
											                  "<td>"+userName+"</td>"+
										                "</tr>";
					
					var recycleList=element.recycleData;
					if(recycleList!=null){
						$.each(recycleList, function(index,element){
							var recycleId=element.recycleId;
							var regRecycleId=element.regRecycleId;
							var useType=element.useType;
							var recycledTo=element.recycledTo;
							var recycleStartReading=element.recycleStartReading;
							var recycleEndReading=element.recycleEndReading;
							var recycleActualReading=element.recycleActualReading;
							var isMeter=element.isMeter;
							var userName=element.userName;
							
							if(isMeter==true){
								singleAccordion+= "<tr>"+
												  		"<input type='hidden' name='recycleId[]' value='"+recycleId+"'>"+
												  		"<input type='hidden' name='regRecycleId[]' value='"+regRecycleId+"'>"+
												     "<td>"+useType+":"+recycledTo+"</td>"+
													 "<td>"+recycleStartReading+"</td>"+
													 "<td>"+recycleEndReading+"</td>"+
													 "<td>"+recycleActualReading+"</td>"+
													 "<td>"+userName+"</td>"+
								                  "</tr>";
							}else{
								singleAccordion+= "<tr>"+
								                        "<input type='hidden' name='recycleId[]' value='"+recycleId+"'>"+
								                        "<input type='hidden' name='regRecycleId[]' value='"+regRecycleId+"'>"+
								                     "<td>"+useType+":"+recycledTo+"</td>"+
								                     "<td>-</td>"+
								                     "<td>-</td>"+
								                     "<td>"+recycleActualReading+"</td>"+
								                     "<td>"+userName+"</td>"+
								                  "</tr>";
							}
						});
					}
					singleAccordion +="</tbody>"+
					"<table></div></div>";
					$("#wwtpAccordion1").append(singleAccordion); 
				});
			}
            }
                       $("#wwtpAccordion1").accordion({
            				heightStyle: 'content',
            				collapsible: true
            			});
                      
			  }else{
					var nodataAccordion = "<h2><a href='#'>Waste Water Treatment</a></h2>"+
										  	"<div>"+no_data+"</div>";
							$("#dataWater").append(nodataAccordion);
				  }
							$("#dataWater").accordion({heightStyle: 'content',collapsible: true});
							$("#dataWater").accordion("destroy");
		  }
		},
		async: false
	});
}
