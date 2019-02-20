package com.fr.adaming.service;

import java.util.Date;
import java.util.List;

import com.fr.adaming.entity.Prestation;

/**
 * @author Thomas S
 *
 * @param <T>
 */
public interface IProduitService<T> {

	/**
	 * @param obj objet de classe Objet
	 * @return un objet de la classe Objet
	 */
	public T createPrestation(T obj);

	/**
	 * @param obj objet de classe Objet
	 * @return un objet de la classe Objet
	 */
	public T updatePrestation(T obj);

	/**
	 * @return une List d'Objet
	 */
	public List<T> readAllPrestation();

	/**
	 * @param id id de la Prestation
	 * @return un objet de la classe Objet
	 */
	public T readPrestationById(Long id);

	/**
	 * @param id id de la Prestation
	 * @return String
	 */
	public String deletePrestationById(Long id);

	/**
	 * @param debutPresta date du debut de la Prestation
	 * @param finPresta   date de la fin de la Prestation
	 * @return une List de Prestation
	 */
	public List<Prestation> readByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta);

	/**
	 * @param villeDepartArrivee nom de la ville de depart et d arrivee
	 * @param destination        nom de la destination
	 * @return une List de Prestation
	 */
	public List<Prestation> readByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination);

	/**
	 * @param prestation objet de la classe Prestation
	 */
	public void calculPrixTotal(Prestation prestation);

}
