package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;

/**
 * @author Thomas S
 *
 */
public interface IAdminService {

	/**
	 * @param user objet de la classe Admin
	 * @return un objet de la classe Admin
	 */
	public Admin createAdmin(Admin user);

	/**
	 * @param admin objet de la classe Admin
	 * @return un objet de la classe Admin
	 */
	public Admin updateAdmin(Admin admin);

	/**
	 * @param id id de l admin
	 * @return un objet de la classe User
	 */
	public User readAdminById(Long id);

	/**
	 * @param email email de l admin
	 * @param pwd   pwd de l admin
	 * @return un objet de la classe User
	 */
	public User readAdminByEmailAndPwd(String email, String pwd);

	/**
	 * @return une List de User
	 */
	public List<Admin> readAllAdmin();

	/**
	 * @param id id de l Admin
	 * @return String
	 */
	public String deleteAdminById(Long id);
}
