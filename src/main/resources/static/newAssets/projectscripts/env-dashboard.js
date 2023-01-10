$( document ).ready(function() {
	addstepsfortodo();
    consentPopUp();
    consentNotice();
    
});

function consentPopUp(){
	
	
	$.ajax({
		type: "GET",
		url: "ajax-GetReminderStatus",

		data: ({
			action: "getConsentReminder"
		}),
		success: function (d) {
			var data = JSON.parse(d);
			$.each(data, function (index, element) {
				//debugger;
				var str = element.msg;
				var res = str.split(":");
				var message = "";
				if (res[1] == "ABOUT TO EXPIRE") {
					var msg = "Your consent no.<span class='text-red'> "+res[0]+"</span> is about to expire.";
					setTimeout(function(){
						consentExpireNotificationPopup(res[0],msg);
						}, 1000);
					
				} else if (res[1] == "EXPIRED") {
					if (res[3] == "Yes") {
						var msg = "Your consent " + res[0] + " had expired and manually extended upto " + res[2] +" .";
					}else if (res[3] == "No") {
						var msg = "Your consent no.<span class='text-red'>" + res[0] + "</span> had expired on <span class='text-red'>" + res[2] + ".</span>";
					}
					
					setTimeout(function(){
						consentExpireNotificationPopup(res[0],msg);
						}, 1000);
				}
			});
		},
		async: false
	});
	
}
function consentExpireNotificationPopup(consentNo,msg){
	var modelTitle = "CONSENT EXPIRATION NOTICE !";

	var bodyForm = "<div class='row'>"
					+ "<div class='col-12'>"
					+ "<center>"+msg+"</center>"
					+ "<center> Have you extended the validity?</center>"
					+ "</div>"
					+"<div class='col-12 mt-2'> <center>"
					+"<div class='radio radio--inline'> <input type='radio' name='customRadio' id='consentExpReminderChkBox1' onclick='closeReminder(\"saveConsentCopy\",this)'> <label class='radio__label' for='consentExpReminderChkBox1'>Yes</label> </div>"
					+"<div class='radio radio--inline'> <input type='radio' name='customRadio' id='consentExpReminderChkBox2' onclick='closeReminder(\"extend\",this)'> <label class='radio__label' for='consentExpReminderChkBox2'>No</label> </div>"
					+"</center></div>"
				+ "</div>";
	$.showModal({
		title: modelTitle,
		modalClass: "fade",
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
}

function closeReminder(action,el){
	
	console.log("action >"+action);
	
	var modalId = $(el).closest('.modal').attr('id');
	$('#' + modalId).modal('toggle');
	
	if(action == "saveConsentCopy"){
		window.location="create-consent";
	} else if(action == "extend"){
		consentExtendNotificationPopup();
	} else if(action == "dateList"){
		loadConsents();
	}
	
	
}

function consentExtendNotificationPopup(){
	var modelTitle = "";

	var bodyForm = "<div class='row'>"
					+ "<div class='col-12'>"
					+ "<center>Do you want to extend consent validity date?</center>"
					+ "</div>"
					+"<div class='col-12 mt-2'> <center>"
					+"<div class='radio radio--inline'> <input type='radio' name='customRadio' id='consentExtendChkBox1' onclick='closeReminder(\"dateList\",this)'> <label class='radio__label' for='consentExtendChkBox1'>Yes</label> </div>"
					+"<div class='radio radio--inline'> <input type='radio' name='customRadio' id='consentExtendChkBox2' onclick='closeReminder(\"close\",this)'> <label class='radio__label' for='consentExtendChkBox2'>No</label> </div>"
					+"</center></div>"
				+ "</div>";
	$.showModal({
		title: modelTitle,
		modalClass: "fade",
		modalDialogClass: '',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
}

function loadConsents(){
	
	var optionss = "";
	$.ajax({
		type : "GET",
		url : "ajax-GetReminderStatus",

		data : ({
			action : "getConsents"
		}),
		dataType : "json",
		success : function(d) {
			
			var data = JSON.parse(d);
			$.each(data, function(index, element) {
				id = element.id;
				consent_no = element.consent_no;
				if(id=="NoData"){
					optionss += "<option value=''>Your consent not expired yet </option>";
				} else {
					optionss += "<option value='"+id+"'>"+consent_no+"</option>";
				}
			});
			
			
		},
		error : function(xhr, type) {
			alert('server error occoured');
		},
		async : false
	});
	
	var time = new Date();
	
	time.setDate(time.getDate()+ 30);
	
	var strdate = time.toString('yyyy-MM-dd');
	
	var today = new Date();
	today.setDate(today.getDate()+30);

	var dd = today.getDate();		if(dd < 10) dd = "0"+dd;
	var mm = today.getMonth()+1; 	if(mm < 10) mm = "0"+mm;
	var yyyy = today.getFullYear();
	var today = yyyy+'-'+mm+'-'+dd;
	
	
	var modelTitle = "";

	var bodyForm = "<div class='row'>"
					+ "<div class='col-12'>"
					+ "<center>Validity of consent will be extended for one month. Please select Consent no.</center>"
					+ "</div>"
					
					+"<div class='col-6 mt-4'>"
					+ "<div class='form-group'>"
						+"<label>Consent No.:</label>"
						+ "<select class='select2' data-placeholder='Select Consent No.' name='consent_id' id='consent_id' onchange='changeExtendConsent(this);'>"
						+ "<option value=''>Select Consent No.</option>"
						+ optionss
						+ "</select>"
						+ "<div class='invalid-feedback'>Please select any !</div>"
					+ "</div>"
					+"</div>"
					
					+"<div class='col-6 mt-4'>"
					+ "<div class='form-group'>"
						+"<label>Validity Date :</label>"
						+"<input type='text' class='form-control' id='newValidUptoDate' value='"+today+"' disabled>"
					+ "</div>"
					+"</div>"
					
					+"<div class='col-12 mt-4'>"
						+ "<center><button class='btn btn-primary btn--icon-text' id='extendConsentBtn' onclick='saveExtendedDate(this)' disabled><i class='zmdi zmdi-save'></i> Extend </button></center>"
					+"</div>"
					
				+ "</div>";
	$.showModal({
		title: modelTitle,
		modalClass: "fade",
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();
	
}

function changeExtendConsent(el){
	var selectedvalue = el.value;
	if(selectedvalue != ""){
		$("#extendConsentBtn").attr("disabled", false);
	} else {
		$("#extendConsentBtn").attr("disabled", true);
	}
}
/////display steps in todo list.
function addstepsfortodo(){
	 $.ajax({
			url: 'ajax-todosteps-add',
			type: 'post',
			 async : false,
			dataType:'text',	
			success: function(data){
			 if (data == null ) {
				var parseData = JSON.parse(data);			
				$.each(parseData, function(index, element) {
					
				});		
				
			 }			
			},

		});
}
////

function saveExtendedDate(el){
	var consent_id = $("#consent_id").val();
	var valid_upto = $("#newValidUptoDate").val();
	
	$.ajax({
		type : "POST",
		url : "ajax-GetReminderStatus",
		
		data: ({
			action : "extendConsentValidity",
			consentIdForEx:consent_id,
			validUptoForEx:valid_upto
        }),
		success : function(data) {
			var modalId = $(el).closest('.modal').attr('id');
			$('#' + modalId).modal('toggle');
			jBoxBottomRightBigNotice("Success", " Consent Extended Successfully !!", "green", "2000");
		},
		error : function(xhr, type) {
			alert('server error occoured');
		},
		async : false
	});
}
function consentNotice(){
	setTimeout(function(){
	
		$.ajax({
			type : "GET",
			url : "ajax-GetReminderStatus",
			
			data: ({
				action : "getConsentReminder"
	        }),
			success : function(d) {
				var data = JSON.parse(d);
				$.each(data, function(index, element) {
					//debugger;
					var str = element.msg;
					var res = str.split(":");
					var message = "";
					if(res[1] == "ABOUT TO EXPIRE"){
						message = "Consent "+ res[0] +" is "+ res[1];
						jBoxBottomRightBigNotice("Warning",message, "yellow", 5000);
					}else if(res[1] == "EXPIRED"){
						if(res[3] == "No"){
							message = "Consent "+ res[0] + " was "+ res[1] + " on "+ res[2] + " and is not extended !";
						}else{
							message = "Consent "+ res[0] + " was "+ res[1] +" and is extended upto "+ res[2];
						}
						jBoxBottomRightBigNotice("Notice",message, "red", 5000);
					}
				});
				
				
			}
		});
	
	
	}, 1000);
	
	
}