package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stack_poll_data")
public class StackPollData
{

	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int stackPollId;

	@ManyToOne
	@JoinColumn(name = "reg_st_poll_id")
	private RegStPoll regStPoll;

	@Column(name = "poll_name")
	private String pollName;

	@Column(name = "conc_poll")
	private Float concPoll;

	@Column(name = "poll_unit")
	private String pollUnit;

	public StackPollData()
	{
	}

	public int getStackPollId()
	{
		return stackPollId;
	}

	public void setStackPollId(int stackPollId)
	{
		this.stackPollId = stackPollId;
	}

	public RegStPoll getRegStPoll()
	{
		return regStPoll;
	}

	public void setRegStPoll(RegStPoll regStPoll)
	{
		this.regStPoll = regStPoll;
	}

	public String getPollName()
	{
		return pollName;
	}

	public void setPollName(String pollName)
	{
		this.pollName = pollName;
	}

	public Float getConcPoll()
	{
		return concPoll;
	}

	public void setConcPoll(Float concPoll)
	{
		this.concPoll = concPoll;
	}

	public String getPollUnit()
	{
		return pollUnit;
	}

	public void setPollUnit(String pollUnit)
	{
		this.pollUnit = pollUnit;
	}

}
