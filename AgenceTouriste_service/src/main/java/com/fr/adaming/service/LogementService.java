package com.fr.adaming.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ILogementDao;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.enumeration.typeLogEnum;

@Service
public class LogementService implements ILogementService{

	@Autowired
	private ILogementDao daoL;
	private Logger log = Logger.getLogger(LogementService.class);
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
}
