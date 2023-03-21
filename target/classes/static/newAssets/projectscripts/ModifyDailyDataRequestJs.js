$(document).ready(function () {
	modifyDailyData();
});

function modifyDailyData(){
var fwd_url="ajax-get-modify-daily-data-request";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		dataType : 'json',
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
					"<div class='table-responsive'>" +
					"<table id='modifyReqTable_"+i+"' class='table table-bordered table-hover normal'> <thead> <tr> <th style='width: 150px'>SR. NO.</th> <th style='width: 200px'>Product Name</th> <th> Old Quantity</th> <th>New Quantity</th> <th>Reason</th> <th  style='width: 250px'>Date</th> <th>Requested By</th> <th  style='width: 250px'>Action</th> </tr> </thead> " +
					"<tbody>";
				 var j = 1;
				  $.each(allDataArray, function (index, element) {
					  
					var modifiedId = element.id;
					var itemName = element.ProductName;
					var cQuantity =  element.CQuantity;
					var nQuantity =  element.NQuantity;
					var inpDate = element.requestDate;
					var reason = element.reason;
					var regId = element.regId;
					var pUnit = element.productUnit;
					var uName= element.userName;
					var action = element.action;
					
					html1 +=  "<tr> <td height='10'> <label>"+ j +"</label> </td> <td height='10'> <label>"+ itemName +"</label> </td> <td height='10'> <label>" + cQuantity + "</label> </td> <td height='10'> <label>" + nQuantity + "</label> </td> <td height='10'> <label>"+ reason +"</label> </td> <td height='10'> <label>" + inpDate + "</label> </td> <td> <label>"+ uName +"</label> </td> <td  style='width: 300px' height='10'><button class='btn btn-warning' onclick='approvePopup(\"" + itemName + "\",\"" + cQuantity + "\",\"" + nQuantity + "\",\""+regId+"\",\""+modifiedId+"\",\""+inpDate+"\",this);'><i class='zmdi zmdi-edit'></i></button><button class='btn btn-danger' onclick='rejectPopup(\"" + itemName + "\",\"" + cQuantity + "\",\"" + nQuantity + "\",\""+regId+"\",\""+modifiedId+"\",\""+inpDate+"\",this)'><i class='zmdi zmdi-close'></i></button> </td> </tr>";
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
				 
				 makedatatable("modifyReqTable_"+i);
			 }
			 var size=data1.length;
			 if(size > 0 ){
				 $("#dataProduct").accordion({heightStyle: 'content',collapsible: true});
				 $("#dataSoild").accordion({heightStyle: 'content',collapsible: true});
				 $("#dataAir").accordion({heightStyle: 'content',collapsible: true});
			 }
		},
		error : function(xhr, type) {
			// $("." + error_div).html("<center><img src='img/nodata1.png'
			// style='width:338px'></center>");
		},
		async : false
	});
}

function rejectPopup(itemName,cQuantity,nQuantity,regId,modifiedId,inpDate,el) {
	var msg = "Are you sure you want to reject request of modify "+itemName+" quantity from "+cQuantity+" to "+nQuantity+" on date : "+inpDate;
	swal({
		title: "Reject daily modify request",
		text : msg,
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-success",
			confirmButtonText: "Reject",
			cancelButtonText: "No",
			allowOutsideClick: false
		})
		.then((res) => {
			if (res.value == true) {
				chnageStatusModifyRequest(regId,modifiedId,nQuantity,"Reject",el);
			} else if (res.dismiss == "cancel") {
			}
		});
}
function approvePopup(itemName,cQuantity,nQuantity,regId,modifiedId,inpDate,el) {
  var msg = "Are you sure you want to approve request of modify "+itemName+" quantity from "+cQuantity+" to "+nQuantity+" on date : "+inpDate;
	swal({
		title: "Approve daily modify request",
		text : msg,
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-success",
			confirmButtonText: "Approve",
			cancelButtonText: "No",
			allowOutsideClick: false
		})
		.then((res) => {
			if (res.value == true) {
				chnageStatusModifyRequest(regId,modifiedId,nQuantity,"Approved",el);
			} else if (res.dismiss == "cancel") {
			}
		});
}

function chnageStatusModifyRequest(regId,modifiedId,nQuantity,status,el){
	
	var myObj = {};
	myObj["regId"] = regId;
	myObj["modifiedId"] = modifiedId;
	myObj["nQuantity"] = nQuantity;
	myObj["status"] = status;
	
	$.ajax({
	      type : "POST",
	      url : "ajax-change-status-modify-record",
	      contentType : "application/json",
	      data : JSON.stringify(myObj),
	      success : function(data) {
	    	  //location.reload();
	        $(el).parent('td').parent('tr').remove();
	        jBoxBottomRightBigNotice("Success", status + " !!", "green", "2500");
	      }
	    });
	
}