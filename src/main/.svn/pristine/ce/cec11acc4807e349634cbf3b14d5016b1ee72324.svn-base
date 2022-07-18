package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "containers")
public class Containers
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int containersId;

	@ManyToOne
	@JoinColumn(name = "hm_id")
	private HazardousManifest hazardousManifest;

	@Column(name = "containers_number")
	private String containersNumber;

	@Column(name = "containers_type")
	private String containersType;

	public int getContainersId()
	{
		return containersId;
	}

	public void setContainersId(int containersId)
	{
		this.containersId = containersId;
	}

	public HazardousManifest getHazardousManifest()
	{
		return hazardousManifest;
	}

	public void setHazardousManifest(HazardousManifest hazardousManifest)
	{
		this.hazardousManifest = hazardousManifest;
	}

	public String getContainersNumber()
	{
		return containersNumber;
	}

	public void setContainersNumber(String containersNumber)
	{
		this.containersNumber = containersNumber;
	}

	public String getContainersType()
	{
		return containersType;
	}

	public void setContainersType(String containersType)
	{
		this.containersType = containersType;
	}

	@Override
	public String toString()
	{
		return "Containers [containersId=" + containersId + ", hazardousManifest=" + hazardousManifest
				+ ", containersNumber=" + containersNumber + ", containersType=" + containersType + "]";
	}

}
