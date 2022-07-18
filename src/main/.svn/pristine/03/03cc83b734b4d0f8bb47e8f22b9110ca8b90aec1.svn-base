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
@Table(name = "reg_wastewater_recycle")
public class RegWastewaterRecycle
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int regWastewaterRecycleId;

	@ManyToOne
	@JoinColumn(name = "wwrecycle_id")
	private WastewaterRecycle wastewaterRecycle;

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
