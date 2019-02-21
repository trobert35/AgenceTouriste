package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;

/**
 * @author Thomas S
 * @author Maxime
 * @author Thomas R
 *
 */
@Service
public class AdminService implements IAdminService {

	@Autowired
	private IUserDao daoU;

	private Logger log = Logger.getLogger(AdminService.class);

	// Methodes CRUD Admin

	/**
	 * Insere un objet Admin dans la database si admin y est inexistant
	 * 
	 * @param admin prend une instance de l objet Admin en param, ne doit pas etre
	 *              null
	 * @return un objet Admin si l id d'admin est null, s il est egal a zero ou si
	 *         admin n existe pas dans la database, null sinon
	 * @throws NullPointerException si admin est null
	 */
	public Admin createAdmin(Admin admin) {
		if (admin.getId() == null || admin.getId() == 0 || !daoU.existsById(admin.getId())) {
			log.info("Admin cree");
			return daoU.save(admin);
		} else {
			log.warn("Attention l'ID de l'admin est null, zero ou existant");
			return null;
		}
	}

	/**
	 * Modifie un objet Admin existant dans la database si admin y existe
	 * 
	 * @param admin prend une instance de l objet Admin en param, ne doit pas etre
	 *              null
	 * @return un objet Admin si l id d'admin n est pas null, s il n est pas egal a
	 *         zero ou si admin existe dans la database, null sinon
	 * @throws NullPointerException si admin est null
	 */
	public Admin updateAdmin(Admin admin) {
		if (admin.getId() != null && admin.getId() != 0 && daoU.existsById(admin.getId())) {
			log.info("Admin modifie");
			return daoU.save(admin);
		} else {
			log.warn("Admin non existant, modification impossible");
			return null;
		}
	}

	/**
	 * Ressort un objet User de la database si user y existe
	 * 
	 * @param id (Long) id de user, ne doit pas etre null
	 * @return un objet User si user est existant dans la database, null sinon
	 * @throws InvalidDataAccessApiUsageException si id est null
	 */
	public User readAdminById(Long id) { // on considere un admin comme etant un user, car classe heritee de user
		Optional<User> optAdm = daoU.findById(id);
		if (!optAdm.isPresent()) {
			log.warn("L'Admin avec l'id " + id + " n'existe pas");
			return null;
		} else {
			log.info("Recherche de l'admin avec l'id " + id + " SUCCESS");
			return optAdm.get();
		}
	}

	/**
	 * Ressort un objet User (Admin) de la database s'il existe
	 * 
	 * @param email email du User
	 * @param pwd   pwd du User
	 * @return Null si l'email et le pwd n'existe pas, Admin s'ils existent
	 */
	public User readAdminByEmailAndPwd(String email, String pwd) {
		try {
			Admin a = (Admin) daoU.findByEmailAndPwd(email, pwd);
			log.info("Recherche de l'admin avec l'email " + email + " SUCCESS");
			return a;
		} catch (Exception e) {
			log.warn("L'Admin avec l'email " + email + " n'existe pas");
			return null;
		}
	}

	/**
	 * Ressort la liste de tous les User de la database
	 * 
	 * @return une List de User
	 */
	public List<User> readAllAdmin() {
		List<User> lili = daoU.findAll();
		if (lili.isEmpty()) {
			log.warn("La liste des Admin est vide");
		} else {
			log.info("La liste des Admin existe");
		}
		return lili;
	}

	/**
	 * Supprime un objet Admin de la database en utilisant son id
	 * 
	 * @param id (Long) id de l'admin a supprimer, ne doit pas etre null
	 * @return String
	 * @throws InvalidDataAccessApiUsageException si id est null
	 * @throws EmptyResultDataAccessException     si admin est inexistant dans la
	 *                                            database
	 */
	public String deleteAdminById(Long id) {
		daoU.deleteById(id);
		return "Admin supprime";
	}
}
