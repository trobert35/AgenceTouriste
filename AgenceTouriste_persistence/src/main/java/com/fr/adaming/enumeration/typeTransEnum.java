package com.fr.adaming.enumeration;

import lombok.Getter;

@Getter
public enum typeTransEnum {

	avion("avion"), train("train"), car("car"), bateau("bateau");
	
	private String typeTrans="";

	private typeTransEnum(String typeTrans) {
		this.typeTrans = typeTrans;
	}
	
	
}
