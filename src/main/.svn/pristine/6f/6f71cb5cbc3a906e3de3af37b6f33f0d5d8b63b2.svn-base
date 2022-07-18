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
<title>Admin Dashboard | KYC</title>
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
		<jsp:include page="../NewCommon/admin-menus.jsp" />
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner">
				<div class="row todo">
					<!-- todo div start-->
					<div class="col-md-6">
						<div class="card todo-height">
							<div class="toolbar toolbar--inner">
								<div class="toolbar__label">To do List</div>
								<div class="actions">
									<button class="btn btn-primary btn-todo zmdi zmdi-plus mr-2"
										onclick="makeTodoModal();"></button>
									<li class="actions__item zmdi zmdi-help-outline mt-2"
										data-toggle="tooltip" data-placement="top"
										data-original-title="Todo List"></li>
								</div>
							</div>
							<div class="todo_div listview listview--bordered todo-scroll">
								<!-- all todo will be appended here -->
							</div>
						</div>
					</div>
					<!-- todo div end-->
					<!-- video div start-->
					<div class="col-md-6 ">
						<div class="card">
							<div class="card-header mdb-color lighten-1">
								<center>
									<h3 class="text-white">KYC INTRODUCTION</h3>
								</center>
							</div>
							<div class="card-body">
								<video width="100%" height="310" controls playsinline autoplay>
									<source src='../newAssets/LogoVideo/111.mp4' type='video/mp4'>
								</video>
							</div>
						</div>
					</div>
					<!-- video div end-->
				</div>
				<!-- row end -->
				<!-- row start -->
				<div class="row groups">

					<div class="col-xl-2 col-lg-3 col-sm-4 col-6 offset-md-4">
						<a href="view-company-profile" data-toggle="tooltip"
							data-placement="top" title=""
							data-original-title="Company Profile">
							<div class="groups__item grey lighten-3">
								<img src="../newAssets/img/custom/fill-consent.png"
									alt="fill-consent" width="120px" height="120px" class="img_hvr">
								<div class="groups__info">
									<strong>Company Profile</strong> <small>Create/View
										Company Profile</small>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xl-2 col-lg-3 col-sm-4 col-6">
						<a href="view-users" data-toggle="tooltip" data-placement="top"
							title="" data-original-title="User Profiles">
							<div class="groups__item grey lighten-3">
								<img src="../newAssets/img/custom/view-users.png"
									alt="User Profiles" width="120px" height="120px"
									class="img_hvr">
								<div class="groups__info">
									<strong>User Profiles</strong> <small>Create/View Users</small>
								</div>
							</div>
						</a>
					</div>

				</div>
				<!-- row end   -->
			</div>
			<!-- inner container div end -->
			<!-- include common footer start-->
			<jsp:include page="../NewCommon/common-footer.jsp" />
			<!-- include common footer end-->
			</div>
		</section>
	</main>
	<!-- Older IE warning message -->
	<!--[if IE]>
      <div class="ie-warning">
         <h1>Warning!!</h1>
         <p>You are using an outdated version of Internet Explorer, please upgrade to any of the following web browsers to access this website.</p>
         <div class="ie-warning__downloads">
            <a href="http://www.google.com/chrome">
            <img src="img/browsers/chrome.png" alt="">
            </a>
            <a href="https://www.mozilla.org/en-US/firefox/new">
            <img src="img/browsers/firefox.png" alt="">
            </a>
            <a href="http://www.opera.com">
            <img src="img/browsers/opera.png" alt="">
            </a>
            <a href="https://support.apple.com/downloads/safari">
            <img src="img/browsers/safari.png" alt="">
            </a>
            <a href="https://www.microsoft.com/en-us/windows/microsoft-edge">
            <img src="img/browsers/edge.png" alt="">
            </a>
            <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
            <img src="img/browsers/ie.png" alt="">
            </a>
         </div>
         <p>Sorry for the inconvenience!</p>
      </div>
      <![endif]-->

	<!-- include common css start-->
	<jsp:include page="../NewCommon/common-javascript.jsp" />
	<!-- include common css end-->

	<c:if test="${companyCount == '1'}">
		<script src="../newAssets/projectscripts/todo.js"></script>
	</c:if>

</body>
</html>
