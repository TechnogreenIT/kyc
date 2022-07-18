$(function () {
    setNavigation();
});
$('.navigation__sub a').click(function() {
	$(this).children().eq(1).toggleClass("zmdi-plus zmdi-minus");
});

function setNavigation() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);
    
    var remppp = path.split("/");
    var activateHref = remppp[remppp.length-1];
    
    var searchh = window.location.search;
    if(searchh != undefined || searchh != null || searchh != ""){
	 searchh = searchh.replace(/\/$/, "");
	searchh = decodeURIComponent(searchh);
	activateHref = activateHref +searchh;
    }
    
    $(".navigation li a").each(function () {
    	var href = $(this).attr('href');
        if (activateHref === href) {
        	//var ttt = $(this).parents("li:eq(1)").addClass('navigation__sub--active navigation__sub--toggled');
        	var ttt = $(this).parents("li:eq(1)").hasClass('navigation__sub');
        	if(ttt){
        		$(this).parents(".navigation__sub").addClass('navigation__sub--active navigation__sub--toggled');
        		var elementID = $(this).parents(".navigation__sub--toggled").children().find('i:nth-child(2)');
        		var isOpen = elementID.hasClass("zmdi-plus");
        		if(isOpen){
        			elementID.toggleClass("zmdi-plus zmdi-minus");
        		} else {
        			
        		}
        	} else {
        	}
            $(this).closest('li').addClass('navigation__active');
        } else {
        	$(this).closest('li').removeClass('navigation__active');
        }
    });
}
function findNextAccordion(el) {
    var selector = ".ui-accordion-header";
    var accordionId = $(el).closest('.ui-accordion-content').attr('id');
    var spl = Number(accordionId.split("-")[2]);
    spl--;
    var nextAcc = "ui-id-" + spl;

    var elementList = document.querySelectorAll(selector);

    for (var i = 0; i < elementList.length - 1; i++) {
        var tttt = elementList[i].id;
        if (tttt == nextAcc) {
            var triggEle = elementList[i + 1].id;
            $("#" + triggEle).trigger("click");
        }
    }
}

function getAllUnits() {
	var unitOptions = "";
	//var unitOptions = "<option value=''>Select Unit</option>";
	$.ajax({
		type: 'post',
		url: "ajax-getAllUnits",
		success: function (data) {
			var allUnits = JSON.parse(data);
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
function getWaterSourcesOptions() {
	var waterSourceNameOptions = "";
	$.ajax({
		type: 'post',
		url: "ajax-getAllWaterSourcesNames",
		success: function (data) {
			allwaterSourceName = JSON.parse(data);
			$.each(allwaterSourceName, function (index, element) {
				waterSourceNameOptions += "<option value='"+element.waterSourceName+"'>"+element.waterSourceName+"</option>"
			});
		},
		async: false
	});
	return waterSourceNameOptions;
}
function getWaterSourcesNames() {
	var waterSourcesNamesArray = new Array();
	$.ajax({
		type: 'post',
		url: "ajax-getAllWaterSourcesNames",
		success: function (data) {
			allwaterSourceName = JSON.parse(data);
			$.each(allwaterSourceName, function (index, element) {
				waterSourcesNamesArray.push(element.waterSourceName);
			});
		},
		async: false
	});
	return waterSourcesNamesArray;
}
function getWaterFiltersOptions() {
	var waterFilterNameOptions = "";
	$.ajax({
		type: 'post',
		url: "ajax-getAllFiltersNames",
		success: function (data) {
			allFilterName = JSON.parse(data);
			$.each(allFilterName, function (index, element) {
				waterFilterNameOptions += "<option value='"+element.filterName+"'>"+element.filterName+"</option>"
			});
		},
		async: false
	});
	return waterFilterNameOptions;
}
function getWaterFiltersNamesList() {
	var filterNamesArray = "";
	$.ajax({
		type: 'post',
		url: "ajax-getAllFiltersNames",
		success: function (data) {
			var allFilterNames = JSON.parse(data);
			$.each(allFilterNames, function (index, element) {
				filterNamesArray.push(element.filterName);
			});
		},
		async: false
	});
	return filterNamesArray;
}


function getWaterFilterUseNameOptions() {
	var filterUseNamesArray = new Array();
	$.ajax({
		type: 'post',
		url: "ajax-getAllFilterUseNames",
		success: function (data) {
			var allFilterUseName = JSON.parse(data);
			$.each(allFilterUseName, function (index, element) {
				filterUseNamesArray += "<option value='"+element.filterUseName+"'>"+element.filterUseName+"</option>"
			});
		},
		async: false
	});
	return filterUseNamesArray;
}


function getWaterFilterUseName() {
	var filterUseNamesArray = new Array();
	$.ajax({
		type: 'post',
		url: "ajax-getAllFilterUseNames",
		success: function (data) {
			var allFilterUseName = JSON.parse(data);
			$.each(allFilterUseName, function (index, element) {
				filterUseNamesArray.push(element.filterUseName);
			});
		},
		async: false
	});
	return filterUseNamesArray;
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

function getFullName(name){
	if(name == "product" || name =="Product"){
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

function customInputValidator(eleValue, eleName) {
    var flag = 0;
    if (eleValue == "") {
        if (eleName[0] == '#') {
            $(eleName).addClass("is-invalid");
        } else {
            $("input[name=" + eleName + "]").addClass("is-invalid");
        }
        flag--;
   
    } else {
        if (eleName[0] == '#') {
            $(eleName).removeClass("is-invalid");
        } else {
            $("input[name=" + eleName + "]").removeClass("is-invalid");
        }
    }
    return flag;
}

function customSelectValidator(eleValue, eleName) {
    var flag = 0;
    var errSelector = ".invalid-feedback";
    if (eleValue == "") {
        if (eleName[0] == '#') {
            $(eleName).parent().find(errSelector).addClass("d-block");
        } else {
            $("[name=" + eleName + "]").parent().find(errSelector).addClass("d-block");
        }
        flag--;
    } else {
        if (eleName[0] == '#') {
            $("#" + eleName).parent().find(errSelector).removeClass("d-block");
        } else {
            $("[name=" + eleName + "]").parent().find(errSelector).removeClass("d-block");
        }
    }
    return flag;
}

function makeDatepicker(){
	if($('.date-picker')[0]) {
	    $('.date-picker').flatpickr({
	        enableTime: false,
	        nextArrow: '<i class="zmdi zmdi-long-arrow-right" />',
	        prevArrow: '<i class="zmdi zmdi-long-arrow-left" />'
	    });
	}
}

function makedatatable(tableId) {
    if ($('#' + tableId)[0]) {

        // Add custom buttons
        var dataTableButtons = '<div class="dataTables_buttons hidden-sm-down actions">' +
            '<span class="actions__item zmdi zmdi-print black-color" data-table-action="print" />' +
            '<span class="actions__item zmdi zmdi-fullscreen black-color" data-table-action="fullscreen" />' +
            '<div class="dropdown actions__item">' +
            '<i data-toggle="dropdown" class="zmdi zmdi-download black-color" />' +
            '<ul class="dropdown-menu dropdown-menu-right">' +
            '<a href="" class="dropdown-item" data-table-action="excel">Excel (.xlsx)</a>' +
            '<a href="" class="dropdown-item" data-table-action="csv">CSV (.csv)</a>' +
            '</ul>' +
            '</div>' +
            '</div>';

        // Initiate data-table
        $('#' + tableId).DataTable({
            autoWidth: false,
            responsive: true,
            "pageLength": 10,
            lengthMenu: [
                [10, 20, 30, -1],
                ['10 Rows', '20 Rows', '30 Rows', 'Everything']
            ], //Length select
            language: {
                searchPlaceholder: "Search for records..." // Search placeholder
            },
            dom: 'Blfrtip',
            buttons: [ // Data table buttons for export and print
                {
                    extend: 'excelHtml5',
                    title: 'Export Data'
                },
                {
                    extend: 'csvHtml5',
                    title: 'Export Data'
                },
                {
                    extend: 'print',
                    title: 'Material Admin'
                }
            ],
            "initComplete": function(settings, json) {
                $(this).closest('.dataTables_wrapper').prepend(dataTableButtons); // Add custom button (fullscreen, print and export)
            }
        });

        // Data table button actions
        $('body').on('click', '[data-table-action]', function(e) {
            e.preventDefault();

            var exportFormat = $(this).data('table-action');

            if (exportFormat === 'excel') {
                $(this).closest('.dataTables_wrapper').find('.buttons-excel').trigger('click');
            }
            if (exportFormat === 'csv') {
                $(this).closest('.dataTables_wrapper').find('.buttons-csv').trigger('click');
            }
            if (exportFormat === 'print') {
                $(this).closest('.dataTables_wrapper').find('.buttons-print').trigger('click');
            }
            if (exportFormat === 'fullscreen') {
                var parentCard = $(this).closest('.card');

                if (parentCard.hasClass('card--fullscreen')) {
                    parentCard.removeClass('card--fullscreen');
                    $('body').removeClass('data-table-toggled');
                } else {
                    parentCard.addClass('card--fullscreen')
                    $('body').addClass('data-table-toggled');
                }
            }
        });
    }
}

function bootsrapNotify(msg,from,align,timer,type){
	// type = inverse,info,success,warning,danger
	// from = top, bottom
	//align: 'left', 'center' or 'right'
	$.notify({
	    message: msg
	},{
	    type: type,
	    placement: {
	        from: from, // 'top' or 'bottom'
	        align: align // 'left', 'center' or 'right',
	    },
	    timer: timer,
	    allow_dismiss: true,
	    mouse_over:true,
	    animate: {
	        enter: 'animated fadeInRight',
	        exit: 'animated fadeOutRight'
	    },
	    template:   '<div data-notify="container" class="alert alert-dismissible alert-{0} alert--notify" role="alert">' +
	                    '<span data-notify="icon"></span> ' +
	                    '<span data-notify="title">{1}</span> ' +
	                    '<span data-notify="message">{2}</span>' +
	                    '<div class="progress" data-notify="progressbar">' +
	                        '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
	                    '</div>' +
	                    '<a href="{3}" target="{4}" data-notify="url"></a>' +
	                    '<button type="button" aria-hidden="true" data-notify="dismiss" class="close"><i class="zmdi zmdi-close zmdi-hc-fw"></i></button>' +
	                '</div>'
	});
}
function makeToolTip2() {
	if ($('[data-toggle="tooltip"]')[0]) {
		$('[data-toggle="tooltip"]').tooltip();
	}
}

function getUsedSourceAndFilter() {
	var waterSourcesAndFilterLblArray = new Array();
	$.ajax({
		type: 'post',
		url: "ajax-getWaterSourceAndFilterLbl",
		success: function (data) {
			allwaterSourceName = JSON.parse(data);
			$.each(allwaterSourceName, function (index, element) {
				waterSourcesNamesArray.push(element.waterSourceName);
			});
		},
		async: false
	});
	return waterSourcesNamesArray;
}
function defaultSelect2(selectId,selectedValue){
  $("#"+selectId).val($("#"+selectId+" option:contains('"+selectedValue+"')").val()).trigger("change");
}
