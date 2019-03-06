package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IPrestationDao;
import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.User;

/**
 * @author Thomas S
 * @author Maxime
 *
 */
@Service
public class UserService implements IUserService<User> {

	@Autowired
	private IUserDao dao;

	@Autowired
	private IPrestationDao daoP;

	private Logger log = Logger.getLogger(UserService.class);

	private static final String SUCCESS = " SUCCESS";
	private static final String FAILED = " FAILED";

	/**
	 * @param nom    nom du User
	 * @param prenom prenom du User
	 * @return un objet de la classe User
	 */
	public User readByNomAndPrenom(String nom, String prenom) {
		try {
			User u = dao.findByNomAndPrenom(nom, prenom);
			log.info("Lecture du User de nom " + nom + " et prenom " + prenom + SUCCESS);
			return u;
		} catch (Exception e) {
			log.warn("Lecture du User de nom " + nom + " et prenom " + prenom + FAILED);
			return null;
		}
	}

	/**
	 * @param email email du User
	 * @param pwd   pwd du User
	 * @return un user selon son email et son pwd ou null si l'email ou le pwd est
	 *         incorrect
	 */
	public User login(String email, String pwd) {
		try {
			User u = dao.findByEmailAndPwd(email, pwd);
			log.info("Login du User de mail " + email + " et PWD " + pwd + SUCCESS);
			return u;
		} catch (Exception e) {
			log.warn("Login du User de mail " + email + " et PWD " + pwd + FAILED);
			return null;
		}
	}

	/**
	 * @param idProvider identifiant du user sur la base de donnee du reseau social
	 * @param token 	token du user envoye par le reseau social
	 * @return User si le login ou le register fonctionnent, null sinon 
	 */
	public User socialLogin(User user) {
		try {
			User u = dao.findByIdProviderAndToken(user.getIdProvider(), user.getToken());
			if (dao.existsById(u.getId())) {
				log.info("socialLogin (LOGIN) : " + u.getPrenom() + " " + u.getNom()
						+ " est deja enregistre dans la base de donnee!!");
				return u;
			} else {
				dao.save(u);
				log.info("socialLogin (REGISTER) : " + u.getPrenom() + " " + u.getNom()
						+ " a ete enregistre dans la base de donnee!!");
				return u;
			}
		} catch (Exception e) {
			log.warn("socialLogin (ERREUR) : " + FAILED);
			return null;
		}

	}
	
	/**
	 * @param user objet de classe User
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
	 * @param user objet de classe User
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
	 * @param user objet de classe User
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
	 * @param id id du User
	 * @return un user s'il n'est pas null, renvoie un warning dans le cas contraire
	 */
	public User readById(Long id) {
		Optional<User> optUser = dao.findById(id);
		if (!optUser.isPresent()) {
			log.warn("Lecture du User avec l'id " + id + FAILED);
			return null;
		} else {
			log.info("Lecture du User avec l'id " + id + SUCCESS);
			return optUser.get();
		}
	}

	/**
	 * 
	 * @return une List de User, renvoie un warning si cette liste est vide
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
	 * @param id id du User
	 * @return String
	 */
	public String deleteById(Long id) {
		dao.deleteById(id);
		return "User supprime";
	}

	/**
	 * @param user       objet de classe User
	 * @param prestation objet de classe Prestation
	 * @return false si la prestation et/ou le user n'existent pas dans la BD;
	 *         renvoie true si l'user et la prestation ont bien été associés l'un à
	 *         l'autre
	 */
	public boolean bookPrestation(User user, Prestation prestation) {

		if ((user.getId() == null || user.getId() == 0) || !dao.existsById(user.getId())) {
			log.warn("RESERVATION pour l'Utilisateur FAILED > User n'existe pas");
			return false;
		} else if ((prestation.getId() == null || prestation.getId() == 0) || !daoP.existsById(prestation.getId())) {
			log.warn("RESERVATION pour l'Utilisateur FAILED > Prestation n'existe pas");
			return false;
		} else {
			List<User> midUserList = prestation.getUsers();
			midUserList.add(user);
			prestation.setUsers(midUserList);
			prestation.setNbPersonnes(midUserList.size());
			log.info("RESERVATION pour l'Utilisateur " + user.getNom() + " et la Prestation " + prestation.getNom()
					+ SUCCESS);
			return true;
		}

	}

	/**
	 * @param Long id  identifiant d'un user
	 * @return true si user est un admin, false sinon
	 */
	public String isAdmin(Long id) {

		return dao.isAdmin(id);
		
	}

}
