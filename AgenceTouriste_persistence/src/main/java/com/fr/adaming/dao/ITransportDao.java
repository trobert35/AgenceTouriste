package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Transport;
import com.fr.adaming.entity.User;

public interface ITransportDao extends JpaRepository<Transport, Long> {

	public Transport findByPrestaTrans(String prestaTrans);
}
