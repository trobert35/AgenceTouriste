package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.enumeration.typeActEnum;

/**
 * @author Thomas S
 *
 */
public interface IActiviteService {

	/**
	 * @param activite Activite a creer
	 * @return Activite de la base
	 */
	public Activite createActivite(Activite activite);

	/**
	 * @param activite Activite a modifier
	 * @return Activite modifie de la base
	 */
	public Activite updateActivite(Activite activite);

	/**
	 * @param id id de l activite
	 * @return Activite lie a l'id
	 */
	public Activite readActiviteById(Long id);

	/**
	 * @return List des Activite de la base
	 */
	public List<Activite> readAllActivite();

	/**
	 * @param id id de l activite
	 * @return String de confirmation
	 */
	public String deleteActiviteById(Long id);

	/**
	 * @param nomPrestaAct
	 * @return List des Activite lie a la Prestation
	 */
	public List<Activite> readActiviteByNomPrestaAct(String nomPrestaAct);

	/**
	 * @param prix prix de l activite
	 * @return List des Activite lie au prix
	 */
	public List<Activite> readActiviteByPrix(Double prix);

	/**
	 * @param typeAct type de l Activite
	 * @return List des Activite lie au type
	 */
	public List<Activite> readActiviteByTypeAct(typeActEnum typeAct);

}
