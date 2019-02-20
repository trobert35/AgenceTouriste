package com.fr.adaming.restController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.PrestationCreateDTO;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.service.IProduitService;

/**
 * @author Mohamed EL AGREBI
 *		   Thomas S
 */
@RestController
@RequestMapping(path = "api/")
public class PrestationRestController implements IPrestationRestController {

	@Autowired
	private IProduitService<Prestation> prestaService;

	/**
	 * @param dtoPresta dto prestation
	 * @return String + nom presta
	 * @throws ParseException parseException
	 */
	@RequestMapping(path = "prestation", method = RequestMethod.POST)
	public String createPrestation(@RequestBody PrestationCreateDTO dtoPresta) throws ParseException {
		prestaService.createPrestation(
				new Prestation(dtoPresta.getNom(), new SimpleDateFormat("dd/MM/yyyy").parse(dtoPresta.getDebutPresta()),
						new SimpleDateFormat("dd/MM/yyyy").parse(dtoPresta.getFinPresta()),
						dtoPresta.getVilleDepartArrivee(), dtoPresta.getDestination(), dtoPresta.getNbPersonnesMax(),
						dtoPresta.getCommission(), dtoPresta.getAvis()));
		return "Prestation creee : " + dtoPresta.getNom();
	}

	/**
	 * @param presta un objet prestation
	 * @return String + l'objet prestation
	 */
	@RequestMapping(path = "prestation", method = RequestMethod.PUT)
	public String updatePrestation(@RequestBody Prestation presta) {
		presta = prestaService.updatePrestation(presta);
		return "Prestation modifiee : " + presta;
	}

	/**
	 * @param id id de la prestation
	 * @return nom de la presta + String
	 */
	@RequestMapping(path = "prestation/{id}", method = RequestMethod.DELETE)
	public String deletePrestation(@PathVariable Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		prestaService.deletePrestationById(id);
		return presta.getNom() + " a ete supprimee";
	}

	/**
	 * @return liste de prestations
	 */
	@RequestMapping(path = "prestation", method = RequestMethod.GET)
	public List<Prestation> readAll() {
		List<Prestation> listPrestation = prestaService.readAllPrestation();
		return listPrestation;
	}

	/**
	 * @param id id de la prestation
	 * @return String + prestation
	 */
	@RequestMapping(path = "prestation/{idPresta}", method = RequestMethod.GET)
	public String readById(@PathVariable Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		return "Prestation : " + presta;
	}

	/**
	 * @param debut date de debut de la presta
	 * @param fin date de fin de la presta
	 * @return String + liste de prestation
	 */
	@RequestMapping(path = "prestation/{debut}/conf/{fin}", method = RequestMethod.GET)
	public String readByDatesDePresta(@PathVariable("debut") Date debut, @PathVariable("fin") Date fin) {
		List<Prestation> listPrestation = prestaService.readByDebutPrestaAndFinPresta(debut, fin);
		return "Pour des dates comprises entre " + debut + " et " + fin + ", voici le(s) prestation(s) : \n"
				+ listPrestation;
	}

	/**
	 * @param villeResidence ville de residence
	 * @param destination ville de destination
	 * @return String + liste de prestations
	 */
	@RequestMapping(path = "prestation/{villeResidence}/conf/{destination}", method = RequestMethod.GET)
	public String readByResidenceAndDestinationPresta(@PathVariable("villeResidence") String villeResidence,
			@PathVariable("destination") String destination) {
		List<Prestation> listPrestation = prestaService.readByVilleDepartArriveeAndDestination(villeResidence,
				destination);
		return "Pour votre ville de residence " + villeResidence + " pour " + destination
				+ ", voici le(s) prestation(s) : \n" + listPrestation;
	}

	/**
	 * @param dtoPresta dto presta
	 * @return le prix total combine entre le prix transport + logement + activite et en comptant la commission de l'agence
	 * @throws ParseException parseException
	 */
	@RequestMapping(path = "prestation/{presta}", method = RequestMethod.POST)
	public String calculPrixTotal(@RequestBody PrestationCreateDTO dtoPresta) throws ParseException {
		Prestation p = prestaService
				.readByDebutPrestaAndFinPresta(new SimpleDateFormat("dd/MM/yyyy").parse(dtoPresta.getDebutPresta()),
						new SimpleDateFormat("dd/MM/yyyy").parse(dtoPresta.getFinPresta()))
				.get(0);
		prestaService.calculPrixTotal(p);
		return Double.toString(p.getPrixTotal());
	}
}
