<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>

<title>Contact page</title>
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
<script type="text/javascript">
function sendContactEmail()
{
	$("#status").empty();
	var filename1="";
	var from="technogreen.env.solutions@gmail.com";
	var to=$("input[name=mailTo]").val();
	var message = $("#message").val();
	message=message.replace(/ /g,"[sp][sp]");
	message=message.replace(/[\r\n]+/g,"[nl]");
	var filename=$("#file").val();
		//document.getElementById('file').value;
	var valid = 0;
     if(filename!=""){
		var file_data = $('#file').prop('files')[0];//alert(file_data)
		filename1 = file_data.name;
	} 

	if(valid <= 0){
		var form_data = new FormData();
		form_data.append('file', file_data);
	}
	$.ajax({
		type : 'POST',
		enctype :'multipart/form-data', 
		url : "ajax-sendContactEmail?from="+from+"&message="+message+"&to="+to,
		cache: false,
		contentType: false,
		processData: false,
		data : new FormData($("#mailForm")[0]),
		success : function(data) {
			if(data==1)
				{
				 document.getElementById("mailForm").reset(); 
				$("#status").html("<label class='text-info'>Mail Sent Successfully!!</label>");
				}
			
		},
		async: false
		});
}
</script>
</head>

<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />

<c:if test="${userRole == 'Environmental Officer'}">
	<jsp:include page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
</c:if>

<c:if test="${userRole == 'Management'}">
	<jsp:include page="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" />
</c:if>

<c:if test="${userRole == 'admin'}">
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
								style="font-family: sans-serif; font-size: 15px;"><b>Contact
									Us</b></font>
						</div>
					</div>
				</div>

				<div class="card-body card-padding card-bg"
					style="padding-left: 60px">
					<div class="row" style="padding-left: -50px; margin-left: -50px;">
						<center>
							<img src="../img/logo.png" class="img-rounded" alt="logo"
								width="260" height="70">
						</center>
					</div>
					<div class="row"></div>
					<div class="row"></div>
					<div class="row">
						<div class="col-md-6">

							<div class="row"></div>
							<div class="row">
								<div class="col-sm-12">
									<h4>
										<label class="text-darkbrown"><p>
												<span class="glyphicon glyphicon-map-marker"></span> 101,
												202, Hem Opal, Plot No. 26</br> Ekta Society, Near Joshi Wadewale
												Lane,</br> Wakadewadi, Pune 411003
											</p> </label>
									</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<h4>
										<label class="text-darkbrown">
											<p>
												<span class="glyphicon glyphicon-phone"></span> 7276081202
											</p>
										</label>
									</h4>

									<h4>
										<label class="text-darkbrown">
											<p>
												<span class="glyphicon glyphicon-envelope"></span>
												it@technogreen.co.in
											</p>
										</label>
									</h4>

									<h4>
										<label class="text-darkbrown">
											<p>
												<span class="glyphicon glyphicon-globe"></span>
												www.technogreen.co.in
											</p>
										</label>
									</h4>
								</div>
							</div>
						</div>
						<div class="col-md-6" style="margin-top: 20px;">
							<form method="post" action='#' method='post' id='mailForm'
								name="mailForm" enctype='multipart/form-data'>
								<div class="row">
									<div class="col-sm-12">
										<h4>
											<label class="text-darkbrown"><label class="text-red">*</label>
												Email (To respond you back) : </label>
										</h4>
										<div class="form-group">
											<input type="text" class="form-control" required
												name="mailTo" placeholder="Email id">
											<div id="invalid-email" class="error_msg"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<h4>
											<label class="text-darkbrown"><label class="text-red">*</label>
												Query : </label>
										</h4>
										<h4>
											<textarea class="form-control" rows="5" name="message"
												id="message" placeholder="Enter your queries" required></textarea>
									</div>
								</div>
								<div class="row"></div>
								<div class="row">
									<div class="col-sm-12" style="margin-left: 16px">
										<h4>
											<label class="text-darkbrown">Attach the error copy
												(optional) : </label>
										</h4>
										<span class="fileinput-new">Select file</span>
										<h4>
											<input type="file" id="file" name="file" class="form-input"
												placeholder=' Upload File' style="margin-top: 20px;">
									</div>
								</div>
								<div id="invalid-image" class="error_msg"></div>
								<div class="row">
									<center>
										<input type="reset" class="btn btn-default waves-effect"
											value="Cancel" /> <input type="button"
											onclick="sendContactEmail();" onclass="btn btn-info"
											style="background-color: #ff9800; clear: both; height: 45px;"
											value="Send Mail!" id='submit_btn' name="submit_btn" />
									</center>
								</div>
								<div id="status"></div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</section>
<!-- <script src="jquery.validate.min.js"></script> -->

<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />