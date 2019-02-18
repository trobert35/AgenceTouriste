package com.fr.adaming.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CB {
	/**
	 * @author Claire
	 */

	@Column(unique = true, nullable = true)
	private Long numeroCarte; // numéro de la carte bancaire
	private Date dateExp; // la date d'expiration de la carte bancaire
	private Long crypto; // le cryptogramme de la carte bancaire

}
