package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;

@Service
public class AdminService implements IAdminService{
	/**
	 * @author Thomas S
	 * @author Maxime
	 */

	@Autowired
	private IUserDao dao;

	public Admin create(Admin admin) {
		if (admin.getId() == null || admin.getId() == 0 || !dao.existsById(admin.getId())) {
			System.out.println("Admin cree");
			return dao.save(admin);
		} else {
			System.out.println("Admin non cree car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public Admin update(Admin admin) {
		if (admin.getId() != null && admin.getId() != 0 && dao.existsById(admin.getId())) {
			System.out.println("Admin modifie");
			return dao.save(admin);
		} else {
			System.out.println("Admin non modifie");
			return null;
		}
	}

	public User readById(Long id) { //on considere un admin comme etant un user, car classe heritee de user
		return dao.findById(id).get();
	}

	public List<User> readAll() {
		return dao.findAll();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "Admin supprime";
	}

}
