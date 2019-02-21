package com.fr.adaming.enumeration;

import lombok.Getter;

/**
 * @author Claire
 *
 */
@Getter
public enum typeLogEnum {

	// L agence definit les types de logements disponibles pour chaque instance de
	// logement.

	HOTEL("hotel"), AUBERGE("auberge"), GITE("gite"), CAMPING("camping"), PALACE("palace"), CHAMBRE("chambre"),
	APPARTEMENT("appartement");

	private String typeLog = "";

	private typeLogEnum(String typeLog) {
		this.typeLog = typeLog;
	}

}
