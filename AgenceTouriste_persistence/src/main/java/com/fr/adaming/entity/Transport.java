package com.fr.adaming.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fr.adaming.enumeration.typeTransEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Claire
	 * @author Maxime
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prestaTrans;
	private String villeArriveeTrans;
	private String villeDepartTrans;
	private Double prix;
	private typeTransEnum typeTrans;

	/**
	 * Constructor with parameters for Transport
	 * 
	 * @param prestaTrans       le nom du prestataire du transport
	 * @param arriveeTrans      la date d'arrivee du transport
	 * @param departTrans       la date de depart du transport
	 * @param villeArriveeTrans le nom de la ville d'arrivee du transport
	 * @param villeDepartTrans  le nom de la ville de depart du transport
	 * @param prix              le prix du transport
	 * @param typeTrans         le type de transport
	 */

	public Transport(String prestaTrans, String villeArriveeTrans, String villeDepartTrans, Double prix,
			typeTransEnum typeTrans) {
		super();
		this.prestaTrans = prestaTrans;
		this.villeArriveeTrans = villeArriveeTrans;
		this.villeDepartTrans = villeDepartTrans;
		this.prix = prix;
		this.typeTrans = typeTrans;
	}

}
