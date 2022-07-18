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
@Table(name = "reg_st_poll")
public class RegStPoll
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int regStPollId;

	@ManyToOne
	@JoinColumn(name = "stack_id")
	private Stack stack;

	@Transient
	private List<StackPollData> stackPollData = new ArrayList<>();

	@Column(name = "stack_name")
	private String stackName;

	@Column(name = "attache_to")
	private String attachTo;

	@Column(name = "gas_quant")
	private String gasQuant;

	@Column(name = "gas_temp")
	private String gasTemp;

	@Column(name = "pressure")
	private String pressure;

	@Column(name = "exit_gas_velocity")
	private Float exitGasVelocity;

	@Column(name = "hrs_op")
	private String hrsOp;

	@Column(name = "samp_st")
	private String sampSt;

	@Column(name = "sub_st")
	private String subSt;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "stack_file_path")
	private String stackFilePath;

	@Column(name = "reason")
	private String reason;

	public int getRegStPollId()
	{
		return regStPollId;
	}

	public List<StackPollData> getStackPollData()
	{
		return stackPollData;
	}

	public void setStackPollData(List<StackPollData> stackPollData)
	{
		this.stackPollData = stackPollData;
	}

	public void setRegStPollId(int regStPollId)
	{
		this.regStPollId = regStPollId;
	}

	public Stack getStack()
	{
		return stack;
	}

	public void setStack(Stack stack)
	{
		this.stack = stack;
	}

	public String getStackName()
	{
		return stackName;
	}

	public void setStackName(String stackName)
	{
		this.stackName = stackName;
	}

	public String getAttachTo()
	{
		return attachTo;
	}

	public void setAttachTo(String attachTo)
	{
		this.attachTo = attachTo;
	}

	public String getGasQuant()
	{
		return gasQuant;
	}

	public void setGasQuant(String gasQuant)
	{
		this.gasQuant = gasQuant;
	}

	public String getGasTemp()
	{
		return gasTemp;
	}

	public void setGasTemp(String gasTemp)
	{
		this.gasTemp = gasTemp;
	}

	public String getPressure()
	{
		return pressure;
	}

	public void setPressure(String pressure)
	{
		this.pressure = pressure;
	}

	public Float getExitGasVelocity()
	{
		return exitGasVelocity;
	}

	public void setExitGasVelocity(Float exitGasVelocity)
	{
		this.exitGasVelocity = exitGasVelocity;
	}

	public String getHrsOp()
	{
		return hrsOp;
	}

	public void setHrsOp(String hrsOp)
	{
		this.hrsOp = hrsOp;
	}

	public String getSampSt()
	{
		return sampSt;
	}

	public void setSampSt(String sampSt)
	{
		this.sampSt = sampSt;
	}

	public String getSubSt()
	{
		return subSt;
	}

	public void setSubSt(String subSt)
	{
		this.subSt = subSt;
	}

	public String getStackFilePath()
	{
		return stackFilePath;
	}

	public void setStackFilePath(String stackFilePath)
	{
		this.stackFilePath = stackFilePath;
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

	public RegStPoll()
	{
		super();
	}

	public RegStPoll(Float exitGasVelocity, String hrsOp)
	{
		super();
		this.exitGasVelocity = exitGasVelocity;
		this.hrsOp = hrsOp;
	}

}
