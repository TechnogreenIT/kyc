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
@Table(name = "filter_use")
public class FilterUse
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int filterUseId;

	@ManyToOne
	@JoinColumn(name = "multiple_filter_id")
	private MultipleFilter multipleFilter;

	@ManyToOne
	@JoinColumn(name = "prefilter_id")
	private Prefilter prefilter;

	@Column(name = "filter_use_type")
	private String filterUseType;

	@Column(name = "filter_use_label")
	private String filterUseLabel;

	@Column(name = "is_industrial")
	private boolean isIndustrial;

	@Column(name = "is_meter")
	private boolean isMeter;

	@Column(name = "water_loss")
	private float waterLoss;

	@Column(name = "is_filter")
	private boolean isFilter;

	@Column(name = "is_active")
	private boolean isActive = true;

	@CreationTimestamp
	private Timestamp creationTimestamp;

	@Transient
	private List<RegFiltersUseData> regFiltersUseData = new ArrayList<>();

	public FilterUse(String filterUseType, String filterUseLabel, boolean isMeter, boolean isFilter, boolean isActive)
	{
		super();
		this.filterUseType = filterUseType;
		this.filterUseLabel = filterUseLabel;
		this.isMeter = isMeter;
		this.isFilter = isFilter;
		this.isActive = isActive;
	}

	public FilterUse(String filterUseType, String filterUseLabel)
	{
		super();
		this.filterUseType = filterUseType;
		this.filterUseLabel = filterUseLabel;
	}

	public FilterUse()
	{
		super();
	}

}
