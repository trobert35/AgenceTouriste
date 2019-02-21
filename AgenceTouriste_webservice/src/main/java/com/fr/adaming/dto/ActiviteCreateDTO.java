package com.fr.adaming.dto;

import com.fr.adaming.enumeration.typeActEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Thomas R
 *
 */
@Getter
@Setter
public class ActiviteCreateDTO {

	private Double prix;
	private typeActEnum typeAct;
	private String nom;
	private String nomPrestaAct;
}
