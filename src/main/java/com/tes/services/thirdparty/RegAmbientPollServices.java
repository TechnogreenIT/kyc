package com.tes.services.thirdparty;

import java.util.List;

import com.tes.model.RegAmbientPoll;

public interface RegAmbientPollServices
{

	public RegAmbientPoll save(RegAmbientPoll regambientpoll);

	public List<Integer> getYearFromAmbient();

	public List<String> getYearFromAmbientDate();

	public List<String> getMonthFromAmbient(int Yr);

	public List<Integer> getMonthFromAmbients(int Yr);

	public List<String> getDateForAmbient(int Yr);

	public List<RegAmbientPoll> findByAmbientId(int id, int regAmbientId);

	public List<Integer> getRegAmbientId(int ambientId);

	public List<RegAmbientPoll> findByAmbientDate(String date);

}
