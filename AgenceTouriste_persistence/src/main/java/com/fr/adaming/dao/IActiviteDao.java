package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.entity.User;


public interface IActiviteDao extends JpaRepository<Activite, Long> {
	/**
	 * @author Claire
	 * @param nomPrestaAct
	 * @return
	 */

	public Activite findByNomPrestaAct(String nomPrestaAct);
}
