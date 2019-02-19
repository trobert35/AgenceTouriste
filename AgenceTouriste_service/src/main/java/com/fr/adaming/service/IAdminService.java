package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;

public interface IAdminService {
	
	/**
	 * @author Thomas S
	 */

	public Admin createAdmin(Admin user);

	public Admin updateAdmin(Admin admin);

	public User readAdminById(Long id);
	
	public User readAdminByEmailAndPwd(String email, String pwd);

	public List<User> readAllAdmin();

	public String deleteAdminById(Long id);
}
