package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.Transport;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IActiviteService;
import com.fr.adaming.service.IAdminService;
import com.fr.adaming.service.ILogementService;
import com.fr.adaming.service.IProduitService;
import com.fr.adaming.service.ITransportService;

@RestController
@RequestMapping(path="Admin/")
public class AdminRestController implements IAdminRestController{

	@Autowired
	private IAdminService adminService;

	
	// Methodes CRUD Admin
	@RequestMapping(path="createAdmin", method=RequestMethod.POST)
	public String createAdmin(@RequestBody Admin admin) {
		admin = adminService.createAdmin(admin);
		return admin.getNom() + " est un admin correctement cree";
	}
	
	@RequestMapping(path="updateAdmin", method=RequestMethod.POST)
	public String updateAdmin(@RequestBody Admin admin) {
		admin = adminService.updateAdmin(admin);
		return admin.getNom() + " est un admin correctement modifie";
	}
	
	@RequestMapping(path="readAdmin", method=RequestMethod.GET)
	public Admin readAdminById(@RequestBody Long id) {
		Admin admin = (Admin) adminService.readAdminById(id);
		return admin;
	}
	
	@RequestMapping(path="readAllAdmin", method=RequestMethod.GET)
	public List<User> readAllAdmin() {
		List<User> admin = adminService.readAllAdmin();
		return admin;
	}
	
	@RequestMapping(path="deleteAdmin", method=RequestMethod.GET)
	public String deleteAdmin(@RequestBody Long id) {
		Admin admin = (Admin) adminService.readAdminById(id);
		adminService.deleteAdminById(id);
		return admin.getNom() + " est un admin correctement supprime";
	}
	
	//Methode CRUD Logement
	@Autowired
	private ILogementService logementService;
	
	@RequestMapping(path="createLogement", method=RequestMethod.POST)
	public String createLogement(@RequestBody Logement logement) {
		logementService.createLogement(logement);
		return logement.getNom() + " est un admin correctement supprime";
	}
	
	@RequestMapping(path="updateLogement", method=RequestMethod.POST)
	public String updateLogement(@RequestBody Logement logement) {
		logementService.updateLogement(logement);
		return logement.getNom() + " est un admin correctement modifie";
	}
	
	@RequestMapping(path="readAllLogement", method=RequestMethod.GET)
	public List<Logement> readAllLogement() {
		List<Logement> listLogement = logementService.readAllLogement();
		return listLogement;
	}
	
	@RequestMapping(path="readByIdLogement", method=RequestMethod.GET)
	public String readByIdLogement(@RequestBody Long id) {
		Logement logement = logementService.readLogementById(id);
		return "Logement : " + logement;
	}
	
	@RequestMapping(path="deleteLogement", method=RequestMethod.GET)
	public String deleteLogement(@RequestBody Long id) {
		Logement logement = logementService.readLogementById(id);
		logementService.deleteLogementById(id);
		return logement.getNom() + " a correctement ete supprime";
	}
	
	@RequestMapping(path="readLogementByPresta", method=RequestMethod.GET)
	public String readLogementByPresta(@RequestBody String prestaLog) {
		List<Logement> listlogement = logementService.readLogementByPrestaLog(prestaLog);
		return "Logements fournis par " + prestaLog + " :\n" + listlogement;
	}
	
	
	//Methode CRUD Prestation
	@Autowired
	private IProduitService<Prestation> prestaService;
		
	@RequestMapping(path="createPrestation", method=RequestMethod.POST)
	public String createPrestation(@RequestBody Prestation presta) {
		presta = prestaService.createPrestation(presta);
		return "Prestation cree : " + presta;
	}
	
	@RequestMapping(path="updatePrestation", method=RequestMethod.POST)
	public String updatePrestation(@RequestBody Prestation presta) {
		presta = prestaService.updatePrestation(presta);
		return "Prestation modifiee : " + presta;
	}
	
	@RequestMapping(path="readByIdPrestation", method=RequestMethod.GET)
	public String readByIdPrestation(@RequestBody Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		return "Prestation : " + presta;
	}
	
	@RequestMapping(path="readAllPrestation", method=RequestMethod.GET)
	public String readAllPrestation() {
		List<Prestation> listpresta = prestaService.readAllPrestation();
		return "Liste des Prestations : " + listpresta;
	}
	
	@RequestMapping(path="deletePrestation", method=RequestMethod.GET)
	public String deletePrestation(@RequestBody Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		prestaService.deletePrestationById(id);
		return presta.getNom() + " a ete supprimee";
	}
	
	
	//Methodes CRUD Transport
	@Autowired
	private ITransportService transportService;
	
	@RequestMapping(path="createTransport", method=RequestMethod.POST)
	public String createTransport(@RequestBody Transport trans) {
		trans = transportService.createTransport(trans);
		return "Transport cree : " + trans;
	}
	
	@RequestMapping(path="updateTransport", method=RequestMethod.POST)
	public String updateTransport(@RequestBody Transport trans) {
		trans = transportService.updateTransport(trans);
		return "Transport modifie : " + trans;
	}
	
	@RequestMapping(path="readAllTransport", method=RequestMethod.GET)
	public String readAllTransport() {
		List<Transport> listtrans = transportService.readAllTransport();
		return "Liste des Transports : " + listtrans;
	}
	
	@RequestMapping(path="readByIdTransport", method=RequestMethod.GET)
	public String readByIdTransport(@RequestBody Long id) {
		Transport trans = transportService.readTransportById(id);
		return "Transports : " + trans;
	}
	
	@RequestMapping(path="deleteTransport", method=RequestMethod.GET)
	public String deleteTransport(@RequestBody Long id) {
		Transport trans = transportService.readTransportById(id);
		transportService.deleteTransportById(id);
		return trans.getPrestaTrans() + " a ete supprime";
	}
	
	@RequestMapping(path="readTransportByPresta", method=RequestMethod.GET)
	public String readTransportByPresta(@RequestBody String presta) {
		List<Transport> listtrans = transportService.readTransportByPrestaTrans(presta);
		return "Transports de " + presta + " : \n" + listtrans;
	}
	
	//Methodes CRUD Activite
	
	@Autowired
	private IActiviteService activiteService;
	
	@RequestMapping(path="createActivite", method=RequestMethod.POST)
	public String createActivite(@RequestBody Activite act) {
		act = activiteService.createActivite(act);
		return "Activite cree : " + act;
	}
	
	@RequestMapping(path="updateActivite", method=RequestMethod.POST)
	public String updateActivite(@RequestBody Activite act) {
		act = activiteService.updateActivite(act);
		return "Activite modifie : " + act;
	}
	
	@RequestMapping(path="readAllActivite", method=RequestMethod.GET)
	public String readAllActivite() {
		List<Activite> listact = activiteService.readAllActivite();
		return "Liste Activite : " + listact;
	}
	
	@RequestMapping(path="readByIdActivite", method=RequestMethod.GET)
	public String readByIdActivite(@RequestBody Long id) {
		Activite act = activiteService.readActiviteById(id);
		return "Activite : " + act;
	}
	
	@RequestMapping(path="deleteActivite", method=RequestMethod.GET)
	public String deleteActivite(@RequestBody Long id) {
		Activite act = activiteService.readActiviteById(id);
		activiteService.deleteActiviteById(id);
		return act.getNom() + " a ete supprime";
	}
	
	@RequestMapping(path="readActiviteByPresta", method=RequestMethod.GET)
	public String readActiviteByPresta(@RequestBody String presta) {
		List<Activite> listact = activiteService.readActiviteByNomPrestaAct(presta);
		return "Activites proposee par " + presta + ":\n" + listact;
	}
	
}
