package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "water_marks")
public class WaterMarks
{
	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int waterMarksId;

	@Column(name = "items")
	private String items;

	@Column(name = "marks")
	private Float marks;

	@Column(name = "type")
	private String type;

	@Column(name = "main_type")
	private String mainType;

	public int getWaterMarksId()
	{
		return waterMarksId;
	}

	public void setWaterMarksId(int waterMarksId)
	{
		this.waterMarksId = waterMarksId;
	}

	public String getItems()
	{
		return items;
	}

	public void setItems(String items)
	{
		this.items = items;
	}

	public Float getMarks()
	{
		return marks;
	}

	public void setMarks(Float marks)
	{
		this.marks = marks;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getMainType()
	{
		return mainType;
	}

	public void setMainType(String mainType)
	{
		this.mainType = mainType;
	}

}
