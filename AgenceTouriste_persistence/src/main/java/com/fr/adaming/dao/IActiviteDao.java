package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.entity.User;
import com.fr.adaming.enumeration.typeActEnum;

import java.lang.Double;


public interface IActiviteDao extends JpaRepository<Activite, Long> {
	/**
	 * @author Claire
	 * @param nomPrestaAct
	 * @return
	 */

	public List<Activite> findByNomPrestaAct(String nomPrestaAct);
	public List<Activite> findByPrix(Double prix);
	public List<Activite> findByTypeAct(typeActEnum typeAct);
}
