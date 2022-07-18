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
<body data-ma-theme="blue" class="body-bg"
	style="background-image: url('newAssets/img/custom/data-science-bg.png') !important; background-size: cover !important; background-repeat: no-repeat !important; background-attachment: fixed !important;">
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
				<header class="content__title">
					<blockquote class="blockquote">
						<h4 class="mb-0">Hazardous Return Form</h4>
					</blockquote>
					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="You can view consent in this section."><a
							class="actions__item zmdi zmdi-help"></a></span>
					</div>
				</header>
				<!-- breadcrumb end -->
				<div class="container">
					<c:forEach items="${companyData}" var="companyData">
						<input type="hidden" name="year" id="years"
							value="<c:out value='${selectedYear}'/>" />
						<div class="row">
							<div class="col-sm-3">
								<label>Unique Application Number:</label>
							</div>
							<div class="col-sm-3">
								<label>${companyData.uan}</label>
							</div>
							<div class="col-sm-3">
								<label>Submitted Date:</label>
							</div>
							<div class="col-sm-3">
								<label>${today}</label>
							</div>

							<div class="col-sm-3">
								<label>1.Name of the generator/operator of facility:</label>
							</div>
							<div class="col-sm-3">
								<label>${companyData.compName}</label>
							</div>
							<div class="col-sm-3">
								<label><b><font>Address of the unit/facility:</font></b></label>
							</div>
							<div class="col-sm-3">
								<label>${companyData.plotNo},
									${companyData.street},${companyData.taluka},
									${companyData.village},
									${companyData.city}-${companyData.pincode},
									${companyData.country}</label>
							</div>

							<div class="col-sm-3">
								<label>1b. Authorization Number:</label>
							</div>
							<div class="col-sm-3">
								<c:forEach var="consentNo" items="${consentNumberList}"
									varStatus="loop">
									<label>${consentNo}</label>
									${!loop1.last ? '</br>' : ''}
							  </c:forEach>
							</div>
							<div class="col-sm-3">
								<label><b><font>Date of issue:</font></b></label>
							</div>
							<div class="col-sm-3">
								<c:forEach var="issueDate" items="${issueDateList}"
									varStatus="loop">
									<label>${issueDate}</label>
									${!loop1.last ? '</br>' : ''}
								</c:forEach>
							</div>

							<div class="col-sm-3">
								<label>2. Name of the authorised person</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<label>${companyData.contPerName}</label>
							</div>
							<div class="col-sm-3">
								<label><b><font>Full address of authorised
											person:</font></b></label>
							</div>
							<div class="col-sm-3">
								<label><b><font>${companyData.plotNo},
											${companyData.street},${companyData.taluka},
											${companyData.village},
											${companyData.city}-${companyData.pincode},
											${companyData.country}:</font></b></label>

							</div>

							<div class="col-sm-2">
								<label>Telephone:</label>
							</div>
							<div class="col-sm-2">
								<label>${companyData.phoneNo}</label>
							</div>
							<div class="col-sm-2">
								<label><b><font>Email:</font></b></label>
							</div>
							<div class="col-sm-2">
								<label><b><font>${companyData.email}:</font></b></label>
							</div>
							<div class="col-sm-2">
								<label>Fax:</label>
							</div>
							<div class="col-sm-2">
								<label>${companyData.fax}</label>
							</div>

							<div class="col-sm-12">
								<label>3. Production during the year (product wise),
									wherever applicable:</label>
							</div>
							<div class="col-12">
								<div class="row" id="production">
									<div class="col-sm-3">
										<label>Product Type</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>Product Name</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>Quantity</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>UOM</font>:
										</label>
									</div>
								</div>
							</div>

							<div class="col-sm-12">
								<h4>
									<b>PART A: To be filled by hazardous waste generators:</b>
								</h4>
							</div>
							<div class="col-sm-12">
								<label>1. Total Quantity of waste generated category
									wise:</label>
							</div>
							<div class="col-12">
								<div class="row" id="wastegen">
									<div class="col-sm-3">
										<label>Type of hazardous waste:</label>
									</div>
									<div class="col-sm-3">
										<label>Wate Name</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>Quantity</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>UOM</font>:
										</label>
									</div>
								</div>
							</div>

							<div class="col-sm-12">
								<label>2. Quantity dispatched category wise:</label>
							</div>
							<div class="col-12">
								<div class="row" id="dispatch">
									<div class="col-sm-2">
										<label>Type of waste</font>:
										</label>
									</div>
									<div class="col-sm-2">
										<label>Quantity</font>:
										</label>
									</div>
									<div class="col-sm-2">
										<label>UOM</font>:
										</label>
									</div>
									<div class="col-sm-2">
										<label>Dispacth to</font>:
										</label>
									</div>
									<div class="col-sm-2">
										<label>Facility Name</font>:
										</label>
									</div>
								</div>
							</div>

							<div class="col-sm-12">
								<label>3. Quantity Utilized in-house, If any</label>
							</div>
							<div class="col-12">
								<div class="row" id="inhouse">
									<div class="col-sm-3">
										<label>Type of waste</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>Wate Name</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>Quantity</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>UOM</font>:
										</label>
									</div>
								</div>
							</div>

							<div class="col-sm-12">
								<label>4. Quantity in storage at the end of the year</label>
							</div>
							<div class="col-12">
								<div class="row" id="storage">
									<div class="col-sm-3">
										<label>Type of waste</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>Wate Name</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>Quantity</font>:
										</label>
									</div>
									<div class="col-sm-3">
										<label>UOM</font>:
										</label>
									</div>
								</div>
							</div>

							<div class="col-sm-12">
								<h4>
									<b>PART B: To be filled bt Treatment,storage, and disposal
										facility operators:</b>
								</h4>
							</div>
							<div class="col-sm-3">
								<label>1.Total Quantity received</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<input type="text" class="form-control" required
									name="Total Quantity received" value="NA" disabled>
							</div>
							<div class="col-sm-3">
								<label>UOM</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-3">
								<label>2.Quantity in stock at the beginning of the year:</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required
										name="Total Quantity in stock" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<label>UOM</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-3">
								<label>3.Quantity treated:</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required
										name="Quantity treated" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<label>UOM</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>4.Quantity disposed in landfills as such and
									after treatment:</label>
							</div>
							<div class="col-sm-3">
								<label>Direct landfilling:</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Direct landfilling" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<label>UOM</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-3">
								<label>Landfill after treatment:</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" name="landfilling"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<label>UOM</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-3">
								<label>5.Quantity incinerated (if applicable):</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Quantity incinerated" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<label>UOM</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-3">
								<label>6. Quantiry processed other than specified above:</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Quantity processed" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<label>UOM:</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-3">
								<label>7. Quantity in storage at the end of the year:</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Quantity processed" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<label>UOM</font>:
								</label>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<h4>
									<b>PART C: To be filled by recyclers or co-processors or
										other users</b>
								</h4>
							</div>
							<div class="col-sm-12">
								<label>1. Quantity of waste received during the year</label>
							</div>

							<div class="col-sm-3">
								<label>Waste Name/Category</label>
							</div>
							<div class="col-sm-3">
								<label>Quantity of waste received from domestic sources</label>
							</div>
							<div class="col-sm-3">
								<label>Quantity of waste imported(If any)</label>
							</div>
							<div class="col-sm-3">
								<label>UOM</label>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Waste Name/Category" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Quantity of waste received from domestic sources"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required
										name="Quantity of waste imported(If any)" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>2.Quantity in stock at the beginning of the year</label>
							</div>

							<div class="col-sm-3">
								<label>Waste Name/Category</label>
							</div>
							<div class="col-sm-3">
								<label>Quantity of waste received from domestic sources</label>
							</div>
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
								<label>UOM</label>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Waste Name/Category" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Quantity of waste received from domestic sources"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3"></div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>3.Quantity of waste recycled or co-procesed or
									used</label>
							</div>

							<div class="col-sm-3">
								<label>Name of Waste</label>
							</div>
							<div class="col-sm-3">
								<label>Type of Waste</label>
							</div>
							<div class="col-sm-3">
								<label>Quantity</label>
							</div>
							<div class="col-sm-3">
								<label>UOM</label>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" name="Waste Name"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" name="Type of Waste"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required
										name="Quantity" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>4.Quantity of products dispatched (wherever
									applicable)</label>
							</div>

							<div class="col-sm-4">
								<label>Name of product</label>
							</div>
							<div class="col-sm-4">
								<label>Quantity</label>
							</div>
							<div class="col-sm-4">
								<label>UOM</label>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Name of product"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Quantity"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>5.Total quantity of waste generated</label>
							</div>

							<div class="col-sm-4">
								<label>Waste name/category</label>
							</div>
							<div class="col-sm-4">
								<label>Quantity</label>
							</div>
							<div class="col-sm-4">
								<label>UOM</label>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Waste name/category" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Quantity"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>6.Total quantity of waste disposed</label>
							</div>

							<div class="col-sm-4">
								<label>Waste name/category</label>
							</div>
							<div class="col-sm-4">
								<label>Quantity</label>
							</div>
							<div class="col-sm-4">
								<label>UOM</label>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Waste name/category" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Quantity"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>7.Total quantity of waste re-exported (If
									Applicable)</label>
							</div>

							<div class="col-sm-4">
								<label>Waste name/category</label>
							</div>
							<div class="col-sm-4">
								<label>Quantity</label>
							</div>
							<div class="col-sm-4">
								<label>UOM</label>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Waste name/category" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Quantity"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>8.Quantity in storage at the end of the year</label>
							</div>
							<div class="col-sm-4">
								<label>Waste name/category</label>
							</div>
							<div class="col-sm-4">
								<label>Quantity</label>
							</div>
							<div class="col-sm-4">
								<label>UOM</label>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control"
										name="Waste name/category" value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Quantity"
										value="NA" disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" required name="uom"
										value="KL/Annum" value="NA" disabled>
								</div>
							</div>

							<div class="col-sm-12">
								<label>Personal Details</label>
							</div>
							<div class="col-sm-4">
								<label>Place</label>
							</div>
							<div class="col-sm-4">
								<label>Date</label>
							</div>
							<div class="col-sm-4">
								<label>Designation</label>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Place" value="NA"
										disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" name="Date" value="NA"
										disabled>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="text" class="form-control" required
										name="Designation" value="KL/Annum" value="NA" disabled>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- include common footer start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common footer end-->
			</div>
			<!-- inner container div end -->
		</section>
		<!-- Javascript -->
		<!-- include common css start-->
		<jsp:include page="../NewCommon/common-javascript.jsp" />
		<!-- include common css end-->
		<script type="text/javascript"
			src="../newAssets/projectscripts/common-functions.js"></script>
		<script type="text/javascript"
			src="../newAssets/projectscripts/hazardous-returns.js"></script>
	</main>
</body>
</html>