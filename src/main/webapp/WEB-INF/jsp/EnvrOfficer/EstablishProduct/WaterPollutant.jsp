
<!-- eff poll data -->
<div class="row mt-4">
	<div class="col-12">
		<p class="font-weight-bolder">Select Effluent Pollutant</p>
	</div>
	<div class="col-5">
		<div class="row">
			<div class="col-5 mt-2">
				<div class="checkbox">
					<input type="checkbox" name="eff_poll[]" id="effpollCheckBox1" 
						value="pH" onclick="addLimitsUnits(1, 'eff')"> <label
						class="checkbox__label" for="effpollCheckBox1">pH</label>
				</div>
			</div>
			<div class="col-7" id="appendeffLimitPoll_1"></div>
		</div>
	</div>
	<div class="col-6 offset-1">
		<div class="row">
			<div class="col-5 mt-2">
				<div class="checkbox">
					<input type="checkbox" name="eff_poll[]" id="effpollCheckBox2"
						value="Biological Oxygen Demand (BOD)" 
						onclick="addLimitsUnits(2, 'eff')"> <label
						class="checkbox__label" for="effpollCheckBox2">Biological
						Oxygen Demand (BOD)</label>
				</div>
			</div>
			<div class="col-7" id="appendeffLimitPoll_2"></div>
		</div>
	</div>
	<div class="col-5">
		<div class="row">
			<div class="col-5 mt-2">
				<div class="checkbox">
					<input type="checkbox" name="eff_poll[]" id="effpollCheckBox3"
						value="Total Dissolved Solids (TDS)"
						onclick="addLimitsUnits(3, 'eff')"> <label
						class="checkbox__label" for="effpollCheckBox3">Total
						Dissolved Solids (TDS)</label>
				</div>
			</div>
			<div class="col-7" id="appendeffLimitPoll_3"></div>
		</div>
	</div>
	<div class="col-6 offset-1">
		<div class="row">
			<div class="col-5 mt-2">
				<div class="checkbox">
					<input type="checkbox" name="eff_poll[]" id="effpollCheckBox4"
						value="Total Suspended Solids (TSS)"
						onclick="addLimitsUnits(4, 'eff')"> <label
						class="checkbox__label" for="effpollCheckBox4">Total
						Suspended Solids (TSS)</label>
				</div>
			</div>
			<div class="col-7" id="appendeffLimitPoll_4"></div>
		</div>
	</div>
</div>
<!-- add more -->
<div class="row mt-4">
	<div class="col-12">
		<b>*Click here to add more parameter</b>
		<button type="button" class="btn btn-sm btn-info pt-1 pb-1"
			onclick="addExtraPoll('eff');">
			<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
		</button>
	</div>
	<div class="col-12 mt-3" id="appendeffPoll"></div>
</div>
<!-- eff poll data -->

<!-- sew poll data -->
<div class="row mt-4">
	<div class="col-12">
		<p class="font-weight-bolder">Select Sewage Pollutant</p>
	</div>
	<div class="col-5">
		<div class="row">
			<div class="col-5 mt-2">
				<div class="checkbox">
					<input type="checkbox" name="sew_poll[]" id="sewpollCheckBox1"
						value="pH" onclick="addLimitsUnits(1, 'sew')"> <label
						class="checkbox__label" for="sewpollCheckBox1">pH</label>
				</div>
			</div>
			<div class="col-7" id="appendsewLimitPoll_1"></div>
		</div>
	</div>
	<div class="col-6 offset-1">
		<div class="row">
			<div class="col-5 mt-2">
				<div class="checkbox">
					<input type="checkbox" name="sew_poll[]" id="sewpollCheckBox2"
						value="Biological Oxygen Demand (BOD)"
						onclick="addLimitsUnits(2, 'sew')"> <label
						class="checkbox__label" for="sewpollCheckBox2">Biological
						Oxygen Demand (BOD)</label>
				</div>
			</div>
			<div class="col-7" id="appendsewLimitPoll_2"></div>
		</div>
	</div>
	<div class="col-5">
		<div class="row">
			<div class="col-5 mt-2">
				<div class="checkbox">
					<input type="checkbox" name="sew_poll[]" id="sewpollCheckBox3"
						value="Total Suspended Solids (TSS)"
						onclick="addLimitsUnits(3, 'sew')"> <label
						class="checkbox__label" for="sewpollCheckBox3">Total
						Suspended Solids (TSS)</label>
				</div>
			</div>
			<div class="col-7" id="appendsewLimitPoll_3"></div>
		</div>
	</div>
</div>
<!-- add more -->
<div class="row mt-4">
	<div class="col-12">
		<b>*Click here to add more parameter</b>
		<button type="button" class="btn btn-sm btn-info pt-1 pb-1"
			onclick="addExtraPoll('sew');">
			<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
		</button>
	</div>
	<div class="col-12 mt-3" id="appendsewPoll"></div>
</div>
<!-- sew poll data -->

<div class="row mt-4 mb-4">
	<div class="col text-center">
		<button type="button" class="btn btn-primary btn-sm"
			onclick="saveWaterPollutant(this);" id="save-waterPoll-btn">
			<i class='zmdi zmdi-save'></i> Save
		</button>
	</div>
</div>