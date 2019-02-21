package com.fr.adaming.enumeration;

import lombok.Getter;

/**
 * @author Claire
 *
 */
@Getter
public enum pensionLogEnum {

	// L agence definit le type de pension pour chaque instance de logement.

	DEMIPENSION("demiPension"), PENSIONCOMPLETE("pensionComp"), TOUTINCLUS("toutInclus"), PETITDEJEUNER("petitDej");

	private String pensionLog = "";

	private pensionLogEnum(String pensionLog) {
		this.pensionLog = pensionLog;
	}

}
