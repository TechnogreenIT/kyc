$(document).ready(function () {
//openEcDetails();	
});

function openEcDetails() {
	ecNo = $("#ecNo").val();
	en= parseInt(ecNo);
	getECToEcData(en);
}

function getECToEcData(en) {
	fwd_url = "ajax-get-ec-details-by-id?ec_no=" + en;

	$.ajax({
		type: "GET",
		url: fwd_url,
		dataType: "json",
		success: function (data) {
			$("#ecsDetailsData").empty();

			$.each(data, function (index, element) {
				msg_data = "<div class='col-10 offset-1 mt-4'><table class='table' border='1'>" +
					"<tr>" +
					"<td width='300px' ><b style='color:#000000'>Environment Clearance No:</b></td>" +
 					"<td width='400px'colspan='4' ><font color='#00688B'> " + element.ecNo + " </font></td>" +	
					"<td width='300px'><b style='color:#000000'>Ec Valid Upto:</b></td>" +
					"<td width='400px' colspan='4'><font color='#00688B' > " + element.ecvalid_date + "</font></td>" +
					"</tr>" +
					
					"<tr>" +
					"	<td height='50px' width='500px'  colspan='9' style='color:#000000'><b>Whether project/activity attracts the general condition specified in the Schedule of EIA Notification ?</b></td>" +
					"	<td ><font color='#00688B'>  " + element.eia_Notification + " </font></td>" +		
					"</tr>" +
					
					"<tr>" +
					"	<td height='50px' width='500px' colspan='9' style='color:#000000'><b>Whether located Proximity to Protected Area Notified Under the Wild Life(Protection) Act,1972 ?</b></td>" +
					"	<td ><font color='#00688B'>   " + element.protect_Area_Wildlife + " </font></td>" +		
					"</tr>" +
					
					"<tr>" +
					"	<td height='50px' width='500px' colspan='9' style='color:#000000'><b>Whether located proximity to critically Polluted area as identified by the CPCB from time to time ?</b></td>" +
					"	<td ><font color='#00688B'>   " + element.criticalPoll_Area_Identify + " </font></td>" +		
					"</tr>" +
					
					"<tr>" +
					"	<td height='50px' width='500px'colspan='9' style='color:#000000'><b>Whether located proximity to Notified Eco-Sensitive area notified under Environmental(Protection) Act,1986 ?</b></td>" +
					"	<td ><font color='#00688B'>   " + element.ecosensitive_Area + " </font></td>" +		
					"</tr>" +
					
					"<tr>" +
					"<input type='hidden' id='ec_file_old' value='" + element.ecFileName + "'><td colspan='9' height='80px' style='color:#000000'><center class='mt-3'><b>EC File:</b></font><a onclick='downloadEc(\"" + element.ecFileName + "\",\"ec\")' title='click to download this consent copy.'> " +
					element.ecFileName + "</font></a></center></td>" +
					"<td><a href='#'  class='btn btn-default' style='background-color:#9e9e9e;color:white;' onclick='openEditEc();'><b>EDIT</b></a></td>" +
					"</tr>" +
					"</table>";
				$(msg_data).appendTo("#ecsDetailsData");
		
			});

			
		}
	});
}

//edit purpose
function openEditEc() {
	var msg_data;
	var fwd_url;	
   $("#ecsDetailsData").empty();
		fwd_url = "ajax-get-ec-details-by-id?ec_no=" + ecNo;
	
	$(document).ready(function () {
		$.ajax({
			type: "GET",
			url: fwd_url,
			dataType: "json",
			success: function (data) {
				$.each(data, function (index, element) {
				msg_data ="<div class='col-10 offset-1 mt-4'><form method='post' action=''>" +
						"<input type='hidden' id='ecNo' name='ecNo' value='" + element.ecNo + "'>" + 
				       "<table class='table' border='1'>" +
					"<tr>" +
					"<td width='300px'height='50px' ><b style='color:#000000'>Environment Clearance No:</b></td>" +
 					"<td width='400px'colspan='6' height='50px' > " + element.ecNo + " </td>" +	
					"<td width='300px' height='50px'`><b>Ec Valid Upto:</b></td>" +
					"<td width='400px'colspan='8' height='50px'>"+
					"<div class='input-group mb-1'>" +
						"<input type='date' class='form-control hidden-md-up'>" +
						"<input type='text' class='form-control date-picker hidden-sm-down' id='ecvalid_upto' value='" + element.ecvalid_date + "' >" +
						"</div>" +
						"</td>"+
					"</tr>" +
					
					"<tr>" +
					"<td height='50px' width='500px'colspan='9' style='color:#000000'><b>Whether project/activity attracts the general condition specified in the Schedule of EIA Notification ?</b></td>" +
					"<td height='50px' colspan='5'>"+
					"<div id='eiaQ_edit'>" +
						"<input type='text' class='form-control' id='eiaQ'  value='"+element.eia_Notification+"' onclick='editECQue(id)'>" +						
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>"+
					"</tr>" +
					
					"<tr>" +
					"<td height='50px' width='500px' colspan='9' style='color:#000000'><b>Whether located Proximity to Protected Area Notified Under the Wild Life(Protection) Act,1972 ?</b></td>" +
					"<td height='50px'>"+
					"<div id='eiaQ1_edit'>" +
						"<input type='text' class='form-control' id='eiaQ1' value='" + element.protect_Area_Wildlife + "' onclick='editECQue(id)'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>"+
					"</tr>" +
					
					"<tr>" +
					"<td height='50px' width='500px' colspan='9' style='color:#000000'><b>Whether located proximity to critically Polluted area as identified by the CPCB from time to time ?</b></td>" +
					"<td height='50px'>"+
					"<div id='eiaQ2_edit'>" +
						"<input type='text' class='form-control' id='eiaQ2' value='" + element.criticalPoll_Area_Identify + "' onclick='editECQue(id)'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>"+
					"</tr>" +
					
					"<tr>" +
					"<td height='50px' width='500px'colspan='9' style='color:#000000'><b>Whether located proximity to Notified Eco-Sensitive area notified under Environmental(Protection) Act,1986 ?</b></td>" +
					"<td height='50px'>"+
					"<div id='eiaQ3_edit'>" +
						"<input type='text' class='form-control' id='eiaQ3' value='" + element.ecosensitive_Area + "' onclick='editECQue(id)'>" +
						"<div class='invalid-feedback'>Invalid !</div>" +
						"<i class='form-group__bar'></i>" +
						"</div>" +
						"</td>"+				
					"</tr>" +
					
					"<tr>" +
					"<td colspan='9' height='80px'>" +
						"<center><input type='hidden' id='ec_file_old' value='" + element.ecFileName + "'><b>EC File:  </b><a onclick='downloadEc(\"" + element.ecFileName + "\",\"ec\")'> " + element.ecFileName + "</a><br/><br/>" +
						"<div class='fileinput fileinput-new' data-provides='fileinput'>" +
						"<label>EC File : </label>" +
						"<span class='btn btn-primary btn-file'>" +
						"<span class='fileinput-new'>Select file</span>" +
						"<span class='fileinput-exists'>Change</span>" +
						"<input type='file' name='ecdataFile_file' id='ecdataFile_file' required>" +
						"</span>" +
						"<span class='fileinput-filename'></span> <a href='#' class='close fileinput-exists' data-dismiss='fileinput'>&times;</a>" +
						"</div></center>" +
						"</td>" +
						"<td>" +
						"<a href='#' class='btn btn-default' style='background-color:#9e9e9e;color:white;' onclick='openEcDetails();'><b>Cancel</b></a> | " +
						"<a href='#' class='btn btn-default' style='background-color:#9e9e9e;color:white;' onclick='submitEcDetails();'><b>Modify</b></a>" +
						"</td>" +
					"</tr>" +
						"</table></form></div>"
					$(msg_data).appendTo("#ecsDetailsData");
					makeDatepicker();
				});
			}
		});
	});
	}

//edited data submitted
function submitEcDetails() {
	
	var form_data = new FormData();
	flag = 0;
	var ecNo = $("#ecNo").val();
	var ecvalid_upto = $("#ecvalid_upto").val();
	var eiaQ = $("#eiaQ").val();
	var eiaQ1 = $("#eiaQ1").val();
	var eiaQ2 = $("#eiaQ2").val();
	var eiaQ3 = $("#eiaQ3").val();	
	var ec_file_old = $("#ec_file_old").val();
	var ec_file_new = $("input[name=ecdataFile_file]").val();

//	flag += customInputValidator(gross_ci, "#gross_ci");
	//flag += customInputValidator(no_staff, "#no_staff");
	//flag += customInputValidator(no_worker, "#no_worker");
	//flag += customInputValidator(tot_plot_area, "#tot_plot_area");
	//flag += customInputValidator(tot_build_area, "#tot_build_area");
	//flag += customInputValidator(tot_green_area, "#tot_green_area");

	var cc = ec_file_new.split("\\");
	var status = "Yes";
	if (ec_file_new == "") {
		status = "No";
	} else if (cc[2] == ec_file_old) {
		status = "No";
	} else {
		var file_data = $("#ecdataFile_file").prop('files')[0]; //alert(file_data)
		form_data.append('file', file_data);
	}

	form_data.append('status', status);
	form_data.append('ecNo', ecNo);
	form_data.append('ecvalid_upto', ecvalid_upto);
	form_data.append('eiaQ', eiaQ);
	form_data.append('eiaQ1', eiaQ1);
	form_data.append('eiaQ2', eiaQ2);
	form_data.append('eiaQ3', eiaQ3);
	form_data.append('ec_file_old', ec_file_old);

	if (flag == 0) {
		$.ajax({
			url: "ajax-view-ec-modify-ec-data",
			dataType: 'json',
			cache: false,
			contentType: false,
			processData: false,
			data: form_data,
			type: 'post',
			success: function (res) {
				if (res == "success") {
					jBoxBottomRightBigNotice("Success", "Ec Updated !!", "green", "2000");
					openEcDetails();
				} else {
					jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
				}

			}
		});
	}
}

//ec file download purpose
function downloadEc(fileName, fileType) {
	window.open('view-file?fileName=' + fileName.toString() + '&fileType=' + fileType, '_blank');
}


function editECQue(id) {
		var tempId = id + "_edit";
		var tempdtId = id + "_data";
		$("#" + tempId).empty();
	
	 htmlContent1 ="<div>"
	 if(id=="eiaQ"){
		 	 htmlContent1 +="<select class='select2' name='selectque1' id='eiaQ'>"
	 }
	  if(id=="eiaQ1"){
		 	 htmlContent1 +="<select class='select2' name='selectque2' id='eiaQ1'>"
	 }
	  if(id=="eiaQ2"){
		 	 htmlContent1 +="<select class='select2' name='selectque3' id='eiaQ2'>"
	 }
	  if(id=="eiaQ3"){
		 	 htmlContent1 +="<select class='select2' name='selectque4' id='eiaQ3'>"
	 }
			
					 htmlContent1 +="<option value='Yes'>Yes</option>"
								  +"<option value='No'>No</option>"					
								  +"</select>"
								  +"</div>"	
						$(htmlContent1).appendTo("#" + tempId);
						makeSelect2();
 			//		element.eia_Notification=document.getElementById("selectque").value;
//}
}

function makeSelect2() {
	if ($('select.select2')[0]) {
		var select2parent = $('.select2-parent')[0] ? $('.select2-parent') : $('body');

		$('select.select2').select2({
			dropdownAutoWidth: true,
			width: '100%',
			dropdownParent: select2parent
		});
	}
}

