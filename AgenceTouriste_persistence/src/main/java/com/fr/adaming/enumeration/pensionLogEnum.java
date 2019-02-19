package com.fr.adaming.enumeration;

import lombok.Getter;

@Getter
public enum pensionLogEnum {
	/**
	 * @author Claire
	 */
	
	//L'agence définit le type de pension pour chaque instance de logement.

	demiPension("demiPension"), pensionComplete("pensionComp"), toutInclus("toutInclus"), petitDejeuner("petitDej");
	
	private String pensionLog = "";

	private pensionLogEnum(String pensionLog) {
		this.pensionLog = pensionLog;
	}
	
	
	
}
