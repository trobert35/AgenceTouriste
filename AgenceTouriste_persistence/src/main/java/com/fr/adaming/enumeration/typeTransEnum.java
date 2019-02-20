package com.fr.adaming.enumeration;

import lombok.Getter;

@Getter
public enum typeTransEnum {
	/**
	 * @author Claire
	 * 
	 */
// L agence de voyage peut definir de quel type de transport il s agit pour chaque instance de transport creee

	avion("avion"), train("train"), car("car"), bateau("bateau");

	private String typeTrans = "";

	private typeTransEnum(String typeTrans) {
		this.typeTrans = typeTrans;
	}

}
