var sub_roLists = new Array(13);
sub_roLists["Amravati"] = ["Amravati I", "Amravati II", "Akola"]; 
sub_roLists["Aurangabad"] = ["Aurangabad I", "Jalna", "Latur", "Nanded" ,"Parbhani"]; 
sub_roLists["Kalyan"] = ["Kalyan I", "Kalyan II", "Kalyan III", "Bhiwandi"]; 
sub_roLists["Kolhapur"]= ["Kolhapur", "Sangli", "Ratnagiri", "Chiplun"]; 
sub_roLists["Mumbai"]= ["Mumbai I", "Mumbai II", "Mumbai III", "Mumbai IV"]; 
sub_roLists["Nagpur"]= ["Nagpur I", "Nagpur II", "Bhandara"]; 
sub_roLists["Nashik"]= ["Nashik", "Jalgaon", "Dhule", "Ahmednagar"]; 
sub_roLists["Navi Mumbai"]= ["Navi Mumbai-I", "Navi Mumbai II", "Taloja"]; 
sub_roLists["Pune"]= ["Pune I", "Pune II", "Pimpri-Chichwad", "Satara", "Solapur"]; 
sub_roLists["Raigad"]= ["Raigad I", "Raigad II", "Mahad"]; 
sub_roLists["Thane"]= ["Thane I", "Thane II", "Tarapur", "Tarapur II"]; 
sub_roLists["Chandrapur"]= ["Chandrapur I"]; 

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