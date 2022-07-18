package com.tes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "water_con_gen_parameter")
public class WaterConGenParameter
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int waterConGenParameterId;

	@Column(name = "parameter_name")
	private String parameterName;

	@Column(name = "parameter_unit")
	private String parameterUnit;

	@OneToMany(mappedBy = "waterConGenParameter", fetch = FetchType.EAGER)
	private List<WaterConGen> waterConGen = new ArrayList<>();

	public int getWaterConGenParameterId()
	{
		return waterConGenParameterId;
	}

	public void setWaterConGenParameterId(int waterConGenParameterId)
	{
		this.waterConGenParameterId = waterConGenParameterId;
	}

	public String getParameterName()
	{
		return parameterName;
	}

	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}

	public String getParameterUnit()
	{
		return parameterUnit;
	}

	public void setParameterUnit(String parameterUnit)
	{
		this.parameterUnit = parameterUnit;
	}

	public List<WaterConGen> getWaterConGen()
	{
		return waterConGen;
	}

	public void setWaterConGen(List<WaterConGen> waterConGen)
	{
		this.waterConGen = waterConGen;
	}

}
