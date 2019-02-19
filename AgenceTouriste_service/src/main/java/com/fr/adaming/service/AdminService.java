package com.fr.adaming.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * @author Thomas R (Logger)
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
	 * @param admin prend une instance de l objet Admin en param
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

	public User readAdminById(Long id) { // on considere un admin comme etant un user, car classe heritee de user
		User u = daoU.findById(id).get();
		if(u == null) {
			log.warn("L'Admin avec l'id "+id+" n'existe pas");
			return u;
		}else {
			log.info("Recherche de l'admin avec l'id "+id+" SUCCESS");
			return u;
		}
	}

	public List<User> readAllAdmin() {
		List<User> lili = daoU.findAll();
		if(lili.isEmpty()) {
			log.warn("La liste des Admin est vide");
		}else {
			log.info("La liste des Admin existe");
		}
		return lili;
	}

	public String deleteAdminById(Long id) {
		daoU.deleteById(id);
		return "Admin supprime";
	}

	// Methodes CRUD Logement + readLogementByPrestaLog + readByVille + readByTypeLog
	/**
	 * @param logement 
	 * prend une instance de l objet Logement en param
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

	public List<Logement> readAllLogement() {
		List<Logement> lili = daoL.findAll();
		if(lili.isEmpty()) {
			log.warn("La liste des Logement est vide, FAILED");
		}else {
			log.info("Lecture de la liste des Logement SUCCESS");
		}
		return lili;
	}

	public Logement readLogementById(Long id) {
		Logement l = daoL.findById(id).get(); 
		if(l == null) {
			log.warn("Lecture du Logement avec l'id "+id+" FAILED");
		}else {
			log.info("Lecture du Logement avec l'id "+id+" SUCCESS");
		}
		return l;
	}

	public String deleteLogementById(Long id) {
		daoL.deleteById(id);
		return "logement supprime";
	}

	public List<Logement> readLogementByPrestaLog(String prestaLog) {
		List<Logement> lili = daoL.findByPrestaLog(prestaLog);
		if(lili.isEmpty()) {
			log.warn("Lecture de la liste des Logement de "+prestaLog+" FAILED");
		}else {
			log.warn("Lecture de la liste des Logement de "+prestaLog+" SUCCESS");

		}
		return lili;
	}

	public List<Logement> readByVille(String ville) {
		return daoL.findByVille(ville);
	}

	public List<Logement> readByTypeLog(typeLogEnum typeLog) {
		return daoL.findByTypeLog(typeLog);
	}

	public List<Logement> readByPrixLogement(Double prix) {
		return daoL.findByPrix(prix);
	}
	
	// Methodes CRUD Prestation + readByDebutPrestaAndFinPresta + readByVilleDepartArriveeAndDestination
	/**
	 * @param prestation 
	 * prend une instance de l objet Prestation en param
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

	public Prestation readPrestationById(Long id) {
		Prestation p = daoP.findById(id).get();
		if(p == null) {
			log.warn("Lecture de la Prestation avec l'id "+id+" FAILED");
		}else {
			log.info("Lecture de la Prestation avec l'id "+id+" SUCCESS");
		}
		return p;
	}

	public List<Prestation> readAllPrestation() {
		List<Prestation> lili = daoP.findAll();
		if(lili.isEmpty()) {
			log.warn("Lecture de la liste de Prestation FAILED");
		}else {
			log.info("Lecture de la liste de Prestation SUCCESS");
		}
		return lili;
	}

	public String deletePrestationById(Long id) {
		if(id != null && daoP.findById(id).isPresent()) {
			daoP.deleteById(id);
			return "Prestation supprimee";
		}else {
		return null;
		}
	}

	public List<Prestation> readByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta) {
		return daoP.findByDebutPrestaAndFinPresta(debutPresta, finPresta);
	}

	public List<Prestation> readByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination) {
		return daoP.findByVilleDepartArriveeAndDestination(villeDepartArrivee, destination);
	}

	
	// Methodes CRUD Transport + readTransportByPrestaTrans + readByPrix + readByTypeTrans
	/**
	 * @param transport 
	 * prend une instance de l objet Transport en param
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

	public List<Transport> readAllTransport() {
		List<Transport> lili = daoT.findAll();
		if(lili.isEmpty()) {
			log.warn("Lecture de la liste de Transport FAILED");
		}else {
			log.info("Lecture de la liste de Transport SUCCESS");
		}
		return lili;
	}

	public Transport readTransportById(Long id) {
		Transport t = daoT.findById(id).get();
		if(t == null) {
			log.warn("Lecture du Transport avec l'id "+id+" FAILED");
		}else {
			log.info("Lecture du Transport avec l'id "+id+" SUCCESS");
		}
		return t;
	}

	public String deleteTransportById(Long id) {
		daoT.deleteById(id);
		return "Transport supprimé";
	}

	public List<Transport> readTransportByPrestaTrans(String prestaTrans) {
		List<Transport> lili = daoT.findByPrestaTrans(prestaTrans);
		if(lili.isEmpty()) {
			log.warn("Lecture de la liste des Transport concernant "+prestaTrans+" FAILED");
		}else {
			log.info("Lecture de la liste des Transport concernant "+prestaTrans+" FAILED");

		}
		return lili;
	}

	public List<Transport> readByPrix(Double prix) {
		return daoT.findByPrix(prix);
	}

	public List<Transport> readByTypeTrans(typeTransEnum type) {
		return daoT.findByTypeTrans(type);
	}

	
	// Methodes CRUD Activite + readActiviteByPrestaAct + readActiviteByPrix + readActiviteByTypeAct
	/**
	 * @param activite 
	 * prend une instance de l objet Activite en param
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

	public List<Activite> readAllActivite() {
		List<Activite> lili = daoA.findAll();
		if(lili.isEmpty()) {
			log.warn("Lecture des Activite FAILED");
		}else {
			log.info("Lecture des Activite FAILED");

		}
		return lili;
	}

	public Activite readActiviteById(Long id) {
		Activite a = daoA.findById(id).get();
		if(a == null) {
			log.warn("Lecture de l'Activite avec l'id "+id+" FAILED");
		}else {
			log.info("Lecture de l'Activite avec l'id "+id+" SUCCESS");
		}
		return a;
	}

	public String deleteActiviteById(Long id) {
		daoA.deleteById(id);
		return "Activite supprimee";
	}

	public List<Activite> readActiviteByNomPrestaAct(String nomPrestaAct) {
		List<Activite> lili = daoA.findByNomPrestaAct(nomPrestaAct);
		if(lili.isEmpty()) {
			log.warn("Lecture de l'activite par la Prestation "+nomPrestaAct+" FAILED");
		}else {
			log.info("Lecture de l'activite par la Prestation "+nomPrestaAct+" SUCCESS");

		}
		return lili;
	}

	public List<Activite> readActiviteByPrix(Double prix) {
		return daoA.findByPrix(prix);
	}

	public List<Activite> readActiviteByTypeAct(typeActEnum typeAct) {
		return daoA.findByTypeAct(typeAct);
	}


}
