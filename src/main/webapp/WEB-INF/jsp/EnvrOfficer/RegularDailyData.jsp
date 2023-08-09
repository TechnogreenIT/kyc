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
<title>DailyInput | KYC</title>

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
		<c:if test="${userRole == 'Management'}">
			<jsp:include
				page="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" />
		</c:if>
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item"><a href="#">Daily Data</a></li>
						<li class="breadcrumb-item active"><a href="#">Add Daily
								Data</a></li>
					</ol>
				</div>

				<header class="content__title">

					<blockquote class="blockquote">
						<h4 class="mb-0">Daily Data</h4>
					</blockquote>

					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="This is daily input section you can <br> fillup today's data all fields are <br>necessary to fillup daily for better analysis."><a
							class="actions__item zmdi zmdi-help"></a></span>
						<div class="dropdown actions__item">
							<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
							<div class="dropdown-menu dropdown-menu-right">
								<a data-toggle="modal" data-target="#upload-excel-modal"
									class="dropdown-item"> Upload Excel file </a> <a
									onclick="openRegularData();" class="dropdown-item"> Finish </a>
							</div>
						</div>
					</div>
				</header>
				<!-- breadcrumb end -->

				<div class="container">
					<div class="card">
						<div class="card-body">
							<div class="bs-example">
								<div id="regInputAccordion">
									<h2>
										<a href="#">Production Details</a>
									</h2>
									<div>
										<div id="productionAccordion"></div>
									</div>
									<h2>
										<a href="#">Fuel Consumption</a>
									</h2>
									<div>
										<div id="fuelAccordion"></div>
									</div>
									<h2>
										<a href="#">Water Consumption</a>
									</h2>
									<div>
										<div id="waterMainAccordion"></div>

									</div>
									<h2>
										<a href="#">Solid Waste Environment</a>
									</h2>
									<div>
										<div id="solidWasteAccordion"></div>
									</div>
									<h2>
										<a href="#">Cut and Fill</a>
									</h2>
									<div>
										<div id="cutandfillAccordion"></div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="modal fade" id="upload-excel-modal" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title font-weight-bold">Add Previous Data</h4>
								<div class="actions">
									<span class="mantooltip hover" data-jbox-title=""
										data-jbox-content="You can download sample Excel file <br> and fillup production data like product,<br> byptoduct, raw material, fuel, all wastes."><a
										class="actions__item zmdi zmdi-help" title='help'></a></span>
									<div class="dropdown actions__item">
										<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
										<div
											class="dropdown-menu dropdown-menu-right custom-dropdown-menu">
											<a class="ml-2" href="../newAssets/documents/RegularData.xlsx"><i
												class="zmdi zmdi-download"></i>&nbsp; Download Sample</a>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-body">
								<center>
									<!-- Download Sample Excel <a class='btn btn-primary btn--icon-text'
										href="newAssets/documents/RegularData.xlsx"><i
										class="zmdi zmdi-download"></i> Download </a> <br> -->

									<div class="input-file-container mt-4">
										<input class="input-file" name="dataFile_file"
											id="dataFile_file" type="file" accept=".xlsx" required>
										<label tabindex="0" for="dataFile_file"
											class="input-file-trigger">Select a file...</label>
									</div>
									<p class="file-return"></p>

									<button class="btn btn-primary btn--icon-text mt-4" id="saveD"
										onclick="saveExcelSheetForDailyData('dataFile')">
										<i class="zmdi zmdi-upload"></i> Upload
									</button>
								</center>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
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
		<!-- GET REG DATA -->
		<script src="../newAssets/projectscripts/get-regular-data.js"></script>
		<!-- Jquery UI -->
		<script>
			$(document)
					.ready(
							function() {
								// file uploads button script 
								document.querySelector("html").classList
										.add('js');
								var fileInput = document
										.querySelector(".input-file"), button = document
										.querySelector(".input-file-trigger"), the_return = document
										.querySelector(".file-return");

								button.addEventListener("keydown", function(
										event) {
									if (event.keyCode == 13
											|| event.keyCode == 32) {
										fileInput.focus();
									}
								});
								button.addEventListener("click",
										function(event) {
											fileInput.focus();
											return false;
										});
								fileInput.addEventListener("change", function(
										event) {
									the_return.innerHTML = this.value;
								});

								$("#regInputAccordion").accordion({
									heightStyle : 'content',
									collapsible : true
								});
							});
		</script>
	</main>
</body>
</html>