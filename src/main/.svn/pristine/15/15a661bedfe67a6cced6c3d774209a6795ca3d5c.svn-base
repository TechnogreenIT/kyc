<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>

<title>Reports page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
<link href="https://afeld.github.io/emoji-css/emoji.css"
	rel="stylesheet">
<script src="jquery/tests/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/addElements.js"></script>
<link rel="stylesheet" href="css/demos.css">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/modernizr.custom.53451.js"></script>
<script type="text/javascript" src="js/jquery.gallery.js"></script>
<style type="text/css">
h4 {
	font-size: 16px;
	font-family: "Trebuchet MS", Verdana;
	line-height: 18px;
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

.notifications .btn {
	width: 100%;
	margin-bottom: 20px;
}
</style>

</head>

<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />

<c:if test="${userRole == 'Environmental Officer'}">
	<jsp:include page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
</c:if>

<c:if test="${userRole == 'Management'}">
	<jsp:include page="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" />
</c:if>

<c:if test="${userRole == 'Admin'}">
	<jsp:include page="../CommonWebpages/MenusWebpages/AdminMenus.jsp" />
</c:if>

<c:if test="${userRole == 'Third Party'}">
	<jsp:include page="../CommonWebpages/MenusWebpages/TpMenus.jsp" />
</c:if>

<section id="main">
	<section id="content">
		<div class="container">
			<div class="card">
				<div class="card-header_consent">
					<div class="title_form_consent">
						<div class="col-sm-11">
							<font color="black"
								style="font-family: sans-serif; font-size: 15px;"><b>Report</b></font>
						</div>
					</div>
				</div>

				<div class="card-body card-padding card-bg">
					<form method="post" action='#' method='post' id='mailForm'
						enctype='multipart/form-data'>
						<div class="row">
							<div class="col-sm-12">
								<label class="text-darkbrown"><label class="text-red">*</label>
									Email (To respond you back) : </label>
								<div class="form-group">
									<input type="text" class="form-control" required name="email"
										placeholder="Email id">
									<div id="invalid-email" class="error_msg"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<label class="text-darkbrown"><label class="text-red">*</label>
									Query : </label>
								<textarea class="form-control" rows="5" name="message"
									id="message" placeholder="Enter your queries" required></textarea>
								<div id="invalid-message" class="error_msg"></div>
							</div>
							<div class="row"></div>
							<div class="row">
								<div class="col-sm-12" style="margin-left: 50px">
									<label class="text-darkbrown">Attach the error copy
										(optional) : </label> <input type="file" id="image" name="image"
										class="form-input" placeholder='Image'>
									<div id="invalid-image" class="error_msg"></div>
								</div>
							</div>
							<div class="row">
								<center>
									<input type="reset" class="btn btn-default waves-effect"
										value="Cancel" /> <input type="submit" class="btn btn-info"
										style="background-color: #ff9800;" value="Send Mail!"
										id='submit_btn' name="submit_btn" />

								</center>
							</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</section>
<script src="jquery.validate.min.js"></script>
<script>
	  (function($){
	     jQuery.validator.setDefaults({
			errorPlacement: function(error, element) {
				error.appendTo('#invalid-' + element.attr('id'));
			}
		});
	     $("#mailForm").validate({
		        rules: {
					email:{ 
					   required: true,
					   email: true,
					},
					message: "required"
				},
				messages: {
					email:{ 
					   required: "Please enter your email",
					   minlength: "Please enter a valid email address",
					},
					message: "Please enter your message"
				}
		 });
	  })($);
	</script>
<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />