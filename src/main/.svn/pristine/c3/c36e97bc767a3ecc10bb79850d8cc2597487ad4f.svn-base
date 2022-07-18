<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="ct"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Performance ${performanceName}</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<style type="text/css">
.content__title {
	padding: 1.5rem 0rem 0 !important;
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
		<c:if test="${userRole == 'Environmental Officer'}">
			<jsp:include page="../NewCommon/env-menus.jsp" />
		</c:if>
		<c:if test="${userRole == 'Management'}">
			<jsp:include page="../NewCommon/management-menus.jsp" />
		</c:if>
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<input type="hidden" id="prevDate" value="0"> <input
						type="hidden" id="newDate" value="0">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item"><a href="#">Performance</a></li>
						<li class="breadcrumb-item active"><a href="#">${performanceName}</a></li>
					</ol>
				</div>
				<div class="container">
					<div class="card p-4">
						<div class="tab-container">
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									data-toggle="tab" href="#DataQuality" role="tab"
									onclick="getDataQuality();setDefaultTitle('str');">Data
										Quality</a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#compliance" role="tab"
									onclick="getConformance();setDefaultTitle('str1');">Compliance</a>
								</li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#performance" role="tab"
									onclick="getDailyData();setDefaultTitle('str2');">Daily
										Data</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active fade show" id="DataQuality"
									role="tabpanel">
									<header class="content__title">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b>${performanceName} Daily Quality Performance</b>
											</h4>
											<div id="str"></div>
										</blockquote>
										<div class="actions">
											<span data-toggle="popover" data-trigger="hover" title=""
												data-content="This is data quality report."><a
												class="actions__item zmdi zmdi-help"></a> </span>
										</div>
									</header>
									<input type="hidden" id="type_name" value="${performanceName}">
									<div class="row">
										<!-- <label class="col-sm-3 col-form-label">Industry Category</label> -->
										<div class="col-sm-4">
											<div class="form-group">
												<select class="select2" data-placeholder="Select Year"
													name="yyear" id="yyear1">
													<option value="">Select Year</option>
													<c:choose>
														<c:when test="${consentYears.size() > 0}">
															<c:forEach var="consentYears" items="${consentYears}">
																<option>
																	<c:out value="${consentYears}" />
																</option>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<option value="">No Any Data in daily inputs</option>
														</c:otherwise>
													</c:choose>
												</select>
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<select class="select2" data-placeholder="Select Month"
													name="mmonth" id="mmonth1"
													onchange="javascript:openDays(this,1);onMonthSetGetValues(1, this, '${performanceName}', 'a');">
													<option value="">Select Month</option>
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
													onchange="javascript:getOnDateSetPerformance(1,this,'${performanceName}', 'a');">
													<option value="">Select Day</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12" id="performanceData"></div>
									</div>
									<div class='row mt-5'>
										<div class="col-md-12">
											<div class="card ml-4 mr-4">
												<div class="card-header text-center">
													<h5>Sectoral Data Quality Analysis</h5>
												</div>
												<div class="card-body">
													<div class="row" id="performanceDataByProducts"></div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div id="chart_div_error"></div>
									</div>
								</div>
								<!-- 2nd -->
								<div class="tab-pane fade show" id="compliance" role="tabpanel">
									<header class="content__title">
										<input type="hidden" id="type_name_performance"
											value="${performanceName}">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b>${performanceName} Data Compliance Performance</b>
											</h4>
											<div id="str1"></div>
										</blockquote>
										<div class="actions">
											<span data-toggle="popover" data-trigger="hover" title=""
												data-content="This is data compliance performance report."><a
												class="actions__item zmdi zmdi-help"></a> </span>
										</div>
									</header>
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<select class="select2" data-placeholder="Select Year"
													name="yyear" id="yyear2">
													<option value="">Select Year</option>
													<c:choose>
														<c:when test="${consentYears.size() > 0}">
															<c:forEach var="consentYears" items="${consentYears}">
																<option>
																	<c:out value="${consentYears}" />
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
													name="mmonth" id="mmonth2"
													onchange="javascript:openDays(this,2);onMonthSetGetValues(2, this, '${performanceName}', 'b');">
													<option value="">Select Month</option>
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
													id='add_days2' name="dday"
													onchange="javascript:getOnDateSetPerformance(2,this,'${performanceName}', 'b');">
													<option value="">Select Day</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12" id="performanceComplianceAll"></div>
									</div>

									<div class='row mt-5'>
										<div class="col-md-12">
											<div class="card ml-4 mr-4">
												<div class="card-header text-center">
													<h5>Sectoral Data Compliance Analysis</h5>
												</div>
												<div class="card-body row"
													id="performanceComplianceDataByProducts">
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 3rd-->
								<div class="tab-pane fade show" id="performance" role="tabpanel">
									<header class="content__title">
										<input type="hidden" id="type_name_daily_data"
											value="${performanceName}"> <input type="hidden"
											id="type_name_performance" value="${performanceName}">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b>${performanceName} Daily Data</b>
											</h4>
											<div id="str2"></div>
										</blockquote>
										<div class="actions">
											<span data-toggle="popover" data-trigger="hover" title=""
												data-content="This is daily report."><a
												class="actions__item zmdi zmdi-help"></a> </span>
										</div>
									</header>
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<select class="select2" data-placeholder="Select Year"
													name="yyear" id="yyear3">
													<option value="">Select Year</option>
													<c:choose>
														<c:when test="${consentYears.size() > 0}">
															<c:forEach var="consentYears" items="${consentYears}">
																<option>
																	<c:out value="${consentYears}" />
																</option>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<option value="">No Any Data in daily inputs</option>
														</c:otherwise>
													</c:choose>
												</select>
											</div>
										</div>
										<!-- <label class="col-sm-3 col-form-label">Select Industry Type</label> -->
										<div class="col-sm-4">
											<div class="form-group">
												<select name="mmonth" id="mmonth3" class="select2"
													data-placeholder="Select Month"
													onchange="javascript:openDays(this,3);onMonthSetGetValues(3, this, '${performanceName}', 'c');">
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
													id='add_days3' name="dday"
													onchange="javascript:getOnDateSetPerformance(3,this,'${performanceName}', 'c');">
													<option value="">Select Day</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive" id="performance_daily_data"></div>
										</div>
									</div>
									<div class="row">
										<div id="chart_div_error"></div>
									</div>
								</div>
								<!-- 3rd end-->
							</div>
						</div>
					</div>
				</div>
				<!-- include common css start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common css end-->
			</div>
		</section>
		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->
		<!-- Vendors: Data tables -->
		<script type="text/javascript"
			src="../newAssets/vendors/datatables/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="../newAssets/vendors/datatables-buttons/dataTables.buttons.min.js"></script>
		<script type="text/javascript"
			src="../newAssets/vendors/datatables-buttons/buttons.print.min.js"></script>
		<script type="text/javascript"
			src="../newAssets/vendors/jszip/jszip.min.js"></script>
		<script type="text/javascript"
			src="../newAssets/vendors/datatables-buttons/buttons.html5.min.js"></script>
		<!-- Performance scripts -->
		<script type="text/javascript"
			src="../newAssets/projectscripts/performanceJS.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/functions.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/common-functions.js"></script>
		<script
			src="../newAssets/vendors/easy-pie-chart/jquery.easypiechart.min.js"></script>
	</main>
</body>
</html>