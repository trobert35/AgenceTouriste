package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ITransportDao;
import com.fr.adaming.entity.Transport;

@Service
public class TransportService implements IProduitService<Transport>{

	@Autowired
	private ITransportDao dao;

	public Transport create(Transport transport) {
		if (transport.getId() == null || transport.getId() == 0) {
			System.out.println("transport cree");
			return dao.save(transport);
		} else {
			System.out.println("transport non cree car existe deja pour cer id");
			return null;
		}
	}

	public Transport update(Transport transport) {
		if (transport.getId() != null && transport.getId() != 0 && dao.existsById(transport.getId())) {
			System.out.println("transport modifie");
			return dao.save(transport);
		} else {
			return null;
		}
	}

	public Transport readById(Long id) {
		return dao.findById(id).get();
	}

	public List<Transport > readByPrestaTrans(String prestaTrans) {
		return dao.findByPrestaTrans(prestaTrans);
	}
	
	public List<Transport> readAll() {
		return dao.findAll();
	}

	public String deleteById(Long id) {
		dao.deleteById(id);
		return "transport supprime";
	}

}
