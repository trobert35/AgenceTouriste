package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.User;

@Service
public class AdminService {

	@Autowired
	private IUserDao dao;

	public User create(User user) {
		if (user.getId() == null || user.getId() == 0) {
			System.out.println("user cree");
			return dao.save(user);
		} else {
			System.out.println("user non cree");
			return null;
		}
	}

	public User update(User user) {
		if (user.getId() != null && user.getId() != 0 && dao.existsById(user.getId())) {
			System.out.println("user modifie");
			return dao.save(user);
		} else {
			System.out.println("user non modifie");
			return null;
		}
	}

	public User readById(Long id) {
		return dao.findById(id).get();
	}

	public List<User> readAll() {
		return dao.findAll();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "utilisateur supprime";
	}

}
