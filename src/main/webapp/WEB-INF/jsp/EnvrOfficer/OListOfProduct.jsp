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
<title>KYC - FILL CONSENT TO OPERATE DATA</title>

<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
</head>
<body data-ma-theme="blue" class="body-bg">
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
		<jsp:include page="../NewCommon/env-menus.jsp" />
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Environment
								Officer</a></li>
						<li class="breadcrumb-item"><a href="#">Consent</a></li>
						<li class="breadcrumb-item active"><a href="#">Fill To
								Operate Data</a></li>
					</ol>
				</div>
				<header class="content__title">
					<blockquote class="blockquote">
						<h4 class="mb-0">CONTINUE FILLING CONSENT TO OPERATE DATA</h4>
					</blockquote>
					<input type="hidden" name="consent_no" id="consent_no"
						value="${consentId}"> <input type="hidden"
						name="UserIdSession" value="${uId}">
					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="Fill Your Consent To Operate Data."><a
							class="actions__item zmdi zmdi-help"></a></span>
						<div class="dropdown actions__item">
							<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
							<div class="dropdown-menu dropdown-menu-right">
								<a onclick="openConsentForm()" class="dropdown-item"> Finish
								</a>
							</div>
						</div>
					</div>
				</header>
				<!-- breadcrumb end -->
				<div class="container">
					<div class="card">
						<div class="card-body">
							<div id="accordion">
								<c:if test="${sessionIndustryType == 'Industry'}">
									<h3>Production Details</h3>
									<div id="productionAccordion">
										<h3>Product Details</h3>
										<div>
											<div class="row mt-3" id="append_Product">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Product Name</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>

											</div>

											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'Product');" id="save-product-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>

										</div>
										<h3>Byproduct Details</h3>
										<div>
											<div class="row mt-3" id="append_byproduct">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Byproduct Name</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>
											</div>

											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'byproduct');"
														id="save-byproduct-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>
										</div>
										<h3>Raw Material Details</h3>
										<div>
											<div class="row mt-3" id="append_rawMaterial">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Raw Material Name</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>
											</div>

											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'rawMaterial');" id="save-raw-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>
										</div>
									</div>
								</c:if>
								<h3>Air Environment</h3>
								<div>
									<div id="fuelAccordion">
										<h3>Fuel Details</h3>
										<div>
											<div class="row mt-3" id="append_Fuel">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Fuel Name</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>
											</div>
											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'Fuel');" id="save-fuel-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>
										</div>
										<!-- Stack Details -->
										<h3>Stack Details</h3>
										<div>
											<jsp:include
												page="../EnvrOfficer/OperateDesign/DesignStack.jsp" />
										</div>
										<!-- Stack Details -->

										<!-- Ambient Details -->
										<h3>Ambient Details</h3>
										<div>
											<jsp:include
												page="../EnvrOfficer/OperateDesign/DesignAmbient.jsp" />
										</div>
										<!-- Ambient Details -->
									</div>
								</div>
								<h3>Water Environment</h3>
								<div>
									<div id="waterAccordion">
										<!-- Water Pollutant -->
										<h3>Water Pollutant</h3>
										<div>
											<jsp:include
												page="../EnvrOfficer/OperateDesign/DesignWaterPollutant.jsp" />
										</div>
										<!-- Water Pollutant -->

										<!-- Water Consumption and Wastewater Generation -->
										<h3>Water Consumption and WasteWater Generation</h3>
										<div>
											<jsp:include
												page="../EnvrOfficer/OperateDesign/DesignWaterConGen.jsp" />
										</div>
										<!-- Water Consumption and Wastewater Generation -->

										<!-- Water Inventory -->
										<h3>Water Inventory</h3>
										<div>
											<jsp:include
												page="../EnvrOfficer/OperateDesign/DesignWaterInventory.jsp" />
										</div>
										<!-- Water Inventory -->
									</div>
								</div>
								<h3>Solid Waste Environment</h3>
								<div>
									<div id="solidWasteAccordion">
										<h3>Non-Hazardous Waste from Process</h3>
										<div>
											<div class="row mt-3" id="append_nhwp">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Non-Hazardous Waste from
														Process</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>
											</div>

											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'nhwp');" id="save-nhwp-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>

										</div>
										<h3>Non-Hazardous Waste from Pollution Control Facility</h3>
										<div>
											<div class="row mt-3" id="append_nhwpcf">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Non-Hazardous Waste from
														PCF</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>
											</div>

											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'nhwpcf');" id="save-nhwpcf-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>
										</div>
										<h3>Hazardous Waste from Process</h3>
										<div>
											<div class="row mt-3" id="append_hwp">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Hazardous Waste from
														Process</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>
											</div>

											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'hwp');" id="save-hwp-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>
										</div>
										<h3>Hazardous Waste from Pollution Control Facility</h3>
										<div>
											<div class="row mt-3" id="append_hwpcf">
												<div class="col-6 offset-1">
													<p class="font-weight-bolder">Hazardous Waste from PCF</p>
												</div>
												<div class="col-3">
													<p class="font-weight-bolder">Quantity</p>
												</div>
												<div class="col-2">
													<p class="font-weight-bolder">Units</p>
												</div>
											</div>

											<div class="row mt-4 mb-4">
												<div class="col text-center">
													<button type="button" class="btn btn-primary btn-sm"
														onclick="saveData(this,'hwpcf');" id="save-hwpcf-btn">
														<i class='zmdi zmdi-save'></i> Save
													</button>
												</div>
											</div>
										</div>
										<c:if test="${sessionIndustryType == 'Hospital'}">
											<h3>Bio-Medical Waste</h3>
											<div>
												<div class="row mt-3" id="append_bio">
													<div class="col-6 offset-1">
														<p class="font-weight-bolder">Bio-Medical Waste from
															PCF</p>
													</div>
													<div class="col-3">
														<p class="font-weight-bolder">Quantity</p>
													</div>
													<div class="col-2">
														<p class="font-weight-bolder">Units</p>
													</div>
												</div>

												<div class="row mt-4 mb-4">
													<div class="col text-center">
														<button type="button" class="btn btn-primary btn-sm"
															onclick="saveData(this,'bio');" id="save-bio-btn">
															<i class='zmdi zmdi-save'></i> Save
														</button>
													</div>
												</div>
											</div>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- include common footer start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common footer end-->
			</div>
			<!-- inner container div end -->
		</section>
		<!-- Javascript -->
		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->
		<script src="../newAssets/projectscripts/add-elements.js"></script>
		<script src="../newAssets/projectscripts/consent-operate-data.js"></script>
		<script src="../newAssets/projectscripts/add-water-inventory.js"></script>
		<script>
      $(document).ready(function() {
        $("#accordion").accordion({
          heightStyle: 'content',
          collapsible: true
        });
        $("#productionAccordion").accordion({
          heightStyle: 'content',
          collapsible: true
        });
        $("#fuelAccordion").accordion({
          heightStyle: 'content',
          collapsible: true
        });
        $("#waterAccordion").accordion({
          heightStyle: 'content',
          collapsible: true
        });
        $("#solidWasteAccordion").accordion({
          heightStyle: 'content',
          collapsible: true
        });
      });
    </script>
	</main>
</body>
</html>