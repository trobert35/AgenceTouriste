package com.fr.adaming.service;

import java.util.Date;
import java.util.List;

import com.fr.adaming.entity.Prestation;

public interface IProduitService<T> {
	
	/**
	 * @author Thomas S
	 */

	public T createPrestation (T obj);
	
	public T updatePrestation (T obj);

	public List<T> readAllPrestation();
	
	public T readPrestationById(Long id);
	
	public String deletePrestationById(Long id);
	
	public List<Prestation> readByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta);
	
	public List<Prestation> readByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination);

}
