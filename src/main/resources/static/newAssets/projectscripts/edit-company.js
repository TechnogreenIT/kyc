function editBasicInformation(){
	$("#editCompanyName").prop("disabled", false);
	$("#editWebsite").prop("disabled", false);
	$("#editBranch").prop("disabled", false);
	$("#yearOfEst").prop("disabled", false);
	$("#esDate").prop("disabled", false);
	
	$("#editBtnId1").attr('onclick',$(this).attr('editBasicInformation()')==='true'?'editBasicInformation()':'saveBasicInformation()' );
	$("#editBtnId1").toggleClass("btn-primary btn-success");
	var ele = $("#editBtnId1").first();
	ele.text("");ele.text("Save");
}

function saveBasicInformation(){
	var newCompanyName = $("#editCompanyName").val();
	var newWebsite = $("#editWebsite").val();
	var newBranch = $("#editBranch").val();
	var newYearOfEst = $("#yearOfEst").val();
	var newEsDate = $("#esDate").val();
	$("#editCompanyName").prop("disabled", true);
	$("#editWebsite").prop("disabled", true);
	$("#editBranch").prop("disabled", true);
	$("#yearOfEst").prop("disabled", true);
	$("#esDate").prop("disabled", true);
	
	$("#editBtnId1").attr('onclick',$(this).attr('editBasicInformation()')==='true'?'saveBasicInformation()':'editBasicInformation()' );
	$("#editBtnId1").toggleClass("btn-success btn-primary");
	$("#editCompanyName").prop("disabled", true);
	var ele = $("#editBtnId1").first();
	ele.text("");ele.text("Edit");
	
	var msg = "Are you sure, you want to update company details ?";
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
				
				$.ajax({
					url: 'ajax-updateCompanyBasicInformation',
					type: 'post',
					data: ({
						newCompanyName : newCompanyName,
						newWebsite : newWebsite,
						newBranch : newBranch,
						newYearOfEst : newYearOfEst,
						newEsDate : newEsDate
			        }),

					success: function(data){
						if(data == "Success"){
							jBoxBottomRightBigNotice("Success", "Comapany Data Updated !!", "green", "2000");
							$("#editCompanyName").val(newCompanyName);
							$("#editWebsite").val(newWebsite);
							$("#editBranch").val(newBranch);
							$("#yearOfEst").val(newYearOfEst);
							$("#esDate").val(newEsDate);
						} else {
							jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
						}
						
					},
				   error: function(data){
						jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
						window.setTimeout(window.location = "logout", 7000);
				   }
				});
				
			} else if (res.dismiss == "cancel") {
				jBoxBottomRightBigNotice("Warning", "You cancelled the operation", "yellow", "2000");
			}
		});
}

function editCompanyInformation(){
	$("#editPhoneNo").prop("disabled", false);
	$("#editFax").prop("disabled", false);
	
	$("#editBtnId2").attr('onclick',$(this).attr('editCompanyInformation()')==='true'?'editCompanyInformation()':'saveCompanyInformation()' );
	$("#editBtnId2").toggleClass("btn-primary btn-success");
	var ele = $("#editBtnId2").first();
	ele.text("");ele.text("Save");
}

function saveCompanyInformation(){
	var newPhoneNo = $("#editPhoneNo").val();
	var newFax = $("#editFax").val();
	$("#editPhoneNo").prop("disabled", true);
	$("#editFax").prop("disabled", true);
	
	$("#editBtnId2").attr('onclick',$(this).attr('editCompanyInformation()')==='true'?'saveCompanyInformation()':'editCompanyInformation()' );
	$("#editBtnId2").toggleClass("btn-success btn-primary");
	var ele = $("#editBtnId2").first();
	ele.text("");ele.text("Edit");
	
	var msg = "Are you sure, you want to update company details ?";
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
				
				$.ajax({
					url: 'ajax-updateCompanyInformation',
					type: 'post',
					data: ({
						newPhoneNo : newPhoneNo,
						newFax : newFax
			        }),

					success: function(data){
						if(data == "Success"){
							jBoxBottomRightBigNotice("Success", "Comapany Data Updated !!", "green", "2000");
							$("#editPhoneNo").val(newPhoneNo);
							$("#editFax").val(newFax);
						} else {
							jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
						}
						
					},
				   error: function(data){
						jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
						window.setTimeout(window.location = "logout", 7000);
				   }
				});
				
			} else if (res.dismiss == "cancel") {
				jBoxBottomRightBigNotice("Warning", "You cancelled the operation", "yellow", "2000");
			}
		});
}

function editContactPersonInformation(){
	$("#editContactName").prop("disabled", false);
	$("#editContactNo").prop("disabled", false);
	$("#editContactEmail").prop("disabled", false);
	
	$("#editBtnId3").attr('onclick',$(this).attr('editContactPersonInformation()')==='true'?'editContactPersonInformation()':'saveContactPersonInformation()' );
	$("#editBtnId3").toggleClass("btn-primary btn-success");
	var ele = $("#editBtnId3").first();
	ele.text("");ele.text("Save");
}

function saveContactPersonInformation(){
	$("#editContactName").prop("disabled", false);
	$("#editContactNo").prop("disabled", false);
	$("#editContactEmail").prop("disabled", false);
	
	var newContactName = $("#editContactName").val();
	var newContactNo = $("#editContactNo").val();
	var newContactEmail = $("#editContactEmail").val();
	
	$("#editBtnId3").attr('onclick',$(this).attr('editContactPersonInformation()')==='true'?'saveContactPersonInformation()':'editContactPersonInformation()' );
	$("#editBtnId3").toggleClass("btn-success btn-primary");
	var ele = $("#editBtnId3").first();
	ele.text("");ele.text("Edit");
	
	var msg = "Are you sure, you want to update company details ?";
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
				$.ajax({
					url: 'ajax-updateContactPersonInformation',
					type: 'post',
					data: ({
						newContactName : newContactName,
						newContactNo : newContactNo,
						newContactEmail : newContactEmail
			        }),

					success: function(data){
						if(data == "Success"){
							jBoxBottomRightBigNotice("Success", "Comapany Data Updated !!", "green", "2000");
							$("#editContactName").val(newContactName);
							$("#editContactNo").val(newContactNo);
							$("#editContactEmail").val(newContactEmail);
						} else {
							jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
						}
						
					},
				   error: function(data){
						jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
						window.setTimeout(window.location = "logout", 7000);
				   }
				});
				
			} else if (res.dismiss == "cancel") {
				jBoxBottomRightBigNotice("Warning", "You cancelled the operation", "yellow", "2000");
			}
		});
}
function editOtherInformation(){
	$("#editIndCategory").prop("disabled", false);
	$("#editIndType").prop("disabled", false);
	$("#editPrimaryCategory").prop("disabled", false);
	$("#editSecondaryCategory").prop("disabled", false);
	$("#editWoringDays").prop("disabled", false);
	$("#editWoringHours").prop("disabled", false);
	
	
	$("#editBtnId4").attr('onclick',$(this).attr('editOtherInformation()')==='true'?'editOtherInformation()':'saveOtherInformation()' );
	$("#editBtnId4").toggleClass("btn-primary btn-success");
	var ele = $("#editBtnId4").first();
	ele.text("");ele.text("Save");
}
function saveOtherInformation(){
	$("#editIndCategory").prop("disabled", true);
	$("#editIndType").prop("disabled", true);
	$("#editPrimaryCategory").prop("disabled", true);
	$("#editSecondaryCategory").prop("disabled", true);
	$("#editWoringDays").prop("disabled", true);
	$("#editWoringHours").prop("disabled", true);
	
	var newIndCategory = $("#editIndCategory").val();
	var newIndType = $("#editIndType").val();
	var newPrimaryCategory = $("#editPrimaryCategory").val();
	var newSecondaryCategory = $("#editSecondaryCategory").val();
	var newWoringDays = $("#editWoringDays").val();
	var newWoringHours = $("#editWoringHours").val();
	
	$("#editBtnId4").attr('onclick',$(this).attr('editOtherInformation()')==='true'?'saveOtherInformation()':'editOtherInformation()' );
	$("#editBtnId4").toggleClass("btn-success btn-primary");
	var ele = $("#editBtnId4").first();
	ele.text("");ele.text("Edit");
	
	var msg = "Are you sure, you want to update company details ?";
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
				$.ajax({
					url: 'ajax-updateOtherInformation',
					type: 'post',
					data: ({
						newIndCategory : newIndCategory,
						newIndType : newIndType,
						newPrimaryCategory : newPrimaryCategory,
						newSecondaryCategory : newSecondaryCategory,
						newWoringDays : newWoringDays,
						newWoringHours : newWoringHours
						
			        }),
					success: function(data){
						if(data == "Success"){
							jBoxBottomRightBigNotice("Success", "Comapany Data Updated !!", "green", "2000");
							/*$("#editContactName").val(newContactName);
							$("#editContactNo").val(newContactNo);
							$("#editContactEmail").val(newContactEmail);*/
						} else {
							jBoxBottomRightBigNotice("Error", "something went wrong !!", "red", "2000");
						}
						
					},
				   error: function(data){
						jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
						window.setTimeout(window.location = "logout", 7000);
				   }
				});
				
			} else if (res.dismiss == "cancel") {
				jBoxBottomRightBigNotice("Warning", "You cancelled the operation", "yellow", "2000");
			}
		});
}
