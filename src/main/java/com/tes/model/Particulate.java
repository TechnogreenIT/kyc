package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "particulate")
public class Particulate
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "pollutant")
	private String pollutant;

	@Column(name = "type")
	private String type;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getPollutant()
	{
		return pollutant;
	}

	public void setPollutant(String pollutant)
	{
		this.pollutant = pollutant;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
