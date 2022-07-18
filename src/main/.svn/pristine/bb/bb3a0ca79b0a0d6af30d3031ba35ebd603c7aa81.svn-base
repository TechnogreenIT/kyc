package com.tes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.poi.ss.usermodel.Row;

@Entity
@Table(name = "stack")
public class Stack
{

	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int stackId;

	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;

	@Column(name = "stack_name")
	private String stackName;

	@Column(name = "attached_to")
	private String attachedTo;

	@Column(name = "capacity")
	private float capacity;

	@Column(name = "capacity_units")
	private String capacityUnits;

	@Column(name = "fuel_type")
	private String fuelType;

	@Column(name = "fuel_quant")
	private float fuelQuant;

	@Column(name = "fuel_units")
	private String fuelUnits;

	@Column(name = "shape")
	private String shape;

	@Column(name = "height")
	private float height;

	@Column(name = "ht_units")
	private String htUnits;

	@Column(name = "diam")
	private float diam;

	@Column(name = "diam_units")
	private String diamUnits;

	@Column(name = "mat_cons")
	private String matCons;

	@Column(name = "apc")
	private String apc;

	@Column(name = "apc_system")
	private String apcSystem;

	@Column(name = "consent_to_operate")
	private String consentToOperate;

	@OneToMany(mappedBy = "stack")
	private List<StackOp> stackOp = new ArrayList<>();

	@Transient
	private List<StackPoll> stackPollDetailList = new ArrayList<>();

	@Transient
	private List<RegAPC> regAPCList = new ArrayList<>();

	public List<RegAPC> getRegAPCList()
	{
		return regAPCList;
	}

	public void setRegAPCList(List<RegAPC> regAPCList)
	{
		this.regAPCList = regAPCList;
	}

	public List<StackPoll> getStackPollDetailList()
	{
		return stackPollDetailList;
	}

	public void setStackPollDetailList(List<StackPoll> stackPollDetailList)
	{
		this.stackPollDetailList = stackPollDetailList;
	}

	public int getStackId()
	{
		return stackId;
	}

	public void setStackId(int stackId)
	{
		this.stackId = stackId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
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

	public float getCapacity()
	{
		return capacity;
	}

	public void setCapacity(float capacity)
	{
		this.capacity = capacity;
	}

	public String getCapacityUnits()
	{
		return capacityUnits;
	}

	public void setCapacityUnits(String capacityUnits)
	{
		this.capacityUnits = capacityUnits;
	}

	public String getFuelType()
	{
		return fuelType;
	}

	public void setFuelType(String fuelType)
	{
		this.fuelType = fuelType;
	}

	public float getFuelQuant()
	{
		return fuelQuant;
	}

	public void setFuelQuant(float fuelQuant)
	{
		this.fuelQuant = fuelQuant;
	}

	public String getFuelUnits()
	{
		return fuelUnits;
	}

	public void setFuelUnits(String fuelUnits)
	{
		this.fuelUnits = fuelUnits;
	}

	public String getShape()
	{
		return shape;
	}

	public void setShape(String shape)
	{
		this.shape = shape;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public String getHtUnits()
	{
		return htUnits;
	}

	public void setHtUnits(String htUnits)
	{
		this.htUnits = htUnits;
	}

	public float getDiam()
	{
		return diam;
	}

	public void setDiam(float diam)
	{
		this.diam = diam;
	}

	public String getDiamUnits()
	{
		return diamUnits;
	}

	public void setDiamUnits(String diamUnits)
	{
		this.diamUnits = diamUnits;
	}

	public String getMatCons()
	{
		return matCons;
	}

	public void setMatCons(String matCons)
	{
		this.matCons = matCons;
	}

	public String getApc()
	{
		return apc;
	}

	public void setApc(String apc)
	{
		this.apc = apc;
	}

	public String getApcSystem()
	{
		return apcSystem;
	}

	public void setApcSystem(String apcSystem)
	{
		this.apcSystem = apcSystem;
	}

	public String getConsentToOperate()
	{
		return consentToOperate;
	}

	public void setConsentToOperate(String consentToOperate)
	{
		this.consentToOperate = consentToOperate;
	}

	public List<StackOp> getStackOp()
	{
		return stackOp;
	}

	public void setStackOp(List<StackOp> stackOp)
	{
		this.stackOp = stackOp;
	}

	public Stack()
	{
	}

	public Stack(Consent consent)
	{
		super();
		this.consent = consent;
	}

	public Stack(int stackId, String stackName, String attachedTo, String matCons, float height, String htUnits, String shape, String fuelType, float diam, String diamUnits)
	{
		super();
		this.stackId = stackId;
		this.stackName = stackName;
		this.attachedTo = attachedTo;
		this.matCons = matCons;
		this.height = height;
		this.htUnits = htUnits;
		this.shape = shape;
		this.fuelType = fuelType;
		this.diam = diam;
		this.diamUnits = diamUnits;
	}

	public Stack(int stackId, String apc, String apcSystem, String stackName)
	{
		super();
		this.stackId = stackId;
		this.apc = apc;
		this.apcSystem = apcSystem;
		this.stackName = stackName;
	}

	public Stack(int stackId, Consent consent, String stackName, String attachedTo, float capacity,
			String capacityUnits, String fuelType, float fuelQuant, String fuelUnits, String shape, float height,
			String htUnits, float diam, String diamUnits, String matCons, String apc, String apcSystem,
			String consentToOperate)
	{
		super();
		Consent cn = new Consent();
		cn.setConsentId(consent.getConsentId());
		this.stackId = stackId;
		this.consent = cn;
		this.stackName = stackName;
		this.attachedTo = attachedTo;
		this.capacity = capacity;
		this.capacityUnits = capacityUnits;
		this.fuelType = fuelType;
		this.fuelQuant = fuelQuant;
		this.fuelUnits = fuelUnits;
		this.shape = shape;
		this.height = height;
		this.htUnits = htUnits;
		this.diam = diam;
		this.diamUnits = diamUnits;
		this.matCons = matCons;
		this.apc = apc;
		this.apcSystem = apcSystem;
		this.consentToOperate = consentToOperate;
	}

	public void setStackDetails(Row row, int consentId)
	{
		Consent objConsentId = new Consent();
		objConsentId.setConsentId(consentId);
		consent = objConsentId;
		stackName = row.getCell(1).toString();
		attachedTo = row.getCell(2).toString();
		capacity = Float.parseFloat(row.getCell(3).toString());
		capacityUnits = row.getCell(4).toString();
		fuelType = row.getCell(5).toString();
		fuelQuant = Float.parseFloat(row.getCell(6).toString());
		fuelUnits = row.getCell(7).toString();
		shape = row.getCell(8).toString();
		height = Float.parseFloat(row.getCell(9).toString());
		htUnits = row.getCell(10).toString();
		diam = Float.parseFloat(row.getCell(11).toString());
		diamUnits = row.getCell(12).toString();
		matCons = row.getCell(13).toString();
		apc = row.getCell(14).toString();
		apcSystem = row.getCell(15).toString();
		// consentToOperate = row.getCell(16).toString();
	}

	public Stack(int stackId, String stackName, String attachedTo)
	{
		super();
		this.stackId = stackId;
		this.stackName = stackName;
		this.attachedTo = attachedTo;
	}

}
