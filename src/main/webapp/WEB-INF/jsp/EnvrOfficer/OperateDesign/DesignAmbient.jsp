<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1>${ambientDetailList.size()}</h1>
<c:choose>
	<c:when test="${fn:length(ambientDetail) gt 0}">
		<div>
			<input type="hidden" name="ambientcount" id="ambientcount"
				value="${ambientDetail.size()}">
			<div class="checkbox">
				<input type="checkbox" class="ambient-select-all"
					name="stack-select-all" id="ambient-select-all"
					onclick="selectAll(this,'ambient')"> <label
					class="checkbox__label" for="ambient-select-all">Select All</label>
			</div>
		</div>
		<table class="table normal">
			<thead>
				<tr>
					<th>#</th>
					<th>Ambient Location</th>
					<th>Criteria</th>
					<th>Ambient Pollutants</th>
				</tr>
			</thead>
			<c:forEach items="${ambientDetail}" var="ambientDetail">
				<tr>
					<td>
						<div class="checkbox">
							<input type="checkbox" class="ambientcheckbox"
								name="ambient_id[]" id="ambient-${ambientDetail.ambientId}"
								value="${ambientDetail.ambientId}"> <label
								class="checkbox__label" for="ambient-${ambientDetail.ambientId}"></label>
						</div>
					</td>
					<td>${ambientDetail.ambientLocName}</td>
					<td>${ambientDetail.criteria}</td>
					<td><c:forEach items="${ambientDetail.ambientPollDetailList}"
							var="ambientPollDetail">
								${ambientPollDetail.pollName}
								<label>, </label>
						</c:forEach></td>
				</tr>
			</c:forEach>

		</table>
		<div class="row mt-4 mb-4">
			<div class="col text-center">
				<button type="button" class="btn btn-primary btn-sm"
					onclick="saveStackData(this,'ambient');" id="save-ambient-btn">
					<i class='zmdi zmdi-save'></i> Save
				</button>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<center>
			<label><b><font color="#00688B"
					style="font-family: sans-serif">No data available</font></b></label>
		</center>
	</c:otherwise>
</c:choose>
