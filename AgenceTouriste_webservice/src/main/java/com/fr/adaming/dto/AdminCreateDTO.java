package com.fr.adaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Thomas R
 *
 */
@Getter
@Setter
public class AdminCreateDTO {

	String nom;
	String prenom;
	@Email
	String email;
	@NotNull
	String pwd;
	float remise;
}
