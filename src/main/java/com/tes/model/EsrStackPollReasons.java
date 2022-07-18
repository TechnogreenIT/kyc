package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "esr_stack_poll_reasons")
@Data
public class EsrStackPollReasons
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "poll_name")
	private String pollName;

	@Column(name = "reason")
	private String reason;

	@Column(name = "reason_date")
	private String reasonDate;
}
