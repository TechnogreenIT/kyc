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
						<li class="breadcrumb-item"><a href="#">Environment Clearance</a></li>
						<li class="breadcrumb-item active"><a href="#">Fill
								EC</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<!-- main container start -->
				<div class="container">
					<blockquote class="blockquote">
						<h5 class="mb-0">FILL ENVIRONMENT CLEARANCE DETAILS</h5>
					</blockquote>
					<!-- main card start-->
					<div class="card">
						<div class="card-body">
							<form action="create-ec" id="ec-form"
								method="post" role="form" enctype="multipart/form-data">
								<input type="hidden" name="multipleOperate" id="multipleOperate"
									value="${multipleOperate}">

									<div id="ecform"></div>
								
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
		<script src="../newAssets/projectscripts/add-ec.js"></script>
		<script>
  
			$("#ec-form").validate({
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