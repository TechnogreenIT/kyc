<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:useBean id="date" class="java.util.Date" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>View Company Profile | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
</head>
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
		<sec:authorize access="hasRole('ADMIN')"><jsp:include
				page="../NewCommon/admin-menus.jsp" />
		</sec:authorize>
		<c:if test="${userRole == 'Environmental Officer'}"><jsp:include
				page="../NewCommon/env-menus.jsp" /></c:if>
		<c:if test="${userRole == 'Management'}"><jsp:include
				page="../NewCommon/management-menus.jsp" /></c:if>
		<!-- include menus here end-->

		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item active"><a href="#">View
								Company Profile</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->

				<!-- main container start -->
				<div class="container">
					<blockquote class="blockquote">
						<h4 class="mb-0">${companylist.getCompName()}</h4>
					</blockquote>
					<!-- main card start-->
					<div class="card">
						<div class="card-body">

							<div class="tab-container">
								<ul class="nav nav-tabs" role="tablist">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#BasicInformation" role="tab">Basic
											Information</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#CompanyInformation" role="tab">Company Information</a>
									</li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#ContactPersonInformation" role="tab">Contact Person
											Information</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#OtherInformation" role="tab">Other Information</a></li>
								</ul>

								<div class="tab-content">
									<div class="tab-pane active fade show" id="BasicInformation"
										role="tabpanel">
										<div class="ml-4">
											<div class="row">
												<label class="col-sm-3 col-form-label">Company Name</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control"
															id="editCompanyName" value="${companylist.getCompName()}"
															disabled> <i class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Website</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control" id="editWebsite"
															value="${companylist.getWebsite()}" disabled> <i
															class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Branch</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control" id="editBranch"
															value="${companylist.getBranch()}" disabled> <i
															class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Year of
													establish</label>
												<div class="col-sm-6">
													<div class="form-group">
														<div class="input-group">
															<div class="input-group-prepend">
																<span class="input-group-text"><i
																	class="zmdi zmdi-calendar"></i></span>
															</div>
															<input type="datetime-local"
																class="form-control hidden-md-up"> <input
																type="text"
																class="form-control date-picker hidden-sm-down"
																id="yearOfEst" value="${companylist.getYearEstb()}"
																disabled>
														</div>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Date of Last
													Environmental statement submitted:</label>
												<div class="col-sm-6">
													<div class="form-group">
														<div class="input-group">
															<div class="input-group-prepend">
																<span class="input-group-text"><i
																	class="zmdi zmdi-calendar"></i></span>
															</div>
															<input type="datetime-local"
																class="form-control hidden-md-up"> <input
																type="text"
																class="form-control date-picker hidden-sm-down"
																id="esDate" value="${companylist.getLastEnv()}" disabled>
														</div>
													</div>

												</div>
											</div>
											<c:if test="${userRole == 'Admin'}">
												<button type="button" class="btn btn-primary"
													id="editBtnId1" onclick="editBasicInformation();">&nbsp;Edit
												</button>
											</c:if>
										</div>
										<!-- Basic Information block end -->

									</div>
									<div class="tab-pane fade" id="CompanyInformation"
										role="tabpanel">
										<form class="ml-4">
											<div class="row">
												<label class="col-sm-3 col-form-label">Address</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control"
															placeholder="Address"
															value="${companylist.plotNo}, ${companylist.street}, ${companylist.village}, ${companylist.taluka}, ${companylist.city}-${companylist.pincode}, ${companylist.country}"
															disabled> <i class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Phone No.</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="number" class="form-control" id="editPhoneNo"
															value="${companylist.phoneNo}" disabled> <i
															class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Fax</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control" id="editFax"
															value="${companylist.fax}" disabled> <i
															class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<c:if test="${userRole == 'Admin'}">
												<button type="button" class="btn btn-primary"
													id="editBtnId2" onclick="editCompanyInformation();">&nbsp;Edit
												</button>
											</c:if>
										</form>
									</div>
									<div class="tab-pane fade" id="ContactPersonInformation"
										role="tabpanel">
										<form class="ml-4">
											<div class="row">
												<label class="col-sm-3 col-form-label">Name</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control"
															id="editContactName" value="${companylist.contPerName}"
															disabled> <i class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Contact no.</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control" id="editContactNo"
															value="${companylist.contPerNo}" disabled> <i
															class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Email</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="email" class="form-control"
															id="editContactEmail" value="${companylist.email}"
															disabled> <i class="form-group__bar"></i>
													</div>

												</div>
											</div>

											<c:if test="${userRole == 'Admin'}">
												<button type="button" class="btn btn-primary"
													id="editBtnId3" onclick="editContactPersonInformation();">&nbsp;Edit
												</button>
											</c:if>
										</form>
									</div>

									<div class="tab-pane fade" id="OtherInformation"
										role="tabpanel">
										<form class="ml-4">

											<div class="row">
												<label class="col-sm-3 col-form-label">Industry
													Category</label>
												<div class="col-sm-3">
													<div class="form-group">
														<select class="select2" id="editIndCategory"
															data-placeholder="Industry Category" disabled>
															<option value="${companylist.industryCategory}" selected>${companylist.industryCategory}</option>
															<c:forEach items="${IndcatList}" var="indcatList">
																<option value="${indcatList.getCategory()}">${indcatList.getCategory()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<label class="col-sm-3 col-form-label">Industry Type</label>
												<div class="col-sm-3">
													<div class="form-group">
														<select class="select2" id="editIndType"
															data-placeholder="Industry Type" disabled>
															<option value="${companylist.industryType}" selected>${companylist.industryType}</option>
															<c:forEach items="${IndtypeList}" var="IndtypeList">
																<option value="${IndtypeList.getType()}">${IndtypeList.getType()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Primary
													Category</label>
												<div class="col-sm-3">
													<div class="form-group">
														<select class="select2" id="editPrimaryCategory"
															data-placeholder="Primary Category" disabled>
															<option value="${companylist.indPrimary}" selected>${companylist.indPrimary}</option>
															<c:forEach items="${Indprimarylist}" var="Indprimarylist">
																<option value="${Indprimarylist.getPrimaryName()}">${Indprimarylist.getPrimaryName()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<label class="col-sm-3 col-form-label">Secondary
													Category</label>
												<div class="col-sm-3">
													<div class="form-group">
														<select class="select2" id="editSecondaryCategory"
															data-placeholder="Secondary Category" disabled>
															<option value="${companylist.indSecondary}" selected>${companylist.indSecondary}</option>
															<c:forEach items="${Indsecondarylist}"
																var="Indsecondarylist">
																<option value="${Indsecondarylist.getSecondaryName()}">${Indsecondarylist.getSecondaryName()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>

											<div class="row">
												<label class="col-sm-3 col-form-label">Number of
													Working Days</label>
												<div class="col-sm-3">
													<div class="form-group">
														<select class="select2" id="editWoringDays"
															data-placeholder="Number of Working Days" disabled>
															<option value="${companylist.noWorkDays}" selected>${companylist.noWorkDays}</option>
															<c:forEach begin="1" end="7" varStatus="loop">
																<option value="${loop.index}">${loop.index}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<label class="col-sm-3 col-form-label">Working Hour
													Per Day</label>
												<div class="col-sm-3">
													<div class="form-group">
														<select class="select2" id="editWoringHours"
															data-placeholder="Working Hour Per Day" disabled>
															<option value="${companylist.workingHour}" selected>${companylist.workingHour}</option>
															<c:forEach begin="1" end="24" varStatus="loop">
																<option value="${loop.index}">${loop.index}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<c:if test="${userRole == 'Admin'}">
												<button type="button" class="btn btn-primary"
													id="editBtnId4" onclick="editOtherInformation();">&nbsp;Edit
												</button>
											</c:if>
										</form>
									</div>
								</div>
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

		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->

		<c:if test="${userRole == 'Admin'}">
			<script src="newAssets/projectscripts/edit-company.js"></script>
		</c:if>
</body>
</html>
