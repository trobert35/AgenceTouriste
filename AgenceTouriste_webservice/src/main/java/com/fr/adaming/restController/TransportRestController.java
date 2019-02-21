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

import com.fr.adaming.dto.TransportCreateDTO;
import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;
import com.fr.adaming.service.ITransportService;

/**
 * @author Mohamed EL AGREBI
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/")
public class TransportRestController {

	// Methodes CRUD Transport
	@Autowired
	private ITransportService transportService;

	private static final String SAUT = " : \n";

	/**
	 * @param trans transportCreateDTO
	 * @return String + attributs du dto
	 */
	@PostMapping(path = "transport")
	public String createTransport(@RequestBody TransportCreateDTO trans) {
		transportService.createTransport(new Transport(trans.getPrestaTrans(), trans.getVilleArriveeTrans(),
				trans.getVilleDepartTrans(), trans.getPrix(), trans.getTypeTrans()));
		return "Transport cree : " + trans;
	}

	/**
	 * @param trans objet transport
	 * @return String + objet
	 */
	@PutMapping(path = "transport")
	public String updateTransport(@RequestBody Transport trans) {
		trans = transportService.updateTransport(trans);
		return "Transport modifie : " + trans;
	}

	/**
	 * @return la liste de transports
	 */
	@GetMapping(path = "transport")
	public String readAllTransport() {
		List<Transport> listtrans = transportService.readAllTransport();
		return "Liste des Transports : " + listtrans;
	}

	/**
	 * @param id l'id du transport
	 * @return le transport par id
	 */
	@GetMapping(path = "transport/{id}")
	public String readByIdTransport(@PathVariable Long id) {
		Transport trans = transportService.readTransportById(id);
		return "Transport : " + trans;
	}

	/**
	 * @param id l'id du transport
	 * @return String
	 */
	@DeleteMapping(path = "transport/{id}")
	public String deleteTransport(@PathVariable Long id) {
		Transport trans = transportService.readTransportById(id);
		transportService.deleteTransportById(id);
		return trans.getPrestaTrans() + " a ete supprime";
	}

	/**
	 * @param prix prix du transport
	 * @return String + liste des transports
	 */
	@GetMapping(path = "transport/{prixTrans}")
	public String readByPrixTransport(@PathVariable Double prix) {
		List<Transport> translist = transportService.readByPrix(prix);
		return "Transport(s) au prix de " + prix + SAUT + translist;
	}

	/**
	 * @param type type de transport
	 * @return String + liste des transports
	 */
	@GetMapping(path = "transport/{typeTrans}")
	public String readByTypeTransport(@PathVariable typeTransEnum type) {
		List<Transport> translist = transportService.readByTypeTrans(type);
		return "Transport(s) de type " + type + SAUT + translist;
	}

	/**
	 * @param presta nom de la prestation
	 * @return String + liste de transports selon la prestation recherchee
	 */
	@GetMapping(path = "transport/{presta}")
	public String readTransportByPresta(@PathVariable String presta) {
		List<Transport> listtrans = transportService.readTransportByPrestaTrans(presta);
		return "Transport(s) de " + presta + SAUT + listtrans;
	}

}
