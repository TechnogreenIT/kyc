var waterFiltersOptions ="";
$( document ).ready(function() {
	waterFiltersOptions = getWaterFiltersOptions();
	$("#waterFilterNames").append(waterFiltersOptions);
});


function addFilter(){
	var filterTypeValue = $("#waterFilterNames").val();

	if (filterTypeValue == "") {
		$("#waterFilterNames_error").addClass("d-block");
		return;
	} else {
		$("#waterFilterNames_error").removeClass("d-block");
	}
	
	var htmlCard = '<div class="card">'
			+'<div class="card-body">'
				+'<h4 class="card-title">'+filterTypeValue+'</h4>'
				+'<input type="hidden" name="filterNamesList[]" value="'+filterTypeValue+'">'
				+'<div class="actions"><a class="actions__item zmdi text-danger zmdi-delete" data-toggle="tooltip" data-placement="top" title="delete filter" onclick="deleteThisFilterCard(this,\'' + filterTypeValue + '\');"></a></div>'
				
				+'<div class="row">'
					+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2"><label class="mt-2">Filter Name :</label> </div>'
					+'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'
						+'<div class="form-group mb-1">'
							+'<input type="text" name="filterName" class="form-control" id="filterName">'
							+'<div class="invalid-feedback">Required !</div>'
							+'<i class="form-group__bar"></i>'
						+'</div>'
					+'</div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"><label>Select Source :</label> </div>'
					+'<div class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11 offset-1">'
						+'<div class="form-group">'
							//+waterSourceRatioButtonHtml
						+'</div>'
					+'</div>'
				+'</div>'
			
			+'</div>'
		+'</div>';
		
$("#appendFilterFormsCard").append(htmlCard);
makeToolTip2();
}