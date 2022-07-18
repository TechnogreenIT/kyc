<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row"></div>
<center>
	<label><b>Select Effluent Pollutant</b></label>
</center>
<a href='#' data-toggle='modal' data-target='#effluentModal'
	class='btn btn-default'
	style='background-color: #9e9e9e; color: white;'><b>Add
		Pollutant</b></a>
<div class="row"></div>
<div class="table-responsive">
	<table id='data-table-basic' class='table table-bordered'>

		<thead>
			<td><b>#</td>
			<td><b>Pollutant name</td>
			<td><b>Limit</td>
			<td><b>Unit</td>
			<td colspan="2"><b>Modify/Delete</td>
		</thead>
		<body>
			<c:choose>
				<c:when test="${wateriePollutantListDetails.size() > 0 }">
					<c:forEach items="${wateriePollutantListDetails}" var="wpList"
						varStatus="i" begin="0">
						<tr>
							<td><font color='##00688B'>${i.index + 1}</font></td>
							<td><font color='##00688B'>${wpList.pollName}</font></td>
							<td><font color='##00688B'>${wpList.quantity}</font></td>
							<td><font color='##00688B'>${wpList.unit.units}</font></td>
							<td><a href='#' data-toggle='modal' class='btn btn-default'
								style='background-color: #9e9e9e; color: white;'
								data-target='#modifyEffModal${wpList.wateriePollutantId}'>Modify</a></td>
							<td><a href='#' data-toggle='modal' class='btn btn-default'
								style='background-color: #9e9e9e; color: white;'
								data-target='#deleteEffModal${wpList.wateriePollutantId}'>Delete</a></td>
						</tr>
						<!-- Delete Effluent -->
						<div class="modal fade"
							id="deleteEffModal${wpList.wateriePollutantId}" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div style="background-color: #ffffff;">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>
										<div class="modal-body">
											<div class="row">
												<center>
													<h4>
														Are you sure, you want to delete
														<?php echo $poll_name ;?>
														?
													</h4>
												</center>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<center>
														<button class='btn btn-default' data-dismiss='modal'
															onclick="deleteWaterPollutant(${wpList.wateriePollutantId},'effluent')">Delete</button>
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
						<!-- Effluent Modal -->
						<div class="modal fade"
							id="modifyEffModal${wpList.wateriePollutantId}" role="dialog">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
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
										<input type="hidden" id="effId${wpList.wateriePollutantId}"
											value="${wpList.wateriePollutantId}">
										<div class="form-group">
											<div class="row"></div>
											<div class="row">
												<div class="col-xs-12">
													<div class="col-xs-6">
														<div class="fg-line">
															<input required type="text" class="form-control"
																value="${wpList.pollName}"
																id="eff_poll${wpList.wateriePollutantId}" />
														</div>
													</div>
													<div class="col-xs-3">
														<div class="fg-line">
															<input type="text" class="form-control"
																value="${wpList.quantity}"
																id="eff_limit${wpList.wateriePollutantId}"
																oninput="noZero(this)"
																onkeypress="numberValidate(event)">
														</div>
													</div>
													<div class="col-xs-3">
														<div class="fg-line">
															<%-- <input type="text" class="form-control" value="${wpList.unit.units}" id="eff_units${wpList.wateriePollutantId}"> --%>
															<select class='form-control select-choosen'
																id='eff_units${wpList.wateriePollutantId}' required>
																<option value='${wpList.unit.unitId}'>${wpList.unit.units}</option>
																<c:forEach items="${unit}" var="unit">
																	<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<center>
										<button type="button" class='btn btn-default'
											style='background-color: #9e9e9e; color: white;'
											data-dismiss='modal'
											onclick='javascript:modifyEffluent(${wpList.wateriePollutantId});'>Modify</button>
										<div class="modal-footer"></div>
									</center>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6"><center>
								<label><b><font color="##00688B"
										style="font-family: sans-serif">No Data Found</font></b></label>
							</center></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</body>
	</table>
</div>
<div class="row"></div>
<div class="row"></div>
<center>
	<label><b>Select Sewage Pollutant</b></label>
</center>
<a href='#' data-toggle='modal' data-target='#sewageModal'
	class='btn btn-default'
	style='background-color: #9e9e9e; color: white;'><b>Add
		Pollutant</b></a>
<div class="row"></div>
<div class="table-responsive">
	<table id='data-table-basic' class='table table-bordered'>
		<thead>
			<td><b>#</td>
			<td><b>Pollutant name</td>
			<td><b>Limit</td>
			<td><b>Unit</td>
			<td colspan="2"><b>Modify/Delete</td>
		</thead>
		<body>
			<c:choose>
				<c:when test="${waterSewPollListDetails.size() > 0 }">
					<c:forEach items="${waterSewPollListDetails}" var="wspList"
						varStatus="i" begin="0">
						<tr>
							<td><font color='##00688B'>${i.index + 1}</font></td>
							<td><font color='##00688B'>${wspList.pollName}</font></td>
							<td><font color='##00688B'>${wspList.quantity}</font></td>
							<td><font color='##00688B'>${wspList.unit.units}</font></td>
							<td><a href='#' data-toggle='modal' class='btn btn-default'
								style='background-color: #9e9e9e; color: white;'
								data-target='#modifySewModal${wspList.waterSewPollId}'>Modify</a></td>
							<td><a href='#' data-toggle='modal' class='btn btn-default'
								style='background-color: #9e9e9e; color: white;'
								data-target='#deleteSewModal${wspList.waterSewPollId}'>Delete</a></td>
						</tr>
						<!-- Delete Sewage -->
						<div class="modal fade"
							id="deleteSewModal${wspList.waterSewPollId}" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div style="background-color: #ffffff;">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>
										<div class="modal-body">
											<div class="row">
												<center>
													<h4>Are you sure, you want to delete
														${wspList.pollName}?</h4>
												</center>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<center>
														<button class='btn btn-default' data-dismiss='modal'
															onclick="deleteWaterPollutant(${wspList.waterSewPollId},'sewage')">Delete</button>
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
						<!-- Sewage Modal -->
						<div class="modal fade"
							id="modifySewModal${wspList.waterSewPollId}" role="dialog">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
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
										<input type="hidden" id="sewId${wspList.waterSewPollId}"
											value="${wspList.waterSewPollId}">
										<div class="form-group">
											<div class="row"></div>
											<div class="row">
												<div class="col-xs-12">
													<div class="col-xs-6">
														<div class="fg-line">
															<input required type="text" class="form-control"
																value="${wspList.pollName}"
																id="sew_poll${wspList.waterSewPollId}" />
														</div>
													</div>
													<div class="col-xs-3">
														<div class="fg-line">
															<input type="text" class="form-control"
																value="${wspList.quantity}"
																id="sew_limit${wspList.waterSewPollId}"
																oninput="noZero(this)"
																onkeypress="numberValidate(event)">
														</div>
													</div>
													<div class="col-xs-3">
														<div class="fg-line">
															<%-- <input type="text" class="form-control" value="${wspList.unit.units}" id="sew_units${wspList.waterSewPollId}"> --%>
															<select class='form-control select-choosen'
																id='sew_units${wspList.waterSewPollId}' required>
																<option value='${wspList.unit.unitId}'>${wspList.unit.units}</option>
																<c:forEach items="${unit}" var="unit">
																	<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<center>
										<button type="button" class='btn btn-default'
											style='background-color: #9e9e9e; color: white;'
											data-dismiss='modal'
											onclick='javascript:mofifySewage(${wspList.waterSewPollId});'>Modify</button>
										<div class="modal-footer"></div>
									</center>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6"><center>
								<label><b><font color="##00688B"
										style="font-family: sans-serif">No Data Found</font></b></label>
							</center></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</body>
	</table>
</div>
<div class="row"></div>
<div class="row"></div>
<!-- Effluent Modal -->
<div class="modal fade" id="effluentModal" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="row"></div>
				<center>
					<h4 class="modal-title">
						<label class="text-darkbrown">Add Effluent Pollutant</label>
					</h4>
				</center>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<div class="row"></div>
					<div class="row">
						<div class="col-xs-12">
							<div class="col-xs-6">
								<div class="fg-line">
									<input required type="text" class="form-control"
										placeholder="Effluent Pollutant Name" id="ie_poll" />
								</div>
							</div>
							<div class="col-xs-3">
								<div class="fg-line">
									<input type="text" class="form-control" placeholder="Limits"
										id="ie_limit" oninput="noZero(this)"
										onkeypress="numberValidate(event)">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="fg-line">
									<!-- <input type="text" class="form-control" placeholder="Units" id="ie_units"> -->
									<select class='form-control select-choosen' id='ie_units'
										required>
										<option value=''>Select unit</option>
										<c:forEach items="${unit}" var="unit">
											<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<center>
				<button type="button" class='btn btn-default'
					style='background-color: #9161cc; color: white;'
					data-dismiss='modal' onclick='javascript:addEffluent();'>Add</button>
			</center>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
<!-- Operate Effluent Modal -->
<div class="modal fade" id="effluentModal" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Add Effluent Pollutant</h4>
			</div>
			<div class="modal-body">
				<!-- <?php $date = date("Y-m-d");
				$query = "SELECT w.* from waterie_pollutant w, consent c where valid_upto > '$date'";
				$rs = mysql_query($query);
				if($rs){
					$count = mysql_num_rows($rs);
					if($count > 0){
						$query1 = "SELECT w.* from waterie_pollutant_op wop, consent c, consent_extended_date ce
									where (ce.valid_upto >= '$today_date' or c.valid_upto >= '$today_date') and 
									and ce.consent_id = c.id and c.cons_status != 'EXPIRED' and wop.cons_no = '$id'";
						$rs1 = mysql_query($query1);
						if($rs1){
							$count1 = mysql_num_rows($rs1);
							if($count1 > 0){
								while ($row = mysql_fetch_array($rs)){
									while ($row1 = mysql_fetch_array($rs1)){
									
									}
								}
							}
						}
					}
				}
				?> -->
				<div class="form-group">
					<div class="row">
						<div class="col-xs-12">
							<div class="col-xs-6">
								<div class="fg-line">
									<input required type="text" class="form-control"
										placeholder="Effluent Pollutant Name" id="ie_poll" />
								</div>
							</div>
							<div class="col-xs-3">
								<div class="fg-line">
									<input type="text" class="form-control" placeholder="Limits"
										id="ie_limit">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="fg-line">
									<input type="text" class="form-control" placeholder="Units"
										id="ie_units">
									<%-- <select class='form-control select-choosen' id='ie_units' required>
										<option value=''>Select unit</option>
										<c:forEach items="${unit}" var="unit">
											<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
										</c:forEach>
									</select> --%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<button type="button" class='btn btn-default'
				style='margin-left: 400px; background-color: #9161cc; color: white;'
				data-dismiss='modal' onclick='javascript:addEffluent();'>Add</button>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
<!-- Sewage Modal -->
<div class="modal fade" id="sewageModal" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="row"></div>
				<center>
					<h4 class="modal-title">
						<label class="text-darkbrown">Add Sewage Pollutant</label>
					</h4>
				</center>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<div class="row"></div>
					<div class="row">
						<div class="col-xs-12">
							<div class="col-xs-6">
								<div class="fg-line">
									<input required type="text" class="form-control"
										placeholder="Sewage Pollutant Name" id="sew_poll" />
								</div>
							</div>
							<div class="col-xs-3">
								<div class="fg-line">
									<input type="text" class="form-control" placeholder="Limits"
										id="sew_limit" oninput="noZero(this)"
										onkeypress="numberValidate(event)">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="fg-line">
									<!-- <input type="text" class="form-control" placeholder="Units" id="sew_units"> -->
									<select class='form-control select-choosen' id='sew_units'
										required>
										<option value=''>Select unit</option>
										<c:forEach items="${unit}" var="unit">
											<option value="${unit.getUnitId()}">${unit.getUnits()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row"></div>
			<button type="button" class='btn btn-default'
				style='margin-left: 324px; background-color: #9161cc; color: white;'
				data-dismiss='modal' onclick='javascript:addSewage();'>Add</button>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>