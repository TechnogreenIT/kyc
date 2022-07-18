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
					class="zmdi zmdi-view-compact"></i> Monitoring Forms</a>
				<ul>
					<li><a href="monitoring-add"> <i
							class="zmdi zmdi-view-list zmdi-hc-fw"></i> Add
					</a></li>
				</ul></li>
		</ul>
	</div>
</aside>
