<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page import="java.util.*"%>
<%
	ArrayList<String> urlstring = new ArrayList<String>();//Creating arraylist
	ArrayList<String> urlencpt = new ArrayList<String>();//Creating arraylist'

	urlstring.add("product"); //0
	urlstring.add("byproduct"); //1'
	urlstring.add("raw material"); //2
	urlstring.add("fuel"); //3
	urlstring.add("hwp"); //4
	urlstring.add("hwpcf"); //5
	urlstring.add("nhwp"); //6
	urlstring.add("nhwpcf"); //7
	urlstring.add("bio"); //8
	urlstring.add("source"); //9
	urlstring.add("filter"); //10
	urlstring.add("filteruse"); //11
	urlstring.add("useofwater"); //12

	for (int i = 0; i < urlstring.size(); i++) {
		String name = urlstring.get(i);

		byte[] bytesEncoded = Base64.encodeBase64(name.getBytes());

		urlencpt.add(new String(bytesEncoded));
	}
%>
<aside class="sidebar sidebar--hidden">
	<div class="scrollbar-inner">
		<div class="user">
			<div class="user__info" data-toggle="dropdown">
				<img class="user__img"
					src="data:image/jpeg;base64,
            <c:out value="${sessionScope.userProfilePic}"/>
            "
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
					class="zmdi zmdi-balance zmdi-hc-fw"></i> Company Profile <i
					class="zmdi zmdi-plus float-right"></i></a>
				<ul>
					<li><a href="view-company-profile"> <i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> View
					</a></li>
				</ul></li>
			<li class="navigation__sub"><a href=""> <i
					class="zmdi zmdi-view-list zmdi-hc-fw"></i> Daily Data <i
					class="zmdi zmdi-plus float-right"></i></a>
				<ul>
					<li><a href="view-regular-data"> <i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> View Daily Data
					</a></li>
					<li><a href="modify-daily-data-request"> <i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> View Modified Request
					</a></li>
					<li><a href="view-modified-data"> <i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> View Modified Data
					</a></li>
				</ul>
			</li>
			<li><a href="view-monitoring-reports"><i
					class="zmdi zmdi-file zmdi-hc-fw"></i> View Monitoring Data</a></li>
			<li class="navigation__sub"><a href=""><i
					class="zmdi zmdi-file-text zmdi-hc-fw"></i> Compliance Forms <i
					class="zmdi zmdi-plus float-right"></i></a>
				<ul>
					<li class="navigation__sub"><a href=""><i
							class="zmdi zmdi-file-text zmdi-hc-fw"></i> Environmental
							Statement Report(ESR) <i class="zmdi zmdi-plus float-right"></i></a>
						<ul>
							<li><a onclick="makeYearlyEsrPopUP('Man')"> <i
									class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Yearly
							</a></li>
						</ul></li>
					
					<li><a href="Water-budget"> <i class="fa fa-tint"
							aria-hidden="true"></i> Water Budget
					</a></li>
					
					<!--by pallavi..
					<li><a href="view-mData?msg=operate"> <i class="fa fa-tint"
							aria-hidden="true"></i> Water Budget
					</a></li>-->
					
					<li><a onclick="makeHazardousReturnPopUP1()"> <i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Return
							Form
					</a></li>
				</ul></li>
			<li class="navigation__sub"><a href=""><i
					class="zmdi zmdi-trending-up zmdi-hc-fw"></i> Statistics <i
					class="zmdi zmdi-plus float-right"></i></a>
				<ul>
					<c:if test="${sessionIndustryType == 'Industry'}">
						<li><a href="statistics?statisticsfor=<%=urlencpt.get(0)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Products</a></li>
						<li><a href="statistics?statisticsfor=<%=urlencpt.get(1)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Byproducts</a></li>
						<li><a href="statistics?statisticsfor=<%=urlencpt.get(2)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Raw Materials</a></li>
					</c:if>
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(3)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Fuel</a></li>
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(4)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
							From Process</a></li>
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(5)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
							From PCF</a></li>
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(6)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
							Waste From Process</a></li>
					<li><a href="statistics?statisticsfor=<%=urlencpt.get(7)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
							Waste From PCF</a></li>
					<c:if test="${sessionIndustryType == 'Hospital'}">
						<li><a href="statistics?statisticsfor=<%=urlencpt.get(8)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Bio-Medical
								Waste</a></li>
					</c:if>
				</ul></li>
			<li class="navigation__sub"><a href=""><i
					class="zmdi zmdi-trending-up zmdi-hc-fw"></i> Water Statistics <i
					class="zmdi zmdi-plus float-right"></i></a>
				<ul>
					<li><a href="waterConsumption?wc=<%=urlencpt.get(9)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Source</a></li>
					<li><a href="waterConsumption?wc=<%=urlencpt.get(10)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Filter</a></li>
					<li><a href="waterConsumption?wc=<%=urlencpt.get(11)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Filter Use </a></li>
					<li><a href="waterConsumption?wc=<%=urlencpt.get(12)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Use Of Water</a></li>
				</ul></li>
			<li><a href="view-report"><i
					class="zmdi zmdi-chart zmdi-hc-fw"></i> Graphs</a></li>
			<li class="navigation__sub"><a href=""><i
					class="zmdi zmdi-assignment-check zmdi-hc-fw"></i> Performance <i
					class="zmdi zmdi-plus float-right"></i></a>
				<ul>
					<c:if test="${sessionIndustryType == 'Industry'}">
						<li><a
							href="management-performance?performancefor=<%=urlencpt.get(0)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Products</a></li>
						<li><a
							href="management-performance?performancefor=<%=urlencpt.get(1)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Byproducts</a></li>
						<li><a
							href="management-performance?performancefor=<%=urlencpt.get(2)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Raw Materials</a></li>
					</c:if>
					<li><a
						href="management-performance?performancefor=<%=urlencpt.get(3)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Fuel</a></li>
					<li><a
						href="management-performance?performancefor=<%=urlencpt.get(4)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
							From Process</a></li>
					<li><a
						href="management-performance?performancefor=<%=urlencpt.get(5)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous Waste
							From PCF</a></li>
					<li><a
						href="management-performance?performancefor=<%=urlencpt.get(6)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
							Waste From Process</a></li>
					<li><a
						href="management-performance?performancefor=<%=urlencpt.get(7)%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Non-Hazardous
							Waste From PCF</a></li>
					<c:if test="${sessionIndustryType == 'Hospital'}">
						<li><a
							href="management-performance?performancefor=<%=urlencpt.get(8)%>"><i
								class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Bio-Medical
								Waste</a></li>
								</c:if>
					<!--
						by pallavi 
					
					<li><a href="water-performance"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Water Performance
					</a></li>
				</ul></li>-->

			<li class="navigation__sub"><a href=""><i
					class="zmdi zmdi-assignment-check zmdi-hc-fw"></i> Overall
					Environmental Performance <i class="zmdi zmdi-plus float-right"></i></a>
				<ul>
					<li><a href="waterOverall?type=<%="water"%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Water</a></li>
					<li><a href="waterOverall?type=<%="air"%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Air</a></li>
					<li><a href="waterOverall?type=<%="hazardous"%>"><i
							class="zmdi zmdi-view-headline zmdi-hc-fw"></i> Hazardous</a></li>
				</ul></li>


		</ul>
	</div>
</aside>