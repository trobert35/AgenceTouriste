package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ActiviteCreateDTO;
import com.fr.adaming.entity.Activite;
import com.fr.adaming.enumeration.typeActEnum;
import com.fr.adaming.service.IActiviteService;

@RestController
@RequestMapping(path = "api/")
public class ActiviteRestController {

	// Methodes CRUD Activite

	@Autowired
	private IActiviteService activiteService;

	@RequestMapping(path = "activite", method = RequestMethod.POST)
	public String createActivite(@RequestBody ActiviteCreateDTO act) {
		Activite a = activiteService
				.createActivite(new Activite(act.getPrix(), act.getTypeAct(), act.getNom(), act.getNomPrestaAct()));
		return "Activite creee : " + a.getNom();
	}

	@RequestMapping(path = "activite", method = RequestMethod.PUT)
	public String updateActivite(@RequestBody Activite act) {
		act = activiteService.updateActivite(act);

		return "Activite modifiee : " + act;
	}

	@RequestMapping(path = "activite", method = RequestMethod.GET)
	public String readAllActivite() {
		List<Activite> listact = activiteService.readAllActivite();
		return "Liste activite(s) : " + listact;
	}

	@RequestMapping(path = "activite/{id}", method = RequestMethod.GET)
	public String readByIdActivite(@PathVariable Long id) {
		Activite act = activiteService.readActiviteById(id);
		return "Activite : " + act;
	}

	@RequestMapping(path = "activite/{id}", method = RequestMethod.DELETE)
	public String deleteActivite(@PathVariable Long id) {
		Activite act = activiteService.readActiviteById(id);
		activiteService.deleteActiviteById(id);
		return act.getNom() + " a ete supprimee";
	}

	@RequestMapping(path = "activite/{prixAct}", method = RequestMethod.GET)
	public String readByPrixActivite(@PathVariable Double prix) {
		List<Activite> activiteList = activiteService.readActiviteByPrix(prix);
		return "Activite(s) au prix de " + prix + " : \n" + activiteList;
	}

	@RequestMapping(path = "activite/{typeAct}", method = RequestMethod.GET)
	public String readByTypeActivite(@PathVariable typeActEnum type) {
		List<Activite> activiteList = activiteService.readActiviteByTypeAct(type);
		return "Activite(s) de type " + type + " : \n" + activiteList;
	}
	
	@RequestMapping(path = "activite/{presta}", method = RequestMethod.GET)
	public String readActiviteByPresta(@PathVariable String presta) {
		List<Activite> listact = activiteService.readActiviteByNomPrestaAct(presta);
		return "Activite(s) proposee(s) par " + presta + ":\n" + listact;
	}

}
