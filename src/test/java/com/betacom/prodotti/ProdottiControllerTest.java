package com.betacom.prodotti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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

import com.betacom.controllers.commerce.items.ProdottiController;
import com.betacom.dto.inputs.commerce.items.ProdottiReq;
import com.betacom.dto.outputs.commerce.items.ProdottiDTO;
import com.betacom.response.Resp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottiControllerTest {

	@Autowired
	private ProdottiController prodC;

	@Test
	@Order(3)
	public void findByCodice() {
		log.debug("Test findByCodice OK");
		ResponseEntity<?> resp = prodC.findByCodice(1001L);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
		ProdottiDTO prod = (ProdottiDTO) resp.getBody();
		Assertions.assertThat(prod.getSku()).isEqualTo(1001L);
	}

	@Test
	@Order(2)
	public void findByCodiceError() {
		log.debug("Test findByCodice Error");
		ResponseEntity<?> resp = prodC.findByCodice(999999L);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		Assertions.assertThat(resp.getBody().toString()).contains("non trovato");
	}

	@Test
	@Order(1)
	public void create() {
		log.debug("Create prodotto");
		ProdottiReq req = new ProdottiReq();
		req.setSku(1001L);
		req.setNome("Test Prodotto Nuovissimo");
		req.setDescrizione("Descrizione test");
		req.setUrlImmagine("test.img");
		req.setPrezzo(new BigDecimal("19.99"));
		req.setDimensioni(new BigDecimal("10.5"));
		req.setPeso(new BigDecimal("1.2"));
		req.setStock(50);
		req.setCategoriaId(1);

		ResponseEntity<?> resp = prodC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp) resp.getBody();
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
	}

	@Test
	@Order(4)
	public void update() {
		log.debug("Update prodotto");
		ProdottiReq req = new ProdottiReq();
		req.setSku(1001L);
		req.setNome("Test Prodotto Updated");
		req.setDescrizione("Descrizione aggiornata");
		req.setPrezzo(new BigDecimal("25.00"));
		req.setStock(100);
		req.setCategoriaId(1);

		ResponseEntity<Resp> resp = prodC.update(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = resp.getBody();
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
	}

	@Test
	@Order(5)
	public void updateoErr() {
		log.debug("Update prodotto error");
		ProdottiReq req = new ProdottiReq();
		req.setSku(9999L);

		ResponseEntity<Resp> resp = prodC.update(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@Test
	@Order(6)
	public void deleteProdotto() {
		log.debug("Delete prodotto");
		ResponseEntity<Resp> resp = prodC.delete(2001);

		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = resp.getBody();
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");
	}

	@Test
	@Order(7)
	public void deleteError() {
		log.debug("Delete prodotto error");
		ResponseEntity<Resp> resp = prodC.delete(9999);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@Test
	@Order(8)
	public void list() {
		log.debug("Test list prodotti");
		ResponseEntity<?> resp = prodC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		List<ProdottiDTO> listP = (List<ProdottiDTO>) resp.getBody();
		Assertions.assertThat(listP).isNotEmpty();
		listP.forEach(p -> log.debug(p.toString()));
	}
}