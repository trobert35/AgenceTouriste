package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.User;

public interface IAdminDao extends JpaRepository<User, Long> {
	/**
	 * @author Claire
	 */

	/**
	 * @param email l'email
	 * @param pwd le mot de passe
	 * @return un Admin trouve grace a l'email et le password
	 */
	public User findByEmailAndPwd(String email, String pwd);
}
