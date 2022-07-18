package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esr_product_water")
public class EsrProductWater
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int esrProductWaterId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "previous_data")
	private String previousData;

	@Column(name = "current_data")
	private String currentData;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "units")
	private String unit;

	@Column(name = "year")
	private String year;

	public int getEsrProductWaterId()
	{
		return esrProductWaterId;
	}

	public void setEsrProductWaterId(int esrProductWaterId)
	{
		this.esrProductWaterId = esrProductWaterId;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getPreviousData()
	{
		return previousData;
	}

	public void setPreviousData(String previousData)
	{
		this.previousData = previousData;
	}

	public String getCurrentData()
	{
		return currentData;
	}

	public void setCurrentData(String currentData)
	{
		this.currentData = currentData;
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	@Override
	public String toString()
	{
		return "EsrProductWater [esrProductWaterId=" + esrProductWaterId + ", productName=" + productName
				+ ", previousData=" + previousData + ", currentData=" + currentData + ", productType=" + productType
				+ ", unit=" + unit + ", year=" + year + "]";
	}

}
