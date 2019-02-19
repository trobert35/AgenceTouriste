package com.fr.adaming.restController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeActEnum;
import com.fr.adaming.enumeration.typeLogEnum;
import com.fr.adaming.enumeration.typeTransEnum;
import com.fr.adaming.service.IActiviteService;
import com.fr.adaming.service.ILogementService;
import com.fr.adaming.service.IProduitService;
import com.fr.adaming.service.ITransportService;

@RestController
@RequestMapping(path = "api/")
public class PrestationRestController implements IPrestationRestController {

	@Autowired
	private IProduitService<Prestation> prestaService;

	@Autowired
	private ILogementService logementService;

	@Autowired
	private ITransportService transportService;

	@Autowired
	private IActiviteService activiteService;

	@RequestMapping(path = "prestation", method = RequestMethod.POST)
	public String createPrestation(@RequestBody Prestation presta) {
		presta = prestaService.createPrestation(presta);
		return "Prestation cree : " + presta;
	}

	@RequestMapping(path = "prestation", method = RequestMethod.PUT)
	public String updatePrestation(@RequestBody Prestation presta) {
		presta = prestaService.updatePrestation(presta);
		return "Prestation modifiee : " + presta;
	}

	@RequestMapping(path = "prestation/{id}", method = RequestMethod.DELETE)
	public String deletePrestation(@PathVariable Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		prestaService.deletePrestationById(id);
		return presta.getNom() + " a ete supprimee";
	}

	@RequestMapping(path = "prestation", method = RequestMethod.GET)
	public List<Prestation> readAll() {
		List<Prestation> listPrestation = prestaService.readAllPrestation();
		return listPrestation;
	}

	@RequestMapping(path = "prestation/{idPresta}", method = RequestMethod.GET)
	public String readById(@PathVariable Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		return "Prestation : " + presta;
	}

	@RequestMapping(path = "prestation/{ville}", method = RequestMethod.GET)
	public String readByVille(@PathVariable String ville) {
		List<Logement> listLogement = logementService.readByVille(ville);
		return "Pour la ville de " + ville + ", voici les logements : \n" + listLogement;
	}

	@RequestMapping(path = "prestation/{typeLog}", method = RequestMethod.GET)
	public String readByTypeOfLogement(@PathVariable typeLogEnum type) {
		List<Logement> listLogement = logementService.readByTypeLog(type);
		return "Pour le type " + type + ", voici les logements : \n" + listLogement;
	}

	@RequestMapping(path = "prestation/{prixLog}", method = RequestMethod.GET)
	public String readByPrixLogement(@PathVariable Double prix) {
		List<Logement> listLogement = logementService.readByPrixLogement(prix);
		return "Pour la modique somme de " + prix + ", voici les logements : \n" + listLogement;
	}

	@RequestMapping(path = "prestation/{debut}/conf/{fin}", method = RequestMethod.GET)
	public String readByDatesDePresta(@PathVariable("debut") Date debut, @PathVariable("fin") Date fin) {
		List<Prestation> listPrestation = prestaService.readByDebutPrestaAndFinPresta(debut, fin);
		return "Pour des dates comprises entre " + debut + " et " + fin + ", voici les prestations : \n"
				+ listPrestation;
	}

	@RequestMapping(path = "prestation/{villeResidence}/conf/{destination}", method = RequestMethod.GET)
	public String readByResidenceAndDestinationPresta(@PathVariable("villeResidence") String villeResidence,
			@PathVariable("destination") String destination) {
		List<Prestation> listPrestation = prestaService.readByVilleDepartArriveeAndDestination(villeResidence,
				destination);
		return "Pour votre ville de residence " + villeResidence + " pour " + destination
				+ ", voici les prestations : \n" + listPrestation;
	}

	@RequestMapping(path = "prestation/{prixTrans}", method = RequestMethod.GET)
	public String readByPrixTransport(@PathVariable Double prix) {
		List<Transport> translist = transportService.readByPrix(prix);
		return "Prestation avec des transports au prix de " + prix + " : \n" + translist;
	}

	@RequestMapping(path = "prestation/{typeTrans}", method = RequestMethod.GET)
	public String readByTypeTransport(@PathVariable typeTransEnum type) {
		List<Transport> translist = transportService.readByTypeTrans(type);
		return "Prestation avec des transports de type " + type + " : \n" + translist;
	}

	@RequestMapping(path = "prestation/{prixAct}", method = RequestMethod.GET)
	public String readByPrixActivite(@PathVariable Double prix) {
		List<Activite> activiteList = activiteService.readActiviteByPrix(prix);
		return "Prestation avec des activites au prix de " + prix + " : \n" + activiteList;
	}

	@RequestMapping(path = "prestation/{typeAct}", method = RequestMethod.GET)
	public String readByTypeActivite(@PathVariable typeActEnum type) {
		List<Activite> activiteList = activiteService.readActiviteByTypeAct(type);
		return "Prestation avec des activites de type " + type + " : \n" + activiteList;
	}

}
