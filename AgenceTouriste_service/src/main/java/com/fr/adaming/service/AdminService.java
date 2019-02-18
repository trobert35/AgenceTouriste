package com.fr.adaming.service;

import java.util.Date;
import java.util.List;

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

	// Methodes CRUD Admin
	/**
	 * @param admin prend une instance de l objet Admin en param
	 */
	public Admin createAdmin(Admin admin) {
		if (admin.getId() == null || admin.getId() == 0 || !daoU.existsById(admin.getId())) {
			System.out.println("Admin cree");
			return daoU.save(admin);
		} else {
			System.out.println("Admin non cree car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public Admin updateAdmin(Admin admin) {
		if (admin.getId() != null && admin.getId() != 0 && daoU.existsById(admin.getId())) {
			System.out.println("Admin modifie");
			return daoU.save(admin);
		} else {
			System.out.println("Admin non modifie");
			return null;
		}
	}

	public User readAdminById(Long id) { // on considere un admin comme etant un user, car classe heritee de user
		return daoU.findById(id).get();
	}

	public List<User> readAllAdmin() {
		return daoU.findAll();
	}

	public String deleteAdminById(Long id) {
		daoU.deleteById(id);
		return "Admin supprime";
	}

	// Methodes CRUD Logement + readLogementByPrestaLog
	/**
	 * @param logement 
	 * prend une instance de l objet Logement en param
	 */
	public Logement createLogement(Logement logement) {
		if (logement.getId() == null || logement.getId() == 0 || !daoL.existsById(logement.getId())) {
			System.out.println("Logement cree");
			return daoL.save(logement);
		} else {
			System.out.println("Logement non crée car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public Logement updateLogement(Logement logement) {
		if (logement.getId() != null && logement.getId() != 0 && daoL.existsById(logement.getId())) {
			System.out.println("logement modifie");
			return daoL.save(logement);
		} else {
			System.out.println("logement non modifie");
			return null;
		}
	}

	public List<Logement> readAllLogement() {
		return daoL.findAll();
	}

	public Logement readLogementById(Long id) {
		return daoL.findById(id).get();
	}

	public String deleteLogementById(Long id) {
		daoL.deleteById(id);
		return "logement supprime";
	}

	public List<Logement> readLogementByPrestaLog(String prestaLog) {
		return daoL.findByPrestaLog(prestaLog);
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
	
	// Methodes CRUD Prestation
	/**
	 * @param prestation 
	 * prend une instance de l objet Prestation en param
	 */

	public Prestation createPrestation(Prestation obj) {
		if (obj.getId() == null || obj.getId() == 0 || !daoP.existsById(obj.getId())) {
			System.out.println("Prestation creee");
			return daoP.save(obj);

		} else {
			System.out.println("Prestation non creee car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public Prestation updatePrestation(Prestation obj) {
		if (obj.getId() != null && obj.getId() != 0 && daoP.existsById(obj.getId())) {
			System.out.println("Prestation modifiee");
			return daoP.save(obj);

		} else {
			System.out.println("Prestation non modifiee");
			return null;
		}
	}

	public Prestation readPrestationById(Long id) {
		return daoP.findById(id).get();
	}

	public List<Prestation> readAllPrestation() {
		return daoP.findAll();
	}

	public String deletePrestationById(Long id) {
		daoP.deleteById(id);
		return "Prestation supprimee";
	}

	public List<Prestation> readByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta) {
		return daoP.findByDebutPrestaAndFinPresta(debutPresta, finPresta);
	}

	public List<Prestation> readByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination) {
		return daoP.findByVilleDepartArriveeAndDestination(villeDepartArrivee, destination);
	}

	
	// Methodes CRUD Transport + readTransportByPrestaTrans
	/**
	 * @param transport 
	 * prend une instance de l objet Transport en param
	 */
	public Transport createTransport(Transport transport) {
		if (transport.getId() == null || transport.getId() == 0 || !daoT.existsById(transport.getId())) {
			System.out.println("transport cree");
			return daoT.save(transport);
		} else {
			System.out.println("Transport non crée car existe déjà pour cet id");
			return null;
		}
	}

	public Transport updateTransport(Transport transport) {
		if (transport.getId() != null && transport.getId() != 0 && daoT.existsById(transport.getId())) {
			System.out.println("Transport modifié");
			return daoT.save(transport);
		} else {
			return null;
		}
	}

	public List<Transport> readAllTransport() {
		return daoT.findAll();
	}

	public Transport readTransportById(Long id) {
		return daoT.findById(id).get();
	}

	public String deleteTransportById(Long id) {
		daoT.deleteById(id);
		return "Transport supprimé";
	}

	public List<Transport> readTransportByPrestaTrans(String prestaTrans) {
		return daoT.findByPrestaTrans(prestaTrans);
	}

	public List<Transport> readByPrix(Double prix) {
		return daoT.findByPrix(prix);
	}

	public List<Transport> readByTypeTrans(typeTransEnum type) {
		return daoT.findByTypeTrans(type);
	}

	
	// Methodes CRUD Activite + readActiviteByPrestaAct
	/**
	 * @param activite 
	 * prend une instance de l objet Activite en param
	 */

	public Activite createActivite(Activite activite) {
		if (activite.getId() == null || activite.getId() == 0 || !daoA.existsById(activite.getId())) {
			System.out.println("Activite creee");
			return daoA.save(activite);
		} else {
			System.out.println("Activite non creee car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public Activite updateActivite(Activite activite) {
		if (activite.getId() != null && activite.getId() != 0 && daoA.existsById(activite.getId())) {
			System.out.println("Activite modifiee");
			return daoA.save(activite);
		} else {
			System.out.println("Activite non modifiee");
			return null;

		}
	}

	public List<Activite> readAllActivite() {
		return daoA.findAll();
	}

	public Activite readActiviteById(Long id) {
		return daoA.findById(id).get();
	}

	public String deleteActiviteById(Long id) {
		daoA.deleteById(id);
		return "Activite supprimee";
	}

	public List<Activite> readActiviteByNomPrestaAct(String nomPrestaAct) {
		return daoA.findByNomPrestaAct(nomPrestaAct);
	}

	public List<Activite> readActiviteByPrix(Double prix) {
		return daoA.findByPrix(prix);
	}

	public List<Activite> readActiviteByTypeAct(typeActEnum typeAct) {
		return daoA.findByTypeAct(typeAct);
	}


}
