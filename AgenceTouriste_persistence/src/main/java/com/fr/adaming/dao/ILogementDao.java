package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Logement;
import com.fr.adaming.entity.User;

public interface ILogementDao extends JpaRepository<Logement, Long>{
	/**
	 * @author Claire
	 * @param prestaLog
	 * @return
	 */

	public Logement findByPrestaLog(String prestaLog);
}
