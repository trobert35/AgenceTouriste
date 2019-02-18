package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Activite;

public interface IActiviteService {

	/**
	 * @author Thomas S
	 **/

	public Activite createActivite(Activite activite);

	public Activite updateActivite(Activite activite);

	public Activite readActiviteById(Long id);

	public List<Activite> readAllActivite();

	public String deleteActiviteById(Long id);
	
	public List<Activite> readActiviteByNomPrestaAct(String nomPrestaAct);

}
