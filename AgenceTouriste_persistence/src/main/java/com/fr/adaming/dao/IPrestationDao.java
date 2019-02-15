package com.fr.adaming.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Prestation;

public interface IPrestationDao extends JpaRepository<Prestation, Long> {

	public List<Prestation> findByDebutPrestaAndFinPresta(Date debutPresta, Date finPresta);
	public List<Prestation> findByVilleDepartArriveeAndDestination(String villeDepartArrivee, String destination);
}
