package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.User;


public interface IUserDao extends JpaRepository<User, Long> {

	public User findByEmailAndPwd(String email, String pwd);
	public User findByNomAndPrenom(String nom, String prenom);
}
