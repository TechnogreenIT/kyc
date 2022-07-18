package com.tes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Settings;
import com.tes.repository.SettingRepository;
import com.tes.services.SettingServices;

@Service
public class SettingServicesImpl implements SettingServices
{

	@Autowired
	SettingRepository settingRepository;

	@Override
	public String findIntroductoryStatus(int usersId)
	{
		return settingRepository.findIntroductoryStatus(usersId);
	}

	@Override
	public Settings findSettingData(int usersId)
	{
		return settingRepository.findSettingData(usersId);
	}

	@Override
	public void updateBkImageAndColor(int image, String color, int userId)
	{

		settingRepository.updateBkImageAndColor(image, color, userId);
	}

	@Override
	public Settings save(Settings settings)
	{
		return settingRepository.save(settings);
	}

	@Override
	public void updateIntroStatus(String status, int userId)
	{
		settingRepository.updateIntroStatus(status, userId);
	}

}
