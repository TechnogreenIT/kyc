<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../jquery/tests/jquery-1.9.1.js"></script>
<style type="text/css">
.toggle-switch[data-ts-color="blue"] input:checked+.ts-helper {
	background: rgba(33, 150, 243, 0.5);
}
</style>
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
<script src="jquery/tests/jquery-1.9.1.js"></script>
<link
	href="vendors/bower_components/lightgallery/light-gallery/css/lightGallery.css"
	rel="stylesheet">
<link rel="icon" href="img/faviconkyc.png" type="image/gif"
	sizes="12x12">
</head>
<body>
	<jsp:include page="../CommonWebpages/HeaderWebpages/CommonHeader.jsp" />
	<jsp:include page="../CommonWebpages/MenusWebpages/OfficerMenus.jsp" />
	<%-- <%
 String user_id=request.getParameter("id");
session.setAttribute("user_id", user_id);
String istatus="ON";
String bstatus="ON";
String sql=
%> --%>
	<!-- $user_id = $_SESSION['id'];
	$istatus = "ON";
	$bstatus = "ON";
	$sql = "select * from settings where login_id = '$user_id'";
	$rs= mysql_query($sql);	
	if($rs){
		$no_row_a = mysql_num_rows($rs);
		if($no_row_a > 0){
			while ($rowa = mysql_fetch_array($rs))
			{
				$istatus = $rowa['IntroductoryVideo_status'];
				$bstatus = $rowa['BirthdayVideo_status'];
			}
		}
	}
?> -->

	<script>
$(function () {
	var status = "";
	var title = "";
	$("#introductory").click(function () {
		if ($(this).is(":checked"))
		{
			 status = "ON";
			 title = "Introductory Video";
			 swtichStatus(status, title);
		}
		else
		{
			status = "OFF";
			title = "Introductory Video";
			swtichStatus(status, title);
		}
	});
});
$(function () {
	var status = "";
	var title = "";
	$("#birthday").click(function () {
		if ($(this).is(":checked"))
		{
			 status = "ON";
			 title = "Birthday Video";
			 swtichStatus(status, title);
		}
		else
		{
			status = "OFF";
			title = "Birthday Video";
			swtichStatus(status, title);
		}
	});
});
function swtichStatus(status, title){
	alert(" status:"+status+" and title="+title);
	 $.ajax({
		url:"ajax-changeStatus",
		type :"POST",
		data: ({
			actionStatus : 'actionStatus',
			status:status,
			title: title
        }),
        success:function(data){
        	alert(data);
	 var fwd_url = "ajax-changeStatus";
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			//alert("response : "+this.responseText);
		}
	};
	xmlhttp.open("POST",fwd_url,true);
	xmlhttp.send();
        }
	 }); 
} 

function saveTheme(){
	var pval = $("input[name='profile']:checked").val();
	var txtColor = "#000000";
	if(pval == 1 || pval == 5 || pval == 6 || pval == 8 || pval == 9 || pval == 10 || pval == 11 || pval == 12 || pval == 13 ||
		pval == 14 || pval == 17 || pval == 18  || pval == 20 || pval == 24 || pval == 26 || pval == 27 || pval == 28 || 
		pval == 29 || pval == 30 || pval == 37 || pval == 39 || pval == 40 || pval == 42 || pval == 43 || pval == 45 ||  
		pval == 48 || pval == 50 || pval == 51 || pval == 52 || pval == 53 || pval == 54){
		txtColor = "#ffffff";
	}
	
	var fwd_url = "ajax-changeTheme";
	$.ajax({
		type : 'POST',
		url : fwd_url,
		data: ({pval: pval,
			txtColor:txtColor,
			action: 'changeTheme'}),
			
		success : function(data) {
			if(data != 0){
				var img_path = "url('img/profileBackgroundImg/profile-menu"+data+".png')";
				$(".profile-menu").css('background-image', img_path);
				$(".txtC").css('color', txtColor);
			}
		}
	});
}
</script>
	<section id="main"> <section id="content">
	<div class="container">
		<div class="card">
			<div class="card-profile">
				<div class="title_profile">
					<b><font color="black"
						style="font-family: sans-serif; font-size: 15px;"><b>SETTINGS</b></font></b>
				</div>
			</div>
			<div class="card-body card-padding" id="consentDetail">
				<div class="row">
					<div class="col-sm-12">
						<div class="col-sm-12">
							<p>
								<b>Change setting of video play </b>
							</p>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-6">
								<label for="introductory" class="ts-label">1)
									Introductory Video</label>
							</div>
							<div class="col-sm-6">
								<div class="toggle-switch" data-ts-color="blue">
									<c:choose>
										<c:when test="${settingdata.introductoryVideoStatus=='ON'}">
									OFF&nbsp;&nbsp;<input id="introductory" type="checkbox"
												hidden="hidden" onclick="swtichStatus()" checked>
										</c:when>
										<c:otherwise>
									OFF&nbsp;&nbsp;<input id="introductory" type="checkbox"
												hidden="hidden">
										</c:otherwise>
									</c:choose>
									<label for="introductory" class="ts-helper"></label>&nbsp;&nbsp;&nbsp;ON
								</div>
							</div>
						</div>

						<div class="col-sm-12">
							<div class="col-sm-6">
								<br /> <label for="birthday" class="ts-label">2)
									Birthday Video</label>
							</div>
							<div class="col-sm-6">
								<div class="toggle-switch" data-ts-color="blue">
									<br />
									<c:choose>
										<c:when test="${settingdata.birthdayVideoStatus=='ON'}">
									OFF&nbsp;&nbsp;<input id="birthday" type="checkbox"
												hidden="hidden" onclick="swtichStatus()" checked>
										</c:when>
										<c:otherwise>
									OFF&nbsp;&nbsp;<input id="birthday" type="checkbox"
												hidden="hidden">
										</c:otherwise>
									</c:choose>

									<label for="birthday" class="ts-helper"></label>&nbsp;&nbsp;&nbsp;ON
								</div>
							</div>
						</div>

						<div class="col-sm-12">
							<div class="col-sm-12">
								<br /> <label for="introductory" class="ts-label">3)
									Change Profile Background Image (Double to select)</label>
							</div>
							<div class="col-sm-12">
								<c:forEach var="i" begin="1" end="54">
									<label class="radio radio-inline m-r-20"> <input
										type="radio" name="profile" id="profile" value=" ${ i}"
										onclick="saveTheme();"> <i class="input-helper"></i></label>
									<div class="lightbox photos">
										<div data-src="img/profileBackgroundImg/profile-menu${ i}.png"
											class="col-md-2 col-sm-4 col-xs-6">
											<div class="lightbox-item p-item">
												<img src="img/profileBackgroundImg/profile-menu${ i}.png"
													alt="img/profileBackgroundImg/loading-gif.gif">
											</div>
										</div>
									</div>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section> </section>

	<jsp:include page="../CommonWebpages/FooterWebpages/Footer.jsp" />

</body>
<script
	src="vendors/bower_components/lightgallery/light-gallery/js/lightGallery.min.js"></script>
</html>
