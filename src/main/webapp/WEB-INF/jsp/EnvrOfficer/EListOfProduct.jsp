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
<title>KYC - FILL CONSENT TO ESABLISH DATA</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
</head>
<body data-ma-theme="blue" class="body-bg"
	style="background-image: url('newAssets/img/custom/data-science-bg.png') !important; background-size: cover !important; background-repeat: no-repeat !important; background-attachment: fixed !important;">
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
								Establish Data</a></li>
					</ol>
				</div>
				<header class="content__title">
					<blockquote class="blockquote">
						<h4 class="mb-0">CONTINUE FILLING CONSENT TO ESABLISH DATA</h4>
					</blockquote>
					<input type="hidden" name="consent_no" id="consent_no"
						value="${consentId}"> <input type="hidden"
						name="UserIdSession" value="${uId}">
						<h2 hidden><%= request.getParameter("ecstatus")%></h2>	
					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="Fill Your Consent To Establish Data."><a
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
					<div id="accordion">
						<c:if test="${sessionIndustryType == 'Industry'}">
							<h3>Production Details</h3>
							<div id="productionAccordion">
								<h3>Product Details</h3>
								<div>
									<div class="mt-4" id="append_ctoe_product">
										<div class="row" id="appended_0_product">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="product_name[]"
														class="form-control" placeholder="Product Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="product_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="product_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_product','product');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('product',this);"
												id="save-product-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
								<h3>Byproduct Details</h3>
								<div>
									<div class="mt-4" id="append_ctoe_byproduct">
										<div class="row" id="appended_0_byproduct">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="byproduct_name[]"
														class="form-control" placeholder="Byproduct Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="byproduct_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="byproduct_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_byproduct','byproduct');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('byproduct',this);"
												id="save-byproduct-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
								<h3>Raw Material Details</h3>
								<div>
									<div class="mt-4" id="append_ctoe_raw">
										<div class="row" id="appended_0_raw">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="raw_name[]" class="form-control"
														placeholder="Raw Material Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="raw_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="raw_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_raw','raw');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('raw',this);" id="save-raw-btn">
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
									<div class="mt-4" id="append_ctoe_fuel">
										<div class="row" id="appended_0_fuel">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="fuel_name[]" class="form-control"
														placeholder="Fuel Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="fuel_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="fuel_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_fuel','fuel');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('fuel',this);"
												id="save-fuel-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
								<!-- Stack Details -->
								<h3>Stack Details</h3>
								<div>
									<jsp:include
										page="../EnvrOfficer/EstablishProduct/StackDetails.jsp" />
								</div>
								<!-- Stack Details -->
								<!-- Ambient Details -->
								<h3>Ambient Details</h3>
								<div>
									<jsp:include page="../EnvrOfficer/EstablishProduct/Ambient.jsp" />
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
										page="../EnvrOfficer/EstablishProduct/WaterPollutant.jsp" />
								</div>
								<!-- Water Pollutant -->

								<!-- Water Consumption and Wastewater Generation -->
								<h3>Water Consumption and Wastewater Generation</h3>
								<div>
									<jsp:include
										page="../EnvrOfficer/EstablishProduct/WaterGenCons.jsp" />
								</div>
								<!-- Water Consumption and Wastewater Generation -->
							</div>
						</div>
						<h3>Solid Waste Environment</h3>
						<div>
							<div id="solidWasteAccordion">
								<h3>Non-Hazardous Waste from Process</h3>
								<div>
									<div class="mt-4" id="append_ctoe_nhwp">
										<div class="row" id="appended_0_nhwp">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="nhwp_name[]" class="form-control"
														placeholder="Non-Hazardous Waste from Process Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="nhwp_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="nhwp_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_nhwp','nhwp');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('nhwp',this);"
												id="save-nhwp-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
								<h3>Non-Hazardous Waste from Pollution Control Facility</h3>
								<div>
									<div class="mt-4" id="append_ctoe_nhwpcf">
										<div class="row" id="appended_0_nhwpcf">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="nhwpcf_name[]"
														class="form-control"
														placeholder="Non-Hazardous Waste from PCF Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="nhwpcf_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="nhwpcf_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_nhwpcf','nhwpcf');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('nhwpcf',this);"
												id="save-nhwpcf-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
								<h3>Hazardous Waste from Process</h3>
								<div>
									<div class="mt-4" id="append_ctoe_hwp">
										<div class="row" id="appended_0_hwp">
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="hwp_categories[]">
														<option value=''>Select Category</option>
														<c:forEach items="${hwCategroriesList}" var="cList">
															<option value="${cList.getCategoryNumber()}">${cList.getCategoryDesc()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<input type="text" name="hwp_name[]" class="form-control"
														placeholder="Hazardous Waste from Process Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="hwp_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<select class="select2" name="hwp_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_hwp','hwp');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>̥
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('hwp',this);" id="save-hwp-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
								<h3>Hazardous Waste from Pollution Control Facility</h3>
								<div>
									<div class="mt-4" id="append_ctoe_hwpcf">
										<div class="row" id="appended_0_hwpcf">
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="hwpcf_categories[]">
														<option value=''>Select Category</option>
														<c:forEach items="${hwCategroriesList}" var="cList">
															<option value="${cList.getCategoryNumber()}">${cList.getCategoryDesc()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<input type="text" name="hwpcf_name[]" class="form-control"
														placeholder="Hazardous Waste from Pollution Control Facility">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="hwpcf_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<select class="select2" name="hwpcf_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_hwpcf','hwpcf');"
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('hwpcf',this);"
												id="save-hwpcf-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
								
								<!--chnges by pallavi-->
								
								<c:if test="${param.ecstatus == 'Yes'}">
								<h3>E-Waste </h3> 
								<div>
									<div class="mt-4" id="append_ctoe_eWaste">
										<div class="row" id="appended_0_eWaste ">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="eWaste_name[]" class="form-control"
														placeholder="eWaste Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="eWaste_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="eWaste_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_eWaste','eWaste');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
								
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('eWaste',this);"
												id="save-eWaste-btn">
												<i class='zmdi zmdi-save'></i>Save
											</button>
										</div>
									</div>
								</div>
								</c:if>
								<c:if test="${param.ecstatus == 'Yes'}">
								<h3>Plastic Waste </h3>
								<div>
									<div class="mt-4" id="append_ctoe_pWaste">
										<div class="row" id="appended_0_pWaste ">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="pWaste_name[]" class="form-control"
														placeholder="pWaste Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="pWaste_quantity[]"
														class="form-control" placeholder="Consent Quantity"> 
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="pWaste_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_pWaste','pWaste');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('pWaste',this);"
												id="save-pWaste-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>
									</c:if>
								<c:if test="${param.ecstatus == 'Yes'}">
									<h3>Batteries Waste </h3>
								<div>
									<div class="mt-4" id="append_ctoe_bWaste">
										<div class="row" id="appended_0_bWaste ">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="bWaste_name[]" class="form-control"
														placeholder="bWaste Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="bWaste_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="bWaste_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_bWaste','bWaste');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('bWaste',this);"
												id="save-bWaste-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
										</div>
									</div>
								</div>	
								</c:if>
								<c:if test="${param.ecstatus == 'Yes'}">
								<h3>C and D Waste </h3>
								<div>	
								<div class="mt-4" id="append_ctoe_cdWaste">
										<div class="row" id="appended_0_cdWaste ">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="cdWaste_name[]" class="form-control"
														placeholder="cdWaste Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="cdWaste_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="cdWaste_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_cdWaste','cdWaste');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
										<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('cdWaste',this);"
												id="save-cdWaste-btn">
												<i class='zmdi zmdi-save'></i> Save
											</button>
									</div>
									</div>
								</div>
								</c:if>
							<c:if test="${sessionIndustryType == 'Hospital'}">
									<h3>Bio-Medical Waste</h3>
									<div> 
										<div class="mt-4" id="append_ctoe_bio">
										<div class="row" id="appended_0_bio">
												<div class="col-3">
													<div class="form-group">
														<select class="select2" name="bio_categories[]">
															<option value=''>Select Category</option>
														</select>
														<div class="invalid-feedback">Please select any !</div>
													</div>
												</div>
												<div class="col-3">
													<div class="form-group">
														<input type="text" name="bio_name[]" class="form-control"
															placeholder="Bio-Medical Waste Name">
														<div class="invalid-feedback">Please enter something
															!</div>
														<i class="form-group__bar"></i>
													</div>
												</div>
												<div class="col-2">
													<div class="form-group">
														<input type="number" name="bio_quantity[]"
															class="form-control" placeholder="Consent Quantity">
														<div class="invalid-feedback">Please enter something
															!</div>
														<i class="form-group__bar"></i>
													</div>
											</div>
												<div class="col-2">
													<div class="form-group">
														<select class="select2" name="bio_unit[]">
															<option value=''>Select unit</option>
															<c:forEach items="${unit}" var="unit">
																<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
															</c:forEach>
														</select>
														<div class="invalid-feedback">Please select any !</div>
													</div>
												</div>
												<div class="col-2">
													<div class="form-group">
														<button type="button"
															class="btn btn-sm btn-light pt-1 pb-1"̥
															onclick="addCtoEElemet('append_ctoe_bio','bio');">
															<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col text-center">
												<button type="button" class="btn btn-primary btn-sm"
													onclick="saveProductionDatas('bio',this);"
													id="save-bio-btn">
													<i class='zmdi zmdi-save'></i> Save
												</button>
											</div>
										</div>
									</div>
								</c:if>
							</div>
						</div>
					      <c:if test="${param.ecstatus == 'Yes'}">
						<h3>Cut and Fill</h3>
							<div id="cutanfillAccordion">
						        <div> 
						        <div class="mt-4" id="append_ctoe_cutfill">
										<div class="row" id="appended_0_cutfill ">
											<div class="col-5">
												<div class="form-group">
													<input type="text" name="cutfill_name[]" class="form-control"
														placeholder="cutfill Name">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<input type="number" name="cutfill_quantity[]"
														class="form-control" placeholder="Consent Quantity">
													<div class="invalid-feedback">Please enter something
														!</div>
													<i class="form-group__bar"></i>
												</div>
											</div>
											<div class="col-3">
												<div class="form-group">
													<select class="select2" name="cutfill_unit[]">
														<option value=''>Select unit</option>
														<c:forEach items="${unit}" var="unit">
															<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
														</c:forEach>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2">
												<div class="form-group">
													<button type="button"
														class="btn btn-sm btn-light pt-1 pb-1"
														onclick="addCtoEElemet('append_ctoe_cutfill','cutfill');">
														<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col text-center">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="saveProductionDatas('cutfill',this);"
												id="save-cutfill-btn">
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
		<script src="../newAssets/projectscripts/add-elements.js" ></script>
		<script src="../newAssets/projectscripts/consent-establish-data.js"></script>
		<script>
            $(document).ready(function () {
            	$("#accordion").accordion({heightStyle: 'content',collapsible: true});
            	$("#productionAccordion").accordion({heightStyle: 'content',collapsible: true});
            	$("#fuelAccordion").accordion({heightStyle: 'content',collapsible: true});
            	$("#waterAccordion").accordion({heightStyle: 'content',collapsible: true});
            	$("#solidWasteAccordion").accordion({heightStyle: 'content',collapsible: true});
            });
         </script>
	</main>
</body>
</html>