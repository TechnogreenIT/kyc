/**
 * 
 */

$(document).ready(function() {
	var isproduction = "Yes";
	$.ajax({
		type : "POST",
		url : "getProduction-saveData",
		data : {
			action : 'getProduction',
		},
		success : function(data) {
			isproduction = data.trim();
		},
		async : false
	});
	var msg = "";
	var j = 0;
	// isproduction="Yes";
	console.log("isproduction==" + isproduction);

	if (isproduction == "Yes") {
		var year = $("input[name=year]").val();
		/*var year = document.getElementById("haz_year").value;*/
		var msg = "";
		console.log("year=" + year);

		getProductionData();
		getHazardousdata();
		getDispatchedData();
		getInhouseData();
		getStorageData();
	}
});

function getProductionData() {
	var year = $("input[name=year]").val();
	/*var year = document.getElementById("haz_year");*/
	/*var year=year1.options[year1.selectedIndex].value;*/
	var msg = "";
	console.log("year=" + year);
	$.ajax({
				type : 'POST',
				url : 'ajax-getProductionData?type=production',
				data : {
					year : year
				},
				success : function(data1) {
					var data = JSON.parse(data1);
					$.each(data, function(index, element) {
										var temp = element.productName;
										if (temp != null || temp != "") {
											msg =  "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='NA'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.productName
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.actualQuantity
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.UOM
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>";
											$("#production").append(msg);
										}
									});
				},
				async: false
			});
}
function getHazardousdata() {
	var year = $("input[name=year]").val();
	var msg = "";
	$.ajax({
				type : 'POST',
				url : 'ajax-getHazardousdata?type1=wastegen',
				data : {
					year : year
				},
				success : function(data2) {
					var data = JSON.parse(data2);
					$.each(data, function(index, element) {
										var temp = element.wasteName;
										if (temp != null || temp != "") {
											msg =  "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.typeOfWaste
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.wasteName
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.quantity
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.units
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
											$("#wastegen").append(msg);
										}
									});
				},
				async: false
			});
}

function getDispatchedData() {
	var year = $("input[name=year]").val();
	var msg = "";
	console.log("year=" + year);
	$.ajax({
				type : 'POST',
				url : 'ajax-getDispatchData?type1=dispatch',
				data : {
					year : year
				},
				success : function(data3) {
					var data = JSON.parse(data3);
					console.log("dispatchdata==" + data);
					$.each(data, function(index, element) {
										var temp = element.dispatchDispatchedTo;
										if (temp != null || temp != "") {
											msg =  "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.typeOfWaste
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-2'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.dispatchQuantity
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-2'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.dispatchUnits
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-2'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.dispatchDispatchedTo
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.dispatchFacilityName
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>";
											$("#dispatch").append(msg);
										}
									});
				},
				async: false
			});
}

function getInhouseData() {
	var year = $("input[name=year]").val();
	var msg = "";
	console.log("year=" + year);
	$.ajax({
				type : 'POST',
				url : 'ajax-getInhouseData?type=inhouse',
				data : {
					year : year
				},
				success : function(data1) {
					var data = JSON.parse(data1);
					$.each(data, function(index, element) {
										var temp = element.inhouseWasteName;
										if (temp != null || temp != "") {
											msg = "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.typeOfWaste
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.inhouseWasteName
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.wasteQuantity
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.UOM
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>";
											$("#inhouse").append(msg);
										}
									});
				}
			});
}
function getStorageData() {
	var year = $("input[name=year]").val();
	var msg = "";
	console.log("year=" + year);
	$.ajax({
				type : 'POST',
				url : 'ajax-getStorageData?type=storage',
				data : {
					year : year
				},
				success : function(data1) {
					var data = JSON.parse(data1);
					$.each(data, function(index, element) {
										var temp = element.storageTypeWasteName;
										if (temp != null || temp != "") {
											msg =  "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.storageTypeWasteName
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.storageWasteName
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.storageQuantity
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "<div class='col-sm-3'>"
													+ "<div class='col-sm-12'>"
													+ "<div class='form-group'>"
													+ "<div class='fg-line'>"
													+ "<input type='text' disabled class='form-control' value='"
													+ element.UOM
													+ "'>"
													+ "</div>"
													+ "</div>"
													+ "</div>"
													+ "</div>";
											$("#storage").append(msg);
										}
									});
				},
				async: false
			});
}