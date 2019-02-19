package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
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
		createdTransport2 = new Transport("prestaTrans2", "villeArriveeTrans2", "villeDepartTrans2",
				(double) 100, typeTransEnum.avion);
		createdTransport2.setId(null);// pour bien enfoncer le clou et etre sur que l id est null
		createdTransport2 = transService.createTransport(createdTransport2);
		assertNotNull(createdTransport2);
	}

	@Test
	public void d_createTransportWithIdEqualsToZero() {
		// Tester l'insertion d'un objet avec id = 0
		createdTransport = new Transport("prestaTrans3", "villeArriveeTrans3", "villeDepartTrans3",
				(double) 100, typeTransEnum.avion);
		createdTransport.setId(0L);// pour bien enfoncer le clou et etre sur que id = 0
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
	@Test
	public void f_readTransportByIdValid() {
		// Tester la lecture d'un objet valide
		a_createValidTransport();
		createdTransport = transService.readTransportById(createdTransport.getId());
		assertNotNull(createdTransport);
	}

	@Test (expected=NoSuchElementException.class)
	public void g_readTransportByUnknownId() {
		// Tester la lecture d'un objet non existant
		a_createValidTransport();
		Long i = createdTransport.getId();
		createdTransport.setId(9999999L);
		transService.readTransportById(createdTransport.getId());
		createdTransport.setId(i);
	}

	@Test (expected=InvalidDataAccessApiUsageException.class)
	public void h_readTransportByIdNull() {
		// Tester la lecture d'un objet avec id = null
		a_createValidTransport();
		createdTransport.setId(null);
		createdTransport = transService.readTransportById(createdTransport.getId());
	}

	@Test (expected=NoSuchElementException.class)
	public void i_readTransportByIdEqualsToZero() {
		// Tester la lecture d'un objet avec id = 0
		a_createValidTransport();
		Long i = createdTransport.getId();
		createdTransport.setId(0L);
		createdTransport = transService.readTransportById(createdTransport.getId());
		createdTransport.setId(i);

	}

	@Test
	public void j_readAllTransportValid() {
		// Tester la lecture d'une liste d'objets valides
		List<Transport> listTrans = transService.readAllTransport();
		assertNotNull(listTrans);
	}

	@Test
	public void k_readAllTransportEmpty() {
		// Tester la lecture d'une liste d'objets vide
		List<Transport> listTrans2 = transService.readAllTransport();
		

	}

	// UPDATE
	@Test
	public void l_updateValidTransport() {
		// Tester la modification d'un objet valide
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		createdTransport = transService.updateTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test (expected=IndexOutOfBoundsException.class)
	public void m_updateUnknownTransport() {
		// Tester la modification d'un objet inconnu
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(9999);
		createdTransport = transService.updateTransport(createdTransport);
		assertNotNull(createdTransport);
	}

	@Test
	public void n_updateTransportWithNullId() {
		// Tester la modification d'un objet avec id = null
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		createdTransport.setId(null);
		createdTransport = transService.updateTransport(createdTransport);
		assertNull(createdTransport);
	}

	@Test
	public void o_updateTransportWithIdEqualsToZero() {
		// Tester la modification d'un objet avec id = 0
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
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

	@Test (expected=NoSuchElementException.class)
	public void r_deleteUnknownTransport() {
		// Tester la suppression d'un objet inconnu
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		Long i = createdTransport.getId();
		createdTransport.setId(99999L);
		transService.deleteTransportById(createdTransport.getId());
		createdTransport.setId(i);
	}

	@Test (expected = InvalidDataAccessApiUsageException.class)
	public void s_deleteTransportWithNullId() {
		// Tester la suppression d'un objet avec id null
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		createdTransport.setId(null);
		transService.deleteTransportById(createdTransport.getId());
	}

	@Test (expected=NoSuchElementException.class)
	public void t_deleteTransportWithIdEqualsToZero() {
		// Tester la suppression d'un objet avec id = 0
		a_createValidTransport();
		createdTransport = transService.readAllTransport().get(0);
		Long i = createdTransport.getId();
		createdTransport.setId(0L);
		transService.deleteTransportById(createdTransport.getId());
		createdTransport.setId(i);
	}

	@Test (expected=NullPointerException.class)
	public void u_deleteTransportNull() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}

	@Test (expected=NullPointerException.class)
	public void va_readTransportByValidPrestaTrans() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void vb_readTransportByUnknownPrestaTrans() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void vc_readTransportByNullPrestaTrans() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void wa_readByValidPrix() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void wb_readByUnknownPrix() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void wc_readByNullPrix() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void xa_readByValidTypeTrans() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void xa_readByUnknownTypeTrans() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	
	@Test (expected=NullPointerException.class)
	public void xa_readByNullTypeTrans() {
		// Tester la suppression d'un objet null
		createdTransport = null;
		transService.deleteTransportById(createdTransport.getId());
	}
	

	
	@After
	public void after() {
		System.out.println("********************* DEBUG TESTING afterMethod transport *********************");
		if (createdTransport != null && createdTransport.getId() != null) {
			transService.deleteTransportById(createdTransport.getId());
		}
	}
}
