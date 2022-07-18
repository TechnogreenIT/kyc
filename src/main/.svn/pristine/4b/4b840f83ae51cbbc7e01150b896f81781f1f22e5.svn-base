package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esr_particulars")
public class EsrParticulars
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int EsrParticularsId;

	@Column(name = "particulars")
	private String particulars;

	@Column(name = "year")
	private String year;

	public int getEsrParticularsId()
	{
		return EsrParticularsId;
	}

	public void setEsrParticularsId(int esrParticularsId)
	{
		EsrParticularsId = esrParticularsId;
	}

	public String getParticulars()
	{
		return particulars;
	}

	public void setParticulars(String particulars)
	{
		this.particulars = particulars;
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
		return "EsrParticulars [EsrParticularsId=" + EsrParticularsId + ", particulars=" + particulars + ", year="
				+ year + "]";
	}

}
