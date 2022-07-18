<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.notifications .btn {
	width: 100%;
	margin-bottom: 20px;
}

.toggler {
	width: 500px;
	height: 200px;
}

#button {
	padding: .5em 1em;
	text-decoration: none;
}

#effect {
	width: 240px;
	height: 15px;
	padding: 0.4em;
	position: relative;
}

#effect h3 {
	margin: 0;
	padding: 0.4em;
	text-align: center;
}
</style>
<meta charset="utf-8">
<link rel="icon" href="img/faviconkyc.png" type="image/gif"
	sizes="12x12">
<title>Create User</title>
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="css/demos.css">
<script src="jquery/tests/jquery-1.9.1.js"></script>
<script src="js/graphs/common.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
<script>
    $(document).on('keypress', '#emp_name', function (event) {
	    var regex = new RegExp("^[a-zA-Z ]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	        event.preventDefault();
	        return false;
	    } 
	   
	});
         function validateForm(){
	    	 var c1 = document.getElementById("contPerDesig");
	    	 var s1 = c1.options[c1.selectedIndex].value;
	    	 if(s1=="")
	    		 {
	    			$("#desigerror").html("<label class=text-red>Select Designation!</label>");
	    		 return false;
	    		 }
	    	 else
	    		 {
	    		 return true;
	    		 }
	    	 }
	    
    
 
    /* $("#comp_cre").validate({
        messages: {
         contPerDesig: {
          chosen: "Please select atleast one from dropdown list",
         },
        }
    }); */
    	
    
    function checkEmail(checkemail){
	//debugger;
	var c_email = "";
	var new_password = "";
	var rnew_password = "";
	if(checkemail == "checkEmail"){
		c_email = $("#email").val();
		if(c_email==""){
			$("#error_old_email").html("");
		}
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(c_email.match(mailformat))
		{
		$(document).ready(function() {
			$.ajax({
				type : "POST",
				url : "ajax-check-email",
				data: ({
					c_email:c_email,
					
	            }),
				success : function(data) {
					//alert(data);
					if(data ==1){
						$("#error_old_email").html("<label class=text-green>Email is valid!</label>");
						
						$("#submitDataBtn").attr("disabled", false);
					}else{
						$("#error_old_email").html("<label class=text-red>Entered Email Already Exist!</label>");
						$("#submitDataBtn").attr("disabled", true);
						 $('#email').val('');
					}
				}
			});
		});
		}else if(c_email==""){
			$("#error_old_email").html("");
		} else{
			
			$("#error_old_email").html("<label class=text-red>Email is invalid!</label>");
			
		}
	}
}
    
   
    function checkUserName(userName)
    {
    	var c_userName="";
    	if(userName=="checkusername")
    	{
    		c_userName=$("#user_name").val();
    		$(document).ready(function() {
    			var dontBlock = true;
    			$.ajax({
    				type : "POST",
    				url : "ajax-check-user-name",
    				data: ({
    					c_userName:c_userName,
    					
    	            }),
    				success : function(data) {
    					dontBlock = false;
    					if(data==1){
    						$("#error_user_name").html("<label class=text-green>UserName is available!</label>");
    						
    						$("#submitDataBtn").attr("disabled", false);
    					}else{
    						$("#error_user_name").html("<label class=text-red>Entered UserName Already Registered!</label>");
    						$("#submitDataBtn").attr("disabled", true);
    						 $('#user_name').val('');
    					}
    				}
    			});
    		});
    	}
    }
    </script>
</head>

<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
<jsp:include page="../CommonWebpages/MenusWebpages/AdminMenus.jsp" />


<section id="main"> <section id="content">
<div class="container">
	<div class="card">
		<div class="card-header_emp">
			<div class="title_form_emp">
				<b><font color="black"
					style="font-family: sans-serif; font-size: 15px;"><b>CREATE
							USER</b></font></b>
			</div>

		</div>

		<div class="card-body card-padding">

			<p>
				<strong>Note:</strong>Admin is the one who will "Create Company
				Profile", He will create minimum three users :<br />
			<h6 style="margin-bottom: 18px;">
				<a href="#" class="other-tooltip" data-toggle="tooltip"
					data-placement="right" data-html="true"
					title="Management is the one who will Review / Supervise the work. He will be able to view outputs of Hazardous waste Return, Waster Cess , ESR, statistics, performance and graph.">1.<u>
						Management</u></a>
			</h6>
			<h6 style="margin-bottom: 18px;">
				<a href="#" class="other-tooltip" data-toggle="tooltip"
					data-placement="right" data-html="true"
					title="Environmental Officer is the one who will fill the daily input data and will be able to see if any non-compliance is created by Industry . He will fill the Consent to Establish/Operate, Hazardous waste and daily input data.">2.<u>
						Environmental Officer</u></a>
			</h6>
			<h6 style="margin-bottom: 8px;">
				<a href="#" class="other-tooltip" data-toggle="tooltip"
					data-placement="right" data-html="true"
					title="Third party will be the one who will upload the Monitoring details of Ambient Air and Water.">3.<u>
						Third Party</u></a>
			</h6>

			<form name="comp_cre" id="comp_cre" method="post" role="form"
				onsubmit="return validateForm()" action="save-user">
				<div class="form-group">
					<div class="fg-line">
						<br /> <label class="text-cyan">Employee Name</label><label
							class="complusoryColor">*</label>
						<div class="dtp-container fg-line">
							<input type='text' class="form-control" id="emp_name"
								placeholder="&nbsp;&nbsp;Employee Name" name="employeeName"
								value="" onkeyup="checkEmployeeName('emp_name')"
								onkeypress="return checkNum()" maxlength="70" required>
							<div class="col-sm-4" id="error_emp_name"
								onkeypress="return checkNum()"></div>
						</div>
					</div>
				</div>
				<label class="text-cyan">Address</label><label
					class="complusoryColor">*</label>
				<div class="row">
					<div class="col-sm-4">
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" id="address"
									name="address" placeholder="Plot No." maxlength="30" required>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" required id="address2"
									name="address2" placeholder="Street" maxlength="100">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" required id="address3"
									name="address3" placeholder="City"
									onkeypress="return checkNum()" maxlength="50">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-4">
						<label class="text-cyan">Designation</label><label
							class="complusoryColor">*</label>
						<div class="form-group">
							<div class="fg-line">
								<select class='chosen' name="contPerDesig" id="contPerDesig">
									<option value="">Select Designation</option>
									<option value="Management">Management</option>
									<option value="Environmental Officer">Environmental
										Officer</option>
									<option value="Third Party">Third Party</option>
								</select>
							</div>
							<div id="desigerror"></div>
						</div>
					</div>
					<div class="col-sm-4">
						<label class="text-cyan">Contact</label><label
							class="complusoryColor">*</label>
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" required
									id="cont_per_no" maxlength="10" minlength="10"
									placeholder="&nbsp;&nbsp;Contact Person No"
									onkeypress="numberValidate(event)" required name="contPerNo">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<label class="text-cyan">Email</label><label
							class="complusoryColor">*</label>
						<div class="form-group">
							<div class="fg-line">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="&nbsp;&nbsp;Email"
									onkeyup="checkEmail('checkEmail')" required>
								<div class="col-sm-4" id="error_old_email"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<label class="text-cyan">Username</label><label
							class="complusoryColor">*</label>
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control"
									placeholder="&nbsp;&nbsp;User Name" name="userName"
									id="user_name" onchange="checkUserName('checkusername')"
									required>
								<div class="col-sm-4" id="error_user_name"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<label class="text-cyan">Password</label><label
							class="complusoryColor">*</label>
						<div class="form-group">
							<div class="fg-line">
								<input type="password" class="form-control"
									placeholder="&nbsp;&nbsp;Password" name="password"
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
									title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
									required>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="fg-line">
						<center>
							<button type="reset" class="btn btn-default waves-effect">Cancel</button>
							<button type="submit" class="btn btn-info" id="submitDataBtn"
								style="background-color: #a9c52f;" disabled>Submit</button>
						</center>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</section> </section>
<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />
<body>

</body>
</html>