package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "water_con_gen_comparative_sheet")
public class WaterConGenComparativeSheet
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int WaterConGenComparativeSheetId;

	@ManyToOne
	@JoinColumn(name = "water_con_gen_id")
	private WaterConGen waterConGen;

	@Column(name = "cons")
	private float cons;

	@Column(name = "cons_bal")
	private float consBal;

	@Column(name = "gen")
	private float gen;

	@Column(name = "gen_bal")
	private float genBal;

	@Column(name = "status")
	private byte status;

	public int getWaterConGenComparativeSheetId()
	{
		return WaterConGenComparativeSheetId;
	}

	public void setWaterConGenComparativeSheetId(int waterConGenComparativeSheetId)
	{
		WaterConGenComparativeSheetId = waterConGenComparativeSheetId;
	}

	public WaterConGen getWaterConGen()
	{
		return waterConGen;
	}

	public void setWaterConGen(WaterConGen waterConGen)
	{
		this.waterConGen = waterConGen;
	}

	public float getCons()
	{
		return cons;
	}

	public void setCons(float cons)
	{
		this.cons = cons;
	}

	public float getConsBal()
	{
		return consBal;
	}

	public void setConsBal(float consBal)
	{
		this.consBal = consBal;
	}

	public float getGen()
	{
		return gen;
	}

	public void setGen(float gen)
	{
		this.gen = gen;
	}

	public float getGenBal()
	{
		return genBal;
	}

	public void setGenBal(float genBal)
	{
		this.genBal = genBal;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

}
