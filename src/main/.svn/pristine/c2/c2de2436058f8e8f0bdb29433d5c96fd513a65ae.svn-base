<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="ct"%>



<div id="temp">


	<ul class="list-unstyled c">
		<c:if test="${sessionIndustryType == 'Industry'}">
			<li><a href="#" onclick="createSideGraph('pw', '' )"> <u>Production
						VS Water Consumption</u></a></li>
			<c:set var="productUnitList" value="${ProductUnitList}" />

			<c:choose>
				<c:when test="${fn:length(productUnitList) gt 0}">
					<li class="active"><a href="#products" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">Product</a>
						<ul class="collapse list-unstyled" id="products">
							<c:forEach items="${productUnitList}" varStatus="i">
								<li><a href="#"
									onclick="createSideGraph('Product','${productUnitList[i.index]}')">
										<u>Products in (${productUnitList[i.index]})</u>
								</a></li>
							</c:forEach>
						</ul></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" onclick="createSideGraph('Product','NA')"><u>Product</u></a></li>
				</c:otherwise>
			</c:choose>

			<!-- Byproduct -->
			<c:set var="byProductUnitList" value="${ByProductUnitList}" />

			<c:choose>
				<c:when test="${fn:length(ByProductUnitList) gt 0}">
					<li class="active"><a href="#byproducts"
						data-toggle="collapse" aria-expanded="false"
						class="dropdown-toggle">Byproduct</a>
						<ul class="collapse list-unstyled" id="byproducts">
							<c:forEach items="${ByProductUnitList}" varStatus="i">
								<li><a href="#"
									onclick="createSideGraph('Byproduct','${ByProductUnitList[i.index]}')"><u>
											Byproduct in (${ByProductUnitList[i.index]})</u></a></li>
							</c:forEach>
						</ul></li>

				</c:when>
				<c:otherwise>
					<li><a href="#" onclick="createSideGraph('Byproduct','NA')"><u>Byproduct</u></a></li>
				</c:otherwise>
			</c:choose>

			<!-- Raw -->
			<c:set var="rawUnitList" value="${RawUnitList}" />

			<c:choose>
				<c:when test="${fn:length(rawUnitList) gt 0}">
					<li class="active"><a href="#raw" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">Raw Material</a>
						<ul class="collapse list-unstyled" id="raw">
							<c:forEach items="${rawUnitList}" varStatus="i">
								<li><a href="#"
									onclick="createSideGraph('Raw Material','${rawUnitList[i.index]}')"><u>Raw
											Material in (${rawUnitList[i.index]})</u></a></li>
							</c:forEach>
						</ul></li>

				</c:when>
				<c:otherwise>
					<li><a href="#" onclick="createSideGraph('Raw Material','NA')"><u>Raw
								Material</u></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>


		<!-- Fuel -->
		<c:set var="fuelUnitList" value="${FuelUnitList}" />

		<c:choose>
			<c:when test="${fn:length(fuelUnitList) gt 0}">
				<li class="active"><a href="#fuel" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Fuel</a>
					<ul class="collapse list-unstyled" id="fuel">
						<c:forEach items="${fuelUnitList}" varStatus="i">
							<li><a href="#"
								onclick="createSideGraph('Fuel', '${fuelUnitList[i.index]}')"><u>
										Fuel in (${fuelUnitList[i.index]})</u></a></li>
						</c:forEach>
					</ul></li>

			</c:when>
			<c:otherwise>
				<li><a href="#" onclick="createSideGraph('Fuel', 'NA')"><u>Fuel</u></a></li>

			</c:otherwise>
		</c:choose>

		<!-- water consumption -->
		<li class="active"><a href="#water" data-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle">Water Consumption</a>
			<ul class="collapse list-unstyled" id="water">
				<li><a href="#" onclick="createSideGraph('swr','NA')"><u>Source
							Zone</u></a></li>
				<li><a href="#" onclick="createSideGraph('fr', 'NA')"><u>Waste
							Water Treatment Zone</u></a></li>
				<li><a href="#" onclick="createSideGraph('fru', 'NA')"><u>Waste
							Water TreatmentUse Zone</u></a></li>
				<li><a href="#" onclick="createSideGraph('fwur', 'NA')"><u>Filtered
							Water Use Zone</u></a></li>
				<li><a href="#" onclick="createSideGraph('swur', 'NA')"><u>Source
							Water Used Zone</u></a></li>
				<li><a href="#" onclick="createSideGraph('tpr', 'NA')"><u>Waste
							Water Zone</u></a></li>
				<li><a href="#" onclick="createSideGraph('pfw', 'NA')"><u>PreFilter
							Water Zone</u></a></li>
			</ul></li>

		<!-- hwp -->
		<c:set var="hWPUnitList" value="${HWPUnitList}" />
		<c:choose>
			<c:when test="${fn:length(hWPUnitList) gt 0}">
				<li class="active"><a href="#hwp" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Hazardous Waste
						from Process</a>
					<ul class="collapse list-unstyled" id="hwp">
						<c:forEach items="${hWPUnitList}" varStatus="i">
							<li><a href="#"
								onclick="createSideGraph('hwp','${hWPUnitList[i.index]}')"><u>
										Hazardous Waste from Process in (${hWPUnitList[i.index]})</u></a></li>
						</c:forEach>
					</ul></li>

			</c:when>
			<c:otherwise>
				<li><a href="#" onclick="createSideGraph('hwp','NA')"><u>
							Hazardous Waste from Process</u></a></li>
			</c:otherwise>
		</c:choose>

		<!-- hwpcf -->
		<c:set var="hWPCFUnitList" value="${HWPCFUnitList}" />
		<c:choose>
			<c:when test="${fn:length(hWPCFUnitList) gt 0}">
				<li class="active"><a href="#hwpcf" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Hazardous Waste
						from PCF</a>
					<ul class="collapse list-unstyled" id="hwpcf">
						<c:forEach items="${hWPCFUnitList}" varStatus="i">
							<li><a href="#"
								onclick="createSideGraph('hwpcf','${hWPCFUnitList[i.index]}')"><u>
										Hazardous Waste from PCF in (${hWPCFUnitList[i.index]})</u></a></li>
						</c:forEach>
					</ul></li>

			</c:when>
			<c:otherwise>
				<li><a href="#" onclick="createSideGraph('hwpcf','NA')"><u>
							Hazardous Waste from PCF</u></a></li>
			</c:otherwise>
		</c:choose>

		<!-- nhwp -->
		<c:set var="nHWPUnitList" value="${NHWPUnitList}" />

		<c:choose>
			<c:when test="${fn:length(nHWPUnitList) gt 0}">
				<li class="active"><a href="#nhwp" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Non-Hazardous
						Waste from Process</a>
					<ul class="collapse list-unstyled" id="nhwp">
						<c:forEach items="${nHWPUnitList}" varStatus="i">
							<li><a href="#"
								onclick="createSideGraph('nhwp', '${nHWPUnitList[i.index]}')"><u>Non-Hazardous
										Waste from Process in (${nHWPUnitList[i.index]})</u></a></li>
						</c:forEach>
					</ul></li>

			</c:when>
			<c:otherwise>
				<li><a href="#" onclick="createSideGraph('nhwp', 'NA')"><u>Non-Hazardous
							Waste from Process</u></a></li>
			</c:otherwise>
		</c:choose>


		<!-- nhwpcf -->
		<c:set var="nHWPCFUnitList" value="${NHWPCFUnitList}" />
		<c:choose>
			<c:when test="${fn:length(nHWPCFUnitList) gt 0}">
				<li class="active"><a href="#nhwpcf" data-toggle="collapse"
					aria-expanded="false" class="dropdown-toggle">Non-Hazardous
						Waste from PCF</a>
					<ul class="collapse list-unstyled" id="nhwpcf">
						<c:forEach items="${nHWPCFUnitList}" varStatus="i">
							<li><a href="#"
								onclick="createSideGraph('nhwpcf','${nHWPCFUnitList[i.index]}')"><u>Non-Hazardous
										Waste from PCF in (${nHWPCFUnitList[i.index]})</u></a></li>
						</c:forEach>
					</ul></li>

			</c:when>
			<c:otherwise>
				<li><a href="#" onclick="createSideGraph('nhwpcf', 'NA')"><u>Non-Hazardous
							Waste from PCF</u></a></li>
			</c:otherwise>
		</c:choose>


		<!-- Hospitals -->
		<c:if test="${sessionIndustryType == 'Hospitals'}">
			<c:set var="bioMedicalUnitList" value="${BioMedicalUnitList}" />
			<c:choose>
				<c:when test="${fn:length(bioMedicalUnitList) gt 0}">
					<li class="active"><a href="#nhwpcf" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">Bio-Medical</a>
						<ul class="collapse list-unstyled" id="nhwpcf">
							<c:forEach items="${bioMedicalUnitList}" varStatus="i">
								<li><a href="#"
									onclick="createSideGraph('Bio-Medical', '${bioMedicalUnitList[i.index]}')"><u>Bio-Medical
											in (${bioMedicalUnitList[i.index]})</u></a></li>
							</c:forEach>
						</ul></li>

				</c:when>
				<c:otherwise>
					<li><a href="#" onclick="createSideGraph('Bio-Medical','NA')"><u>Bio-Medical</u></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>


	</ul>
</div>
