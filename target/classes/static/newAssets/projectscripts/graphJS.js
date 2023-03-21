var type_of_graph = "pw";
var current_date;
var units = "NA";
$('#load_chart_div').hide();
var today = new Date();
x = today.getMonth()+1;
y = today.getDate();
if(x < 10){
	x = "0"+x;
}
if(y < 10){
	y = "0"+y;
}

$(function() {
    $("#btns").html("<li style='float: right;'> <button id='testing' class='btn btn-default waves-effect' style='padding: 5px 15px;'><i class='zmdi zmdi-print zmdi-hc-fw' style='font-size: large;'></i></button></li>");
});

// onload_div_print
$(document).on('click', '#btns #testing',{arg1:'chart_div'},printCanvas);
var date = today.getFullYear()+'-'+x+'-'+y;
function openGraph(id,id1,id2,unit){ //id - no. of days , id1 - type , id2 - date, units - if having different units
	// debugger;
	if(id2){
		today_date = new Date(id2).toJSON().slice(0,10).replace(/-/g,'-');	
	}
	todayDate=today_date.split('-');
    year=todayDate[0];
    month=todayDate[1];
    day=todayDate[2];
    day = Number(day)+1;
    if(day>=1 && day<=9)
    	{
    	day='0'+day;
    	}
    var todaydate=year+"-"+month+"-"+day;
	var str = "";
	units = unit;
	type_of_graph = id1;
	if(id1 == 'swr'){
		getArrayLineData(id,type_of_graph,today_date);
	}else if(id1 == 'fr'){
		getArrayLineData(id,type_of_graph,today_date);
	}else if(id1 == 'fwur'){
		getArrayLineData(id,type_of_graph,today_date);
	}else if(id1 == 'swur'){
		getArrayLineData(id,type_of_graph,today_date);
	}else if(id1 == 'tpr'){
		getArrayLineData(id,type_of_graph,today_date);
	}else if(id1 == 'pw'){
		getArrayData(today_date,id,"Water Consumption");
	}else if(id1 == 'pf'){
		getArrayData(today_date,id,"Fuel Consumption");
	}else{
		
		getArrayBarData(id,type_of_graph,todaydate,units);
	}
	$('select').prop('selectedIndex',0);
}

function checkMonth(id){
	$("#mmonth"+id).prop('selectedIndex',0);
	$("#add_days"+id).prop('selectedIndex',0);
}
function openDays(id,id1){
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
function noDataMsg(id){
	$("#"+id).empty();
	document.getElementById(id).innerHTML = "<center><img src='img/nodata1.png' style='width:338px'></center>";
}
function setDate(id,id1, id2){	/* id - no. of days , id1 - id of div , id2 - variable for year selection */
	// debugger;
	if(id == '30'){
		var yy = document.getElementById('yyear'+id2).value;
		var mm = id1.value;
		var dd = d.getDay();
		if(dd>=1 && dd<9)
			{
			dd="0"+dd;
			}
		current_date = yy+"-"+mm+"-"+dd;
	}else if(id == '365'){
		var yy = id2.value;
		var mm = d.getMonth();
		var dd = d.getDay();
		current_date = yy+"-"+mm+"-"+dd;
	}else{
		var yy = document.getElementById('yyear'+id1).value;
		var mm = document.getElementById('mmonth'+id1).value;
		var dd = document.getElementById('add_days'+id1).value;
		current_date = yy+"-"+mm+"-"+dd;
	}
	openGraph(id,type_of_graph,current_date, units);
}


function opentab(id) {
	// debugger;
	if(id == 1){
		$(document).on('click', '#btns #testing',{arg1:'chart_div'},printCanvas);
		drawCharts(1);
	}else if(id == 2){
		$(document).on('click', '#btns #testing',{arg1:'chart_div1'},printCanvas);
		drawCharts(7);
	}else if(id == 3){
		$(document).on('click', '#btns #testing',{arg1:'chart_div2'},printCanvas);
		drawCharts(30);
	}else if(id == 4){
		$(document).on('click', '#btns #testing',{arg1:'chart_div3'},printCanvas);
		drawCharts(365);
	}else if(id == 5){
		$(document).on('click', '#btns #testing',{arg1:'chart_div4'},printCanvas);
		drawCharts(0);
	}
}

//print
function printCanvas(e)  
{  
	var div=e.data.arg1;
    var dataUrl = document.getElementById(div).toDataURL(); //attempt to save base64 string to server using this var  
    var windowContent = '<!DOCTYPE html>';
    windowContent += '<html>'
    windowContent += '<head><title>Print canvas</title></head>';
    windowContent += '<body>'
    windowContent += '<img src="' + dataUrl + '">';
    windowContent += '</body>';
    windowContent += '</html>';
    var src="canvasPrinting";
    var printWin = window.open(src, "newWin", "width="+screen.availWidth+",height="+screen.availHeight)
    printWin.document.open();
    printWin.document.write(windowContent);
    printWin.document.addEventListener('load', function() {
        printWin.focus();
        printWin.print();
        printWin.document.close();
        printWin.close();            
    }, true);
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
	var str = " <small> of previous 30 days from "+prev_date+" to "+date+" </small>";
	document.getElementById(divId).innerHTML = str;
}

function getPerformanceTitle(date, divId){
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
	$("#performance_prevDate").val(prev_date);$("#performance_newDate").val(date);
	var str = " <small> of previous 30 days from "+prev_date+" to "+date+" </small>";
	document.getElementById(divId).innerHTML = str;
}

function getMonthlyTitle(date, divId){
	var a = date.split("-");
	var prev_date = a[0]+'-'+a[1]+'-01';
	$("#prevDate").val(prev_date);$("#newDate").val(date);
	var str = " <small>from "+prev_date+" to "+date+" </small>";
	document.getElementById(divId).innerHTML = str;
}

function lastdayfromDate(yy,mm) {
	var date = new Date();
	var lastDay = new Date(yy, mm, 0);
	var dd = lastDay.getDate();
	return  lastDayWithSlashes = (yy + '-' + mm + '-' + dd);
}
function openPerformanceTab(id) {
	if(id == 1){
		getDataQuality();
	}else if(id == 2){
		getConformance();
	}else if(id == 3){
		getDailyData(date);
	}else if(id == 4){
		getWaterDataQuality();
	}else if(id == 5){
		getPerCompliance();
	}else if(id == 6){
		getEnvQualityData();
	}
}
