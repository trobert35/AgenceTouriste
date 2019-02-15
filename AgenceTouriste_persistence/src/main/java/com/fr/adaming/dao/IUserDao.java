package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.User;


public interface IUserDao extends JpaRepository<User, Long> {
	/**
	 * @author Claire
	 * @param email
	 * @param pwd
	 * @return
	 */

	public User findByEmailAndPwd(String email, String pwd);
}
