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

/**
 * @author Mohamed EL AGREBI
 *		   Thomas S
 */
@RestController
@RequestMapping(path = "api/")
public class ActiviteRestController {

	// Methodes CRUD Activite

	@Autowired
	private IActiviteService activiteService;

	/**
	 * @param correspond aux caracs necessaires a la creation d une activite 
	 * @return String retournant le nom de l activite creee
	 */
	@RequestMapping(path = "activite", method = RequestMethod.POST)
	public String createActivite(@RequestBody ActiviteCreateDTO act) {
		Activite a = activiteService
				.createActivite(new Activite(act.getPrix(), act.getTypeAct(), act.getNom(), act.getNomPrestaAct()));
		return "Activite creee : " + a.getNom();
	}

	/**
	 * @param objet Activite qui correpond a l activite que l on souhaite modifier
	 * @return l activite modifiee et ses caracteristiques
	 */
	@RequestMapping(path = "activite", method = RequestMethod.PUT)
	public String updateActivite(@RequestBody Activite act) {
		act = activiteService.updateActivite(act);

		return "Activite modifiee : " + act;
	}

	/**
	 * @return la liste des activites
	 */
	@RequestMapping(path = "activite", method = RequestMethod.GET)
	public String readAllActivite() {
		List<Activite> listact = activiteService.readAllActivite();
		return "Liste activite(s) : " + listact;
	}

	/**
	 * @param objet Long, prend l id de l activite recherchee 
	 * @return String retournant les details de l activite recherchee
	 */
	@RequestMapping(path = "activite/{id}", method = RequestMethod.GET)
	public String readByIdActivite(@PathVariable Long id) {
		Activite act = activiteService.readActiviteById(id);
		return "Activite : " + act;
	}

	/**
	 * @param objet Long, correspond a l id de l activite que l on souhaite supprimer
	 * @return String retournant le nom de l activite supprimee
	 */
	@RequestMapping(path = "activite/{id}", method = RequestMethod.DELETE)
	public String deleteActivite(@PathVariable Long id) {
		Activite act = activiteService.readActiviteById(id);
		activiteService.deleteActiviteById(id);
		return act.getNom() + " a ete supprimee";
	}

	/**
	 * @param objet Long, correspond au prix des activites recherchees
	 * @return String + liste des activites correspondant au prix recherche
	 */
	@RequestMapping(path = "activite/{prixAct}", method = RequestMethod.GET)
	public String readByPrixActivite(@PathVariable Double prix) {
		List<Activite> activiteList = activiteService.readActiviteByPrix(prix);
		return "Activite(s) au prix de " + prix + " : \n" + activiteList;
	}

	/**
	 * @param typeActEnum, correspond au type d activite que l on recherche
	 * @return String + liste d activites correspondant au type recherche
	 */
	@RequestMapping(path = "activite/{typeAct}", method = RequestMethod.GET)
	public String readByTypeActivite(@PathVariable typeActEnum type) {
		List<Activite> activiteList = activiteService.readActiviteByTypeAct(type);
		return "Activite(s) de type " + type + " : \n" + activiteList;
	}
	
	/**
	 * @param String, permet de chercher les activites liees a une prestation
	 * @return String renvoyant les activites liees a la prrestation recherchee
	 */
	@RequestMapping(path = "activite/{presta}", method = RequestMethod.GET)
	public String readActiviteByPresta(@PathVariable String presta) {
		List<Activite> listact = activiteService.readActiviteByNomPrestaAct(presta);
		return "Activite(s) proposee(s) par " + presta + ":\n" + listact;
	}

}
