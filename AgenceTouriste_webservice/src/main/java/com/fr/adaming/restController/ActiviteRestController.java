package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ActiviteCreateDTO;
import com.fr.adaming.entity.Activite;
import com.fr.adaming.enumeration.typeActEnum;
import com.fr.adaming.service.IActiviteService;

/**
 * @author Mohamed EL AGREBI
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/")
public class ActiviteRestController {

	// Methodes CRUD Activite

	@Autowired
	private IActiviteService activiteService;

	private static final String SAUT = ": \n";

	/**
	 * @param act correspond aux caracs necessaires a la creation d une activite
	 * @return String retournant le nom de l activite creee
	 */
	@PostMapping(path = "activite")
	public String createActivite(@RequestBody ActiviteCreateDTO act) {
		Activite a = activiteService
				.createActivite(new Activite(act.getPrix(), act.getTypeAct(), act.getNom(), act.getNomPrestaAct()));
		return "Activite creee : " + a.getNom();
	}

	/**
	 * @param act objet Activite qui correpond a l activite que l on souhaite
	 *            modifier
	 * @return l activite modifiee et ses caracteristiques
	 */
	@PutMapping(path = "activite")
	public String updateActivite(@RequestBody Activite act) {
		act = activiteService.updateActivite(act);

		return "Activite modifiee : " + act;
	}

	/**
	 * @return la liste des activites
	 */
	@GetMapping(path = "activite")
	public String readAllActivite() {
		List<Activite> listact = activiteService.readAllActivite();
		return "Liste activite(s) : " + listact;
	}

	/**
	 * @param id objet Long, prend l id de l activite recherchee
	 * @return String retournant les details de l activite recherchee
	 */
	@GetMapping(path = "activite/{id}")
	public String readByIdActivite(@PathVariable Long id) {
		Activite act = activiteService.readActiviteById(id);
		return "Activite : " + act;
	}

	/**
	 * @param id objet Long, correspond a l id de l activite que l on souhaite
	 *           supprimer
	 * @return String retournant le nom de l activite supprimee
	 */
	@DeleteMapping(path = "activite/{id}")
	public String deleteActivite(@PathVariable Long id) {
		Activite act = activiteService.readActiviteById(id);
		activiteService.deleteActiviteById(id);
		return act.getNom() + " a ete supprimee";
	}

	/**
	 * @param prix objet Double, correspond au prix des activites recherchees
	 * @return String + liste des activites correspondant au prix recherche
	 */
	@GetMapping(path = "activite/{prixAct}")
	public String readByPrixActivite(@PathVariable Double prix) {
		List<Activite> activiteList = activiteService.readActiviteByPrix(prix);
		return "Activite(s) au prix de " + prix + SAUT + activiteList;
	}

	/**
	 * @param type typeActEnum, correspond au type d activite que l on recherche
	 * @return String + liste d activites correspondant au type recherche
	 */
	@GetMapping(path = "activite/{typeAct}")
	public String readByTypeActivite(@PathVariable typeActEnum type) {
		List<Activite> activiteList = activiteService.readActiviteByTypeAct(type);
		return "Activite(s) de type " + type + SAUT + activiteList;
	}

	/**
	 * @param presta String, permet de chercher les activites liees a une prestation
	 * @return String renvoyant les activites liees a la prrestation recherchee
	 */
	@GetMapping(path = "activite/{presta}")
	public String readActiviteByPresta(@PathVariable String presta) {
		List<Activite> listact = activiteService.readActiviteByNomPrestaAct(presta);
		return "Activite(s) proposee(s) par " + presta + SAUT + listact;
	}

}
