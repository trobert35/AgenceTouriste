package com.fr.adaming.restController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.BookingDTO;
import com.fr.adaming.dto.LoginDTO;
import com.fr.adaming.dto.RegisterDTO;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IProduitService;
import com.fr.adaming.service.IUserService;

/**
 * @author Thomas R
 *
 */
@RestController
@RequestMapping(path = "api/")
public class UserRestController implements IUserRestController {

	@Autowired
	private IUserService<User> userService;

	@Autowired
	private IProduitService<Prestation> prestaService;

	private static final String FORMATDATE = "dd/MM/yyyy";

	/**
	 * @param regDto DTO pour enregistrer un user
	 * @return String
	 */
	@PostMapping(path = "user")
	public String register(@Valid @RequestBody RegisterDTO regDto) {
		User user = new User(regDto.getNom(), regDto.getPrenom(), regDto.getEmail(), regDto.getPwd());
		userService.register(user);
		return "Bienvenue " + user.getPrenom() + " " + user.getNom();
	}

	/**
	 * @param logDto dto login
	 * @return String
	 */
	@PostMapping(path = "login")
	public String login(@Valid @RequestBody LoginDTO logDto) {
		User user = userService.login(logDto.getEmail(), logDto.getPwd());
		return "Bonjour " + user.getPrenom() + " " + user.getNom() + ", vous etes connecte";
	}

	/**
	 * @return List des User
	 */
	@GetMapping(path = "user")
	public List<User> readAll() {
		return userService.readAll();
	}

	/**
	 * @param id id user
	 * @return String
	 */
	@GetMapping(path = "user/{id}")
	public String readById(@PathVariable Long id) {
		User user = userService.readById(id);
		return "Client : " + user;
	}

	/**
	 * @param nom    nom
	 * @param prenom prenom
	 * @return User object
	 */
	@GetMapping(path = "user/{nom}/conf/{prenom}")
	public User readByNomAndPrenom(@PathVariable String nom, @PathVariable String prenom) {
		return userService.readByNomAndPrenom(nom, prenom);

	}

	/**
	 * @param user Object user
	 * @return String
	 */
	@PutMapping(path = "user")
	public String update(@RequestBody User user) {
		User user0 = userService.readById(user.getId());
		User user1 = userService.update(user);
		return "Avant modif : " + user0 + "\nApres modif : " + user1;
	}

	/**
	 * @param id id user
	 * @return user + String
	 */
	@DeleteMapping(path = "user/{id}")
	public String delete(@PathVariable Long id) {
		User user0 = userService.readById(id);
		userService.deleteById(id);
		return user0.getPrenom() + " " + user0.getNom() + " a ete supprime";
	}

	/**
	 * @param dtoBook dto book
	 * @return String
	 * @throws ParseException parseException
	 */
	@PostMapping(path = "user/PrestationToBook/conf/userToBook")
	public String book(@RequestBody BookingDTO dtoBook) throws ParseException {

		Prestation p = prestaService
				.readByDebutPrestaAndFinPresta(new SimpleDateFormat(FORMATDATE).parse(dtoBook.getDebutPresta()),
						new SimpleDateFormat(FORMATDATE).parse(dtoBook.getFinPresta()))
				.get(0);

		User u = userService.readByNomAndPrenom(dtoBook.getNomUser(), dtoBook.getPrenomUser());
		boolean isBookGood = userService.bookPrestation(u, p);
		if (isBookGood) {
			return "Reservation effectuee ! Pour l'utilisateur " + u.getNom() + " et a la date " + p.getDebutPresta();
		} else {
			return "Reservation ECHEC";
		}

	}

}
