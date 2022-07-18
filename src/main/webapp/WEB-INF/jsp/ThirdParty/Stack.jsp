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
					<input type='text' class="form-control date-picker" name="samp_st"
						id="samp_st"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Sample Collected Date"
						required>
					<div id="error_samp_st"></div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-6">
					<b><font color="#4a2f07">Report Submitted Date: </font></b>
				</div>
				<div class="col-sm-6">
					<input type='text' class="form-control date-picker" name="sub_st"
						id="sub_st"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Report Submitted Date"
						required>
					<div id="error_sub_st"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row"></div>
	<div class="col-sm-1 m-b-25"></div>
	<div class="col-sm-10 m-b-25">
		<div class="panel-group" data-collapse-color="amber"
			id="accordionAmber" role="tablist" aria-multiselectable="true">
			<c:forEach items="${stackList}" var="stack">
				<c:set var="stackNameWithoutSpace"
					value="${stack.getStackName()}-${stack.getAttachedTo()}" />
				<c:set var="stackName"
					value="${fn:replace(stackNameWithoutSpace,' ','')}" />
				<c:set var="stackName" value="${fn:replace(stackName,'.','')}" />
				<c:set var="stackName" value="${fn:replace(stackName,'/','')}" />
				<!-- <div class="panel panel-collapse"> -->
				<div class="panel-heading" role="tab">
					<h4 class="panel-title">
						<!-- <a data-toggle="collapse" data-parent="#accordionAmber" href="#accordionAmber-<?php echo $str; ?>" aria-expanded="false"> -->
						<a data-toggle="collapse" data-parent="#accordionAmber"
							href="#stack_name_${stackName}" aria-expanded="false"> <!-- <?php echo $stack_name."-".$attached_to; ?> -->
							<%-- <b>${stackName}</b> --%> <b>${stackNameWithoutSpace}</b>
						</a>
					</h4>
				</div>
				<!-- <div id="accordionAmber-<?php echo $str; ?>" class="collapse" role="tabpanel"> -->
				<div id="stack_name_${stackName}" class="collapse" role="tabpanel">
					<input type="hidden" name="stack_name[]"
						id="astack_name_${stackName}" value="${stack.getStackName()}">
					<input type="hidden" name="attached_to[]"
						id="attached_to_${stackName}" value="${stack.getAttachedTo()}">
					<input type="hidden" name="stack_id[]" id="stack_id_${stackName}"
						value="${stack.getStackId()}">
					<div class="panel-body">
						<div class="col-sm-12">
							<center>
								<h4>
									<b>STACK DETAILS</b>
								</h4>
							</center>
							<div class='table-responsive'>
								<table id='data-table-basic' class='table table-bordered'>
									<thead>
										<th data-column-id="id">Sr. No.</th>
										<th data-column-id="stackParticulars">Particulars</th>
										<th data-column-id="stackDetails">Details</th>
										<th data-column-id="stackunits">Unit</th>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Material of Stack</td>
											<td>${stack.matCons}</td>
											<td>--</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Stack Height above roof</td>
											<td>${stack.height}</td>
											<td>${stack.htUnits}</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Type of Stack</td>
											<td>${stack.shape}</td>
											<td>--</td>
										</tr>
										<tr>
											<td>4</td>
											<td>Fuel Type</td>
											<td>${stack.fuelType}</td>
											<td>--</td>
										</tr>
										<tr>
											<td>5</td>
											<td>Flue Gas Temperature</td>
											<td><input type="text" required class="form-control"
												id="gas_temp_${stackName}"
												onkeypress="numberValidate(event)">
												<div id="error_gas_temp_${stackName}"></div></td>
											<td><sup>o</sup>K</td>
										</tr>
										<tr>
											<td>6</td>
											<td>Differential Pressure</td>
											<td><input type="text" class="form-control"
												id="pressure_${stackName}"
												onkeypress="numberValidate(event)">
												<div id="error_pressure_${stackName}"></div></td>
											<td>mmWG</td>
										</tr>
										<tr>
											<td>7</td>
											<td>Diameter of Stack</td>
											<td>${stack.diam}</td>
											<td>${stack.diamUnits}</td>
										</tr>
										<tr>
											<td>8</td>
											<td>Velocity</td>
											<td><input type="text" class="form-control"
												id="velocity_${stackName}"
												onkeypress="numberValidate(event)">
												<div id="error_velocity_${stackName}"></div></td>
											<td>m/s</td>
										</tr>
										<tr>
											<td>9</td>
											<td>Hours of Operation</td>
											<td><input type="text" class="form-control"
												id="hrs_${stackName}" onkeypress="numberValidate(event)">
												<div id="error_hrs_${stackName}"></div></td>
											<td><select class="chosen" id="hrs_units_${stackName}">
													<option>Select
													<option>
													<option>Minutes
													<option>
													<option>Hours
													<option>
													<option>Days
													<option>
											</select>
												<div id="error_hrs_units_${stackName}"></div></td>
										</tr>
										<tr>
											<td>10</td>
											<td>Gas Volume</td>
											<td><input type="text" class="form-control"
												id="volume_${stackName}" onkeypress="numberValidate(event)">
												<div id="error_volume_${stackName}"></div></td>
											<td>Nm<sup>3</sup>/Hr
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="row"></div>
							<div class="row"></div>
							<center>
								<h4>
									<b>TEST PARAMETERS</b>
								</h4>
							</center>
							<%-- <b><c:out value="${stack.getStackName()}-${stack.getAttachedTo()}">TEST TEST</c:out></b> --%>
							<div class='table-responsive'>
								<table id='data-table-basic' class='table table-bordered'>
									<thead>
										<th data-column-id="id">Sr. No.</th>
										<th data-column-id="stackParticulars">Parameters</th>
										<th data-column-id="stackDetails">Result</th>
										<th data-column-id="stackunits">Unit</th>
									</thead>
									<tbody>
										<c:set var="count" value="0" />
										<c:set var="numberOfRows" value="0" />
										<c:set var="stackiid" value="${stack.getStackId()}" />
										<c:forEach items="${stackPoll}" var="stackPoll">
											<%-- <c:set var="i" value="${i+1}"/> --%>
											<c:set var="numberOfRows" value="${numberOfRows+1}" />
											<c:choose>
												<c:when test="${stackPoll.stack.getStackId()==stackiid}">
													<tr>
														<td><c:out value="${numberOfRows}" /></td>
														<td><input type="hidden"
															id="pollutant_name_${stackName}_${count}"
															value="${stackPoll.getPollName()}">
															${stackPoll.getPollName()}</td>

														<td><input type="text" class="form-control"
															id="pollutant_quantity_${stackName}_${count}"
															onkeypress="numberValidate(event)">
															<div id="error_pollutant_quantity_${stackName}_${count}"></div>
														</td>

														<td><input type="hidden"
															id="pollutant_unit_${stackName}_${count}"
															value="${stackPoll.unit.units}"> <c:set
																var="pollunit" value="mg/Nm3" /> <c:choose>
																<c:when test="${stackPoll.unit.units==pollunit}">mg/Nm<sup>3</sup>
																</c:when>
																<c:otherwise>
        																${stackPoll.unit.units}
   																 	</c:otherwise>
															</c:choose></td>
													</tr>
													<c:set var="count" value="${count+1}" />
												</c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose>

											<c:if test="${stackPoll.stack.getStackId()!=stackiid}">
												<c:set var="numberOfRows" value="0" />
											</c:if>

										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<input type="hidden" value="${count}" id="count_${stackName}">
						<div class="form-group">
							<div class="col-sm-5">
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<label><b><font color="#4a2f07"
											style="font-family: sans-serif"> Stack Monitering
												Copy: </font></b></label> <span class="btn btn-primary btn-file m-r-10"
										style="background-color: #9e9e9e;"> <span
										class="fileinput-new">Select file</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="stack_file_${stackName}" id="stack_file_${stackName}">
									</span> <span class="fileinput-filename"></span> <a href="#"
										class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
								</div>
							</div>
							<div class="col-sm-12" id="error_stack_file_${stackName}">
								<label>File name should not contain any special
									characters.</label>
							</div>
						</div>
						<div class="col-sm-12">
							<center>
								<button type="button" onclick="saveStData('${stackName}')"
									name="stack_${stackName}" id="btn_stack_${stackName}"
									class="btn btn-info" style="background-color: #4a2f07;">Submit</button>
							</center>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="col-sm-1 m-b-25"></div>
</div>