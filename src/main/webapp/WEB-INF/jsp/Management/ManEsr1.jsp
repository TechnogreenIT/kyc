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
<title>ESR Yearly | KYC</title>
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
	<jsp:include page="../NewCommon/common-header.jsp" /> <!-- include menus here start-->
	
	<section class="content content--full">
		<!-- inner container div start -->
		<div class="content__inner top-thick-border grey lighten-4">
			<header class="content__title">
				<blockquote class="blockquote">
					<h4 class="mb-0">ESR Yearly Form</h4>
				</blockquote>
				<div class="actions">
					<span class="mantooltip hover p-1" data-jbox-title=""
						data-jbox-content="You can view consent in this section."><a
						class="actions__item zmdi zmdi-help"></a></span>
				</div>
			</header>
			<!-- breadcrumb end -->
			<div class="container">
				<c:forEach items="${companyProfileDatas}" var="companyData">
						<input type="hidden" id="selected_year"
							value="<c:out value="${esrDatas[0]}"/>">
						<input type="hidden" id="date_from"
							value="<c:out value="${esrDatas[3]}"/>">
						<input type="hidden" id="date_to"
							value="<c:out value="${esrDatas[4]}"/>">
						<input type="hidden" id="esrConsentDate"
							value="<c:out value="${esrDatas[5]}"/>">
					<div class="row">
						<div class="col-sm-12">
							<h3>Part A<h3>
						</div>
						<div class="col-sm-12">
							<h5>Company Information<h5>
						</div>
						<div class="col-sm-4 mt-3">
							<label>Company Name</label>
						</div>
						<div class="col-sm-4  mt-3">
							<label>Unique Application Number:</label>
						</div>
						<div class="col-sm-4  mt-3">
							<label>Financial Year</label>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<input type="text" class="form-control" required name="uom"
									value="${companyData.compName}" disabled>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<input type="text" class="form-control" required name="uom"
									value="${companyData.uan}" disabled>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<input type="text" class="form-control" required name="Finyear"
									value="${companyData.compName}" disabled>
							</div>
						</div>

						<div class="col-sm-12">
							<label>Address</label>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
							<input type="hidden" id="working_days" value="${companyData.noWorkDays}">
							<input type="hidden" id="working_hr" value="${companyData.workingHour}">
								<input type="text" class="form-control" required name="Finyear"
									value="${companyData.plotNo}" disabled>
							</div>
						</div>

						<div class="col-sm-4">
							<label>Plot Number</label>
						</div>
						<div class="col-sm-4">
							<label>Taluka</label>
						</div>
						<div class="col-sm-4">
							<label>Village</label>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<input type="text" class="form-control" required name="uom"
									value="${companyData.plotNo}" disabled>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<input type="text" class="form-control" required name="uom"
									value="${companyData.taluka}" disabled>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<input type="text" class="form-control" required name="Finyear"
									value="${companyData.village}" disabled>
							</div>
						</div>

						<div class="col-sm-4">
							<label>Capital Investment (In lakhs) </label>
							<c:forEach items="${capitalInvestmentList}"
								var="capitalInvestment" varStatus="loop">
								<div class="form-group">
									<div class="fg-line">
										<input type="text" class="form-control" required
											name="Finyear" value="${capitalInvestment}" disabled>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="col-sm-4">
							<label>Scale</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="Finyear"
										value="${companyData.indPrimary}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4"></div>
						<div class="col-sm-4">
							<label>City</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="city"
										value="${companyData.city}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Pincode</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="pincode"
										value="${companyData.pincode}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4"></div>

						<div class="col-sm-4">
							<label>Person Name</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="perName"
										value="${companyData.contPerName}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Designation</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="desig"
										value="${companyData.contPerDesig}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4"></div>

						<div class="col-sm-4">
							<label>Telephone Number</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="phone"
										value="${companyData.phoneNo}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Fax Number</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="fax"
										value="${companyData.fax}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Email</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="email"
										value="${companyData.email}" disabled>
								</div>
							</div>
						</div>

						<div class="col-sm-4">
							<label>Region</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="phone"
										value="${companyData.sro}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Industry Category</label>
							<div class="form-group">
								<div class="fg-line">
								  <input type="hidden" id="company_category" value="${companyData.industryCategory}">
									<input type="text" class="form-control" required name="fax"
										value="${companyData.industryCategory}" disabled>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Industry Type</label>
							<div class="form-group">
								<div class="fg-line">
									<input type="text" class="form-control" required name="email"
										value="${companyData.industryType}" disabled>
								</div>
							</div>
						</div>
						
							 <div class="col-sm-4">
							<label>Last Environmental statement submitted online</label>
							<div class="form-group">
								<div class="fg-line">
									<c:choose>
										<c:when test="${companyData.lastEnv  == ''}">
											<input type="text" class="form-control" required
												name="notsubmitted" value="Not Submitted" disabled>
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control" required
												name="submited" value="Submitted on ${companyData.lastEnv}"
												disabled>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Consent Number</label>
							<div class="form-group">
								<div class="fg-line">
									<c:forEach items="${consNoList }" var="consNo" varStatus="loop">
									<input type="hidden" id="consent_id" name="consent_id[]" value="${consNo}" />
										<input type="text" class="form-control" required name="fax"
											value="${consNo}" disabled>
									</c:forEach>

								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Consent Issue Date</label>
							<div class="form-group">
								<div class="fg-line">
									<c:forEach items="${issueDateList}" var="Inssdate" varStatus="loop">
										<input type="text" class="form-control" required name="fax"
											value="${Inssdate}" disabled>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label>Consent Valid Upto</label>
							<div class="form-group">
								<div class="fg-line">
									<c:forEach items="${validUptoList}" var="Validdate" varStatus="loop">
										<input type="text" class="form-control" required name="fax"
											value="${Validdate}" disabled>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="row">
				<div class="col-sm-12">
						<h5>Product Information<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Product Name</th>
								<th>Consent Quantity</th>
								<th>Actual Quantity</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="product_info">
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h5>ByProduct Information<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>ByProduct Name</th>
								<th>Consent Quantity</th>
								<th>Actual Quantity</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="byproduct_info">
						</tbody>
					</table>
				</div>
				<div class="row">
				<div class="col-sm-12 mt-4">
						<h3>Part B<h3>
					</div>
					<div class="col-sm-12">
						<h5>1) Water Consumption in m<sup>3</sup>/day<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Water Consumption for</th>
								<th>Consent Quantity in m<sup>3</th>
								<th>Actual Quantity in m<sup>3</sup>/day</th>
							</tr>
						</thead>
						<tbody id="water_info">
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h5>1)Effluent Generation in CMD / MLD<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Water Consumption for</th>
								<th>Consent Quantity in m<sup>3</th>
								<th>Actual Quantity in m<sup>3</sup>/day</th>
							</tr>
						</thead>
						<tbody id="water_Ger_info">
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h5>2) Product Wise Process Water Consumption (cubic meter of process water per unit of product)<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Name of Products (Production)</th>
								<th>During the Previous financial Year</th>
								<th>During the Current Financial Year</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="div_esr_product_consumption">
						</tbody>
					</table>
					<div id="hidden_esr_product"></div>
					<!--   <div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_product_water_consumption_button" onclick="saveProductYearESR()"><i class="zmdi zmdi-save"></i> Save</button>
					</div>-->
				</div>
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h5>3) Raw Material Consumption (Consumption of raw material per unit of product):<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Name of Products (Raw Material)</th>
								<th>During the Previous financial Year</th>
								<th>During the Current Financial Year</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="div_esr_rawmaterial_consumption">
						</tbody>
					</table>
					<div id="hidden_esr_rawmaterial"></div>
				<!--  	 <div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_product_rawmaterial_button" onclick="saveRawMaterialYearESR()"><i class="zmdi zmdi-save"></i> Save</button>
					</div> -->
				</div>
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h5>4)Fule Counsumption:<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Name of Products (Fule)</th>
								<th>During the Previous financial Year</th>
								<th>During the Current Financial Year</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="Fuel_info">
						</tbody>
					</table>
					<div id="hidden_esr_fule"></div>
				</div>
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h3>Part C:<h3>
					</div>
					<div class="col-sm-12 mt-4">
						<h5>Pollution discharged to environment/unit of output (Parameter as specified in the consent issued)<h5>
					</div>
					<div class="col-sm-12 mt-4">
						<h5>A) Water<h5>
					</div>
					<input type="hidden" id="effConsumption"/>
						<input type="hidden" id="sewConsumption"/>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Pollutants Detail</th>
								<th>Quantity of Pollutants discharged(kL/day or kg/day)</th>
								<th>Concentration of Pollutants discharged(Mg/Lit) Except PH,Temp,Color.</th>
								<th>Percentage of variation from prescribed	standards with reasons</th>
							</tr>
							<tr>
								<th></th>
								<th>Quantity</th>
								<th>Concentration</th>
								<th>%variation</th>
								<th>Standard</th>
								<th>Reason</th>
							</tr>
							
						</thead>
						<tbody id="esr-div-effluent-pollutant">
						<tr><th colspan="2" class="font-weight-900">Effulant POLLUTANTS</th></tr>
						</tbody>
						<tbody id="esr-div-sewage-pollutant">
						<tr><th colspan="2"  class="font-weight-900">Sewage POLLUTANTS</th></tr>
						</tbody>
					</table>
					<div id="esr-div-effluentHidden-pollutant"></div>
					<div id="esr-div-sewageHidden-pollutant"></div>
					<!--  <div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="effSaveMonthly" onclick="saveEffSewResonYear()"><i class="zmdi zmdi-save"></i> Save</button>
					</div>-->
					
					<div class="col-sm-12 mt-4">
						<h5>B) Air(Stack)<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Pollutants Detail</th>
								<th>Quantity of Pollutants discharged(kL/day or kg/day)</th>
								<th>Concentration of Pollutants discharged(Mg/Lit) Except PH,Temp,Color.</th>
								<th>Percentage of variation from prescribed	standards with reasons</th>
							</tr>
							<tr>
								<th></th>
								<th>Quantity</th>
								<th>Concentration</th>
								<th>%variation</th>
								<th>Standard</th>
								<th>Reason</th>
							</tr>
							
						</thead>
						<tbody id="esr-div-Stack-pollutant">
						
						</tbody>
					</table>
					<!-- <div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="airSaveYearly" onclick="saveAirSewResonYear()"><i class="zmdi zmdi-save"></i> Save</button>
					</div> -->
				</div>
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h3>Part D:<h3>
					</div>
					<div class="col-sm-12 mt-4">
						<h5>HAZARDOUS WASTES<h5>
					</div>
					<div class="col-sm-12">
						<h5>1) From Process<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Hazardous Waste Type</th>
								<th>Total During Previous Financial Month</th>
								<th> Total During Current Financial Month</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="esr-hwp">
						
						</tbody>
					</table>
					
					<div class="col-sm-12">
						<h5>2) From Pollution Control Facilities<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Hazardous Waste Type</th>
								<th>Total During Previous Financial Month</th>
								<th> Total During Current Financial Month</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="esr-hwpcf">
						
						</tbody>
					</table>
				</div>
				
				<div class="row">
					<div class="col-sm-12 mt-4">
						<h3>Part E:<h3>
					</div>
					<div class="col-sm-12 mt-4">
						<h5>Solid WASTES<h5>
					</div>
					<div class="col-sm-12">
						<h5>1) From Process<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Solid Waste Type</th>
								<th>Total During Previous Financial Month</th>
								<th> Total During Current Financial Month</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="esr-nhwp">
						
						</tbody>
					</table>
					
					<div class="col-sm-12">
						<h5>2) From Pollution Control Facilities<h5>
					</div>
					<table class='table table-bordered normal formatted-padding'>
						<thead>
							<tr>
								<th>Solid Waste Type</th>
								<th>Total During Previous Financial Month</th>
								<th> Total During Current Financial Month</th>
								<th>UOM</th>
							</tr>
						</thead>
						<tbody id="esr-nhwpcf">
						
						</tbody>
					</table>
					
					<div class="col-sm-12">
						<h5>3) Quantity Recycled or Re-utilized within the <h5>
					</div>
					<div class="col-12">
					<div class="row">
								<div class="col-3">
									<label>Waste Type</label>
								</div>
	
								<div class="col-3">
									<label>Total During Previous Financial year</label>
								</div>
								
								<div class="col-3">
									<label>Total During Current Financial year</label>
								</div>
								
								<div class="col-2">
									<label>UOM</label>
								</div>
					</div>
					<div  id="appendContainers"></div>
					</div>
					<!--  <div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_recylced_data" onclick="saveRecyledData()"><i class="zmdi zmdi-save"></i> Save</button>
					</div>-->
				</div>
				<div class="row">
				<div class="col-sm-12">
					<h4>Please specify the characteristics(in terms of concentration and quantum) 
					of hazardous as well as solid wastes and indicate disposal practice adopted for both these categories of wastes.</h4>
				</div>
				<div class="col-sm-12 mt-4">
				<h5>1) Hazardous Waste</h5></div>

					<div class="col-3">
						<label>Waste Type</label>
					</div>

					<div class="col-3">
						<label>Quantity</label>
					</div>

					<div class="col-3">
						<label>UOM</label>
					</div>

					<div class="col-2">
						<label>Concentration</label>
					</div>
				
				</div>
				<div  id="appendHWdata"></div>
				<!-- <div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_Haz_data" onclick="saveHazData()"><i class="zmdi zmdi-save"></i> Save</button>
				</div> -->
				<div class="row">
				<div class="col-sm-12 mt-4">
				<h5>2) Solid Waste</h5></div>

					<div class="col-3">
						<label>Waste Type</label>
					</div>

					<div class="col-3">
						<label>Quantity</label>
					</div>

					<div class="col-3">
						<label>UOM</label>
					</div>

					<div class="col-2">
						<label>Concentration</label>
					</div>
				
				</div>
				<div  id="appendSoliddata"></div>
				<!--  <div class="row">
					<div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_Solid_data" onclick="saveSolidData()"><i class="zmdi zmdi-save"></i> Save</button>
				</div>
				</div>-->
				<div class="row">
					<div class="col-sm-12">
						<h4>Impact of the pollution Control measures taken on
						 conservation of natural resources and consequently on the cost of production.</h4>
					</div>
					<div class="col-2">
						<label>Description</label>
					</div>

					<div class="col-2">
						<label>Reduction in Water Consumption (M3/day)</label>
					</div>

					<div class="col-2">
						<label>Reduction in Fuel & Solvent Consumption (KL/day)</label>
					</div>

					<div class="col-1">
						<label>Reduction in Raw Material (Kg)</label>
					</div>

					<div class="col-1">
						<label>Reduction in Power Consumption (KWH)</label>
					</div>

					<div class="col-1">
						<label>Capital Investment(in Lacs)</label>
					</div>
					
					<div class="col-2">
						<label>Reduction in Maintenance(in Lacs)</label>
					</div>
				</div>
				<div  id="appendPollutionControldata"></div>
				<!-- <div class="row">
					<div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_Polution_data" onclick="savePollutionControlData()"><i class="zmdi zmdi-save"></i> Save</button>
				</div>
				</div> -->
				<div class="row">
					<div class="col-sm-12">
						<h4>[A] Investment made during the period of Environmental Statement.</h4>
					</div>
					<div class="col-4">
						<label>Detail of measures for Environmental Protection</label>
					</div>

					<div class="col-4">
						<label>Environmental Protection Measures</label>
					</div>

					<div class="col-4">
						<label>Capital Investment (Lacs)</label>
					</div>
				</div>
				<div  id="appendInvestmentstatedata"></div>
				<!--  <div class="row">
					<div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_Investment_data" onclick="saveInvestmentData()"><i class="zmdi zmdi-save"></i> Save</button>
				    </div>
				</div>-->
				<div class="row">
					<div class="col-sm-12">
						<h4>[B]  Investment Proposed for next Year.</h4>
					</div>
					<div class="col-4">
						<label>Detail of measures for Environmental Protection</label>
					</div>

					<div class="col-4">
						<label>Environmental Protection Measures</label>
					</div>

					<div class="col-4">
						<label>Capital Investment (Lacs)</label>
					</div>
				</div>
				<div id="appendInvestmentstateNextdata"></div>
				<!--  <div class="row">
					<div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_InvestmentNext_data" onclick="saveInvestmentNextData()"><i class="zmdi zmdi-save"></i> Save</button>
				    </div>
				</div>-->
				<div class="row">
					<div class="col-sm-12">
						<h5>Any other particulars in respect of environmental protection and abatement of pollution.</h5>
					</div>
					<div class="col-12">
						<label>Particular</label>
					</div>
				</div>
				<div id="appendParticulardata"></div>
			<!--  	<div class="row">
					<div  class="col-sm-12">
						<button class="btn btn-primary btn--icon-text float-right" id="esrmonthly_Particular_data" onclick="saveParticularData()"><i class="zmdi zmdi-save"></i> Save</button>
				    </div>
				</div>-->
			</div>
				
			<!-- include common footer start-->
			<jsp:include page="../NewCommon/common-footer.jsp" />
			<!-- include common footer end-->
		</div>
		<!-- inner container div end -->
	</section>
	<!-- Javascript --> <!-- include common css start--> <jsp:include
		page="../NewCommon/common-javascript.jsp" /> <!-- include common css end-->
	<script type="text/javascript" src="../newAssets/projectscripts/common-functions.js"></script>
		<script type="text/javascript" src="../newAssets/projectscripts/esr-Yearlyman.js"></script>
	</main>
</body>
</html>