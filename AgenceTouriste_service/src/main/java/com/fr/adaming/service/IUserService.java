package com.fr.adaming.service;

import java.util.List;

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
}
