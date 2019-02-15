package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Logement;
import com.fr.adaming.entity.User;

public interface ILogementDao extends JpaRepository<Logement, Long>{

	public Logement findByPrestaLog(String prestaLog);
}
