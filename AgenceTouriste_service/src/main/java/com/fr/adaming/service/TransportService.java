package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ITransportDao;
import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;

/**
 * @author Thomas S
 * @author Maxime
 *
 */
@Service
public class TransportService implements ITransportService {

	@Autowired
	private ITransportDao daoT;
	private Logger log = Logger.getLogger(TransportService.class);

	private static final String SUCCESS = " SUCCESS";
	private static final String FAILED = " FAILED";

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
			log.info("Creation du Transport SUCCESS");
			return daoT.save(transport);
		} else {
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
		Optional<Transport> optTra = daoT.findById(id);
		if (!optTra.isPresent()) {
			log.warn("Lecture du Transport avec l'id " + id + FAILED);
			return null;
		} else {
			log.info("Lecture du Transport avec l'id " + id + SUCCESS);
			return optTra.get();
		}
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
	 * transport y existe
	 * 
	 * @param prestaTrans (String) nom du prestataire de transport
	 * @return une List de Transport
	 */
	public List<Transport> readTransportByPrestaTrans(String prestaTrans) {
		List<Transport> lili = daoT.findByPrestaTrans(prestaTrans);
		if (lili.isEmpty()) {
			log.warn("Lecture de la liste des Transport concernant " + prestaTrans + FAILED);
		} else {
			log.info("Lecture de la liste des Transport concernant " + prestaTrans + SUCCESS);

		}
		return lili;
	}

	/**
	 * Ressort un objet Transport de la database, en utilisant son prix, si
	 * transport y existe
	 * 
	 * @param prix (Double) prix de transport
	 * @return une List de Transport
	 */
	public List<Transport> readByPrix(Double prix) {
		return daoT.findByPrix(prix);
	}

	/**
	 * Ressort un objet Transport de la database, en utilisant son type, si
	 * transport y existe
	 * 
	 * @param type (Enum) type de transport
	 * @return une List de Transport
	 */
	public List<Transport> readByTypeTrans(typeTransEnum type) {
		return daoT.findByTypeTrans(type);
	}
}
