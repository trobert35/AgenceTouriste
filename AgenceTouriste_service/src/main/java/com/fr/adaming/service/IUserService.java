package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Prestation;

public interface IUserService<User> {
	
	/**
	 * @author Thomas S
	 */

	public User login(String email, String pwd);

	public User register(User user);

	public User readByNomAndPrenom(String nom, String prenom);
	
	public User create(User user);

	public User update(User user);

	public User readById(Long id);

	public List<User> readAll();

	public String deleteById(Long id);
	
	public boolean bookPrestation(User user, Prestation prestation);
}
