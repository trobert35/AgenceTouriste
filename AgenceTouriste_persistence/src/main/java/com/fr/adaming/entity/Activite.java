package com.fr.adaming.entity;

import java.io.Serializable;

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
public class Activite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private String urlImg; // l url de l'image correspondant a l activite

	/**
	 * Constructor with parameters for Activite
	 * 
	 * @param prix         le prix de l'activite
	 * @param typeAct      le type d'activite
	 * @param nom          le nom de l'activite
	 * @param nomPrestaAct le nom du prestataire de l'activite
	 */
	public Activite(Double prix, typeActEnum typeAct, String nom, String nomPrestaAct) {
		super();
		this.prix = prix;
		this.typeAct = typeAct;
		this.nom = nom;
		this.nomPrestaAct = nomPrestaAct;
	}

}
