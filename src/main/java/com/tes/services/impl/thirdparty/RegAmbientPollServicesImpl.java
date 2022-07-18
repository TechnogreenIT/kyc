package com.tes.services.impl.thirdparty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.RegAmbientPoll;
import com.tes.repository.thirdparty.RegAmbientPollRepository;
import com.tes.services.thirdparty.RegAmbientPollServices;

@Service
public class RegAmbientPollServicesImpl implements RegAmbientPollServices
{

	@Autowired
	RegAmbientPollRepository regAmbientPollRepository;

	@Override
	public RegAmbientPoll save(RegAmbientPoll regambientpoll)
	{
		return regAmbientPollRepository.save(regambientpoll);
	}

	@Override
	public List<Integer> getYearFromAmbient()
	{
		return regAmbientPollRepository.getYearFromAmbient();
	}

	@Override
	public List<String> getYearFromAmbientDate()
	{
		return regAmbientPollRepository.getYearFromAmbientDate();
	}

	@Override
	public List<String> getMonthFromAmbient(int Yr)
	{
		return regAmbientPollRepository.getMonthFromAmbient(Yr);
	}

	@Override
	public List<Integer> getMonthFromAmbients(int Yr)
	{
		return regAmbientPollRepository.getMonthFromAmbients(Yr);
	}

	@Override
	public List<String> getDateForAmbient(int Yr)
	{
		return regAmbientPollRepository.getDateForAmbient(Yr);
	}

	@Override
	public List<RegAmbientPoll> findByAmbientId(int id, int regAmbientId)
	{
		return regAmbientPollRepository.findByAmbientId(id, regAmbientId);
	}

	@Override
	public List<Integer> getRegAmbientId(int ambientId)
	{
		return regAmbientPollRepository.getRegAmbientId(ambientId);
	}

	@Override
	public List<RegAmbientPoll> findByAmbientDate(String date)
	{

		return regAmbientPollRepository.findByAmbientDate(date);
	}

}
