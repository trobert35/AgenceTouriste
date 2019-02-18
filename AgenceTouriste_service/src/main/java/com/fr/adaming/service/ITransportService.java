package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Transport;

public interface ITransportService {
	
	/**
	 * @author Thomas S
	 * **/
	
	public Transport createTransport(Transport transport);

	public Transport updateTransport(Transport transport);

	public Transport readTransportById(Long id);

	public List<Transport> readAllTransport();

	public String deleteTransportById(Long id);
	
	public List<Transport> readTransportByPrestaTrans(String prestaTrans);


}
