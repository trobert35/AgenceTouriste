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

	/**
	 * @param String nom
	 * @param String prenom
	 * @return un user selon son nom et son prenom ou null si le nom ou le prenom
	 *         est incorrect
	 */
	public User readByNomAndPrenom(String nom, String prenom) {
		try {
			User u = dao.findByNomAndPrenom(nom, prenom);
			log.info("Lecture du User de nom " + nom + " et prenom " + prenom + " SUCCESS");
			return u;
		} catch (Exception e) {
			log.warn("Lecture du User de nom " + nom + " et prenom " + prenom + " FAILED");
			return null;
		}
	}

	/**
	 * @param String email
	 * @param String pwd
	 * @return un user selon son email et son pwd ou null si l'email ou le pwd est
	 *         incorrect
	 */
	public User login(String email, String pwd) {
		try {
			User u = dao.findByEmailAndPwd(email, pwd);
			log.info("Login du User de mail " + email + " et PWD " + pwd + " SUCCESS");
			return u;
		} catch (Exception e) {
			log.warn("Login du User de mail " + email + " et PWD " + pwd + " SUCCESS");
			return null;
		}
	}

	/**
	 * @param user
	 * @return un user si son id est null, égal à 0 ou s'il n'existe pas dans la BD;
	 *         retourne null dans le cas contraire
	 */
	public User register(User user) {
		if (user.getId() == null || user.getId() == 0 || !dao.existsById(user.getId())) {
			log.info("Enregistrement du User SUCCESS");
			return dao.save(user);
		} else {
			log.warn("Enregistrement du User FAILED");
			return null;
		}
	}

	/**
	 * @param user
	 * @return un user si son id est null, égal à 0 ou s'il n'existe pas dans la BD;
	 *         retourne null dans le cas contraire
	 */
	public User create(User user) {
		if (user.getId() == null || user.getId() == 0 || !dao.existsById(user.getId())) {
			log.info("Enregistrement du User SUCCESS");
			return dao.save(user);
		} else {
			log.warn("Enregistrement du User FAILED");
			return null;
		}
	}

	/**
	 * @param user
	 * @return un user si son id est différent de null, n'est pas égal à 0 ou s'il
	 *         existe dans la BD; retourne null dans le cas contraire
	 */
	public User update(User user) {
		if (user.getId() != null && user.getId() != 0 && dao.existsById(user.getId())) {
			log.info("Modification du User SUCCESS");
			return dao.save(user);
		} else {
			log.warn("Modification du User FAILED");
			return null;
		}
	}

	/**
	 * @param id
	 * @return un user s'il n'est pas null; renvoie un warning dans le cas contraire
	 */
	public User readById(Long id) {
		User u = dao.findById(id).get();
		if (u == null) {
			log.warn("Lecture du User avec l'id " + id + " FAILED");
		} else {
			log.info("Lecture du User avec l'id " + id + " SUCCESS");
		}
		return u;
	}

	/**
	 * 
	 * @return une liste de user renvoie un warning si cette liste est vide
	 */
	public List<User> readAll() {
		List<User> lili = dao.findAll();
		if (lili.isEmpty()) {
			log.warn("Lecture de la liste des Utilisateur FAILED");
		} else {
			log.info("Lecture de la liste des Utilisateur SUCCESS");
		}
		return lili;
	}

	/**
	 * @param id
	 * @return un string pour signaler que l'user a bien été supprimé
	 */
	public String deleteById(Long id) {
		dao.deleteById(id);
		return "User supprime";
	}

	/**
	 * @param user
	 * @param prestation
	 * @return false si la prestation et/ou le user n'existent pas dans la BD;
	 *         renvoie true si l'user et la prestation ont bien été associés l'un à
	 *         l'autre
	 */
	public boolean bookPrestation(User user, Prestation prestation) {

		if ((user.getId() != null || user.getId() != 0) && !dao.existsById(user.getId())) {
			log.warn("RESERVATION pour l'Utilisateur FAILED > User n'existe pas");
			return false;
		} else if ((prestation.getId() != null || prestation.getId() != 0) && !daoP.existsById(prestation.getId())) {
			log.warn("RESERVATION pour l'Utilisateur FAILED > Prestation n'existe pas");
			return false;
		} else {
			List<User> midUserList = prestation.getUsers();
			midUserList.add(user);
			prestation.setUsers(midUserList);
			prestation.setNbPersonnes(midUserList.size());
			log.info("RESERVATION pour l'Utilisateur " + user.getNom() + " et la Prestation " + prestation.getNom()
					+ " SUCCESS");
			return true;
		}

	}

}
