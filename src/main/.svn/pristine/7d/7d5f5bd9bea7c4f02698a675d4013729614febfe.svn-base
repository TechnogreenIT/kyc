function generateLabel(plantType){
	var labelName = "";
	$.ajax({
		type: 'post',
		url: "ajax-generateTretmentLabel",
		data : {
			plantType : plantType,
		},
		success: function (data) {
			labelName = data.trim();
		},
		async: false
	});
	return labelName;
}
function addWasteWaterTreatment(){
	var flag = 0;var html = "";
	var selectTreatmentTypeVal =($('select[name="treatmentType"] option:selected').val()).toUpperCase();
	
	var treatedUseArray = ['Gardening','Car Washing','Flushing'];
	
	flag = customSelectValidator(selectTreatmentTypeVal, "treatmentType");
	var recId = "appendDataRecyled";
	var disposeId = "appendDataDispose",disposeidData = "dispos";
	
	if(flag == 0){
		var res = "";
		selectTreatmentTypeVal == "ETP" ? res ='Effluent' : res ="Sewage";
		var labelName = generateLabel(selectTreatmentTypeVal);
		$("#appendTreatmentCard").empty();
		
		html = '<div class="card">'
					+'<div class="card-header"><h5>Add '+selectTreatmentTypeVal+'</h5></div>'
			
					+'<div class="card-body"><div class="row">'
					
						+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
							+'<div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Type :</strong></div>'
						+'</div>'
						+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
							+'<div class="form-group mb-0 mt-2"> <strong id="tretmentType">'+selectTreatmentTypeVal+'</strong></div>'
						+'</div>'
						
						+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
							+'<div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Label :</strong></div>'
						+'</div>'
						+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
							+'<div class="form-group"><input type="text" class="form-control" id="labelname" value="'+labelName+'" disabled></div>'
						+'</div>'
						
						+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
							+'<div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Capacity :</strong></div>'
						+'</div>'
						+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
							+'<div class="form-group"><input type="number" class="form-control" id="Quantity" placeholder="In CMD"> <i class="form-group__bar"></i></div>'
						+'</div>'

					    
					    +'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
					     	+'<div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Enargy Meter :</strong></div>'
					    +'</div>'
					    
					    +'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'
							+'<div class="form-group mb-1">'
								+'<div class="radio radio--inline cursor-pointer"> <input type="radio" name="energyUseMeter" id="meterY" value="Yes"> <label class="radio__label" for="meterY">Yes</label> </div> <div class="radio radio--inline cursor-pointer"> <input type="radio" name="energyUseMeter" id="meterN" value="No" checked> <label class="radio__label" for="meterN">No</label> </div>'
							+'</div>'
					    +'</div>'
					    
						+'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10 offset-1">'
							+'<div class="row mt-5">'
								+'<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"><strong class="font-weight-bolder">End Use of Treated '+res+'</strong></div>'
								
								+'<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">'
		
									
									+'<div class="row mt-2" >'
										+'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3 offset-1">'
											+'<div class="checkbox mt-2">'
												+'<input type="checkbox" id="Recycled" name="recycled" onclick="checkTreatmentRecyled(this,\'Recycled\');"> <label class="checkbox__label" for="Recycled">recycled:</label>'
											+'</div>'
										+'</div>'
									+'</div>'
									+'<div class="row mt-2">'
									     +'<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12" id="RecyledUse">'
									     +'</div>'
							       +'</div>'
									+'<div class="row mt-2" >'
										+'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3 offset-1">'
											+'<div class="checkbox mt-2">'
												+'<input type="checkbox" id="Dispos" name="disposal" onclick="checkTreatmentdispose(this,\'Dispos\');"> <label class="checkbox__label" for="Dispos">Dispos:</label>'
											+'</div>'
										+'</div>'
									+'</div>'
									+'<div class="row mt-2">'
								     +'<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12" id="DisposeUse">'
								     +'</div>'
						          +'</div>'
								+'</div>'
							+'</div>'
						+'</div>'
					+'</div>'
					+'</div></div>'
				+'</div>';
	}
	$("#appendTreatmentCard").append(html);
}

function checkTreatmentRecyled(el,useType){
	var checkbox = document.getElementById(useType);
	$("#RecyledUse").empty();
	var html="";
	 if (checkbox.checked == true) {
		 var treatedUseArray = ['Gardening','Car Washing','Flushing'];
		 var filterdata = usedFilterUseNames();
		 var wasteWaterUse = treatedUseArray.concat(filterdata);
		 for (var i = 0; i < wasteWaterUse.length; i++) {
				var waterUse = wasteWaterUse[i].replace(/ /g,"_");
				html+='<div class="row mt-2" >'
						+'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3 offset-2">'
							+'<div class="checkbox mt-2">'
								+'<input type="checkbox" id="waterUse'+waterUse+'" name="waterUserecyle[]" value="'+waterUse+'" onclick="checkUseQuatity(this,\'waterQuantity_'+waterUse+'\',\'recyled\');"> <label class="checkbox__label" for="waterUse'+waterUse+'">'+waterUse+' :</label>'
							+'</div>'
						+'</div>'
						+'<div id="waterQuantity_'+waterUse+'" class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7"></div>'
					+'</div>';
			}
				html+= '<div class="row mt-2" id="recyledBTN">'
			      +'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10 offset-2">'
			         +'<button class="btn btn-primary btn--icon size-2x" title="Add More" onclick="addMoreUse(\'recyledBTN\')"><i class="zmdi zmdi-plus"></i></button><br>'
			    +'</div>'
			+'</div>'
	 }
	 
	 $("#RecyledUse").append(html);
}
function usedFilterUseNames(){
		var usedFilterName = "";
		$.ajax({
			type: 'post',
			url: "ajax-getUsedFilterUseNameList",
			success: function (data) {
				usedFilterName = data;
			},
			async: false
		});
		return usedFilterName;
}
function checkTreatmentdispose(el,useType){
	var checkbox = document.getElementById(useType);
	 $("#DisposeUse").empty();
	var html="";
	 if (checkbox.checked == true) {
		 var treatedUseArray = ['CEPT','Nala'];
		 for (var i = 0; i < treatedUseArray.length; i++) {
				var waterUse = treatedUseArray[i];
				
				html+='<div class="row mt-2" >'
						+'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3 offset-2">'
							+'<div class="checkbox mt-2">'
								+'<input type="checkbox" id="waterUse'+waterUse+'" onclick="checkUseQuatity(this,\'waterQuantity_'+waterUse+'\',\'disposal\');" name="waterUseDisposal[]" value="'+waterUse+'"> <label class="checkbox__label" for="waterUse'+waterUse+'">'+waterUse+' :</label>'
							+'</div>'
						+'</div>'
						+'<div id="waterQuantity_'+waterUse+'" class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7"></div>'
					+'</div>';
			}
				html+= '<div class="row mt-2" id="disposeBTN">'
			      +'<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10 offset-2">'
			         +'<button class="btn btn-primary btn--icon size-2x" title="Add More" onclick="addMoreUse(\'disposeBTN\')"><i class="zmdi zmdi-plus"></i></button><br>'
			    +'</div>'
			+'</div>'
	 }
	 
	 $("#DisposeUse").append(html);
}
function checkUseQuatity(el,id,name){
	$("#"+id).empty();
	if ($(el).prop("checked") == true) {
		html = '<div class="row">'
		   +'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'
		     +'<input type="number" class="form-control"  name="'+name+'Qauntity[]"   placeholder="In CMD"><i class="form-group__bar"></i>'
		   +'</div>'
		   +'<div class="col-9 col-sm-9 col-md-9 col-lg-9 col-xl-9">'
		    +'<div class="row">'
		    	+'<div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">'
		    	 +'<div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Meter Installed:</strong></div>'
		    	+'</div>'
		    	+'<div class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7">'
		    	  +'<div class="radio radio--inline cursor-pointer"> <input type="radio" name="'+id+'meter" id="'+id+'meterY" value="true"> <label class="radio__label" for="'+id+'meterY">Yes</label> </div> <div class="radio radio--inline cursor-pointer"> <input type="radio" name="'+id+'meter" id="'+id+'meterN" value="false" checked> <label class="radio__label" for="'+id+'meterN">No</label> </div>'
		    	+'</div>'
		    +'</div>'
	    +'</div>'
         +'</div>';
		$("#"+id).append(html);
	}
}
function addMoreUse(appendToId){
	var modelTitle ="Add other use name";
	var bodyForm = "<div class='container'>" 
			            +"<div class='row'>" 
							+"<div class='col-6'>"
								+"<div class='form-group mb-0'>" 
									+"<input type='text' id='ExtraUseName' placeholder='Enter Use Name' class='form-control'>"
									+"<div class='invalid-feedback'>Invalid!</div><i class='form-group__bar'></i>"
								+"</div>" 
							+"</div>"
						
							+"<div class='col-6'>"
								+"<div class='form-group mb-0'>" 
									+"<input type='Number' id='ExtraUsequantity' placeholder='Enter Quantitity In CMD' class='form-control'>"
									+"<div class='invalid-feedback'>Invalid!</div><i class='form-group__bar'></i>"
								+"</div>" 
						    +"</div>"
		                +"</div>" 
		                +"<div class='row'>" 
						+"<div class='col-6'>"
							  +'<div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Meter Installed:</strong></div>'
						+"</div>"
					
						+"<div class='col-6'>"
								+"<div class='radio radio--inline cursor-pointer'>"
									+"<input type='radio' name='extmeter' id='extmeterY' value='Yes'>"
									+"<label class='radio__label' for='extmeterY'>Yes</label>"
								+"</div>"
								+"<div class='radio radio--inline cursor-pointer'>"
								   +"<input type='radio' name='extmeter' id='extmeterN' value='No' checked>"
								   +"<label class='radio__label' for='extmeterN'>No</label>"
								+"</div>" 
					    +"</div>"
	                +"</div>"
	                
	                
	                
	                +"</div>";
	$.showModal({
		title: modelTitle,
		modalDialogClass: 'modal-md',
		body: bodyForm,
		footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='addOtherUse(this,\""+appendToId+"\")' class='btn btn-primary'>Add</button>",
		onCreate: function (modal) {
			// create event handler for form submit and handle values
		}
	})
}
function addOtherUse(el,appendToId){
var extraName = $("#ExtraUseName").val();
	var extraQuantity = $('#ExtraUsequantity').val();
	var modalId = $(el).closest('.modal').attr('id');
	var thisMeter= $("input[name='extmeter']:checked").val();
	var metervalue = "false";
	if(thisMeter == "Yes")
		{
		   metervalue = "true";
		}
	$('#' + modalId).modal('toggle');
	if(appendToId == "recyledBTN"){
		var name = "waterUserecyle";
		var quantity="recyled";
	}else{
		var name = "waterUseDisposal";
		var quantity="disposal";
	}
	
	
	var html ='<div class="row mt-2" id="extraUseListEle"><div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3 offset-2">'
					+'<div class="checkbox mt-2">'
						+'<input type="checkbox" id="waterUse'+extraName+'" name="'+name+'[]" value="'+extraName+'" checked disabled> <label class="checkbox__label" for="waterUse'+extraName+'">'+extraName+' :</label>'
					+'</div>'
				+'</div>'
				+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
					+'<div class="form-group mb-0">'
						+'<input type="number" class="form-control" name="'+quantity+'Qauntity[]" value="'+extraQuantity+'" disabled>'
					+'</div>'
				+'</div>'
				+'<div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'
				+'<div class="form-group mb-0">'
				 +'<div class="row">'
				 	+'<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">'
				 	   +'<strong class="font-weight-bold">Meter :</strong>'
				 	+'</div>'
				 	+'<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">'

					 	+'<div class="radio radio--inline cursor-pointer">'
						   +'<input type="radio" name="waterQuantity_'+extraName+'meter" id="waterQuantity_'+extraName+'meterN" value="'+metervalue+'" checked disabled>'
						   +'<label class="radio__label" for="waterQuantity_'+extraName+'meterN">'+thisMeter+'</label>'
						+"</div>" 	
				 	
				 	+'</div>'
				 +'</div>'
				+'</div>'
				+'</div>'
				+'<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'
					+'<div class="form-group mb-0">'
						+"<a class='actions__item zmdi text-danger zmdi-delete' data-toggle='tooltip' data-placement='top' title='delete use' onclick='deleteThisUse(this,\" " + extraName + "\");'></a>"
					+'</div>'
			+'</div></div>'
	$( html ).insertBefore( "#"+appendToId );
	 makeToolTip2();
}
function deleteThisUse(el,useName){
    var msg = "Are you sure, you want to delete this "+useName+" !";
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
			el.closest('div#extraUseListEle').remove();
		}
	});
}
function saveTreatment(){
	var prevDataList=[];var flag=0;var myObj={};
	var recylecedArray=[];
	var tretmentType = $("#tretmentType").text();
	var labelName = $("#labelname").val();
	var quantity = $("#Quantity").val();
    flag+= customInputValidator(quantity, "#Quantity");
    
    myObj["tretmentType"] = tretmentType;
    myObj["labelName"] = labelName;
    myObj["quantity"] = quantity;
    
    var energyMeter=$("input[name=energyUseMeter]:checked").val();
    var checkbox = document.getElementById("Recycled");
    if (checkbox.checked == true) {
    	  var checkedBoxes = $("input[name='waterUserecyle[]']:checked");//waterUseQauntity
    	  for (var a = 0; a < checkedBoxes.length; a++) {
    	        var checkBox = checkedBoxes[a].value;
    	        var inps = $("input[name='recyledQauntity[]']")[a];
    	        var thisMeter= $("input[name='waterQuantity_"+checkBox+"meter']:checked").val();
    	        var recyledQuntity = inps.value;
    	        if (recyledQuntity == "") {
    	            flag--;
    	            inps.classList.add("is-invalid");    	            
    	            return;
    	        } else {
    	            inps.classList.remove("is-invalid");
    	            recylecedArray.push({ "Name": checkBox,"Quantity": recyledQuntity,"type": "recyled","meter": thisMeter})
    	        }
    	  }
    }
    var checkbox1 = document.getElementById("Dispos");
    if (checkbox1.checked == true) {
    	  var checkedBoxes = $("input[name='waterUseDisposal[]']:checked");//waterUseQauntity
    	  for (var a = 0; a < checkedBoxes.length; a++) {
    	        var checkBox = checkedBoxes[a].value;
    	        var inps = $("input[name='disposalQauntity[]']")[a];
    	        var thisMeter= $("input[name='waterQuantity_"+checkBox+"meter']:checked").val();
    	        var disposalQuntity = inps.value;
    	        if (disposalQuntity == "") {
    	            flag--;
    	            inps.classList.add("is-invalid");
    	            return;
    	        } else {
    	            inps.classList.remove("is-invalid");
    	            recylecedArray.push({ "Name": checkBox,"Quantity": disposalQuntity,"type": "disposal","meter": thisMeter})
    	        }
    	  }
    }
    myObj["RecycledArrayList"] = recylecedArray;
    if (flag == 0) {
		$.ajax({
			type : "POST",
			url : "ajax-water-wasteWater-c2o",
			contentType : "application/json",
			data : JSON.stringify(myObj),
			success : function(data) {
				//appendTreatmentCard
				 jBoxBottomRightBigNotice("Success", "Waste Water Saved !!", "green", "2000");
				 moreWasteWatserForm();

			}
		});
	}
}
function moreWasteWatserForm(){
	
		swal({
			  text: "Do you want to add more Waste Water Tretment Plant?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  confirmButtonText: "Yes",
			  cancelButtonText: "No",
			  allowOutsideClick: false
			})
			.then((res) => {
			  if (res.value == true) {
				  $('#appendTreatmentCard').empty();
				  
			  } else if (res.dismiss == "cancel") {
				  jBoxBottomRightBigNotice("Success", "You will be redirected to Home", "green", "2000");
				  setTimeout(function(){
					  window.location="envrOfficerDesk";
					}, 3000);
			  }
			});
}