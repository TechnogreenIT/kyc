<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Modified Data</title>
<link rel="icon" href="img/faviconkyc.png" type="image/gif"
	sizes="12x12">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="jquery/tests/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
<script src="jquery/tests/jquery-1.9.1.js"></script>
<script src="jquery/ui/jquery.ui.effect.js"></script>
<script src="jquery/ui/jquery.ui.effect-drop.js"></script>
<script src="jquery/ui/jquery.ui.effect.js"></script>
<script src="jquery/ui/jquery.ui.effect-blind.js"></script>
<script src="jquery/ui/jquery.ui.effect-bounce.js"></script>
<script src="jquery/ui/jquery.ui.effect-clip.js"></script>
<script src="jquery/ui/jquery.ui.effect-drop.js"></script>
<script src="jquery/ui/jquery.ui.effect-explode.js"></script>
<script src="jquery/ui/jquery.ui.effect-fold.js"></script>
<script src="jquery/ui/jquery.ui.effect-highlight.js"></script>
<script src="jquery/ui/jquery.ui.effect-pulsate.js"></script>
<script src="jquery/ui/jquery.ui.effect-scale.js"></script>
<script src="jquery/ui/jquery.ui.effect-shake.js"></script>
<script src="jquery/ui/jquery.ui.effect-slide.js"></script>
<script src="jquery/ui/jquery.ui.core.js"></script>
<script src="jquery/ui/jquery.ui.widget.js"></script>
<script src="jquery/ui/jquery.ui.accordion.js"></script>
<link rel="stylesheet" href="css/demos.css">
<script src="vendors/bootgrid/jquery.bootgrid.updated.min.js"></script>
<link href="vendors/bootgrid/jquery.bootgrid.min.css" rel="stylesheet" />

<style type="text/css">
.bs-example {
	margin: 10px;
}
</style>
<style>
.loader {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
}
</style>

<script type="text/javascript">
        $(window).load(function () {
            $(".loader").fadeOut(500);
        })
	</script>
</head>
<body>
	<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
	<jsp:include page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
	<section id="main"> <section id="content">
	<div class="container">
		<div class="card">
			<div class="card-header_Regular">
				<h2>
					<div class='col-sm-9 title_form_Regular'>
						<b><font color="black" style="font-family: sans-serif">Modified
								Data</font></b>
					</div>
				</h2>
			</div>
			<div class="card-body card-padding" id="productDet">
				<div class="table-responsive">
					<table id="data-table-command"
						class="table table-striped table-vmiddle">
						<thead>
							<tr>
								<th data-column-id="id" data-type="numeric" data-order="asc">ID</th>
								<th data-column-id='product'>Name</th>
								<th data-column-id='oldQuan'>Old Quantity</th>
								<th data-column-id='newQuan'>New Quantity</th>
								<th data-column-id='pDate'>Modified on Date</th>
								<th data-column-id='sDate'>Modified of Date</th>
								<th data-column-id='pby'>By</th>
								<th data-column-id='reason'>Reason</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="ii" value="1" />
							<c:forEach items="${modifiedDataList}" var="modifiedData"
								varStatus="i">
								<%-- <c:forEach  items="${modifiedArrayList}" var="modifiedArrayData" varStatus="j">
									<c:choose>
											<c:when test="${modifiedData[0] == modifiedArrayData[3]}"> --%>
								<tr>
									<td><c:out value="${ii}" /></td>
									<td><c:out value="${modifiedData[0]}" /></td>
									<td><c:out value="${modifiedData[2]}" /> <c:out
											value="${unitRegularModified}" /></td>
									<td><c:out value="${modifiedData[5]}" /> <c:out
											value="${unitRegularModified}" /></td>
									<td><c:out value="${modifiedData[3]}" /></td>
									<td><c:out value="${modifiedData[1]}" /></td>
									<td><c:out value="${modifiedData[6]}" /></td>
									<td><c:out value="${modifiedData[4]}" /></td>
									<c:set var="ii" value="${ii+1}" />
								</tr>
								<%-- </c:when>
									</c:choose>
										
										</c:forEach> --%>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section> </section>
	</section>
	<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />

	<!-- Data Table -->
	<script type="text/javascript">
            $(document).ready(function(){
                //Command Buttons	
				$("#data-table-command").bootgrid({
					css: {
						icon: 'zmdi icon',
						iconColumns: 'zmdi-view-module',
						iconDown: 'zmdi-expand-more',
						iconRefresh: 'zmdi-refresh',
						iconUp: 'zmdi-expand-less'
					}
				});
            });
        </script>
</body>
</html>