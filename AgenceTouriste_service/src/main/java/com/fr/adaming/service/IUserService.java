package com.fr.adaming.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.User;

/**
 * @author Thomas S
 *
 * @param <T> objet de la classe Object
 */
public interface IUserService<T> {

	public T login(String email, String pwd);

	public T register(User user);

	/**
	 * @param nom    nom du User
	 * @param prenom prenom du User
	 * @return un objet de la classe User
	 */
	public T readByNomAndPrenom(String nom, String prenom);

	/**
	 * @param user objet de classe User
	 * @return un objet de la classe User
	 */
	public T create(User user);

	/**
	 * @param user objet de classe User
	 * @return un objet de la classe User
	 */
	public T update(User user);

	/**
	 * @param id id du User
	 * @return un objet de la classe User
	 */
	public T readById(Long id);

	/**
	 * @return une List de User
	 */
	public List<T> readAll();

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

	/**
	 * @param idProvider identifiant du user sur la base de donnee du reseau social
	 * @param token 	token du user envoye par le reseau social
	 * @return User si le login ou le register fonctionnent, null sinon 
	 */
	public User socialLogin(User user);

	public String isAdmin(Long id);
	
}
