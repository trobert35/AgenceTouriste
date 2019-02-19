package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Transport;
import com.fr.adaming.entity.User;
import com.fr.adaming.enumeration.typeTransEnum;

public interface ITransportDao extends JpaRepository<Transport, Long> {
	/**
	 * @author Claire
	 * @param prestaTrans
	 * @return
	 */

	/**
	 * @param prestaTrans
	 * @return une liste de transports selon le nom du prestataire
	 */
	public List<Transport> findByPrestaTrans(String prestaTrans);
	
	/**
	 * @param prix
	 * @return une liste de transports selon le prix
	 */
	public List<Transport> findByPrix(Double prix);
	
	/**
	 * @param typeTrans
	 * @return une liste de transports selon le type de transport
	 */
	public List<Transport> findByTypeTrans(typeTransEnum typeTrans);
	
}
