<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>View Monitoring Data | KYC</title>

<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<style media="screen">
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
		<c:if test="${userRole == 'Environmental Officer'}">
			<jsp:include page="../NewCommon/env-menus.jsp" />
		</c:if>
		<sec:authorize access="hasRole('Management')"><jsp:include
				page="../NewCommon/management-menus.jsp" />
		</sec:authorize>
		<!-- include menus here end-->

		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item"><a href="#">View Monitoring
								Data</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<!-- main container start -->
				<div class="container">
					<blockquote class="blockquote">
						<h5 class="mb-0">View Monitoring</h5>
					</blockquote>
					<!-- main card start-->
					<div class="card">
						<div class="card-body">
							<select class="select2" data-placeholder="Select Date"
								onchange="GetMonitoringDataByDate(this)">
								<option>Select Year</option>
								<c:choose>
									<c:when test="${DateArray.size() > 0}">
										<c:forEach var="DateArray" items="${DateArray}">
											<option>
												<c:out value="${DateArray}" /></option>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option>No Any Data in daily inputs</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
					<div id="accordionStack" class="bs-example">
						<h3>Stack</h3>
						<div id="datastack"></div>
					</div>
					<div id="accordionAmbient" class="bs-example">
						<h3>Ambient</h3>
						<div id="dataAmbient"></div>
					</div>
					<div id="accordionWater" class="bs-example">
						<h3>Water</h3>
						<div id="dataWater"></div>
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

		<!-- Vendors: Data tables -->
		<script src="../newAssets/vendors/datatables/jquery.dataTables.min.js"></script>
		<script
			src="../newAssets/vendors/datatables-buttons/dataTables.buttons.min.js"></script>
		<script
			src="../newAssets/vendors/datatables-buttons/buttons.print.min.js"></script>
		<script src="../newAssets/vendors/jszip/jszip.min.js"></script>
		<script
			src="../newAssets/vendors/datatables-buttons/buttons.html5.min.js"></script>

		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/chartjs-plugin-zoom/0.6.6/chartjs-plugin-zoom.js"></script>

		<script src="../newAssets/vendors/downloadJs/downloadJs.js"></script>

		<script type="text/javascript"
			src="../newAssets/projectscripts/viewMonitoring.js"></script>
		<script>
            $(document).ready(function(){
            	$("#accordionStack").accordion({heightStyle: 'content', collapsible: true });
            	$("#accordionAmbient").accordion({active: false, heightStyle: 'content', collapsible: true });
            	$("#accordionWater").accordion({active: false, heightStyle: 'content', collapsible: true });
                });
         </script>
</body>
</html>