package com.tes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "water_con_gen")
public class WaterConGen
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int waterConGenId;

	@ManyToOne
	@JoinColumn(name = "cons_id")
	private Consent consent;

	@ManyToOne
	@JoinColumn(name = "water_con_gen_parameter_id")
	private WaterConGenParameter waterConGenParameter;

	@Column(name = "cons")
	private float cons;

	@Column(name = "gen")
	private float gen;

	@OneToMany(mappedBy = "waterConGen", fetch = FetchType.EAGER)
	private List<WaterConGenComparativeSheet> waterConGenComparativeSheet = new ArrayList<>();

	public int getWaterConGenId()
	{
		return waterConGenId;
	}

	public void setWaterConGenId(int waterConGenId)
	{
		this.waterConGenId = waterConGenId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public WaterConGenParameter getWaterConGenParameter()
	{
		return waterConGenParameter;
	}

	public void setWaterConGenParameter(WaterConGenParameter waterConGenParameter)
	{
		this.waterConGenParameter = waterConGenParameter;
	}

	public float getCons()
	{
		return cons;
	}

	public void setCons(float cons)
	{
		this.cons = cons;
	}

	public float getGen()
	{
		return gen;
	}

	public void setGen(float gen)
	{
		this.gen = gen;
	}

	public List<WaterConGenComparativeSheet> getWaterConGenComparativeSheet()
	{
		return waterConGenComparativeSheet;
	}

	public void setWaterConGenComparativeSheet(List<WaterConGenComparativeSheet> waterConGenComparativeSheet)
	{
		this.waterConGenComparativeSheet = waterConGenComparativeSheet;
	}

	public WaterConGen()
	{
	}
}
