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
	/**
	 * @author Claire
	 */

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

	/**
	 * Constructor with parameters for Logement
	 * @param prestaLog le nom du prestataire du logement
	 * @param entreeLog la date d'entree dans le logement
	 * @param sortieLog la date de la sortie du logement
	 * @param nom       le nom du logement
	 * @param ville     la ville ou le logement se situe
	 * @param prix      le prix de location du logement
	 * @param typeLog   le type de logement
	 * @param pension   le type de pension desire
	 * @param qualite   l'evaluation du logement
	 */
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
