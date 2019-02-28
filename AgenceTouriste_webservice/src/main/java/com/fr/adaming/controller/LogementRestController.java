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
	@GetMapping(path = "logement/{id}")
	public String readByIdLogement(@PathVariable Long id) {
		Logement logement = logementService.readLogementById(id);
		return "Logement : " + logement;
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
	@GetMapping(path = "logement/{prestaLog}")
	public String readLogementByPresta(@PathVariable String prestaLog) {
		List<Logement> listlogement = logementService.readLogementByPrestaLog(prestaLog);
		return "Logement(s) fourni(s) par " + prestaLog + " :\n" + listlogement;
	}

	/**
	 * @param ville ville logement
	 * @return String + liste logement
	 */
	@GetMapping(path = "logement/{ville}")
	public String readByVille(@PathVariable String ville) {
		List<Logement> listLogement = logementService.readByVille(ville);
		return "Pour la ville de " + ville + STRING + listLogement;
	}

	/**
	 * @param type type logement
	 * @return String + liste de logements
	 */
	@GetMapping(path = "logement/{typeLog}")
	public String readByTypeOfLogement(@PathVariable typeLogEnum type) {
		List<Logement> listLogement = logementService.readByTypeLog(type);
		return "Pour le type " + type + STRING + listLogement;
	}

	/**
	 * @param prix prix
	 * @return String + liste logements
	 */
	@GetMapping(path = "logement/{prixLog}")
	public String readByPrixLogement(@PathVariable Double prix) {
		List<Logement> listLogement = logementService.readByPrixLogement(prix);
		return "Pour la modique somme de " + prix + STRING + listLogement;
	}

}
