package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Activite;
import com.fr.adaming.entity.Logement;
import com.fr.adaming.enumeration.pensionLogEnum;
import com.fr.adaming.enumeration.qualiteLogEnum;
import com.fr.adaming.enumeration.typeActEnum;
import com.fr.adaming.enumeration.typeLogEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActiviteServiceTest {

	@Autowired
	private IActiviteService actService;

	private Activite activite;

	// CREATE
	@Test
	public void a_createValidActivite() {
		// Tester l'insertion d'un objet valide
		activite = new Activite(120d, typeActEnum.croisiere, "Croisiere sur le Nil", "MSC Croisiere");
		activite = actService.createActivite(activite);
		assertNotNull(activite);
	}

	@Test
	public void b_createExistingActivite() {
		// Tester l'insertion d'un objet existant
		a_createValidActivite();
		Activite activite1 = new Activite();
		activite1 = actService.readAllActivite().get(0);
		activite1 = actService.createActivite(activite1);
		assertNull(activite1);
	}

	@Test
	public void c_createActiviteWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		activite = new Activite();
		activite.setId(null);
		activite = actService.createActivite(activite);
		assertNotNull(activite);

	}

	@Test
	public void d_createActiviteWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		activite = new Activite();
		activite.setId(0L);
		activite = actService.createActivite(activite);
		assertNotNull(activite);
	}

	@Test(expected = NullPointerException.class)
	public void e_createActiviteNull() {
		// Tester l'insertion d'un objet null
		activite = null;
		actService.createActivite(activite);
	}

	// READ
	@Test(expected = NoSuchElementException.class)
	public void f_readActiviteByIdValid() {
		// Tester la lecture d'un objet valide
		a_createValidActivite();
		activite = actService.readActiviteById(1L);
	}

	@Test(expected = NoSuchElementException.class)
	public void g_readActiviteByUnknownId() {
		// Tester la lecture d'un objet non existant
		a_createValidActivite();
		activite = actService.readActiviteById(999999999L);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void h_readActiviteByIdNull() {
		// Tester la lecture d'un objet avec id = null
		a_createValidActivite();
		activite = actService.readActiviteById(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void i_readActiviteByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		a_createValidActivite();
		activite = actService.readActiviteById(0L);
	}

	@Test
	public void j_readAllActiviteValid() {
		// Tester la lecture d'une liste d'objets valides
		a_createValidActivite();
		activite = actService.readAllActivite().get(0);
		assertNotNull(activite);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void k_readAllActiviteEmpty() {
		// Tester la lecture d'une liste d'objets vide
		activite = actService.readAllActivite().get(0);
	}

	// UPDATE
	@Test
	public void l_updateValidActivite() {
		// Tester la modification d'un objet valide
		a_createValidActivite();
		activite = actService.readAllActivite().get(0);
		activite.setNom("nomModifie");
		activite = actService.updateActivite(activite);
		assertNotNull(activite);
	}

	@Test
	public void m_updateUnknownActivite() {
		// Tester la modification d'un objet inconnu
		activite = new Activite();
		activite = actService.updateActivite(activite);
		assertNull(activite);
	}

	@Test
	public void n_updateActiviteWithNullId() {
		// Tester la modification d'un objet avec id = null
		activite = new Activite();
		activite.setId(null);
		activite = actService.updateActivite(activite);
		assertNull(activite);
	}

	@Test
	public void o_updateActiviteWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0
		activite = new Activite();
		activite.setId(0L);
		activite = actService.updateActivite(activite);
		assertNull(activite);
	}

	@Test(expected = NullPointerException.class)
	public void p_updateActiviteNull() {
		// Tester la modification d'un objet null
		activite = null;
		activite = actService.updateActivite(activite);
	}

	// DELETE
	@Test
	public void q_deleteValidActivite() {
		// Tester la suppression d'un objet valide
		a_createValidActivite();
		actService.deleteActiviteById(activite.getId());
		activite = null;
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void r_deleteUnknownActivite() {
		// Tester la suppression d'un objet inconnu
		actService.deleteActiviteById(999999999L);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void s_deleteActiviteWithNullId() {
		// Tester la suppression d'un objet avec id = null
		actService.deleteActiviteById(null);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void t_deleteActiviteWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0
		actService.deleteActiviteById(0L);
	}

	@Test(expected = NullPointerException.class)
	public void u_deleteActiviteNull() {
		// Tester la suppression d'un objet null
		activite = null;
		actService.deleteActiviteById(activite.getId());
	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod *********************");
		if (activite != null && activite.getId() != null) {
			actService.deleteActiviteById(activite.getId());
		}

	}
}
