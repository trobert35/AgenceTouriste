package com.fr.adaming.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class RegisterDTO {

	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String pwd;
	private Long numCartBank;
	private Long crypto;
	private Date dateExp;
	
}
