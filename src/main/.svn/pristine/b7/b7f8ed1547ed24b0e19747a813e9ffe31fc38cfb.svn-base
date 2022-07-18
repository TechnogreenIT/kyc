package com.tes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "waste_description_consistency")
public class WasteDescriptionConsistency implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int wasteDescriptionConsistencyId;

	@ManyToOne
	@JoinColumn(name = "hm_id")
	private HazardousManifest hazardousManifest;

	@Column(name = "consistency")
	private String consistency;

	@Column(name = "waste_name")
	private String wasteName;

	@Column(name = "description")
	private String description;

	@Column(name = "quantity_utilized")
	private float quantityUtilized;

	@Column(name = "waste_quantity")
	private float wasteQuantity;

	@Column(name = "waste_units")
	private String wasteUnits;

	@Column(name = "waste_category_number")
	private String wasteCategoryNumber;

	public String getWasteCategoryNumber()
	{
		return wasteCategoryNumber;
	}

	public void setWasteCategoryNumber(String wasteCategoryNumber)
	{
		this.wasteCategoryNumber = wasteCategoryNumber;
	}

	public int getWasteDescriptionConsistencyId()
	{
		return wasteDescriptionConsistencyId;
	}

	public void setWasteDescriptionConsistencyId(int wasteDescriptionConsistencyId)
	{
		this.wasteDescriptionConsistencyId = wasteDescriptionConsistencyId;
	}

	public HazardousManifest getHazardousManifest()
	{
		return hazardousManifest;
	}

	public void setHazardousManifest(HazardousManifest hazardousManifest)
	{
		this.hazardousManifest = hazardousManifest;
	}

	public String getConsistency()
	{
		return consistency;
	}

	public void setConsistency(String consistency)
	{
		this.consistency = consistency;
	}

	public String getWasteName()
	{
		return wasteName;
	}

	public void setWasteName(String wasteName)
	{
		this.wasteName = wasteName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public float getQuantityUtilized()
	{
		return quantityUtilized;
	}

	public void setQuantityUtilized(float quantityUtilized)
	{
		this.quantityUtilized = quantityUtilized;
	}

	public float getWasteQuantity()
	{
		return wasteQuantity;
	}

	public void setWasteQuantity(float wasteQuantity)
	{
		this.wasteQuantity = wasteQuantity;
	}

	public String getWasteUnits()
	{
		return wasteUnits;
	}

	public void setWasteUnits(String wasteUnits)
	{
		this.wasteUnits = wasteUnits;
	}

	public WasteDescriptionConsistency()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "WasteDescriptionConsistency [wasteDescriptionConsistencyId=" + wasteDescriptionConsistencyId
				+ ", hazardousManifest=" + hazardousManifest + ", consistency=" + consistency + ", wasteName="
				+ wasteName + ", description=" + description + ", wasteQuantity=" + wasteQuantity + ", wasteUnits="
				+ wasteUnits + ", wasteCategoryNumber=" + wasteCategoryNumber + "]";
	}

	public WasteDescriptionConsistency(String wasteName, String wasteCategoryNumber, String description)
	{
		super();
		this.wasteName = wasteName;
		this.wasteCategoryNumber = wasteCategoryNumber;
		this.description = description;
	}

}
