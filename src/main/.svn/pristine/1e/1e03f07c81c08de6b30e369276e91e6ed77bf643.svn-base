package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "industry_primary")
public class IndustryPrimary
{
	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int industryPrimaryId;

	@Column(name = "primary_name")
	private String primaryName;

	@Column(name = "type")
	private String type;

	public int getIndustryPrimaryId()
	{
		return industryPrimaryId;
	}

	public void setIndustryPrimaryId(int industryPrimaryId)
	{
		this.industryPrimaryId = industryPrimaryId;
	}

	public String getPrimaryName()
	{
		return primaryName;
	}

	public void setPrimaryName(String primaryName)
	{
		this.primaryName = primaryName;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
