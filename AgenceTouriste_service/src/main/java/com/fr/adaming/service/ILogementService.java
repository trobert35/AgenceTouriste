package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Logement;
import com.fr.adaming.enumeration.typeLogEnum;

/**
 * @author Thomas S
 *
 */
public interface ILogementService {

	/**
	 * @param logement objet de la classe Logement
	 * @return un objet de la classe Logement
	 */
	public Logement createLogement(Logement logement);

	/**
	 * @param logement objet de la classe Logement
	 * @return un objet de la classe Logement
	 */
	public Logement updateLogement(Logement logement);

	/**
	 * @param id id du Logement
	 * @return un objet de la classe Logement
	 */
	public Logement readLogementById(Long id);

	/**
	 * @return une List de Logement
	 */
	public List<Logement> readAllLogement();

	/**
	 * @param id du logement
	 * @return String
	 */
	public String deleteLogementById(Long id);

	/**
	 * @param prestaLog nom du prestataire du Logement
	 * @return une List de Logement
	 */
	public List<Logement> readLogementByPrestaLog(String prestaLog);

	/**
	 * @param ville nom de la ville du Logement
	 * @return une List de Logement
	 */
	public List<Logement> readByVille(String ville);

	/**
	 * @param typeLog type du Logement
	 * @return une List de Logement
	 */
	public List<Logement> readByTypeLog(typeLogEnum typeLog);

	/**
	 * @param prix prix du Logement
	 * @return une List de Logement
	 */
	public List<Logement> readByPrixLogement(Double prix);
}
