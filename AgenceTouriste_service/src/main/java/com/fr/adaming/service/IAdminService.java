package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;

public interface IAdminService {
	
	/**
	 * @author Thomas S
	 */

	public Admin create(Admin user);

	public Admin update(Admin admin);

	public User readById(Long id);

	public List<User> readAll();

	public String deleteById(Long id);
}
