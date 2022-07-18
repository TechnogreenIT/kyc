<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="ct"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Water Performance</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<style type="text/css">
.content__title {
	padding: 1.5rem 0rem 0 !important;
}
</style>
</head>
<body data-ma-theme="blue" class="body-bg"
	style="background-image: url('newAssets/img/custom/data-science-bg.png') !important; background-size: cover !important; background-repeat: no-repeat !important; background-attachment: fixed !important;">
	<main class="main">
	<div class="page-loader">
		<div class="page-loader__spinner">
			<svg viewBox="25 25 50 50"> <circle cx="50" cy="50" r="20"
				fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
		</div>
	</div>
	<jsp:include page="../NewCommon/common-header.jsp" /> <!-- include menus here start-->
	<c:if test="${userRole == 'Environmental Officer'}">
		<jsp:include page="../NewCommon/env-menus.jsp" />
	</c:if> <c:if test="${userRole == 'Management'}">
		<jsp:include page="../NewCommon/management-menus.jsp" />
	</c:if> <!-- include menus here end--> <section class="content content--full">
	<!-- inner container div start -->
	<div class="content__inner grey lighten-4">
		<!-- breadcrumb start-->
		<div class="breadcrumb_container ml-4">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Environment Officer</a></li>
				<li class="breadcrumb-item"><a href="#">Performance</a></li>
				<li class="breadcrumb-item active"><a href="#">Water
						Performance</a></li>
				<input type="hidden" id="performance_prevDate" value="0">
				<input type="hidden" id="performance_newDate" value="0">
				<input type="hidden" id="type_name" value="${performanceType}">
			</ol>
		</div>
		<div class="container">
			<div class="card p-4">
				<div class="tab-container">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#DataQuality" role="tab"
							onclick="javascript:onTabClick(1);">Data Quality</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#compliance" role="tab"
							onclick="javascript:openWaterPerformanceTab(2);">
								Non-conformance</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#performance" role="tab"
							onclick="javascript:openWaterPerformanceTab(3);">WWTP</a></li>
					</ul>
					<div class="tab-content">
						<!-- 1st TAB -->
						<div class="tab-pane active fade show" id="DataQuality"
							role="tabpanel">
							<header class="content__title">
							<blockquote class="blockquote">
								<h4 class="mb-0 text-capitalize">
									<b>${performanceType} Water Performance Data Quality</b>
								</h4>
								<div id="str"></div>
							</blockquote>
							<div class="actions">
								<span data-toggle="popover" data-trigger="hover" title=""
									data-content="This is water data quality report."><a
									class="actions__item zmdi zmdi-help"></a> </span>
							</div>
							</header>
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<select class="select2" data-placeholder="Select Year"
											name="yyear" id="yyear1" onchange="checkMonth(1)">
											<option value="">Select Year</option>
											<c:choose>
												<c:when test="${regSourceYearList.size() > 0}">
													<c:forEach var="regSourceYearList"
														items="${regSourceYearList}">
														<option>
															<c:out value="${regSourceYearList}" />
														</option>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<option>No Any Data in daily inputs</option>
												</c:otherwise>
											</c:choose>
										</select>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<select class="select2" data-placeholder="Select Month"
											name="mmonth" id="mmonth1"
											onchange="javascript:openDays(this,1);getMonthChangeData(1,this,'a');">
											<option>Select Month</option>
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
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<select class="select2" data-placeholder="Select Date"
											id='add_days1' name="dday"
											onchange="javascript:getOnDateSetWaterData(1)">
											<option>Select Day</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row" id="overAllDataQality"></div>
						</div>
						<div class='row mt-5'>
							<div class="col-12 col-sm-12 col-md-12 col-lg-12">
								<div class="card ml-4 mr-4">
									<div class="card-header text-center">
										<h5>Single Water Data performance</h5>
									</div>
									<div class="card-body">
										<div class="row" id="sectoralDataQality"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 1st TAB -->
				</div>
			</div>
		</div>
	</div>
	<!-- include common css start--> <jsp:include
		page="../NewCommon/common-footer.jsp" /> <!-- include common css end-->
	</div>
	</section> <!-- include common css start--> <jsp:include
		page="../NewCommon/common-javascript.jsp" /> <!-- include common css end-->
	<!-- Vendors: Data tables --> <script
		src="newAssets/vendors/datatables/jquery.dataTables.min.js"></script>
	<script
		src="newAssets/vendors/datatables-buttons/dataTables.buttons.min.js"></script>
	<script src="newAssets/vendors/datatables-buttons/buttons.print.min.js"></script>
	<script src="newAssets/vendors/jszip/jszip.min.js"></script> <script
		src="newAssets/vendors/datatables-buttons/buttons.html5.min.js"></script>
	<script
		src="newAssets/vendors/easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script type="text/javascript"
		src="newAssets/projectscripts/waterPerformanceJS.js"></script> </main>
</body>
</html>