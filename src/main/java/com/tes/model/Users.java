package com.tes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int usersId;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "designation")
	private String designation;

	@Column(name = "enabled")
	private boolean enabled;

	private int failedLoginCount;

	private Boolean locked;

	@Column(name = "last_login_date", length = 23)
	private Date lastLoginDate;

	@ManyToOne
	@JoinColumn(name = "company_profile_id")
	private CompanyProfile companyProfile;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authorities_id"))
	private Set<Authorities> authorities = new HashSet<Authorities>();

	public Users(String username, String password)
	{
		this.userName = username;
		this.password = password;
	}

	public int getUsersId()
	{
		return usersId;
	}

	public void setUsersId(int usersId)
	{
		this.usersId = usersId;
	}

	public Set<Authorities> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities)
	{
		this.authorities = authorities;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public CompanyProfile getCompanyProfile()
	{
		return companyProfile;
	}

	public void setCompanyProfile(CompanyProfile companyProfile)
	{
		this.companyProfile = companyProfile;
	}

	public int getFailedLoginCount()
	{
		return failedLoginCount;
	}

	public void setFailedLoginCount(int failedLoginCount)
	{
		this.failedLoginCount = failedLoginCount;
	}

	public Boolean getLocked()
	{
		return locked;
	}

	public void setLocked(Boolean locked)
	{
		this.locked = locked;
	}

	public Date getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	public Users(int usersId, String userName, String designation, CompanyProfile companyProfile)
	{
		super();
		this.usersId = usersId;
		this.userName = userName;
		this.designation = designation;
		this.companyProfile = companyProfile;
	}

	public Users()
	{
		super();
	}

}
