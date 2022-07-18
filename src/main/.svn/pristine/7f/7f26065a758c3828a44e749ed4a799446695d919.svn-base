package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "reg_eff_poll")
public class RegEffPoll
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long regEffPollId;

	@ManyToOne
	@JoinColumn(name = "wwtp_id")
	private WastewaterTreatment wastewaterTreatment;

	@ManyToOne
	@JoinColumn(name = "poll_id")
	private WateriePollutantOp wateriePollutantOp;

	@Column(name = "in_cons_e")
	private float inConsE;

	@Column(name = "ou_cons_e")
	private float ouConsE;

	@Column(name = "unit_e")
	private String unitE;

	@Column(name = "samp_e")
	private String sampE;

	@Column(name = "sub_e")
	private String subE;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "reason")
	private String reason;

	@Transient
	private String label;
	@Transient
	private String treatmentType;
	@Transient
	private int trementId;

	public RegEffPoll(WastewaterTreatment wastewaterTreatment)
	{
		super();
		this.label = wastewaterTreatment.getLabel();
		this.treatmentType = wastewaterTreatment.getTreatmentType();
		this.trementId = wastewaterTreatment.getWastewaterTreatmentId();
	}

	public RegEffPoll()
	{
		super();
	}

}
