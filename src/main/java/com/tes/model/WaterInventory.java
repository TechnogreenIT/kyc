package com.tes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "water_inventory")
public class WaterInventory implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int waterInventoryId;

	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;

	
	@Column(name = "is_house_canteen")
	private boolean isHouseCanteen;

	@Column(name = "is_cooking_canteen")
	private boolean isCookingCanteen;

	@Column(name = "is_water_treatment")
	private boolean isWaterTreatment;

	@Column(name = "is_wastewater_treatment")
	private boolean isWastewaterTreatment;

//	@Column(name = "is_cgwa_permission")
//	private String iscgwapermissiion;

	@Column(name = "is_cgwa_permission")
	private boolean iscgwapermissiion;
	
	
	@Column(name = "cgwa_file_path")
	private String cgwa_file_path;
	
	@Column(name = "cgwa_file_name")
	private String cgwa_file_name;
	
	@Transient
	private List<WaterSource> waterSourceList = new ArrayList<>();

	/*
	 * @Transient private List<Prefilter> prefilterList = new ArrayList<>();
	 */

	@Transient
	private List<RegWaterSourceData> regularSourceDataByDate = new ArrayList<>();

	@Transient
	private List<RegWaterSourceData> regularSourceDataBySrcName = new ArrayList<>();

	@Transient
	private List<RegWaterSourceData> regularSourceDataBySrcNameDate = new ArrayList<>();

	@Transient
	private List<Filters> filtersData = new ArrayList<>();

	@Transient
	private List<MultipleFilter> multipleFiltersData = new ArrayList<>();

	@Transient
	List<RegFiltersUseData> regularFiltersUseDataByFilterUseName = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataByDomestic = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataByDomesticDate = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataByIndName = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataIndNameDate = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataByLaundry = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataByLaundryDate = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataByFireHydrant = new ArrayList<>();

	@Transient
	private List<DirectUseOfWater> regularWaterUseDataByFireHydrantDate = new ArrayList<>();

	@Transient
	private List<WastewaterTreatment> treatmentTypeAndNameBywiId = new ArrayList<>();

	@Transient
	private List<WastewaterTreatment> wastewaterTreatmentList = new ArrayList<>();

	@Transient
	private List<RegularTreatmentData> regularTreatmentDataByTreatmentType = new ArrayList<>();

	@Transient
	private List<RegularTreatmentData> regularTreatmentDataByTreatmentTypeDate = new ArrayList<>();

	public WaterInventory()
	{
		super();
	}

	public WaterInventory(int waterInventoryId)
	{
		super();
		this.waterInventoryId = waterInventoryId;
	}

	public WaterInventory(boolean houseCanteen, boolean cookingCanteen)
	{
		super();
		this.isHouseCanteen = houseCanteen;
		this.isCookingCanteen = cookingCanteen;
	}

	public WaterInventory(boolean iscgwapermissiion, String cgwa_file_path, String cgwa_file_name) {
		super();
		this.iscgwapermissiion = iscgwapermissiion;
		this.cgwa_file_path = cgwa_file_path;
		this.cgwa_file_name = cgwa_file_name;
	}
}
