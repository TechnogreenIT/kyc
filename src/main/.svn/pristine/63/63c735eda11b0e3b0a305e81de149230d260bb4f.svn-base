package com.tes.services.impl.thirdparty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Stack;
import com.tes.repository.thirdparty.StackRepository;
import com.tes.services.thirdparty.StackServices;

@Service
public class StackServicesImpl implements StackServices
{

	@Autowired
	StackRepository stackRepository;

	@Override
	public List<Stack> getConsentId(String today)
	{
		return stackRepository.getConsentId(today);
	}

	@Override
	public List<Stack> findByConsNo(int stackId)
	{
		return stackRepository.findByConsNo(stackId);
	}

	@Override
	public Stack save(Stack stack)
	{
		return stackRepository.save(stack);
	}

	@Override
	public List<Stack> findStackInfoToShow(int stackId, int month, int year)
	{
		return stackRepository.findStackInfoToShow(stackId, month, year);
	}

	@Override
	public int[] getStackId(String today, int companyId)
	{
		return stackRepository.getStackId(today, companyId);
	}

	@Override
	public List<Stack> findStackData(String today)
	{
		return stackRepository.findStackData(today);
	}

	@Override
	public List<Stack> findByConsType()
	{
		return stackRepository.findByConsType();
	}

	@Override
	public int updateConsentToOperate(int stackId)
	{
		return stackRepository.updateConsentToOperate(stackId);
	}

	@Override
	public List<Stack> findAllByConsNoOrderByASC(int consentId)
	{
		return stackRepository.findAllByConsNoOrderByASC(consentId);
	}

	@Override
	public List<Stack> findByconsentToOperate(int consentId)
	{
		return stackRepository.findByconsentToOperate(consentId);
	}

	@Override
	public List<Stack> deleteByStackId(int productId)
	{
		return stackRepository.deleteByStackId(productId);
	}

	@Override
	public Stack findByStackId(int stackId)
	{
		return stackRepository.findByStackId(stackId);
	}

	@Override
	public int[] getStackIdAttchedTo(int companyId, String poll)
	{
		return stackRepository.getStackIdAttchedTo(companyId, poll);
	}

	@Override
	public int[] getStackIdAttchedToReg()
	{
		return stackRepository.getStackIdAttchedToReg();
	}

	@Override
	public int[] getStackIdAttchedToRegGases()
	{
		return stackRepository.getStackIdAttchedToRegGases();
	}

	@Override
	public int[] getStackIdForAhp(String today, int companyId)
	{
		return stackRepository.getStackIdForAhp(today, companyId);
	}

}
