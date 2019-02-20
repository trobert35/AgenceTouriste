package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Prestation;

/**
 * @author Thomas S
 *
 * @param <User> objet de la classe User
 */
public interface IUserService<User> {

	public User login(String email, String pwd);

	public User register(User user);

	/**
	 * @param nom    nom du User
	 * @param prenom prenom du User
	 * @return un objet de la classe User
	 */
	public User readByNomAndPrenom(String nom, String prenom);

	/**
	 * @param user objet de classe User
	 * @return un objet de la classe User
	 */
	public User create(User user);

	/**
	 * @param user objet de classe User
	 * @return un objet de la classe User
	 */
	public User update(User user);

	/**
	 * @param id id du User
	 * @return un objet de la classe User
	 */
	public User readById(Long id);

	/**
	 * @return une List de User
	 */
	public List<User> readAll();

	/**
	 * @param id id du User
	 * @return String
	 */
	public String deleteById(Long id);

	/**
	 * @param user       objet de classe User
	 * @param prestation objet de classe Prestation
	 * @return true si le User ou la Prestation sont connus, false sinon
	 */
	public boolean bookPrestation(User user, Prestation prestation);
}
