function todayDateValue(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();
	var today_date = yyyy + "-" + mm + "-" + dd;
	var date = getFormattedDate(today);
	return date;
}

function formateDate(date){
	var temp = date.split("-");
	var year = temp[0];
	var month = temp[1];
	var day = temp[2];
	if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
	return year +'-' + month + '-' + day;
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
function openDays(id,id1){
	var msg_data,msg_data1,msg_data11,msg_data2,k;
	var month_id = $("#mmonth"+id1).val();
	var yrValue = $("#yyear"+id1).val();
	if(month_id == "" || yrValue == "")return;
	
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
		
		if (yrValue % 400 == 0 || (yrValue % 100 != 0 && yrValue % 4 == 0)) {
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

function getMonthlyTitle(date, divId) {
	var a = date.split("-");
	var prev_date = a[0] + '-' + a[1] + '-01';
	$("#prevDate").val(prev_date);
	$("#newDate").val(date);
	var str = " <small>from " + prev_date + " to " + date + " </small>";
	$("#" + divId).empty();
	$("#" + divId).append(str);
}

function lastdayfromDate(yy, mm) {
	var date = new Date();
	var lastDay = new Date(yy, mm, 0);
	var dd = lastDay.getDate();
	return lastDayWithSlashes = (yy + '-' + mm + '-' + dd);
}

function getTitle(date, divId) {
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
	var prev_date = c.getFullYear() + '-' + x + '-' + y;
	$("#prevDate").val(prev_date);
	$("#newDate").val(date);
	var str = " <small> of previous 30 days from " + prev_date + " to " + date
			+ " </small>";
	$("#" + divId).empty();
	$("#" + divId).append(str);
}



function setDefaultTitle(divId) {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = "0" + dd;
	}
	if (mm < 10) {
		mm = "0" + mm;
	}
	var today_date = yyyy + "-" + mm + "-" + dd;

	today.setDate(today.getDate() - 30);
	x = today.getMonth() + 1;
	y = today.getDate();
	if (x < 10) {
		x = "0" + x;
	}
	if (y < 10) {
		y = "0" + y;
	}
	var prev_date = today.getFullYear() + '-' + x + '-' + y;
	$("#prevDate").val(prev_date);
	$("#newDate").val(date);
	var str = " <small> of previous 30 days from " + prev_date + " to " + date
			+ " </small>";
	$("#" + divId).empty();
	$("#" + divId).append(str);
}