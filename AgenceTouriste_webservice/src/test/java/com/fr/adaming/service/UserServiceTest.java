package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	
	@Autowired
	private IUserService<User> userService;
	@Autowired
	private PrestationService prestaService;

	private User user, user2;
	private Prestation presta;

	// CREATE
	@Test
	public void aa_createValidUser() {
		// Tester l'insertion d'un objet valide
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user = userService.create(user);
		assertNotNull(user);

	}

	@Test
	public void ab_createExistingUser() {
		// Tester l'insertion d'un objet existant
		aa_createValidUser();
		user2 = userService.readAll().get(0);
		user2 = userService.create(user2);
		assertNull(user2);
	}

	@Test
	public void ac_createUserWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		user = new User("MICHEL2", "Jean", "guigui@gmail.com", "kiki");
		user.setId(null);
		user = userService.create(user);
		assertNotNull(user);

	}

	@Test
	public void ad_createUserWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user.setId(0L);
		user = userService.create(user);
		assertNotNull(user);

	}

	@Test(expected = NullPointerException.class)
	public void ae_createUserNull() {
		// Tester l'insertion d'un objet null
		user = null;
		userService.create(user);

	}

	// READ
	@Test
	public void af_readUserByIdValid() {
		// Tester la lecture d'un objet valide
		aa_createValidUser();
		userService.readById(user.getId());

	}

	@Test(expected = NoSuchElementException.class)
	public void ag_readUserByUnknownId() {
		// Tester la lecture d'un objet non existant
		userService.readById(7L);

	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void ah_readUserByIdNull() {
		// Tester la lecture d'un objet avec id = null
		user = userService.readById(null);

	}

	@Test(expected = NoSuchElementException.class)
	public void ai_readUserByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		user = userService.readById(0L);

	}

	@Test
	public void aj_readAllUserValid() {
		// Tester la lecture d'une liste d'objets valides
		aa_createValidUser();
		userService.readAll();

	}

	@Test
	public void ak_readAllUserEmpty() {
		// Tester la lecture d'une liste d'objets vide
		assertTrue(userService.readAll().isEmpty());

	}

	// UPDATE
	@Test
	public void al_updateValidUser() {
		// Tester la modification d'un objet valide
		aa_createValidUser();
		user = userService.readAll().get(0);
		user = userService.update(user);
		assertNotNull(user);

	}

	@Test
	public void am_updateUnknownUser() {
		// Tester la modification d'un objet inconnu
		user = new User("MARS", "Jean", "gaga@gmail.com", "kaka");
		user = userService.update(user);
		assertNull(user);

	}

	@Test
	public void an_updateUserWithNullId() {
		// Tester la modification d'un objet avec id = null
		aa_createValidUser();
		long i = user.getId();
		user.setId(null);
		assertNull(user2 = userService.update(user));
		user.setId(i);
	}

	@Test
	public void ao_updateUserWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0
		aa_createValidUser();
		long i = user.getId();
		user.setId(0L);
		assertNull(userService.update(user));
		user.setId(i);

	}

	@Test(expected = NullPointerException.class)
	public void ap_updateUserNull() {
		// Tester la modification d'un objet null
		user = null;
		userService.update(user);

	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void aq_updatePouvoirWithSameNom() {
		// Tester la modification d'un élément existant en lui appliquant un alias déjà
		// existant
		aa_createValidUser();
		user2 = new User("JACK", "Jack", "kaka@orange.fr", "koko");
		userService.create(user2);
		user.setEmail(user2.getEmail());
		userService.update(user);
	}

	// DELETE
	@Test
	public void ar_deleteValidUser() {
		// Tester la suppression d'un objet valide
		aa_createValidUser();
		userService.deleteById(user.getId());
		user = null;

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void as_deleteUnknownUser() {
		// Tester la suppression d'un objet inconnu
		userService.deleteById(50L);

	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void at_deleteUserWithNullId() {
		// Tester la suppression d'un objet avec id = null
		userService.deleteById(null);

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void au_deleteUserWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0
		userService.deleteById(0L);

	}
	
	// CREATE
	@Test
	public void av_registerValidUser() {
		// Tester l'insertion d'un objet valide
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user = userService.register(user);
		assertNotNull(user);

	}

	@Test
	public void aw_registerExistingUser() {
		// Tester l'insertion d'un objet existant
		av_registerValidUser();
		user2 = userService.readAll().get(0);
		user2 = userService.register(user2);
		assertNull(user2);
	}

	@Test
	public void ax_registerUserWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		user = new User("MICHEL2", "Jean", "guigui@gmail.com", "kiki");
		user.setId(null);
		user = userService.register(user);
		assertNotNull(user);

	}

	@Test
	public void ay_registerUserWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		user = new User("MICHEL", "Jean", "guigui@gmail.com", "kiki");
		user.setId(0L);
		user = userService.register(user);
		assertNotNull(user);

	}

	@Test(expected = NullPointerException.class)
	public void az_registerUserNull() {
		// Tester l'insertion d'un objet null
		user = null;
		userService.register(user);

	}
	
	@Test
	public void ba_readByNomAndPrenomValid() {
		// Tester la lecture d'un objet valide par nom et prenom
		aa_createValidUser();
		user = userService.readByNomAndPrenom(user.getNom(), user.getPrenom());
		assertNotNull(user);
	}
	
	@Test
	public void bb_readByNomAndPrenomUnknown() {
		// Tester la lecture d'un objet par nom et prenom inconnus
		user = userService.readByNomAndPrenom("Jacky", "Moumoute");
		assertNull(user);
	}
	
	@Test
	public void bc_readByNomAndPrenomWithNomNull() {
		// Tester la lecture d'un objet par nom et prenom avec nom = null
		userService.readByNomAndPrenom(null, "Jean");
		assertNull(user);
	}
	
	@Test
	public void bd_readByNomAndPrenomWithPrenomNull() {
		// Tester la lecture d'un objet par nom et prenom avec prenom = null
		userService.readByNomAndPrenom("MICHEL", null);
		assertNull(user);
	}
	
	@Test
	public void be_loginValid() {
		aa_createValidUser();
		user = userService.login(user.getEmail(), user.getPwd());
		assertNotNull(user);
	}
	
	@Test
	public void bf_loginWithEmailAndPwdUnknown() {
		user = userService.login("hujn@hot.fr", "frere");
		assertNull(user);
	}
	
	@Test
	public void bg_loginWithEmailNull() {
		user = userService.login(null, "kaka");
		assertNull(user);
	}
	
	@Test
	public void bh_loginWithPwdNull() {
		user = userService.login("guigui@gmail.com", null);
		assertNull(user);
	}
	
	@Test
	public void bi_bookPrestationValid() throws ParseException {
		aa_createValidUser();
		presta = new Prestation("Campin", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		prestaService.createPrestation(presta);
		assertTrue(userService.bookPrestation(user, presta));
		prestaService.deletePrestationById(presta.getId());
	}
	
	@Test
	public void bj_bookPrestationWithUserUnknown() {
		user = new User("MICHEL2", "Jean", "guigui@gmail.com", "kiki");
		assertFalse(userService.bookPrestation(user, presta));
	}
	
	@Test
	public void bk_bookPrestationWithPrestationUnknown() throws ParseException {
		aa_createValidUser();
		presta = new Prestation("Camping", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		assertFalse(userService.bookPrestation(user, presta));
	}
	
	@Test
	public void bl_bookPrestationWithIdUserNull() {
		aa_createValidUser();
		long i = user.getId();
		user.setId(null);
		assertFalse(userService.bookPrestation(user, presta));
		user.setId(i);
	}
	
	@Test
	public void bm_bookPrestationWithIdUserEqualsToZero() {
		aa_createValidUser();
		long i = user.getId();
		user.setId(0L);
		assertFalse(userService.bookPrestation(user, presta));
		user.setId(i);
	}
	
	@Test
	public void bn_bookPrestationWithIdPrestationNull() throws ParseException {
		aa_createValidUser();
		presta = new Prestation("Campong", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		prestaService.createPrestation(presta);
		long i = presta.getId();
		presta.setId(null);
		assertFalse(userService.bookPrestation(user, presta));
		prestaService.deletePrestationById(i);
	}
	
	@Test
	public void bo_bookPrestationWithIdPrestationNull() throws ParseException {
		aa_createValidUser();
		presta = new Prestation("Campang", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		prestaService.createPrestation(presta);
		long i = presta.getId();
		presta.setId(0L);
		assertFalse(userService.bookPrestation(user, presta));
		prestaService.deletePrestationById(i);
	}
	
	@Test(expected = NullPointerException.class)
	public void bp_bookPrestationWithUserNull() {
		userService.bookPrestation(null, presta);
	}
	
	@Test(expected = NullPointerException.class)
	public void bq_bookPrestationWithPrestationNull() {
		aa_createValidUser();
		userService.bookPrestation(user, null);
	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod *********************");
		if (user != null && user.getId() != null) {
			userService.deleteById(user.getId());
			if(user2 != null && user2.getId() != null) {
				userService.deleteById(user2.getId());
			}
		}

	}

}
