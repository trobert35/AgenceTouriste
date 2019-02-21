package com.fr.adaming.enumeration;

import lombok.Getter;

/**
 * @author Claire
 *
 */
@Getter
public enum typeActEnum {

	// L agence definit le type d'activites disponibles pour chaque instance
	// d activite.

	ESCALADE("escalade"), PLAGE("plage"), MONUMENT("monument"), MUSEE("musee"), SPA("spa"), RANDONNEE("randonnee"),
	CROISIERE("croisiere"), GOLF("golf"), PLONGEE("plongee"), SAFARI("safari"), TOUR("tour");

	private String typeAct = "";

	private typeActEnum(String typeAct) {
		this.typeAct = typeAct;
	}

}
