package com.fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.User;

@Service
public class UserService implements IUserService<User> {

	@Autowired
	private IUserDao dao;

	public User readByNomAndPrenom(String nom, String prenom) {
		try {
			return dao.findByNomAndPrenom(nom, prenom);
		} catch (Exception e) {
			return null;
		}
	}

	public User login(String email, String pwd) {
		try {
			return dao.findByEmailAndPwd(email, pwd);
		} catch (Exception e) {
			return null;
		}
	}

	public User register(User user) {
		if (user.getId() == null || user.getId() == 0) {
			System.out.println("user cree");
			return dao.save(user);
		} else {
			System.out.println("user non cree");
			return null;
		}
	}

}
