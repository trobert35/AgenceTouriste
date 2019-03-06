package com.fr.adaming.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Claire
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "admin")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	@Column(unique = true)
	@Email
	private String email;
	private String pwd;
	private String urlImg; // l url correspondant a la photo du user

	@Embedded
	private CB cb;
	
	private String provider;
	
	@Column(unique=true)
	private String idProvider;
	private String token;
	private String idToken;

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
	 * 
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

	public User(String nom, String prenom, @Email String email, String pwd, String urlImg, CB cb, String provider,
			String idProvider, String token, String idToken) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.pwd = pwd;
		this.urlImg = urlImg;
		this.cb = cb;
		this.provider = provider;
		this.idProvider = idProvider;
		this.token = token;
		this.idToken = idToken;
	}

	public User(String nom, String prenom, @Email String email, String urlImg, String provider,
			String idProvider, String token, String idToken) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.urlImg = urlImg;
		this.provider = provider;
		this.idProvider = idProvider;
		this.token = token;
		this.idToken = idToken;
	}

	
	
}
