package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ILogementDao;
import com.fr.adaming.entity.Logement;

@Service
public class LogementService implements IProduitService<Logement>{

	@Autowired
	private ILogementDao dao;

	public Logement create(Logement logement) {
		if (logement.getId() == null || logement.getId() == 0) {
			System.out.println("logement cree");
			return dao.save(logement);
		} else {
			System.out.println("logement non cree car existe deja pour cer id");
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

	public Logement readById(Long id) {
		return dao.findById(id).get();
	}
	public List<Logement> readByPrestaLog(String prestaLog) {
		return dao.findByPrestaLog(prestaLog);
	}
	
	public List<Logement> readAll() {
		return dao.findAll();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "logement supprime";
	}

}
