package com.tes.model;

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

@Entity
@Data
@Table(name = "wastewater_treatment")
public class WastewaterTreatment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int wastewaterTreatmentId;

	@ManyToOne
	@JoinColumn(name = "wi_id")
	private WaterInventory waterInventory;

	@Column(name = "treatment_type")
	private String treatmentType;

	@Column(name = "label")
	private String label;

	@Column(name = "capacity")
	private int capacity;

	@Transient
	private List<RegularTreatmentData> regularTreatmentData = new ArrayList<>();

	@Transient
	private List<WastewaterRecycle> WasteWaterRecycleData = new ArrayList<>();

	public WastewaterTreatment()
	{
		super();
	}

	public WastewaterTreatment(String treatmentType, String label, WaterInventory waterInventory)
	{
		super();
		this.waterInventory = waterInventory;
		this.treatmentType = treatmentType;
		this.label = label;
	}

	@Override
	public String toString()
	{
		return "WastewaterTreatment [wastewaterTreatmentId=" + wastewaterTreatmentId + ", waterInventory="
				+ waterInventory + ", treatmentType=" + treatmentType + ", label=" + label + ", capacity=" + capacity
				+ ", regularTreatmentData=" + regularTreatmentData + ", WasteWaterRecycleData=" + WasteWaterRecycleData
				+ "]";
	}

	public WastewaterTreatment(int wastewaterTreatmentId, String label)
	{
		super();
		this.wastewaterTreatmentId = wastewaterTreatmentId;
		this.label = label;
	}

}
