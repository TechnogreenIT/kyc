package com.tes.services.environmentalofficer;

import java.util.List;

import com.tes.model.Containers;

public interface ContainersServices
{
	public Containers save(Containers containers);

	public List<Containers> containersDataById(int hmId);

	public List<Containers> containersTypeByDate(String from, String to);

	List<Containers> findContainersData();

	List<Containers> getTransportAndWasteDetails(String from, String to);
}
