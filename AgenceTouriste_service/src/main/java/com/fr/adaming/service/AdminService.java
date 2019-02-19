package com.fr.adaming.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IActiviteDao;
import com.fr.adaming.dao.ILogementDao;
import com.fr.adaming.dao.IPrestationDao;
import com.fr.adaming.dao.ITransportDao;
import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.Activite;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.Transport;
import com.fr.adaming.entity.User;
import com.fr.adaming.enumeration.typeActEnum;
import com.fr.adaming.enumeration.typeLogEnum;
import com.fr.adaming.enumeration.typeTransEnum;

@Service
public class AdminService
		implements IAdminService, IProduitService<Prestation>, ITransportService, IActiviteService, ILogementService {
	/**
	 * @author Thomas S
	 * @author Maxime
	 * @author Thomas R
	 */

	@Autowired
	private IUserDao daoU;
	@Autowired
	private ILogementDao daoL;
	@Autowired
	private IPrestationDao daoP;
	@Autowired
	private ITransportDao daoT;
	@Autowired
	private IActiviteDao daoA;

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
			System.out.println("Admin cree");
			log.info("Admin cree");
			return daoU.save(admin);
		} else {
			log.warn("Attention l'ID de l'admin est null, zero ou existant");
			System.out.println("Admin non cree car id null, id = 0 ou id deja existant");
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
			System.out.println("Admin modifie");
			log.info("Admin modifie");
			return daoU.save(admin);
		} else {
			log.warn("Admin non existant, modification impossible");
			System.out.println("Admin non modifie");
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
		User u = daoU.findById(id).get();
		if (u == null) {
			log.warn("L'Admin avec l'id " + id + " n'existe pas");
			return null;
		} else {
			log.info("Recherche de l'admin avec l'id " + id + " SUCCESS");
			return u;
		}
	}

	
	/**
	 * Ressort un objet User (Admin) de la database si il existe
	 * @param email
	 * @param pwd
	 * @return Null si l'email et le pwd n'existe pas, Admin si ils existent
	 */
	public User readAdminByEmailAndPwd(String email, String pwd) {
		try {
			Admin a =  (Admin) daoU.findByEmailAndPwd(email, pwd);
			log.info("Recherche de l'admin avec l'email " + email + " SUCCESS");
			return a;
		}catch(Exception e) {
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

	// Methodes CRUD Logement + readLogementByPrestaLog + readByVille +
	// readByTypeLog
	/**
	 * Insere un objet Logement dans la database si logement y est inexistant
	 * 
	 * @param logement prend une instance de l objet Logement en param
	 * @return un objet Logement si l id de logement est null, est egal a zero ou si
	 *         logement n existe pas dans la database, null sinon
	 * @throws NullPointerException si logement est null
	 */
	public Logement createLogement(Logement logement) {
		if (logement.getId() == null || logement.getId() == 0 || !daoL.existsById(logement.getId())) {
			System.out.println("Logement cree");
			log.info("Logement cree SUCESS");
			return daoL.save(logement);
		} else {
			log.warn("Logement non cree FAILED");
			System.out.println("Logement non crée car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	/**
	 * Modifie un objet Logement dans la database si logement y existe
	 * 
	 * @param logement prend une instance de l objet Logement en param, ne doit pas
	 *                 etre null
	 * @return un objet Logement si l id de logement n est pas null, s il n est pas
	 *         egal a zero ou si logement existe pas dans la database, null sinon
	 * @throws NullPointerException si logement est null
	 */
	public Logement updateLogement(Logement logement) {
		if (logement.getId() != null && logement.getId() != 0 && daoL.existsById(logement.getId())) {
			System.out.println("logement modifie");
			log.info("Logement modifie SUCESS");
			return daoL.save(logement);
		} else {
			log.warn("Logement non modifie FAILED");
			System.out.println("logement non modifie");
			return null;
		}
	}

	/**
	 * Ressort la liste de tous les Logement de la database
	 * 
	 * @return une List de Logement
	 */
	public List<Logement> readAllLogement() {
		List<Logement> lili = daoL.findAll();
		if (lili.isEmpty()) {
			log.warn("La liste des Logement est vide, FAILED");
		} else {
			log.info("Lecture de la liste des Logement SUCCESS");
		}
		return lili;
	}

	/**
	 * Ressort un objet Logement de la database en utilisant son id si logement y
	 * existe
	 * 
	 * @param id (Long) id de logement, ne doit pas etre null
	 * @return un objet Logement s il est existant dans la database, null sinon
	 * @throws InvalidDataAccessApiUsageException si id est null
	 */
	public Logement readLogementById(Long id) {
		Logement l = daoL.findById(id).get();
		if (l == null) {
			log.warn("Lecture du Logement avec l'id " + id + " FAILED");
		} else {
			log.info("Lecture du Logement avec l'id " + id + " SUCCESS");
		}
		return l;
	}

	/**
	 * Supprime un objet Logement de la database en utilisant son id
	 * 
	 * @param id (Long) id du logement a supprimer, ne doit pas etre null
	 * @return String
	 * @throws InvalidDataAccessApiUsageException si id est null
	 * @throws EmptyResultDataAccessException     si logement est inexistant dans
	 *                                            la database
	 */
	public String deleteLogementById(Long id) {
		daoL.deleteById(id);
		return "logement supprime";
	}

	/**
	 * Ressort un objet Logement de la database en utilisant son prestaLog si
	 * logement y existe @param prestaLog (String) nom du prestataire de
	 * logement @return une List de Logement @throws
	 */
	public List<Logement> readLogementByPrestaLog(String prestaLog) {
		List<Logement> lili = daoL.findByPrestaLog(prestaLog);
		if (lili.isEmpty()) {
			log.warn("Lecture de la liste des Logement de " + prestaLog + " FAILED");
		} else {
			log.warn("Lecture de la liste des Logement de " + prestaLog + " SUCCESS");

		}
		return lili;
	}

	/**
	 * Ressort un objet Logement de la database en utilisant sa ville si logement y
	 * existe @param ville (String) nom de la ville de logement @return une List de
	 * Logement @throws
	 */
	public List<Logement> readByVille(String ville) {
		return daoL.findByVille(ville);
	}

	/**
	 * Ressort un objet Logement de la database en utilisant son typeLog si logement
	 * y existe @param typeLog (Enum) type de logement @return une List de
	 * Logement @throws
	 */
	public List<Logement> readByTypeLog(typeLogEnum typeLog) {
		return daoL.findByTypeLog(typeLog);
	}

	/**
	 * Ressort un objet Logement de la database en utilisant son prix si logement y
	 * existe @param prix (Double) prix de logement @return une List de
	 * Logement @throws
	 */
	public List<Logement> readByPrixLogement(Double prix) {
		return daoL.findByPrix(prix);
	}

	// Methodes CRUD Prestation + readByDebutPrestaAndFinPresta +
	// readByVilleDepartArriveeAndDestination
	/**
	 * Insere un objet Prestation dans la database s il est inexistant
	 * 
	 * @param obj prend une instance de l objet Prestation en param
	 * @return un objet Prestation si l id de prestation est null, s il est egal a
	 *         zero ou si prestation n existe pas dans la database, null sinon
	 * @throws NullPointerException si prestation est null
	 */
	public Prestation createPrestation(Prestation obj) {
		if (obj.getId() == null || obj.getId() == 0 || !daoP.existsById(obj.getId())) {
			System.out.println("Prestation creee");
			log.info("Creation de Prestation SUCCESS");
			return daoP.save(obj);

		} else {
			log.warn("Creation de Prestation Failed");
			System.out.println("Prestation non creee car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	/**
	 * Modifie un objet Prestation dans la database si prestation y existe
	 * 
	 * @param obj prend une instance de l objet Prestation en param, ne doit pas
	 *            etre null
	 * @return un objet Prestation si l id de prestation n est pas null, s il n est
	 *         pas egal a zero ou si prestation existe dans la database, null sinon
	 * @throws NullPointerException si prestation est null
	 */
	public Prestation updatePrestation(Prestation obj) {
		if (obj.getId() != null && obj.getId() != 0 && daoP.existsById(obj.getId())) {
			System.out.println("Prestation modifiee");
			log.info("Modification de la Prestation SUCCESS");
			return daoP.save(obj);
		} else {
			System.out.println("Prestation non modifiee");
			log.warn("Modification de la Prestation FAILED");
			return null;
		}
	}

	/**
	 * Ressort un objet Prestation de la database en utilisant son id si prestation
	 * y existe
	 * 
	 * @param id (Long) id de prestation, ne doit pas etre null
	 * @return un objet Prestation s il est existant dans la database, null sinon
	 * @throws InvalidDataAccessApiUsageException si id est null
	 */
	public Prestation readPrestationById(Long id) {
		Prestation p = daoP.findById(id).get();
		if (p == null) {
			log.warn("Lecture de la Prestation avec l'id " + id + " FAILED");
		} else {
			log.info("Lecture de la Prestation avec l'id " + id + " SUCCESS");
		}
		return p;
	}

	/**
	 * Ressort la liste de toutes les Prestation de la database
	 * 
	 * @return une List de Prestation
	 */
	public List<Prestation> readAllPrestation() {
		List<Prestation> lili = daoP.findAll();
		if (lili.isEmpty()) {
			log.warn("Lecture de la liste de Prestation FAILED");
		} else {
			log.info("Lecture de la liste de Prestation SUCCESS");
		}
		return lili;
	}

	/**
	 * Supprime un objet Prestation de la database en utilisant son id
	 * 
	 * @param id (Long) id de la prestation a supprimer, ne doit pas etre null
	 * @return un String
	 * @throws InvalidDataAccessApiUsageException si id est null
	 * @throws EmptyResultDataAccessException     si prestation est inexistant
	 *                                            dans la database
	 */
	public String deletePrestationById(Long id) {
		if (id != null && daoP.findById(id).isPresent()) {
			daoP.deleteById(id);
			return "Prestation supprimee";
		} else {
			return null;
		}
	}

	/**
	 * Ressort un objet Prestation de la database, en utilisant son debutPresta et
	 * sa finPresta, si prestation y existe @param debutPresta (Date) date de debut
	 * de prestation @param finPresta (Date) date de fin de prestation @return une
	 * List de Prestation @throws
	 */
	public List<Prestation> readByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta) {
		return daoP.findByDebutPrestaAndFinPresta(debutPresta, finPresta);
	}

	/**
	 * Ressort un objet Prestation de la database, en utilisant sa
	 * villeDepartArrivee et sa destination, si prestation y existe @param
	 * villeDepartArrivee (String) ville de depart et de retour de prestation @param
	 * destination (String) destination de prestation @return une List de
	 * Prestation @throws
	 */
	public List<Prestation> readByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination) {
		return daoP.findByVilleDepartArriveeAndDestination(villeDepartArrivee, destination);
	}

	// Methodes CRUD Transport + readTransportByPrestaTrans + readByPrix +
	// readByTypeTrans
	/**
	 * Insere un objet Transport dans la database si transport y est inexistant
	 * 
	 * @param transport prend une instance de l objet Transport en param
	 * @return un objet Transport si l id de transport est null, est egal a zero ou
	 *         si transport n existe pas dans la database, null sinon
	 * @throws NullPointerException si transport est null
	 */
	public Transport createTransport(Transport transport) {
		if (transport.getId() == null || transport.getId() == 0 || !daoT.existsById(transport.getId())) {
			System.out.println("transport cree");
			log.info("Creation du Transport SUCCESS");
			return daoT.save(transport);
		} else {
			System.out.println("Transport non crée car existe déjà pour cet id");
			log.warn("Creation du Transport FAILED");
			return null;
		}
	}

	/**
	 * Modifie un objet Transport existant dans la database
	 * 
	 * @param transport prend une instance de l objet Transport en param, ne doit
	 *                  pas etre null
	 * @return un objet Transport si l id de transport n est pas null, s il n est
	 *         pas egal a zero ou si transport existe dans la database, null sinon
	 * @throws NullPointerException si transport est null
	 */
	public Transport updateTransport(Transport transport) {
		if (transport.getId() != null && transport.getId() != 0 && daoT.existsById(transport.getId())) {
			System.out.println("Transport modifié");
			log.info("Modification du Transport SUCCESS");
			return daoT.save(transport);
		} else {
			log.warn("Modification du transport FAILED");
			return null;
		}
	}

	/**
	 * Ressort la liste de tous les Transport de la database
	 * 
	 * @return une List de Transport
	 */
	public List<Transport> readAllTransport() {
		List<Transport> lili = daoT.findAll();
		if (lili.isEmpty()) {
			log.warn("Lecture de la liste de Transport FAILED");
		} else {
			log.info("Lecture de la liste de Transport SUCCESS");
		}
		return lili;
	}

	/**
	 * Ressort un objet Transport de la database, en utilisant son id, si transport
	 * y existe
	 * 
	 * @param id (Long) id de transport, ne doit pas etre null
	 * @return un objet Transport s il est existant dans la database, null sinon
	 * @throws InvalidDataAccessApiUsageException si id est null
	 */
	public Transport readTransportById(Long id) {
		Transport t = daoT.findById(id).get();
		if (t == null) {
			log.warn("Lecture du Transport avec l'id " + id + " FAILED");
		} else {
			log.info("Lecture du Transport avec l'id " + id + " SUCCESS");
		}
		return t;
	}

	/**
	 * Supprime un objet Transport de la database en utilisant son id
	 * 
	 * @param id (Long) id du transport a supprimer, ne doit pas etre null
	 * @return String
	 * @throws InvalidDataAccessApiUsageException si id est null
	 * @throws EmptyResultDataAccessException     si transport est inexistant dans
	 *                                            la database
	 */
	public String deleteTransportById(Long id) {
		daoT.deleteById(id);
		return "Transport supprimé";
	}

	/**
	 * Ressort un objet Transport de la database, en utilisant son prestaTrans, si
	 * transport y existe @param prestaTrans (String) nom du prestataire de
	 * transport @return une List de Transport @throws
	 */
	public List<Transport> readTransportByPrestaTrans(String prestaTrans) {
		List<Transport> lili = daoT.findByPrestaTrans(prestaTrans);
		if (lili.isEmpty()) {
			log.warn("Lecture de la liste des Transport concernant " + prestaTrans + " FAILED");
		} else {
			log.info("Lecture de la liste des Transport concernant " + prestaTrans + " FAILED");

		}
		return lili;
	}

	/**
	 * Ressort un objet Transport de la database, en utilisant son prix, si
	 * transport y existe @param prix (Double) prix de transport @return une List de
	 * Transport @throws
	 */
	public List<Transport> readByPrix(Double prix) {
		return daoT.findByPrix(prix);
	}

	/**
	 * Ressort un objet Transport de la database, en utilisant son type, si
	 * transport y existe @param type (Enum) type de transport @return une List de
	 * Transport @throws
	 */
	public List<Transport> readByTypeTrans(typeTransEnum type) {
		return daoT.findByTypeTrans(type);
	}

	// Methodes CRUD Activite + readActiviteByPrestaAct + readActiviteByPrix +
	// readActiviteByTypeAct
	/**
	 * Insere un objet Activite dans la database si activite y est inexistant
	 * 
	 * @param activite prend une instance de l objet Activite en param
	 * @return un objet Activite si l id d'activite est null, est egal a zero ou si
	 *         activite n existe pas dans la database, null sinon
	 * @throws NullPointerException si activite est null
	 */
	public Activite createActivite(Activite activite) {
		if (activite.getId() == null || activite.getId() == 0 || !daoA.existsById(activite.getId())) {
			System.out.println("Activite creee");
			log.info("Creation de l'Activite SUCCESS");
			return daoA.save(activite);
		} else {
			log.warn("Creation de l'Activite FAILED");
			System.out.println("Activite non creee car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	/**
	 * Modifie un objet Activite existant dans la database
	 * 
	 * @param activite prend une instance de l objet Activite en param, ne doit pas
	 *                 etre null
	 * @return un objet Activite si l id d'activite n est pas null, s il n est pas
	 *         egal a zero ou si activite existe dans la database, null sinon
	 * @throws NullPointerException si activite est null
	 */
	public Activite updateActivite(Activite activite) {
		if (activite.getId() != null && activite.getId() != 0 && daoA.existsById(activite.getId())) {
			System.out.println("Activite modifiee");
			log.info("Modification de l'Activite SUCCESS");
			return daoA.save(activite);
		} else {
			log.warn("Modification de l'Activite SUCCESS");
			System.out.println("Activite non modifiee");
			return null;

		}
	}

	/**
	 * Ressort la liste de tous les Activite de la database
	 * 
	 * @return une List de Activite
	 */
	public List<Activite> readAllActivite() {
		List<Activite> lili = daoA.findAll();
		if (lili.isEmpty()) {
			log.warn("Lecture des Activite FAILED");
		} else {
			log.info("Lecture des Activite FAILED");

		}
		return lili;
	}

	/**
	 * Ressort un objet Activite de la database, en utilisant son id, si activite y
	 * existe
	 * 
	 * @param id (Long) id d'activite, ne doit pas etre null
	 * @return un objet Activite s il est existant dans la database, null sinon
	 * @throws InvalidDataAccessApiUsageException si id est null
	 */
	public Activite readActiviteById(Long id) {
		Activite a = daoA.findById(id).get();
		if (a == null) {
			log.warn("Lecture de l'Activite avec l'id " + id + " FAILED");
		} else {
			log.info("Lecture de l'Activite avec l'id " + id + " SUCCESS");
		}
		return a;
	}

	/**
	 * Supprime un objet Activite de la database en utilisant son id
	 * 
	 * @param id (Long) id de l'activite a supprimer, ne doit pas etre null
	 * @return String
	 * @throws InvalidDataAccessApiUsageException si id est null
	 * @throws EmptyResultDataAccessException     si activite est inexistant dans
	 *                                            la database
	 */
	public String deleteActiviteById(Long id) {
		daoA.deleteById(id);
		return "Activite supprimee";
	}

	/**
	 * Ressort un objet Activite de la database, en utilisant son nomPrestaAct, si
	 * activite y existe
	 * @param nomPrestaAct (String) nom du prestataire de
	 * activite
	 * @return une List de Activite
	 * @throws
	 */
	public List<Activite> readActiviteByNomPrestaAct(String nomPrestaAct) {
		List<Activite> lili = daoA.findByNomPrestaAct(nomPrestaAct);
		if (lili.isEmpty()) {
			log.warn("Lecture de l'activite par la Prestation " + nomPrestaAct + " FAILED");
		} else {
			log.info("Lecture de l'activite par la Prestation " + nomPrestaAct + " SUCCESS");

		}
		return lili;
	}

	/**
	 * Ressort un objet Activite de la database, en utilisant son prix, si activite
	 * y existe @param prix (Double) prix de activite @return une List de
	 * Activite
	 * @throws
	 */
	public List<Activite> readActiviteByPrix(Double prix) {
		return daoA.findByPrix(prix);
	}

	/**
	 * Ressort un objet Activite de la database, en utilisant son typeAct, si
	 * activite y existe @param typeAct (Enum) type de activite @return une List de
	 * Activite
	 * @throws
	 */
	public List<Activite> readActiviteByTypeAct(typeActEnum typeAct) {
		return daoA.findByTypeAct(typeAct);
	}

}
