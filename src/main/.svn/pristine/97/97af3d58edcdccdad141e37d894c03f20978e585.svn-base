<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>View User | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
<style media="screen">
</style>
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
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Admin</a></li>
						<li class="breadcrumb-item active"><a href="#">User
								Profiles</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<!-- main container start -->
				<div class="container">
					<blockquote class="blockquote">
						<h4 class="mb-0">Ms- Godavari Biorefineries Limited (Company
							Name)</h4>
					</blockquote>
					<!-- main card start-->
					<div class="card">
						<div class="card-body">
							<!-- div user info start -->
							<div class="listview listview--bordered">
								<!-- users info div start -->
								<div class="listview__item">
									<div class="listview__content">
										<div class="listview__heading">
											<strong>Note:Admin is the one who will "Create
												Company Profile", He will create minimum three users :</strong>
										</div>
										<br> <a href="#" class="badge purple darken-2 m-1"
											title="" data-toggle="tooltip" data-placement="right"
											data-original-title="Management is the one who will Review / Supervise the work. He will be able to view outputs of Hazardous waste Return, Waster Cess , ESR, statistics, performance and graph.">Management</a>
										<a href="#" class="badge teal darken-2 m-1" title=""
											data-toggle="tooltip" data-placement="right"
											data-original-title="Environmental Officer is the one who will fill the daily input data and will be able to see if any non-compliance is created by Industry . He will fill the Consent to Establish/Operate, Hazardous waste and daily input data.">Environmental
											Officer</a> <a href="#" class="badge green darken-3 m-1" title=""
											data-toggle="tooltip" data-placement="right"
											data-original-title="Third party will be the one who will upload the Monitoring details of Ambient Air and Water.">Third
											Party</a>
										<button class="btn btn-primary btn--icon-text float-right"
											data-toggle="modal" data-target="#modal-backdrop-ignore">
											<i class="zmdi zmdi-plus"></i> Add user
										</button>
									</div>
								</div>
								<!-- users info div end -->
								<!-- div view users start -->
								<div class="listview__item">
									<div class="listview__content">
										<!-- table start -->
										<table id="user-data-table" class="table table-bordered">
											<thead>
												<tr>
													<th>ID</th>
													<th>Name</th>
													<th>Designation</th>
													<th>Status</th>
													<th>#</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${userlist}" var="u" varStatus="status">
													<tr>
														<td id="empDataId">${u.empDataId}</td>
														<td>${u.employeeName}</td>
														<td>${u.contPerDesig}</td>
														<td>
															<div class="btn-group btn-group-toggle"
																data-toggle="buttons">
																<c:choose>
																	<c:when test="${usersstatus[status.index]==true}">
																		<label class="btn active"
																			onclick="changeStatus('${u.empDataId}','Active')">
																			<input type="radio" name="userStatus"
																			autocomplete="off" value="Active" checked="">
																			Active
																		</label>
																		<label class="btn"
																			onclick="changeStatus('${u.empDataId}','Deactive')">
																			<input type="radio" name="userStatus"
																			autocomplete="off" value="Deactive"> Deactive
																		</label>
																	</c:when>
																	<c:when test="${usersstatus[status.index]==false}">
																		<label class="btn"
																			onclick="changeStatus('${u.empDataId}','Active')">
																			<input type="radio" name="userStatus"
																			autocomplete="off" value="ON"> Active
																		</label>
																		<label class="btn active"
																			onclick="changeStatus('${u.empDataId}','Deactive')">
																			<input type="radio" name="userStatus"
																			autocomplete="off" value="OFF" checked="">
																			Deactive
																		</label>
																	</c:when>
																</c:choose>
															</div>
														</td>
														<td><button class="btn btn-warning btn--icon-text"
																onclick="sendMail('${u.empDataId}')">
																<i class="zmdi zmdi-email"></i> Send Mail
															</button></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<!-- table end -->
									</div>
								</div>
								<!-- div view users start -->
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
		<!-- form modals -->
		<div class="modal fade" id="modal-backdrop-ignore"
			data-backdrop="static" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title pull-left">Create KYC user</h5>
					</div>
					<div class="modal-body">
						<form name="comp_cre" id="comp_cre" method="post" role="form"
							action="save-user">
							<div class="form-group">
								<input type="text" class="form-control" name="employeeName"
									placeholder="Employee Name" required> <i
									class="form-group__bar"></i>
							</div>
							<div class="form-row">
								<div class="col-12">
									<p class="card-title">Address</p>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<input type="text" class="form-control" name="address"
											placeholder="Plot No." required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<input type="text" class="form-control" name="address2"
											placeholder="Street" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<input type="text" class="form-control" name="address3"
											placeholder="City" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-group">
										<select class="select2 select2insidemodal" name="contPerDesig"
											id="contPerDesig" data-placeholder="Designation">
											<option value=""></option>
											<option value="Management">Management</option>
											<option value="Environmental Officer">Environmental
												Officer</option>
											<option value="Third Party">Third Party</option>
											<option value="Super Admin">Super Admin</option>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="contPerNo"
											placeholder="Contact No." required> <i
											class="form-group__bar"></i>
									</div>
								</div>
							</div>
							<div class="form-group">
								<input type="email" class="form-control" name="email"
									placeholder="Email" required> <i
									class="form-group__bar"></i>
							</div>
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="userName"
											placeholder="Username" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="password" class="form-control" name="password"
											pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
											title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
											placeholder="Password" autocomplete="off" required> <i
											class="form-group__bar"></i>
									</div>
								</div>
							</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-success btn--icon-text">
							<i class="zmdi zmdi-save"></i> Create User
						</button>
						<button class="btn btn-warning btn--icon-text"
							data-dismiss="modal">
							<i class="zmdi zmdi-close"></i> Close
						</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		</main>
		<!-- form modals -->
		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->
		<!-- Vendors: Data tables -->
		<script src="../newAssets/vendors/datatables/jquery.dataTables.min.js"></script>
		<script
			src="../newAssets/vendors/datatables-buttons/dataTables.buttons.min.js"></script>
		<script
			src="../newAssets/vendors/datatables-buttons/buttons.print.min.js"></script>
		<script src="../newAssets/vendors/jszip/jszip.min.js"></script>
		<script
			src="../newAssets/vendors/datatables-buttons/buttons.html5.min.js"></script>
		<script type="text/javascript">
      $(".select2insidemodal").select2({
    	    dropdownParent: $("#modal-backdrop-ignore")
    	  });
	  
      if ($('#user-data-table')[0]) {

      	// Add custom buttons
      	var dataTableButtons = '<div class="dataTables_buttons hidden-sm-down actions">' +
      		'<span class="actions__item zmdi zmdi-print black-color" data-table-action="print" />' +
      		'<span class="actions__item zmdi zmdi-fullscreen black-color" data-table-action="fullscreen" />' +
      		'<div class="dropdown actions__item">' +
      		'<i data-toggle="dropdown" class="zmdi zmdi-download black-color" />' +
      		'<ul class="dropdown-menu dropdown-menu-right">' +
      		'<a href="" class="dropdown-item" data-table-action="excel">Excel (.xlsx)</a>' +
      		'<a href="" class="dropdown-item" data-table-action="csv">CSV (.csv)</a>' +
      		'</ul>' +
      		'</div>' +
      		'</div>';

      	// Initiate data-table
      	$('#user-data-table').DataTable({
      		responsive: true,
      		"pageLength": 5,
      		lengthMenu: [
      			[5, 10, 20, -1],
      			['5 Rows', '10 Rows', '20 Rows', 'Everything']
      		],
      		language: {
      			searchPlaceholder: "Search for records..."
      		},
      		"sDom": '<"dataTables__top"lfB>rt<"dataTables__bottom"ip><"clear">',
      		buttons: [{
      				extend: 'excelHtml5',
      				title: 'Export Data'
      			},
      			{
      				extend: 'csvHtml5',
      				title: 'Export Data'
      			},
      			{
      				extend: 'print',
      				title: 'Material Admin'
      			}
      		],
      		"initComplete": function (settings, json) {
      			$(this).closest('.dataTables_wrapper').find('.dataTables__top').prepend(dataTableButtons);
      		}
      	});


      	// Add blue line when search is active
      	$('.dataTables_filter input[type=search]').focus(function () {
      		$(this).closest('.dataTables_filter').addClass('dataTables_filter--toggled');
      	});

      	$('.dataTables_filter input[type=search]').blur(function () {
      		$(this).closest('.dataTables_filter').removeClass('dataTables_filter--toggled');
      	});


      	// Data table buttons
      	$('body').on('click', '[data-table-action]', function (e) {
      		e.preventDefault();

      		var exportFormat = $(this).data('table-action');

      		if (exportFormat === 'excel') {
      			$(this).closest('.dataTables_wrapper').find('.buttons-excel').trigger('click');
      		}
      		if (exportFormat === 'csv') {
      			$(this).closest('.dataTables_wrapper').find('.buttons-csv').trigger('click');
      		}
      		if (exportFormat === 'print') {
      			$(this).closest('.dataTables_wrapper').find('.buttons-print').trigger('click');
      		}
      		if (exportFormat === 'fullscreen') {
      			var parentCard = $(this).closest('.card');

      			if (parentCard.hasClass('card--fullscreen')) {
      				parentCard.removeClass('card--fullscreen');
      				$('body').removeClass('data-table-toggled');
      			} else {
      				parentCard.addClass('card--fullscreen')
      				$('body').addClass('data-table-toggled');
      			}
      		}
      	});
      }

      function changeStatus(empDataId, currentStatus) {
      	$.ajax({
      		type: "POST",
      		url: "ajax-changeUserStatus",
      		data: ({
      			empDataId: empDataId,
      			currentStatus: currentStatus
      		}),

      		success: function (data) {
      			console.log(" user status changed >> " + data);
      		}
      	});
      }

      function sendMail(empDataId) {
      	$.ajax({
      		type: "POST",
      		url: "ajax-sendMail",
      		data: ({
      			empDataId: empDataId,
      		}),

      		success: function (data) {
      			console.log(" mail sent ?? >> " + data);
      		}
      	});
      }
      </script>
</body>
</html>