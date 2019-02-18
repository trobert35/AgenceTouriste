package com.fr.adaming.service;

import java.util.List;

public interface IProduitService<T> {
	
	/**
	 * @author Thomas S
	 */

	public T createPrestation (T obj);
	
	public T updatePrestation (T obj);

	public List<T> readAllPrestation();
	
	public T readPrestationById(Long id);
	
	public String deletePrestationById(Long id);

}
