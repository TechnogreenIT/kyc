<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="ct"%>
<%@ page isELIgnored="false"%>
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-6">
					<b><font color="#4a2f07">Sample Collected Date: </font></b>
				</div>
				<div class="col-sm-6">
					<input type='text' class="form-control date-picker" name="samp_amb"
						id="samp_amb"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Sample Collected Date"
						required>
					<div id="error_samp_amb"></div>
				</div>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-6">
					<b><font color="#4a2f07">Report Submitted Date: </font></b>
				</div>
				<div class="col-sm-6">
					<input type='text' class="form-control date-picker" name="sub_amb"
						id="sub_amb"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Report Submitted Date"
						required>
					<div id="error_sub_amb"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row"></div>
	<div class="col-sm-1 m-b-25"></div>
	<div class="col-sm-10 m-b-25">
		<div class="panel-group" data-collapse-color="amber" id="ambient1"
			role="tablist" aria-multiselectable="true">
			<c:forEach items="${ambientList}" var="ambient">
				<c:set var="ambientNameWithoutSpace"
					value="${ambient.getAmbientLocName()}-${ambient.getCriteria()}" />
				<c:set var="ambientName"
					value="${fn:replace(ambientNameWithoutSpace,' ', '')}" />
				<c:set var="ambientName" value="${fn:replace(ambientName,'.', '')}" />
				<!-- <div class="panel panel-collapse"> -->
				<div class="panel-heading" role="tab">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#ambient1"
							href="#ambient_name_${ambientName}" aria-expanded="false"> <b>${ambientNameWithoutSpace}</b>
						</a>
					</h4>
				</div>
				<div id="ambient_name_${ambientName}" class="collapse"
					role="tabpanel">
					<input type="hidden" name="ambient_name[]"
						id="aambient_name_${ambientName}"
						value="${ambient.getAmbientLocName()}"> <input
						type="hidden" name="criteria[]" id="criteria_${ambientName}"
						value="${ambient.getCriteria()}"> <input type="hidden"
						name="ambient_id[]" id="ambient_id_${ambientName}"
						value="${ambient.getAmbientId()}">
					<div class="panel-body">
						<div class="col-sm-12">
							<center>
								<h4>
									<b>TEST PARAMETERS</b>
								</h4>
							</center>
							<div class='table-responsive'>
								<table id='data-table-basic' class='table table-bordered'>
									<thead>
										<th data-column-id="id">Sr. No.</th>
										<th data-column-id="ambientParticulars">Parameters</th>
										<th data-column-id="ambientDetails">Result</th>
										<th data-column-id="ambientunits">Unit</th>
									</thead>
									<tbody>
										<c:set var="count" value="0" />
										<c:set var="numberOfRows" value="0" />
										<c:set var="ambientiid" value="${ambient.getAmbientId()}" />
										<c:forEach items="${ambientPollList}" var="ambientPoll">
											<c:set var="numberOfRows" value="${numberOfRows+1}" />

											<c:choose>
												<c:when
													test="${ambientPoll.ambient.getAmbientId()==ambientiid}">
													<tr>
														<td><c:out value="${numberOfRows}" /></td>
														<td><input type="hidden"
															id="pollutant_name_${ambientName}_${count}"
															value="${ambientPoll.getPollName()}">
															${ambientPoll.getPollName()}</td>
														<td><input type="text" class="form-control"
															id="pollutant_quantity_${ambientName}_${count}"
															onkeypress="numberValidate(event)">
															<div
																id="error_pollutant_quantity_${ambientName}_${count}"></div>
														</td>
														<td><input type="hidden"
															id="pollutant_unit_${ambientName}_${count}"
															value="${ambientPoll.unit.units}"> <c:set
																var="pollunit" value="mg/Nm3" /> <c:choose>
																<c:when test="${ambientPoll.unit.units==pollunit}">
        																mg/Nm<sup>3</sup>
																</c:when>
																<c:otherwise>
        																${ambientPoll.unit.units}
   																 	</c:otherwise>
															</c:choose></td>
													</tr>
													<c:set var="count" value="${count+1}" />
												</c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose>

											<c:if
												test="${ambientPoll.ambient.getAmbientId()!=ambientiid}">
												<c:set var="numberOfRows" value="0" />
											</c:if>

										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<input type="hidden" value="${count}" id="count_${ambientName}">
						<div class="form-group">
							<div class="col-sm-5">
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<label><b><font color="#4a2f07"
											style="font-family: sans-serif"> Ambient Monitering
												Copy: </font></b></label> <span class="btn btn-primary btn-file m-r-10"
										style="background-color: #9e9e9e;"> <span
										class="fileinput-new">Select file</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="ambient_file_${ambientName}"
										id="ambient_file_${ambientName}">
									</span> <span class="fileinput-filename"></span> <a href="#"
										class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
								</div>
							</div>
							<div class="col-sm-12" id="error_ambient_file_${ambientName}"></div>
						</div>
						<div class="col-sm-12">
							<center>
								<button type="button"
									onclick="saveAmbientData('${ambientName}')"
									name="ambient_${ambientName}" id="btn_ambient_${ambientName}"
									class="btn btn-info" style="background-color: #4a2f07;">Submit</button>
							</center>
						</div>
					</div>
				</div>
				<!-- </div> -->
			</c:forEach>
			<!-- </div> -->
		</div>
	</div>
	<div class="col-sm-1 m-b-25"></div>
</div>