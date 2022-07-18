package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.AmbientOp;
import com.tes.repository.environmentalofficer.AmbientOpRepository;
import com.tes.services.environmentalofficer.AmbientOpServices;

@Service
public class AmbiantOpServicesImpl implements AmbientOpServices
{

	@Autowired
	AmbientOpRepository ambientOpRepository;

	@Override
	public AmbientOp save(AmbientOp ambientOp)
	{
		return ambientOpRepository.save(ambientOp);
	}

}
