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
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class WastewaterRecycle
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int wastewaterRecycleId;

	@ManyToOne
	@JoinColumn(name = "wwt_id")
	private WastewaterTreatment wastewaterTreatment;

	@Column(name = "use_type")
	private String useType;

	@Column(name = "recycled_to")
	private String recycledTo;

	@Column(name = "quantity")
	private Float quantity;

	@Column(name = "is_meter")
	private boolean isMeter;

	@Column(name = "treatment_label")
	private String treatmentLabel;

	@Transient
	private List<RegWastewaterRecycle> RegWastewaterRecycleData = new ArrayList<>();

	/*
	 * private String treatmentLabel;
	 */
	public WastewaterRecycle()
	{
		super();
	}

	public WastewaterRecycle(int wastewaterRecycleId, String recycledTo)
	{
		super();
		this.wastewaterRecycleId = wastewaterRecycleId;
		this.recycledTo = recycledTo;
	}

}
