package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ambient_poll_data")
public class AmbientPollData
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int ambientPollId;

	@ManyToOne
	@JoinColumn(name = "reg_amb_poll_id")
	private RegAmbientPoll regAmbientPoll;

	@Column(name = "poll_name")
	private String pollName;

	@Column(name = "conc_poll")
	private String concPoll;

	@Column(name = "poll_units")
	private String units;

	public int getAmbientPollId()
	{
		return ambientPollId;
	}

	public void setAmbientPollId(int ambientPollId)
	{
		this.ambientPollId = ambientPollId;
	}

	public RegAmbientPoll getRegAmbientPoll()
	{
		return regAmbientPoll;
	}

	public void setRegAmbientPoll(RegAmbientPoll regAmbientPoll)
	{
		this.regAmbientPoll = regAmbientPoll;
	}

	public String getPollName()
	{
		return pollName;
	}

	public void setPollName(String pollName)
	{
		this.pollName = pollName;
	}

	public String getConcPoll()
	{
		return concPoll;
	}

	public void setConcPoll(String concPoll)
	{
		this.concPoll = concPoll;
	}

	public String getUnits()
	{
		return units;
	}

	public void setUnits(String units)
	{
		this.units = units;
	}

	public AmbientPollData()
	{
		super();
	}

}
