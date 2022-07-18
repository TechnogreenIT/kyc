<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<script src="js/regular.min.js"></script>
<div class="card-body card-padding">
	<div class="col-sm-12" id="water_error"></div>
	<!-- MY Regular Water Data Starts -->

	<c:set var="noRowsSource" value="${fn:length(WaterInventoryList)}" />
	<c:choose>
		<c:when test="${!empty WaterInventoryList}">

			<c:forEach var="waterInventoryList" items="${WaterInventoryList}"
				varStatus="loop">

				<div class="row">
					<div class="col-sm-4">
						<label class="text-darkbrown">Staff : </label>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<div class="fg-line">
								<c:choose>
									<c:when test="${!empty staff}">
										<input type="text" class="form-control" name="no_staff"
											id="no_staff" value="${staff}" disabled>
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
							<c:when test="${!empty staff}">
								<div id="error_staff">
									<button class="btn btn-default"
										onclick="changeStaffDivData('enable', '${staffId}')">Change</button>
								</div>
							</c:when>
							<c:otherwise>
								<div id="error_staff"></div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
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

				<c:set var="sourceIndex" value="0" />
				<c:forEach items="${rsDataBySrcNameDate}" var="rsdatabysrcnamedate">
					<c:set var="sourceIndex" value="${sourceIndex+1}" />
					<c:set var="waterInventoryId" value="${waterInventoryId}" />
					<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
					<c:set var="sourceName" value="${sourceName}" />
					<c:set var="sourceMeter" value="${sourceMeter}" />
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<div class="fg-line">
									<input type="hidden" name="wi_id" id="wi_id"
										value="${waterInventoryId}"> <input type="hidden"
										name="source_name[]" id="source_name[]	"
										value="${rsdatabysrcnamedate.sourceName}"> <label
										class="text-darkbrown">${rsdatabysrcnamedate.sourceName}:</label>
								</div>
							</div>
						</div>

						<c:set var="watrInvIdAndSnameObject"
							value="${regSourceDataWatrInvIdAndSnameObject}" />
						<c:set var="strtReading" value="${regSourceDataStartReading}" />
						<c:set var="rsDate" value="${regSourceDataDate}" />
						<c:set var="today" value="${today}" />
						<c:set var="sdate" value="${sdate}" />

						<c:choose>
							<c:when test="${sourceMeter == 'Yes'} && ${rsDate != today}">

								<div class="col-sm-2">
									<div class="form-group">
										<div class="fg-line">
											<input type="text" class="form-control" name="source_start[]"
												value="${strtReading}" id="source_start_${sidd}"
												onkeypress="numberValidate(event,'error_source_${sidd}')"
												onchange="setConsumption('source_end_${sidd}','source_start_${sidd}','${sidd}')">
										</div>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="form-group">
										<div class="fg-line">
											<input type="text" class="form-control" name="source_end[]"
												placeholder="End Reading" id="source_end_${sidd}"
												onkeypress="numberValidate(event,'error_source_${sidd}')"
												onchange="setConsumption('source_end_${sidd}','source_start_${sidd}','${sidd}')">
										</div>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="form-group">
										<div class="fg-line" id="source_avg_${sidd}">
											<input type="text" class="form-control" name="source_avg[]"
												disabled placeholder="Act Consumption">
										</div>
									</div>
								</div>
								<div class="col-sm-2" id="error_source_${sidd}"></div>
							</c:when>

							<c:when test="${sourceMeter == 'No'}">
								<c:choose>
									<c:when test="${rsDate == today}">
										<c:set var="sorId"
											value="${rsdatabysrcnamedate.regularSourceDataId}" />
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="hidden" class="form-control"
														name="source_start[]" id="source_start_${sidd}" value="0">
													<input type="hidden" class="form-control"
														name="source_end[]" id="source_end_${sidd}" value="0">
													<input type="text" class="form-control" name="source_avg[]"
														id="source_avg_${sidd}" disabled
														value="${rsdatabysrcnamedate.actualReading}">

												</div>
											</div>
										</div>
										<div class="col-sm-4"></div>
										<div class="col-sm-2" id="error_source_${sidd}">
											<button class="btn btn-default"
												onclick="changeDivData('enable','error_source_${sidd}','source_start_${sidd}','source_end_${sidd}', 'source_avg_${sidd}',1,${sorId},'source')">
												Change</button>
										</div>

									</c:when>
									<c:otherwise>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="hidden" class="form-control"
														name="source_start[]" value="0"> <input
														type="hidden" class="form-control" name="source_end[]"
														value="0"> <input type="text" class="form-control"
														name="avg_source_start[]" disabled
														value="${rsdatabysrcnamedate.actualReading}"
														onkeypress="numberValidate(event,'error_source_${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-4"></div>
										<div class="col-sm-2" id="error_source_${sidd}"></div>
									</c:otherwise>
								</c:choose>
							</c:when>

							<c:when test="${rsDate == today}">
								<c:set var="sorId"
									value="${rsdatabysrcnamedate.regularSourceDataId}" />
								<div class="col-sm-2">
									<div class="form-group">
										<div class="fg-line">
											<input type="text" class="form-control" name="source_start[]"
												disabled id="source_start_${sidd}"
												value="${rsdatabysrcnamedate.startReading}">
										</div>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="form-group">
										<div class="fg-line">
											<input type="text" class="form-control" name="source_end[]"
												id="source_end_${sidd}" disabled
												value="${rsdatabysrcnamedate.endReading}">
										</div>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="form-group">
										<div class="fg-line" id="act_source_start">
											<input type="text" class="form-control" name="source_avg[]"
												id="source_avg_${sidd}" disabled
												value="${rsdatabysrcnamedate.actualReading}">
										</div>
									</div>
								</div>
								<div class="col-sm-2" id="error_source_${sidd}">
									<button class="btn btn-default"
										onclick="changeDivData('enable','error_source_${sidd}','source_start_${sidd}','source_end_${sidd}','source_avg_${sidd}',3, ${sorId},'source')">
										Change</button>
								</div>
							</c:when>
						</c:choose>
					</div>
				</c:forEach>
				<hr>
				<!-- filters -->
				<label class="text-darkbrown"><u>Filters</u></label>
				<c:choose>
					<c:when test="${waterInventoryList.filterationPlant=='Yes'}">
						<c:set var='today' value="${today}"></c:set>
						<c:set var="numRowsFilter" value="${filterList.size()}" />
						<c:if test="${numRowsFilter > 0}">
							<c:forEach items="${filterList}" var="filterList">

								<c:set var="numRowsRegularFilter"
									value="${regularFilterDataArrayList.size()}" />
								<c:forEach items="${regularFilterDataArrayList}"
									var="regularFilterDataArrayList">
									<c:if
										test="${filterList.filterName == regularFilterDataArrayList.filterName}">
										<c:choose>
											<c:when test="${numRowsRegularFilter>0}">
												<c:set var="startReading"
													value="${regularFilterDataArrayList.endReading}" />
												<c:set var="rfDate1"
													value="${regularFilterDataArrayList.rfDate}" />

											</c:when>
											<c:otherwise>
												<c:set var="startReading" value="0" />
												<c:set var="rfDate1" value="" />
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when
												test="${filterList.waterMeter=='Yes' && rfDate1 != today}">
												<c:set var="sourceIndex" value="${sourceIndex+1}" />
												<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<label class="text-darkbrown">${filterList.filterName}
																	Filter (Source: ${filterList.sourceName}):</label> <input
																	type="hidden" name="filter_name[]"
																	value="${filterList.filterName}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_start[]" value="${startReading}"
																	id="filter_start_${sidd}"
																	onkeypress="numberValidate(event,'error_filter_${sidd}')"
																	onchange="setConsumption('filter_end_${sidd}',
												'filter_start_${sidd}'
												,'${sidd}')">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_end[]" placeholder="End Reading"
																	id="filter_end_${sidd}"
																	onkeypress="numberValidate(event,'error_filter_${sidd}')"
																	onchange="setConsumption('filter_end_${sidd}',
												'filter_start_${sidd}',
												'${sidd}')">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line" id="filter_avg_${sidd}">
																<input type="text" class="form-control"
																	name="avg_filter_start[]" disabled
																	placeholder="Act Consumption">
															</div>
														</div>
													</div>
													<div class="col-sm-2" id="error_filter_${sidd}"></div>
												</div>
											</c:when>
											<c:when test="${filterList.waterMeter=='No'}">
												<c:choose>
													<c:when test="${rfDate1 == today}">
														<c:set var="sourceIndex" value="${sourceIndex+1}" />
														<c:set var="sidd"
															value="${waterInventoryId}_${sourceIndex}" />
														<div class="row">
															<div class="col-sm-4">
																<div class="form-group">
																	<div class="fg-line">
																		<label class="text-darkbrown">${filterList.filterName}
																			Filter (Source: ${filterList.sourceName}):</label> <input
																			type="hidden" name="filter_name[]"
																			value="${regfiltrNameInvdate.filterName}">
																	</div>
																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group">
																	<div class="fg-line" id="filter_avg_${sidd}">
																		<input type="hidden" class="form-control"
																			name="filter_end[]" value="0" id="filter_end_${sidd}">
																		<input type="hidden" class="form-control"
																			name="filter_start[]" value="0"
																			id="filter_start_${sidd}"> <input type="text"
																			class="form-control" name="avg_filter_start[]"
																			id="avg_filter_start_${sidd}" disabled
																			value="${regularFilterDataArrayList.actualReading}">
																	</div>
																</div>
															</div>
															<div class="col-sm-4"></div>
															<div class="col-sm-2" id="error_filter_${sidd}">
																<button class="btn btn-default"
																	onclick="changeDivData('enable',
												'error_filter_${sidd}','filter_start_${sidd}','filter_end_${sidd}',
												'avg_filter_start_${sidd}',1, ${regularFilterDataArrayList.regularFiltesDataId},'filter')">
																	Change</button>
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<c:set var="sourceIndex" value="${sourceIndex+1}" />
														<c:set var="sidd"
															value="${waterInventoryId}_${sourceIndex}" />
														<div class="row">
															<div class="col-sm-4">
																<div class="form-group">
																	<div class="fg-line">
																		<label class="text-darkbrown">${filterList.filterName}
																			Filter (Source: ${filterList.sourceName}):</label> <input
																			type="hidden" name="filter_name[]"
																			value="${regfiltrNameInvdate.filterName}">
																	</div>
																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group">
																	<div class="fg-line">
																		<input type="hidden" class="form-control"
																			name="filter_start[]" value="0"> <input
																			type="hidden" class="form-control"
																			name="filter_end[]" value="0"> <input
																			type="text" class="form-control"
																			name="avg_filter_start[]"
																			placeholder="Act Consumption"
																			onkeypress="numberValidate(event,'error_filter_${sidd}')">
																	</div>
																</div>
															</div>
															<div class="col-sm-3" id="error_source_${sidd}"></div>
														</div>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:when test="${rfDate1 == today}">
												<c:set var="sourceIndex" value="${sourceIndex+1}" />
												<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="fg-line">
																<label class="text-darkbrown">${filterList.filterName}
																	Filter (Source: ${filterList.sourceName}):</label> <input
																	type="hidden" name="filter_name[]"
																	value="${filterList.filterName}">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_start[]"
																	value="${regularFilterDataArrayList.startReading}"
																	id="filter_start_${sidd}" disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line">
																<input type="text" class="form-control"
																	name="filter_end[]"
																	value="${regularFilterDataArrayList.endReading}"
																	id="filter_end_${sidd}" disabled>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="fg-line" id="filter_avg_${sidd}">
																<input type="text" class="form-control"
																	name="avg_filter_start[]" id="avg_filter_start_${sidd}"
																	disabled
																	value="${regularFilterDataArrayList.actualReading}">
															</div>
														</div>
													</div>
													<div class="col-sm-2" id="error_filter_${sidd}">
														<button class="btn btn-default"
															onclick="changeDivData('enable',
										'error_filter_${sidd}','filter_start_${sidd}','filter_end_${sidd}',
										'avg_filter_start_${sidd}',3,${regularFilterDataArrayList.regularFiltesDataId},'filter')">
															Change</button>
													</div>
												</div>

											</c:when>
										</c:choose>
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${filterUseAndMeter.size() >0}">
										<c:forEach items="${filterUseAndMeter}"
											var="filterUseAndMeter">
											<c:set var="numRowsFilterss"
												value="${filterUseDataNameType.size()}" />
											<c:forEach items="${filterUseDataNameType}"
												var="filterUseDataNameType">
												<c:if
													test="${filterList.filterName == filterUseDataNameType.filterName}">
													<c:if
														test="${filterUseAndMeter.filterUse == filterUseDataNameType.filterType }">
														<c:choose>
															<c:when test="${numRowsFilterss > 0}">
																<c:set var="startReading1"
																	value="${filterUseDataNameType.startReading}" />
																<c:set var="rs_date1"
																	value="${filterUseDataNameType.rfDate}" />
															</c:when>
															<c:otherwise>
																<c:set var="startReading1" value="0" />
																<c:set var="rs_date1" value="" />
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when
																test="${filterUseAndMeter.filterMeter == 'Yes' &&  rs_date1 != today}">
																<c:set var="sourceIndex" value="${sourceIndex+1}" />
																<c:set var="sidd"
																	value="${waterInventoryId}_${sourceIndex}" />
																<div class='row'>
																	<div class='col-sm-1'></div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<div class="fg-line">
																				<input type="hidden" name="filter_type[]"
																					value="${filterUseAndMeter.filterName}-${filterUseAndMeter.filterUse}">
																				<label class="text-darkbrown">${filterUseAndMeter.filterUse}:
																				</label>
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2">
																		<div class="form-group">
																			<div class="fg-line">
																				<input type="text" class="form-control"
																					name="filter_type_start[]" value="${startReading1}"
																					id="filter_type_start_${sidd}"
																					onkeypress="numberValidate(event,'error_filter_type_${sidd}')"
																					onchange="setConsumption('filter_type_end_${sidd}'
																	,'filter_type_start_${sidd}'
																	,'${sidd}')">
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2">
																		<div class="form-group">
																			<div class="fg-line">
																				<input type="text" class="form-control"
																					name="filter_type_end[]" placeholder="End Reading"
																					id="filter_type_end_${sidd}"
																					onkeypress="numberValidate(event,'error_filter_type_${sidd}')"
																					onchange="setConsumption('filter_type_end_${sidd}',
																	'filter_type_start_${sidd}',
																	'${sidd}')">
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2">
																		<div class="form-group">
																			<div class="fg-line" id="filter_type_avg_${sidd}">
																				<input type="text" class="form-control"
																					name="avg_filter_type_start[]" disabled
																					placeholder="Act Consumption">
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2" id="error_filter_type_${sidd}"></div>
																</div>
															</c:when>
															<c:when test="${filterUseAndMeter.filterMeter == 'No'}">
																<c:set var="sourceIndex" value="${sourceIndex+1}" />
																<c:set var="sidd"
																	value="${waterInventoryId}_${sourceIndex}" />
																<c:choose>
																	<c:when test="${rs_date1 == today}">
																		<div class="row">
																			<div class="col-sm-4">
																				<div class="form-group">
																					<div class="fg-line">
																						<label class="text-darkbrown">${filterUseAndMeter.filterUse}</label>
																						<input type="hidden" name="filter_name[]"
																							value="${filterUseAndMeter.filterUse}">
																					</div>
																				</div>
																			</div>
																			<div class="col-sm-2">
																				<div class="form-group">
																					<div class="fg-line">
																						<input type="hidden" class="form-control"
																							name="filter_end[]" value="0"
																							id="filter_type_end_${sidd}"> <input
																							type="hidden" class="form-control"
																							name="filter_start[]" value="0"
																							id="filter_type_start_${sidd}"> <input
																							type="text" id="avg_filter_type_start_${sidd}"
																							class="form-control"
																							name="avg_filter_type_start[]" disabled
																							value="${filterUseDataNameType.actualReading}">
																					</div>
																				</div>
																			</div>
																			<div class="col-sm-2" id="error_filter_type_${sidd}">
																				<button class="btn btn-default"
																					onclick="changeDivData('enable',
															'error_filter_type_${sidd}','filter_type_start_${sidd}','filter_type_end_${sidd}',
															'avg_filter_type_start_${sidd}',1,${filterUseDataNameType.regularFiltersUseDataId},'filter_type')">
																					Change</button>
																			</div>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<c:set var="sourceIndex" value="${sourceIndex+1}" />
																		<c:set var="sidd"
																			value="${waterInventoryId}_${sourceIndex}" />
																		<div class="row">
																			<div class='col-sm-1'></div>
																			<div class="col-sm-2">
																				<label class="text-darkbrown">${filterUseAndMeter.filterUse}
																					: </label>
																			</div>
																			<div class="col-sm-2">
																				<div class="form-group">
																					<div class="fg-line">
																						<input type="hidden" name="filter_type_start[]"
																							value="0"> <input type="hidden"
																							name="filter_type_end[]" value="0"> <input
																							type="text" class="form-control"
																							name="avg_filter_type_start${j}[]"
																							placeholder="Act Consumption">
																					</div>
																				</div>
																			</div>
																		</div>
																	</c:otherwise>
																</c:choose>
															</c:when>
															<c:when test="${rs_date1 == today}">
																<c:set var="sourceIndex" value="${sourceIndex+1}" />
																<c:set var="sidd"
																	value="${waterInventoryId}_${sourceIndex}" />
																<div class="row">
																	<div class="col-sm-4">
																		<div class="form-group">
																			<div class="fg-line">
																				<label class="text-darkbrown">${filterUseAndMeter.filterUse}</label>
																				<input type="hidden" name="filter_name[]"
																					value="${filterUseAndMeter.filterUse}">
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2">
																		<div class="form-group">
																			<div class="fg-line">
																				<input type="text" class="form-control"
																					name="filter_type_start[]"
																					value="${filterUseDataNameType.startReading}"
																					id="filter_type_start_${sidd}" disabled>
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2">
																		<div class="form-group">
																			<div class="fg-line">
																				<input type="text" class="form-control"
																					name="filter_type_end[]"
																					value="${filterUseDataNameType.endReading}"
																					id="filter_type_end_${sidd}" disabled>
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2">
																		<div class="form-group">
																			<div class="fg-line" id="filter_avg_${sidd}">
																				<input type="text" class="form-control"
																					name="avg_filter_type_start[]"
																					id="avg_filter_type_start_${sidd}" disabled
																					value="${filterUseDataNameType.actualReading}">
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-2" id="error_filter_type_${sidd}">
																		<button class="btn btn-default"
																			onclick="changeDivData('enable',
															'error_filter_type_${sidd}','filter_type_start_${sidd}','filter_type_end_${sidd}',
															'avg_filter_type_start_${sidd}',3, ${filterUseDataNameType.regularFiltersUseDataId},'filter_type')">
																			Change</button>
																	</div>
																</div>
															</c:when>

														</c:choose>
													</c:if>
												</c:if>
											</c:forEach>
										</c:forEach>
									</c:when>
								</c:choose>

							</c:forEach>
						</c:if>
					</c:when>
				</c:choose>
				<c:set var="sourceIndex" value="${ssss}" />

				<!--- Use Of Source Water Starts  -->
				<hr>
				<br />
				<label class="text-darkbrown"><u>Use Of Source Water</u></label>
				<c:set var='today' value="${today}"></c:set>
				<c:set var="useOfWaterInvList" value="${UseOfWaterInvList}" />
				<c:if test="${fn:length(useOfWaterInvList) ge 0}">
					<c:set var="sorId"
						value="${RegularWUDateWiId.regularWaterUseDataId}" />
					<c:if test="${waterInventoryList.domesticUseOfSource=='checked'}">
						<c:set var="k" value="0" />
						<c:forEach items="${UseOfWaterInvList}" var="useOfWaterInvList">
							<c:set var="sourceIndex" value="${sourceIndex+1}" />
							<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
							<c:choose>
								<c:when
									test="${useOfWaterInvList.domesticMeter=='Yes' && UWDrsDate != today}">
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
														value="${UWDstartReading}" id="use_start_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}'
											,'use_start_${sidd}'
											,'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="use_end[]"
														placeholder="End Reading" id="use_end_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}',
											'use_start_${sidd}',
											'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="use_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_use_start[]" disabled
														placeholder="Act Consumption">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_use_${sidd}"></div>
									</div>
								</c:when>

								<c:when test="${useOfWaterInvList.domesticMeter=='No'}">

									<c:choose>
										<c:when test="${UWDrsDate == today }">
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
														<div class="fg-line" id="filter_avg_${sidd}">
															<input type="hidden" class="form-control"
																name="filter_end[]" value="0"
																id="source_use_end_${sidd}" disabled> <input
																type="hidden" class="form-control" name="filter_start[]"
																value="0" id="source_use_start_${sidd}" disabled>
															<input type="text" class="form-control"
																name="avg_source_use_start[]" disabled
																id="avg_source_use_start_${sidd}"
																value="${RegularWUDateWiId.actualReading}">
														</div>
													</div>
												</div>
												<div class="col-sm-4"></div>
												<div class="col-sm-2" id="error_source_use_${sidd}">
													<button class="btn btn-default"
														onclick="changeDivData('enable',
														'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
														'avg_source_use_start_${sidd}',1,${sorId},'source_use')">
														Change</button>
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
								<c:when test="${UWDrsDate == today}">
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
														value="${RegularWUDateWiId.startReading}"
														id="source_use_start_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="filter_end[]"
														value="${RegularWUDateWiId.endReading}"
														id="source_use_end_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="filter_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_filter_start[]" disabled
														id="avg_source_use_start_${sidd}"
														value="${RegularWUDateWiId.actualReading}">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_source_use_${sidd}">
											<button class="btn btn-default"
												onclick="changeDivData('enable',
											'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
											'avg_source_use_start_${sidd}',3, ${sorId},'source_use')">
												Change</button>
										</div>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</c:if>
					<c:if test="${waterInventoryList.industrialUseOfSource=='checked'}">
						<c:forEach items="${IndustrialList}" var="industrialList">
							<c:set var="sourceIndex" value="${sourceIndex+1}" />
							<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
							<c:choose>
								<c:when
									test="${industrialList.waterMeter=='Yes' && Industrialdate != today}">
									<c:set var="k" value="${k+1}" />
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<div class="fg-line">
													<input type="hidden" name="source_use_name[]"
														value="${industrialList.indName}"> <label
														class="text-darkbrown">Industrial -
														${industrialList.indName}: </label>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="use_start[]"
														value="${indStartReading}" id="use_start_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}'
											,'use_start_${sidd}'
											,'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="use_end[]"
														placeholder="End Reading" id="use_end_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}',
											'use_start_${sidd}',
											'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="use_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_use_start[]" disabled
														placeholder="Act Consumption">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_use_${sidd}"></div>
									</div>
								</c:when>
								<c:when test="${industrialList.waterMeter=='No'}">
									<c:choose>
										<c:when test="${Industrialdate == today}">
											<c:set var="RWUDNameWaterInvDate"
												value="${RWUDNameWaterInvDate}" />
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<div class="fg-line">
															<input type="hidden" name="source_use_name[]"
																value="${industrialList.indName}"> <label
																class="text-darkbrown">Industrial -
																${industrialList.indName}: </label>
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<div class="fg-line" id="filter_avg_${sidd}">
															<input type="hidden" class="form-control"
																name="filter_end[]" value="0"
																id="source_use_end_${sidd}" disabled> <input
																type="hidden" class="form-control" name="filter_start[]"
																value="0" id="source_use_start_${sidd}" disabled>

															<input type="text" class="form-control"
																name="avg_source_use_start[]" disabled
																id="avg_source_use_start_${sidd}"
																value="${RWUDNameWaterInvDate.actualReading}">
														</div>
													</div>
												</div>
												<div class="col-sm-2" id="error_source_use_${sidd}">
													<button class="btn btn-default"
														onclick="changeDivData('enable',
										'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
										'avg_source_use_start_${sidd}',1, ${RWUDNameWaterInvDate.regularWaterUseDataId},'source_use')">
														Change</button>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<div class="fg-line">
															<input type="hidden" name="source_use_name[]"
																value="${industrialList.indName}"> <label
																class="text-darkbrown">Industrial -
																${industrialList.indName}: </label>
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
								<c:when test="${Industrialdate == today}">
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<div class="fg-line">
													<input type="hidden" name="source_use_name[]"
														value="${industrialList.indName}"> <label
														class="text-darkbrown">Industrial -
														${industrialList.indName}:</label>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control"
														name="filter_start[]"
														value="${RWUDNameWaterInvDate.startReading}"
														id="source_use_start_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="filter_end[]"
														value="${RWUDNameWaterInvDate.endReading}"
														id="source_use_end_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="filter_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_filter_start[]" disabled
														id="avg_source_use_start_${sidd}"
														value="${RWUDNameWaterInvDate.actualReading}">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_source_use_${sidd}">
											<button class="btn btn-default"
												onclick="changeDivData('enable',
											'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
											'avg_source_use_start_${sidd}',3, ${RWUDNameWaterInvDate.regularWaterUseDataId},'source_use')">
												Change</button>
										</div>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</c:if>

					<c:if test="${waterInventoryList.laundryUseOfSource == 'checked'}">
						<c:forEach items="${UseOfWaterInvList}" var="useOfWaterInvList">
							<c:set var="sourceIndex" value="${sourceIndex+1}" />
							<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
							<c:set var="RegWaterUseDataByStypeWiId"
								value="${regWaterUseDataByStypeWiId}" />
							<c:choose>
								<c:when
									test="${useOfWaterInvList.laundryMeter=='Yes' && laundrydate != today}">
									<c:set var="sourceIndex" value="${sourceIndex+1}" />
									<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
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
														value="${laundryStartReading}" id="use_start_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}'
												,'use_start_${sidd}'
												,'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="use_end[]"
														placeholder="End Reading" id="use_end_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}',
												'use_start_${sidd}',
												'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="use_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_use_start[]" disabled
														placeholder="Act Consumption">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_use_${sidd}"></div>
									</div>
								</c:when>
								<c:when test="${useOfWaterInvList.laundryMeter=='No'}">
									<c:set var="RegWaterUDByWiIdDate"
										value="${regWaterUDByWiIdDate}" />
									<c:choose>

										<c:when test="${laundrydate== today}">
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
														<div class="fg-line" id="filter_avg_${sidd}">
															<input type="hidden" class="form-control"
																name="filter_end[]" value="0"
																id="source_use_end_${sidd}" disabled> <input
																type="hidden" class="form-control" name="filter_start[]"
																value="0" id="source_use_start_${sidd}" disabled>

															<input type="text" class="form-control"
																name="avg_source_use_start[]" disabled
																id="avg_source_use_start_${sidd}"
																value="${RegWaterUDByWiIdDate.actualReading}">
														</div>
													</div>
												</div>
												<div class="col-sm-2" id="error_source_use_${sidd}">
													<button class="btn btn-default"
														onclick="changeDivData('enable',
												'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
												'avg_source_use_start_${sidd}',1, ${RegWaterUDByWiIdDate.regularWaterUseDataId},'source_use')">
														Change</button>
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
								<c:when test="${laundrydate == today}">
									<c:set var="RegWaterUDByWiIdDate"
										value="${regWaterUDByWiIdDate}" />
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
														value="${RegWaterUDByWiIdDate.startReading}"
														id="source_use_start_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="filter_end[]"
														value="${RegWaterUDByWiIdDate.endReading}"
														id="source_use_end_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="filter_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_filter_start[]" disabled
														id="avg_source_use_start_${sidd}"
														value="${RegWaterUDByWiIdDate.actualReading}">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_source_use_${sidd}">
											<button class="btn btn-default"
												onclick="changeDivData('enable',
										'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
										'avg_source_use_start_${sidd}',3, ${RegWaterUDByWiIdDate.regularWaterUseDataId},'source_use')">
												Change</button>
										</div>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>

					</c:if>

					<c:if
						test="${waterInventoryList.fireHydrantUseOfSource == 'checked'}">
						<c:set var="fireHdate" value="${fireHDate}" />
						<c:forEach items="${UseOfWaterInvList}" var="useOfWaterInvList">
							<c:set var="sourceIndex" value="${sourceIndex+1}" />
							<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
							<c:choose>
								<c:when
									test="${useOfWaterInvList.fireHydrantMeter=='Yes' && fireHdate != today}">
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
														value="${fireHStartReading}" id="use_start_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}'
											,'use_start_${sidd}'
											,'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="use_end[]"
														placeholder="End Reading" id="use_end_${sidd}"
														onkeypress="numberValidate(event,'error_use_${sidd}')"
														onchange="setConsumption('use_end_${sidd}',
											'use_start_${sidd}',
											'${sidd}')">
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="use_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_use_start[]" disabled
														placeholder="Act Consumption">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_use_${sidd}"></div>
									</div>
								</c:when>
								<c:when test="${useOfWaterInvList.fireHydrantMeter=='No'}">
									<c:choose>
										<c:when test="${fireHdate == today}">
											<c:set var="sourceIndex" value="${sourceIndex+1}" />
											<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
											<c:set var="RWUDWiIddate" value="${RWUDWiIdDate}" />
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
														<div class="fg-line" id="filter_avg_${sidd}">
															<input type="hidden" class="form-control"
																name="filter_end[]" value="0"
																id="source_use_end_${sidd}" disabled> <input
																type="hidden" class="form-control" name="filter_start[]"
																value="0" id="source_use_start_${sidd}" disabled>

															<input type="text" class="form-control"
																name="avg_source_use_start[]" disabled
																id="avg_source_use_start_${sidd}"
																value="${rWUDWiIdDateActualReding}">
														</div>
													</div>
												</div>
												<div class="col-sm-2" id="error_source_use_${sidd}">
													<button class="btn btn-default"
														onclick="changeDivData('enable',
										'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
										'avg_source_use_start_${sidd}',1, ${regularWaterUseDataId},'source_use')">
														Change</button>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<c:set var="sourceIndex" value="${sourceIndex+1}" />
											<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
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
								<c:when test="${fireHdate == today}">
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
														name="filter_start[]" value="${rWUDWiIdDateStartReding}"
														id="source_use_start_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line">
													<input type="text" class="form-control" name="filter_end[]"
														value="${rWUDWiIdDateEndReding}"
														id="source_use_end_${sidd}" disabled>
												</div>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<div class="fg-line" id="filter_avg_${sidd}">
													<input type="text" class="form-control"
														name="avg_filter_start[]" disabled
														id="avg_source_use_start_${sidd}"
														value="${rWUDWiIdDateActualReding}">
												</div>
											</div>
										</div>
										<div class="col-sm-2" id="error_source_use_${sidd}">
											<button class="btn btn-default"
												onclick="changeDivData('enable',
										'error_source_use_${sidd}','source_use_start_${sidd}','source_use_end_${sidd}',
										'avg_source_use_start_${sidd}',3, ${regularWaterUseDataId},'source_use')">
												Change</button>
										</div>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</c:if>

				</c:if>

				<hr>
				<br />
				<label class="text-darkbrown"><u>Waste-water Treatment</u></label>
				<br />
				<br />
				<br />
				<c:if test="${waterInventoryList.waterTreatment=='Yes'}">
					<c:set var="today" value="${today}" />
					<c:if test="${!empty WaterTreatmentTypeArrayList}">
						<c:forEach var="waterTreatmentTypeArrayList"
							items="${WaterTreatmentTypeArrayList}">
							<c:set var="treatmentType" value="${waterTreatmentTypeArrayList}" />
							<label class='text-darkbrown'>${treatmentType}</label>:
					<c:set var="l" value="0" />
							<c:forEach var="waterTreatmentTypeNameArrayList"
								items="${waterTreatmentTypeNameArrayList}">
								<c:set var="sourceIndex" value="${sourceIndex+1}" />
								<c:set var="sidd" value="${waterInventoryId}_${sourceIndex}" />
								<c:set var="typeName" value="${waterTreatmentTypeNameArrayList}" />
								<c:set var="treatmentType" value="${fn:split(typeName, '-')}" />
								<c:set var="l" value="${l+1}" />
								<%-- <c:set var="rTDByTypeWiIdArrayList" value="${RTDByTypeWiIdArrayList}"/> --%>
								<c:forEach var="rTDByTypeWiIdArrayList"
									items="${regularTreatmentDataByTypeWiIdArrayList}">
									<c:choose>
										<c:when test="${fn:length(useOfWaterInvList) ge 0}">
											<c:set var="rtDate" value="${rTDByTypeWiIdArrayList.rtDate}" />
										</c:when>
										<c:otherwise>
											<c:set var="wasteWaterStartReading" value="" />
											<c:set var="rtDate" value="" />
										</c:otherwise>
									</c:choose>

									<c:forEach var="rTDByTypeWiIdDateArrayList"
										items="${regTreatmentDataByTypeWiIdDateArrayList}">
										<c:if
											test="${waterTreatmentTypeArrayList == rTDByTypeWiIdArrayList.type}">
											<c:if
												test="${treatmentType[0] == rTDByTypeWiIdDateArrayList.treatmentType}">
												<c:if
													test="${treatmentType[0] == rTDByTypeWiIdArrayList.treatmentType}">
													<c:choose>
														<c:when test="${rtDate == today}">
															<div class="row">
																<div class="col-sm-4">
																	<div class="form-group">
																		<div class="fg-line">
																			<input type="hidden" name="treatment_name[]"
																				value="${treatmentType}"> <label
																				class="text-darkbrown-nobold">${typeName} :</label>
																		</div>
																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group">
																		<div class="fg-line">
																			<input type="text" class="form-control"
																				name="treatment_start[]"
																				value="${rTDByTypeWiIdDateArrayList.startReading}"
																				id="treatment_start_${sidd}" disabled>
																		</div>
																	</div>
																</div>

																<div class="col-sm-2">
																	<div class="form-group">
																		<div class="fg-line">
																			<input type="text" class="form-control"
																				name="treatment_end[]"
																				value="${rTDByTypeWiIdDateArrayList.endReading}"
																				id="treatment_end_${sidd}" disabled>
																		</div>
																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group">
																		<div class="fg-line" id="filter_avg_${sidd}">
																			<input type="text" class="form-control"
																				name="avg_treatment_start[]" disabled
																				id="avg_treatment_start_${sidd}"
																				value="${rTDByTypeWiIdDateArrayList.actualReading}">
																		</div>
																	</div>
																</div>
																<div class="col-sm-2" id="error_treatment_${sidd}">
																	<button class="btn btn-default"
																		onclick="changeDivData('enable',
										'error_treatment_${sidd}','treatment_start_${sidd}','treatment_end_${sidd}',
										'avg_treatment_start_${sidd}',3, ${rTDByTypeWiIdDateArrayList.regularTreatmentDataId},'treatment')">
																		Change</button>
																</div>
															</div>
														</c:when>
														<c:otherwise>
															<div class="row">
																<div class="col-sm-4">
																	<div class="form-group">
																		<div class="fg-line">
																			<input type="hidden" name="treatment_name[]"
																				value="${treatmentType}"> <input
																				type="hidden" name="treatment_type[]"
																				value="<?php //echo $type; ?>"> <label
																				class="text-darkbrown-nobold">${typeName} :</label>
																		</div>
																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group">
																		<div class="fg-line">
																			<input type="text" class="form-control"
																				name="treatment_start[]" placeholder="Inlet"
																				id="treatment_start_${sidd}"
																				onkeypress="numberValidate(event,'error_treatment_${sidd}')"
																				onchange="setTreatedConsumption('treatment_end_${sidd}'
												,'treatment_start_${sidd}'
												,'${sidd}')">
																		</div>
																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group">
																		<div class="fg-line">
																			<input type="text" class="form-control"
																				name="treatment_end[]" placeholder="Outlet"
																				id="treatment_end_${sidd}"
																				onkeypress="numberValidate(event,'error_treatment_${sidd}')"
																				onchange="setTreatedConsumption('treatment_end_${sidd}',
												'treatment_start_${sidd}',
												'${sidd}')">
																		</div>
																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group">
																		<div class="fg-line" id="treatment_avg_${sidd}">
																			<input type="text" class="form-control"
																				name="avg_treatment_start[]" disabled
																				placeholder="Actual Loss">
																		</div>
																	</div>
																</div>
																<div class="col-sm-2" id="error_treatment_${sidd}"></div>
															</div>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:if>
										</c:if>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</c:if>

				</c:if>
			</c:forEach>
			<c:set var="count" value="0" />
			<%-- </c:forEach> --%>
		</c:when>

		<c:otherwise>
			<center>
				<img src="img/nodata.png"></img>
			</center>
		</c:otherwise>
	</c:choose>
</div>