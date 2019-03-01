package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LogementCreateDTO;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.enumeration.typeLogEnum;
import com.fr.adaming.service.ILogementService;

/**
 * @author Mohamed
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/")
@CrossOrigin
public class LogementRestController {

	// Methode CRUD Logement
	@Autowired
	private ILogementService logementService;

	private static final String STRING = ", voici le(s) logement(s) : \n";

	/**
	 * @param logement logement dto
	 * @return nom logement + String
	 */
	@PostMapping(path = "logement")
	public String createLogement(@RequestBody LogementCreateDTO logement) {
		logementService.createLogement(new Logement(logement.getPrestaLog(), logement.getNom(), logement.getVille(),
				logement.getPrix(), logement.getTypeLog(), logement.getPension(), logement.getQualite()));
		return logement.getNom() + " est un logement cree";
	}

	/**
	 * @param logement objet logement
	 * @return nom logement + String
	 */
	@PutMapping(path = "logement")
	public String updateLogement(@RequestBody Logement logement) {
		logementService.updateLogement(logement);
		return logement.getNom() + " est un logement modifie";
	}

	/**
	 * @return liste de logements
	 */
	@GetMapping(path = "logement")
	public List<Logement> readAllLogement() {
		return logementService.readAllLogement();
	}

	/**
	 * @param id id logement
	 * @return String + logement
	 */
	@GetMapping(path = "logement/id/{id}")
	public Logement readByIdLogement(@PathVariable Long id) {
		
		return logementService.readLogementById(id);
	}

	/**
	 * @param id id logement
	 * @return nom logement + String
	 */
	@DeleteMapping(path = "logement/{id}")
	public String deleteLogement(@PathVariable Long id) {
		Logement logement = logementService.readLogementById(id);
		logementService.deleteLogementById(id);
		return logement.getNom() + " a correctement ete supprime";
	}

	/**
	 * @param prestaLog nom prestataire
	 * @return String + liste logements
	 */
	@GetMapping(path = "logement/prestataire/{prestaLog}")
	public List<Logement> readLogementByPresta(@PathVariable String prestaLog) {
		return logementService.readLogementByPrestaLog(prestaLog);
	}

	/**
	 * @param ville ville logement
	 * @return String + liste logement
	 */
	@GetMapping(path = "logement/ville/{ville}")
	public List<Logement> readByVille(@PathVariable String ville) {
		
		return logementService.readByVille(ville);
	}

	/**
	 * @param type type logement
	 * @return String + liste de logements
	 */
	@GetMapping(path = "logement/{typeLog}")
	public List<Logement> readByTypeOfLogement(@RequestParam(value="typeLog") typeLogEnum type) {
		 
		return logementService.readByTypeLog(type);
	}

	/**
	 * @param prix prix
	 * @return String + liste logements
	 */
	@GetMapping(path = "logement/{prixLog}")
	public List<Logement> readByPrixLogement(@RequestParam(value="prixLog") Double prix) {
		
		return logementService.readByPrixLogement(prix);
	}

}
