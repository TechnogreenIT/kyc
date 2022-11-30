<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Fill Hazardous Waste Manifest Form | KYC</title>
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
</head>
<body data-ma-theme="blue" class="body-bg">
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
		<jsp:include page="../NewCommon/env-menus.jsp" />
		<!-- include menus here end-->
		<form name="form" action="#" id="hwmanifest-form">
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<header class="content__title">
					<blockquote class="blockquote">
						<h4 class="mb-0">Hazardous Waste Manifest</h4>
					</blockquote>
					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="You can view consent in this section."><a
							class="actions__item zmdi zmdi-help"></a></span>
					</div>
				</header>
				<!-- breadcrumb end -->
				<div class="container">
					<div class="row">
						<div class="col-sm-1">1</div>
						<div class="col-sm-3">
							<label>Sender's Name & Mailing Address (including Phone
								No. and e-mail):</label>
						</div>
						<div class="col-sm-8">
							<div class="row">
								<div class="col-4">
									<div class="form-group">
										<input type="text" name="sendersName" class="form-control"
											placeholder="Enter Name">
										<div class="invalid-feedback">Required ! !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-4">
									<div class="form-group">
										<input type="email" name="sendersMailingAddress"
											class="form-control" placeholder="Enter Email Address" pattern="[^ @]*@[^ @]*"> 
										<div class="invalid-feedback">Required !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-4">
									<div class="form-group">
										<input type="tel" name="sendersPhoneNo" id="sendersPhoneNo"
											class="form-control" placeholder="Enter Phone Number" pattern="[1-9]{1}[0-9]{9}">
										<div class="invalid-feedback">Required !</div><div id="sensmob"></div>
										<i class="form-group__bar"></i>
									</div>
								</div>
							</div>
						</div>

						<div class="col-sm-1">2</div>
						<div class="col-sm-3">
							<label>Sender's authentication No:</label>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control"
									name="sendersAuthorizationNo"
									placeholder="Enter authentication">
							</div>
						</div>

						<div class="col-sm-1">3</div>
						<div class="col-sm-3">
							<label>Manifest Document No:</label>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control"
									name="manifestDocumentsNo" placeholder="Enter Document No">
							</div>
						</div>

						<div class="col-sm-1">4</div>
						<div class="col-sm-3">
							<label>Transporteis Name & Address (including Phone No. &
								email)</label>
						</div>
						<div class="col-sm-8">
							<div class="row">
								<div class="col-4">
									<div class="form-group">
										<input type="text" name="transporterName" class="form-control"
											placeholder="Enter Name">
										<div class="invalid-feedback">Required ! !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-4">
									<div class="form-group">
										<input type="email" name="transporterAddress"
											class="form-control" placeholder="Enter Email Address">
										<div class="invalid-feedback">Required !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-4">
									<div class="form-group">
										<input type="tel" name="transportermobilepNo"
											id="transportermobilepNo" class="form-control"
											placeholder="Enter Phone Number" pattern="[1-9]{1}[0-9]{9}">
										<div class="invalid-feedback">Required !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>
							</div>
						</div>

						<div class="col-sm-1">5</div>
						<div class="col-sm-3">
							<label>Type of Vehicle</label>
						</div>
						<div class="col-sm-8">
							<div class="row">
								<div class="col-sm-4">
									<div class="radio radio--inline cursor-pointer">
										<input type="radio" name="vehicle_type" id="Truck"> <label
											class="radio__label" for="Truck">Truck</label>
									</div>
								</div>

								<div class="col-sm-4">
									<div class="radio radio--inline cursor-pointer">
										<input type="radio" name="vehicle_type" id="Tanker"> <label
											class="radio__label" for="Tanker">Tanker</label>
									</div>
								</div>

								<div class="col-sm-4">
									<div class="radio radio--inline cursor-pointer">
										<input type="radio" name="vehicle_type" id="Special">
										<label class="radio__label" for="Special"> Special
											Whiqle </label>
									</div>
								</div>
							</div>
						</div>


						<div class="col-sm-1">6</div>
						<div class="col-sm-3">
							<label>Transporter's Registration No:</label>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control"
									name="transporterRegistrationNo"
									placeholder="Transporter Registration No">
								<div class="invalid-feedback">Required !</div>
								<i class="form-group__bar"></i>
							</div>
						</div>

						<div class="col-sm-1">7</div>
						<div class="col-sm-3">
							<label>Vehicle Registration No:</label>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control"
									name="transporterVehicleRegistrationNo"
									placeholder="Vehicle Registration No.">
								<div class="invalid-feedback">Required !</div>
								<i class="form-group__bar"></i>
							</div>
						</div>

						<div class="col-sm-1">8</div>
						<div class="col-sm-3">
							<label>Receiver's Name & mailing address (including Phone
								No. and e-mail):</label>
						</div>
						<div class="col-sm-8">
							<div class="row">
								<div class="col-4">
									<div class="form-group">
										<input type="text" name="receiversName" class="form-control"
											placeholder="Enter Name">
										<div class="invalid-feedback">Required ! !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-4">
									<div class="form-group">
										<input type="email" name="receiversAddress"
											class="form-control" placeholder="Enter Email Address" pattern="[^ @]*@[^ @]*">
										<div class="invalid-feedback">Required !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-4">
									<div class="form-group">
										<input type="tel" name="receiversPhoneNo"
											id="receiversPhoneNo" class="form-control"
											placeholder="Enter Phone Number" pattern="[1-9]{1}[0-9]{9}">
										<div class="invalid-feedback">Required !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>
							</div>
						</div>

						<div class="col-sm-1">9</div>
						<div class="col-sm-3">
							<label>Receiver's authorization No:</label>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control"
									name="receiversAuthorizationNo"
									placeholder="Receivers Authorization No.">
								<div class="invalid-feedback">Required !</div>
								<i class="form-group__bar"></i>
							</div>
						</div>

						<div class="col-sm-1">10</div>
						<div class="col-sm-3">
							<label>Waste Description:</label>
						</div>
						<div class="col-sm-8">
							<c:forEach items="${allProductName}" varStatus="status"
								var="productName">
								<div class="row">
									<div class="col-4">
										<div class="checkbox">
											<input type="checkbox" name="wasteProductName[]"
												id="wasteProductName${status.index}"
												value="<c:out value="${productName}"/>"
												onclick="setQuantityUnit(${status.index})"> <label
												class="checkbox__label"
												for="wasteProductName${status.index}"><c:out
													value="${productName}" /></label>
										</div>
									</div>
									<div id="quantity_id_${status.index}"
										style="display: contents;"></div>
								</div>

							</c:forEach>
						</div>

						<div class="col-sm-1">11</div>
						<div class="col-sm-3">
							<label>Total Quantity:</label>
						</div>
						<div class="col-sm-8">
							<div class="row">
								<div class="col-6">
									<div class="form-group">
										<input type="number" id="tot_quantity_container"
											name="tot_quantity_container" class="form-control"
											placeholder="Total Quantity Container ">
										<div class="invalid-feedback">Required ! !</div>
										<i class="form-group__bar"></i>
									</div>
								</div>

								<div class="col-6">
									<div class="form-group">
										<select class='select2' data-placeholder='Select Units'
											id="tot_Quantity_container_unit"
											name='tot_Quantity_container_unit'>
											<option value="">Units</option>
											<option value="kg">kg</option>
											<option value="m3">m3</option>
											<option value="Tons">Tons</option>
										</select>
										<div class="invalid-feedback">Required !</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-12">
							<div class="row" id="appendContainers">

								<div class="col-sm-1"></div>

								<div class="col-sm-3">
									<label>Containers Number:</label>
								</div>

								<div class="col-sm-8">
									<div class="row">
										<div class="col-5">
											<div class="form-group">
												<input type="text" name="containers_Number[]"
													class="form-control" placeholder="Container Number">
												<div class="invalid-feedback">Required ! !</div>
												<i class="form-group__bar"></i>
											</div>
										</div>

										<div class="col-5">
											<div class="form-group">
												<input type="text" name="containers_Type[]"
													class="form-control" placeholder="Type">
												<div class="invalid-feedback">Required ! !</div>
												<i class="form-group__bar"></i>
											</div>
										</div>

										<div class="col-2">
											<div class="form-group">
												<button type="button"
													class="btn btn-sm btn-light pt-1 pb-1 waves-effect waves-light"
													onclick="AddContainer();">
													<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>

						<div class="col-sm-1">12</div>
						<div class="col-sm-3">
							<label>Special Handling lnstructions and Additional
								lnformation:</label>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control" name="specialHandling"
									placeholder="Special Handling Instructions and Additional Information">
								<div class="invalid-feedback">Required ! !</div>
								<i class="form-group__bar"></i>
							</div>
						</div>


						<div class="col-sm-1">13</div>

						<div class="col-sm-3">
							<label>Sender's Certificate</label>
						</div>

						<div class="col-sm-8">
							<label>I hereby declare tliat the contents of the
								consignment are fully and accurately described above by proper
								shipping name and are categorized, packed, marked and labeled,
								and are in all respects jn proper condition for transport by
								road according to applicable national government regulations.</label>
						</div>

						<div class="col-sm-1"></div>

						<div class="col-sm-3">
							<label>Name & Stamp:</label>
						</div>

						<div class="col-sm-8">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class="zmdi zmdi-calendar"></i></span>
								</div>
								<input type="date" class="form-control hidden-md-up"
									placeholder="Pick a date"> <input type="text"
									name="subdate" class="form-control date-picker hidden-sm-down" onchange='validatedt()' 
									placeholder="Pick a date"><div id='pickdt'></div>
								<div class="invalid-feedback">Required ! !</div>
								<i class="form-group__bar"></i>
							</div>
						</div>

						<div class="col-sm-1">14</div>

						<div class="col-sm-3">
							<label>Transporter Acknowledgment of receipt of wastes:</label>
						</div>

						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control"
									name="transport_desc_waste">
							</div>
						</div>

						<div class="col-sm-1"></div>
						<div class="col-sm-3">
							<label>Name & Stamp:</label>
						</div>

						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control">
								<div class="invalid-feedback">Required ! !</div>
								<i class="form-group__bar"></i>	
							</div> 
						</div>

						<div class="col-sm-1">15</div>
						<div class="col-sm-11">
							<label>Receiver's Certification for Receipt bf Hazardous
								and other Waste:</label>
						</div>

						<div class="col-sm-1"></div>

						<div class="col-sm-3">
							<label>Maharashtra Enviro Power Ltd. Stamp : Signature:</label>
						</div>

						<div class="col-sm-8">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Enter date" >	
									<div class="invalid-feedback">Required ! !</div>
										<i class="form-group__bar"></i>							
							</div>
						</div>



					</div>
					<center>
						<button class="btn btn-light btn--icon-text"
							onclick="saveHWManifest()">
							<i class="zmdi zmdi-save"></i> Save
						</button>
					</center>
				</div>

				<!-- include common footer start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common footer end-->
			</div>
			<!-- inner container div end -->
		</section>
		</form>
		<!-- Javascript -->
		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->		
		
			<script type="text/javascript"
			src="../newAssets/projectscripts/common-functions.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/HazManifest.js"></script>
	</main>
</body>
</html>