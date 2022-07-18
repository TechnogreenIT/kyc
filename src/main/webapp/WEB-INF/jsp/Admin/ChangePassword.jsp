<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<meta charset="utf-8">
<link rel="icon" href="img/faviconkyc.png" type="image/gif"
	sizes="12x12">
<title>Password Recovery</title>
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
<script src="jquery/tests/jquery-1.9.1.js"></script>
<script src="jquery/ui/jquery.ui.effect.js"></script>
<script src="jquery/ui/jquery.ui.effect-drop.js"></script>
<script src="jquery/ui/jquery.ui.effect.js"></script>
<link rel="stylesheet" href="css/demos.css">
</head>
<script>
$(function () {
	$("#successmsg").hide();
	$("#errorsmsg").hide();
});
function changePassword(action){
	//debugger;
	var old_password = "";
	var new_password = "";
	var rnew_password = "";
	if(action == "checkOldPassword"){
		old_password = $("#old_password").val();
		$(document).ready(function() {
			$.ajax({
				type : "POST",
				url : "ajax-check-oldpassword",
				data: ({
					password:old_password,
					type : "check"
	            }),
				success : function(data) {
					//alert(data);
					if(data =="checkOldPassword"){
						$("#error_old_password").html("<label class=text-green>Password match!</label>");
						$("#new_password").attr("disabled", false);
						$("#rnew_password").attr("disabled", false);
					}else{
						$("#error_old_password").html("<label class=text-red>Password didnt match!</label>");
					}
				}
			});
		});
	}else if(action == "checkRNewPassword"){
		new_password = $("#new_password").val();
		rnew_password = $("#rnew_password").val();
		if(rnew_password != new_password)
			$("#error_rnew_password").html("<label class=text-red>Password does not match!</label>");
		else
			//$("#error_rnew_password").html("");
		$("#error_rnew_password").html("<label class=text-green>Password match!</label>");
		$("#submitDataBtn").attr("disabled", false);
	}else if(action == "save"){
		//debugger;
		new_password = $("#new_password").val();
		$("#old_password").val("");
		$("#rnew_password").val("");
		$("#new_password").val("");
		$(document).ready(function() {
			$.ajax({
				type : "POST",
				url : "ajax-check-oldpassword",
				data: ({
					password:new_password,
					type : "save"
	            }),
				success : function(data) {
					if(data == 1){
						$("#errorsmsg").hide();
						$("#successmsg").show();
						window.location="logout";
					}else{
						$("#successmsg").hide();
						$("#errorsmsg").show();
					}
				}
			});
		});
	}
}
</script>
<div id="commentsModel" class="sweet-alert showSweetAlert visible"
	style="width: 550px;" tabindex="-1" data-has-cancel-button="false"
	data-has-confirm-button="true" data-allow-ouside-click="false"
	data-has-done-function="false" data-timer="null"
	style=" margin-top: -167px;">
	<h2 class="text-darkbrown">Comments</h2>
	<input type="hidden" class="form-control" name="task_id" id="task_id"
		value="0">
	<textarea class="form-control" rows="4" cols="50" id="comments_area"
		autofocus maxlength="1500"
		placeholder="Enter comments here(max 1500 words)..."></textarea>
	<p>
		<button class="cancel btn btn-lg btn-default" tabindex="2"
			style="display: inline-block;" onclick="closeTab('comments')">Cancel</button>
		<button class="confirm btn btn-lg btn-warning" tabindex="1"
			style="display: inline-block;" onclick="submitComments()">Submit</button>
	</p>
</div>
</head>
<body>
	<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
	<c:choose>
		<c:when test="${emplogindata.contPerDesig=='Admin'}">
			<c:import url="../CommonWebpages/MenusWebpages/AdminMenus.jsp" />
		</c:when>
		<c:when test="${emplogindata.contPerDesig=='Management'}">
			<c:import url="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" />
		</c:when>
		<c:when test="${emplogindata.contPerDesig=='Environmental Officer'}">
			<c:import url="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
		</c:when>
		<c:when test="${emplogindata.contPerDesig=='Third Party'}">
			<c:import url="../CommonWebpages/MenusWebpages/TpMenus.jsp" />
		</c:when>
	</c:choose>

	<section id="main"> <section id="content">
	<div class="container">
		<div class="card">
			<div class="card-header_consent">
				<div class="title_form_consent">
					<font color="black"
						style="font-family: sans-serif; font-size: 15px;"><b>CHANGE
							PASSWORD</b></font>
				</div>
			</div>
			<div class="card-body card-padding card-bg" id="formData">
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="bs-example col-sm-5" id="successmsg">
						<div class="alert alert-success fade in">
							<a href="#" class="close" data-dismiss="alert">&times;</a>
							<center>
								<font color="#00688B" style="font-family: sans-serif"> <strong>Success!</strong>
									Your data has inserted successfully.
								</font>
							</center>
						</div>
					</div>
					<div class="bs-example col-sm-6" id="errorsmsg">
						<div class="alert alert-danger fade in">
							<a href="#" class="close" data-dismiss="alert">&times;</a>
							<center>
								<font color="#e60000" style="font-family: sans-serif"> <strong>Error!</strong>
									A problem has been occurred while submitting your data.
								</font>
							</center>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label class="text-darkbrown">Enter old password: </label>
					</div>
					<div class="col-sm-6">
						<input type="password" class="form-control"
							placeholder="Enter old password"
							onblur="changePassword('checkOldPassword')" name="old_password"
							id="old_password">
					</div>
					<div class="col-sm-4" id="error_old_password"></div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label class="text-darkbrown">Enter new password: </label>
					</div>
					<div class="col-sm-6">
						<input type="password" class="form-control"
							placeholder="Enter new password" name="new_password"
							id="new_password" disabled>
					</div>
					<div class="col-sm-4" id="error_new_password"></div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label class="text-darkbrown">Enter re-type new password:
						</label>
					</div>
					<div class="col-sm-6">
						<input type="password" class="form-control"
							placeholder="Enter re-type new password"
							onblur="changePassword('checkRNewPassword')" name="rnew_password"
							id="rnew_password" disabled>
					</div>
					<div class="col-sm-4" id="error_rnew_password"></div>
				</div>
				<div class="form-group">
					<center>
						<button type="reset" class="btn btn-default waves-effect"
							id="cancelDataBtn">Cancel</button>
						<button type="submit" class="btn bgm-orange waves-effect"
							onclick="changePassword('save')" id="submitDataBtn" disabled>Submit</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	</section> </section>
	<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />
</body>
</html>