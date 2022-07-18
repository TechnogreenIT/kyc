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
@Table(name = "stack_poll")
public class StackPoll
{

	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int stackPollId;

	@ManyToOne
	@JoinColumn(name = "stack_id")
	private Stack stack;

	@Column(name = "poll_name")
	private String pollName;

	@Column(name = "poll_limit")
	private float pollLimit;

	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	public int getStackPollId()
	{
		return stackPollId;
	}

	public void setStackPollId(int stackPollId)
	{
		this.stackPollId = stackPollId;
	}

	public Stack getStack()
	{
		return stack;
	}

	public void setStack(Stack stack)
	{
		this.stack = stack;
	}

	public String getPollName()
	{
		return pollName;
	}

	public void setPollName(String pollName)
	{
		this.pollName = pollName;
	}

	public float getPollLimit()
	{
		return pollLimit;
	}

	public void setPollLimit(float pollLimit)
	{
		this.pollLimit = pollLimit;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public StackPoll()
	{
		super();
	}

	public StackPoll(int stackPollId, Stack stack, String pollName, float pollLimit, Unit unit)
	{
		super();
		this.stackPollId = stackPollId;
		this.stack = stack;
		this.pollName = pollName;
		this.pollLimit = pollLimit;
		this.unit = unit;
	}

	public void setStackPollDetails(Row row, int stackId, int getUnitId)
	{
		Stack objStackId = new Stack();
		Unit objUnitId = new Unit();
		objStackId.setStackId(stackId);
		objUnitId.setUnitId(getUnitId);
		stack = objStackId;
		pollName = row.getCell(3).toString();
		pollLimit = Float.parseFloat(row.getCell(4).toString());
		unit = objUnitId;
	}

	public StackPoll(Stack stack, String pollName, float pollLimit, Unit unit)
	{
		super();
		this.stack = stack;
		this.pollName = pollName;
		this.pollLimit = pollLimit;
		this.unit = unit;
	}

}
