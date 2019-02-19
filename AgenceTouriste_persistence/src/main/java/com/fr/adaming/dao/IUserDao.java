package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.User;


public interface IUserDao extends JpaRepository<User, Long> {
	/**
	 * @author Claire
	 * 
	 * @param email
	 * @param pwd
	 * @return un user selon son email et son mot de passe
	 */
	public User findByEmailAndPwd(String email, String pwd);
	
	/**
	 * @param nom
	 * @param prenom
	 * @return un user selon son nom et son prénom
	 */
	public User findByNomAndPrenom(String nom, String prenom);
}
