function setQuantityUnit(id){
		 if ($("#wasteProductName"+ id).is(":checked")) {
			 var html = "<div class='col-3'><div class='form-group'>"
					+"<input type='text' name='tot_quantity_waste[]' id='tot_green_area' class='form-control' placeholder='Total Quantity of Waste'>"
					+"<div class='invalid-feedback'>Required !</div>"
					+"<i class='form-group__bar'></i>"
					+"</div></div>"
					+"<div class='col-2'><div class='form-group'>"
					+"<select class='select2' data-placeholder='Select Units' name='tot_quantity_waste_units[]'>"
					+"<option value=''>Select Units</option>"
					+"<option value='kg'>kg</option>"
					+"<option value='m3'>m3</option>"
					+"<option value='Tons'>Tons</option>"
					+"</select>"
					+"<div class='invalid-feedback'>Required !</div>"
					+"</div></div>"
					+"<div class='col-3'><div class='form-group'>"
					+"<select class='select2' data-placeholder='Select Consistency' name='consistency[]'>"
					+"<option value=''>Select Consistency</option>"
					+"<option value='Solid'>Solid</option>"
					+"<option value='Semi-Solid'>Semi-Solid</option>"
					+"<option value='Sludge'>Sludge</option>"
					+"<option value='Oily'>Oily</option>"
					+"<option value='Tarry'>Tarry</option>"
					+"<option value='Slurry'>Slurry</option>"
					+"</select>"
					+"<div class='invalid-feedback'>Required !</div>"
					+"</div></div>";

			  $("#quantity_id_"+ id).append(html);
			 }else{
				 $("#quantity_id_"+ id).empty();
				 }
		 makeSelect2();
}

function AddContainer(){
	var randomId = randomIdNumber();
	var hzHtml = "";
	var containerId = "container"+randomId;
			var html = "<div class='row' id='"+containerId+"'><div class='col-sm-1'></div>"
				+"<div class='col-sm-3'>"
				+"<label>No. of Containers:</label>"
				+"</div>"
				+"<div class='col-sm-8'>"
				+"<div class='row'><div class='col-5'>"
				+"<div class='form-group'>"
				+"<input type='text' name='containers_Number[]' class='form-control' placeholder='Container Number'>"
				+"<div class='invalid-feedback'>Required ! !</div>"
				+"<i class='form-group__bar'></i></div></div>"
				+"<div class='col-5'><div class='form-group'>"
				+"<input type='text' name='containers_Type[]' class='form-control' placeholder='Type'>"
				+"<div class='invalid-feedback'>Required ! !</div>"
				+"<i class='form-group__bar'></i></div></div>"
				+"<div class='col-2'><div class='form-group'>"
				+"<button type='button' class='btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light' onclick='removeElement(\""+containerId+"\");'>"
				+"<i class='zmdi zmdi-delete'></i> Remove</button>"
				+"</div></div>"
				+"</div></div></div>"
	$(html).insertAfter("#appendContainers");
	makeSelect2();
}

function randomIdNumber() {
	var min = 1;
	var max = 5000;
	return Math.round(Math.random() * (+max - +min) + +min);
}
function removeElement(removeId){
	$("#"+removeId).remove();
}

function saveHWManifest(){
	var form_data = new FormData();
	var flag = 0;
	var containersNumber=new Array();
	var containersType=new Array();
	var sendersName=$("input[name=sendersName]").val();
	flag += customInputValidator(sendersName, "sendersName");

	var sendersMailingAddress=$("input[name=sendersMailingAddress]").val();
	flag += customInputValidator(sendersMailingAddress, "sendersMailingAddress");
	
	var sendersPhoneNo=$("input[name=sendersPhoneNo]").val();
	flag += customInputValidator(sendersPhoneNo, "sendersPhoneNo");
	
	var sendersAuthorizationNo=$("input[name=sendersAuthorizationNo]").val();
	flag += customInputValidator(sendersAuthorizationNo, "sendersAuthorizationNo");
	
	var manifestDocumentsNo=$("input[name=manifestDocumentsNo]").val();
	flag += customInputValidator(manifestDocumentsNo, "manifestDocumentsNo");
	
	var transporterName=$("input[name=transporterName]").val();
	flag += customInputValidator(transporterName, "transporterName");
	
	var transporterAddress=$("input[name=transporterAddress]").val();
	flag += customInputValidator(transporterAddress, "transporterAddress");
	
	var transportermobilepNo=$("input[name=transportermobilepNo]").val();
	flag += customInputValidator(transportermobilepNo, "transportermobilepNo");
	
	var vehicle_type=$("input[name=vehicle_type]").val();
	var transporterRegistrationNo=$("input[name=transporterRegistrationNo]").val();
	flag += customInputValidator(transporterRegistrationNo, "transporterRegistrationNo");
	
	var transporterVehicleRegistrationNo=$("input[name=transporterVehicleRegistrationNo]").val();
	flag += customInputValidator(transporterVehicleRegistrationNo, "transporterVehicleRegistrationNo");
	
	var receiversName=$("input[name=receiversName]").val();
	flag += customInputValidator(receiversName, "receiversName");
	
	var receiversAddress=$("input[name=receiversAddress]").val();
	flag += customInputValidator(receiversAddress, "receiversAddress");
	
	var receiversAuthorizationNo=$("input[name=receiversAuthorizationNo]").val();
	flag += customInputValidator(receiversAuthorizationNo, "receiversAuthorizationNo");
	
	var receiversPhoneNo=$("input[name=receiversPhoneNo]").val();
	flag += customInputValidator(receiversPhoneNo, "receiversPhoneNo");
	
	var transport_desc_waste=$("input[name=transport_desc_waste]").val();
	flag += customInputValidator(transport_desc_waste, "transport_desc_waste");
	
	var totQuantityContainer=$("input[name=tot_quantity_container]").val();
	flag += customInputValidator(totQuantityContainer, "tot_quantity_container");
	
	var totQuantityContainerUnit= $('#tot_Quantity_container_unit').val();
	flag += customSelectValidator(totQuantityContainerUnit, "tot_Quantity_container_unit");
	
	var specialHandling=$("input[name=specialHandling]").val();
	flag += customSelectValidator(specialHandling, "specialHandling");
	
	var subdate=$("input[name=subdate]").val();
	flag += customSelectValidator(subdate, "subdate");
	
	var dispatchedTo=$('#dispatched_to option:selected').text();
	var wasteName=new Array();
	var wasteQuantityUnits=new Array();
	var wasteQuantity=new Array();
	var consistency=new Array();
	
	
	//xskjd
	var inps1 = document.getElementsByName("containers_Number[]");
    var inps2 = document.getElementsByName("containers_Type[]");

    for (var i = 0; i < inps1.length; i++) {
        var inp1 = inps1[i];
        var inp2 = inps2[i];

        if (inp1.value == "") {
            flag--;
            inp1.classList.add("is-invalid");
        } else {
        	containersNumber.push(inp1.value);
            inp1.classList.remove("is-invalid");
        }

        if (inp2.value == "") {
            flag--;
            inp2.classList.add("is-invalid");
        } else {
        	containersType.push(inp2.value);
            inp2.classList.remove("is-invalid");
        }

    }
    var checkedBoxes = $("input[name='wasteProductName[]']:checked");
    for (var a = 0; a < checkedBoxes.length; a++) {
        var checkBox = checkedBoxes[a].value;

        wasteName.push(checkBox);

        var inps = $("input[name='tot_quantity_waste[]']")[a];
        var inps1 = $("[name='tot_quantity_waste_units[]']")[a];
        var inps2 = $("[name='consistency[]']")[a];
        var quntity = inps.value;
        var Unit = inps1.value;
        var consitancy = inps2.value;

        if (quntity == "") {
            flag--;
            inps.classList.add("is-invalid");
        } else {
            inps.classList.remove("is-invalid");
            wasteQuantity.push(quntity);
        }

        if (Unit == "") {
            flag--;
            inps1.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps1.closest('div').lastElementChild.classList.remove("d-block");
            wasteQuantityUnits.push(Unit);
        }
        if (consitancy == "") {
            flag--;
            inps2.closest('div').lastElementChild.classList.add("d-block");
        } else {
            inps2.closest('div').lastElementChild.classList.remove("d-block");
            consistency.push(consitancy);
        }

    }
    
	
	form_data.append('sendersName',sendersName);
	form_data.append('sendersMailingAddress',sendersMailingAddress);
	form_data.append('sendersPhoneNo',sendersPhoneNo);
	form_data.append('sendersAuthorizationNo',sendersAuthorizationNo);
	
	form_data.append('manifestDocumentsNo',manifestDocumentsNo);
	form_data.append('transporterName',transporterName);
	form_data.append('transporterAddress',transporterAddress);
	form_data.append('transportermobilepNo',transportermobilepNo);
	form_data.append('transporterRegistrationNo',transporterRegistrationNo);
	form_data.append('transporterVehicleRegistrationNo',transporterVehicleRegistrationNo);
	form_data.append('vehicle_type',vehicle_type);
	
	form_data.append('receiversName',receiversName);
	form_data.append('receiversAddress',receiversAddress);
	form_data.append('receiversAuthorizationNo',receiversAuthorizationNo);
	form_data.append('receiversPhoneNo',receiversPhoneNo);
	
	form_data.append('transport_desc_waste',transport_desc_waste);
	form_data.append('totQuantityContainer',totQuantityContainer);
	form_data.append('totQuantityContainerUnit',totQuantityContainerUnit);
	form_data.append('specialHandling', specialHandling);
	form_data.append('subdate', subdate);
	
	form_data.append('containersNumber', containersNumber);
	form_data.append('containersType', containersType);
	
	form_data.append('wasteName', wasteName);
	form_data.append('wasteQuantity', wasteQuantity);
	form_data.append('wasteQuantityUnits', wasteQuantityUnits);
	form_data.append('consistency', consistency);
	
	if(flag == 0){
		$.ajax({
			url : "ajax-add-hw-manifest",
			dataType: 'json',
			cache: false,
			contentType: false,
			processData: false,
			data: form_data,
			type: 'post',
			success : function(data) {
				if (data == "success") {
					jBoxBottomRightBigNotice("Success",
							"Hazardous Manifest Saved !!", "green", "2000");
					window.location.href = "view-manifest";
				}
	
			},
			error : function(xhr, type) {
				jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n",
						"red", "2000");
				window.setTimeout(window.location = "logout", 7000)
			},
			async : false
		});
	}
}
function openDaysHazManifest(){
	var year = $("#viewManifest_year").val();
	var month = $("#viewManifest_month").val();
	$.ajax({
        type: "POST",
        url: "ajax-day-hw-manifest",
        data: ({
            year: year,
            month: month
        }),
        success: function(data) {
        	$('#viewManifest_day').empty();
        	var html = "<option value=''>Select day</option>";
    		for(k = 0; k < data.length; k++){
    			if( data[k] < 10){
    				var dayy = "0"+data[k];
    			} else {
    				var dayy = data[k];
    			}
    			html += "<option value='"+dayy+"'>"+dayy+"</option>";
    		}
    		$("#viewManifest_day").append(html);
        }
    });
		
	
}
function onDaySetGetDataHazData(){
	var year = $("#viewManifest_year").val();
	var month = $("#viewManifest_month").val();
	var day = $("#viewManifest_day").val();
	today = year+"-"+month+"-"+day;
	$.ajax({
        type: "POST",
        url: "ajax-hw-manifest-data",
        data: ({
        	selectedDate : today
        }),
        success: function(data) {
        	var data1 = JSON.parse(data);
        	$.each(data1, function (index, element) {
				var Container = element.container;
				var WasteDescription = element.WasteDescription;
				var WasteDescriptionCount = 0;var ContainerCount = 0;
				
				var html1 = "<div class='row'>"
							+"<div class='col-sm-1'>1</div><div class='col-sm-3'>"
							+"<label>SEnder's Name & Mailing Address (including Phone No. and e-mail):</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='row'>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.SendersName+" disabled>"
							+"</div></div>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.SendersMailAddress+" disabled>"
							+"</div></div>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.SendersPhoneNo+" disabled>"
							+"</div></div>"
							+"</div></div>"
							+"<div class='col-sm-1'>2</div><div class='col-sm-3'>"
							+"<label>Sender's authentication No:</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.SendersAuthNo+" disabled>"
							+"</div></div>"
							+"<div class='col-sm-1'>3</div><div class='col-sm-3'>"
							+"<label>Manifest Document No:</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.ManifestDocumentNo+" disabled>"
							+"</div></div>"
							+"<div class='col-sm-1'>4</div><div class='col-sm-3'>"
							+"<label>Transporteis Name & Mailing Address (including Phone No. and e-mail):</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='row'>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.TransportsName+" disabled>"
							+"</div></div>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.TransportAddress+" disabled>"
							+"</div></div>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.TransportPhoneNo+" disabled>"
							+"</div></div>"
							+"</div></div>"
							+"<div class='col-sm-1'>5</div><div class='col-sm-3'>"
							+"<label>Type of Vehicle:</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.VehicleType+" disabled>"
							+"</div></div>"
							+"<div class='col-sm-1'>6</div><div class='col-sm-3'>"
							+"<label>Transporter's Registration No:</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.TransportRegNo+" disabled>"
							+"</div></div>"
							+"<div class='col-sm-1'>7</div><div class='col-sm-3'>"
							+"<label>Vehicle Registration No:</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.TransportVehicleRegNo+" disabled>"
							+"</div></div>"
							+"<div class='col-sm-1'>8</div><div class='col-sm-3'>"
							+"<label>Receiver's  Name & Mailing Address (including Phone No. and e-mail):</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='row'>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.ReceiversName+" disabled>"
							+"</div></div>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.ReceiversAdress+" disabled>"
							+"</div></div>"
							+"<div class='col-4'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.ReceiversPhoneNo+" disabled>"
							+"</div></div>"
							+"</div></div>"
							+"<div class='col-sm-1'>9</div><div class='col-sm-3'>"
							+"<label>Receiver's  authentication No:</label>"
							+"</div>"
							+"<div class='col-sm-8'><div class='form-group'>"
							+"<input type='text' class='form-control' value="+element.ReceiversAuthNo+" disabled>"
							+"</div></div>"
							+"<div class='col-sm-1'>10</div><div class='col-sm-3'>"
							+"<label>Waste Description:</label>"
							+"</div><div class='col-sm-8'>"

						for (const [key, value] of WasteDescription.entries()) {
							//var pollId = value.StackPollName;
								var html2 = "<div class='row'>" 
										+"<div class='form-group'>"
								        +"<input type='text' class='form-control' value="+value.wasteName+" disabled>"
										+"</div>"
										+"<div class='form-group'>"
								        +"<input type='text' class='form-control' value="+value.Quantity+" disabled>"
										+"</div>"
										+"<div class='form-group'>"
								        +"<input type='text' class='form-control' value="+value.unit+" disabled>"
										+"</div>"
										+"<div class='form-group'>"
								        +"<input type='text' class='form-control' value="+value.consistency+" disabled>"
										+"</div>"
										+"</div>"
									WasteDescriptionCount++;
			
							html1 += html2;
						}
				              html1 +=   "</div>"
					            	    +"<div class='col-sm-1'>11</div><div class='col-sm-3'>"
										+"<label>Total Quantity:</label>"
										+"</div>"
										+"<div class='col-sm-8'><div class='row'>" 
										+"<div class='col-6'><div class='form-group'>"
										+"<input type='text' class='form-control' value="+element.TotalQuantity+" disabled>"
										+"</div></div>"
										+"<div class='col-6'><div class='form-group'>"
										+"<input type='text' class='form-control' value="+element.TotalQuantityUnit+" disabled>"
										+"</div></div>"
										+"</div></div>"
										+"<div class='col-sm-1'></div><div class='col-sm-3'>"
										+"<label>Containers Number:</label>"
										+"</div><div class='col-sm-8'>"
						for (const [key, value] of Container.entries()) {
								var html2 = "<div class='row'>" 
									+"<div class='form-group'>"
							        +"<input type='text' class='form-control' value="+value.Container+" disabled>"
									+"</div>"
									+"<div class='form-group'>"
							        +"<input type='text' class='form-control' value="+value.Type+" disabled>"
									+"</div>"
									+"</div>"
									ContainerCount++;
		
							html1 += html2;
						}
				              html1 +=  "</div>"
				            	  		+"<div class='col-sm-1'>12</div><div class='col-sm-3'>"
										+"<label>Special Handling lnstructions and Additional lnformation:</label>"
										+"</div>"
										+"<div class='col-sm-8'><div class='form-group'>"
										+"<input type='text' class='form-control' value="+element.SpecialHandling+" disabled>"
										+"</div></div>"
										+"<div class='col-sm-1'>13</div><div class='col-sm-3'>"
										+"<label>Sender's Certificate:</label>"
										+"</div>"
										+"<div class='col-sm-8'><div class='form-group'>"
										+"<label>I hereby declare tliat the contents of the consignment are fully and accurately described above by proper shipping name and are categorized, packed, marked and labeled, and are in all respects jn proper condition for transport by road according to applicable national government regulations:</label>"
										+"</div></div>"
										+"<div class='col-sm-1'></div><div class='col-sm-3'>"
										+"<label>Name & Stamp:</label>"
										+"</div>"
										+"<div class='col-sm-8'><div class='form-group'>"
										+"<input type='text' class='form-control' value="+element.SubDate+" disabled>"
										+"</div></div>"
										+"<div class='col-sm-1'>14</div><div class='col-sm-3'>"
										+"<label>Transporter Acknowledgment of receipt of wastes:</label>"
										+"</div>"
										+"<div class='col-sm-8'><div class='form-group'>"
										+"<input type='text' class='form-control' value="+element.TransDescWeast+" disabled >"
										+"</div></div>"
										+"<div class='col-sm-1'></div><div class='col-sm-3'>"
										+"<label>Name & Stamp:</label>"
										+"</div>"
										+"<div class='col-sm-8'><div class='form-group'>"
										+"<input type='text' class='form-control' >"
										+"</div></div>"
										+"<div class='col-sm-1'>15</div><div class='col-sm-11'>"
										+"<label>Receiver's Certification for Receipt bf Hazardous and other Waste:</label>"
										+"</div>"
										+"<div class='col-sm-1'></div><div class='col-sm-11'>"
										+"<label>Maharashtra Enviro Power Ltd. Stamp : Signature</label>"
										+"</div>"

			var html3 = "</div>";
			$("#HazData").append(html1 + html3);
		    });
        }
    });
		
	
}
