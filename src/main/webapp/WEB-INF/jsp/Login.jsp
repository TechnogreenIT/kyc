<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>KYC - LOGIN</title>
<!-- include common css start-->
<link rel="icon" href="newAssets/img/faviconkyc.png" type="image/gif"
	sizes="12x12">

<!-- Vendor styles -->
<link rel="stylesheet"
	href="newAssets/vendors/material-design-iconic-font/css/material-design-iconic-font.min.css">
<!-- fontawesome -->
<link rel="stylesheet"
	href="newAssets/vendors/fontawesome/css/all.min.css">

<!-- App styles -->
<link rel="stylesheet" href="newAssets/css/app.min.css">

<!-- Custom styles -->
<link rel="stylesheet" href="newAssets/css/custom.css">

<link rel="stylesheet" href="newAssets/css/theme-blue.css">
<link rel="stylesheet" href="newAssets/css/theme-black.css">
<link rel="stylesheet" href="newAssets/css/theme-green.css">

<style>
.password-icon {
	margin-top: 11px;
	position: absolute;
	margin-left: 100px;
	margin-left: 116px;
}
</style>
</head>

<body data-ma-theme="blue">
	<form action="login" method="post">
		<div class="login">

			<div class="login__block" id="l-login">
				<div class="login__block__header">
					<i class="zmdi zmdi-account-circle"></i> Hi ${tenantHeader} !
					Please Sign in <input type="hidden" id="tenantHeaderId"
						value="${tenantHeader}" />
					<div class="actions actions--inverse login__block__actions">
						<div class="dropdown">
							<i data-toggle="dropdown"
								class="zmdi zmdi-more-vert actions__item"></i>

							<div class="dropdown-menu dropdown-menu-right">
								<a class="dropdown-item" data-ma-action="login-switch"
									data-ma-target="#l-set-tenant" href="">Set Company</a>
							</div>
						</div>
					</div>
				</div>

				<div class="login__block__body">
					<div class="form-group form-group--float form-group--centered">
						<input type="text" class="form-control" name="username"> <label>Email
							Address</label> <i class="form-group__bar"></i>
					</div>
					<div class="form-group form-group--float form-group--centered">
						<span id="view-password" class="input-group-addon"><i
							class="fa fa-eye password-icon cursor-pointer" aria-hidden="true"></i></span> <input
							type="password" class="form-control" name="password"
							id="input-password"> <label>Password</label> <i
							class="form-group__bar"></i>
					</div>
					<button type="submit" id="loginMe" name="submit"
						class="btn btn--icon login__block__btn">
						<i class="zmdi zmdi-long-arrow-right"></i>
					</button>
				</div>
			</div>
	</form>
	<!-- SET tenant -->
	<div class="login__block active" id="l-set-tenant">
		<div class="login__block__header palette-Purple bg">
			<i class="zmdi zmdi-account-circle"></i> Set Company
			<div class="actions actions--inverse login__block__actions">
				<div class="dropdown">
					<i data-toggle="dropdown" class="zmdi zmdi-more-vert actions__item"></i>

					<div class="dropdown-menu dropdown-menu-right">
						<a id="goToLogin" class="dropdown-item"
							data-ma-action="login-switch" data-ma-target="#l-login" href="">Login</a>
					</div>
				</div>
			</div>
		</div>

		<div class="login__block__body">

			<div class="form-group form-group--float form-group--centered">
				<input type="text" id="tanent_field" class="form-control"> <label>Secret
					Key</label> <i class="form-group__bar"></i>
			</div>

			<button onclick="setTenant()" class="btn btn--icon login__block__btn">
				<i class="zmdi zmdi-check"></i>
			</button>
		</div>
	</div>

	<!-- Javascript -->
	<!-- Vendors -->
	<script src="newAssets/vendors/jquery/jquery.min.js"></script>
	<script src="newAssets/vendors/popper.js/popper.min.js"></script>
	<script src="newAssets/vendors/bootstrap/js/bootstrap.min.js"></script>

	<!-- App functions and actions -->
	<script src="newAssets/js/app.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			var tenantHeaderValue = $("#tenantHeaderId").val();
			if (tenantHeaderValue != "") {
				$("#goToLogin").click();
			}
		});

		function setTenant() {
			var tanent = $('#tanent_field').val();
			$.ajax({
				url : 'ajax-tanent1',
				beforeSend : function(xhr) {
					xhr.setRequestHeader("X-TenantID", tanent);
				},
				success : function(data) {
					console.log("tenant set ..");
					location.reload();
				}
			});

		}
		$('#view-password').click(function(e) {
			$password = $('#input-password');
			if ($password.prop('type') == 'password') {
				$password.prop('type', 'text');
			} else {
				$password.prop('type', 'password');
			}
			$('i', this).toggleClass('fa-eye-slash');
		});

		var myTheme = "";

		$(document).ready(function() {

			$('body').on('change', '.theme-switch input:radio', function() {
				var theme = $(this).val();
				localStorage.setItem('kyc-theme', theme);
				$('body').attr('data-ma-theme', theme);
			});

			var myTheme = localStorage.getItem('kyc-theme');
			if (myTheme == "" || myTheme == null || myTheme == undefined) {
				myTheme = "blue";
			}
			setKYCTheme(myTheme);
		});

		function setKYCTheme(myTheme) {
			$('body').attr('data-ma-theme', myTheme);
		}
	</script>
</body>
</html>