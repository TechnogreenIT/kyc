<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>View Regular Data</title>

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
	<jsp:include page="../NewCommon/common-header.jsp" /> <!-- include menus here start-->
	<c:if test="${userRole == 'Environmental Officer'}">
		<jsp:include page="../NewCommon/env-menus.jsp" />
	</c:if> <c:if test="${userRole == 'Management'}">
		<jsp:include page="../NewCommon/management-menus.jsp" />
	</c:if> <!-- include menus here end-->

	<section class="content content--full">
		<!-- inner container div start -->
		<div class="content__inner top-thick-border grey lighten-4">
			<!-- breadcrumb start-->
			<div class="breadcrumb_container ml-4">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
					<li class="breadcrumb-item"><a href="#">Daily Data</a></li>
					<li class="breadcrumb-item"><a href="#">View Daily Data</a></li>
				</ol>
			</div>
			<!-- breadcrumb end -->
			<!-- main container start -->
			<div class="container">
				<blockquote class="blockquote">
					<h5 class="mb-0" name="New">View Daily Data</h5>
					<div id="str"></div>
				</blockquote>
				<div id="str"></div>
				<!-- main card start-->
				<div id='str1'></div>
				<input type="hidden" id="prevDate" value="0"> <input
					type="hidden" id="newDate" value="0">
				<div class="card">
					<div class="row card-body"">
						<div class="col-sm-4">
							<select class="select2" name="yyear" id="yyear1">
								<option value=''>Select Year</option>
								<c:choose>
									<c:when test="${arrayYear.size() > 0}">
										<c:forEach var="arrayYear" items="${arrayYear}">
											<option>
												<c:out value="${arrayYear}" /></option>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option>No Any Data in daily inputs</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<div class="col-sm-4">
							<select class="select2" name="mmonth" id="mmonth1"
								onchange="javascript:openDaysRegular(this,1);">
								<option value=''>Select Month</option>
								<option value='01'>January</option>
								<option value='02'>February</option>
								<option value='03'>March</option>
								<option value='04'>April</option>
								<option value='05'>May</option>
								<option value='06'>June</option>
								<option value='07'>July</option>
								<option value='08'>August</option>
								<option value='09'>September</option>
								<option value='10'>October</option>
								<option value='11'>November</option>
								<option value='12'>December</option>
							</select>
						</div>
						<div class="col-sm-4">
							<select class="select2" id='add_days1' name="dday"
								onchange="onDaySetGetDataRegular(1,this)">
								<option value=''>Select Day</option>

							</select>
						</div>
					</div>
				</div>
				<div id="accordionStack" class="bs-example">
					<h3>Production</h3>
					<div id="dataProduct"></div>
					<h3>Air</h3>
					<div id="dataAir"></div>

					<h3>Water</h3>
					<div id="dataWater"></div>

					<h3>Solid Waste</h3>
					<div id="dataSoild"></div>
				</div>
				<!-- main card end-->
			</div>
			<!-- main container start -->

			<!-- include common css start-->
			<jsp:include page="../NewCommon/common-footer.jsp" />
			<!-- include common css end-->
		</div>
	</section>

	<!-- include common css start--> <jsp:include
		page="../NewCommon/common-javascript.jsp" /> <!-- include common css end-->
	</main>
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


	<sec:authorize access="hasRole('ROLE_ENVROFFICER')">
		<script type="text/javascript"
			src="../newAssets/projectscripts/ViewRegularDataJs.js"></script>
		<script src="../newAssets/projectscripts/modified-data.js"></script>
	</sec:authorize>

	 <sec:authorize access="hasRole('ROLE_MANAGEMENT')">
		<script src="../newAssets/projectscripts/Man-ViewRegularDataJs.js"
			type="text/javascript"></script>
	</sec:authorize> 

	<script>
		$(document).ready(function() {
			$("#accordionStack").accordion({
				heightStyle : 'content',
				collapsible : true
			});
		});
	</script>
</body>
</html>