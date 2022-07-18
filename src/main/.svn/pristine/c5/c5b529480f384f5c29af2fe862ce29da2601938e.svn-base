package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esr_investment")
public class EsrInvestment
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int EsrInvestmentId;

	@Column(name = "detail")
	private String details;

	@Column(name = "protection_mea")
	private String protectionMea;

	@Column(name = "capital_investment")
	private Float capitalInvestment;

	@Column(name = "year")
	private String year;

	@Column(name = "investment_year")
	private String investmentYear;

	public int getEsrInvestmentId()
	{
		return EsrInvestmentId;
	}

	public void setEsrInvestmentId(int esrInvestmentId)
	{
		EsrInvestmentId = esrInvestmentId;
	}

	public String getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	public String getProtectionMea()
	{
		return protectionMea;
	}

	public void setProtectionMea(String protectionMea)
	{
		this.protectionMea = protectionMea;
	}

	public Float getCapitalInvestment()
	{
		return capitalInvestment;
	}

	public void setCapitalInvestment(Float capitalInvestment)
	{
		this.capitalInvestment = capitalInvestment;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getInvestmentYear()
	{
		return investmentYear;
	}

	public void setInvestmentYear(String investmentYear)
	{
		this.investmentYear = investmentYear;
	}

	@Override
	public String toString()
	{
		return "EsrInvestment [EsrInvestmentId=" + EsrInvestmentId + ", details=" + details + ", protectionMea="
				+ protectionMea + ", capitalInvestment=" + capitalInvestment + ", year=" + year + ", investmentYear="
				+ investmentYear + "]";
	}

}
