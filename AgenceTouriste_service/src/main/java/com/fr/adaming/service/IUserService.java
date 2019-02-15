package com.fr.adaming.service;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserService<User> {

	public User login(String email, String pwd);
	
	public User register(User user);

	public User readByNomAndPrenom(String nom, String prenom);

}
