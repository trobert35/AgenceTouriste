package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.AdminCreateDTO;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IAdminService;

/**
 * @author Mohamed EL AGREBI
 * @author Thomas S
 */
@RestController
@RequestMapping(path = "api/")
public class AdminRestController implements IAdminRestController {

	@Autowired
	private IAdminService adminService;

	// Methodes CRUD Admin
	/**
	 * @param admin AdminCreateDTO prend les caracs necessaires pour creation d un
	 *              admin
	 * @return String retournant le nom de l admin cree
	 */
	@PostMapping(path = "admin")
	public String createAdmin(@RequestBody AdminCreateDTO admin) {
		Admin adm = adminService.createAdmin(
				new Admin(admin.getNom(), admin.getPrenom(), admin.getEmail(), admin.getPwd(), admin.getRemise()));
		return adm.getNom() + " est un admin correctement cree";
	}

	/**
	 * @param admin AdminCreateDTO prend les caracs necessaires pour la modification
	 *              d un admin
	 * @return String retournant le nom l admin modifie
	 */
	@PutMapping(path = "admin")
	public String updateAdmin(@RequestBody AdminCreateDTO admin) {
		User a = adminService.readAdminByEmailAndPwd(admin.getEmail(), admin.getPwd());
		return a.getNom() + " est un admin correctement modifie";
	}

	/**
	 * @param id objet Long, prend l id de l admin recherche
	 * @return un objet Admin correspondant a l admin recherche
	 */
	@GetMapping(path = "admin/{id}")
	public Admin readAdminById(@PathVariable Long id) {
		return (Admin) adminService.readAdminById(id);
	}

	/**
	 * @return la liste des utilisateurs
	 */
	@GetMapping(path = "admin")
	public List<User> readAllAdmin() {
		return adminService.readAllAdmin();
	}

	/**
	 * @param id prend un objet Long correpondant a l id de l admin que l on
	 *           souhaite supprimer
	 * @return un String correspondant a l admin supprime
	 */
	@DeleteMapping(path = "admin/{id}")
	public String deleteAdmin(@PathVariable Long id) {
		Admin admin = (Admin) adminService.readAdminById(id);
		adminService.deleteAdminById(id);
		return admin.getNom() + " est un admin correctement supprime";
	}

}
