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
<title>Water Budget</title>

<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<style media="screen">
.node {
	white-space: nowrap;
}

.node rect, .node circle, .node ellipse {
	stroke: #333;
	fill: #fff;
	stroke-width: 1.5px;
}

.cluster rect {
	stroke: #333;
	fill: #000;
	fill-opacity: 0.1;
	stroke-width: 1.5px;
}

.edgePath path.path {
	stroke: #333;
	stroke-width: 1.5px;
	fill: none;
}
</style>

<style>
h1, h2 {
	color: #333;
}

body {
	overflow-x: scroll !important;
}

textarea {
	width: 800px;
}

label {
	margin-top: 1em;
	display: block;
}

#mainSvg {
	width: 150rem;
	height: 80rem;
	overflow-x: scroll;
	overflow-y: scroll;
}

.error {
	color: red;
}
</style>
<script src="../newAssets/vendors/waterBudget/d3.v4.js"></script>
<script src="../newAssets/vendors/waterBudget/graphlib-dot.js"></script>
<script src="../newAssets/vendors/waterBudget/dagre-d3.js"></script>
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
					<li class="breadcrumb-item"><a href="#">Water Budget</a></li>
				</ol>
			</div>
			<!-- breadcrumb end -->
			<!-- main container start -->
			<div class="container">
				<blockquote class="blockquote">
					<h5 class="mb-0">Water Budget</h5>
				</blockquote>
				<div id="str"></div>
				<!-- main card start-->
				<div id='str1'></div>
				<input type="hidden" id="prevDate" value="0"> <input
					type="hidden" id="newDate" value="0">
				<div class="card">
					<div class="row card-body"">
						<div class="col-sm-4">
							<div class="form-group">
								<select class="select2" data-placeholder="Select Year"
									name="yyear" id="yyear1">
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
							<select class="select2" name="mmonth" id="mmonth1"
								onchange="javascript:openDays(this,1);">
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
								onchange="onDaySetGetData(1,this)">
								<option value=''>Select Day</option>
							</select>
						</div>
					</div>
					<div class="row card-body justify-content-center ">
						<div class="col-sm-12" style="overflow-x: scroll">
							<!--  <svg id="mainSvg" width=1000 height=800>
  							<g/>
			 	 </svg> -->
							<svg id="mainSvg">
  							<g />
			 	 </svg>
						</div>
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
	</main>
	<!-- include common css start-->
	<jsp:include page="../NewCommon/common-javascript.jsp" />
	<!-- include common css end-->
	<script src="../newAssets/projectscripts/waterBudget/water-budget.js"></script>
	<script
		src="../newAssets/projectscripts/waterBudget/water-budget-custom-function.js"></script>
</body>

</html>