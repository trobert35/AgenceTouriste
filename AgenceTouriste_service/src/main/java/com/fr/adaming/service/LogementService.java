package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ILogementDao;
import com.fr.adaming.entity.Logement;

@Service
public class LogementService implements ILogementService {
	
	/**
	 * @author Thomas S
	 * @author Maxime
	 */

	@Autowired
	private ILogementDao dao;

	public Logement create(Logement logement) {
		if (logement.getId() == null || logement.getId() == 0 || !dao.existsById(logement.getId())) {
			System.out.println("Logement crée");
			return dao.save(logement);
		} else {
			System.out.println("Logement non crée car id null, id = 0 ou id déjà existant");
			return null;
		}
	}

	public Logement update(Logement logement) {
		if (logement.getId() != null && logement.getId() != 0 && dao.existsById(logement.getId())) {
			System.out.println("logement modifie");
			return dao.save(logement);
		} else {
			System.out.println("logement non modifie");
			return null;
		}
	}

	public List<Logement> readAll() {
		return dao.findAll();
	}

	public Logement readById(Long id) {
		return dao.findById(id).get();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "logement supprime";
	}

	public List<Logement> readByPrestaLog(String prestaLog) {
		return dao.findByPrestaLog(prestaLog);
	}

}
