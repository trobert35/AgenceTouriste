package com.fr.adaming.service;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IProduitService<T> {

	public T create (T obj);
	
	public T update (T obj);

	public List<T> readAll();
	
	public T readById(Long id);
	
	public String deleteById(Long id);

}
