package com.fr.adaming.enumeration;

import lombok.Getter;

@Getter
public enum typeLogEnum {

	hotel("hotel"), auberge("auberge"), gite("gite"), camping("camping"), palace("palace"), 
	chambre("chambre"), appartement("appartement");
	
	private String typeLog = "";

	private typeLogEnum(String typeLog) {
		this.typeLog = typeLog;
	}
	
	
}
