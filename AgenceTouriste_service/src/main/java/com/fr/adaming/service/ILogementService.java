package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Logement;
import com.fr.adaming.enumeration.typeLogEnum;

public interface ILogementService {

	/**
	 * @author Thomas S
	 **/

	public Logement createLogement(Logement logement);

	public Logement updateLogement(Logement logement);

	public Logement readLogementById(Long id);

	public List<Logement> readAllLogement();

	public String deleteLogementById(Long id);
	
	public List<Logement> readLogementByPrestaLog(String prestaLog);

	public List<Logement> readByVille(String ville);
	
	public List<Logement> readByTypeLog(typeLogEnum typeLog);
	
	public List<Logement> readByPrixLogement(Double prix);
}
