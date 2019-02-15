package com.fr.adaming.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fr.adaming.enumeration.pensionLogEnum;
import com.fr.adaming.enumeration.qualiteLogEnum;
import com.fr.adaming.enumeration.typeLogEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Logement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prestaLog;
	private Date entreeLog;
	private Date sortieLog;
	private String nom;
	private String ville;
	private Double prix;
	private typeLogEnum typeLog;
	private pensionLogEnum pension;
	private qualiteLogEnum qualite;
	private String urlImg;

	public Logement(String prestaLog, Date entreeLog, Date sortieLog, String nom, String ville, Double prix,
			typeLogEnum typeLog, pensionLogEnum pension, qualiteLogEnum qualite) {
		super();
		this.prestaLog = prestaLog;
		this.entreeLog = entreeLog;
		this.sortieLog = sortieLog;
		this.nom = nom;
		this.ville = ville;
		this.prix = prix;
		this.typeLog = typeLog;
		this.pension = pension;
		this.qualite = qualite;
	}

}
