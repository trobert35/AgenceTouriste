package com.fr.adaming.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fr.adaming.enumeration.typeActEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Activite {
	/**
	 * @author Claire
	 *
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double prix;
	private typeActEnum typeAct;
	private String nom;
	private String nomPrestaAct;
	private String urlImg;

	public Activite(Double prix, typeActEnum typeAct, String nom, String nomPrestaAct) {
		super();
		this.prix = prix;
		this.typeAct = typeAct;
		this.nom = nom;
		this.nomPrestaAct = nomPrestaAct;
	}

}
