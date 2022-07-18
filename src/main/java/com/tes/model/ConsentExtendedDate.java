package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ConsentExtendedDate
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int consentExtendedDateId;

	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;

	@Column(name = "valid_upto")
	private String validUpto;

	@Column(name = "input_date")
	private String inputDate;

	public int getConsentExtendedDateId()
	{
		return consentExtendedDateId;
	}

	public void setConsentExtendedDateId(int consentExtendedDateId)
	{
		this.consentExtendedDateId = consentExtendedDateId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public String getValidUpto()
	{
		return validUpto;
	}

	public void setValidUpto(String validUpto)
	{
		this.validUpto = validUpto;
	}

	public String getInputDate()
	{
		return inputDate;
	}

	public void setInputDate(String inputDate)
	{
		this.inputDate = inputDate;
	}

	@Override
	public String toString()
	{
		return "ConsentExtendedDate [consentExtendedDateId=" + consentExtendedDateId + ", consent=" + consent
				+ ", validUpto=" + validUpto + ", inputDate=" + inputDate + "]";
	}

}
