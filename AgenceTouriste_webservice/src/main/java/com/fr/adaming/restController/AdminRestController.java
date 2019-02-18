package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping(path="createAdmin")
	public String createAdmin(@RequestBody Admin admin) {
		admin = adminService.createAdmin(admin);
		return admin.getNom() + " est un admin correctement cree";
	}
	
	@RequestMapping(path="updateAdmin")
	public String updateAdmin(@RequestBody Admin admin) {
		admin = adminService.updateAdmin(admin);
		return admin.getNom() + " est un admin correctement modifie";
	}
	
	@RequestMapping(path="readAdmin")
	public Admin readAdminById(@RequestBody Long id) {
		Admin admin = (Admin) adminService.readAdminById(id);
		return admin;
	}
	
	@RequestMapping(path="readAllAdmin")
	public List<User> readAllAdmin() {
		List<User> admin = adminService.readAllAdmin();
		return admin;
	}
	
	@RequestMapping(path="deleteAdmin")
	public String deleteAdmin(@RequestBody Long id) {
		Admin admin = (Admin) adminService.readAdminById(id);
		adminService.deleteAdminById(id);
		return admin.getNom() + " est un admin correctement supprime";
	}
	
	//Methode CRUD Logement
	@Autowired
	private ILogementService logementService;
	
	@RequestMapping(path="createLogement")
	public String createLogement(@RequestBody Logement logement) {
		logementService.createLogement(logement);
		return logement.getNom() + " est un admin correctement supprime";
	}
	
	@RequestMapping(path="updateLogement")
	public String updateLogement(@RequestBody Logement logement) {
		logementService.updateLogement(logement);
		return logement.getNom() + " est un admin correctement modifie";
	}
	
	@RequestMapping(path="readAllLogement")
	public List<Logement> readAllLogement() {
		List<Logement> listLogement = logementService.readAllLogement();
		return listLogement;
	}
	
	@RequestMapping(path="readByIdLogement")
	public String readByIdLogement(@RequestBody Long id) {
		Logement logement = logementService.readLogementById(id);
		return "Logement : " + logement;
	}
	
	@RequestMapping(path="deleteLogement")
	public String deleteLogement(@RequestBody Long id) {
		Logement logement = logementService.readLogementById(id);
		logementService.deleteLogementById(id);
		return logement.getNom() + " a correctement ete supprime";
	}
	
	@RequestMapping(path="readLogementByPresta")
	public String readLogementByPresta(@RequestBody String prestaLog) {
		List<Logement> listlogement = logementService.readLogementByPrestaLog(prestaLog);
		return "Logements fournis par " + prestaLog + " :\n" + listlogement;
	}
	
	
	//Methode CRUD Prestation
	@Autowired
	private IProduitService<Prestation> prestaService;
		
	@RequestMapping(path="createPrestation")
	public String createPrestation(@RequestBody Prestation presta) {
		presta = prestaService.createPrestation(presta);
		return "Prestation cree : " + presta;
	}
	
	@RequestMapping(path="updatePrestation")
	public String updatePrestation(@RequestBody Prestation presta) {
		presta = prestaService.updatePrestation(presta);
		return "Prestation modifiee : " + presta;
	}
	
	@RequestMapping(path="readByIdPrestation")
	public String readByIdPrestation(@RequestBody Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		return "Prestation : " + presta;
	}
	
	@RequestMapping(path="readAllPrestation")
	public String readAllPrestation() {
		List<Prestation> listpresta = prestaService.readAllPrestation();
		return "Liste des Prestations : " + listpresta;
	}
	
	@RequestMapping(path="deletePrestation")
	public String deletePrestation(@RequestBody Long id) {
		Prestation presta = prestaService.readPrestationById(id);
		prestaService.deletePrestationById(id);
		return presta.getNom() + " a ete supprimee";
	}
	
	
	//Methodes CRUD Transport
	@Autowired
	private ITransportService transportService;
	
	@RequestMapping(path="createTransport")
	public String createTransport(@RequestBody Transport trans) {
		trans = transportService.createTransport(trans);
		return "Transport cree : " + trans;
	}
	
	@RequestMapping(path="updateTransport")
	public String updateTransport(@RequestBody Transport trans) {
		trans = transportService.updateTransport(trans);
		return "Transport modifie : " + trans;
	}
	
	@RequestMapping(path="readAllTransport")
	public String readAllTransport() {
		List<Transport> listtrans = transportService.readAllTransport();
		return "Liste des Transports : " + listtrans;
	}
	
	@RequestMapping(path="readByIdTransport")
	public String readByIdTransport(@RequestBody Long id) {
		Transport trans = transportService.readTransportById(id);
		return "Transports : " + trans;
	}
	
	@RequestMapping(path="deleteTransport")
	public String deleteTransport(@RequestBody Long id) {
		Transport trans = transportService.readTransportById(id);
		transportService.deleteTransportById(id);
		return trans.getPrestaTrans() + " a ete supprime";
	}
	
	@RequestMapping(path="readTransportByPresta")
	public String readTransportByPresta(@RequestBody String presta) {
		List<Transport> listtrans = transportService.readTransportByPrestaTrans(presta);
		return "Transports de " + presta + " : \n" + listtrans;
	}
	
	//Methodes CRUD Activite
	
	@Autowired
	private IActiviteService activiteService;
	
	@RequestMapping(path="createActivite")
	public String createActivite(@RequestBody Activite act) {
		act = activiteService.createActivite(act);
		return "Activite cree : " + act;
	}
	
	@RequestMapping(path="updateActivite")
	public String updateActivite(@RequestBody Activite act) {
		act = activiteService.updateActivite(act);
		return "Activite modifie : " + act;
	}
	
	@RequestMapping(path="readAllActivite")
	public String readAllActivite() {
		List<Activite> listact = activiteService.readAllActivite();
		return "Liste Activite : " + listact;
	}
	
	@RequestMapping(path="readByIdActivite")
	public String readByIdActivite(@RequestBody Long id) {
		Activite act = activiteService.readActiviteById(id);
		return "Activite : " + act;
	}
	
	@RequestMapping(path="deleteActivite")
	public String deleteActivite(@RequestBody Long id) {
		Activite act = activiteService.readActiviteById(id);
		activiteService.deleteActiviteById(id);
		return act.getNom() + " a ete supprime";
	}
	
	@RequestMapping(path="readActiviteByPresta")
	public String readActiviteByPresta(@RequestBody String presta) {
		List<Activite> listact = activiteService.readActiviteByNomPrestaAct(presta);
		return "Activites proposee par " + presta + ":\n" + listact;
	}
	
}
