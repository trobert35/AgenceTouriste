package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Transport;

public interface ITransportService extends IProduitService<Transport>{

	/**
	 * @author Thomas S
	 * **/
	
	public List<Transport> readByPrestaTrans(String nom); 

	
}
