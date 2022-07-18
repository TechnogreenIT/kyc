<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ThirdParty | Dashboard</title>
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
		<c:if test="${userRole == 'admin'}">
			<jsp:include page="../NewCommon/admin-menus.jsp" />
		</c:if>
		<c:if test="${userRole == 'Environmental Officer'}"><jsp:include
				page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" /></c:if>
		<c:if test="${userRole == 'Management'}"><jsp:include
				page="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" /></c:if>
		<c:if test="${userRole == 'Third Party'}"><jsp:include
				page="../CommonWebpages/MenusWebpages/TpMenus.jsp" /></c:if>
		<!-- include menus here end-->

		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container">
					<ol class="breadcrumb ml-4">
						<li class="breadcrumb-item active"><a href="#">ThirdParty
								Dashboard</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<div class="row todo">
					<!-- todo div start-->
					<div class="col-md-6">
						<div class="card todo-height">
							<div
								class="card-header toolbar toolbar--inner mdb-color lighten-1">
								<div class="toolbar__label">
									<h3 class="text-white">TO DO LIST</h3>
								</div>
								<div class="actions">
									<button class="btn btn-primary btn-todo zmdi zmdi-plus mr-2"
										data-toggle="modal" data-target="#modal-new-todo"></button>
									<li class="actions__item zmdi zmdi-help-outline mt-2"
										data-toggle="tooltip" data-placement="top"
										data-original-title="Todo List"></li>
								</div>
							</div>
							<div class="todo_div listview listview--bordered todo-scroll">
								<!-- all todo will be appended here -->

								<!-- <a href="todos.html" class="view-more">View More</a> -->
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
					<div class="col-xl-2 col-lg-3 col-sm-4 col-6 offset-md-5">
						<a href="monitoring-add" data-toggle="tooltip"
							data-placement="top" title=""
							data-original-title="Insert Monitoring Form">
							<div class="groups__item grey lighten-3">
								<img src="../newAssets/img/custom/fill-consent.png"
									alt="Insert Monitoring Form" width="120px" height="120px"
									class="img_hvr">
								<div class="groups__info">
									<strong>Insert Monitoring Form</strong>
								</div>
							</div>
						</a>

					</div>
				</div>
				<!-- row end -->


			</div>
			<!-- inner container div end -->


			<!-- include common footer start-->
			<jsp:include page="../NewCommon/common-footer.jsp" />
			<!-- include common footer end-->


			</div>
		</section>
	</main>
	<!-- to do modal start -->
	<div class="modal fade show" id="modal-new-todo" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">New Todo List</h5>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input type="text" id="newTodoText" class="form-control"
							placeholder="What do you want to do?">
					</div>
					<div class="form-group select2-parent">
						<select class="select2">
							<option>Select a Label</option>
							<option>#Messages</option>
							<option>#Clients</option>
							<option>#Server</option>
							<option>#Marketing</option>
							<option>#Work Related</option>
							<option>#Website</option>
						</select>
					</div>
					<div class="form-group mb-0">
						<label>Set Priority</label>
						<div class="btn-group btn-group-toggle btn-group-justified"
							data-toggle="buttons">
							<label class="btn active"> <input type="radio"
								name="options" id="option1" autocomplete="off" checked>
								Low
							</label> <label class="btn"> <input type="radio" name="options"
								id="option2" autocomplete="off"> Moderate
							</label> <label class="btn"> <input type="radio" name="options"
								id="option3" autocomplete="off"> High
							</label>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-success btn--icon-text" onclick="savedata()">
						<i class="zmdi zmdi-save"></i> Save
					</button>
					<button class="btn btn-warning btn--icon-text" data-dismiss="modal">
						<i class="zmdi zmdi-close"></i> Close
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- add to do modal end -->
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

	<!-- TODO -->
	<script src="../newAssets/projectscripts/todo.js"></script>


</body>
</html>
