var no_data = "<center><img src='../newAssets/img/nodata1.png' style='width:338px'></center>";
$(document).ready(function () {
	wasteWaterTreatmentDataETP("ETP");
	wasteWaterTreatmentDataETP("STP");
});
function wasteWaterTreatmentDataETP(type){
	var myObj = {};
	myObj["treatmentType"] = type;
//	myObj["STP"] = type1;
	$.ajax({
		type : 'POST',
		url : "ajax-get-etp-waste-water-data",
		contentType : "application/json",
	    data : JSON.stringify(myObj),
		success : function(data) {
			var x = document.getElementById("data"+type).childElementCount;
			if(x > 0){
				$( "#data"+type ).accordion( "destroy" );
			}
			$("#data"+type).empty();
			
			var data1 = JSON.parse(data);
			var objSize = Object.keys(data1).length;
				 
				
					$.each(data1, function (index, element) {
						
						var dataArray = data1[index];
						 var allDataArray = new Array();
						 var pType = "";
						 
						 Object.keys(dataArray).forEach(function(key) {
							 allDataArray = dataArray[key];
							 pType = key;
							
							});
						 
						 var singleAccordion ="<h2><a href='#'>"+ pType +" </a></h2> <div>" +
						 		"<div class='card-body'>"+
						 		"<table class='table table-bordered'> <thead> <tr> <th>SR. NO.</th> <th>Use Type</th> <th>Recycle To</th> <th>Quantity</th> </thead> " +
								"<tbody></div>";
						 var j = 1;
						 for (const [key, value] of allDataArray.entries()) {
							 var label=value.label;
							 var treatmentType=value.treatmentType;
							 var useType=value.useType;
							 var recycleTo=value.recycleTo;
							 var quantity=value.quantity;
							
							 singleAccordion +=  "<tr> <td>"+ j +"</td> <td>"+ useType +"</td> <td>" + recycleTo + "</td> <td>"+ quantity +"</td> </tr>";
					 			j++; 
							 
						 }
						 
					 			 $("#data"+type).append(singleAccordion);
						 });

					var html3 = "</tbody></table></div>";

					 $("#data"+type).accordion({
						heightStyle: 'content',
						collapsible: true
				});
				 
			},
			error : function(xhr, type) {
				//$("." + error_div).html("<center><img src='img/nodata1.png' style='width:338px'></center>");
			},
			async : false
	});	
}