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
<title>Water ${WaterStatiticsName} Statistics | KYC</title>
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
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item"><a href="#">Water Statistics</a></li>
						<li class="breadcrumb-item active"><a href="#">${WaterStatiticsName}</a></li>
					</ol>
					<input type="hidden" id="prevDate" value="0"> <input
						type="hidden" id="newDate" value="0"> <input type="hidden"
						id="waterpageornot" value="No"> <input type="hidden"
						id="source_type" value="${WaterStatiticsName}">
				</div>
				<!-- breadcrumb end -->
				<div class="container">
					<div class="card p-4">
						<div class="tab-container">
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									data-toggle="tab" href="#graph" role="tab"
									onclick="javascript:graphdata();setDefaultTitle('str');">Graph</a>
								</li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#statistic" role="tab"
									onclick="javascript:statisticalAnalysis();setDefaultTitle('str1');">Statistical
										Analysis</a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#performance" role="tab"
									onclick="javascript:dataQualityPerformanceStat();setDefaultTitle('str2');">Data
										Quality Performance</a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#product" role="tab"
									onclick="javascript:dailyDataStat();setDefaultTitle('str3');">Product
										Daily Data</a></li>
							</ul>
							<div class="tab-content">
								<!-- Graph Panel Starts -->
								<div class="tab-pane active fade show" id="graph"
									role="tabpanel">
									<header class="content__title">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b> ${WaterStatiticsName} Data Graph </b>
											</h4>
											<div id="str"></div>

										</blockquote>
										<div class="actions">
											<span data-toggle="popover" data-trigger="hover" title=""
												data-content="This is daily data graph."><a
												class="actions__item zmdi zmdi-help"></a> </span>
										</div>
									</header>
									<div class='container'>
										<div class="row">
											<!-- <label class="col-sm-3 col-form-label">Industry Category</label> -->
											<div class="col-sm-4">
												<div class="form-group">
													<select class="select2" data-placeholder="Select Year"
														name="yyear" id="yyear1">
														<option value="">Select Year</option>
														<c:choose>
															<c:when test="${regSourceYears.size() > 0}">
																<c:forEach var="regSourceYears"
																	items="${regSourceYears}">
																	<option>
																		<c:out value="${regSourceYears}" />
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
											<!-- <label class="col-sm-3 col-form-label">Select Industry Type</label> -->
											<div class="col-sm-4">
												<div class="form-group">
													<select class="select2" name="mmonth" id="mmonth1"
														data-placeholder="Select Month"
														onchange="javascript:openDays(this,1);onMonthSetGetValues('1','wa')">
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
													<select class="select2" id='add_days1' name="dday"
														data-placeholder="Select Day"
														onchange="javascript:onDateSetGetValuesWater(1, this, 'water', 'wa');">
														<option value="">Select Day</option>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="row"></div>
									<div class="row"></div>
									<div class="graph-div">
										<%-- <canvas class="col-sm-12" id="source_div" width="980" height="450" style="overflow-y:hidden; overflow-x:auto;"><img src="img/graph_img.gif"></canvas> --%>
										<div class="col-sm-12 mr-3" id="source_div"
											style="width: auto; height: 450px;"></div>
									</div>
								</div>
								<!-- Statistical Analysis panel starts -->
								<div class="tab-pane fade" id="statistic" role="tabpanel">
									<header class="content__title">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b> ${WaterStatiticsName} Statistical Analysis </b>
											</h4>
											<div id="str1"></div>

										</blockquote>
										<div class="actions">
											<span data-toggle="popover" data-trigger="hover" title=""
												data-content="This is daily data graph."><a
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
														<c:when test="${regSourceYears.size() > 0}">
															<c:forEach var="regSourceYears" items="${regSourceYears}">
																<option>
																	<c:out value="${regSourceYears}" />
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
										<!-- <label class="col-sm-3 col-form-label">Select Industry Type</label> -->
										<div class="col-sm-4">
											<div class="form-group">
												<select class="select2" data-placeholder="Select Month"
													name="mmonth" id="mmonth2"
													onchange="javascript:openDays(this,2);onMonthSetGetValues('2','wb')">
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
												<select class="select2" data-placeholder="Select Day"
													id='add_days2' name="dday"
													onchange="javascript:onDateSetGetValuesWater(2, this, 'water', 'wb')">
													<option value="">Select Day</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12" id="statisticalAnalysisWater"></div>
									</div>
								</div>
								<!-- Performance Panel is Start -->
								<div class="tab-pane fade" id="performance" role="tabpanel">
									<header class="content__title">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b> ${WaterStatiticsName} Daily Quality Performance </b>
											</h4>
											<div id="str2"></div>

										</blockquote>
										<div class="actions">
											<span data-toggle="popover" data-trigger="hover" title=""
												data-content="This is daily data graph."><a
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
														<c:when test="${regSourceYears.size() > 0}">
															<c:forEach var="regSourceYears" items="${regSourceYears}">
																<option>
																	<c:out value="${regSourceYears}" />
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
													name="mmonth" id="mmonth3"
													onchange="javascript:openDays(this,3);onMonthSetGetValues('3','wc')">
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
												<select class="select2" data-placeholder="Select Day"
													id='add_days3' name="dday"
													onchange="javascript:onDateSetGetValuesWater(3, this, 'water', 'wc')">
													<option value="">Select Day</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row" id="performanceStatWater"></div>
								</div>
								<!-- Product Daily Data -->
								<div class="tab-pane fade" id="product" role="tabpanel">
									<header class="content__title">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b> ${WaterStatiticsName} Daily Data </b>
											</h4>
											<div id="str3"></div>

										</blockquote>
										<div class="actions">
											<span data-toggle="popover" data-trigger="hover" title=""
												data-content="This is daily data graph."><a
												class="actions__item zmdi zmdi-help"></a> </span>
										</div>
									</header>
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<select class="select2" data-placeholder="Select Year"
													name="yyear" id="yyear4">
													<option value="">Select Year</option>
													<c:choose>
														<c:when test="${regSourceYears.size() > 0}">
															<c:forEach var="regSourceYears" items="${regSourceYears}">
																<option>
																	<c:out value="${regSourceYears}" />
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
													name="mmonth" id="mmonth4"
													onchange="javascript:openDays(this, 4);onMonthSetGetValues('4','wd')">
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
												<select class="select2" data-placeholder="Select Day"
													id='add_days4' name="dday"
													onchange="javascript:onDateSetGetValuesWater(4, this, 'water', 'wd');">
													<option value="">Select Day</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12" id="dailyDataStat"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- include common css start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common css end-->
			</div>
		</section>
		<!-- Javascript -->
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
			src="https://cdnjs.cloudflare.com/ajax/libs/echarts/4.6.0/echarts-en.js"></script>
		<!-- include common echart start-->
		<jsp:include page="../NewCommon/common-echarts-themes.jsp" />
		<!-- include common echart end-->
		<script type="text/javascript"
			src="../newAssets/projectscripts/custom-echarts-script.js"></script>

		<script type="text/javascript"
			src="../newAssets/projectscripts/water-statistics.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/functions.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/common-functions.js"></script>

	</main>
</body>
</html>