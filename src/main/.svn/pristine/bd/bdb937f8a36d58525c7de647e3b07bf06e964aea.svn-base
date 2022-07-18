<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<button class='btn btn-default'
	style='background-color: #9e9e9e; color: white; display: none;'
	id="addambient" onclick="addAirEnvDetailsThroughExcelsheet('ambient')">Add
	Ambient</button>
<div class="row"></div>
<div id="add_ambient_details_through_excelsheet"
	class="card-body card-padding" style="display: none">
	<div class="col-sm-12">
		<div class="col-sm-6">
			<label class="text-darkbrown"> Download and Upload .csv file
				of ambient details</label>
			<div class="col-sm-12">
				<button class="btn waves-effect">
					<a href="newAssets/documents/Ambient.xlsx"> Download Input Data</a>
				</button>
			</div>
		</div>
		<div class="col-sm-6">
			<label class="text-darkbrown"> Download and Upload .csv file
				of ambient parameter details</label>
			<div class="col-sm-12">
				<button class="btn  waves-effect">
					<a href="newAssets/documents/AmbientParameter.xlsx"> Download
						Input Data</a>
				</button>
			</div>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="col-sm-6">
			<div class="fileinput fileinput-new" data-provides="fileinput">
				<br /> <br /> <label><b><font color="#4a2f07"
						style="font-family: sans-serif">Ambient Copy: </font></b></label> <span
					class="btn btn-primary btn-file m-r-10"
					style="background-color: #9e9e9e;"> <span
					class="fileinput-new">Select file</span> <span
					class="fileinput-exists">Change</span> <input type="file"
					name="ambient_file" id="ambient_file" accept=".xlsx" required>
				</span> <span class="fileinput-filename"></span> <a href="#"
					class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
			</div>
			<div id="error_ambient"></div>
		</div>
		<div class="col-sm-6">
			<div class="fileinput fileinput-new" data-provides="fileinput">
				<br /> <br /> <label><b><font color="#4a2f07"
						style="font-family: sans-serif">Ambient Parameter Copy: </font></b></label> <span
					class="btn btn-primary btn-file m-r-10"
					style="background-color: #9e9e9e;"> <span
					class="fileinput-new">Select file</span> <span
					class="fileinput-exists">Change</span> <input type="file"
					name="ambientparameter" id="ambientparameter" accept=".xlsx"
					required>
				</span> <span class="fileinput-filename"></span> <a href="#"
					class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
			</div>
			<div id="error_ambient_parameter"></div>
		</div>
	</div>
	<div class="col-sm-12">
		<center>
			<button class="btn btn-default waves-effect" name="loadstack"
				onclick="saveExcelSheet('ambient', 'air', 'three', 'water', 'three')">Upload</button>
		</center>
	</div>
</div>

<c:choose>
	<c:when test="${ambientDetail.size() > 0 }">
		<table class="table responsive" border="1">
			<thead>
				<td><b>#</td>
				<td><b>Ambient Location</td>
				<td><b>Criteria</td>
				<td><b>Ambient Pollutants</td>
				<td colspan="2"><b>Modify/Delete</td>
			</thead>
			<c:forEach items="${ambientDetail}" var="ambientDetail" varStatus="i"
				begin="0">
				<tr>
					<td><font color='##00688B'>${i.index + 1}</font></td>
					<td><font color='##00688B'>${ambientDetail.ambientLocName}</font></td>
					<td><font color='##00688B'>${ambientDetail.criteria}</font></td>
					<td><font color='##00688B'> <c:forEach
								items="${ambientDetail.ambientPollDetailList}"
								var="ambientPollDetail">
						${ambientPollDetail.pollName}
						<label>, </label>
							</c:forEach></font></td>
					<td><a href='#' class='btn btn-default'
						style='background-color: #9e9e9e; color: white;'
						data-toggle='modal' data-target="#ambientModal${i.index}">Modify</a></td>
					<td><a href='#' class='btn btn-default'
						style='background-color: #9e9e9e; color: white;'
						data-toggle='modal' data-target="#ambientDeleteModal${i.index}">Delete</a></td>
				</tr>
				<div class="modal fade" id="ambientDeleteModal${i.index}"
					role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div style="background-color: #ffffff;">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body">
									<div class="row">
										<center>
											<h4>Are you sure, you want to delete the ambient?</h4>
										</center>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<center>
												<button class='btn btn-default' data-dismiss='modal'
													onclick="deleteStackAmbient(${ambientDetail.ambientId},'ambient')">Delete</button>
												<button type="button" class='btn btn-default'
													style='background-color: #4a2f07; color: white;'
													data-dismiss='modal'>Cancel</button>
											</center>
										</div>
									</div>
									<div class="modal-footer"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Modal -->
				<div class="modal fade" id="ambientModal${i.index}" role="dialog"
					style="overflow-y: auto; overflow-x: hidden;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" style="background-color: #ffffff;">
							<div class="modal-header">
								<div class="row"></div>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<center>
									<h4 class="modal-title">
										<label class="text-darkbrown">Modify Data</label>
									</h4>
								</center>
							</div>
							<div class="modal-body">
								<input type="hidden" id="ambientid${ambientDetail.ambientId}"
									value="${ambientDetail.ambientId}">
								<div class="form-group">
									<div class="row"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="col-sm-6">
												<div class="form-group">
													<div class="fg-line">
														<label class="text-darkbrown">Location Identity</label>
														<div class="dtp-container fg-line">
															<input required type="text" class="form-control"
																id="loc_ident${ambientDetail.ambientId}"
																name="loc_ident" value="${ambientDetail.ambientLocName}">
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<div class="fg-line">
														<label class="text-darkbrown">Siting Criteria</label>
														<div class="dtp-container fg-line">
															<select class='form-control select-choosen'
																id="criteria${ambientDetail.ambientId}" name="criteria">
																<option>${ambientDetail.criteria}</option>
																<option>Up Wind</option>
																<option>Down Wind</option>
																<option>Cross Wind</option>
																<option>Other</option>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-12">
											<div class="col-sm-12">
												<label class="text-darkbrown">Ambient Pollutants</label>
											</div>
											<div class="col-sm-12">
												<center>
													<div id="error_ambient_poll_msg"></div>
												</center>
											</div>
											<div class="col-sm-12">
												<div class="row">
													<div class="col-sm-6">
														<label class="text-darkbrown">Pollutant Name</label>
													</div>
													<div class="col-sm-2">
														<label class="text-darkbrown">Limit</label>
													</div>
													<div class="col-sm-2">
														<label class="text-darkbrown">Units</label>
													</div>
													<div class="col-sm-2">
														<label class="text-darkbrown">Delete</label>
													</div>
												</div>
												<div id="reloadambientPollutants${i.index}">
													<c:forEach items="${ambientDetail.ambientPollDetailList}"
														var="ambientPollDetail">
														<div class="row">
															<%-- <div class="col-sm-6">
																<input type="text" class="form-control" name="poll_name[]" value="${ambientPollDetail.pollName}"><br />
															</div>
															<div class="col-sm-2">
																<input type="text" class="form-control" name="limit[]" value="${ambientPollDetail.limits}">
															</div>
															<div class="col-sm-2">
																<input type="text" class="form-control" name="units[]" value="${ambientPollDetail.unit.units}">
															</div> --%>
															<div class="col-sm-6">
																<label class="text-darkbrown-nobold">${ambientPollDetail.pollName}</label>
															</div>
															<div class="col-sm-2">
																<label class="text-darkbrown-nobold">${ambientPollDetail.limits}</label>
															</div>
															<div class="col-sm-2">
																<label class="text-darkbrown-nobold">${ambientPollDetail.unit.units}</label>
															</div>
															<div class="col-sm-2">
																<a href="#"
																	onclick="javascript:deletePollutant(${ambientPollDetail.ambientPollId},${ambientDetail.ambientId},'ambient');">
																	<img src="img/delete.png" width="45px" height="35px">
																</a>
															</div>
														</div>
													</c:forEach>
												</div>
												<div class="row">
													<div class="col-sm-12">
														<center>
															<button class='btn btn-default'
																style='background-color: #9e9e9e; color: white;'
																data-toggle='modal'
																data-target='#addAmMorePollutants${i.index}'>Add
																Pollutant</button>
															<button type="button" class='btn btn-default'
																style='background-color: #9e9e9e; color: white;'
																data-dismiss='modal'
																onclick='javascript:modifyAmbient(${ambientDetail.ambientId});'>Modify</button>
														</center>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer"></div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="addAmMorePollutants${i.index}"
					role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div style="background-color: #ffffff;">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<div class="row"></div>
									<center>
										<h4 class="modal-title">
											<label class="text-darkbrown">Add Pollutant</label>
										</h4>
									</center>
								</div>
								<div class="modal-body">
									<div class="row"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="col-sm-3">
												<input class="form-control" type="text"
													id="ambient_pollutant_name${i.index}"
													placeholder="Pollutant Name">
											</div>
											<div class="col-sm-3">
												<input class="form-control" type="text"
													id="ambient_pollutant_quantity${i.index}"
													placeholder="Limit">
											</div>
											<div class="col-sm-3">
												<select class='form-control select-choosen'
													id='ambient_pollutant_units${i.index}' required>
													<option value=''>Select unit</option>
													<c:forEach items="${unit}" var="unit">
														<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-sm-3">
												<center>
													<button type="button" class='btn btn-default'
														style='background-color: #4a2f07; color: white;'
														data-dismiss='modal'
														onclick='javascript:addPollutantsMore("ambient",${ambientDetail.ambientId},${i.index});'>ADD</button>
												</center>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer"></div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<center>
					<label><b><font color="#00688B"
							style="font-family: sans-serif">No data available</font></b></label>
				</center>
				<div id="ambient2" class="card-body card-padding"
					style='display: none'>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<label class="text-darkbrown"> Download and Upload .csv
								file of ambient details</label>
							<div class="col-sm-12">
								<button class="btn waves-effect">
									<a href="newAssets/documents/Ambient.xlsx"> Download Input
										Data</a>
								</button>
							</div>
						</div>
						<div class="col-sm-6">
							<label class="text-darkbrown"> Download and Upload .csv
								file of ambient parameter details</label>
							<div class="col-sm-12">
								<button class="btn  waves-effect">
									<a href="newAssets/documents/AmbientParameter.xlsx">
										Download Input Data</a>
								</button>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-5">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<br /> <br /> <label><b><font color="#4a2f07"
										style="font-family: sans-serif">Ambient Copy: </font></b></label> <span
									class="btn btn-primary btn-file m-r-10"
									style="background-color: #9e9e9e;"> <span
									class="fileinput-new">Select file</span> <span
									class="fileinput-exists">Change</span> <input type="file"
									name="ambient_file" id="ambient_file" accept=".xlsx" required>
								</span> <span class="fileinput-filename"> </span> <a href="#"
									class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
							</div>
							<div id="error_ambient"></div>
						</div>
						<div class="col-sm-7">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<br /> <br /> <label><b><font color="#4a2f07"
										style="font-family: sans-serif">Ambient Parameter Copy:
									</font></b></label> <span class="btn btn-primary btn-file m-r-10"
									style="background-color: #9e9e9e;"> <span
									class="fileinput-new">Select file</span> <span
									class="fileinput-exists">Change</span> <input type="file"
									name="ambientparameter" id="ambientparameter" accept=".xlsx"
									required>
								</span> <span class="fileinput-filename"></span> <a href="#"
									class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
							</div>
							<div id="error_ambient_parameter"></div>
						</div>
					</div>
					<div class="col-sm-12">
						<center>
							<button class="btn btn-default waves-effect" name="loadambient"
								onclick="saveExcelSheet('ambient', 'air', 'three', 'water', 'three')">Upload</button>
						</center>
					</div>
				</div>
			</c:otherwise>
			</c:choose>
		</table>