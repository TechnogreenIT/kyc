<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email Dashboard</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="vendors/ckeditor/ckeditor.js" /></script>

<script>
	var jq = jQuery.noConflict();
	jq( function() {
		jq( "#tabs" ).tabs();
	});
	jq( function() {
		jq( "#tabs" ).tabs({
			beforeLoad: function( event, ui ) {
				ui.panel.html(jq('#ind').clone()); // the only line one I added to the official sample
				ui.jqXHR.fail(function() {
					ui.panel.html("ERROR: Couldn't load this tab.");
				});
			}
		});
	});
	</script>
<script type="text/javascript" src="js/graphs/common.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script>
function sendEmail()
{
	var mail_from = $('#mail_from').val();
	var mail_to=$('#mail_to').val();
	var subject=$('#mail_subject').val();
	//var message=$('#message').val(); 

	    	if(mail_to=="")
	    		{
	    		$("#emailtoerror").html("<label class=text-red>Select Receiver Email!</label>");
	    		}else
	    			{
	    			
	         $.ajax({
	        	 type : "POST",
				url : "ajax-send-email",
            data: ({
				mail_from: mail_from,
				mail_to: mail_to,
				subject: subject,
				//message: message
            }),
	        success: function(data){
	        	alert("Mail Sent Successfully");
    	      
	        },
	        error: function (e) {
            alert(e);
	        }
	        }); 
	    			}
}
</script>
</head>
<body>

	<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
	<jsp:include page="../CommonWebpages/MenusWebpages/AdminMenus.jsp" />

	<section id="main"> <section id="content">
	<div class="container">
		<div class="card">
			<div class="card-graph">
				<div class="title_graph">
					<font color="black"
						style="font-family: sans-serif; font-size: 15px;"><b>Email
							Dashboard</b></font>
				</div>
			</div>
			<div class="card-body card-padding">
				<div id="tabs">
					<ul>
						<li><a href="#tabs-1">Compose Email</a></li>
					</ul>

					<div id="tabs-1">
						<div class="row">
							<!-- SET TITLE --->
							<div class="col-sm-12">

								<div class="col-sm-12"
									style="margin-top: 35px;; margin-bottom: 10px; margin-left: 15px;">
									<h4>
										<font color="grey"><b> Compose email template </b></font>
									</h4>
									<br>
								</div>
								<div class="col-sm-12">

									<form name="comp_cre" method="post" role="form" action="#">
										<div class="form-group">
											<div class="fg-line">
												<br /> <label><b><font color="#2d6b2f"
														style="font-family: sans-serif">From :</font></b></label>
												<div class="dtp-container fg-line">
													<input type='text' class="form-control" id="mail_from"
														name="mail_from" value="${emplogindata.email}"
														name="mail_from" disabled>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-12">
												<label><b><font color="#2d6b2f"
														style="font-family: sans-serif">To :</font></b></label>
												<div class="form-group">
													<div class="fg-line">

														<select class='chosen' required name="mail_to"
															id="mail_to" required>
															<option value="">Select Option</option>
															<c:forEach items="${emails }" var="email">
																<option value="${email }">${email }</option>
															</c:forEach>
														</select>

													</div>
													<div id="emailtoerror"></div>
												</div>
											</div>

											<div class="col-sm-12">
												<label><b><font color="#2d6b2f"
														style="font-family: sans-serif">Subject :</font></b></label>
												<div class="dtp-container fg-line">
													<input type='text' class="form-control" id="mail_subject"
														placeholder="&nbsp;&nbsp;Subject" name="mail_subject">
												</div>
											</div>

											<!-- <div class="col-sm-12">
												<label><b><font color="#2d6b2f"
														style="font-family: sans-serif">Message</font></b></label>
												<div class="form-group">
												
												<textarea name="message" rows="10" id="message">
												</textarea>
												</div>
											</div> -->
										</div>
										<div class="form-group">
											<div class="fg-line">
												<center>
													<button type="reset" class="btn btn-default waves-effect">Cancel</button>
													<button type="button" class="btn btn-info"
														id="emailDataBtn" style="background-color: #a9c52f;"
														onclick="sendEmail()">Send Mail</button>
												</center>
											</div>
										</div>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section> </section>
	<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />
	<script src="vendors/bootgrid/jquery.bootgrid.updated.min.js"></script>
	<!-- <link href="vendors/bootgrid/jquery.bootgrid.min.css" rel="stylesheet" /> -->

	<!-- Data Table -->
</body>
</html>