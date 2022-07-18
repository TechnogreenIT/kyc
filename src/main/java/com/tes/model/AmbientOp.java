package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ambient_op")
public class AmbientOp
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int ambientOpId;

	@ManyToOne
	@JoinColumn(name = "cons_id")
	private Consent consent;

	@ManyToOne
	@JoinColumn(name = "ambient_id")
	private Ambient ambient;

	public int getAmbientOpId()
	{
		return ambientOpId;
	}

	public void setAmbientOpId(int ambientOpId)
	{
		this.ambientOpId = ambientOpId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public Ambient getAmbient()
	{
		return ambient;
	}

	public void setAmbient(Ambient ambient)
	{
		this.ambient = ambient;
	}

}
