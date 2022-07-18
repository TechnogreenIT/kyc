package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consent_amulgamation")
public class ConsentAmulgamation
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int consentAmulgamationId;

	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;

	@Column(name = "amulgamation_id")
	private int amulgamationId;

	public int getConsentAmulgamationId()
	{
		return consentAmulgamationId;
	}

	public void setConsentAmulgamationId(int consentAmulgamationId)
	{
		this.consentAmulgamationId = consentAmulgamationId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public int getAmulgamationId()
	{
		return amulgamationId;
	}

	public void setAmulgamationId(int amulgamationId)
	{
		this.amulgamationId = amulgamationId;
	}
}
