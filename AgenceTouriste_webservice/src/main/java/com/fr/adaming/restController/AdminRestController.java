package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.AdminCreateDTO;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IAdminService;

@RestController
@RequestMapping(path = "api/")
public class AdminRestController implements IAdminRestController {

	@Autowired
	private IAdminService adminService;

	// Methodes CRUD Admin
	@RequestMapping(path = "admin", method = RequestMethod.POST)
	public String createAdmin(@RequestBody AdminCreateDTO admin) {
		Admin adm = adminService.createAdmin(
				new Admin(admin.getNom(), admin.getPrenom(), admin.getEmail(), admin.getPwd(), admin.getRemise()));
		return adm.getNom() + " est un admin correctement cree";
	}

	@RequestMapping(path = "admin", method = RequestMethod.PUT)
	public String updateAdmin(@RequestBody AdminCreateDTO admin) {
		User a = adminService.readAdminByEmailAndPwd(admin.getEmail(), admin.getPwd());
		return a.getNom() + " est un admin correctement modifie";
	}

	@RequestMapping(path = "admin/{id}", method = RequestMethod.GET)
	public Admin readAdminById(@PathVariable Long id) {
		Admin admin = (Admin) adminService.readAdminById(id);
		return admin;
	}

	@RequestMapping(path = "admin", method = RequestMethod.GET)
	public List<User> readAllAdmin() {
		List<User> admin = adminService.readAllAdmin();
		return admin;
	}

	@RequestMapping(path = "admin/{id}", method = RequestMethod.DELETE)
	public String deleteAdmin(@PathVariable Long id) {
		Admin admin = (Admin) adminService.readAdminById(id);
		adminService.deleteAdminById(id);
		return admin.getNom() + " est un admin correctement supprime";
	}

}
