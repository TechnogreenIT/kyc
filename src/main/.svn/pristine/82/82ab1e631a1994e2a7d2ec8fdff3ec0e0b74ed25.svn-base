package com.tes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "consent")
public class Consent implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6765793073874421114L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int consentId;

	@ManyToOne
	@JoinColumn(name = "company_profile_id")
	private CompanyProfile companyProfile;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	// @ManyToOne
	// private List<Users> users = new ArrayList<>();

	@Column(name = "cons_no")
	private String consNo;

	@Column(name = "cons_type")
	private String consType;

	@Column(name = "cons_status")
	private String consStatus;

	@Column(name = "expansion_status")
	private String expansionStatus;

	@Column(name = "issue_date")
	private String issueDate;

	@Column(name = "valid_upto")
	private String validUpto;

	@Column(name = "gross_ci")
	private float grossCi;

	@Column(name = "no_staff")
	private int noStaff;

	@Column(name = "no_worker")
	private int noWorker;

	@Column(name = "tot_plot_area")
	private float totPlotArea;

	@Column(name = "tot_plot_area_units")
	private String totPlotAreaUnits;

	@Column(name = "tot_build_area")
	private float totBuildArea;

	@Column(name = "tot_build_area_units")
	private String totBuildAreaUnits;

	@Column(name = "open_space_ava")
	private float openSpaceAva;

	@Column(name = "open_space_ava_units")
	private String openSpaceAvaUnits;

	@Column(name = "tot_green_area")
	private float totGreenArea;

	@Column(name = "tot_green_area_units")
	private String totGreenAreaUnits;

	@Column(name = "consent_file_path")
	private String consentFilePath;

	@Column(name = "consent_file_name")
	private String consentFileName;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdDate;

	@OneToMany(mappedBy = "consent")
	private List<ConsentExtendedDate> consentExtendedDate = new ArrayList<>();

	public Consent(int consentId)
	{
		super();
		this.consentId = consentId;
	}

	public int getConsentId()
	{
		return consentId;
	}

	public void setConsentId(int consentId)
	{
		this.consentId = consentId;
	}

	public CompanyProfile getCompanyProfile()
	{
		return companyProfile;
	}

	public void setCompanyProfile(CompanyProfile companyProfile)
	{
		this.companyProfile = companyProfile;
	}

	public Users getUsers()
	{
		return users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

	public String getConsNo()
	{
		return consNo;
	}

	public void setConsNo(String consNo)
	{
		this.consNo = consNo;
	}

	public String getConsType()
	{
		return consType;
	}

	public void setConsType(String consType)
	{
		this.consType = consType;
	}

	public String getConsStatus()
	{
		return consStatus;
	}

	public void setConsStatus(String consStatus)
	{
		this.consStatus = consStatus;
	}

	public String getExpansionStatus()
	{
		return expansionStatus;
	}

	public void setExpansionStatus(String expansionStatus)
	{
		this.expansionStatus = expansionStatus;
	}

	public String getIssueDate()
	{
		return issueDate;
	}

	public void setIssueDate(String issueDate)
	{
		this.issueDate = issueDate;
	}

	public String getValidUpto()
	{
		return validUpto;
	}

	public void setValidUpto(String validUpto)
	{
		this.validUpto = validUpto;
	}

	public float getGrossCi()
	{
		return grossCi;
	}

	public void setGrossCi(float grossCi)
	{
		this.grossCi = grossCi;
	}

	public int getNoStaff()
	{
		return noStaff;
	}

	public void setNoStaff(int noStaff)
	{
		this.noStaff = noStaff;
	}

	public int getNoWorker()
	{
		return noWorker;
	}

	public void setNoWorker(int noWorker)
	{
		this.noWorker = noWorker;
	}

	public float getTotPlotArea()
	{
		return totPlotArea;
	}

	public void setTotPlotArea(float totPlotArea)
	{
		this.totPlotArea = totPlotArea;
	}

	public String getTotPlotAreaUnits()
	{
		return totPlotAreaUnits;
	}

	public void setTotPlotAreaUnits(String totPlotAreaUnits)
	{
		this.totPlotAreaUnits = totPlotAreaUnits;
	}

	public float getTotBuildArea()
	{
		return totBuildArea;
	}

	public void setTotBuildArea(float totBuildArea)
	{
		this.totBuildArea = totBuildArea;
	}

	public String getTotBuildAreaUnits()
	{
		return totBuildAreaUnits;
	}

	public void setTotBuildAreaUnits(String totBuildAreaUnits)
	{
		this.totBuildAreaUnits = totBuildAreaUnits;
	}

	public float getOpenSpaceAva()
	{
		return openSpaceAva;
	}

	public void setOpenSpaceAva(float openSpaceAva)
	{
		this.openSpaceAva = openSpaceAva;
	}

	public String getOpenSpaceAvaUnits()
	{
		return openSpaceAvaUnits;
	}

	public void setOpenSpaceAvaUnits(String openSpaceAvaUnits)
	{
		this.openSpaceAvaUnits = openSpaceAvaUnits;
	}

	public float getTotGreenArea()
	{
		return totGreenArea;
	}

	public void setTotGreenArea(float totGreenArea)
	{
		this.totGreenArea = totGreenArea;
	}

	public String getTotGreenAreaUnits()
	{
		return totGreenAreaUnits;
	}

	public void setTotGreenAreaUnits(String totGreenAreaUnits)
	{
		this.totGreenAreaUnits = totGreenAreaUnits;
	}

	public String getConsentFilePath()
	{
		return consentFilePath;
	}

	public void setConsentFilePath(String consentFilePath)
	{
		this.consentFilePath = consentFilePath;
	}

	public String getConsentFileName()
	{
		return consentFileName;
	}

	public void setConsentFileName(String consentFileName)
	{
		this.consentFileName = consentFileName;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public List<ConsentExtendedDate> getConsentExtendedDate()
	{
		return consentExtendedDate;
	}

	public void setConsentExtendedDate(List<ConsentExtendedDate> consentExtendedDate)
	{
		this.consentExtendedDate = consentExtendedDate;
	}

	public Consent(String consNo, String consType, String issueDate, String validUpto, float grossCi, int noStaff,
			int noWorker, float totPlotArea, float totBuildArea, float totGreenArea, String consentFilePath,
			String consentFileName)
	{
		super();
		this.consNo = consNo;
		this.consType = consType;
		this.issueDate = issueDate;
		this.validUpto = validUpto;
		this.grossCi = grossCi;
		this.noStaff = noStaff;
		this.noWorker = noWorker;
		this.totPlotArea = totPlotArea;
		this.totBuildArea = totBuildArea;
		this.totGreenArea = totGreenArea;
		this.consentFilePath = consentFilePath;
		this.consentFileName = consentFileName;
	}

	public Consent()
	{
	}

	public Consent(String consNo, String issueDate)
	{
		super();
		this.consNo = consNo;
		this.issueDate = issueDate;
	}

	public Consent(int consentId, String consNo)
	{
		super();
		this.consentId = consentId;
		this.consNo = consNo;
	}

	public Consent(int consentId, String consNo, String validUpto, float grossCi, String issueDate)
	{
		super();
		this.consentId = consentId;
		this.consNo = consNo;
		this.validUpto = validUpto;
		this.grossCi = grossCi;
		this.issueDate = issueDate;

	}

	public Consent(String consNo, int consentId)
	{
		super();
		this.consentId = consentId;
		this.consNo = consNo;
	}

}
