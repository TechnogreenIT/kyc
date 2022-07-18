<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(stackDetail) gt 0}">
		<div class="mt-4">
			<input type="hidden" name="stackcount" id="stackcount"
				value="${stackDetail.size()}">
			<div class="checkbox">
				<input type="checkbox" name="stack-select-all" id="stack-select-all"
					onclick="selectAll(this,'stack')"> <label
					class="checkbox__label" for="stack-select-all">Select All</label>
			</div>
		</div>
		<table class="table normal">
			<thead>
				<tr>
					<th>#</th>
					<th>Stack name</th>
					<th>Attached to</th>
					<th>Capacity</th>
					<th>Fuel type</th>
					<th>Fuel quan.</th>
					<th>MoC</th>
					<th>Shape</th>
					<th>Height</th>
					<th>Dia</th>
					<th>Stack Pollutants</th>
				</tr>
			</thead>
			<c:forEach items="${stackDetail}" var="stackDetail">
				<tr>
					<td>
						<div class="checkbox">
							<input type="checkbox" class="stackcheckbox" name="stack_id[]"
								id="stack-${stackDetail.stackId}" value="${stackDetail.stackId}">
							<label class="checkbox__label" for="stack-${stackDetail.stackId}"></label>
						</div>
					</td>
					<td>${stackDetail.stackName}</td>
					<td>${stackDetail.attachedTo}</td>
					<td>${stackDetail.capacity}${stackDetail.capacityUnits}</td>
					<td>${stackDetail.fuelType}</td>
					<td>${stackDetail.fuelQuant}${stackDetail.fuelUnits}</td>
					<td>${stackDetail.matCons}</td>
					<td>${stackDetail.shape}</td>
					<td>${stackDetail.height}${stackDetail.htUnits}</td>
					<td>${stackDetail.diam}${stackDetail.diamUnits}</td>
					<td><c:forEach items="${stackDetail.stackPollDetailList}"
							var="stackPollDetail">
							${stackPollDetail.pollName}
							<label>, </label>
						</c:forEach></td>

				</tr>
			</c:forEach>
		</table>
		<div class="row mt-4 mb-4">
			<div class="col text-center">
				<button type="button" class="btn btn-primary btn-sm"
					onclick="saveStackData(this,'stack');" id="save-stack-btn">
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
