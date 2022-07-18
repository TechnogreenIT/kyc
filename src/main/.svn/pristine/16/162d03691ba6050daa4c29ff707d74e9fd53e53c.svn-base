package com.tes.services.thirdparty;

import java.util.List;

import com.tes.model.Stack;

public interface StackServices
{

	List<Stack> getConsentId(String today);

	// List<Stack> findStackInfo();

	List<Stack> findByConsNo(int stackId);

	Stack save(Stack stack);

	List<Stack> findStackInfoToShow(int a, int mon, int yr);

	int[] getStackId(String today, int companyId);

	int updateConsentToOperate(int stackId);

	List<Stack> findByConsType();

	List<Stack> findStackData(String today);

	List<Stack> findAllByConsNoOrderByASC(int consentId);

	List<Stack> findByconsentToOperate(int consentId);

	List<Stack> deleteByStackId(int productId);

	Stack findByStackId(int stackId);

	int[] getStackIdAttchedTo(int companyId, String poll);

	public int[] getStackIdAttchedToReg();

	public int[] getStackIdAttchedToRegGases();

	public int[] getStackIdForAhp(String today, int companyId);

}
