package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.Containers;
import com.tes.repository.environmentalofficer.ContainersRepository;
import com.tes.services.environmentalofficer.ContainersServices;

@Service
public class ContainersServicesImpl implements ContainersServices
{

	@Autowired
	ContainersRepository containersRepository;

	@Override
	public Containers save(Containers containers)
	{
		return containersRepository.save(containers);
	}

	@Override
	public List<Containers> containersDataById(int hmId)
	{
		return containersRepository.containersDataById(hmId);
	}

	@Override
	public List containersTypeByDate(String from, String to)
	{
		return containersRepository.containersTypeByDate(from, to);
	}

	@Override
	public List<Containers> findContainersData()
	{
		return containersRepository.findContainersData();
	}

	@Override
	public List<Containers> getTransportAndWasteDetails(String from, String to)
	{
		return containersRepository.getTransportAndWasteDetails(from, to);
	}

}
