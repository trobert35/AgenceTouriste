package com.fr.adaming.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Claire
	 */

	@Column(unique = true, nullable = true)
	private Long numeroCarte; // numero de la carte bancaire
	private Date dateExp; // la date d expiration de la carte bancaire
	private Long crypto; // le cryptogramme de la carte bancaire

}
