package com.fr.adaming.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IActiviteDao;
import com.fr.adaming.entity.Activite;
import com.fr.adaming.enumeration.typeActEnum;

/**
 * @author Thomas S
 * @author Maxime
 *
 */
@Service
public class ActiviteService implements IActiviteService {

	@Autowired
	private IActiviteDao daoA;
	private Logger log = Logger.getLogger(ActiviteService.class);

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
			log.info("Creation de l'Activite SUCCESS");
			return daoA.save(activite);
		} else {
			log.warn("Creation de l'Activite FAILED");
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
			log.info("Modification de l'Activite SUCCESS");
			return daoA.save(activite);
		} else {
			log.warn("Modification de l'Activite FAILED");
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
			log.info("Lecture des Activite SUCCESS");

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
	 * @throws EmptyResultDataAccessException     si activite est inexistant dans la
	 *                                            database
	 */
	public String deleteActiviteById(Long id) {
		daoA.deleteById(id);
		return "Activite supprimee";
	}

	/**
	 * Ressort un objet Activite de la database, en utilisant son nomPrestaAct, si
	 * activite y existe
	 * 
	 * @param nomPrestaAct (String) nom du prestataire de activite
	 * @return une List de Activite
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
	 * y existe
	 * 
	 * @param prix (Double) prix de activite
	 * @return une List de Activite
	 */
	public List<Activite> readActiviteByPrix(Double prix) {
		return daoA.findByPrix(prix);
	}

	/**
	 * Ressort un objet Activite de la database, en utilisant son typeAct, si
	 * activite y existe
	 * 
	 * @param typeAct (Enum) type de activite
	 * @return une List de Activite
	 */
	public List<Activite> readActiviteByTypeAct(typeActEnum typeAct) {
		return daoA.findByTypeAct(typeAct);
	}
}
