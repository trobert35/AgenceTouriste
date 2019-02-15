package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Logement;
import com.fr.adaming.entity.User;
import com.fr.adaming.enumeration.typeLogEnum;

public interface ILogementDao extends JpaRepository<Logement, Long>{

	public List<Logement> findByPrestaLog(String prestaLog);
	public List<Logement> findByVille(String ville);
	public List<Logement> findByTypeLog(typeLogEnum typeLog);
	public List<Logement> findByPrix(Double prix);
}
