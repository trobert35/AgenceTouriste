package com.fr.adaming.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PrestationCreateDTO {

	private String nom;
	private Date debutPresta;
	private Date finPresta;
	private String villeDepartArrivee;
	private String destination;
	private int nbPersonnes; // le nombre de personnes participant à cette formation
	private double commission;
	private String avis;
	private int nbPersonnesMax;
}
