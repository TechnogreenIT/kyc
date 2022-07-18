package com.tes.model;

import java.sql.Timestamp;
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
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Data
@Entity
@Table(name = "multiple_filter")
public class MultipleFilter
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int multipleFilterId;

	@ManyToOne
	@JoinColumn(name = "filter_id")
	private Filters filters;

	@Column(name = "filter_label")
	private String filterLabel;

	@Column(name = "is_meter")
	private boolean isMeter;

	@Column(name = "water_loss")
	private float waterLoss;

	@Column(name = "is_active")
	private boolean isActive = true;

	@CreationTimestamp
	private Timestamp creationTimestamp;

	@Transient
	private List<RegMultipleFilterData> regMultipleFilterData = new ArrayList<>();

	public MultipleFilter(int multipleFilterId, String filterLabel)
	{
		super();
		this.multipleFilterId = multipleFilterId;
		this.filterLabel = filterLabel;
	}

	public MultipleFilter()
	{
	}
}
