package com.tes.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	Todo save(Todo todo);

	@Query("SELECT  t.todoId,t.todo,t.createDate FROM Todo t  WHERE t.empData.empDataId= :empId AND t.action != 'save'")
	List<Object[]> findOneByGetAllToDo(@Param("empId") int empId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Todo t  WHERE t.todoId= :id")
	int findOneByDeleteToDo(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Todo t SET t.action='save' WHERE  t.todoId= :id")
	int updateToDoAction(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Todo t SET t.todo= :todoMsg,t.createDate= :cDate WHERE t.todoId= :id")
	int updateToDoMsg(@Param("todoMsg") String todoMsg, @Param("cDate") String cDate, @Param("id") int id);

	@Query(value = "SELECT t.todoId FROM Todo t where t.todo LIKE %:consentNo%")
	List findOneByGetAllToDoMsg(@Param("consentNo") String consentNo);

	@Query(value = "SELECT t.todoId FROM Todo t ORDER BY t.todoId DESC")
	List<Todo> getCheckTodoData(Pageable pageable);

	/// mmm
	// @Query(value = "DELETE FROM Todo t WHERE t.todoId= :todo")
	// int findOneByDeleteToDobynm(@Param("todo") String todo);
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Todo t  WHERE t.empData.empDataId= :empId ")
	int deletecheckTodoData(@Param("empId") int empId);

}
