<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Environmental Officer Dashboard | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
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
		<c:if test="${userRole == 'Environmental Officer'}">
		<jsp:include page="../NewCommon/env-menus.jsp" />
		</c:if>		
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner">
				<div class="row todo">
					<!-- todo div start-->
					<div class="col-md-6 ">
						<div class="card" style="height: 441px; !important">
							<div class="card-header">
								<div class="row">
									<div class="col-md-9">
										<h3 class="text-white">To do list</h3>
									</div>
									<div class="col-md-3">
										<div class="actions">
											<button class="btn btn-primary btn-todo zmdi zmdi-plus mr-2"
												onclick="makeTodoModal();"
												style="padding: 0.450rem .75rem; font-size: 1rem; line-height: 11px;"></button>
											<li class="actions__item zmdi zmdi-help-outline mt-2"
												data-toggle="tooltip" data-placement="top"
												data-original-title="Todo List"></li>
										</div>
									</div>

								</div>

							</div>
							<div class="card-body">
								<div class="todo_div listview listview--bordered todo-scroll">
								</div>							
							</div>
						</div>
					</div>
					<!-- todo div end-->
					<!-- video div start-->
					<div class="col-md-6 ">
						<div class="card">
							<div class="card-header">
								<center>
									<h3 class="text-white">KYC INTRODUCTION</h3>
								</center>
							</div>
							<div class="card-body">
								<video width="100%" height="310" controls playsinline autoplay>
									<source src='../newAssets/LogoVideo/111.mp4' type='video/mp4'>
								</video>
							</div>
						</div>
					</div>
					<!-- video div end-->
				</div>
				<!-- row end -->
				<!-- row start -->
				<div class="row groups">
					 <div class="col-xl-2 col-lg-3 col-sm-4 col-6 offset-md-2">
						<a href="create-consent" data-toggle="tooltip"
							data-placement="top" title=""
							data-original-title="Click here to fill Consent to Establish and Operate">
							<div class="groups__item grey lighten-3">
								<img src="../newAssets/img/custom/fill-consent.png"
									alt="fill-consent" width="120px" height="120px" class="img_hvr">
								<div class="groups__info">
									<strong>Fill Consent Form</strong>
								</div>
							</div>
						</a>
					</div>
					<div class="col-xl-2 col-lg-3 col-sm-4 col-6">
						<a href="regular-daily-data" data-toggle="tooltip"
							data-placement="top" title=""
							data-original-title="Click here to fill Daily Inputs.">
							<div class="groups__item grey lighten-3">
								<img src="../newAssets/img/custom/daily-input.png"
									alt="dailyinput" width="120px" height="120px" class="img_hvr">
								<div class="groups__info">
									<strong>Fill Daily Input</strong>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xl-2 col-lg-3 col-sm-4 col-6">
						<a href="HWManifest-form" data-toggle="tooltip"
							data-placement="top" title=""
							data-original-title="Click here to fill Waste Manifest Details.">
							<div class="groups__item grey lighten-3">
								<img src="../newAssets/img/custom/waste-manifest.png"
									alt="dailyinput" width="120px" height="120px" class="img_hvr">
								<div class="groups__info">
									<strong>Fill Waste Manifest</strong>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xl-2 col-lg-3 col-sm-4 col-6">
						<a href="view-monitoring-reports" data-toggle="tooltip"
							data-placement="top" title=""
							data-original-title="Click here to View Monitoring Details.">
							<div class="groups__item grey lighten-3">
								<img src="../newAssets/img/custom/monitoring-form.png"
									alt="dailyinput" width="120px" height="120px" class="img_hvr">
								<div class="groups__info">
									<strong>View Monitoring Data</strong>
								</div>
							</div>
						</a>
					</div>
				</div>
				<!-- row end   -->
			</div>
			<!-- inner container div end -->
			<!-- include common footer start-->
			<jsp:include page="../NewCommon/common-footer.jsp" />
			<!-- include common footer end-->
			</div>
		</section>
	</main>
	<!-- include common css start-->
	<jsp:include page="../NewCommon/common-javascript.jsp" />
	<!-- include common css end-->
	<!-- TODO -->
	<script src="../newAssets/projectscripts/env-dashboard.js"></script>
	<script src="../newAssets/projectscripts/todo.js"></script>
	

</body>
</html>