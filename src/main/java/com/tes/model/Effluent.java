package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "effluent")
public class Effluent
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int effluentId;

	@Column(name = "eff_name")
	String effName;

	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;

	public int getEffluentId()
	{
		return effluentId;
	}

	public void setEffluentId(int effluentId)
	{
		this.effluentId = effluentId;
	}

	public String getEffName()
	{
		return effName;
	}

	public void setEffName(String effName)
	{
		this.effName = effName;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

}
