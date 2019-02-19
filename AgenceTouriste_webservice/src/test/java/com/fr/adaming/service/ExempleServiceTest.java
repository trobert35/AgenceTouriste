package com.fr.adaming.service;

import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Logement;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExempleServiceTest {

	@Autowired
	private IAdminService admService;

	private Admin admin;

	// CREATE
	@Test
	public void a_createValidExemple() {
		// Tester l'insertion d'un objet valide

	}

	@Test
	public void b_createExistingExemple() {
		// Tester l'insertion d'un objet existant

	}

	@Test
	public void c_createExempleWithNullId() {
		// Tester l'insertion d'un objet avec id = null

	}

	@Test
	public void d_createExempleWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0

	}

	@Test
	public void e_createExempleNull() {
		// Tester l'insertion d'un objet null

	}

	// READ
	@Test
	public void f_readExempleByIdValid() {
		// Tester la lecture d'un objet valide

	}

	@Test
	public void g_readExmpleByUnknownId() {
		// Tester la lecture d'un objet non existant

	}

	@Test
	public void h_readExmpleByIdNull() {
		// Tester la lecture d'un objet avec id = null

	}

	@Test
	public void i_readExmpleByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0

	}

	@Test
	public void j_readAllExempleValid() {
		// Tester la lecture d'une liste d'objets valides

	}

	@Test
	public void k_readAllExempleEmpty() {
		// Tester la lecture d'une liste d'objets vide

	}

	// UPDATE
	@Test
	public void l_updateValidExemple() {
		// Tester la modification d'un objet valide

	}

	@Test
	public void m_updateUnknownExemple() {
		// Tester la modification d'un objet inconnu

	}

	@Test
	public void n_updateExempleWithNullId() {
		// Tester la modification d'un objet avec id = null

	}

	@Test
	public void o_updateExempleWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0

	}

	@Test
	public void p_updateExempleNull() {
		// Tester la modification d'un objet null

	}

	// DELETE
	@Test
	public void q_deleteValidExemple() {
		// Tester la suppression d'un objet valide

	}

	@Test
	public void r_deleteUnknownExemple() {
		// Tester la suppression d'un objet inconnu

	}

	@Test
	public void s_deleteExempleWithNullId() {
		// Tester la suppression d'un objet avec id = null

	}

	@Test
	public void t_deleteExempleWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0

	}

	@Test
	public void u_deleteExempleNull() {
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
