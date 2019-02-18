package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	/**
	 * @author Claire
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	@Column(unique = true)
	private String email;
	private String pwd;
	private String urlImg;

	@Embedded
	private CB cb;

	/**
	 * Constructor with parameters for User
	 * 
	 * @param nom    le nom du user
	 * @param prenom le prenom du user
	 * @param email  l'email du user
	 * @param pwd    le mot de passe du user
	 * @param cb     la carte bancaire du user
	 */

	public User(String nom, String prenom, String email, String pwd, CB cb) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.pwd = pwd;
		this.cb = cb;
	}

	/**
	 * Constructor with parameters (without CB) for User
	 * @param nom    le nom du user
	 * @param prenom le prenom du user
	 * @param email  l'email du user
	 * @param pwd    le mot de passe du user
	 */
	public User(String nom, String prenom, String email, String pwd) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.pwd = pwd;
	}

}
