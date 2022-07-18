package com.tes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "water_source")
public class WaterSource
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	// @Setter(AccessLevel.NONE)
	private int waterSourceId;

	@ManyToOne
	@JoinColumn(name = "wi_id")
	private WaterInventory waterInventory;

	@Column(name = "source_name")
	private String sourceName;

	@Column(name = "is_source_meter")
	private boolean isSourceMeter;

	@Transient
	private List<RegWaterSourceData> regularSourceDataList = new ArrayList<>();

	@Transient
	private List<Prefilter> prefilterList = new ArrayList<>();
	/*
	 * public WaterSource(int waterSourceId) { super(); this.waterSourceId =
	 * waterSourceId; }
	 * public WaterSource() { // TODO Auto-generated constructor stub }
	 */

}
