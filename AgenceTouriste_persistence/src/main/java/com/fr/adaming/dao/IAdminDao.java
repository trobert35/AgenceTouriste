package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Admin;

/**
 * @author Claire
 *
 */
public interface IAdminDao extends JpaRepository<Admin, Long> {

	/**
	 * @param email l'email
	 * @param pwd   le mot de passe
	 * @return un Admin trouve grace a l'email et le password
	 */
	public Admin findByEmailAndPwd(String email, String pwd);
}
