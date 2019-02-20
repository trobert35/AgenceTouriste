package com.fr.adaming.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Prestation;

public interface IPrestationDao extends JpaRepository<Prestation, Long> {
	/**
	 * @author Claire
	 */

	/**
	 * @param debutPresta
	 * @param finPresta
	 * @return une liste de prestations selon la date de début et la date de fin de
	 *         prestation
	 */
	public List<Prestation> findByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta);

	/**
	 * @param villeDepartArrivee
	 * @param destination
	 * @return une liste de prestations selon la ville de départ(qui est aussi celle
	 *         de retour) et la ville de destination
	 */
	public List<Prestation> findByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination);
}
