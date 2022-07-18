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
@Table(name = "direct_use_of_water")
public class DirectUseOfWater
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	@Setter(AccessLevel.NONE)
	private int directUseOfWaterId;

	@ManyToOne
	@JoinColumn(name = "ws_id")
	private WaterSource waterSource;

	@Column(name = "type_of_use")
	private String typeOfUse;

	@Column(name = "where_to_use")
	private String whereToUse;

	@Column(name = "is_meter")
	private boolean isMeter;

	@Column(name = "water_loss")
	private float waterLoss;

	@Column(name = "is_industrial")
	private boolean isIndustrial;

	@Transient
	List<RegDirectUseOfWaterData> regDirectUseOfWaterData = new ArrayList<>();

	public DirectUseOfWater(int directUseOfWaterId, String whereToUse, boolean isIndustrial)
	{
		super();
		this.directUseOfWaterId = directUseOfWaterId;
		this.whereToUse = whereToUse;
		this.isIndustrial = isIndustrial;
	}

	public DirectUseOfWater()
	{
		super();
	}

	public DirectUseOfWater(String typeOfUse, String whereToUse, boolean isMeter)
	{
		super();
		this.typeOfUse = typeOfUse;
		this.whereToUse = whereToUse;
		this.isMeter = isMeter;
	}

	public DirectUseOfWater(String whereToUse, boolean isIndustrial)
	{
		super();
		this.whereToUse = whereToUse;
		this.isIndustrial = isIndustrial;
	}
}
