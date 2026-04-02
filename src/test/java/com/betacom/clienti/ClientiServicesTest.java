package com.betacom.clienti;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.commerce.ClientiDTO;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.services.interfaces.commerce.items.IClientiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientiServicesTest {

	@Autowired
	private IUtentiRepository utRepo;

	@Autowired
	private IClientiServices clientiS;

	@Test
	@Order(1)
	public void createCliente() {
		TestDataFactory.creaUtenteValido(utRepo);
		log.debug("create cliente");
		try {
			ClientiReq req = new ClientiReq();
			req.setNome("Luigi");
			req.setCognome("Bianchi");
			req.setIndirizzo("Via Milano 2");
			req.setComune("Torino");
			req.setCap("10100");
			req.setProvincia("TO");
			req.setTelefono("987654321");
			req.setUtenteUsername("testUser");

			clientiS.create(req);
			
			List<ClientiDTO> lC = clientiS.findAll();
			ClientiDTO createCliente = lC.stream().filter(c -> "987654321".equals(c.getTelefono())).findFirst()
					.orElseThrow(() -> new AssertionError("Cliente non trovato"));

			Assertions.assertThat(createCliente.getCognome()).isEqualTo("Bianchi");

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Test
	@Order(2)
	public void getCliente() {
		log.debug("getById test");
		try {
			List<ClientiDTO> lC = clientiS.findAll();
			if (!lC.isEmpty()) {
				Integer id = lC.get(0).getId();
				ClientiDTO dto = clientiS.getById(id);
				Assertions.assertThat(dto).isNotNull();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Test
	@Order(3)
	public void updateCliente() {
		log.debug("update cliente test");
		try {
			List<ClientiDTO> lC = clientiS.findAll();
			if (!lC.isEmpty()) {
				Integer id = lC.get(0).getId();

				ClientiReq req = new ClientiReq();
				req.setId(id);
				req.setNome("Luigi Aggiornato");

				clientiS.update(req);

				ClientiDTO dto = clientiS.getById(id);
				Assertions.assertThat(dto.getNome()).isEqualTo("Luigi Aggiornato");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Test
	@Order(4)
	public void deleteCliente() {
		log.debug("delete cliente test");
		try {
			List<ClientiDTO> lC = clientiS.findAll();
			if (!lC.isEmpty()) {
				Integer id = lC.get(0).getId();
				clientiS.delete(id);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
