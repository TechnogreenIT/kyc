<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<aside id="sidebar" class="sidebar c-overflow"> <%
					String dp = "";
					if (request.getSession().getAttribute("dp") != null && !(request.getSession().getAttribute("dp")).equals("")) {
						dp = request.getSession().getAttribute("dp").toString();
					}
					else
					{
						request.getSession().removeAttribute("dp");
					}
			
				%>

	<div class="profile-menu"
		style="background: url('img/profileBackgroundImg/profile-menu${imgvalue}.png') no-repeat left top;background-size: 280px 137px;">

		<a href="#">

			<div class="profile-pic">
				<img src="data:image/jpeg;base64,<%=dp%>" alt="">
			</div>

			<div class="profile-info other-tooltip" data-toggle="tooltip"
				data-placement="bottom" data-html="true"
				title="Click here to View Your Profile ">
				<font style="text-transform: uppercase;"> <c:if
						test="${ !empty emplogindata}">${emplogindata.getEmployeeName()}</c:if>
				</font> <i class="zmdi zmdi-chevron-down"></i>
			</div>
		</a>

		<ul class="main-menu">
			<li><a href="user-profile"><c:if
						test="${! empty settingdata }">
						<font class="txtC" color="${settingdata.textColor}"></font>
					</c:if><i class="zmdi zmdi-account"></i> View Profile</a></li>
			<li><a href="change-password"><c:if
						test="${! empty settingdata }">
						<font class="txtC" color="${settingdata.textColor }"></font>
					</c:if><i class="zmdi zmdi-lock-open zmdi-hc-fw"></i> Change Password</a></li>
			<li><a href="settings"><c:if test="${! empty settingdata }">
						<font class="txtC" color="${settingdata.textColor }"></font>
					</c:if><i class="zmdi zmdi-settings zmdi-hc-fw"></i> Settings</a></li>

		</ul>
	</div>
	<ul class="main-menu">
		<li class="active"><a href="adminDesk"> <i
				class="zmdi zmdi-home"></i> Home
		</a></li>
		<li class="sub-menu"><a href="admin-addCompLink"><i
				class="zmdi zmdi-balance zmdi-hc-fw"></i>Company Profile</a>
			<ul>
				<c:set var="count" value="${counterc }" />
				<c:choose>
					<c:when test="${count<1}">
						<li><a href="<c:url value="add-company"/>"><i
								class="zmdi zmdi-plus-circle-o zmdi-hc-fw"></i>Add</a></li>
					</c:when>
					<c:when test="${count>=1}">
						<li><a href="<c:url value="view-company-profile"/>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i>View</a></li>
					</c:when>
				</c:choose>
			</ul></li>
		<li class="sub-menu"><a href="#"><i
				class="zmdi zmdi-accounts zmdi-hc-fw"></i>User Profiles</a>
			<ul>
				<li><a href="add-user"><i
						class="zmdi zmdi-accounts-add zmdi-hc-fw"></i>Add</a></li>
				<li><a href="admin-viewUsers"><i
						class="zmdi zmdi-accounts-list zmdi-hc-fw"></i> View</a></li>
			</ul></li>
	</ul>
	</aside>
</body>
</html>
