package com.tes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "hazardous_manifest")
public class HazardousManifest
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int hazardousManifestId;

	@Column(name = "senders_name")
	public String sendersName;

	@Column(name = "senders_mailing_address")
	private String sendersMailingAddress;

	@Column(name = "senders_phone_no")
	private String sendersPhoneNo;

	@Column(name = "senders_authorization_no")
	private String sendersAuthorizationNo;

	@Column(name = "manifest_documents_no")
	private String manifestDocumentsNo;

	@Column(name = "transporter_name")
	private String transporterName;

	@Column(name = "transporter_address")
	private String transporterAddress;

	@Column(name = "transportermobilep_no")
	private String transportermobilepNo;

	@Column(name = "vehicle_type")
	private String vehicleType;

	@Column(name = "transporter_reg_no")
	private String transporterRegNo;

	@Column(name = "transporter_vehicle_reg_no")
	private String transporterVehicleRegNo;

	@Column(name = "receivers_name")
	private String receiversName;

	@Column(name = "receivers_address")
	private String receiversAddress;

	@Column(name = "receivers_authorization_no")
	private String receiversAuthorizationNo;

	@Column(name = "receivers_phone_no")
	private String receiversPhoneNo;

	@Column(name = "transport_desc_waste")
	private String transportDescWaste;

	@Column(name = "tot_quantity_container")
	private String totQuantityContainer;

	@Column(name = "tot_Quantity_container_unit")
	private String totQuantityContainerUnit;

	@Column(name = "special_handling")
	private String specialHandling;

	@Column(name = "submitted_date") // its datatype needs to be confirmed--sushma
	private String submittedDate;

	@Column(name = "dispatched_to")
	private String dispatchedTo;

	@Transient
	private List<WasteDescriptionConsistency> wasteDescriptionListByConsistency = new ArrayList<>();

	@Transient
	private List<WasteDescriptionConsistency> wasteDescriptionListByDescWaste = new ArrayList<>();

	@Transient
	private List<Containers> containersList = new ArrayList<>();

	public String getSendersMailingAddress()
	{
		return sendersMailingAddress;
	}

	public void setSendersMailingAddress(String sendersMailingAddress)
	{
		this.sendersMailingAddress = sendersMailingAddress;
	}

	public String getSendersName()
	{
		return sendersName;
	}

	public void setSendersName(String sendersName)
	{
		this.sendersName = sendersName;
	}

	public String getSendersAuthorizationNo()
	{
		return sendersAuthorizationNo;
	}

	public void setSendersAuthorizationNo(String sendersAuthorizationNo)
	{
		this.sendersAuthorizationNo = sendersAuthorizationNo;
	}

	public String getReceiversName()
	{
		return receiversName;
	}

	public void setReceiversName(String receiversName)
	{
		this.receiversName = receiversName;
	}

	public String getReceiversAuthorizationNo()
	{
		return receiversAuthorizationNo;
	}

	public void setReceiversAuthorizationNo(String receiversAuthorizationNo)
	{
		this.receiversAuthorizationNo = receiversAuthorizationNo;
	}

	public List<WasteDescriptionConsistency> getWasteDescriptionListByDescWaste()
	{
		return wasteDescriptionListByDescWaste;
	}

	public void setWasteDescriptionListByDescWaste(List<WasteDescriptionConsistency> wasteDescriptionListByDescWaste)
	{
		this.wasteDescriptionListByDescWaste = wasteDescriptionListByDescWaste;
	}

	public List<Containers> getContainersList()
	{
		return containersList;
	}

	public void setContainersList(List<Containers> containersList)
	{
		this.containersList = containersList;
	}

	public List<WasteDescriptionConsistency> getWasteDescriptionListByConsistency()
	{
		return wasteDescriptionListByConsistency;
	}

	public void setWasteDescriptionListByConsistency(
			List<WasteDescriptionConsistency> wasteDescriptionListByConsistency)
	{
		this.wasteDescriptionListByConsistency = wasteDescriptionListByConsistency;
	}

	public int getHazardousManifestId()
	{
		return hazardousManifestId;
	}

	public void setHazardousManifestId(int hazardousManifestId)
	{
		this.hazardousManifestId = hazardousManifestId;
	}

	public String getManifestDocumentsNo()
	{
		return manifestDocumentsNo;
	}

	public void setManifestDocumentsNo(String manifestDocumentsNo)
	{
		this.manifestDocumentsNo = manifestDocumentsNo;
	}

	public String getTransporterName()
	{
		return transporterName;
	}

	public void setTransporterName(String transporterName)
	{
		this.transporterName = transporterName;
	}

	public String getTransporterAddress()
	{
		return transporterAddress;
	}

	public void setTransporterAddress(String transporterAddress)
	{
		this.transporterAddress = transporterAddress;
	}

	public String getVehicleType()
	{
		return vehicleType;
	}

	public void setVehicleType(String vehicleType)
	{
		this.vehicleType = vehicleType;
	}

	public String getTransporterRegNo()
	{
		return transporterRegNo;
	}

	public void setTransporterRegNo(String transporterRegNo)
	{
		this.transporterRegNo = transporterRegNo;
	}

	public String getTransporterVehicleRegNo()
	{
		return transporterVehicleRegNo;
	}

	public void setTransporterVehicleRegNo(String transporterVehicleRegNo)
	{
		this.transporterVehicleRegNo = transporterVehicleRegNo;
	}

	public String getReceiversAddress()
	{
		return receiversAddress;
	}

	public void setReceiversAddress(String receiversAddress)
	{
		this.receiversAddress = receiversAddress;
	}

	public String getTransportDescWaste()
	{
		return transportDescWaste;
	}

	public void setTransportDescWaste(String transportDescWaste)
	{
		this.transportDescWaste = transportDescWaste;
	}

	public String getTotQuantityContainer()
	{
		return totQuantityContainer;
	}

	public void setTotQuantityContainer(String totQuantityContainer)
	{
		this.totQuantityContainer = totQuantityContainer;
	}

	public String getTotQuantityContainerUnit()
	{
		return totQuantityContainerUnit;
	}

	public void setTotQuantityContainerUnit(String totQuantityContainerUnit)
	{
		this.totQuantityContainerUnit = totQuantityContainerUnit;
	}

	public String getSpecialHandling()
	{
		return specialHandling;
	}

	public void setSpecialHandling(String specialHandling)
	{
		this.specialHandling = specialHandling;
	}

	public String getSubmittedDate()
	{
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate)
	{
		this.submittedDate = submittedDate;
	}

	public String getSendersPhoneNo()
	{
		return sendersPhoneNo;
	}

	public void setSendersPhoneNo(String sendersPhoneNo)
	{
		this.sendersPhoneNo = sendersPhoneNo;
	}

	public String getTransportermobilepNo()
	{
		return transportermobilepNo;
	}

	public void setTransportermobilepNo(String transportermobilepNo)
	{
		this.transportermobilepNo = transportermobilepNo;
	}

	public String getReceiversPhoneNo()
	{
		return receiversPhoneNo;
	}

	public void setReceiversPhoneNo(String receiversPhoneNo)
	{
		this.receiversPhoneNo = receiversPhoneNo;
	}

	public String getDispatchedTo()
	{
		return dispatchedTo;
	}

	public void setDispatchedTo(String dispatchedTo)
	{
		this.dispatchedTo = dispatchedTo;
	}

	public HazardousManifest(String receiversName, String dispatchedTo)
	{
		super();
		this.receiversName = receiversName;
		this.dispatchedTo = dispatchedTo;
	}

	public HazardousManifest()
	{
		super();
	}

}
