<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <title>Modify Daily Data Request</title>
      <!-- include common css start-->
      <jsp:include page="../NewCommon/common-css.jsp" />
      <!-- include common css end-->
      <style media="screen">
      </style>
   </head>
  <body data-ma-theme="blue" class="body-bg" style="background-image: url('../newAssets/img/custom/data-science-bg.png') !important;background-size: cover !important;background-repeat: no-repeat !important;background-attachment: fixed !important;">
      <main class="main">
         <div class="page-loader">
            <div class="page-loader__spinner">
               <svg viewBox="25 25 50 50">
                  <circle cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
               </svg>
            </div>
         </div>
         <jsp:include page="../NewCommon/common-header.jsp" />
         <!-- include menus here start-->
         <c:if test="${userRole == 'Environmental Officer'}">
            <jsp:include page="../NewCommon/env-menus.jsp" />
         </c:if>
         <c:if test="${userRole == 'Management'}">
            <jsp:include page="../NewCommon/management-menus.jsp" />
         </c:if>
         <!-- include menus here end-->
      <section class="content content--full">
         <!-- inner container div start -->
         <div class="content__inner top-thick-border grey lighten-4">
            <!-- breadcrumb start-->
            <div class="breadcrumb_container ml-4">
               <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="#">${userRole}</a></li>
                  <li class="breadcrumb-item"><a href="#">Daily Data</a></li>
				  <li class="breadcrumb-item active"><a href="#">Modify Daily Data  Request</a></li>
               </ol>
            </div>
            <!-- breadcrumb end -->
            <!-- main container start -->
            <div class="container">
               <blockquote class="blockquote">
                  <h5 class="mb-0">Modify Daily Data  Request</h5>
               </blockquote>
               <!--  <div  class="card">
					<div class="card-body"">
					 </div>
               </div> -->
               <div id="accordionStack" class="bs-example">
						<h3>Production</h3>
						<div id="dataProduct"></div>
						<h3>Air</h3>
						<div id="dataAir"></div>

						<h3>Water</h3>
						<div id="dataWater"></div>

						<h3>Solid Waste</h3>
						<div id="dataSoild"></div>
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
      <!-- Vendors: Data tables -->
        <script src="../newAssets/vendors/datatables/jquery.dataTables.min.js"></script>
         <script src="../newAssets/vendors/datatables-buttons/dataTables.buttons.min.js"></script>
         <script src="../newAssets/vendors/datatables-buttons/buttons.print.min.js"></script>
         <script src="../newAssets/vendors/jszip/jszip.min.js"></script>
         <script src="../newAssets/vendors/datatables-buttons/buttons.html5.min.js"></script>
         
         
         
         <script src="../newAssets/projectscripts/ModifyDailyDataRequestJs.js"></script>
	 <script>
	   $(document).ready(function() {
		      $("#accordionStack").accordion({
		        heightStyle: 'content',
		        collapsible: true
		      });
		    });
         </script>
   </body>
</html>