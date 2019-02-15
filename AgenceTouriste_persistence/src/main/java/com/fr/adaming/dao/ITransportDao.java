package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Transport;
import com.fr.adaming.entity.User;
import com.fr.adaming.enumeration.typeTransEnum;

public interface ITransportDao extends JpaRepository<Transport, Long> {

	public List<Transport> findByPrestaTrans(String prestaTrans);
	public List<Transport> findByPrix(Double prix);
	public List<Transport> findByTypeTrans(typeTransEnum typeTrans);
	
}
