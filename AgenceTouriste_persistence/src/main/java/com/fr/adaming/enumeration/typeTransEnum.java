package com.fr.adaming.enumeration;

import lombok.Getter;

/**
 * @author Claire
 *
 */
@Getter
public enum typeTransEnum {

// L agence de voyage peut definir de quel type de transport il s agit pour chaque instance de transport creee

	AVION("avion"), TRAIN("train"), CAR("car"), BATEAU("bateau");

	private String typeTrans = "";

	private typeTransEnum(String typeTrans) {
		this.typeTrans = typeTrans;
	}

}
