package com.tes.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Entity
@Table(name = "modified_record")
@Data
public class ModifiedRecord
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "reg_id")
	private RegularData regId;

	@Column(name = "old_quantity")
	private float oldQuantity;

	@Column(name = "new_quantity")
	private float newQuantity;

	@ManyToOne
	@JoinColumn(name = "requested_by_id")
	private Users requestedById;

	@ManyToOne
	@JoinColumn(name = "management_id", nullable = true)
	private Users managementId;

	// @UpdateTimestamp
	@Column(name = "approval_date")
	private Timestamp approvalDate;

	@CreationTimestamp
	@Column(name = "request_date")
	private Timestamp requestDate;

	@Column(name = "action")
	private String action;

	@Column(name = "reason")
	private String reason;
}
