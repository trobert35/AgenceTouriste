package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LoginDTO;
import com.fr.adaming.dto.RegisterDTO;
import com.fr.adaming.dto.SocialLoginDTO;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IUserService;

/**
 * @author Mohamed EL AGREBI
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/auth/")
@CrossOrigin
public class AuthRestController implements IAuthRestController {

	@Autowired
	private IUserService<User> userService;

	/**
	 * @param logDto prend les caracteristiques d un objet User necessaires a la
	 *               connexion
	 * @return User avec les donnees de ce user
	 */
	@PostMapping(path = "login")
	public User loginRest(@Valid @RequestBody LoginDTO logDto) {
		User user = new User();
		user.setEmail(logDto.getEmail());
		user.setPwd(logDto.getPwd());
		user = userService.login(user.getEmail(), user.getPwd());
		return user;
	}

	/**
	 * @param socLogDto prend les caracteristiques d un objet User necessaires a la
	 *               connexion via les reseaux sociaux
	 * @return User avec les donnees de ce user
	 */
	@PostMapping(path = "socialLogin")
	public User socialLogin(@RequestBody SocialLoginDTO socLogDto) {
		String name = socLogDto.getName();
		int espace = name.indexOf(' ');
		String prenom = "";
		String nom = "";
		if(espace >= 0) {
			prenom = name.substring(0, espace);
			nom = name.substring(espace + 1, name.length());
		}
		User user = new User(nom, prenom, socLogDto.getEmail(), socLogDto.getUrlImg(), socLogDto.getIdProvider(), socLogDto.getIdProvider(), socLogDto.getIdToken(), socLogDto.getIdToken());
		user = userService.socialLogin(user);
		return user;
	}
	
	/**
	 * @param regDto prend les caracteristiques d un objet User necessaires a l
	 *               isncription
	 * @return String attestant de l inscription + identite de l utilisateur inscrit
	 */
	@PostMapping(path = "register")
	public User register(@Valid @RequestBody RegisterDTO regDto) {
		User user = new User(regDto.getNom(), regDto.getPrenom(), regDto.getEmail(), regDto.getPwd());
		return userService.register(user);
	}

	
	@GetMapping(path="isAdmin/{id}")
	public String isAdmin(@PathVariable Long id) {
		String result = userService.isAdmin(id);
		System.out.println("DEBUG id du user : " + id + " | et la reponse de isAdmin est : " + result);
		return result;
	}
}
