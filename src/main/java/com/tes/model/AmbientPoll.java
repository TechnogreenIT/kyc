package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.poi.ss.usermodel.Row;

@Entity
@Table(name = "ambient_poll")
public class AmbientPoll
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int ambientPollId;

	@ManyToOne
	@JoinColumn(name = "ambient_id")
	private Ambient ambient;

	@Column(name = "poll_name")
	private String pollName;

	@Column(name = "limits")
	private float limits;

	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	public int getAmbientPollId()
	{
		return ambientPollId;
	}

	public void setAmbientPollId(int ambientPollId)
	{
		this.ambientPollId = ambientPollId;
	}

	public Ambient getAmbient()
	{
		return ambient;
	}

	public void setAmbient(Ambient ambient)
	{
		this.ambient = ambient;
	}

	public String getPollName()
	{
		return pollName;
	}

	public void setPollName(String pollName)
	{
		this.pollName = pollName;
	}

	public float getLimits()
	{
		return limits;
	}

	public void setLimits(float limits)
	{
		this.limits = limits;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public AmbientPoll()
	{
		super();
	}

	public AmbientPoll(Ambient ambient, String pollName, Unit unit)
	{
		super();
		this.ambient = ambient;
		this.pollName = pollName;
		this.unit = unit;
	}

	public AmbientPoll(Ambient ambient, String pollName, float limits, Unit unit)
	{
		super();// i thing this is not used constructor
		// this.ambientPollId = ambientPollId;
		this.ambient = ambient;
		this.pollName = pollName;
		this.limits = limits;
		this.unit = unit;
	}

	public void setAmbientPollDetails(Row row, int ambientId, int getUnitId)
	{
		Ambient objAmbientId = new Ambient();
		Unit objUnitId = new Unit();
		objAmbientId.setAmbientId(ambientId);
		objUnitId.setUnitId(getUnitId);
		ambient = objAmbientId;
		pollName = row.getCell(3).toString();
		limits = Float.parseFloat(row.getCell(4).toString());
		unit = objUnitId;

	}

}
