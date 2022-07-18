package com.tes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "air_marks")
@Data
public class AirMarks
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int airMarksId;

	@Column(name = "pollutants")
	private String pollutants;

	@Column(name = "type")
	private String type;

	@Column(name = "marks")
	private float marks;

	@Column(name = "main_type")
	private String mainType;
}
