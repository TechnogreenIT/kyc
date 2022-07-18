package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "industry_type_list")
public class IndustryTypeList
{
	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int industryTypeId;

	@Column(name = "type")
	private String type;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getindustryTypeId()
	{
		return industryTypeId;
	}

	public void setIndustryTypeId(int industryTypeId)
	{
		this.industryTypeId = industryTypeId;
	}

}
