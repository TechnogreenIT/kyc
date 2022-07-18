function modifiedDataPOPUp(el,itemName,cQuantity,inpDate,regId,pUnit) {
	var modelTitle = "Modify " +itemName+ " ("+pUnit+")";
	var bodyForm = "<div class='container'>"
		+"<div class='row'>"
		+"<input type='hidden' id='m_itemName' class='form-control' value ='" + itemName + "'>"
		+"<input type='hidden' id='m_inpDate' class='form-control' value ='" + inpDate + "'>"
		+"<input type='hidden' id='m_regId' class='form-control' value ='" + regId + "'>"
			+"<div class='col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3'>" 
				+"<div class='form-group'>"
					+"Current Quantity" 
				+"</div>"
			+"</div>"
		
			+"<div class='col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3'>" 
				+"<input id='m_cQuantity' class='from-control form-control' type='number' value='"+cQuantity+"' disabled/>"  
			+"</div>"
			
			+"<div class='col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3'>" 
				+"<div class='form-group'>"
					+"New Quantity" 
				+"</div>"
			+"</div>"
		
			+"<div class='col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3'>" 
				+"<div class='form-group'>"
					+"<input class='from-control form-control' id='m_nQuantity' type='number'/>" 
					+"<div class='invalid-feedback'>Invalid!</div><i class='form-group__bar'></i>"
				+"</div>"
			+"</div>"
			
			+"<div class='col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12'>"
				+"<div class='form-group'>"
					+"<textarea id='m_reason' class='form-control'  placeholder='Enter Reason'></textarea>"
					+"<div class='invalid-feedback'>Invalid!</div><i class='form-group__bar'></i>"
				+"</div>"
			+"</div>"  
		+"</div>"
	+ "</div>";// container
	
			$.showModal({
				title : modelTitle,
				modalDialogClass : 'modal-md',
				body : bodyForm,
				footer : "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='saveModifiedData(this)' class='btn btn-primary'>Modify</button>",
				onCreate : function(modal) {
					// create event handler for form submit and handle values
				}
			})
	makeSelect2();
	
}

function saveModifiedData(el){
	var flag = 0;
	var itemName= $("#m_itemName").val();
	var inpDate= $("#m_inpDate").val();
	var regId= $("#m_regId").val();
	var cQuantity= $("#m_cQuantity").val();
	var nQuantity= $("#m_nQuantity").val();
	var mReason= $("#m_reason").val();

	flag += customInputValidator(nQuantity, "#m_nQuantity");
	flag += customInputValidator(mReason, "#m_reason");
	
	var myObject = {};

	myObject["itemName"] = itemName;
	myObject["inpDate"] = inpDate;
	myObject["regId"] = regId;
	myObject["cQuantity"] = cQuantity;
	myObject["nQuantity"] = nQuantity;
	myObject["mReason"] = mReason;
	

	if (flag == 0) {
		$.ajax({
			type : "POST",
			url : "ajax-save-modified-data",
			contentType : "application/json",
			data : JSON.stringify(myObject),
			success : function(data) {
				var modalId = $(el).closest('.modal').attr('id');
				$('#' + modalId).modal('toggle');
				jBoxBottomRightBigNotice("Success", "Modified Data Saved !!","green", "2000");
			}
		});
	}
}


