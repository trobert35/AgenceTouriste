package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ITransportDao;
import com.fr.adaming.entity.Transport;

@Service
public class TransportService implements ITransportService {
	
	/**
	 * @author Thomas S
	 * @author Maxime
	 */

	@Autowired
	private ITransportDao dao;

	public Transport create(Transport transport) {
		if (transport.getId() == null || transport.getId() == 0 || !dao.existsById(transport.getId())) {
			System.out.println("transport cree");
			return dao.save(transport);
		} else {
			System.out.println("Transport non crée car existe déjà pour cet id");
			return null;
		}
	}

	public Transport update(Transport transport) {
		if (transport.getId() != null && transport.getId() != 0 && dao.existsById(transport.getId())) {
			System.out.println("Transport modifié");
			return dao.save(transport);
		} else {
			return null;
		}
	}

	public List<Transport> readAll() {
		return dao.findAll();
	}

	public Transport readById(Long id) {
		return dao.findById(id).get();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "Transport supprimé";
	}

	public List<Transport> readByPrestaTrans(String prestaTrans) {
		return dao.findByPrestaTrans(prestaTrans);
	}

}
