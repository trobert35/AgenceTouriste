package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;

/**
 * @author Thomas S
 *
 */
public interface ITransportService {

	/**
	 * @param transport objet de classe Transport
	 * @return un objet de la classe Transport
	 */
	public Transport createTransport(Transport transport);

	/**
	 * @param transport objet de classe Transport
	 * @return un objet de la classe Transport
	 */
	public Transport updateTransport(Transport transport);

	/**
	 * @param id id du Transport
	 * @return un objet de la classe Transport
	 */
	public Transport readTransportById(Long id);

	/**
	 * @return une List de Transport
	 */
	public List<Transport> readAllTransport();

	/**
	 * @param id id du Transport
	 * @return String
	 */
	public String deleteTransportById(Long id);

	/**
	 * @param prestaTrans nom du prestataire du Transport
	 * @return une List de Transport
	 */
	public List<Transport> readTransportByPrestaTrans(String prestaTrans);

	/**
	 * @param prix prix du Transport
	 * @return une List de Transport
	 */
	public List<Transport> readByPrix(Double prix);

	/**
	 * @param type type du Transport
	 * @return une List de Transport
	 */
	public List<Transport> readByTypeTrans(typeTransEnum type);

}
