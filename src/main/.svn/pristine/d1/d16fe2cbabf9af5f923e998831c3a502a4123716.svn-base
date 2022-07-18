package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reg_apc")
public class RegAPC
{
	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int regAPCId;

	@ManyToOne
	@JoinColumn(name = "stack_id")
	private Stack stack;

	@Column(name = "start_reading")
	private Float startReading;

	@Column(name = "end_reading")
	private Float endReading;

	@Column(name = "actual_reading")
	private Float actualReading;

	@Column(name = "apc_date")
	private String apcDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	public int getRegAPCId()
	{
		return regAPCId;
	}

	public void setRegAPCId(int regAPCId)
	{
		this.regAPCId = regAPCId;
	}

	public Stack getStack()
	{
		return stack;
	}

	public void setStack(Stack stack)
	{
		this.stack = stack;
	}

	public Float getStartReading()
	{
		return startReading;
	}

	public void setStartReading(Float startReading)
	{
		this.startReading = startReading;
	}

	public Float getEndReading()
	{
		return endReading;
	}

	public void setEndReading(Float endReading)
	{
		this.endReading = endReading;
	}

	public Float getActualReading()
	{
		return actualReading;
	}

	public void setActualReading(Float actualReading)
	{
		this.actualReading = actualReading;
	}

	public String getApcDate()
	{
		return apcDate;
	}

	public void setApcDate(String apcDate)
	{
		this.apcDate = apcDate;
	}

	public Users getUsers()
	{
		return users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

}
