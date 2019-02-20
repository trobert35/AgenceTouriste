package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Admin;

/**
 * @author Mohamed
 * @author Maxime
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminServiceTest {

	@Autowired
	private IAdminService admService;

	private Admin admin, admin2;

	// CREATE
	@Test
	public void a_createValid() {
		// Tester l'insertion d'un objet valide
		admin = new Admin("adminNom", "adminPrenom", "email@admin.fr", "pwd", 0.1F);
		admin = admService.createAdmin(admin);
		assertNotNull(admin);

	}

	@Test
	public void b_createExisting() {
		// Tester l'insertion d'un objet existant
		a_createValid();
		admin2 = (Admin) admService.readAdminById(admin.getId());
		admin2 = admService.createAdmin(admin2);
		assertNull(admin2);

	}

	@Test
	public void c_createWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		admin = new Admin("nomAdmin", "prenomAdmin", "admin@email.fr", "pwd", 0.2F);
		admin.setId(null);
		admin = admService.createAdmin(admin);
		assertNotNull(admin);
	}

	@Test
	public void d_createWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		admin = new Admin("nomAdmin", "prenomAdmin", "admin@email.com", "pwd", 0.3F);
		admin.setId(0L);
		admin = admService.createAdmin(admin);
		assertNotNull(admin);
	}

	@Test(expected = NullPointerException.class)
	public void e_createNull() {
		// Tester l'insertion d'un objet null
		admService.createAdmin(null);
	}

	// READ
	@Test
	public void f_readByIdValid() {
		// Tester la lecture d'un objet valide
		a_createValid();
		admin = (Admin) admService.readAdminById(admin.getId());
		assertNotNull(admin);

	}

	@Test(expected = NoSuchElementException.class)
	public void g_readByUnknownId() {
		// Tester la lecture d'un objet non existant
		admin = (Admin) admService.readAdminById(999999L);
		assertNull(admin);

	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void h_readByIdNull() {
		// Tester la lecture d'un objet avec id = null
		a_createValid();
		admService.readAdminById(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void i_readByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		admin = (Admin) admService.readAdminById(0L);
		assertNull(admin);
	}

	@Test
	public void j_readAllValid() {
		// Tester la lecture d'une liste d'objets valides
		a_createValid();
		assertNotNull(admService.readAllAdmin().get(0));
	}

	@Test
	public void k_readAllEmpty() {
		// Tester la lecture d'une liste d'objets vide
		assertTrue(admService.readAllAdmin().isEmpty());
	}

	// UPDATE
	@Test
	public void l_updateValid() {
		// Tester la modification d'un objet valide
		a_createValid();
		admin = (Admin) admService.readAllAdmin().get(0);
		admin.setPrenom("jojo");
		admin = admService.updateAdmin(admin);
		assertNotNull(admin);
	}

	@Test
	public void m_updateUnknown() {
		// Tester la modification d'un objet inconnu
		admin = new Admin("adminUnknown", "adminUnknown", "adminUnknown@email.com", "pwd", 0.5F);
		admin = admService.updateAdmin(admin);
		assertNull(admin);
	}

	@Test
	public void n_updateWithNullId() {
		// Tester la modification d'un objet avec id = null
		a_createValid();
		long i = admin.getId();
		admin.setId(null);
		assertNull(admService.updateAdmin(admin));
		admin.setId(i);
	}

	@Test
	public void o_updateWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0
		a_createValid();
		long i = admin.getId();
		admin.setId(0L);
		assertNull(admService.updateAdmin(admin));
		admin.setId(i);
	}

	@Test(expected = NullPointerException.class)
	public void p_updateNull() {
		// Tester la modification d'un objet null
		admService.updateAdmin(null);
	}

	// DELETE
	@Test
	public void q_deleteValid() {
		// Tester la suppression d'un objet valide
		a_createValid();
		admService.deleteAdminById(admin.getId());
		admin = null;
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void r_deleteUnknown() {
		// Tester la suppression d'un objet inconnu
		admService.deleteAdminById(99999L);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void s_deleteWithNullId() {
		// Tester la suppression d'un objet avec id = null
		admService.deleteAdminById(null);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void t_deleteWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0
		admService.deleteAdminById(0L);
	}

	@Before
	public void before() {
		System.out.println("********************* DEBUG TESTING beforeMethod *********************");
		System.out.println("\nDEBUG admin avant methode (attendu = null) : " + admin);
		System.out.println("\n=============================================================================");

	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod *********************");
		if (admin != null && admin.getId() != null) {
			admService.deleteAdminById(admin.getId());
			if(admin2 != null && admin2.getId() != null){
				admService.deleteAdminById(admin2.getId());
			}
		}
		System.out.println("\n");
		System.out.println("=============================================================================");

	}
}
