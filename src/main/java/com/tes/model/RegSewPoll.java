package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "reg_sew_poll")
@Data
public class RegSewPoll
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long regSewPollId;

	@ManyToOne
	@JoinColumn(name = "wwtp_id")
	private WastewaterTreatment wastewaterTreatment;

	@ManyToOne
	@JoinColumn(name = "poll_id")
	private WaterSewPollOp waterSewPollOp;

	@Column(name = "in_cons_s")
	private float inConsS;

	@Column(name = "ou_cons_s")
	private float ouConsS;

	@Column(name = "unit_s")
	private String unitS;

	@Column(name = "samp_s")
	private String sampS;

	@Column(name = "sub_s")
	private String subS;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "reason")
	private String reason;
}
