package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo
{

	@Id
	@GeneratedValue()
	@Column(name = "id")
	private int todoId;

	@ManyToOne
	@JoinColumn(name = "emp_id")
	private EmpData empData;

	@Column(name = "create_date")
	private String createDate;

	@Column(name = "todo")
	private String todo;

	@Column(name = "action")
	private String action;

	public int getTodoId()
	{
		return todoId;
	}

	public void setTodoId(int todoId)
	{
		this.todoId = todoId;
	}

	public EmpData getEmpData()
	{
		return empData;
	}

	public void setEmpData(EmpData empData)
	{
		this.empData = empData;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getTodo()
	{
		return todo;
	}

	public void setTodo(String todo)
	{
		this.todo = todo;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	@Override
	public String toString()
	{
		return "Todo [todoId=" + todoId + ", empData=" + empData + ", createDate=" + createDate + ", todo=" + todo
				+ ", action=" + action + "]";
	}

}
