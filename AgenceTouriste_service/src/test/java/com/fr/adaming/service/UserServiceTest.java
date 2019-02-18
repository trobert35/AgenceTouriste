package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.fr.adaming.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	
	@Autowired
	private IUserService<User> userService;

	private User user, user2;

	// CREATE
	@Test
	public void a_createValidUser() {
		// Tester l'insertion d'un objet valide
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user = userService.create(user);
		assertNotNull(user);

	}

	@Test
	public void b_createExistingUser() {
		// Tester l'insertion d'un objet existant
		a_createValidUser();
		user2 = userService.readAll().get(0);
		user2 = userService.create(user2);
		assertNull(user2);
	}

	@Test
	public void c_createUserWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user.setId(null);
		user = userService.create(user);
		assertNotNull(user);

	}

	@Test
	public void d_createUserWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user.setId(0L);
		user = userService.create(user);
		assertNotNull(user);

	}

	@Test(expected = NullPointerException.class)
	public void e_createUserNull() {
		// Tester l'insertion d'un objet null
		user = null;
		userService.create(user);

	}

	// READ
	@Test
	public void f_readUserByIdValid() {
		// Tester la lecture d'un objet valide
		a_createValidUser();
		user = userService.readById(user.getId());
		assertNotNull(user);

	}

	@Test
	public void g_readUserByUnknownId() {
		// Tester la lecture d'un objet non existant
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user.setId(7L);
		user = userService.readById(user.getId());
		assertNull(user);

	}

	@Test
	public void h_readUserByIdNull() {
		// Tester la lecture d'un objet avec id = null
		user = userService.readById(null);
		assertNull(user);

	}

	@Test
	public void i_readUserByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0

	}

	@Test
	public void j_readAllUserValid() {
		// Tester la lecture d'une liste d'objets valides

	}

	@Test
	public void k_readAllUserEmpty() {
		// Tester la lecture d'une liste d'objets vide

	}

	// UPDATE
	@Test
	public void l_updateValidUser() {
		// Tester la modification d'un objet valide

	}

	@Test
	public void m_updateUnknownUser() {
		// Tester la modification d'un objet inconnu

	}

	@Test
	public void n_updateUserWithNullId() {
		// Tester la modification d'un objet avec id = null

	}

	@Test
	public void o_updateUserWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0

	}

	@Test
	public void p_updateUserNull() {
		// Tester la modification d'un objet null

	}

	// DELETE
	@Test
	public void q_deleteValidUser() {
		// Tester la suppression d'un objet valide

	}

	@Test
	public void r_deleteUnknownUser() {
		// Tester la suppression d'un objet inconnu

	}

	@Test
	public void s_deleteUserWithNullId() {
		// Tester la suppression d'un objet avec id = null

	}

	@Test
	public void t_deleteUserWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0

	}

	@Test
	public void u_deleteUserNull() {
		// Tester la suppression d'un objet null

	}

//	@After
//	public void after() {
//		System.out.println("********************* DEBUG TESTING afterMethod *********************");
//		if (user != null && user.getId() != null) {
//			userService.deleteById(user.getId());
//			if(user2 != null && user2.getId() != null) {
//				userService.deleteById(user2.getId());
//			}
//		}
//
//	}

}
