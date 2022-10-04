var dUseCount =1 ;
function getPrefilterDetails(el){
  var filterId = $(el).val();
  $('div[id^="preFilterDiv_"]').hide();
  $("#preFilterDiv_"+filterId).show();
}

function addDirectUseBlock(){
  $("#main-container").empty();
  var flag = 0;
  var mainHtml = '<div class="row">'+
        ' <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 offset-2">'+
        '   <div class="form-group">'+
        '     <select class="select2" id=\'directUseType\' name="directUseType">'+
        '       <option value="">Select direct use type</option>'+
        '       <option value="Domestic">Domestic</option>'+
        '       <option value="Laundary">Laundary</option>'+
        '       <option value="Fire Hydrant">Fire Hydrant</option>'+
        '       <option value="Industrial">Industrial</option>'+
        '       <option value="Other">Other</option>'+
        '     </select>'+
        '     <div class="invalid-feedback">Please select any !</div>'+
        '   </div>'+
        ' </div>'+
        ' <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
        '   <button class="btn btn-primary btn--icon size-2x" title="Add" onclick="addDirectUseBtn(\'directUseType\');"><i class="zmdi zmdi-plus"></i></button>'+
        ' </div>'+
        ''+
        ' <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">'+
        '   <table class="table table-hover normal">'+
        '     <thead>'+
        '       <tr>'+
        '         <!-- <th>#</th> -->'+
        '         <th>Direct Use Type</th>'+
        '         <th>Label</th>'+
        '         <th>Loss (in %)</th>'+
        '         <th>Meter</th>'+
        '         <th>Industrial</th>'+
        '         <th>#</th>'+
        '       </tr>'+
        '     </thead>'+
        ''+
        '     <tbody id="used-directUse">'+
        '     '+
        '     </tbody>'+
        '   </table>'+
        ' </div>'+
        '<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 text-center">'+
        ' <button type="button" class="btn btn-primary btn-sm waves-effect waves-light" onclick="saveAllDirectUse(this);"> <i class="zmdi zmdi-save"></i> Save </button>'+
        '</div>'+
        '</div>';

  flag += customSelectValidator($("#connectedTo").val(), "connectedTo");
  if(flag == 0){
    $("#main-container").append(mainHtml);
    makeSelect2();
  }
  
}
function addDirectUseBtn(dUseId){
  var directUseType = $("#"+dUseId).val();
  
  customSelectValidator(directUseType, "directUseType");
  
  if(directUseType !=""){
    makeDirectUseModal(directUseType);
  }
}

function makeDirectUseModal(dUseType){
  
  var lblAutoSuggested = dUseType+dUseCount;
  var indUseHtml = "";
  if(dUseType == "Industrial"){
    indUseHtml += "<div class='col-12 mb-3'>"
              +"<select class='select2' data-placeholder='Select industrial use' name='directUseIndTypeIdModal' id='directUseIndTypeIdModal'>"
              +"<option value=''>Select industrial use</option>"
              +"<option value='Process'>Process</option>"
              +"<option value='Cooling'>Cooling</option>"
              +"<option value='Boiler'>Boiler</option>"
              +"<option value='Equipment Washing'>Equipment Washing</option>"
              +"<option value='Water Scrubber'>Water Scrubber</option>"
            +"</select>"
            +"<div class='invalid-feedback ml-4'>Please select any !</div>"
          +"</div>";
  }

  var modelTitle ="Add "+dUseType+" use ";
  var bodyForm = "<div class='container'>" 
                  +"<div class='row mb-3'>"
                  
                    +indUseHtml

              +"<div class='col-6 mt-2'>"
              +"<input type='hidden' id='directUseTypeIdModal' value='"+dUseType+"'>"
                +"<div class='form-group mb-0'>" 
                  +"<input type='text' id='directUseLblIdModal' value='"+lblAutoSuggested+"' placeholder='direct use label' class='form-control'>"
                  +"<div class='invalid-feedback'>Invalid!</div><i class='form-group__bar'></i>"
                +"</div>" 
              +"</div>"
            
              +"<div class='col-6 mt-2'>"
                +"<div class='form-group mb-0'>" 
                  +"<input type='Number' id='directUseLossModal' placeholder='Water Loss in Percent (%)' class='form-control'>"
                  +"<div class='invalid-feedback'>Invalid!</div><i class='form-group__bar'></i>"
                +"</div>" 
                +"</div>"
                    +"</div>" 
                    +"<div class='row'>" 
            +"<div class='col-6'>"
                +'<div class="form-group mb-0 mt-2"> <strong class="font-weight-bold">Meter Installed ?:</strong></div>'
            +"</div>"
          
            +"<div class='col-6'>"
                +"<div class='radio radio--inline cursor-pointer'>"
                  +"<input type='radio' name='directUseMeterModal' id='extmeterY' value='Yes'>"
                  +"<label class='radio__label' for='extmeterY'>Yes</label>"
                +"</div>"
                +"<div class='radio radio--inline cursor-pointer'>"
                   +"<input type='radio' name='directUseMeterModal' id='extmeterN' value='No' checked>"
                   +"<label class='radio__label' for='extmeterN'>No</label>"
                +"</div>" 
              +"</div>"
                  +"</div>"
                  
                  +"</div>";
  $.showModal({
    title: modelTitle,
    modalDialogClass: 'modal-md',
    body: bodyForm,
    footer: "<button type='button' class='btn btn-link' data-dismiss='modal'>Cancel</button><button type='submit' onclick='addDirectUse(this,\""+dUseCount+"\")' class='btn btn-primary'>Add</button>",
    onCreate: function (modal) {
      // create event handler for form submit and handle values
    }
  })
  makeSelect2();
 
}

function addDirectUse(el,dUseCount,directUseType){
   var flag = 0;
  var indUseType = "";
  var isIndustrial = "No";
  var directUseType = $("#directUseTypeIdModal").val();
  var directUselabel = $("#directUseLblIdModal").val();
  var directUseLoss = $("#directUseLossModal").val();
  var directUseMeter = $('input[name=directUseMeterModal]:checked').val();
  
  if(directUseType=="Industrial"){
    var indUseType = $("#directUseIndTypeIdModal").val();
    flag += customSelectValidator(indUseType, "directUseIndTypeIdModal");
    directUseType = indUseType; isIndustrial = "Yes"; // Override old values
  }
   flag += customInputValidator(directUselabel, "#directUseLblIdModal");
   flag += customInputValidator(directUseLoss, "#directUseLossModal");
  
  if (flag == 0) {
    var modalId = $(el).closest('.modal').attr('id');
    dUseCount++;
    $('#' + modalId).modal('toggle');
    addTableRaw(directUseType,directUselabel,directUseLoss,directUseMeter,isIndustrial,"used-directUse");
  }
  
}

function addTableRaw(directUseType,directUselabel,directUseLoss,directUseMeter,isIndustrial,appendId){
  
  var trHtml = $("<tr>"+
          "<td name='directUseTypes[]'>"+directUseType+"</td>"+
          "<td name='directUseLables[]'>"+directUselabel+"</td>"+
          "<td name='directUseLoss[]'>"+directUseLoss+"</td>"+
          "<td name='directUseMeter[]'>"+directUseMeter+"</td>"+
          "<td name='isIndustrial[]'>"+isIndustrial+"</td>"+
          "<td><button type='button' class='btn btn-sm btn-danger pt-1 pb-1' onclick='removeDirectUse(this,\""+directUselabel+"\");'><i class='zmdi zmdi-delete'></i></button></td>"+
        "</tr>")
  $("#"+appendId).append(trHtml);
}


function removeDirectUse(el,directUselabel){
    var msg = "Are you sure, you want to delete this "+directUselabel+" !";
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

function saveAllDirectUse(el){
  var Obj = {};
  var arrDirectUseData =new Array();
  
  var flag = 0;
  
  var connectedTo = $("#connectedTo").val();
  flag += customSelectValidator(connectedTo, "connectedTo");
  
  if(flag == 0){
    
    Obj.preFilterId = connectedTo;
    
    var directUseTypes = document.getElementsByName("directUseTypes[]");
    var directUseLables = document.getElementsByName("directUseLables[]");
    var directUseLoss = document.getElementsByName("directUseLoss[]");
    var directUseMeter = document.getElementsByName("directUseMeter[]");
    var directUseIsIndustrial = document.getElementsByName("isIndustrial[]");
    for (var i = 0; i < directUseTypes.length; i++) {
      var item = {}
      item["directUseType"] = directUseTypes[i].innerHTML;
      item["directUseLabel"] = directUseLables[i].innerHTML;
      item["directUseLoss"] = directUseLoss[i].innerHTML;
      item["directUseMeter"] = directUseMeter[i].innerHTML == 'Yes' ? 'true' : 'false';
      item["directUseIsIndustrial"] = directUseIsIndustrial[i].innerHTML == 'Yes' ? 'true' : 'false';
      
      arrDirectUseData.push(item);
    }
    
    Obj.arrDirectUseData = arrDirectUseData;
//    console.log(Obj);
    $.ajax({
      type : "POST",
      url : "ajax-save-inventory-direct-use",
      contentType : "application/json",
      data : JSON.stringify(Obj),
      success : function(data) {
        location.reload();
      }
    });
  }
  
}

