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
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.TransportCreateDTO;
import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;
import com.fr.adaming.service.ITransportService;

/**
 * @author Mohamed
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/")
@CrossOrigin
public class TransportRestController {

	// Methodes CRUD Transport
	@Autowired
	private ITransportService transportService;

	/**
	 * @param trans transportCreateDTO
	 * @return String + attributs du dto
	 */
	@PostMapping(path = "transport")
	public String createTransport(@RequestBody TransportCreateDTO trans) {
		transportService.createTransport(new Transport(trans.getPrestaTrans(), trans.getVilleArriveeTrans(),
				trans.getVilleDepartTrans(), trans.getPrix(), trans.getTypeTrans()));
		return "Transport cree : " + trans.toString();
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
	public List<Transport> readAllTransport() {

		return transportService.readAllTransport();
	}

	/**
	 * @param id l'id du transport
	 * @return le transport par id
	 */
	@GetMapping(path = "transport/id/{id}")
	public Transport readByIdTransport(@PathVariable Long id) {

		return transportService.readTransportById(id);
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
	public List<Transport> readByPrixTransport(@RequestParam(value = "prixTrans") Double prix) {

		return transportService.readByPrix(prix);
	}

	/**
	 * @param type type de transport
	 * @return String + liste des transports
	 */
	@GetMapping(path = "transport/{typeTrans}")
	public List<Transport> readByTypeTransport(@RequestParam(value = "typeTrans") typeTransEnum type) {

		return transportService.readByTypeTrans(type);
	}

	/**
	 * @param presta nom de la prestation
	 * @return String + liste de transports selon la prestation recherchee
	 */
	@GetMapping(path = "transport/prestataire/{presta}")
	public List<Transport> readTransportByPresta(@PathVariable String presta) {

		return transportService.readTransportByPrestaTrans(presta);
	}

}
