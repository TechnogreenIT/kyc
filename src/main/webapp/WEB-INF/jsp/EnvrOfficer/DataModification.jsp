<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script src="jquery/tests/jquery-1.9.1.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

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
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
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
<script type="text/javascript">
        $(window).load(function () {
            $(".loader").fadeOut(500);
        })
		
		/* function validateQuantity(id){
        	debugger;
			var new_quantity = id.value;
			var product_name = document.getElementById("pname").value;
			var check_quan = document.getElementById("check-quantity").value;
			if(new_quantity != check_quan){
				var update_for = $("#update_for").val();
				var t = $("#reg_pro_name").val();
				fwd_url = "ajax-checkValidity?type=quan&product_name="+product_name+"&new_quantity="+new_quantity+"&check_quan="+check_quan+"&product_type="+t+"update_for"+update_for;
				if (window.XMLHttpRequest) {
					// code for IE7+, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				} else {
					// code for IE6, IE5
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
						
				xmlhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						var res = this.responseText.trim();
						//alert(res);
						if(res == "true"){ 
							document.getElementById("msg").innerHTML = "Allowed";
						}else if(res == "false"){
							document.getElementById("msg").innerHTML = "Something is wrong. Please  cross check again.";
						}else if(res == "complied"){
							document.getElementById("msg").innerHTML = "Non-complied";
						}else if(res == "increase"){
							document.getElementById("msg").innerHTML = "If production rate will be same throughout the month/year, then it will cross the consented capacity!";
						}
					}
				};
				xmlhttp.open("GET",fwd_url,true);
				xmlhttp.send();
			}else{
				document.getElementById("msg").innerHTML = "";
			}
			
		} */
		function goOnBackPage(){
			window.location='envr-officer-view-regular-data';
		}
		function submitData(){
			
			var id = $("#reg_id").val();
			var update_for = $("#update_for").val();
			var new_quantity = document.getElementById("quantity").value;
			var old_quantity = document.getElementById("check-quantity").value;
			var product_name = document.getElementById("pname").value;
			var reason = document.getElementById("reason").value;
			var redirect = 'ajax-submitDataUpdate?type=update';
			redirectPost(redirect, {rd_id: id,product_name: product_name,quan: new_quantity,old_quantity: old_quantity,update_for: update_for,reason: reason});
		}
		function redirectPost(location, args){
			var form = '';
				$.each( args, function( key, value ) {
					form += '<input type="hidden" name="'+key+'" value="'+value+'">';
				});
				$('<form action="'+location+'" method="POST">'+form+'</form>').appendTo('body').submit();
		}
    </script>
<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
<jsp:include page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
<title>Data Modification</title>
</head>
<body>

	<c:set value="${ModifiedData}" var="modifiedData"></c:set>
	<section id="main"> <section id="content">
	<div class="container">
		<div class="card">
			<div class="card-header_Regular">
				<h2>
					<div class='col-sm-9 title_form_Regular'>
						<b><font color="black" style="font-family: sans-serif">Modify
								data of ${modifiedData[2]} of day ${modifiedData[1]}</font></b>
					</div>
					<div class='col-sm-3'>
						<b><font color="black" style="font-family: sans-serif">Date
								:</font></b> ${modifiedData[1]}<br>
					</div>
				</h2>
			</div>
			<input type="hidden" id="reg_pro_name" value="${modifiedData[3]}">
			<input type="hidden" id="reg_id" value="${modifiedData[0]}">
			<input type="hidden" id="update_for" value="${modifiedData[1]}">
			<div class="card-body card-padding" id="productDet">
				<form action="">
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class="col-sm-2"></div>
								<div class="col-sm-8">
									<label class="text-orange"> <i
										class="zmdi zmdi-alert-circle-o zmdi-hc-fw"></i> If you keep
										on modifying data, this is going to affect data performance!
									</label>
								</div>
								<div class="col-sm-2"></div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-4"></div>
								<div class="col-sm-5">
									<label class="text-red"> <i
										class="zmdi zmdi-refresh-sync-off zmdi-hc-fw"></i>DON'T
										REFRESH THE PAGE!
									</label> </label>
								</div>
								<div class="col-sm-3"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4">
							<div class="fg-line">
								<c:choose>
									<c:when test="${modifiedData[3]=='nhwp'}">
										<label><b><font color="#4a2f07"
												style="font-family: sans-serif">Non-Hazardous Waste
													from Process </font></b></label>
										<div class="row"></div>
									</c:when>

									<c:when test="${modifiedData[3]=='nhwpcf'}">
										<label><b><font color="#4a2f07"
												style="font-family: sans-serif">Non-Hazardous Waste
													from PCF </font></b></label>
										<div class="row"></div>
									</c:when>

									<c:when test="${modifiedData[3]=='hwp'}">
										<label><b><font color="#4a2f07"
												style="font-family: sans-serif">Hazardous Waste from
													Process </font></b></label>
										<div class="row"></div>
									</c:when>

									<c:when test="${modifiedData[3]=='hwpcf'}">
										<label><b><font color="#4a2f07"
												style="font-family: sans-serif">Hazardous Waste from
													PCF </font></b></label>
										<div class="row"></div>
									</c:when>

									<c:otherwise>
										<label><b><font color="#4a2f07"
												style="font-family: sans-serif">${modifiedData[3]}</font></b></label>
										<div class="row"></div>
									</c:otherwise>

								</c:choose>
								<label value="${modifiedData[2]}" name="pname" disabled>${modifiedData[2]}</label>
								<input type="hidden" class="form-control"
									value="${modifiedData[2]}" id="pname" name="pname"> <input
									type="hidden" class="form-control" value="${modifiedData[0]}"
									id="pid" name="pid">
							</div>
						</div>
						<div class="col-xs-2">
							<div class="fg-line">
								<label><b><font color="#4a2f07"
										style="font-family: sans-serif">Quantity </font></b></label>
								<div class="row"></div>
								<input type="text" class="form-control"
									value="${modifiedData[4]}" id="quantity" name="quantity"
									onkeypress="numberValidate(event)">
								<!-- required onblur="validateQuantity(this)" -->
								<input type="hidden" class="form-control"
									value="${modifiedData[4]}" id="check-quantity">
							</div>
						</div>
						<div class="col-xs-2">
							<div class="fg-line">
								<label><b><font color="#4a2f07"
										style="font-family: sans-serif">Units </font></b></label>
								<div class="row"></div>
								<label value="${modifiedData[5]}" name="units" disabled>${modifiedData[5]}</label>
								<input type="hidden" class="form-control"
									value="${modifiedData[5]}" id="units" name="units">
							</div>
						</div>
						<div class="col-xs-4">
							<label><b><font color="#4a2f07"
									style="font-family: sans-serif">Warnings/ Errors </font></b></label>
							<div class="row"></div>
							<div id="msg"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="fg-line">
								<label><b><font color="#4a2f07"
										style="font-family: sans-serif">Reason </font></b></label>
								<div class="row"></div>
								<input type="text" class="form-control" placeholder="Reason"
									minlength="500" name="reason" id="reason">
							</div>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="row">
							<div class="fg-line">
								<center>
									<button type="button" class='btn btn-default'
										style="background-color: #9161cc; color: white;"
										onclick="goOnBackPage()">Go back</button>
									<button type="button" name="" class='btn btn-default'
										style="background-color: #9161cc; color: white;"
										onclick="submitData()">Submit</button>
								</center>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section> </section>
</body>
<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />
</html>