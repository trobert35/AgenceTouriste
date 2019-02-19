package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminServiceTest {

	@Autowired
	private IAdminService admService;

	private Admin admin;

	// CREATE
	@Test
	public void a_createValid() {
		// Tester l'insertion d'un objet valide
		admin = new Admin("adminNom", "adminPrenom", "email@admin.fr", "pwd", 0.2F);
		System.out.println("DEBUG Admin : " + admin.getNom() + " " + admin.getPrenom() +" " + admin.getEmail() + " " + admin.getPwd()+ " " + admin.getRemise());
		admin = admService.createAdmin(admin);
		assertNotNull(admin);

	}

	@Test
	public void b_createExisting() {
		// Tester l'insertion d'un objet existant

	}

	@Test
	public void c_createWithNullId() {
		// Tester l'insertion d'un objet avec id = null

	}

	@Test
	public void d_createWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0

	}

	@Test
	public void e_createNull() {
		// Tester l'insertion d'un objet null

	}

	// READ
	@Test
	public void f_readByIdValid() {
		// Tester la lecture d'un objet valide

	}

	@Test
	public void g_readByUnknownId() {
		// Tester la lecture d'un objet non existant

	}

	@Test
	public void h_readByIdNull() {
		// Tester la lecture d'un objet avec id = null

	}

	@Test
	public void i_readByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0

	}

	@Test
	public void j_readAllValid() {
		// Tester la lecture d'une liste d'objets valides

	}

	@Test
	public void k_readAllEmpty() {
		// Tester la lecture d'une liste d'objets vide

	}

	// UPDATE
	@Test
	public void l_updateValid() {
		// Tester la modification d'un objet valide

	}

	@Test
	public void m_updateUnknown() {
		// Tester la modification d'un objet inconnu

	}

	@Test
	public void n_updateWithNullId() {
		// Tester la modification d'un objet avec id = null

	}

	@Test
	public void o_updateWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0

	}

	@Test
	public void p_updateNull() {
		// Tester la modification d'un objet null

	}

	// DELETE
	@Test
	public void q_deleteValid() {
		// Tester la suppression d'un objet valide

	}

	@Test
	public void r_deleteUnknown() {
		// Tester la suppression d'un objet inconnu

	}

	@Test
	public void s_deleteWithNullId() {
		// Tester la suppression d'un objet avec id = null

	}

	@Test
	public void t_deleteWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0

	}

	@Test
	public void u_deleteNull() {
		// Tester la suppression d'un objet null

	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod *********************");
		if (admin != null && admin.getId() != null) {
			admService.deleteAdminById(admin.getId());
		}

	}
}
