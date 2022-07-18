package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "settings")
public class Settings
{

	@Id
	@Column(name = "id")
	private int settingId;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Column(name = "Introductory_video_status")
	private String introductoryVideoStatus;

	@Column(name = "background_image")
	private int backgroundImage;

	@Column(name = "text_color")
	private String textColor;

	public int getSettingId()
	{
		return settingId;
	}

	public void setSettingId(int settingId)
	{
		this.settingId = settingId;
	}

	public Users getUsers()
	{
		return users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

	public String getIntroductoryVideoStatus()
	{
		return introductoryVideoStatus;
	}

	public void setIntroductoryVideoStatus(String introductoryVideoStatus)
	{
		this.introductoryVideoStatus = introductoryVideoStatus;
	}

	public int getBackgroundImage()
	{
		return backgroundImage;
	}

	public void setBackgroundImage(int backgroundImage)
	{
		this.backgroundImage = backgroundImage;
	}

	public String getTextColor()
	{
		return textColor;
	}

	public void setTextColor(String textColor)
	{
		this.textColor = textColor;
	}

}
