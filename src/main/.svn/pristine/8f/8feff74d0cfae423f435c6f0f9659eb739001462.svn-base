<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<button class='btn btn-default'
	style='background-color: #9e9e9e; color: white; display: none;'
	id="addstack" onclick="addAirEnvDetailsThroughExcelsheet('stack')">Add
	Stack</button>
<div class="row"></div>
<div id="add_stack_details_through_excelsheet"
	class="card-body card-padding" style="display: none">
	<div class="row">
		<div class="col-sm-12">
			<div class="col-sm-6">
				<label class="text-darkbrown"> Download and Upload .csv file
					of stack details</label>
				<div class="col-sm-12">
					<button class="btn waves-effect">
						<a href="newAssets/documents/Stack.xlsx"> Download Input Data</a>
					</button>
				</div>
			</div>
			<div class="col-sm-6">
				<label class="text-darkbrown"> Download and Upload .csv file
					of stack parameter details</label>
				<div class="col-sm-12">
					<button class="btn  waves-effect">
						<a href="newAssets/documents/StackParameter.xlsx"> Download
							Input Data</a>
					</button>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="col-sm-6">
				<div class="fileinput fileinput-new" data-provides="fileinput">
					<br /> <br /> <label><b><font color="#4a2f07"
							style="font-family: sans-serif">Stack Copy: </font></b></label> <span
						class="btn btn-primary btn-file m-r-10"
						style="background-color: #9e9e9e;"> <span
						class="fileinput-new">Select file</span> <span
						class="fileinput-exists">Change</span> <input type="file"
						name="stack_file" id="stack_file" accept=".xlsx" required>
					</span> <span class="fileinput-filename"></span> <a href="#"
						class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
				</div>
				<div id="error_stack"></div>
			</div>
			<div class="col-sm-6">
				<div class="fileinput fileinput-new" data-provides="fileinput">
					<br /> <br /> <label><b><font color="#4a2f07"
							style="font-family: sans-serif">Stack Parameter Copy: </font></b></label> <span
						class="btn btn-primary btn-file m-r-10"
						style="background-color: #9e9e9e;"> <span
						class="fileinput-new">Select file</span> <span
						class="fileinput-exists">Change</span> <input type="file"
						name="stackparameter" id="stackparameter" accept=".xlsx" required>
					</span> <span class="fileinput-filename"></span> <a href="#"
						class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
				</div>
				<div id="error_stack_parameter"></div>
			</div>
		</div>
		<div class="col-sm-12">
			<center>
				<button class="btn btn-default waves-effect" name="loadstack"
					onclick="saveExcelSheet('stack', 'air', 'two', 'air', 'three')">Upload</button>
			</center>
		</div>
	</div>
</div>
<c:choose>
	<c:when test="${stackDetail.size() > 0 }">
		<div id="stack_error_msg"></div>
		<table class="table responsive" border="1">
			<thead>
				<td><b>Stack name</td>
				<td><b>Attached to</td>
				<td><b>Capacity</td>
				<td><b>Fuel type</td>
				<td><b>Fuel quan.</td>
				<td><b>MoC</td>
				<td><b>Shape</td>
				<td><b>Height</td>
				<td><b>Dia</td>
				<td><b>Stack Pollutants</td>
				<td colspan="2"><b>Modify / Delete</td>
			</thead>
			<c:forEach items="${stackDetail}" var="stackDetail" varStatus="i">
				<tr>
					<td><font color='##00688B'>${stackDetail.stackName}</font></td>
					<td><font color='##00688B'>${stackDetail.attachedTo}</font></td>
					<td><font color='##00688B'>${stackDetail.capacity}
							${stackDetail.capacityUnits}</font></td>
					<td><font color='##00688B'>${stackDetail.fuelType}</font></td>
					<td><font color='##00688B'>${stackDetail.fuelQuant}
							${stackDetail.fuelUnits}</font></td>
					<td><font color='##00688B'>${stackDetail.matCons}</font></td>
					<td><font color='##00688B'>${stackDetail.shape}</font></td>
					<td><font color='##00688B'>${stackDetail.height}
							${stackDetail.htUnits}</font></td>
					<td><font color='##00688B'>${stackDetail.diam}
							${stackDetail.diamUnits}</font></td>
					<td><font color='##00688B'> <c:forEach
								items="${stackDetail.stackPollDetailList}" var="stackPollDetail">
								${stackPollDetail.pollName}
								<label>, </label>
							</c:forEach>
					</font></td>
					<td><a href='#' class='btn btn-default'
						style='background-color: #9e9e9e; color: white;'
						data-toggle='modal' data-target='#stackModal${i.index}'>Modify</a></td>
					<td><a href='#' class='btn btn-default'
						style='background-color: #9e9e9e; color: white;'
						data-toggle='modal' data-target='#stackDeleteModal${i.index}'>Delete</a></td>
				</tr>

				<div class="modal fade" id="stackDeleteModal${i.index}"
					role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div style="background-color: #ffffff;">
								<div class="modal-header">
									<button type="button" class="close" id="stackCloseButton"
										data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body">
									<div class="row">
										<center>
											<h4>Are you sure, you want to delete the stack?</h4>
										</center>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<center>
												<button class='btn btn-default' data-dismiss='modal'
													onclick="deleteStackAmbient(${stackDetail.stackId},'stack')">Delete</button>
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
				<div class="modal fade" id="stackModal${i.index}" role="dialog"
					style="overflow-y: auto; overflow-x: hidden;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div style="background-color: #ffffff;">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<div class="row"></div>
									<center>
										<h4 class="modal-title">
											<label class="text-darkbrown">Modify Data</label>
										</h4>
									</center>
								</div>
								<div class="modal-body">
									<input type="hidden" id="stackid${stackDetail.stackId}"
										value="${stackDetail.stackId}">
									<div class="form-group">
										<div class="row"></div>
										<div class="row"></div>
										<div class="row">
											<div class="col-sm-12">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="text-darkbrown">Stack Name</label>
														<div class="fg-line">
															<div class="dtp-container fg-line">
																<input type="text" class="form-control"
																	id="stack_name${stackDetail.stackId}" name="stack_name"
																	value="${stackDetail.stackName}" required>
															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="text-darkbrown">Attached to</label>
														<div class="fg-line">
															<div class="dtp-container fg-line">
																<select class="form-control select-choosen"
																	id="attached_to${stackDetail.stackId}"
																	name="attached_to" name="attached_to" required>
																	<option value="${stackDetail.attachedTo}">${stackDetail.attachedTo}</option>
																	<c:if test="${stackDetail.attachedTo != 'DG Sets'}">
																		<option value="DG Sets">DG Sets</option>
																	</c:if>
																	<c:if test="${stackDetail.attachedTo != 'Process'}">
																		<option value="Process">Process</option>
																	</c:if>
																	<c:if test="${stackDetail.attachedTo != 'Boilers'}">
																		<option value="Boilers">Boilers</option>
																	</c:if>
																	<c:if test="${stackDetail.attachedTo != 'Others'}">
																		<option value="Others">Others</option>
																	</c:if>
																</select>
															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<label class="text-darkbrown">Capacity</label>
													<div class="form-group">
														<div class="fg-line">
															<div class="dtp-container fg-line">
																<input type="text" class="form-control"
																	id="capacity${stackDetail.stackId}" name="capacity"
																	value="${stackDetail.capacity}" required>
															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<label class="text-darkbrown">Units</label>
													<div class="form-group">
														<div class="fg-line">
															<div class="dtp-container fg-line">
																<input type="text" class="form-control"
																	id="capacity_units${stackDetail.stackId}"
																	name="capacity_units"
																	value="${stackDetail.capacityUnits}" required>
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
												<div class="col-sm-4">
													<div class="form-group">
														<label class="text-darkbrown">Material of
															Construction</label>
														<div class="fg-line">
															<div class="dtp-container fg-line">
																<input type="text" class="form-control"
																	id="mat_cons${stackDetail.stackId}" name="mat_cons"
																	value="${stackDetail.matCons}" required>
															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="text-darkbrown">Shape</label>
														<div class="fg-line">
															<input type="text" class="form-control"
																id="shape${stackDetail.stackId}" name="shape"
																value="${stackDetail.shape}" required>
														</div>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="text-darkbrown">Fuel Type</label>
														<div class="fg-line">
															<input type="text" id="fuel_type${stackDetail.stackId}"
																name="fuel_type" class="form-control"
																value="${stackDetail.fuelType}" required>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="col-sm-2">
												<label class="text-darkbrown">Height</label>
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															id="height${stackDetail.stackId}" name="height"
															value="${stackDetail.height}" required>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<label class="text-darkbrown">Units</label>
												<div class="form-group">
													<div class="fg-line">
														<div class="dtp-container fg-line">
															<input type="text" class="form-control"
																id="ht_units${stackDetail.stackId}" name="ht_units"
																value="${stackDetail.htUnits}" required>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<label class="text-darkbrown">Diameter</label>
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															id="diam${stackDetail.stackId}" name="diam"
															value="${stackDetail.diam}" required>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<label class="text-darkbrown">Units</label>
												<div class="form-group">
													<div class="fg-line">
														<div class="dtp-container fg-line">
															<input type="text" class="form-control"
																id="diam_units${stackDetail.stackId}" name="diam_units"
																value="${stackDetail.diamUnits}" required>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<label class="text-darkbrown">Fuel Quantity</label>
												<div class="form-group">
													<div class="fg-line">
														<input type="text" id="fuel_quant${stackDetail.stackId}"
															name="fuel_quant" class="form-control"
															value="${stackDetail.fuelQuant}" required>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<label class="text-darkbrown">Units</label>
												<div class="form-group">
													<div class="fg-line">
														<div class="dtp-container fg-line">
															<select class="form-control"
																id="fuel_units${stackDetail.stackId}" name="fuel_units"
																required>
																<option value="${stackDetail.fuelUnits}"
																	selected="selected">${stackDetail.fuelUnits}</option>
																<option value="" selected="selected">Select uom</option>
																<option value="NA">--NA--</option>
																<option value="Beam/M">Beam/M</option>
																<option value="Box">Box</option>
																<option value="Brass/A">Brass/A</option>
																<option value="Brass/D">Brass/D</option>
																<option value="Brass/M">Brass/M</option>
																<option value="Gel">Gel.</option>
																<option value="Kg">Kg</option>
																<option value="Kg/Year">Kg/Year</option>
																<option value="Kg/cycle">Kg/cycle</option>
																<option value="Kg/Day">Kg/Day</option>
																<option value="Kg/Hr">Kg/Hr</option>
																<option value="Kg/Month">Kg/Month</option>
																<option value="KL/Year">KL/Year</option>
																<option value="KL/Day">KL/Day</option>
																<option value="KL/Month">KL/Month</option>
																<option value="KLtr.">KLtr.</option>
																<option value="Lit/Day">Lit/Day</option>
																<option value="Ltr/Year">Ltr/Year</option>
																<option value="Ltr/Hr">Ltr/Hr</option>
																<option value="Ltr/Month">Ltr/Month</option>
																<option value="Ltrs">Ltrs</option>
																<option value="M/Day">M/Day</option>
																<option value="m/Month">m/Month</option>
																<option value="m3/Day">m3/Day</option>
																<option value="m3/Hr">m3/Hr</option>
																<option value="m3/Month">m3/Month</option>
																<option value="mg/kg">mg/kg</option>
																<option value="mg/l">mg/l</option>
																<option value="MLD">MLD</option>
																<option value="MT">MT</option>
																<option value="MT/Year">MT/Year</option>
																<option value="MT/Day">MT/Day</option>
																<option value="MT/Hr">MT/Hr</option>
																<option value="MT/Month">MT/Month</option>
																<option value="Mtrs/Day">Mtrs/Day</option>
																<option value="Mtrs/M">Mtrs/Month</option>
																<option value="Mtrs/Year">Mtrs/Year</option>
																<option value="MW">MW</option>
																<option value="No.">No.</option>
																<option value="No/Cycle">No/Cycle</option>
																<option value="No/Day">No/Day</option>
																<option value="No/Month">No/Month</option>
																<option value="Nos./Year">Nos./Year</option>
																<option value="Pcs/Year">Pcs/Year</option>
																<option value="Pcs/Month">Pcs/Month</option>
																<option value="Pieces">Pieces</option>
																<option value="Qnt/Month">Qnt/Month</option>
																<option value="Qnt/Year">Qnt/Year</option>
																<option value="Rim">Rim</option>
																<option value="Rooms">Rooms</option>
																<option value="SqFeet/Day">SqFeet/Day</option>
																<option value="SqFeet/Month">SqFeet/Month</option>
																<option value="SqFeet/Year">SqFeet/Year</option>
																<option value="Ton/Day">Ton/Day</option>
																<option value="Ton/Month">Ton/Month</option>
																<option value="Ton/Year">Ton/Year</option>
																<option value="CMD">CMD</option>
																<option value="Ton/Ton">Ton/Ton</option>
																<option value="Mwh">Mwh</option>
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
												<label class="text-darkbrown"><u>Stack
														Pollutants</u></label>
											</div>
											<div class="col-sm-12">
												<center>
													<div id="error_stack_poll_msg${i.index}"></div>
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

												<div id="reloadstackPollutants${i.index}">
													<c:forEach items="${stackDetail.stackPollDetailList}"
														var="stackPollDetail">
														<div class="row">
															<div class="col-sm-6">
																<label class="text-darkbrown-nobold">${stackPollDetail.pollName}</label>
															</div>
															<div class="col-sm-2">
																<label class="text-darkbrown-nobold">${stackPollDetail.pollLimit}</label>
															</div>
															<div class="col-sm-2">
																<label class="text-darkbrown-nobold">${stackPollDetail.unit.units}</label>
															</div>
															<div class="col-sm-2">
																<a href="#"
																	onclick="javascript:deletePollutant(${stackPollDetail.stackPollId},${stackDetail.stackId},'stack',${i.index});">
																	<img src="img/delete.png" width="45px" height="35px">
																</a>
															</div>
														</div>
													</c:forEach>
												</div>
												<div class="row"></div>
											</div class="row">
											<div class="col-sm-12">
												<center>
													<button class='btn btn-default'
														style='background-color: #9e9e9e; color: white;'
														data-toggle='modal'
														data-target='#addMorePollutants${i.index}'>Add
														Pollutant</button>
													<button type="button" class='btn btn-default'
														style='background-color: #9e9e9e; color: white;'
														data-dismiss='modal'
														onclick='javascript:modifyStack(${stackDetail.stackId});'>Modify</button>
												</center>
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
				<div class="modal fade" id="addMorePollutants${i.index}"
					role="dialog">
					<div class="modal-dialog">
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
													id="stack_pollutant_name${i.index}"
													placeholder="Pollutant Name">
											</div>
											<div class="col-sm-3">
												<input class="form-control" type="text"
													id="stack_pollutant_quantity${i.index}" placeholder="Limit">
											</div>
											<div class="col-sm-3">
												<select class="form-control select-choosen"
													id="stack_pollutant_units${i.index}"
													onkeypress="numberValidate(event)" required>
													<option value=''>Select unit</option>
													<option value='1'>MT/Hr</option>
													<option value='2'>MT/Day</option>
													<option value='3'>MT/Week</option>
													<option value='4'>MT/Month</option>
													<option value='5'>MT/Year</option>
													<option value='6'>Kg/Cycle</option>
													<option value='7'>Kg/Hr</option>
													<option value='8'>Kg/Day</option>
													<option value='9'>Kg/Week</option>
													<option value='10'>Kg/Month</option>
													<option value='11'>Kg/Year</option>
													<option value='12'>Liter/Hr</option>
													<option value='13'>Liter/Day</option>
													<option value='14'>Liter/Week</option>
													<option value='15'>Liter/Month</option>
													<option value='16'>Liter/Year</option>
													<option value='17'>KLiter/Hr</option>
													<option value='18'>KLiter/Day</option>
													<option value='19'>KLiter/Week</option>
													<option value='20'>KLiter/Month</option>
													<option value='21'>KLiter/Year</option>
													<option value='22'>Nos/Day</option>
													<option value='23'>Nos/Week</option>
													<option value='24'>Nos/Month</option>
													<option value='25'>Nos/Year</option>
													<option value='26'>Beam/Month</option>
													<option value='27'>Box</option>
													<option value='28'>Brass/Hr</option>
													<option value='29'>Brass/Day</option>
													<option value='30'>Brass/Week</option>
												</select>
												<%-- <input class="form-control" type="text" id="stack_pollutant_units${i.index}" placeholder="Unit"> --%>
											</div>
											<div class="col-sm-3">
												<center>
													<button type="button" class='btn btn-default'
														style='background-color: #4a2f07; color: white;'
														data-dismiss='modal'
														onclick='javascript:addPollutantsMore("stack",${stackDetail.stackId},${i.index});'>ADD</button>
											</div>
											</center>
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
				<div id="airstack2" class="card-body card-padding"
					style='display: none'>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<label class="text-darkbrown"> Download and Upload .csv
								file of stack details</label>
							<div class="col-sm-12">
								<button class="btn waves-effect">
									<a href="newAssets/documents/Stack.xlsx"> Download Input
										Data</a>
								</button>
							</div>
						</div>
						<div class="col-sm-6">
							<label class="text-darkbrown"> Download and Upload .csv
								file of stack parameter details</label>
							<div class="col-sm-12">
								<button class="btn  waves-effect">
									<a href="newAssets/documents/StackParameter.xlsx"> Download
										Input Data</a>
								</button>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-5">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<br /> <br /> <label><b><font color="#4a2f07"
										style="font-family: sans-serif">Stack Copy: </font></b></label> <span
									class="btn btn-primary btn-file m-r-10"
									style="background-color: #9e9e9e;"> <span
									class="fileinput-new">Select file</span> <span
									class="fileinput-exists">Change</span> <input type="file"
									name="stackfile" id="stackfile" accept=".xlsx" required>
								</span> <span class="fileinput-filename"></span> <a href="#"
									class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
							</div>
							<div id="error_stack"></div>
						</div>
						<div class="col-sm-7">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<br /> <br /> <label><b><font color="#4a2f07"
										style="font-family: sans-serif">Stack Parameter Copy: </font></b></label>
								<span class="btn btn-primary btn-file m-r-10"
									style="background-color: #9e9e9e;"> <span
									class="fileinput-new">Select file</span> <span
									class="fileinput-exists">Change</span> <input type="file"
									name="stackparameter" id="stackparameter" accept=".xlsx"
									required>
								</span> <span class="fileinput-filename"></span> <a href="#"
									class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
							</div>
							<div id="error_stack_parameter"></div>
						</div>
					</div>
					<div class="col-sm-12">
						<center>
							<button class="btn btn-default waves-effect" name="loadstack"
								onclick="saveExcelSheet('stack', 'air', 'two', 'air', 'three')">Upload</button>
						</center>
					</div>
				</div>
			</c:otherwise>
			</c:choose>
		</table>