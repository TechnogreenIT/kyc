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
<title>Graph | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->

<link rel="stylesheet" href="../newAssets/css/jquery.feedBackBox.css">
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
						<li class="breadcrumb-item active"><a href="#">Graph</a></li>
						<input type="hidden" id="lastResourceType" value="pw" />
						<input type="hidden" id="lastResourceTypeUnit" value="" />
						<input type="hidden" id="lastGraphTabName" value="DailyTab" />
						<input type="hidden" id="startDate" value="" />
						<input type="hidden" id="endDate" value="" />

					</ol>

				</div>
				<!-- breadcrumb end -->
				<div class="container">
					<div class="row">

						<!--    main -->
						<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
							<div class="card p-4">

								<div class="tab-container">
									<ul class="nav nav-tabs" role="tablist">
										<li class="nav-item"><a class="nav-link active"
											data-toggle="tab" href="#Daily"
											onclick="javascript:openTab('pw','','Daily');" role="tab">Daily</a>
										</li>
										<li class="nav-item"><a class="nav-link"
											data-toggle="tab" href="#Back7Days" role="tab"
											onclick="javascript:openTab('pw','','Back7Days');">Back 7
												Days</a></li>
										<li class="nav-item"><a class="nav-link"
											data-toggle="tab" href="#Weekly" role="tab"
											onclick="javascript:openTab('pw','','Weekly');">Weekly</a></li>
										<li class="nav-item"><a class="nav-link"
											data-toggle="tab" href="#Monthly" role="tab"
											onclick="javascript:openTab('pw','','Monthly');">Monthly</a>
										</li>
										<li class="nav-item"><a class="nav-link"
											data-toggle="tab" href="#Yearly" role="tab"
											onclick="javascript:openTab('pw','','Yearly');">Yearly</a></li>
									</ul>

									<header class="content__title">
										<blockquote class="blockquote">
											<h4 class="mb-0 text-capitalize">
												<b id="mainHeading"> </b>
											</h4>
											<div id="graph_for_title"></div>
										</blockquote>
									</header>
									<div class="tab-content pt-0">
										<div class="tab-pane active fade show" id="Daily"
											role="tabpanel">
											<div class="row">
												<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
													<div class="row">
														<div class="col-sm-4">
															<div class="form-group">
																<select class="select2" data-placeholder="Select Year"
																	name="yyear" id="yyearDailyTab">
																	<option value="">Select Year</option>
																	<c:choose>
																		<c:when test="${consentYears.size() > 0}">
																			<c:forEach var="consentYears" items="${consentYears}">
																				<option>
																					<c:out value="${consentYears}" />
																				</option>
																			</c:forEach>
																		</c:when>

																	</c:choose>
																</select>
															</div>
														</div>
														<div class="col-sm-4">
															<div class="form-group">
																<select class="select2" name="mmonth"
																	id="mmonthDailyTab" data-placeholder="Select Month"
																	onchange="javascript:openDays(this,'DailyTab');">
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
																<select class="select2" id='add_daysDailyTab'
																	name="dday" onchange="makeOnChangeGraph('DailyTab');">
																	<option>Select Day</option>
																</select>
															</div>
														</div>
													</div>
												</div>

												<div class="col-sm-12 mr-3" id="chart_div"
													style="width: auto; height: 500px;"></div>
											</div>
										</div>
										<div class="tab-pane fade" id="Back7Days" role="tabpanel">
											<div class="row">
												<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
													<div class="row">
														<div class="col-sm-4">
															<div class="form-group">
																<select class="select2" data-placeholder="Select Year"
																	name="yyear" id="yyearBack7Days">
																	<option value="">Select Year</option>
																	<c:choose>
																		<c:when test="${consentYears.size() > 0}">
																			<c:forEach var="consentYears" items="${consentYears}">
																				<option>
																					<c:out value="${consentYears}" />
																				</option>
																			</c:forEach>
																		</c:when>

																	</c:choose>
																</select>
															</div>
														</div>
														<div class="col-sm-4">
															<div class="form-group">
																<select class="select2" name="mmonth"
																	id="mmonthBack7Days" data-placeholder="Select Month"
																	onchange="javascript:openDays(this,'Back7Days');">
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
																<select class="select2" id='add_daysBack7Days'
																	name="dday" data-placeholder="Select Day"
																	onchange="makeOnChangeGraph('Back7Days');">
																	<option value="">Select Day</option>
																</select>
															</div>
														</div>
													</div>
												</div>

												<div class="col-sm-12 mr-3" id="chart_div1"
													style="width: auto; height: 500px;"></div>
											</div>
										</div>
										<div class="tab-pane fade" id="Weekly" role="tabpanel">

											<div class="row">
												<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
													<div class="row">
														<div class="col-sm-4">
															<div class="form-group">
																<select class="select2" data-placeholder="Select Year"
																	name="yyear" id="yyearWeekly">
																	<option value="">Select Year</option>
																	<c:choose>
																		<c:when test="${consentYears.size() > 0}">
																			<c:forEach var="consentYears" items="${consentYears}">
																				<option>
																					<c:out value="${consentYears}" />
																				</option>
																			</c:forEach>
																		</c:when>

																	</c:choose>
																</select>
															</div>
														</div>
														<div class="col-sm-4">
															<div class="form-group">
																<select class="select2" name="mmonth" id="mmonthWeekly"
																	data-placeholder="Select Month"
																	onchange="makeOnChangeGraph('Weekly');">
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

													</div>
												</div>

												<div class="col-sm-12 mr-3" id="chart_div2"
													style="width: auto; height: 500px;"></div>
											</div>

										</div>
										<div class="tab-pane fade" id="Monthly" role="tabpanel">
											<div class="row">


												<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
													<div class="col-sm-4">
														<div class="form-group">
															<select class="select2" data-placeholder="Select Year"
																name="yyear" id="yyearMonthly"
																onchange="makeOnChangeGraph('Monthly');">
																<option value="">Select Year</option>
																<c:choose>
																	<c:when test="${consentYears.size() > 0}">
																		<c:forEach var="consentYears" items="${consentYears}">
																			<option>
																				<c:out value="${consentYears}" />
																			</option>
																		</c:forEach>
																	</c:when>

																</c:choose>
															</select>
														</div>
													</div>
													<div class="col-sm-12 mr-3" id="chart_div3"
														style="width: auto; height: 500px;"></div>
												</div>
											</div>
										</div>
										<div class="tab-pane fade" id="Yearly" role="tabpanel">
											<div class="row">

												<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
													<div class="col-sm-4">
														<div class="form-group">
															<select class="select2" data-placeholder="Select Year"
																name="yyear" id="yyearYearly"
																onchange="makeOnChangeGraph('Yearly');">
																<option value="">Select Year</option>
																<c:choose>
																	<c:when test="${consentYears.size() > 0}">
																		<c:forEach var="consentYears" items="${consentYears}">
																			<option>
																				<c:out value="${consentYears}" />
																			</option>
																		</c:forEach>
																	</c:when>

																</c:choose>
															</select>
														</div>
													</div>
													<div class="col-sm-12 mr-3" id="chart_div4"
														style="width: auto; height: 500px;"></div>
												</div>
											</div>

										</div>
									</div>
								</div>

							</div>
						</div>
						<div class="sidebar_hidden" style="display: none">
							<%--   <jsp:include page="../NewCommon/common-graph-aside.jsp" /> --%>
							<nav id="sidebar">


								<div class="list-unstyled components">
									<jsp:include page="../NewCommon/common-graph-aside.jsp" />

								</div>


							</nav>

						</div>
						<div id="test"></div>



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

		<script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
            });
            $('#test').feedBackBox();
        });
    </script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/functions.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/common-functions.js"></script>

		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/echarts/4.7.0/echarts-en.min.js"></script>
		<!-- include common echart start-->
		<jsp:include page="../NewCommon/common-echarts-themes.jsp" />
		<!-- include common echart end-->
		<script type="text/javascript"
			src="../newAssets/projectscripts/custom-echarts-script.js"></script>

		<script type="text/javascript"
			src="../newAssets/projectscripts/custom-chartjs-script.js"></script>


		<script type="text/javascript"
			src="../newAssets/projectscripts/view-graph.js"></script>

		<script src="../newAssets/js/jquery.feedBackBox.js"></script>
	</main>
</body>
</html>