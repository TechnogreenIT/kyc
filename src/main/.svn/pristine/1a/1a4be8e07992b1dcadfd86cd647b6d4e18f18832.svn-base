var counter = 0;

$("#preFilterY").click(function() {
  var prefilterDiv = "<div class='row mt-6 mt-4'>"+
              "<div class='col-2 col-sm-2 col-md-2 col-lg-2 col-xl-4'>"+
                "<div class='checkbox'>"+
                  "<input type='checkbox' name='ACF' value='ACF' id='prefilterACF'>"+
                  "<label class='checkbox__label' for='prefilterACF'>ACF</label>"+
                "</div>"+
              "</div>"+
              "<div class='col-2 col-sm-2 col-md-2 col-lg-2 col-xl-4'>"+
                "<div class='checkbox'>"+
                  "<input type='checkbox' name='PSF' value='PSF' id='prefilterPSF' >"+
                  "<label class='checkbox__label' for='prefilterPSF'>PSF</label>"+
                "</div>"+
              "</div>"+
              "<div class='col-2 col-sm-2 col-md-2 col-lg-2 col-xl-4'>"+
                "<div class='checkbox'>"+
                  "<input type='checkbox' name='DMF' value='DMF' id='prefilterDMF' >"+
                  "<label class='checkbox__label' for='prefilterDMF'>DMF</label>"+
                "</div>"+
              "</div>"+
            "</div>"+
            "<div class='row mt-2'>"+
              "<div class='col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10' id='isPreFltrRequired'></div>"+
            "</div>";

  $("#prefilterDiv").empty();
  $("#prefilterDiv").append(prefilterDiv);
});

$("#preFilterN").click(function() {
  $("#prefilterDiv").empty();
});

$('#filterType').change(function() {
  $("#fltrAddMore").show();
  $("#useFltrLbl").empty();
  $("#useFltrInfo").empty();
  $("#useFltrAddMore").empty();
  $('#useFltrType').val("");
  $('#useFltrDiv').empty();
  $("#fltrUseInfo").empty();
  
  var fltrUseInfo = '<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-4">'+
        ' <div class="checkbox">'+
        '   <input type="checkbox" name="directUseWater[]" value="Filter" id="useWaterSourceFltr" onclick="getUseWaterSourceFilter(this);">'+
        '   <label class="checkbox__label" for="useWaterSourceFltr">Filter</label>'+
        ' </div>'+
        '</div>'+
        '<!-- Filter use of filter Dropbox -->'+
        '<div id="useFltrTypeDiv" class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6"></div>'+
        ''+
        '<!-- Filter label END-->'+
        '<div class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11">'+
        ' <div class="row" id="useFltrLbl"></div>'+
        '</div>'+
        ''+
        '<!-- Filter label ie. RO1,RO2..... -->'+
        '<div class="row">'+
        ' <div  id="useFltrInfo" class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"></div>'+
        '</div>'+
        ''+
        '<!-- Button for add more filter  -->'+
        '<div class="row mt-4 mb-4" id="useFltrAddMore"></div>'+
        ''+
        '<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceFltr"> </div>';
    
  $("#fltrUseInfo").append(fltrUseInfo);
  
  var selectedFltr = $("select[name=filterType]").val();
  fltrAndFltrUseDetails(selectedFltr,"Fltr");
  $("#fltrWhereToUseSpan").text("Select Where to use "+selectedFltr+" Filtered Water");
});

function fltrAndFltrUseDetails(selectedFltr,fltrOrFltrUse){
  var fltrOrFltrUseVar;
  if(fltrOrFltrUse == "Fltr"){
    fltrOrFltrUseVar = "fltr";
  }else if(fltrOrFltrUse == "UseOFFltr"){
    fltrOrFltrUseVar = "useFltr";
  }
  
  //var selectedFltrLbl = $("select[name=filterType]").val();
  $("#"+fltrOrFltrUseVar+"Lbl").empty();
  
  var filterLabelHtml =   '<div class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11 offset-lg-1">'+
              ' <div class="form-group">'+
              '   <h6 class="font-weight-700 mt-1 mb-1">Filter Label:</h6>'+
              ' </div>'+
              '</div>';
  $("#"+fltrOrFltrUseVar+"Lbl").append(filterLabelHtml);  
  
  $("#"+fltrOrFltrUseVar+"Info").empty();
  counter = 1;
  addAnoFltr(selectedFltr,fltrOrFltrUseVar);
  
  $("#"+fltrOrFltrUseVar+"AddMore").empty();
  var addMoreFilterBtn =  '<div class="col text-center">'+
              ' <button type="button" class="btn btn-sm light-green darken-3 text-white pt-1 pb-1 on" onclick="addAnoFltr(\''+selectedFltr+'\',\''+fltrOrFltrUseVar+'\')">'+
              '   <i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add Another '+selectedFltr+''+
              ' </button>'+
              '</div>';
  $("#"+fltrOrFltrUseVar+"AddMore").append(addMoreFilterBtn); 
}

function addAnoFltr(selectedFltr,fltrOrFltrUseVar){
  //var selectedFltr = $("select[name=filterType]").val();
  var selectedFltrLbl = selectedFltr + counter;
  
  if(counter!=1){
    var delFltrInfoBtn = selectedFltr + (counter-1);//$("select[name=filterType]").val()+ (counter-1);
    $("#deleteBtnFltrInfo"+delFltrInfoBtn).hide();
  }
  var filterInfo ='<div class="row" id="newAddedFilter">'+
          ' <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 offset-lg-1">'+
          '   <div class="form-group">'+
          '     <input type="text" class="form-control col-3" name='+selectedFltrLbl+' placeholder='+selectedFltrLbl+' disabled>'+
          '   </div>'+
          ' </div>'+
          ' <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-1 offset-lg-4" id="deleteBtnFltrInfo'+selectedFltrLbl+'">'+
          '   <div class="form-group">'+
          '     <a class="actions__item zmdi text-danger zmdi-delete" data-toggle="tooltip" data-placement="top" title="delete use" onclick="deleteThisUse(this,\''+selectedFltr+'\');"></a>'+
          '   </div>'+
          ' </div>'+
          ''+
          ' <div class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7 offset-lg-2">'+
          '   <div class="form-group">'+
          '     <label>1. Do You Have Water Meter for '+selectedFltrLbl+' Filter?</label>'+
          '     <i class="form-group__bar"></i>'+
          '   </div>'+
          ' </div>'+
          ' <div class="col-3">'+
          '   <div class="form-group">'+
          '     <div class="radio radio--inline cursor-pointer">'+
          '       <input type="radio" name="'+selectedFltrLbl+'watremeter" id="'+selectedFltrLbl+'watremeterY" value="Yes"> <label class="radio__label" for="'+selectedFltrLbl+'watremeterY">Yes</label>'+
          '     </div>'+
          '     <div class="radio radio--inline cursor-pointer">'+
          '       <input type="radio" name="'+selectedFltrLbl+'watremeter" id="'+selectedFltrLbl+'watremeterN" value="No" checked> <label class="radio__label" for="'+selectedFltrLbl+'watremeterN">No</label>'+
          '     </div>'+
          '     <i class="form-group__bar"></i>'+
          '   </div>'+
          ' </div>'+
          ''+
          ' <div class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7 offset-lg-2">'+
          '   <div class="form-group">'+
          '     <label>2. Do you want to use default loss calculation for output of '+selectedFltrLbl+' filter?</label> <i class="form-group__bar"></i>'+
          '   </div>'+
          ' </div>'+
          ' <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'+
          '   <div class="form-group">'+
          '     <div class="radio radio--inline cursor-pointer">'+
          '       <input type="radio" name="'+selectedFltrLbl+'defultLoss" id="'+selectedFltrLbl+'defultLossY" value="Yes"> <label class="radio__label" for="'+selectedFltrLbl+'defultLossY">Yes</label>'+
          '     </div>'+
          '     <div class="radio radio--inline cursor-pointer">'+
          '       <input type="radio" name="'+selectedFltrLbl+'defultLoss" id="'+selectedFltrLbl+'defultLossN" value="No" checked> <label class="radio__label" for="'+selectedFltrLbl+'defultLossN">No</label>'+
          '     </div>'+
          '     <i class="form-group__bar"></i>'+
          '   </div>'+
          ' </div>'+
          ''+
          ' <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6  offset-lg-3">'+
          '   <div class="form-group">'+
          '     <label>Water Loss in Percent (%) for '+selectedFltrLbl+' Filter :</label>'+
          '     <i class="form-group__bar"></i>'+
          '   </div>'+
          ' </div>'+
          ' <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'+
          '   <div class="form-group">'+
          '     <input type="text" class="form-control" name="'+selectedFltrLbl+'wtloss" placeholder="Enter Loss">'+
          '     <div class=\'invalid-feedback\'>Invalid !</div>'+
          '     <i class="form-group__bar"></i>'+
          '   </div>'+
          ' </div>'+
          '</div>';
  $("#"+fltrOrFltrUseVar+"Info").append(filterInfo);
  counter++;
}


var cntDomestic = 1;
function addAnoDomesticUseLbl(useTypeDiv,useFltr){
  
  cntDomestic++;
  var myvar = '<div class="row mt-2">'+
        ' <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
        '   <div class="checkbox">'+
        '     <input type="checkbox" name="directUseWater[]" value="Domestic'+cntDomestic+'" id="useWaterSourceDomestic'+cntDomestic+'" onclick="getUseWaterSource(this,\'Domestic\',\'\','+cntDomestic+');">'+
        '     <label class="checkbox__label" for="useWaterSourceDomestic'+cntDomestic+'">Domestic '+cntDomestic+'</label>'+
        '   </div>'+
        ' </div>'+
        ' <div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceDomestic'+cntDomestic+'"> </div>'+
        '</div>';
  
  $("#"+useTypeDiv).append(myvar);
}

var cntLaundry = 1;
function addAnoLaundryUseLbl(useTypeDiv,useFltr){
  
  cntLaundry++;
  var myvar = '<div class="row mt-2">'+
        ' <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
        '   <div class="checkbox">'+
        '     <input type="checkbox" name="directUseWater[]" value="Laundry'+cntLaundry+'" id="useWaterSourceLaundry'+cntLaundry+'" onclick="getUseWaterSource(this,\'Laundry\',\'\','+cntLaundry+');">'+
        '     <label class="checkbox__label" for="useWaterSourceLaundry'+cntLaundry+'">Laundry '+cntLaundry+'</label>'+
        '   </div>'+
        ' </div>'+
        ' <div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceLaundry'+cntLaundry+'"> </div>'+
        '</div>';
  
  $("#"+useTypeDiv).append(myvar);
}

var cntFireHydrant = 1;
function addAnoFireHydrantUseLbl(useTypeDiv,useFltr){
  
  cntFireHydrant++;
  var myvar = '<div class="row mt-2">'+
        ' <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">'+
        '   <div class="checkbox">'+
        '     <input type="checkbox" name="directUseWater[]" value="FireHydrant'+cntFireHydrant+'" id="useWaterSourceFireHydrant'+cntFireHydrant+'" onclick="getUseWaterSource(this,\'FireHydrant\',\'\','+cntFireHydrant+');">'+
        '     <label class="checkbox__label" for="useWaterSourceFireHydrant'+cntFireHydrant+'">FireHydrant '+cntFireHydrant+'</label>'+
        '   </div>'+
        ' </div>'+
        ' <div class="col-9 col-sm-9 col-md-9 col-lg-9 col-xl-9" id="appendUseWaterSourceFireHydrant'+cntFireHydrant+'"> </div>'+
        '</div>';
  
  $("#"+useTypeDiv).append(myvar);
}


function getUseWaterSourceFilter(el){
  $("#useFltrTypeDiv").empty();
  var fltrUseOffltr = '<h6 class="font-weight-700 mt-1 mb-1">Filters Type :</h6>'+
            '<div class="form-group">'+
            ' <select class="select2" id="useFltrType" name="useFltrType" onchange="useFltrOnChange()">'+
            '   <option value="">Select Connected To</option>'+
            '   <option value="Softner">Softner</option>'+// Take from ajax and super filter should not be repad
            '   <option value="RO">RO</option>'+
            '   <option value="ACF">ACF</option>'+
            '   <option value="UV">UV</option>'+
            ' </select>'+
            ' <div class="invalid-feedback">Please select any !</div>'+
            '</div>';
  $("#useFltrTypeDiv").append(fltrUseOffltr);
  makeSelect2();
}

function useFltrOnChange(){
  $("#useFltrDiv").empty();
  var useFltr =   '<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">'+
          ' <div class="form-group mb-2">'+
          '    <h6 class="font-weight-700 mt-1 mb-1"><p><span id="useFltrWhereToUseSpan"> </span></p></h6>'+
          ' </div>'+
          '</div>'+
          ''+
          '<div class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11 offset-1">'+
          ' <div class="row mt-2">'+
          '    <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
          '     <div class="checkbox">'+
          '      <input type="checkbox" name="useFltrDirectUseWater[]" value="Domestic" id="useWaterSourceDomesticUseFltr" onclick="getUseWaterSource(this,\'Domestic\',\'UseFltr\');">'+
          '      <label class="checkbox__label" for="useWaterSourceDomesticUseFltr">Domestic</label>'+
          '     </div>'+
          '    </div>'+
          '    <div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceDomesticUseFltr"> </div>'+
          ' </div>'+
          ' <div class="row mt-2">'+
          '    <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
          '     <div class="checkbox">'+
          '      <input type="checkbox" name="useFltrDirectUseWater[]" value="Industrial" id="useFltrUseWaterSourceIndustrial" onclick="getUseWaterSourceIndustrial(this,\'UseFltr\');">'+
          '      <label class="checkbox__label" for="useFltrUseWaterSourceIndustrial">Industrial</label>'+
          '     </div>'+
          '    </div>'+
          '    <div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceIndustrialUseFltr"> </div>'+
          ' </div>'+
          ' <div class="row mt-2">'+
          '    <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
          '     <div class="checkbox">'+
          '      <input type="checkbox" name="useFltrDirectUseWater[]" value="Laundry" id="useWaterSourceLaundryUseFltr" onclick="getUseWaterSource(this,\'Laundry\',\'UseFltr\');">'+
          '      <label class="checkbox__label" for="useWaterSourceLaundryUseFltr">Laundry</label>'+
          '     </div>'+
          '    </div>'+
          '    <div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceLaundryUseFltr"> </div>'+
          ' </div>'+
          ' <div class="row mt-2">'+
          '    <div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">'+
          '     <div class="checkbox">'+
          '      <input type="checkbox" name="useFltrDirectUseWater[]" value="Fire Hydrant" id="useWaterSourceFireHydrantUseFltr" onclick="getUseWaterSource(this,\'Fire Hydrant\',\'UseFltr\');">'+
          '      <label class="checkbox__label" for="useWaterSourceFireHydrantUseFltr">Fire Hydrant</label>'+
          '     </div>'+
          '    </div>'+
          '    <div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceFireHydrantUseFltr"> </div>'+
          ' </div>'+
          '</div>';
    
  var selectedUseFltr = $("select[name=useFltrType]").val();
  $("#fltrAddMore").hide();
  fltrAndFltrUseDetails(selectedUseFltr,"UseOFFltr");
  $("#useFltrDiv").append(useFltr);
  $("#useFltrWhereToUseSpan").text("Select Where to use "+selectedUseFltr+" Filtered Water");
}

function deleteThisUse(el,fltrName){
    var msg = "Are you sure, you want to delete this "+fltrName + (counter-1)+" !";
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
                el.closest('div#newAddedFilter').remove();
                counter--;
                $("#deleteBtnFltrInfo"+fltrName+(counter-1)).show();
            }
        });
}