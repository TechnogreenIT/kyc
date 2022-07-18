package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esr_pollution_control")
public class EsrPollutionControl
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int esrPollutionControlId;

	@Column(name = "description")
	private String description;

	@Column(name = "reduction_water")
	private float reductionWater;

	@Column(name = "reduction_fuel")
	private float reductionFuel;

	@Column(name = "reduction_rm")
	private float reductionRm;

	@Column(name = "reduction_pc")
	private float reductionPc;

	@Column(name = "capital_investment")
	private float capitalInvestment;

	@Column(name = "reduction_maintenance")
	private float reductionMaintenance;

	@Column(name = "year")
	private String year;

	public int getEsrPollutionControlId()
	{
		return esrPollutionControlId;
	}

	public void setEsrPollutionControlId(int esrPollutionControlId)
	{
		this.esrPollutionControlId = esrPollutionControlId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public float getReductionWater()
	{
		return reductionWater;
	}

	public void setReductionWater(float reductionWater)
	{
		this.reductionWater = reductionWater;
	}

	public float getReductionFuel()
	{
		return reductionFuel;
	}

	public void setReductionFuel(float reductionFuel)
	{
		this.reductionFuel = reductionFuel;
	}

	public float getReductionRm()
	{
		return reductionRm;
	}

	public void setReductionRm(float reductionRm)
	{
		this.reductionRm = reductionRm;
	}

	public float getReductionPc()
	{
		return reductionPc;
	}

	public void setReductionPc(float reductionPc)
	{
		this.reductionPc = reductionPc;
	}

	public float getCapitalInvestment()
	{
		return capitalInvestment;
	}

	public void setCapitalInvestment(float capitalInvestment)
	{
		this.capitalInvestment = capitalInvestment;
	}

	public float getReductionMaintenance()
	{
		return reductionMaintenance;
	}

	public void setReductionMaintenance(float reductionMaintenance)
	{
		this.reductionMaintenance = reductionMaintenance;
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
		return "EsrPollutionControl [esrPollutionControlId=" + esrPollutionControlId + ", description=" + description
				+ ", reductionWater=" + reductionWater + ", reductionFuel=" + reductionFuel + ", reductionRm="
				+ reductionRm + ", reductionPc=" + reductionPc + ", capitalInvestment=" + capitalInvestment
				+ ", reductionMaintenance=" + reductionMaintenance + ", year=" + year + "]";
	}

}
