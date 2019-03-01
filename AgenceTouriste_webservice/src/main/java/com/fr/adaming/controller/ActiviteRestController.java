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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ActiviteCreateDTO;
import com.fr.adaming.entity.Activite;
import com.fr.adaming.enumeration.typeActEnum;
import com.fr.adaming.service.IActiviteService;

/**
 * @author Mohamed
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/")
@CrossOrigin
public class ActiviteRestController {

	// Methodes CRUD Activite

	@Autowired
	private IActiviteService activiteService;

	
	/**
	 * @param act correspond aux caracs necessaires a la creation d une activite
	 * @return String retournant le nom de l activite creee
	 */
	@PostMapping(path = "activite")
	public String createActivite(@RequestBody ActiviteCreateDTO act) {
		activiteService
		.createActivite(new Activite(act.getPrix(), act.getTypeAct(), act.getNom(), act.getNomPrestaAct()));
		return "Creation Activite SUCCESS";
		
	}

	/**
	 * @param act objet Activite qui correpond a l activite que l on souhaite
	 *            modifier
	 * @return l activite modifiee et ses caracteristiques
	 */
	@PutMapping(path = "activite")
	public String updateActivite(@RequestBody Activite act) {
		activiteService.updateActivite(act);
		return "Mise a jour de l'Activite SUCCESS";

	}

	/**
	 * @return la liste des activites
	 */
	@GetMapping(path = "activite")
	public List<Activite> readAllActivite() {
		return (activiteService.readAllActivite());
	}

	/**
	 * @param id objet Long, prend l id de l activite recherchee
	 * @return String retournant les details de l activite recherchee
	 */
	@GetMapping(path = "activite/id/{id}")
	public Activite readByIdActivite(@PathVariable Long id) {
		return activiteService.readActiviteById(id);
	}

	/**
	 * @param id objet Long, correspond a l id de l activite que l on souhaite
	 *           supprimer
	 * @return String retournant le nom de l activite supprimee
	 */
	@DeleteMapping(path = "activite/{id}")
	public void deleteActivite(@PathVariable Long id) {
		activiteService.readActiviteById(id);
		activiteService.deleteActiviteById(id);
	}

	/**
	 * @param prix objet Double, correspond au prix des activites recherchees
	 * @return String + liste des activites correspondant au prix recherche
	 */
	@GetMapping(path = "activite/prix/{prixAct}")
	public List<Activite> readByPrixActivite(@PathVariable Double prixAct) {
		return activiteService.readActiviteByPrix(prixAct);
	}

	/**
	 * @param type typeActEnum, correspond au type d activite que l on recherche
	 * @return String + liste d activites correspondant au type recherche
	 */
	@GetMapping(path = "activite/{typeAct}")
	public List<Activite> readByTypeActivite(@RequestParam(value="typeAct") typeActEnum type) {
		return activiteService.readActiviteByTypeAct(type);
	}


	/**
	 * @param presta String, permet de chercher les activites liees a une prestation
	 * @return String renvoyant les activites liees a la prrestation recherchee
	 */
	@GetMapping(path = "activite/{presta}")
	@ResponseBody
	public List<Activite>  readActiviteByPresta(@RequestParam String presta) {
		return activiteService.readActiviteByNomPrestaAct(presta);
	}

}
