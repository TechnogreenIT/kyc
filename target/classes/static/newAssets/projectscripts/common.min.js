$(document).ready(function(){
	//alert($(window).width());
	if ($(window).width() < 700){
		   $("#add_con").hide();
		   $("#add1_con").hide();
		  // alert("mobile");
		   $("#pollut").hide();
		   $("#air").hide();
    }
    else {
        //alert("not mobile");
    	 $("#pollut1").hide();
    	 $("#air1").hide();
    }
});
var keyStr = "ABCDEFGHIJKLMNOP" +
               "QRSTUVWXYZabcdef" +
               "ghijklmnopqrstuv" +
               "wxyz0123456789+/" +
               "=";

  function encode64(input) {
     input = escape(input);
     var output = "";
     var chr1, chr2, chr3 = "";
     var enc1, enc2, enc3, enc4 = "";
     var i = 0;

     do {
        chr1 = input.charCodeAt(i++);
        chr2 = input.charCodeAt(i++);
        chr3 = input.charCodeAt(i++);

        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;

        if (isNaN(chr2)) {
           enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
           enc4 = 64;
        }

        output = output +
           keyStr.charAt(enc1) +
           keyStr.charAt(enc2) +
           keyStr.charAt(enc3) +
           keyStr.charAt(enc4);
        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = "";
     } while (i < input.length);

     return output;
  }

  function decode64(input) {
     var output = "";
     var chr1, chr2, chr3 = "";
     var enc1, enc2, enc3, enc4 = "";
     var i = 0;

     // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
     var base64test = /[^A-Za-z0-9\+\/\=]/g;
     if (base64test.exec(input)) {
        alert("There were invalid base64 characters in the input text.\n" +
              "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
              "Expect errors in decoding.");
     }
     input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

     do {
        enc1 = keyStr.indexOf(input.charAt(i++));
        enc2 = keyStr.indexOf(input.charAt(i++));
        enc3 = keyStr.indexOf(input.charAt(i++));
        enc4 = keyStr.indexOf(input.charAt(i++));

        chr1 = (enc1 << 2) | (enc2 >> 4);
        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
        chr3 = ((enc3 & 3) << 6) | enc4;

        output = output + String.fromCharCode(chr1);

        if (enc3 != 64) {
           output = output + String.fromCharCode(chr2);
        }
        if (enc4 != 64) {
           output = output + String.fromCharCode(chr3);
        }

        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = "";

     } while (i < input.length);

     return unescape(output);
  }
function getAllUrlParams(url) {

  // get query string from url (optional) or window
  var queryString = url ? url.split('?')[1] : window.location.search.slice(1);

  // we'll store the parameters here
  var obj = {};

  // if query string exists
  if (queryString) {

    // stuff after # is not part of query string, so get rid of it
    queryString = queryString.split('#')[0];

    // split our query string into its component parts
    var arr = queryString.split('&');

    for (var i=0; i<arr.length; i++) {
      // separate the keys and the values
      var a = arr[i].split('=');

      // in case params look like: list[]=thing1&list[]=thing2
      var paramNum = undefined;
      var paramName = a[0].replace(/\[\d*\]/, function(v) {
        paramNum = v.slice(1,-1);
        return '';
      });

      // set parameter value (use 'true' if empty)
      var paramValue = typeof(a[1])==='undefined' ? true : a[1];

      // (optional) keep case consistent
      paramName = paramName;
      paramValue = paramValue;

      // if parameter name already exists
      if (obj[paramName]) {
        // convert value to array (if still string)
        if (typeof obj[paramName] === 'string') {
          obj[paramName] = [obj[paramName]];
        }
        // if no array index number specified...
        if (typeof paramNum === 'undefined') {
          // put the value on the end of the array
          obj[paramName].push(paramValue);
        }
        // if array index number specified...
        else {
          // put the value at that index number
          obj[paramName][paramNum] = paramValue;
        }
      }
      // if param name doesn't exist yet, set it
      else {
        obj[paramName] = paramValue;
      }
    }
  }

  return obj;
}
var sub_roLists = new Array(13);
sub_roLists["empty"] = ["Select Sub Regional Office"]; 
sub_roLists["Amravati"] = ["Select Sub Regional Office","Amravati I", "Amravati II", "Akola"]; 
sub_roLists["Aurangabad"] = ["Select Sub Regional Office","Aurangabad I", "Jalna", "Latur", "Nanded" ,"Parbhani"]; 
sub_roLists["Kalyan"] = ["Select Sub Regional Office","Kalyan I", "Kalyan II", "Kalyan III", "Bhiwandi"]; 
sub_roLists["Kolhapur"]= ["Select Sub Regional Office","Kolhapur", "Sangli", "Ratnagiri", "Chiplun"]; 
sub_roLists["Mumbai"]= ["Select Sub Regional Office","Mumbai I", "Mumbai II", "Mumbai III", "Mumbai IV"]; 
sub_roLists["Nagpur"]= ["Select Sub Regional Office","Nagpur I", "Nagpur II", "Bhandara"]; 
sub_roLists["Nashik"]= ["Select Sub Regional Office","Nashik", "Jalgaon", "Dhule", "Ahmednagar"]; 
sub_roLists["Navi Mumbai"]= ["Select Sub Regional Office","Navi Mumbai-I", "Navi Mumbai II", "Taloja"]; 
sub_roLists["Pune"]= ["Select Sub Regional Office","Pune I", "Pune II", "Pimpri-Chichwad", "Satara", "Solapur"]; 
sub_roLists["Raigad"]= ["Select Sub Regional Office","Raigad I", "Raigad II", "Mahad"]; 
sub_roLists["Thane"]= ["Select Sub Regional Office","Thane I", "Thane II", "Tarapur", "Tarapur II"]; 
sub_roLists["Chandrapur"]= ["Select Sub Regional Office","Chandrapur I"]; 

function sub_roChange(selectObj) { 
	var idx = selectObj.selectedIndex; // get the index of the selected option
	var which = selectObj.options[idx].value; // get the value of the selected option 
	cList = sub_roLists[which];  // use the selected option value to retrieve the list of items from the sub_roLists array 
	var cSelect = document.getElementById("sub_ro"); // get the sub_ro select element via its known id 
	var len=cSelect.options.length; // remove the current options from the sub_ro select 
		
	while (cSelect.options.length > 0) {
		cSelect.remove(0); 
	} 
				
	var newOption; 
	for (var i=0; i<cList.length; i++)  { // create new options
		newOption = document.createElement("option"); 
		newOption.value = cList[i];  // assumes option string and value are the same 
		newOption.text=cList[i]; 
		try { // add the new option 
			cSelect.add(newOption);  // this will fail in DOM browsers but is needed for IE 
		} catch (e) { 
			cSelect.appendChild(newOption); 
		} 
	} 
} 

function getFormattedDate(today){
	x = today.getMonth()+1;
	y = today.getDate();
	if(x < 10){
		x = "0"+x;
	}
	if(y < 10){
		y = "0"+y;
	}
	var date = today.getFullYear()+'-'+x+'-'+y;
	return date;
}
var specialKeys = new Array();
specialKeys.push(8);
function numberValidate(e, divId) {
	var keyCode = e.which ? e.which : e.keyCode;
	var ret = (((keyCode >= 48 && keyCode <= 57) || keyCode == 46 || keyCode == 60 
				|| keyCode == 62 || keyCode == 66 || keyCode == 68 || keyCode == 76) || specialKeys.indexOf(keyCode) != -1);
	var isnumber = ret ? "true " : "false";
	if (isnumber.indexOf("true") > -1) {
		$("#"+divId).html('');
		return true;
	} else {
		e.preventDefault();
		$("#"+divId).html('<label class="text-red">No characters allowed, except "."</label>');
		return false;
	}
}
//jayshree
function checkNum()
{
//debugger;
if ((event.keyCode > 64 && event.keyCode < 91) || (event.keyCode > 96 && event.keyCode < 123) || event.keyCode == 8 || event.keyCode == 32)
return true;
else
{
   //alert("Please enter only char");
   return false;
}

}
function numberValidate(e) {
	var keyCode = e.which ? e.which : e.keyCode;
	var ret = ((keyCode >= 46 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);

	var isnumber = ret ? "true " : "false";
	if (isnumber.indexOf("true") > -1) {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
}
function noZero(input) {
	if($(input).val() == '0'){
	      $(input).val('');
}
}
	
function getArray(inps){
	var main = new Array();
	for (var i = 0; i <inps.length; i++) {
		var inp=inps[i];
		if(inp.value!=""){
			main.push(inp.value);
		}
	}
	return main
}


