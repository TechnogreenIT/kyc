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

@Entity
@Table(name = "reg_amb_poll")
public class RegAmbientPoll
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int regAmbientPollId;

	@ManyToOne()
	@JoinColumn(name = "ambient_id")
	private Ambient ambient;

	@Column(name = "loc_name")
	private String locName;

	@Column(name = "criteria")
	private String criteria;

	@Column(name = "samp_amb")
	private String sampAmb;

	@Column(name = "sub_amb")
	private String subAmb;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "reason")
	private String reason;

	@Transient
	private List<AmbientPollData> ambientPollData = new ArrayList<>();

	public int getRegAmbientPollId()
	{
		return regAmbientPollId;
	}

	public void setRegAmbientPollId(int regAmbientPollId)
	{
		this.regAmbientPollId = regAmbientPollId;
	}

	public Ambient getAmbient()
	{
		return ambient;
	}

	public void setAmbient(Ambient ambient)
	{
		this.ambient = ambient;
	}

	public String getLocName()
	{
		return locName;
	}

	public void setLocName(String locName)
	{
		this.locName = locName;
	}

	public String getCriteria()
	{
		return criteria;
	}

	public void setCriteria(String criteria)
	{
		this.criteria = criteria;
	}

	public String getSampAmb()
	{
		return sampAmb;
	}

	public void setSampAmb(String sampAmb)
	{
		this.sampAmb = sampAmb;
	}

	public String getSubAmb()
	{
		return subAmb;
	}

	public void setSubAmb(String subAmb)
	{
		this.subAmb = subAmb;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public List<AmbientPollData> getAmbientPollData()
	{
		return ambientPollData;
	}

	public void setAmbientPollData(List<AmbientPollData> ambientPollData)
	{
		this.ambientPollData = ambientPollData;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
}
