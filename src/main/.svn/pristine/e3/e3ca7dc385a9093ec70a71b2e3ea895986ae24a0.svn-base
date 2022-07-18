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
@Table(name = "reg_multiple_filter_data")
public class RegMultipleFilterData
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	@Setter(AccessLevel.NONE)
	private int regMultipleFilterDataId;

	@ManyToOne
	@JoinColumn(name = "multiple_filter_id")
	private MultipleFilter multipleFilter;

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
