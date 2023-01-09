<div class="row mt-3">
	<div class="form-group col-4 offset-2">
		<div class="radio radio--inline cursor-pointer">
			<input type="radio" name="stackData" id='stackYes'
				onclick="showStackForm('Yes')"> <label class="radio__label"
				for="stackYes">Stack Details</label>
		</div>
	</div>
	<div class="form-group col-6">
		<div class="radio radio--inline cursor-pointer">
			<input type="radio" name="stackData" id='stackNo'
				onclick="showStackForm('Popup')"> <label
				class="radio__label" for="stackNo">Upload xlsheet having
				stack more than one</label>
		</div>
	</div>
</div>
<!-- Stack Form -->
<div id="stackForm" style="display: none;">
	<div class="row mt-3">
		<div class="col-12 mb-3 text-center">
			<h4>
				<b>Add Stack Details</b>
			</h4>
		</div>
		<div class="col-3">
			<div class="form-group">
				<label>Stack Name</label> <input type="text" class="form-control"
					placeholder="Stack Name" id="stack_name" name="stack_name">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-3">
			<div class="form-group">
				<label>Attached to</label> <select class="select2" id="attached_to"
					name="attached_to">
					<option value="">Select Attached to</option>
					<option value="DG Sets">DG Sets</option>
					<option value="Process">Process</option>
					<option value="Boilers">Boilers</option>
					<option value="Others">Others</option>
				</select>
				<div class="invalid-feedback">Please select any !</div>
			</div>
		</div>
		<div class="col-3">
			<div class="form-group">
				<label>Capacity</label> <input type="number" class="form-control"
					placeholder="Capacity" id="capacity" name="capacity">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-3">
			<div class="form-group">
				<label>Units</label> <input type="text" class="form-control"
					placeholder="Units" id="capacity_units" name="capacity_units">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			<div class="form-group">
				<label>Material of Construction</label> <input type="text"
					class="form-control" placeholder="Material of Construction"
					id="mat_cons" name="mat_cons">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			<div class="form-group">
				<label>Shape</label> <input type="text" class="form-control"
					placeholder="Shape" id="shape" name="shape">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			<div class="form-group">
				<label>Fuel Type</label> <input type="text" class="form-control"
					placeholder="Fuel Type" id="fuel_type" name="fuel_type">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			<div class="form-group">
				<label>Fuel Quantity</label> <input type="number" class="form-control"
					id="fuel_quant" name="fuel_quant" placeholder="Fuel Quantity">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			<div class="form-group">
				<label>Units</label> <select class="select2" id="fuel_units"
					name="fuel_units">
					<option value="">Select uom</option>
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
				<div class="invalid-feedback">Please select any !</div>
			
			</div>
		</div>
		<div class="col-2">
			<div class="form-group">
				<label>Height</label> <input type="number" class="form-control"
					id="height" name="height" placeholder="Height">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			<div class="form-group">
				<label>Units</label> <input type="text" class="form-control"
					id="ht_units" name="ht_units" placeholder="m or mm">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			<div class="form-group">
				<label>Diameter</label> <input type="number" class="form-control"
					id="diam" name="diam" placeholder="Diameter">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			<div class="form-group">
				<label>Units</label> <input type="text" class="form-control"
					id="diam_units" name="diam_units" placeholder="m or mm">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		
		
		
		<!--changes by pallavi-->
		<div class="col-2">
			    <div class="form-group">
				<label>Gas Quantity</label> <input type="number" class="form-control"
					id="gas_quant" name="gas_quant" placeholder="Gas Quantity">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			    <div class="form-group">
				<label>Gas Unit</label> <input type="text" class="form-control"
					id="gas_unit" name="gas_unit" placeholder="Gas Unit">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			    <div class="form-group">
				<label>Gas Temperature</label> <input type="number" class="form-control"
					id="gas_temp" name="gas_temp" placeholder=" Gas Temperature">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			    <div class="form-group">
				<label>Gas Temperature Unit</label> <input type="text" class="form-control"
					id="gas_temp_unit" name="gas_temp_unit" placeholder=" Gas Temperature Unit">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			    <div class="form-group">
				<label>Exit gas velocity</label> <input type="number" class="form-control"
					id="Exit_gas_vel" name="Exit_gas_vel" placeholder="Exit gas velocity">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-2">
			    <div class="form-group">
				<label>Exit gas velocity Unit</label> <input type="text" class="form-control"
					id="Exit_gas_unit" name="Exit_gas_unit" placeholder="Exit gas velocity Unit">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			    <div class="form-group">
				<label>Control equipment preceding the stack</label> <input type="text" class="form-control"
					id="preceding_stack" name="preceding_stack" placeholder= "Preceding the stack">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			    <div class="form-group">
				<label>Pollutants present in stack gases</label> <input type="text" class="form-control"
					id="pollu_present" name="pollu_present" placeholder="Pollutants in stack gases">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			    <div class="form-group">
				<label>Emissions control system provided</label> <input type="text" class="form-control"
					id="ECS_provided" name="ECS_provided" placeholder="Emissions control system ">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			    <div class="form-group">
				<label>D.G set Power generation capacity</label> <input type="number" class="form-control"
					id="gen_capacity" name="gen_capacity" placeholder="D.G set Power generation capacity">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		<div class="col-4">
			    <div class="form-group">
				<label>D.G set Power generation capacity unit</label> <input type="text" class="form-control"
					id="gen_Cap_unit" name="gen_Cap_unit" placeholder="D.G set Power generation capacity unit">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
		
	</div>
	<div class="row mt-3">
		<div class="form-group col-5">
			<label class="font-weight-bold"> Do you have Pollution
				Control Equipment?</label><br>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="apc" id="apcYes" value="Yes"
					onclick="showAPC('Yes')"> <label class="radio__label"
					for="apcYes">Yes</label>
			</div>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="apc" id="apcNo" value='No'
					onclick="showAPC('No')" checked> <label
					class="radio__label" for="apcNo">No</label>
			</div>
		</div>
		<div class="col-7" id="apcForm" style="display: none">
			<div class="form-group">
				<label>APC System</label> <input type="text" class="form-control"
					id="apc_system" name="apc_system" placeholder="e.g. Water scrubber">
				<div class="invalid-feedback">Please enter something !</div>
				<i class="form-group__bar"></i>
			</div>
		</div>
	</div>
	<!-- Select Stack Pollutant -->
	<div class="row mt-3">
		<div class="col-12">
			<p class="font-weight-bolder">Select Stack Pollutant</p>
		</div>
	</div>
	<div class="row">
		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox1"
							value="Total Particulate Matter (TPM)"
							onclick="addLimitsUnits(1, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox1">Total
							Particulate Matter (TPM)</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_1"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox2"
							value="SO2" onclick="addLimitsUnits(2, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox2">SO<sub>2</sub></label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_2"></div>
			</div>
		</div>
		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox3"
							value="NOx" onclick="addLimitsUnits(3, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox3">NO<sub>x</sub></label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_3"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox4"
							value="HCL" onclick="addLimitsUnits(4, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox4">HCL</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_4"></div>
			</div>
		</div>
		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox5"
							value="Total Organic" onclick="addLimitsUnits(5, 'stack')">
						<label class="checkbox__label" for="stackpollCheckBox5">Total
							Organic</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_5"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox6"
							value="CO" onclick="addLimitsUnits(6, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox6">CO</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_6"></div>
			</div>
		</div>
		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox7"
							value="Total Dioxins" onclick="addLimitsUnits(7, 'stack')">
						<label class="checkbox__label" for="stackpollCheckBox7">Total
							Dioxins</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_7"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox8"
							value="HF" onclick="addLimitsUnits(8, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox8">HF</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_8"></div>
			</div>
		</div>
		<div class="col-5">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]" id="stackpollCheckBox9"
							value="cd+Th" onclick="addLimitsUnits(9, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox9">cd+Th</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_9"></div>
			</div>
		</div>
		<div class="col-6 offset-1">
			<div class="row">
				<div class="col-5 mt-2">
					<div class="checkbox">
						<input type="checkbox" name="stack_poll[]"
							id="stackpollCheckBox10" value="Hg"
							onclick="addLimitsUnits(10, 'stack')"> <label
							class="checkbox__label" for="stackpollCheckBox10">Hg</label>
					</div>
				</div>
				<div class="col-7" id="appendstackLimitPoll_10"></div>
			</div>
		</div>
	</div>
	<!-- add more -->
	<div class="row mt-4">
		<div class="col-12">
			<b>*Click here to add more parameter</b>
			<button type="button" class="btn btn-sm btn-info pt-1 pb-1"
				onclick="addExtraPoll('st');">
				<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
			</button>
		</div>
		<div class="col-12 mt-3" id="appendstPoll"></div>
	</div>
	<div class="row mt-4 mb-4">
		<div class="col text-center">
			<button type="button" class="btn btn-primary btn-sm"
				onclick="saveStackData(this);" id="save-stack-btn">
				<i class='zmdi zmdi-save'></i> Save
			</button>
		</div>
	</div>
</div>


<!-- modal Stack popup -->
<div class="modal fade" id="upload-stack-excel-modal"
	data-backdrop="static" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title font-weight-bold">Upload multiple stack
					data</h4>
				<div class="actions">
					<span class="mantooltip hover" data-jbox-title=""
						data-jbox-content="download stack and stack parameter <br>files and add multiple stack data easily."><a
						class="actions__item zmdi zmdi-help"></a></span>
					<div class="dropdown actions__item">
						<i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
						<div
							class="dropdown-menu dropdown-menu-right custom-dropdown-menu">
							<a class="ml-2" href="../newAssets/documents/Stack.xlsx"><button
									class="btn btn-success">
									<i class="zmdi zmdi-download"></i> Stack details
								</button></a> <a class="ml-2" href="../newAssets/documents/StackParameter.xlsx"><button
									class="btn btn-success">
									<i class="zmdi zmdi-download"></i> Stack parameter
								</button></a>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-body row">
				<div class="col-6">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<label>Upload .csv file of stack details:</label> <span
							class="btn btn-primary btn-file"> <span
							class="fileinput-new">Select file</span> <span
							class="fileinput-exists">Change</span> <input type="file"
							name="stackFile" id="stackFile" accept=".xlsx" required>
						</span> <span class="fileinput-filename"></span> <a href="#"
							class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
					</div>
				</div>
				<div class="col-6">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<label>Upload .csv file of stack parameter details</label> <span
							class="btn btn-primary btn-file"> <span
							class="fileinput-new">Select file</span> <span
							class="fileinput-exists">Change</span> <input type="file"
							name="stackParameter" id="stackParameter" accept=".xlsx" required>
						</span> <span class="fileinput-filename"></span> <a href="#"
							class="close fileinput-exists" data-dismiss="fileinput">&times;</a>
					</div>
				</div>
				<div class="col-12 text-center">
					<button class="btn btn-primary btn--icon-text mt-4" id="saveD"
						onclick="saveExcelSheet('stack')">
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