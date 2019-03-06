package com.fr.adaming.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Prestation;

/**
 * @author Claire
 *
 */
public interface IPrestationDao extends JpaRepository<Prestation, Long> {

	/**
	 * @param debutPresta debut prestation
	 * @param finPresta   fin prestation
	 * @return une liste de prestations selon la date de début et la date de fin de
	 *         prestation
	 */
	public List<Prestation> findByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta);

	/**
	 * @param villeDepartArrivee ville de depart et aussi d'arrivee
	 * @param destination        ville de destination
	 * @return une liste de prestations selon la ville de départ(qui est aussi celle
	 *         de retour) et la ville de destination
	 */
	public List<Prestation> findByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination);
	
	/**
	 * @param destination de la prestation
	 * @return une liste de prestations selon la destination
	 */
	public List<Prestation> findByDestination(String destination);
}
