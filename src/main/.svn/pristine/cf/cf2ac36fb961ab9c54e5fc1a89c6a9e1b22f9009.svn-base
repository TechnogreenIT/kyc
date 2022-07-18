package com.tes.services.impl.thirdparty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Stack;
import com.tes.model.StackPoll;
import com.tes.repository.thirdparty.StackPollRepository;
import com.tes.services.thirdparty.StackPollServices;

@Service
public class StackPollServicesImpl implements StackPollServices
{

	@Autowired
	StackPollRepository stackPollRepository;

	@Override
	public List<StackPoll> findByStackId(int stackId)
	{
		return stackPollRepository.findByStackId(stackId);
	}

	@Override
	public StackPoll save(StackPoll stackPoll)
	{
		return stackPollRepository.save(stackPoll);
	}

	@Override
	public List<StackPoll> deleteByStack(Stack productId)
	{
		return stackPollRepository.deleteByStack(productId);
	}

	@Override
	public List<StackPoll> deleteByStackPollId(int productId)
	{
		return stackPollRepository.deleteByStackPollId(productId);
	}

	@Override
	public List<StackPoll> findByStack(int stackId)
	{
		return stackPollRepository.findByStack(stackId);
	}

	@Override
	public List<StackPoll> getStackPollData()
	{
		return stackPollRepository.getStackPollData();
	}
}
