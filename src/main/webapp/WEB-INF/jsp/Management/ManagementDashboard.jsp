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
<title>Management Dashboard | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<link rel="stylesheet" href="../newAssets/vendors/ECharts/reset.css">
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
		<c:if test="${userRole == 'Management'}">
			<jsp:include page="../NewCommon/management-menus.jsp" />
		</c:if>
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-3">
				<div class="container">
					<!-- section 1 -->
					<div class="row mt-5">
						<div class="col-md-6 col-12">
							<div class="card man-todo-height">
								<div class="toolbar toolbar--inner">
									<div class="toolbar__label">To do List</div>
									<div class="actions">
										<button class="btn btn-primary btn-todo zmdi zmdi-plus mr-2"
											onclick="makeTodoModal();"></button>
										<li class="actions__item zmdi zmdi-help-outline mt-2"
											data-toggle="tooltip" data-placement="top"
											data-original-title="Todo List"></li>
									</div>
								</div>
								<div class="todo_div listview listview--bordered todo-scroll">
									<!-- all todo will be appended here -->
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-6 col-12">
									<div class="card card-top-border">
										<div class="card-body card-body-meter">
											<canvas class="meter-canvas" id="overAllDataQuality"> </canvas>
											<center>
												<div id="overAllDataQuality-text" class="meter-value">0</div>
											</center>
										</div>
										<div
											class="card-footer card-footer-custom card-botttom-border mt-2 special-color-dark">
											<div class="row">
												<div class="col-md-6 col-6 card-devider text-white"
													id="date-overAll-state">Data Quality Performance</div>
												<div class="col-md-6 col-6" id="overAll-state"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="card card-top-border">
										<div class="card-body card-body-meter">
											<canvas class="meter-canvas" id="overAllDataCompliance"> </canvas>
											<center>
												<div id="overAllDataCompliance-text" class="meter-value">0</div>
											</center>
										</div>
										<div
											class="card-footer card-footer-custom card-botttom-border mt-2 special-color-dark">
											<div class="row">
												<div class="col-md-6 col-6 card-devider text-white">
													Data Non-compliance</div>
												<div class="col-md-6 col-6" id="overAllCompliance-state">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="card card-top-border">
										<div class="card-body card-body-meter">
											<canvas class="meter-canvas" id="waterTreatmentGaugeMeter"> </canvas>
											<center>
												<div id="waterTreatmentGaugeMeter-text" class="meter-value">0</div>
											</center>
										</div>
										<div
											class="card-footer card-footer-custom card-botttom-border mt-2 special-color-dark">
											<div class="row">
												<div class="col-md-12 col-12 text-white">Water
													Treatment Environment Performance</div>
												<!-- <div class="col-md-12 col-12">
                                          <i class="zmdi zmdi-trending-up zmdi-hc-lg text-success"></i>
                                          <span class="text-success"> (+26.98 %)</span> </div> -->
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="card card-top-border">
										<div class="card-body card-body-meter">
											<canvas class="meter-canvas" id="overAllEnvPerformance"> </canvas>
											<center>
												<div id="overAllEnvPerformance-text" class="meter-value">0</div>
											</center>
										</div>
										<div
											class="card-footer card-footer-custom card-botttom-border mt-2 special-color-dark">
											<div class="row">
												<div class="col-md-12 col-12 text-white">OverAll
													Environment Performance</div>
												<!-- <div class="col-md-12 col-12"><i class="zmdi zmdi-trending-up zmdi-hc-lg text-success"></i> <span class="text-success"> (+26.98 %)</span> </div> -->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- section 1 end -->
					<div class="row">
						<div class="col-sm-12" id="chart_main_div">
							<div class="card">
								<div class="card-body">
									<blockquote class="blockquote mb-4">
										<h4 class="card-title">Analytical Graphs</h4>
									</blockquote>

									<!-- <h6 class="card-subtitle">Praesent commodo cursus magna scelerisque consectetur. </h6> -->
									<div class="tab-container">
										<ul class="nav nav-tabs" role="tablist">
											<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#analyticalGraph1" role="tab" onclick="analyticalGraph1();">Production v/s Water</a></li>
											<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#analyticalGraph2" role="tab" onclick="analyticalGraph2();">Water Consumption</a></li>
											<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#analyticalGraph3" role="tab" onclick="analyticalGraph3();">Fuel Consumption</a></li>
											<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#graph3" role="tab" onclick="getSolidWasteGenerationGraphs();">Solid Waste Generation</a></li>
											<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#graph4" role="tab"onclick="getwasteWaterGenerationGraph();">Waste Water Generation</a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active fade show" id="analyticalGraph1" role="tabpanel">
												<div class="card no-border-card">
													<div class="card-body">
														<div class="row">
															<div class="col-sm-12" id="div_analyticalGraph1"
																style="width: auto; height: 500px;"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane fade" id="analyticalGraph2" role="tabpanel">
												<div class="card no-border-card">
													<div class="card-body">
														<div class="row">
															<div class="col-sm-12" id="div_analyticalGraph2"
																style="width: auto; height: 500px;"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane fade" id="analyticalGraph3" role="tabpanel">
												<div class="card no-border-card">
													<div class="card-body">
														<div class="row">
															<div class="col-sm-12" id="div_analyticalGraph3"
																style="width: auto; height: 500px;"></div>
														</div>
											
													</div>
												</div>
											</div>
											<div class="tab-pane fade" id="graph3" role="tabpanel">
												<div class="card no-border-card">
													<div class="card-body">
														<div class="row">
															<div class="col-sm-12" id="chart_div2"
																style="width: auto; height: 500px;"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane fade" id="graph4" role="tabpanel">
												<div class="card no-border-card">
													<div class="card-body">
														<div class="row">
															<div class="col-sm-12" id="chart_div3"
																style="width: auto; height: 500px;"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-5">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<blockquote class="blockquote mb-4">
										<h4 class="card-title">Sectoral Data</h4>
									</blockquote>
									<!-- <h6 class="card-subtitle">Praesent commodo cursus magna scelerisque consectetur. </h6> -->
									<div class="tab-container">
										<ul class="nav nav-tabs" role="tablist">
											<li class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#quality-performance" role="tab">Quality Performance</a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#non-conformance-performance" role="tab">non-conformance Performance</a></li>
										</ul>

										<div class="tab-content">
											<div class="tab-pane active fade show" id="quality-performance"
												role="tabpanel">
												<div class="row" id="append_quality_performance"></div>
											</div>
											<div class="tab-pane fade" id="non-conformance-performance" role="tabpanel">
												<div class="row" id="append_non_conformance_performance">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-5">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<blockquote class="blockquote mb-4">
										<h4 class="card-title">Monthly Environmental Statistics</h4>
									</blockquote>
									<!-- <h6 class="card-subtitle">Average data input of last 30 days. </h6> -->
									<div id="envStatisticsAccordion">
										<h2>
											<a href="#">Production</a>
										</h2>
										<div>
											<div id="productionAccordion">
												<h2>
													<a href="#">Products</a>
												</h2>
												<div>
													<div id="product_MonthlyStat"></div>
												</div>
												<h2>
													<a href="#">Byproducts</a>
												</h2>
												<div>
													<div id="byproduct_MonthlyStat"></div>
												</div>
												<h2>
													<a href="#">Raw Material</a>
												</h2>
												<div>
													<div id="raw_MonthlyStat"></div>
												</div>
											</div>
										</div>
										<h2>
											<a href="#">Fuel</a>
										</h2>
										<div>
											<div id="fuel_MonthlyStat"></div>
										</div>
										<h2>
											<a href="#">Water Consumption</a>
										</h2>
										<div>
											<div id="waterConsumption_MonthlyStat"></div>
										</div>
										<h2>
											<a href="#">Solid Waste</a>
										</h2>
										<div>
											<div id="solidWasteAccordion">
												<h2>
													<a href="#">Hazardous Waste from Process</a>
												</h2>
												<div>
													<div id="hwp_MonthlyStat"></div>
												</div>
												<h2>
													<a href="#">Hazardous Waste from PCF</a>
												</h2>
												<div>
													<div id="hwpcf_MonthlyStat"></div>
												</div>
												<h2>
													<a href="#">Non-Hazardous Waste from Process</a>
												</h2>
												<div>
													<div id="nhwp_MonthlyStat"></div>
												</div>
												<h2>
													<a href="#">Non-Hazardous Waste from PCF</a>
												</h2>
												<div>
													<div id="nhwpcf_MonthlyStat"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-5">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<blockquote class="blockquote mb-4">
										<h4 class="card-title">Summary of Non-compliance</h4>
									</blockquote>
									<!-- <h6 class="card-subtitle">Input non-compliance of last 30 days.</h6> -->
									<div id="envNonComplianceAccordion">
										<h2>
											<a href="#">Production</a>
										</h2>
										<div>
											<div id="productionNonComplianceAccordion">
												<h2>
													<a href="#">Products</a>
												</h2>
												<div>
													<table class="table table-hover table-bordered normal">
														<thead>
															<tr>
																<th>Product Name</th>
																<th>frequency</th>
																<th>Warning</th>
															</tr>
														</thead>
														<tbody id="product_summary">
														</tbody>
													</table>
												</div>
												<h2>
													<a href="#">Byproducts</a>
												</h2>
												<div>
													<table class="table table-hover table-bordered normal">
														<thead>
															<tr>
																<th>Byproduct Name</th>
																<th>frequency</th>
																<th>Warning</th>
															</tr>
														</thead>
														<tbody id="byproduct_summary">
														</tbody>
													</table>
												</div>
												<h2>
													<a href="#">Raw Material</a>
												</h2>
												<div>
													<table class="table table-hover table-bordered normal">
														<thead>
															<tr>
																<th>Raw Material Name</th>
																<th>frequency</th>
																<th>Warning</th>
															</tr>
														</thead>
														<tbody id="raw_summary">
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<h2>
											<a href="#">Fuel</a>
										</h2>
										<div>
											<table class="table table-hover table-bordered normal">
												<thead>
													<tr>
														<th>Fuel Name</th>
														<th>frequency</th>
														<th>Warning</th>
													</tr>
												</thead>
												<tbody id="fuel_summary">
												</tbody>
											</table>
										</div>
										<h2>
											<a href="#">Solid Waste</a>
										</h2>
										<div>
											<div id="solidWasteNonComplianceAccordion">
												<h2>
													<a href="#">Hazardous Waste from Process</a>
												</h2>
												<div>
													<table class="table table-hover table-bordered normal">
														<thead>
															<tr>
																<th>Hazardous Waste from Process Name</th>
																<th>frequency</th>
																<th>Warning</th>
															</tr>
														</thead>
														<tbody id="hwp_summary">
														</tbody>
													</table>
												</div>
												<h2>
													<a href="#">Hazardous Waste from PCF</a>
												</h2>
												<div>
													<table class="table table-hover table-bordered normal">
														<thead>
															<tr>
																<th>Hazardous Waste from PCF Name</th>
																<th>frequency</th>
																<th>Warning</th>
															</tr>
														</thead>
														<tbody id="hwpcf_summary">
														</tbody>
													</table>
												</div>
												<h2>
													<a href="#">Non-Hazardous Waste from Process</a>
												</h2>
												<div>
													<table class="table table-hover table-bordered normal">
														<thead>
															<tr>
																<th>Hazardous Waste from Process Name</th>
																<th>frequency</th>
																<th>Warning</th>
															</tr>
														</thead>
														<tbody id="nhwp_summary">
														</tbody>
													</table>
												</div>
												<h2>
													<a href="#">Non-Hazardous Waste from PCF</a>
												</h2>
												<div>
													<table class="table table-hover table-bordered normal">
														<thead>
															<tr>
																<th>Hazardous Waste from PCF Name</th>
																<th>frequency</th>
																<th>Warning</th>
															</tr>
														</thead>
														<tbody id="nhwpcf_summary">
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-5">
						<div class="col-md-12">
							<div class="card" id="efficiencyAll" style="display:none;">
								<div class="card-body">
									<blockquote class="blockquote mb-4">
										<h4 class="card-title">Sectoral Data</h4>
									</blockquote>
									<!-- <h6 class="card-subtitle">Praesent commodo cursus magna scelerisque consectetur. </h6> -->
									<div class="tab-container">
										<ul class="nav nav-tabs" role="tablist" id="appendLi">
										</ul>
										<div class="tab-content" id="appendContent">
										</div>
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

		<script type="text/javascript"
			src="../newAssets/projectscripts/gauge.min.js"></script>

		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/echarts/4.7.0/echarts-en.min.js"></script>
		<!-- include common echart start-->
		<jsp:include page="../NewCommon/common-echarts-themes.jsp" />
		<!-- include common echart end-->
		<script type="text/javascript"
			src="../newAssets/projectscripts/custom-echarts-script.js"></script>

		<script type="text/javascript"
			src="../newAssets/projectscripts/functions.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/common-functions.js"></script>
		<script src="../newAssets/projectscripts/todo.js"></script>

		<script type="text/javascript"
			src="../newAssets/projectscripts/man-dashboard.js"></script>
		<script
			src="../newAssets/vendors/easy-pie-chart/jquery.easypiechart.min.js"></script>
		<!-- <script src="../newAssets/projectscripts/common-chartJs.js"></script> -->
		<script>
      $("#envStatisticsAccordion").accordion({
        heightStyle: 'content',
        collapsible: true,
        active: false
      });
      $("#productionAccordion").accordion({
        heightStyle: 'content',
        collapsible: true,
        active: false
      });
      $("#solidWasteAccordion").accordion({
        heightStyle: 'content',
        collapsible: true
      });

      $("#envNonComplianceAccordion").accordion({
        heightStyle: 'content',
        collapsible: true,
        active: false
      });
      $("#productionNonComplianceAccordion").accordion({
        heightStyle: 'content',
        collapsible: true,
        active: false
      });
      $("#solidWasteNonComplianceAccordion").accordion({
        heightStyle: 'content',
        collapsible: true
      });
    </script>
	</main>
</body>
</html>
