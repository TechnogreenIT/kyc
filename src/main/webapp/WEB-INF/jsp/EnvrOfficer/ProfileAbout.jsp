<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Env Officer Profile</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="img/faviconkyc.png" type="image/gif"
	sizes="12x12">

<script src="jquery/tests/jquery-1.9.1.js"></script>
<script>
function uploadprofile(){
	$('#clientImage').trigger('click');
}
$("#basic_info").hide();
var specialKeys = new Array();
specialKeys.push(8);
function numberValidate(e) {
	alert("env");
    var keyCode = e.which ? e.which : e.keyCode;
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);

    var isnumber = ret ? "true " : "false";
    if (isnumber.indexOf("true") > -1) {
        return true;
    } else {
        $("#basic_info").show();
        e.preventDefault();
        $('#basic_info').delay(1500).fadeOut();
        return false;
    }

}
</script>
</head>
<body>
	<div id="basic_info" class="sweet-alert showSweetAlert visible"
		tabindex="-1" data-has-cancel-button="false"
		data-has-confirm-button="true" data-allow-ouside-click="false"
		data-has-done-function="false" data-timer="null"
		style="margin-top: -167px;">
		<div class="icon warning pulseWarning">
			<span class="body pulseWarningIns"></span> <span
				class="dot pulseWarningIns"></span>
		</div>
		<div class="icon custom"></div>
		<h2>Enter number only</h2>
	</div>
	<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
	<jsp:include page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />

	<section id="main"> <section id="content">
	<div class="container">
		<div class="card" id="profile-main">

			<div class="pm-overview c-overflow">
				<div class="pmo-pic">
					<div class="p-relative">
						<img class="img-responsive" src="profile-pics/male.png" alt=""
							width="512" height="512">
						<div class="dropdown pmop-message">
							<a data-toggle="dropdown" href="#"
								class="btn bgm-white btn-float z-depth-1"> <i
								class="zmdi zmdi-comment-text-alt"></i>
							</a>
							<form action="" method="post">
								<input type="hidden" name="id" value="id;">
								<div class="dropdown-menu">
									<textarea placeholder="Write something..." name="status"></textarea>
									<button class="btn bgm-green btn-float" name="profile_status">
										<i class="zmdi zmdi-mail-send"></i>
									</button>
								</div>
							</form>
						</div>

						<form action="uploadProfilepic" enctype="multipart/form-data"
							method="post" name="imgForm" id="imgForm">
							<input type="hidden" name="id" value="id value "> <input
								type="hidden" name="emp_name" value="employee_name;"> <input
								type="file" name="clientImage" id="clientImage"
								style="visibility: hidden;" accept="image/*"
								onchange="this.form.submit()" />
						</form>
						<a href="#" class="pmop-edit" onclick="uploadprofile();"> <i
							class="zmdi zmdi-camera"></i><span class="hidden-xs">Update
								Profile Picture </span></a>
					</div>
					<div class="pmo-stat">
						<h4 class="m-0 c-white">profile_status</h4>
					</div>
				</div>

				<div class="pmo-block pmo-contact hidden-xs">
					<h2></h2>
					<h2>Contact</h2>
					<ul>
						<li>No contact details available.</li>";

					</ul>
				</div>
			</div>

			<div class="pm-body clearfix">

				<div class="card-profile">
					<div class="title_profile">
						<b><font color="black" style="font-family: sans-serif">User
								Details</font></b>
					</div>
				</div>
				<div class="pmb-block">

					<div class="pmbb-header">
						<h2>
							<i class="zmdi zmdi-account m-r-5"></i><span
								style="color: #c19203;"> Basic Information</span>
						</h2>

						<ul class="actions">
							<li class="dropdown"><a href="..#" data-toggle="dropdown">
									<i class="zmdi zmdi-more-vert"></i>
							</a>

								<ul class="dropdown-menu dropdown-menu-right">
									<li><a data-pmb-action="edit" href="..#">Edit</a></li>
								</ul></li>
						</ul>
					</div>
					<div class="pmbb-body p-l-30">
						<div class="pmbb-view">
							<dl class="dl-horizontal">
								<dt>Full Name</dt>
								<dd>employee_name</dd>
							</dl>
							<dl class="dl-horizontal">
								<dt>Gender</dt>
								<dd>gender</dd>
							</dl>
							<dl class="dl-horizontal">
								<dt>Birthday</dt>
								<dd>birthday</dd>
							</dl>
							<dl class="dl-horizontal">
								<dt>Martial Status</dt>
								<dd>martial_status</dd>
							</dl>
							<dl class="dl-horizontal">
								<dt>Designation</dt>
								<dd>cont_per_desig</dd>
							</dl>
						</div>

						<div class="pmbb-edit">
							<form action="" method="post">
								<input type="hidden" name="id" value="id">
								<dl class="dl-horizontal">
									<dt class="p-t-10">Full Name</dt>
									<dd>
										<div class="fg-line">
											<input type="text" class="form-control" name="emp_name"
												value="employee_name">
										</div>

									</dd>
								</dl>
								<dl class="dl-horizontal">
									<dt class="p-t-10">Gender</dt>
									<dd>
										<div class="fg-line">
											<select class="chosen" name="gender">
												<option>Male</option>
												<option>Female</option>
												<option>Other</option>
											</select>
										</div>
									</dd>
								</dl>
								<dl class="dl-horizontal">
									<dt class="p-t-10">Birthday</dt>
									<dd>
										<div class="dtp-container dropdown fg-line">
											<input type='text' class="form-control date-picker"
												data-toggle="dropdown" name="dob" value="$birthday; ">
										</div>
									</dd>
								</dl>
								<dl class="dl-horizontal">
									<dt class="p-t-10">Martial Status</dt>
									<dd>
										<div class="fg-line">
											<select class="chosen" name="martial_status">
												<option>Single</option>
												<option>Married</option>
												<option>Other</option>
											</select>
										</div>
									</dd>
								</dl>
								<dl class="dl-horizontal">
									<dt class="p-t-10">Designation</dt>
									<dd>
										<div class="fg-line">
											<select class="chosen" name="designation">
												<option value="Environmental Officer">Environmental
													Officer</option>
												<option value="Third Party">Third Party</option>
												<option value="Management">Management</option>

											</select>
										</div>
									</dd>
								</dl>
								<div class="m-t-30">
									<button class="btn btn-sm"
										style="background-color: #ecb50e; color: white;"
										name="basic_info">Save</button>
									<button data-pmb-action="reset" class="btn btn-link btn-sm">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="pmb-block">
					<div class="pmbb-header">
						<h2>
							<i class="zmdi zmdi-phone m-r-5"></i><span
								style="color: #c19203;"> Contact Information</span>
						</h2>

						<ul class="actions">
							<li class="dropdown"><a href="..#" data-toggle="dropdown">
									<i class="zmdi zmdi-more-vert"></i>
							</a>

								<ul class="dropdown-menu dropdown-menu-right">
									<li><a data-pmb-action="edit" href="..#">Edit</a></li>
								</ul></li>
						</ul>
					</div>
					<div class="pmbb-body p-l-30">
						<div class="pmbb-view">
							<dl class="dl-horizontal">
								<dt>Address</dt>
								<dd>$address; $address2; ,$address3;.</dd>
							</dl>
							<dl class="dl-horizontal">
								<dt>Mobile Phone</dt>
								<dd>cont_per_no;</dd>
							</dl>
							<dl class="dl-horizontal">
								<dt>Email Address</dt>
								<dd>$email;</dd>
							</dl>
						</div>

						<div class="pmbb-edit">
							<form action="" method="post">
								<input type="hidden" name="id" value="$id;">
								<dl class="dl-horizontal">
									<dt class="p-t-10">Address</dt>
									<dd>
										<div class="fg-line">
											<input type="text" class="form-control" name="address"
												value="$address;">
										</div>
										<div class="fg-line">
											<input type="text" class="form-control" name="address2"
												value="$address2;">
										</div>
										<div class="fg-line">
											<input type="text" class="form-control" name="address3"
												value="$address3;" onkeypress="return checkNum()">
										</div>
									</dd>
								</dl>
								<dl class="dl-horizontal">
									<dt class="p-t-10">Mobile Phone</dt>
									<dd>
										<div class="fg-line">
											<input type="text" class="form-control" name="cont_per_no"
												onkeypress="numberValidate(event)" maxlength="10"
												minlength="10" pattern="[789][0-9]{9}"
												value=" $cont_per_no; ">
										</div>
									</dd>
								</dl>
								<dl class="dl-horizontal">
									<dt class="p-t-10">Email Address</dt>
									<dd>
										<div class="fg-line">
											<input type="email" class="form-control" name="email"
												value=" $email;">
										</div>
									</dd>
								</dl>

								<div class="m-t-30">
									<button class="btn  btn-sm"
										style="background-color: #ecb50e; color: white;"
										name="contact_info">Save</button>
									<button data-pmb-action="reset" class="btn btn-link btn-sm">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section> </section>
	<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />
</body>
</html>