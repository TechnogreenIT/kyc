package com.tes.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "prefilter")
public class Prefilter
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	// @Setter(AccessLevel.NONE)
	private int prefilterId;

	@ManyToOne
	@JoinColumn(name = "ws_id")
	private WaterSource waterSource;

	@Column(name = "ACF")
	private boolean acf;

	@Column(name = "PSF")
	private boolean psf;

	@Column(name = "DMF")
	private boolean dmf;

	@Column(name = "is_prefilter")
	private boolean isPrefilter;

	@Column(name = "is_meter")
	private boolean isMeter;

	@Transient
	private List<RegPrefilter> regPrefilterList = new ArrayList<>();

	public Prefilter(int prefilterId, WaterSource waterSource)
	{
		super();
		this.prefilterId = prefilterId;
		this.waterSource = waterSource;
	}

	public Prefilter()
	{
		super();
	}

}
