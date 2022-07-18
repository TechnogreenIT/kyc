package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "water_sew_poll")
public class WaterSewPoll
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int waterSewPollId;

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

	public int getWaterSewPollId()
	{
		return waterSewPollId;
	}

	public void setWaterSewPollId(int waterSewPollId)
	{
		this.waterSewPollId = waterSewPollId;
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

	public WaterSewPoll()
	{
	}

	public WaterSewPoll(int waterSewPollId, Consent consent, String pollName, float quantity, Unit unit, String consentToOperate)
	{
		super();
		Consent cn = new Consent();
		cn.setConsentId(consent.getConsentId());
		this.waterSewPollId = waterSewPollId;
		this.consent = cn;
		this.pollName = pollName;
		this.quantity = quantity;
		this.unit = unit;
		this.consentToOperate = consentToOperate;
	}

	public WaterSewPoll(String pollName, float quantity, Unit unit)
	{
		super();
		this.pollName = pollName;
		this.quantity = quantity;
		this.unit = unit;
	};

	public WaterSewPoll(String pollName, float quantity)
	{
		super();
		this.pollName = pollName;
		this.quantity = quantity;
	};

}
