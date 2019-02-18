package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IActiviteDao;
import com.fr.adaming.entity.Activite;

@Service
public class ActiviteService implements IProduitService<Activite> {

	@Autowired
	private IActiviteDao dao;

	public Activite create(Activite activite) {
		if (activite.getId()==null || activite.getId()==0) {
			System.out.println("activite cree");
			return dao.save(activite);
		}else {
			System.out.println("activite non cree car existe deja pour cer id");
			return null;
		}
	}

	public Activite update(Activite activite) {
		if (activite.getId() !=null && activite.getId() !=0 && dao.existsById(activite.getId())) {
			System.out.println("activite modifiee");
			return dao.save(activite);
		} else {
			System.out.println("activite non modifiee");
			return null;

		}
	}

	public Activite readById(Long id) {
		return dao.findById(id).get();
	}
	
	public List<Activite> readByNomPrestaAct(String nomPrestaAct) {
		return dao.findByNomPrestaAct(nomPrestaAct);
	}

	public List<Activite> readAll() {
		return dao.findAll();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "activite supprimee";
	}


}
