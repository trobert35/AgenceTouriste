package com.fr.adaming.enumeration;

import lombok.Getter;

@Getter
public enum typeTransEnum {
	/**
	 * @author Claire
	 * 
	 */
//l'agence de voyage peut définir de quel type de transport il s'agit pour chaque instance de transport créée
	
	avion("avion"), train("train"), car("car"), bateau("bateau");

	private String typeTrans = "";

	private typeTransEnum(String typeTrans) {
		this.typeTrans = typeTrans;
	}

}
