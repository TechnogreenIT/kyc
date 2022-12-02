

function makeYearlyEsrPopUP(){
	var html= "";
	var fwd_url = "ajax-getYearlyEsrValuesMan";
	$.ajax({
		type: 'POST',
		url: fwd_url,
		success: function (data) {
			var parseData = JSON.parse(data);
			var esrMinY = "";
			var esrMaxY = "";
			var yearArray=[];
			yearArray=parseData;
			$.each(parseData, function(index, element) { 
				var esrYear = element.esrYear;
				
				if(esrYear != "NA"){
					var res = esrYear.split("-");
					html += "<option value='"+esrYear+"'>April "+res[0]+" - March "+res[1]+"</option>"
				} else {
					html += "<option value='selectcard'>You have no any consent to Operate</option>"
				}
			});

		},
		async: false
	});
	var modelTitle = "SELECT YEAR FOR ENVIRONMENTAL STATEMENT REPORT";

	var bodyForm = "<div class='row'>"
			+ "<div class='col-12'>"
			+ "<div class='form-group'>"
			+ "<select class='select2' data-placeholder='Select Year' name='esrYearlyYear' id='esrYearlyYear'>"
			+ "<option value=''>Select Year</option>"
			+ html
			+ "</select>"
			+ "<div class='invalid-feedback'>Please select any !</div>"
			+ "</div>"
			+ "</div>"
			+ "</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: '',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='button' onclick='getYearlyESRData()' class='btn btn-primary'>GET STATEMENT</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();
}
function getYearlyESRData(){
	var flag = 0;
	var year = btoa($("#esrYearlyYear").val());
	flag += customSelectValidator(year, "esrYearlyYear");
	if(flag == 0 ){
		window.location="management-esr-form?year="+year;

	}
	
}

//BY PALLAVI..
/*function waterBudgetPopUP1(){

	var html= "";
	var fwd_url ="ajax-consentNoPopUpValues?msg=operate";
	$.ajax({
		type: 'POST',
		url: fwd_url,
		success: function (data) {
			var parseData = JSON.parse(data);
			$.each(parseData, function(index, element) { 
				var consentNo = element.consentNo;
				var consentName = element.consentName;
				if(parseData != "NA"){
					html += "<option value='"+consentNo+"'>"+consentNo+"</option>"
				} else {
					html += "<option value=''>You have no any consent to Operate</option>"
				}
			}); 

		},
		async: false
	});
	var modelTitle = "SELECT CONSENT FOR WATER BUDGET REPORT";

	var bodyForm = "<div class='row'>"
			+ "<div class='col-12'>"
			+ "<div class='form-group'>"
			+ "<select class='select2' data-placeholder='Select Consent Number' name='waterBudgetConsent' id='waterBudgetConsent'>"
			+ "<option value=''>Select Consent Number</option>"
			+ html
			+ "</select>"
			+ "<div class='invalid-feedback'>Please select any !</div>"
			+ "</div>"
			+ "</div>"
			+ "</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: '',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='button' onclick='getWaterBudget()' class='btn btn-primary'>GET WATER BUDGET REPORT</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();

}

*/

function makeHazardousReturnPopUP1(){

	var html= "";
	var fwd_url = "ajax-getHazardousValuesMan";
	$.ajax({
		type: 'POST',
		url: fwd_url,
		success: function (data) {
			var parseData = JSON.parse(data);
			var esrMinY = "";
			var esrMaxY = "";
			var yearArray=[];
			yearArray=parseData;
			$.each(parseData, function(index, element) { 
			    esrMinY = element.esrMinYear;
				console.log("miny>>>"+esrMinY);
				esrMaxY = element.esrMaxYear;
				 console.log("maxxx"+esrMaxY);
			});
			var a = esrMinY;
			var b = "";
			for(i = esrMinY ; i<=esrMaxY ;i++){
				b = a +1 ;
				html += "<option value='"+a+"-"+b+"'>April "+a+" - March "+b+"</option>"
				a = a +1;
			} 
		},
		async: false
	});
	var modelTitle = "SELECT YEAR FOR HAZARDOUS RETURN";

	var bodyForm = "<div class='row'>"
			+ "<div class='col-12'>"
			+ "<div class='form-group'>"
			+ "<select class='select2' data-placeholder='Select Year' name='hzReturnYear' id='hzReturnYear'>"
			+ "<option value=''>Select Year</option>"
			+ html
			+ "</select>"
			+ "<div class='invalid-feedback'>Please select any !</div>"
			+ "</div>"
			+ "</div>"
			+ "</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: '',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='button' onclick='getHzReturnData()' class='btn btn-primary'>GET STATEMENT</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();

}

function getHzReturnData(){
	var flag = 0;
	var year = btoa($("#hzReturnYear").val());
	flag += customSelectValidator(year, "hzReturnYear");
	
	if(flag == 0){
		window.location="envr-officer-hazardous-return-man?year="+year;
	}
}
