package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fr.adaming.entity.User;

/**
 * @author Claire
 *
 */
public interface IUserDao extends JpaRepository<User, Long> {

	/**
	 * @param email email
	 * @param pwd   mot de passe
	 * @return un user selon son email et son mot de passe
	 */
	public User findByEmailAndPwd(String email, String pwd);

	/**
	 * @param nom    nom
	 * @param prenom prenom
	 * @return un user selon son nom et son prénom
	 */
	public User findByNomAndPrenom(String nom, String prenom);
	
	/**
	 * @param idProvider
	 * @param token
	 * @return un user s'etant logge via les reseaux sociaux, avec idProvider, l'id du user dans la base de donnee du reseau social, et son token
	 * */
	public User findByIdProviderAndToken(String idProvider, String token);
	

	/**
	 * @param Long id identifiant du user
	 * @return String renvoi admin si le user est un admin, user sinon
	 */
	@Query(value="select admin from user where id= :id", nativeQuery=true)
	public String isAdmin(@Param("id") Long id);
	
}
