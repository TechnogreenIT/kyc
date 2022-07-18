<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>KYC - WELCOME</title>
<link rel="stylesheet"
	href="newAssets/vendors/material-design-iconic-font/css/material-design-iconic-font.min.css">
<!-- App styles -->
<link rel="stylesheet" href="newAssets/css/app.min.css">
<!-- Custom styles -->
<link rel="stylesheet" href="newAssets/css/custom.css">
<style type="text/css">
ul {
	list-style-type: none;
}

ul li {
	padding: 4px;
}

.btnn {
	right: 25px;
	border: none;
	display: block;
	text-align: center;
	cursor: pointer;
	outline: none;
	overflow: hidden;
	position: relative;
	color: #fff;
	font-weight: 400;
	font-size: 14px;
	background-color: #222;
	height: 50px;
	width: 140px;
	padding: 17px;
	margin: 0;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.20);
}

.btnn span {
	position: relative;
	z-index: 1;
}

.btnn:after {
	content: "";
	position: absolute;
	left: 0;
	top: 0;
	height: 150%;
	width: 140%;
	background: #78C7D2;
	-webkit-transition: all .5s ease-in-out;
	transition: all .5s ease-in-out;
	-webkit-transform: translateX(-98%) translateY(-25%) rotate(45deg);
	transform: translateX(-98%) translateY(-25%) rotate(45deg);
}

.btnn:hover:after {
	-webkit-transform: translateX(-9%) translateY(-25%) rotate(45deg);
	transform: translateX(-9%) translateY(-25%) rotate(45deg);
}

.button1b-content {
	font-family: Helvetica;
	font-size: 15px;
	font-weight: bold;
	line-height: 50px;
	color: rgba(255, 255, 255, 1);
	width: 150px;
	position: relative;
	transition: all 1s;
	left: 150px;
	height: 55px;
}

.fancy {
	position: relative;
	background: linear-gradient(120deg, #CCF403 50%, #62EAFF 50%);
	padding: 2rem;
	height: 350px;
	text-align: center;
}

/* .fancy li{

	  width: 100%;
	  text-align: center; 
	  display: table;
	   float: left;   
} */
#slideshow {
	position: relative;
	height: 350px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
}

#slideshow>div {
	position: absolute;
	width: 400px;
	height: 350px;
	text-align: center;
}
</style>
</head>
<body data-ma-theme="blue" class="body-bg"
	style="background-image: url('newAssets/img/custom/data-science-bg.png') !important; background-size: cover !important; background-repeat: no-repeat !important; background-attachment: fixed !important;">
	<div class="container mt-5">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-4">
						<img src="../newAssets/categoryImg/8.png" height="150px">
					</div>
					<div class="col-8">
						<div class="card">
							<div class="card-body">
								<div class="row">
									<div class="col-9" id="fancy">
										<div class="card">
											<div class="fancy" id="slideshow">
												<div class="dept">
													<b>Welcome</b>
												</div>
												<div id="Construction" class="dept" style="display: none">
													<img src='newAssets/categoryImg/crane.png'>
													<li>Residential / Construction</li>
												</div>
												<div id="Industry" class="dept" style="display: none">
													<img src="newAssets/categoryImg/industry.png">
													<li>Canteen with Cooking</li>
													<li>Canteen without Cooking</li>
													<li>Industry</li>
												</div>
												<div id="School" class="dept" style="display: none">
													<img src='newAssets/categoryImg/school.png'>
													<li>Schools with Hostels</li>
													<li>Schools without Hostels</li>
												</div>
												<div id="Commercial" class="dept" style="display: none">
													<img src="newAssets/categoryImg/skyline.png">
													<li>Offices</li>
													<li>Cinemas,concert halls and theatres</li>
												</div>
												<div id="Hospitals" class="dept" style="display: none">
													<img src="newAssets/categoryImg/hospital.png">
													<li>Hospital (Including Laundry)</li>
													<li>Up to 100 beds</li>
													<li>More than 100 beds</li>
													<li>Nurses homes and medical quarters</li>
												</div>
												<div id="Hotels" class="dept" style="display: none">
													<img src="newAssets/categoryImg/meal.png">
													<li>Hotels with Lodging Facility</li>
													<li>Hotels without Lodging Facility</li>
													<li>Up to 4 Star</li>
													<li>5 Star & above</li>
												</div>
											</div>
										</div>
									</div>
									<div class="col-3">
										<ul>
											<li><button class="btnn"
													onclick="option('Construction')">
													<span>Construction</span>
												</button></li>
											<li><button class="btnn" onclick="option('Industry')">
													<span>Industry</span>
												</button></li>
											<li><button class="btnn" onclick="option('School')">
													<span>School/Colleges</span>
												</button></li>
											<li><button class="btnn" onclick="option('Commercial')">
													<span>Commercial</span>
												</button></li>
											<li><button class="btnn" onclick="option('Hospitals')">
													<span>Hospitals</span>
												</button></li>
											<li><button class="btnn" onclick="option('Hotels')">
													<span>Hotels</span>
												</button></li>
										</ul>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- Javascript -->
<!-- Vendors -->
<script src="newAssets/vendors/jquery/jquery.min.js"></script>
<script src="newAssets/vendors/popper.js/popper.min.js"></script>
<script src="newAssets/vendors/bootstrap/js/bootstrap.min.js"></script>
<!-- App functions and actions -->
<script src="newAssets/js/app.min.js"></script>
<script type="text/javascript">
	function option(task) {
		var i;
		var x = document.getElementsByClassName("dept");
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		var i = document.getElementById(task).style.display = "block";
	}

	$("#slideshow > div:gt(0)").hide();

	setInterval(function() {

		$("#slideshow > div:gt(0)").hide();
		$('#slideshow > div:first').fadeOut(1000).next().fadeIn(1000).end()
				.appendTo('#slideshow');
	}, 3000);
</script>
</html>