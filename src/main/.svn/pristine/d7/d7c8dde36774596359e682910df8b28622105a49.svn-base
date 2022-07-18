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
<title>Create Company Profile | KYC</title>
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
		<jsp:include page="../NewCommon/admin-menus.jsp" />
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item active"><a href="#">Create
								Company Profile</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<div class="container mt-2">
					<blockquote class="blockquote">
						<h4 class="mb-0">Create Company Profile</h4>
					</blockquote>
					<div class="card p-4">
						<form class="p-4" name="comp_cre" role="form"
							id="create-company-form" action="admin-addCompany">
							<div class="row">
								<div class="col-md-12 mb-2">
									<h5 class="blockquote">Company Basic Information</h5>
								</div>
								<div class="col-md-6 col-sm-6">
									<div class="form-group">
										<input type="text" class="form-control" name="compName"
											placeholder="Company Name" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-6 col-sm-6">
									<div class="form-group">
										<input type="email" class="form-control" name="compEmail"
											placeholder="Company E-mail" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-6 col-sm-6">
									<div class="form-group">
										<input type="text" class="form-control" name="website"
											placeholder="Company Website" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-6 col-sm-6">
									<div class="form-group">
										<input type="text" class="form-control" name="branch"
											placeholder="Company Branch" required> <i
											class="form-group__bar"></i>
									</div>
								</div>


								<div class="col-md-12 mb-2">
									<h5 class="blockquote">Address</h5>
								</div>
								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="plotNo"
											placeholder="Plot No." required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="street"
											placeholder="Street" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="village"
											placeholder="Village" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="taluka"
											placeholder="Taluka" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="district"
											placeholder="District" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<select class="select2" name="ro"
											onchange="sub_roChange(this);"
											data-placeholder="Select Regional Office" required>
											<option value=""></option>
											<option value="Pune">Pune</option>
											<option value="Amravati">Amravati</option>
											<option value="Aurangabad">Aurangabad</option>
											<option value="Kalyan">Kalyan</option>
											<option value="Kolhapur">Kolhapur</option>
											<option value="Mumbai">Mumbai</option>
											<option value="Nagpur">Nagpur</option>
											<option value="Nashik">Nashik</option>
											<option value="Navi Mumbai">Navi Mumbai</option>
											<option value="Raigad">Raigad</option>
											<option value="Thane">Thane</option>
											<option value="Chandrapur">Chandrapur</option>
										</select>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<select class="select2" name="sro" id="sub_ro"
											data-placeholder="Select Sub Regional Office" required>
											<option value=""></option>
										</select>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="city"
											placeholder="City" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="state"
											value="Maharashtra" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="country"
											value="India" required> <i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="number" class="form-control" name="pincode"
											placeholder="Pincode" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="number" class="form-control" name="phoneNo"
											placeholder="Phone Number" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="number" class="form-control" name="fax"
											placeholder="Fax" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-12 mb-2">
									<h5 class="blockquote">Contact Person Details</h5>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="contPerName"
											placeholder="Contact Person Name" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="contPerDesig"
											placeholder="Contact Person Designation" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="number" class="form-control" name="contPerNo"
											placeholder="Contact Person Number" maxlength="10" required>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="email" class="form-control" name="email"
											placeholder="Contact Person's Email" required> <i
											class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-md-12 mb-2">
									<h5 class="blockquote">Industry Category</h5>
								</div>

								<div class="col-sm-6 col-md-6">
									<div class="form-group">
										<select class="select2" name="industryCategory"
											data-placeholder="Industry Category" required>
											<option value=""></option>
											<c:forEach items="${IndcatList }" var="ind_cat">
												<option value="${ind_cat.category}">${ind_cat.category}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-sm-6 col-md-6">
									<div class="form-group">
										<select class="select2" name="industryType"
											data-placeholder="Select Industry Type" required>
											<option value=""></option>
											<c:forEach items="${IndtypeList }" var="indtypelist">
												<option value="${indtypelist.type }">${indtypelist.type }</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-sm-6 col-md-6">
									<div class="form-group">
										<select class="select2" name="indPrimary"
											data-placeholder="Select Category as per given in Consent(Primary)"
											required>
											<option value=""></option>
											<c:forEach items="${Indprimarylist}" var="indprimary">
												<option value="${indprimary.primaryName}">${indprimary.primaryName}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-sm-6 col-md-6">
									<div class="form-group">
										<select class="select2" name="indSecondary"
											data-placeholder="Select scale as given in Consent (Secondary)"
											required>
											<option value=""></option>
											<c:forEach items="${Indsecondarylist}" var="indsecondary">
												<option value="${indsecondary.secondaryName}">${indsecondary.secondaryName}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-md-12 mb-2">
									<h5 class="blockquote">Other Information</h5>
								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control" name="yearEstb"
											placeholder="Year of Establishment" maxlength="4" required>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<div class="input-group" data-toggle="tooltip"
											data-placement="bottom"
											title="Date of Last Environmental Statement Report Submitted">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="zmdi zmdi-calendar"></i></span>
											</div>
											<input type="datetime-local"
												class="form-control hidden-md-up"> <input
												type="text" name="lastEnv"
												class="form-control date-picker hidden-sm-down"
												id="yearOfEst" required>
										</div>
									</div>
								</div>

								<div class="col-sm-3 col-md-3">
									<div class="form-group">
										<select class="select2" name="no_work_days"
											data-placeholder="Number of Working Days in a week" required>
											<option value=""></option>
											<c:forEach begin="1" end="7" varStatus="loop">
												<option value="${loop.index}">${loop.index}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-sm-3 col-md-3">
									<div class="form-group">
										<select class="select2" name="workingHour"
											data-placeholder="Working Hours" required>
											<option value=""></option>
											<c:forEach begin="1" end="24" varStatus="loop">
												<option value="${loop.index}">${loop.index}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-sm-12 col-md-12 text-center">
									<button type="reset" class="btn btn-light">Cancel</button>
									<button type="submit" class="btn btn-primary">
										<i class="zmdi zmdi-save"></i> &nbsp; Save
									</button>
								</div>

							</div>
						</form>
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
			src="newAssets/projectscripts/add-company.js"></script>
		<script type="text/javascript"
			src="newAssets/projectscripts/functions.js"></script>
		<script type="text/javascript"
			src="newAssets/projectscripts/common-functions.js"></script>
		<script>
			$("#create-company-form").validate();
		</script>
	</main>
</body>
</html>