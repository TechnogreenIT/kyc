<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript">
    $(function () {
    	var div =$("<div class='row'>" 
    		+"<div class='col-sm-4'><label class='text-darkbrown'>APC System</label></div>"
    	+"<div class='col-sm-2'><label class='text-darkbrown'>Start Reading</label></div>"
    	+"<div class='col-sm-2'><label class='text-darkbrown'>End Reading</label></div>"
    	+"<div class='col-sm-2'><label class='text-darkbrown'>Actual Consumption</label></div>"
    	+"<div class='col-sm-2'><label class='text-darkbrown'>Warnings</label></div></div>")
    	;
    	
    	$(div).appendTo("#apcheadings");
    	var div="";
    	$(div).appendTo("#apcheadings1");
    });
    </script>
</head>
<div class="card-body card-padding">

	<!-- <div class="row" id="apcreading">
		<div class="col-sm-4"><label class="text-darkbrown">APC System</label></div>
		<div class="col-sm-2"><label class="text-darkbrown">Start Reading</label></div>
		<div class="col-sm-2"><label class="text-darkbrown">End Reading</label></div>
		<div class="col-sm-2"><label class="text-darkbrown">Actual Consumption</label></div>
		<div class="col-sm-2"><label class="text-darkbrown">Warnings</label></div>
	</div> -->

	<c:forEach items="${stackData}" var="st">
		<c:set var="flag" value="${flagstack }" />
		<c:choose>
			<c:when test="${st.getApc()=='Yes'}">
				<c:choose>
					<c:when test="${!empty st.regAPCList }">
						<c:forEach items="${st.regAPCList }" var="regapc">
							<c:choose>
								<c:when test="${regapc.apcDate!=today }">

									<div id="apcheadings" /></div>
									<div class="row">
										<div class="col-sm-4">${st.getApcSystem()}</div>
										<c:set var="reason" value="NA" />
										<input type="hidden" name="stack_id[]" id="stack_id[]"
											value="${st.getStackId()}">
										<div class="col-sm-2">
											<div class="fg-line">
												<input type="text" class="form-control" name="apc_start[]"
													value="${regapc.endReading}"
													id="apc_start_${st.getStackId()}"
													onkeypress="numberValidate(event,'error_apc_${st.getStackId()}')"
													onchange="setConsumption('apc_end_${st.getStackId()}','apc_start_${st.getStackId()}','${st.getStackId()}')" />
											</div>
										</div>
										<div class="col-sm-2">
											<div class="fg-line">
												<input type="text" class="form-control" name="apc_end[]"
													placeholder="End Reading" id="apc_end_${st.getStackId()}"
													onkeypress="numberValidate(event,'error_apc_${st.getStackId()}')"
													onchange="setConsumption('apc_end_${st.getStackId() }','apc_start_${st.getStackId()}','${st.getStackId()}')" />
											</div>
										</div>
										<div class="col-sm-2">
											<div class="fg-line" id="apc_avg_${st.getStackId()}">
												<input type="text" class="form-control" name="apc_avg[]"
													disabled placeholder="Act Consumption" />
											</div>
										</div>
										<div class="col-sm-2" id="error_apc_${st.getStackId()}"></div>
									</div>

								</c:when>
								<c:otherwise>
									<div id="apcheadings" /></div>
									<div class="row">
										<div class="col-sm-4">${st.getApcSystem()}</div>
										<input type="hidden" name="stack_id[]" id="stack_id[]"
											value="${st.getStackId()}">
										<div class="col-sm-2">
											<div class="fg-line">
												<input type="text" class="form-control"
													value="${regapc.startReading}" disabled>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="fg-line">
												<input type="text" class="form-control"
													value="${regapc.endReading}" disabled>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="fg-line">
												<input type="text" class="form-control"
													value="${regapc.actualReading}" disabled>
											</div>
										</div>
										<div class="col-sm-2" id="error_apc_${st.getStackId()}">
											<label class='text-cyan'><i
												class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
										</div>
									</div>

								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div id="apcheadings" /></div>
						<div class="row">
							<div class="col-sm-4">${st.getApcSystem()}</div>
							<input type="hidden" name="stack_id[]" id="stack_id[]"
								value="${st.getStackId()}">
							<div class="col-sm-2">
								<div class="fg-line">
									<input type="text" class="form-control" name="apc_start[]"
										value="" id="apc_start_${st.getStackId()}"
										onkeypress="numberValidate(event,'error_apc_${st.getStackId()}')"
										onchange="setConsumption('apc_end_${st.getStackId()}','apc_start_${st.getStackId()}','${st.getStackId()}')" />
								</div>
							</div>
							<div class="col-sm-2">
								<div class="fg-line">
									<input type="text" class="form-control" name="apc_end[]"
										placeholder="End Reading" id="apc_end_${st.getStackId()}"
										onkeypress="numberValidate(event,'error_apc_${st.getStackId()}')"
										onchange="setConsumption('apc_end_${st.getStackId() }','apc_start_${st.getStackId()}','${st.getStackId()}')" />
								</div>
							</div>
							<div class="col-sm-2">
								<div class="fg-line" id="apc_avg_${st.getStackId()}">
									<input type="text" class="form-control" name="apc_avg[]"
										disabled placeholder="Act Consumption" />
								</div>
							</div>
							<div class="col-sm-2" id="error_apc_${st.getStackId()}"></div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<div id="apcheadings1" /></div>
			</c:otherwise>
		</c:choose>
		<div id="apcheadings" /></div>
	</c:forEach>
</div>
