package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Logement;
import com.fr.adaming.enumeration.typeLogEnum;

public interface ILogementDao extends JpaRepository<Logement, Long> {
	/**
	 * @author Claire
	 * 
	 * @param prestaLog le nom du prestataire
	 * @return une liste de logements selon le nom du prestataire
	 */
	public List<Logement> findByPrestaLog(String prestaLog);

	/**
	 * @param ville la ville
	 * @return une liste de logements selon la ville
	 */
	public List<Logement> findByVille(String ville);

	/**
	 * @param typeLog le type de logement
	 * @return une liste de logements selon le type de logement
	 */
	public List<Logement> findByTypeLog(typeLogEnum typeLog);

	/**
	 * @param prix le prix
	 * @return une liste de logements selon le prix
	 */
	public List<Logement> findByPrix(double prix);
}
