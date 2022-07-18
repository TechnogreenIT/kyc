
<!-- CommonHeader -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="../img/faviconkyc.png" type="image/gif"
	sizes="12x12">
<title>KYCTracker</title>
<!-- Vendor CSS -->
<link
	href="vendors/bower_components/fullcalendar/dist/fullcalendar.min.css"
	rel="stylesheet">
<link href="vendors/bower_components/animate.css/animate.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css"
	rel="stylesheet">
<link href="vendors/bower_components/animate.css/animate.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/bootstrap-select/dist/css/bootstrap-select.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/nouislider/distribute/jquery.nouislider.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="vendors/farbtastic/farbtastic.css" rel="stylesheet">
<link href="vendors/bower_components/chosen/chosen.min.css"
	rel="stylesheet">
<link href="vendors/summernote/dist/summernote.css" rel="stylesheet">

<link href="vendors/jbox/jBox.all.min.css" rel="stylesheet">
<script src="../newAssets/vendors/jbox/jBox.all.min.js"></script>
<script src="../newAssets/vendors/jbox/custom-jbox.js"></script>

<!-- CSS -->
<link href="css/app.min.1.css" rel="stylesheet">
<link href="css/app.min.2.css" rel="stylesheet">
<link href="css/popup.css" rel="stylesheet">
<!-- <script src="jquery/tests/jquery-1.9.1.js"></script> -->
<script src="js/graphs/common.min.js"></script>

<!--  library for ajax loader -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.70/jquery.blockUI.js"></script>

<script src="vendors/notification/notify.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfobject/2.1.1/pdfobject.min.js"></script>

<script> 
  	$(window).load(function () {PDFObject.embed("../newAssets/documents/KYCManual.pdf", "#example1")});
  	var dontBlock = false;
	if(!dontBlock)
  	var ajaxBlock = function() { $.blockUI({message: '<img src="GIFLOADER/loder.gif" />',
  		css: { border: 'none',
            padding: '15px',
            backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'}}) }
  	$(document).ajaxStart(ajaxBlock).ajaxStop($.unblockUI); // call common loader for all ajax call
  	
  	</script>


<style>
.pdfobject-container {
	width: 500px;
	height: 800px;
}
</style>
</head>
<body style="overflow: auto; height: 100%;">

	<header id="header" class="clearfix" data-current-skin="cyan">

	<ul class="header-inner">

		<li id="menu-trigger" data-trigger="#sidebar">
			<div class="line-wrap">
				<div class="line top"></div>
				<div class="line center"></div>
				<div class="line bottom"></div>
			</div>
		</li>
		<li class="abcde1" style="background-color: white;"><img
			src="../img/8.png" width="90" height="50">
		<li>
		<li class="abcde" style="margin-top: 25px;">
			<center>
				&nbsp;<font size="4" color="#F7EBF0"> <c:choose>
						<c:when test="!empty ${companydetails.compName}">
					 ${companydetails.compName}
					 </c:when>
					</c:choose></font>
			</center>
		</li>

		<li class="logo hidden-xs"><a href="#"></a></li>

		<li class="pull-right">
			<ul class="top-menu">

				<li id="toggle-width">
					<div class="toggle-switch other-tooltip" data-toggle="tooltip"
						data-placement="left" data-html="true"
						title="Click here to hide or show menus">
						<input id="tw-switch" type="checkbox" hidden="hidden"> <label
							for="tw-switch" class="ts-helper"></label>
					</div>
				</li>
				<li class="dropdown" style="right: 10px;"><a
					data-toggle="dropdown"><img src="img/help2.png" width="30"
						height="30"></a>
					<ul class="dropdown-menu dm-icon pull-right"
						style="width: 500px; height: 755px; overflow: scroll; padding-left: 10px; font-style: bold;">
						<div id="example1"></div>
					</ul></li>

				<li><a href="logout" style="margin-top: 7px; font-size: 14px;"><i
						class="zmdi zmdi-power zmdi-hc-fw"></i> Logout</a></li>
			</ul>
		</li>
	</ul>


	<!-- Top Search Content -->
	<div id="top-search-wrap">
		<div class="tsw-inner">
			<i id="top-search-close" class="zmdi zmdi-arrow-left"></i> <input
				type="text">
		</div>
	</div>
	</header>
</body>
</html>
