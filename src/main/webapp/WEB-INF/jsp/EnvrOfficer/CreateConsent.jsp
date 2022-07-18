<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Fill Consent Form | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<style media="screen"></style>
</head>
<body data-ma-theme="blue">
	<main class="main">
		<div class="page-loader">
			<div class="page-loader__spinner">
				<svg viewBox="25 25 50 50">
                  <circle cx="50" cy="50" r="20" fill="none"
						stroke-width="2" stroke-miterlimit="10" />
               </svg>
			</div>
		</div>
		<jsp:include page="../NewCommon/common-header.jsp" />
		<!-- include menus here start-->
		<jsp:include page="../NewCommon/env-menus.jsp" />
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Environment
								Officer</a></li>
						<li class="breadcrumb-item"><a href="#">Consent</a></li>
						<li class="breadcrumb-item active"><a href="#">Fill
								Consent</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<!-- main container start -->
				<div class="container">
					<blockquote class="blockquote">
						<h5 class="mb-0">FILL CONSENT DETAILS</h5>
					</blockquote>
					<!-- main card start-->
					<div class="card">
						<div class="card-body">
							<form action="worker-create-consent" id="consent-form"
								method="post" role="form" enctype="multipart/form-data">
								<input type="hidden" name="multipleOperate" id="multipleOperate"
									value="${multipleOperate}">
								<div id="que1">
									<div class="form-group">
										<label class="font-weight-bold">Do You Have Valid
											Consent to Establish for Existing Consent to Operate ? </label> <span
											data-toggle="popover" data-trigger="hover" title=""
											data-html="true"
											data-content="1. Click Yes if you already have Consent to Establish. <br/> 2. Click No if you dont have Consent to Establish"><a
											class="actions__item zmdi zmdi-help"
											style="margin-top: -6px;"></a> </span><br>
										<div class="radio radio--inline cursor-pointer">
											<input type="radio" name="Validstatus" id='validY'
												value='Yes' onclick="showForm('EYes')"> <label
												class="radio__label" for="validY">Yes</label>
										</div>
										<div class="radio radio--inline cursor-pointer">
											<input type="radio" name="Validstatus" id='ValidN' value='No'
												onclick="showForm('ENo')"> <label
												class="radio__label" for="ValidN">No</label>
										</div>
									</div>

									<div id="que2" style="display: none;">
										<div class="form-group">
											<label class="font-weight-bold"> Do You Have Valid
												Consent to Establish for Expansion?</label> <span
												data-toggle="popover" data-trigger="hover" title=""
												data-html="true"
												data-content="1. Click Yes if you have Consent to Establish for expansion. <br/> 2. Click No if you dont have Consent to Establish for expansion."><a
												class="actions__item zmdi zmdi-help"
												style="margin-top: -6px;"></a> </span><br>
											<div class="radio radio--inline cursor-pointer">
												<input type="radio" name="ExValidstatus" id="ExvalidY"
													value='Yes' onclick="showForm('ExYes')"> <label
													class="radio__label" for="ExvalidY">Yes</label>
											</div>
											<div class="radio radio--inline cursor-pointer">
												<input type="radio" name="ExValidstatus" id="ExValidN"
													value='No' onclick="showForm('ExNo')"> <label
													class="radio__label" for="ExValidN">No</label>
											</div>
										</div>
									</div>

									<div id="que3" style="display: none;">
										<div class="form-group">
											<label class="font-weight-bold"> Do You Have Multiple
												Consent to Operate ?</label> <br>
											<div class="radio radio--inline cursor-pointer">
												<input type="radio" name="MultipleOP" id="MultipleOPYes"
													value='Yes' onclick="showForm('MultipleYes')"> <label
													class="radio__label" for="MultipleOPYes">Yes</label>
											</div>
											<div class="radio radio--inline cursor-pointer">
												<input type="radio" name="MultipleOP" id="MultipleOPNo"
													value='No' onclick="showForm('MultipleNo')"> <label
													class="radio__label" for="MultipleOPNo">No</label>
											</div>
										</div>
									</div>


									<div id="que4" style="display: none;">
										<div class="form-group">
											<label class="font-weight-bold"> Do you want to
												Amalgamation Consent to Operate ?</label> <br>
											<div class="radio radio--inline cursor-pointer">
												<input type="radio" name="expandOP" id="expandOPYes"
													value='Yes' onclick="showForm('expandOPYes')"> <label
													class="radio__label" for="expandOPYes">Yes</label>
											</div>
											<div class="radio radio--inline cursor-pointer">
												<input type="radio" name="expandOP" id="expandOPNo"
													value='No' onclick="showForm('expandOPNo')"> <label
													class="radio__label" for="expandOPNo">No</label>
											</div>
										</div>
									</div>

									<div id="consentform"></div>
								</div>
							</form>
						</div>
					</div>
					<!-- main card end-->
				</div>
				<!-- main container start -->
				<!-- include common css start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common css end-->
			</div>
		</section>
		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->
		<!-- add-consent -->
		<script src="../newAssets/projectscripts/add-consent.js"></script>
		<script>
  
			$("#consent-form").validate({
				rules : {
					consentFilePath:{
			            required:true,
			            extension: "docx|rtf|doc|pdf"
			        }
				}
			});
		</script>
</body>
</html>