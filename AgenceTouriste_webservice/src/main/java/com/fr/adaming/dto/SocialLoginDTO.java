package com.fr.adaming.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SocialLoginDTO {

	private Long id;
	private String name;
	private String email;
	private String pwd;
	private String idProvider;
	private String token;
	private String provider;
	private String idToken;
	private String urlImg;
	
	public SocialLoginDTO(String name, String email, String pwd, String idProvider, String token,
			String provider, String idToken, String urlImg) {
		super();
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.idProvider = idProvider;
		this.token = token;
		this.provider = provider;
		this.idToken = idToken;
		this.urlImg = urlImg;
		
	}
	

	
}
