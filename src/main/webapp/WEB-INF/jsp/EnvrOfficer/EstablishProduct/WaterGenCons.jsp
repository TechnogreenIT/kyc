<div class="row mt-4">
	<div class="col-12 mb-2">
		<p class="font-weight-bolder">(*Write down Water Consumption and
			Effluent Generation as given in Consent )</p>
	</div>

	<div class="col-7">
		<p class="font-weight-bolder">Purpose</p>
	</div>
	<div class="col-2">
		<p class="font-weight-bolder">Consumption</p>
	</div>
	<div class="col-2">
		<p class="font-weight-bolder">Generation</p>
	</div>
	<div class="col-1">
		<p class="font-weight-bolder">Unit</p>
	</div>

	<!-- purpose 1 -->
	<div class="col-7">
		<div class="form-group">
			<input type="hidden" name="purpose[]" value="domestic"> <label
				class="form-control no-border">Domestic Purpose</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="number" class="form-control" name="consum[]"
				id="domes_consum" onchange="javascript:conSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" class="form-control" name="gen[]" id="domes_gen"
				onchange="javascript:genSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-1">
		<div class="form-group">
			<input type="text" name="unit[]" class="form-control" value="CMD"
				disabled>
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<!-- purpose 1 -->


	<!-- purpose 2 -->
	<div class="col-7">
		<div class="form-group">
			<input type="hidden" name="purpose[]" value="Biodegradable">
			<label class="form-control no-border">Processing whereby
				water gets Polluted & Pollutants are Biodegradable</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="consum[]" class="form-control"
				id="biodeg_consum" onchange="javascript:conSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="gen[]" class="form-control" id="biodeg_gen"
				onchange="javascript:genSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-1">
		<div class="form-group">
			<input type="text" name="unit[]" class="form-control" value="CMD"
				disabled>
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<!-- purpose 2 -->

	<!-- purpose 3 -->
	<div class="col-7">
		<div class="form-group">
			<input type="hidden" name="purpose[]" value="Non Biodegradable">
			<label class="form-control no-border">Processing whereby
				Water gets Polluted,Pollutants are Non Biodegradable & Toxic</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="consum[]" class="form-control"
				id="notbiodeg_consum" onchange="javascript:conSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="gen[]" class="form-control"
				id="notbiodeg_gen" onchange="javascript:genSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-1">
		<div class="form-group">
			<input type="text" name="unit[]" class="form-control" value="CMD"
				disabled>
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<!-- purpose 3 -->

	<!-- purpose 4 -->
	<div class="col-7">
		<div class="form-group">
			<input type="hidden" name="purpose[]" value="cooling"> <label
				class="form-control no-border">Industrial Cooling,spraying
				in mine pits or boiler feed</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="consum[]" class="form-control"
				id="ind_consum" onchange="javascript:conSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="gen[]" class="form-control" id="ind_gen"
				onchange="javascript:genSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-1">
		<div class="form-group">
			<input type="text" name="unit[]" class="form-control" value="CMD"
				disabled>
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<!-- purpose 4 -->

	<!-- purpose 5 -->
	<div class="col-7">
		<div class="form-group">
			<input type="hidden" name="purpose[]" value="Others"> <label
				class="form-control no-border">Others</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="consum[]" class="form-control"
				id="Others_consum" onchange="javascript:conSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" name="gen[]" class="form-control" id="Others_gen"
				onchange="javascript:genSum(this);">
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<div class="col-1">
		<div class="form-group">
			<input type="text" name="unit[]" class="form-control" value="CMD"
				disabled>
			<div class="invalid-feedback">Required !</div>
			<i class="form-group__bar"></i>
		</div>
	</div>
	<!-- purpose 5 -->

	<!-- purpose 6 -->
	<div class="col-7">
		<div class="form-group">
			<label class="form-control no-border">Total Sewage</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" class="form-control" id="dcRes" disabled>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" class="form-control" id="dgRes" disabled>
		</div>
	</div>
	<!-- purpose 6 -->

	<!-- purpose 7 -->
	<div class="col-7">
		<div class="form-group">
			<label class="form-control no-border">Total Effluent</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" class="form-control" id="ecRes" disabled>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" class="form-control" id="egRes" disabled>
		</div>
	</div>
	<!-- purpose 7 -->

	<!-- purpose 8 -->
	<div class="col-7">
		<div class="form-group">
			<label class="form-control no-border">Total</label>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" class="form-control" id="cRes" disabled>
		</div>
	</div>
	<div class="col-2">
		<div class="form-group">
			<input type="text" class="form-control" id="gRes" disabled>
		</div>
	</div>
	<!-- purpose 8 -->
</div>
<div class="row mt-4 mb-4">
	<div class="col text-center">
		<button type="button" class="btn btn-primary btn-sm"
			onclick="saveWaterConGen(this,'waterConGen');"
			id="save-waterConGen-btn">
			<i class='zmdi zmdi-save'></i> Save
		</button>
	</div>
</div>