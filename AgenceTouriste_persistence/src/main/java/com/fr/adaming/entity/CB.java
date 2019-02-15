package com.fr.adaming.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class CB {
	/**
	 * @author Claire
	 */

	@Column(unique=true, nullable=true)
	private Long numeroCarte;
	private Date dateExp;
	private Long crypto;
}
