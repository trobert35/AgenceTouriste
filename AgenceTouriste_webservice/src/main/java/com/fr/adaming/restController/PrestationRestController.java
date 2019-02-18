package com.fr.adaming.restController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(path = "Prestation/")
public class PrestationRestController implements IPrestationRestController {

	@Autowired
	private IProduitService<Prestation> prestaService;

	@Autowired
	private ILogementService logementService;
	
	@Autowired
	private ITransportService transportService;
	
	@Autowired
	private IActiviteService activiteService;
	
	@RequestMapping(path="readAll")
	public List<Prestation> readAll() {
		List<Prestation> listPrestation = prestaService.readAllPrestation();
		return listPrestation;
	}

	@RequestMapping(path="readById")
	public String readById(@RequestBody Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		return "Prestation : " + presta;
	}

	@RequestMapping(path="readByVille")
	public String readByVille(@RequestBody String ville) {
		List<Logement> listLogement = logementService.readByVille(ville);
		return "Pour la ville de " + ville + ", voici les logements : \n" + listLogement;
	}
	
	@RequestMapping(path="readByTypeOfLogement")
	public String readByTypeOfLogement(@RequestBody typeLogEnum type) {
		List<Logement> listLogement = logementService.readByTypeLog(type);
		return "Pour le type " + type + ", voici les logements : \n" + listLogement;
	}
	
	@RequestMapping(path="readByPrixLogement")
	public String readByPrixLogement(@RequestBody Double prix) {
		List<Logement> listLogement = logementService.readByPrixLogement(prix);
		return "Pour la modique somme de " + prix + ", voici les logements : \n" + listLogement;
	}
	
	@RequestMapping(path="readByDatesDePresta")
	public String readByDatesDePresta(@RequestBody Date debut, @RequestBody Date fin) {
		List<Prestation> listPrestation = prestaService.readByDebutPrestaAndFinPresta(debut, fin);
		return "Pour des dates comprises entre " + debut + " et " + fin + ", voici les prestations : \n" + listPrestation;
	}
	
	@RequestMapping(path="readByResidenceAndDestinationPresta")
	public String readByResidenceAndDestinationPresta(@RequestBody String villeResidence, @RequestBody String destination) {
		List<Prestation> listPrestation = prestaService.readByVilleDepartArriveeAndDestination(villeResidence, destination);
		return "Pour votre ville de residence " + villeResidence + " pour " + destination + ", voici les prestations : \n" + listPrestation;
	}
	
	@RequestMapping(path="readByPrixTransport")
	public String readByPrixTransport(@RequestBody Double prix) {
		List<Transport> translist = transportService.readByPrix(prix);
		return "Prestation avec des transports au prix de " + prix + " : \n" + translist;
	}
	
	@RequestMapping(path="readByTypeTransport")
	public String readByTypeTransport(@RequestBody typeTransEnum type) {
		List<Transport> translist = transportService.readByTypeTrans(type);
		return "Prestation avec des transports de type " + type + " : \n" + translist;
	}
	
	@RequestMapping(path="readByPrixActivite")
	public String readByPrixActivite(@RequestBody Double prix) {
		List<Activite> activiteList = activiteService.readActiviteByPrix(prix);
		return "Prestation avec des activites au prix de " + prix + " : \n" + activiteList;
	}
	
	@RequestMapping(path="readByTypeActivite")
	public String readByTypeActivite(@RequestBody typeActEnum type) {
		List<Activite> activiteList = activiteService.readActiviteByTypeAct(type);
		return "Prestation avec des activites de type " + type + " : \n" + activiteList;
	}
	
	
}
