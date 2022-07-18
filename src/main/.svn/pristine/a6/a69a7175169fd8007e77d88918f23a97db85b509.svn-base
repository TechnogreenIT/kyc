var fCount=1;
var count =1;
var sCount =0;
var filterOptions ="";
var  filterUseOptions ="";
//flter add code in water inventory

$(document).ready(function () {
	waterSourceView();
	filterView();
});

function addFiltersBlock(){
	  $("#main-container").empty();
	  filterOptions=getWaterFiltersOptions();
	  var flag = 0;
	  var mainHtml = '<div class="row">'+
	        ' <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 offset-2">'+
	        '   <div class="form-group">'+
	        '     <select class="select2" id="inventoryFilter" name="inventoryFilter">'+
	        '       <option value="">Select filter type</option>'+
	               filterOptions +
	        '     </select>'+
	        '     <div class="invalid-feedback">Please select any !</div>'+
	        '   </div>'+
	        ' </div>'+
	        ' <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
	        '   <button class="btn btn-primary btn--icon size-2x" id="btn" title="Add" onclick="addFilterBtn(this);"><i class="zmdi zmdi-plus"></i></button>'+
	        ' </div>'+
	        
	        ' <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12" id="addfilterform">'+
	        '</div>'+
	        '</div>'
	        ;
	 
	  flag += customSelectValidator($("#connectedTo").val(), "connectedTo");
	  if(flag == 0){
	    $("#main-container").append(mainHtml);
	    makeSelect2();
	  }
	  
}



function addFilterBtn(el){
	  var inventoryFilterType = $("#inventoryFilter").val();
	  customSelectValidator(inventoryFilterType, "inventoryFilter");
	  if(inventoryFilterType !=""){
		  filterModal(inventoryFilterType);
	  }
	}


function filterModal(filterType){
	
	 var lblAutoSuggestedFilter = filterType+fCount;
 
 var modelTitle ="Add "+filterType+" filter ";
 
 var bodyForm =  '<div class="container" >'+
         '<div class="row mb-3">'+
 
 		  '        <div class="col-6 mt-2">'+
 		  '            <div class="form-group mb-0">'+
 		  '                <input type="text" id="filterType" name="filterType" class="form-control" value="'+filterType+'" disabled>'+
 		  '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
 	      '            </div>'+
 		  '        </div>'+
 		  '    </div>'+
         '    <div class="row mb-3">'+
         '        <div class="col-6 mt-2">'+
         
         '            <div class="form-group mb-0">'+
         '                <input type="text" id="filterLable" name="filterLable" placeholder="Filter label" value="'+lblAutoSuggestedFilter+'"  class="form-control">'+
         '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
         '            </div>'+
         '        </div>'+
         '        <div class="col-6 mt-2">'+
         '            <div class="form-group mb-0">'+
         '                <input type="Number" id="filterWaterLoss"  name="filterWaterLoss"  placeholder="Water Loss in Percent (%)" class="form-control" >'+
         '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
         '            </div>'+
         '        </div>'+
         '    </div>'+
         

         
         '    <div class="row">'+
         '        <div class="col-6">'+
         '            <div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Meter Installed ? :</strong></div>'+
         '        </div>'+
         ''+
         '        <div class="col-6">'+
         '            <div class="radio radio--inline cursor-pointer">'+
         '                <input type="radio" name="filterWaterMeter" id="extmeterY" value="Yes">'+
         '                <label class="radio__label" for="extmeterY">Yes</label>'+
         '            </div>'+
         '            <div class="radio radio--inline cursor-pointer">'+
         '                <input type="radio" name="filterWaterMeter" id="extmeterN" value="No" checked>'+
         '                <label class="radio__label" for="extmeterN">No</label>'+
         '            </div>'+
         '        </div>'+
         '   <hr>'+
         '    </div>'+
         '</div>';
 

 $.showModal({
   title: modelTitle,
   modalDialogClass: 'modal-md',
   body: bodyForm,
   footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='filterValueModal(this);' class='btn btn-primary'>Add</button>",
   onCreate: function (modal) {
     // create event handler for form submit and handle values
   }
 })
 makeSelect2();
}

function  filterValueModal(el){
	var flag=0;
	 var filterType = $("#filterType").val();
	  var filterLable = $("#filterLable").val();
	  var filterWaterLoss = $("#filterWaterLoss").val();
	  var filterWaterMeter = $('input[name=filterWaterMeter]:checked').val();

	  flag += customSelectValidator(filterType, "filterType");
	  flag += customInputValidator(filterLable, "#filterLable");
	  flag += customInputValidator(filterWaterLoss, "#filterWaterLoss"); 
	  
	  
	  if (flag == 0) {
		    var modalId = $(el).closest('.modal').attr('id');
		    dUseCount++;
		    $('#' + modalId).modal('toggle');
		    makeFilterModal(filterType,filterLable,filterWaterLoss,filterWaterMeter);
		  makeSelect2();
		  }
	
}




function makeFilterModal(filterType,filterLable,filterWaterLoss,filterWaterMeter){

	var extraDiv="<div id='extraDiv"+count+"'><div>";
	$("#addfilterform").append(extraDiv);
	 $("#btn").attr("disabled", true);
	 $("#inventoryFilter").attr("disabled", true);

  var modelTitle ="Add "+filterType+" filter ";
  
  var bodyForm =  '<div class="container" >'+
          '<div class="row mb-3">'+
  
  		  '        <div class="col-6 mt-2">'+
  		  '            <div class="form-group mb-0">'+
  	     '               <label class="font-weight-700 ">Filter Type :    </label>'+
  		  '                <label class="font-weight-bold"  id="filterType'+count+'">'+filterType+'</label>'+
  		  '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
  	      '            </div>'+
  		  '        </div>'+
  		  '    </div>'+
          '    <div class="row mb-3">'+
          '        <div class="col-6 mt-2">'+
          
          '            <div class="form-group mb-0">'+
          '               <label class="font-weight-700 ">Filter Lable :    </label>'+
  		  '                <label class="font-weight-bold"  id="filterLable'+count+'">'+filterLable+'</label>'+
          '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
          '            </div>'+
          '        </div>'+
          '        <div class="col-6 mt-2">'+
          '            <div class="form-group mb-0">'+
          '               <label class="font-weight-700 ">Filter Water Loss (in %) :    </label>'+
          '               <label class="font-weight-bold"  id="filterWaterLoss'+count+'">'+  filterWaterLoss+'</label>'+
          '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
          '            </div>'+
          '        </div>'+
          '    </div>'+
          
          '    <div class="row">'+
          '        <div class="col-6">'+
          '            <div class="form-group mb-0 mt-2"> <strong class="font-weight-700">Meter Installed ? :</strong></div>'+
          '        </div>'+
          '        <div class="col-6">'+
          '               <label class="font-weight-bold" id="filterWaterMeter'+count+'">'+filterWaterMeter+'</label>'+
          '            </div>'+
          '        </div>'+
          '   <hr>'+
          '    </div>'+
          

	        ' <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'+
	        '                <label class="font-weight-bold">Where To Use</label>'+
	        '   <button class="btn btn-primary btn--icon size-2x" title="Add" onclick="industrialUseModal(\''+filterType+'\',\''+count+'\');"><i class="zmdi zmdi-plus"></i></button>'+
	        ' </div>'+
	        
	        '<div class="row mb-3">'+
			  ' <div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10 offset-1" style="border:1px solid black;">'+
			  '   <table class="table table-hover normal">'+
			  '     <thead>'+
			  '       <tr>'+
			  '         <!-- <th>#</th> -->'+
			  '         <th>Filter Use Type</th>'+
			  '         <th>Filter Label</th>'+
			  '         <th>Loss (in %)</th>'+
			  '         <th>Meter</th>'+
			  '         <th>Delete</th>'+
			  '       </tr>'+
			  '     </thead>'+ 
			  '       <tbody>  '+
			  '      <tbody id="rowData'+count+'">'+ 
			  '     </tbody>'+
			  '   </table>'+
			  '</div></div>'+

	      
	       '<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 text-center">'+
	        ' <button type="button" class="btn btn-primary btn-sm waves-effect waves-light" onclick="saveAllFilterUse(this,\''+count+'\');"> <i class="zmdi zmdi-save"></i> Save </button>'+
           '</div></div>    </div>';
  
  sCount++;
  $("#extraDiv"+count).append(bodyForm);
  makeSelect2();
  count++;
}




function industrialUseModal(FilterType,count){

	 
	 var modelTitle ="Add "+FilterType+" filter ";
	 filterUseOptions=getWaterFilterUseNameOptions();
	
	 
	 var bodyForm = '<div class="row" style="border:1px solid black;">'+
	 ' <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 offset-2">'+
	 '   <div class="form-group">'+
	 '     <select class="select2" id="filterUseType" name="filterUseType"  placeholder="Select industrial use" >'+
	 ' <option value=""> Select filter use</option>'+
     filterOptions+
	    filterUseOptions+
	 '     </select>'+
	 '     <div class="invalid-feedback">Please select any !</div>'+
	 '   </div>'+
	 '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
	 '            </div>'+
	 


		  '        <div class="col-6 mt-2">'+
	 
		  '            <div class="form-group mb-0">'+
		  '                <input type="text" id="filterUselabel" placeholder="Filter_Use_Label"  class="form-control">'+
		  '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
		  '            </div>'+
		  '        </div>'+
		  '        <div class="col-6 mt-2">'+
		  '            <div class="form-group mb-0">'+
		  '                <input type="Number" id="filterUsewaterLoss"    placeholder="Water Loss in Percent (%)" class="form-control" >'+
		  '                <div class="invalid-feedback">Invalid!</div><i class="form-group__bar"></i>'+
		  '            </div>'+
		  '        </div>'+
		

	 
		
		  '        <div class="col-6">'+
		  '            <div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Meter Installed ? :</strong></div>'+
		  '        </div>'+
		  ''+
		  '        <div class="col-6">'+
		  '            <div class="radio radio--inline cursor-pointer">'+
		  '                <input type="radio" name="filterUsewaterUseMeter" id="uMeterY" value="Yes">'+
		  '                <label class="radio__label" for="uMeterY">Yes</label>'+
		  '            </div>'+
		  '            <div class="radio radio--inline cursor-pointer">'+
		  '                <input type="radio" name="filterUsewaterUseMeter" id="uMmeterN" value="No" checked>'+
		  '                <label class="radio__label" for="uMmeterN">No</label>'+
		  '            </div>'+
		  '        </div>'+
		  '   <hr>'+

		  ' </div>'+
	          '</div>';


	 $.showModal({
	   title: modelTitle,
	   modalDialogClass: 'modal-md',
	   body: bodyForm,
	   footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='industrialUseTable(this,\""+count+"\");' class='btn btn-primary'>Add</button>",
	   onCreate: function (modal) {
	     // create event handler for form submit and handle values
	   }
	 })
	 makeSelect2();
	}



	function  industrialUseTable(el,count){
		var flag=0;
		 var filterUseType = $("#filterUseType").val();
		  var filterUselabel = $("#filterUselabel").val();
		  var filterUsewaterLoss = $("#filterUsewaterLoss").val();
		  var filterUsewaterUseMeter = $('input[name=filterUsewaterUseMeter]:checked').val();

		  flag += customSelectValidator(filterUseType, "filterUseType");
		  flag += customInputValidator(filterUselabel, "#filterUselabel");
		  flag += customInputValidator(filterUsewaterLoss, "#filterUsewaterLoss"); 
		  
		  
		  if (flag == 0) {
			    var modalId = $(el).closest('.modal').attr('id');
			    dUseCount++;
			    $('#' + modalId).modal('toggle');
			    addIndustrialUseTableRaw(filterUseType,filterUselabel,filterUsewaterLoss,filterUsewaterUseMeter,count);
			  makeSelect2();
			  }
		
	}


	function addIndustrialUseTableRaw(filterUseType,filterUselabel,filterUsewaterLoss,filterUsewaterUseMeter,count){
		
		
		  var trHtml = $(	  ' <tr>'+
		          '<td name="filterUseType[]"">'+filterUseType+'</td>'+
		          '<td name="filterUselabel[]">'+filterUselabel+'</td>'+
		          '<td name="filterUsewaterLoss[]">'+filterUsewaterLoss+'</td>'+
		          '<td name="filterUsewaterUseMeter[]">'+filterUsewaterUseMeter+'</td>'+
		          '<td><button type="button" class="btn btn-sm btn-danger pt-1 pb-1" onclick="removeUse(this,\''+filterUselabel+'\');"><i class="zmdi zmdi-delete">DELETE</i></button></td>'+
		          ' </tr>')

		  $("#rowData"+count).append(trHtml);
		}

	function removeUse(el,industrialUselabel){
	    var msg = "Are you sure, you want to delete this "+industrialUselabel+" !";
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
	              $(el).parent('td').parent('tr').remove();
	            }
	        });
	}


	function saveAllFilterUse(el,count){
		
		 var Obj = {};
		 var arrFilterData =new Array();
		 
		 
		 var flag = 0;
		  
		  var connectedTo = $("#connectedTo").val();
		  flag += customSelectValidator(connectedTo, "connectedTo");
		  
		  if(flag == 0){
			                  
			               //   $("#btn").attr("disabled", false);
			             //     $("#inventoryFilter").attr("disabled", false);
			  sCount--;
			    Obj.preFilterId = connectedTo;
			    
			  
			    var  filterType =$("#filterType"+count).text()
			    var  filterLable = $("#filterLable"+count).text()
			    var  filterWaterLoss = $("#filterWaterLoss"+count).text()
			    var  filterWaterMeter =$("#filterWaterMeter"+count).text()== 'Yes' ? 'true' : 'false';              
			    
			    var fData={};
			    fData["filterType"]=filterType;
			    fData["filterLable"]=filterLable;
			    fData["filterWaterLoss"]=filterWaterLoss;
			    fData["filterWaterMeter"]=filterWaterMeter ;
			   
			    var filterUseType = document.getElementsByName("filterUseType[]");
			    var filterUselabel = document.getElementsByName("filterUselabel[]");
			    var filterUsewaterLoss = document.getElementsByName("filterUsewaterLoss[]");
			    var filterUsewaterUseMeter = document.getElementsByName("filterUsewaterUseMeter[]");
			    
			    var uData=new Array();
			    for (var i = 0; i < filterUseType.length; i++) {
			        var item = {};
			        item["filterUseType"] = filterUseType[i].innerHTML;
			        item["filterUselabel"] = filterUselabel[i].innerHTML;
			        item["filterUsewaterLoss"] = filterUsewaterLoss[i].innerHTML;
			        item["filterUsewaterUseMeter"] = filterUsewaterUseMeter[i].innerHTML == 'Yes' ? 'true' : 'false';
			     
			        uData.push(item);
			        
			       
			    }
			    fData["filterUseData"]=uData;
			    arrFilterData.push(fData);
			    //arrFilterData["filterUse"]=uData;
			    Obj.allFilterData = arrFilterData;
			   // console.log(Obj);
			    $.ajax({
			      type : "POST",
			      url : "ajax-save-inventory-filter-use",
			      contentType : "application/json",
			      data : JSON.stringify(Obj),
			      success : function(data) {
			    	  var data = JSON.parse(data);
			    	  $("#extraDiv"+count).empty();
			    	  $.each(data, function (index, element) {
			    		  var result=element.Result;
			    		  if(result){
			    		
				    		  var filterType=element.filterType;
				    		  var filterLable=element.filterUselabel;
				    		  var filterWaterLoss=element.filterUsewaterLoss;
				    		  var filterWaterMeter=element.filterUsewaterUseMeter === true ? 'Yes' : 'No';
				    		  
				    		  makeFilterModal(filterType,filterLable,filterWaterLoss,filterWaterMeter);
			    		  }
			    		  else{
			    			  if(sCount==0)
			    			      addFiltersBlock();
			    			
			    		  }
			    		  	  
			    	  });
			      }
			    });
			    
		  }
		  
		
	
	}

	function waterSourceView(){
		$.ajax({
		      type : "POST",
		      url : "view-waterSource",
		      success : function(data) {
		    	  var data1 = JSON.parse(data);
		    	  
		 
		    		
		    		 var waterSourceAccordion =
				 		"<div class='card-body'>"+
				 		"<table class='table table-bordered'> <thead><tr><th style='text-align: center; vertical-align: middle;' class='font-weight-700' colspan='2'>Water Source </th><th style='text-align: center; vertical-align: middle;'  class='font-weight-700' colspan='4'>PreFilter</th></tr> <tr> <th class='font-weight-700'>Water source Name</th> <th class='font-weight-700'>Source Meter</th><th class='font-weight-700'>ACF</th> <th class='font-weight-700'>PSF</th><th class='font-weight-700'>DMF</th> <th class='font-weight-700'>Meter</th></tr>  </thead> " +
						"<tbody></div>";
		    		 
		    		 var directUseAccordion =
					 		"<div class='card-body'>"+
					 		"<table class='table table-bordered'> <thead><tr> <th class='font-weight-700'>Type Of Use</th> <th class='font-weight-700'>Where To Use</th><th class='font-weight-700'>Water Loss</th> <th class='font-weight-700'>isMeter</th></tr>  </thead> " +
							"<tbody></div>";
		    	
		    	
		    			$.each(data1, function (index, element) {
		    			
		    					
								 var waterSourceArray=element.waterSource;
								 var directUseArray=element.directUse;
								 for (const [key, value] of waterSourceArray.entries()) {
									 
									 var sourceName=value.sourceName;
									 var isSourceMeter=value.isSourceMeter == true ? 'Yes' : 'No';
									 
									 var acf=value.acf == true ? "Yes" : "No";
									 var psf=value.psf == true ? 'Yes' : 'No';
									 var dmf=value.dmf == true ? 'Yes' : 'No';
									 var isMeter=value.isMeter == true ? 'Yes' : 'No';
										
									 waterSourceAccordion +=  "<tr> <td>"+ sourceName +"</td> <td>"+ isSourceMeter +"</td><td>"+ acf +"</td> <td>"+ psf +"</td><td>"+ dmf +"</td> <td>"+ isMeter +"</td>  </tr>";
									
								 }
								
								 $("#dataWaterSource").append(waterSourceAccordion);
								 
								 
                                for (const [key, value] of directUseArray.entries()) {
									 
									 var typeOfUse=value.typeOfUse;
									 var whereToUse=value.whereToUse;
									 var waterLoss=value.waterLoss;
									 var isMeter=value.isMeter == true ? 'Yes' : 'No';
										
									 directUseAccordion +=  "<tr> <td>"+ typeOfUse +"</td> <td>"+ whereToUse +"</td><td>"+ waterLoss +"</td> <td>"+ isMeter +"</td></tr>";
									
								 }
								
								 $("#dataDirectUse").append(directUseAccordion);
		    				
						    });
		      }
		    });
		
		
	}

	
	function filterView(){
		$.ajax({
		      type : "POST",
		      url : "view-filterData",
		      success : function(data) {
		    	  var data1 = JSON.parse(data);
		    	  
		    	  var filterType;
		    	  $.each(data1, function (index, element) {
		    			
  					
		    			 filterType=element.filterType;
						 var multipleFilter=element.multipleFilter;
						 
						 var filterAccordian="<h2>"+filterType+"</h2><div id='mulAccordian"+filterType+"'></div>";
						 
						 $("#dataFilter").append(filterAccordian);
						 
						 for (const [key, value] of multipleFilter.entries()) {
							 var filterLabel=value.filterLabel;
							 var filterUse=value.filterUse;
							 var filterWaterLoss=value.filterWaterLoss;
							 var filterIsMeter=value.filterIsMeter  == true ? 'Yes' : 'No';
							 
								 var multipleFilterAccordian="<h2>"+filterLabel+"</h2><div id='useData"+filterLabel+"'></div>";
								 $("#mulAccordian"+filterType).append(multipleFilterAccordian);
								 	
								 var filterUseAccordion =
								 		"<div class='card-body'>"
								 		
								 		+"<div class='row mb-3'>"
								 		+" <div class='col-6 mt-2'>"
								 		+" <div class='form-group mb-0'>"
								 		+"   <label class='font-weight-700' >Filter Lable :  </label>"
								 		+"  <label class='font-weight-bold' style='margin-left:15px'>"+filterLabel+"</label>"
								 		+"</div></div>"
								 		+" <div class='col-6 mt-2'>"
								 		+" <div class='form-group mb-0'>"
								 		+"   <label class='font-weight-700' >Filter Water Loss (in %) : </label>"
								 		+"  <label class='font-weight-bold'  style='margin-left:15px'>"+filterWaterLoss+"</label>"
								 		+"</div></div></div>"
								 		
								 		+"<div class='row mb-3'>"
								 		+" <div class='col-6'>"
								 		+"<div class='form-group mb-0 mt-2'> <strong class='font-weight-700'>Meter Installed ? :</strong></div></div>"
								 		+" <div class='col-6'>"
								 		+" <label class='font-weight-bold' >"+filterIsMeter+"</label>"
								 		+"</div></div></div>"
								 		
								 		+"<table class='table table-bordered'> <thead><tr><th class='font-weight-700'>filter Use Label</th> <th class='font-weight-700'>water Loss (in %)</th><th class='font-weight-700'>Meter Installed </th></tr>  </thead> " 
										+"<tbody></div></div>";
								 
								 for (const [key, value] of filterUse.entries()) {
									 var filterUseLabel=value.filterUseLabel;
									 var waterLoss=value.waterLoss;
									 var isMeter=value.isMeter  == true ? 'Yes' : 'No';
									 
									 
								
									 filterUseAccordion +=  "<tr> <td>"+ filterUseLabel +"</td> <td>"+ waterLoss +"</td><td>"+ isMeter +"</td></tr>";
									 
								 }
								 $("#useData"+filterLabel).append(filterUseAccordion);
						 }
						 $("#mulAccordian"+filterType).accordion({heightStyle : 'content',collapsible : true});
						
						 });
		    	  
		    	  $("#dataFilter").accordion({heightStyle : 'content',collapsible : true});
		    	  
		    	  
		    	  
		    	  
		    
	    }
	
		});
		
	}