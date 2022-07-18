package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stack_op")
public class StackOp
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int stackOpId;

	@ManyToOne
	@JoinColumn(name = "cons_no")
	private Consent consent;

	@ManyToOne
	@JoinColumn(name = "stack_id")
	private Stack stack;

	public int getStackOpId()
	{
		return stackOpId;
	}

	public void setStackOpId(int stackOpId)
	{
		this.stackOpId = stackOpId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public Stack getStack()
	{
		return stack;
	}

	public void setStack(Stack stack)
	{
		this.stack = stack;
	}

}
