package com.fr.adaming.service;

import java.util.List;

public interface IProduitService<T> {
	
	/**
	 * @author Thomas S
	 */

	public T create (T obj);
	
	public T update (T obj);

	public List<T> readAll();
	
	public T readById(Long id);
	
	public String deleteById(Long id);

}
