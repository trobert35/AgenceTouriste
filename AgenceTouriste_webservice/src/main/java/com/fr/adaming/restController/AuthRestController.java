package com.fr.adaming.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LoginDTO;
import com.fr.adaming.dto.RegisterDTO;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IUserService;



@RestController
@RequestMapping(path="auth/")
public class AuthRestController implements IAuthRestController{

	@Autowired
	private IUserService<User> userService;
	
	@RequestMapping(path="login", method=RequestMethod.POST)
	public String loginRest(@Valid @RequestBody LoginDTO logDto) {
		User user = new User();
		user.setEmail(logDto.getEmail());
		user.setPwd(logDto.getPwd());
		user = userService.login(user.getEmail(), user.getPwd());
		return "Bonjour " + user.getPrenom() +  user.getNom() + " , vous etes bien connecte";
	}
	
	@RequestMapping(path="register", method=RequestMethod.POST)
	public String register(@Valid @RequestBody RegisterDTO regDto) {
		User user = new User(regDto.getNom(), regDto.getPrenom(), regDto.getEmail(), regDto.getPwd());
		User user1 = userService.register(user);
		return user1.getPrenom() + " " + user1.getNom() + " a correctement ete enregistre";
	}
	
//	@RequestMapping(path="disconnect")
//	public String disconnect() {
//		userService.;
//		return "Au revoir";
//	}
}
