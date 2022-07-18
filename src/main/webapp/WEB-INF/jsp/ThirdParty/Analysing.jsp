<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Add Monitoring Report</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<style>
.bs-example {
	margin: 20px;
}

.accordion .fa {
	margin-right: 0.5rem;
}
</style>
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
		<jsp:include page="../CommonWebpages/MenusWebpages/TpMenus.jsp" />
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">ThirdParty</a></li>
						<li class="breadcrumb-item active"><a href="#">Add
								Monitoring Report</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<div class="container">
					<div id="accordion" class="bs-example">
						<h3>Stack</h3>
						<div>
							<div class="row mt-3">
								<div class="col-sm-2 ">
									<label class="col-form-label">Sample Collected Date:</label></br> <span
										class="text-danger" id="samp_st_error" hidden>&nbsp;
										please pick date></span>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="zmdi zmdi-calendar"></i></span>
										</div>
										<input type="date" class="form-control hidden-md-up"
											placeholder="Pick a date"> <input type="text"
											class="form-control date-picker hidden-sm-down" id="samp_st"
											placeholder="Pick a date">
									</div>
								</div>
								<div class="col-sm-2 ">
									<label class="col-form-label">Report Submitted Date:</label></br> <span
										class="text-danger" id="sub_st_error" hidden>&nbsp;
										please pick date</span>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="zmdi zmdi-calendar"></i></span>
										</div>
										<input type="date" class="form-control hidden-md-up"
											placeholder="Pick a date"> <input type="text"
											class="form-control date-picker hidden-sm-down" id="sub_st"
											placeholder="Pick a date">
									</div>
								</div>
							</div>
							<!-- date picker row end -->
							<div id="stackAppend"></div>
						</div>
						<h3>Ambient</h3>
						<div>
							<!-- date picker row start -->
							<div class="row mt-3">
								<div class="col-sm-2 ">
									<label class="col-form-label">Report Submitted Date:</label></br> <span
										class="text-danger" id="samp_amb_error" hidden>&nbsp;
										please pick date</span>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="zmdi zmdi-calendar"></i></span>
										</div>
										<input type="date" class="form-control hidden-md-up"
											placeholder="Pick a date"> <input type="text"
											class="form-control date-picker hidden-sm-down"
											placeholder="Pick a date" id="samp_amb">
									</div>
								</div>
								<div class="col-sm-2 ">
									<label class="col-form-label">Report Submitted Date:</label></br> <span
										class="text-danger" id="sub_amb_error" hidden>&nbsp;
										please pick date</span>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="zmdi zmdi-calendar"></i></span>
										</div>
										<input type="date" class="form-control hidden-md-up"
											placeholder="Pick a date"> <input type="text"
											class="form-control date-picker hidden-sm-down"
											placeholder="Pick a date" id="sub_amb">
									</div>
								</div>
							</div>
							<!-- date picker row end -->
							<div id="ambientAppend"></div>
						</div>
						<h3>Water</h3>
						<div>
							<!-- date picker row start -->
							<div class="row mt-3">
								<label class="col-sm-2 col-form-label">Sample Collected
									Date:</label> <span class="text-danger" id="samp_water_error" hidden>&nbsp;
									please pick date</span>
								<div class="col-sm-4">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="zmdi zmdi-calendar"></i></span>
										</div>
										<input type="date" class="form-control hidden-md-up"
											placeholder="Pick a date"> <input type="text"
											class="form-control date-picker hidden-sm-down"
											placeholder="Pick a date" id="samp_e">
									</div>
								</div>
								<label class="col-sm-2 col-form-label">Report Submitted
									Date:</label> <span class="text-danger" id="sub_water_error" hidden>&nbsp;
									please pick date</span>
								<div class="col-sm-4">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="zmdi zmdi-calendar"></i></span>
										</div>
										<input type="date" class="form-control hidden-md-up"
											placeholder="Pick a date"> <input type="text"
											class="form-control date-picker hidden-sm-down"
											placeholder="Pick a date" id="sub_e">
									</div>
								</div>
							</div>
							<!-- date picker row end -->
							<div id="waterAppend"></div>
						</div>
					</div>

					<div class="bs-example"></div>

				</div>
				<!-- include common footer start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common footer end-->
			</div>
			<!-- inner container div end -->
		</section>
		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->

		<!-- add-monitoring -->
		<script src="../newAssets/projectscripts/add-monitoring.js"></script>
		<script src="../newAssets/projectscripts/common-functions.js"></script>
		<script>
            $(document).ready(function(){
            	$("#accordion").accordion({ heightStyle: 'content', collapsible: true });
             });
         </script>
	</main>
</body>
</html>