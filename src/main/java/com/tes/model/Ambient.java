package com.tes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.poi.ss.usermodel.Row;

@Entity
@Table(name = "ambient")
public class Ambient
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int ambientId;

	@ManyToOne
	@JoinColumn(name = "cons_id")
	private Consent consent;

	@Column(name = "ambient_loc_name")
	private String ambientLocName;

	@Column(name = "criteria")
	private String criteria;

	@Column(name = "consent_to_operate")
	private String consentToOperate;

	@Transient
	private List<AmbientPoll> ambientPollDetailList = new ArrayList<>();

	public List<AmbientPoll> getAmbientPollDetailList()
	{
		return ambientPollDetailList;
	}

	public void setAmbientPollDetailList(List<AmbientPoll> ambientPollDetailList)
	{
		this.ambientPollDetailList = ambientPollDetailList;
	}

	public int getAmbientId()
	{
		return ambientId;
	}

	public void setAmbientId(int ambientId)
	{
		this.ambientId = ambientId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public String getAmbientLocName()
	{
		return ambientLocName;
	}

	public void setAmbientLocName(String ambientLocName)
	{
		this.ambientLocName = ambientLocName;
	}

	public String getCriteria()
	{
		return criteria;
	}

	public void setCriteria(String criteria)
	{
		this.criteria = criteria;
	}

	public String getConsentToOperate()
	{
		return consentToOperate;
	}

	public void setConsentToOperate(String consentToOperate)
	{
		this.consentToOperate = consentToOperate;
	}

	public Ambient()
	{
		super();
	}

	public Ambient(int ambientId)
	{
		super();
		this.ambientId = ambientId;
	}

	public Ambient(int ambientId, Consent consent, String ambientLocName, String criteria)
	{
		super();
		this.ambientId = ambientId;
		this.consent = consent;
		this.ambientLocName = ambientLocName;
		this.criteria = criteria;
	}

	public void setAmbientDetails(Row row, int consentId)
	{
		Consent objConsentId = new Consent();
		objConsentId.setConsentId(consentId);
		consent = objConsentId;
		ambientLocName = row.getCell(1).toString();
		criteria = row.getCell(2).toString();
		// consentToOperate = row.getCell(3).toString();
	}

	public Ambient(int ambientId, String ambientLocName, String criteria)
	{
		super();
		this.ambientId = ambientId;
		this.ambientLocName = ambientLocName;
		this.criteria = criteria;
	}

}
