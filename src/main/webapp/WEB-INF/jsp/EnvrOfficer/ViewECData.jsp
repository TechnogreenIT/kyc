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
<title>View Environment Clearance Details | KYC</title>
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
						<li class="breadcrumb-item"><a href="#">EC</a></li>
						<li class="breadcrumb-item active"><a href="#">View
								Environment Clearance to ${actionType}</a></li>
					</ol>
				</div>
				<header class="content__title">
					<blockquote class="blockquote">
						<h4 class="mb-0">ENVIRONMENT CLEARANCE DATA</h4>
					</blockquote>
					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="You can view EC in this section."><a
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
											data-placeholder="Select Environment Clearance number" name='ecNo'
											id='ecNo' onchange="openEcDetails();">
											<option value="">Select Environment Clearance number</option>
											<c:choose>
												<c:when test="${envEC.size() > 0 }">
													<c:forEach items="${envEC}" var="envEC">
														<option value="${ envEC.getEcId()}">${ envEC.getEcNo()}</option>
													</c:forEach>
												</c:when>
											</c:choose>
										</select>
									</div>
								</div>
							</div>
							<div class="bs-example">
								<div id="ecViweMainAccordion">
									<h2>
										<a href="#">Environment Clearance Details</a>
									</h2>
									<div>
										<div class="row" id="ecsDetailsData">
											<div class="col-12 mt-4 mb-4 text-center">
												<img src="../newAssets/img/nodata1.png" style="width: 338px">
											</div>
										</div>
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
		<!-- <script type="text/javascript" src="newAssets/vendors/bootstrap/js/bootstrap-show-modal.js"></script> -->
		<script type="text/javascript"
			src="../newAssets/projectscripts/view-ec.js"></script>
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