package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "industry_category")
public class IndustryCategory
{
	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int industryCategoryId;

	@Column(name = "category")
	private String category;

	public int getIndustryCategoryId()
	{
		return industryCategoryId;
	}

	public void setIndustryCategoryId(int industryCategoryId)
	{
		this.industryCategoryId = industryCategoryId;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

}
