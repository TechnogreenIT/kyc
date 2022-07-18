package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "esr_hw_solid_waste")
public class EsrHwSolidWaste
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int esrHwSolidWasteId;

	@Column(name = "type_of_waste")
	private String typeOfWaste;

	@Column(name = "quantity")
	private float quantity;

	@JoinColumn(name = "unit")
	private String unit;

	@Column(name = "concentration")
	private float concentration;

	@Column(name = "type")
	private String type;

	@Column(name = "year")
	private String year;

	public int getEsrHwSolidWasteId()
	{
		return esrHwSolidWasteId;
	}

	public void setEsrHwSolidWasteId(int esrHwSolidWasteId)
	{
		this.esrHwSolidWasteId = esrHwSolidWasteId;
	}

	public String getTypeOfWaste()
	{
		return typeOfWaste;
	}

	public void setTypeOfWaste(String typeOfWaste)
	{
		this.typeOfWaste = typeOfWaste;
	}

	public float getQuantity()
	{
		return quantity;
	}

	public void setQuantity(float quantity)
	{
		this.quantity = quantity;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public float getConcentration()
	{
		return concentration;
	}

	public void setConcentration(float concentration)
	{
		this.concentration = concentration;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
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
		return "EsrHwSolidWaste [esrHwSolidWasteId=" + esrHwSolidWasteId + ", typeOfWaste=" + typeOfWaste
				+ ", quantity=" + quantity + ", unit=" + unit + ", concentration=" + concentration + ", type=" + type
				+ ", year=" + year + "]";
	}

}
