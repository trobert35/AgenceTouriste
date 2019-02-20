package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LogementCreateDTO;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.service.ILogementService;

@RestController
@RequestMapping("api/")
public class LogementRestController {

	// Methode CRUD Logement
	@Autowired
	private ILogementService logementService;

	@RequestMapping(path = "logement", method = RequestMethod.POST)
	public String createLogement(@RequestBody LogementCreateDTO logement) {
		logementService.createLogement(new Logement(logement.getPrestaLog(), logement.getNom(),
				logement.getVille(), logement.getPrix(), logement.getTypeLog(),
				logement.getPension(), logement.getQualite()));
		return logement.getNom() + " est un logement creer";
	}

	@RequestMapping(path = "logement", method = RequestMethod.PUT)
	public String updateLogement(@RequestBody Logement logement) {
		logementService.updateLogement(logement);
		return logement.getNom() + " est un logement modifie";
	}

	@RequestMapping(path = "logement", method = RequestMethod.GET)
	public List<Logement> readAllLogement() {
		List<Logement> listLogement = logementService.readAllLogement();
		return listLogement;
	}

	@RequestMapping(path = "logement/{id}", method = RequestMethod.GET)
	public String readByIdLogement(@PathVariable Long id) {
		Logement logement = logementService.readLogementById(id);
		return "Logement : " + logement;
	}

	@RequestMapping(path = "logement/{id}", method = RequestMethod.DELETE)
	public String deleteLogement(@PathVariable Long id) {
		Logement logement = logementService.readLogementById(id);
		logementService.deleteLogementById(id);
		return logement.getNom() + " a correctement ete supprime";
	}

	@RequestMapping(path = "logement/{prestaLog}", method = RequestMethod.GET)
	public String readLogementByPresta(@PathVariable String prestaLog) {
		List<Logement> listlogement = logementService.readLogementByPrestaLog(prestaLog);
		return "Logements fournis par " + prestaLog + " :\n" + listlogement;
	}

}
