package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "water_sew_poll_op")
public class WaterSewPollOp
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int waterSewPollOpId;

	@ManyToOne
	@JoinColumn(name = "cons_no")
	private Consent consent;

	@ManyToOne
	@JoinColumn(name = "water_sew_poll_id")
	private WaterSewPoll waterSewPoll;

	public int getWaterSewPollOpId()
	{
		return waterSewPollOpId;
	}

	public void setWaterSewPollOpId(int waterSewPollOpId)
	{
		this.waterSewPollOpId = waterSewPollOpId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public WaterSewPoll getWaterSewPoll()
	{
		return waterSewPoll;
	}

	public void setWaterSewPoll(WaterSewPoll waterSewPoll)
	{
		this.waterSewPoll = waterSewPoll;
	}
}
