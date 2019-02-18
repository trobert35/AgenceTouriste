package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Activite;

public interface IActiviteService extends IProduitService<Activite>{

	public List<Activite>readByNomPrestaAct(String nom);
	
	/**
	 * @author Thomas S
	 * **/
	
}
