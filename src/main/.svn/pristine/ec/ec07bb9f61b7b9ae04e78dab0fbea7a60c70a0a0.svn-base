<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<aside class="sidebar sidebar--hidden">
	<div class="scrollbar-inner">
		<div class="user">
			<div class="user__info" data-toggle="dropdown">
				<img class="user__img"
					src="data:image/jpeg;base64,<c:out value="${sessionScope.userProfilePic}"/>"
					id="user-profile-pic" alt="user-profile">
				<div>
					<div class="user__name">
						<c:if test="${ !empty emplogindata}">${emplogindata.getEmployeeName()}</c:if>
					</div>
					<div class="user__email">
						<c:if test="${ !empty emplogindata}">${emplogindata.getEmail()}</c:if>
					</div>
				</div>
			</div>

			<div class="dropdown-menu">
				<a class="dropdown-item" href="user-profile">View Profile</a> <a
					class="dropdown-item" href="settings">Settings</a> <a
					class="dropdown-item" href="logout">Logout</a>
			</div>
		</div>

		<ul class="navigation">
			<li><a href="dashboard"><i class="zmdi zmdi-home"></i> Home</a></li>

			<li class="navigation__sub"><a href=""><i
					class="zmdi zmdi-balance zmdi-hc-fw"></i>Company Profile</a>
				<ul>
					<c:set var="count" value="${companyCount}" />
					<c:choose>
						<c:when test="${count<1}">
							<li><a href="add-company"><i
									class="zmdi zmdi-plus-circle-o zmdi-hc-fw"></i> &nbsp;Add</a></li>
						</c:when>
						<c:when test="${count>=1}">
							<li><a href="view-company-profile"><i
									class="zmdi zmdi-view-headline zmdi-hc-fw"></i> &nbsp;View</a></li>
						</c:when>
					</c:choose>
				</ul></li>

			<li><a href="view-users"><i class="zmdi zmdi-accounts "></i>
					User Profiles</a></li>

		</ul>
	</div>
</aside>