<div class="row mt-3">
	<div class="form-group col-4 offset-2">
		<div class="radio radio--inline cursor-pointer">
			<input type="radio" name="ambientData" id='ambientYes'
				onclick="showAmbientForm('Yes')"> <label
				class="radio__label" for="ambientYes">Ambient Details</label>
		</div>
	</div>
	<div class="form-group col-6">
		<div class="radio radio--inline cursor-pointer">
			<input type="radio" name="ambientData" id='ambientNo'
				onclick="showAmbientForm('Popup')"> <label
				class="radio__label" for="ambientNo">Upload xlsheet having
				ambient more than one</label>
		</div>
	</div>
</div>
<!-- Ambient Form -->
<div id="ambientForm" style="display: none;">
	<div class="row mt-3">
		<div class="col-12 mb-3 text-center">
			<h4>
				<b>Add Ambient Air Monitoring Details</b>
			</h4>
		</div>
		<div class="col-6">
			<div class="form-group">
				<label class="active">Location Identity</label> <input type="text"
					name="loc_ident" id="loc_ident" class="form-control"
					placeholder="Location Identity">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-6">
			<div class="form-group">
				<label class="active">Location Identity</label> <select
					class="select2" name="criteria">
					<option value=''>Select option</option>
					<option value='Up Wind'>Up Wind</option>
					<option value='Down Wind'>Down Wind</option>
					<option value='Cross Wind'>Cross Wind</option>
					<option value='Other'>Other</option>
				</select>
				<div class="invalid-feedback">Please select any !</div>
			</div>
		</div>
	</div>
	<div class="row mt-3">
		<div class="col-12">
			<p class="font-weight-bolder">Ambient Air Pollutant</p>
		</div>
	</div>
	<div class="row">

		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox1" value="Particulate Matter (PM10)"
							onclick="addLimitsUnits(1, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox1">Particulate
							Matter PM<sub>10</sub>
						</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_1"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox2" value="Particulate Matter (PM2.5)"
							onclick="addLimitsUnits(2, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox2">Particulate
							Matter PM<sub>2.5</sub>
						</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_2"></div>
			</div>
		</div>

		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox3" value="Sulphur Dioxide (SO2)"
							onclick="addLimitsUnits(3, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox3">Sulphur
							Dioxide (SO<sub>2</sub>)
						</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_3"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox4" value="Nickel (Ni)"
							onclick="addLimitsUnits(4, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox4">Nickel
							(Ni)</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_4"></div>
			</div>
		</div>

		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox5" value="NOx"
							onclick="addLimitsUnits(5, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox5">NOx</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_5"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox6" value="HCL"
							onclick="addLimitsUnits(6, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox6">HCL</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_6"></div>
			</div>
		</div>

		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox7" value="Carbon Monoxide (CO)"
							onclick="addLimitsUnits(7, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox7">Carbon
							Monoxide (CO)</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_7"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox8" value="Lead (Pb)"
							onclick="addLimitsUnits(8, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox8">Lead
							(Pb)</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_8"></div>
			</div>
		</div>

		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox9" value="Ammonia (NH3)"
							onclick="addLimitsUnits(9, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox9">Ammonia
							(NH3)</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_9"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox10" value="Arsenic (As)"
							onclick="addLimitsUnits(10, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox10">Arsenic
							(As)</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_10"></div>
			</div>
		</div>

		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox11" value="Benzene (C6H6)"
							onclick="addLimitsUnits(11, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox11">Benzene
							(C6H6)</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_11"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="ambient_poll[]"
							id="ambientpollCheckBox12" value="Benzo(a)Pyrene (BaP)"
							onclick="addLimitsUnits(12, 'ambient')"> <label
							class="checkbox__label" for="ambientpollCheckBox12">Benzo(a)Pyrene
							(BaP)</label>
					</div>
				</div>
				<div class="col-7" id="appendambientLimitPoll_12"></div>
			</div>
		</div>
	</div>
	<!-- add more -->
	<div class="row mt-4">
		<div class="col-12">
			<b>*Click here to add more parameter</b>
			<button type="button" class="btn btn-sm btn-info pt-1 pb-1"
				onclick="addExtraPoll('amb');">
				<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
			</button>
		</div>
		<div class="col-12 mt-3" id="appendambPoll"></div>
	</div>
	<div class="row mt-4 mb-4">
		<div class="col text-center">
			<button type="button" class="btn btn-primary btn-sm"
				onclick="saveAmbientData(this);" id="save-ambient-btn">
				<i class='zmdi zmdi-save'></i> Save
			</button>
		</div>
	</div>
</div>

<!-- modal popup -->
<div class="modal fade" id="upload-ambient-excel-modal"
	data-backdrop="static" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title font-weight-bold">Upload multiple
					ambient data</h4>
				<div class="actions">
					<span class="mantooltip hover" data-jbox-title=""
						data-jbox-content="download stack and stack parameter <br>files and add multiple stack data easily."><a
						class="actions__item zmdi zmdi-help"></a></span>
					<div class="dropdown actions__item">
						<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
						<div
							class="dropdown-menu dropdown-menu-right custom-dropdown-menu">
							<a class="ml-2" href="newAssets/documents/Ambient.xlsx"><button
									class="btn btn-success">
									<i class="zmdi zmdi-download"></i> Ambient details
								</button></a> <a class="ml-2"
								href="newAssets/documents/AmbientParameter.xlsx"><button
									class="btn btn-success">
									<i class="zmdi zmdi-download"></i> Ambient parameter
								</button></a>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-body row">
				<div class="col-6">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<label>Upload .csv file of ambient details:</label> <span
							class="btn btn-primary btn-file"> <span
							class="fileinput-new">Select file</span> <span
							class="fileinput-exists">Change</span> <input type="file"
							name="ambientFile" id="ambientFile" accept=".xlsx" required>
						</span> <span class="fileinput-filename"></span> <a href="#"
							class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
					</div>
				</div>
				<div class="col-6">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<label>Upload .csv file of ambient parameter details</label> <span
							class="btn btn-primary btn-file"> <span
							class="fileinput-new">Select file</span> <span
							class="fileinput-exists">Change</span> <input type="file"
							name="ambientParameter" id="ambientParameter" accept=".xlsx"
							required>
						</span> <span class="fileinput-filename"></span> <a href="#"
							class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
					</div>
				</div>
				<div class="col-12 text-center">
					<button class="btn btn-primary btn--icon-text mt-4" id="saveD"
						onclick="saveExcelSheet('ambient')">
						<i class="zmdi zmdi-upload"></i> Upload
					</button>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>