<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body> --%>

<!-- Water Sewage Pollutant Starts -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form method="post" name="water_sew" id="water_sew" action=""
	enctype="multipart/form-data">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-6">
					<b><font color="#4a2f07">Sample Collection: </font></b>
				</div>
				<div class="col-sm-6">
					<input type='text' class="form-control date-picker" name="samp_s"
						id="samp_s"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Sample Collected Date"
						required>
					<div id="error_sample_col"></div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-6">
					<b><font color="#4a2f07">Report Submission: </font></b>
				</div>
				<div class="col-sm-6">
					<input type='text' class="form-control date-picker" name="sub_s"
						id="sub_s"
						placeholder="&nbsp;&nbsp;&nbsp;&nbsp;Report Submitted Date"
						required>
					<div id="error_report_submission"></div>
				</div>
			</div>
		</div>
	</div>
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

	<c:if test="${!empty waterSewPoll}">
		<c:forEach items="${waterSewPoll}" var="waterSewPoll">
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
						<div class="fg-line">
							<input type="hidden" name="poll_name_s[]"
								value="${waterSewPoll.pollName}"> <input type="text"
								disabled class="form-control" value="${waterSewPoll.pollName}">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="col-sm-6">
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" name="in_cons_s[]">
								<div id="error_in_cons_s"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<div class="fg-line">
								<input type="text" class="form-control" name="ou_cons_s[]">
								<div id="error_ou_cons_s"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-2">
					<div class="form-group">
						<div class="fg-line">
							<input type="hidden" value="${waterSewPoll.unit.units}"
								name="unit_s[]"> <input type="text" class="form-control"
								disabled value="${waterSewPoll.unit.units}">
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
					<br /> <br /> <label><b> <font color="#4a2f07"
							style="font-family: sans-serif"> Stack Monitering Copy: </font></b></label> <span
						class="btn btn-primary btn-file m-r-10"
						style="background-color: #9e9e9e;"> <span
						class="fileinput-new">Select file</span> <span
						class="fileinput-exists">Change</span> <input type="file"
						name="sewage_file" id="sewage_file">
					</span> <span class="fileinput-filename"></span> <a href="#"
						class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
					<div id="error_water_sew_file"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="fg-line">
			<center>
				<button type="reset" class="btn btn-default waves-effect">Cancel</button>
				<button type="button" name="reg_sew" id="reg_sew"
					class="btn btn-info" style="background-color: #4a2f07;"
					onclick="saveWaterSew()">Submit</button>
			</center>
		</div>
	</div>
</form>
