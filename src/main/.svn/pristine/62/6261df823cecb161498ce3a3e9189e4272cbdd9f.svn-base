<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${fn:length(wateriePollutantDetailList) gt 0}">
	<div class="row mt-4">
		<div class="col text-center">
			<p class="font-weight-bolder">Select Effluent Pollutant</p>
		</div>
	</div>
	<div class="table-responsive">
		<table class='table normal'>
			<thead>
				<tr>
					<th>#</th>
					<th>Pollutant name</th>
					<th>Limit</th>
					<th>Unit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${wateriePollutantDetailList }"
					var="wateriePollutantDetail">
					<tr>
						<td>
							<div class="checkbox">
								<input type="checkbox" class="eff_pollutant"
									name="eff_pollutant[]"
									id="eff-${wateriePollutantDetail.wateriePollutantId}"
									value="${wateriePollutantDetail.wateriePollutantId}"> <label
									class="checkbox__label"
									for="eff-${wateriePollutantDetail.wateriePollutantId}"></label>
							</div>
						</td>
						<td>${wateriePollutantDetail.pollName}</td>
						<td>${wateriePollutantDetail.quantity}</td>
						<td>${wateriePollutantDetail.unit.units}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>
<c:choose>
	<c:when test="${fn:length(wateriePollutantDetailList) gt 0}">
		<c:choose>
			<c:when test="${fn:length(waterSewPollDetailList) gt 0}">
				<div class="row mt-4">
					<div class="col text-center">
						<p class="font-weight-bolder">Select Sewage Pollutant</p>
					</div>
				</div>
				<div class="row"></div>
				<div class="table-responsive">
					<table class='table normal'>
						<thead>
							<tr>
								<th>#</th>
								<th>Pollutant name</th>
								<th>Limit</th>
								<th>Unit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${waterSewPollDetailList}"
								var="waterSewPollDetail">
								<tr>
									<td>
										<div class="checkbox">
											<input type="checkbox" class="sew_pollutant"
												name="sew_pollutant[]"
												id="sew-${waterSewPollDetail.waterSewPollId}"
												value="${waterSewPollDetail.waterSewPollId}"> <label
												class="checkbox__label"
												for="sew-${waterSewPollDetail.waterSewPollId}"></label>
										</div>
									</td>
									<td>${waterSewPollDetail.pollName}</td>
									<td>${waterSewPollDetail.quantity}</td>
									<td>${waterSewPollDetail.unit.units}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="row mt-4 mb-4">
					<div class="col text-center">
						<button type="button" class="btn btn-primary btn-sm"
							onclick="savePollutantData(this,'water');" id="save-water-btn">
							<i class='zmdi zmdi-save'></i> Save
						</button>
					</div>
				</div>
			</c:when>
		</c:choose>
	</c:when>
	<c:otherwise>
		<center>
			<label><b><font color="#00688B"
					style="font-family: sans-serif">No data available</font></b></label>
		</center>
	</c:otherwise>
</c:choose>