package com.fr.adaming.enumeration;

import lombok.Getter;

@Getter
public enum typeTransEnum {
	/**
	 * @author Claire
	 */

	avion("avion"), train("train"), car("car"), bateau("bateau");
	
	private String typeTrans="";

	private typeTransEnum(String typeTrans) {
		this.typeTrans = typeTrans;
	}
	
	
}
