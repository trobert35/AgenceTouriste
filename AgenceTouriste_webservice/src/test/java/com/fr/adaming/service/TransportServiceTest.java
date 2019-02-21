package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;

/**
 * @author Thomas S
 * @author Maxime
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransportServiceTest {

	@Autowired
	private ITransportService transService;

	private Transport createdTransport, createdTransport2;

	// CREATE
	@Test
	public void a_createValidTransport() {
		// Tester l'insertion d'un objet valide
		createdTransport = new Transport("prestaTrans1", "villeArriveeTrans1", "villeDepartTrans1", 100d,
				typeTransEnum.avion);
		createdTransport = transService.createTransport(createdTransport);
		assertNotNull(createdTransport);

	}

	@Test
	public void b_createExistingTransport() {
		// Tester l'insertion d'un objet existant
		a_createValidTransport();
		createdTransport2 = transService.readAllTransport().get(0);
		createdTransport2 = transService.createTransport(createdTransport2);
		assertNull(createdTransport2);

	}

	@Test
	public void c_createTransportWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		createdTransport = new Transport("prestaTrans2", "villeArriveeTrans2", "villeDepartTrans2", (double) 100,
				typeTransEnum.avion);
		createdTransport.setId(null);// pour etre sur que id = null
		createdTransport = transService.createTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test
	public void d_createTransportWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		createdTransport = new Transport("prestaTrans3", "villeArriveeTrans3", "villeDepartTrans3", (double) 100,
				typeTransEnum.avion);
		createdTransport.setId(0L);// pour sur que id = 0
		createdTransport = transService.createTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test(expected = NullPointerException.class)
	public void e_createTransportNull() {
		// Tester l'insertion d'un objet null
		createdTransport = null;
		transService.createTransport(createdTransport);
	}

	// READ
	@Test
	public void f_readTransportByIdValid() {
		// Tester la lecture d'un objet valide
		a_createValidTransport();
		assertNotNull(transService.readTransportById(createdTransport.getId()));
	}

	@Test
	public void g_readTransportByUnknownId() {
		// Tester la lecture d'un objet avec id inexistant
		assertNull(transService.readTransportById(9999999L));
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void h_readTransportByIdNull() {
		// Tester la lecture d'un objet avec id = null
		transService.readTransportById(null);
	}

	@Test
	public void i_readTransportByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		assertNull(transService.readTransportById(0L));
	}

	@Test
	public void j_readAllTransportValid() {
		// Tester la lecture d'une liste d'objets valides
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		assertNotNull(createdTransport);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void k_readAllTransportEmpty() {
		// Tester la lecture d'une liste d'objets vide
		transService.readAllTransport().get(0);
	}

	// UPDATE
	@Test
	public void l_updateValidTransport() {
		// Tester la modification d'un objet valide
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		createdTransport.setPrestaTrans("prestaTransModifie");
		createdTransport = transService.updateTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test
	public void m_updateUnknownTransport() {
		// Tester la modification d'un objet inconnu
		createdTransport = new Transport();
		createdTransport.setId(999999999L);
		createdTransport = transService.updateTransport(createdTransport);
		assertNull(createdTransport);
	}

	@Test
	public void n_updateTransportWithNullId() {
		// Tester la modification d'un objet avec id = null
		createdTransport = new Transport();
		createdTransport.setId(null);
		createdTransport = transService.updateTransport(createdTransport);
		assertNull(createdTransport);
	}

	@Test
	public void o_updateTransportWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0
		createdTransport = new Transport();
		createdTransport.setId(0L);
		createdTransport = transService.updateTransport(createdTransport);
		assertNull(createdTransport);
	}

	@Test(expected = NullPointerException.class)
	public void p_updateTransportNull() {
		// Tester la modification d'un objet null
		createdTransport = null;
		transService.updateTransport(createdTransport);

	}

	// DELETE
	@Test
	public void q_deleteValidTransport() {
		// Tester la suppression d'un objet valide
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		transService.deleteTransportById(createdTransport.getId());
		createdTransport = null;

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void r_deleteUnknownTransport() {
		// Tester la suppression d'un objet inconnu
		transService.deleteTransportById(999999999L);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void s_deleteTransportWithNullId() {
		// Tester la suppression d'un objet avec id null
		transService.deleteTransportById(null);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void t_deleteTransportWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0
		transService.deleteTransportById(0L);
	}

	@Test(expected = NullPointerException.class)
	public void u_deleteTransportNull() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}

	@Test
	public void va_readTransportByValidPrestaTrans() {
		// Tester la suppression d'un objet avec prestaTrans valid
		a_createValidTransport();
		transService.readTransportByPrestaTrans(createdTransport.getPrestaTrans());

	}

	@Test
	public void vb_readTransportByUnknownPrestaTrans() {
		// Tester la suppression d'un objet avec prestaTrans unKnown
		assertThat(transService.readTransportByPrestaTrans("prestaTransInexistant")).isEmpty();
		;
	}

	@Test
	public void vc_readTransportByNullPrestaTrans() {
		// Tester la suppression d'un objet avec prestaTrans null
		assertThat(transService.readTransportByPrestaTrans(null)).isEmpty();
		;

	}

	@Test
	public void wa_readByValidPrix() {
		// Tester la lecture d'un objet avec prix valide
		a_createValidTransport();
		transService.readByPrix(createdTransport.getPrix());
	}

	@Test
	public void wb_readByUnknownPrix() {
		// Tester la suppression d'un objet avec prix unKnown
		assertThat(transService.readByPrix(8888888888888888888888888D)).isEmpty();
		;
	}

	@Test
	public void wc_readByNullPrix() {
		// Tester la suppression d'un objet avec prix null
		assertThat(transService.readByPrix(null)).isEmpty();
		;
	}

	@Test
	public void ya_readByValidTypeTrans() {
		// Tester la lecture d'un objet avec un typeTrans valide
		a_createValidTransport();
		transService.readByTypeTrans(createdTransport.getTypeTrans());
	}

	@Test
	public void yb_readByNullTypeTrans() {
		// Tester la lecture d'un objet avec un typeTrans null
		assertThat(transService.readByTypeTrans(null)).isEmpty();
		;

	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod transport *********************");
		if (createdTransport != null && createdTransport.getId() != null) {
			transService.deleteTransportById(createdTransport.getId());
			if (createdTransport2 != null && createdTransport2.getId() != null) {
				transService.deleteTransportById(createdTransport2.getId());
			}
		}
	}
}
