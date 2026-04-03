package com.betacom.clienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.controllers.commerce.items.ClientiController;
import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientiControllerTest {
	@Autowired
	private ClientiController clientiC;

	@Autowired
	private IUtentiRepository utRepo;

	@Autowired
	private IClientiRepository clRepo;

	@Autowired
	private IMessaggiServices msgS;

	@Test
	@Order(1)
	public void createCliente() {
		com.betacom.persistence.entity.Utenti u = TestDataFactory.creaUtenteValido(utRepo);

		log.debug("Create cliente");
		ClientiReq req = new ClientiReq();
		req.setNome("Mario");
		req.setCognome("Rossi");
		req.setIndirizzo("Via Roma 1");
		req.setUtenteUsername(u.getUserName());
		req.setComune("Milano");
		req.setCap("20100");
		req.setProvincia("MI");
		req.setTelefono("123456789");

		ResponseEntity<Resp> resp = clientiC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp) resp.getBody();

		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
	}

	@Test
	@Order(2)
	public void getById() {
		log.debug("getById test");
		Clienti c = TestDataFactory.creaClienteValido(clRepo, utRepo);
		ResponseEntity<?> resp = clientiC.getById(c.getId());
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		ClientiDTO dto = (ClientiDTO) resp.getBody();
		Assertions.assertThat(dto.getNome()).isEqualTo(c.getNome());
	}

	@Test
	@Order(3)
	public void updateCliente() {
		log.debug("Update cliente");

		Clienti c = TestDataFactory.creaClienteValido(clRepo, utRepo);

		ClientiReq req = new ClientiReq();
		req.setId(c.getId());
		req.setNome(c.getNome());
		req.setCognome("Rossi Aggiornato");

		ResponseEntity<Resp> resp = clientiC.update(req);

		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp) resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
	}

	@Test
	@Order(4)
	public void deleteCliente() {
		log.debug("delete cliente");

		Clienti c = TestDataFactory.creaClienteValido(clRepo, utRepo);

		ResponseEntity<Resp> resp = clientiC.delete(c.getId());

		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp) resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
	}

	@Test
	@Order(5)
	public void list() {
		log.debug("Test list clienti");

		ResponseEntity<?> resp = clientiC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();

		List<ClientiDTO> lC = (List<ClientiDTO>) body;

		Assertions.assertThat(lC.size()).isGreaterThanOrEqualTo(1);
		lC.forEach(c -> log.debug(c.toString()));
	}

}
