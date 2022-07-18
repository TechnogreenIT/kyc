package com.tes.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "regular_treatment_data")
public class RegularTreatmentData
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int regularTreatmentDataId;

	@ManyToOne
	@JoinColumn(name = "wwt_id")
	private WastewaterTreatment wastewaterTreatment;

	@Column(name = "start_reading")
	private Float startReading;

	@Column(name = "end_reading")
	private Float endReading;

	@Column(name = "actual_reading")
	private Float actualReading;

	@Column(name = "energy_start_reading")
	private Float energyStartReading;

	@Column(name = "energy_end_reading")
	private Float energyEndReading;

	@Column(name = "energy_avg")
	private Float energyAvg;

	@CreationTimestamp
	@Column(name = "create_date_time")
	private Timestamp createDateTime;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	public RegularTreatmentData(Float actualReading, Float energyAvg)
	{
		super();
		this.actualReading = actualReading;
		this.energyAvg = energyAvg;
	}

	public RegularTreatmentData()
	{
	}

}
