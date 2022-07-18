package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "filters")
public class Filters
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	@Setter(AccessLevel.NONE)
	private int filtersId;

	@ManyToOne
	@JoinColumn(name = "prefilter_id")
	private Prefilter prefilter;

	@Column(name = "filter_type")
	private String filterType;
}
