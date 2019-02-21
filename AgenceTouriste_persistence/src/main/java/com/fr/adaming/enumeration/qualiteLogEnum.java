package com.fr.adaming.enumeration;

import lombok.Getter;

/**
 * @author Claire
 *
 */
@Getter
public enum qualiteLogEnum {

	// L agence definit la qualite du logement pour chaque instance de logement.

	UN("1"), DEUX("2"), TROIS("3"), QUATRE("4"), CINQ("5");

	private String qualLog = "";

	private qualiteLogEnum(String qualLog) {
		this.qualLog = qualLog;
	}

}
