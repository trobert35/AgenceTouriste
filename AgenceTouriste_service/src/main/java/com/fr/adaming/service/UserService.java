package com.fr.adaming.service;

import java.util.List;

import org.apache.log4j.Logger;
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
	
	private Logger log = Logger.getLogger(UserService.class);

	public User readByNomAndPrenom(String nom, String prenom) {
		try {
			User u = dao.findByNomAndPrenom(nom, prenom);
			
			return u;
		} catch (Exception e) {
			log.info("Lecture du User de nom "+nom+" et prenom "+prenom+" FAILED");
			return null;
		}
	}

	public User login(String email, String pwd) {
		try {
			User u = dao.findByEmailAndPwd(email, pwd);
			log.info("Login du User de mail "+email+" et PWD "+pwd+" SUCCESS");
			return u;
		} catch (Exception e) {
			log.warn("Login du User de mail "+email+" et PWD "+pwd+" SUCCESS");
			return null;
		}
	}

	public User register(User user) {
		if (user.getId() == null || user.getId() == 0 || !dao.existsById(user.getId())) {
			System.out.println("User cree");
			log.info("Enregistrement du User SUCCESS");
			return dao.save(user);
		} else {
			System.out.println("User non cree car id null, id = 0 ou id deja existant");
			log.warn("Enregistrement du User FAILED");
			return null;
		}
	}

	public User create(User user) {
		if (user.getId() == null || user.getId() == 0 || !dao.existsById(user.getId())) {
			System.out.println("User cree");
			log.info("Test LOG");
			log.info("Enregistrement du User SUCCESS");
			System.out.println("DEEEEEBUG");
			return dao.save(user);
		} else {
			System.out.println("User non cree car id null, id = 0 ou id deja existant");
			log.warn("Enregistrement du User FAILED");
			return null;
		}
	}

	public User update(User user) {
		if (user.getId() != null && user.getId() != 0 && dao.existsById(user.getId())) {
			System.out.println("User modifie");
			log.warn("Modification du User SUCCESS");
			return dao.save(user);
		} else {
			System.out.println("User non modifie");
			log.warn("Modification du User FAILED");
			return null;
		}
	}

	public User readById(Long id) {
		User u = dao.findById(id).get();
		if(u == null) {
			log.warn("Lecture du User avec l'id "+id+" FAILED");
		}else {
			log.info("Lecture du User avec l'id "+id+" SUCCESS");
		}
		return u;
	}

	public List<User> readAll() {
		List<User> lili = dao.findAll();
		if(lili.isEmpty()) {
			log.warn("Lecture de la liste des Utilisateur FAILED");
		}else {
			log.info("Lecture de la liste des Utilisateur SUCCESS");
		}
		return lili;
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "User supprime";
	}

	public boolean bookPrestation(User user, Prestation prestation) {

		if (!(user.getId() != null && user.getId() != 0 && dao.existsById(user.getId()))) {
			log.warn("RESERVATION pour l'Utilisateur FAILED > User n'existe pas");
			return false;
		} else if (!(prestation.getId() != null && prestation.getId() != 0 && daoP.existsById(prestation.getId()))) {
			log.warn("RESERVATION pour l'Utilisateur FAILED > Prestation n'existe pas");
			return false;
		} else {
			List<User> midUserList = prestation.getUsers();
			midUserList.add(user);
			prestation.setUsers(midUserList);
			log.info("RESERVATION pour l'Utilisateur "+user.getNom()+" et la Prestation "+prestation.getNom()+" SUCCESS");
			return true;
		}

	}

}
