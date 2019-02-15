package com.fr.adaming.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Admin extends User {
	/**
	 * @author Claire
	 */

	private Boolean admin;
	private float remise;

	public Admin(String nom, String prenom, String email, String pwd, Boolean admin, float remise) {
		super(nom, prenom, email, pwd);
		this.admin = admin;
		this.remise = remise;
	}

	public Admin(String nom, String prenom, String email, String pwd, float remise) {
		super(nom, prenom, email, pwd);
		this.remise = remise;
	}
	
	

}
