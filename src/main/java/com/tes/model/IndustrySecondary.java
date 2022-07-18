package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "industry_secondary")
public class IndustrySecondary
{
	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int industrySecondaryId;

	@Column(name = "secondary_name")
	private String secondaryName;

	@Column(name = "type")
	private String type;

	public int getIndustrySecondaryId()
	{
		return industrySecondaryId;
	}

	public void setIndustrySecondaryId(int industrySecondaryId)
	{
		this.industrySecondaryId = industrySecondaryId;
	}

	public String getSecondaryName()
	{
		return secondaryName;
	}

	public void setSecondaryName(String secondaryName)
	{
		this.secondaryName = secondaryName;
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
