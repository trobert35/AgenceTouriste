package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Prestation;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrestationServiceTest {

	@Autowired
	private IAdminService admService;
	
	@Autowired
	private IProduitService<Prestation> prestaService;

	private Prestation prestation, prestation2;

	// CREATE
	@Test
	public void a_createValidPrestation() throws ParseException {
		// Tester l'insertion d'un objet valide	
		prestation = new Prestation("Camping", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		assertNotNull(prestaService.createPrestation(prestation));
	}

	@Test
	public void b_createExistingPrestation() throws ParseException {
		// Tester l'insertion d'un objet existant
		prestation = new Prestation("Camping", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		
		prestation = prestaService.createPrestation(prestation);
		prestation2 = prestaService.readAllPrestation().get(0);
		prestation2 = prestaService.createPrestation(prestation2);
		assertNull(prestation2);
	}

	@Test
	public void c_createPrestationWithNullId() throws ParseException {
		// Tester l'insertion d'un objet avec id = null
		prestation = new Prestation("Camping", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		prestation.setId(null);
		prestation = prestaService.createPrestation(prestation);
		assertNotNull(prestation);
	}

	@Test
	public void d_createPrestationWithIdEqualsToZero() throws ParseException {
		// Tester l'insertion d'un objet avec id = 0
		prestation = new Prestation("Camping", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		prestation.setId(0L);
		prestation = prestaService.createPrestation(prestation);
		assertNotNull(prestation);
	}

	@Test(expected=NullPointerException.class)
	public void e_createPrestationNull() {
		// Tester l'insertion d'un objet null
		prestation = null;
		prestation = prestaService.createPrestation(prestation);
	}

	// READ
	@Test
	public void f_readPrestationByIdValid() throws ParseException {
		// Tester la lecture d'un objet valide
		a_createValidPrestation();
		assertNotNull(prestaService.readPrestationById(prestation.getId()));
	}

	@Test(expected=NoSuchElementException.class)
	public void g_readExmpleByUnknownId() throws ParseException {
		// Tester la lecture d'un objet non existant
		assertNull(prestaService.readPrestationById(9999999L));
	}

	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void h_readExmpleByIdNull() {
		// Tester la lecture d'un objet avec id = null
		assertNull(prestaService.readPrestationById(null));
	}

	@Test(expected=NoSuchElementException.class)
	public void i_readExmpleByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		assertNull(prestaService.readPrestationById(0L));
	}

	@Test
	public void j_readAllPrestationValid() throws ParseException {
		// Tester la lecture d'une liste d'objets valides
		a_createValidPrestation();
		assertNotNull(prestaService.readAllPrestation().get(0));
	}

	@Test
	public void k_readAllPrestationEmpty() {
		// Tester la lecture d'une liste d'objets vide
		assertTrue(prestaService.readAllPrestation().isEmpty());	
	}

	// UPDATE
	@Test
	public void l_updateValidPrestation() throws ParseException {
		// Tester la modification d'un objet valide
		a_createValidPrestation();
		prestation.setNbPersonnes(10);
		prestation.setCommission(50);
		assertNotNull(prestaService.updatePrestation(prestation));
	}

	@Test
	public void m_updateUnknownPrestation() throws ParseException {
		// Tester la modification d'un objet inconnu
		prestation = new Prestation("Camping", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"),
				"Paris", "Marseille", 60, 100, null);
		
		assertNull(prestaService.updatePrestation(prestation));
	}

	@Test
	public void n_updatePrestationWithNullId() throws ParseException {
		// Tester la modification d'un objet avec id = null
		a_createValidPrestation();
		Long id = prestation.getId();
		prestation.setId(null);
		assertNull(prestaService.updatePrestation(prestation));
		prestation.setId(id);
	}

	@Test
	public void o_updatePrestationWithIdEqualsToZero() throws ParseException {
		// Tester la modification d'un objet avec id = 0
		a_createValidPrestation();
		Long id = prestation.getId();
		prestation.setId(0L);
		assertNull(prestaService.updatePrestation(prestation));
		prestation.setId(id);
	}

	@Test(expected=NullPointerException.class)
	public void p_updatePrestationNull() throws ParseException {
		// Tester la modification d'un objet null
		prestaService.updatePrestation(null);
	}

	// DELETE
	@Test
	public void q_deleteValidPrestation() throws ParseException {
		// Tester la suppression d'un objet valide
		a_createValidPrestation();
		System.out.println("DEBUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUG : "+prestation.getId());
		assertNotNull(prestaService.deletePrestationById(prestation.getId()));
		
		
	}

	@Test
	public void r_deleteUnknownPrestation() throws ParseException {
		// Tester la suppression d'un objet inconnu
	
	assertNull(prestaService.deletePrestationById(99999L));
	}

	@Test
	public void s_deletePrestationWithNullId() throws ParseException {
		// Tester la suppression d'un objet avec id = null
		a_createValidPrestation();
		Long id = prestation.getId();
		prestation.setId(null);
		assertNull(prestaService.deletePrestationById(prestation.getId()));
		prestation.setId(id);
	}

	@Test
	public void t_deletePrestationWithIdEqualsToZero() throws ParseException {
		// Tester la suppression d'un objet avec id = 0
		a_createValidPrestation();
		Long id = prestation.getId();
		prestation.setId(0L);
		assertNull(prestaService.deletePrestationById(prestation.getId()));
		prestation.setId(id);
	}

	@Test
	public void u_readByDebutPrestaAndFinPresta() throws ParseException {
		a_createValidPrestation();
		assertNotNull(prestaService.readByDebutPrestaAndFinPresta(prestation.getDebutPresta(),
				prestation.getFinPresta()));
	}
	
	@Test
	public void u_readByVilleDepartAndDestination() throws ParseException {
		//Creation d'une prestation valide puis lecture par Ville
		// Test22
		a_createValidPrestation();
		assertNotNull(prestaService.readByVilleDepartArriveeAndDestination(
				prestation.getVilleDepartArrivee(), prestation.getDestination()));
	}
	
	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod *********************");
		if (prestation != null && prestation.getId() != null) {
			prestaService.deletePrestationById(prestation.getId());
			if(prestation2 != null && prestation2.getId() != null) {
				prestaService.deletePrestationById(prestation2.getId());
			}
		}
	}
}
