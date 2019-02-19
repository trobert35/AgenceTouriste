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

import com.fr.adaming.entity.Transport;
import com.fr.adaming.enumeration.typeTransEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransportServiceTest {

	@Autowired
	private ITransportService transService;

	private Transport createdTransport, createdTransport2 ;

	// CREATE
	@Test
	public void a_createValidTransport() {
		// Tester l'insertion d'un objet valide c ,l!
		createdTransport = new Transport("prestaTrans1", "villeArriveeTrans1", "villeDepartTrans1", 100d,
				typeTransEnum.avion);
		createdTransport = transService.createTransport(createdTransport);
		assertNotNull(createdTransport);

	}

	@Test
	public void b_createExistingTransport() {
		// Tester l'insertion d'un objet existant
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		createdTransport = transService.createTransport(createdTransport);
		assertNull(createdTransport);

	}

	@Test
	public void c_createTransportWithNullId() {
		// Tester l'insertion d'un objet avec id = null
		createdTransport = new Transport();
		createdTransport.setId(null);// pour etre sur que id est null
		createdTransport = transService.createTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test
	public void d_createTransportWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		createdTransport = new Transport("prestaTrans3", "villeArriveeTrans3", "villeDepartTrans3",
				(double) 100, typeTransEnum.avion);
		createdTransport.setId(0L);// pour etre sur que id = 0
		createdTransport = transService.createTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test(expected=NullPointerException.class)
	public void e_createTransportNull() {
		// Tester l'insertion d'un objet null
		createdTransport = null;
		createdTransport = transService.createTransport(createdTransport);
	}

	// READ
	@Test (expected = NoSuchElementException.class)
	public void f_readTransportByIdValid() {
		// Tester la lecture d'un objet valide
		a_createValidTransport();
		createdTransport = transService.readTransportById(1L);
	}

	@Test (expected=NoSuchElementException.class)
	public void g_readTransportByUnknownId() {
		// Tester la lecture d'un objet non existant
		createdTransport = transService.readTransportById(99999999999L);
	}

	@Test (expected=InvalidDataAccessApiUsageException.class)
	public void h_readTransportByIdNull() {
		// Tester la lecture d'un objet avec id = null
		createdTransport = transService.readTransportById(null);
	}

	@Test (expected=NoSuchElementException.class)
	public void i_readTransportByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		createdTransport = transService.readTransportById(0L);
	}

	@Test
	public void j_readAllTransportValid() {
		// Tester la lecture d'une liste d'objets valides
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		assertNotNull(createdTransport);
	}

	@Test
	public void k_readAllTransportEmpty() {
		// Tester la lecture d'une liste d'objets vide
		createdTransport = transService.readAllTransport().get(0);
	}

	// UPDATE
	@Test
	public void l_updateValidTransport() {
		// Tester la modification d'un objet valide
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		createdTransport.setVilleArriveeTrans("villeArriveeTransModifie");
		createdTransport = transService.updateTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test
	public void m_updateUnknownTransport() {
		// Tester la modification d'un objet inconnu
		createdTransport = new Transport();
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

	@Test(expected=NullPointerException.class)
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

	@Test (expected=EmptyResultDataAccessException.class)
	public void r_deleteUnknownTransport() {
		// Tester la suppression d'un objet inconnu
		transService.deleteTransportById(99999999999L);
	}

	@Test (expected = InvalidDataAccessApiUsageException.class)
	public void s_deleteTransportWithNullId() {
		// Tester la suppression d'un objet avec id null
		transService.deleteTransportById(null);
	}

	@Test (expected=EmptyResultDataAccessException.class)
	public void t_deleteTransportWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0
		transService.deleteTransportById(0L);
	}

	@Test (expected=NullPointerException.class)
	public void u_deleteTransportNull() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}

	@Test
	public void va_readTransportByValidPrestaTrans() {
		// Tester la lecture d'un objet avec prestaTrans valide
		createdTransport = new Transport();
		transService.readTransportByPrestaTrans(createdTransport.getPrestaTrans());
		assertNotNull(createdTransport);
	}
	
	@Test
	public void vb_readTransportByUnknownPrestaTrans() {
		// Tester la lecture d'un objet avec prestaTrans unknown
		transService.readTransportByPrestaTrans("UnknownPrestaTrans");
		assertNull(createdTransport);
	}
	
	@Test 
	public void vc_readTransportByNullPrestaTrans() {
		// Tester la lecture d'un objet avec prestaTrans null
		transService.readTransportByPrestaTrans(null);
	}
	
	@Test
	public void wa_readByValidPrix() {
		// Tester la lecture d'un objet avec prix valide
		createdTransport = new Transport();
		transService.readByPrix(createdTransport.getPrix());
		assertNotNull(createdTransport);
	}
	
	@Test (expected=NullPointerException.class)
	public void wb_readByUnknownPrix() {
		// Tester la lecture d'un objet avec prix unknown
		createdTransport.setPrix(99999999999999999999D);
		transService.readByPrix(createdTransport.getPrix());
	}
	
	@Test (expected=NullPointerException.class)
	public void wc_readByNullPrix() {
		// Tester la lecture d'un objet avec prix null
		createdTransport.setPrix(null);
		transService.readByPrix(createdTransport.getPrix());
	}
	
	@Test 
	public void xa_readByValidTypeTrans() {
		// Tester la lecture d'un objet avec typeTrans valide
		createdTransport = new Transport("prestaTrans1", "villeArriveeTrans1", "villeDepartTrans1", 100d,
				typeTransEnum.avion);
		createdTransport = transService.createTransport(createdTransport);
		transService.readByTypeTrans(createdTransport.getTypeTrans());
	}
	
	@Test (expected=NullPointerException.class)
	public void xb_readByNullTypeTrans() {
		// Tester la lecture d'un objet avec typeTrans null
		createdTransport.setTypeTrans(null);
		transService.readByTypeTrans(createdTransport.getTypeTrans());
	}

	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod transport *********************");
		if (createdTransport != null && createdTransport.getId() != null) {
			transService.deleteTransportById(createdTransport.getId());
		}
	}
}
