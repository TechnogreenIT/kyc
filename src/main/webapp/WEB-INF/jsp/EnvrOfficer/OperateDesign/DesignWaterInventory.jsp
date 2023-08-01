<div class="mt-4" id="waterSourceDiv">
	<div class="row">
		<div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">
			<div class="form-group">
				<select class="select2" data-placeholder="Select Source"
					name="waterSourceNames[]" id="appendWaterSources" onchange="addcgwa();">
					<option value="">Select Source</option>

				</select>
				<div class="invalid-feedback">Please select any !</div>
			</div>
		</div>

		<div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">
			<div class="form-group">
				<label class="font-weight-bold">1. Do You Have Water Meter
					for Source Water?</label></br>
				<div class="radio radio--inline cursor-pointer">
					<input type="radio" name="waterMeter0" id='meterY' value='true'>
					<label class="radio__label" for="meterY">Yes</label>
				</div>
				<div class="radio radio--inline cursor-pointer">
					<input type="radio" name="waterMeter0" id='meterN' value='false'
						checked> <label class="radio__label" for="meterN">No</label>
				</div>
			</div>
		</div>

		<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
			<button type="button"
				class="btn btn-sm light-green darken-3 text-white pt-1 pb-1"
				onclick="addMoreWaterSource();">
				<i class="zmdi zmdi-plus zmdi-hc-fw"></i>Add
			</button>
		</div>	
		
	
	</div>
	<div class="appendExtraWaterSource"></div>
	
	<!--<div class="row ">
	    <div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">-->
		<div id="appendcgwc" ></div>
		<!--</div>
		</div>-->
	
</div>

<!-- question House Canteen -->
<div class="row mt-4">
	<div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">
		<div class="form-group">
			<label class="font-weight-bold">2. Do You Have in House
				Canteen?</label></br>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="isHouseCanteen" id='canteenY' value='true'
					onclick="showCookingForm('Yes')"> <label
					class="radio__label" for="canteenY">Yes</label>
			</div>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="isHouseCanteen" id='canteenN'
					value='false' onclick="showCookingForm('No')" checked> <label
					class="radio__label" for="canteenN">No</label>
			</div>
		</div>
	</div>

	<div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5"
		id="appendCookingFacilityBlock"></div>
</div>

<!-- question House Canteen -->
<div class="row mt-4">
	<div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">
		<div class="form-group">
			<label class="font-weight-bold">3. Do You Have prefilter ?
			</label></br>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="isPre" id='preY' value='true'
					onclick="preFilterForm('Yes')"> <label
					class="radio__label" for="preY">Yes</label>
			</div>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="isPre" id='preN'
					value='false' onclick="preFilterForm('No')" checked> <label
					class="radio__label" for="preN">No</label>
			</div>
		</div>
	</div>

	<div class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7"
		id="preFilterFormBlock"></div>
</div>





<!-- question water treatment  -->
<div class="row mt-4">
	<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">
		<div class="form-group">
			<label class="font-weight-bold">4.Do You Have Water Treatment
				Plant?</label></br>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="isWaterTreatment" id='waterTreatmentY'
					value='true'> <label class="radio__label"
					for="waterTreatmentY">Yes</label>
			</div>
			<div class="radio radio--inline cursor-pointer">
				<input type="radio" name="isWaterTreatment" id='waterTreatmentN'
					value='false' checked> <label class="radio__label"
					for="waterTreatmentN">No</label>
			</div>
		</div>
	</div>
</div>

<!-- this blocked moved to water inventory II  by Jemish-->

<!-- question Where To Use Source Water  -->
<!-- <div class="row mt-4">
	<div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">
		<div class="form-group mb-2">
		   <label class="font-weight-bold">4.Select Where To Use Source Water(Direct use of water)</label></br>
		</div>
	</div>
	
	<div class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11 offset-1">
		<div class="row mt-2">
			<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
				<div class="checkbox">
					<input type="checkbox" name="directUseWater[]" value="Domestic" id="useWaterSourceDomestic" onclick="getUseWaterSource(this,'Domestic','');">
					<label class="checkbox__label" for="useWaterSourceDomestic">Domestic</label>
				</div>
			</div>
			<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceDomestic"> </div>
		</div>
		
		<div class="row mt-2">
			<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
				<div class="checkbox">
					<input type="checkbox" name="directUseWater[]" value="Industrial" id="useWaterSourceIndustrial" onclick="getUseWaterSourceIndustrial(this,'');">
					<label class="checkbox__label" for="useWaterSourceIndustrial">Industrial</label>
				</div>
			</div>
			<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceIndustrial"> </div>
		</div>
		
		<div class="row mt-2">
			<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
				<div class="checkbox">
					<input type="checkbox" name="directUseWater[]" value="Laundry" id="useWaterSourceLaundry" onclick="getUseWaterSource(this,'Laundry','');">
					<label class="checkbox__label" for="useWaterSourceLaundry">Laundry</label>
				</div>
			</div>
			<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceLaundry"> </div>
		</div>
		
		<div class="row mt-2">
			<div class="col-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
				<div class="checkbox">
					<input type="checkbox" name="directUseWater[]" value="Fire Hydrant" id="useWaterSourceFireHydrant" onclick="getUseWaterSource(this,'Fire Hydrant','');">
					<label class="checkbox__label" for="useWaterSourceFireHydrant">Fire Hydrant</label>
				</div>
			</div>
			<div class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10" id="appendUseWaterSourceFireHydrant"> </div>
		</div>
		
	</div>
</div> -->




						<!-- onclick="wasteWaterTreatment('Yes')" onclick="wasteWaterTreatment('No')" -->


<div class="mt-4">
	<div class="row">
		<div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">
			<div class="form-group mb-3">
				<label class="font-weight-bold">5. Do you have Wastewater
					Treatment Plant?</label></br>
				<div class="radio radio--inline cursor-pointer">
					<input type="radio" name="wasteTreatmentPlantQuestion"
						id='treatmentPlantY' value='true'
						> <label
						class="radio__label" for="treatmentPlantY">Yes</label>
				</div>
				<div class="radio radio--inline cursor-pointer">
					<input type="radio" name="wasteTreatmentPlantQuestion"
						id='treatmentPlantN' value='false' checked> <label
						class="radio__label" for="treatmentPlantN">No</label>
				</div>
			</div>
		</div>
	</div>
	<div class="row" id="appendWastewaterTreatment"></div>
</div>


<div class="row mt-4 mb-4">
	<div class="col text-center">
		<button type="button" class="btn btn-primary btn-sm"
			onclick="saveWaterInventory(this,'dataFile');" id="save-waterInventory-btn">
			<i class='zmdi zmdi-save'></i> Save
		</button>
	</div>
</div>