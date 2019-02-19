package com.fr.adaming.restController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LoginDTO;
import com.fr.adaming.dto.RegisterDTO;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IUserService;

@RestController
@RequestMapping(path="api/")
public class UserRestController implements IUserRestController{

	@Autowired
	private IUserService<User> userService;
	
	
	@RequestMapping(path="user", method=RequestMethod.POST)
	public String register(@Valid @RequestBody RegisterDTO regDto) {
		User user = new User(regDto.getNom(), regDto.getPrenom(), regDto.getEmail(), regDto.getPwd());
		userService.register(user);
		return "Bienvenue " + user.getPrenom() + " " +user.getNom();
	}
	
	@RequestMapping(path="login", method=RequestMethod.POST)
	public String login(@Valid @RequestBody LoginDTO logDto) {
		User user = userService.login(logDto.getEmail(), logDto.getPwd());
		return "Bonjour " + user.getPrenom() + " " + user.getNom() + ", vous etes connecte";
	}
	
	@RequestMapping(path="user", method=RequestMethod.GET)
	public List<User> readAll() {
		List<User> listUser = userService.readAll();
		return listUser;
	}
	
	@RequestMapping(path="user/{id}", method=RequestMethod.GET)
	public String readById(@PathVariable Long id) {
		User user = userService.readById(id);
		return "Client : " + user;
	}

	@RequestMapping(path="user/{nom}/conf/{prenom}", method=RequestMethod.GET)
	public User readByNomAndPrenom(@PathVariable String nom, @PathVariable String prenom) {
		User user = userService.readByNomAndPrenom(nom, prenom);
		return user;
	}
	
	@RequestMapping(path="user", method=RequestMethod.PUT)
	public String update(@RequestBody User user){
		User user0 = userService.readById(user.getId());
		User user1 = userService.update(user);
		return "Avant modif : " + user0 + "\nApres modif : " + user1;
	}

	@RequestMapping(path="user/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		User user0 = userService.readById(id);
		userService.deleteById(id);
		return user0.getPrenom() + " " + user0.getNom() + " a ete supprime";
	}

}
