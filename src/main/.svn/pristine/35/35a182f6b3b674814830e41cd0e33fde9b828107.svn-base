package com.tes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.Todo;
import com.tes.repository.TodoRepository;
import com.tes.services.TodoServices;

@Service
public class TodoServicesImpl implements TodoServices
{

	@Autowired
	TodoRepository todoRepository;

	@Override
	public Todo save(Todo todo)
	{
		return todoRepository.save(todo);
	}

	@Override
	public List<Object[]> findOneByGetAllToDo(int empId)
	{
		return todoRepository.findOneByGetAllToDo(empId);
	}

	@Override
	public int findOneByDeleteToDo(int id)
	{
		return todoRepository.findOneByDeleteToDo(id);
	}

	@Override
	public int updateToDoAction(int id)
	{
		return todoRepository.updateToDoAction(id);
	}

	@Override
	public int updateToDoMsg(String todoMsg, String cDate, int id)
	{
		return todoRepository.updateToDoMsg(todoMsg, cDate, id);
	}

	@Override
	public List findOneByGetAllToDoMsg(String consentNo)
	{
		return todoRepository.findOneByGetAllToDoMsg(consentNo);
	}
}
