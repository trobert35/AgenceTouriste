package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dao.IPrestationDao;
import com.fr.adaming.entity.Prestation;

public class PrestationService implements IProduitService<Prestation> {

	@Autowired
	private IPrestationDao dao;

	public Prestation readById(Long id) {
		return dao.findById(id).get();
	}

	public List<Prestation> readAll() {
		return dao.findAll();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "prestation supprimee";
	}

	public Prestation create(Prestation obj) {
		if (obj.getId() == null || obj.getId() == 0) {
			System.out.println("prestation cree");
			return dao.save(obj);

		} else {
			System.out.println("prestation non cree car existe deja pour cer id");
			return null;
		}
	}

	public Prestation update(Prestation obj) {
		if (obj.getId() == null || obj.getId() == 0) {
			System.out.println("prestation cree");
			return dao.save(obj);

		} else {
			System.out.println("prestation non cree car existe deja pour cer id");
			return null;
		}
	}
}
