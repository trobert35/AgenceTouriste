package com.fr.adaming.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.fr.adaming.dto.PrestationCreateDTO;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.service.IProduitService;

/**
 * @author Mohamed
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/")
@CrossOrigin
public class PrestationRestController implements IPrestationRestController {

	@Autowired
	private IProduitService<Prestation> prestaService;
	private static final String FORMATDATE = "dd/MM/yyyy";

	/**
	 * @param dtoPresta dto prestation
	 * @return String + nom presta
	 * @throws ParseException parseException
	 */
	@PostMapping(path = "prestation")
	public String createPrestation(@RequestBody PrestationCreateDTO dtoPresta) throws ParseException {
		prestaService.createPrestation(new Prestation(dtoPresta.getNom(),
				new SimpleDateFormat(FORMATDATE).parse(dtoPresta.getDebutPresta()),
				new SimpleDateFormat(FORMATDATE).parse(dtoPresta.getFinPresta()), dtoPresta.getVilleDepartArrivee(),
				dtoPresta.getDestination(), dtoPresta.getNbPersonnesMax(), dtoPresta.getCommission()));
		return "Prestation creee : " + dtoPresta.getNom();
	}

	/**
	 * @param presta un objet prestation
	 * @return String + l'objet prestation
	 * @throws ParseException
	 */
	@PutMapping(path = "prestation")
	public String updatePrestation(@RequestBody Prestation presta)
			throws ParseException {
		presta = prestaService.updatePrestation(presta);
		return "Prestation modifiee : " + presta;
	}

	/**
	 * @param id id de la prestation
	 * @return nom de la presta + String
	 */
	@DeleteMapping(path = "prestation/{id}")
	public String deletePrestation(@PathVariable Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		prestaService.deletePrestationById(id);
		return presta.getNom() + " a ete supprimee";
	}

	/**
	 * @return liste de prestations
	 */
	@GetMapping(path = "prestation")
	public List<Prestation> readAll() {
		return prestaService.readAllPrestation();
	}

	/**
	 * @param id id de la prestation
	 * @return String + prestation
	 */
	@GetMapping(path = "prestation/{id}")
	public Prestation readById(@PathVariable Long id) {
		return prestaService.readPrestationById(id);
	}

	/**
	 * @param debut date de debut de la presta
	 * @param fin   date de fin de la presta
	 * @return String + liste de prestation
	 * @throws ParseException
	 */
	@PostMapping(path = "prestation/{prestadto}")
	public List<Prestation> readByDatesDePresta(@RequestBody PrestationCreateDTO prestadto) throws ParseException {
		return prestaService.readByDebutPrestaAndFinPresta(
				new SimpleDateFormat(FORMATDATE).parse(prestadto.getDebutPresta()),
				new SimpleDateFormat(FORMATDATE).parse(prestadto.getFinPresta()));
	}

	/**
	 * @param villeResidence ville de residence
	 * @param destination    ville de destination
	 * @return String + liste de prestations
	 */
	@GetMapping(path = "prestation/{villeResidence}/conf/{destination}")
	public List<Prestation> readByResidenceAndDestinationPresta(@PathVariable("villeResidence") String villeResidence,
			@PathVariable("destination") String destination) {
		return prestaService.readByVilleDepartArriveeAndDestination(villeResidence, destination);
	}

	/**
	 * @param presta dto presta
	 * @return le prix total combine entre le prix transport + logement + activite
	 *         et en comptant la commission de l'agence
	 * @throws ParseException parseException
	 */
	@PostMapping(path = "prestation/calcul/{presta}")
	public String calculPrixTotal(@RequestBody PrestationCreateDTO presta) throws ParseException {
		if (presta.getDebutPresta().charAt(2) != '/' && presta.getFinPresta().charAt(2) != '/') {
			String jd = presta.getDebutPresta().substring(8, 10);
			int a = Integer.parseInt(jd) + 1;
			if (a < 1) {
				jd = "0" + Integer.toString(a);
			} else {
				jd = Integer.toString(a);
			}
			String md = presta.getDebutPresta().substring(5, 7);
			String yd = presta.getDebutPresta().substring(0, 4);
			String jf = presta.getFinPresta().substring(8, 10);
			a = Integer.parseInt(jf) + 1;
			if (a < 1) {
				jf = "0" + Integer.toString(a);
			} else {
				jf = Integer.toString(a);
			}
			String mf = presta.getFinPresta().substring(5, 7);
			String yf = presta.getFinPresta().substring(0, 4);
			presta.setDebutPresta(jd + "/" + md + "/" + yd);
			presta.setFinPresta(jf + "/" + mf + "/" + yf);
		}
		Prestation p = prestaService
				.readByDebutPrestaAndFinPresta(new SimpleDateFormat(FORMATDATE).parse(presta.getDebutPresta()),
						new SimpleDateFormat(FORMATDATE).parse(presta.getFinPresta()))
				.get(0);
		prestaService.calculPrixTotal(p);
		prestaService.updatePrestation(p);
		return Double.toString(p.getPrixTotal());
	}
}
