package com.fr.adaming.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IPrestationDao;
import com.fr.adaming.entity.Prestation;

/**
 * @author Thomas S
 * @author Maxime
 *
 */
@Service
public class PrestationService implements IProduitService<Prestation> {

	@Autowired
	private IPrestationDao daoP;

	private Logger log = Logger.getLogger(PrestationService.class);

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
			log.info("Creation de Prestation SUCCESS");
			return daoP.save(obj);

		} else {
			log.warn("Creation de Prestation FAILED");
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
			log.info("Modification de la Prestation SUCCESS");
			return daoP.save(obj);
		} else {
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
		Optional<Prestation> optPres = daoP.findById(id);
		if (!optPres.isPresent()) {
			log.warn("Lecture de la Prestation avec l'id " + id + " FAILED");
			return null;
		} else {
			log.info("Lecture de la Prestation avec l'id " + id + " SUCCESS");
			return optPres.get();
		}
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
	 * @throws EmptyResultDataAccessException     si prestation est inexistant dans
	 *                                            la database
	 */
	public String deletePrestationById(Long id) {
		if (id != null && daoP.findById(id).isPresent()) {
			daoP.deleteById(id);
			log.warn("Suppression de Prestation FAILED");
			return "Prestation supprimee";
		} else {
			log.info("Suppression de Prestation SUCCESS");
			return null;
		}
	}

	/**
	 * Ressort un objet Prestation de la database, en utilisant son debutPresta et
	 * sa finPresta, si prestation y existe
	 * 
	 * @param debutPresta (Date) date de debut de prestation
	 * @param finPresta   (Date) date de fin de prestation
	 * @return une List de Prestation
	 */
	public List<Prestation> readByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta) {
		return daoP.findByDebutPrestaAndFinPresta(debutPresta, finPresta);
	}

	/**
	 * Ressort un objet Prestation de la database, en utilisant sa
	 * villeDepartArrivee et sa destination, si prestation y existe
	 * 
	 * @param villeDepartArrivee (String) ville de depart et de retour de prestation
	 * @param destination        (String) destination de prestation
	 * @return une List de Prestation
	 */
	public List<Prestation> readByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination) {
		return daoP.findByVilleDepartArriveeAndDestination(villeDepartArrivee, destination);
	}

	/**
	 * Calcule le prix total de l'objet Prestation, grace aux prix d activite, de
	 * logement et de transport
	 * 
	 * @param prestation prend une instance de l objet Prestation en param, ne doit
	 *                   pas etre null
	 * @throws NullPointerException si prestation est null
	 */
	public void calculPrixTotal(Prestation prestation) {
		double prixActTot = 0;
		double prixLogTot = 0;
		double prixTraTot = 0;

		if (!prestation.getActivite().isEmpty()) {
			for (int i = 0; i < prestation.getActivite().size(); i++) {
				prixActTot = prixActTot + prestation.getActivite().get(i).getPrix();
			}
		}

		if (!prestation.getLogement().isEmpty()) {
			for (int i = 0; i < prestation.getLogement().size(); i++) {
				prixLogTot = prixLogTot + prestation.getLogement().get(i).getPrix();
			}
		}

		if (!prestation.getTransport().isEmpty()) {
			for (int i = 0; i < prestation.getTransport().size(); i++) {
				prixTraTot = prixTraTot + prestation.getTransport().get(i).getPrix();
			}
		}
		System.out.println(prixActTot + prixLogTot + prixTraTot);
		prestation.setPrixTotal((prixActTot + prixLogTot + prixTraTot) * (1 + prestation.getCommission()));
	}
}
