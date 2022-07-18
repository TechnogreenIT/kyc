package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hazardous_wastes")
public class HazardousWastes
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int hazardousWastesId;

	@Column(name = "category_number")
	private String categoryNumber;

	@Column(name = "category_desc")
	private String categoryDesc;

	public int getHazardousWastesId()
	{
		return hazardousWastesId;
	}

	public void setHazardousWastesId(int hazardousWastesId)
	{
		this.hazardousWastesId = hazardousWastesId;
	}

	public String getCategoryNumber()
	{
		return categoryNumber;
	}

	public void setCategoryNumber(String categoryNumber)
	{
		this.categoryNumber = categoryNumber;
	}

	public String getCategoryDesc()
	{
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc)
	{
		this.categoryDesc = categoryDesc;
	}

}
