function addCtoEElemet(appendToId,appendName){
	var randomId = randomIdNumber();
	var fullName = getFullName(appendName);
	var unitOptions = getAllUnits();
	var hzHtml = "";
	if(appendName == "hwp" || appendName == "hwpcf" || appendName == "bio" ){
		var hzCategoryOptions = getAllHzCategory();
		var colName = "col-3";
		var colUnit = "col-2";
		
		hzHtml = "<div class='"+colName+"'>"
						+"<div class='form-group'>"
							+"<select class='select2' name='"+appendName+"_categories[]'>"
								  +"<option value=''>Select Category</option>"
								  +hzCategoryOptions
							+"</select>"
							+"<div class='invalid-feedback'>Please select any !</div>"
						+"</div>"
					+"</div>";
	} else {
		var colName = "col-5";
		var colUnit = "col-3";
	}
	
	var html = "<div class='row' id='appended_"+randomId+"_"+appendName+"'>"
					+hzHtml
					+"<div class='"+colName+"'>"
						+"<div class='form-group'>"
							+"<input type='text' name='"+appendName+"_name[]' class='form-control' placeholder='"+fullName+" Name'><div class=invalid-feedback>Please enter something !</div><i class='form-group__bar'></i>"
						+"</div>"
					+"</div>"
					
					+"<div class='col-2'>"
						+"<div class='form-group'>"
							+"<input type='number' name='"+appendName+"_quantity[]' class='form-control' placeholder='Consent Quantity'><div class=invalid-feedback>Please enter something!</div><i class='form-group__bar'></i>"
						+"</div>"
					+"</div>"
					
					+"<div class='"+colUnit+"'>"
						+"<div class='form-group'>"
							+"<select class='select2' name='"+appendName+"_unit[]'>"
								 +"<option value=''>Select unit</option>"
								 +unitOptions
							+"</select>"
							+"<div class='invalid-feedback'>Please select any !</div>"
						+"</div>"
					+"</div>"
					
					+"<div class='col-2'>"
						+"<div class='form-group'>"
							+"<button type='button' class='btn btn-sm btn-light pt-1 pb-1' onclick='removeElement(\"appended_"+randomId+"_"+appendName+"\")'><i class='zmdi zmdi-delete'></i>  Remove</button>"
						+"</div>"
					+"</div>"
					
				+"</div>";
	$("#"+appendToId).append(html);
	makeSelect2();
}

function getAllUnits() {
	var unitOptions = "";
	$.ajax({
		type: 'post',
		url: "ajax-getAllUnits",
		success: function (data) {
			allUnits = JSON.parse(data);
			$.each(allUnits, function (index, element) {
				unitOptions += "<option value='"+element.unitId+"'>"+element.unitName+"</option>"
			});
		},
		async: false
	});
	return unitOptions;
}

function getAllHzCategory() {
	var AllHzCategory = "";
	$.ajax({
		type: 'post',
		url: "ajax-getAllHzCategory",
		success: function (data) {
			hzData = JSON.parse(data);
			$.each(hzData, function (index, element) {
				AllHzCategory += "<option value='"+element.categoryNumber+"'>"+element.categoryDesc+"</option>"
			});
		},
		async: false
	});
	return AllHzCategory;
}

function addExtraPoll(type){
	var randomId = randomIdNumber();
	var unitOptions = getAllUnits();
	
	var html = "<div class='row' id='extra"+type+"Poll_"+randomId+"'>"
				+"<div class='col-6'>"
					+"<div class='form-group'>"
						+"<input type='text' class='form-control' name='"+type+"_extra_poll[]' placeholder='Pollutant Name'>"
						+"<div class='invalid-feedback'>Please enter something !</div>"
						+"<i class='form-group__bar'></i>"
					+"</div>"
				+"</div>"
				
				+"<div class='col-2'>"
					+"<div class='form-group'>"
						+"<input type='number' class='form-control' name='"+type+"_extra_limit[]' placeholder='Limits'>"
						+"<div class='invalid-feedback'>Please enter something !</div>"
						+"<i class='form-group__bar'></i>"
					+"</div>"
				+"</div>"
				
				+"<div class='col-2'>"
					+"<div class='form-group'>"
						+"<select class='select2' name='"+type+"_extra_units[]'>"
							+"<option value=''>Select Unit </option>"
							+unitOptions
						+"</select>"
						+"<div class='invalid-feedback'>Please select any !</div>"
					+"</div>"
				+"</div>"
				
				+"<div class='col-2'>"
					+"<div class='form-group'>"
						+"<button type='button' class='btn btn-sm btn-warning pt-1 pb-1' onclick='removeElement(\"extra"+type+"Poll_"+randomId+"\");'><i class='zmdi zmdi-delete'></i> Remove</button>"
					+"</div>"
				+"</div>"
			+"</div>";
	
	$("#append"+type+"Poll").append(html);
	makeSelect2();
}

function removeElement(removeId){
	$("#"+removeId).remove();
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

function randomIdNumber() {
	var min = 1;
	var max = 5000;
	return Math.round(Math.random() * (+max - +min) + +min);
}

function getFullName(name){
	if(name == "product"){
		return "Product";
	} else if (name == "byproduct"){
		return "Byproduct";
	} else if (name == "raw"){
		return "Raw Material";
	} else if (name == "nhwp"){
		return "Non-Hazardous Waste from Process";
	} else if (name == "nhwpcf"){
		return "Non-Hazardous Waste from PCF";
	} else if (name == "hwp"){
		return "Hazardous Waste from Process";
	} else if (name == "hwpcf"){
		return "Hazardous Waste from Pollution Control Facility";
	} else if (name == "bio"){
		return "Bio-Medical Waste";
	} else if (name == "fuel"){
		return "Fuel";
	}
}
/*------------------------------------------------
Datetime picker (Flatpickr)
------------------------------------------------*/
//Date and time
function makeDatePicker() {
	if ($('.datetime-picker')[0]) {
		$('.datetime-picker').flatpickr({
			enableTime : true,
			nextArrow : '<i class="zmdi zmdi-long-arrow-right" />',
			prevArrow : '<i class="zmdi zmdi-long-arrow-left" />'
		});
	}

	// Date only
	if ($('.date-picker')[0]) {
		$('.date-picker').flatpickr({
			enableTime : false,
			nextArrow : '<i class="zmdi zmdi-long-arrow-right" />',
			prevArrow : '<i class="zmdi zmdi-long-arrow-left" />'
		});
	}

	// Time only
	if ($('.time-picker')[0]) {
		$('.time-picker').flatpickr({
			noCalendar : true,
			enableTime : true
		});
	}

}

/*function openFilePicker(el){
	var idd = el.nextElementSibling;
	idd.click();
	//var idsss = $('#'+idd).closest().find([type='file']).attr('id');
	
	var namee = this.value;
}*/
$('#custom-file-picker').click(function() {
	var currentElement = this;
    var nextSibbling = currentElement.nextElementSibling;
    var filePickerId = nextSibbling.id;
    $("#"+filePickerId).click();
   // var appendFileName = nextSibbling.nextElementSibling;
    $("#"+filePickerId).change(function() {
        var filename = $('#'+filePickerId).val();
        if (filename.substring(3,11) == 'fakepath') {
            filename = filename.substring(12);
        } // Remove c:\fake at beginning from localhost chrome
        $('#'+appendFileName).html(filename);
   });
    console.log("ok");
});
