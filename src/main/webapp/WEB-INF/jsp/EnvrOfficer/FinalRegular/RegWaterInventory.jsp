<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="card-body card-padding">
		<c:if test="${! empty wiData}">
			<c:forEach items="${wiData }" var="wiData">
				<c:set var="staff" value="0" />
				<c:forEach items="${wiData.regularSourceDataByDate}"
					var="rsDataByDate">
					<c:choose>
						<c:when test="${!empty rsDataByDate }">
							<c:set var="staff" value="${rsDataByDate.staff }" />
						</c:when>
						<c:otherwise>
							<c:set var="staff" value="0" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<div class="row" id="staffdiv">
					<div class="col-sm-4">
						<label class="text-darkbrown">Staff :</label>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<div class="fg-line">
								<c:choose>
									<c:when test="${staff!=0}">
										<input type="text" class="form-control" name="no_staff"
											value="${staff}" disabled>
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" name="no_staff"
											placeholder="Number of Staff" required
											onkeypress="numberValidate(event)">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-sm-4"></div>
					<div class="col-sm-2">
						<c:choose>
							<c:when test="${staff!=0 }">
								<div id="error_staff">
									<label class='text-cyan'><i
										class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
								</div>
							</c:when>
							<c:otherwise>
								<div id="error_staff"></div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div id="sourceId1">
					<div class="col-sm-4"></div>
					<div class="col-sm-2">
						<label class="text-darkbrown">Start Reading</label>
					</div>
					<div class="col-sm-2">
						<label class="text-darkbrown">End Reading</label>
					</div>
					<div class="col-sm-2">
						<label class="text-darkbrown">Actual Consumption</label>
					</div>
					<div class="col-sm-2">
						<label class="text-darkbrown">Warnings</label>
					</div>
				</div>
				<div id="sourceId2">
					<c:set var="count" value="0" />
					<c:if test="${!empty wiData.waterSourceList}">
						<c:forEach items="${wiData.waterSourceList }" var="wsData">
							<c:set var="count" value="${count + 1}" />
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<div class="fg-line">
											<input type="hidden" name="wi_id"
												value="${wsData.getWaterInventory().getWaterInventoryId()}">
											<input type="hidden" name="source_name[]"
												value="${wsData.sourceName}"> <label
												class="text-darkbrown">${wsData.sourceName}: </label>
										</div>
									</div>
								</div>
								<c:choose>
									<c:when test="${! empty wsData.regularSourceDataList }">
										<c:forEach items="${wsData.regularSourceDataList}"
											var="regularSourceData">
											<c:choose>
												<c:when
													test="${(wsData.sourceMeter=='Yes') &&(regularSourceData.rsDate!=today)}">
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="source_start[]"
																	value="${regularSourceData.endReading}"
																	id="source_start_${wsData.getWaterInventory().getWaterInventoryId()}_${count}"
																	onkeypress="numberValidate(event,'error_source_${wsData.getWaterInventory().getWaterInventoryId()}_${count }')"
																	onchange="setConsumption('source_end_${wsData.getWaterInventory().getWaterInventoryId()}_${count}','source_start_${wsData.getWaterInventory().getWaterInventoryId()}_${count}','${wsData.getWaterInventory().getWaterInventoryId()}_${count }')">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="source_end[]" placeholder="End Reading"
																	id="source_end_${wsData.getWaterInventory().getWaterInventoryId()}_${count}"
																	onkeypress="numberValidate(event,'error_source_${wsData.getWaterInventory().getWaterInventoryId()}_${count}')"
																	onchange="setConsumption('source_end_${wsData.getWaterInventory().getWaterInventoryId()}_${count}','source_start_${wsData.getWaterInventory().getWaterInventoryId() }_${count}','${wsData.getWaterInventory().getWaterInventoryId()}_${count}')">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="source_avg_${wsData.getWaterInventory().getWaterInventoryId() }_${count}">
																<input type="text" class="form-control"
																	name="source_avg[]" disabled
																	placeholder="Act Consumption">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_source_${wsData.getWaterInventory().getWaterInventoryId()}_${count}"></div>

												</c:when>
												<c:when
													test="${(wsData.sourceMeter=='Yes')&&(regularSourceData.rsDate==today)}">
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="source_start[]" disabled id="source_start"
																	value="${regularSourceData.startReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="source_end[]" disabled
																	value="${regularSourceData.endReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line" id="act_source_start">
																<input type="text" class="form-control"
																	name="source_avg[]" disabled
																	value="${regularSourceData.actualReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_source_${wiData.waterInventoryId}_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>

												</c:when>
												<c:when
													test="${(wsData.sourceMeter=='No')&&(regularSourceData.rsDate!=today) }">
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" class="form-control"
																	name="source_start[]" value="0"> <input
																	type="hidden" class="form-control" name="source_end[]"
																	value="0"> <input type="text"
																	class="form-control" name="avg_source_start[]"
																	placeholder="Act Consumption"
																	onkeypress="numberValidate(event,'error_source_${wsData.getWaterInventory().getWaterInventoryId()}_${count}')">
															</div>
														</div>
													</div>
													<div class="col-sm-4"></div>
													<div class="col-sm-2"
														id="error_source_${wiData.waterInventoryId}_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</c:when>
												<c:when
													test="${(wsData.sourceMeter=='No')&&(regularSourceData.rsDate==today) }">
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" class="form-control"
																	name="source_start[]" value="0"> <input
																	type="hidden" class="form-control" name="source_end[]"
																	value="0"> <input type="text"
																	class="form-control" name="source_avg[]" disabled
																	value="${regularSourceData.actualReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-4"></div>
													<div class="col-sm-2"
														id="error_source_${wsData.getWaterInventory().getWaterInventoryId() }_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control"
														name="source_start[]" value=""
														id="source_start_${wsData.getWaterInventory().getWaterInventoryId()}_${count}"
														onkeypress="numberValidate(event,'error_source_${wsData.getWaterInventory().getWaterInventoryId()}_${count }')"
														onchange="setConsumption('source_end_${wsData.getWaterInventory().getWaterInventoryId()}_${count}','source_start_${wsData.getWaterInventory().getWaterInventoryId()}_${count}','${wsData.getWaterInventory().getWaterInventoryId()}_${count }')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="source_end[]"
														placeholder="End Reading"
														id="source_end_${wsData.getWaterInventory().getWaterInventoryId()}_${count}"
														onkeypress="numberValidate(event,'error_source_${wsData.getWaterInventory().getWaterInventoryId()}_${count}')"
														onchange="setConsumption('source_end_${wsData.getWaterInventory().getWaterInventoryId()}_${count}','source_start_${wsData.getWaterInventory().getWaterInventoryId() }_${count}','${wsData.getWaterInventory().getWaterInventoryId()}_${count}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line"
													id="source_avg_${wsData.getWaterInventory().getWaterInventoryId() }_${count}">
													<input type="text" class="form-control" name="source_avg[]"
														disabled placeholder="Act Consumption">
												</div>
											</div>
										</div>
										<div class="col-sm-2"
											id="error_source_${wsData.getWaterInventory().getWaterInventoryId()}_${count}"></div>

									</c:otherwise>
								</c:choose>

							</div>
						</c:forEach>
						<%-- <% end water source ws %> --%>
					</c:if>
				</div>
				<%--  <% end ws if not empty %> --%>
				<div class="row">
					<center>
						<button type="button" class="btn btn-info"
							style="background-color: #4a2f07;" name="ambientfinish"
							id="sourcebutton"
							onclick="saveRegularSourceData('source', 'water', 'one', 'waste', 'two')">Submit</button>
					</center>
				</div>
				<hr>
				<label class="text-darkbrown"><u>Filters</u></label>

				<div class="row"></div>
				<div id="filtersId">
					<c:if test="${!empty wiData.filtersData}">
						<c:forEach items="${wiData.filtersData }" var="filtersData"
							varStatus="loop">

							<input type="hidden" name="wi_id"
								value="${filtersData.getWaterInventory().getWaterInventoryId()}">
							<c:set var="count" value="${count+1 }" />
							<c:choose>
								<c:when test="${!empty filtersData.regularFiltersData}">
									<c:forEach items="${filtersData.regularFiltersData }"
										var="regularFiltersData">
										<c:set var="count" value="${count+1 }" />
										<c:choose>
											<c:when
												test="${(filtersData.waterMeter=='Yes') && (regularFiltersData.rfDate!=today)}">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">

																<label class="text-darkbrown">${filtersData.filterName}
																	Filter (Source: ${filtersData.sourceName}):</label> <input
																	type="hidden" name="filter_name[]"
																	value="${filtersData.filterName}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_start[]" placeholder="Start Reading"
																	value="${regularFiltersData.endReading }"
																	id="filter_start_${wiData.waterInventoryId }_${count}"
																	onkeypress="numberValidate(event,'error_filter_${wiData.waterInventoryId }_${count}')"
																	onchange="setConsumption('filter_end_${wiData.waterInventoryId }_${count}',
										'filter_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_end[]" placeholder="End Reading"
																	id="filter_end_${wiData.waterInventoryId }_${count}"
																	onkeypress="numberValidate(event,'error_filter_${wiData.waterInventoryId }_${count}')"
																	onchange="setConsumption('filter_end_${wiData.waterInventoryId }_${count}',
										'filter_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="filter_avg_${wiData.waterInventoryId }_${count}">
																<input type="text" class="form-control"
																	name="avg_filter_start[]" disabled
																	placeholder="Act Consumption">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}"></div>
												</div>
											</c:when>
											<c:when
												test="${(filtersData.waterMeter=='Yes')&& (regularFiltersData.rfDate==today)}">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<label class="text-darkbrown">${filtersData.filterName}
																	Filter (Source: ${filtersData.sourceName}):</label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	value="${regularFiltersData.startReading }" disabled
																	id="filter_start_${wiData.waterInventoryId }_${count}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	value="${regularFiltersData.endReading }" disabled
																	id="filter_end_${wiData.waterInventoryId }_${count}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="filter_avg_${wiData.waterInventoryId }_${count}">
																<input type="text" class="form-control" disabled
																	value="${regularFiltersData.actualReading }">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</div>

											</c:when>
											<c:when
												test="${ (filtersData.waterMeter=='No')&& (regularFiltersData.rfDate!=today)}">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<label class="text-darkbrown">${filtersData.filterName}
																	Filter (Source:${filtersData.sourceName}):</label> <input
																	type="hidden" name="filter_name[]"
																	value="${filtersData.filterName}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" class="form-control"
																	name="filter_start[]" value="0"> <input
																	type="hidden" class="form-control" name="filter_end[]"
																	value="0"> <input type="text"
																	class="form-control" name="avg_filter_start[]"
																	placeholder="Actual Reading">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}"></div>
												</div>
											</c:when>
											<c:when
												test="${ (filtersData.waterMeter=='No')&& (regularFiltersData.rfDate==today) }">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<label class="text-darkbrown">${filtersData.filterName}
																	Filter (Source: ${filtersData.sourceName}):</label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="filter_avg_${wiData.waterInventoryId }_${count}">
																<input type="text" class="form-control" disabled
																	value="${regularFiltersData.actualReading }">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</div>

											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<div class="fg-line">
													<label class="text-darkbrown">${filtersData.filterName}
														Filter (Source: ${filtersData.sourceName}):</label> <input
														type="hidden" name="filter_name[]"
														value="${filtersData.filterName}">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control"
														name="filter_start[]" placeholder="Start Reading" value=""
														id="filter_start_${wiData.waterInventoryId }_${count}"
														onkeypress="numberValidate(event,'error_filter_${wiData.waterInventoryId }_${count}')"
														onchange="setConsumption('filter_end_${wiData.waterInventoryId }_${count}',
										'filter_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="filter_end[]"
														placeholder="End Reading"
														id="filter_end_${wiData.waterInventoryId }_${count}"
														onkeypress="numberValidate(event,'error_filter_${wiData.waterInventoryId }_${count}')"
														onchange="setConsumption('filter_end_${wiData.waterInventoryId }_${count}',
										'filter_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line"
													id="filter_avg_${wiData.waterInventoryId }_${count}">
													<input type="text" class="form-control"
														name="avg_filter_start[]" disabled
														placeholder="Act Consumption">
												</div>
											</div>
										</div>
										<div class="col-sm-2"
											id="error_filter_${wiData.waterInventoryId }_${count}"></div>
									</div>
								</c:otherwise>
							</c:choose>
							<c:forEach items="${filtersData.filtersAllData}"
								var="filtersAllData" varStatus="floop">
								<input type="hidden" name="wi_id"
									value="${filtersAllData.getWaterInventory().getWaterInventoryId()}">
								<c:set var="count" value="${count+1 }" />
								<c:choose>
									<c:when
										test="${(filtersAllData.filterMeter=='Yes')&&(filtersData.regularFiltersUseDataByfilterName[floop.index].rfDate!=today) }">
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<label class="text-darkbrown">${filtersAllData.filterUse}:</label>
														<input type="hidden" name="filter_type[]"
															value="${filtersAllData.filterName }-${filtersAllData.filterUse }">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_type_start[]"
															value="${filtersData.regularFiltersUseDataByfilterName[floop.index].endReading}"
															id="filter_type_start_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_filter_type_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('filter_type_end_${wiData.waterInventoryId }_${count}',
										'filter_type_start_${wiData.waterInventoryId }_${count}'
										,'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_type_end[]" placeholder="End Reading"
															id="filter_type_end_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_filter_type_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('filter_type_end_${wiData.waterInventoryId }_${count}',
										'filter_type_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="filter_type_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_filter_type_start[]" disabled
															placeholder="Act Consumption">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_type_${wiData.waterInventoryId }_${count}"></div>
										</div>
									</c:when>
									<c:when
										test="${(filtersAllData.filterMeter=='Yes')&&(filtersData.regularFiltersUseDataByfilterName[floop.index].rfDate==today) }">
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<label class="text-darkbrown">${filtersAllData.filterUse}:</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															value="${filtersData.regularFiltersUseDataByfilterName[floop.index].startReading }"
															disabled
															id="filter_type_start_${wiData.waterInventoryId }_${count}">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															value="${filtersData.regularFiltersUseDataByfilterName[floop.index].endReading }"
															disabled
															id="filter_type_end_${wiData.waterInventoryId }_${count}">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="filter_type_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control" disabled
															value="${filtersData.regularFiltersUseDataByfilterName[floop.index].actualReading }">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_type_${wiData.waterInventoryId }_${count}">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
									</c:when>
									<c:when
										test="${(filtersAllData.filterMeter=='No')&&(filtersData.regularFiltersUseDataByfilterName[floop.index].rfDate!=today) }">
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<label class="text-darkbrown">${filtersAllData.filterUse }:</label>
														<input type="hidden" name="filter_type[]"
															value="${filtersAllData.filterName}-${filtersAllData.filterUse}">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" class="form-control"
															name="filter_type_start[]" value="0"> <input
															type="hidden" class="form-control"
															name="filter_type_end[]" value="0"> <input
															type="text" class="form-control"
															name="avg_filter_type_start[]"
															placeholder="Actual Reading">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_type_${wiData.waterInventoryId }_${count}"></div>
										</div>
									</c:when>
									<c:when
										test="${(filtersAllData.filterMeter=='No')&&(filtersData.regularFiltersUseDataByfilterName[floop.index].rfDate==today) }">
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<label class="text-darkbrown">${filtersAllData.filterUse}:</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="filter_type_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control" disabled
															value="${filtersData.regularFiltersUseDataByfilterName[floop.index].actualReading }">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_type_${wiData.waterInventoryId }_${count}">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:forEach>


					</c:if>
					<%-- <% filetrsData>0 %> --%>
				</div>

				<%--  <% end ws if not empty %> --%>
				<div class="row">
					<center>
						<button type="button" class="btn btn-info"
							style="background-color: #4a2f07;" name="ambientfinish"
							id="filtersbutton"
							onclick="saveRegularFiltersData('filter', 'water', 'one', 'waste', 'two')">Submit</button>
					</center>
				</div>
				<%--  <% end ws if not empty %> --%>

				<%-- <%FiltersId div end %> --%>


				<hr>
				<label class="text-darkbrown"><u>Use Of Source Water</u></label>
				<div class="row"></div>
				<div id="useOfSourceId">
					<c:if test="${wiData.useOfWaterData.size() > 0}">
						<c:set var="k" value="0" />
						<c:if test="${wiData.domesticUseOfSource=='checked'}">
							<c:set var="count" value="${count+1 }" />
							<c:choose>
								<c:when
									test="${wiData.regularWaterUseDataByDomestic.size() > 0}">
									<c:forEach items="${wiData.regularWaterUseDataByDomestic}"
										var="regularWaterUseDataByDomestic">
										<c:set var="domesticStartReading"
											value="${regularWaterUseDataByDomestic.endReading}" />
										<c:set var="rsDate"
											value="${regularWaterUseDataByDomestic.rWDate}" />
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:set var="domesticStartReading" value="0" />
									<c:set var="rsDate" value="" />
								</c:otherwise>
							</c:choose>
							<c:forEach items="${wiData.useOfWaterData}" var="useOfWaterData">
								<input type="hidden" name="wi_id"
									value="${useOfWaterData.getWaterInventory().getWaterInventoryId()}">
								<c:choose>
									<c:when
										test="${(useOfWaterData.domesticMeter=='Yes')&&(rsDate!=today)}">
										<c:set var="k" value="${k+1}" />
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="Domestic"> <label class="text-darkbrown">Domestic:
														</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_start[]"
															value="${domesticStartReading }"
															id="use_start_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}'
										,'use_start_${wiData.waterInventoryId }_${count}'
										,'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_end[]"
															placeholder="End Reading"
															id="use_end_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}',
										'use_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="use_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_use_start[]" disabled
															placeholder="Act Consumption">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_use_${wiData.waterInventoryId }_${count}"></div>
										</div>
									</c:when>
									<c:when test="${useOfWaterData.laundryMeter=='No' }">
										<c:choose>
											<c:when test="${rsDate==today }">
												<c:set var="domesticDateStartReading" value="0" />
												<c:set var="domesticDateEndReading" value="0" />
												<c:set var="domesticDateActualReading" value="0" />
												<c:if
													test="${wiData.regularWaterUseDataByDomesticDate.size() > 0}">
													<c:forEach
														items="${wiData.regularWaterUseDataByDomesticDate}"
														var="regularWaterUseDataByDomesticDate">
														<c:set var="domesticDateStartReading"
															value="${regularWaterUseDataByDomesticDate.startReading }" />
														<c:set var="domesticDateEndReading"
															value="${regularWaterUseDataByDomesticDate.endReading }" />
														<c:set var="domesticDateActualReading"
															value="${regularWaterUseDataByDomesticDate.actualReading }" />
													</c:forEach>
												</c:if>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="Domestic"> <label class="text-darkbrown">Domestic:
																</label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_start[]"
																	value="${domesticDateStartReading }"
																	id="filter_start_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_end[]" value="${domesticDateEndReading }"
																	id="filter_end_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="filter_avg_${wiData.waterInventoryId }_${count}">
																<input type="text" class="form-control"
																	name="avg_filter_start[]" disabled
																	value="${domesticDateActualReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="Domestic"> <label class="text-darkbrown">Domestic:
																</label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="use_start[]" value="0">
																<input type="hidden" name="use_end[]" value="0">
																<input type="text" class="form-control"
																	name="avg_use_start[]" id="act_use_start${k}[]"
																	placeholder="Act Consumption">
															</div>
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${rsDate==today}">
										<c:set var="domesticDateStartReading" value="0" />
										<c:set var="domesticDateEndReading" value="0" />
										<c:set var="domesticDateActualReading" value="0" />
										<c:if
											test="${wiData.regularWaterUseDataByDomesticDate.size() > 0}">
											<c:forEach
												items="${wiData.regularWaterUseDataByDomesticDate}"
												var="regularWaterUseDataByDomesticDate">
												<c:set var="domesticDateStartReading"
													value="${regularWaterUseDataByDomesticDate.startReading }" />
												<c:set var="domesticDateEndReading"
													value="${regularWaterUseDataByDomesticDate.endReading }" />
												<c:set var="domesticDateActualReading"
													value="${regularWaterUseDataByDomesticDate.actualReading }" />
											</c:forEach>
										</c:if>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="Domestic"> <label class="text-darkbrown">Domestic:
														</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_start[]"
															value="${domesticDateStartReading }"
															id="filter_start_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_end[]" value="${domesticDateEndReading }"
															id="filter_end_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="filter_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_filter_start[]" disabled
															value="${domesticDateActualReading}">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_${wiData.waterInventoryId }_${count}">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
						<%-- <% domestic use of source checked if %> --%>
						<c:if test="${wiData.industrialUseOfSource=='checked' }">

							<c:forEach items="${wiData.industrialDataByUOWId}"
								var="industrialDataByUOWId">
								<c:set var="count" value="${count+1 }" />
								<c:choose>
									<c:when
										test="${wiData.regularWaterUseDataByIndName.size() > 0 }">
										<c:forEach items="${wiData.regularWaterUseDataByIndName }"
											var="regularWaterUseDataByIndName">
											<c:set var="IndStartReading"
												value="${regularWaterUseDataByIndName.endReading}" />
											<c:set var="rsDate"
												value="${regularWaterUseDataByIndName.rWDate}" />
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:set var="IndStartReading" value="0" />
										<c:set var="rsDate" value="" />
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${(industrialDataByUOWId.waterMeter=='Yes')&&(rsDate!=today) }">
										<c:set var="k" value="${k+1}" />
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="${industrialDataByUOWId.indName }"> <label
															class="text-darkbrown">Industrial -
															${industrialDataByUOWId.indName }: </label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_start[]"
															value="${IndStartReading }"
															id="use_start_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}'
											,'use_start_${wiData.waterInventoryId }_${count}'
											,'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_end[]"
															placeholder="End Reading"
															id="use_end_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}',
											'use_start_${wiData.waterInventoryId }_${count}',
											'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="use_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_use_start[]" disabled
															placeholder="Act Consumption">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_use_${wiData.waterInventoryId }_${count}"></div>
										</div>
									</c:when>
									<c:when test="${industrialDataByUOWId.waterMeter=='No' }">
										<c:choose>
											<c:when test="${rsDate==today }">
												<c:set var="indDateStartReading" value="0" />
												<c:set var="indDateEndReading" value="0" />
												<c:set var="indDateActualReading" value="0" />
												<c:if
													test="${wiData.regularWaterUseDataIndNameDate.size() > 0 }">
													<c:forEach
														items="${wiData.regularWaterUseDataIndNameDate }"
														var="regularWaterUseDataByIndNameDate">
														<c:set var="indDateStartReading"
															value="${regularWaterUseDataByIndNameDate.startReading }" />
														<c:set var="indDateEndReading"
															value="${regularWaterUseDataByIndNameDate.endReading }" />
														<c:set var="indDateActualReading"
															value="${regularWaterUseDataByIndNameDate.actualReading }" />
													</c:forEach>
												</c:if>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="${industrialDataByUOWId.indName }"> <label
																	class="text-darkbrown">Industrial -
																	${industrialDataByUOWId.indName}: </label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_start[]" value="${indDateStartReading }"
																	id="filter_start_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_end[]" value="${indDateEndReading }"
																	id="filter_end_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="filter_avg_${wiData.waterInventoryId }_${count}">
																<input type="text" class="form-control"
																	name="avg_filter_start[]" disabled
																	value="${indDateActualReading }">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="${industrialDataByUOWId.indName }"> <label
																	class="text-darkbrown">Industrial -
																	${industrialDataByUOWId.indName }: </label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="use_start[]" value="0">
																<input type="hidden" name="use_end[]" value="0">
																<input type="text" class="form-control"
																	name="avg_use_start[]" id="act_use_start${k}[]"
																	placeholder="Act Consumption">
															</div>
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${rsDate==today }">
										<c:set var="indDateStartReading" value="0" />
										<c:set var="indDateEndReading" value="0" />
										<c:set var="indDateActualReading" value="0" />
										<c:if
											test="${wiData.regularWaterUseDataIndNameDate.size() > 0 }">
											<c:forEach items="${wiData.regularWaterUseDataIndNameDate }"
												var="regularWaterUseDataByIndNameDate">
												<c:set var="indDateStartReading"
													value="${regularWaterUseDataByIndNameDate.startReading }" />
												<c:set var="indDateEndReading"
													value="${regularWaterUseDataByIndNameDate.endReading }" />
												<c:set var="indDateActualReading"
													value="${regularWaterUseDataByIndNameDate.actualReading }" />
											</c:forEach>
										</c:if>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="${industrialDataByUOWId.indName }"> <label
															class="text-darkbrown">Industrial -
															${industrialDataByUOWId.indName }: </label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_start[]" value="${indDateStartReading }"
															id="filter_start_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_end[]" value="${indDateEndReading }"
															id="filter_end_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="filter_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_filter_start[]" disabled
															value="${indDateActualReading}">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_${wiData.waterInventoryId }_${count}">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
							<%-- <% end industrial data for %> --%>
						</c:if>
						<%-- <% end industrial use of source checked if %> --%>

						<c:if test="${wiData.laundryUseOfSource=='checked'}">
							<c:set var="count" value="${count+1 }" />
							<c:choose>
								<c:when test="${wiData.regularWaterUseDataByLaundry.size() > 0}">
									<c:forEach items="${wiData.regularWaterUseDataByLaundry}"
										var="regularWaterUseDataByLaundry">
										<c:set var="laundryStartReading"
											value="${regularWaterUseDataByLaundry.endReading}" />
										<c:set var="rsDate"
											value="${regularWaterUseDataByLaundry.rWDate}" />
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:set var="laundryStartReading" value="0" />
									<c:set var="rsDate" value="" />
								</c:otherwise>
							</c:choose>
							<c:forEach items="${wiData.useOfWaterData}" var="useOfWaterData">
								<c:choose>
									<c:when
										test="${(useOfWaterData.laundryMeter=='Yes')&&(rsDate!=today)}">
										<c:set var="k" value="${k+1}" />
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="Laundry"> <label class="text-darkbrown">Laundry:
														</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_start[]"
															value="${laundryStartReading }"
															id="use_start_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}'
										,'use_start_${wiData.waterInventoryId }_${count}'
										,'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_end[]"
															placeholder="End Reading"
															id="use_end_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}',
										'use_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="use_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_use_start[]" disabled
															placeholder="Act Consumption">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_use_${wiData.waterInventoryId }_${count}"></div>
										</div>
									</c:when>
									<c:when test="${useOfWaterData.laundryMeter=='No' }">
										<c:choose>
											<c:when test="${rsDate==today }">
												<c:set var="laundryDateStartReading" value="0" />
												<c:set var="laundryDateEndReading" value="0" />
												<c:set var="laundryDateActualReading" value="0" />
												<c:if
													test="${wiData.regularWaterUseDataByLaundryDate.size() > 0}">
													<c:forEach
														items="${wiData.regularWaterUseDataByLaundryDate}"
														var="regularWaterUseDataByLaundryDate">
														<c:set var="laundryDateStartReading"
															value="${regularWaterUseDataByLaundryDate.startReading }" />
														<c:set var="laundryDateEndReading"
															value="${regularWaterUseDataByLaundryDate.endReading }" />
														<c:set var="laundryDateActualReading"
															value="${regularWaterUseDataByLaundryDate.actualReading }" />
													</c:forEach>
												</c:if>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="Laundry"> <label class="text-darkbrown">Laundry:
																</label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_start[]"
																	value="${laundryDateStartReading }"
																	id="filter_start_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_end[]" value="${laundryDateEndReading }"
																	id="filter_end_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="filter_avg_${wiData.waterInventoryId }_${count}">
																<input type="text" class="form-control"
																	name="avg_filter_start[]" disabled
																	value="${laundryDateActualReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="Laundry"> <label class="text-darkbrown">Laundry:
																</label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="use_start[]" value="0">
																<input type="hidden" name="use_end[]" value="0">
																<input type="text" class="form-control"
																	name="avg_use_start[]" id="act_use_start${k}[]"
																	placeholder="Act Consumption">
															</div>
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${rsDate==today}">
										<c:set var="laundryDateStartReading" value="0" />
										<c:set var="laundryDateEndReading" value="0" />
										<c:set var="laundryDateActualReading" value="0" />
										<c:if
											test="${wiData.regularWaterUseDataByLaundryDate.size() > 0}">
											<c:forEach items="${wiData.regularWaterUseDataByLaundryDate}"
												var="regularWaterUseDataByLaundryDate">
												<c:set var="laundryDateStartReading"
													value="${regularWaterUseDataByLaundryDate.startReading }" />
												<c:set var="laundryDateEndReading"
													value="${regularWaterUseDataByLaundryDate.endReading }" />
												<c:set var="laundryDateActualReading"
													value="${regularWaterUseDataByLaundryDate.actualReading }" />
											</c:forEach>
										</c:if>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="Laundry"> <label class="text-darkbrown">Laundry:
														</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_start[]" value="${laundryDateStartReading }"
															id="filter_start_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_end[]" value="${laundryDateEndReading }"
															id="filter_end_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="filter_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_filter_start[]" disabled
															value="${laundryDateActualReading}">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_${wiData.waterInventoryId }_${count}">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
						<%-- <% laundry use of source checked if %> --%>
						<c:if test="${wiData.fireHydrantUseOfSource=='checked'}">
							<c:set var="count" value="${count+1 }" />
							<c:choose>
								<c:when
									test="${wiData.regularWaterUseDataByFireHydrant.size() > 0}">
									<c:forEach items="${wiData.regularWaterUseDataByFireHydrant}"
										var="regularWaterUseDataByFireHydrant">
										<c:set var="fireHydrantStartReading"
											value="${regularWaterUseDataByFireHydrant.endReading}" />
										<c:set var="rsDate"
											value="${regularWaterUseDataByFireHydrant.rWDate}" />
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:set var="fireHydrantStartReading" value="0" />
									<c:set var="rsDate" value=" " />
								</c:otherwise>
							</c:choose>
							<c:forEach items="${wiData.useOfWaterData}" var="useOfWaterData">
								<c:choose>
									<c:when
										test="${(useOfWaterData.fireHydrantMeter=='Yes')&&(rsDate!=today)}">
										<c:set var="k" value="${k+1}" />
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="Fire Hydrant"> <label
															class="text-darkbrown">Fire Hydrant: </label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_start[]"
															value="${fireHydrantStartReading }"
															id="use_start_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}'
										,'use_start_${wiData.waterInventoryId }_${count}'
										,'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control" name="use_end[]"
															placeholder="End Reading"
															id="use_end_${wiData.waterInventoryId }_${count}"
															onkeypress="numberValidate(event,'error_use_${wiData.waterInventoryId }_${count}')"
															onchange="setConsumption('use_end_${wiData.waterInventoryId }_${count}',
										'use_start_${wiData.waterInventoryId }_${count}',
										'${wiData.waterInventoryId }_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="use_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_use_start[]" disabled
															placeholder="Act Consumption">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_use_${wiData.waterInventoryId }_${count}"></div>
										</div>
									</c:when>
									<c:when test="${useOfWaterData.fireHydrantMeter=='No' }">
										<c:choose>
											<c:when test="${rsDate==today }">
												<c:set var="fireHydrantDateStartReading" value="0" />
												<c:set var="fireHydrantDateEndReading" value="0" />
												<c:set var="fireHydrantDateActualReading" value="0" />
												<c:if
													test="${wiData.regularWaterUseDataByFireHydrantDate.size() > 0}">
													<c:forEach
														items="${wiData.regularWaterUseDataByFireHydrantDate}"
														var="regularWaterUseDataByFireHydrantDate">
														<c:set var="fireHydrantDateStartReading"
															value="${regularWaterUseDataByFireHydrantDate.startReading }" />
														<c:set var="fireHydrantDateEndReading"
															value="${regularWaterUseDataByFireHydrantDate.endReading }" />
														<c:set var="fireHydrantDateActualReading"
															value="${regularWaterUseDataByFireHydrantDate.actualReading }" />
													</c:forEach>
												</c:if>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="Fire Hydrant"> <label
																	class="text-darkbrown">Fire Hydrant: </label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_start[]"
																	value="${fireHydrantDateStartReading }"
																	id="filter_start_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_end[]"
																	value="${fireHydrantDateEndReading }"
																	id="filter_end_${wiData.waterInventoryId }_${count}"
																	disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line"
																id="filter_avg_${wiData.waterInventoryId }_${count}">
																<input type="text" class="form-control"
																	name="avg_filter_start[]" disabled
																	value="${fireHydrantDateActualReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-2"
														id="error_filter_${wiData.waterInventoryId }_${count}">
														<label class='text-cyan'><i
															class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data
															Filled!</label>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="source_use_name[]"
																	value="Fire Hydrant"> <label
																	class="text-darkbrown">Fire Hydrant: </label>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="hidden" name="use_start[]" value="0">
																<input type="hidden" name="use_end[]" value="0">
																<input type="text" class="form-control"
																	name="avg_use_start[]" id="act_use_start${k}[]"
																	placeholder="Act Consumption">
															</div>
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${rsDate==today}">
										<c:set var="fireHydrantDateStartReading" value="0" />
										<c:set var="fireHydrantDateEndReading" value="0" />
										<c:set var="fireHydrantDateActualReading" value="0" />
										<c:if
											test="${wiData.regularWaterUseDataByFireHydrantDate.size() > 0}">
											<c:forEach
												items="${wiData.regularWaterUseDataByFireHydrantDate}"
												var="regularWaterUseDataByFireHydrantDate">
												<c:set var="fireHydrantDateStartReading"
													value="${regularWaterUseDataByFireHydrantDate.startReading }" />
												<c:set var="fireHydrantDateEndReading"
													value="${regularWaterUseDataByFireHydrantDate.endReading }" />
												<c:set var="fireHydrantDateActualReading"
													value="${regularWaterUseDataByFireHydrantDate.actualReading }" />
											</c:forEach>
										</c:if>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="source_use_name[]"
															value="Fire Hydrant"> <label
															class="text-darkbrown">Fire Hydrant: </label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_start[]"
															value="${fireHydrantDateStartReading }"
															id="filter_start_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="filter_end[]" value="${fireHydrantDateEndReading }"
															id="filter_end_${wiData.waterInventoryId }_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="filter_avg_${wiData.waterInventoryId }_${count}">
														<input type="text" class="form-control"
															name="avg_filter_start[]" disabled
															value="${fireHydrantDateActualReading}">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_filter_${wiData.waterInventoryId }_${count}">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
						<%-- <% firehydrant use of source checked if %> --%>
					</c:if>
					<%-- <% use of water data size >0  if %> --%>
				</div>
				<%--  <% end useofsourceid div %> --%>
				<div class="row">
					<center>
						<button type="button" class="btn btn-info"
							style="background-color: #4a2f07;" name="ambientfinish"
							id="useofsourcebutton"
							onclick="saveRegularUseOfSourceData('useofsource', 'water', 'one', 'waste', 'two')">Submit</button>
					</center>
				</div>

				<hr>

				<label class="text-darkbrown"><u>Waste-water Treatment</u></label>
				<div class="row"></div>
				<!-- <h5></h5> -->
				<div id="watertreatmentId">
					<c:if test="${wiData.waterTreatment=='Yes' }">
						<c:if test="${wiData.treatmentTypeAndNameBywiId.size() > 0}">
							<c:forEach items="${wiData.treatmentTypeAndNameBywiId }"
								var="treatmentTypeAndNameBywiId" varStatus="loop" begin="0">
								<input type="hidden" name="wi_id"
									value="${treatmentTypeAndNameBywiId.getWaterInventory().getWaterInventoryId()}">
								<label class='text-darkbrown'>${treatmentTypeAndNameBywiId.treatmentType}:</label>
								<br />
								<c:set var="typeName"
									value="${treatmentTypeAndNameBywiId.typeName }" />
								<c:set var="treatmentType" value="${fn:split(typeName, '-')}" />
								<c:set var="count" value="${count+1 }" />
								<c:choose>
									<c:when
										test="${wiData.regularTreatmentDataByTreatmentType[loop.index].rtDate==today}">

										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="treatment_name[]"
															value="${treatmentType[0] }"> <label
															class="text-darkbrown-nobold">${typeName } :</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="treatment_start[]"
															value="${wiData.regularTreatmentDataByTreatmentTypeDate[loop.index].startReading}"
															id="filter_start_${wiData.waterInventoryId}_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="treatment_end[]"
															value="${wiData.regularTreatmentDataByTreatmentTypeDate[loop.index].endReading}"
															id="treatment_end_${wiData.waterInventoryId}_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="treatment_avg_${wiData.waterInventoryId}_${count}">
														<input type="text" class="form-control"
															name="avg_treatment_start[]" disabled
															value="${wiData.regularTreatmentDataByTreatmentTypeDate[loop.index].actualReading}">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_treatment_${wiData.waterInventoryId}_${count}">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12">Energy Meter Reading</div>
										</div>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="energy_treatment[]"
															value="${treatmentType[0] }"> <label
															class="text-darkbrown-nobold"> ${typeName}: </label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="energy_treatment_start[]"
															value="${wiData.regularTreatmentDataByTreatmentTypeDate[loop.index].energyStartReading}"
															id="energy_treatment_start_${wiData.waterInventoryId}_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="energy_treatment_end[]"
															value="${wiData.regularTreatmentDataByTreatmentTypeDate[loop.index].energyEndReading }"
															id="energy_treatment_end_${wiData.waterInventoryId}_${count}"
															disabled>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="energy_treatment_avg_${wiData.waterInventoryId}_${count}">
														<input type="text" class="form-control"
															name="avg_energy_treatment_start[]" disabled
															value="${wiData.regularTreatmentDataByTreatmentTypeDate[loop.index].energyAvg}">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_energy_treatment__${wiData.waterInventoryId}_${count}
															">
												<label class='text-cyan'><i
													class="zmdi zmdi-check-circle zmdi-hc-fw"></i>Data Filled!</label>
											</div>
										</div>
										<%--  </c:forEach>  --%>
									</c:when>
									<c:otherwise>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="treatment_name[]"
															value="${treatmentType[0]}"> <input type="hidden"
															name="treatment_type[]" value="${typeName}"> <label
															class="text-darkbrown-nobold">${typeName} :</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="treatment_start[]" placeholder="Inlet"
															id="treatment_start_${wiData.waterInventoryId}_${count}"
															onkeypress="numberValidate(event,'error_treatment_${wiData.waterInventoryId}_${count}')"
															onchange="setTreatedConsumption('treatment_end_${wiData.waterInventoryId}_${count}'
												,'treatment_start_${wiData.waterInventoryId}_${count}'
												,'${wiData.waterInventoryId}_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="treatment_end[]" placeholder="Outlet"
															id="treatment_end_${wiData.waterInventoryId}_${count}"
															onkeypress="numberValidate(event,'error_treatment_${wiData.waterInventoryId}_${count}')"
															onchange="setTreatedConsumption('treatment_end_${wiData.waterInventoryId}_${count}',
												'treatment_start_${wiData.waterInventoryId}_${count}',
												'${wiData.waterInventoryId}_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line"
														id="treatment_avg_${wiData.waterInventoryId}_${count}">
														<input type="text" class="form-control"
															name="avg_treatment_start[]" disabled
															placeholder="Actual Loss">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_treatment_${wiData.waterInventoryId}_${count}"></div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<label class="text-darkbrown">Energy Meter Reading</label>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="fg-line">
														<input type="hidden" name="energy_treatment[]"
															value="${treatmentType[0] }"> <label
															class="text-darkbrown-nobold">${typeName} :</label>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="energy_treatment_start[]"
															placeholder="Start Reading"
															id="energy_treatment_start_${wiData.waterInventoryId}_${count}"
															onkeypress="numberValidate(event,'error_energy_treatment_${wiData.waterInventoryId}_${count}')"
															onchange="setConsumption('energy_treatment_end_${wiData.waterInventoryId}_${count}',
												'energy_treatment_start_${wiData.waterInventoryId}_${count}',
												'${wiData.waterInventoryId}_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line">
														<input type="text" class="form-control"
															name="energy_treatment_end[]" placeholder="End Reading"
															id="energy_treatment_end_${wiData.waterInventoryId}_${count}"
															onkeypress="numberValidate(event,'error_energy_treatment_${wiData.waterInventoryId}_${count}')"
															onchange="setConsumption('energy_treatment_end_${wiData.waterInventoryId}_${count}',
												'energy_treatment_start_${wiData.waterInventoryId}_${count}',
												'${wiData.waterInventoryId}_${count}')">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<div class="fg-line" name="avg_energy_treatment_start[]"
														id="energy_treatment_avg_${wiData.waterInventoryId}_${count}">
														<input type="text" class="form-control"
															name="avg_energy_treatment_start[]" disabled
															placeholder="Actual Consumption">
													</div>
												</div>
											</div>
											<div class="col-sm-2"
												id="error_energy_treatment_${wiData.waterInventoryId}_${count}"></div>
										</div>
									</c:otherwise>
								</c:choose>
								<%-- </c:forEach> --%>

							</c:forEach>
							<%--  <% end treatmentTypeBywId for %> --%>
						</c:if>
						<%-- <% treatmentTypeBywId size>0 %> --%>


					</c:if>
					<%-- <%waterTreatment Yes if end %> --%>
				</div>
				<div class="row">
					<center>
						<button type="button" class="btn btn-info"
							style="background-color: #4a2f07;" name="ambientfinish"
							id="watertreatmentbutton"
							onclick="saveRegularTreatmentData('watertreatment', 'water', 'one', 'waste', 'two')">Submit</button>
					</center>
				</div>

			</c:forEach>
			<%-- <% end wi  for %> --%>
		</c:if>
		<%-- <% end wiData.size()>0 %> --%>

		<div id="waterRes"></div>

	</div>

</body>
</html>