package com.fr.adaming.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.User;

@Repository
public interface IAdminService {

	public User create(User user);
	
	public User update(User user);
	
	public User readById (Long id);
	
	public List<User> readAll();
}
