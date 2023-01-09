package com.tes.repository.thirdparty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tes.model.Stack;

public interface StackRepository extends JpaRepository<Stack, Integer>
{

	@Query("SELECT  s FROM StackOp sop LEFT JOIN sop.stack s LEFT JOIN sop.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	List<Stack> getConsentId(@Param("today") String today);

	@Query("SELECT sop.stack.stackId FROM StackOp sop LEFT JOIN sop.consent c  LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate'  AND c.companyProfile.companyProfileId  =:companyId  AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >=:today OR c.validUpto >= :today)")
	int[] getStackId(@Param("today") String today, @Param("companyId") int companyId);

	// To get company Id
	@Query("SELECT e.companyProfile.companyProfileId FROM EmpData e WHERE e.users.usersId=:userId")
	int findCompanyId(@Param("userId") int userId);

	@Query("SELECT s FROM Stack s WHERE s.consent.consentId= :stackId")
	public List<Stack> findByConsNo(@Param("stackId") int stackId);

	@Query("SELECT s FROM StackOp sop LEFT JOIN sop.stack s LEFT JOIN sop.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	public List<Stack> findStackData(@Param("today") String today);

	@Override
	@SuppressWarnings("unchecked")
	Stack save(Stack stack);

	@Query("SELECT DISTINCT(s) FROM Stack s, RegStPoll r WHERE s.stackId= :stackId AND r.stackName = s.stackName AND r.attachTo = s.attachedTo AND EXTRACT(MONTH FROM r.sampSt) = :month AND (EXTRACT(YEAR FROM r.sampSt)) = :year")
	public List<Stack> findStackInfoToShow(@Param("stackId") int stackId, @Param("month") int month, @Param("year") int year);

	@Query("SELECT s FROM Stack s, Consent c WHERE c.consentId=s.consent AND c.consType='Consent to Establish' ")
	List<Stack> findByConsType();

	@Transactional
	@Modifying
	@Query("UPDATE Stack s SET s.consentToOperate =  'Yes' WHERE s.stackId = :stackId")
	int updateConsentToOperate(@Param("stackId") int stackId);

	@Query("SELECT NEW Stack(s.stackId,s.consent,s.stackName,s.attachedTo,s.capacity,s.capacityUnits,s.fuelType,s.fuelQuant,s.fuelUnits,s.shape,s.height,s.htUnits,s.diam,s.diamUnits,s.matCons,s.apc,s.apcSystem,s.consentToOperate,s.gasQuant,s.gasUnit,s.gasTemp,s.gasTempUnit,s.exitGasVel,s.exitGasUnit,s.precedingStack,s.polluPresent,s.eCSProvided,s.genCapacity,s.genCapUnit) FROM Stack s WHERE s.consent.consentId = :consentId ORDER BY s.stackId ASC")
	List<Stack> findAllByConsNoOrderByASC(@Param("consentId") int consentId);

	@Query("SELECT s FROM StackOp sop LEFT JOIN sop.stack s LEFT JOIN sop.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND  sop.consent.consentId=:consentId")
	List<Stack> findByconsentToOperate(@Param("consentId") int consentId);

	/*
	 * @Query("SELECT sop.stack.stackId FROM StackOp sop LEFT JOIN sop.stack s LEFT JOIN s.consent c  LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate'  AND c.companyProfile.companyProfileId  =:companyId  AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >=:today OR c.validUpto >= :today) AND s.attachedTo=:poll")
	 * int[] getStackIdAttchedTo(@Param("today") String today,@Param("companyId") int companyId,@Param("poll") String poll);
	 */

	@Query("SELECT sop.stack.stackId FROM StackOp sop LEFT JOIN sop.stack s LEFT JOIN s.consent c  WHERE c.companyProfile.companyProfileId  =:companyId  AND s.attachedTo=:poll")
	int[] getStackIdAttchedTo(@Param("companyId") int companyId, @Param("poll") String poll);

	@Query("SELECT sp.regStPoll.regStPollId FROM StackPollData sp,Particulate p ,RegStPoll r LEFT JOIN r.stack s  WHERE  r.regStPollId =sp.regStPoll.regStPollId AND r.stack.stackId=s.stackId AND sp.pollName=p.pollutant AND p.type='Particulate'")
	int[] getStackIdAttchedToReg();

	@Query("SELECT sp.regStPoll.regStPollId FROM StackPollData sp,Particulate p ,RegStPoll r LEFT JOIN r.stack s  WHERE  r.regStPollId =sp.regStPoll.regStPollId AND r.stack.stackId=s.stackId AND sp.pollName=p.pollutant AND p.type='Gases'")
	int[] getStackIdAttchedToRegGases();

	@Transactional
	List<Stack> deleteByStackId(int productId);

	@Query("SELECT sop.stack.stackId FROM StackOp sop LEFT JOIN sop.stack s LEFT JOIN s.consent c  LEFT JOIN  c.consentExtendedDate ce  WHERE  c.companyProfile.companyProfileId  =:companyId  AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	int[] getStackIdForAhp(@Param("today") String today, @Param("companyId") int companyId);

	Stack findByStackId(int stackId);

}
