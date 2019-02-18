package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IActiviteDao;
import com.fr.adaming.entity.Activite;

@Service
public class ActiviteService implements IActiviteService {
	/**
	 * @author Thomas S
	 * @author Maxime
	 */

	@Autowired
	private IActiviteDao dao;

	public Activite create(Activite activite) {
		if (activite.getId() == null || activite.getId() == 0 || !dao.existsById(activite.getId())) {
			System.out.println("Activite creee");
			return dao.save(activite);
		} else {
			System.out.println("Activite non creee car id null, id = 0 ou id deja existant");
			return null;
		}
	}

	public Activite update(Activite activite) {
		if (activite.getId() != null && activite.getId() != 0 && dao.existsById(activite.getId())) {
			System.out.println("Activite modifiee");
			return dao.save(activite);
		} else {
			System.out.println("Activite non modifiee");
			return null;

		}
	}

	public List<Activite> readAll() {
		return dao.findAll();
	}

	public Activite readById(Long id) {
		return dao.findById(id).get();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "Activite supprimee";
	}

	public List<Activite> readByNomPrestaAct(String nomPrestaAct) {
		return dao.findByNomPrestaAct(nomPrestaAct);
	}

}
