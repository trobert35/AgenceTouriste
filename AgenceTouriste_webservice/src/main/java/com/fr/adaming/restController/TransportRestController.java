package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.TransportCreateDTO;
import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;
import com.fr.adaming.service.ITransportService;

/**
 * @author Mohamed EL AGREBI
 *		   Thomas S
 */
@RestController
@RequestMapping(path = "api/")
public class TransportRestController {

	// Methodes CRUD Transport
	@Autowired
	private ITransportService transportService;

	/**
	 * @param trans transportCreateDTO
	 * @return String + attributs du dto
	 */
	@RequestMapping(path = "transport", method = RequestMethod.POST)
	public String createTransport(@RequestBody TransportCreateDTO trans) {
		transportService.createTransport(new Transport(trans.getPrestaTrans(), trans.getVilleArriveeTrans(),
				trans.getVilleDepartTrans(), trans.getPrix(), trans.getTypeTrans()));
		return "Transport cree : " + trans;
	}

	/**
	 * @param trans objet transport
	 * @return String + objet
	 */
	@RequestMapping(path = "transport", method = RequestMethod.PUT)
	public String updateTransport(@RequestBody Transport trans) {
		trans = transportService.updateTransport(trans);
		return "Transport modifie : " + trans;
	}

	/**
	 * @return la liste de transports
	 */
	@RequestMapping(path = "transport", method = RequestMethod.GET)
	public String readAllTransport() {
		List<Transport> listtrans = transportService.readAllTransport();
		return "Liste des Transports : " + listtrans;
	}

	/**
	 * @param id l'id du transport
	 * @return le transport par id
	 */
	@RequestMapping(path = "transport/{id}", method = RequestMethod.GET)
	public String readByIdTransport(@PathVariable Long id) {
		Transport trans = transportService.readTransportById(id);
		return "Transport : " + trans;
	}

	/**
	 * @param id l'id du transport
	 * @return String
	 */
	@RequestMapping(path = "transport/{id}", method = RequestMethod.DELETE)
	public String deleteTransport(@PathVariable Long id) {
		Transport trans = transportService.readTransportById(id);
		transportService.deleteTransportById(id);
		return trans.getPrestaTrans() + " a ete supprime";
	}

	/**
	 * @param prix prix du transport
	 * @return String + liste des transports
	 */
	@RequestMapping(path = "transport/{prixTrans}", method = RequestMethod.GET)
	public String readByPrixTransport(@PathVariable Double prix) {
		List<Transport> translist = transportService.readByPrix(prix);
		return "Transport(s) au prix de " + prix + " : \n" + translist;
	}

	/**
	 * @param type type de transport
	 * @return String + liste des transports
	 */
	@RequestMapping(path = "transport/{typeTrans}", method = RequestMethod.GET)
	public String readByTypeTransport(@PathVariable typeTransEnum type) {
		List<Transport> translist = transportService.readByTypeTrans(type);
		return "Transport(s) de type " + type + " : \n" + translist;
	}

	/**
	 * @param presta nom de la prestation
	 * @return String + liste de transports selon la prestation recherchee
	 */
	@RequestMapping(path = "transport/{presta}", method = RequestMethod.GET)
	public String readTransportByPresta(@PathVariable String presta) {
		List<Transport> listtrans = transportService.readTransportByPrestaTrans(presta);
		return "Transport(s) de " + presta + " : \n" + listtrans;
	}

}
