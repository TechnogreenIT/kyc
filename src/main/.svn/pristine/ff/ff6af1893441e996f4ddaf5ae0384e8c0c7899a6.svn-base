var consentTypee = $("#actionType").val();
var consentNo = "";
var productTypeArrayList = ["product", "byproduct", "raw", "fuel", "hwp", "hwpcf", "nhwp", "nhwpcf", "bio"];

function openConsentDetails() {
	consentNo = $("#consentNo").val();

	var msg_data;
	var fwd_url;

	if (consentTypee == 'establish') {
		getConsentToEData(consentTypee);
	} else if (consentTypee == 'operate') {
		getConsentToEData(consentTypee);
	}

	getConsentDatas(consentTypee);
	// call this function if consent Data available
	openStack();
	openAmbient();
	openWaterPollutants();
	getWaterConGen();
}

function getConsentToEData(consentType) {
	fwd_url = "ajax-get-consent-details-by-id?type=" + consentType + "&consent_no=" + consentNo;

	if (consentTypee == 'operate') {

	}

	$.ajax({
		type: "GET",
		url: fwd_url,
		dataType: "json",
		success: function (data) {
			$("#consentDetailsData").empty();

			$.each(data, function (index, element) {
				msg_data = "<div class='col-10 offset-1 mt-4'><table class='table' border='1'>" +
					"<tr>" +
					"<td height='50px' width='170px' style='color:#000000'><b>Consent No.:</b></td>" +
					"<td width='300px' ><font color='#00688B'> " + element.consNo + " </font></td>" +
					"<td><b style='color:#000000'>Consent Type:</b></td>" +
					"<td width='200px' colspan='3'><font color='#00688B'> " + element.consType + " </font></td>" +
					"</tr>" +
					"<tr>" +
					"	<td height='50px' style='color:#000000'><b>Issue Date:</b></td>" +
					"	<td><font color='#00688B'> " + element.issueDate + "  </font></td>" +
					"	<td style='color:#000000'><b>Valid Date:</b></td>" +
					"	<td colspan='3'><font color='#00688B'> " + element.validUpto + " </font></td>" +
					"</tr>" +
					"<tr>" +
					"	<td height='50px' style='color:#000000'><b>Gross Capital Investment:</b></td>" +
					"	<td><font color='#00688B'>  " + element.grossCi + " </font></td>" +
					"	<td style='color:#000000'><b>Staff:</b></td>" +
					"	<td width='100px'><font color='#00688B'> " + element.noStaff + " </font></td>" +
					"	<td style='color:#000000'><b>Worker:</b></td>" +
					"	<td width='100px'><font color='#00688B'> " + element.noWorker + " </font></td>" +
					"</tr>" +
					"<tr>" +
					"	<td height='50px' style='color:#000000'><b>Total Plot Area:</b></td>" +
					"	<td><font color='#00688B'> " + element.totPlotArea + " </font></td>" +
					"	<td width='110px'style='color:#000000'><b>Total Build Area:</b></td>" +
					"	<td><font color='#00688B'> " + element.totBuildArea + "  </font></td>" +
					"	<td width='110px'style='color:#000000'><b>Total Green Area:</b></td>" +
					"	<td><font color='#00688B'> " + element.totGreenArea + " </font></td>" +
					"</tr>" +
					"<tr>" +
					"<input type='hidden' id='consent_file_old' value='" + element.consentFileName + "'><td colspan='5' height='80px' style='color:#000000'><center class='mt-3'><b>Consent File:</b></font><a onclick='downloadConsent(\"" + element.consentFileName + "\",\"consent\")' title='click to download this consent copy.'> " +
					element.consentFileName + "</font></a></center></td>" +
					"<td colspan='1'><a href='#'  class='btn btn-default' style='background-color:#9e9e9e;color:white;' onclick='openEditConsent(\"" + consentType + "\");'><b>EDIT</b></a></td>" +
					"</tr>" +
					"</table>";
				$(msg_data).appendTo("#consentDetailsData");
			});

			
		}
	});
}

function openEditConsent(consentType) {
	var msg_data;
	var fwd_url;
	var consent_type = "Consent to Establish";
	$("#consentDetailsData").empty();
	if (consentTypee == 'establish') {
		consent_type = "Consent to Establish";
		fwd_url = "ajax-get-consent-details-by-id?type=" + consentType + "&consent_no=" + consentNo;
	} else if (consentTypee == 'operate') {
		consent_type = "Consent to Operate";
		fwd_url = "ajax-get-consent-details-by-id?type=" + consentType + "&consent_no=" + consentNo;
	}
	$(document).ready(function () {
		$.ajax({
			type: "GET",
			url: fwd_url,
			dataType: "json",
			success: function (data) {
				$.each(data, function (index, element) {
					msg_data = "<div class='col-10 offset-1 mt-4'><form method='post' action=''>" +
						"<input type='hidden' id='consentNo' name='consentNo' value='" + element.consNo + "'>" +
						"<input type='hidden' id='consentType' name='consentType' value='" + element.consType + "'>" +
						"<table class='table' border='1'>" +
						"<tr>" +
						"<td height='50px' width='170px' ><b>Consent No.:</b></td>" +
						"<td width='300px'> " + element.consNo + "</td>" +
						"<td style='color:#000000'><b>Consent Type:</b></td>" +
						"<td width='200px' colspan='3'> " + element.consType + "</td>" +
						"</tr>" +
						"<tr>" +
						"	<td height='50px'><b>Issue Date:</b></td>" +
						"	<td>" +
						"<div class='input-group mb-1'>" +
						"<input type='date' class='form-control hidden-md-up'>" +
						"<input type='text' class='form-control date-picker hidden-sm-down' id='issue_date' value='" + element.issueDate + "'>" +
						"</div>" +
						"</td>" +
						"	<td><b>Valid Date:</b></td>" +
						"	<td colspan='3'>" +
						"<div class='input-group mb-1'>" +
						"<input type='date' class='form-control hidden-md-up'>" +
						"<input type='text' class='form-control date-picker hidden-sm-down' id='valid_upto' value='" + element.validUpto + "'>" +
						"</div>" +
						"</td>" +
						"</tr>" +
						"<tr>" +
						"	<td height='50px'><b>Gross Capital Investment:</b></td>" +
						"	<td>" +
						"<div class='form-group'>" +
						"<input type='number' class='form-control' id='gross_ci' value='" + element.grossCi + "'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>" +
						"	<td><b>Staff:</b></td>" +
						"	<td width='100px'>" +
						"<div class='form-group'>" +
						"<input type='number' class='form-control' id='no_staff' value='" + element.noStaff + "'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>" +
						"	<td><b>Worker:</b></td>" +
						"	<td width='100px'>" +
						"<div class='form-group'>" +
						"<input type='number' class='form-control' id='no_worker' value='" + element.noWorker + "'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>" +
						"</tr>" +
						"<tr>" +
						"	<td height='50px'><b>Total Plot Area:</b></td>" +
						"<td>" +
						"<div class='form-group'>" +
						"<input type='number' class='form-control' id='tot_plot_area' value='" + element.totPlotArea + "'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>" +
						"<td width='110px'><b>Total Build Area:</b></td>" +
						"<td>" +
						"<div class='form-group'>" +
						"<input type='number' class='form-control' id='tot_build_area' value='" + element.totBuildArea + "'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>" +
						"<td width='110px'><b>Total Green Area:</b></td>" +
						"<td>" +
						"<div class='form-group'>" +
						"<input type='number' class='form-control' id='tot_green_area' value='" + element.totGreenArea + "'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>" +
						"</tr>" +
						"</tr>" +
						"<tr>" +
						"<td colspan='3' height='80px'>" +
						"<center><input type='hidden' id='consent_file_old' value='" + element.consentFileName + "'><b>Consent File:  </b><a onclick='downloadConsent(\"" + element.consentFileName + "\",\"consent\")'> " + element.consentFileName + "</a><br/><br/>" +
						"<div class='fileinput fileinput-new' data-provides='fileinput'>" +
						"<label>Consent File : </label>" +
						"<span class='btn btn-primary btn-file'>" +
						"<span class='fileinput-new'>Select file</span>" +
						"<span class='fileinput-exists'>Change</span>" +
						"<input type='file' name='consent_file' id='consent_file' required>" +
						"</span>" +
						"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>" +
						"</div></center>" +
						"</td>" +
						"<td colspan='3'>" +
						"<a href='#' class='btn btn-default' style='background-color:#9e9e9e;color:white;' onclick='openConsentDetails();'><b>Cancel</b></a> | " +
						"<a href='#' class='btn btn-default' style='background-color:#9e9e9e;color:white;' onclick='submitConsentDetails();'><b>Modify</b></a>" +
						"</td>" +
						"</tr>" +
						"</table></form></div>"
					$(msg_data).appendTo("#consentDetailsData");
					makeDatepicker();
				});
			}
		});
	});
}

function submitConsentDetails() {
	var form_data = new FormData();
	flag = 0;
	var consentNo = $("#consentNo").val();
	var consentType = $("#consentType").val();
	var issue_date = $("#issue_date").val();
	var valid_upto = $("#valid_upto").val();
	var gross_ci = $("#gross_ci").val();
	var no_staff = $("#no_staff").val();
	var no_worker = $("#no_worker").val();
	var tot_plot_area = $("#tot_plot_area").val();
	var tot_build_area = $("#tot_build_area").val();
	var tot_green_area = $("#tot_green_area").val();
	var consent_file_old = $("#consent_file_old").val();
	var consent_file_new = $("input[name=consent_file]").val();

	flag += customInputValidator(gross_ci, "#gross_ci");
	flag += customInputValidator(no_staff, "#no_staff");
	flag += customInputValidator(no_worker, "#no_worker");
	flag += customInputValidator(tot_plot_area, "#tot_plot_area");
	flag += customInputValidator(tot_build_area, "#tot_build_area");
	flag += customInputValidator(tot_green_area, "#tot_green_area");

	var cc = consent_file_new.split("\\");
	var status = "Yes";
	if (consent_file_new == "") {
		status = "No";
	} else if (cc[2] == consent_file_old) {
		status = "No";
	} else {
		var file_data = $("#consent_file").prop('files')[0]; //alert(file_data)
		form_data.append('file', file_data);
	}

	form_data.append('status', status);
	form_data.append('consentNo', consentNo);
	form_data.append('consentType', consentType);
	form_data.append('issue_date', issue_date);
	form_data.append('valid_upto', valid_upto);
	form_data.append('gross_ci', gross_ci);
	form_data.append('no_staff', no_staff);
	form_data.append('no_worker', no_worker);
	form_data.append('tot_plot_area', tot_plot_area);
	form_data.append('tot_build_area', tot_build_area);
	form_data.append('tot_green_area', tot_green_area);
	form_data.append('consent_file_old', consent_file_old);

	if (flag == 0) {
		$.ajax({
			url: "ajax-view-consent-modify-consent-data",
			dataType: 'json',
			cache: false,
			contentType: false,
			processData: false,
			data: form_data,
			type: 'post',
			success: function (res) {
				if (res == "success") {
					jBoxBottomRightBigNotice("Success", "Consent Updated !!", "green", "2000");
					openConsentDetails();
				} else {
					jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
				}

			}
		});
	}
}

function getConsentDatas(consentType) {
	var i = 0;
	var msg_data;
	var fwd_url;

	$("#prods").empty();
	$("#byprods").empty();
	$("#raw").empty();
	$("#fuel").empty();
	$("#hwp").empty();
	$("#hwpcf").empty();
	$("#nhwp").empty();
	$("#nhwpcf").empty();
	$("#bio").empty();

	for (var r = 0; r < productTypeArrayList.length; r++) {
		var tempId = productTypeArrayList[r] + "_view";
		$("#" + tempId).empty();
	}

	if (consentType == 'establish') {
		fwd_url = "ajax-get-production-details?type=establish&consentId=" + consentNo;
	} else if (consentType == 'operate') {
		fwd_url = "ajax-get-production-details?type=operate&consentId=" + consentNo;
	}
	$(document).ready(
		function () {
			$.ajax({
				type: "GET",
				url: fwd_url,
				dataType: "json",
				success: function (data) {

					for (var p = 0; p < productTypeArrayList.length; p++) {
						if (consentTypee == 'establish') {
							var html_btn = "<div class='row'><div class='col-2 offset-10'><div class='form-group'><button type='button' class='btn btn-warning btn--icon-text brown lighten-1'onclick='addEstablishData(\"" + productTypeArrayList[p] + "\");'><i class='zmdi zmdi-plus zmdi-hc-fw'></i>Add</button></div></div></div>";
						} else if (consentTypee == 'operate') {
							//var html_btn = "<div class='row'><div class='col-2 offset-10'><div class='form-group'><button type='button' class='btn btn-warning btn--icon-text brown lighten-1'onclick='addOperateData(\"" + productTypeArrayList[p] + "\");'><i class='zmdi zmdi-plus zmdi-hc-fw'></i>Add</button></div></div></div>";
							var html_btn = "";
						}

						var tempId = "" + productTypeArrayList[p] + "_view";
						$(html_btn).appendTo("#" + tempId);

						var extraTh = "";
						if (productTypeArrayList[p] == "hwp" || productTypeArrayList[p] == "hwpcf") {
							extraTh = "<th>Category</th>"
						}
						var hmtl_data = "<div class='row'>" +
							"<div class='table-responsive'>" +
							"<table class='table table-bordered table-hover normal'>" +
							"<thead><tr>" + extraTh +
							"<th>Name</th>" +
							"<th>Quantity</th>" +
							"<th>Unit</th>" +
							"<th>Status</th>";
						if (consentTypee != 'operate') {
							hmtl_data += "<th>Modify</th>";
						}
						hmtl_data += "<th>Unable/Disable</th>" +
							"</tr></thead>" +
							"<tbody id='appendTable_" + tempId + "'>" +
							"</tbody>" +
							"</table>" +
							"</div>" +
							"</div>";
						$(hmtl_data).appendTo("#" + tempId);
					}

					$.each(data, function (index, element) {
						var product_type = element.allProductName.type;
						i++;
						if (product_type == 'Product') {
							var msg_data = addDivs('product', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_product_view");
						} else if (product_type == 'byproduct') {
							var msg_data = addDivs('byproduct', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_byproduct_view");
						} else if (product_type == 'Raw Material') {
							var msg_data = addDivs('raw', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_raw_view");
						} else if (product_type == 'Fuel') {
							var msg_data = addDivs('fuel', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_fuel_view");
						} else if (product_type == 'hwp') {
							var msg_data = addDivs('hwp', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_hwp_view");
						} else if (product_type == 'hwpcf') {
							var msg_data = addDivs('hwpcf', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_hwpcf_view");
						} else if (product_type == 'nhwp') {
							var msg_data = addDivs('nhwp', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_nhwp_view");
						} else if (product_type == 'nhwpcf') {
							var msg_data = addDivs('nhwpcf', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_nhwpcf_view");
						} else if (product_type == 'Bio Medical') {
							var msg_data = addDivs('bio', element.allProductName.productName, element.quantity, element.unit.units, element.allProductName.status, element.allProductsId, element.unit.unitId, element.allProductName.allProductNameId, consentNo);
							$(msg_data).appendTo("#appendTable_bio_view");
						}
					});

				}
			});
		});

	$("#productionAccordion").accordion({
		heightStyle: 'content'
	});

}

function addEstablishData(type) {

	var fullName = getFullName(type);
	var modelTitle = "Add " + fullName;
	var unitOptions = getAllUnits();

	var extraDiv = "";
	var class1 = "col-7";
	if (type == "hwp" || type == "hwpcf") {
		var hzCategoryOptions = getAllHzCategory();

		class1 = "col-5";
		extraDiv = "<div class='col-2'>" +
			"<div class='form-group'>" +
			"<select class='select2' name='new_product_category' id='new_product_category'>" +
			"<option value=''>Select Category</option>" +
			hzCategoryOptions +
			"</select>" +
			"<div class='invalid-feedback'>Please select any !</div>" +
			"</div>" +
			"</div>";
	}
	var bodyForm = "<div class='row'>" +

		extraDiv +

		"<div class='" + class1 + "'>" +
		"<div class='form-group'>" +
		"<input type='text' id='new_product_name' class='form-control' placeholder='" + fullName + " Name'><div class=invalid-feedback>Please enter something !</div><i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-3'>" +
		"<div class='form-group'>" +
		"<input type='number' id='new_product_quantity' class='form-control' placeholder='Consent Quantity'><div class=invalid-feedback>Please enter something!</div><i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-2'>" +
		"<div class='form-group'>" +
		"<select class='select2' name='new_product_unit' id='new_product_unit'>" +
		"<option value=''>Select unit</option>" +
		unitOptions +
		"</select>" +
		"<div class='invalid-feedback'>Please select any !</div>" +
		"</div>" +
		"</div>"

		+
		"</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='saveCtoEData(\"" + type + "\",this)' class='btn btn-primary'>Add</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();
}

function addOperateData(type) {

	var fullName = getFullName(type);
	var modelTitle = "Add " + fullName;
	var unitOptions = getAllUnits();
	var ctoEstablishList = getConsentToEForCToO(type);
	var extraDiv = "";
	var class1 = "col-7";
	if (type == "hwp" || type == "hwpcf") {
		var hzCategoryOptions = getAllHzCategory();

		class1 = "col-5";
		extraDiv = "<div class='col-2'>" +
			"<div class='form-group'>" +
			"<select class='select2' name='new_product_category' id='new_product_category'>" +
			"<option value=''>Select Category</option>" +
			hzCategoryOptions +
			"</select>" +
			"<div class='invalid-feedback'>Please select any !</div>" +
			"</div>" +
			"</div>";
	}
	var bodyForm = "<div class='row'>" +

		extraDiv +

		"<div class='" + class1 + "'>" +
		"<div class='form-group'>" +
		"<select class='select2' name='new_product_name' id='new_product_name'>" +
		"<option value=''>Select Product Name</option>" +
		ctoEstablishList +
		"</select>" +
		"<div class='invalid-feedback'>Please select any !</div>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-3'>" +
		"<div class='form-group'>" +
		"<input type='number' id='new_product_quantity' class='form-control' placeholder='Consent Quantity'><div class=invalid-feedback>Please enter something!</div><i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-2'>" +
		"<div class='form-group'>" +
		"<select class='select2' name='new_product_unit' id='new_product_unit'>" +
		"<option value=''>Select unit</option>" +
		unitOptions +
		"</select>" +
		"<div class='invalid-feedback'>Please select any !</div>" +
		"</div>" +
		"</div>"

		+
		"</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='saveCtoEData(\"" + type + "\",this)' class='btn btn-primary'>Add</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();
}

function getConsentToEForCToO(type) {
	var allProductionList = "";
	$.ajax({
		type: "POST",
		url: "ajax-get-products-by-product-type?consentId=" + consentNo + "&productType=" + type,
		dataType: 'json',
		success: function (data) {

			var parseData = JSON.parse(data);
			$.each(parseData, function (index, element) {
				allProductNameId = element.allProductNameId;
				productName = element.productName;
				allProductionList += "<option value=" + allProductNameId + ">" + productName + "</option>";
			});
		},
		error: function (xhr, type) {
			alert('server error occoured');
		},
		async: false
	});

	return allProductionList;
}

function saveCtoEData(type, el) {
	var flag = 0;
	var consentNo = $("#consentNo").val();
	var product_name = "";
	if (consentTypee == "establish") {
		product_name = $("#new_product_name").val();
		flag += customInputValidator(product_name, "#new_product_name");

		if (type == "hwp" || type == "hwpcf") {
			var catNumber = $("#new_product_category").val();
			flag += customSelectValidator(catNumber, "new_product_category");
			product_name = catNumber + "-" + product_name;

		}
	} else {
		var skillsSelect = document.getElementById("new_product_name" + id);
		product_name = skillsSelect.options[skillsSelect.selectedIndex].text;
	}


	var quantity = $("#new_product_quantity").val();
	var units = $("#new_product_unit").val();
	flag += customInputValidator(quantity, "#new_product_quantity");
	flag += customSelectValidator(units, "new_product_unit");

	if (consentTypee == "establish") {
		var fwd_url = "ajax-view-consent-add-elements?productName=" + product_name + "&quantity=" + quantity + "&units=" + units + "&productType=" + type + "&consentNo=" + consentNo;
	} else {
		var fwd_url = "ajax-viewOList-consent-add-elements?productName=" + product_name + "&quantity=" + quantity + "&units=" + units + "&productType=" + type + "&consentNo=" + consentNo;
	}

	if (flag == 0) {
		$.ajax({
			url: fwd_url,
			dataType: 'text',
			cache: false,
			contentType: false,
			processData: false,
			type: 'post',
			success: function (res) {
				jBoxBottomRightBigNotice("Success", product_name + " Saved !!", "green", "2500");
				var modalId = $(el).closest('.modal').attr('id');
				$('#' + modalId).modal('toggle');
				getConsentDatas(consentTypee);
			},
			error: function (e) {
				jBoxBottomRightBigNotice("Error", " session expired /n", "red", "2000");
				//window.setTimeout(window.location = "logout", 7000)
			}
		});

	}
}

function addDivs(type, product_name, quantity, units, status, allProductsId, unitId, allProductNameId) {
	var isActive = Boolean(status == "Active");
	var statusTag = isActive ? "Disable" : "Unable";
	var icon = isActive ? "zmdi zmdi-close" : "zmdi zmdi-check-circle";
	var btnColor = isActive ? "btn-danger" : "teal darken-1";

	var extraBlock = "";
	if (type == "hwp" || type == "hwpcf") {
		var catNumber = "";
		var catNumbers = product_name.split("-");
		var hzDesc = getHzDescByNumber(catNumber[0]);

		if (catNumbers.length > 1) {
			product_name = catNumbers[1];
			catNumber = catNumbers[0];
		}
		extraBlock = "<td> <label class='mt-3'>" + catNumber + "</label></td>";
	}
	msg_data = "<tr>" +
		extraBlock +

		"<td>" +
		"<label class='mt-3'>" + product_name + "</label>" +
		"</td>"

		+
		"<td>" +
		"<label class='mt-3'>" + quantity + "</label>" +
		"</td>"

		+
		"<td>" +
		"<label class='mt-3'>" + units + "</label>" +
		"</td>"

		+
		"<td>" +
		"<label class='mt-3'>" + status + "</label>" +
		"</td>";
	if (consentTypee != 'operate') {

		msg_data += "<td>" +
			"<button class='btn btn-warning btn--icon-text' onclick='modifyProductModal(\"" + type + "\",\"" + product_name + "\",\"" + quantity + "\",\"" + units + "\",\"" + allProductNameId + "\",\"" + allProductsId + "\");'><i class='zmdi zmdi-edit'></i> Modify </button>" +
			"</td>";
	}

	msg_data += "<td>" +
		"<button class='btn " + btnColor + " btn--icon-text text-white' onclick='unableDisableProduction(" + allProductNameId + ",\"" + status + "\",\"" + product_name + "\");'><i class='" + icon + "'></i> " + statusTag + " </button>" +
		"</td>"


		+
		"</tr>"
	return msg_data;
}

function unableDisableProduction(id, status, product_name) {
	var isActive = Boolean(status == "Active");
	var statusTag = isActive ? "Inactive" : "Active";
	var unableDisable = isActive ? "Disable" : "Unable";

	var msg = "Are you sure, you want to " + unableDisable + " " + product_name + " !";
	swal({
			text: msg,
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-success",
			confirmButtonText: "Yes",
			cancelButtonText: "No",
			allowOutsideClick: false
		})
		.then((res) => {
			if (res.value == true) {
				jBoxBottomRightBigNotice("Success", product_name + " " + unableDisable + "  !", "green", "2000");
				changeItemStatus(id, statusTag);

			} else if (res.dismiss == "cancel") {
				jBoxBottomRightBigNotice("Warning", "You cancelled the operation", "yellow", "2000");
			}
		});
}

function changeItemStatus(id, statusTag) {
	$.ajax({
		url: "ajax-view-consent-unable-disable-product?productNameId=" + id + "&status=" + statusTag,
		type: "POST",
		success: function (data) {
			getConsentDatas(consentTypee);
		},
		error: function (e) {
			//called when there is an error
			//console.log(e.message);
		}
	});
}

function modifyProductModal(type, proName, proQuantity, proUnit, allProductNameId, allProductsId) {
	var fullName = getFullName(type);
	var modelTitle = "Modify : " + proName + " (" + fullName + ")";
	var unitOptions = getAllUnits();


	var extraDiv = "";
	var class1 = "col-7";
	if (type == "hwp" || type == "hwpcf") {
		var hzCategoryOptions = getAllHzCategory();
		var tempName = proName.split("-");

		if (tempName.length > 1) {
			proName = tempName[1];
		}
		class1 = "col-5";
		extraDiv = "<div class='col-2'>" +
			"<div class='form-group'>" +
			"<label>Category</label>" +
			"<select class='select2' name='product_category_modify' id='product_category_modify'>" +
			"<option value=''>Select Category</option>" +
			hzCategoryOptions +
			"</select>" +
			"<div class='invalid-feedback'>Please select any !</div>" +
			"</div>" +
			"</div>";
	}

	var bodyForm = "<div class='row mt-4'>" +
		extraDiv +
		"<div class='" + class1 + "'>" +
		"<div class='form-group'>" +
		"<label>Name</label>" +
		"<input type='text' id='product_name_modify' class='form-control' value ='" + proName + "' disabled>" +
		"<input type='hidden' id='product_name_id_modify' class='form-control' value ='" + allProductNameId + "'>" +
		"<input type='hidden' id='product_id_modify' class='form-control' value ='" + allProductsId + "'>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-3'>" +
		"<div class='form-group'>" +
		"<label>Quantity</label>" +
		"<input type='number' id='product_quantity_modify' class='form-control' value='" + proQuantity + "'><div class=invalid-feedback>Invalid!</div><i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-2'>" +
		"<div class='form-group'>" +
		"<label>Unit</label>" +
		"<select class='select2' name='product_unit_modify' id='product_unit_modify'>" +
		"<option value=''>Select unit</option>" +
		unitOptions +
		"</select>" +
		"<div class='invalid-feedback'>Please select any !</div>" +
		"</div>" +
		"</div>"

		+
		"</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='modifyProduction(\"" + type + "\",this)' class='btn btn-primary'>Modify</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})

	makeSelect2();
	defaultSelect2("product_unit_modify",proUnit)
}

function modifyProduction(type,el) {
	var flag = 0;
	var product_name_id = $("#product_name_id_modify").val();
	var product_name = $("#product_name_modify").val();

	if (type == "hwp" || type == "hwpcf") {
		var category = $("#product_category_modify").val();
		flag += customSelectValidator(category, "product_category_modify");
		product_name = category + "-" + product_name;
	}

	var productId = $("#product_id_modify").val();
	var quantity = $("#product_quantity_modify").val();
	var units = $("#product_unit_modify").val();

	flag += customInputValidator(quantity, "#product_quantity_modify");
	flag += customSelectValidator(units, "product_unit_modify");

	if (flag == 0) {
		var fwd_url = "ajax-view-consent-modify-production?productId=" + productId + "&productName=" + product_name + "&quantity=" + quantity + "&units=" + units + "&productNameId=" + product_name_id;

		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {
				jBoxBottomRightBigNotice("Success", product_name + " Data Modified !!", "green", "2500");
				var modalId = $(el).closest('.modal').attr('id');
				$('#' + modalId).modal('toggle');
				getConsentDatas(consentTypee);
			}
		};
		xmlhttp.open("GET", fwd_url, true);
		xmlhttp.send();
	}

}


function openStack() {
	fwd_url = "ajax-consent-view-get-stack-details?type=" + consentTypee + "&consent_no=" + consentNo;
	$("#appendStack").empty();

	if (consentTypee != 'operate') {
		var htmlStackUploadBtn = "<div class='col-2 offset-10'>" +
			"<div class='form-group'><button type='button' class='btn btn-warning btn--icon-text brown lighten-1' data-toggle='modal' data-target='#upload-stack-excel-modal'><i class='zmdi zmdi-plus zmdi-hc-fw'></i>Add</button></div>" +
			"</div>";

		$(htmlStackUploadBtn).appendTo("#appendStack");
	}

	var unitOptions = getAllUnits();
	$.ajax({
		type: "GET",
		url: fwd_url,
		dataType: "json",
		success: function (data) {
			var parseData = JSON.parse(data);
			var btn_html = "";

			var htmlContent1 = "<div class='row'>" +
				"<div class='table-responsive'>" +
				"<table class='table table-bordered table-hover normal' id='table-stack'>" +
				"<thead><tr>" +
				"<th>Stack name</th>" +
				"<th>Attached to</th>" +
				"<th>Capacity</th>" +
				"<th>Fuel type</th>" +
				"<th>Fuel quan.</th>" +
				"<th>MoC</th>" +
				"<th>Shape</th>" +
				"<th>Height</th>" +
				"<th>Dia</th>" +
				"<th>Stack Pollutants</th>" +
				"<th>Modify / Delete</th>" +
				"</tr></thead>" +
				"<tbody>";

			var htmlBody = "";
			$.each(parseData, function (index, element) {
				var stackId = element.stackId;
				var StackName = element.stackName;
				var stackAttachedTo = element.stackAttachedTo;
				var stackCapacity = element.stackCapacity;
				var stackCapacityUnit = element.stackCapacityUnit;

				var stackFuelType = element.stackFuelType;
				var stackFuelQuant = element.stackFuelQuant;
				var stackFuelUnits = element.stackFuelUnits;
				var stackMatCons = element.stackMatCons;
				var stackShape = element.stackShape;
				var stackHeight = element.stackHeight;
				var stackHtUnits = element.stackHtUnits;
				var stackDiam = element.stackDiam;
				var stackDiamUnits = element.stackDiamUnits;
				var stackPollDatas = element.stackPollDatas;

				htmlContent1 += "<tr>" +
					"<td>" +
					"<label class='mt-3'>" + StackName + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackAttachedTo + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackCapacity + " " + stackCapacityUnit + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackFuelType + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackFuelQuant + " " + stackFuelUnits + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackMatCons + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackShape + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackHeight + " " + stackHtUnits + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + stackDiam + " " + stackDiamUnits + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'> ";

				var stackModifyModal = "<div class='modal fade' id='modifyStackModal_" + stackId + "' data-backdrop='static' tabindex='-1'>" +
					"<div class='modal-dialog modal-lg'>" +
					"<div class='modal-content'>" +
					"<div class='modal-header'> <h5 class='modal-title pull-left'>Modify Stack " + StackName + " " + stackAttachedTo + "</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'> <span aria-hidden='true'>&times;</span> </button> </div>" +
					"<div class='modal-body'>" +
					"<div id='stackNo_" + stackId + "'>" +
					"<div class='row'>" +
					"<div class='col-4'>" +
					"<div class='form-group'>" +
					"<label>Stack Name</label>" +
					"<input type='text' id='modify_stack_name" + stackId + "' class='form-control' value='" + StackName + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-4'>" +
					"<div class='form-group'>" +
					"<label>Attached to</label>" +
					"<select class='select2' name='modify_stack_name_attachTo" + stackId + "' id='modify_stack_name_attachTo" + stackId + "'>" +
					"<option value='" + stackAttachedTo + "'>" + stackAttachedTo + "</option>" +
					"<option value='DG Sets'>DG Sets</option>" +
					"<option value='Process'>Process</option>" +
					"<option value='Boilers'>Boilers</option>" +
					"<option value='Others'>Others</option>" +
					"</select>" +
					"<div class='invalid-feedback'>Please select any !</div>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Capacity</label>" +
					"<input type='Number' class='form-control' id='modify_stack_capacity" + stackId + "' value='" + stackCapacity + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Units</label>" +
					"<input type='text' class='form-control' id='modify_stack_capacity_unit" + stackId + "' value='" + stackCapacityUnit + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-4'>" +
					"<div class='form-group'>" +
					"<label>Material of Construction</label>" +
					"<input type='text' class='form-control' id='modify_stack_matCons" + stackId + "' value='" + stackMatCons + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-4'>" +
					"<div class='form-group'>" +
					"<label>Shape</label>" +
					"<input type='text' class='form-control' id='modify_stack_shape" + stackId + "' value='" + stackShape + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-4'>" +
					"<div class='form-group'>" +
					"<label>Fuel Type</label>" +
					"<input type='text' class='form-control' id='modify_stack_fuel_type" + stackId + "' value='" + stackFuelType + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Height</label>" +
					"<input type='number' class='form-control' id='modify_stack_height" + stackId + "' value='" + stackHeight + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Height Units</label>" +
					"<input type='text' class='form-control' id='modify_stack_height_unit" + stackId + "' value='" + stackHtUnits + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Stack Diameter</label>" +
					"<input type='number' class='form-control' id='modify_stack_diam" + stackId + "' value='" + stackDiam + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Stack Diameter Units</label>" +
					"<input type='text' class='form-control' id='modify_stack_diam_unit" + stackId + "' value='" + stackDiamUnits + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Fuel Quantity</label>" +
					"<input type='number' class='form-control' id='modify_stack_fuel_quan" + stackId + "' value='" + stackFuelQuant + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-2'>" +
					"<div class='form-group'>" +
					"<label>Fuel Quantity Units</label>" +
					"<select class='select2' name='modify_stack_fuel_unit" + stackId + "' id='modify_stack_fuel_unit" + stackId + "'" +
					unitOptions +
					"</select>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"


					+
					"</div>"

					+
					"<div class='row'>" +
					"<div class='col-12'><p class='font-weight-700'>Stack Pollutants</p></div>" +
					"<div class='col-12'>" +
					"<div class='table-responsive'>" +
					"<table class='table table-bordered table-hover normal'>" +
					"<thead><tr>" +
					"<th>Pollutant Name</th>" +
					"<th>Limit</th>" +
					"<th>Units</th>" +
					"<th>Delete</th>"
				"</tr></thead>" +
				"<tbody>";

				$.each(stackPollDatas, function (index, element) {
					var stackPollId = element.stackPollId;
					var stackPollName = element.stackPollName;
					var stackPollLimit = element.stackPollLimit;
					var stackPollUnit = element.stackPollUnit;

					htmlContent1 += stackPollName + ", ";

					stackModifyModal += "<tr>" +
						"<td>" +
						"<label class='mt-3'> " + stackPollName + " </label>" +
						"</td>"

						+
						"<td>" +
						"<label class='mt-3'> " + stackPollLimit + " </label>" +
						"</td>"

						+
						"<td>" +
						"<label class='mt-3'> " + stackPollUnit + " </label>" +
						"</td>"

						+
						"<td>" +
						"<button class='btn btn-danger' onclick='deletePollutant(\"" + stackPollId + "\",\"" + stackPollName + "\",\"stack\",this);'><i class='fa fa-trash' aria-hidden='true'></i></button>" +
						"</td>" +
						"</tr>";

				});
				stackModifyModal += "</tbody>" +
					"</table></div></div>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"<div class='modal-footer'>" +
					"<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='addConsentStackAmbientPoll(\"stack\",\"" + stackId + "\",\"" + StackName + "\",\"" + stackAttachedTo + "\")' class='btn btn-primary'>Add Pollutant</button><button type='submit' onclick='modifyStack(\"stack\",\"" + stackId + "\",this)' class='btn btn-primary'>Modify</button>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"</div>";
				$(stackModifyModal).appendTo("#stackModalsHidden");


				htmlContent1 += "</label>" +
					"</td>" +
					"<td>" +
					"<button class='btn btn-warning' data-toggle='modal' data-target='#modifyStackModal_" + stackId + "'><i class='zmdi zmdi-edit'></i></button>" +
					" | " +
					"<button class='btn btn-danger' onclick='deleteStackAmbientConfirmation(\"" + stackId + "\",\"stack\");'><i class='zmdi zmdi-close'></i></button>" +
					"</td>" +

					"</tr>";

			});

			"</tbody>" +
			"</table>" +
			"</div>" +
			"</div>";

			$(htmlContent1).appendTo("#appendStack");
			makeSelect2()
		},
		error: function (e) {
			console.log(e.message);
		}
	});

}

function deleteStackAmbientConfirmation(id, type) {
	var msg = "Are you sure, you want to delete this " + type + " !";
	swal({
			text: msg,
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-success",
			confirmButtonText: "Yes",
			cancelButtonText: "No",
			allowOutsideClick: false
		})
		.then((res) => {
			if (res.value == true) {
				deleteStackAmbient(id, type)
			}
		});
}

function modifyStackModal(type, idd, name, attachTo) {

	var modelTitle = "Modify " + type + " " + name + " " + attachTo;
	var pollsDatas = $('#stackNo_' + idd).html();

	var bodyForm = pollsDatas;
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='addConsentStackAmbientPoll(\"" + type + "\",\"" + idd + "\",\"" + name + "\",\"" + attachTo + "\")' class='btn btn-primary'>Add Pollutant</button><button type='submit' onclick='modifyStack(\"" + type + "\",\"" + idd + "\")' class='btn btn-primary'>Modify</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})

	makeSelect2();
}

function addConsentStackAmbientPoll(type, idd, name, attachTo) {

	if (type == "stack") {
		var temp = "st";
	} else if (type == "ambient") {
		var temp = "amb";
	}
	var unitOptions = getAllUnits();
	var modelTitle = "Add Pollutant for : " + type + " " + name + " " + attachTo;
	var bodyForm = "<div class='row'>" +
		"<div class='col-4'>" +
		"<div class='form-group'>" +
		"<input type='text' id='new_extra_" + temp + "_poll_name' class='form-control' placeholder='Pollutant Name'>" +
		"<div class='invalid-feedback'>Invalid !</div>" +
		"<i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-4'>" +
		"<div class='form-group'>" +
		"<input type='number' id='new_extra_" + temp + "_poll_limit' class='form-control' placeholder='Limit'>" +
		"<div class='invalid-feedback'>Invalid !</div>" +
		"<i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-4'>" +
		"<div class='form-group'>" +
		"<select class='select2' data-placeholder='Select Unit' name='new_extra_" + temp + "_poll_unit' id='new_extra_" + temp + "_poll_unit'>" +
		unitOptions +
		"</select>" +
		"<div class='invalid-feedback'>Invalid !</div>" +
		"<i class='form-group__bar'></i>" +
		"</div>" +
		"</div>" +
		"</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='button' onclick='savePollConsent(\"" + type + "\",\"" + idd + "\",this)' class='btn btn-primary'>Save Pollutant</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();
}

function savePollConsent(type, idd, el) {
	if (type == "stack") {
		var temp = "st";
	} else if (type == "ambient") {
		var temp = "amb";
	}

	var flag = 0;
	var poll_name = $("#new_extra_" + temp + "_poll_name").val();
	var limit = $("#new_extra_" + temp + "_poll_limit").val();
	var units = $("#new_extra_" + temp + "_poll_unit").val();

	flag += customInputValidator(poll_name, "#new_extra_" + temp + "_poll_name");
	flag += customInputValidator(limit, "#new_extra_" + temp + "_poll_limit");
	flag += customSelectValidator(units, "new_extra_" + temp + "_poll_unit");

	if (flag == 0) {
		var fwd_url = "ajax-add-pollutant?type=" + type + "&cons_no=" + idd + "&poll_name=" + poll_name + "&limit=" + limit + "&units=" + units;
		$.ajax({
			type: "GET",
			url: fwd_url,
			dataType: "text",
			success: function (data) {
				if (data == 1) {
					$('.modal').modal('hide');
					jBoxBottomRightBigNotice("Success", type + " Pollutant Added !!", "green", "2000");
					openStack();
				} else {
					$('.modal').modal('hide');
					jBoxBottomRightBigNotice("Error", "Something went wrong !!", "red", "2000");
				}
			}
		});
	}

}

function deleteStackAmbient(id, type) {
	var fwd_url = "ajax-delete-pollutant?type=" + type + "Data&product_id=" + id;
	$.ajax({
		type: "GET",
		url: fwd_url,
		dataType: "text",
		success: function (data) {
			if (data) { // == "successsuccess"
				jBoxBottomRightBigNotice("Success", type + " Deleted !!", "green", "2500");
				if (type == "stack")
					openStack();
				else
					openAmbient();

				$('.modal').modal('hide');
			} else {
				jBoxBottomRightBigNotice("Error", "Oops !, Something went wrong.", "red", "2500");
			}
		}
	});
}

function deletePollutant(PollId, pollName, type, el) {
	var flag = 0;
	var msg = "Are you sure, you want to delete pollutant " + pollName + " !";
	swal({
			text: msg,
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-success",
			confirmButtonText: "Yes",
			cancelButtonText: "No",
			allowOutsideClick: false
		})
		.then((res) => {
			if (res.value == true) {
				var fwd_url = "ajax-delete-pollutant?type=" + type + "&product_id=" + PollId;
				$.ajax({
					type: "GET",
					url: fwd_url,
					dataType: "text",
					success: function (data) {
						if (data == "success") {

							var modalId = $(el).closest('.modal').attr('id');
							$('#' + modalId).modal('toggle');

							if (type == "stack")
								openStack();
							else
								openAmbient();

							jBoxBottomRightBigNotice("Success", pollName + " Deleted !!", "green", "2500");
						} else {
							jBoxBottomRightBigNotice("Error", "Oops !, Something went wrong.", "red", "2500");
						}

					}
				});
			}
		});


}

function modifyStack(type, idd, el) {
	//consentNo
	var flag = 0;
	var stack_name = $("#modify_stack_name" + idd).val();
	var capacity = $("#modify_stack_capacity" + idd).val();
	var capacity_units = $("#modify_stack_capacity_unit" + idd).val();
	var fuel_type = $("#modify_stack_fuel_type" + idd).val();

	var fuel_quant = $("#modify_stack_fuel_quan" + idd).val();
	var shape = $("#modify_stack_shape" + idd).val();
	var height = $("#modify_stack_height" + idd).val();
	var ht_units = $("#modify_stack_height_unit" + idd).val();
	var diam = $("#modify_stack_diam" + idd).val();
	var diam_units = $("#modify_stack_diam_unit" + idd).val();
	var mat_cons = $("#modify_stack_matCons" + idd).val();

	flag += customInputValidator(stack_name, "#modify_stack_name" + idd);
	flag += customInputValidator(capacity, "#modify_stack_capacity" + idd);
	flag += customInputValidator(capacity_units, "#modify_stack_capacity_unit" + idd);
	flag += customInputValidator(fuel_type, "#modify_stack_fuel_type" + idd);
	flag += customInputValidator(fuel_quant, "#modify_stack_fuel_quan" + idd);
	flag += customInputValidator(shape, "#modify_stack_shape" + idd);
	flag += customInputValidator(height, "#modify_stack_height" + idd);
	flag += customInputValidator(ht_units, "#modify_stack_height_unit" + idd);
	flag += customInputValidator(diam, "#modify_stack_diam" + idd);
	flag += customInputValidator(diam_units, "#modify_stack_diam_unit" + idd);
	flag += customInputValidator(mat_cons, "#modify_stack_matCons" + idd);

	var attached_to = $("#modify_stack_name_attachTo" + idd).val();
	var fuel_units = $("#modify_stack_fuel_unit" + idd).val();

	flag += customSelectValidator(attached_to, "modify_stack_name_attachTo" + idd);
	flag += customSelectValidator(fuel_units, "modify_stack_fuel_unit" + idd);

	if (flag == 0) {
		var fwd_url = "ajax-modify-stack-ambient?stackid=" +
			idd + "&stack_name=" + stack_name + "&attached_to=" + attached_to + "&capacity=" +
			capacity + "&capacity_units=" + capacity_units + "&fuel_type=" + fuel_type + "&fuel_quant=" +
			fuel_quant + "&fuel_units=" + fuel_units + "&shape=" + shape + "&height=" +
			height + "&ht_units=" + ht_units + "&diam=" + diam + "&diam_units=" + diam_units + "&mat_cons=" + mat_cons + "&type=stack";

		$.ajax({
			type: "GET",
			url: fwd_url,
			dataType: "text",
			success: function (data) {
				if (data == "success") {
					var modalId = $(el).closest('.modal').attr('id');
					$('#' + modalId).modal('toggle');
					jBoxBottomRightBigNotice("Success", "Stack Details Updated !!", "green", "2000");
					openStack();
				} else {
					jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
				}
			}
		});
	}
}

function modifyAmbientData(type, idd, el) {
	var flag = 0;
	var loc_ident = $("#modify_ambient_name" + idd).val();
	var criteria = $("#modify_ambient_name_attachTo" + idd).val();
	flag += customInputValidator(loc_ident, "#modify_ambient_name" + idd);
	flag += customSelectValidator(criteria, "modify_ambient_name_attachTo" + idd);

	if (flag == 0) {
		var fwd_url = "ajax-modify-stack-ambient?ambientid=" +
			idd + "&loc_ident=" + loc_ident + "&criteria=" + criteria + "&type=ambient";
		$.ajax({
			type: "GET",
			url: fwd_url,
			dataType: "text",
			success: function (data) {
				if (data == "success") {
					var modalId = $(el).closest('.modal').attr('id');
					$('#' + modalId).modal('toggle');
					jBoxBottomRightBigNotice("Success", "Ambient Details Updated !!", "green", "2000");
					openAmbient();
				} else {
					jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
				}
			}
		});
	}


}

function saveExcelSheet(type) {
	var consent_no = $("#consent_no").val();
	var valid = 0;
	var filename = $('[name=' + type + 'File]').val();
	//var type = "stack";
	if (filename != "") {
		var file_data = $('[name=' + type + 'File]').prop('files')[0]; //alert(file_data)
		filename1 = file_data.name;
		if (filename1 == "") {
			valid--;
		}
	} else {
		valid--;
	}

	var stackparameterfilename = $('[name=' + type + 'Parameter]').val();
	if (stackparameterfilename != "") {
		var stackparameter_file_data = $('[name=' + type + 'Parameter]').prop('files')[0]; //alert(file_data)
		filename1 = stackparameter_file_data.name;
		if (filename1 == "") {
			valid--;
		}
	} else {
		valid--;
	}
	if (valid == 0) {
		var form_data = new FormData();
		form_data.append('file', file_data);
		form_data.append('file', stackparameter_file_data);

		$.ajax({
			url: "ajax-save-data?action=fileUploading&fn=" + type + "&consent_no=" + consentNo,
			contentType: false,
			processData: false,
			data: form_data,
			type: 'post',
			success: function (res) {
				jBoxBottomRightBigNotice("Success", "Stack Data Uploaded !!", "green", "2000");
				$('#upload-' + type + '-excel-modal').modal('hide');
				openStack();
			}
		});
	}
}

function openAmbient() {
	fwd_url = "ajax-consent-view-get-ambient-details?type=" + consentTypee + "&consentId=" + consentNo;
	$("#appendAmbient").empty();

	if (consentTypee != 'operate') {
		var htmlStackUploadBtn = "<div class='col-2 offset-10'>" +
			"<div class='form-group'><button type='button' class='btn btn-warning btn--icon-text brown lighten-1' data-toggle='modal' data-target='#upload-ambient-excel-modal'><i class='zmdi zmdi-plus zmdi-hc-fw'></i>Add</button></div>" +
			"</div>";

		$(htmlStackUploadBtn).appendTo("#appendAmbient");
	}


	$.ajax({
		type: "GET",
		url: fwd_url,
		dataType: "json",
		success: function (data) {
			var parseData = JSON.parse(data);
			var btn_html = "";

			var htmlContent1 = "<div class='row'>" +
				"<div class='table-responsive'>" +
				"<table class='table table-bordered table-hover normal' id='table-stack'>" +
				"<thead><tr>" +
				"<th>#</th>" +
				"<th>Ambient Location</th>" +
				"<th>Criteria</th>" +
				"<th>Ambient Pollutants</th>" +
				"<th>Modify / Delete</th>" +
				"</tr></thead>" +
				"<tbody>";

			var htmlBody = "";
			var count = 1;
			$.each(parseData, function (index, element) {
				var ambientId = element.ambientId;
				var ambientLocName = element.ambientLocName;
				var ambientCriteria = element.ambientCriteria;
				var ambientPollDatas = element.ambientPollDatas;


				htmlContent1 += "<tr>" +
					"<td>" +
					"<label class='mt-3'>" + count + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + ambientLocName + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'>" + ambientCriteria + "</label>" +
					"</td>" +

					"<td>" +
					"<label class='mt-3'> ";

				var stackModifyModal = "<div class='modal fade' id='modifyAmbientModal_" + ambientId + "' data-backdrop='static' tabindex='-1'>" +
					"<div class='modal-dialog modal-lg'>" +
					"<div class='modal-content'>" +
					"<div class='modal-header'> <h5 class='modal-title pull-left'>Modify Ambient " + ambientLocName + "</h5><button type='button' class='close' data-dismiss='modal' aria-label='Close'> <span aria-hidden='true'>&times;</span> </button> </div>" +
					"<div class='modal-body'>" +

					"<div id='ambientNo_" + ambientId + "'>" +
					"<div class='row'>" +
					"<div class='col-6'>" +
					"<div class='form-group'>" +
					"<label>Stack Name</label>" +
					"<input type='text' id='modify_ambient_name" + ambientId + "' class='form-control' value='" + ambientLocName + "'>" +
					"<div class='invalid-feedback'>Invalid !</div>" +
					"<i class='form-group__bar'></i>" +
					"</div>" +
					"</div>"

					+
					"<div class='col-6'>" +
					"<div class='form-group'>" +
					"<label>Attached to</label>" +
					"<select class='select2' name='modify_ambient_name_attachTo" + ambientId + "' id='modify_ambient_name_attachTo" + ambientId + "'>" +
					"<option value='" + ambientCriteria + "'>" + ambientCriteria + "</option>" +
					"<option value='Up Wind'>Up Wind</option>" +
					"<option value='Down Wind'>Down Wind</option>" +
					"<option value='Cross Wind'>Cross Wind</option>" +
					"<option value='Other'>Other</option>" +
					"</select>" +
					"<div class='invalid-feedback'>Please select any !</div>" +
					"</div>" +
					"</div>" +

					"</div>"

					+
					"<div class='row'>" +
					"<div class='col-12'><p class='font-weight-700'>Ambient Pollutants</p></div>" +
					"<div class='col-12'>" +
					"<div class='table-responsive'>" +
					"<table class='table table-bordered table-hover normal'>" +
					"<thead><tr>" +
					"<th>Pollutant Name</th>" +
					"<th>Limit</th>" +
					"<th>Units</th>" +
					"<th>Delete</th>"
				"</tr></thead>" +
				"<tbody>";

				$.each(ambientPollDatas, function (index, element) {

					var ambientPollId = element.ambientPollId;
					var ambientPollName = element.ambientPollName;
					var ambientPollLimit = element.ambientPollLimit;
					var ambientkPollUnit = element.ambientkPollUnit;

					htmlContent1 += ambientPollName + ", ";

					stackModifyModal += "<tr>" +
						"<td>" +
						"<label class='mt-3'> " + ambientPollName + " </label>" +
						"</td>"

						+
						"<td>" +
						"<label class='mt-3'> " + ambientPollLimit + " </label>" +
						"</td>"

						+
						"<td>" +
						"<label class='mt-3'> " + ambientkPollUnit + " </label>" +
						"</td>"

						+
						"<td>" +
						"<button class='btn btn-danger' onclick='deletePollutant(\"" + ambientPollId + "\",\"" + ambientPollName + "\",\"ambient\",this);'><i class='fa fa-trash' aria-hidden='true'></i></button>" +
						"</td>" +
						"</tr>";
				});

				stackModifyModal += "</tbody>" +
					"</table></div></div>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"<div class='modal-footer'>" +
					"<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='addConsentStackAmbientPoll(\"ambient\",\"" + ambientId + "\",\"" + ambientLocName + "\",\"" + ambientCriteria + "\")' class='btn btn-primary'>Add Pollutant</button><button type='submit' onclick='modifyAmbientData(\"ambient\",\"" + ambientId + "\",this)' class='btn btn-primary'>Modify</button>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"</div>";
				$(stackModifyModal).appendTo("#ambientModalsHidden");

				htmlContent1 += "</label>" +
					"</td>" +
					"<td>" +
					"<button class='btn btn-warning' data-toggle='modal' data-target='#modifyAmbientModal_" + ambientId + "'><i class='zmdi zmdi-edit'></i></button>" +
					" | " +
					"<button class='btn btn-danger' onclick='deleteStackAmbientConfirmation(\"" + ambientId + "\",\"stack\");'><i class='zmdi zmdi-close'></i></button>" +
					"</td>" +

					"</tr>";

			});

			"</tbody>" +
			"</table>" +
			"</div>" +
			"</div>";

			$(htmlContent1).appendTo("#appendAmbient");
			makeSelect2()
		},
		error: function (e) {
			console.log(e.message);
		}
	});

}

function openWaterPollutants() {
	fwd_url = "ajax-view-consent-view-water-pollutant-details?type=" + consentTypee + "&consent_no=" + consentNo;
	$("#appendWaterPollutant").empty();


	var unitOptions = getAllUnits();
	$.ajax({
		type: "GET",
		url: fwd_url,
		dataType: "json",
		success: function (data) {
			var parseData = JSON.parse(data);
			var objSize = Object.keys(parseData).length;
			var keyArray = Object.keys(parseData);
			for (var i = 0; i < keyArray.length; i++) {
				var count = 1;
				var hmtl_content = "";
				var varKeyName = keyArray[i];
				var allDataArray = new Array();
				allDataArray = parseData[varKeyName];
				varKeyName = (varKeyName == "eff") ? "Effluent" : "Sewage";
				var html_btn = "<div class='row mt-4'><div class='col-12'><center><h5 class='font-weight-700'>" + varKeyName + " Pollutant</h5></center></div><div class='col-2 offset-10'><div class='form-group'><button type='button' class='btn btn-warning btn--icon-text brown lighten-1'onclick='addEstablishWaterPollutant(\"" + varKeyName + "\");'><i class='zmdi zmdi-plus zmdi-hc-fw'></i>Add</button></div></div></div>";

				hmtl_content += html_btn;

				hmtl_content += "<div class='row'>" +
					"<div class='table-responsive'>" +
					"<table class='table table-bordered table-hover normal'>" +
					"<thead><tr><th>#</th>" +
					"<th>Pollutant name</th>" +
					"<th>Limit</th>" +
					"<th>Unit</th>" +
					"<th>Modify / Delete</th>" +
					"</tr></thead>" +
					"<tbody>";
				$.each(allDataArray, function (index, element) {
					var pollId = element.pollId;
					var pollName = element.pollName;
					var pollQuantity = element.pollQuantity;
					var pollUnit = element.pollUnit;
					pollUnit = (pollUnit != "") ? pollUnit : "-";

					hmtl_content += "<tr>" +
						"<td>" +
						"<label class='mt-3'>" + count + "</label>" +
						"</td>"

						+
						"<td>" +
						"<label class='mt-3'>" + pollName + "</label>" +
						"</td>"

						+
						"<td>" +
						"<label class='mt-3'>" + pollQuantity + "</label>" +
						"</td>"

						+
						"<td>" +
						"<label class='mt-3'>" + pollUnit + "</label>" +
						"</td>"

						+
						"<td>" +
						"<button class='btn btn-warning btn--icon-text' onclick='modifyWaterPollModal(\"" + pollId + "\",\"" + pollName + "\",\"" + pollQuantity + "\",\"" + pollUnit + "\",\"" + varKeyName + "\");'><i class='zmdi zmdi-edit'></i> Modify </button>" +
						" | " +
						"<button class='btn btn-danger btn--icon-text text-white' onclick='deleteWaterPollConfirmation(\"" + varKeyName + "\",\"" + pollId + "\",\"" + pollName + "\"  ,\"" + consentTypee + "\" );'><i class='fa fa-trash'></i> DELETE </button>" +
						"</td>";
					count++;

				});

				"</tbody>" +
				"</table>" +
				"</div>" +
				"</div>";

				$(hmtl_content).appendTo("#appendWaterPollutant");
			}
			console.log(data);
		},
		error: function (e) {
			console.log(e.message);
		}
	});
}

function modifyWaterPollModal(pollId, pollName, pollQuantity, pollUnit, pollType) {

	var modelTitle = "Modify : " + pollName + " (" + pollType + " Pollutant )";
	var unitOptions = getAllUnits();
	var isDisable = (pollUnit != "-") ? "" : "disabled";
	var bodyForm = "<div class='row mt-4'>" +
		"<div class='col-7'>" +
		"<div class='form-group'>" +
		"<label>Name</label>" +
		"<input type='text' id='" + pollType + "_name_modify' class='form-control' value ='" + pollName + "' disabled>" +
		"<input type='hidden' id='" + pollType + "_id_modify' class='form-control' value ='" + pollId + "'>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-3'>" +
		"<div class='form-group'>" +
		"<label>Quantity</label>" +
		"<input type='number' id='" + pollType + "_quantity_modify' class='form-control' value='" + pollQuantity + "'><div class='invalid-feedback'>Invalid!</div><i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-2'>" +
		"<div class='form-group'>" +
		"<label>Unit</label>" +
		"<select class='select2' name='" + pollType + "_unit_modify' id='" + pollType + "_unit_modify' " + isDisable + ">" +
		"<option value=''>Select unit</option>" +
		unitOptions +
		"</select>" +
		"<div class='invalid-feedback'>Please select any !</div>" +
		"</div>" +
		"</div>"

		+
		"</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='modifyWaterPoll(\"" + pollType + "\",this)' class='btn btn-primary'>Modify</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})

	makeSelect2();

}

function modifyWaterPoll(pollType, el) {

	var flag = 0;
	var units = "";
	var pollName = $("#" + pollType + "_name_modify").val();
	var pollId = $("#" + pollType + "_id_modify").val();

	var pollQuantity = $("#" + pollType + "_quantity_modify").val();

	flag += customInputValidator(pollQuantity, "#" + pollType + "_quantity_modify");

	if (pollName == "pH") {
		units = "37"
	} else {
		units = $("#" + pollType + "_unit_modify").val();
		flag += customSelectValidator(units, "" + pollType + "_unit_modify");
	}
	if (pollType == "Effluent") {
		var typee = "modifyEffluent";
	} else if (pollType == "Sewage") {
		var typee = "modifySewage";
	}


	if (flag == 0) {
		var fwd_url = "ajax-add-pollutant?type=" + typee + "&cons_no=" + consentNo + "&poll_id=" + pollId + "&poll_name=" + pollName + "&limit=" + pollQuantity + "&units=" + units;
		$.ajax({
			type: "GET",
			url: fwd_url,
			dataType: "text",
			success: function (data) {
				if (data == "1") {
					var modalId = $(el).closest('.modal').attr('id');
					$('#' + modalId).modal('toggle');
					jBoxBottomRightBigNotice("Success", pollName + " Pollutant Updated !!", "green", "2000");
					openWaterPollutants();
				} else {
					jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
				}
			}
		});
	}
}

function addEstablishWaterPollutant(type) {

	var modelTitle = "Add Extra " + type + " Pollutant";
	var unitOptions = getAllUnits();

	var extraDiv = "";
	var bodyForm = "<div class='row'>" +

		"<div class='col-7'>" +
		"<div class='form-group'>" +
		"<input type='text' id='new_" + type + "_name' class='form-control' placeholder='" + type + " Pollutant Name'><div class=invalid-feedback>Please enter something !</div><i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-3'>" +
		"<div class='form-group'>" +
		"<input type='number' id='new_" + type + "_quantity' class='form-control' placeholder='Limit'><div class=invalid-feedback>Please enter something!</div><i class='form-group__bar'></i>" +
		"</div>" +
		"</div>"

		+
		"<div class='col-2'>" +
		"<div class='form-group'>" +
		"<select class='select2' name='new_" + type + "_unit' id='new_" + type + "_unit'>" +
		"<option value=''>Select unit</option>" +
		unitOptions +
		"</select>" +
		"<div class='invalid-feedback'>Please select any !</div>" +
		"</div>" +
		"</div>"

		+
		"</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='saveExtraWaterPoll(\"" + type + "\",this)' class='btn btn-primary'>Add</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();
}

function saveExtraWaterPoll(type, el) {

	var flag = 0;
	var units = "";
	var pollName = $("#new_" + type + "_name").val();

	var limit = $("#new_" + type + "_quantity").val();

	flag += customInputValidator(limit, "#new_" + type + "_quantity");

	if (pollName == "pH") {
		units = "37"
	} else {
		units = $("#new_" + type + "_unit").val();
		flag += customSelectValidator(units, "new_" + type + "_unit");
	}

	var fwd_url = "ajax-add-pollutant?type=" + type + "&cons_no=" + consentNo + "&poll_name=" + pollName + "&limit=" + limit + "&units=" + units;
	$.ajax({
		type: "GET",
		url: fwd_url,
		dataType: "text",
		success: function (data) {
			if (data == "1") {
				var modalId = $(el).closest('.modal').attr('id');
				$('#' + modalId).modal('toggle');
				jBoxBottomRightBigNotice("Success", pollName + " Pollutant Added !!", "green", "2000");
				openWaterPollutants();
			} else {
				jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
			}
		}
	});
}

function deleteWaterPollConfirmation(type, id, pollName,cType) {
	var msg = "Are you sure, you want to delete this " + pollName + " !";
	swal({
			text: msg,
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-success",
			confirmButtonText: "Yes",
			cancelButtonText: "No",
			allowOutsideClick: false
		})
		.then((res) => {
			if (res.value == true) {

				var fwd_url = "ajax-delete-pollutant?type=" + type + "&product_id=" + id+ "&cons_type=" + cType;
				$.ajax({
					type: "GET",
					url: fwd_url,
					dataType: "text",
					success: function (data) {
						if (data == "success") {
							jBoxBottomRightBigNotice("Success", pollName + " Deleted !!", "green", "2500");
							openWaterPollutants();
						} else {
							jBoxBottomRightBigNotice("Error", "Oops !, Something went wrong.", "red", "2500");
						}
					}
				});

			}
		});
}

function getWaterConGen() {
	fwd_url = "ajax-view-consent-view-con-gen-details?type=" + consentTypee + "&consent_no=" + consentNo;
	$("#appendWaterConGen").empty();
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("appendWaterConGen").innerHTML = this.responseText;
		}
	};
	xmlhttp.open("GET", fwd_url, true);
	xmlhttp.send();
}

function getHzDescByNumber(categoryNumber) {
	var returnCategoryDesc = "";
	$.ajax({
		url: "ajax-getHzCategoryByCatNumber?categoryNumber=" + categoryNumber,
		type: "POST",
		success: function (data) {
			returnCategoryDesc = data;
		},
		error: function (e) {
			console.log(e.message);
		},
		async: false
	});

	return returnCategoryDesc;
}

function downloadConsent(fileName, fileType) {
	window.open('view-file?fileName=' + fileName.toString() + '&fileType=' + fileType, '_blank');
}