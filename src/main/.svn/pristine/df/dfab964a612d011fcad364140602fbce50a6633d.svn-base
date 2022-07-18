<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>${userRole}-user profile | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<!-- cropperJs -->
<link rel="stylesheet" href="../newAssets/vendors/cropperJs/croppie.css">

<style>
label.cabinet {
	display: block;
	cursor: pointer;
}

label.cabinet input.file {
	position: relative;
	height: 100%;
	width: auto;
	opacity: 0;
	-moz-opacity: 0;
	filter: progid: DXImageTransform.Microsoft.Alpha(opacity=0);
	margin-top: -30px;
}

#upload-demo {
	width: 250px;
	height: 250px;
	padding-bottom: 25px;
}

figure figcaption {
	position: absolute;
	bottom: 0;
	color: #fff;
	width: 100%;
	padding-left: 9px;
	padding-bottom: 5px;
	text-shadow: 0 0 10px #000;
}
</style>
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
		<c:if test="${userRole == 'Third Party'}"><jsp:include
				page="../CommonWebpages/MenusWebpages/TpMenus.jsp" /></c:if>
		<!-- include menus here end-->

		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item active"><a href="#">Profile</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<div class="content__inner content__inner--sm">
					<header class="content__title">
						<h1>
							<c:if test="${ !empty emplogindata}">${emplogindata.getEmployeeName()}</c:if>
						</h1>
						<small><c:if test="${ !empty emplogindata}">${emplogindata.getContPerDesig()}</c:if>
							at <c:if test="${ !empty emplogindata}">${emplogindata.getContPerDesig()}</c:if></small>
					</header>

					<div class="card profile">
						<div class="profile__img">
							<img
								src="data:image/jpeg;base64,<c:out value="${sessionScope.userProfilePic}"/>"
								id="img-user-profile" alt="img-user-profile"> <i
								onclick="openFp()"
								class="zmdi zmdi-camera profile__img__edit cursor-pointer">
							</i> <input type="file" id="myFile"
								class="item-img file center-block" name="image" accept="image/*"
								style="display: none" />
						</div>

						<div class="profile__info">
							<p>Cras mattis consectetur purus sit amet fermentum. Maecenas
								sed diam eget risus varius blandit sit amet non magnae tiam
								porta sem malesuada magna mollis euismod.</p>
							<ul class="icon-list">
								<li><i class="zmdi zmdi-phone"></i> <c:if
										test="${ !empty emplogindata}">${emplogindata.getContPerNo()}</c:if></li>
								<li><i class="zmdi zmdi-email"></i> <c:if
										test="${ !empty emplogindata}">${emplogindata.getEmail()}</c:if></li>
							</ul>
						</div>
					</div>
					<div class="card">
						<div class="card-body">
							<div class="tab-container">
								<ul class="nav nav-tabs" role="tablist">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#BasicInformation" role="tab">Basic
											Information</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#ContactInformation" role="tab">Contact Information</a>
									</li>
								</ul>
								<!-- Basic Information Tab start-->
								<div class="tab-content">
									<div class="tab-pane active fade show" id="BasicInformation"
										role="tabpanel">
										<form>
											<div class="row">
												<label class="col-sm-2 col-form-label">Full Name</label>
												<div class="col-sm-4">
													<div class="form-group">
														<input type="text" class="form-control"
															placeholder="Full Name"
															value="${emplogindata.employeeName}"> <i
															class="form-group__bar"></i>
													</div>
												</div>
											</div>
											<div class="row">
												<label class="col-sm-2 col-form-label">Birthday</label>
												<div class="col-sm-4">
													<div class="input-group">
														<div class="input-group-prepend">
															<span class="input-group-text"><i
																class="zmdi zmdi-calendar"></i></span>
														</div>
														<input type="date" class="form-control hidden-md-up"
															placeholder="Pick a date"> <input type="text"
															class="form-control date-picker hidden-sm-down"
															placeholder="Pick a date"
															value="${emplogindata.birthday}">
													</div>
												</div>
											</div>
											<div class="row">
												<label class="col-sm-2 col-form-label">Gender</label>
												<div class="col-sm-4">
													<div class="form-group">
														<div class="radio">
															<input type="radio" name="form-horizontal-radio"
																id="form-horizontal-radio-1"
																${emplogindata.gender=='Male'?'checked':'no'}> <label
																class="radio__label" for="form-horizontal-radio-1">Male</label>
															&nbsp; <span><i class="fas fa-male fa-lg"
																style="color: blue"></i></span>
														</div>
														<div class="radio">
															<input type="radio" name="form-horizontal-radio"
																id="form-horizontal-radio-2" ${emplogindata.gender}
																${emplogindata.gender=='Female'?'checked':''}> <label
																class="radio__label" for="form-horizontal-radio-2">Female</label>
															&nbsp; <span><i class="fas fa-female fa-lg"
																style="color: deeppink"></i></span>
														</div>
													</div>
												</div>
											</div>
											<%-- <div class="row">
                                       <label class="col-sm-2 col-form-label">Martial Status</label>
                                       <div class="col-sm-4">
                                          <div class="form-group">
                                             <div class="radio">
                                                <input type="radio" name="form-horizontal-radio1" id="form-horizontal-radio-11" ${emplogindata.gender=='Male'?'checked':'no'}>
                                                <label class="radio__label" for="form-horizontal-radio-11">Married</label>
                                             </div>
                                             <div class="radio">
                                                <input type="radio" name="form-horizontal-radio1" id="form-horizontal-radio-22">
                                                <label class="radio__label" for="form-horizontal-radio-22">Unmarried</label>
                                             </div>
                                          </div>
                                       </div>
                                    </div> --%>
											<div class="row">
												<label class="col-sm-2 col-form-label">Designation</label>
												<div class="col-sm-4">
													<div class="form-group">
														<input type="text" class="form-control"
															placeholder="Full Name"
															value="${emplogindata.contPerDesig}" disabled> <i
															class="form-group__bar"></i>
													</div>
												</div>
											</div>
											<button type="button" class="btn btn-primary">
												<span><i class="fas fa-edit"></i></span> &nbsp;Edit
											</button>
										</form>
									</div>
									<!-- Basic Information Tab end-->
									<!-- Contact Information Tab start-->
									<div class="tab-pane fade" id="ContactInformation"
										role="tabpanel">
										<form>
											<div class="row">
												<label class="col-sm-2 col-form-label">Address</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control"
															placeholder="Address"
															value="${emplogindata.address} ${emplogindata.address2} ${emplogindata.address3}">
														<i class="form-group__bar"></i>
													</div>
												</div>
											</div>
											<div class="row">
												<label class="col-sm-2 col-form-label">Mobile Phone</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="text" class="form-control"
															placeholder="Mobile Phone"
															value="${emplogindata.contPerNo}" maxlength="10">
														<i class="form-group__bar"></i>
													</div>
												</div>
											</div>
											<div class="row">
												<label class="col-sm-2 col-form-label">Email Address</label>
												<div class="col-sm-6">
													<div class="form-group">
														<input type="email" class="form-control"
															placeholder="Email Address" value="${emplogindata.email}">
														<i class="form-group__bar"></i>
													</div>
												</div>
											</div>
											<button type="button" class="btn btn-primary">
												<span><i class="fas fa-edit"></i></span> &nbsp;Edit
											</button>
										</form>
									</div>
									<!-- Contact Information Tab end-->
								</div>
							</div>
						</div>
					</div>
					<!-- update password card start-->
					<div class="card">
						<div class="card-body">
							<h4 class="card-body__title mb-4">Update Password</h4>
							<br>
							<div class="row">
								<label class="col-sm-3 col-form-label">Old Passwod</label>
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" onkeyup="checkOldPass(this);"
											class="form-control" placeholder="Old Passwod" required />
										<div></div>
										<i class="form-group__bar"></i>
									</div>
								</div>
							</div>
							<div class="row">
								<label class="col-sm-3 col-form-label">New Passwod</label>
								<div class="col-sm-6">
									<div class="form-group">
										<input type="password" onkeyup="ValidationEvent();"
											class="form-control" name="password" id="password"
											placeholder="New Passwod" disabled>
										<div></div>
										<i class="form-group__bar"></i>
									</div>
								</div>
							</div>
							<div class="row">
								<label class="col-sm-3 col-form-label">Confirm New
									Passwod</label>
								<div class="col-sm-6">
									<div class="form-group">
										<input type="password" onkeyup="ValidationEvent();"
											class="form-control" name="confirmpassword"
											id="confirmpassword" placeholder="Confirm New Passwod"
											disabled>
										<div></div>
										<i class="form-group__bar"></i>
									</div>
								</div>
							</div>
							<button type="button" onclick="saveNewPassword();"
								id="btn-changePass" value="Submit" class="btn btn-primary"
								disabled>
								<span><i class="fas fa-save"></i></span> &nbsp;Update Password
							</button>
						</div>
					</div>
					<!-- update password card end-->
				</div>

				<div class="modal fade" id="cropImagePop" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title pull-left">Crop Profile</h5>
							</div>
							<div class="modal-body">
								<center>
									<div id="upload-demo"></div>
								</center>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-link" id="cropImageBtn">Crop</button>
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>


				<!-- include common css start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common css end-->

			</div>
		</section>

		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->
		<!--  cropperJs -->
		<script src="../newAssets/vendors/cropperJs/croppie.js"></script>
		<script src="../newAssets/projectscripts/user-profile.js"></script>
		<script>
		
		  function addErrorMsg(el,error_msg,success_msg) {
			  if (el.value == "ok") {
				  $(el).addClass("is-valid").removeClass("is-invalid");
				  $(el).siblings('div').addClass('valid-feedback').removeClass("invalid-feedback").removeAttr("hidden").empty().text(success_msg);
			} else if (el.value == "" || el.value == null){
				$(el).removeClass("is-invalid").removeClass("is-valid");
				$(el).siblings('div').addAttr("hidden");
			} else {
				$(el).addClass("is-invalid").removeClass("is-valid");
				$(el).siblings('div').addClass('invalid-feedback').removeClass("valid-feedback").removeAttr("hidden").empty().text(error_msg);
			}
		    }
		
			function ValidationEvent() {
				var inpbtn = $("#btn-changePass");var inp1 = $("#password");var inp2 = $("#confirmpassword");
				if ($("#password").val() == $("#confirmpassword").val()) {
					$(inpbtn).prop("disabled", false);
					$(inp1).addClass("is-valid").removeClass("is-invalid");
					$(inp1).siblings('div').addClass('valid-feedback').removeClass("invalid-feedback").removeAttr("hidden").empty().text("matched !!");
					$(inp2).addClass("is-valid").removeClass("is-invalid");
					$(inp2).siblings('div').addClass('valid-feedback').removeClass("invalid-feedback").removeAttr("hidden").empty().text("matched !!");
				} else {
					$(inpbtn).prop("disabled", true);
					$(inp1).addClass("is-invalid").removeClass("is-valid");
					$(inp1).siblings('div').addClass('invalid-feedback').removeClass("valid-feedback").removeAttr("hidden").empty().text("password didnt matched");
					$(inp2).addClass("is-invalid").removeClass("is-valid");
					$(inp2).siblings('div').addClass('invalid-feedback').removeClass("valid-feedback").removeAttr("hidden").empty().text("password didnt matched");
					
				}
			}
			function checkOldPass(el){
				var oldpass = el.value;
				var inp1 = $("#password");var inp2 = $("#confirmpassword");
				$.ajax({
					type : "POST",
					url : "ajax-check-oldpassword",
					data: ({
						password:oldpass,
						type : "check"
		            }),
					success : function(data) {
						console.log("old pass :"+data);
						if (data == "true") {
							$(inp1).prop("disabled", false);$(inp2).prop("disabled", false);
							$(el).addClass("is-valid").removeClass("is-invalid");
							$(el).siblings('div').addClass('valid-feedback').removeClass("invalid-feedback").removeAttr("hidden").empty().text("matched !!");
						} else {
							$(inp1).prop("disabled", true);$(inp2).prop("disabled", true);
							$(el).addClass("is-invalid").removeClass("is-valid");
							$(el).siblings('div').addClass('invalid-feedback').removeClass("valid-feedback").removeAttr("hidden").empty().text("not matched !!!!");
						}
					}
				});
			}
			
			function saveNewPassword(){
				var newPass = $("#confirmpassword").val();
				$.ajax({
					type : "POST",
					url : "ajax-check-oldpassword",
					data: ({
						password:newPass,
						type : "save"
		            }),
					success : function(data) {
						successPopup("success","password changed succesfully !! Password sent to your linked mail address.");
					}
				});
			}
			
			function successPopup(title,msg){
                swal({
                    title: title,
                    text: msg,
                    type: 'success',
                    buttonsStyling: false,
                    confirmButtonClass: 'btn btn-primary'
                }).then(function() {
					location.reload();
				});
			}
		</script>
	</main>
</body>
</html>