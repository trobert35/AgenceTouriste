package com.fr.adaming.restController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LoginDTO;
import com.fr.adaming.dto.RegisterDTO;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IAdminService;
import com.fr.adaming.service.IUserService;

@RestController
@RequestMapping(path="User/")
public class UserRestController implements IUserRestController{

	@Autowired
	private IUserService<User> userService;
	
	
	@RequestMapping(path="register")
	public String register(@Valid @RequestBody RegisterDTO regDto) {
		User user = new User(regDto.getNom(), regDto.getPrenom(), regDto.getEmail(), regDto.getPwd());
		userService.register(user);
		return "Bienvenue " + user.getPrenom() + " " +user.getNom();
	}

	@RequestMapping(path="create")
	public String create(@Valid @RequestBody RegisterDTO regDto) {
		User user = new User(regDto.getNom(), regDto.getPrenom(), regDto.getEmail(), regDto.getPwd());
		userService.register(user);
		return "Bienvenue " + user.getPrenom() + " " +user.getNom();
	}
	
	
	@RequestMapping(path="login")
	public String login(@Valid @RequestBody LoginDTO logDto) {
		User user = userService.login(logDto.getEmail(), logDto.getPwd());
		return "Bonjour " + user.getPrenom() + " " + user.getNom() + ", vous etes connecte";
	}
	
	@RequestMapping(path="readAll")
	public List<User> readAll() {
		List<User> listUser = userService.readAll();
		return listUser;
	}
	
	@RequestMapping(path="readById")
	public String readById(@RequestBody Long id) {
		User user = userService.readById(id);
		return "Client : " + user;
	}

	@RequestMapping(path="readByNomAndPrenom")
	public User readByNomAndPrenom(@RequestBody String nom, @RequestBody String prenom) {
		User user = userService.readByNomAndPrenom(nom, prenom);
		return user;
	}
	
	@RequestMapping(path="updateUser")
	public String update(@RequestBody User user){
		User user0 = userService.readById(user.getId());
		User user1 = userService.update(user);
		return "Avant modif : " + user0 + "\nApres modif : " + user1;
	}

	@RequestMapping(path="delete")
	public String delete(@RequestBody Long id) {
		User user0 = userService.readById(id);
		userService.deleteById(id);
		return user0.getPrenom() + " " + user0.getNom() + " a ete supprime";
	}

}
