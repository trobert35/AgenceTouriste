package com.fr.adaming.dto;

import com.fr.adaming.enumeration.pensionLogEnum;
import com.fr.adaming.enumeration.qualiteLogEnum;
import com.fr.adaming.enumeration.typeLogEnum;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogementCreateDTO {
	
	private String prestaLog;
	private String nom;
	private String ville;
	private double prix;
	private typeLogEnum typeLog;
	private pensionLogEnum pension;
	private qualiteLogEnum qualite;
}
