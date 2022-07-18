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
@Table(name = "waterie_pollutant")
public class WateriePollutant
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int wateriePollutantId;

	@ManyToOne
	@JoinColumn(name = "cons_id")
	private Consent consent;

	@Column(name = "poll_name")
	private String pollName;

	@Column(name = "quantity")
	private float quantity;

	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@Column(name = "consent_to_operate")
	private String consentToOperate;

	public int getWateriePollutantId()
	{
		return wateriePollutantId;
	}

	public void setWateriePollutantId(int wateriePollutantId)
	{
		this.wateriePollutantId = wateriePollutantId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public String getPollName()
	{
		return pollName;
	}

	public void setPollName(String pollName)
	{
		this.pollName = pollName;
	}

	public float getQuantity()
	{
		return quantity;
	}

	public void setQuantity(float quantity)
	{
		this.quantity = quantity;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public String getConsentToOperate()
	{
		return consentToOperate;
	}

	public void setConsentToOperate(String consentToOperate)
	{
		this.consentToOperate = consentToOperate;
	}

	public WateriePollutant(String pollName, float quantity, Unit unit)
	{
		super();
		this.pollName = pollName;
		this.quantity = quantity;
		this.unit = unit;
	}

	public WateriePollutant(int wateriePollutantId, Consent consent, String pollName, float quantity, Unit unit,
			String consentToOperate)
	{
		super();
		Consent cn = new Consent();
		cn.setConsentId(consent.getConsentId());
		this.wateriePollutantId = wateriePollutantId;
		this.consent = cn;
		this.pollName = pollName;
		this.quantity = quantity;
		this.unit = unit;
		this.consentToOperate = consentToOperate;
	}

	public WateriePollutant()
	{
	}

	public WateriePollutant(String pollName, float quantity, String consentToOperate)
	{
		super();
		this.pollName = pollName;
		this.quantity = quantity;
		this.consentToOperate = consentToOperate;
	}

	public WateriePollutant(String pollName, float quantity)
	{
		super();
		this.pollName = pollName;
		this.quantity = quantity;
	}

}
