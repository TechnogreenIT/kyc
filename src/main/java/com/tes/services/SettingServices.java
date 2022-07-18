package com.tes.services;

import com.tes.model.Settings;

public interface SettingServices
{
	public String findIntroductoryStatus(int usersId);

	public Settings findSettingData(int usersId);

	public void updateIntroStatus(String status, int userId);

	public void updateBkImageAndColor(int image, String color, int userId);

	public Settings save(Settings settings);

}
