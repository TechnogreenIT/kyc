package com.tes.services.impl.environmentalofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.StackOp;
import com.tes.repository.environmentalofficer.StackOpRepository;
import com.tes.services.environmentalofficer.StackOpServices;

@Service
public class StackOpServicesImpl implements StackOpServices
{

	@Autowired
	StackOpRepository stackOpRepository;

	@Override
	public StackOp save(StackOp stackOp)
	{
		return stackOpRepository.save(stackOp);
	}

}
