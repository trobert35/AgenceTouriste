package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IPrestationDao;
import com.fr.adaming.entity.Prestation;

@Service
public class PrestationService implements IProduitService<Prestation> {
	
	/**
	 * @author Thomas S
	 * @author Maxime
	 */

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
		return "Prestation supprimée";
	}

	public Prestation create(Prestation obj) {
		if (obj.getId() == null || obj.getId() == 0 || !dao.existsById(obj.getId())) {
			System.out.println("Prestation créée");
			return dao.save(obj);

		} else {
			System.out.println("Prestation non créée car id null, id = 0 ou id déjà existant");
			return null;
		}
	}

	public Prestation update(Prestation obj) {
		if (obj.getId() != null && obj.getId() != 0 && dao.existsById(obj.getId())) {
			System.out.println("Prestation modifiée");
			return dao.save(obj);

		} else {
			System.out.println("Prestation non modifiée");
			return null;
		}
	}
}
