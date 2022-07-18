<!-- Water Effluent Pollutant Starts -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form method="post" name="reg_eff" id="waterEffForm" action=""
	enctype="multipart/form-data" onsubmit="return validate()">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-6">
					<b><font color="#4a2f07">Sample Collection: </font></b>
				</div>
				<div class="col-sm-6">
					<input type='text' class="form-control date-picker" name="samp_e"
						id="samp_e"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Sample Collected Date"
						required>
				</div>
				<div id="error_samp_eff"></div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-6">
					<b><font color="#4a2f07">Report Submission: </font></b>
				</div>
				<div class="col-sm-6">
					<input type='text' class="form-control date-picker" name="sub_e"
						id="sub_e"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Report Submitted Date"
						required>
				</div>
				<div id="error_sub_eff"></div>
			</div>
		</div>
	</div>
	<div class="row"></div>
	<div class="row" style="margin-top: 15px;">
		<div class="col-sm-4">
			<div class="form-group">
				<h5 style="color: #4a2f07;">
					<b>Parameter Name</b>
				</h5>

			</div>
		</div>
		<div class="col-sm-6">
			<div class="col-sm-6">
				<h5 style="color: #4a2f07;">
					<b>Inlet (Conc.)</b>
				</h5>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<h5 style="color: #4a2f07;">
						<b>Outlet (Conc.)</b>
					</h5>
				</div>
			</div>
		</div>

		<div class="col-sm-2">
			<div class="form-group">
				<h5 style="color: #4a2f07;">
					<b>Unit</b>
				</h5>
			</div>
		</div>
	</div>
	<div class="row"></div>
	<c:if test="${!empty wateriePollutant}">
		<c:forEach items="${wateriePollutant}" var="wateriePoll">
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
						<div class="fg-line">
							<input type="hidden" name="poll_name_e[]"
								value="${wateriePoll.pollName}" /> <input type="text"
								class="form-control" disabled value="${wateriePoll.pollName}" />
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="col-sm-6">
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" name="in_cons_e[]">
								<div id="error_incons_eff"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" name="ou_cons_e[]">
								<div id="error_oucons_eff"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group">
						<div class="fg-line">
							<input type="hidden" name="unit_e[]"
								value="${wateriePoll.unit.getUnits()}"> <input
								type="text" disabled class="form-control"
								value="${wateriePoll.unit.getUnits()}">
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<div class="form-group">
		<div class="fg-line">
			<div class="col-sm-10">
				<div class="fileinput fileinput-new" data-provides="fileinput">
					<br /> <br /> <label><b><font color="#4a2f07"
							style="font-family: sans-serif">Effluent Monitering Copy:
						</font></b></label> <span class="btn btn-primary btn-file m-r-10"
						style="background-color: #9e9e9e;"> <span
						class="fileinput-new">Select file</span> <span
						class="fileinput-exists">Change</span> <input type="file"
						name="waterEffluent_file" id="waterEffluent_file"></span> <span
						class="fileinput-filename"></span> <a href="#"
						class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
					<div class="col-sm-12" id="error_waterEff_file"></div>
				</div>
			</div>
		</div>

	</div>
	<div class="form-group">
		<div class="fg-line">
			<center>
				<button type="reset" class="btn btn-default waves-effect">Cancel</button>
				<button type="button" name="reg_eff" id="reg_eff"
					class="btn btn-info" style="background-color: #4a2f07;"
					onclick="saveWaterPollutant('vishal')">Submit</button>
			</center>
		</div>
	</div>
</form>
