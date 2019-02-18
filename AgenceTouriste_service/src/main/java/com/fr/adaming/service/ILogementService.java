package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Logement;

public interface ILogementService extends IProduitService<Logement>{

	/**
	 * @author Thomas S
	 * **/
	
	public List<Logement> readByPrestaLog(String nom);
}
