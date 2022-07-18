package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reg_poll_reasons")
public class RegPollReasons
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int regPollReasonsId;

	@Column(name = "poll_name")
	private String pollName;

	@Column(name = "poll_type")
	private String pollType;

	@Column(name = "stack_name")
	private String stackName;

	@Column(name = "attached_to")
	private String attachedTo;

	@Column(name = "reason_date")
	private String reasonDate;

	@Column(name = "reason")
	private String reason;

	public int getRegPollReasonsId()
	{
		return regPollReasonsId;
	}

	public void setRegPollReasonsId(int regPollReasonsId)
	{
		this.regPollReasonsId = regPollReasonsId;
	}

	public String getPollName()
	{
		return pollName;
	}

	public void setPollName(String pollName)
	{
		this.pollName = pollName;
	}

	public String getPollType()
	{
		return pollType;
	}

	public void setPollType(String pollType)
	{
		this.pollType = pollType;
	}

	public String getStackName()
	{
		return stackName;
	}

	public void setStackName(String stackName)
	{
		this.stackName = stackName;
	}

	public String getAttachedTo()
	{
		return attachedTo;
	}

	public void setAttachedTo(String attachedTo)
	{
		this.attachedTo = attachedTo;
	}

	public String getReasonDate()
	{
		return reasonDate;
	}

	public void setReasonDate(String reasonDate)
	{
		this.reasonDate = reasonDate;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	@Override
	public String toString()
	{
		return "RegPollReasons [regPollReasonsId=" + regPollReasonsId + ", pollName=" + pollName + ", pollType="
				+ pollType + ", stackName=" + stackName + ", attachedTo=" + attachedTo + ", reasonDate=" + reasonDate
				+ ", reason=" + reason + "]";
	}

}
