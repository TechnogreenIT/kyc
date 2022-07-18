package com.tes.repository.thirdparty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tes.model.Stack;
import com.tes.model.StackPoll;

@Repository
public interface StackPollRepository extends JpaRepository<StackPoll, Long>
{

	@Query(value = "select NEW StackPoll(sp.stackPollId,sp.stack,sp.pollName,sp.pollLimit,sp.unit) from StackPoll sp where sp.stack.stackId= :stackId", nativeQuery = false)
	public List<StackPoll> findByStackId(@Param("stackId") int stackId);

	@Override
	@SuppressWarnings("unchecked")
	StackPoll save(StackPoll stackPoll);

	/*
	 * @Query("SELECT sp FROM StackPoll sp WHERE sp.stack= :stackId")
	 * List<StackPoll> findByStack(int stackId);
	 */

	String findStackPollDataByconsNo = "SELECT spoll.poll_name ,spoll.poll_limit , u.units , s.stack_name ,s.attached_to FROM `stack_poll` spoll LEFT JOIN  stack s ON s.id = spoll.stack_id LEFT JOIN unit u ON spoll.unit_id = u.id  where s.consent_to_operate = 'Yes' AND s.consent_id = :consNo";

	@Query(value = findStackPollDataByconsNo, nativeQuery = true)
	public List<Object[]> findStackPollDataByconsNo(@Param("consNo") int consNo);

	@Transactional
	public List<StackPoll> deleteByStack(@Param("productId") Stack stackId);// deleteByStackPollId and this query is repeated need to remove ...by vishal

	@Transactional
	public List<StackPoll> deleteByStackPollId(int productId);

	@Query("SELECT sp FROM StackPoll sp WHERE sp.stack.stackId = :stackId")
	public List<StackPoll> findByStack(@Param("stackId") int stackId);

	@Query("SELECT DISTINCT new StackPoll(sp.stack, sp.pollName, sp.pollLimit, sp.unit) FROM StackPoll sp left join sp.stack s left join s.consent c where s.consentToOperate='Yes'")
	public List<StackPoll> getStackPollData();
}
