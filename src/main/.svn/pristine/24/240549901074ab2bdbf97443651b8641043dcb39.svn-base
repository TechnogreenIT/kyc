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
@Table(name = "waterie_pollutant_op")
@Data
public class WateriePollutantOp
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int wateriePollutantOpId;

	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;

	@ManyToOne
	@JoinColumn(name = "eff_pollutant_id")
	private WateriePollutant wateriePollutant;

	// public int getWateriePollutantOpId() {
	// return wateriePollutantOpId;
	// }
	//
	// public void setWateriePollutantOpId(int wateriePollutantOpId) {
	// this.wateriePollutantOpId = wateriePollutantOpId;
	// }
	//
	// public Consent getConsent() {
	// return consent;
	// }
	//
	// public void setConsent(Consent consent) {
	// this.consent = consent;
	// }
	//
	// public WateriePollutant getWateriePollutant() {
	// return wateriePollutant;
	// }
	//
	// public void setWateriePollutant(WateriePollutant wateriePollutant) {
	// this.wateriePollutant = wateriePollutant;
	// }

}
