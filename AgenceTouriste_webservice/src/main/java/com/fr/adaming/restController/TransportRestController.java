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
import com.fr.adaming.service.ITransportService;

@RestController
@RequestMapping(path = "api/")
public class TransportRestController {

	//Methodes CRUD Transport
	@Autowired
	private ITransportService transportService;
	
	@RequestMapping(path="transport", method=RequestMethod.POST)
	public String createTransport(@RequestBody TransportCreateDTO trans) {
		transportService.createTransport(new Transport(trans.getPrestaTrans(), trans.getVilleArriveeTrans(),
				trans.getVilleDepartTrans(), trans.getPrix(), trans.getTypeTrans()));
		return "Transport cree : " + trans;
	}
	
	@RequestMapping(path="transport", method=RequestMethod.PUT)
	public String updateTransport(@RequestBody Transport trans) {
		trans = transportService.updateTransport(trans);
		return "Transport modifie : " + trans;
	}
	
	@RequestMapping(path="transport", method=RequestMethod.GET)
	public String readAllTransport() {
		List<Transport> listtrans = transportService.readAllTransport();
		return "Liste des Transports : " + listtrans;
	}
	
	@RequestMapping(path="transport/{id}", method=RequestMethod.GET)
	public String readByIdTransport(@PathVariable Long id) {
		Transport trans = transportService.readTransportById(id);
		return "Transports : " + trans;
	}
	
	@RequestMapping(path="transport/{id}", method=RequestMethod.DELETE)
	public String deleteTransport(@PathVariable Long id) {
		Transport trans = transportService.readTransportById(id);
		transportService.deleteTransportById(id);
		return trans.getPrestaTrans() + " a ete supprime";
	}
	
	@RequestMapping(path="transport/{presta}", method=RequestMethod.GET)
	public String readTransportByPresta(@PathVariable String presta) {
		List<Transport> listtrans = transportService.readTransportByPrestaTrans(presta);
		return "Transports de " + presta + " : \n" + listtrans;
	}
}
