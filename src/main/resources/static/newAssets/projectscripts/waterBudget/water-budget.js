var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; // January is 0!
var yy = today.getFullYear();
if (dd < 10) {
    dd = "0" + dd;
}
if (mm < 10) {
    mm = "0" + mm;
}
var today=yy+"-"+mm+"-"+dd;

$(document).ready(function() {
	getWaterBudgetData(today);
	//	var s='digraph {'+
	//	'A[labelType="html" label="<span style=\'font-size:32px\'>A test</span>"]'+
	//	'B[labelType="html" label="<span style=\'font-size:32px\'>B test</span>"]'+
	//	'A -> B;'+
	//	'}'
});




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
			kk=k;
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


function onDaySetGetData(id, dateValue){
	var yy="", mm="", dd="";
	 yy = document.getElementById('yyear'+id).value;
	 mm = document.getElementById('mmonth'+id).value;
	 dd = document.getElementById('add_days'+id).value;
	 if(dd>=1 && dd<9)
		{
		dd="0"+dd;
		}
	today = yy+"-"+mm+"-"+dd;
	
		date = today;
		getWaterBudgetData(date);
//		getTitle(today, "str");
//		dailyDataStat();
//		dailyDataStatAir();
//		dailyDataStatWater();
	
	
//	$("#mmonth"+id).prop('selectedIndex',0);
//	$("#add_days"+id).prop('selectedIndex',0);
}

function getWaterBudgetData(today) {
	var labels = new Array();
	var relations = new Array();
	var regQuantity = new Array();
	var str1 = 'digraph {';
	var str2 = "";
	var str3 = "";
	var str4="}";
	var finalString = "";
	var result=false;

	$
			.ajax({
				url : 'ajax-get-water-budget-data?sDate='+today,
				dataType : 'json',
				success : function(res) {
					 var responseData = JSON.parse(res);
					 var result=responseData[0].result;
				    var data=responseData[0].data;
					if (result==true)
					 {
						var myLebls =data[0].labels;
						var temp = "digraph {";
						for (var i = 0; i < myLebls.length; i++) {
							temp +=myLebls[i];
						}
						
						
						/// for rel
						var myrels =data[1].relations;
						for (var i = 0; i < myrels.length; i++) {
							temp +=myrels[i];
						}
						
						
						
						temp +="}";
						tryDraw(temp);
					 }
					else
						{
						var msg="No Data Found";
						}
					
				},
				error : function(res) {
					console.log("error");
				}
			});
}
