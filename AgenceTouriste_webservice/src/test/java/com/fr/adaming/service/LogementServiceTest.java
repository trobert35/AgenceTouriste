package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.fr.adaming.entity.Logement;
import com.fr.adaming.enumeration.pensionLogEnum;
import com.fr.adaming.enumeration.qualiteLogEnum;
import com.fr.adaming.enumeration.typeLogEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogementServiceTest {
	/**
	 * @author Claire
	 */

	@Autowired
	private ILogementService logService;

	private Logement logement;

	// CREATE
	@Test
	public void a_createValidLogement() {
		// Tester l'insertion d'un objet valide
		logement = new Logement("Michel et Jacqueline", "Les Flots Bleus", "Saint-Marc", 60d, typeLogEnum.camping,
				pensionLogEnum.petitDejeuner, qualiteLogEnum.deux);
		logement = logService.createLogement(logement);
		assertNotNull(logement);
	}

	@Test
	public void b_createExistingLogement() {
		// Tester l'insertion d'un objet existant
		a_createValidLogement();
		Logement logement1 = new Logement();
		logement1 = logService.readAllLogement().get(0);
		logement1 = logService.createLogement(logement1);
		assertNull(logement1);
	}

	@Test
	public void c_createLogementWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		logement = new Logement();
		logement.setId(null);
		logement = logService.createLogement(logement);
		assertNotNull(logement);

	}

	@Test
	public void d_createLogementWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		logement = new Logement();
		logement.setId(0L);
		logement = logService.createLogement(logement);
		assertNotNull(logement);
	}

	@Test(expected = NullPointerException.class)
	public void e_createLogementNull() {
		// Tester l'insertion d'un objet null
		logement = null;
		logService.createLogement(logement);
	}

	// READ
	@Test(expected = NoSuchElementException.class)
	public void f_readLogementByIdValid() {
		// Tester la lecture d'un objet valide
		a_createValidLogement();
		logement = logService.readLogementById(1L);
	}

	@Test(expected = NoSuchElementException.class)
	public void g_readLogementByUnknownId() {
		// Tester la lecture d'un objet non existant
		a_createValidLogement();
		logement = logService.readLogementById(999999999L);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void h_readLogementByIdNull() {
		// Tester la lecture d'un objet avec id = null
		a_createValidLogement();
		logement = logService.readLogementById(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void i_readLogementByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		a_createValidLogement();
		logement = logService.readLogementById(0L);
	}

	@Test
	public void j_readAllLogementValid() {
		// Tester la lecture d'une liste d'objets valides
		a_createValidLogement();
		logement = logService.readAllLogement().get(0);
		assertNotNull(logement);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void k_readAllLogementEmpty() {
		// Tester la lecture d'une liste d'objets vide
		logement = logService.readAllLogement().get(0);
	}

	// UPDATE
	@Test
	public void l_updateValidLogement() {
		// Tester la modification d'un objet valide
		a_createValidLogement();
		logement = logService.readAllLogement().get(0);
		logement.setNom("nomModifie");
		logement = logService.updateLogement(logement);
		assertNotNull(logement);
	}

	@Test
	public void m_updateUnknownLogement() {
		// Tester la modification d'un objet inconnu
		logement = new Logement();
		logement = logService.updateLogement(logement);
		assertNull(logement);
	}

	@Test
	public void n_updateLogementWithNullId() {
		// Tester la modification d'un objet avec id = null
		logement = new Logement();
		logement.setId(null);
		logement = logService.updateLogement(logement);
		assertNull(logement);
	}

	@Test
	public void o_updateLogementWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0
		logement = new Logement();
		logement.setId(0L);
		logement = logService.updateLogement(logement);
		assertNull(logement);
	}

	@Test(expected = NullPointerException.class)
	public void p_updateLogementNull() {
		// Tester la modification d'un objet null
		logement = null;
		logement = logService.updateLogement(logement);
	}

	// DELETE
	@Test
	public void q_deleteValidLogement() {
		// Tester la suppression d'un objet valide
		a_createValidLogement();
		logService.deleteLogementById(logement.getId());
		logement = null;
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void r_deleteUnknownLogement() {
		// Tester la suppression d'un objet inconnu
		logService.deleteLogementById(999999999L);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void s_deleteLogementWithNullId() {
		// Tester la suppression d'un objet avec id = null
		logService.deleteLogementById(null);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void t_deleteLogementWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0
		logService.deleteLogementById(0L);
	}

	@Test(expected = NullPointerException.class)
	public void u_deleteLogementNull() {
		// Tester la suppression d'un objet null
		logement = null;
		logService.deleteLogementById(logement.getId());
	}

	@Test
	public void v_readLogementByNomPrestLog() {
		// Tester la lecture d'un objet avec nomPrestaLog valide
		logement = new Logement();
		logService.readLogementByPrestaLog(logement.getPrestaLog());
		assertNotNull(logement);
	}

	@Test
	public void w_readLogementByNullNomPrestLog() {
		// Tester la lecture d'un objet avec nomPrestaLog = null
		assertThat(logService.readLogementByPrestaLog(null)).isEmpty();
	}

	@Test
	public void x_readLogementByNonExistingNomPrestLog() {
		// Tester la lecture d'un objet avec nomPrestaLog inexistant
		assertThat(logService.readLogementByPrestaLog("nomPrestInexistant")).isEmpty();
	}

	@Test
	public void y_readLogementByValidPrix() {
		// Tester la lecture d'un objet avec prix valide
		logement = new Logement();
		logService.readByPrixLogement(logement.getPrix());
		assertNotNull(logement);
	}

	@Test
	public void z_readActiviteByNonExistingPrix() {
		// Tester la lecture d'un objet avec prix non existant
		assertThat(logService.readByPrixLogement(88888888888888888888888888888d)).isEmpty();
	}

	@Test
	public void za_readLogementByNullPrix() {
		// Tester la lecture d'un objet avec prix = null
		assertThat(logService.readByPrixLogement(null)).isEmpty();
	}

	@Test
	public void zb_readLogementByValidTypeLog() {
		// Tester la lecture d'un objet avec typeLog valide
		logement = new Logement();
		logService.readByTypeLog(logement.getTypeLog());
		assertNotNull(logement);
	}

	@Test
	public void zc_readLogementByNullTypeLog() {
		// Tester la lecture d'un objet avec typeLog = null
		assertThat(logService.readByTypeLog(null)).isEmpty();
	}

	@Test
	public void zd_readLogementByValidVille() {
		// Tester la lecture d'un objet avec ville valide
		logement = new Logement();
		logService.readByVille(logement.getVille());
		assertNotNull(logement);
	}

	@Test
	public void ze_readLogementByNonExistingVille() {
		// Tester la lecture d'un objet avec ville non valide
		assertThat(logService.readByVille("villeInexistant")).isEmpty();
	}

	@Test
	public void zf_readLogementByNullVille() {
		// Tester la lecture d'un objet avec ville = null
		assertThat(logService.readByVille(null)).isEmpty();
	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod *********************");
		if (logement != null && logement.getId() != null) {
			logService.deleteLogementById(logement.getId());
		}

	}
}
