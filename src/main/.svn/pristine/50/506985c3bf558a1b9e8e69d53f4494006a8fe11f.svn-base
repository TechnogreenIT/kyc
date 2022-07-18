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

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "reg_water_source_data")
public class RegWaterSourceData
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	@Setter(AccessLevel.NONE)
	private int regWaterSourceDataId;

	@Column(name = "staff")
	private int staff;

	@ManyToOne
	@JoinColumn(name = "ws_id")
	private WaterSource waterSource;

	@Column(name = "start_reading")
	private float startReading;

	@Column(name = "end_reading")
	private float endReading;

	@Column(name = "actual_reading")
	private float actualReading;

	@CreationTimestamp
	@Column(name = "create_date_time")
	private Timestamp createDateTime;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

}
