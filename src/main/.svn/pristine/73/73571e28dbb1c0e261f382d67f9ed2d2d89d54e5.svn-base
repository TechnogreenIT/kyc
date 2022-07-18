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
<title>Add Water Inventory - II | KYC</title>
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
		<jsp:include page="../NewCommon/env-menus.jsp" />
		<!-- include menus here end-->
		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner top-thick-border grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item"><a href="#">Consent</a></li>
						<li class="breadcrumb-item active"><a href="#">Add Water
								Inventory - II</a></li>
					</ol>
				</div>
				<header class="content__title">
					<blockquote class="blockquote">
						<h4 class="mb-0">Add Water Inventory - II</h4>
					</blockquote>
					<div class="actions">
						<span class="mantooltip hover p-1" data-jbox-title=""
							data-jbox-content="This is daily input section you can <br> fillup today's data all fields are <br>necessary to fillup daily for better analysis."><a
							class="actions__item zmdi zmdi-help"></a></span>
					</div>
				</header>
				<!-- breadcrumb end -->
				<div class="container">
					<div class="bs-example">
						<div id="allAccordion">
							<h2>
								<a href="#">Water DIrect Use & Filters</a>
							</h2>
							<div>
								<!-- Water Source & Prefilter -->
								<div class="row mt-4">
									<div class="col-12 col-sm-5 col-md-5 col-lg-5 col-xl-6 mt-1">
										<div class="form-group">
											<h6 class="font-weight-700 mt-1 mb-1">Water Source:</h6>
											<select class="select2" id='connectedTo' name="connectedTo"
												onchange="getPrefilterDetails(this);">
												<option value="">Select Connected To</option>
												<c:forEach var="preFilter" items="${preFilterList}">
													<c:choose>
														<c:when test="${preFilter.isPrefilter()== false}">
															<option value="${preFilter.getPrefilterId()}">${preFilter.getWaterSource().getSourceName()}-Without
																Prefilter</option>
														</c:when>
														<c:when test="${preFilter.isPrefilter()== true}">
															<option value="${preFilter.getPrefilterId()}">${preFilter.getWaterSource().getSourceName()}-With
																Prefilter</option>
														</c:when>
													</c:choose>
												</c:forEach>
											</select>
											<div class="invalid-feedback">Please select any !</div>
										</div>
									</div>
									<div class="col-12 col-sm-5 col-md-5 col-lg-5 col-xl-5">
										<c:forEach var="preFilter" items="${preFilterList}">
											<div class="row disable-block filterDataDiv"
												style="display: none"
												id="preFilterDiv_${preFilter.getPrefilterId()}">

												<div
													class="col-12 col-sm-5 col-md-5 col-lg-5 col-xl-5 offset-1">

													<div class="form-group">
														<label class="font-weight-bold"> Do You Have
															Pre-Filtrer?</label></br>
														<div class="radio radio--inline cursor-pointer">
															<input type="radio"
																name="preFilter_${preFilter.getPrefilterId()}"
																id="preFilterY_${preFilter.getPrefilterId()}"
																value="Yes"
																<c:out value="${preFilter.isPrefilter()== true ? 'checked' : ''} " />>
															<label class="radio__label"
																for="preFilterY_${preFilter.getPrefilterId()}">Yes</label>
														</div>
														<div class="radio radio--inline cursor-pointer">
															<input type="radio"
																name="preFilter_${preFilter.getPrefilterId()}"
																id="preFilterN_${preFilter.getPrefilterId()}" value="No"
																<c:out value="${preFilter.isPrefilter()== true ? '' : 'checked'} " />>
															<label class="radio__label"
																for="preFilterN_${preFilter.getPrefilterId()}">No</label>
														</div>
														<div class='invalid-feedback'>Invalid !</div>
													</div>

												</div>

												<div class="row mt-6 mt-4">
													<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-4 mt-2">
														<div class="checkbox">
															<input type="checkbox" name="ACF" value="ACF"
																id="prefilterACF"
																<c:out value="${preFilter.isAcf()== true ? 'checked' : ''} " />>
															<label class="checkbox__label" for="prefilterACF">ACF</label>
														</div>
													</div>
													<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-4 mt-2">
														<div class="checkbox">
															<input type="checkbox" name="PSF" value="PSF"
																id="prefilterPSF"
																<c:out value="${preFilter.isPsf()== true ? 'checked' : ''} " />>
															<label class="checkbox__label" for="prefilterPSF">PSF</label>
														</div>
													</div>
													<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-4 mt-2">
														<div class="checkbox">
															<input type="checkbox" name="DMF" value="DMF"
																id="prefilterDMF"
																<c:out value="${preFilter.isDmf()== true ? 'checked' : ''} " />>
															<label class="checkbox__label" for="prefilterDMF">DMF</label>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>

									</div>
								</div>

								<!-- buttons for add direct use or filter -->
								<div class="row">
									<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2 offset-4">
										<button class="btn btn-primary btn--icon-text"
											onclick="addDirectUseBlock();">
											<i class="zmdi zmdi-plus"></i> Direct use
										</button>
									</div>
									<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
										<button class="btn btn-primary btn--icon-text"
											onclick="addFiltersBlock();">
											<i class="zmdi zmdi-plus"></i> Filter
										</button>
									</div>
								</div>
								<div class="row mt-5">
									<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"
										id="main-container">


										<!-- add filters block -->
									<!-- 	<div class="row">
											<div
												class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 offset-2">
												<div class="form-group">
													<select class="select2" id="inventoryFilter"
														name="inventoryFilter">
														<option value="">Select Filter type</option>
														<option value="Softner">Softner</option>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
											<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
												<div class="form-group">
													<button class="btn btn-primary btn--icon size-2x"
														title="Add" onclick="addFilterBtn(this);">
														<i class="zmdi zmdi-plus"></i>
													</button>
												</div>
											</div>
										</div> -->
										<!-- add filters block -->


									</div>
								</div>

								</br> </br> </br> </br> </br>
								<!-- Filter type -->
								<!-- <div class="row">
									<div class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11">
										<h6 class="font-weight-700 mt-1 mb-1">Filters Type :</h6>
										<div class="row">
											<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
												<div class="form-group">
													<select class="select2" id='filterType' name="filterType">
														<option value="">Select Connected To</option>
														<option value="Softner">Softner</option>
														<option value="RO">RO</option>
														<option value="ACF">ACF</option>
														<option value="UV">UV</option>
													</select>
													<div class="invalid-feedback">Please select any !</div>
												</div>
											</div>
										</div>
									</div>
								</div> -->
								<!-- Filter type END-->

								<!-- Filter label END-->
								<!-- <div class="row" id="fltrLbl"></div> -->

								<!-- Filter label ie. RO1,RO2..... -->
								<!-- <div class="row">
									<div id="fltrInfo"
										class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"></div>
								</div> -->

								<!-- Button for add more filter  -->
								<!-- <div class="row mt-4 mb-4" id="fltrAddMore"></div> -->

								<!-- question Where To Use Source Water  -->
								<!-- <div class="row mt-4">
									<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">
										<div class="form-group mb-2">
											<h6 class="font-weight-700 mt-1 mb-1">
												<p>
													<span id="fltrWhereToUseSpan">Select Direct use of
														Water</span>
												</p>
											</h6>
										</div>
									</div>

									<div
										class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11 offset-1">
										<div
											class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-4 offset-xl-8">
											<button type="button"
												class="btn btn-sm light-green darken-3 text-white pt-1 pb-1 on"
												onclick="addAnoDomesticUseLbl('appendDomestic','DirectUse')">
												<i class="zmdi zmdi-plus zmdi-hc-fw"></i> Add Another
												Domestic
											</button>
										</div>
										<div class="row mt-2">
											<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
												<div class="checkbox">
													<input type="checkbox" name="directUseWater[]"
														value="Domestic1" id="useWaterSourceDomestic1"
														onclick="getUseWaterSource(this,'Domestic','',1);">
													<label class="checkbox__label"
														for="useWaterSourceDomestic1">Domestic 1</label>
												</div>
											</div>
											<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10"
												id="appendUseWaterSourceDomestic1"></div>
										</div>
										Add Another Domestic Checkbox
										<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10"
											id="appendDomestic"></div>



										<div class="row mt-2">
											<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
												<div class="checkbox">
													<input type="checkbox" name="directUseWater[]"
														value="Industrial" id="useWaterSourceIndustrial"
														onclick="getUseWaterSourceIndustrial(this,'');"> <label
														class="checkbox__label" for="useWaterSourceIndustrial">Industrial</label>
												</div>
											</div>
											<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10"
												id="appendUseWaterSourceIndustrial"></div>
										</div>


										<div
											class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-4 offset-xl-8">
											<button type="button"
												class="btn btn-sm light-green darken-3 text-white pt-1 pb-1 on"
												onclick="addAnoLaundryUseLbl('appendLaundry','DirectUse')">
												<i class="zmdi zmdi-plus zmdi-hc-fw"></i> Add Another
												Laundry
											</button>
										</div>
										<div class="row mt-2">
											<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
												<div class="checkbox">
													<input type="checkbox" name="directUseWater[]"
														value="Laundry1" id="useWaterSourceLaundry1"
														onclick="getUseWaterSource(this,'Laundry','',1);">
													<label class="checkbox__label" for="useWaterSourceLaundry1">Laundry
														1</label>
												</div>
											</div>
											<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10"
												id="appendUseWaterSourceLaundry1"></div>
										</div>
										Add Another Laundry Checkbox
										<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10"
											id="appendLaundry"></div>


										<div
											class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-4 offset-xl-8">
											<button type="button"
												class="btn btn-sm light-green darken-3 text-white pt-1 pb-1 on"
												onclick="addAnoFireHydrantUseLbl('appendFireHydrant','DirectUse')">
												<i class="zmdi zmdi-plus zmdi-hc-fw"></i> Add Another Fire
												Hydrant
											</button>
										</div>
										<div class="row mt-2">
											<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
												<div class="checkbox">
													<input type="checkbox" name="directUseWater[]"
														value="Fire Hydrant1" id="useWaterSourceFireHydrant1"
														onclick="getUseWaterSource(this,'Fire Hydrant','',1);">
													<label class="checkbox__label"
														for="useWaterSourceFireHydrant1">Fire Hydrant 1</label>
												</div>
											</div>
											<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10"
												id="appendUseWaterSourceFireHydrant1"></div>
										</div>
										Add Another Fire Hydrant Checkbox
										<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10"
											id="appendFireHydrant"></div>


										<div id="fltrUseInfo" class="row mt-2"></div>

										Use Fltr 
										<div id="useFltrDiv" class="row mt-4"></div>

									</div>
								</div> -->
								<!-- END  -->

								<!-- <div class="row mt-4 mb-4">
									<div class="col text-center">
										<button type="button"
											class="btn btn-primary btn-sm waves-effect waves-light"
											onclick="saveFilter(this);" id="save-water-btn">
											<i class="zmdi zmdi-save"></i> Save
										</button>
									</div>
								</div> -->
							</div>
						</div>
					</div>
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
		<script src="../newAssets/projectscripts/add-direct-use.js"></script>
		<script src="../newAssets/projectscripts/add-filter-use-data.js"></script>
		<script src="../newAssets/projectscripts/add-waterfilter-inventory.js"></script>
		<script src="../newAssets/projectscripts/add-water-inventory.js"></script>
		<script>
      $(document).ready(function() {
        $("#allAccordion").accordion({
          heightStyle: 'content',
          collapsible: true
        });
      });
    </script>
	</main>
</body>
</html>