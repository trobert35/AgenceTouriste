package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.enumeration.typeActEnum;

/**
 * @author Claire
 *
 */
public interface IActiviteDao extends JpaRepository<Activite, Long> {

	/**
	 * @param nomPrestaAct le nom du prestataire
	 * @return une liste d'activités en fonction du nom du prestataire
	 */
	public List<Activite> findByNomPrestaAct(String nomPrestaAct);

	/**
	 * @param prix le prix
	 * @return une liste d'activités en fonction de leur prix
	 */
	public List<Activite> findByPrix(Double prix);

	/**
	 * @param typeAct le type d'activite
	 * @return une liste d'activites en fonction de leur type
	 */
	public List<Activite> findByTypeAct(typeActEnum typeAct);
}
