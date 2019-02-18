package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IPrestationDao;
import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.User;

@Service
public class UserService implements IUserService<User> {
	
	/**
	 * @author Thomas S
	 * @author Maxime
	 */

	@Autowired
	private IUserDao dao;
	
	@Autowired
	private IPrestationDao daoP;

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
		if (user.getId() == null || user.getId() == 0 || !dao.existsById(user.getId())) {
			System.out.println("User cree");
			return dao.save(user);
		} else {
			System.out.println("User non cree car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public User create(User user) {
		if (user.getId() == null || user.getId() == 0 || !dao.existsById(user.getId())) {
			System.out.println("User cree");
			return dao.save(user);
		} else {
			System.out.println("User non cree car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public User update(User user) {
		if (user.getId() != null && user.getId() != 0 && dao.existsById(user.getId())) {
			System.out.println("User modifie");
			return dao.save(user);
		} else {
			System.out.println("User non modifie");
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
		return "User supprime";
	}

	public boolean bookPrestation(User user, Prestation prestation) {

		if (!(user.getId() != null && user.getId() != 0 && dao.existsById(user.getId()))) {
			return false;
		} else if (!(prestation.getId() != null && prestation.getId() != 0 && daoP.existsById(prestation.getId()))) {
			return false;
		} else {
			List<User> midUserList = prestation.getUserS();
			midUserList.add(user);
			prestation.setUserS(midUserList);
			return true;
		}

	}

}
