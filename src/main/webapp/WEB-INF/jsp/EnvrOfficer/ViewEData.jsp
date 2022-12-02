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
<title>View Consent Details | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
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
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item"><a href="#">Consent</a></li>
						<li class="breadcrumb-item active"><a href="#">View
								Consent to ${actionType}</a></li>
					</ol>
				</div>
				<header class="content__title">
					<blockquote class="blockquote">
						<h4 class="mb-0">CONSENT DATA</h4>
					</blockquote>
					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="You can view consent in this section."><a
							class="actions__item zmdi zmdi-help"></a></span>
					</div>
				</header>
				<!-- breadcrumb end -->
				<div class="container">
					<div class="card">
						<div class="card-body">
							<div class="row">
								<input type="hidden" id="actionType" value="${actionType}">
								<div class="col-6">
									<div class="form-group">
										<select class="select2"
											data-placeholder="Select consent number" name='consentNo'
											id='consentNo' onchange="openConsentDetails();">
											<option value="">Select consent number</option>
											<c:choose>
												<c:when test="${consent.size() > 0 }">
													<c:forEach items="${consent}" var="consent">
														<option value="${consent.getConsentId()}">${consent.getConsNo()}</option>
													</c:forEach>
												</c:when>
											</c:choose>
										</select>
									</div>
								</div>
							</div>
							<div class="bs-example">
								<div id="consentViweMainAccordion">
									<h2>
										<a href="#">Consent Details</a>
									</h2>
									<div>
										<div class="row" id="consentDetailsData">
											<div class="col-12 mt-4 mb-4 text-center">
												<img src="../newAssets/img/nodata1.png" style="width: 338px">
											</div>
										</div>
									</div>
									<h2>
										<a href="#">Production Details</a>
									</h2>
									<div>
										<div id="productionAccordion">
											<h2>
												<a href="#">Product Details</a>
											</h2>
											<div>
												<div class="container" id="product_view"></div>
											</div>
											<h2>
												<a href="#">Byproduct Details</a>
											</h2>
											<div>
												<div class="container" id="byproduct_view"></div>
											</div>
											<h2>
												<a href="#">Raw Material Details</a>
											</h2>
											<div>
												<div class="container" id="raw_view"></div>
											</div>
										</div>
									</div>
									<h2>
										<a href="#">Air Environment</a>
									</h2>
									<div>
										<div id="airAccordion">
											<h2>
												<a href="#">Fuel Details</a>
											</h2>
											<div>
												<div class="container" id="fuel_view"></div>
											</div>
											<h2>
												<a href="#">Stack Details</a>
											</h2>
											<div>
												<div class="container" id="appendStack"></div>
											</div>
											<h2>
												<a href="#">Ambient Details</a>
											</h2>
											<div>
												<div class="container" id="appendAmbient"></div>
											</div>
										</div>
									</div>
									<h2>
										<a href="#">Water Consumption</a>
									</h2>
									<div>
										<div id="waterAccordion">
											<h2>
												<a href="#">Water Pollutant</a>
											</h2>
											<div>
												<div class="container" id="appendWaterPollutant"></div>
											</div>
											<h2>
												<a href="#">Water Consumption and Wastewater Generation</a>
											</h2>
											<div>
												<div class="container" id="appendWaterConGen"></div>
											</div>
										</div>
									</div>
									<h2>
										<a href="#">Solid Waste Environment</a>
									</h2>
									<div>
										<div id="solidWasteAccordion">
											<h2>
												<a href="#">Non-Hazardous Waste from Process</a>
											</h2>
											<div>
												<div class="container" id="nhwp_view"></div>
											</div>
											<h2>
												<a href="#">Non-Hazardous Waste from Pollution Control
													Facility</a>
											</h2>
											<div>
												<div class="container" id="nhwpcf_view"></div>
											</div>
											<h2>
												<a href="#">Hazardous Waste from Process</a>
											</h2>
											<div>
												<div class="container" id="hwp_view"></div>
											</div>
											<h2>
												<a href="#">Hazardous Waste from Pollution Control
													Facility</a>
											</h2>
											<div>
												<div class="container" id="hwpcf_view"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div id="stackModalsHidden"></div>

				<div id="ambientModalsHidden"></div>

				<!-- modal Stack popup -->
				<div class="modal fade" id="upload-stack-excel-modal"
					data-backdrop="static" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title font-weight-bold">Upload multiple
									stack data</h4>
								<div class="actions">
									<span class="mantooltip hover" data-jbox-title=""
										data-jbox-content="download stack and stack parameter <br>files and add multiple stack data easily."><a
										class="actions__item zmdi zmdi-help"></a></span>
									<div class="dropdown actions__item">
										<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
										<div
											class="dropdown-menu dropdown-menu-right custom-dropdown-menu">
											<a class="ml-2" href="../newAssets/documents/Stack.xlsx"><button
													class="btn btn-success">
													<i class="zmdi zmdi-download"></i> Stack details
												</button></a> <a class="ml-2"
												href="newAssets/documents/StackParameter.xlsx"><button
													class="btn btn-success">
													<i class="zmdi zmdi-download"></i> Stack parameter
												</button></a>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-body row">
								<div class="col-6">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<label>Upload .csv file of stack details:</label> <span
											class="btn btn-primary btn-file"> <span
											class="fileinput-new">Select file</span> <span
											class="fileinput-exists">Change</span> <input type="file"
											name="stackFile" id="stackFile" accept=".xlsx" required>
										</span> <span class="fileinput-filename"></span> <a href="#"
											class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
									</div>
								</div>
								<div class="col-6">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<label>Upload .csv file of stack parameter details</label> <span
											class="btn btn-primary btn-file"> <span
											class="fileinput-new">Select file</span> <span
											class="fileinput-exists">Change</span> <input type="file"
											name="stackParameter" id="stackParameter" accept=".xlsx"
											required>
										</span> <span class="fileinput-filename"></span> <a href="#"
											class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
									</div>
								</div>
								<div class="col-12 text-center">
									<button class="btn btn-primary btn--icon-text mt-4" id="saveD"
										onclick="saveExcelSheet('stack')">
										<i class="zmdi zmdi-upload"></i> Upload
									</button>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- end modal Stack popup -->


				<!-- modal ambient popup -->
				<div class="modal fade" id="upload-ambient-excel-modal"
					data-backdrop="static" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title font-weight-bold">Upload multiple
									ambient data</h4>
								<div class="actions">
									<span class="mantooltip hover" data-jbox-title=""
										data-jbox-content="download stack and stack parameter <br>files and add multiple stack data easily."><a
										class="actions__item zmdi zmdi-help"></a></span>
									<div class="dropdown actions__item">
										<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
										<div
											class="dropdown-menu dropdown-menu-right custom-dropdown-menu">
											<a class="ml-2" href="newAssets/documents/Ambient.xlsx"><button
													class="btn btn-success">
													<i class="zmdi zmdi-download"></i> Ambient details
												</button></a> <a class="ml-2"
												href="newAssets/documents/AmbientParameter.xlsx"><button
													class="btn btn-success">
													<i class="zmdi zmdi-download"></i> Ambient parameter
												</button></a>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-body row">
								<div class="col-6">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<label>Upload .csv file of Ambient details:</label> <span
											class="btn btn-primary btn-file"> <span
											class="fileinput-new">Select file</span> <span
											class="fileinput-exists">Change</span> <input type="file"
											name="ambient_file" id="ambient_file" accept=".xlsx" required>
										</span> <span class="fileinput-filename"></span> <a href="#"
											class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
									</div>
								</div>
								<div class="col-6">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<label>Upload .csv file of Ambient parameter details</label> <span
											class="btn btn-primary btn-file"> <span
											class="fileinput-new">Select file</span> <span
											class="fileinput-exists">Change</span> <input type="file"
											name="ambientparameter" id="ambientparameter" accept=".xlsx"
											required>
										</span> <span class="fileinput-filename"></span> <a href="#"
											class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
									</div>
								</div>
								<div class="col-12 text-center">
									<button class="btn btn-primary btn--icon-text mt-4" id="saveD"
										onclick="saveExcelSheet('ambient')">
										<i class="zmdi zmdi-upload"></i> Upload
									</button>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- end modal ambient popup -->

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
		<!-- <script type="text/javascript" src="newAssets/vendors/bootstrap/js/bootstrap-show-modal.js"></script> -->
		<script type="text/javascript"
			src="../newAssets/projectscripts/view-consent.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/common-functions.js"></script>
		<script>
            $(document).ready(function () {
            	 $("#consentViweMainAccordion").accordion({heightStyle: 'content',collapsible: true});
            	 $("#productionAccordion").accordion({heightStyle: 'content',collapsible: true});
            	 $("#airAccordion").accordion({heightStyle: 'content',collapsible: true});
            	 $("#solidWasteAccordion").accordion({heightStyle: 'content',collapsible: true});
            	 $("#waterAccordion").accordion({heightStyle: 'content',collapsible: true});
            });
         </script>
	</main>
</body>
</html>