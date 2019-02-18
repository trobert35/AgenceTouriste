package com.fr.adaming.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prestation {
	/**
	 * @author Claire
	 * @author Maxime
	 *
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nom;
	private Date debutPresta;
	private Date finPresta;
	private String villeDepartArrivee;
	private String destination;
	private int nbPersonnes; // le nombre de personnes participant à cette formation
	private float commission;
	private String avis;
	private int nbPersonnesMax;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestation")
	private List<Transport> transport;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestation")
	private List<Logement> logement;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestation")
	private List<Activite> activite;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestation")
	private List<User> user;

	/**
	 * Constructor with parameters for Prestation
	 * 
	 * @param nom                le nom de la prestation
	 * @param debutPresta        la date de debut de la prestation
	 * @param finPresta          la date de fin de la prestation
	 * @param villeDepartArrivee le nom de la ville d'aller et de retour de la
	 *                           prestation
	 * @param destination        le nom de la destination
	 * @param nbPersonnesMax     le nombre de personnes maximal pour cette
	 *                           prestation
	 * @param commission         la commission due a l'agence de voyage
	 * @param avis               l'avis des clients sur cette prestation
	 */

	public Prestation(String nom, Date debutPresta, Date finPresta, String villeDepartArrivee, String destination,
			int nbPersonnesMax, float commission, String avis) {
		super();
		this.nom = nom;
		this.debutPresta = debutPresta;
		this.finPresta = finPresta;
		this.villeDepartArrivee = villeDepartArrivee;
		this.destination = destination;
		this.nbPersonnesMax = nbPersonnesMax;
		this.commission = commission;
		this.avis = avis;
	}

}
