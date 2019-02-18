package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;

public class TransportServiceTest {

	@Autowired
	private ITransportService transService;

	private Transport CreatedTransport;

	// CREATE
	@Test
	public void a_createValidTransport() {
		// Tester l'insertion d'un objet valide
		CreatedTransport = new Transport("prestaTrans1", "villeArriveeTrans1", "villeDepartTrans1", (double) 100, typeTransEnum.avion);
		CreatedTransport = transService.createTransport(CreatedTransport);
		assertNotNull(CreatedTransport);

	}

	@Test
	public void b_createExistingTransport() {
		// Tester l'insertion d'un objet existant
		a_createValidTransport();
		Transport createdTransport2 = transService.readAllTransport().get(0);
		createdTransport2 = transService.createTransport(createdTransport2);
		assertNull(createdTransport2);

	}

	@Test
	public void c_createTransportWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		Transport createdTransport2 = new Transport("prestaTrans2", "villeArriveeTrans2", "villeDepartTrans2", (double) 100, typeTransEnum.avion);
		createdTransport2.setId(null);// pour bien enfoncer le clou et etre sur que l id est null
		createdTransport2 = transService.createTransport(createdTransport2);
		assertNotNull(createdTransport2);
	}

	@Test
	public void d_createTransportWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		Transport createdTransport = new Transport("prestaTrans3", "villeArriveeTrans3", "villeDepartTrans3", (double) 100, typeTransEnum.avion);
		createdTransport = transService.createTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test
	public void e_createTransportNull() {
		// Tester l'insertion d'un objet null

	}

	// READ
	@Test
	public void f_readTransportByIdValid() {
		// Tester la lecture d'un objet valide

	}

	@Test
	public void g_readTransportByUnknownId() {
		// Tester la lecture d'un objet non existant

	}

	@Test
	public void h_readTransportByIdNull() {
		// Tester la lecture d'un objet avec id = null

	}

	@Test
	public void i_readTransportByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0

	}

	@Test
	public void j_readAllTransportValid() {
		// Tester la lecture d'une liste d'objets valides

	}

	@Test
	public void k_readAllTransportEmpty() {
		// Tester la lecture d'une liste d'objets vide

	}

	// UPDATE
	@Test
	public void l_updateValidTransport() {
		// Tester la modification d'un objet valide

	}

	@Test
	public void m_updateUnknownTransport() {
		// Tester la modification d'un objet inconnu

	}

	@Test
	public void n_updateTransportWithNullId() {
		// Tester la modification d'un objet avec id = null

	}

	@Test
	public void o_updateTransportWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0

	}

	@Test
	public void p_updateTransportNull() {
		// Tester la modification d'un objet null

	}

	// DELETE
	@Test
	public void q_deleteValidTransport() {
		// Tester la suppression d'un objet valide

	}

	@Test
	public void r_deleteUnknownTransport() {
		// Tester la suppression d'un objet inconnu

	}

	@Test
	public void s_deleteTransportWithNullId() {
		// Tester la suppression d'un objet avec id = null

	}

	@Test
	public void t_deleteTransportWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0

	}

	@Test
	public void u_deleteTransportNull() {
		// Tester la suppression d'un objet null

	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod transport *********************");
		if (CreatedTransport != null && CreatedTransport.getId() != null) {
			transService.deleteTransportById(CreatedTransport.getId());
		}

	}
}
