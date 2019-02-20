package com.fr.adaming.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationCreateDTO {

	private String nom;
	@ApiModelProperty(example = "dd/mm/yyyy")
	private String debutPresta;
	@ApiModelProperty(example = "dd/mm/yyyy")
	private String finPresta;
	private String villeDepartArrivee;
	private String destination;
	private int nbPersonnes; // le nombre de personnes participant a cette formation
	private double commission;
	private String avis;
	private int nbPersonnesMax;
}
