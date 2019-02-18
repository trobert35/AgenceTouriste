package com.fr.adaming.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue("admin")
public class Admin extends User {
	/**
	 * @author Claire
	 */

	private float remise;

	/**
	 * Constructor with parameters for Admin
	 * @param nom    le nom de l'admin
	 * @param prenom le prenom de l'admin
	 * @param email  l'email de l'admin
	 * @param pwd    le mot de passe de l'admin
	 * @param remise la remise accordee a l'admin sur les prestations
	 */

	public Admin(String nom, String prenom, String email, String pwd, float remise) {
		super(nom, prenom, email, pwd);
		this.remise = remise;
	}

}
