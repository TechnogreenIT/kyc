<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
<script src="jquery/tests/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/esr.js"></script>
<script type="text/javascript" src="js/management-esr.js"></script>
<script type="text/javascript" src="js/addElements.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="img/faviconkyc.png" type="image/gif"
	sizes="12x12">
<title>E.S.R Yearly</title>

</head>
<body>

	<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
	<c:choose>
		<c:when test="${emplogindata.contPerDesig=='admin'}">
			<c:import url="../CommonWebpages/MenusWebpages/AdminMenus.jsp" />
		</c:when>
		<c:when test="${emplogindata.contPerDesig=='Management'}">
			<c:import url="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" />
		</c:when>
		<c:when test="${emplogindata.contPerDesig=='Environmental Officer'}">
			<c:import url="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
		</c:when>
		<c:when test="${emplogindata.contPerDesig=='Third Party'}">
			<c:import url="../CommonWebpages/MenusWebpages/TpMenus.jsp" />
		</c:when>
	</c:choose>
	<input type="hidden" id="selected_year"
		value="<c:out value="${esrDatas[0]}"/>">
	<input type="hidden" id="date_from"
		value="<c:out value="${esrDatas[3]}"/>">
	<input type="hidden" id="date_to"
		value="<c:out value="${esrDatas[4]}"/>">
	<input type="hidden" id="esrConsentDate"
		value="<c:out value="${esrDatas[5]}"/>">
	<section id="main"> <section id="content">
	<div class="container">
		<div class="card" id="print_div">
			<div class="row">
				<div class="col-sm-12">
					<div class="card-header_ESR">
						<div class="col-sm-11">
							<div class="title_ESR">
								<h4>
									<b>FORM V</b><br /> <small>Environmental Audit Report
										for the financial Year ending the <c:out
											value="${esrDatas[1]}" />
									</small>
								</h4>
							</div>
						</div>
						<div class="col-sm-1" data-name="more-vert" tooltip="f19b">
							<ul class="actions">
								<li class="dropdown"><a href="..#" data-toggle="dropdown">
										<i class="zmdi zmdi-more-vert"></i>
								</a>

									<ul class="dropdown-menu dropdown-menu-right">
										<li><a href="#" onclick="saveData('save');">Save</a></li>
										<li><a href="#" onclick="saveData('sap');">Save &
												Print</a></li>
										<li><a href="#" onclick="openModal();">Select Year</a></li>
									</ul></li>
							</ul>
						</div>

					</div>
					<!--company info-->
					<div class="card-body card-padding card-bg">
						<c:forEach items="${companyProfileDatas}"
							var="companyProfileDatas">
							<div class="row">
								<div class="col-sm-12">
									<b style="color: #77022a;">Company Information: </b>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-6">
										<div class="form-group">
											<div class="col-sm-12">
												<label><b>Company Name</b></label><br />
												<c:out value="${companyProfileDatas.compName}" />
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<div class="col-sm-12">
												<label><b>Application UAN Number</b></label><br />
												<c:out value="${companyProfileDatas.getUan()}" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-12">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Address</b></label> <br>Plot No.
												<c:out value="${companyProfileDatas.plotNo}" />
												,
												<c:out value="${companyProfileDatas.street}" />
												, <br>Taluka -
												<c:out value="${companyProfileDatas.taluka}" />
												, Dist. -
												<c:out value="${companyProfileDatas.district}" />
												, City -
												<c:out value="${companyProfileDatas.city}" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Plot no</b></label> <br>
												<c:out value="${companyProfileDatas.plotNo}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Taluka</b></label> <br>
												<c:out value="${companyProfileDatas.taluka}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Village</b></label> <br>
												<c:out value="${companyProfileDatas.village}" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Scale</b></label> <br>
												<c:out value="${companyProfileDatas.indPrimary}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>City</b></label> <br>
												<c:out value="${companyProfileDatas.city}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <b>Region</b></label> <br>
												<c:out value="${companyProfileDatas.sro}" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Person Name</b></label> <br>
												<c:out value="${companyProfileDatas.contPerName}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Designation</b></label> <br>
												<c:out value="${companyProfileDatas.contPerDesig}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Email</b></label> <br>
												<c:out value="${companyProfileDatas.email}" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Telephone</b></label> <br>
												<c:out value="${companyProfileDatas.phoneNo}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Fax Number</b></label> <br>
												<c:out value="${companyProfileDatas.fax}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Pincode</b></label> <br>
												<c:out value="${companyProfileDatas.pincode}" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Industry Category</b></label> <br>
												<c:out value="${companyProfileDatas.industryCategory}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <b>Industry Type</b></label> <br>
												<c:out value="${companyProfileDatas.industryType}" />
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <b>Last Environmental statement submitted online</b></label>
												<br>${companyProfileDatas.lastEnv }
											</div>
										</div>
									</div>
								</div>
								<c:forEach items="${ consNoList}" var="consNo">
									<input type="hidden" id="consent_id[]" name="consent_id[]"
										value="${consNo }" />
								</c:forEach>
								<div class="col-sm-12">
									<div class="col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Consent Number</b></label>
												<c:forEach items="${consNoList }" var="consNo"
													varStatus="loop">
													<br />
													<c:out value="${consNo}" />
											${!loop1.last ? '</br>' : ''}
											</c:forEach>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Consent Issue Date</b></label>
												<c:forEach items="${issueDateList }" var="issueDate"
													varStatus="loop">
													<br />
													<c:out value="${issueDate}" />
											${!loop1.last ? '</br>' : ''}
											</c:forEach>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Consent Valid Upto</b></label>
												<c:forEach items="${validUptoList }" var="validUpto"
													varStatus="loop">
													<br />
													<c:out value="${validUpto}" />
											${!loop1.last ? '</br>' : ''}
											</c:forEach>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
												<br /> <label><b>Capital Investment (In lakhs)</b></label>
												<c:forEach items="${capitalInvestmentList }"
													var="capitalInvestment" varStatus="loop">
													<br />
													<c:out value="${capitalInvestment}" />
											${!loop1.last ? '</br>' : ''}
											</c:forEach>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12"></div>
							</div>
							<input type="hidden" id="company_category"
								value="${companyProfileDatas.industryCategory}">
							<input type="hidden" id="working_days"
								value="${companyProfileDatas.noWorkDays}">
						</c:forEach>
						<!--company info-->
						<hr>
						<!--product info-->
						<div class="row" id="product_info" style="margin-top: 30px;">
							<div class="col-sm-12">
								<b style="color: #77022a;">Product Information</b>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-3">
									<label><b>Product Name</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Consent Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Actual Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>UOM</b></label>
								</div>
							</div>
							<div id="product-information"></div>
						</div>
						<!--product info-->
						<hr />
						<!--By-product info-->
						<div class="row" id="byproduct_info" style="margin-top: 30px;">
							<div class="col-sm-12">
								<b style="color: #77022a;">By-product Information</b>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-3">
									<label><b>By Product Name</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Consent Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Actual Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>UOM</b></label>
								</div>
							</div>
							<div id="byproduct-information"></div>
						</div>
						<!--By-product info-->
						<hr />
						<!--Water Consumption  info-->
						<div class="row" id="water_info" style="margin-top: 30px;">
							<div class="col-sm-12">
								<b style="color: #77022a;">1) Water Consumption in m<sup>3</sup>/day
								</b>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-4">
									<label><b>Water Consumption for</b></label>
								</div>
								<div class="col-sm-4">
									<label><b>Consent Quantity in m<sup>3</sup>/day
									</b></label>
								</div>
								<div class="col-sm-4">
									<label><b>Actual Quantity in m<sup>3</sup>/day
									</b></label>
								</div>
							</div>
							<div id="Process-esryeardiv"></div>
							<div id="Cooling-esryeardiv"></div>
							<div id="Domestic-esryeardiv"></div>
							<div id="All-others-esryeardiv"></div>
							<div id="total-esryeardiv"></div>
						</div>
						<!--Water Consumption  info-->
						<hr />
						<!-- Effluent Generation  info-->
						<div class="row" id="efflu_info" style="margin-top: 30px;">
							<div class="col-sm-12">
								<b style="color: #77022a;">1) Effluent Generation in CMD /
									MLD:</b>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-3">
									<label><b>Particulates</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Consent Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Actual Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>UOM</b></label>
								</div>
							</div>
							<div id="div_esryearly_etp"></div>
							<div id="div_esryearly_stp"></div>
							<div id="div_esryearly_na"></div>
						</div>
						<!-- Effluent Generation  info-->
						<hr />
						<!--  Product Wise Process Water Consumption info-->
						<div class="row" id="peff_info" style="margin-top: 30px;">
							<div class="col-sm-12">
								<b style="color: #77022a;"> 2) Product Wise Process Water
									Consumption (cubic meter of process water per unit of product):</b>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-3">
									<label><b>Name of Products (Production)</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>During the Previous financial Year</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>During the Current Financial Year</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>UOM</b></label>
								</div>
							</div>
							<div id="div_esryearly_product_water_consumption"></div>

						</div>

						<!--  Product Wise Process Water Consumption  info-->
						<hr />
						<!--  Raw Material Consumption  info-->
						<div class="row" id="raw_info" style="margin-top: 30px;">
							<div class="col-sm-12">
								<b style="color: #77022a;">3) Raw Material Consumption
									(Consumption of raw material per unit of product):</b>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-3">
									<label><b>Name of Products (Raw Material)</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>During the Previous financial Year</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>During the Current Financial Year</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>UOM</b></label>
								</div>
							</div>
							<div id="div_esryearly_rawmaterial_consumption"></div>

						</div>
						<hr />
						<!--  Raw Material Consumption  info-->
						<div class="row" id="fuel_info" style="margin-top: 30px;">
							<div class="col-sm-12">
								<b style="color: #77022a;">4) Fuel Consumption:</b>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-3">
									<label><b>Fuel Name</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Consent Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>Actual Quantity</b></label>
								</div>
								<div class="col-sm-3">
									<label><b>UOM</b></label>
								</div>
							</div>
							<div id="Fuel-information"></div>
						</div>
						<!--  Raw Material Consumption  info-->
						<hr />
						<div class="row" id="raw_con" style="margin-top: 30px;">
							<div class="form-group">
								<p style="color: #77022a;">
								<h4>Pollution discharged to environment/unit of output
									(Parameter as specified in the consent issued)</h4>
								</p>
								</center>
							</div>
							<div class="row" id="poll_info" style="margin-top: 20px;">
								<!--WATER-->
								<div class="col-sm-12">
									<h4 style="color: #77022a;">[A] Water</h4>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-2">
										<label><b>Pollutants Detail</b></label>
									</div>
									<div class="col-sm-2">
										<label><b>Quantity of Pollutants discharged
												(kL/day)</b></label>
									</div>
									<div class="col-sm-3">
										<label><b>Concentration of Pollutants
												discharged(Mg/Lit) Except PH,Temp,Color.</b></label>
									</div>
									<div class="col-sm-3">
										<label><b>Percentage of variation from prescribed
												standards with reasons</b></label>
									</div>

								</div>
								<div class="col-sm-12" id="pollut">
									<div class="col-sm-2"></div>
									<div class="col-sm-2">Quantity</div>
									<div class="col-sm-2">Concentration</div>
									<div class="col-sm-2">%variation</div>
									<div class="col-sm-2">Standard</div>
									<div class="col-sm-2">Reason</div>
								</div>
								<div class="col-sm-12" id="pollut1">
									<div class="col-sm-2"></div>
									<div class="col-sm-2">Quant.</div>
									<div class="col-sm-2">Conc.</div>
									<div class="col-sm-2">%</div>
									<div class="col-sm-2">Std.</div>
									<div class="col-sm-2">Reason</div>
								</div>
								<div class="col-sm-12">
									<br /> <u>EFFLUENT POLLUTANTS</u><br />
								</div>
								<div class="col-sm-12">
									<br />
									<div id="esryearly-div-effluent-pollutant"></div>
								</div>
								<div class="form-group">

									<div class="col-sm-12">
										<br /> <u>SEWAGE POLLUTANTS</u><br />
									</div>
									<div class="col-sm-12">
										<div id="esryearly-div-sewage-pollutant"></div>
									</div>

								</div>
								<!--WATER-->
								<!--Air (Stack)-->
								<div class="col-sm-12">
									<h4 style="color: #77022a;">
										<br />[B] Air (Stack)
									</h4>
								</div>
								<div class="col-sm-12">
									<div class="col-sm-2">
										<label><b>Pollutants Detail</b></label>
									</div>
									<div class="col-sm-2">
										<label><b>Quantity of Pollutants discharged
												(kL/day)</b></label>
									</div>
									<div class="col-sm-3">
										<label><b>Concentration of Pollutants
												discharged(Mg/Lit) Except PH,Temp,Color.</b></label>
									</div>
									<div class="col-sm-3">
										<label><b>Percentage of variation from prescribed
												standards with reasons</b></label>
									</div>
								</div>
								<div class="col-sm-12" id="air">
									<div class="col-sm-2"></div>
									<div class="col-sm-2">Quantity</div>
									<div class="col-sm-2">Concentration</div>
									<div class="col-sm-2">%variation</div>
									<div class="col-sm-2">Standard</div>
									<div class="col-sm-2">Reason</div>
								</div>
								<div class="col-sm-12" id="air1">
									<div class="col-sm-2"></div>
									<div class="col-sm-2">Quant.</div>
									<div class="col-sm-2">Conc.</div>
									<div class="col-sm-2">%</div>
									<div class="col-sm-2">Std.</div>
									<div class="col-sm-2">Reason</div>
								</div>
								<div class="col-sm-12">
									<div id="esryearly-div-air-stack"></div>
								</div>

							</div>
							<!--Air (Stack)-->
							<hr />
							<div class="row" id="haz_info"
								style="margin-top: 30px; margin-left: 20px">
								<div class="form-group">
									<p style="color: #77022a; padding: 20px;">
									<h4>HAZARDOUS WASTES</h4>
									</p>
									</center>
								</div>
								<div class="col-sm-12">
									<h4 style="color: #77022a;">1) From Process</h4>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="col-sm-3">
											<label><b>Hazardous Waste Type</b></label>
										</div>
										<div class="col-sm-3">
											<label><b>Total During Previous Financial year</b></label>
										</div>
										<div class="col-sm-3">
											<label><b>Total During Current Financial year</b></label>
										</div>
										<div class="col-sm-3">
											<label><b>UOM</b></label>
										</div>
									</div>

									<div id="esryearly-hwp"></div>


									<div class="col-sm-12">
										<br />
										<h4 style="color: #77022a;">2) From Pollution Control
											Facilities</h4>
									</div>
									<div class="col-sm-12">
										<div class="col-sm-3">
											<label><b>Hazardous Waste Type</b></label>
										</div>
										<div class="col-sm-3">
											<label><b>Total During Previous Financial year</b></label>
										</div>
										<div class="col-sm-3">
											<label><b>Total During Current Financial year</b></label>
										</div>
										<div class="col-sm-3">
											<label><b>UOM</b></label>
										</div>
									</div>

									<div id="esryearly-hwpcf"></div>

								</div>
								<div id="sol_info" class="row esr-row-formatter">
									<hr />
									<div class="form-group">
										<p style="color: #77022a;">
										<h4>SOLID WASTES</h4>
										</p>
										</center>
									</div>
									<div class="col-sm-12">
										<h4 style="color: #77022a;">1) From Process</h4>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="col-sm-3">
												<label><b>Non Hazardous Waste Type</b></label>
											</div>
											<div class="col-sm-3">
												<label><b>Total During Previous Financial year</b></label>
											</div>
											<div class="col-sm-3">
												<label><b>Total During Current Financial year</b></label>
											</div>
											<div class="col-sm-3">
												<label><b>UOM</b></label>
											</div>
										</div>

										<div id="esryearly-nhwp"></div>

										<div class="col-sm-12">
											<br />
											<h4 style="color: #77022a;">2) From Pollution Control
												Facilities</h4>
										</div>
										<div class="col-sm-12">
											<div class="col-sm-3">
												<label><b>Non Hazardous Waste Type</b></label>
											</div>
											<div class="col-sm-3">
												<label><b>Total During Previous Financial year</b></label>
											</div>
											<div class="col-sm-3">
												<label><b>Total During Current Financial year</b></label>
											</div>
											<div class="col-sm-3">
												<label><b>UOM</b></label>
											</div>
										</div>

										<div id="esryearly-nhwpcf"></div>

										<div class="row esr-row-formatter">
											<div class="col-sm-12">
												<br />
												<h4 style="color: #77022a;">3) Quantity Recycled or
													Re-utilized within the unit</h4>
											</div>
											<div class="col-sm-12">
												<div class="col-sm-3">
													<label><b>Waste Type</b></label>
												</div>
												<div class="col-sm-3">
													<label><b>Total During Previous Financial year</b></label>
												</div>
												<div class="col-sm-3">
													<label><b>Total During Current Financial year</b></label>
												</div>
												<div class="col-sm-3">
													<label><b>UOM</b></label>
												</div>
											</div>
											<c:choose>
												<c:when test="${!empty esrProductWaterData}">
													<c:forEach items="${esrProductWaterData }"
														var="esrProductWaterData">
														<input type="hidden" id="is_recycled" value="No">
														<div class="col-sm-12">
															<div class="col-sm-3">
																<label>${esrProductWaterData.productName }</label>
															</div>
															<div class="col-sm-3">
																<label>${esrProductWaterData. previousData}</label>
															</div>
															<div class="col-sm-3">
																<label>${esrProductWaterData. currentData}</label>
															</div>
															<div class="col-sm-3">
																<label>${esrProductWaterData.unit }</label>
															</div>
														</div>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<input type="hidden" id="is_recycled" value="Yes">
													<div class="col-sm-12 con-env">
														<div class="col-sm-3">
															<label>No Data</label>
														</div>
														<div class="col-sm-3">
															<label>No Data</label>
														</div>
														<div class="col-sm-3">
															<label>No Data</label>
														</div>
														<div class="col-sm-3">
															<label>No Data</label>
														</div>
													</div>
												</c:otherwise>
											</c:choose>

										</div>
										<div id="hzw_info" class="row esr-row-formatter">
											<hr />
											<div class="form-group">
												<p style="color: #77022a;">
												<h4>Please specify the characteristics(in terms of
													concentration and quantum) of hazardous as well as solid
													wastes and indicate disposal practice adopted for both
													these categories of wastes.</h4>
												</p>
												</center>
											</div>
											<div class="row esr-row-formatter">
												<div class="col-sm-12">
													<h4 style="color: #77022a;">1) Hazardous Waste</h4>
												</div>
												<div class="col-sm-12">
													<div class="col-sm-3">
														<label><b>Type of Hazardous Waste Generated</b></label>
													</div>
													<div class="col-sm-3">
														<label><b>Qty of Hazardous Waste</b></label>
													</div>
													<div class="col-sm-3">
														<label><b>UOM</b></label>
													</div>
													<div class="col-sm-3">
														<label><b>Concentration of Hazardous Waste</b></label>
													</div>
												</div>
												<c:choose>
													<c:when test="${!empty esrHwSolidWasteData }">
														<c:forEach items="${esrHwSolidWasteData }"
															var="esrHwSolidWasteData">
															<input type="hidden" id="is_hw" value="No">
															<div class="col-sm-12">
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteData.typeOfWaste }</label>
																</div>
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteData.quantity }</label>
																</div>
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteData.unit}</label>
																</div>
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteData.concentration }</label>
																</div>
															</div>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<input type="hidden" id="is_hw" value="Yes">
														<div class="col-sm-12 con-env">
															<div class="col-sm-3">
																<label>No Data</label>
															</div>
															<div class="col-sm-3">
																<label>No Data</label>
															</div>
															<div class="col-sm-3">
																<label>No Data</label>
															</div>
															<div class="col-sm-3">
																<label>No Data</label>
															</div>

														</div>

													</c:otherwise>
												</c:choose>

												<div class="col-sm-12">
													<br />
													<h4 style="color: #77022a;">2) Solid Waste</h4>
												</div>
												<div class="col-sm-12">
													<div class="col-sm-3">
														<label><b>Type of Solid Waste Generated</b></label>
													</div>
													<div class="col-sm-3">
														<label><b>Qty of Solid Waste</b></label>
													</div>
													<div class="col-sm-3">
														<label><b>UOM</b></label>
													</div>
													<div class="col-sm-3">
														<label><b>Concentration of Solid Waste</b></label>
													</div>
												</div>
												<c:choose>
													<c:when test="${!empty esrHwSolidWasteDataBySolidYear }">
														<c:forEach items="${esrHwSolidWasteDataBySolidYear }"
															var="esrHwSolidWasteDataBySolidYear">
															<input type="hidden" id="is_sw" value="No">
															<div class="col-sm-12">
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteDataBySolidYear.typeOfWaste }</label>
																</div>
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteDataBySolidYear.quantity }</label>
																</div>
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteDataBySolidYear.unit}</label>
																</div>
																<div class="col-sm-3">
																	<label>${esrHwSolidWasteDataBySolidYear.concentration }</label>
																</div>
															</div>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<input type="hidden" id="is_sw" value="Yes">
														<div class="col-sm-12 con-env">
															<div class="col-sm-3">
																<label>No Data</label>
															</div>
															<div class="col-sm-3">
																<label>No Data</label>
															</div>
															<div class="col-sm-3">
																<label>No Data</label>
															</div>
															<div class="col-sm-3">
																<label>No Data</label>
															</div>
														</div>

													</c:otherwise>
												</c:choose>

											</div>
										</div>
										<div id="imp" class="row esr-row-formatter">
											<hr />
											<div class="form-group">
												<p style="color: #77022a;">
												<h4>Impact of the pollution Control measures taken on
													conservation of natural resources and consequently on the
													cost of production.</h4>
												</p>
												</center>
											</div>

											<c:choose>
												<c:when test="${!empty esrPollutionControlDataByYear}">
													<c:forEach items="${esrPollutionControlDataByYear }"
														var="esrPollutionControlDataByYear">
														<input type="hidden" id="is_pc" value="No">
														<div class="row">
															<div class="col-sm-12">
																<div class="col-sm-6">
																	<label><b>Description</b></label> <br /> <label>${esrPollutionControlDataByYear.description }</label>
																</div>
																<div class="col-sm-6">
																	<label><b>Reduction in Water Consumption (M<sup>3</sup>/day)
																	</b></label> <br /> <label>${esrPollutionControlDataByYear.reductionWater }</label>
																</div>
															</div>
															<div class="col-sm-12">
																<div class="col-sm-6">
																	<label><b>Reduction in Fuel & Solvent
																			Consumption (KL/day)</b></label> <br /> <label>${esrPollutionControlDataByYear.reductionFuel }</label>
																</div>
																<div class="col-sm-6">
																	<label><b>Reduction in Raw Material (Kg)</b></label> <br />
																	<label>${esrPollutionControlDataByYear.reductionRm}</label>
																</div>
															</div>
															<div class="col-sm-12">
																<div class="col-sm-6">
																	<label><b>Reduction in Power Consumption
																			(KWH)</b></label> <br /> <label>${esrPollutionControlDataByYear.reductionPc}</label>
																</div>
																<div class="col-sm-6">
																	<label><b>Capital Investment(in Lacs)</b></label> <br />
																	<label>${esrPollutionControlDataByYear.capitalInvestment}</label>
																</div>
															</div>
															<div class="col-sm-12">
																<div class="col-sm-11">
																	<label><b>Reduction in Maintenance(in Lacs)</b></label>
																	<br /> <label>${esrPollutionControlDataByYear.reductionMaintenance }</label>
																</div>
																<div class="col-sm-1"></div>
															</div>
														</div>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<input type="hidden" id="is_pc" value="Yes">
													<div class="row">
														<div class="col-sm-12">
															<div class="col-sm-6">
																<label><b>Description</b></label> <br> <label
																	class="con-env">No Data</label>
															</div>
															<div class="col-sm-6">
																<label><b>Reduction in Water Consumption (M<sup>3</sup>/day)
																</b></label> <br> <label class="con-env">No Data</label>
															</div>
														</div>
														<div class="col-sm-12">
															<div class="col-sm-6">
																<label><b>Reduction in Fuel & Solvent
																		Consumption (KL/day)</b></label> <br> <label class="con-env">No
																	Data</label>
															</div>
															<div class="col-sm-6">
																<label><b>Reduction in Raw Material (Kg)</b></label> <br>
																<label class="con-env">No Data</label>
															</div>
														</div>
														<div class="col-sm-12">
															<div class="col-sm-6">
																<label><b>Reduction in Power Consumption
																		(KWH)</b></label> <br> <label class="con-env">No Data</label>
															</div>
															<div class="col-sm-6">
																<label><b>Capital Investment(in Lacs)</b></label> <br>
																<label class="con-env">No Data</label>
															</div>
														</div>
														<div class="col-sm-12">
															<div class="col-sm-11">
																<label><b>Reduction in Maintenance(in Lacs)</b></label>
																<br> <label class="con-env">No Data</label>
															</div>
															<div class="col-sm-1"></div>
														</div>
													</div>

												</c:otherwise>
											</c:choose>
										</div>
										<div id="inv" class="row esr-row-formatter">
											<hr />
											<div class="col-sm-12">
												<h4 style="color: #77022a;">[A] Investment made during
													the period of Environmental Statement</h4>
											</div>

											<div class="row esr-row-formatter">
												<div class="col-sm-12">
													<div class="col-sm-4">
														<label><b>Detail of measures for Environmental
																Protection</b></label>
													</div>
													<div class="col-sm-4">
														<label><b>Environmental Protection Measures</b></label>
													</div>
													<div class="col-sm-4">
														<label><b>Capital Investment (Lacs)</b></label>
													</div>
												</div>
												<c:choose>
													<c:when test="${!empty getEsrInvestmentbyYear }">
														<c:forEach items="${getEsrInvestmentbyYear}"
															var="getEsrInvestmentbyYear">
															<input type="hidden" id="is_curr" value="No">
															<div class="col-sm-12">
																<div class="col-sm-4">
																	<label>${getEsrInvestmentbyYear.details}</label>
																</div>
																<div class="col-sm-4">
																	<label>${getEsrInvestmentbyYear.protectionMea}</label>
																</div>
																<div class="col-sm-4">
																	<label>${getEsrInvestmentbyYear.capitalInvestment}</label>
																</div>
															</div>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<input type="hidden" id="is_curr" value="Yes">
														<div class="col-sm-12 con-env">
															<div class="col-sm-4">
																<label>No Data</label>
															</div>
															<div class="col-sm-4">
																<label>No Data</label>
															</div>
															<div class="col-sm-4">
																<label>No Data</label>
															</div>
														</div>
													</c:otherwise>
												</c:choose>

												<div class="col-sm-12">
													<br />
													<h4 style="color: #77022a;">[B] Investment Proposed
														for next Year</h4>
												</div>
												<div class="col-sm-12">
													<div class="col-sm-4">
														<label><b>Detail of measures for Environmental
																Protection</b></label>
													</div>
													<div class="col-sm-4">
														<label><b>Environmental Protection Measures</b></label>
													</div>
													<div class="col-sm-4">
														<label><b>Capital Investment (Lacs)</b></label>
													</div>
												</div>
												<c:choose>
													<c:when test="${!empty getEsrInvestmentbyNextYear }">
														<c:forEach items="${getEsrInvestmentbyNextYear }"
															var="getEsrInvestmentbyNextYear">
															<input type="hidden" id="is_next" value="No">
															<div class="col-sm-12">
																<div class="col-sm-4">
																	<label>${getEsrInvestmentbyNextYear.details }</label>
																</div>
																<div class="col-sm-4">
																	<label>${getEsrInvestmentbyNextYear.protectionMea }</label>
																</div>
																<div class="col-sm-4">
																	<label>${getEsrInvestmentbyNextYear.capitalInvestment }</label>
																</div>
															</div>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<input type="hidden" id="is_next" value="Yes">
														<div class="col-sm-12 con-env">
															<div class="col-sm-4">
																<label>No Data</label>
															</div>
															<div class="col-sm-4">
																<label>No Data</label>
															</div>
															<div class="col-sm-4">
																<label>No Data</label>
															</div>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
									<div class="row esr-row-formatter">
										<hr />
										<div class="col-sm-12">
											<p style="color: #77022a;">
											<h5>Any other particulars in respect of environmental
												protection and abatement of pollution.</h5>
											</p>
											</center>
										</div>
										<div class="col-sm-12">
											<label><b> Particulars</b></label>
										</div>
										<c:choose>
											<c:when test="${!empty esrParticularsDataByYear }">
												<c:forEach items="${ esrParticularsDataByYear}"
													var="esrParticularsDataByYear">
													<input type="hidden" id="is_part" value="No">
													<div class="col-sm-12">
														<div class="col-sm-12">
															<label>${esrParticularsDataByYear.particulars }</label>
														</div>
													</div>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<input type="hidden" id="is_part" value="Yes">
												<div class="col-sm-12">
													<div class="col-sm-12 con-env">
														<label>No Data</label>
													</div>

												</div>

											</c:otherwise>
										</c:choose>
										<br>
										<div class="col-sm-12">
											<label><b>Name & Designation</b></label>
											<c:forEach items="${companyProfileDatas}"
												var="companyProfileDatas">
												<br>
												<c:out value="${companyProfileDatas.contPerName }"></c:out> -
							<c:out value="${companyProfileDatas.contPerDesig }" />
											</c:forEach>
										</div>
										<br>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section> </section>
	<script type="text/javascript">

$(document).ready(function(){
	 var consentId=[];
	$("input[name='consent_id[]']").each(function() {
		consentId.push($(this).val());
	});
	
	var companyCategory = ($('#company_category').val()).split("-");
	var esrConsentDate = $("#esrConsentDate").val();
	var dateFrom = $('#date_from').val();
	var dateTo = $('#date_to').val();
	var workingDays = $('#working_days').val();
	var selected_year = $('#selected_year').val();
	var Category = $.trim(companyCategory[0]);
	getYearlyESRValues(Category,"product",dateFrom,dateTo,workingDays,esrConsentDate);
	
	getYearlyESRValues(Category,"byproduct",dateFrom,dateTo,workingDays,esrConsentDate);
	getYearlyESRValues(Category,"Fuel",dateFrom,dateTo,workingDays,esrConsentDate);
	//debugger;
	getESRYearlyWaterConsumption(dateFrom, dateTo, selected_year);
	
	var eff_gen = 0;
	
	getESRYearlyWaterGeneration(eff_gen, 'Biodegradable');
	
	getYearlyProductWaterConsumption(dateFrom, dateTo, selected_year);
	getYearlyRawMaterialWaterConsumption(dateFrom, dateTo, selected_year);
	getEffluentPollutant(dateFrom, dateTo, selected_year);
	getYearlySewagePollutant(dateFrom, dateTo, selected_year);
	getYealyAirStack(dateFrom, dateTo, selected_year);
	var type=["hwp", "hwpcf", "nhwp", "nhwpcf"];
	for(var i=0;i<type.length;i++){
	getYearlyHazardousAndNonHazardousData(type[i],dateFrom, dateTo, selected_year);
	}
	//getQuantityRecycled(dateFrom, dateTo, selected_year, "Recycled");
		
});
</script>
	<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />

</body>
</html>