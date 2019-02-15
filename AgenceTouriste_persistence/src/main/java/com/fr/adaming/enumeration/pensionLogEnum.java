package com.fr.adaming.enumeration;

import lombok.Getter;

@Getter
public enum pensionLogEnum {

	demiPension("demiPension"), pensionComplete("pensionComp"), toutInclus("toutInclus"), petitDejeuner("petitDej");
	
	private String pensionLog = "";

	private pensionLogEnum(String pensionLog) {
		this.pensionLog = pensionLog;
	}
	
	
	
}
