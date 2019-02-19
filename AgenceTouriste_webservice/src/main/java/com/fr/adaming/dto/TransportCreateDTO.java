package com.fr.adaming.dto;

import com.fr.adaming.enumeration.typeTransEnum;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransportCreateDTO {

	private String prestaTrans;
	private String villeArriveeTrans;
	private String villeDepartTrans;
	private Double prix;
	private typeTransEnum typeTrans;
}
