package com.tes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "env_clearance")
public class EnvEC implements Serializable{
	
	private static final long serialVersionUID = 6765793073874421114L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int ecId;
	
	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;
	
	

	
	public Consent getConsent() {
		return consent;
	}

	public void setConsent(Consent consent) {
		this.consent = consent;
	}



	@Column(name = "ec_no")
	private String ecNo;
	
	@Column(name = "ecvalid_date")
	private String ecvalid_date;
	
	@Column(name = "ec_file_path")
	private String ecFilePath;
	
	@Column(name = "ec_file_name")
	private String ecFileName;
	
	@Column(name = "eia_notification")
	private String eia_Notification;
	
	@Column(name = "protect_area_wildlife")
	private String protect_Area_Wildlife;
	
	@Column(name = "criticalpoll_area_identify")
	private String criticalPoll_Area_Identify;
	
	@Column(name = "ecosensitive_area")
	private String ecosensitive_Area;

	public int getEcId() {
		return ecId;
	}

	public void setEcId(int ecId) {
		this.ecId = ecId;
	}

	public String getEcNo() {
		return ecNo;
	}

	public void setEcNo(String ecNo) {
		this.ecNo = ecNo;
	}

	public String getEcvalid_date() {
		return ecvalid_date;
	}

	public void setEcvalid_date(String ecvalid_date) {
		this.ecvalid_date = ecvalid_date;
	}

	public String getEcFilePath() {
		return ecFilePath;
	}

	public void setEcFilePath(String ecFilePath) {
		this.ecFilePath = ecFilePath;
	}

	public String getEcFileName() {
		return ecFileName;
	}

	public void setEcFileName(String ecFileName) {
		this.ecFileName = ecFileName;
	}

	public String getEia_Notification() {
		return eia_Notification;
	}

	public void setEia_Notification(String eia_Notification) {
		this.eia_Notification = eia_Notification;
	}

	public String getProtect_Area_Wildlife() {
		return protect_Area_Wildlife;
	}

	public void setProtect_Area_Wildlife(String protect_Area_Wildlife) {
		this.protect_Area_Wildlife = protect_Area_Wildlife;
	}

	public String getCriticalPoll_Area_Identify() {
		return criticalPoll_Area_Identify;
	}

	public void setCriticalPoll_Area_Identify(String criticalPoll_Area_Identify) {
		this.criticalPoll_Area_Identify = criticalPoll_Area_Identify;
	}

	public String getEcosensitive_Area() {
		return ecosensitive_Area;
	}

	public void setEcosensitive_Area(String ecosensitive_Area) {
		this.ecosensitive_Area = ecosensitive_Area;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public EnvEC(int ecId, String ecNo, String ecvalid_date, String ecFilePath, String ecFileName,
//			String eia_Notification, String protect_Area_Wildlife, String criticalPoll_Area_Identify,
//			String ecosensitive_Area) {
//		super();
//		this.ecId = ecId;
//	    this.consent = consent;
//		this.ecNo = ecNo;
//		this.ecvalid_date = ecvalid_date;
//		this.ecFilePath = ecFilePath;
//		this.ecFileName = ecFileName;
//		this.eia_Notification = eia_Notification;
//		this.protect_Area_Wildlife = protect_Area_Wildlife;
//		this.criticalPoll_Area_Identify = criticalPoll_Area_Identify;
//		this.ecosensitive_Area = ecosensitive_Area;
//	}

	public EnvEC(int ecId, Consent consent, String ecNo, String ecvalid_date, String ecFilePath, String ecFileName,
			String eia_Notification, String protect_Area_Wildlife, String criticalPoll_Area_Identify,
			String ecosensitive_Area) {
		super();
		this.ecId = ecId;
		this.consent = consent;
		this.ecNo = ecNo;
		this.ecvalid_date = ecvalid_date;
		this.ecFilePath = ecFilePath;
		this.ecFileName = ecFileName;
		this.eia_Notification = eia_Notification;
		this.protect_Area_Wildlife = protect_Area_Wildlife;
		this.criticalPoll_Area_Identify = criticalPoll_Area_Identify;
		this.ecosensitive_Area = ecosensitive_Area;
	}


	public EnvEC() {
		// TODO Auto-generated constructor stub
	}

	

	public void setConsent(String consNo) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
}