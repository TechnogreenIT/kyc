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
@Table(name = "emp_data")
public class EmpData implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int empDataId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "address")
	private String address;

	@Column(name = "address2")
	private String address2;

	@Column(name = "address3")
	private String address3;

	@Column(name = "cont_per_desig")
	private String contPerDesig;

	@Column(name = "cont_per_no")
	private String contPerNo;

	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private CompanyProfile companyProfile;

	@Column(name = "email_status")
	private String emailStatus;

	@Column(name = "profile_pic")
	private String profilePic;

	public int getEmpDataId()
	{
		return empDataId;
	}

	public void setEmpDataId(int empDataId)
	{
		this.empDataId = empDataId;
	}

	public Users getUsers()
	{
		return users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

	public String getEmployeeName()
	{
		return employeeName;
	}

	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getAddress3()
	{
		return address3;
	}

	public void setAddress3(String address3)
	{
		this.address3 = address3;
	}

	public String getContPerDesig()
	{
		return contPerDesig;
	}

	public void setContPerDesig(String contPerDesig)
	{
		this.contPerDesig = contPerDesig;
	}

	public String getContPerNo()
	{
		return contPerNo;
	}

	public void setContPerNo(String contPerNo)
	{
		this.contPerNo = contPerNo;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public CompanyProfile getCompanyProfile()
	{
		return companyProfile;
	}

	public void setCompanyProfile(CompanyProfile companyProfile)
	{
		this.companyProfile = companyProfile;
	}

	public String getEmailStatus()
	{
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus)
	{
		this.emailStatus = emailStatus;
	}

	public String getProfilePic()
	{
		return profilePic;
	}

	public void setProfilePic(String profilePic)
	{
		this.profilePic = profilePic;
	}

	public EmpData(int empDataId, String employeeName, String gender, String birthday, String maritalStatus,
			String address, String address2, String address3, String contPerDesig, String contPerNo, String email,
			String profileStatus, String profilePic)
	{
		super();
		this.empDataId = empDataId;
		this.employeeName = employeeName;
		this.gender = gender;
		this.birthday = birthday;
		this.maritalStatus = maritalStatus;
		this.address = address;
		this.address2 = address2;
		this.address3 = address3;
		this.contPerDesig = contPerDesig;
		this.contPerNo = contPerNo;
		this.email = email;
		this.profilePic = profilePic;
	}

	public EmpData(int empDataId, Users users, String employeeName, CompanyProfile companyProfile, String profilePic, String contPerDesig)
	{
		super();
		CompanyProfile cmp = new CompanyProfile();
		Users us = new Users();
		us.setUsersId(users.getUsersId());
		cmp.setCompanyProfileId(companyProfile.getCompanyProfileId());
		cmp.setCompName(companyProfile.getCompName());
		cmp.setIndustryType(companyProfile.getIndustryType());
		cmp.setIndustryCategory(companyProfile.getIndustryCategory());
		cmp.setNoWorkDays(companyProfile.getNoWorkDays());
		this.empDataId = empDataId;
		this.users = us;
		this.employeeName = employeeName;
		this.companyProfile = cmp;
		this.profilePic = profilePic;
		this.contPerDesig = contPerDesig;
	}

	public EmpData()
	{
		super();
	}
}
