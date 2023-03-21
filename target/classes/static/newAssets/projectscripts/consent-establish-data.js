function saveProductionDatas(type, el) {
    var flag = 0;
    var consentId = $("#consent_no").val();
    var dataNames = new Array();
    var dataConsentQuantity = new Array();
    var dataUnit = new Array();

    var inps1 = document.getElementsByName(type + "_name[]");
    var inps2 = document.getElementsByName(type + "_quantity[]");
    var inps3 = document.getElementsByName(type + "_unit[]");
    if (type == "hwp" || type == "hwpcf" || type == "bio") {
        var inps4 = document.getElementsByName(type + "_categories[]");
    }

    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];


        if (type == "hwp" || type == "hwpcf" || type == "bio") {
            var inp4 = inps4[i];
            if (inp4.value == "") {
                flag--;
                inp4.closest('div').lastElementChild.classList.add("d-block");
            } else {
                var tempHzCat = inp4.value;
                inp4.closest('div').lastElementChild.classList.remove("d-block");
            }

            if (inp1.value == "") {
                flag--;
                inp1.classList.add("is-invalid");
            } else {
                var tempHzName = inp1.value;
                inp1.classList.remove("is-invalid");
            }
            var nameValue = tempHzCat + "-" + tempHzName;

        } else {

            if (inp1.value == "") {
                flag--;
                inp1.classList.add("is-invalid");
            } else {
                var nameValue = inp1.value;
                inp1.classList.remove("is-invalid");
            }
        }



        if (inp2.value == "") {
            flag--;
            inp2.classList.add("is-invalid");
        } else {
            inp2.classList.remove("is-invalid");
        }
        if (inp3.value == "") {
            flag--;
            inp3.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inp3.closest('div').lastElementChild.classList.remove("d-block");
        }

        dataNames.push(nameValue);
        dataConsentQuantity.push(inp2.value);
        dataUnit.push(inp3.value);
    }


    if (flag == 0) {
        $.ajax({
            type: "POST",
            url: "ajax-add-consentdata",
            data: ({
                productName: dataNames.toString(),
                productType: type,
                units: dataUnit.toString(),
                quantity: dataConsentQuantity.toString(),
                consentNo: consentId
            }),
            success: function(data) {
                $("#save-" + type + "-btn").attr("disabled", true);
                findNextAccordion(el);
                jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
                
                var res = data; //alert(data);
                //if(inps.length == res){}else{}
                
                if(type == "hwpcf"){
                	window.location.replace("http://localhost:8080/env/dashboard");
                }
            }
        });

    } else {
        jBoxBottomRightBigNotice("Warning", "Fill Some Data !!", "yellow", "3500");
    }

}

function showStackForm(data,el) {
    if (data == "Yes") {
        $("#stackForm").show();
    } else if (data == "Popup") {
        console.log("Open Stack Excel popup");
        $("#stackForm").hide();
        $('#upload-stack-excel-modal').modal('show');
    } else {
        $("#stackForm").hide();
    }
}



/*function showStackForm(data) {
	var modelTitle='<div class="modal fade" id="upload-stack-excel-modal"'+
	    'data-backdrop="static" tabindex="-1">'+
	    '<div class="modal-dialog">'+
		'<div class="modal-content">'+
	    '<div class="modal-header">'+
		'<h4 class="modal-title font-weight-bold">Upload multiple stack data</h4>'+
		'<div class="actions">'+
		'<span class="mantooltip hover" data-jbox-title=" " data-jbox-content="download stack and stack parameter <br>files and add multiple stack data easily.">'+
         '<a class="actions__item zmdi zmdi-help"></a></span>'+
		 '<div class="dropdown actions__item">'+ '</div>'+
		 '<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>'+
		 '<div class="dropdown-menu dropdown-menu-right custom-dropdown-menu">'+
		 '<a class="ml-2" href="../newAssets/documents/Stack.xlsx">'+
          '<button class="btn btn-success">'+ '</div>'+
		  '<i class="zmdi zmdi-download"></i> Stack details</button>'+
           '</a> <a class="ml-2" href="../newAssets/documents/StackParameter.xlsx">'+
          '<button class="btn btn-success"><i class="zmdi zmdi-download"></i> Stack parameter</button></a>'+
		 '</div>'+
		  '</div>'+
		  '</div>'+
	      '</div>'+
		  '</div>';
	var bodyForm=
         '<div class="modal-body row">'+
		  '<div class="col-6">'+
		  '<div class="fileinput fileinput-new" data-provides="fileinput">'+
		  '<label>Upload .csv file of stack details:</label>'+
           '<span class="btn btn-primary btn-file"> '+
           '<span class="fileinput-new">Select file</span>'+
           '<span class="fileinput-exists">Change</span>'+
           '<input type="file" name="stackFile" id="stackFile" accept=".xlsx" required></span>'+
           '<span class="fileinput-filename"></span>'+ 
           '<a href="#"class="close fileinput-exists" data-dismiss="fileinput">&times;</a></div>'+
			'</div>'+
			'<div class="col-6">'+
			'<div class="fileinput fileinput-new" data-provides="fileinput">'+
			'<label>Upload .csv file of stack parameter details</label>'+ 
            '<span class="btn btn-primary btn-file">'+ 
            '<span class="fileinput-new">Select file</span>'+
            '<span class="fileinput-exists">Change</span>'+
            '<input type="file"name="stackParameter" id="stackParameter" accept=".xlsx" required></span>'+
			  '<span class="fileinput-filename"></span>'+
             '<a href="#" class="close fileinput-exists" data-dismiss="fileinput">&times;</a>'+
			 '</div>'+
			 '</div>'+
			'<div class="col-12 text-center">'+
			'<button class="btn btn-primary btn--icon-text mt-4" id="saveD" onclick="saveExcelSheet('+"stack"+')">'+
			 '<i class="zmdi zmdi-upload"></i> Upload </button>'+
			'</div>'+
			'</div>'+
			'<div class="modal-footer">'+
			'<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>'+
			 '</div>';
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-lg',
		body: bodyForm,
		//footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Upload</button><button type='upload' onclick='saveExcelSheet(\"" + type + "\",this)' class='btn btn-primary'>close</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
}*/

function showAmbientForm(data,el) {
    if (data == "Yes") {
        $("#ambientForm").show();
    } else if (data == "Popup") {
        $("#ambientForm").hide();
        $('#upload-ambient-excel-modal').modal('show');
    } else {
        $("#ambientForm").hide();
    }
}

function showAPC(data) {
    console.log("showAPC >" + data);
    if (data == "Yes") {
        $("#apcForm").show();
    } else {
        $("#apcForm").hide();
    }
}

function addLimitsUnits(id, type) {
    if ($("#" + type + "pollCheckBox" + id).is(":checked")) {
        console.log(type + "pollCheckBox" + id + "  > checked !");
        var unitOptions = getAllUnits();
        var html = "<div class='row'><div class='col-6'>" +
            "<div class='form-group'>" +
            "<input type='number' class='form-control' name='" + type + "limit[]' placeholder='Limits' >" +
            "<div class='invalid-feedback'>Required !</div>" +
            "<i class='form-group__bar'></i>" +
            "</div>" +
            "</div>" +
            "<div class='col-6'>" +
            "<div class='form-group'>" +
            "<select class='select2' name='" + type + "units[]'>" +
            "<option value=''>Select unit</option>" +
            unitOptions +
            "</select>" +
            "<div class='invalid-feedback'>Required !</div>" +
            "</div>" +
            "</div></div>";

        $("#append" + type + "LimitPoll_" + id).append(html);
        makeSelect2();
    } else {
        $("#append" + type + "LimitPoll_" + id).empty();
        console.log("stackpollCheckBox" + id + "  > not checked !");
    }
}

function saveStackData(el) {
    var a_name = new Array();
    var a_quantity = new Array();
    var a_unit = new Array();
    var main = new Array();
    var flag = 0;
    var consent_no = $("#consent_no").val();
    var stack_name = $("input[name=stack_name]").val();
    var attached_to = $("select[name=attached_to]").val();
    var capacity = $("input[name=capacity]").val();
    var capacity_units = $("input[name=capacity_units]").val();
    var mat_cons = $("input[name=mat_cons]").val();
    var shape = $("input[name=shape]").val();
    var fuel_type = $("input[name=fuel_type]").val();
    var height = $("input[name=height]").val();
    var ht_units = $("input[name=ht_units]").val();
    var diam = $("input[name=diam]").val();
    var diam_units = $("input[name=diam_units]").val();
    var fuel_quant = $("input[name=fuel_quant]").val();
    var fuel_units = $("select[name=fuel_units]").val();
     //changes by pallavi
    var gas_quant = $("input[name=gas_quant]").val();
    var gas_unit = $("input[name=gas_unit]").val();
    var gas_temp = $("input[name=gas_temp]").val();
    var gas_temp_unit = $("input[name=gas_temp_unit]").val()
    var Exit_gas_vel = $("input[name=Exit_gas_vel]").val();
     var Exit_gas_unit = $("input[name=Exit_gas_unit]").val();
      var preceding_stack = $("input[name=preceding_stack]").val();
       var pollu_present = $("input[name=pollu_present]").val();
      var ECS_provided = $("input[name=ECS_provided]").val();
       var gen_capacity = $("input[name=gen_capacity]").val();
        var gen_Cap_unit = $("input[name=gen_Cap_unit]").val();
   
    
    var apc = $("input[name='apc']:checked").val();
    var apc_system = null;

    if (apc == "Yes") {
        apc_system = $("input[name=apc_system]").val().trim();
        flag += customInputValidator(apc_system, "apc_system");
    } else {
        apc_system = "NA";
    }

    flag += customInputValidator(stack_name, "#stack_name");
    flag += customInputValidator(capacity, "capacity");
    flag += customInputValidator(capacity_units, "capacity_units");
    flag += customInputValidator(mat_cons, "mat_cons");
    flag += customInputValidator(shape, "shape");
    flag += customInputValidator(fuel_type, "fuel_type");
    flag += customInputValidator(height, "height");
    flag += customInputValidator(ht_units, "ht_units");
    flag += customInputValidator(diam, "diam");
    flag += customInputValidator(diam_units, "diam_units");
    flag += customInputValidator(fuel_quant, "fuel_quant");
    flag += customSelectValidator(fuel_units, "fuel_units");
    flag += customSelectValidator(attached_to, "attached_to");
    //changes by pallavi..
     flag += customSelectValidator(gas_quant, "gas_quant");
     flag += customSelectValidator(gas_unit, "gas_unit");
     flag += customSelectValidator(gas_temp, "gas_temp");
     flag += customSelectValidator(gas_temp_unit, "gas_temp_unit");
     flag += customSelectValidator(Exit_gas_vel, "Exit_gas_vel");
     flag += customSelectValidator(Exit_gas_unit, "Exit_gas_unit");
     flag += customSelectValidator(preceding_stack, "preceding_stack");
     flag += customSelectValidator(pollu_present, "pollu_present");
     flag += customSelectValidator(ECS_provided, "ECS_provided");
     flag += customSelectValidator(gen_capacity, "gen_capacity");
     flag += customSelectValidator(gen_Cap_unit, "gen_Cap_unit");
 
    var checkedBoxes = $("input[name='stack_poll[]']:checked");
    for (var a = 0; a < checkedBoxes.length; a++) {
        var checkBox = checkedBoxes[a].value;

        a_name.push(checkBox);

        var inps = $("input[name='stacklimit[]']")[a];
        var inps1 = $("[name='stackunits[]']")[a];
        var stackPollLimit = inps.value;
        var stackPollLimitUnit = inps1.value;

        if (stackPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            a_quantity.push(stackPollLimit);
        }

        if (stackPollLimitUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            a_unit.push(stackPollLimitUnit);
        }

    }

    var stExtraPoll = $("input[name='st_extra_poll[]']");
    for (var a = 0; a < stExtraPoll.length; a++) {

        var inpPollsName = $("input[name='st_extra_poll[]']")[a];
        var inps = $("input[name='st_extra_limit[]']")[a];
        var inps1 = $("[name='st_extra_units[]']")[a];

        var stExtraPollName = inpPollsName.value;
        var stExtraPollLimit = inps.value;
        var stExtraPollUnit = inps1.value;

        if (stExtraPollName == "") {
            flag--;
            inpPollsName.classList.add("is-invalid");
        } else {
            inpPollsName.classList.remove("is-invalid");
            a_name.push(stExtraPollName);
        }

        if (stExtraPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            a_quantity.push(stExtraPollLimit);
        }

        if (stExtraPollUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            a_unit.push(stExtraPollUnit);
        }

    }


    if (flag == 0) {
        main.push(stack_name);
        main.push(attached_to);
        main.push(capacity);
        main.push(capacity_units);
        main.push(mat_cons);
        main.push(shape);
        main.push(fuel_type);
        main.push(height);
        main.push(ht_units);
        main.push(diam);
        main.push(diam_units);
        main.push(fuel_quant);
        main.push(fuel_units);
        main.push(apc);
        main.push(apc_system);

        //changes by pallavi..
        main.push(gas_quant);
        main.push(gas_unit);
        main.push(gas_temp);
        main.push(gas_temp_unit);
        main.push(Exit_gas_vel);
        main.push(Exit_gas_unit);
        main.push(preceding_stack);
        main.push(pollu_present);
        main.push(ECS_provided);
        main.push(gen_capacity);  
        main.push(gen_Cap_unit);
                            
       
        $(document).ready(function() {
            $.ajax({
                type: "POST",
                url: "ajax-add-stack-detail",
                data: {
                    consent_no: consent_no,
                    main: main.toString(),
                    a_name: a_name.toString(),
                    a_quantity: a_quantity.toString(),
                    a_unit: a_unit.toString()
                },
                success: function(data) {
                    $("#save-stack-btn").attr("disabled", true);
                    findNextAccordion(el);
                    jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
                }
            });
        });

    } else {
        jBoxBottomRightBigNotice("Warning", "Fill Some Data !!", "yellow", "3500");
    }
}

function saveAmbientData(el) {
    var a_name = new Array();
    var a_quantity = new Array();
    var a_unit = new Array();
    var flag = 0;

    var consent_no = $("input[name=consent_no]").val();
    var loc_ident = $("input[name=loc_ident]").val();
    var criteria = $("select[name=criteria]").val();

    flag += customInputValidator(loc_ident, "#loc_ident");
    flag += customSelectValidator(criteria, "criteria");


    var checkedBoxes = $("input[name='ambient_poll[]']:checked");
    for (var a = 0; a < checkedBoxes.length; a++) {
        var checkBox = checkedBoxes[a].value;

        a_name.push(checkBox);

        var inps = $("input[name='ambientlimit[]']")[a];
        var inps1 = $("[name='ambientunits[]']")[a];
        var ambientPollLimit = inps.value;
        var ambientPollLimitUnit = inps1.value;

        if (ambientPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            a_quantity.push(ambientPollLimit);
        }

        if (ambientPollLimitUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            a_unit.push(ambientPollLimitUnit);
        }

    }

    var stExtraPoll = $("input[name='amb_extra_poll[]']");
    for (var a = 0; a < stExtraPoll.length; a++) {

        var inpPollsName = $("input[name='amb_extra_poll[]']")[a];
        var inps = $("input[name='amb_extra_limit[]']")[a];
        var inps1 = $("[name='amb_extra_units[]']")[a];

        var ambientExtraPollName = inpPollsName.value;
        var ambientExtraPollLimit = inps.value;
        var ambientExtraPollUnit = inps1.value;

        if (ambientExtraPollName == "") {
            flag--;
            inpPollsName.classList.add("is-invalid");
        } else {
            inpPollsName.classList.remove("is-invalid");
            a_name.push(ambientExtraPollName);
        }

        if (ambientExtraPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            a_quantity.push(ambientExtraPollLimit);
        }

        if (ambientExtraPollUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            a_unit.push(ambientExtraPollUnit);
        }

    }

    if (flag == 0) {

        $.ajax({
            type: "POST",
            url: "ajax-add-ambient-data",
            data: {
                consentId: consent_no,
                locIdent: loc_ident,
                criteria: criteria,
                pollName: a_name.toString(),
                limits: a_quantity.toString(),
                units: a_unit.toString()
            },
            success: function(data) {
                $("#save-ambient-btn").attr("disabled", true);
                findNextAccordion(el);
                jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
            }
        });

    } else {
        jBoxBottomRightBigNotice("Warning", "Fill Some Data !!", "yellow", "3500");
    }
}

function saveWaterPollutant(el) {
    var consent_no = $("input[name=consent_no]").val();

    // EFF
    var effPollNames = new Array();
    var effPollQuantity = new Array();
    var effPollUnits = new Array();
    var flag = 0;

    var checkedBoxes = $("input[name='eff_poll[]']:checked");
    for (var a = 0; a < checkedBoxes.length; a++) {
        var checkBox = checkedBoxes[a].value;

        effPollNames.push(checkBox);

        var inps = $("input[name='efflimit[]']")[a];
        var inps1 = $("[name='effunits[]']")[a];
        var effPollLimit = inps.value;
        var effPollLimitUnit = inps1.value;

        if (effPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            effPollQuantity.push(effPollLimit);
        }

        if (effPollLimitUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            effPollUnits.push(effPollLimitUnit);
        }
    }
    var effExtraPoll = $("input[name='eff_extra_poll[]']");
    for (var a = 0; a < effExtraPoll.length; a++) {

        var inpPollsName = $("input[name='eff_extra_poll[]']")[a];
        var inps = $("input[name='eff_extra_limit[]']")[a];
        var inps1 = $("[name='eff_extra_units[]']")[a];

        var effExtraPollName = inpPollsName.value;
        var effExtraPollLimit = inps.value;
        var effExtraPollUnit = inps1.value;

        if (effExtraPollName == "") {
            flag--;
            inpPollsName.classList.add("is-invalid");
        } else {
            inpPollsName.classList.remove("is-invalid");
            effPollNames.push(effExtraPollName);
        }

        if (effExtraPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            effPollQuantity.push(effExtraPollLimit);
        }

        if (effExtraPollUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            effPollUnits.push(effExtraPollUnit);
        }
    }

    //SEWAGE
    var sewPollNames = new Array();
    var sewPollQuantity = new Array();
    var sewPollUnits = new Array();

    var checkedBoxes = $("input[name='sew_poll[]']:checked");
    for (var a = 0; a < checkedBoxes.length; a++) {
        var checkBox = checkedBoxes[a].value;

        sewPollNames.push(checkBox);

        var inps = $("input[name='sewlimit[]']")[a];
        var inps1 = $("[name='sewunits[]']")[a];
        var sewPollLimit = inps.value;
        var sewPollLimitUnit = inps1.value;

        if (sewPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            sewPollQuantity.push(sewPollLimit);
        }

        if (sewPollLimitUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            sewPollUnits.push(sewPollLimitUnit);
        }
    }
    var sewExtraPoll = $("input[name='sew_extra_poll[]']");
    for (var a = 0; a < sewExtraPoll.length; a++) {

        var inpPollsName = $("input[name='sew_extra_poll[]']")[a];
        var inps = $("input[name='sew_extra_limit[]']")[a];
        var inps1 = $("[name='sew_extra_units[]']")[a];

        var sewExtraPollName = inpPollsName.value;
        var sewExtraPollLimit = inps.value;
        var sewExtraPollUnit = inps1.value;

        if (sewExtraPollName == "") {
            flag--;
            inpPollsName.classList.add("is-invalid");
        } else {
            inpPollsName.classList.remove("is-invalid");
            sewPollNames.push(sewExtraPollName);
        }

        if (sewExtraPollLimit == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            sewPollQuantity.push(sewExtraPollLimit);
        }

        if (sewExtraPollUnit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            sewPollUnits.push(sewExtraPollUnit);
        }
    }

//    var checkedBoxes = $("input[name='sew_poll[]']:checked");
//    for (var a = 0; a < checkedBoxes.length; a++) {
//        var checkBox = checkedBoxes[a].value;
//
//        sewPollNames.push(checkBox);
//
//        var inps = $("input[name='sewlimit[]']")[a];
//        var inps1 = $("[name='sewunits[]']")[a];
//        var sewPollLimit = inps.value;
//        var sewPollLimitUnit = inps1.value;
//
//        if (sewPollLimit == "") {
//            flag--;
//            inps.classList.add("is-invalid");
//        } else {
//            inps.classList.remove("is-invalid");
//            sewPollQuantity.push(sewPollLimit);
//        }
//
//        if (sewPollLimitUnit == "") {
//            flag--;
//            inps1.closest('div').lastElementChild.classList.add("d-block");
//        } else {
//            inps1.closest('div').lastElementChild.classList.remove("d-block");
//            sewPollUnits.push(sewPollLimitUnit);
//        }
//    }

    if (flag == 0 && effPollNames.length + sewPollNames.length > 0) {
        $.ajax({
            type: "POST",
            url: "ajax-add-water-pollutant-data",
            data: {
                consent_no: consent_no,
                e_a_name: effPollNames.toString(),
                e_a_quantity: effPollQuantity.toString(),
                e_a_unit: effPollUnits.toString(),
                s_a_name: sewPollNames.toString(),
                s_a_quantity: sewPollQuantity.toString(),
                s_a_unit: sewPollUnits.toString()
            },
            success: function(data) {
                $("#save-waterPoll-btn").attr("disabled", true);
                findNextAccordion(el);
                jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
            }
        });
    } else {
        jBoxBottomRightBigNotice("Warning", "Fill Some Data !!", "yellow", "3500");
    }

}

function saveWaterConGen(el, type) {
    var consent_no = $("input[name=consent_no]").val();
    var a_purpose = new Array();
    var a_consum = new Array();
    var a_gen = new Array();
    var flag = 0;
    var inps1 = document.getElementsByName("purpose[]");
    var inps2 = document.getElementsByName("consum[]");
    var inps3 = document.getElementsByName("gen[]");

    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];
        var inp3 = inps3[i];

        a_purpose.push(inp1.value);

        if (inp2.value == "") {
            flag--;
            inp2.classList.add("is-invalid");
        } else {
            a_consum.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }

        if (inp3.value == "") {
            flag--;
            inp3.classList.add("is-invalid");
        } else {
            a_gen.push(inp3.value);
            inp3.classList.remove("is-invalid");
        }

    }

    if (flag == 0) {
        $.ajax({
            type: "POST",
            url: "ajax-add-watercongen",
            data: {
                consent_no: consent_no,
                a_purpose: a_purpose.toString(),
                a_consum: a_consum.toString(),
                a_gen: a_gen.toString()
            },
            success: function(data) {
                $("#save-" + type + "-btn").attr("disabled", true);
                findNextAccordion(el);
                jBoxBottomRightBigNotice("Success", "Data Saved !!", "green", "2000");
            }
        });
    } else {
        jBoxBottomRightBigNotice("Warning", "Some Data Missing !!", "yellow", "3500");
    }

}

function conSum(el) {
    var dom = $("#domes_consum").val();
    var ecSum = +(document.getElementById("biodeg_consum").value) + +(document.getElementById("notbiodeg_consum").value) + +(document.getElementById("ind_consum").value);
    cSum = +dom + +ecSum;
    $("#cRes").val(cSum);
    $("#dcRes").val(dom);
    $("#ecRes").val(ecSum);
}

function genSum(id) {
    var domgen = document.getElementById("domes_gen").value;
    var egSum = +(document.getElementById("biodeg_gen").value) + +(document.getElementById("notbiodeg_gen").value) + +(document.getElementById("ind_gen").value);
    gSum = +domgen + +egSum;
    $("#gRes").val(gSum);
    $("#dgRes").val(domgen);
    $("#egRes").val(egSum);
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

function saveExcelSheet(type) {
    var consent_no = $("#consent_no").val();
    var valid = 0;
    var filename = $('[name=' + type + 'File]').val();
    //var type = "stack";
    if (filename != "") {
        var file_data = $('[name=' + type + 'File]').prop('files')[0]; //alert(file_data)
        filename1 = file_data.name;
        if (filename1 == "") {
            valid--;
        }
    } else {
        valid--;
    }

    var stackparameterfilename = $('[name=' + type + 'Parameter]').val();
    if (stackparameterfilename != "") {
        var stackparameter_file_data = $('[name=' + type + 'Parameter]').prop('files')[0]; //alert(file_data)
        filename1 = stackparameter_file_data.name;
        if (filename1 == "") {
            valid--;
        }
    } else {
        valid--;
    }
    if (valid == 0) {
        var form_data = new FormData();
        form_data.append('file', file_data);
        form_data.append('file', stackparameter_file_data);

        $.ajax({
            url: "ajax-save-data?action=fileUploading&fn=" + type + "&consent_no=" + consent_no,
            contentType: false,
            processData: false,
            data: form_data,
            type: 'post',
            success: function(res) {
	            //$("#save-" + type + "-btn").attr("disabled", true);
	            // $('#upload-' + type + '-btn').attr("disabled", true)
               $('#upload-' + type +'-excel-modal').modal('hide');
               // findNextAccordion(el);
            }
        });
    }
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

function storeValues(divId, form1, form2, form3, form4, array1, array2, array3, array4, flag, sourceId) {
    for (var i = 0; i < form1.length; i++) {
        sourceId++;
        var inp = form1[i];
        var inp1 = form2[i];
        var inp2 = form3[i];
        var inp3 = form4[i];

        if ((inp2.value == "" || inp2.value == 00) && inp1.value == "") {
            $("#" + divId + sourceId).html("<label class='text-red'>Enter meter reading</label>");
            flag++;
        } else {
            array1.push(inp.value);
            array2.push(inp1.value);
            array3.push(inp2.value);
            array4.push(inp3.value);
            $("#" + divId + sourceId).html("");
            flag--;
        }
    }
    return sourceId;
}