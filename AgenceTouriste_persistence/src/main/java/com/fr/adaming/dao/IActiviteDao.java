package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.enumeration.typeActEnum;

public interface IActiviteDao extends JpaRepository<Activite, Long> {
	/**
	 * @author Claire
	 * 
	 * @param nomPrestaAct
	 * @return une liste d'activités en fonction du nom du prestataire
	 */
	public List<Activite> findByNomPrestaAct(String nomPrestaAct);

	/**
	 * @param prix
	 * @return une liste d'activités en fonction de leur prix
	 */
	public List<Activite> findByPrix(Double prix);

	/**
	 * @param typeAct
	 * @return une liste d'activités en fonction de leur type
	 */
	public List<Activite> findByTypeAct(typeActEnum typeAct);
}
