<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="ct"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="img/faviconkyc.png" type="image/gif"
	sizes="12x12">
<title>Water Performance</title>

<style>
#profile-description .show-more-height {
	height: 62px;
	overflow: hidden;
}
</style>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#tabs").tabs();
	});
	jq(function() {
		jq("#tabs").tabs({
			beforeLoad : function(event, ui) {
				ui.panel.html(jq('#ind').clone()); // the only line one I added to the official sample
				ui.jqXHR.fail(function() {
					ui.panel.html("ERROR: Couldn't load this tab.");
				});
			}
		});
	});
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="vendors/bootgrid/jquery.bootgrid.updated.min.js"></script>
<link href="vendors/bootgrid/jquery.bootgrid.min.css" rel="stylesheet">
<script>
	$(function() {
		// run the currently selected effect
		function runEffect() {
			// get effect type from
			var selectedEffect = "drop";
			// most effect types need no options passed by default
			var options = {};
			// run the effect
			$("#effect").show();
		};
		//getWaterPerformanceQuality();
	});
</script>
<script type="text/javascript" src="js/ahp.js"></script>
</head>
<body>
	<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />

	<c:if test="${userRole == 'Environmental Officer'}">
	
		<jsp:include page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
	</c:if>

	<c:if test="${userRole == 'Management'}">
		<jsp:include
			page="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" />
			
	</c:if>
	<section id="main"> <section id="content">
	<div class="container">
		<input type="hidden" id="performance_type" value="${type}">
		<c:if test="${type == 'water'}">
			<div class="card">
				<div class="card-graph">
					<div class="title_graph">
						<font color="black"
							style="font-family: sans-serif; font-size: 15px;"><b>WATER
								- ENVIRONMENTAL PERFORMANCE</b></font>
					</div>
					<input type="hidden" id="performance_prevDate" value="0"> <input
						type="hidden" id="performance_newDate" value="0"> <input
						type="hidden" id="yearId" value="0">
				</div>
				<div class="card-body card-padding">
					<div id="tabs">
						<ul>
							<div id="btns">
								<li style="float: right;">
									<button id="print" class='btn btn-default waves-effect'
										style='padding: 5px 15px;' onclick="printDiv('chart_div')">
										<i class="zmdi zmdi-print zmdi-hc-fw"
											style="font-size: large;"></i>
									</button>
								</li>
							</div>
						</ul>
						<div id="tabs-3">
							<div class="row">
								<div class="col-sm-12"
									style="margin-top: 35px; margin-bottom: 10px; margin-left: 15px;">
									<h4>
										<font color="grey"><b>Water Environmental
												Performance
												<div id="str2"></div>
										</b></font>
									</h4>
								</div>
							</div>
							<div class="row">
								<div class="row">
									<div id="noDataWaterPerformance"></div>
								</div>

								<li><u>WWTP Performance</u></li>
								<div class="row" id="performanceStatBoth"></div>





								<li><u>ETP Performance</u></li>
								<div class="row">
									<div id="performanceStat_ETP"></div>
								</div>

					

								<li><u>STP Performance</u></li>
								<div class="row">
									<div id="performanceStat_STP"></div>
								</div>

								<input type="hidden" id="combinePerformance"
									value="<c:out value="${isBoth}"/>"> <input
									type="hidden" id="etpPerformance"
									value="<c:out value="${isETP}"/>"> <input type="hidden"
									id="stpPerformance" value="<c:out value="${isSTP}"/>">
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${type == 'air'}">
			<div class="card">
				<div class="card-graph">
					<div class="title_graph">
						<font color="black"
							style="font-family: sans-serif; font-size: 15px;"><b>Air
								- ENVIRONMENTAL PERFORMANCE</b></font>
					</div>
					<input type="hidden" id="performance_prevDate" value="0"> <input
						type="hidden" id="performance_newDate" value="0"> <input
						type="hidden" id="yearId" value="0">
				</div>
				<div class="card-body card-padding">
					<div id="tabs">
						<ul>
							<div id="btns">
								<li style="float: right;">
									<button id="print" class='btn btn-default waves-effect'
										style='padding: 5px 15px;' onclick="printDiv('chart_div')">
										<i class="zmdi zmdi-print zmdi-hc-fw"
											style="font-size: large;"></i>
									</button>
								</li>
							</div>
						</ul>
						<div id="tabs-3">
							<div class="row">
								<div class="col-sm-12"
									style="margin-top: 35px; margin-bottom: 10px; margin-left: 15px;">
									<h4>
										<font color="grey"><b>Air Environmental Performance
												<div id="str2"></div>
										</b></font>
									</h4>
								</div>
							</div>
							<div class="row">
								<div class="row">
									<div id="noDataWaterPerformance"></div>
								</div>

								<li><u>AirOverall Performance</u></li>
								<div class="row" id="performanceStatBoth"></div>

								<li><u>Stack Performance</u></li>
								<div class="row">
									<div id="performanceStat_Stack"></div>
								</div>

								<li><u>Ambient Performance</u></li>
								<div class="row">
									<div id="performanceStat_Ambient"></div>
								</div>

								<input type="hidden" id="combinePerformance"
									value="<c:out value="${isBoth}"/>"> <input
									type="hidden" id="etpPerformance"
									value="<c:out value="${isETP}"/>"> <input type="hidden"
									id="stpPerformance" value="<c:out value="${isSTP}"/>">
							</div>
							<!--  mm-->
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${type == 'hazardous'}">
			<div class="card">
				<div class="card-graph">
					<div class="title_graph">
						<font color="black"
							style="font-family: sans-serif; font-size: 15px;"><b>WATER
								- ENVIRONMENTAL PERFORMANCE</b></font>
					</div>
					<input type="hidden" id="performance_prevDate" value="0"> <input
						type="hidden" id="performance_newDate" value="0"> <input
						type="hidden" id="yearId" value="0">
				</div>
				<div class="card-body card-padding">
					<div id="tabs">
						<ul>
							<div id="btns">
								<li style="float: right;">
									<button id="print" class='btn btn-default waves-effect'
										style='padding: 5px 15px;' onclick="printDiv('chart_div')">
										<i class="zmdi zmdi-print zmdi-hc-fw"
											style="font-size: large;"></i>
									</button>
								</li>
							</div>
						</ul>
						<div id="tabs-3">
							<div class="row">
								<div class="col-sm-12"
									style="margin-top: 35px; margin-bottom: 10px; margin-left: 15px;">
									<h4>
										<font color="grey"><b>Solid Waste Overall
												Performance.
												<div id="str2"></div>
										</b></font>
									</h4>
								</div>
							</div>
							<div class="row">
								<div class="row">
									<div id="noDataWaterPerformance"></div>
								</div>

								<li><u>Solid Waste Performance</u></li>
								<div class="row" id="performanceHz"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>



	</div>
	</section> </section>
	<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />
</body>
</html>